package com.fam.controller.admin;

import com.fam.service.IThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/thongke")
@CrossOrigin("*")
public class ThongKeController {
    @Autowired
    private IThongKeService thongKeService;

    @GetMapping(value = "/total-revenue")
    public ResponseEntity<?> totalRevenue() {
        return new ResponseEntity<>(thongKeService.totalRevenue(), HttpStatus.OK);
    }

    @GetMapping(value = "/total-product-sold")
    public ResponseEntity<?> totalProductSold() {
        return new ResponseEntity<>(thongKeService.totalProductSold(), HttpStatus.OK);
    }

    @GetMapping(value = "/count-order")
    public ResponseEntity<?> countOrderWithType(@RequestParam("type") String trangThaiDonDat) {
        return new ResponseEntity<>(thongKeService.countOrderWithType(trangThaiDonDat), HttpStatus.OK);
    }

    @GetMapping(value = "/customer-order-success")
    public ResponseEntity<?> countOrderWithType(@RequestParam("year") int year) {
        return new ResponseEntity<>(thongKeService.countCustomerBuyOrderDone(year), HttpStatus.OK);
    }

    @GetMapping(value = "/statistic-order-year")
    public ResponseEntity<?> statisticOrderByYear(@RequestParam("year") int year, @RequestParam("type") String trangThaiDonDat) {
        return new ResponseEntity<>(thongKeService.statisticOrderByYear(year, trangThaiDonDat), HttpStatus.OK);
    }

    @GetMapping(value = "/category-most-sold")
    public ResponseEntity<?> categorySoldWithOrder() {
        return new ResponseEntity<>(thongKeService.categorySoldWithOrder(), HttpStatus.OK);
    }
}
