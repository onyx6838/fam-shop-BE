package com.fam.service.impl;

import com.fam.dto.form.DanhGiaCreate;
import com.fam.entity.DanhGia;
import com.fam.entity.SanPham;
import com.fam.repository.IDanhGiaRepository;
import com.fam.service.IDanhGiaService;
import com.fam.service.ISanPhamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author giangdm
 */
@Service
public class DanhGiaService implements IDanhGiaService {
    @Autowired
    private IDanhGiaRepository danhGiaRepository;

    @Autowired
    private ISanPhamService sanPhamService;

    @Override
    public Page<DanhGia> getAllParentDanhGiaBySanPham(Pageable pageable, int maSP) {
        return danhGiaRepository.getAllParentDanhGiaBySanPham(pageable, maSP);
    }

    @Override
    public List<DanhGia> getChildDanhGia(int maDanhGiaCha) {
        return danhGiaRepository.getChildDanhGia(maDanhGiaCha);
    }

    @Override
    public DanhGia addDanhGia(DanhGiaCreate form) {
        SanPham sp = sanPhamService.getById(form.getMaSPDanhGia());
        DanhGia danhGiaCha;
        DanhGia dg = new DanhGia();
        if (form.getMaDanhGiaCha() > 0) {
            danhGiaCha = danhGiaRepository.findById(form.getMaDanhGiaCha()).get();
            BeanUtils.copyProperties(form, dg);
            dg.setSanPham(sp);
            dg.setDanhGiaCha(danhGiaCha);
        } else {
            BeanUtils.copyProperties(form, dg);
            dg.setSanPham(sp);
        }
        return danhGiaRepository.save(dg);
    }
}
