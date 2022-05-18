package com.fam.service.impl;

import com.fam.dto.statistic.SummaryByYear;
import com.fam.entity.SanPham;
import com.fam.excel.DoanhThuTongHopTheoNamExcel;
import com.fam.excel.SanPhamExcel;
import com.fam.excel.ThongKeSanPhamBanTheoThangExcel;
import com.fam.service.IExcelService;
import com.fam.service.ISanPhamService;
import com.fam.service.IThongKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        SanPhamExcel spx = new SanPhamExcel();
        spx.setData(sanPhamList);
        List<String> titles = new ArrayList<>();
        titles.add("Mã sản phẩm");
        titles.add("Tên");
        titles.add("Đơn giá nhập");
        titles.add("Đơn giá bán");
        titles.add("Số lượng");
        titles.add("Loại sản phẩm");
        titles.add("Thương hiệu");
        spx.setColumnTitles(titles.toArray(new String[0]));
        spx.setHeaderName("San pham");

        spx.export(response);
    }

    @Override
    public void exportToExcelSanPhamSoldPerMonthInYear(HttpServletResponse response, int selectedYear) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=sanphamthongketheothangtrong" + selectedYear + "_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        Map<Integer, List<Object>> data = thongKeService.productSoldPerMonthInYear(selectedYear);

        ThongKeSanPhamBanTheoThangExcel txh = new ThongKeSanPhamBanTheoThangExcel();
        txh.setData(data);
        List<String> titles = new ArrayList<>();
        titles.add("Mã sản phẩm");
        titles.add("Tên");
        titles.add("Tháng 1");
        titles.add("Tháng 2");
        titles.add("Tháng 3");
        titles.add("Tháng 4");
        titles.add("Tháng 5");
        titles.add("Tháng 6");
        titles.add("Tháng 7");
        titles.add("Tháng 8");
        titles.add("Tháng 9");
        titles.add("Tháng 10");
        titles.add("Tháng 11");
        titles.add("Tháng 12");
        titles.add("Cả Năm");
        txh.setColumnTitles(titles.toArray(new String[0]));
        txh.setHeaderName("Thong ke san pham");
        txh.export(response);
    }

    @Override
    public void exportToExcelDoanhThuTongHopInYear(HttpServletResponse response, int selectedYear) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=doanhthutonghopnam" + selectedYear + "_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<SummaryByYear> data = thongKeService.summaryByYear(selectedYear);
        DoanhThuTongHopTheoNamExcel txh = new DoanhThuTongHopTheoNamExcel();
        txh.setData(data);
        List<String> titles = new ArrayList<>();
        titles.add("Tháng");
        titles.add("Số mặt hàng bán ra");
        titles.add("Doanh thu");

        txh.setColumnTitles(titles.toArray(new String[0]));
        txh.setHeaderName("Doanh thu tong hop nam " + selectedYear);
        txh.export(response);
    }
}
