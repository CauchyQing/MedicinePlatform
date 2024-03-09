package org.qingkexu.mapper;

import org.apache.ibatis.annotations.*;
import org.qingkexu.pojo.entity.HealthInfo;
import org.qingkexu.pojo.entity.Consult;
import org.qingkexu.pojo.entity.Recommend;
import org.qingkexu.pojo.entity.User;

import java.util.List;

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

    @Insert("INSERT INTO health_info " +
            "(user_id) " +
            "VALUES " +
            "(#{userId})")
    void insertHealthInfo(Long userId);

    @Update("update health_info set height=#{height}, weight=#{weight}, blood_high_pressure=#{bloodHighPressure}, blood_low_pressure=#{bloodLowPressure}, blood_fat=#{bloodFat}, blood_sugar=#{bloodSugar}, heartbeat=#{heartbeat}, temperature=#{temperature}, medical_history=#{medicalHistory}, special=#{special} where user_id=#{userId}")
    void updateHealthInfo(HealthInfo healthInfo);

    @Select("select * from health_info where user_id=#{userId}")
    HealthInfo getHealthInfoByUserId(Long userId);

    @Insert("insert into consult_hospital" +
            "(org_id, user_id, user_telephone) " +
            "values" +
            "(#{orgId},#{userId},#{userTelephone})")
    void insertHospitalConsult(Consult consult);

    @Insert("insert into consult_resthome" +
            "(org_id, user_id, user_telephone) " +
            "values" +
            "(#{orgId},#{userId},#{userTelephone})")
    void insertResthomeConsult(Consult consult);

    @Select("<script>" +
            "SELECT * FROM recommend WHERE symptom IN " +
            "<foreach item='item' index='index' collection='symptoms' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "order by rand()"+
            "</script>")
    List<Recommend> getRecommendBySymptom(@Param("symptoms") List<String> symptoms);
}
