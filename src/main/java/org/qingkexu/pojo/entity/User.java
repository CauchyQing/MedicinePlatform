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
    private Long userId;
    private String  id;
    private String username;
    private String password;
    private String trueName;
    private String phone;
    private String sex;
    private String loginIp;
    private LocalDateTime loginDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
