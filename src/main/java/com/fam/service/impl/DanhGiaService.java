package com.fam.service.impl;

import com.fam.dto.form.DanhGiaCreate;
import com.fam.entity.DanhGia;
import com.fam.entity.SanPham;
import com.fam.entity.enumerate.TrangThaiBinhLuan;
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
    public Page<DanhGia> getParentDanhGias(Pageable pageable) {
        return danhGiaRepository.getDanhGiasByDanhGiaChaIsNull(pageable);
    }

    @Override
    public List<DanhGia> getChildDanhGia(int maDanhGiaCha) {
        return danhGiaRepository.getChildDanhGia(maDanhGiaCha);
    }

    @Override
    public Page<DanhGia> getChildDanhGias(Pageable pageable, int maDanhGiaCha) {
        return danhGiaRepository.getChildDanhGia(pageable, maDanhGiaCha);
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
        dg.setQuanTriVien(false);
        dg.setTrangThai(TrangThaiBinhLuan.CONG_BO);
        return danhGiaRepository.save(dg);
    }

    @Override
    public DanhGia addQuanTriDanhGia(DanhGiaCreate form) {
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
        dg.setQuanTriVien(true);
        dg.setTrangThai(TrangThaiBinhLuan.QUAN_TRI_VIEN);
        return danhGiaRepository.save(dg);
    }

    @Override
    public DanhGia updateQuanTriDanhGia(DanhGiaCreate form, int maDanhGia) {
        DanhGia danhGiaCha = danhGiaRepository.findById(maDanhGia).get();
        danhGiaCha.setNoiDung(form.getNoiDung());
        return danhGiaRepository.save(danhGiaCha);
    }

    @Override
    public Integer findQuanTriReplyParent(int maDanhGia) {
        return danhGiaRepository.findQuanTriReply(maDanhGia);
    }

    @Override
    public boolean removeChildDanhGia(int maDanhGia) {
        try {
            DanhGia be = danhGiaRepository.findById(maDanhGia).get();
            danhGiaRepository.delete(be);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean lockChildDanhGia(int maDanhGia) {
        try {
            DanhGia be = danhGiaRepository.findById(maDanhGia).get();
            be.setTrangThai(TrangThaiBinhLuan.AN);
            danhGiaRepository.save(be);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean unlockChildDanhGia(int maDanhGia) {
        try {
            DanhGia be = danhGiaRepository.findById(maDanhGia).get();
            be.setTrangThai(TrangThaiBinhLuan.CONG_BO);
            danhGiaRepository.save(be);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean checkQuanTriReply(int maDanhGia) {
        Integer count = danhGiaRepository.checkReplyQuanTri(maDanhGia);
        return count > 0;
    }
}
