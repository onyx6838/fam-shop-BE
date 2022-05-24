package com.fam.controller.client;

import com.fam.dto.form.DanhGiaCreate;
import com.fam.service.IDanhGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/danhgia")
@CrossOrigin("*")
public class DanhGiaController {
    @Autowired
    private IDanhGiaService danhGiaService;

    @GetMapping(value = "/{maSP}")
    public ResponseEntity<?> getAllDanhGias(@PathVariable(value = "maSP") int maSP, Pageable pageable) {
        return new ResponseEntity<>(danhGiaService.getAllParentDanhGiaBySanPham(pageable, maSP), HttpStatus.OK);
    }

    @GetMapping(value = "/child/{maDanhGiaCha}")
    public ResponseEntity<?> getChildDanhGias(@PathVariable(value = "maDanhGiaCha") int maDanhGiaCha) {
        return new ResponseEntity<>(danhGiaService.getChildDanhGia(maDanhGiaCha), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> getAllDacTrungsGroupByLoai(@RequestBody DanhGiaCreate form) {
        return new ResponseEntity<>(danhGiaService.addDanhGia(form), HttpStatus.OK);
    }
}
