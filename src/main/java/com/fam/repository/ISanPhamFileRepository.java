package com.fam.repository;

import com.fam.entity.SanPhamFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author giangdm
 */
public interface ISanPhamFileRepository extends JpaRepository<SanPhamFile, Integer> {
    @Query(value = "SELECT l from SanPhamFile l WHERE l.name = :name AND l.sanPham.maSP = :maSP")
    SanPhamFile getSanPhamFileByNameAndMaSP(String name, int maSP);

    @Query(value = "delete from SanPhamFile s where s.token = :token")
    @Modifying
    void deleteByToken(@Param("token") String token);
}
