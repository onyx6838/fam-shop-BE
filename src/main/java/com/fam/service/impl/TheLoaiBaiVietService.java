package com.fam.service.impl;

import com.fam.entity.TheLoaiBaiViet;
import com.fam.repository.ITheLoaiBaiVietRepository;
import com.fam.service.ITheLoaiBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author giangdm
 */
@Service
public class TheLoaiBaiVietService implements ITheLoaiBaiVietService {
    @Autowired
    private ITheLoaiBaiVietRepository theLoaiBaiVietRepository;

    @Override
    public List<TheLoaiBaiViet> getAllTheLoaiBaiVietWithoutPage() {
        return theLoaiBaiVietRepository.findAll();
    }

    @Override
    public Page<TheLoaiBaiViet> getAllTheLoaiBaiViet(Pageable pageable) {
        return theLoaiBaiVietRepository.findAll(pageable);
    }
}
