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
import com.ats.rusasoft.model.reports.AdmsnAgnstResrvCat;
import com.ats.rusasoft.model.reports.BudgetInfraAugmntn;
import com.ats.rusasoft.model.reports.EContntDevFacReport;
import com.ats.rusasoft.model.reports.ExpenditureOnPrchaseBooksJournal;
import com.ats.rusasoft.model.reports.FacAgnstSanctnPost;
import com.ats.rusasoft.model.reports.FacAgnstSanctnPostOthrState;
import com.ats.rusasoft.model.reports.FulTimFacultyWithPhd;
import com.ats.rusasoft.model.reports.ICtEnbldFaclitiesReport;
import com.ats.rusasoft.model.reports.IntrnetConnInfo;
import com.ats.rusasoft.model.reports.StudCompRatioReport;
import com.ats.rusasoft.model.reports.StudPrfrmInFinlYr;
import com.ats.rusasoft.model.reports.StudTeachrRatio;
import com.ats.rusasoft.model.reports.TeacExpFullTimFac;
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

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f});

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

				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
				
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
						
					/*
					 * cell = new PdfPCell(new Phrase("" + ratio.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 * 
					 * table.addCell(cell);
					 */

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
				document.add(new Paragraph("Academic Year : "+ratioList.get(0).getAcademicYear()));
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
					//rowData.add("Insttute Name");
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
					//	rowData.add("" + ratioList.get(i).getInstituteName());
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

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f});

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

				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
				
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
						
					/*
					 * cell = new PdfPCell(new Phrase("" + post.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 * 
					 * table.addCell(cell);
					 */

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
				document.add(new Paragraph("Academic Year : "+postList.get(0).getAcademicYear()));
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
					//rowData.add("Institute Name");
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
						//rowData.add("" + postList.get(i).getInstituteName());
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

				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
				
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
						
					/*
					 * cell = new PdfPCell(new Phrase("" + stud.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 * 
					 * table.addCell(cell);
					 */

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
				document.add(new Paragraph("Academic Year : "+studList.get(0).getAcademicYear()));
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
					//rowData.add("Institute Name");
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
						//rowData.add("" + studList.get(i).getInstituteName());
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

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f});

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

				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
				
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

					/*
					 * cell = new PdfPCell(new Phrase("" + fac.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 * 
					 * table.addCell(cell);
					 */				
						

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
				document.add(new Paragraph("Academic Year : "+facList.get(0).getAcademicYear()));
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
					//rowData.add("Institute Name");
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
						//rowData.add("" + facList.get(i).getInstituteName());
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
	
	@RequestMapping(value = "/showTeachingExpOfFillTimFac", method = RequestMethod.POST)
	public void showTeachingExpOfFillTimFac(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching Learning and Evaluation : Teaching Experience of Full Time Faculty(Current Data)";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYear", ac_year);
			map.add("instId", instituteId);

			TeacExpFullTimFac[] resArray = rest.postForObject(Constants.url + "/getTeachingExpOfFillTimFac", map,
					TeacExpFullTimFac[].class);
			List<TeacExpFullTimFac> facList = new ArrayList<>(Arrays.asList(resArray));

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
			String title = "";

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

				/*hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);*/

				hcell = new PdfPCell(new Phrase("Name of Full Time Faculty", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("PAN No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Designation", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);
				  
				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Name of Department", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				/*
				 * hcell = new PdfPCell(new Phrase("Institute", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
				
				hcell = new PdfPCell(new Phrase("Experience %(Yrs)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				
				 
				int index = 0;
				float expCount = 0; 
				
					for (int i = 0; i < facList.size(); i++) {
						// System.err.println("I " + i);
						TeacExpFullTimFac fac = facList.get(i);
						
						expCount = Float.parseFloat(fac.getCurExp())+expCount;
								
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + fac.getFacultyFirstName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + fac.getfPan(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						// cell.setPaddingLeft(10);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + fac.getDesignationName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);	
						
						cell = new PdfPCell(new Phrase("" + fac.getDeptName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
					/*
					 * cell = new PdfPCell(new Phrase("" + fac.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 * 
					 * table.addCell(cell);
					 */
						

						cell = new PdfPCell(new Phrase("" + fac.getCurExp(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						
					}
				System.out.println("Faculty Count-----"+index);
				System.out.println("Exp Count----"+expCount);
				
				float teachingExp = expCount/index;
				System.out.println("Teaching Experience----"+teachingExp);
				
				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				//document.add(new Paragraph("Academic Year : 2019-20"));
				
		
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
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Teaching Experience : "+teachingExp));

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
					//rowData.add("Academic Year");
					rowData.add("Name of Full Time Faculty");
					rowData.add("PAN No.");
					rowData.add("Designation");
					rowData.add("Name of Department");
					//rowData.add("Institute Name");
					rowData.add("Experience %(Yrs)");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					float expCnt = 0; 
					for (int i = 0; i < facList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						expCnt = Float.parseFloat(facList.get(i).getCurExp())+expCount;
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + facList.get(i).getFacultyFirstName());
						rowData.add("" + facList.get(i).getfPan());
						rowData.add("" + facList.get(i).getDesignationName());
						rowData.add("" + facList.get(i).getDeptName());
						//rowData.add("" + facList.get(i).getInstituteName());
						rowData.add("" + facList.get(i).getCurExp());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}System.out.println("Count"+cnt);
						float teachExp = expCnt/cnt;
						System.out.println("Teaching Exo="+teachExp);
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
	

	@RequestMapping(value = "/showFulTimFacAvalblePhd", method = RequestMethod.POST)
	public void showFulTimFacAvalblePhd(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching Learning and Evaluation : Full Time Faculty Available With Ph.D.s";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
		//	String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			//map.add("acYear", ac_year);
			map.add("instId", instituteId);

			FulTimFacultyWithPhd[] resArray = rest.getForObject(Constants.url + "/getFulTimFacAvalblePhd",
					FulTimFacultyWithPhd[].class);
			List<FulTimFacultyWithPhd> facList = new ArrayList<>(Arrays.asList(resArray));

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

			PdfPTable table = new PdfPTable(3);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f,});

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

				/*
				 * hcell = new PdfPCell(new Phrase("Institute", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */

				hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. Ph.D Awarded Faculty", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				 
				int index = 0;
				int facPer = 0;
				
					for (int i = 0; i < facList.size(); i++) {
						// System.err.println("I " + i);
						FulTimFacultyWithPhd fac = facList.get(i);
					
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
					/*
					 * cell = new PdfPCell(new Phrase("" + fac.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 * 
					 * table.addCell(cell);
					 */

						cell = new PdfPCell(new Phrase("" + fac.getfPassingYear(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						// cell.setPaddingLeft(10);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + fac.getNoOfPhdFac(), headFontData));
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
				//document.add(new Paragraph("Academic Year : 2019-20"));
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
					//rowData.add("Institute Name");
					rowData.add("Academic Year");
					rowData.add("No. Ph.D Awarded Faculty");
				
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str =  0;
					for (int i = 0; i < facList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						//rowData.add("" + facList.get(i).getInstituteName());
						rowData.add("" + facList.get(i).getfPassingYear());
						rowData.add("" + facList.get(i).getNoOfPhdFac());
						
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
	
	@RequestMapping(value = "/showAdmisionAgnstResrvCat", method = RequestMethod.POST)
	public void showAdmisionAgnstResrvCat(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching Learning and Evaluation : Admissions Feeds Against Reservation Category";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			AdmsnAgnstResrvCat[] resArray = rest.postForObject(Constants.url + "/getAdmisionAgnstResrvCat", map,
					AdmsnAgnstResrvCat[].class);
			List<AdmsnAgnstResrvCat> facList = new ArrayList<>(Arrays.asList(resArray));

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
			String title = "";

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
				
				hcell = new PdfPCell(new Phrase("Cast", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Students Admitted in Reservation Category", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Seat Available", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */

				hcell = new PdfPCell(new Phrase("%Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);
				  
				table.addCell(hcell);
								
				 
				int index = 0;
				float admPer = 0;
				float ttlAdmPer = 0;
							
					for (int i = 0; i < facList.size(); i++) {
						// System.err.println("I " + i);
						AdmsnAgnstResrvCat fac = facList.get(i);
						try {
							admPer = fac.getCatTotStudent()*100/fac.getSeatsAvailable();
							ttlAdmPer = admPer+ttlAdmPer;
						}catch(Exception e) {
							System.err.println("Invalid No.---"+e.getMessage());
							admPer=0;
						}
													
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + fac.getAcademic_year(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + fac.getCastName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + fac.getCatTotStudent(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						// cell.setPaddingLeft(10);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + fac.getSeatsAvailable(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);	
											
					/*
					 * cell = new PdfPCell(new Phrase("" + fac.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 * 
					 * table.addCell(cell);
					 */
						

						cell = new PdfPCell(new Phrase("" + admPer, headFontData));
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
				document.add(new Paragraph("Academic Year : "+facList.get(0).getAcademic_year()));
				
		
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
				document.add(new Paragraph("\n"));
				System.out.println("Count-----"+ttlAdmPer);
				document.add(new Paragraph("Average %: "+ttlAdmPer/5));

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
					//rowData.add("Academic Year");
					rowData.add("Academic Year");
					rowData.add("Cast");
					rowData.add("No. of Students Admitted in Reservation Category");
					rowData.add("No. of Seat Available");
					//rowData.add("Institute Name");
					rowData.add("%Year");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					float admCatPer = 0;
					for (int i = 0; i < facList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						try {
						admCatPer = facList.get(i).getCatTotStudent()*100/facList.get(i).getSeatsAvailable();
						}catch(Exception e) {
							System.err.println("Invalid No.---"+e.getMessage());
							admCatPer=0;
						}
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + facList.get(i).getAcademic_year());
						rowData.add("" + facList.get(i).getCastName());
						rowData.add("" + facList.get(i).getCatTotStudent());
						rowData.add("" + facList.get(i).getSeatsAvailable());
						//rowData.add("" + facList.get(i).getInstituteName());
						rowData.add("" + admCatPer);

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
	

	@RequestMapping(value = "/showStudPerformInFinalYear", method = RequestMethod.POST)
	public void showStudPerformInFinalYear(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching Learning and Evaluation : Students Performance in Final Year";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			StudPrfrmInFinlYr[] resArray = rest.postForObject(Constants.url + "/getStudPerformInFinalYear", map,
					StudPrfrmInFinlYr[].class);
			List<StudPrfrmInFinlYr> studList = new ArrayList<>(Arrays.asList(resArray));

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
			String title = "";

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
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f});

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

				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
				
				hcell = new PdfPCell(new Phrase("Name of Programme", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Programme Code", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Final Year Students Appeared for University Exams", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Final Year Students Passed for University Exams", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
			
				hcell = new PdfPCell(new Phrase("% of Final Year Students Passed", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);
				  
				table.addCell(hcell);
								
				 
				int index = 0;
											
					for (int i = 0; i < studList.size(); i++) {
						// System.err.println("I " + i);
						StudPrfrmInFinlYr stud = studList.get(i);
											
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					/*
					 * table.addCell(cell); cell = new PdfPCell(new Phrase("" +
					 * stud.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 */

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + stud.getProgramName(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + stud.getNameOfProgram(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + stud.getNoStudAppear(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);
						// cell.setPaddingLeft(10);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + stud.getNoStudPass(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);	
						

						cell = new PdfPCell(new Phrase("" + stud.getPassingPer(), headFontData));
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
				document.add(new Paragraph("Academic Year : "+studList.get(0).getAcadYear()));
				
		
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
					//rowData.add("Academic Year");
					//rowData.add("Institute Name");
					rowData.add("Name of Programme");
					rowData.add("Programme Code");
					rowData.add("No. of Final Year Students Appeared for University Exams");
					rowData.add("No. of Final Year Students Passed for University Exams");
					rowData.add("% of Final Year Students Passed");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					
					for (int i = 0; i < studList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						//rowData.add("" + studList.get(i).getInstituteName());
						rowData.add("" + studList.get(i).getProgramName());
						rowData.add("" + studList.get(i).getNameOfProgram());
						rowData.add("" + studList.get(i).getNoStudAppear());
						rowData.add("" + studList.get(i).getNoStudPass());
						rowData.add("" + studList.get(i).getPassingPer());

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
	
	@RequestMapping(value = "/showICTEnbldFaclties", method = RequestMethod.POST)
	public void showICTEnbldFaclties(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources : ICT Enabled Facilities";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			ICtEnbldFaclitiesReport[] resArray = rest.postForObject(Constants.url + "/getICTEnbldFaclties", map,
					ICtEnbldFaclitiesReport[].class);
			List<ICtEnbldFaclitiesReport> ictFacList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", ictFacList);

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
			String title = "";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			String headingName = null;
			try {
				headingName = ictFacList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(4);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] {2.4f, 3.2f, 3.2f, 3.2f});

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

							
				hcell = new PdfPCell(new Phrase("No. of Classroom with LCD", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("No. of Classroom with WiFi/LAN", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Seminar HAll with ICT", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
								
				 
				int index = 0;
											
					for (int i = 0; i < ictFacList.size(); i++) {
						// System.err.println("I " + i);
						ICtEnbldFaclitiesReport ictFac = ictFacList.get(i);
											
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						cell = new PdfPCell(new Phrase("" + ictFac.getNoClassromLcd(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + ictFac.getNoClassroomWifi(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + ictFac.getIctSeminarHall(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

					
					/*
					 * cell = new PdfPCell(new Phrase("" + ictFac.getInstituteName(),
					 * headFontData)); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 * 
					 * table.addCell(cell);
					 */
						
					}
				
				
				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : "+ictFacList.get(0).getAcademicYear()));
				
		
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
					//rowData.add("Academic Year");
					
					rowData.add("No. of Classroom with LCD");
					rowData.add("No. of  Classroom with WiFi/LAN");
					rowData.add("No. of Seminar Hall");
					rowData.add("Institute Name");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					
					for (int i = 0; i < ictFacList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + ictFacList.get(i).getNoClassromLcd());
						rowData.add("" + ictFacList.get(i).getNoClassroomWifi());
						rowData.add("" + ictFacList.get(i).getIctSeminarHall());
						rowData.add("" + ictFacList.get(i).getInstituteName());
					
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = ictFacList.get(0).getInstituteName();
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
	
	@RequestMapping(value = "/showExpenditureOnPrchaseBooksJournal", method = RequestMethod.POST)
	public void showExpenditureOnPrchaseBooksJournal(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources : Budget on Infrastructure Augmentation";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			ExpenditureOnPrchaseBooksJournal[] resArray = rest.postForObject(Constants.url + "/getExpenditureOnPrchaseBooksJournal", map,
					ExpenditureOnPrchaseBooksJournal[].class);
			List<ExpenditureOnPrchaseBooksJournal> bookList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", bookList);

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
			String title = "";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			String headingName = null;
			try {
				headingName = bookList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(7);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] {2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f});

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

				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
							
				hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Expenditure on Books (Lakhs)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Expenditure on Journals (Lakhs)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Expenditure on e-Journals (Lakhs)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

							
				hcell = new PdfPCell(new Phrase("Total Expenditure(Lakhs)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
								
				 
				int index = 0;
				float avgAnulExpd = 0; 											
					for (int i = 0; i < bookList.size(); i++) {
						// System.err.println("I " + i);
						ExpenditureOnPrchaseBooksJournal budget = bookList.get(i);
										
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				
						table.addCell(cell);
						
					/*
					 * cell = new PdfPCell(new Phrase("" +budget.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 * 
					 * table.addCell(cell);
					 */

						
						cell = new PdfPCell(new Phrase("" + budget.getAcademicYear(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + budget.getCostOfBooks(),headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
												
						cell = new PdfPCell(new Phrase("" + budget.getCostOfJournal(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						

						cell = new PdfPCell(new Phrase("" + budget.getCostOfEjournal(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + budget.getTotalExpenditures(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						try {
						avgAnulExpd = budget.getTotalExpenditures()+avgAnulExpd;
						}catch(Exception e) {
							e.getMessage();
						}
					}
				
				
				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : "+bookList.get(0).getAcademicYear()));
				
		
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
				
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Average Annual Expenditure in Last 5-Years : "+avgAnulExpd/5));
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
					//rowData.add("Academic Year");
					
				//	rowData.add("Institute Name");
					rowData.add("Academic Year");
					rowData.add("Expenditure on Books (Lakhs)");
					rowData.add("Expenditure on Journals (Lakhs)");
					rowData.add("Expenditure on e-Journals (Lakhs)");
					rowData.add("Total Expenditure(Lakhs)");
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					float bgtPer = 0;
					float ttlBgtper = 0;
					float avgBgtPer = 0;
					for (int i = 0; i < bookList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));
					///	rowData.add("" + bookList.get(i).getInstituteName());
						rowData.add("" + bookList.get(i).getAcademicYear());
						rowData.add("" + bookList.get(i).getCostOfBooks());
						rowData.add("" + bookList.get(i).getCostOfJournal());
						rowData.add("" + bookList.get(i).getCostOfEjournal());
						rowData.add("" + bookList.get(i).getTotalExpenditures());
						rowData.add("" + bgtPer);
					
						try {
							ttlBgtper = bgtPer+ttlBgtper;
							avgBgtPer=ttlBgtper/cnt;
						}catch(Exception e) {
							System.err.println(e.getMessage());
						}
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					System.out.println("AVG Budget = "+avgBgtPer);
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = bookList.get(0).getInstituteName();
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
	
	@RequestMapping(value = "/showBudgetInfraAugmentn", method = RequestMethod.POST)
	public void showBudgetInfraAugmentn(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources : Budget on Infrastructure Augmentation";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			BudgetInfraAugmntn[] resArray = rest.postForObject(Constants.url + "/getBudgetInfraAugmentn", map,
					BudgetInfraAugmntn[].class);
			List<BudgetInfraAugmntn> budgetList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", budgetList);

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
			String title = "";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			String headingName = null;
			try {
				headingName = budgetList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] {2.4f, 3.2f, 3.2f, 3.2f, 3.2f});

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

							
				hcell = new PdfPCell(new Phrase("Financial Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Budget on Infrastructure Augmentation", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total Budget Excluding Salary", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
				
				hcell = new PdfPCell(new Phrase("% of Budget on Infrastructure Augmentation", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
								
				 
				int index = 0;
				float bgt = 0;
				float ttlBugtPer = 0;
				float avgPerOnBugt = 0;
						
											
					for (int i = 0; i < budgetList.size(); i++) {
						// System.err.println("I " + i);
						BudgetInfraAugmntn budget = budgetList.get(i);
						try {
							bgt = Float.parseFloat(budget.getBudgetUtilized())*100/budget.getExInt1();
							
						}catch(Exception e) {
							System.err.println(e.getMessage());
						}
											
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						cell = new PdfPCell(new Phrase("" + budget.getFinYear(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + budget.getBudgetUtilized(),headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
												
						cell = new PdfPCell(new Phrase("" + budget.getExInt1(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

					
					/*
					 * cell = new PdfPCell(new Phrase("" +budget.getInstituteName(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 * 
					 * table.addCell(cell);
					 */

						cell = new PdfPCell(new Phrase("" +bgt, headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						try {
							ttlBugtPer = bgt+ttlBugtPer;
							avgPerOnBugt=ttlBugtPer/index;
						}catch(Exception e) {
							System.err.println(e.getMessage());
						}
					}
				
				
				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : "+budgetList.get(0).getAcademicYear()));
				
		
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
				
				document.add(new Paragraph("\n"));
				System.out.println("Ttl Bugt %-----"+ttlBugtPer);
				System.out.println("Avg Bugt %-----"+avgPerOnBugt);
				document.add(new Paragraph("Average % of Budget on Infrastructure Augmentation : "+avgPerOnBugt));
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
					//rowData.add("Academic Year");
					
					rowData.add("Financial Year");
					rowData.add("Budget on Infrastructure Augmentation");
					rowData.add("Total Budget Excluding Salary");
					//rowData.add("Institute Name");
					rowData.add("% of Budget on Infrastructure Augmentation");
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					float bgtPer = 0;
					float ttlBgtper = 0;
					float avgBgtPer = 0;
					for (int i = 0; i < budgetList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						try {
							bgtPer = Float.parseFloat(budgetList.get(i).getBudgetUtilized())*100/budgetList.get(i).getExInt1();
						}catch (Exception e) {
							System.err.println(e.getMessage());
						}
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + budgetList.get(i).getFinYear());
						rowData.add("" + budgetList.get(i).getBudgetUtilized());
						rowData.add("" + budgetList.get(i).getExInt1());
						//rowData.add("" + budgetList.get(i).getInstituteName());
						rowData.add("" + bgtPer);
					
						try {
							ttlBgtper = bgtPer+ttlBgtper;
							avgBgtPer=ttlBgtper/cnt;
						}catch(Exception e) {
							System.err.println(e.getMessage());
						}
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					System.out.println("AVG Budget = "+avgBgtPer);
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = budgetList.get(0).getInstituteName();
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
	
	@RequestMapping(value = "/showStudentCompterRatio", method = RequestMethod.POST)
	public void showStudentCompterRatio(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources : Student-Computer Ratio";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			//String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			//map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			StudCompRatioReport[] resArray = rest.postForObject(Constants.url + "/getStudentCompterRatio", map,
					StudCompRatioReport[].class);
			List<StudCompRatioReport> studCompList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", studCompList);

			Document document = new Document(PageSize.A4);
			
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
			String title = "";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			String headingName = null;
			try {
				headingName = studCompList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(6);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] {2.4f, 3.2f, 3.2f, 3.2f, 3.2f,  3.2f});

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

							
				hcell = new PdfPCell(new Phrase("No. of Computers", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Purchase Date", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Purchase Amount", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				

				hcell = new PdfPCell(new Phrase("No. of Student Utilising", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
				
				hcell = new PdfPCell(new Phrase("% of Budget on Infrastructure Augmentation", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
								
				 
				int index = 0;
				float studcompratio = 0;
						
											
					for (int i = 0; i < studCompList.size(); i++) {
						// System.err.println("I " + i);
						StudCompRatioReport stdCmpRatioList = studCompList.get(i);
						try {
							studcompratio = stdCmpRatioList.getNoOfComputers()/stdCmpRatioList.getNoOfStudUtilizing();
							
						}catch(Exception e) {
							System.err.println(e.getMessage());
						}
											
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						cell = new PdfPCell(new Phrase("" + stdCmpRatioList.getNoOfComputers(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + stdCmpRatioList.getPurchaseDate(),headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
												
						cell = new PdfPCell(new Phrase("" + stdCmpRatioList.getPurchaseAmt(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + stdCmpRatioList.getNoOfStudUtilizing(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
					/*
					 * cell = new PdfPCell(new Phrase("" + stdCmpRatioList.getInstituteName(),
					 * headFontData)); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 * 
					 * table.addCell(cell);
					 */

						cell = new PdfPCell(new Phrase("" +studcompratio, headFontData));
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
			//	document.add(new Paragraph("Academic Year : 2019-20"));
				
		
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
					//rowData.add("Academic Year");
					
					rowData.add("No. of Computers");
					rowData.add("Purchase Date");
					rowData.add("Purchase Amount");
					rowData.add("No. of Student Utilising");
					rowData.add("Institute Name");
					rowData.add("");
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					
					float ratio = 0;
					for (int i = 0; i < studCompList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						try {
							ratio = studCompList.get(i).getNoOfComputers()/studCompList.get(i).getNoOfStudUtilizing();
						}catch (Exception e) {
							System.err.println(e.getMessage());
						}
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + studCompList.get(i).getNoOfComputers());
						rowData.add("" + studCompList.get(i).getPurchaseDate());
						rowData.add("" + studCompList.get(i).getPurchaseAmt());
						rowData.add("" + studCompList.get(i).getNoOfStudUtilizing());
						rowData.add("" + ratio);
					
						
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
				
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = studCompList.get(0).getInstituteName();
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
	
	@RequestMapping(value = "/showEContntDevFac", method = RequestMethod.POST)
	public void showEContntDevFac(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources : E-Content Development Facilities";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			//String ac_year = request.getParameter("ac_year");
			String eContFacility = request.getParameter("e_contentType");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("eContFacility", eContFacility);
			map.add("instId", instituteId);

			EContntDevFacReport[] resArray = rest.postForObject(Constants.url + "/getEContntDevFac", map,
					EContntDevFacReport[].class);
			List<EContntDevFacReport> eContList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", eContList);

			Document document = new Document(PageSize.A4);
			
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
			String title = "";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			String headingName = null;
			try {
				headingName = eContList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(4);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] {2.4f, 3.2f, 3.2f, 3.2f});

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

							
				/*hcell = new PdfPCell(new Phrase("E-Content Development Facility", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);*/
				
				hcell = new PdfPCell(new Phrase("Name of E-Content Development Facility", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Video Link", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				
				hcell = new PdfPCell(new Phrase("Year Of Establishment", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				/*
				 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */
												
				 
				int index = 0;
				float studcompratio = 0;
						
											
					for (int i = 0; i < eContList.size(); i++) {
						// System.err.println("I " + i);
						EContntDevFacReport econt = eContList.get(i);
																	
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						/*table.addCell(cell);
						cell = new PdfPCell(new Phrase("" + econt.geteContentDevFacility(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);*/

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + econt.getNameEcontentDevFacility(),headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
												
						cell = new PdfPCell(new Phrase("" + econt.getVideoLink(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);

						cell = new PdfPCell(new Phrase("" + econt.getExVar1(), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
					/*
					 * cell = new PdfPCell(new Phrase("" + stdCmpRatioList.getInstituteName(),
					 * headFontData)); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 * 
					 * table.addCell(cell);
					 */
					}
				
				
				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				//document.add(new Paragraph("Academic Year : 2019-20"));
				document.add(new Paragraph("\n"));
				document.add(new Paragraph(eContList.get(0).geteContentDevFacility()));
				
		
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
					//rowData.add("Academic Year");
					
				//	rowData.add("E-Content Development");
					rowData.add("Name of E-Content Development");
					rowData.add("Video Link");
					rowData.add("Year of Establishment");
					rowData.add("");
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					
					float ratio = 0;
					for (int i = 0; i < eContList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
											
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						//rowData.add("" + eContList.get(i).geteContentDevFacility());
						rowData.add("" + eContList.get(i).getNameEcontentDevFacility());
						rowData.add("" + eContList.get(i).getVideoLink());
						rowData.add("" + eContList.get(i).getExVar1());
						
					
						
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
				
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = eContList.get(0).getInstituteName();
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
	
	@RequestMapping(value = "/showInternetConnInfo", method = RequestMethod.POST)
	public void showInternetConnInfo(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources : Internet Connection Information";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			
			//String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("instId", instituteId);

			IntrnetConnInfo[] resArray = rest.postForObject(Constants.url + "/getInternetConnInfo", map,
					IntrnetConnInfo[].class);
			List<IntrnetConnInfo> intrntInfoList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", intrntInfoList);

			Document document = new Document(PageSize.A4);
			
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
			String title = "";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			String headingName = null;
			try {
				headingName = intrntInfoList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(3);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] {2.4f, 3.2f, 3.2f});

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

											
				hcell = new PdfPCell(new Phrase("Total Bandwidth(Leased Line)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Bandwidth for Library Abd E-Resources", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
							 
				int index = 0;
															
					for (int i = 0; i < intrntInfoList.size(); i++) {
						// System.err.println("I " + i);
						IntrnetConnInfo intrnt = intrntInfoList.get(i);
																	
						index++;
						PdfPCell cell;
						cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_CENTER);

						

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + intrnt.getLeaseLineBandwidth(),headFontData));
						cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						cell.setHorizontalAlignment(Element.ALIGN_LEFT);

						table.addCell(cell);
						
						cell = new PdfPCell(new Phrase("" + intrnt.getLibBandwidth(), headFontData));
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
			//	document.add(new Paragraph("Academic Year : 2019-20"));
					
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
					//rowData.add("Academic Year");
					
				//	rowData.add("E-Content Development");
					rowData.add("Total Bandwidth(Leased Line)");
					rowData.add("Bandwidth for Library Abd E-Resources");
					rowData.add("");
					
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					
					float ratio = 0;
					for (int i = 0; i < intrntInfoList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
											
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						//rowData.add("" + eContList.get(i).geteContentDevFacility());
						rowData.add("" + intrntInfoList.get(i).getLeaseLineBandwidth());
						rowData.add("" + intrntInfoList.get(i).getLibBandwidth());
						
					
						
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
				
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = intrntInfoList.get(0).getInstituteName();
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
