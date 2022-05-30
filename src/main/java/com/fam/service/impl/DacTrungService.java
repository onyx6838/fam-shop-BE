package com.fam.service.impl;

import com.fam.dto.form.DacTrungCreateDto;
import com.fam.dto.product.FeatureDto;
import com.fam.entity.DacTrung;
import com.fam.entity.DacTrungSanPham;
import com.fam.repository.IDacTrungRepository;
import com.fam.repository.IDacTrungSanPhamRepository;
import com.fam.service.IDacTrungService;
import com.fam.specification.SanPhamFilter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
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
    public Page<DacTrung> getAllGrByLoai(Pageable pageable) {
        return dacTrungRepository.getAllGrByLoai(pageable);
    }

    @Override
    public Page<DacTrung> getAllDacTrungNoneGr(Pageable pageable) {
        return dacTrungRepository.findAll(pageable);
    }

    @Override
    public Map<String, List<DacTrung>> getAllDacTrungs() {
        List<DacTrung> test = dacTrungRepository.findAll();
        return test.stream().collect(Collectors.groupingBy(DacTrung::getLoaiDacTrung));
    }

    @Override
    public Page<DacTrung> getDacTrungByLoaiDacTrung(String loaiDacTrung, Pageable pageable) {
        return dacTrungRepository.getDacTrungByLoaiDacTrung(loaiDacTrung, pageable);
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

    @Override
    public boolean createDacTrung(DacTrungCreateDto dto) {
        try {
            DacTrung dacTrung = new DacTrung();
            int ext = dacTrungRepository.checkExistLoaiDacTrung(dto.getLoaiDacTrung());
            if (ext > 0) {
                int ordered = getFeatureOrderedNumber(dto.getLoaiDacTrung());
                dacTrung.setThuTu(ordered + 1);
            } else {
                dacTrung.setThuTu(1);
            }
            BeanUtils.copyProperties(dto, dacTrung);
            if (ObjectUtils.isEmpty(dacTrung.getGiaTri())) dacTrung.setGiaTri(null);
            if (ObjectUtils.isEmpty(dacTrung.getDonVi())) dacTrung.setDonVi(null);
            dacTrungRepository.save(dacTrung);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateDacTrung(int maDacTrung, DacTrungCreateDto dto) {
        try {
            DacTrung dacTrung = dacTrungRepository.findById(maDacTrung).get();
            BeanUtils.copyProperties(dto, dacTrung);
            if (ObjectUtils.isEmpty(dacTrung.getGiaTri())) dacTrung.setGiaTri(null);
            if (ObjectUtils.isEmpty(dacTrung.getDonVi())) dacTrung.setDonVi(null);
            dacTrungRepository.save(dacTrung);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getFeatureOrderedNumber(String loaiDacTrung) {
        return dacTrungRepository.getFeatureOrderedNumber(loaiDacTrung);
    }
}
