package org.qingkexu.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResthomeVO {
    private int oldHomeId;
    private String name;
    private String price;
    private String oldHomeBeds;
    private String info;
    private String oldHomeCity;
    private String address;
    private String phone;
    private String description;
    private String image;
    private boolean star;
}
