package com.fam.controller.admin;

import com.fam.service.ILoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
