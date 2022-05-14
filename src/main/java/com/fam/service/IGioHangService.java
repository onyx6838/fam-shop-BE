package com.fam.service;

import com.fam.dto.cart.AddToGioHangDto;
import com.fam.dto.cart.LocalCartUserSaveDto;
import com.fam.entity.GioHang;
import com.fam.entity.SanPham;
import com.fam.entity.TaiKhoan;

import java.util.List;

public interface IGioHangService {
    List<SanPham> getAllSanPhams();

    GioHang getGioHangByTK(TaiKhoan n);

    GioHang addToGioHang(AddToGioHangDto form);

    GioHang changeSoLuongSPGioHang(AddToGioHangDto form);

    GioHang removeToGioHang(AddToGioHangDto form);

    GioHang saveLocalCartWithUser(LocalCartUserSaveDto form);
}
