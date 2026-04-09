package com.amazon.utils;

import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {

    public static Object[][] getData(String path, String sheetName){
        Object [][] data;
        try{
       FileInputStream fis = new FileInputStream(path);
       Workbook wb = WorkbookFactory.create(fis);
       Sheet sheet = wb.getSheet(sheetName);
            DataFormatter formatter = new DataFormatter();
         int rownum = sheet.getLastRowNum();// returns index  2
         int cellnum = sheet.getRow(0).getLastCellNum();// returns count

         data = new Object[rownum][cellnum];
                for (int i = 1; i <= rownum; i++) {
                    Row row = sheet.getRow(i);

                    for (int j = 0; j < cellnum; j++) {
                        Cell cell = (row == null) ? null : row.getCell(j);
//                        data[i - 1][j] = (cell == null) ? "" : cell.toString();
                        data[i - 1][j] = (cell == null) ? "" : formatter.formatCellValue(cell);
                    }
                }

            wb.close();
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

return data;
    }
}
