package com.fam.excel;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author giangdm
 */
public interface IExcelHelper {
    void writeHeaderLine(String[] columnTitles, String sheetName);

    void createCell(Row row, int columnCount, Object value, CellStyle style);

    void export(HttpServletResponse response) throws IOException;
}
