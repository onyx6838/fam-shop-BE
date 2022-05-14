package com.fam.controller.client;

import com.fam.dto.cart.AddToGioHangDto;
import com.fam.dto.cart.LocalCartUserSaveDto;
import com.fam.dto.cart.SyncCartDto;
import com.fam.entity.TaiKhoan;
import com.fam.service.IGioHangService;
import com.fam.service.ITaiKhoanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "GioHang REST", description = "Manage GioHang Api", tags = "API liên quan đến Giỏ Hàng")
@RestController
@RequestMapping(value = "api/v1/giohang")
@CrossOrigin("*")
public class GioHangController {
    @Autowired
    private IGioHangService gioHangService;

    @Autowired
    private ITaiKhoanService taiKhoanService;

    @ApiOperation(value = "1, Lấy tất cả các sản phẩm", notes = "Url: /api/v1/giohang")
    @GetMapping
    public ResponseEntity<?> getAllSanPhams() {
        return new ResponseEntity<>(gioHangService.getAllSanPhams(), HttpStatus.OK);
    }

    @ApiOperation(value = "2, Lấy giỏ hàng trong db", notes = "Url: /api/v1/giohang")
    @PostMapping(value = "/get-sync-cart")
    public ResponseEntity<?> getSyncGioHang(@RequestBody SyncCartDto form) {
        TaiKhoan tk = taiKhoanService.getTaiKhoanByTenTK(form.getTenTK());
        return new ResponseEntity<>(gioHangService.getGioHangByTK(tk), HttpStatus.OK);
    }

    @ApiOperation(value = "2, Thêm sản phẩm (check user ext)", notes = "Url: /api/v1/giohang")
    @PostMapping(value = "/add-to-cart")
    public ResponseEntity<?> addToGioHang(@RequestBody AddToGioHangDto form) {
        return new ResponseEntity<>(gioHangService.addToGioHang(form), HttpStatus.OK);
    }

    @ApiOperation(value = "3, Thay đổi số sản phẩm (check user ext)", notes = "Url: /api/v1/giohang")
    @PostMapping(value = "/change-qty-cart")
    public ResponseEntity<?> changeSoLuongSPGioHang(@RequestBody AddToGioHangDto form) {
        return new ResponseEntity<>(gioHangService.changeSoLuongSPGioHang(form), HttpStatus.OK);
    }

    @ApiOperation(value = "3, Lưu local cart với user (check user ext)", notes = "Url: /api/v1/giohang")
    @PostMapping(value = "/save-local-cart")
    public ResponseEntity<?> saveLocalCartWithUser(@RequestBody LocalCartUserSaveDto form) {
        return new ResponseEntity<>(gioHangService.saveLocalCartWithUser(form), HttpStatus.OK);
    }

    @ApiOperation(value = "4, Xóa sản phẩm", notes = "Url: /api/v1/giohang")
    @PostMapping(value = "/remove-from-cart")
    public ResponseEntity<?> removeFromCart(@RequestBody AddToGioHangDto form) {
        return new ResponseEntity<>(gioHangService.removeToGioHang(form), HttpStatus.OK);
    }
}
