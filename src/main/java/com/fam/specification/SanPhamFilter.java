package com.fam.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class SanPhamFilter {
    @JsonProperty("list-dac-trung")
    private List<Integer> dacTrungs;

    @JsonProperty("loai-sp-list")
    private List<Integer> loaiSPList;

    @JsonProperty("loai-sp")
    private Integer loaiSP;
}
