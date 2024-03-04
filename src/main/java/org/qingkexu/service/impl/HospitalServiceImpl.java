package org.qingkexu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.qingkexu.mapper.HospitalMapper;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.FavoriteHospitalDTO;
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

    public void favorite(FavoriteHospitalDTO favoriteHospitalDTO, int[] code) {
        int userId=favoriteHospitalDTO.getUserId();
        Long orgId=favoriteHospitalDTO.getOrgId();
        Long userIdLong=(long) userId;
        if(userMapper.getByUserId(userIdLong)==null || hospitalMapper.getById(orgId)==null){
            code[0]=1;
            return;
        }
        FavoriteHospital favoriteHospital = new FavoriteHospital();
        favoriteHospital.setUserId(userIdLong);
        favoriteHospital.setOrgId(orgId);
        hospitalMapper.insert(favoriteHospital);
    }


    public void cancelFavorite(FavoriteHospitalDTO favoriteHospitalDTO, int[] code) {
        int userId=favoriteHospitalDTO.getUserId();
        Long orgId=favoriteHospitalDTO.getOrgId();
        Long userIdLong=(long)userId;
        if(hospitalMapper.getAFavorite(userIdLong,orgId)==null){
            code[0]=1;
            return;
        }
        hospitalMapper.deleteFavorite(userIdLong,orgId);
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

    @Override
    public void comment(CommentDTO commentDTO, int[] code) {
        int userId= commentDTO.getUserId();
        Long orgId= commentDTO.getOrgId();
        Long userIdLong=(long)userId;
        if(userMapper.getByUserId(userIdLong)==null || hospitalMapper.getById(orgId)==null){
            code[0]=1;
            return;
        }
        Comment comment =new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setOrgId(orgId);
        comment.setUserId(userIdLong);
        comment.setPostTime(LocalDateTime.now());
        hospitalMapper.comment(comment);
    }

    @Override
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

    @Override
    public Hospital getAHospitalById(Long orgId) {
        return hospitalMapper.getById(orgId);
    }
}
