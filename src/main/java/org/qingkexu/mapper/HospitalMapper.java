package org.qingkexu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qingkexu.pojo.entity.Hospital;

import java.util.List;

@Mapper
public interface HospitalMapper {

    @Select("select * from hospitalinfo")
    List<Hospital> getAllHospital();
}
