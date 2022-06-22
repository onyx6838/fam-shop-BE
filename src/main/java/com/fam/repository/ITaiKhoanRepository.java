package com.fam.repository;

import com.fam.entity.TaiKhoan;
import com.fam.entity.enumerate.TrangThaiTK;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ITaiKhoanRepository extends JpaRepository<TaiKhoan, Integer> {
    boolean existsByTenTK(String tenTK);

    boolean existsByEmail(String email);

    @Query("	SELECT 	trangThai		"
            + "	FROM 	TaiKhoan 		"
            + " WHERE 	email = :email")
    TrangThaiTK findTrangThaiTKByEmail(String email);

    TaiKhoan findByTenTK(String tenTK);

    TaiKhoan findByEmail(String email);

    TaiKhoan findBySdt(String sdt);

    Page<TaiKhoan> getTaiKhoansByLoaiTKContainsIgnoreCase(String loaiTK, Pageable pageable);
}
