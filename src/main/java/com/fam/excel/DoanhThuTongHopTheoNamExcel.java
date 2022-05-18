package com.fam.excel;

import com.fam.dto.statistic.SummaryByYear;
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
public class DoanhThuTongHopTheoNamExcel extends ExcelHelper {
    private List<SummaryByYear> data;

    public DoanhThuTongHopTheoNamExcel() {
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
        for (SummaryByYear x : data) {
            Row row = super.getSheet().createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, x.getMonth(), style);
            createCell(row, columnCount++, x.getPrdSold(), style);
            createCell(row, columnCount++, x.getTotal(), style);
        }
    }
}
