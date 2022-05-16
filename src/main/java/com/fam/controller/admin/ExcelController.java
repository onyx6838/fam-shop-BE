package com.fam.controller.admin;

import com.fam.service.IExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author giangdm
 */
@RestController
@RequestMapping(value = "api/v1/admin/baocao")
@CrossOrigin("*")
public class ExcelController {
    @Autowired
    private IExcelService excelService;

    @GetMapping("/sanphams/export/excel")
    public ResponseEntity<?> exportSanPhamToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        excelService.exportToExcelSanPham(response);
        return new ResponseEntity<>("Xuat file excel thanh cong", HttpStatus.OK);
    }

    @GetMapping("/sp-ban-thang-nam/export/excel/{year}")
    public ResponseEntity<?> exportToExcelSanPhamSoldPerMonth(HttpServletResponse response, @PathVariable(value = "year") int selectedYear) throws IOException {
        response.setContentType("application/octet-stream");
        excelService.exportToExcelSanPhamSoldPerMonthInYear(response, selectedYear);
        return new ResponseEntity<>("Xuat file excel thanh cong", HttpStatus.OK);
    }
}
