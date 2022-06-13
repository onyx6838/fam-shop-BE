package com.fam.service;

import com.fam.entity.BaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author giangdm
 */
public interface IBaiVietService {
    Page<BaiViet> getAllBaiVietWithPage(Pageable pageable);

    Page<BaiViet> getAllByTheLoaiBaiVietWithPage(String duongDanTheLoaiBaiViet, Pageable pageable);

    BaiViet chiTietBaiViet(int maBaiViet);
}
