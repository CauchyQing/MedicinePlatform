package org.qingkexu.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class HospitalDTO implements Serializable {
    private Long userId;
    private Long orgId;
}
