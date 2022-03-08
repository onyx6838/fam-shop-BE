package com.fam.service.impl;

import com.fam.entity.LoaiSanPham;
import com.fam.repository.ILoaiSanPhamRepository;
import com.fam.service.ILoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
