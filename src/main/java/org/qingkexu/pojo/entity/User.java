package org.qingkexu.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private long userId;
    private String  id;
    private String username;
    private String password;
    private String trueName;
    private String phone;
    private char sex;
    private char status;
    private String loginIp;
    private LocalDateTime loginDate;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;
}
