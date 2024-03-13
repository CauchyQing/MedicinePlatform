package org.qingkexu.service;

import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.HospitalDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.Hospital;

import java.util.List;

public interface HospitalService {

    int favorite(HospitalDTO hospitalDTO);

    int cancelFavorite(HospitalDTO hospitalDTO);

    List<Long> getFavorite(Long userId);

    Hospital getAHospitalById(Long orgId);

    List<Hospital> getHospitals(int page, int pageSize);

    int comment(CommentDTO commentDTO);

    List<Comment> getComment(Long orgId);
}
