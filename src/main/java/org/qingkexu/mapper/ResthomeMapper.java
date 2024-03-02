package org.qingkexu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.qingkexu.pojo.entity.Resthome;

import java.util.List;

@Mapper
public interface ResthomeMapper {

    @Select("select * from oldhomeinfo LIMIT #{pageSize} OFFSET #{offset}")
    List<Resthome> getAllResthome(@Param("pageSize") int pageSize, @Param("offset") int offset);
}
