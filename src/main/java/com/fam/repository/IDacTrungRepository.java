package com.fam.repository;

import com.fam.entity.DacTrung;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IDacTrungRepository extends JpaRepository<DacTrung, Integer> {
    @Query("SELECT DISTINCT(dt.dacTrung) from SanPham sp JOIN DacTrungSanPham dt ON sp.maSP = dt.sanPham.maSP" +
            " WHERE sp.loaiSanPham.maLoai IN :SPs AND LOWER(sp.ten) LIKE" +
            " lower(concat('%',:tenSP,'%'))")
    List<DacTrung> getDacTrungInLoaiSP(@Param("SPs") List<Integer> loaiSPs, @Param("tenSP") String tenSP);
}
