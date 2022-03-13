package com.fam.service;

import com.fam.dto.product.CategoryDto;
import com.fam.dto.product.ProductWithCategoryDto;
import com.fam.entity.SanPham;
import com.fam.specification.SanPhamFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISanPhamService {
    Page<SanPham> getAllSanPhams(Pageable pageable);

    Page<SanPham> getByDacTrungsAndLoaiSP(SanPhamFilter sanPhamFilter, Pageable pageable);

    Page<SanPham> getNewSanPhamsOrderByThoiGian(Pageable pageable);

    SanPham getById(int id);

    Page<ProductWithCategoryDto> getByParentLoaiSP(List<CategoryDto> categories, Pageable pageable);
}
