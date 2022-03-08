package com.fam.service.impl;

import com.fam.entity.SanPham;
import com.fam.repository.ISanPhamRepository;
import com.fam.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements ICartService {
    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Override
    public List<SanPham> getAllSanPhams() {
        return sanPhamRepository.findAll();
    }
}
