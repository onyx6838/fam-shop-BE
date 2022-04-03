package com.fam.controller.client;

import com.fam.service.ILoaiSanPhamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "LoaiSanPham REST", description = "Manage LoaiSanPham Api", tags = "API liên quan đến Loại Sản Phẩm")
@RestController
@RequestMapping(value = "api/v1/loaisanphams")
@CrossOrigin("*")
public class LoaiSanPhamController {
    @Autowired
    private ILoaiSanPhamService loaiSanPhamService;

    @ApiOperation(value = "1, Lấy tất cả các loại sản phẩm", notes = "Url: /api/v1/loaisanphams")
    @GetMapping
    public ResponseEntity<?> getAllLoaiSanPhams() {
        return new ResponseEntity<>(loaiSanPhamService.getAllLoaiSanPhams(), HttpStatus.OK);
    }

    @ApiOperation(value = "2, Lấy các loại sản phẩm cha", notes = "Url: /api/v1/loaisanphams/parent")
    @GetMapping(value = "/parent")
    public ResponseEntity<?> getParentLoaiSanPhams() {
        return new ResponseEntity<>(loaiSanPhamService.getParentLoaiSP(), HttpStatus.OK);
    }
}
