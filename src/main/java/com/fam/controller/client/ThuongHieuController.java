package com.fam.controller.client;

import com.fam.service.IThuongHieuService;
import com.fam.specification.SanPhamFilter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "ThuongHieu REST", description = "Manage ThuongHieu Api", tags = "API liên quan đến Thương Hiệu")
@RestController
@RequestMapping(value = "api/v1/thuonghieu")
@CrossOrigin("*")
public class ThuongHieuController {
    @Autowired
    private IThuongHieuService thuongHieuService;

    @ApiOperation(value = "1, Lấy các thương hiệu theo filter data", notes = "Url: /api/v1/thuonghieu")
    @PostMapping(value = "/brand")
    public ResponseEntity<?> getAllDacTrungsGroupByLoai(@RequestBody SanPhamFilter sanPhamFilter) {
        return new ResponseEntity<>(thuongHieuService.getBrandWithFilterData(sanPhamFilter), HttpStatus.OK);
    }
}
