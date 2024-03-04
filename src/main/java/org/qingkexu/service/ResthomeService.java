package org.qingkexu.service;

import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.FavoriteHospitalDTO;
import org.qingkexu.pojo.dto.FavoriteResthomeDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.Resthome;

import java.util.List;

public interface ResthomeService {
    List<Resthome> getResthomes(int currentPage, int pageSize);

    void favorite(FavoriteResthomeDTO favoriteResthomeDTO, int[] code);

    void cancelFavorite(FavoriteResthomeDTO favoriteResthomeDTO, int[] code);

    List<Long> getFavorite(Long userId);

    Resthome getAResthomeById(Long orgId);

    void comment(CommentDTO commentDTO, int[] code);

    List<Comment> getComment(Long orgId);
}
