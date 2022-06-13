package com.fam.repository;

import com.fam.entity.TheLoaiBaiViet;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author giangdm
 */
public interface ITheLoaiBaiVietRepository extends JpaRepository<TheLoaiBaiViet, Integer> {
    TheLoaiBaiViet findByDuongDan(String duongDan);
}
