package com.fam.controller.admin;

import com.fam.service.IThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/admin/thuonghieu")
public class ThuongHieuAdminController {
    @Autowired
    private IThuongHieuService thuongHieuService;

    @GetMapping()
    public ResponseEntity<?> getAllThuongHieus(Pageable pageable) {
        return new ResponseEntity<>(thuongHieuService.getAllThuongHieus(pageable), HttpStatus.OK);
    }
}
