package com.ats.rusasoft.controller;

import java.io.BufferedInputStream;
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
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.ats.rusasoft.model.reports.FacAgnstSanctnPost;
import com.ats.rusasoft.model.reports.FacAgnstSanctnPostOthrState;
import com.ats.rusasoft.model.reports.StudTeachrRatio;
import com.ats.rusasoft.util.ItextPageEvent;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Controller
public class RusaReportsController {

	RestTemplate rest = new RestTemplate();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());
	String redirect = null;

	MultiValueMap<String, Object> map = null;
	
	@RequestMapping(value = "/showStudTeachrRatio", method = RequestMethod.POST)
	public void showStudTeachrRatio(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching Learning and Evaluation : Student Teacher Ratio";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYear", ac_year);
			map.add("instId", instituteId);

			StudTeachrRatio[] resArray = rest.postForObject(Constants.url + "/getStudTeachrRatioList", map,
					StudTeachrRatio[].class);
			List<StudTeachrRatio> ratioList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", ratioList);

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
				headingName = ratioList.get(0).getInstituteName();
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

				hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total No. of Full Time Faculty", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total No. of Student Enrolled", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);
				  
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("STR of Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				
				 
				int index = 0;
				int studTchrRato = 0;
				
					for (int i = 0; i < ratioList.size(); i++) {
						// System.err.println("I " + i);
						StudTeachrRatio ratio = ratioList.get(i);
						studTchrRato = ratio.getNoCurrentAdmitedStnt()/ratio.getNoOfFulltimeFaculty();
						
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + ratio.getAcademicYear(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						// cell.setPaddingLeft(10);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + ratio.getNoOfFulltimeFaculty(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + ratio.getNoCurrentAdmitedStnt(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + ratio.getInstituteName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + studTchrRato, headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						
					}
				

				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : 2019-20"));
				// document.add(new Paragraph("Institute " +
				// ratioList.get(0).getInstituteName()));
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
					rowData.add("Academic Year");
					rowData.add("Total No. of Full Time Faculty");
					rowData.add("Total No. of Student Enrolled");
					rowData.add("Insttute Name");
					rowData.add("STR of Year");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str =  0;
					for (int i = 0; i < ratioList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						str = ratioList.get(i).getNoCurrentAdmitedStnt()/ratioList.get(i).getNoOfFulltimeFaculty();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + ratioList.get(i).getAcademicYear());
						rowData.add("" + ratioList.get(i).getNoOfFulltimeFaculty());
						rowData.add("" + ratioList.get(i).getNoCurrentAdmitedStnt());
						rowData.add("" + ratioList.get(i).getInstituteName());
						rowData.add("" + str);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = ratioList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName, "");
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

			System.err.println("Exce in showratioReport " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	
	@RequestMapping(value = "/showFacultyAgnstSanctionpost", method = RequestMethod.POST)
	public void showFacultyAgnstSanctionpost(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching Learning and Evaluation : Faculty Available Against Sanctioned Post";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			FacAgnstSanctnPost[] resArray = rest.postForObject(Constants.url + "/getFacAgnstSanctnPostList", map,
					FacAgnstSanctnPost[].class);
			List<FacAgnstSanctnPost> postList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", postList);

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
				headingName = postList.get(0).getInstituteName();
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

				hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Full Time Faculty", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total No. of Sanctioned Post", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);
				  
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Year Wise %", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				
				 
				int index = 0;
				int postPer = 0;
				
					for (int i = 0; i < postList.size(); i++) {
						// System.err.println("I " + i);
						FacAgnstSanctnPost post = postList.get(i);
					try {
						postPer =  post.getNoOfFulltimeFaculty()/post.getSanctionedPost()*100;
					}catch(Exception e) {
						System.err.println("Invalid Values---"+e.getMessage());
					}
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + post.getAcademicYear(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						// cell.setPaddingLeft(10);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + post.getNoOfFulltimeFaculty(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + post.getSanctionedPost(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + post.getInstituteName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + postPer, headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						
					}
				

				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : 2019-20"));
				// document.add(new Paragraph("Institute " +
				// ratioList.get(0).getInstituteName()));
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
					rowData.add("Academic Year");
					rowData.add("No. of Full Time Faculty");
					rowData.add("No. of Sanctioned Post");
					rowData.add("Institute Name");
					rowData.add("Year Wise %");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str =  0;
					for (int i = 0; i < postList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						str = postList.get(i).getNoOfFulltimeFaculty()/postList.get(i).getSanctionedPost()*100;
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + postList.get(i).getAcademicYear());
						rowData.add("" + postList.get(i).getNoOfFulltimeFaculty());
						rowData.add("" + postList.get(i).getSanctionedPost());
						rowData.add("" + postList.get(i).getInstituteName());
						rowData.add("" + str);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = postList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName, "");
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

			System.err.println("Exce in showratioReport " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showDifferentlyAbledStud", method = RequestMethod.POST)
	public void showDifferentlyAbledStud(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching Learning and Evaluation : Differently Abled Students (Divyanjan)";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYear", ac_year);
			map.add("instId", instituteId);

			DifferentlyAbldStudReport[] resArray = rest.postForObject(Constants.url + "/getDifferntlyAbldStudList", map,
					DifferentlyAbldStudReport[].class);
			List<DifferentlyAbldStudReport> studList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", studList);

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
				headingName = studList.get(0).getInstituteName();
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

				hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Differently Abled Student", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total No. of Student Enrolled", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);
				  
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Year Wise %", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				
				 
				int index = 0;
				int studPer = 0;
				
					for (int i = 0; i < studList.size(); i++) {
						// System.err.println("I " + i);
						DifferentlyAbldStudReport stud = studList.get(i);
					try {
						studPer =  stud.getNoOfPwdStud()*100/stud.getTotalStudEnrolled();
					}catch(Exception e) {
						System.err.println("Invalid Values---"+e.getMessage());
					}
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + stud.getAcademicYear(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						// cell.setPaddingLeft(10);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + stud.getNoOfPwdStud(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + stud.getTotalStudEnrolled(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + stud.getInstituteName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + studPer, headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						
					}
				

				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : 2019-20"));
				// document.add(new Paragraph("Institute " +
				// ratioList.get(0).getInstituteName()));
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
					rowData.add("Academic Year");
					rowData.add("No. of Differently Abled Student");
					rowData.add("Total No. of Student Enrolled");
					rowData.add("Institute Name");
					rowData.add("Year Wise %");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str =  0;
					for (int i = 0; i < studList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						str = studList.get(i).getNoOfPwdStud()*100/studList.get(i).getTotalStudEnrolled();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + studList.get(i).getAcademicYear());
						rowData.add("" + studList.get(i).getNoOfPwdStud());
						rowData.add("" + studList.get(i).getTotalStudEnrolled());
						rowData.add("" + studList.get(i).getInstituteName());
						rowData.add("" + str);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = studList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName, "");
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

			System.err.println("Exce in showratioReport " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showFacultyAgnstSanctionPostOthrState", method = RequestMethod.POST)
	public void showFacultyAgnstSanctionPostOthrState(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching Learning and Evaluation : Full Time Faculty From Other States Against Sanctioned Post";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYear", ac_year);
			map.add("instId", instituteId);

			FacAgnstSanctnPostOthrState[] resArray = rest.postForObject(Constants.url + "/getFacultyAgnstSanctionPostOthrState", map,
					FacAgnstSanctnPostOthrState[].class);
			List<FacAgnstSanctnPostOthrState> facList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", facList);

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
				headingName = facList.get(0).getInstituteName();
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

				hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Full Time Faculty from other States", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Sanctioned Post", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);
				  
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Year Wise %", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				
				 
				int index = 0;
				int facPer = 0;
				
					for (int i = 0; i < facList.size(); i++) {
						// System.err.println("I " + i);
						FacAgnstSanctnPostOthrState fac = facList.get(i);
					try {
						facPer = fac.getNoOfOtherStateFac()/fac.getSanctionedPost()*100;
					}catch(Exception e) {
						System.err.println("Invalid Values---"+e.getMessage());
					}
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + fac.getAcademicYear(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + fac.getNoOfOtherStateFac(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						// cell.setPaddingLeft(10);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + fac.getSanctionedPost(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + fac.getInstituteName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);					
						

						cell = new PdfPCell(new Phrase("" + facPer, headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						
					}
				

				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : 2019-20"));
				// document.add(new Paragraph("Institute " +
				// ratioList.get(0).getInstituteName()));
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
					rowData.add("Academic Year");
					rowData.add("No. of Full Time Faculty From Other States");
					rowData.add("No. of Sanctioned Post");
					rowData.add("Institute Name");
					rowData.add("Year Wise %");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str =  0;
					for (int i = 0; i < facList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						try {
						str = facList.get(i).getNoOfOtherStateFac()/facList.get(i).getSanctionedPost()*100;
						}catch(Exception e) {
							System.err.println("Invalid Values---"+e.getMessage());
						}
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + facList.get(i).getAcademicYear());
						rowData.add("" + facList.get(i).getNoOfOtherStateFac());
						rowData.add("" + facList.get(i).getSanctionedPost());
						rowData.add("" + facList.get(i).getInstituteName());
						rowData.add("" + str);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = facList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName, "");
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

			System.err.println("Exce in showratioReport " + e.getMessage());
			e.printStackTrace();

		}

	}
}
