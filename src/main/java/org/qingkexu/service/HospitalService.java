package org.qingkexu.service;

import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.FavoriteHospitalDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.Hospital;

import java.util.List;

public interface HospitalService {

    void favorite(FavoriteHospitalDTO favoriteHospitalDTO, int[] code);

    void cancelFavorite(FavoriteHospitalDTO favoriteHospitalDTO, int[] code);

    List<Long> getFavorite(Long userId);

    Hospital getAHospitalById(Long orgId);

    List<Hospital> getHospitals(int page, int pageSize);

    void comment(CommentDTO commentDTO, int[] code);

    List<Comment> getComment(Long orgId);
}
