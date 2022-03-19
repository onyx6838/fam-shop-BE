package com.fam.dto.product;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FeatureDto {
    private int maDacTrung;

    private String LoaiDacTrung;

    private int thuTu;

    private String giaTri;

    private String donVi;

    private String ten;

    private String moTa;
}
