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
public class AllFavorite implements Serializable {
    private List<HospitalVO> medical;
    private List<ResthomeVO> cares;
    private List<NewsVO> news;
}
