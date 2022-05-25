package com.fam.controller.admin;

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
@RequestMapping(value = "api/v1/admin/danhgia")
@CrossOrigin("*")
public class DanhGiaAdminController {
    @Autowired
    private IDanhGiaService danhGiaService;

    @GetMapping(value = "/parent")
    public ResponseEntity<?> getAllParentDanhGias(Pageable pageable) {
        return new ResponseEntity<>(danhGiaService.getParentDanhGias(pageable), HttpStatus.OK);
    }

    @GetMapping(value = "/child/{maDanhGiaCha}")
    public ResponseEntity<?> getChildDanhGias(Pageable pageable, @PathVariable(value = "maDanhGiaCha") int maDanhGiaCha) {
        return new ResponseEntity<>(danhGiaService.getChildDanhGias(pageable, maDanhGiaCha), HttpStatus.OK);
    }

    @GetMapping(value = "/parent/isReply/{maDanhGiaCha}")
    public ResponseEntity<?> checkReplyQuanTri(@PathVariable(value = "maDanhGiaCha") int maDanhGiaCha) {
        return new ResponseEntity<>(danhGiaService.checkQuanTriReply(maDanhGiaCha), HttpStatus.OK);
    }

    @GetMapping(value = "/parent/find/adminRep/{maDanhGiaCha}")
    public ResponseEntity<?> getReplyQuanTri(@PathVariable(value = "maDanhGiaCha") int maDanhGiaCha) {
        return new ResponseEntity<>(danhGiaService.findQuanTriReplyParent(maDanhGiaCha), HttpStatus.OK);
    }

    @DeleteMapping(value = "/child/{maDanhGia}")
    public ResponseEntity<?> deleteChildCmt(@PathVariable(name = "maDanhGia") int maDanhGia) {
        danhGiaService.removeChildDanhGia(maDanhGia);
        return new ResponseEntity<>("Xoa Thanh Cong", HttpStatus.OK);
    }

    @DeleteMapping(value = "/child/lock/{maDanhGia}")
    public ResponseEntity<?> lockChildCmt(@PathVariable(name = "maDanhGia") int maDanhGia) {
        danhGiaService.lockChildDanhGia(maDanhGia);
        return new ResponseEntity<>("Khoa Thanh Cong", HttpStatus.OK);
    }

    @DeleteMapping(value = "/child/unlock/{maDanhGia}")
    public ResponseEntity<?> unlockChildCmt(@PathVariable(name = "maDanhGia") int maDanhGia) {
        danhGiaService.unlockChildDanhGia(maDanhGia);
        return new ResponseEntity<>("Mo Khoa Thanh Cong", HttpStatus.OK);
    }

    @PostMapping(value = "/parent/adminRep")
    public ResponseEntity<?> adminRepParent(@RequestBody DanhGiaCreate form) {
        return new ResponseEntity<>(danhGiaService.addQuanTriDanhGia(form), HttpStatus.OK);
    }

    @PostMapping(value = "/parent/adminRep/update/{maDanhGia}")
    public ResponseEntity<?> adminUpdateRepParent(@RequestBody DanhGiaCreate form, @PathVariable(name = "maDanhGia") int maDanhGia) {
        return new ResponseEntity<>(danhGiaService.updateQuanTriDanhGia(form, maDanhGia), HttpStatus.OK);
    }
}
