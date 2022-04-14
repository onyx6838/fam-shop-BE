package com.fam.controller.admin;

import com.fam.dto.form.SanPhamFileDeleteDto;
import com.fam.service.ISanPhamFileService;
import com.fam.service.ISanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/v1/admin/sanphams")
@CrossOrigin("*")
public class SanPhamAdminController {
    @Autowired
    private ISanPhamService sanPhamService;

    @Autowired
    private ISanPhamFileService sanPhamFileService;

    @GetMapping
    public ResponseEntity<?> getAllSanPhams(Pageable pageable) {
        return new ResponseEntity<>(sanPhamService.getAllSanPhams(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/file/upload")
    public ResponseEntity<?> uploadFileToSanPham(@RequestParam("files") MultipartFile[] files,
                                                 @RequestParam("id") int id) {
        sanPhamFileService.uploadFileToSanPham(files, id);
        return new ResponseEntity<>("Upload thanh cong roi nhe !!", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getSPById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(sanPhamService.getById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/file/db/delete")
    public ResponseEntity<?> deleteFileInDatabase(@RequestBody SanPhamFileDeleteDto dto) {
        sanPhamFileService.deleteFileByTokenAndNameToFireBase(dto);
        return new ResponseEntity<>("Xoa Thanh Cong", HttpStatus.OK);
    }

    @GetMapping(value = "/parents")
    public ResponseEntity<?> getAllParentSanPhams() {
        return new ResponseEntity<>(sanPhamService.getAllParentSanPham(), HttpStatus.OK);
    }
}
