package com.fam.controller;

import com.fam.dto.auth.UserInfoRequest;
import com.fam.entity.TaiKhoan;
import com.fam.service.ITaiKhoanService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "TaiKhoan REST", description = "Manage TaiKhoan Api", tags = "API liên quan đến TaiKhoan")
@RestController
@RequestMapping(value = "api/v1/taikhoan")
@CrossOrigin("*")
public class TaiKhoanController {
    @Autowired
    private ITaiKhoanService taiKhoanService;

    // refresh token
    @PostMapping("/info")
    public ResponseEntity<?> getTKByTenTK(@RequestBody UserInfoRequest request) {

        TaiKhoan taiKhoan = taiKhoanService.getTaiKhoanByTenTK(request.getUserName());
        return new ResponseEntity<>(taiKhoan, HttpStatus.OK);
    }
}
