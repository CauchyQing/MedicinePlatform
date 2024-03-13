package org.qingkexu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.qingkexu.constant.MessageConstant;
import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.HospitalDTO;
import org.qingkexu.pojo.dto.OrgDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.Hospital;
import org.qingkexu.pojo.vo.CommentVO;
import org.qingkexu.pojo.vo.HospitalVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.HospitalService;
import org.qingkexu.service.UserService;
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

    @Autowired
    private UserService userService;

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
    public Result favorite(@RequestBody HospitalDTO hospitalDTO){
        System.out.println(hospitalDTO);
        int code=hospitalService.favorite(hospitalDTO);
        if(code==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        log.info("新增收藏：{}", hospitalDTO);
        return Result.success();
    }

    @DeleteMapping("/unstar")
    public Result unFavorite(@RequestBody HospitalDTO hospitalDTO){
        int code=hospitalService.cancelFavorite(hospitalDTO);
        if(code==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        return Result.success();
    }

    @PostMapping("/comment")
    public Result comment(@RequestBody CommentDTO commentDTO){
        log.info(commentDTO.toString());
        int code = hospitalService.comment(commentDTO);
        if(code==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        log.info("新增评论：{}", commentDTO);
        return Result.success();
    }

    @GetMapping("/comment/{orgId}")
    public Result getComment(@PathVariable Long orgId){
        List<Comment> commentList =hospitalService.getComment(orgId);
        List<CommentVO> commentVOS =new ArrayList<CommentVO>();
        for(Comment comment : commentList){
            CommentVO commentVO =new CommentVO();
            String username=userService.getNameByUserId(comment.getUserId());
            commentVO.setComment(comment.getComment());
            commentVO.setUsername(username);
            commentVO.setPostTime(comment.getPostTime());
            commentVOS.add(commentVO);
        }
        return Result.success(commentVOS);
    }

    @PostMapping("/consult")
    public Result consult(@RequestBody OrgDTO orgDTO){

        log.info(orgDTO.toString());
        int code =userService.consultHospital(orgDTO);
        if(code==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        return Result.success();
    }
}
