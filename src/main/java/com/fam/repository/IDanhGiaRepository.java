package com.fam.repository;

import com.fam.entity.DanhGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author giangdm
 */
public interface IDanhGiaRepository extends JpaRepository<DanhGia, Integer> {
    @Query("SELECT d from DanhGia d WHERE d.sanPham.maSP = :maSP AND d.danhGiaCha IS NULL")
    Page<DanhGia> getAllParentDanhGiaBySanPham(Pageable pageable, @Param("maSP") int maSP);

    @Query("SELECT d from DanhGia d WHERE d.danhGiaCha.maDanhGia = :maDanhGiaCha")
    List<DanhGia> getChildDanhGia(@Param("maDanhGiaCha") int maDanhGiaCha);

    Page<DanhGia> getDanhGiasByDanhGiaChaIsNull(Pageable pageable);

    @Query("SELECT d from DanhGia d WHERE d.danhGiaCha.maDanhGia = :maDanhGiaCha")
    Page<DanhGia> getChildDanhGia(Pageable pageable, @Param("maDanhGiaCha") int maDanhGiaCha);

    @Query("SELECT COUNT(d) from DanhGia d WHERE d.danhGiaCha.maDanhGia = :maDanhGiaCha AND d.quanTriVien = true")
    Integer checkReplyQuanTri(@Param("maDanhGiaCha") int maDanhGiaCha);

    @Query("SELECT d.maDanhGia from DanhGia d WHERE d.danhGiaCha.maDanhGia = :maDanhGiaCha AND d.quanTriVien = true")
    Integer findQuanTriReply(@Param("maDanhGiaCha") int maDanhGiaCha);
}
