package com.fam.service.impl;

import com.fam.entity.ChiMucGioHang;
import com.fam.entity.GioHang;
import com.fam.entity.SanPham;
import com.fam.repository.IChiMucGioHangRepository;
import com.fam.service.IChiMucGioHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author giangdm
 */
@Service
public class ChiMucGioHangService implements IChiMucGioHangService {
    @Autowired
    private IChiMucGioHangRepository chiMucGioHangRepository;

    @Override
    public List<ChiMucGioHang> getChiMucGioHangByGioHang(GioHang g) {
        return chiMucGioHangRepository.findByGioHang(g);
    }

    @Override
    public ChiMucGioHang getChiMucGioHangBySanPhamAndGioHang(SanPham sp, GioHang g) {
        return chiMucGioHangRepository.findBySanPhamAndGioHang(sp, g);
    }
}
