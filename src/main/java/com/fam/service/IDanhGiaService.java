package com.fam.service;

import com.fam.dto.form.DanhGiaCreate;
import com.fam.entity.DanhGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author giangdm
 */
public interface IDanhGiaService {
    Page<DanhGia> getAllParentDanhGiaBySanPham(Pageable pageable, int maSP);

    List<DanhGia> getChildDanhGia(int maDanhGiaCha);

    DanhGia addDanhGia(DanhGiaCreate form);
}
