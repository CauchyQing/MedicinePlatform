package org.qingkexu.service.impl;

import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.UserDTO;
import org.qingkexu.pojo.dto.UserLoginDTO;
import org.qingkexu.pojo.entity.User;
import org.qingkexu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * TODO 用户登录
     * @param userLoginDTO
     * @return
     */
    public User login(UserLoginDTO userLoginDTO) {
        return null;
    }

    /**
     * TODO 用户注册
     * @param userDTO
     */
    public void register(UserDTO userDTO) {

    }
}
