package org.qingkexu.service;

import org.qingkexu.pojo.dto.FavoriteNewsDTO;
import org.qingkexu.pojo.entity.News;

import java.util.List;

public interface NewsService {
    List<News> getNews(int page, int pageSize);

    int favorite(FavoriteNewsDTO favoriteNewsDTO);

    int cancelFavorite(FavoriteNewsDTO favoriteNewsDTO);

    List<Long> getFavorite(Long userId);

    News getANewById(Long newsId);
}
