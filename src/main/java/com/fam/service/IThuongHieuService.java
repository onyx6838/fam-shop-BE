package com.fam.service;

import com.fam.entity.ThuongHieu;
import com.fam.specification.SanPhamFilter;

import java.util.List;

public interface IThuongHieuService {
    List<ThuongHieu> getBrandWithFilterData(SanPhamFilter sanPhamFilter);
}
