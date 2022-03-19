package com.fam.repository;

import com.fam.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ISanPhamRepository
        extends JpaRepository<SanPham, Integer>, JpaSpecificationExecutor<SanPham> {
    @Query("SELECT DISTINCT(sp) FROM SanPham sp " +
            "JOIN ChiTietPNK ctpnk ON sp.maSP = ctpnk.sanPham.maSP " +
            "JOIN PhieuNhapKho pnk ON ctpnk.phieuNhapKho.maPNK = pnk.maPNK " +
            "ORDER BY pnk.thoiGian DESC")
    Page<SanPham> getNewSanPhamsOrderByThoiGianNhap(Pageable pageable);
}
