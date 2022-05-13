package com.fam.controller.admin;

import com.fam.dto.order.OrderShipperChangeDto;
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

    @GetMapping(value = "/distinct-year-dat-hang")
    public ResponseEntity<?> getDistinctYearDatHang() {
        return new ResponseEntity<>(donDatHangService.distinctYearDatHang(), HttpStatus.OK);
    }

    @PostMapping(value = "/change-status")
    public ResponseEntity<?> changeStatusDonDat(@RequestBody OrderStatusChangeDto form) {
        return new ResponseEntity<>(donDatHangService.changeStatusOrder(form), HttpStatus.OK);
    }

    @PostMapping(value = "/change-shipper-order")
    public ResponseEntity<?> changeShipperDonDat(@RequestBody OrderShipperChangeDto form) {
        return new ResponseEntity<>(donDatHangService.changeShipperDonDat(form), HttpStatus.OK);
    }
}
