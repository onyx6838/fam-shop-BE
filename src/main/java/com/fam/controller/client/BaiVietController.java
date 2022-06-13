package com.fam.controller.client;

import com.fam.service.IBaiVietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/baiviet")
@CrossOrigin("*")
public class BaiVietController {
    @Autowired
    private IBaiVietService baiVietService;

    @GetMapping
    public ResponseEntity<?> getAllBaiVietWithPage(Pageable pageable) {
        return new ResponseEntity<>(baiVietService.getAllBaiVietWithPage(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/{duongDanTLBV}")
    public ResponseEntity<?> getAllByTheLoaiBaiViet(@PathVariable(value = "duongDanTLBV") String duongDanTLBV, Pageable pageable) {
        return new ResponseEntity<>(baiVietService.getAllByTheLoaiBaiVietWithPage(duongDanTLBV, pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/detail/{maBaiViet}")
    public ResponseEntity<?> getChiTietBaiViet(@PathVariable(value = "maBaiViet") int maBaiViet) {
        return new ResponseEntity<>(baiVietService.chiTietBaiViet(maBaiViet), HttpStatus.OK);
    }
}
