package org.qingkexu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.qingkexu.pojo.dto.UserDTO;
import org.qingkexu.pojo.dto.UserLoginDTO;
import org.qingkexu.pojo.vo.UserLoginVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Api("用户相关接口")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * TODO 登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        return null;
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
        log.info("新增用户：{}", userDTO);
        userService.register(userDTO);
        return Result.success();
    }
}
