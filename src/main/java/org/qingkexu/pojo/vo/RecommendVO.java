package org.qingkexu.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendVO implements Serializable {
    private Long articleId;
    private String title;
    private String publicationTime;
    private String layout;
    private String abstract1;
    private String content;
    private String link;
    private String symptom;
}
