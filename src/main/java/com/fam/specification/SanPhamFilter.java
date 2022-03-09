package com.fam.specification;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SanPhamFilter {
    @JsonProperty("list-dac-trung")
    private List<Integer> dacTrungs;

    @JsonProperty("loai-sp")
    private Integer loaiSP;
}
