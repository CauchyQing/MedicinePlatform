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
        Long userId=favoriteHospitalDTO.getUserId();
        Long orgId=favoriteHospitalDTO.getOrgId();
        if(userMapper.getByUserId(userId)==null || hospitalMapper.getById(orgId)==null){
            code[0]=1;
            return;
        }
        FavoriteHospital favoriteHospital = new FavoriteHospital();
        BeanUtils.copyProperties(favoriteHospitalDTO,favoriteHospital);
        hospitalMapper.insert(favoriteHospital);
    }

    /**
     * 获取所有医院信息
     * @return
     */
    public List<Hospital> getHospitals() {
        return hospitalMapper.getAllHospital();
    }
}
