package org.qingkexu.service;

import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.HospitalDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.Hospital;

import java.util.List;

public interface HospitalService {

    void favorite(HospitalDTO hospitalDTO, int[] code);

    void cancelFavorite(HospitalDTO hospitalDTO, int[] code);

    List<Long> getFavorite(Long userId);

    Hospital getAHospitalById(Long orgId);

    List<Hospital> getHospitals(int page, int pageSize);

    void comment(CommentDTO commentDTO, int[] code);

    List<Comment> getComment(Long orgId);
}
