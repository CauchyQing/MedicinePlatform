package org.qingkexu.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FavoriteResthomeDTO implements Serializable {
    private int userId;
    private Integer orgId;
}