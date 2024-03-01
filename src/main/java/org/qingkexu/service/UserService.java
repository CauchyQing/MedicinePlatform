package org.qingkexu.service;

import org.qingkexu.pojo.dto.UserDTO;
import org.qingkexu.pojo.dto.UserLoginDTO;
import org.qingkexu.pojo.entity.User;

public interface UserService {

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    User login(UserLoginDTO userLoginDTO, int[] code);

    /**
     * 用户注册
     *
     * @param userDTO
     * @param code
     */
    void register(UserDTO userDTO, int[] code);
}
