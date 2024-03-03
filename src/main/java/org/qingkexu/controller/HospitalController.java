package org.qingkexu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.qingkexu.constant.MessageConstant;
import org.qingkexu.pojo.dto.FavoriteHospitalDTO;
import org.qingkexu.pojo.entity.Hospital;
import org.qingkexu.pojo.vo.HospitalVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hospitals")
@Api("医院相关接口")
@Slf4j
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/get")
    @ApiOperation("获取所有医院信息")
    public Result<List<HospitalVO>> getHospitals(@RequestParam int page, @RequestParam int pageSize, @RequestParam Long userId){
        List<Hospital> hospitals=hospitalService.getHospitals(page, pageSize);
        List<Long> favorite = hospitalService.getFavorite(userId);
        if(hospitals==null){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        List<HospitalVO> hospitalVOS = new ArrayList<HospitalVO>();
        for (Hospital hospital : hospitals) {
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
            if(favorite.contains(hospital.getOrgId())){
                hospitalVO.setStar(true);
            }
            hospitalVOS.add(hospitalVO);
        }
        return Result.success(hospitalVOS);
    }

    @PostMapping("/star")
    public Result favorite(@RequestBody FavoriteHospitalDTO favoriteHospitalDTO){
        int[] code=new int[1];
        System.out.println(favoriteHospitalDTO);
        hospitalService.favorite(favoriteHospitalDTO, code);
        if(code[0]==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        log.info("新增收藏：{}", favoriteHospitalDTO);
        return Result.success();
    }

    @DeleteMapping("/unstar")
    public Result unFavorite(@RequestBody FavoriteHospitalDTO favoriteHospitalDTO){
        int[] code=new int[1];
        hospitalService.cancelFavorite(favoriteHospitalDTO, code);
        if(code[0]==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        return Result.success();
    }
}
