package com.fam.service.impl;

import com.fam.dto.form.SanPhamFileDeleteDto;
import com.fam.dto.form.SanPhamFileUploadDto;
import com.fam.entity.SanPham;
import com.fam.entity.SanPhamFile;
import com.fam.repository.ISanPhamFileRepository;
import com.fam.repository.ISanPhamRepository;
import com.fam.service.IFireBaseService;
import com.fam.service.ISanPhamFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @author giangdm
 */
@Service
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
            sanPhamFile.setSanPham(sp);
            sanPhamFile.setName(StringUtils.cleanPath(Objects.requireNonNull(x.getOriginalFilename())));
            sanPhamFile.setMimetype(x.getContentType());
            sanPhamFile.setCreateDate(new Date());
            try {
                sanPhamFile.setData(x.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            sanPhamFileRepository.save(sanPhamFile);
        });
    }

    @Override
    public void uploadFileToSanPhamV2(SanPhamFileUploadDto dto, int selectedId) {

    }

    @Override
    public Object deleteSanPhamFileBySanPhamId(SanPhamFileDeleteDto dto) {
        SanPhamFile sanPhamFileToDelete = sanPhamFileRepository.getSanPhamFileByNameAndMaSP(dto.getFileName(), dto.getSelectedId());
        if (sanPhamFileToDelete != null) {
            sanPhamFileRepository.delete(sanPhamFileToDelete);
            fireBaseService.delete(dto.getFileName());
            return true;
        } else {
            return false;
        }
    }
}
