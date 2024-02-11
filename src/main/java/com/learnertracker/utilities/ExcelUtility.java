package com.learnertracker.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	private static XSSFSheet ExcelWSheet;

	public static void setExcelFile(String path, String sheetName) throws IOException {
		FileInputStream ExcelFile = new FileInputStream(new File(path));
		XSSFWorkbook ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(sheetName);
		ExcelWBook.close();
	}

	public static String getCellData(int rowNum, int colNum) {
		try {
			Cell cell = ExcelWSheet.getRow(rowNum).getCell(colNum);

			if (cell != null) {
				if (cell.getCellType() == CellType.STRING) {
					return cell.getStringCellValue();
				} else if (cell.getCellType() == CellType.NUMERIC) {
					// Convert numeric value to string
					return String.valueOf((int) cell.getNumericCellValue());
				} else {
					return null; // Handle other cell types as needed
				}
			} else {
				return null; // Cell is null
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null; // Handle exceptions gracefully
		}
	}
}