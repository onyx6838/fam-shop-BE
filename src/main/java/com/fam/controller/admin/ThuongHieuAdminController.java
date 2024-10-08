package com.fam.controller.admin;

import com.fam.dto.form.ThuongHieuUpdateDto;
import com.fam.service.IThuongHieuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "api/v1/admin/thuonghieu")
@CrossOrigin("*")
public class ThuongHieuAdminController {
    @Autowired
    private IThuongHieuService thuongHieuService;

    @GetMapping()
    public ResponseEntity<?> getAllThuongHieus(Pageable pageable) {
        return new ResponseEntity<>(thuongHieuService.getAllThuongHieus(pageable), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllThuongHieusWithoutPaging() {
        return new ResponseEntity<>(thuongHieuService.getAllThuongHieus(), HttpStatus.OK);
    }

    // tạm thời làm việc 1 ảnh
    @PostMapping(value = "/create")
    public ResponseEntity<?> uploadFileToSanPham(@RequestParam("tenThuongHieu") String tenThuongHieu,
                                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        thuongHieuService.createThuongHieu(tenThuongHieu, file);
        return new ResponseEntity<>("Upload thanh cong roi nhe !!", HttpStatus.OK);
    }

    @PostMapping(value = "/file/upload")
    public ResponseEntity<?> uploadFileToThuongHieu(@RequestParam("file") MultipartFile file,
                                                    @RequestParam("id") int id) {
        thuongHieuService.uploadFileToThuongHieu(file, id);
        return new ResponseEntity<>("Upload thanh cong roi nhe !!", HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getThuongHieuById(@PathVariable(name = "id") int id) {
        return new ResponseEntity<>(thuongHieuService.getById(id), HttpStatus.OK);
    }

    @PutMapping(value = "/{maThuongHieu}")
    public ResponseEntity<?> updateSanPham(@PathVariable(name = "maThuongHieu") int maThuongHieu, @RequestBody ThuongHieuUpdateDto form) {
        return new ResponseEntity<>(thuongHieuService.updateThuongHieu(maThuongHieu, form), HttpStatus.OK);
    }
}
