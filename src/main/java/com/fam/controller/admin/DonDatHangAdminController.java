package com.fam.controller.admin;

import com.fam.dto.order.OrderStatusChangeDto;
import com.fam.service.IDonDatHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/dondathang")
@CrossOrigin("*")
public class DonDatHangAdminController {
    @Autowired
    private IDonDatHangService donDatHangService;

    @GetMapping
    public ResponseEntity<?> getAllDonDat(Pageable pageable) {
        return new ResponseEntity<>(donDatHangService.getAllDonDats(pageable), HttpStatus.OK);
    }

    @PostMapping(value = "/change-status")
    public ResponseEntity<?> changeStatusDonDat(@RequestBody OrderStatusChangeDto form) {
        return new ResponseEntity<>(donDatHangService.changeStatusOrder(form), HttpStatus.OK);
    }
}
