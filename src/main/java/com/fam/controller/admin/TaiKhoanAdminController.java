package com.fam.controller.admin;

import com.fam.dto.form.TaiKhoanAdminCreateDto;
import com.fam.dto.form.TaiKhoanAdminUpdateDto;
import com.fam.dto.form.TaiKhoanCreateDto;
import com.fam.entity.TaiKhoan;
import com.fam.service.ITaiKhoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/taikhoan")
@CrossOrigin("*")
public class TaiKhoanAdminController {
    @Autowired
    private ITaiKhoanService taiKhoanService;

    @GetMapping
    public ResponseEntity<?> getAllTaiKhoans(Pageable pageable) {
        return new ResponseEntity<>(taiKhoanService.getAllTaiKhoans(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/list-by-role")
    public ResponseEntity<?> getShipperList(@RequestParam("loaiTK") String loaiTK, Pageable pageable) {
        return new ResponseEntity<>(taiKhoanService.getAccountsByLoaiTK(loaiTK, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/lock-account/{maTK}")
    public ResponseEntity<?> lockAccount(@PathVariable("maTK") int maTK) {
        return new ResponseEntity<>(taiKhoanService.lockAccount(maTK), HttpStatus.OK);
    }

    @GetMapping(value = "/unlock-account/{maTK}")
    public ResponseEntity<?> unlockAccount(@PathVariable("maTK") int maTK) {
        return new ResponseEntity<>(taiKhoanService.unlockAccount(maTK), HttpStatus.OK);
    }

    @PutMapping(value = "/{maTK}")
    public ResponseEntity<?> updateAccount(@PathVariable(name = "maTK") int maTK, @RequestBody TaiKhoanAdminUpdateDto form) {
        return new ResponseEntity<>(taiKhoanService.updateAccountInAdmin(maTK, form), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody TaiKhoanAdminCreateDto request) {
        return new ResponseEntity<>(taiKhoanService.createAccountInAdmin(request), HttpStatus.OK);
    }
}
