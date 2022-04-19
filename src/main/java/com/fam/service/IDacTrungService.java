package com.fam.service;

import com.fam.dto.product.FeatureDto;
import com.fam.entity.DacTrung;
import com.fam.specification.SanPhamFilter;

import java.util.List;
import java.util.Map;

public interface IDacTrungService {
    Map<String, List<DacTrung>> getAllDacTrungs();

    Map<String, List<FeatureDto>> getFeatureByLoaiSP(SanPhamFilter sanPhamFilter);

    Map<String, List<FeatureDto>> getFeatureWithFilterData(List<Integer> loaiSPs, String tenSP);
}
