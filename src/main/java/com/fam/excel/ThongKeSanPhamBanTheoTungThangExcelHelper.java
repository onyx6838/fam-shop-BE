package com.fam.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author giangdm
 */
public class ThongKeSanPhamBanTheoTungThangExcelHelper {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private Map<Integer, List<Object>> data;

    public ThongKeSanPhamBanTheoTungThangExcelHelper(Map<Integer, List<Object>> data) {
        this.data = data;
        workbook = new XSSFWorkbook();
    }

    private void writeHeaderLine() {
        sheet = workbook.createSheet("Thong ke san pham");
        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        font.setFontName("Times New Roman");
        style.setFont(font);

        createCell(row, 0, "Mã sản phẩm", style);
        createCell(row, 1, "Tên", style);
        createCell(row, 2, "Tháng 1", style);
        createCell(row, 3, "Tháng 2", style);
        createCell(row, 4, "Tháng 3", style);
        createCell(row, 5, "Tháng 4", style);
        createCell(row, 6, "Tháng 5", style);
        createCell(row, 7, "Tháng 6", style);
        createCell(row, 8, "Tháng 7", style);
        createCell(row, 9, "Tháng 8", style);
        createCell(row, 10, "Tháng 9", style);
        createCell(row, 11, "Tháng 10", style);
        createCell(row, 12, "Tháng 11", style);
        createCell(row, 13, "Tháng 12", style);
        createCell(row, 14, "Cả Năm", style);
    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
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
        List<Integer> maSPSet = new ArrayList<>(data.keySet());
        Collections.sort(maSPSet);
        for (Integer x : maSPSet) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, x + "", style);
            List<Object> valueCell = data.get(x);
            for (int i = 0; i < valueCell.size(); i++) {
                createCell(row, columnCount++, valueCell.get(i), style);
            }
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
