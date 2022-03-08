package com.fam.service.impl;

import com.fam.entity.DacTrungSanPham;
import com.fam.repository.IDacTrungSanPhamRepository;
import com.fam.service.IDacTrungSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DacTrungSanPhamService implements IDacTrungSanPhamService {
    @Autowired
    private IDacTrungSanPhamRepository dacTrungSanPhamRepository;

    @Override
    public Page<DacTrungSanPham> findByDacTrungs(List<Integer> dacTrungs, Pageable pageable) {
        return dacTrungSanPhamRepository.findByDacTrungs(dacTrungs, pageable);
    }
}
