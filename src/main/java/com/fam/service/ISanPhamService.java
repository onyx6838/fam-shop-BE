package com.fam.service;

import com.fam.dto.form.SanPhamCreateDto;
import com.fam.dto.form.SanPhamUpdateDto;
import com.fam.dto.product.CategoryDto;
import com.fam.dto.product.ParentProduct;
import com.fam.dto.product.ProductWithCategoryDto;
import com.fam.entity.SanPham;
import com.fam.specification.SanPhamFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISanPhamService {
    Page<SanPham> getAllSanPhams(Pageable pageable);

    Page<SanPham> getByDacTrungsAndLoaiSP(SanPhamFilter sanPhamFilter, Pageable pageable);

    Page<SanPham> getNewSanPhamsOrderByThoiGian(Pageable pageable);

    List<SanPham> getAllSanPhamWithoutPaging();

    SanPham getById(int id);

    Page<ProductWithCategoryDto> getByParentLoaiSP(List<CategoryDto> categories, Pageable pageable);

    Page<ParentProduct> getAllParentSanPham();

    boolean createSanPham(SanPhamCreateDto form);

    boolean updateSanPham(int maSP, SanPhamUpdateDto form);

    boolean updateMoTaSanPham(int maSP, SanPhamUpdateDto form);

    void deleteSanPham(int maSP);

    void deleteSanPhams(List<Integer> maSPs);

    void reactiveSanPham(int maSP);

    void uploadImageProfileToSanPham(MultipartFile file, int selectedId);

}
