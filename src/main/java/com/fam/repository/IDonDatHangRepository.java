package com.fam.repository;

import com.fam.entity.DonDatHang;
import com.fam.entity.enumerate.TrangThaiDonDat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDonDatHangRepository extends JpaRepository<DonDatHang, Integer>, DonDatHangRepositoryCustom {
    @Query(value = "SELECT SUM(d.tongTien) from DonDatHang d")
    int totalRevenue(); // tong doanh thu

    @Query(value = "SELECT COUNT(d.maDonDat) from DonDatHang d WHERE d.trangThai = :trangThaiDonDat")
    int countOrderWithType(@Param("trangThaiDonDat") TrangThaiDonDat trangThaiDonDat);   // thong ke tung loai voi trang thai don (enum TrangThaiDonDat
}
