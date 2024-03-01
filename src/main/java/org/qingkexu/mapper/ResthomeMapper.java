package org.qingkexu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.qingkexu.pojo.entity.Resthome;

import java.util.List;

@Mapper
public interface ResthomeMapper {

    @Select("select * from oldhomeinfo")
    List<Resthome> getAllResthome();
}
