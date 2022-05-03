package com.fam.repository;

import com.fam.entity.DacTrung;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query("SELECT d from DacTrung d GROUP BY d.LoaiDacTrung")
    Page<DacTrung> getAllGrByLoai(Pageable pageable);

    @Query("SELECT d from DacTrung d WHERE d.LoaiDacTrung = :loaiDacTrung")
    Page<DacTrung> getDacTrungByLoaiDacTrung(@Param("loaiDacTrung") String loaiDacTrung, Pageable pageable);

    @Query("SELECT MAX(d.thuTu) from DacTrung d WHERE d.LoaiDacTrung = :loaiDacTrung")
    int getFeatureOrderedNumber(@Param("loaiDacTrung") String loaiDacTrung);

    @Query("SELECT COUNT(d) from DacTrung d WHERE d.LoaiDacTrung = :loaiDacTrung")
    int checkExistLoaiDacTrung(@Param("loaiDacTrung") String loaiDacTrung);
}
