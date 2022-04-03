package com.fam.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author giangdm
 */
public interface ISanPhamFileService {
    void uploadFileToSanPham(MultipartFile[] files, int selectedId);
}
