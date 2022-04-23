package com.fam.service;

import com.fam.entity.LoaiSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILoaiSanPhamService {
    List<LoaiSanPham> getAllLoaiSanPhams();

    Page<LoaiSanPham> getAllLoaiSanPhams(Pageable pageable);

    List<LoaiSanPham> getParentLoaiSP();

    List<LoaiSanPham> getChildLoaiSP();
}
