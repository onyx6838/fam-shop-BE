package com.fam.service;

import com.fam.entity.LoaiSanPham;

import java.util.List;

public interface ILoaiSanPhamService {
    List<LoaiSanPham> getAllLoaiSanPhams();

    List<LoaiSanPham> getParentLoaiSP();
}
