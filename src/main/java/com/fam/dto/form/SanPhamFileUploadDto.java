package com.fam.dto.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author giangdm
 */
@Data
public class SanPhamFileUploadDto {
    @JsonProperty("file-upload")
    private List<FileUploadDto> fileUpload;

    @Data
    @NoArgsConstructor
    public static class FileUploadDto {
        private String name;

        private String mimetype;

        private MultipartFile data;

        private int maSP;
    }
}
