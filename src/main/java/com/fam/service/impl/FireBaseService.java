package com.fam.service.impl;

import com.fam.dto.file.FileUploadDto;
import com.fam.service.IFireBaseService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author giangdm
 */
@Service
public class FireBaseService implements IFireBaseService {
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/fam-shop-4fd26.appspot.com/o/%s?alt=media";
    private static final String BUCKET_NAME = "fam-shop-4fd26.appspot.com";
    private static final String PRIVATE_KEY_JSON_PATH = "src/main/resources/serviceAccountKey.json";

    @Value("${firebase.bucket_name}")
    private String bucketName;

    @Value("${firebase.private_key_json_path}")
    private String privateKeyJsonPath;

    //@PostConstruct
    @Override
    public String uploadFile(File file, String fileName, String mimeType, String accessToken, String subDirectoryName) throws IOException {
        BlobId blobId;
        // add sub folder when upload file
        if (!ObjectUtils.isEmpty(subDirectoryName)) {
            blobId = BlobId.of(BUCKET_NAME, subDirectoryName + "/" + fileName);
        } else blobId = BlobId.of(BUCKET_NAME, fileName);
        Map<String, String> map = new HashMap<>();
        map.put("firebaseStorageDownloadTokens", accessToken);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(map).setContentType(mimeType).build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(PRIVATE_KEY_JSON_PATH));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()));
    }

    @Override
    public File convertToFile(MultipartFile multipartFile, String fileName) {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile;
    }

    @Override
    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
    @Override
    public Object upload(MultipartFile multipartFile, String subDirectoryName) {
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.

            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String accessToken = UUID.randomUUID().toString();
            String TEMP_URL = this.uploadFile(file, fileName, multipartFile.getContentType(), accessToken, subDirectoryName);    // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
            FileUploadDto fileUploadDto = new FileUploadDto();
            fileUploadDto.setName(fileName);
            fileUploadDto.setMimeType(multipartFile.getContentType());
            fileUploadDto.setToken(accessToken);
            fileUploadDto.setCreateDate(new Date());
            fileUploadDto.setTempUrl(TEMP_URL);
            return fileUploadDto;            // customized response
        } catch (Exception e) {
            e.printStackTrace();
            return "500" + e + "Unsuccessfully Uploaded!";
        }
    }

    @Override
    public Object download(String fileName) throws IOException {
        String destFileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));     // to set random string for destination file name
        String destFilePath = "D:\\" + destFileName;                                    // to set destination file path
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(PRIVATE_KEY_JSON_PATH));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of(BUCKET_NAME, fileName));
        blob.downloadTo(Paths.get(destFilePath));
        return "200" + "Successfully Downloaded!";
    }

    @Override
    public void delete(String fileName) {
        Bucket bucket = StorageClient.getInstance().bucket(BUCKET_NAME);
        bucket.get(fileName).delete();
    }
}
