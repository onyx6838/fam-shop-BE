package com.fam.excel;

import com.fam.entity.SanPham;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.util.List;

/**
 * @author giangdm
 */
@Getter
@Setter
public class SanPhamExcel extends ExcelHelper {
    private List<SanPham> data;

    public SanPhamExcel() {
        super();
    }

    @Override
    public void writeDataLines() {
        int rowCount = 1;

        CellStyle style = super.getWorkbook().createCellStyle();
        XSSFFont font = super.getWorkbook().createFont();
        font.setFontHeight(14);
        style.setFont(font);
        font.setFontName("Times New Roman");
        for (SanPham x : data) {
            Row row = super.getSheet().createRow(rowCount++);
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

}
