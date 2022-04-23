package com.fam.service;

import com.fam.dto.form.DacTrungSanPhamDeleteDto;
import com.fam.dto.form.DacTrungSanPhamAddDto;
import com.fam.entity.DacTrung;
import com.fam.entity.DacTrungSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author giangdm
 */
public interface IDacTrungSanPhamService {
    Page<DacTrungSanPham> getAllDacTrungSP(Pageable pageable);

    Page<DacTrung> getDacTrungBySanPham(int maSP, Pageable pageable);

    boolean addDacTrungToSP(DacTrungSanPhamAddDto form);

    DacTrungSanPham findBySanPhamAndDacTrung(DacTrungSanPhamDeleteDto dto);

    boolean deleteDacTrungSP(DacTrungSanPhamDeleteDto dto);
}
