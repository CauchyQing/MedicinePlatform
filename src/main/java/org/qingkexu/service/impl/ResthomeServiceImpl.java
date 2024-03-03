package org.qingkexu.service.impl;

import org.qingkexu.mapper.ResthomeMapper;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.FavoriteResthomeDTO;
import org.qingkexu.pojo.entity.FavoriteResthome;
import org.qingkexu.pojo.entity.Resthome;
import org.qingkexu.service.ResthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        System.out.println("获取所有养老院信息");
        return resthomeMapper.getAllResthome(pageSize, offset);
    }

    @Override
    public void favorite(FavoriteResthomeDTO favoriteResthomeDTO, int[] code) {
        Integer userId=favoriteResthomeDTO.getUserId();
        Integer orgId=favoriteResthomeDTO.getOrgId();
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
        Integer orgId=favoriteResthomeDTO.getOrgId();
        Long userIdLong=(long)userId;
        if(resthomeMapper.getAFavorite(userIdLong,orgId)==null){
            code[0]=1;
            return;
        }
        resthomeMapper.deleteFavorite(userIdLong,orgId);
    }

    @Override
    public List<Integer> getFavorite(Long userId) {
        return resthomeMapper.getFavorite(userId);
    }
}
