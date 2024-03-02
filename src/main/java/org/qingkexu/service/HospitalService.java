package org.qingkexu.service;

import org.qingkexu.pojo.dto.FavoriteHospitalDTO;
import org.qingkexu.pojo.entity.FavoriteHospital;
import org.qingkexu.pojo.entity.Hospital;
import org.springframework.beans.BeanUtils;

import java.util.List;

public interface HospitalService {
    List<Hospital> getHospitals();

    public void favorite(FavoriteHospitalDTO favoriteHospitalDTO, int[] code);
}
