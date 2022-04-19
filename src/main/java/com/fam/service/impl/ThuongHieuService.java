package com.fam.service.impl;

import com.fam.entity.DacTrungSanPham;
import com.fam.entity.SanPham;
import com.fam.entity.ThuongHieu;
import com.fam.repository.ISanPhamRepository;
import com.fam.repository.IThuongHieuRepository;
import com.fam.service.IThuongHieuService;
import com.fam.specification.SanPhamFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
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
    public List<ThuongHieu> getBrandWithFilter(SanPhamFilter sanPhamFilter) {
        if (sanPhamFilter.getLoaiSPList().isEmpty()) {
            if (sanPhamFilter.getLoaiSP() == 0) {
                return getBrandWithFilterData(Collections.emptyList(), sanPhamFilter.getTenSP());
            } else {
                List<Integer> loaiSPs = new ArrayList<>();
                loaiSPs.add(sanPhamFilter.getLoaiSP());
                return getBrandWithFilterData(loaiSPs, sanPhamFilter.getTenSP());
            }
        } else {
            return getBrandWithFilterData(sanPhamFilter.getLoaiSPList(), sanPhamFilter.getTenSP());
        }
    }

    @Override
    public List<ThuongHieu> getBrandWithFilterData(List<Integer> loaiSPs, String tenSP) {
        List<SanPham> sp = sanPhamRepository.findAll();

        Predicate<SanPham> inLoaiSP = lsp -> loaiSPs.contains(lsp.getLoaiSanPham().getMaLoai());
        Predicate<SanPham> containTenSP = str -> str.getTen().toLowerCase().contains(tenSP);

        List<SanPham> spFilter;

        if (!ObjectUtils.isEmpty(tenSP)) {
            spFilter = sp.stream().filter(containTenSP).collect(Collectors.toList());
        } else if (!loaiSPs.isEmpty()) {
            spFilter = sp.stream().filter(inLoaiSP).collect(Collectors.toList());
        } else {
            spFilter = sp.stream().filter(inLoaiSP.and(containTenSP)).collect(Collectors.toList());
        }
        List<ThuongHieu> ss = spFilter.stream().map(SanPham::getThuongHieu).distinct().collect(Collectors.toList());
        return ss;
    }

    @Override
    public Page<ThuongHieu> getAllThuongHieus(Pageable pageable) {
        return thuongHieuRepository.findAll(pageable);
    }

    @Override
    public Page<ThuongHieu> getAllThuongHieus() {
        Page<ThuongHieu> entities = thuongHieuRepository.findAll(PageRequest.of(0, Integer.MAX_VALUE));
        return entities;
    }
}
