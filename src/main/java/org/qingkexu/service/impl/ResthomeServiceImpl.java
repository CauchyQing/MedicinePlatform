package org.qingkexu.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.qingkexu.mapper.ResthomeMapper;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.CommentDTO;
import org.qingkexu.pojo.dto.ResthomeDTO;
import org.qingkexu.pojo.entity.Comment;
import org.qingkexu.pojo.entity.FavoriteResthome;
import org.qingkexu.pojo.entity.Resthome;
import org.qingkexu.service.ResthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public void favorite(ResthomeDTO resthomeDTO, int[] code) {
        Long userId= resthomeDTO.getUserId();
        Long orgId= resthomeDTO.getOrgId();
        if(userMapper.getByUserId(userId)==null||resthomeMapper.getById(orgId)==null){
            code[0]=1;
            return;
        }
        FavoriteResthome favoriteResthome=new FavoriteResthome();
        favoriteResthome.setUserId(userId);
        favoriteResthome.setOrgId(orgId);
        resthomeMapper.insert(favoriteResthome);
    }

    @Override
    public void cancelFavorite(ResthomeDTO resthomeDTO, int[] code) {
        Long userId= resthomeDTO.getUserId();
        Long orgId= resthomeDTO.getOrgId();
        if(resthomeMapper.getAFavorite(userId,orgId)==null){
            code[0]=1;
            return;
        }
        resthomeMapper.deleteFavorite(userId,orgId);
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
        Long userId= commentDTO.getUserId();
        Long orgId= commentDTO.getOrgId();
        if(userMapper.getByUserId(userId)==null || resthomeMapper.getById(orgId)==null){
            code[0]=1;
            return;
        }
        Comment comment =new Comment();
        comment.setComment(commentDTO.getComment());
        comment.setOrgId(orgId);
        comment.setUserId(userId);
        comment.setPostTime(LocalDateTime.now());
        resthomeMapper.comment(comment);
    }
    
    @Override
    public List<Comment> getComment(Long orgId) {
        return resthomeMapper.getAllCommentByOrgId(orgId);
    }
}
