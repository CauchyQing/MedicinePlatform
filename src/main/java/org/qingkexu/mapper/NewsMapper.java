package org.qingkexu.mapper;

import org.apache.ibatis.annotations.*;
import org.qingkexu.pojo.entity.FavoriteNews;
import org.qingkexu.pojo.entity.News;

import java.util.List;

@Mapper
public interface NewsMapper {

    @Select("select * from org_paper LIMIT #{pageSize} OFFSET #{offset}")
    List<News> getAllNews(@Param("pageSize") int pageSize, @Param("offset") int offset);

    @Select("select * from org_paper where paper_id=#{newsId}")
    News getById(Long newsId);

    @Insert("insert into favorite_news (user_id, news_id)" +
            "values" +
            "(#{userId},#{newsId})")
    void insert(FavoriteNews favoriteNews);

    @Select("select news_id from favorite_news where user_id=#{userId}")
    List<Long> getFavorite(Long userId);

    @Delete("delete from favorite_news where user_id=#{userId} and news_id=#{newsId}")
    void deleteFavorite(Long userId, Long newsId);

    @Select("select * from favorite_news where user_id=#{userId} and news_id=#{newsId}")
    FavoriteNews getAFavorite(Long userId, Long newsId);
}
