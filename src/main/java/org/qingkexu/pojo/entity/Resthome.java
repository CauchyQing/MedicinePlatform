package org.qingkexu.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Resthome implements Serializable {
    private int oldHomeId;
    private String oldHomeTitle;
    private String oldHomeMoney;
    private String oldHomeBeds;
    private String oldHomeType;
    private String oldHomeCity;
    private String oldHomeAddress;
    private String oldHomePhone;
    private String oldHomeIntro;
    private String oldHomeImgUrl;
}
