package org.qingkexu.service;

import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.ResthomeDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.Resthome;

import java.util.List;

public interface ResthomeService {
    List<Resthome> getResthomes(int currentPage, int pageSize);

    void favorite(ResthomeDTO resthomeDTO, int[] code);

    void cancelFavorite(ResthomeDTO resthomeDTO, int[] code);

    List<Long> getFavorite(Long userId);

    Resthome getAResthomeById(Long orgId);

    void comment(CommentDTO commentDTO, int[] code);

    List<Comment> getComment(Long orgId);
}
