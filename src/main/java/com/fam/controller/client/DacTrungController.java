package com.fam.controller.client;

import com.fam.service.IDacTrungService;
import com.fam.specification.SanPhamFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "DacTrung REST", description = "Manage DacTrung Api", tags = "API liên quan đến Đặc Trưng")
@RestController
@RequestMapping(value = "api/v1/dactrung")
@CrossOrigin("*")
public class DacTrungController {
    @Autowired
    private IDacTrungService dacTrungService;

    @ApiOperation(value = "1, Lấy tất cả các đặc trưng nhóm theo loại đặc trưng", notes = "Url: /api/v1/dactrung")
    @GetMapping
    public ResponseEntity<?> getAllDacTrungsGroupByLoai() {
        return new ResponseEntity<>(dacTrungService.getAllDacTrungs(), HttpStatus.OK);
    }

    @ApiOperation(value = "2, Lấy các đặc trưng theo loại sản phẩm", notes = "Url: /api/v1/dactrung")
    @PostMapping(value = "/category")
    public ResponseEntity<?> getAllDacTrungsGroupByLoai(@RequestBody SanPhamFilter sanPhamFilter) {
        return new ResponseEntity<>(dacTrungService.getFeatureByLoaiSP(sanPhamFilter), HttpStatus.OK);
    }
}
