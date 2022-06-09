package com.fam.service;

import com.fam.entity.ChiTietPNK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author giangdm
 */
public interface IChiTietPNKService {
    Page<ChiTietPNK> getBySanPham(int maSP, Pageable pageable);

    boolean checkCTDDToCTPNK(int maCTPNK, int maCTDDH);
}
