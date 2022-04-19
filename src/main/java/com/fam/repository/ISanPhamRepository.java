package com.fam.repository;

import com.fam.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ISanPhamRepository
        extends JpaRepository<SanPham, Integer>, JpaSpecificationExecutor<SanPham> {
    @Query("SELECT DISTINCT(sp) FROM SanPham sp " +
            "JOIN ChiTietPNK ctpnk ON sp.maSP = ctpnk.sanPham.maSP " +
            "JOIN PhieuNhapKho pnk ON ctpnk.phieuNhapKho.maPNK = pnk.maPNK " +
            "ORDER BY pnk.thoiGian DESC")
    Page<SanPham> getNewSanPhamsOrderByThoiGianNhap(Pageable pageable);

    @Modifying
    @Transactional
    //@Query("DELETE FROM SanPham WHERE maSP IN(:ids)")
    @Query("UPDATE SanPham sp SET sp.trangThai = 0 WHERE sp.maSP IN(:maSPs)")
    void deleteByMaSPs(@Param("maSPs") List<Integer> maSPs);

    @Modifying
    @Transactional
    @Query("UPDATE SanPham sp SET sp.trangThai = 0 WHERE sp.maSP = :maSP")
    void deleteByMaSP(@Param("maSP") int maSP);
}
