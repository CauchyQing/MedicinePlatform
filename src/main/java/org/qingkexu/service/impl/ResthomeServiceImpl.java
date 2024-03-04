package org.qingkexu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.qingkexu.mapper.ResthomeMapper;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.FavoriteResthomeDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.FavoriteResthome;
import org.qingkexu.pojo.entity.Resthome;
import org.qingkexu.service.ResthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ResthomeServiceImpl implements ResthomeService {

    @Autowired
    ResthomeMapper resthomeMapper;

    @Autowired
    UserMapper userMapper;

    /**
     * 获取所有养老院信息
     * @return
     */
    @Override
    public List<Resthome> getResthomes(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        log.info("获取养老院信息: "+currentPage+"页");
        return resthomeMapper.getAllResthome(pageSize, offset);
    }

    @Override
    public void favorite(FavoriteResthomeDTO favoriteResthomeDTO, int[] code) {
        Integer userId=favoriteResthomeDTO.getUserId();
        Long orgId=favoriteResthomeDTO.getOrgId();
        Long userIdLong=(long)userId;
        if(userMapper.getByUserId(userIdLong)==null||resthomeMapper.getById(orgId)==null){
            code[0]=1;
            return;
        }
        FavoriteResthome favoriteResthome=new FavoriteResthome();
        favoriteResthome.setUserId(userIdLong);
        favoriteResthome.setOrgId(orgId);
        resthomeMapper.insert(favoriteResthome);
    }

    @Override
    public void cancelFavorite(FavoriteResthomeDTO favoriteResthomeDTO, int[] code) {
        int userId=favoriteResthomeDTO.getUserId();
        Long orgId=favoriteResthomeDTO.getOrgId();
        Long userIdLong=(long)userId;
        if(resthomeMapper.getAFavorite(userIdLong,orgId)==null){
            code[0]=1;
            return;
        }
        resthomeMapper.deleteFavorite(userIdLong,orgId);
    }

    @Override
    public List<Long> getFavorite(Long userId) {
        return resthomeMapper.getFavorite(userId);
    }

    @Override
    public Resthome getAResthomeById(Long orgId) {
        return resthomeMapper.getById(orgId);
    }

    @Override
    public void comment(CommentDTO commentDTO, int[] code) {
        int userId= commentDTO.getUserId();
        Long orgId= commentDTO.getOrgId();
        Long userIdLong=(long)userId;
    }

    /*    @Override
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
    }*/
    @Override
    public List<Comment> getComment(Long orgId) {
        return null;
    }
}
