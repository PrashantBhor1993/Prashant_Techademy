package com.assignment.tests;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadExcel {
//Task 1- Implement below Program using Java Apache POI Concept. Read data
//from MS-Excel Sheet and Print it in IDE Console

    @Test
    public void readExcel() throws IOException {
        String filePath = "src/test/resources/EmployeeDetails.xlsx";
        FileInputStream fs = new FileInputStream(filePath);

        Workbook workbook = WorkbookFactory.create(fs);
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case NUMERIC:
                        System.out.print((int) cell.getNumericCellValue() + "\t");
                        break;
                    default:
                        System.out.print("\t");
                }
            }
            System.out.println();
        }
        workbook.close();
        fs.close();
    }
}
