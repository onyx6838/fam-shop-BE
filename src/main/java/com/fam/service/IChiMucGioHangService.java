package com.fam.service;

import com.fam.entity.ChiMucGioHang;
import com.fam.entity.GioHang;
import com.fam.entity.SanPham;

import java.util.List;

/**
 * @author giangdm
 */
public interface IChiMucGioHangService {
    List<ChiMucGioHang> getChiMucGioHangByGioHang(GioHang g);

    ChiMucGioHang getChiMucGioHangBySanPhamAndGioHang(SanPham sp, GioHang g);
}
