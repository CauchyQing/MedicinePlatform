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
@ApiModel("用户登录返回的数据格式")
public class UserLoginVO implements Serializable {

    @ApiModelProperty("主键值")
    private Long userId;

    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("姓名")
    private String trueName;
}
