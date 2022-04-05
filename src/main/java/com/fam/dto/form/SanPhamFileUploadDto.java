package com.fam.dto.form;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author giangdm
 */
@Data
public class SanPhamFileUploadDto {
    private MultipartFile[] files;

    private String operation;
}
