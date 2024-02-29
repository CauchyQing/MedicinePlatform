package org.qingkexu.pojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel("用户注册时传递的数据")
public class UserDTO implements Serializable {

    private Long id;

    private String username;

    private String tureName;

    private String phone;

    private String sex;

    private String password;
}
