package com.fam.excel;

import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author giangdm
 */
@Getter
@Setter
public class ThongKeSanPhamBanTheoThangExcel extends ExcelHelper {
    private Map<Integer, List<Object>> data;

    public ThongKeSanPhamBanTheoThangExcel() {
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
        List<Integer> maSPSet = new ArrayList<>(data.keySet());
        Collections.sort(maSPSet);
        for (Integer x : maSPSet) {
            Row row = super.getSheet().createRow(rowCount++);
            int columnCount = 0;
            createCell(row, columnCount++, x + "", style);
            List<Object> valueCell = data.get(x);
            for (int i = 0; i < valueCell.size(); i++) {
                createCell(row, columnCount++, valueCell.get(i), style);
            }
        }
    }
}
