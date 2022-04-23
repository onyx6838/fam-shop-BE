package com.fam.controller.admin;

import com.fam.dto.form.DacTrungSanPhamAddDto;
import com.fam.dto.form.DacTrungSanPhamDeleteDto;
import com.fam.service.IDacTrungSanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/dactrungsanpham")
@CrossOrigin("*")
public class DacTrungSanPhamAdminController {
    @Autowired
    private IDacTrungSanPhamService dacTrungSanPhamService;

    @GetMapping
    public ResponseEntity<?> getAllDacTrungSP(Pageable pageable) {
        return new ResponseEntity<>(dacTrungSanPhamService.getAllDacTrungSP(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{maSP}")
    public ResponseEntity<?> getDacTrungBySanPham(@PathVariable(name = "maSP") int maSP, Pageable pageable) {
        return new ResponseEntity<>(dacTrungSanPhamService.getDacTrungBySanPham(maSP, pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addDacTrungToSP(@RequestBody DacTrungSanPhamAddDto form) {
        return new ResponseEntity<>(dacTrungSanPhamService.addDacTrungToSP(form), HttpStatus.OK);
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<?> deleteDacTrungSP(@RequestBody DacTrungSanPhamDeleteDto dto) {
        return new ResponseEntity<>(dacTrungSanPhamService.deleteDacTrungSP(dto), HttpStatus.OK);
    }
}
