package org.qingkexu.controller;

import lombok.extern.slf4j.Slf4j;
import org.qingkexu.pojo.entity.Hospital;
import org.qingkexu.pojo.entity.News;
import org.qingkexu.pojo.entity.Resthome;
import org.qingkexu.pojo.vo.AllFavorite;
import org.qingkexu.pojo.vo.HospitalVO;
import org.qingkexu.pojo.vo.NewsVO;
import org.qingkexu.pojo.vo.ResthomeVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.HospitalService;
import org.qingkexu.service.NewsService;
import org.qingkexu.service.ResthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/allmystar")
@Slf4j
public class GetAllStarController {
    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private ResthomeService resthomeService;

    @GetMapping("/{userId}")
    public Result getAllFavorite(@PathVariable Long userId){
        List<Long> favoriteHospitals=hospitalService.getFavorite(userId);
        List<Long> favoriteResthomes=resthomeService.getFavorite(userId);
        List<Long> favoriteNewses=newsService.getFavorite(userId);
        List<HospitalVO> hospitalVOS = new ArrayList<HospitalVO>();
        List<ResthomeVO> resthomeVOS=new ArrayList<ResthomeVO>();
        List<NewsVO> newsVOS=new ArrayList<NewsVO>();
        AllFavorite allFavorite = new AllFavorite();
        for(Long favoriteHospital:favoriteHospitals){
            Hospital hospital = hospitalService.getAHospitalById(favoriteHospital);

            String info = "";
            if(hospital.getOrgTag1()!=null){
                info += hospital.getOrgTag1();
            }
            if(hospital.getOrgTag2()!=null){
                info+='、';
                info += hospital.getOrgTag2();
            }
            if(hospital.getOrgTag3()!=null){
                info+='、';
                info += hospital.getOrgTag3();
            }

            HospitalVO hospitalVO = HospitalVO.builder()
                    .orgId(hospital.getOrgId()).description(hospital.getOrgInfo()).image(hospital.getOrgImg())
                    .address(hospital.getOrgLocation()).name(hospital.getOrgName()).info(info)
                    .phone(hospital.getOrgPhoneNum()).build();
            if(favoriteHospitals.contains(hospital.getOrgId())){
                hospitalVO.setStar(true);
            }
            hospitalVOS.add(hospitalVO);
        }
        for(Long favoriteResthome:favoriteResthomes){
            Resthome resthome=resthomeService.getAResthomeById(favoriteResthome);
            // 使用逗号分隔字符串
            String[] imageUrlArray = resthome.getOldHomeImgUrl().split(",");
            // 将数组转换为List
            List<String> imageUrlList = Arrays.asList(imageUrlArray);
            ResthomeVO resthomeVO=ResthomeVO.builder().address(resthome.getOldHomeAddress())
                    .oldHomeBeds(resthome.getOldHomeBeds()).oldHomeId(resthome.getOldHomeId())
                    .oldHomeCity(resthome.getOldHomeCity()).description(resthome.getOldHomeIntro())
                    .price(resthome.getOldHomeStartMoney()).phone(resthome.getOldHomePhone())
                    .name(resthome.getOldHomeTitle()).info(resthome.getOldHomeType())
                    .image(imageUrlList)
                    .build();
            if(favoriteResthomes.contains(resthome.getOldHomeId())){
                resthomeVO.setStar(true);
            }
            resthomeVOS.add(resthomeVO);
        }
        for(Long favoriteNews: favoriteNewses){
            News news1 = newsService.getANewById(favoriteNews);
            NewsVO newsVO=NewsVO.builder().paperContent(news1.getPaperContent()).paperId(news1.getPaperId())
                    .paperLocal(news1.getPaperLocal()).paperTime(news1.getPaperTime())
                    .paperTitle(news1.getPaperTitle()).build();
            if(favoriteNewses.contains(news1.getPaperId())){
                newsVO.setStar(true);
            }
            newsVOS.add(newsVO);
        }
        allFavorite.setMedical(hospitalVOS);
        allFavorite.setCares(resthomeVOS);
        allFavorite.setNews(newsVOS);
        return Result.success(allFavorite);
    }
}
