package com.fam.service.impl;

import com.fam.dto.file.FileUploadDto;
import com.fam.dto.form.SanPhamFileDeleteDto;
import com.fam.entity.SanPham;
import com.fam.entity.SanPhamFile;
import com.fam.repository.ISanPhamFileRepository;
import com.fam.repository.ISanPhamRepository;
import com.fam.service.IFireBaseService;
import com.fam.service.ISanPhamFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;

/**
 * @author giangdm
 */
@Service
@Transactional
public class SanPhamFileService implements ISanPhamFileService {
    @Autowired
    private ISanPhamFileRepository sanPhamFileRepository;

    @Autowired
    private ISanPhamRepository sanPhamRepository;

    @Autowired
    private IFireBaseService fireBaseService;

    @Override
    public void uploadFileToSanPham(MultipartFile[] files, int selectedId) {
        SanPham sp = sanPhamRepository.getById(selectedId);
        Arrays.stream(files).forEach(x -> {
            SanPhamFile sanPhamFile = new SanPhamFile();
            FileUploadDto dto = (FileUploadDto) fireBaseService.upload(x, "product");
            sanPhamFile.setName(dto.getName());
            sanPhamFile.setMimetype(dto.getMimeType());
            sanPhamFile.setToken(dto.getToken());
            sanPhamFile.setCreateDate(new Date());
            sanPhamFile.setSanPham(sp);
            sanPhamFileRepository.save(sanPhamFile);
        });
    }

    @Override
    public void deleteFileByTokenAndNameToFireBase(SanPhamFileDeleteDto dto) {
        fireBaseService.delete(dto.getFileName(), "product");
        sanPhamFileRepository.deleteByToken(dto.getToken());
    }
}
