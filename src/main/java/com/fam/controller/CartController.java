package com.fam.controller;

import com.fam.service.ICartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "GioHang REST", description = "Manage GioHang Api", tags = "API liên quan đến Giỏ Hàng")
@RestController
@RequestMapping(value = "api/v1/giohang")
@CrossOrigin("*")
public class CartController {
    @Autowired
    private ICartService cartService;

    @ApiOperation(value = "1, Lấy tất cả các sản phẩm", notes = "Url: /api/v1/giohang")
    @GetMapping
    public ResponseEntity<?> getAllSanPhams() {
        return new ResponseEntity<>(cartService.getAllSanPhams(), HttpStatus.OK);
    }
}
