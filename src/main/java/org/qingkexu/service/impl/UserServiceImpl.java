package org.qingkexu.service.impl;

import org.qingkexu.constant.MessageConstant;
import org.qingkexu.constant.StatusConstant;
import org.qingkexu.exception.AccountNotFoundException;
import org.qingkexu.exception.PasswordErrorException;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.UserDTO;
import org.qingkexu.pojo.dto.UserLoginDTO;
import org.qingkexu.pojo.entity.User;
import org.qingkexu.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.security.auth.login.AccountLockedException;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *  用户登录
     * @param userLoginDTO
     * @return
     */
    public User login(UserLoginDTO userLoginDTO) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        //处理异常
        if(user==null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        // 密码对比md5
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(user.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        //返回实体对象
        return user;
    }

    /**
     *  用户注册
     * @param userDTO
     */
    public void register(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
        user.setPhone(userDTO.getPhone());
        user.setSex(userDTO.getSex());
        user.setTrueName(userDTO.getTrueName());
        user.setUsername(userDTO.getUsername());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }
}
