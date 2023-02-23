package com.jack.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtlity {


	
	public static FileInputStream fileInputStream;
	public static FileOutputStream fileOutputStream;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	
	
	public static int getRowCount(String filename, String sheetname ) throws IOException {
		
		
		fileInputStream=new FileInputStream(filename);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetname);
		int rowscount=sheet.getLastRowNum();
		workbook.close();
		fileInputStream.close();
		
		
		return rowscount;
		
		
	}
	
	public static int xCellNum(String path, String sName, int row) throws IOException {
		
 		fileInputStream=new FileInputStream(path);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sName);
//		int rowsc=sheet.getLastRowNum();
		int cellnum = sheet.getRow(row).getLastCellNum();
		workbook.close();
		fileInputStream.close();
		
		
		return cellnum;
	}
	
	public static int getCellData(String filename, String sheetname, int rowNum) throws IOException {
		
		fileInputStream=new FileInputStream(filename);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetname);
		row=sheet.getRow(rowNum);
		int cellCount = row.getLastCellNum();
		workbook.close();
		fileInputStream.close();
		
		return cellCount;
		
	}
	
	
	public static String getCellData(String filename, String sheetname, int rowNum, int colNum) throws IOException {
		
		fileInputStream=new FileInputStream(filename);
		workbook=new XSSFWorkbook(fileInputStream);
		sheet = workbook.getSheet(sheetname);
		row=sheet.getRow(rowNum);
		cell=row.getCell(colNum);
		
		String data;
		try {
			
			DataFormatter dataFormatter=new DataFormatter();
			String cellData = dataFormatter.formatCellValue(cell);
			return cellData;
			
		} catch (Exception e) {
			
			data="";
		}
		workbook.close();
		fileInputStream.close();
		return data;
		
	}
	
public static void setCellDataBig(String path ,String sName, int rowcount, int cellcount, StringBuilder celldata) throws IOException {
		
		File xlfile=new File(path);
		if (!xlfile.exists()) {
			workbook=new XSSFWorkbook();
			fileOutputStream=new FileOutputStream(path);
			workbook.write(fileOutputStream);
		}
		
		fileInputStream=new FileInputStream(path);
		workbook=new XSSFWorkbook(fileInputStream);
		
		if(workbook.getSheetIndex(sName)==-1)
			workbook.createSheet(sName);
		sheet=workbook.getSheet(sName);
		
		
		if(sheet.getRow(rowcount)==null)
			sheet.createRow(rowcount);
		row=sheet.getRow(rowcount);
		
		
		cell=row.createCell(cellcount);
		
		cell.setCellValue(celldata.toString());
		fileOutputStream=new FileOutputStream(path);
		workbook.write(fileOutputStream);
		workbook.close();
		fileInputStream.close();
		fileOutputStream.close();
	}

public static void setCellData(String path ,String sName, int rowcount, int cellcount, String celldata) throws IOException {
	
	File xlfile=new File(path);
	if (!xlfile.exists()) {
		workbook=new XSSFWorkbook();
		fileOutputStream=new FileOutputStream(path);
		workbook.write(fileOutputStream);
	}
	
	fileInputStream=new FileInputStream(path);
	workbook=new XSSFWorkbook(fileInputStream);
	
	if(workbook.getSheetIndex(sName)==-1)
		workbook.createSheet(sName);
	sheet=workbook.getSheet(sName);
	
	
	if(sheet.getRow(rowcount)==null)
		sheet.createRow(rowcount);
	row=sheet.getRow(rowcount);
	
	
	cell=row.createCell(cellcount);
	
	cell.setCellValue(celldata);
	fileOutputStream=new FileOutputStream(path);
	workbook.write(fileOutputStream);
	workbook.close();
	fileInputStream.close();
	fileOutputStream.close();
}



}
