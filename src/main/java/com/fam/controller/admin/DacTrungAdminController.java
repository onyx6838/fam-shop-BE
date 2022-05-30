package com.fam.controller.admin;

import com.fam.dto.form.DacTrungCreateDto;
import com.fam.service.IDacTrungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/dactrung")
@CrossOrigin("*")
public class DacTrungAdminController {
    @Autowired
    private IDacTrungService dacTrungService;

    @GetMapping
    public ResponseEntity<?> getAllDacTrungs(Pageable pageable) {
        return new ResponseEntity<>(dacTrungService.getAllGrByLoai(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/group")
    public ResponseEntity<?> getDacTrungGrByLoai(@RequestBody String loaiDacTrung, Pageable pageable) {
        return new ResponseEntity<>(dacTrungService.getDacTrungByLoaiDacTrung(loaiDacTrung, pageable), HttpStatus.OK);
    }

    @GetMapping("/none")
    public ResponseEntity<?> getAllDacTrungNoneGr(Pageable pageable) {
        return new ResponseEntity<>(dacTrungService.getAllDacTrungNoneGr(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> createDacTrung(@RequestBody DacTrungCreateDto form) {
        return new ResponseEntity<>(dacTrungService.createDacTrung(form), HttpStatus.OK);
    }

    @PutMapping(value = "/{maDacTrung}")
    public ResponseEntity<?> updateSanPham(@PathVariable(name = "maDacTrung") int maDacTrung, @RequestBody DacTrungCreateDto form) {
        return new ResponseEntity<>(dacTrungService.updateDacTrung(maDacTrung, form), HttpStatus.OK);
    }
}
