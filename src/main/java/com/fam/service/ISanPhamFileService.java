package com.fam.service;

import com.fam.dto.form.SanPhamFileDeleteDto;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author giangdm
 */
public interface ISanPhamFileService {
    void uploadFileToSanPham(MultipartFile[] files, int selectedId);

    void deleteFileByTokenAndNameToFireBase(SanPhamFileDeleteDto dto);
}
