package com.fam.excel;

import com.fam.entity.SanPham;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author giangdm
 */
public class SanPhamExcelHelper {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<SanPham> sanPhamList;

    public SanPhamExcelHelper(List<SanPham> sanPhamList) {
        this.sanPhamList = sanPhamList;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("SanPham");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        font.setFontName("Times New Roman");
        style.setFont(font);

        createCell(row, 0, "Mã sản phẩm", style);
        createCell(row, 1, "Tên", style);
        createCell(row, 2, "Đơn giá nhập", style);
        createCell(row, 3, "Đơn giá bán", style);
        createCell(row, 4, "Số lượng", style);
        createCell(row, 5, "Loại sản phẩm", style);
        createCell(row, 6, "Thương hiệu", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else if (value instanceof Double) {
            cell.setCellValue((Double) value);
        } else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);
        font.setFontName("Times New Roman");
        for (SanPham x : sanPhamList) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, x.getMaSP(), style);
            createCell(row, columnCount++, x.getTen(), style);
            createCell(row, columnCount++, x.getDonGiaNhap(), style);
            createCell(row, columnCount++, x.getDonGiaBan(), style);
            createCell(row, columnCount++, x.getSoLuong(), style);
            createCell(row, columnCount++, x.getLoaiSanPham().getTen(), style);
            createCell(row, columnCount++, x.getThuongHieu().getTenThuongHieu(), style);
        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
