package com.fam.service.impl;

import com.fam.dto.product.FeatureDto;
import com.fam.entity.DacTrung;
import com.fam.entity.DacTrungSanPham;
import com.fam.repository.IDacTrungRepository;
import com.fam.repository.IDacTrungSanPhamRepository;
import com.fam.service.IDacTrungService;
import com.fam.specification.SanPhamFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class DacTrungService implements IDacTrungService {
    @Autowired
    private IDacTrungRepository dacTrungRepository;

    @Autowired
    private IDacTrungSanPhamRepository dacTrungSanPhamRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Map<String, List<DacTrung>> getAllDacTrungs() {
        List<DacTrung> test = dacTrungRepository.findAll();
        return test.stream().collect(Collectors.groupingBy(DacTrung::getLoaiDacTrung));
    }

    @Override
    public Map<String, List<FeatureDto>> getFeatureByLoaiSP(SanPhamFilter sanPhamFilter) {
        if (sanPhamFilter.getLoaiSPList().isEmpty()) {
            if (sanPhamFilter.getLoaiSP() == 0) {
                return getFeatureWithFilterData(Collections.emptyList(), sanPhamFilter.getTenSP());
            } else {
                List<Integer> loaiSPs = new ArrayList<>();
                loaiSPs.add(sanPhamFilter.getLoaiSP());
                return getFeatureWithFilterData(loaiSPs, sanPhamFilter.getTenSP());
            }
        } else {
            return getFeatureWithFilterData(sanPhamFilter.getLoaiSPList(), sanPhamFilter.getTenSP());
        }
    }

    @Override
    public Map<String, List<FeatureDto>> getFeatureWithFilterData(List<Integer> loaiSPs, String tenSP) {
        List<DacTrungSanPham> dtsp = dacTrungSanPhamRepository.findAll();

        Predicate<DacTrungSanPham> inLoaiSP = lsp -> loaiSPs.contains(lsp.getSanPham().getLoaiSanPham().getMaLoai());
        Predicate<DacTrungSanPham> containTenSP = str -> str.getSanPham().getTen().toLowerCase().contains(tenSP);

        List<DacTrungSanPham> dacTrungFilter;

        if (!ObjectUtils.isEmpty(tenSP)) {
            dacTrungFilter = dtsp.stream().filter(containTenSP).collect(Collectors.toList());
        } else if (!loaiSPs.isEmpty()) {
            dacTrungFilter = dtsp.stream().filter(inLoaiSP).collect(Collectors.toList());
        } else {
            dacTrungFilter = dtsp.stream().filter(inLoaiSP.and(containTenSP)).collect(Collectors.toList());
        }

        List<DacTrung> ents = dacTrungFilter.stream().map(DacTrungSanPham::getDacTrung).collect(Collectors.toList());
        List<FeatureDto> dtos = ents.stream().map(x -> modelMapper.map(x, FeatureDto.class)).distinct().collect(Collectors.toList());
        return dtos.stream().collect(Collectors.groupingBy(FeatureDto::getLoaiDacTrung));
    }
}
