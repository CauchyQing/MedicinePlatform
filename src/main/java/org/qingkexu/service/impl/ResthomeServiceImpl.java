package org.qingkexu.service.impl;

import org.qingkexu.mapper.ResthomeMapper;
import org.qingkexu.pojo.entity.Resthome;
import org.qingkexu.service.ResthomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResthomeServiceImpl implements ResthomeService {

    @Autowired
    ResthomeMapper resthomeMapper;

    /**
     * 获取所有养老院信息
     * @return
     */
    @Override
    public List<Resthome> getResthomes(int currentPage, int pageSize) {
        int offset = (currentPage - 1) * pageSize;
        return resthomeMapper.getAllResthome(pageSize, offset);
    }
}
