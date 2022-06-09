package com.fam.service;

import com.fam.dto.form.ThuongHieuUpdateDto;
import com.fam.entity.ThuongHieu;
import com.fam.specification.SanPhamFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IThuongHieuService {
    List<ThuongHieu> getBrandWithFilter(SanPhamFilter sanPhamFilter);

    List<ThuongHieu> getBrandWithFilterData(List<Integer> loaiSPs, String tenSP);

    Page<ThuongHieu> getAllThuongHieus(Pageable pageable);

    Page<ThuongHieu> getAllThuongHieus();

    boolean createThuongHieu(String tenThuongHieu, MultipartFile file);

    boolean updateThuongHieu(int maThuongHieu, ThuongHieuUpdateDto form);

    void uploadFileToThuongHieu(MultipartFile file, int selectedId);

    ThuongHieu getById(int id);
}
