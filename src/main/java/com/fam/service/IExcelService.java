package com.fam.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author giangdm
 */
public interface IExcelService {
    void exportToExcelSanPham(HttpServletResponse response) throws IOException;

    void exportToExcelSanPhamSoldPerMonthInYear(HttpServletResponse response, int selectedYear) throws IOException;
    void exportToExcelDoanhThuTongHopInYear(HttpServletResponse response, int selectedYear) throws IOException;
}
