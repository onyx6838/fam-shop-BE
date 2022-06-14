package com.fam.controller.admin;

import com.fam.dto.form.BaiVietShortCreateDto;
import com.fam.service.IBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/baiviet")
@CrossOrigin("*")
public class BaiVietAdminController {
    @Autowired
    private IBaiVietService baiVietService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        return new ResponseEntity<>(baiVietService.getAllBaiVietWithPage(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/short")
    public ResponseEntity<?> createTLBV(@RequestBody BaiVietShortCreateDto form) {
        return new ResponseEntity<>(baiVietService.createShortBV(form), HttpStatus.OK);
    }

    @PutMapping(value = "/{maBV}")
    public ResponseEntity<?> updateBV(@PathVariable(name = "maBV") int maBV, @RequestBody BaiVietShortCreateDto form) {
        return new ResponseEntity<>(baiVietService.updateShortBV(form, maBV), HttpStatus.OK);
    }

    @PostMapping(value = "/file/upload")
    public ResponseEntity<?> uploadFileToBV(@RequestParam("file") MultipartFile file, @RequestParam("id") int id) {
        baiVietService.uploadFileToBV(file, id);
        return new ResponseEntity<>("Upload thanh cong roi nhe !!", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getThuongHieuById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(baiVietService.getById(id), HttpStatus.OK);
    }
}
