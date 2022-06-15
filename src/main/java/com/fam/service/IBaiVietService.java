package com.fam.service;

import com.fam.dto.form.BaiVietShortCreateDto;
import com.fam.dto.form.BaiVietUpdateDto;
import com.fam.entity.BaiViet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author giangdm
 */
public interface IBaiVietService {
    Page<BaiViet> getAllBaiVietWithPage(Pageable pageable);

    Page<BaiViet> getAllByTheLoaiBaiVietWithPage(String duongDanTheLoaiBaiViet, Pageable pageable);

    BaiViet chiTietBaiViet(int maBaiViet);

    boolean createShortBV(BaiVietShortCreateDto form);

    boolean updateShortBV(BaiVietShortCreateDto form, int idUpdate);

    void uploadFileToBV(MultipartFile file, int selectedId);

    BaiViet getById(int id);

    boolean updateMoTaBaiViet(int maBV, BaiVietUpdateDto form);
}
