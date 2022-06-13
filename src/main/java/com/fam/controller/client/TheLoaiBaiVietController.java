package com.fam.controller.client;

import com.fam.service.ITheLoaiBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "api/v1/theloaibaiviet")
@CrossOrigin("*")
public class TheLoaiBaiVietController {
    @Autowired
    private ITheLoaiBaiVietService theLoaiBaiVietService;

    @GetMapping(value = "/listwp")
    public ResponseEntity<?> getAllTheLoaiBaiViet() {
        return new ResponseEntity<>(theLoaiBaiVietService.getAllTheLoaiBaiVietWithoutPage(), HttpStatus.OK);
    }
}
