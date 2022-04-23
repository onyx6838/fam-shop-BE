package com.fam.service.impl;

import com.fam.entity.LoaiSanPham;
import com.fam.repository.ILoaiSanPhamRepository;
import com.fam.service.ILoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiSanPhamService implements ILoaiSanPhamService {
    @Autowired
    private ILoaiSanPhamRepository loaiSanPhamRepository;

    @Override
    public List<LoaiSanPham> getAllLoaiSanPhams() {
        return loaiSanPhamRepository.findAll();
    }

    @Override
    public Page<LoaiSanPham> getAllLoaiSanPhams(Pageable pageable) {
        return loaiSanPhamRepository.findAll(pageable);
    }

    @Override
    public List<LoaiSanPham> getParentLoaiSP() {
        return loaiSanPhamRepository.getParentLoaiSP();
    }

    @Override
    public List<LoaiSanPham> getChildLoaiSP() {
        return loaiSanPhamRepository.getChildLoaiSP();
    }
}
