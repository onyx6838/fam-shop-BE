package com.fam.service.impl;

import com.fam.entity.SanPham;
import com.fam.entity.SanPhamFile;
import com.fam.repository.ISanPhamFileRepository;
import com.fam.repository.ISanPhamRepository;
import com.fam.service.ISanPhamFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
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

    @Override
    public void uploadFileToSanPham(MultipartFile[] files, int selectedId) {
        SanPham sp = sanPhamRepository.getById(selectedId);
        Arrays.stream(files).forEach(x -> {
            SanPhamFile sanPhamFile = new SanPhamFile();
            sanPhamFile.setSanPham(sp);
            sanPhamFile.setName(StringUtils.cleanPath(Objects.requireNonNull(x.getOriginalFilename())));
            sanPhamFile.setMimetype(x.getContentType());
            try {
                sanPhamFile.setData(x.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            sanPhamFileRepository.save(sanPhamFile);
        });
    }
}
