package com.fam.repository;

import com.fam.entity.SanPhamFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author giangdm
 */
public interface ISanPhamFileRepository extends JpaRepository<SanPhamFile, Integer> {
    @Query(value = "SELECT l from SanPhamFile l WHERE l.name = :name AND l.sanPham.maSP = :maSP")
    SanPhamFile getSanPhamFileByNameAndMaSP(String name, int maSP);
}
