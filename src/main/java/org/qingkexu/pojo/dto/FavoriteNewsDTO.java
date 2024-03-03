package org.qingkexu.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FavoriteNewsDTO implements Serializable {
    private int userId;
    private Long newsId;
}
