package org.qingkexu.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HospitalVO implements Serializable {
    private long orgId;
    private String name;
    private String phone;
    private String address;
    private String rate;
    private String description;
    private String image;
    private boolean Star;
    private String info;
}