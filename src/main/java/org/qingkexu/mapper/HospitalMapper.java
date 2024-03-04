package org.qingkexu.mapper;

import org.apache.ibatis.annotations.*;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.FavoriteHospital;
import org.qingkexu.pojo.entity.Hospital;

import java.util.List;

@Mapper
public interface HospitalMapper {

    @Select("select * from hospitalinfo LIMIT #{pageSize} OFFSET #{offset}")
    List<Hospital> getAllHospital(@Param("pageSize") int pageSize, @Param("offset") int offset);

    @Select("select * from hospitalinfo where orgId=#{orgId}")
    Hospital getById(Long orgId);

    @Insert("insert into favorite_hospital (user_id, orgId) " +
            "values" +
            "(#{userId},#{orgId})")
    void insert(FavoriteHospital favoriteHospital);

    @Select("select orgId from favorite_hospital where user_id=#{userId}")
    List<Long> getFavorite(Long userId);

    @Delete("delete from favorite_hospital where user_id=#{userId} and orgId=#{orgId}")
    void deleteFavorite(Long userId, Long orgId);

    @Select("select * from favorite_hospital where user_id=#{userId} and orgId=#{orgId}")
    FavoriteHospital getAFavorite(Long userId, Long orgId);

    @Insert("insert into comment_hospital (user_id, org_id, comment, post_time) values (#{userId}, #{orgId}, #{comment}, #{postTime})")
    void comment(Comment comment);

    @Select("select * from comment_hospital where org_id=#{orgId}")
    List<Comment> getAllCommentByOrgId(Long orgId);
}
