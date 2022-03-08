package com.fam.controller;

import com.fam.service.ILoaiSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/loaisanphams")
@CrossOrigin("*")
public class LoaiSanPhamController {
    @Autowired
    private ILoaiSanPhamService loaiSanPhamService;

    @GetMapping
    public ResponseEntity<?> getAllLoaiSanPhams() {
        return new ResponseEntity<>(loaiSanPhamService.getAllLoaiSanPhams(), HttpStatus.OK);
    }
}
