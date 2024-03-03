package org.qingkexu.service;

import org.qingkexu.pojo.dto.FavoriteHospitalDTO;
import org.qingkexu.pojo.entity.Hospital;

import java.util.List;

public interface HospitalService {

    void favorite(FavoriteHospitalDTO favoriteHospitalDTO, int[] code);

    void cancelFavorite(FavoriteHospitalDTO favoriteHospitalDTO, int[] code);

    List<Long> getFavorite(Long userId);


    List<Hospital> getHospitals(int page, int pageSize);
}