package com.mysbrcif.core.util;


import org.apache.poi.xssf.usermodel.*;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Component(immediate = true, service = ExcelReader.class)
public class ExcelReader {

    private final Logger log = LoggerFactory.getLogger(ExcelReader.class);
    public static FileInputStream fis = null;
    public static XSSFWorkbook workbook = null;
    public static XSSFSheet sheet = null;
    public static XSSFRow row = null;
    public XSSFCell cell = null;
    String xlFilePath;

    public static void ExcelApiTest(String xlFilePath) throws Exception
    {

        fis = new FileInputStream(xlFilePath);
        workbook = new XSSFWorkbook(fis);
        fis.close();
    }

    public static int getRowCount(String xlFilePath, String sheetName) throws Exception {
        ExcelApiTest(xlFilePath);
        sheet = workbook.getSheet(sheetName);
        int rowCount = sheet.getLastRowNum()+1;
        return rowCount;
    }

    public static int getColumnCount(String xlFilePath,String sheetName) throws Exception {
        ExcelApiTest(xlFilePath);
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(0);
        int colCount = row.getLastCellNum();
        return colCount;
    }
}
