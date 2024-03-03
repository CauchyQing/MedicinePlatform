package org.qingkexu.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class FavoriteNewsDTO implements Serializable {
    private Long userId;
    private Long newsId;
}
