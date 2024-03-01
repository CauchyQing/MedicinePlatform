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
public class Hospital implements Serializable {
    private long orgId;
    private String  orgName;
    private String orgPhoneNum;
    private String orgLocation;
    private String orgWorkTime;
    private String orgInfo;
    private String orgLogo;
    private String orgImg;
    //private int orgStar;
    private String orgTag1;
    private String orgTag2;
    private String orgTag3;
}
