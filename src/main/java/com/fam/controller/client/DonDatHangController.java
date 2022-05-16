package com.fam.controller.client;

import com.fam.dto.payment.PaymentDto;
import com.fam.service.IDonDatHangService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "DonDatHang REST", description = "Manage DonDatHang Api", tags = "API liên quan đến Đơn Đặt Hàng")
@RestController
@RequestMapping(value = "api/v1/dondathang")
@CrossOrigin("*")
public class DonDatHangController {
    @Autowired
    private IDonDatHangService donDatHangService;

    @ApiOperation(value = "1, Thanh toán", notes = "Url: /api/v1/dondathang/payment")
    @PostMapping(value = "/payment")
    public ResponseEntity<?> getSanPhamByDacTrung(@RequestBody PaymentDto dto) {
        donDatHangService.payment(dto);
        return new ResponseEntity<>("Thanh toán thành công", HttpStatus.OK);
    }

    @GetMapping(value = "/user-order-management")
    public ResponseEntity<?> getDonDatHangByKhachHang(@RequestParam("tenTK") String tenTK, Pageable pageable) {
        return new ResponseEntity<>(donDatHangService.getDonDatHangByKhachHang(tenTK, pageable) , HttpStatus.OK);
    }
}
