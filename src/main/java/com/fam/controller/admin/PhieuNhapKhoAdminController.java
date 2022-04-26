package com.fam.controller.admin;

import com.fam.service.IPhieuNhapKhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/pnk")
@CrossOrigin("*")
public class PhieuNhapKhoAdminController {
    @Autowired
    private IPhieuNhapKhoService phieuNhapKhoService;

    @GetMapping
    public ResponseEntity<?> getAllPNK(Pageable pageable) {
        return new ResponseEntity<>(phieuNhapKhoService.getAllPNK(pageable), HttpStatus.OK);
    }
}
