package com.fam.repository;

import com.fam.entity.DacTrung;
import com.fam.entity.DacTrungSanPham;
import com.fam.entity.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IDacTrungSanPhamRepository extends JpaRepository<DacTrungSanPham, Integer> {
    @Query("SELECT dt.dacTrung from DacTrungSanPham dt WHERE dt.sanPham.maSP = :maSP")
    Page<DacTrung> getDacTrungBySanPham(@Param("maSP") int maSP, Pageable pageable);

    @Query("SELECT dtsp from DacTrungSanPham dtsp " +
            "WHERE dtsp.sanPham.maSP = :maSP AND dtsp.dacTrung.maDacTrung = :maDacTrung")
    DacTrungSanPham findBySanPhamAndDacTrung(@Param("maSP") int maSP,@Param("maDacTrung") int maDacTrung);

    List<DacTrungSanPham> getDacTrungSanPhamBySanPham(SanPham sp);
}
