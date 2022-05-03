package com.fam.service.impl;

import com.fam.dto.form.LoaiSanPhamCreateDto;
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

    @Override
    public boolean createLoaiSanPham(LoaiSanPhamCreateDto form) {
        try {
            LoaiSanPham lsp = new LoaiSanPham();
            if (form.getLoaiSPCha() != 0) {
                LoaiSanPham lspCha = loaiSanPhamRepository.findById(form.getLoaiSPCha()).get();
                lsp.setLoaiSPCha(lspCha);
            }
            lsp.setTen(form.getTen());
            lsp.setMoTa(form.getMoTa());
            loaiSanPhamRepository.save(lsp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
