package com.fam.repository;

import com.fam.entity.ChiMucGioHang;
import com.fam.entity.GioHang;
import com.fam.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author giangdm
 */
public interface IChiMucGioHangRepository extends JpaRepository<ChiMucGioHang, Integer> {
    ChiMucGioHang findBySanPhamAndGioHang(SanPham sp, GioHang g);

    List<ChiMucGioHang> findByGioHang(GioHang g);
}
