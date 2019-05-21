package com.ats.rusasoft.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Header;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.ExportToExcel;
import com.ats.rusasoft.master.model.prodetail.ProgramType;
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.reports.FacParticipationInBodies;
import com.ats.rusasoft.model.reports.NoOfPrograms;
import com.ats.rusasoft.util.ItextPageEvent;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class ReportController {

	RestTemplate rest = new RestTemplate();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());
	String redirect = null;

	MultiValueMap<String, Object> map = null;

	@RequestMapping(value = "/showReports", method = RequestMethod.GET)
	public ModelAndView showReports(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/report_dashboard");
			
			map = new LinkedMultiValueMap<String, Object>();
			map.add("type", 1);
			
			AcademicYear[] quolArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId", map,
					AcademicYear[].class);
			List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));

			model.addObject("acaYearList", acaYearList);
			
			ProgramType[] progTypes = rest.getForObject(Constants.url + "getAllProgramType",
					ProgramType[].class);
			List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
			model.addObject("progTypeList", progTypeList);
			

		} catch (Exception e) {

			System.err.println("Exce in showReports " + e.getMessage());
			e.printStackTrace();

		}

		return model;
	}

	@RequestMapping(value = "/showProgReport", method = RequestMethod.POST)
	public void showProgReport(HttpServletRequest request, HttpServletResponse response) {
		
		String reportName="Curricular Aspects : No of Certificate/Diploma Programs";
		
		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");
			
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			NoOfPrograms[] resArray = rest.postForObject(Constants.url + "getNoOfProgramsList", map,
					NoOfPrograms[].class);
			List<NoOfPrograms> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);

		
			Document document = new Document(PageSize.A4);
			//50, 45, 50, 60
			document.setMargins(Constants.marginLeft, Constants.marginRight, Constants.marginTop, Constants.marginBottom);
			document.setMarginMirroring(false);

			String FILE_PATH = Constants.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			String header = "";
			String title = "                 ";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");

			ItextPageEvent event = new ItextPageEvent(header, title, "",progList.get(0).getInstituteName());

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(6);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });

				Font headFontData =  Constants.headFontData;//new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; //new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
				tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);
				
				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name of Program", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);
				
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Level", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Duration (Months)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Introduction Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Approved By", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				for (int a = 0; a < 10; a++) {
					for (int i = 0; i < progList.size(); i++) {
						//System.err.println("I  " + i);
						NoOfPrograms prog = progList.get(i);

						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getNameOfProgram(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						//cell.setPaddingLeft(10);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getLevelOfProgram(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getMonthDuration(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getYearOfIntrod(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getApprovedBy(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

					}
				}

				document.open();
				Font reportNameFont = new Font(FontFamily.TIMES_ROMAN, 14.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : 2019-20"));
				//document.add(new Paragraph("Institute " + progList.get(0).getInstituteName()));
				/*
				 * Paragraph company = new Paragraph("Customer Wise Report\n", f);
				 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
				 * document.add(new Paragraph(" "));
				 */

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				System.out.println("Page no " + totalPages);

				document.close();
				int p = Integer.parseInt(request.getParameter("p"));
				System.err.println("p " + p);
				
				if (p == 1) {

					if (file != null) {

						String mimeType = URLConnection.guessContentTypeFromName(file.getName());

						if (mimeType == null) {

							mimeType = "application/pdf";

						}

						response.setContentType(mimeType);

						response.addHeader("content-disposition",
								String.format("inline; filename=\"%s\"", file.getName()));

						response.setContentLength((int) file.length());

						InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

						try {
							FileCopyUtils.copy(inputStream, response.getOutputStream());
						} catch (IOException e) {
							System.out.println("Excep in Opening a Pdf File");
							e.printStackTrace();
						}
					}
				}else {
					
					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();
					
					rowData.add("Sr. No");
					rowData.add("Name of Program");
					rowData.add("Level");
					rowData.add("Duration (Months)");
					rowData.add("Introduction Year");
					rowData.add("Approved By");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
				
					for (int a = 0; a < 10; a++) {
						int cnt = 1;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + progList.get(i).getNameOfProgram());
						rowData.add("" + progList.get(i).getLevelOfProgram());
						rowData.add("" + progList.get(i).getMonthDuration());
						rowData.add("" + progList.get(i).getYearOfIntrod());
						rowData.add("" + progList.get(i).getApprovedBy());
						

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						//String excelName = (String) session.getAttribute("excelName");
						wb = createWorkbook(exportToExcelList,progList.get(0).getInstituteName(),reportName);
                       autoSizeColumns(wb, 2);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition", "attachment; filename=" +reportName + "-" + date + ".xlsx");
						wb.write(response.getOutputStream());

					} catch (IOException ioe) {
						throw new RuntimeException("Error writing spreadsheet to output stream");
					} finally {
						if (wb != null) {
							wb.close();
						}
					}

				}

			} catch (DocumentException ex) {

				System.out.println("Pdf Generation Error: " + ex.getMessage());

				ex.printStackTrace();

			}

		} catch (Exception e) {

			System.err.println("Exce in showProgReport " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	private XSSFWorkbook createWorkbook(List<ExportToExcel> exportToExcelList,String instName,String reportName) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("Sheet1");
        sheet.createFreezePane(0, 3);
        CellStyle style=wb.createCellStyle();
	    style.setAlignment(CellStyle.ALIGN_CENTER);

	        Row titleRow = sheet.createRow(0);
	        titleRow.setHeightInPoints(20);
	        titleRow.setRowStyle(style);
	        Cell titleCell = titleRow.createCell(0);
	        
	        //titleCell.setCellValue("Report");
	        titleCell.setCellValue(instName);
	        
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$L$1"));
	        
	        Row titleRow2 = sheet.createRow(1);
	        titleRow2.setHeightInPoints(20);
	        titleRow2.setRowStyle(style);
	        Cell titleCell2 = titleRow2.createCell(0);
	        
	        //titleCell2.setCellValue("Sub ");
	        titleCell2.setCellValue(reportName);
	        
	        sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$L$2"));
       
		/*
		 * writeHeaders(wb, sheet); writeHeaders(wb, sheet); writeHeaders(wb, sheet);
		 */

		for (int rowIndex = 0; rowIndex < exportToExcelList.size(); rowIndex++) {
			XSSFRow row = sheet.createRow(rowIndex+2);
			for (int j = 0; j < exportToExcelList.get(rowIndex).getRowData().size(); j++) {

				XSSFCell cell = row.createCell(j);

				cell.setCellValue(exportToExcelList.get(rowIndex).getRowData().get(j));
				if ((rowIndex+2) == 2)
		            cell.setCellStyle(createHeaderStyleNew(wb));  
			}
			//if (rowIndex == 0)
				//row.setRowStyle(createHeaderStyle(wb));
		}
		return wb;
	}
	 public void autoSizeColumns(Workbook workbook,int index) {
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
	  private XSSFCellStyle createHeaderStyleNew(XSSFWorkbook workbook) {
	        XSSFCellStyle style = workbook.createCellStyle();
	        style.setWrapText(true);
	        style.setFillForegroundColor(new XSSFColor(new java.awt.Color(247, 161, 103)));

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
	        font.setFontName("Arial");
	        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
	        font.setBold(true);
	        font.setColor(HSSFColor.WHITE.index);
	        style.setFont(font);
	 
	        return style;
	    }
	private XSSFCellStyle createHeaderStyle(XSSFWorkbook workbook) {
		XSSFCellStyle style = workbook.createCellStyle();
		style.setWrapText(true);
		style.setFillForegroundColor(new XSSFColor(new java.awt.Color(53, 119, 192)));

		org.apache.poi.ss.usermodel.Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setBoldweight(org.apache.poi.hssf.usermodel.HSSFFont.BOLDWEIGHT_BOLD);
		font.setBold(true);
		// font.setColor(HSSFColor.WHITE.index);
		style.setFont(font);

		return style;
	}
	
	
	@RequestMapping(value = "/showFacPartiVarBodies", method = RequestMethod.POST)
	public void showFacPartiVarBodies(HttpServletRequest request, HttpServletResponse response) {
		
		String reportName="Curricular Aspects : Percentage(%) of Participation in variousUniversity Bodies";
		
		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);
			
			map.add("acYearList", "4");
			
			FacParticipationInBodies[] resArray = rest.postForObject(Constants.url + "getFacParticipationInBodies", map,
					FacParticipationInBodies[].class);
			List<FacParticipationInBodies> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);

			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showCustomerwisePdf");
			Document document = new Document(PageSize.A4);
			document.setMargins(50, 45, 50, 60);
			document.setMarginMirroring(false);

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String FILE_PATH = Constants.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			String header = "";
			String title = "                 ";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			String repDate = DF2.format(new Date());

			ItextPageEvent event = new ItextPageEvent(header, title, "",progList.get(0).getInstituteName());

			writer.setPageEvent(event);
			//writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(6);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
				Font headFontData =  Constants.headFontData;//new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; //new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
				tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Faculty Name", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Member of", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("University", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Validity", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
					for (int i = 0; i < progList.size(); i++) {
						//System.err.println("I  " + i);
						FacParticipationInBodies prog = progList.get(i);

						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getAcademicYear(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getFacultyFirstName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getConLevel()+"-" + prog.getConName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getConUniversity(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase(""+prog.getConTo(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

					}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);
				//document.add(new Paragraph("Institute " + progList.get(0).getInstituteName()));
				/*
				 * Paragraph company = new Paragraph("Customer Wise Report\n", f);
				 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
				 * document.add(new Paragraph(" "));
				 */

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("For Academic Year : 2018-19"));
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				System.out.println("Page no " + totalPages);

				document.close();
				int p = Integer.parseInt(request.getParameter("p"));
				System.err.println("p " + p);
				
				if (p == 1) {

					if (file != null) {

						String mimeType = URLConnection.guessContentTypeFromName(file.getName());

						if (mimeType == null) {

							mimeType = "application/pdf";

						}

						response.setContentType(mimeType);

						response.addHeader("content-disposition",
								String.format("inline; filename=\"%s\"", file.getName()));

						response.setContentLength((int) file.length());

						InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

						try {
							FileCopyUtils.copy(inputStream, response.getOutputStream());
						} catch (IOException e) {
							System.out.println("Excep in Opening a Pdf File");
							e.printStackTrace();
						}
					}
				}else {
					
					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();
					
					rowData.add("Sr. No");
					rowData.add("Academic Year");
					rowData.add("Name of Faculty");
					rowData.add("Member of");
					rowData.add("University");
					rowData.add("Valid upto");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + progList.get(i).getAcademicYear());
						rowData.add("" + progList.get(i).getFacultyFirstName());
						rowData.add(""+progList.get(i).getConLevel()+"" + progList.get(i).getConName());
						rowData.add("" + progList.get(i).getConUniversity());
						rowData.add("" + progList.get(i).getConTo());
						

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						//String excelName = (String) session.getAttribute("excelName");
						wb = createWorkbook(exportToExcelList,progList.get(0).getInstituteName(),reportName);
						autoSizeColumns(wb, 0);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition", "attachment; filename=" +reportName + "-" + date + ".xlsx");
						wb.write(response.getOutputStream());

					} catch (IOException ioe) {
						throw new RuntimeException("Error writing spreadsheet to output stream");
					} finally {
						if (wb != null) {
							wb.close();
						}
					}

				}

			} catch (DocumentException ex) {

				System.out.println("Pdf Generation Error: " + ex.getMessage());

				ex.printStackTrace();

			}

		} catch (Exception e) {

			System.err.println("Exce in showProgReport " + e.getMessage());
			e.printStackTrace();

		}

	}

}
