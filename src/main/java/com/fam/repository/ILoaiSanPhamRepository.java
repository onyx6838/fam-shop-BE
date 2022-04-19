package com.fam.repository;

import com.fam.entity.LoaiSanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoaiSanPhamRepository extends JpaRepository<LoaiSanPham, Integer> {
    @Query(value = "SELECT l from LoaiSanPham l WHERE l.loaiSPConList IS NOT EMPTY")
    List<LoaiSanPham> getParentLoaiSP();

    @Query(value = "SELECT l from LoaiSanPham l WHERE l.loaiSPConList IS EMPTY")
    List<LoaiSanPham> getChildLoaiSP();
}
