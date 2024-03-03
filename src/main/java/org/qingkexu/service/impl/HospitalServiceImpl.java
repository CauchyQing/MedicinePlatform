package org.qingkexu.service.impl;

import org.qingkexu.mapper.HospitalMapper;
import org.qingkexu.mapper.UserMapper;
import org.qingkexu.pojo.dto.FavoriteHospitalDTO;
import org.qingkexu.pojo.entity.FavoriteHospital;
import org.qingkexu.pojo.entity.Hospital;
import org.qingkexu.service.HospitalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        //BeanUtils.copyProperties(favoriteHospitalDTO,favoriteHospital);
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
        System.out.println("获取所有医院信息");
        return hospitalMapper.getAllHospital(pageSize, offset);
    }

    /**
     * 获取收藏的医院
     * @param userId
     * @return
     */
    public List<Long> getFavorite(Long userId){
        return hospitalMapper.getFavorite(userId);
    }
}
