package com.fam.service;

import com.fam.dto.form.LoaiSanPhamCreateDto;
import com.fam.dto.form.LoaiSanPhamUpdateDto;
import com.fam.dto.product.ParentCategory;
import com.fam.entity.LoaiSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ILoaiSanPhamService {
    List<LoaiSanPham> getAllLoaiSanPhams();

    Page<LoaiSanPham> getAllLoaiSanPhams(Pageable pageable);

    List<LoaiSanPham> getParentLoaiSP();

    Page<ParentCategory> getParentLoaiSPIncludeAll();    // lay tat ca la cha

    List<LoaiSanPham> getChildLoaiSP();

    boolean createLoaiSanPham(LoaiSanPhamCreateDto form);

    boolean updateLoaiSanPham(int maLSP, LoaiSanPhamUpdateDto form);
}
