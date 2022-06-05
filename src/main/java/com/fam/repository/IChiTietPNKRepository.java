package com.fam.repository;

import com.fam.entity.ChiTietPNK;
import com.fam.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author giangdm
 */
public interface IChiTietPNKRepository extends JpaRepository<ChiTietPNK, Integer> {
    Page<ChiTietPNK> getBySanPham(SanPham sanPham, Pageable pageable);
}
