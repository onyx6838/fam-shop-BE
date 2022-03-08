package com.fam.service;

import com.fam.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISanPhamService {
    Page<SanPham> getAllSanPhams(Pageable pageable);

    Page<SanPham> getByDacTrungsAndLoaiSP(List<Integer> dacTrungs, Integer loaiSP, Pageable pageable);

    Page<SanPham> getNewSanPhamsOrderByThoiGian(Pageable pageable);

    SanPham getById(int id);

}
