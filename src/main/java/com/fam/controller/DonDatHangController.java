package com.fam.controller;

import com.fam.dto.payment.PaymentDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "DonDatHang REST", description = "Manage DonDatHang Api", tags = "API liên quan đến Đơn Đặt Hàng")
@RestController
@RequestMapping(value = "api/v1/dondathang")
@CrossOrigin("*")
public class DonDatHangController {
    @ApiOperation(value = "1, Thanh toán", notes = "Url: /api/v1/dondathang/payment")
    @PostMapping(value = "/payment")
    public ResponseEntity<?> getSanPhamByDacTrung(@RequestBody PaymentDto dto) {
        String t = "";
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
