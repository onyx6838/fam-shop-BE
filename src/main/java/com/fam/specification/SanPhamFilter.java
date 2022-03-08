package com.fam.specification;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SanPhamFilter {
    private List<Integer> dacTrungs;

    private Integer loaiSP;
}
