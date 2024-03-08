package org.qingkexu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.qingkexu.mapper.HospitalMapper;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.HospitalDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.FavoriteHospital;
import org.qingkexu.pojo.entity.Hospital;
import org.qingkexu.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户收藏一家医院
     * @param hospitalDTO
     * @param code
     */
    public void favorite(HospitalDTO hospitalDTO, int[] code) {
        Long userId= hospitalDTO.getUserId();
        Long orgId= hospitalDTO.getOrgId();
        if(userMapper.getByUserId(userId)==null || hospitalMapper.getById(orgId)==null){
            code[0]=1;
            return;
        }
        FavoriteHospital favoriteHospital = new FavoriteHospital();
        favoriteHospital.setUserId(userId);
        favoriteHospital.setOrgId(orgId);
        hospitalMapper.insert(favoriteHospital);
    }

    /**
     * 用户取消收藏
     * @param hospitalDTO
     * @param code
     */
    public void cancelFavorite(HospitalDTO hospitalDTO, int[] code) {
        Long userId= hospitalDTO.getUserId();
        Long orgId= hospitalDTO.getOrgId();
        if(hospitalMapper.getAFavorite(userId,orgId)==null){
            code[0]=1;
            return;
        }
        hospitalMapper.deleteFavorite(userId,orgId);
    }

    /**
     * 获取所有医院信息
     * @return
     */
    public List<Hospital> getHospitals(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        log.info("获取医院信息: "+page+"页");
        return hospitalMapper.getAllHospital(pageSize, offset);
    }

    /**
     * 用户评论
     * @param commentDTO
     * @param code
     */
    public void comment(CommentDTO commentDTO, int[] code) {
        Long userId= commentDTO.getUserId();
        Long orgId= commentDTO.getOrgId();
        if(userMapper.getByUserId(userId)==null || hospitalMapper.getById(orgId)==null){
            code[0]=1;
            return;
        }
        Comment comment =new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setOrgId(orgId);
        comment.setUserId(userId);
        comment.setPostTime(LocalDateTime.now());
        hospitalMapper.comment(comment);
    }

    /**
     * 获取一家医院的所有评论
     * @param orgId
     * @return
     */
    public List<Comment> getComment(Long orgId) {
        return hospitalMapper.getAllCommentByOrgId(orgId);
    }

    /**
     * 获取收藏的医院
     * @param userId
     * @return
     */
    public List<Long> getFavorite(Long userId){
        return hospitalMapper.getFavorite(userId);
    }

    /**
     * 通过医院id获取一家医院
     * @param orgId
     * @return
     */
    public Hospital getAHospitalById(Long orgId) {
        return hospitalMapper.getById(orgId);
    }
}
