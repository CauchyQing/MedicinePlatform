package org.qingkexu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.qingkexu.constant.MessageConstant;
import org.qingkexu.pojo.entity.Resthome;
import org.qingkexu.pojo.vo.ResthomeVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.ResthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/resthomes")
@Api("养老院相关接口")
@Slf4j
public class ResthomeController {
    @Autowired
    private ResthomeService resthomeService;

    @GetMapping("/get")
    @ApiOperation("获取所有养老院信息")
    public Result<List<ResthomeVO>> getResthomes(@RequestParam int page, @RequestParam int pageSize, @RequestParam Long userId){
        List<Resthome> resthomes=resthomeService.getResthomes(page, pageSize);
        if(resthomes==null){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        List<ResthomeVO> resthomeVOS=new ArrayList<ResthomeVO>();
        for(Resthome resthome:resthomes){
            ResthomeVO resthomeVO=ResthomeVO.builder().address(resthome.getOldHomeAddress())
                    .oldHomeBeds(resthome.getOldHomeBeds()).oldHomeId(resthome.getOldHomeId())
                    .oldHomeCity(resthome.getOldHomeCity()).description(resthome.getOldHomeIntro())
                    .price(resthome.getOldHomeMoney()).phone(resthome.getOldHomePhone())
                    .name(resthome.getOldHomeTitle()).info(resthome.getOldHomeType())
                    .image(resthome.getOldHomeImgUrl()).build();
            resthomeVOS.add(resthomeVO);
        }
        return Result.success(resthomeVOS);
    }
}
