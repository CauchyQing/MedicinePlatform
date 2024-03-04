package org.qingkexu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.qingkexu.pojo.entity.HealthInfo;
import org.qingkexu.pojo.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from client_user where username=#{username}")
    User getByUsername(String username);

    @Select("select * from client_user where user_id=#{userId}")
    User getByUserId(Long userId);

    @Select("select username from client_user where user_id=#{userIds}")
    String getNameById(Long userIds);

    @Insert("insert into client_user (username, true_name, id, password, phone, sex, create_time, update_time) " +
            "values" +
            "(#{username},#{trueName},#{id},#{password},#{phone},#{sex},#{createTime},#{updateTime})")
    void insert(User user);

    @Update("update client_user set username=#{username}, true_name=#{trueName}, phone=#{phone}, sex=#{sex}, password=#{password}, id=#{id}, update_time=#{updateTime} where user_id=#{userId}")
    void update(User user);

/*    @Insert("INSERT INTO health_info " +
            "(user_id, height, weight, blood_fat, blood_sugar, heartbeat, temperature, blood_high_pressure, blood_low_pressure, medical_history, special) " +
            "VALUES " +
            "(#{userId}, #{height}, #{weight}, #{bloodFat}, #{bloodSugar}, #{heartbeat}, #{temperature}, #{bloodHighPressure}, #{bloodLowPressure}, #{medicalHistory}, #{special})")
    void insert(HealthInfo healthInfo);*/

    @Insert("INSERT INTO health_info " +
            "(user_id) " +
            "VALUES " +
            "(#{userId})")
    void insertHealthInfo(Long userId);

    @Update("update health_info set height=#{height}, weight=#{weight}, blood_high_pressure=#{bloodHighPressure}, blood_low_pressure=#{bloodLowPressure}, blood_fat=#{bloodFat}, blood_sugar=#{bloodSugar}, heartbeat=#{heartbeat}, temperature=#{temperature}, medical_history=#{medicalHistory}, special=#{special} where user_id=#{userId}")
    void updateHealthInfo(HealthInfo healthInfo);

    @Select("select * from health_info where user_id=#{userId}")
    HealthInfo getHealthInfoByUserId(Long userId);
}
