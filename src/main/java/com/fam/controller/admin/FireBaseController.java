package com.fam.controller.admin;

import com.fam.dto.form.SanPhamFileDeleteDto;
import com.fam.service.IFireBaseService;
import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 *
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/firebase")
@CrossOrigin("*")
public class FireBaseController {

    @Autowired
    private IFireBaseService fireBaseService;

    @PostMapping("/file/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {
        return fireBaseService.upload(multipartFile);
    }

    @PostMapping("/file/download/{fileName}")
    public Object download(@PathVariable String fileName) throws IOException {
        return fireBaseService.download(fileName);
    }

    @PostMapping("/delete")
    public String deleteFileInFB(@RequestBody SanPhamFileDeleteDto dto){
//        Bucket bucket = StorageClient.getInstance().bucket("fam-shop-4fd26.appspot.com");
//        bucket.get(file).delete();

        return "OK";
    }
}
