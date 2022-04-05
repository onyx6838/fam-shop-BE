package com.fam.service;

import com.fam.dto.form.SanPhamFileDeleteDto;
import com.fam.dto.form.SanPhamFileUploadDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author giangdm
 */
public interface ISanPhamFileService {
    void uploadFileToSanPham(MultipartFile[] files, int selectedId);

    void uploadFileToSanPhamV2(SanPhamFileUploadDto dto, int selectedId);

    Object deleteSanPhamFileBySanPhamId(SanPhamFileDeleteDto dto);
}
