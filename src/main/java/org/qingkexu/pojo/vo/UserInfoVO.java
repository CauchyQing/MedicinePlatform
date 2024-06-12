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
public class UserInfoVO implements Serializable {

    @ApiModelProperty("主键值")
    private Long userId;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("姓名")
    private String trueName;

    private String sex;
    private String phone;
}
