package com.fam.service.impl;

import com.fam.dto.file.FileUploadDto;
import com.fam.dto.form.BaiVietShortCreateDto;
import com.fam.dto.form.BaiVietUpdateDto;
import com.fam.entity.BaiViet;
import com.fam.entity.TaiKhoan;
import com.fam.entity.TheLoaiBaiViet;
import com.fam.repository.IBaiVietRepository;
import com.fam.repository.ITheLoaiBaiVietRepository;
import com.fam.service.IBaiVietService;
import com.fam.service.IFireBaseService;
import com.fam.service.ITaiKhoanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author giangdm
 */
@Service
public class BaiVietService implements IBaiVietService {
    @Autowired
    private IBaiVietRepository baiVietRepository;

    @Autowired
    private ITheLoaiBaiVietRepository theLoaiBaiVietRepository;

    @Autowired
    private ITaiKhoanService taiKhoanService;

    @Autowired
    private IFireBaseService fireBaseService;

    private static final String FIREBASE_URL = "https://firebasestorage.googleapis.com/v0/b/fam-shop-4fd26.appspot.com/o/%s?alt=media&token=%s";

    @Override
    public Page<BaiViet> getAllBaiVietWithPage(Pageable pageable) {
        return baiVietRepository.findAll(pageable);
    }

    @Override
    public Page<BaiViet> getAllByTheLoaiBaiVietWithPage(String duongDanTheLoaiBaiViet, Pageable pageable) {
        TheLoaiBaiViet rs = theLoaiBaiVietRepository.findByDuongDan(duongDanTheLoaiBaiViet);
        return baiVietRepository.findByTheLoaiBaiViet(rs, pageable);
    }

    @Override
    public BaiViet chiTietBaiViet(int maBaiViet) {
        return baiVietRepository.findById(maBaiViet).get();
    }

    @Override
    public boolean createShortBV(BaiVietShortCreateDto form) {
        try {
            BaiViet baiViet = new BaiViet();
            BeanUtils.copyProperties(form, baiViet);
            TaiKhoan creator = taiKhoanService.getTaiKhoanByTenTK(form.getUserNameCreator());
            TheLoaiBaiViet tlbv = theLoaiBaiVietRepository.findById(form.getMaTheLoai()).get();
            baiViet.setTacGia(creator);
            baiViet.setTheLoaiBaiViet(tlbv);
            baiVietRepository.save(baiViet);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateShortBV(BaiVietShortCreateDto form, int idUpdate) {
        try {
            BaiViet bv = baiVietRepository.findById(idUpdate).get();
            bv.setTieuDe(form.getTieuDe());
            bv.setMoTaNgan(form.getMoTaNgan());
            TheLoaiBaiViet tlbv = theLoaiBaiVietRepository.findById(form.getMaTheLoai()).get();
            bv.setTheLoaiBaiViet(tlbv);
            baiVietRepository.save(bv);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void uploadFileToBV(MultipartFile file, int selectedId) {
        BaiViet bv = baiVietRepository.findById(selectedId).get();
        FileUploadDto dto = (FileUploadDto) fireBaseService.upload(file, "post");
        bv.setAnhDaiDien(String.format(FIREBASE_URL, "post%2F" + dto.getName(), dto.getToken()));
        baiVietRepository.save(bv);
    }

    @Override
    public BaiViet getById(int id) {
        return baiVietRepository.findById(id).get();
    }

    @Override
    public boolean updateMoTaBaiViet(int maBV, BaiVietUpdateDto form) {
        try {
            BaiViet bv = baiVietRepository.findById(maBV).get();
            bv.setNoiDung(form.getNoiDung());
            baiVietRepository.save(bv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
