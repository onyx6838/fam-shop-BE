package com.fam.service.impl;

import com.fam.entity.CTDD;
import com.fam.entity.ChiTietPNK;
import com.fam.entity.SanPham;
import com.fam.repository.ICTDDRepository;
import com.fam.repository.IChiTietPNKRepository;
import com.fam.service.IChiTietPNKService;
import com.fam.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author giangdm
 */
@Service
public class ChiTietPNKService implements IChiTietPNKService {
    @Autowired
    private IChiTietPNKRepository chiTietPNKRepository;

    @Autowired
    private ICTDDRepository ctddRepository;

    @Autowired
    private ISanPhamService sanPhamService;

    @Override
    public Page<ChiTietPNK> getBySanPham(int maSP, Pageable pageable) {
        if (maSP != 0) {
            SanPham sp = sanPhamService.getById(maSP);
            Page<ChiTietPNK> pnks = chiTietPNKRepository.getBySanPham(sp, pageable);
            return pnks;
        }
        return null;
    }

    @Override
    public boolean checkCTDDToCTPNK(int maCTPNK, int maCTDDH) {
        try {
            CTDD ctdd = ctddRepository.findById(maCTDDH).get();
            ChiTietPNK ctpnk = chiTietPNKRepository.findById(maCTPNK).get();
            ctpnk.setCtdd(ctdd);
            chiTietPNKRepository.save(ctpnk);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
