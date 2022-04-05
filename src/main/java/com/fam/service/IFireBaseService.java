package com.fam.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author giangdm
 */
public interface IFireBaseService {
    String uploadFile(File file, String fileName, String mimeType, String accessToken) throws IOException; // used to upload a file

    File convertToFile(MultipartFile multipartFile, String fileName); // used to convert MultipartFile to File

    String getExtension(String fileName);  // used to get extension of a uploaded file

    Object upload(MultipartFile multipartFile);

    Object download(String fileName) throws IOException;

    void delete(String fileName);
}
