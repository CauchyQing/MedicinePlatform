package org.qingkexu.service.impl;

import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.HealthInfoDTO;
import org.qingkexu.pojo.dto.UserDTO;
import org.qingkexu.pojo.dto.UserLoginDTO;
import org.qingkexu.pojo.entity.HealthInfo;
import org.qingkexu.pojo.entity.User;
import org.qingkexu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     *  用户登录
     * @param userLoginDTO
     * @return
     */
    public User login(UserLoginDTO userLoginDTO, int[] code) {
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        //根据用户名查询数据库中的数据
        User user = userMapper.getByUsername(username);

        //处理异常
        if(user==null){
            code[0]=2;
            //throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
            return null;
        }

        // 密码对比md5
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if(!password.equals(user.getPassword())){
            code[0]=3;
            //throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
            return null;
        }

        //返回实体对象
        return user;
    }

    /**
     * 用户注册
     *
     * @param userDTO
     * @param code
     */
    public void register(UserDTO userDTO, int[] code) {
        if(userMapper.getByUsername(userDTO.getUsername())!=null){
            code[0]=1;
            return;
        }
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setId(userDTO.getId());
        user.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
        user.setPhone(userDTO.getPhone());
        user.setSex(userDTO.getSex());
        user.setTrueName(userDTO.getTrueName());
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insertHealthInfo(userDTO.getUserId());
        userMapper.insert(user);
    }


    public void change(UserDTO userDTO, int[] code) {
        User user=userMapper.getByUserId(userDTO.getUserId());
        if(user==null){
            code[0]=1;
            return;
        }
        if(userDTO.getUsername()!=null)
            user.setUsername(userDTO.getUsername());
        if(userDTO.getId()!=null)
            user.setId(userDTO.getId());
        if(userDTO.getPassword()!=null)
            user.setPassword(DigestUtils.md5DigestAsHex(userDTO.getPassword().getBytes()));
        if(userDTO.getPhone()!=null)
            user.setPhone(userDTO.getPhone());
        if(userDTO.getSex()!=null)
            user.setSex(userDTO.getSex());
        if(userDTO.getTrueName()!=null)
            user.setTrueName(userDTO.getTrueName());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    public String getNameByUserId(Long userIds) {
        return userMapper.getNameById(userIds);
    }

    @Override
    public HealthInfo getHealthInfoByUserId(Long userId) {
        return userMapper.getHealthInfoByUserId(userId);
    }

    @Override
    public void updateHealthInfo(HealthInfoDTO healthInfoDTO, int[] code) {
        HealthInfo healthInfo=userMapper.getHealthInfoByUserId(healthInfoDTO.getUserId());
        if(healthInfo==null){
            code[0]=1;
            return;
        }
        if(healthInfoDTO.getBloodFat()!=null)
            healthInfo.setBloodFat(healthInfoDTO.getBloodFat());
        if(healthInfoDTO.getBloodHighPressure()!=null)
            healthInfo.setBloodHighPressure(healthInfoDTO.getBloodHighPressure());
        if(healthInfoDTO.getBloodLowPressure()!=null)
            healthInfo.setBloodLowPressure(healthInfoDTO.getBloodLowPressure());
        if(healthInfoDTO.getHeartbeat()!=null)
            healthInfo.setHeartbeat(healthInfoDTO.getHeartbeat());
        if(healthInfoDTO.getBloodSugar()!=null)
            healthInfo.setBloodSugar(healthInfoDTO.getBloodSugar());
        if(healthInfoDTO.getHeight()!=null)
            healthInfo.setHeight(healthInfoDTO.getHeight());
        if(healthInfoDTO.getWeight()!=null)
            healthInfo.setWeight(healthInfoDTO.getWeight());
        if(healthInfoDTO.getMedicalHistory()!=null)
            healthInfo.setMedicalHistory(healthInfoDTO.getMedicalHistory());
        if(healthInfoDTO.getSpecial()!=null)
            healthInfo.setSpecial(healthInfoDTO.getSpecial());
        if(healthInfoDTO.getTemperature()!=null)
            healthInfo.setTemperature(healthInfoDTO.getTemperature());

        userMapper.updateHealthInfo(healthInfo);
    }

    @Override
    public void insertHealthInfo(Long userId) {
        userMapper.insertHealthInfo(userId);
    }
}
