package com.fam.repository;

import com.fam.entity.BaiViet;
import com.fam.entity.TheLoaiBaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author giangdm
 */
public interface IBaiVietRepository extends JpaRepository<BaiViet, Integer> {
    Page<BaiViet> findByTheLoaiBaiViet(TheLoaiBaiViet theLoaiBaiViet, Pageable pageable);
}
