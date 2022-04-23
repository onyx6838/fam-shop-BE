package com.fam.service;

import com.fam.dto.form.DacTrungCreateDto;
import com.fam.dto.product.FeatureDto;
import com.fam.entity.DacTrung;
import com.fam.specification.SanPhamFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface IDacTrungService {
    Page<DacTrung> getAllGrByLoai(Pageable pageable);

    Page<DacTrung> getAllDacTrungNoneGr(Pageable pageable);

    Map<String, List<DacTrung>> getAllDacTrungs();

    Page<DacTrung> getDacTrungByLoaiDacTrung(String loaiDacTrung, Pageable pageable);

    Map<String, List<FeatureDto>> getFeatureByLoaiSP(SanPhamFilter sanPhamFilter);

    Map<String, List<FeatureDto>> getFeatureWithFilterData(List<Integer> loaiSPs, String tenSP);

    boolean createDacTrung(DacTrungCreateDto dto);

    int getFeatureOrderedNumber(String loaiDacTrung);   // lấy ra thứ tự tránh bị unique key
}
