package com.fam.service.impl;

import com.fam.entity.SanPham;
import com.fam.entity.ThuongHieu;
import com.fam.repository.ISanPhamRepository;
import com.fam.repository.IThuongHieuRepository;
import com.fam.service.IThuongHieuService;
import com.fam.specification.SanPhamFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ThuongHieuService implements IThuongHieuService {
    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Autowired
    private IThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> getBrandWithFilterData(SanPhamFilter sanPhamFilter) {
        List<SanPham> sp = sanPhamRepository.findAll();

        Predicate<SanPham> inLoaiSP;
        if (sanPhamFilter.getLoaiSPList().isEmpty()) {
            List<Integer> loaiSPs = new ArrayList<>();
            loaiSPs.add(sanPhamFilter.getLoaiSP());
            inLoaiSP = lsp -> loaiSPs.contains(lsp.getLoaiSanPham().getMaLoai());
        } else {
            inLoaiSP = lsp -> sanPhamFilter.getLoaiSPList().contains(lsp.getLoaiSanPham().getMaLoai());
        }

        Predicate<SanPham> containTenSP = str -> str.getTen().contains(sanPhamFilter.getTenSP());

        List<SanPham> spFilter;

        if (!ObjectUtils.isEmpty(sanPhamFilter.getTenSP())) {
            spFilter = sp.stream().filter(containTenSP).collect(Collectors.toList());
        } else if (!sanPhamFilter.getLoaiSPList().isEmpty()) {
            spFilter = sp.stream().filter(inLoaiSP).collect(Collectors.toList());
        } else {
            spFilter = sp.stream().filter(inLoaiSP.and(containTenSP)).collect(Collectors.toList());
        }
        return spFilter.stream().map(SanPham::getThuongHieu).distinct().collect(Collectors.toList());
    }

    @Override
    public Page<ThuongHieu> getAllThuongHieus(Pageable pageable) {
        return thuongHieuRepository.findAll(pageable);
    }
}
