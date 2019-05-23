package com.ats.rusasoft.commons;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExceUtil {
	
	
	public static XSSFWorkbook createWorkbook(List<ExportToExcel> exportToExcelList,String instName,String reportName,String filterValue) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Sheet1");
       System.err.println();
		sheet.createFreezePane(0, 4);
       
			CellStyle style=wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_RIGHT);
			style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			 style.setWrapText(true);
	        Row titleRow = sheet.createRow(0);
	        titleRow.setHeightInPoints(20);
	        titleRow.setRowStyle(style);
	       
	        Cell titleCell = titleRow.createCell(0);
	        titleCell.setCellValue(instName);
	        titleCell.setCellStyle(style);
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$F$1"));
	        
	       
	        CellStyle style2=wb.createCellStyle();
		    style2.setAlignment(CellStyle.ALIGN_CENTER);
		    //style2.setAlignment(HorizontalAlignment.RIGHT);
		    style2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		   
		    Row titleRow2 = sheet.createRow(1);
	        titleRow2.setHeightInPoints(30);
	        titleRow2.setRowStyle(style2);
	        style2.setWrapText(true);
	        //style.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
	        Font font = wb.createFont();
	        font.setFontHeightInPoints((short)12);
	        font.setUnderline(HSSFFont.U_SINGLE);

	        font.setFontName("Times Roman");
	       
	        style2.setFont(font);
	        Cell titleCell2 = titleRow2.createCell(0);
	        titleCell2.setCellStyle(style2);
	        
	        titleCell2.setCellValue(reportName);
	        
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
	       
	        CellStyle style3=wb.createCellStyle();
		    style3.setAlignment(CellStyle.ALIGN_LEFT);
		    style3.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		    
	        Row titleRow3 = sheet.createRow(2);
	        titleRow3.setHeightInPoints(20);
	        titleRow3.setRowStyle(style3);
	        
	        Cell titleCell3 = titleRow3.createCell(0);
	        titleCell3.setCellStyle(style3);
	        titleCell3.setCellValue(""+filterValue);//Need Dynamic
	        
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$3:$F$3"));
	       
     

		for (int rowIndex = 0; rowIndex < exportToExcelList.size(); rowIndex++) {
			XSSFRow row = sheet.createRow(rowIndex+3);
			for (int j = 0; j < exportToExcelList.get(rowIndex).getRowData().size(); j++) {

				XSSFCell cell = row.createCell(j);

				cell.setCellValue(exportToExcelList.get(rowIndex).getRowData().get(j));
				if ((rowIndex+3) == 3)
		           cell.setCellStyle(createHeaderStyleNew(wb));  
			}
			
		}
		return wb;
	}
	public static void autoSizeColumns(Workbook workbook,int index) {
	        int numberOfSheets = workbook.getNumberOfSheets();
	        for (int i = 0; i < numberOfSheets; i++) {
	            Sheet sheet = workbook.getSheetAt(i);
	            if (sheet.getPhysicalNumberOfRows() > 0) {
	                Row row = sheet.getRow(index);
	                row.setHeight((short)700);

	                Iterator<Cell> cellIterator = row.cellIterator();
	                while (cellIterator.hasNext()) {
	                    Cell cell = cellIterator.next();
	                    int columnIndex = cell.getColumnIndex();
	                    sheet.autoSizeColumn(columnIndex);
	                }
	            }
	        }
	    }
	public static XSSFCellStyle createHeaderStyleNew(XSSFWorkbook workbook) {
	        XSSFCellStyle style = workbook.createCellStyle();
	        style.setWrapText(true);
	       // style.setFillForegroundColor(new XSSFColor(new java.awt.Color(247, 161, 103)));
	        
	        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());


	        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        style.setAlignment(CellStyle.ALIGN_CENTER);
	        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

	        style.setBorderRight(CellStyle.BORDER_THIN);
	        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	        style.setBorderBottom(CellStyle.BORDER_THIN);
	        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	        style.setBorderLeft(CellStyle.BORDER_THIN);
	        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	        style.setBorderTop(CellStyle.BORDER_THIN);
	        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	        style.setDataFormat(1);
	       
	        org.apache.poi.ss.usermodel.Font font =workbook.createFont();
	        font.setFontName("Times New Roman");
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        font.setBold(true);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	 
	        return style;
	    }

}