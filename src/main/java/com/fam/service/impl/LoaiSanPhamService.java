package com.fam.service.impl;

import com.fam.dto.form.LoaiSanPhamCreateDto;
import com.fam.dto.form.LoaiSanPhamUpdateDto;
import com.fam.dto.product.ParentCategory;
import com.fam.entity.LoaiSanPham;
import com.fam.repository.ILoaiSanPhamRepository;
import com.fam.service.ILoaiSanPhamService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiSanPhamService implements ILoaiSanPhamService {
    @Autowired
    private ILoaiSanPhamRepository loaiSanPhamRepository;

    @Autowired
    private ModelMapper modelMapper;

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
    public Page<ParentCategory> getParentLoaiSPIncludeAll() {
        Page<LoaiSanPham> entities = loaiSanPhamRepository.findAll(PageRequest.of(0, Integer.MAX_VALUE));
        return entities.map(x -> modelMapper.map(x, ParentCategory.class));
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

    @Override
    public boolean updateLoaiSanPham(int maLSP, LoaiSanPhamUpdateDto form) {
        try {
            LoaiSanPham lsp = loaiSanPhamRepository.findById(maLSP).get();
            BeanUtils.copyProperties(form, lsp);
            if (form.getLoaiSPCha() != 0) {
                LoaiSanPham lspCha = loaiSanPhamRepository.findById(form.getLoaiSPCha()).get();
                lsp.setLoaiSPCha(lspCha);
            }
            loaiSanPhamRepository.save(lsp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
