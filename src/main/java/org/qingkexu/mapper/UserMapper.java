package org.qingkexu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qingkexu.pojo.entity.User;

@Mapper
public interface UserMapper {

    @Select("select * from client_user where username=#{username}")
    User getByUsername(String username);

    @Insert("insert into client_user (username, true_name, password, phone, sex, create_time, update_time, create_by, update_by, status) " +
            "values" +
            "(#{username},#{trueName},#{password},#{phone},#{sex},#{createTime},#{updateTime},#{createBy},#{updateBy},#{status})")
    void insert(User user);
}
