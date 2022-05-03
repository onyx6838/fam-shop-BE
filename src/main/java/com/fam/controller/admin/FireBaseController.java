package com.fam.controller.admin;

import com.fam.service.IFireBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
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
        return fireBaseService.upload(multipartFile, "");
    }

    @PostMapping("/file/download/{fileName}")
    public Object download(@PathVariable String fileName) throws IOException {
        return fireBaseService.download(fileName);
    }
}
