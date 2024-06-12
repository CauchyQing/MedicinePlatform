package org.qingkexu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.qingkexu.constant.MessageConstant;
import org.qingkexu.pojo.dto.HealthInfoDTO;
import org.qingkexu.pojo.dto.UserDTO;
import org.qingkexu.pojo.dto.UserLoginDTO;
import org.qingkexu.pojo.entity.HealthInfo;
import org.qingkexu.pojo.entity.Recommend;
import org.qingkexu.pojo.entity.User;
import org.qingkexu.pojo.vo.HealthInfoVO;
import org.qingkexu.pojo.vo.RecommendVO;
import org.qingkexu.pojo.vo.UserInfoVO;
import org.qingkexu.pojo.vo.UserLoginVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Api("用户相关接口")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     *  登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        int[] code = new int[1];
        User user = userService.login(userLoginDTO, code);
        if(code[0]==3){
            return Result.error(MessageConstant.PASSWORD_ERROR);
        }
        if (code[0]==2) {
            return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .trueName(user.getTrueName())
                .build();
        return Result.success(userLoginVO);
    }

    /**
     * 退出
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("用户退出")
    public Result<String> logout() {
        return Result.success();
    }

    /**
     * 用户注册
     * @param userDTO
     * @return
     */
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result save(@RequestBody UserDTO userDTO){
        int[] code=new int[1];
        log.info(userDTO.toString());
        userService.register(userDTO,code);
        if(code[0]==1){
            return Result.error(MessageConstant.USERNAME_ALREADY_EXISTS);
        }
        log.info("新增用户：{}", userDTO);
        return Result.success();
    }

    /**
     * 修改用户信息
     * @param userDTO
     * @return
     */
    @PutMapping("/update")
    @ApiOperation("修改用户信息")
    public Result update(@RequestBody UserDTO userDTO){
        int[] code=new int[1];
        userService.change(userDTO,code);
        if (code[0]==1){
            return Result.error(MessageConstant.EDIT_FAILED);
        }
        log.info("修改用户：{}", userDTO);
        return Result.success();
    }

    @GetMapping("/health/{userId}")
    public Result getHealthInfo(@PathVariable Long userId){
        HealthInfo healthInfo=userService.getHealthInfoByUserId(userId);

        if(healthInfo==null){
            log.info("没找到健康信息");
            userService.insertHealthInfo(userId);
            healthInfo=userService.getHealthInfoByUserId(userId);
        }
        HealthInfoVO healthInfoVO=HealthInfoVO.builder().bloodFat(healthInfo.getBloodFat())
                .bloodSugar(healthInfo.getBloodSugar()).bloodHighPressure(healthInfo.getBloodHighPressure())
                .bloodLowPressure(healthInfo.getBloodLowPressure()).heartbeat(healthInfo.getHeartbeat())
                .height(healthInfo.getHeight()).medicalHistory(healthInfo.getMedicalHistory())
                .special(healthInfo.getSpecial()).temperature(healthInfo.getTemperature()).weight(healthInfo.getWeight()).build();
        return Result.success(healthInfoVO);
    }

    @PostMapping("/health/update")
    public Result updateHealthInfo(@RequestBody HealthInfoDTO healthInfoDTO){
        log.info(healthInfoDTO.toString());

        int code=userService.updateHealthInfo(healthInfoDTO);
        if(code==1){
            return Result.error(MessageConstant.EDIT_FAILED);
        }
        return Result.success();
    }

    @GetMapping("/recommend/{userId}")
    public Result getRecommend(@PathVariable Long userId){
        int[] code=new int[1];
        List<RecommendVO> recommendVOS=new ArrayList<RecommendVO>();
        List<Recommend> recommends = userService.getRecommend(userId,code);
        if(code[0]==1){
            return Result.error(MessageConstant.RECOMMEND_ERROR);
        }
        for(Recommend recommend:recommends){
            RecommendVO recommendVO=RecommendVO.builder().symptom(recommend.getSymptom())
                    .abstract1(recommend.getAbstract1()).articleId(recommend.getArticleId())
                    .content(recommend.getContent()).layout(recommend.getLayout()).link(recommend.getLink())
                    .publicationTime(recommend.getPublicationTime()).title(recommend.getTitle()).build();
            recommendVOS.add(recommendVO);
        }
        return Result.success(recommendVOS);
    }

    @GetMapping("/getuserinfo/{userId}")
    public Result getUserInfo(@PathVariable Long userId){
        User user = userService.getByUserId(userId);

        if(user==null){
            log.info("无该用户");
        }
        UserInfoVO userInfoVO = UserInfoVO.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .trueName(user.getTrueName())
                .sex(user.getSex())
                .phone(user.getPhone())
                .build();
        return Result.success(userInfoVO);
    }
}
