package com.TechConnect.FileUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtils 
{
	public static String Exceldata(String s, int row, int column) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("src/test/resources/TC_UserDetails.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(s);
		String data =sheet.getRow(row).getCell(column).toString();
		return data;
		
	}
	public static int getRows(String s) throws EncryptedDocumentException, IOException
	{
		FileInputStream fis = new FileInputStream("src/test/resources/TC_UserDetails.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sheet = wb.getSheet(s);
	
		int rows = sheet.getPhysicalNumberOfRows();
		return rows;
		
	}
//	public static int getCells(String s) throws EncryptedDocumentException, IOException
//	{
//		FileInputStream fis = new FileInputStream("C:\\Users\\Admin\\Documents\\MetaperceptData.xlsx");
//		Workbook wb = WorkbookFactory.create(fis);
//		Sheet sheet = wb.getSheet(s);
//		int rows = sheet.getPhysicalNumberOfRows();
//		int cells = sheet.getRow(rows).getPhysicalNumberOfCells();
//		return cells;
//		
//	}
	public static int getCells(String s) throws EncryptedDocumentException, IOException {
	    FileInputStream fis = new FileInputStream("src/test/resources/TC_UserDetails.xlsx");
	    Workbook wb = WorkbookFactory.create(fis);
	    Sheet sheet = wb.getSheet(s);
	    
	    int rows = sheet.getPhysicalNumberOfRows();
	    
	    if (rows == 0) {
	        wb.close();
	        return 0;  // Return 0 if no rows exist
	    }
	    
	    Row row = sheet.getRow(rows - 1);  // Get the last row
	    if (row == null) {
	        wb.close();
	        return 0;  // Return 0 if the last row is empty
	    }
	    
	    int cells = row.getPhysicalNumberOfCells();
	    wb.close();
	    return cells;
	}
}
