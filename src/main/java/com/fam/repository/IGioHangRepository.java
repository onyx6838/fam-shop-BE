package com.fam.repository;

import com.fam.entity.GioHang;
import com.fam.entity.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author giangdm
 */
public interface IGioHangRepository extends JpaRepository<GioHang, Integer> {
    GioHang findByKhachHang(TaiKhoan n);
}
