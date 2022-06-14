package com.fam.controller.admin;

import com.fam.dto.form.TheLoaiBaiVietCreateDto;
import com.fam.service.ITheLoaiBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/theloaibaiviet")
@CrossOrigin("*")
public class TheLoaiBaiVietAdminController {
    @Autowired
    private ITheLoaiBaiVietService theLoaiBaiVietService;

    @GetMapping
    public ResponseEntity<?> getAll(Pageable pageable) {
        return new ResponseEntity<>(theLoaiBaiVietService.getAllTheLoaiBaiViet(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/list")
    public ResponseEntity<?> getAllWithoutPage() {
        return new ResponseEntity<>(theLoaiBaiVietService.getAllTheLoaiBaiVietWithoutPage(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTLBV(@RequestBody TheLoaiBaiVietCreateDto form) {
        return new ResponseEntity<>(theLoaiBaiVietService.createTheLoaiBaiViet(form), HttpStatus.OK);
    }

    @PutMapping(value = "/{maTLBV}")
    public ResponseEntity<?> updateTLBV(@PathVariable(name = "maTLBV") int maTLBV, @RequestBody TheLoaiBaiVietCreateDto form) {
        return new ResponseEntity<>(theLoaiBaiVietService.updateTLBV(form, maTLBV), HttpStatus.OK);
    }
}
