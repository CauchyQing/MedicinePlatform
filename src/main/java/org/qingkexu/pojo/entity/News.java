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
public class News implements Serializable {
    private Long paperId;
    private String paperTitle;
    private String paperContent;
    private String paperTime;
    private String paperAuthor;
    private String paperLocal;
}
