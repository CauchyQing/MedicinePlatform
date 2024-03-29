package org.qingkexu.pojo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommentDTO implements Serializable {
    private Long userId;
    private Long orgId;
    private String comment;
}
