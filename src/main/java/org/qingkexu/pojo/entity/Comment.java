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
public class Comment implements Serializable {
    private Long id;
    private Long userId;
    private Long orgId;
    private String comment;
    private LocalDateTime postTime;
}
