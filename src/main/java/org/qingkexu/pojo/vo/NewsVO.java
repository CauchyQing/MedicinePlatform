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
public class NewsVO implements Serializable {
    private Long paperId;
    private String paperTitle;
    private String paperContent;
    private String paperTime;
    private String paperLocal;
    private boolean star;
}
