package com.fam.service.impl;

import com.fam.dto.cart.AddToGioHangDto;
import com.fam.dto.cart.CartItemDto;
import com.fam.dto.cart.LocalCartUserSaveDto;
import com.fam.entity.ChiMucGioHang;
import com.fam.entity.GioHang;
import com.fam.entity.SanPham;
import com.fam.entity.TaiKhoan;
import com.fam.repository.IChiMucGioHangRepository;
import com.fam.repository.IGioHangRepository;
import com.fam.repository.ISanPhamRepository;
import com.fam.service.IChiMucGioHangService;
import com.fam.service.IGioHangService;
import com.fam.service.ISanPhamService;
import com.fam.service.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GioHangService implements IGioHangService {
    @Autowired
    private ISanPhamService sanPhamService;

    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Autowired
    private IGioHangRepository gioHangRepository;

    @Autowired
    private IChiMucGioHangRepository chiMucGioHangRepository;

    @Autowired
    private IChiMucGioHangService chiMucGioHangService;

    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Override
    public List<SanPham> getAllSanPhams() {
        return sanPhamRepository.findAll();
    }

    @Override
    public GioHang getGioHangByTK(TaiKhoan n) {
        return gioHangRepository.findByKhachHang(n);
    }

    @Override
    public GioHang addToGioHang(AddToGioHangDto form) {
        try {
            SanPham sp = sanPhamService.getById(form.getMaSP());
            TaiKhoan currentTK = taiKhoanService.findTaiKhoanByEmail(form.getEmail());
            GioHang gh = gioHangRepository.findByKhachHang(currentTK);
            if (gh == null) {
                gh = new GioHang();
                gh.setKhachHang(currentTK);
                gioHangRepository.save(gh);
            }
            ChiMucGioHang cmgh = chiMucGioHangService.getChiMucGioHangBySanPhamAndGioHang(sp, gh);
            if (cmgh == null) {
                cmgh = new ChiMucGioHang();
                cmgh.setGioHang(gh);
                cmgh.setSanPham(sp);
                cmgh.setSoLuong(form.getQty());
            } else {
                cmgh.setSoLuong(cmgh.getSoLuong() + form.getQty());
            }
            chiMucGioHangRepository.save(cmgh);
            return gh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GioHang changeSoLuongSPGioHang(AddToGioHangDto form) {
        try {
            TaiKhoan currentTK = taiKhoanService.findTaiKhoanByEmail(form.getEmail());
            GioHang gh = gioHangRepository.findByKhachHang(currentTK);
            SanPham sp = sanPhamService.getById(form.getMaSP());
            ChiMucGioHang cmgh = chiMucGioHangService.getChiMucGioHangBySanPhamAndGioHang(sp, gh);
            cmgh.setSoLuong(cmgh.getSoLuong() + form.getQty());
            if (cmgh.getSoLuong() == 0) {
                chiMucGioHangRepository.delete(cmgh);
            } else chiMucGioHangRepository.save(cmgh);
            return gh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GioHang removeToGioHang(AddToGioHangDto form) {
        try {
            TaiKhoan currentTK = taiKhoanService.findTaiKhoanByEmail(form.getEmail());
            GioHang g = gioHangRepository.findByKhachHang(currentTK);
            SanPham sp = sanPhamService.getById(form.getMaSP());
            ChiMucGioHang c = chiMucGioHangService.getChiMucGioHangBySanPhamAndGioHang(sp, g);
            chiMucGioHangRepository.delete(c);
            return g;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public GioHang saveLocalCartWithUser(LocalCartUserSaveDto form) {
        try {
            TaiKhoan currentTK = taiKhoanService.getTaiKhoanByTenTK(form.getTenTK());
            GioHang gh = gioHangRepository.findByKhachHang(currentTK);
            if (gh == null) {
                gh = new GioHang();
                gh.setKhachHang(currentTK);
                gioHangRepository.save(gh);
            }
            for (CartItemDto x : form.getCart()) {
                SanPham sp = sanPhamService.getById(x.getMaSP());
                ChiMucGioHang cmgh = chiMucGioHangService.getChiMucGioHangBySanPhamAndGioHang(sp, gh);
                if (cmgh == null) {
                    cmgh = new ChiMucGioHang();
                    cmgh.setGioHang(gh);
                    cmgh.setSanPham(sp);
                    cmgh.setSoLuong(x.getQty());
                } else {
                    cmgh.setSoLuong(cmgh.getSoLuong() + x.getQty());
                }
                chiMucGioHangRepository.save(cmgh);
            }
            return gh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
