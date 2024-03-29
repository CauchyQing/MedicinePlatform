package org.qingkexu.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResthomeVO implements Serializable {
    private Long oldHomeId;
    private String name;
    private Integer price;
    private String oldHomeBeds;
    private String info;
    private String oldHomeCity;
    private String address;
    private String phone;
    private String description;
    private List<String> image;
    private boolean star;
}
