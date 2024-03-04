package org.qingkexu.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HealthInfo implements Serializable {
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
