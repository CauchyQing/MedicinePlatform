package org.qingkexu.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.qingkexu.constant.MessageConstant;
import org.qingkexu.pojo.dto.FavoriteNewsDTO;
import org.qingkexu.pojo.entity.News;
import org.qingkexu.pojo.vo.NewsVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/news")
@Api("新闻相关接口")
public class NewsController {
    @Autowired
    private NewsService newsService;

    @GetMapping("/get")
    public Result<List<NewsVO>> getNews(@RequestParam int page, @RequestParam int pageSize, @RequestParam Long userId){
        List<News> news=newsService.getNews(page,pageSize);
        List<Long> favorite = newsService.getFavorite(userId);
        if(news==null){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        List<NewsVO> newsVOS=new ArrayList<NewsVO>();
        for(News news1:news){
            NewsVO newsVO=NewsVO.builder().paperContent(news1.getPaperContent()).paperId(news1.getPaperId())
                    .paperLocal(news1.getPaperLocal()).paperTime(news1.getPaperTime())
                    .paperTitle(news1.getPaperTitle()).build();
            if(favorite.contains(news1.getPaperId())){
                newsVO.setStar(true);
            }
            newsVOS.add(newsVO);
        }
        return Result.success(newsVOS);
    }

    @PostMapping("/star")
    public Result favorite(@RequestBody FavoriteNewsDTO favoriteNewsDTO){
        int code = newsService.favorite(favoriteNewsDTO);
        if(code==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        log.info("新增收藏：{}", favoriteNewsDTO);
        return Result.success();
    }

    @DeleteMapping("/unstar")
    public Result unFavorite(@RequestBody FavoriteNewsDTO favoriteNewsDTO){
        int code=newsService.cancelFavorite(favoriteNewsDTO);
        if(code==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        return Result.success();
    }
}
