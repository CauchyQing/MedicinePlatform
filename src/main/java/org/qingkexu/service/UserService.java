package org.qingkexu.service;

import org.qingkexu.pojo.dto.HealthInfoDTO;
import org.qingkexu.pojo.dto.UserDTO;
import org.qingkexu.pojo.dto.UserLoginDTO;
import org.qingkexu.pojo.entity.HealthInfo;
import org.qingkexu.pojo.entity.User;

import java.util.List;

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

    /**
     * 修改用户信息
     * @param userDTO
     * @param code
     */
    void change(UserDTO userDTO, int[] code);

    String getNameByUserId(Long userId);

    HealthInfo getHealthInfoByUserId(Long userId);

    void updateHealthInfo(HealthInfoDTO healthInfoDTO, int[] code);

    void insertHealthInfo(Long userId);
}
