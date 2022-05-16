package com.fam.service.impl;

import com.fam.dto.file.FileUploadDto;
import com.fam.dto.form.SanPhamCreateDto;
import com.fam.dto.form.SanPhamUpdateDto;
import com.fam.dto.product.CategoryDto;
import com.fam.dto.product.ParentProduct;
import com.fam.dto.product.ProductWithCategoryDto;
import com.fam.entity.LoaiSanPham;
import com.fam.entity.SanPham;
import com.fam.entity.ThuongHieu;
import com.fam.repository.ILoaiSanPhamRepository;
import com.fam.repository.ISanPhamRepository;
import com.fam.repository.IThuongHieuRepository;
import com.fam.service.IFireBaseService;
import com.fam.service.ISanPhamService;
import com.fam.specification.SanPhamFilter;
import com.fam.specification.SanPhamSpecification;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SanPhamService implements ISanPhamService {
    @Autowired
    private IFireBaseService fireBaseService;

    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Autowired
    private IThuongHieuRepository thuongHieuRepository;

    @Autowired
    private ILoaiSanPhamRepository loaiSanPhamRepository;

    @Autowired
    private ModelMapper modelMapper;

    private static final String FIREBASE_URL = "https://firebasestorage.googleapis.com/v0/b/fam-shop-4fd26.appspot.com/o/%s?alt=media&token=%s";

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
            if (where == null) {
                where = Specification.where(inDacTrungs);
            } else {
                where = where.and(inDacTrungs);
            }
        }

        if (sanPhamFilter.getLoaiSP() != 0) {
            Specification<SanPham> equalsLoaiSP = new SanPhamSpecification("EQUALS", "loaiSanPham", sanPhamFilter);
            if (where == null) {
                where = Specification.where(equalsLoaiSP);
            } else {
                where = where.and(equalsLoaiSP);
            }
        }

        if (sanPhamFilter.getThuongHieu() != 0) {
            Specification<SanPham> equalsThuongHieu = new SanPhamSpecification("EQUALS", "thuongHieu", sanPhamFilter);
            if (where == null) {
                where = Specification.where(equalsThuongHieu);
            } else {
                where = where.and(equalsThuongHieu);
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
    public Page<ParentProduct> getAllParentSanPham() {
        Page<SanPham> entities = sanPhamRepository.findAll(PageRequest.of(0, Integer.MAX_VALUE));
        return modelMapper.map(entities, new TypeToken<Page<ParentProduct>>() {
        }.getType());
    }

    @Override
    public boolean createSanPham(SanPhamCreateDto form) {
        try {
            SanPham sp = new SanPham();
            sp.setTen(form.getTen());
            sp.setMoTa(form.getMoTa());
            sp.setDonGiaBan(form.getDonGiaBan());
            sp.setDonGiaNhap(form.getDonGiaNhap());
            if (form.getParentSP() != 0) {
                SanPham spCha = getById(form.getParentSP());
                sp.setSpCha(spCha);
            }
            if (form.getBrand() != 0) {
                ThuongHieu thuongHieu = thuongHieuRepository.findById(form.getBrand()).get();
                sp.setThuongHieu(thuongHieu);
            }
            if (form.getChildCategory() != 0) {
                LoaiSanPham lsp = loaiSanPhamRepository.findById(form.getChildCategory()).get();
                sp.setLoaiSanPham(lsp);
            }
            sanPhamRepository.save(sp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateSanPham(int maSP, SanPhamUpdateDto form) {
        try {
            SanPham sp = sanPhamRepository.findById(maSP).get();
            sp.setTen(form.getTen());
            sp.setMoTa(form.getMoTa());
            sp.setDonGiaBan(form.getDonGiaBan());
            sp.setDonGiaNhap(form.getDonGiaNhap());
            if (form.getParentSP() != 0) {
                SanPham spCha = getById(form.getParentSP());
                sp.setSpCha(spCha);
            }
            if (form.getBrand() != 0) {
                ThuongHieu thuongHieu = thuongHieuRepository.findById(form.getBrand()).get();
                sp.setThuongHieu(thuongHieu);
            }
            if (form.getChildCategory() != 0) {
                LoaiSanPham lsp = loaiSanPhamRepository.findById(form.getChildCategory()).get();
                sp.setLoaiSanPham(lsp);
            }
            sanPhamRepository.save(sp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateMoTaSanPham(int maSP, SanPhamUpdateDto form) {
        try {
            SanPham sp = sanPhamRepository.findById(maSP).get();
            sp.setMoTa(form.getMoTa());
            sanPhamRepository.save(sp);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Page<SanPham> getNewSanPhamsOrderByThoiGian(Pageable pageable) {
        return sanPhamRepository.getNewSanPhamsOrderByThoiGianNhap(pageable);
    }

    @Override
    public List<SanPham> getAllSanPhamWithoutPaging() {
        Pageable wholePage = Pageable.unpaged();
        return sanPhamRepository.findAll();
    }

    @Override
    public SanPham getById(int id) {
        return sanPhamRepository.findById(id).get();
    }

    @Override
    public void deleteSanPham(int maSP) {
        // logic sẽ thay đổi sang chuyển trangThai = 0 (inactive) để tránh bị reference cascade
        sanPhamRepository.deleteByMaSP(maSP, (short) 0);   // k sdung hàm của jpa
    }

    @Override
    public void deleteSanPhams(List<Integer> maSPs) {
        // logic sẽ thay đổi sang chuyển trangThai = 0 (inactive) để tránh bị reference cascade
        sanPhamRepository.deleteByMaSPs(maSPs);
    }

    @Override
    public void reactiveSanPham(int maSP) {
        sanPhamRepository.deleteByMaSP(maSP, (short) 1);
    }

    @Override
    public void uploadImageProfileToSanPham(MultipartFile file, int selectedId) {
        SanPham sp = sanPhamRepository.findById(selectedId).get();
        FileUploadDto dto = (FileUploadDto) fireBaseService.upload(file, "product");
        sp.setHinhAnh(String.format(FIREBASE_URL, "product%2F" + dto.getName(), dto.getToken()));
        sanPhamRepository.save(sp);
    }
}
