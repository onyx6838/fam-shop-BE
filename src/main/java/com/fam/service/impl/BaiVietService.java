package com.fam.service.impl;

import com.fam.entity.BaiViet;
import com.fam.entity.TheLoaiBaiViet;
import com.fam.repository.IBaiVietRepository;
import com.fam.repository.ITheLoaiBaiVietRepository;
import com.fam.service.IBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author giangdm
 */
@Service
public class BaiVietService implements IBaiVietService {
    @Autowired
    private IBaiVietRepository baiVietRepository;

    @Autowired
    private ITheLoaiBaiVietRepository theLoaiBaiVietRepository;

    @Override
    public Page<BaiViet> getAllBaiVietWithPage(Pageable pageable) {
        return baiVietRepository.findAll(pageable);
    }

    @Override
    public Page<BaiViet> getAllByTheLoaiBaiVietWithPage(String duongDanTheLoaiBaiViet, Pageable pageable) {
        TheLoaiBaiViet rs = theLoaiBaiVietRepository.findByDuongDan(duongDanTheLoaiBaiViet);
        return baiVietRepository.findByTheLoaiBaiViet(rs, pageable);
    }

    @Override
    public BaiViet chiTietBaiViet(int maBaiViet) {
        return baiVietRepository.findById(maBaiViet).get();
    }
}
