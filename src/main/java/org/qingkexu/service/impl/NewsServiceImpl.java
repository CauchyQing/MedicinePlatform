package org.qingkexu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.qingkexu.mapper.NewsMapper;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.FavoriteNewsDTO;
import org.qingkexu.pojo.entity.FavoriteNews;
import org.qingkexu.pojo.entity.News;
import org.qingkexu.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取新闻
     * @param page
     * @param pageSize
     * @return
     */
    public List<News> getNews(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        log.info("获取新闻信息: "+page+"页");
        return newsMapper.getAllNews(pageSize,offset);
    }

    /**
     * 收藏一条新闻
     * @param favoriteNewsDTO
     * @param code
     */
    public void favorite(FavoriteNewsDTO favoriteNewsDTO, int[] code) {
        int userId=favoriteNewsDTO.getUserId();
        Long newsId= favoriteNewsDTO.getNewsId();
        Long userIdLong=(long)userId;
        if(userMapper.getByUserId(userIdLong)==null||newsMapper.getById(newsId)==null){
            code[0]=1;
            return;
        }
        FavoriteNews favoriteNews=new FavoriteNews();
        favoriteNews.setNewsId(newsId);
        favoriteNews.setUserId(userIdLong);
        newsMapper.insert(favoriteNews);
    }

    /**
     * 取消收藏
     * @param favoriteNewsDTO
     * @param code
     */
    public void cancelFavorite(FavoriteNewsDTO favoriteNewsDTO, int[] code) {
        int userId=favoriteNewsDTO.getUserId();
        Long newsId= favoriteNewsDTO.getNewsId();
        Long userIdLong=(long)userId;
        if(newsMapper.getAFavorite(userIdLong,newsId)==null){
            code[0]=1;
            return;
        }
        newsMapper.deleteFavorite(userIdLong,newsId);
    }

    /**
     * 获得一个用户收藏的所有新闻
     * @param userId
     * @return
     */
    public List<Long> getFavorite(Long userId) {
        return newsMapper.getFavorite(userId);
    }

    /**
     * 获取一条新闻
     * @param newsId
     * @return
     */
    public News getANewById(Long newsId) {
        return newsMapper.getById(newsId);
    }
}
