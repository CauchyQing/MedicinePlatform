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
public class Recommend implements Serializable {
    private Long articleId;
    private String title;
    private String publicationTime;
    private String layout;
    private String abstract1;
    private String content;
    private String link;
    private String symptom;
}
