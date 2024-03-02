package org.qingkexu.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qingkexu.pojo.entity.FavoriteHospital;
import org.qingkexu.pojo.entity.Hospital;

import java.util.List;

@Mapper
public interface HospitalMapper {

    @Select("select * from hospitalinfo")
    List<Hospital> getAllHospital();

    @Select("select * from hospitalinfo where orgId=#{orgId}")
    Hospital getById(Long orgId);

    @Insert("insert into favorite_hospital (user_id, orgId) " +
            "values" +
            "(#{userId},#{orgId})")
    void insert(FavoriteHospital favoriteHospital);
}
