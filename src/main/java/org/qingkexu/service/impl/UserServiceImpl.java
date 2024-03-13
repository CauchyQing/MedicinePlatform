package org.qingkexu.service.impl;

import org.qingkexu.mapper.HospitalMapper;
import org.qingkexu.mapper.ResthomeMapper;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.HealthInfoDTO;
import org.qingkexu.pojo.dto.OrgDTO;
import org.qingkexu.pojo.dto.UserDTO;
import org.qingkexu.pojo.dto.UserLoginDTO;
import org.qingkexu.pojo.entity.Consult;
import org.qingkexu.pojo.entity.HealthInfo;
import org.qingkexu.pojo.entity.Recommend;
import org.qingkexu.pojo.entity.User;
import org.qingkexu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private ResthomeMapper resthomeMapper;

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
        userMapper.insert(user);;
        userMapper.insertHealthInfo(userMapper.getByUsername(userDTO.getUsername()).getUserId());
    }

    /**
     * 修改用户信息
     * @param userDTO
     * @param code
     */
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

    /**
     * 根据id返回用户名
     * @param userIds
     * @return
     */
    public String getNameByUserId(Long userIds) {
        return userMapper.getNameById(userIds);
    }

    /**
     * 根据id返回健康信息
     * @param userId
     * @return
     */
    public HealthInfo getHealthInfoByUserId(Long userId) {
        return userMapper.getHealthInfoByUserId(userId);
    }

    /**
     * 更新用户健康信息
     * @param healthInfoDTO
     */
    public int updateHealthInfo(HealthInfoDTO healthInfoDTO) {
        HealthInfo healthInfo=userMapper.getHealthInfoByUserId(healthInfoDTO.getUserId());
        if(healthInfo==null){
            return 1;
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
        return 0;
    }

    /**
     * 插入一条健康信息
     * @param userId
     */
    public void insertHealthInfo(Long userId) {
        userMapper.insertHealthInfo(userId);
    }

    @Override
    public User getByUserId(Long userId) {
        return userMapper.getByUserId(userId);
    }

    @Override
    public int consultHospital(OrgDTO orgDTO) {
        if(userMapper.getByUserId(orgDTO.getUserId())==null||hospitalMapper.getById(orgDTO.getOrgId())==null){
            return 1;
        }
        String telephone=userMapper.getByUserId(orgDTO.getUserId()).getPhone();
        Consult consult=new Consult();
        consult.setOrgId(orgDTO.getOrgId());
        consult.setUserId(orgDTO.getUserId());
        consult.setUserTelephone(telephone);
        userMapper.insertHospitalConsult(consult);
        return 0;
    }

    @Override
    public int consultResthome(OrgDTO orgDTO) {
        if(userMapper.getByUserId(orgDTO.getUserId())==null||resthomeMapper.getById(orgDTO.getOrgId())==null){
            return 1;
        }
        String telephone=userMapper.getByUserId(orgDTO.getUserId()).getPhone();
        Consult consult=new Consult();
        consult.setOrgId(orgDTO.getOrgId());
        consult.setUserId(orgDTO.getUserId());
        consult.setUserTelephone(telephone);
        userMapper.insertResthomeConsult(consult);
        return 0;
    }

    @Override
    public List<Recommend> getRecommend(Long userId, int[] code) {
        HealthInfo healthInfo=userMapper.getHealthInfoByUserId(userId);
        List<String> symptom=new ArrayList<>();
        float height=healthInfo.getHeight();
        float weight=healthInfo.getWeight();
        int bloodHighPressure=healthInfo.getBloodHighPressure();
        int bloodLowPressure=healthInfo.getBloodLowPressure();
        float bloodFat=healthInfo.getBloodFat();
        float bloodSugar=healthInfo.getBloodSugar();
        float temperature=healthInfo.getTemperature();
        float heartbeat=healthInfo.getHeartbeat();
        if(bloodFat>5.18){
            symptom.add("血脂");
        }
        if(bloodSugar>6.1 || bloodSugar<3.9)
            symptom.add("血糖");
        if((bloodHighPressure<90||bloodLowPressure<60) || (bloodHighPressure>140&&bloodLowPressure>90))
            symptom.add("高血压");
        if(heartbeat<60||heartbeat>100)
            symptom.add("心率");
        float bmi=weight/(height*height/10000);
        if(bmi>24.9 || bmi<18.5)
            symptom.add("体重");
        if(temperature>37.0)
            symptom.add("发烧");
        symptom.add("健康");
        return userMapper.getRecommendBySymptom(symptom);
    }
}
