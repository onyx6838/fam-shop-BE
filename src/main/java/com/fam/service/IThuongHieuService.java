package com.fam.service;

import com.fam.entity.ThuongHieu;
import com.fam.specification.SanPhamFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IThuongHieuService {
    List<ThuongHieu> getBrandWithFilter(SanPhamFilter sanPhamFilter);

    List<ThuongHieu> getBrandWithFilterData(List<Integer> loaiSPs, String tenSP);

    Page<ThuongHieu> getAllThuongHieus(Pageable pageable);

    Page<ThuongHieu> getAllThuongHieus();
}
