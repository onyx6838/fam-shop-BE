package com.fam.dto.file;

import lombok.Data;

import java.util.Date;

/**
 * @author giangdm
 */
@Data
public class FileUploadDto {
    private String name;

    private String mimeType;

    private Date createDate;

    private String token;

    private String tempUrl;
}
