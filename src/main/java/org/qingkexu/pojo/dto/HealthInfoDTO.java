package org.qingkexu.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthInfoDTO implements Serializable {
    private Long userId;
    private Float height;
    private Float weight;
    private Float bloodFat;
    private Float bloodSugar;
    private Float heartbeat;
    private Float temperature;
    private Integer bloodHighPressure;
    private Integer bloodLowPressure;
    private String medicalHistory;
    private String special;
}
