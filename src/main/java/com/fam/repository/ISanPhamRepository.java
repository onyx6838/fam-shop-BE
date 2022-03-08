package com.fam.repository;

import com.fam.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISanPhamRepository extends JpaRepository<SanPham, Integer>, JpaSpecificationExecutor<SanPham> {
    @Query("SELECT DISTINCT(sp) FROM SanPham sp " +
            "JOIN ChiTietPNK ctpnk ON sp.maSP = ctpnk.sanPham.maSP " +
            "JOIN PhieuNhapKho pnk ON ctpnk.phieuNhapKho.maPNK = pnk.maPNK " +
            "ORDER BY pnk.thoiGian DESC")
    Page<SanPham> getNewSanPhamsOrderByThoiGianNhap(Pageable pageable);

    // Page<SanPham> getSanPhamByLoaiSanPham(Pageable pageable);

    @Query(value = "SELECT DISTINCT (dtsp.sanPham) FROM SanPham sp " +
            " LEFT JOIN DacTrungSanPham dtsp ON sp.maSP = dtsp.sanPham.maSP" +
            " WHERE dtsp.dacTrung.maDacTrung IN :dacTrungList AND sp.loaiSanPham.maLoai = :loaiSP")
    Page<SanPham> findByDacTrungsAndLoaiSP(
            @Param("dacTrungList") List<Integer> dacTrungs, Integer loaiSP, Pageable pageable);

}
