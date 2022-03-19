package com.fam.service.impl;

import com.fam.dto.product.CategoryDto;
import com.fam.dto.product.ProductWithCategoryDto;
import com.fam.entity.SanPham;
import com.fam.repository.ISanPhamRepository;
import com.fam.service.ISanPhamService;
import com.fam.specification.SanPhamFilter;
import com.fam.specification.SanPhamSpecification;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SanPhamService implements ISanPhamService {
    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Page<SanPham> getAllSanPhams(Pageable pageable) {
        return sanPhamRepository.findAll(pageable);
    }

    // filter function
    @Override
    public Page<SanPham> getByDacTrungsAndLoaiSP(SanPhamFilter sanPhamFilter, Pageable pageable) {
        Specification<SanPham> where = null;
        if (!ObjectUtils.isEmpty(sanPhamFilter.getTenSP())) {
            Specification<SanPham> name = new SanPhamSpecification("LIKE", "ten", sanPhamFilter);
            where = Specification.where(name);
        }

        if (!sanPhamFilter.getDacTrungs().isEmpty()) {
            Specification<SanPham> inDacTrungs = new SanPhamSpecification("IN", "MaDacTrung.LEFT", sanPhamFilter);
            // chua co search chi? filter
            where = Specification.where(inDacTrungs);
        }

        if (sanPhamFilter.getLoaiSP() != 0) {
            Specification<SanPham> equalsLoaiSP = new SanPhamSpecification("EQUALS", "loaiSanPham", sanPhamFilter);
            if (where == null) {
                where = Specification.where(equalsLoaiSP);
            } else {
                where = where.and(equalsLoaiSP);
            }
        }

        if (!sanPhamFilter.getLoaiSPList().isEmpty()) {
            Specification<SanPham> inLoaiSPs = new SanPhamSpecification("IN", "loaiSanPham", sanPhamFilter);
            if (where == null) {
                where = Specification.where(inLoaiSPs);
            } else {
                where = where.and(inLoaiSPs);
            }
        }
        return sanPhamRepository.findAll(where, pageable);
    }

    @Override
    public Page<ProductWithCategoryDto> getByParentLoaiSP(List<CategoryDto> categories, Pageable pageable) {
        List<Integer> loaiSPList = categories.stream().map(CategoryDto::getMaLoai).collect(Collectors.toList());
        SanPhamFilter filter = new SanPhamFilter();
        filter.setLoaiSPList(loaiSPList);
        Specification<SanPham> where = null;
        if (!filter.getLoaiSPList().isEmpty()) {
            Specification<SanPham> inLoaiSPs = new SanPhamSpecification("IN", "loaiSanPham", filter);
            where = Specification.where(inLoaiSPs);
        }
        Page<SanPham> spEntity = sanPhamRepository.findAll(where, pageable);
        return spEntity.map(x -> modelMapper.map(x, ProductWithCategoryDto.class));
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
