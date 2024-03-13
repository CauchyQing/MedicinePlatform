package org.qingkexu.service;

import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.ResthomeDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.Resthome;

import java.util.List;

public interface ResthomeService {
    List<Resthome> getResthomes(int currentPage, int pageSize);

    int favorite(ResthomeDTO resthomeDTO);

    int cancelFavorite(ResthomeDTO resthomeDTO);

    List<Long> getFavorite(Long userId);

    Resthome getAResthomeById(Long orgId);

    int comment(CommentDTO commentDTO);

    List<Comment> getComment(Long orgId);
}
