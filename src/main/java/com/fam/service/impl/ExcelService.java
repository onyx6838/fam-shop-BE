package com.fam.service.impl;

import com.fam.entity.SanPham;
import com.fam.excel.SanPhamExcelHelper;
import com.fam.excel.ThongKeSanPhamBanTheoTungThangExcelHelper;
import com.fam.service.IExcelService;
import com.fam.service.ISanPhamService;
import com.fam.service.IThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author giangdm
 */
@Service
public class ExcelService implements IExcelService {
    @Autowired
    private ISanPhamService sanPhamService;

    @Autowired
    private IThongKeService thongKeService;

    @Override
    public void exportToExcelSanPham(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sanpham_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<SanPham> sanPhamList = sanPhamService.getAllSanPhamWithoutPaging();

        SanPhamExcelHelper excelExporter = new SanPhamExcelHelper(sanPhamList);

        excelExporter.export(response);
    }

    @Override
    public void exportToExcelSanPhamSoldPerMonthInYear(HttpServletResponse response, int selectedYear) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sanphamthongkethangtrong" + selectedYear + "_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Map<Integer, List<Object>> data = thongKeService.productSoldPerMonthInYear(selectedYear);

        ThongKeSanPhamBanTheoTungThangExcelHelper tk = new ThongKeSanPhamBanTheoTungThangExcelHelper(data);

        tk.export(response);
    }
}
