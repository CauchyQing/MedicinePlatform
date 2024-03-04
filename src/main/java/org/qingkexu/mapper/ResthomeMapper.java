package org.qingkexu.mapper;

import org.apache.ibatis.annotations.*;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.FavoriteResthome;
import org.qingkexu.pojo.entity.Resthome;

import java.util.List;

@Mapper
public interface ResthomeMapper {

    @Select("select * from oldhomeinfo LIMIT #{pageSize} OFFSET #{offset}")
    List<Resthome> getAllResthome(@Param("pageSize") int pageSize, @Param("offset") int offset);

    @Select("select resthome_id from favorite_resthome where user_id=#{userId}")
    List<Long> getFavorite(Long userId);

    @Delete("delete from favorite_resthome where user_id=#{userId} and resthome_id=#{orgId}")
    void deleteFavorite(Long userId, Long orgId);

    @Insert("insert into favorite_resthome (user_id, resthome_id) " +
            "values" +
            "(#{userId},#{orgId})")
    void insert(FavoriteResthome favoriteResthome);

    @Select("select * from oldhomeinfo where oldHomeId=#{orgId}")
    Resthome getById(Long orgId);

    @Select("select * from favorite_resthome where user_id=#{userId} and resthome_id=#{orgId}")
    FavoriteResthome getAFavorite(Long userId, Long orgId);

    @Insert("insert into comment_resthome (user_id, org_id, comment, post_time) values (#{userId}, #{orgId}, #{comment}, #{postTime})")
    void comment(Comment comment);

    @Select("select * from comment_resthome where org_id=#{orgId}")
    List<Comment> getAllCommentByOrgId(Long orgId);
}
