package org.qingkexu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.qingkexu.pojo.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from client_user where username=#{username}")
    User getByUsername(String username);

    @Select("select * from client_user where user_id=#{userId}")
    User getByUserId(Long userId);

    @Insert("insert into client_user (username, true_name, id, password, phone, sex, create_time, update_time) " +
            "values" +
            "(#{username},#{trueName},#{id},#{password},#{phone},#{sex},#{createTime},#{updateTime})")
    void insert(User user);

    @Update("update client_user set username=#{username}, true_name=#{trueName}, phone=#{phone}, sex=#{sex}, password=#{password}, id=#{id}, update_time=#{updateTime} where user_id=#{userId}")
    void update(User user);
}
