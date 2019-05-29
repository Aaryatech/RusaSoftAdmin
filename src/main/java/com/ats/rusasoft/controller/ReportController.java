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
import java.text.DecimalFormat;
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
import org.apache.poi.xssf.usermodel.XSSFFont;
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
import com.ats.rusasoft.commons.ExceUtil;
import com.ats.rusasoft.commons.ExportToExcel;
import com.ats.rusasoft.master.model.prodetail.Cast;
import com.ats.rusasoft.master.model.prodetail.ProgramType;
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.reports.AdmissionsAgainstCategory;
import com.ats.rusasoft.model.reports.AvgEnrollmentPrcnt;
import com.ats.rusasoft.model.reports.FacParticipationInBodies;
import com.ats.rusasoft.model.reports.GetAvgStudYearwise;
import com.ats.rusasoft.model.reports.GetTeachersUsingICT;
import com.ats.rusasoft.model.reports.LibAutoLMSInfo;
import com.ats.rusasoft.model.reports.LibSpecFacilities;
import com.ats.rusasoft.model.reports.NoOfMentorsAssignedStudent;
import com.ats.rusasoft.model.reports.NoOfPrograms;
import com.ats.rusasoft.model.reports.RareBookManuscriptSpec;
import com.ats.rusasoft.model.reports.StudentPerformanceOutcome;
import com.ats.rusasoft.model.reports.TeacherStudUsingLib;
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

			ProgramType[] progTypes = rest.getForObject(Constants.url + "getAllProgramType", ProgramType[].class);
			List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
			model.addObject("progTypeList", progTypeList);

			map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			Cast[] catsArray = restTemplate.getForObject(Constants.url + "getAllCastCategory", Cast[].class);
			List<Cast> castList = new ArrayList<>(Arrays.asList(catsArray));
			System.err.println("castList " + castList.toString());

			model.addObject("castList", castList);

		} catch (Exception e) {

			System.err.println("Exce in showReports " + e.getMessage());
			e.printStackTrace();

		}

		return model;
	}

	@RequestMapping(value = "/showProgReport", method = RequestMethod.POST)
	public void showProgReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Curricular Aspects : No of Certificate/Diploma Programs";

		ModelAndView model = null;
		try {
			// String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
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
			// 50, 45, 50, 60
			document.setMargins(Constants.marginLeft, Constants.marginRight, Constants.marginTop,
					Constants.marginBottom);
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
			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(6);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });

				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
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

				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					NoOfPrograms prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getNameOfProgram(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					// cell.setPaddingLeft(10);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getLevelOfProgram(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getMonthDuration(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getYearOfIntrod(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getApprovedBy(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

				}

				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year :" + temp_ac_year + ""));
				// document.add(new Paragraph("Institute " +
				// progList.get(0).getInstituteName()));
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
				} else {

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

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = progList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("rep  " + rep);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,
								"Academic Year:" + temp_ac_year + " ", " ", 'F');
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	/*
	 * private XSSFWorkbook createWorkbook(List<ExportToExcel>
	 * exportToExcelList,String instName,String reportName) throws IOException {
	 * XSSFWorkbook wb = new XSSFWorkbook(); XSSFSheet sheet =
	 * wb.createSheet("Sheet1"); sheet.createFreezePane(0, 3); CellStyle
	 * style=wb.createCellStyle(); style.setAlignment(CellStyle.ALIGN_RIGHT);
	 * 
	 * Row titleRow = sheet.createRow(0); titleRow.setHeightInPoints(20);
	 * titleRow.setRowStyle(style); Cell titleCell = titleRow.createCell(0);
	 * 
	 * //titleCell.setCellValue("Report"); titleCell.setCellValue(instName);
	 * 
	 * sheet.addMergedRegion(CellRangeAddress.valueOf("$A$1:$F$1"));
	 * 
	 * 
	 * Row titleRow3 = sheet.createRow(0); titleRow3.setHeightInPoints(20);
	 * titleRow3.setRowStyle(style); Cell titleCell3 = titleRow3.createCell(0);
	 * titleCell3.setCellValue("Academic Year :2018-2019 ");//Need Dynamic
	 * 
	 * //titleCell.setCellValue("Report"); //titleCell3.setCellValue(instName);
	 * 
	 * sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
	 * 
	 * CellStyle style2=wb.createCellStyle();
	 * style2.setAlignment(CellStyle.ALIGN_CENTER); Row titleRow2 =
	 * sheet.createRow(1); titleRow2.setHeightInPoints(20);
	 * titleRow2.setRowStyle(style2);
	 * 
	 * XSSFFont font=wb.createFont();
	 * 
	 * 
	 * Cell titleCell2 = titleRow2.createCell(0);
	 * 
	 * //titleCell2.setCellValue("Sub "); titleCell2.setCellValue(reportName);
	 * 
	 * 
	 * sheet.addMergedRegion(CellRangeAddress.valueOf("$A$2:$F$2"));
	 * 
	 * 
	 * writeHeaders(wb, sheet); writeHeaders(wb, sheet); writeHeaders(wb, sheet);
	 * 
	 * 
	 * for (int rowIndex = 0; rowIndex < exportToExcelList.size(); rowIndex++) {
	 * XSSFRow row = sheet.createRow(rowIndex+2); for (int j = 0; j <
	 * exportToExcelList.get(rowIndex).getRowData().size(); j++) {
	 * 
	 * XSSFCell cell = row.createCell(j);
	 * 
	 * cell.setCellValue(exportToExcelList.get(rowIndex).getRowData().get(j)); if
	 * ((rowIndex+2) == 2) cell.setCellStyle(createHeaderStyleNew(wb)); } //if
	 * (rowIndex == 0) //row.setRowStyle(createHeaderStyle(wb)); } return wb; }
	 * public void autoSizeColumns(Workbook workbook,int index) { int numberOfSheets
	 * = workbook.getNumberOfSheets(); for (int i = 0; i < numberOfSheets; i++) {
	 * Sheet sheet = workbook.getSheetAt(i); if (sheet.getPhysicalNumberOfRows() >
	 * 0) { Row row = sheet.getRow(index); row.setHeight((short)700);
	 * 
	 * Iterator<Cell> cellIterator = row.cellIterator(); while
	 * (cellIterator.hasNext()) { Cell cell = cellIterator.next(); int columnIndex =
	 * cell.getColumnIndex(); sheet.autoSizeColumn(columnIndex); } } } } private
	 * XSSFCellStyle createHeaderStyleNew(XSSFWorkbook workbook) { XSSFCellStyle
	 * style = workbook.createCellStyle(); style.setWrapText(true);
	 * style.setFillForegroundColor(new XSSFColor(new java.awt.Color(247, 161,
	 * 103)));
	 * 
	 * style.setFillPattern(CellStyle.SOLID_FOREGROUND);
	 * style.setAlignment(CellStyle.ALIGN_CENTER);
	 * style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
	 * 
	 * style.setBorderRight(CellStyle.BORDER_THIN);
	 * style.setRightBorderColor(IndexedColors.BLACK.getIndex());
	 * style.setBorderBottom(CellStyle.BORDER_THIN);
	 * style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
	 * style.setBorderLeft(CellStyle.BORDER_THIN);
	 * style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
	 * style.setBorderTop(CellStyle.BORDER_THIN);
	 * style.setTopBorderColor(IndexedColors.BLACK.getIndex());
	 * style.setDataFormat(1);
	 * 
	 * org.apache.poi.ss.usermodel.Font font =workbook.createFont();
	 * font.setFontName("Times New Roman");
	 * font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD); font.setBold(true);
	 * font.setColor(HSSFColor.WHITE.index); style.setFont(font);
	 * 
	 * return style; }
	 */

	@RequestMapping(value = "/showFacPartiVarBodies", method = RequestMethod.POST)
	public void showFacPartiVarBodies(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Curricular Aspects : Percentage(%) of Participation in variousUniversity Bodies";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("acYearList", ac_year);

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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(6);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
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
					// System.err.println("I " + i);
					FacParticipationInBodies prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getFacultyFirstName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getConLevel() + "-" + prog.getConName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getConUniversity(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getConTo(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);
				// document.add(new Paragraph("Institute " +
				// progList.get(0).getInstituteName()));
				/*
				 * Paragraph company = new Paragraph("Customer Wise Report\n", f);
				 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
				 * document.add(new Paragraph(" "));
				 */

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
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
				} else {

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
						rowData.add("" + progList.get(i).getConLevel() + "" + progList.get(i).getConName());
						rowData.add("" + progList.get(i).getConUniversity());
						rowData.add("" + progList.get(i).getConTo());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						// wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
						// "Academic Year :" + temp_ac_year + " ");

						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year:" + temp_ac_year + " ", "", 'F');

						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	// Report 11

	@RequestMapping(value = "/showAvgEnrollPrcntReport", method = RequestMethod.POST)
	public void showAvgEnrollPrcntReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching -Learing and Evaluation : Average Enrollment Percentage";
		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("acYearList", ac_year);

			AvgEnrollmentPrcnt[] resArray = rest.postForObject(Constants.url + "getAvgEnrollmentPrcnt", map,
					AvgEnrollmentPrcnt[].class);
			List<AvgEnrollmentPrcnt> progList = new ArrayList<>(Arrays.asList(resArray));

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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
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

				hcell = new PdfPCell(new Phrase("Total Sanctioned Intake", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total No of Student Admitted", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("% Yearwise", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				double rslt = 0.0;

				int index = 0;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					AvgEnrollmentPrcnt prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getTotalSanctIntake(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getNoCurrentAdmitedStnt(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					DecimalFormat decimalFormat = new DecimalFormat("0.00");
					String tempprcnt = decimalFormat
							.format(prog.getNoCurrentAdmitedStnt() / prog.getTotalSanctIntake() * 100);

					System.out.println("prog.getNoCurrentAdmitedStnt()" + prog.getNoCurrentAdmitedStnt()
							+ "prog.getTotalSanctIntake()" + prog.getTotalSanctIntake());
					cell = new PdfPCell(new Phrase("" + tempprcnt, headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					rslt = rslt + Double.parseDouble(tempprcnt);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);
				// document.add(new Paragraph("Institute " +
				// progList.get(0).getInstituteName()));
				/*
				 * Paragraph company = new Paragraph("Customer Wise Report\n", f);
				 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
				 * document.add(new Paragraph(" "));
				 */
				double n = 0.0;
				if (ac_year.equals("-5")) {
					n = (rslt / 5);
				} else {
					n = rslt;
				}

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("Academic Year :" + temp_ac_year + ""));
				document.add(new Paragraph("\n"));
				document.add(table);
				document.add(new Paragraph("Avg% :" + n + ""));
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
				} else {

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Academic Year");
					rowData.add("Total Sanctioned Intake");
					rowData.add("Total No of Student Admitted");
					rowData.add("% Yearwise");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					double temp = 0.0;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;
						DecimalFormat decimalFormat = new DecimalFormat("0.00");
						String tempprcnt = decimalFormat.format(progList.get(i).getNoCurrentAdmitedStnt()
								/ progList.get(i).getTotalSanctIntake() * 100);
						rowData.add("" + (i + 1));

						rowData.add("" + progList.get(i).getAcademicYear());
						rowData.add("" + progList.get(i).getTotalSanctIntake());
						rowData.add("" + progList.get(i).getNoCurrentAdmitedStnt());

						rowData.add("" + tempprcnt);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String leaveSum = "Average% :" + n + "";
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year:" + temp_ac_year + " ", leaveSum, 'E');

						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	// Report 10
	@RequestMapping(value = "/showAvgStudYearwiseReport", method = RequestMethod.POST)
	public void showAvgStudYearwiseReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching -Learing and Evaluation : Average % of Students from other States/ Countries – Yearwise  ";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("acYearList", "-5");

			GetAvgStudYearwise[] resArray = rest.postForObject(Constants.url + "getAvgStudYearwiseLocWise", map,
					GetAvgStudYearwise[].class);
			List<GetAvgStudYearwise> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);

			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showAvgStudYearwiseReport");
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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(7);

			table.setHeaderRows(1);
			double f1 = 0;
			double f3 = 0;
			double f2 = 0;
			double f4 = 0;
			double f5 = 0;
			double s1 = 0;
			double s3 = 0;
			double s2 = 0;
			double s4 = 0;
			double s5 = 0;
			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Location", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("CAY", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("CAY-1", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("CAY-2", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("CAY-3", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("CAY-4", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					GetAvgStudYearwise prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getLocationName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcYearAdmiStud1(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcYearAdmiStud2(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcYearAdmiStud3(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcYearAdmiStud4(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcYearAdmiStud5(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					f1 = f1 + prog.getAcYearAdmiStud1();
					f2 = f2 + prog.getAcYearAdmiStud2();
					f3 = f3 + prog.getAcYearAdmiStud3();
					f4 = f4 + prog.getAcYearAdmiStud4();
					f5 = f5 + prog.getAcYearAdmiStud5();
					s1 = prog.getTotStud1();
					s2 = prog.getTotStud2();
					s3 = prog.getTotStud3();
					s4 = prog.getTotStud4();
					s5 = prog.getTotStud5();

				}

				System.err.println("f1 is " + f1);
				System.err.println("s1 is " + s1);
				String r1 = null;
				String r2 = null;
				String r3 = null;
				String r4 = null;
				String r5 = null;
				DecimalFormat decimalFormat = new DecimalFormat("0.00");

				if (s1 != 0) {
					r1 = decimalFormat.format((f1 / s1) * 100);
				} else {
					r1 = "0";
				}
				if (s2 != 0) {
					r2 = decimalFormat.format((f2 / s2) * 100);
				} else {
					r2 = "0";
				}
				if (s3 != 0) {
					r3 = decimalFormat.format((f3 / s3) * 100);
				} else {
					r3 = "0";
				}
				if (s4 != 0) {
					r4 = decimalFormat.format((f4 / s4) * 100);
				} else {
					r4 = "0";
				}
				if (s5 != 0) {
					r5 = decimalFormat.format((f5 / s5) * 100);
				} else {
					r5 = "0";
				}

				PdfPCell cell1;
				cell1 = new PdfPCell(new Phrase(String.valueOf(index + 1), headFontData));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("" + "%Year", headFontData));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_LEFT);

				table.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("" + r1, headFontData));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("" + r2, headFontData));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("" + r3, headFontData));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("" + r4, headFontData));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(cell1);

				cell1 = new PdfPCell(new Phrase("" + r5, headFontData));
				cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell1.setHorizontalAlignment(Element.ALIGN_CENTER);

				table.addCell(cell1);

				double a = 0;
				double c = 0;
				for (int i = 0; i < progList.size(); i++) {
					a = a + progList.get(i).getAcYearAdmiStud1() + progList.get(i).getAcYearAdmiStud2()
							+ progList.get(i).getAcYearAdmiStud3() + progList.get(i).getAcYearAdmiStud4()
							+ progList.get(i).getAcYearAdmiStud5();
					c = c + progList.get(i).getTotStud1() + progList.get(i).getTotStud2()
							+ progList.get(i).getTotStud3() + progList.get(i).getTotStud4()
							+ progList.get(i).getTotStud5();
				}

				double n = Double.parseDouble(r1) + Double.parseDouble(r2) + Double.parseDouble(r4)
						+ Double.parseDouble(r5) + Double.parseDouble(r3);

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
				document.add(new Paragraph("\n"));
				document.add(table);
				document.add(new Paragraph("Total No. of Students from other state and countries :" + a + ""));
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Total No. of Students Enrolled :" + c + ""));

				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Average % :" + decimalFormat.format(n / 5) + ""));

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
				} else {

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Location");
					rowData.add("ACY");
					rowData.add("ACY-1");
					rowData.add("ACY-2");
					rowData.add("ACY-3");
					rowData.add("ACY-4");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + progList.get(i).getLocationName());
						rowData.add("" + progList.get(i).getAcYearAdmiStud1());
						rowData.add("" + progList.get(i).getAcYearAdmiStud2());
						rowData.add("" + progList.get(i).getAcYearAdmiStud3());
						rowData.add("" + progList.get(i).getAcYearAdmiStud4());
						rowData.add("" + progList.get(i).getAcYearAdmiStud5());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year :" + temp_ac_year + " ");
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	@RequestMapping(value = "/showTeachersUsingIctReport", method = RequestMethod.POST)
	public void showTeachersUsingIctReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching -Learing and Evaluation : Teachers Using ICT";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("acYearList", ac_year);

			GetTeachersUsingICT[] resArray = rest.postForObject(Constants.url + "getTeachersUsingICT", map,
					GetTeachersUsingICT[].class);
			List<GetTeachersUsingICT> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);

			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showAvgStudYearwiseReport");
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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
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

				hcell = new PdfPCell(new Phrase("No. of Teachers using ICT/LMS/No. of Resources", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total  No. of Full Time Teachers", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("% of Teachers using ICT/LMS/No. of Resources", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					GetTeachersUsingICT prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getTechersUsingIct(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getTotalFaculty(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					DecimalFormat decimalFormat = new DecimalFormat("0.00");
					String tempprcnt = decimalFormat.format(prog.getTechersUsingIct() / prog.getTotalFaculty() * 100);

					cell = new PdfPCell(new Phrase("" + tempprcnt, headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);
				// document.add(new Paragraph("Institute " +
				// progList.get(0).getInstituteName()));
				/*
				 * Paragraph company = new Paragraph("Customer Wise Report\n", f);
				 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
				 * document.add(new Paragraph(" "));
				 */

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
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
				} else {

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Academic Year");
					rowData.add("No. of Teachers using ICT/LMS/No. of Resources");
					rowData.add("Total  No. of Full Time Teachers");
					rowData.add("% of Teachers using ICT/LMS/No. of Resources");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + progList.get(i).getAcademicYear());
						rowData.add("" + progList.get(i).getTechersUsingIct());
						rowData.add("" + progList.get(i).getTotalFaculty());

						DecimalFormat decimalFormat = new DecimalFormat("0.00");
						String tempprcnt = decimalFormat
								.format(progList.get(i).getTechersUsingIct() / progList.get(i).getTotalFaculty() * 100);
						rowData.add("" + tempprcnt);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year :" + temp_ac_year + " ");
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	@RequestMapping(value = "/showNoOfMentorsAssignedStudentReport", method = RequestMethod.POST)
	public void showNoOfMentorsAssignedStudentReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching -Learing and Evaluation : Total no. of Mentors\n" + "No. of Students Assigned";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("acYearList", ac_year);

			NoOfMentorsAssignedStudent[] resArray = rest.postForObject(Constants.url + "getNoOfMentorsAssignedStudent",
					map, NoOfMentorsAssignedStudent[].class);
			List<NoOfMentorsAssignedStudent> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);

			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showAvgStudYearwiseReport");
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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(4);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of  Faculty Mentor", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Student Mentoring", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Mentor Mentee Ratio", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					NoOfMentorsAssignedStudent prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getTotalFacMentor(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getTotalStudMentoring(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					DecimalFormat decimalFormat = new DecimalFormat("0.00");
					String tempprcnt = decimalFormat
							.format(prog.getTotalFacMentor() / prog.getTotalStudMentoring() * 100);

					cell = new PdfPCell(new Phrase("" + tempprcnt, headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);
				// document.add(new Paragraph("Institute " +
				// progList.get(0).getInstituteName()));
				/*
				 * Paragraph company = new Paragraph("Customer Wise Report\n", f);
				 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
				 * document.add(new Paragraph(" "));
				 */

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
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
				} else {

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("No. of  Faculty Mentor");
					rowData.add("No. of Student Mentoring");
					rowData.add("Mentor Mentee Ratio");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + progList.get(i).getTotalFacMentor());
						rowData.add("" + progList.get(i).getTotalStudMentoring());

						DecimalFormat decimalFormat = new DecimalFormat("0.00");
						String tempprcnt = decimalFormat.format(
								progList.get(i).getTotalStudMentoring() / progList.get(i).getTotalFacMentor() * 100);
						rowData.add("" + tempprcnt);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year :" + temp_ac_year + " ");
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	@RequestMapping(value = "/showStudPerformanceOutconmeReport", method = RequestMethod.POST)
	public void showStudPerformanceOutconmeReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching -Learing and Evaluation : Students Performance & Learning Outcomes";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			// String temp_ac_year = request.getParameter("temp_ac_year");
			int programId = Integer.parseInt(request.getParameter("programId"));
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("programId", programId);

			StudentPerformanceOutcome[] resArray = rest.postForObject(Constants.url + "getStudPerformancePo", map,
					StudentPerformanceOutcome[].class);
			List<StudentPerformanceOutcome> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);

			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showAvgStudYearwiseReport");
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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(3);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name of Course", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Course Outcome", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					StudentPerformanceOutcome prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getSubName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getCoName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("Program Name :" + progList.get(0).getProgramName() + ""));
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
				} else {

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Name of Course");
					rowData.add("Course Outcome");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + progList.get(i).getSubName());
						rowData.add("" + progList.get(i).getCoName());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Program Name :" + progList.get(0).getProgramName() + " ");
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	@RequestMapping(value = "/showAdmissionsAgainstCatReport", method = RequestMethod.POST)
	public void showAdmissionsAgainstCatReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching -Learing and Evaluation : Admissions feeds against reservation category";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			int catId = Integer.parseInt(request.getParameter("catId"));
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);
			map.add("catId", catId);
			map.add("acYearList", ac_year);

			AdmissionsAgainstCategory[] resArray = rest.postForObject(Constants.url + "getAdmisssionsAgainstCat", map,
					AdmissionsAgainstCategory[].class);
			List<AdmissionsAgainstCategory> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);

			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showAvgStudYearwiseReport");
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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
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

				hcell = new PdfPCell(new Phrase("No. of Student Admitted in Reservation Category", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total No. of  Student Admitted", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("% Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					AdmissionsAgainstCategory prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getCatTotStudent(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getSeatsAvaailable(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					DecimalFormat decimalFormat = new DecimalFormat("0.00");
					String tempprcnt = decimalFormat.format(prog.getCatTotStudent() / prog.getSeatsAvaailable() * 100);

					cell = new PdfPCell(new Phrase("" + tempprcnt, headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
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
				} else {

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Academic Year");
					rowData.add("No. of Student Admitted in Reservation Category");
					rowData.add("Total No. of  Student Admitted");
					rowData.add("% Year");
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + progList.get(i).getAcademicYear());
						rowData.add("" + progList.get(i).getCatTotStudent());
						rowData.add("" + progList.get(i).getSeatsAvaailable());

						DecimalFormat decimalFormat = new DecimalFormat("0.00");
						String tempprcnt = decimalFormat.format(
								progList.get(i).getCatTotStudent() / progList.get(i).getSeatsAvaailable() * 100);
						rowData.add("" + tempprcnt);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year :" + temp_ac_year + " ");
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	@RequestMapping(value = "/showLibLMSInfoReport", method = RequestMethod.POST)
	public void showLibLMSInfoReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources : Library Automation and ILMS Information";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			LibAutoLMSInfo[] resArray = rest.postForObject(Constants.url + "getLibLMSInfo", map,
					LibAutoLMSInfo[].class);
			List<LibAutoLMSInfo> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);

			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showAvgStudYearwiseReport");
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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name of Integrated LMS", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Year of Automation", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Version of ILMS", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Existing Users", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					LibAutoLMSInfo prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getSoftName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getSoftVersion(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getUsersOfLms(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				// document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
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
				} else {

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Name of Integrated LMS");
					rowData.add("Year of Automation");
					rowData.add("Version of ILMS");
					rowData.add("No. of Existing Users");
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + progList.get(i).getSoftName());
						rowData.add("" + progList.get(i).getYear());
						rowData.add("" + progList.get(i).getSoftVersion());

						rowData.add("" + progList.get(i).getUsersOfLms());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName, " ");
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	@RequestMapping(value = "/showRareBookManuscriptReport", method = RequestMethod.POST)
	public void showRareBookManuscriptReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources :Rare Book – manuscripts – special report";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			RareBookManuscriptSpec[] resArray = rest.postForObject(Constants.url + "getRareBookManuscript", map,
					RareBookManuscriptSpec[].class);
			List<RareBookManuscriptSpec> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);

			BufferedOutputStream outStream = null;
			System.out.println("Inside Pdf showAvgStudYearwiseReport");
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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(6);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name of Book/Manuscript", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name of Publisher", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Author of Book", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Copies", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Year of Publication", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					RareBookManuscriptSpec prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getRareBookname(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getPublisher(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAuthor(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getBookCopies(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getPublicatioYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				// document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
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
				} else {

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Name of Book/Manuscript");
					rowData.add("Year of Automation");
					rowData.add("Version of ILMS");
					rowData.add("No. of Existing Users");
					rowData.add("Publication Year");
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < progList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + progList.get(i).getRareBookname());
						rowData.add("" + progList.get(i).getPublisher());
						rowData.add("" + progList.get(i).getAuthor());
						rowData.add("" + progList.get(i).getBookCopies());
						rowData.add("" + progList.get(i).getPublicatioYear());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName, " ");
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	@RequestMapping(value = "/showLibSpecFacilitiesReport", method = RequestMethod.POST)
	public void showLibSpecFacilitiesReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources: Availability of Special Facilities in Library";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("acYearList", ac_year);

			LibSpecFacilities[] resArray = rest.postForObject(Constants.url + "getLibSpecFacilities", map,
					LibSpecFacilities[].class);
			List<LibSpecFacilities> progList = new ArrayList<>(Arrays.asList(resArray));

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

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(4);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
				tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.LIGHT_GRAY);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Title of Facility", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Status(Y/N)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Membership/Subscription Details", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				String a = null;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					LibSpecFacilities prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getYesnoTitle(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					if (prog.getYesnoValue() == 1) {
						a = "Yes";

					} else {
						a = "No";
					}
					cell = new PdfPCell(new Phrase("" + a, headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getInstYesnoResponse(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);
				// document.add(new Paragraph("Institute " +
				// progList.get(0).getInstituteName()));
				/*
				 * Paragraph company = new Paragraph("Customer Wise Report\n", f);
				 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
				 * document.add(new Paragraph(" "));
				 */

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
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
				} else {
					String b = null;
					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Title of Facility");
					rowData.add("Status(Y/N)");
					rowData.add("Membership/Subscription Details");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					double temp = 0.0;
					for (int i = 0; i < progList.size(); i++) {

						if (progList.get(i).getYesnoValue() == 1) {
							b = "Yes";

						} else {
							b = "No";
						}
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;
						DecimalFormat decimalFormat = new DecimalFormat("0.00");

						rowData.add("" + (i + 1));

						rowData.add("" + progList.get(i).getYesnoTitle());
						rowData.add("" + a);
						rowData.add("" + progList.get(i).getInstYesnoResponse());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year :" + temp_ac_year + " ");
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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

	@RequestMapping(value = "/showTeacherStudUsingLibReport", method = RequestMethod.POST)
	public void showTeacherStudUsingLibReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources: No of Students and Teachers using Library Per Day";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("acYearList", ac_year);

			TeacherStudUsingLib[] resArray = rest.postForObject(Constants.url + "getTeachersStudUsingLib", map,
					TeacherStudUsingLib[].class);
			List<TeacherStudUsingLib> progList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", progList);
			System.out.println("size" + progList.size());

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
			DecimalFormat decimalFormat = new DecimalFormat("0.00");
			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			String repDate = DF2.format(new Date());

			String headingName = null;
			try {
				headingName = progList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f });
				Font headFontData = Constants.headFontData;// new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL,
															// BaseColor.BLACK);
				Font tableHeaderFont = Constants.tableHeaderFont; // new Font(FontFamily.HELVETICA, 12, Font.BOLD,
																	// BaseColor.BLACK);
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

				hcell = new PdfPCell(
						new Phrase(" Avg No. of Students Using Library Resources Per Day", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(
						new Phrase(" Avg No. of Teachers Using Library Resources Per Day", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("% of Teachers and Students Using Library per day", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				double n1 = 0.00;
				int index = 0;
				String a = null;
				for (int i = 0; i < progList.size(); i++) {
					// System.err.println("I " + i);
					TeacherStudUsingLib prog = progList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAvgStudent(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prog.getAvgTeacher(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					n1 = ((prog.getAvgStudent() + prog.getAvgTeacher())
							/ (prog.getNoOfFullTimeFaculty() + prog.getNoOfCurrentAdmitedStnt())) * 100;

					cell = new PdfPCell(new Phrase("" + decimalFormat.format(n1), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

				}

				document.open();
				Font hf = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, hf);
				name.setAlignment(Element.ALIGN_LEFT);
				document.add(name);

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
				document.add(new Paragraph("\n"));
				document.add(table);
				document.add(new Paragraph("\n"));
				double n = 0.0;

				for (int i = 0; i < progList.size(); i++) {
					document.add(new Paragraph("For Academic Year :" + progList.get(i).getAcademicYear() + ""));
					document.add(new Paragraph(
							"Total No. of Faculty in the Institute:" + progList.get(i).getNoOfFullTimeFaculty() + ""));
					document.add(new Paragraph("Total No. of Students on roll in the Institute :"
							+ progList.get(i).getNoOfCurrentAdmitedStnt() + ""));

					n = ((progList.get(i).getAvgStudent() + progList.get(i).getAvgTeacher())
							/ (progList.get(i).getNoOfFullTimeFaculty() + progList.get(i).getNoOfCurrentAdmitedStnt()))
							* 100;
					document.add(new Paragraph("Library Usages Per Day:" + decimalFormat.format(n) + ""));
				}
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
				} else {
					String b = null;
					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Academic Year");
					rowData.add("Avg No. of Students Using Library Resources Per Day");
					rowData.add("Avg No. of Teachers Using Library Resources Per Day");
					rowData.add("% of Teachers and Students Using Library per day");
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					double temp = 0.0;
					for (int i = 0; i < progList.size(); i++) {

						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;
						n = ((progList.get(i).getAvgStudent() + progList.get(i).getAvgTeacher())
								/ (progList.get(i).getNoOfFullTimeFaculty()
										+ progList.get(i).getNoOfCurrentAdmitedStnt()))
								* 100;
						rowData.add("" + (i + 1));
						rowData.add("" + progList.get(i).getAcademicYear());
						rowData.add("" + progList.get(i).getAvgStudent());
						rowData.add("" + progList.get(i).getAvgTeacher());
						rowData.add("" + decimalFormat.format(n));

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year :" + temp_ac_year + " ");
						ExceUtil.autoSizeColumns(wb, 3);
						response.setContentType("application/vnd.ms-excel");
						String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
						response.setHeader("Content-disposition",
								"attachment; filename=" + reportName + "-" + date + ".xlsx");
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
