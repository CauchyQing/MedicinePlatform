package org.qingkexu.service.impl;

import org.qingkexu.mapper.NewsMapper;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.FavoriteNewsDTO;
import org.qingkexu.pojo.entity.News;
import org.qingkexu.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<News> getNews(int page, int pageSize) {
        return null;
    }

    @Override
    public void favorite(FavoriteNewsDTO favoriteNewsDTO, int[] code) {

    }

    @Override
    public void cancelFavorite(FavoriteNewsDTO favoriteNewsDTO, int[] code) {

    }

    @Override
    public List<Long> getFavorite(Long userId) {
        return null;
    }
}
