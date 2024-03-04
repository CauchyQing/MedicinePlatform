package org.qingkexu.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthInfoVO {
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
