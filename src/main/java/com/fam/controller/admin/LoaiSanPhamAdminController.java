package com.fam.controller.admin;

import com.fam.dto.form.LoaiSanPhamCreateDto;
import com.fam.dto.form.LoaiSanPhamUpdateDto;
import com.fam.service.ILoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/loaisanphams")
@CrossOrigin("*")
public class LoaiSanPhamAdminController {
    @Autowired
    private ILoaiSanPhamService loaiSanPhamService;

    @GetMapping(value = "/child")
    public ResponseEntity<?> getChildLoaiSanPhams() {
        return new ResponseEntity<>(loaiSanPhamService.getChildLoaiSP(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoaiSP(Pageable pageable) {
        return new ResponseEntity<>(loaiSanPhamService.getAllLoaiSanPhams(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createLoaiSanPham(@RequestBody LoaiSanPhamCreateDto form) {
        return new ResponseEntity<>(loaiSanPhamService.createLoaiSanPham(form), HttpStatus.OK);
    }

    @GetMapping(value = "/parents")
    public ResponseEntity<?> getParentLoaiSanPhamsIncludeAll() {
        return new ResponseEntity<>(loaiSanPhamService.getParentLoaiSPIncludeAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/{maLSP}")
    public ResponseEntity<?> updateSanPham(@PathVariable(name = "maLSP") int maLSP, @RequestBody LoaiSanPhamUpdateDto form) {
        return new ResponseEntity<>(loaiSanPhamService.updateLoaiSanPham(maLSP, form), HttpStatus.OK);
    }
}
