package org.qingkexu.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class OrgDTO implements Serializable{
    private Long userId;
    private Long orgId;
}
