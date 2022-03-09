package com.fam.service.impl;

import com.fam.entity.SanPham;
import com.fam.repository.ISanPhamRepository;
import com.fam.service.ISanPhamService;
import com.fam.specification.SanPhamFilter;
import com.fam.specification.SanPhamSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SanPhamService implements ISanPhamService {
    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Override
    public Page<SanPham> getAllSanPhams(Pageable pageable) {
        return sanPhamRepository.findAll(pageable);
    }

    @Override
    public Page<SanPham> getByDacTrungsAndLoaiSP(SanPhamFilter sanPhamFilter, Pageable pageable) {
        Specification<SanPham> where = null;
        if (sanPhamFilter != null && !sanPhamFilter.getDacTrungs().isEmpty()) {
            Specification<SanPham> inDacTrungs = new SanPhamSpecification("IN", "MaDacTrung.LEFT", sanPhamFilter);
            // chua co search chi? filter
            where = Specification.where(inDacTrungs);
        }

        if (sanPhamFilter != null && sanPhamFilter.getLoaiSP() != 0) {
            Specification<SanPham> equalsLoaiSP = new SanPhamSpecification("EQUALS", "loaiSanPham", sanPhamFilter);
            if (where == null) {
                where = Specification.where(equalsLoaiSP);
            } else {
                where = where.and(equalsLoaiSP);
            }
        }
        return sanPhamRepository.findAll(where, pageable);
    }

    @Override
    public Page<SanPham> getNewSanPhamsOrderByThoiGian(Pageable pageable) {
        return sanPhamRepository.getNewSanPhamsOrderByThoiGianNhap(pageable);
    }

    @Override
    public SanPham getById(int id) {
        return sanPhamRepository.findById(id).get();
    }
}
