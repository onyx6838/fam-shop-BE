package com.fam.controller.admin;

import com.fam.service.IChiTietPNKService;
import com.fam.service.IPhieuNhapKhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/pnk")
@CrossOrigin("*")
public class PhieuNhapKhoAdminController {
    @Autowired
    private IPhieuNhapKhoService phieuNhapKhoService;

    @Autowired
    private IChiTietPNKService chiTietPNKService;

    @GetMapping
    public ResponseEntity<?> getAllPNK(Pageable pageable) {
        return new ResponseEntity<>(phieuNhapKhoService.getAllPNK(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/ctpnk/{maSP}")
    public ResponseEntity<?> getCTPNKByMaSP(@PathVariable("maSP") int maSP, Pageable pageable) {
        return new ResponseEntity<>(chiTietPNKService.getBySanPham(maSP, pageable), HttpStatus.OK);
    }
}
