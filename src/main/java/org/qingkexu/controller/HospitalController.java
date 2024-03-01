package org.qingkexu.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.qingkexu.constant.MessageConstant;
import org.qingkexu.pojo.entity.Hospital;
import org.qingkexu.pojo.vo.HospitalVO;
import org.qingkexu.result.Result;
import org.qingkexu.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/hospitals")
@Api("医院相关接口")
@Slf4j
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @GetMapping("/get")
    @ApiOperation("获取所有医院信息")
    public Result<List<HospitalVO>> getHospitals(){
        List<Hospital> hospitals=hospitalService.getHospitals();
        if(hospitals==null){
            return Result.error(MessageConstant.MESSAGE_NOT_FOUND);
        }
        List<HospitalVO> hospitalVOS = new ArrayList<HospitalVO>();
        for (Hospital hospital : hospitals) {
            String info = hospital.getOrgTag1()+'、'+hospital.getOrgTag2()+'、'+hospital.getOrgTag3();
            HospitalVO hospitalVO = HospitalVO.builder()
                    .orgId(hospital.getOrgId()).description(hospital.getOrgInfo()).image(hospital.getOrgImg())
                    .address(hospital.getOrgLocation()).name(hospital.getOrgName()).info(info)
                    .phone(hospital.getOrgPhoneNum()).build();
            hospitalVOS.add(hospitalVO);
        }
        return Result.success(hospitalVOS);
    }
}
