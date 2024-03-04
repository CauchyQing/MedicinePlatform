package org.qingkexu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.qingkexu.constant.MessageConstant;
import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.FavoriteResthomeDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.Resthome;
import org.qingkexu.pojo.vo.CommentVO;
import org.qingkexu.pojo.vo.ResthomeVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.ResthomeService;
import org.qingkexu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/resthomes")
@Api("养老院相关接口")
@Slf4j
public class ResthomeController {
    @Autowired
    private ResthomeService resthomeService;

    @Autowired
    private UserService userService;

    @GetMapping("/get")
    @ApiOperation("获取所有养老院信息")
    public Result<List<ResthomeVO>> getResthomes(@RequestParam int page, @RequestParam int pageSize, @RequestParam Long userId){
        List<Resthome> resthomes=resthomeService.getResthomes(page, pageSize);
        List<Long> favorite=resthomeService.getFavorite(userId);
        if(resthomes==null){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        List<ResthomeVO> resthomeVOS=new ArrayList<ResthomeVO>();

        for(Resthome resthome:resthomes){
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
            if(favorite.contains(resthome.getOldHomeId())){
                resthomeVO.setStar(true);
            }
            resthomeVOS.add(resthomeVO);
        }
        return Result.success(resthomeVOS);
    }

    @PostMapping("/star")
    public Result favorite(@RequestBody FavoriteResthomeDTO favoriteResthomeDTO){
        int[] code = new int[1];
        resthomeService.favorite(favoriteResthomeDTO,code);
        if(code[0]==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        log.info("新增收藏：{}", favoriteResthomeDTO);
        return Result.success();
    }

    @DeleteMapping("/unstar")
    public Result unFavorite(@RequestBody FavoriteResthomeDTO favoriteResthomeDTO){
        int[] code = new int[1];
        resthomeService.cancelFavorite(favoriteResthomeDTO,code);
        if(code[0]==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        return Result.success();
    }

    @PostMapping("/comment")
    public Result comment(@RequestBody CommentDTO commentDTO){
        int[] code = new int[1];
        log.info(commentDTO.toString());
        resthomeService.comment(commentDTO,code);
        if(code[0]==1){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        log.info("新增评论：{}", commentDTO);
        return Result.success();
    }

    @GetMapping("/comment/{orgId}")
    public Result getComment(@PathVariable Long orgId){
        List<Comment> commentList =resthomeService.getComment(orgId);
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
}
