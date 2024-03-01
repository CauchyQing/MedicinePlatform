package org.qingkexu.service.impl;

import org.qingkexu.mapper.HospitalMapper;
import org.qingkexu.pojo.entity.Hospital;
import org.qingkexu.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalMapper hospitalMapper;

    /**
     * 获取所有医院信息
     * @return
     */
    public List<Hospital> getHospitals() {
        return hospitalMapper.getAllHospital();
    }
}
