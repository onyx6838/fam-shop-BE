package com.fam.service.impl;

import com.fam.dto.file.FileUploadDto;
import com.fam.dto.form.ThuongHieuUpdateDto;
import com.fam.entity.SanPham;
import com.fam.entity.ThuongHieu;
import com.fam.repository.ISanPhamRepository;
import com.fam.repository.IThuongHieuRepository;
import com.fam.service.IFireBaseService;
import com.fam.service.IThuongHieuService;
import com.fam.specification.SanPhamFilter;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private IFireBaseService fireBaseService;

    private static final String FIREBASE_URL = "https://firebasestorage.googleapis.com/v0/b/fam-shop-4fd26.appspot.com/o/%s?alt=media&token=%s";

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

    @Override
    public boolean createThuongHieu(String tenThuongHieu, MultipartFile file) {
        try {
            ThuongHieu thuongHieu = new ThuongHieu();
            thuongHieu.setTenThuongHieu(tenThuongHieu);
            FileUploadDto dto = (FileUploadDto) fireBaseService.upload(file, "brand");
            thuongHieu.setHinhAnh(String.format(FIREBASE_URL, dto.getName(), dto.getToken()));
            thuongHieuRepository.save(thuongHieu);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateThuongHieu(int maThuongHieu, ThuongHieuUpdateDto form) {
        try {
            ThuongHieu th = thuongHieuRepository.findById(maThuongHieu).get();
            String hinhAnh = th.getHinhAnh();
            BeanUtils.copyProperties(form, th);
            th.setHinhAnh(hinhAnh);
            thuongHieuRepository.save(th);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void uploadFileToThuongHieu(MultipartFile file, int selectedId) {
        ThuongHieu thuongHieu = thuongHieuRepository.findById(selectedId).get();
        FileUploadDto dto = (FileUploadDto) fireBaseService.upload(file, "brand");
        thuongHieu.setHinhAnh(String.format(FIREBASE_URL, "brand%2F" + dto.getName(), dto.getToken()));
        thuongHieuRepository.save(thuongHieu);
    }

    @Override
    public ThuongHieu getById(int id) {
        return thuongHieuRepository.findById(id).get();
    }
}
