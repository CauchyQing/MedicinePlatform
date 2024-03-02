package org.qingkexu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.qingkexu.constant.MessageConstant;
import org.qingkexu.pojo.dto.UserDTO;
import org.qingkexu.pojo.dto.UserLoginDTO;
import org.qingkexu.pojo.entity.User;
import org.qingkexu.pojo.vo.UserLoginVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
//        User user= userService.getByUserId(userDTO);
//        UserLoginVO userLoginVO = UserLoginVO.builder()
//                .userId(user.getUserId())
//                .username(user.getUsername())
//                .trueName(user.getTrueName())
//                .build();
        log.info("修改用户：{}", userDTO);
        return Result.success();
    }
}
