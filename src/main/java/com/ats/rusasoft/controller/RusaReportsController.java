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
import com.ats.rusasoft.master.model.Program;
import com.ats.rusasoft.model.reports.AdmsnAgnstResrvCat;
import com.ats.rusasoft.model.reports.AvgPerPlacement;
import com.ats.rusasoft.model.reports.AwrdRecgAgnstExtActivityReport;
import com.ats.rusasoft.model.reports.BudgetInfraAugmntn;
import com.ats.rusasoft.model.reports.EContntDevFacReport;
import com.ats.rusasoft.model.reports.ExpenditureOnPrchaseBooksJournal;
import com.ats.rusasoft.model.reports.ExpndGreenInitveWsteMgmt;
import com.ats.rusasoft.model.reports.ExpndturOnPhysclAcademicSupprt;
import com.ats.rusasoft.model.reports.FacAgnstSanctnPost;
import com.ats.rusasoft.model.reports.FacAgnstSanctnPostOthrState;
import com.ats.rusasoft.model.reports.FacParticipationInBodies;
import com.ats.rusasoft.model.reports.FildeProjectInternReport;
import com.ats.rusasoft.model.reports.FulTimFacultyWithPhd;
import com.ats.rusasoft.model.reports.FullTimeTechrInstResrchGuide;
import com.ats.rusasoft.model.reports.FunctionalMou;
import com.ats.rusasoft.model.reports.ICtEnbldFaclitiesReport;
import com.ats.rusasoft.model.reports.InitivAddrsLoctnAdvDisadv;
import com.ats.rusasoft.model.reports.IntelectulPropRightReport;
import com.ats.rusasoft.model.reports.IntrnetConnInfo;
import com.ats.rusasoft.model.reports.NoAwardRecogExtAct;
import com.ats.rusasoft.model.reports.NoInitivAddrsLoctnAdvDisadv;
import com.ats.rusasoft.model.reports.NoOfLinkages;
import com.ats.rusasoft.model.reports.PerNewCource;
import com.ats.rusasoft.model.reports.PerProgCbseElectiveCourse;
import com.ats.rusasoft.model.reports.ResrchProjectGrants;
import com.ats.rusasoft.model.reports.StudCompRatioReport;
import com.ats.rusasoft.model.reports.StudPrfrmInFinlYr;
import com.ats.rusasoft.model.reports.StudProgression;
import com.ats.rusasoft.model.reports.StudTeachrRatio;
import com.ats.rusasoft.model.reports.TeacExpFullTimFac;
import com.ats.rusasoft.model.reports.TeacherAwardRecognitn;
import com.ats.rusasoft.model.reports.TechrResrchPaprJournlInfo;
import com.ats.rusasoft.model.reports.TechrResrchPaprJournlRatio;
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
			String temp_ac_year = request.getParameter("temp_ac_year");
			System.out.println("Filter------"+temp_ac_year);
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
					studTchrRato = ratio.getNoCurrentAdmitedStnt() / ratio.getNoOfFulltimeFaculty();

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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));
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
					// rowData.add("Insttute Name");
					rowData.add("STR of Year");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str = 0;
					String acYear = null;
					for (int i = 0; i < ratioList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						str = ratioList.get(i).getNoCurrentAdmitedStnt() / ratioList.get(i).getNoOfFulltimeFaculty();
						cnt = cnt + i;
						
						rowData.add("" + (i + 1));
						
						rowData.add("" + ratioList.get(i).getAcademicYear());
						rowData.add("" + ratioList.get(i).getNoOfFulltimeFaculty());
						rowData.add("" + ratioList.get(i).getNoCurrentAdmitedStnt());
						// rowData.add("" + ratioList.get(i).getInstituteName());
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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName, "Academic Year : "+temp_ac_year,"",'E');
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

			String temp_ac_year = request.getParameter("temp_ac_year");
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
						postPer = post.getNoOfFulltimeFaculty() / post.getSanctionedPost() * 100;
					} catch (Exception e) {
						System.err.println("Invalid Values---" + e.getMessage());
					}
					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + post.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					// cell.setPaddingLeft(10);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + post.getNoOfFulltimeFaculty(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + post.getSanctionedPost(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));
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
					// rowData.add("Institute Name");
					rowData.add("Year Wise %");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str = 0;
					String acYear = null;
					for (int i = 0; i < postList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						str = postList.get(i).getNoOfFulltimeFaculty() / postList.get(i).getSanctionedPost() * 100;
						cnt = cnt + i;
						
						rowData.add("" + (i + 1));

						rowData.add("" + postList.get(i).getAcademicYear());
						rowData.add("" + postList.get(i).getNoOfFulltimeFaculty());
						rowData.add("" + postList.get(i).getSanctionedPost());
						// rowData.add("" + postList.get(i).getInstituteName());
						rowData.add("" + str);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					acYear=postList.get(0).getAcademicYear();
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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year,"",'E');
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
			String temp_ac_year = request.getParameter("temp_ac_year");
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
						studPer = stud.getNoOfPwdStud() * 100 / stud.getTotalStudEnrolled();
					} catch (Exception e) {
						System.err.println("Invalid Values---" + e.getMessage());
					}
					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					// cell.setPaddingLeft(10);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getNoOfPwdStud(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getTotalStudEnrolled(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));
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
					// rowData.add("Institute Name");
					rowData.add("Year Wise %");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str = 0;
					for (int i = 0; i < studList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						str = studList.get(i).getNoOfPwdStud() * 100 / studList.get(i).getTotalStudEnrolled();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + studList.get(i).getAcademicYear());
						rowData.add("" + studList.get(i).getNoOfPwdStud());
						rowData.add("" + studList.get(i).getTotalStudEnrolled());
						// rowData.add("" + studList.get(i).getInstituteName());
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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "",'E' );
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
			String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("acYear", ac_year);
			map.add("instId", instituteId);

			FacAgnstSanctnPostOthrState[] resArray = rest.postForObject(
					Constants.url + "/getFacultyAgnstSanctionPostOthrState", map, FacAgnstSanctnPostOthrState[].class);
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
						facPer = fac.getNoOfOtherStateFac() / fac.getSanctionedPost() * 100;
					} catch (Exception e) {
						System.err.println("Invalid Values---" + e.getMessage());
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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));
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
					// rowData.add("Institute Name");
					rowData.add("Year Wise %");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str = 0;
					for (int i = 0; i < facList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						try {
							str = facList.get(i).getNoOfOtherStateFac() / facList.get(i).getSanctionedPost() * 100;
						} catch (Exception e) {
							System.err.println("Invalid Values---" + e.getMessage());
						}
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + facList.get(i).getAcademicYear());
						rowData.add("" + facList.get(i).getNoOfOtherStateFac());
						rowData.add("" + facList.get(i).getSanctionedPost());
						// rowData.add("" + facList.get(i).getInstituteName());
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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "", 'E');
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
			//String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			//map.add("acYear", ac_year);
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

				/*
				 * hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */

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

					expCount = Float.parseFloat(fac.getCurExp()) + expCount;

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + fac.getFacultyFirstName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + fac.getfPan(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					// cell.setPaddingLeft(10);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + fac.getDesignationName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + fac.getDeptName(), headFontData));
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

					cell = new PdfPCell(new Phrase("" + fac.getCurExp(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

				}
				System.out.println("Faculty Count-----" + index);
				System.out.println("Exp Count----" + expCount);

				float teachingExp = expCount / index;
				System.out.println("Teaching Experience----" + teachingExp);

				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				// document.add(new Paragraph("Academic Year : 2019-20"));

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
				document.add(new Paragraph("Teaching Experience : " + teachingExp));

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
					// rowData.add("Academic Year");
					rowData.add("Name of Full Time Faculty");
					rowData.add("PAN No.");
					rowData.add("Designation");
					rowData.add("Name of Department");
					// rowData.add("Institute Name");
					rowData.add("Experience %(Yrs)");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					float expCnt = 0;
					for (int i = 0; i < facList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						expCnt = Float.parseFloat(facList.get(i).getCurExp()) + expCount;
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + facList.get(i).getFacultyFirstName());
						rowData.add("" + facList.get(i).getfPan());
						rowData.add("" + facList.get(i).getDesignationName());
						rowData.add("" + facList.get(i).getDeptName());
						// rowData.add("" + facList.get(i).getInstituteName());
						rowData.add("" + facList.get(i).getCurExp());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					System.out.println("Count" + cnt);
					float teachExp = expCnt / cnt;
					System.out.println("Teaching Exo=" + teachExp);
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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"", "", 'F');
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

			// String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			// map.add("acYear", ac_year);
			map.add("instId", instituteId);

			FulTimFacultyWithPhd[] resArray = rest.postForObject(Constants.url + "/getFulTimFacAvalblePhd", map,
					FulTimFacultyWithPhd[].class);
			List<FulTimFacultyWithPhd> facList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", facList);

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
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, });

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
				// document.add(new Paragraph("Academic Year : 2019-20"));
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
					// rowData.add("Institute Name");
					rowData.add("Academic Year");
					rowData.add("No. Ph.D Awarded Faculty");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str = 0;
					for (int i = 0; i < facList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();

						cnt = cnt + i;

						rowData.add("" + (i + 1));

						// rowData.add("" + facList.get(i).getInstituteName());
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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"", "", 'C');
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

	/*
	 * @RequestMapping(value = "/showAdmisionAgnstResrvCat", method =
	 * RequestMethod.POST) public void showAdmisionAgnstResrvCat(HttpServletRequest
	 * request, HttpServletResponse response) {
	 * 
	 * String reportName =
	 * "Teaching Learning and Evaluation : Admissions Feeds Against Reservation Category"
	 * ;
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("report/ratio_report1");
	 * 
	 * HttpSession session = request.getSession();
	 * 
	 * String ac_year = request.getParameter("ac_year"); int instituteId = (int)
	 * session.getAttribute("instituteId");
	 * 
	 * map = new LinkedMultiValueMap<>(); map.add("acYearList", ac_year);
	 * map.add("instId", instituteId);
	 * 
	 * AdmsnAgnstResrvCat[] resArray = rest.postForObject(Constants.url +
	 * "/getAdmisionAgnstResrvCat", map, AdmsnAgnstResrvCat[].class);
	 * List<AdmsnAgnstResrvCat> facList = new ArrayList<>(Arrays.asList(resArray));
	 * 
	 * model.addObject("list", facList);
	 * 
	 * Document document = new Document(PageSize.A4); // 50, 45, 50, 60
	 * document.setMargins(Constants.marginLeft, Constants.marginRight,
	 * Constants.marginTop, Constants.marginBottom);
	 * document.setMarginMirroring(false);
	 * 
	 * String FILE_PATH = Constants.REPORT_SAVE; File file = new File(FILE_PATH);
	 * 
	 * PdfWriter writer = null;
	 * 
	 * FileOutputStream out = new FileOutputStream(FILE_PATH); try { writer =
	 * PdfWriter.getInstance(document, out); } catch (DocumentException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * String header = ""; String title = "";
	 * 
	 * DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy"); String headingName =
	 * null; try { headingName = facList.get(0).getInstituteName(); } catch
	 * (Exception e) {
	 * 
	 * headingName = "-";
	 * 
	 * } ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);
	 * 
	 * writer.setPageEvent(event);
	 * 
	 * PdfPTable table = new PdfPTable(6);
	 * 
	 * table.setHeaderRows(1);
	 * 
	 * try { table.setWidthPercentage(100); table.setWidths(new float[] { 2.4f,
	 * 3.2f, 3.2f, 3.2f, 3.2f, 3.2f });
	 * 
	 * Font headFontData = Constants.headFontData;// new
	 * Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, // BaseColor.BLACK); Font
	 * tableHeaderFont = Constants.tableHeaderFont; // new
	 * Font(FontFamily.HELVETICA, 12, Font.BOLD, // BaseColor.BLACK);
	 * tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);
	 * 
	 * PdfPCell hcell = new PdfPCell();
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Cast", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new
	 * Phrase("No. of Students Admitted in Reservation Category", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("No. of Seat Available", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * 
	 * hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * 
	 * hcell = new PdfPCell(new Phrase("%Year", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * 
	 * int index = 0; float admPer = 0; float ttlAdmPer = 0;
	 * 
	 * for (int i = 0; i < facList.size(); i++) { // System.err.println("I " + i);
	 * AdmsnAgnstResrvCat fac = facList.get(i); try { admPer =
	 * fac.getCatTotStudent()*100/fac.getSeatsAvailable(); ttlAdmPer =
	 * admPer+ttlAdmPer; }catch(Exception e) {
	 * System.err.println("Invalid No.---"+e.getMessage()); admPer=0; }
	 * 
	 * index++; PdfPCell cell; cell = new PdfPCell(new Phrase(String.valueOf(index),
	 * headFontData)); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * 
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase("" + fac.getAcademic_year(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	 * 
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase("" + fac.getCastName(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	 * 
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase("" + fac.getCatTotStudent(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_LEFT); // cell.setPaddingLeft(10);
	 * 
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase("" + fac.getSeatsAvailable(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * 
	 * table.addCell(cell);
	 * 
	 * 
	 * cell = new PdfPCell(new Phrase("" + fac.getInstituteName(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	 * 
	 * table.addCell(cell);
	 * 
	 * 
	 * 
	 * cell = new PdfPCell(new Phrase("" + admPer, headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	 * 
	 * table.addCell(cell);
	 * 
	 * 
	 * }
	 * 
	 * 
	 * document.open(); Font reportNameFont = Constants.reportNameFont;// new
	 * Font(FontFamily.TIMES_ROMAN, 14.0f, // Font.UNDERLINE, BaseColor.BLACK);
	 * 
	 * Paragraph name = new Paragraph(reportName, reportNameFont);
	 * name.setAlignment(Element.ALIGN_CENTER); document.add(name); document.add(new
	 * Paragraph("\n")); document.add(new
	 * Paragraph("Academic Year : "+facList.get(0).getAcademic_year()));
	 * 
	 * 
	 * // document.add(new Paragraph("Institute " + //
	 * ratioList.get(0).getInstituteName()));
	 * 
	 * Paragraph company = new Paragraph("Customer Wise Report\n", f);
	 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
	 * document.add(new Paragraph(" "));
	 * 
	 * 
	 * DateFormat DF = new SimpleDateFormat("dd-MM-yyyy"); document.add(new
	 * Paragraph("\n")); document.add(table); document.add(new Paragraph("\n"));
	 * System.out.println("Count-----"+ttlAdmPer); document.add(new
	 * Paragraph("Average %: "+ttlAdmPer/5));
	 * 
	 * int totalPages = writer.getPageNumber();
	 * 
	 * System.out.println("Page no " + totalPages);
	 * 
	 * document.close(); int p = Integer.parseInt(request.getParameter("p"));
	 * System.err.println("p " + p);
	 * 
	 * if (p == 1) {
	 * 
	 * if (file != null) {
	 * 
	 * String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	 * 
	 * if (mimeType == null) {
	 * 
	 * mimeType = "application/pdf";
	 * 
	 * }
	 * 
	 * response.setContentType(mimeType);
	 * 
	 * response.addHeader("content-disposition",
	 * String.format("inline; filename=\"%s\"", file.getName()));
	 * 
	 * response.setContentLength((int) file.length());
	 * 
	 * InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	 * 
	 * try { FileCopyUtils.copy(inputStream, response.getOutputStream()); } catch
	 * (IOException e) { System.out.println("Excep in Opening a Pdf File");
	 * e.printStackTrace(); } } } else {
	 * 
	 * List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
	 * 
	 * ExportToExcel expoExcel = new ExportToExcel(); List<String> rowData = new
	 * ArrayList<String>();
	 * 
	 * rowData.add("Sr. No"); //rowData.add("Academic Year");
	 * rowData.add("Academic Year"); rowData.add("Cast");
	 * rowData.add("No. of Students Admitted in Reservation Category");
	 * rowData.add("No. of Seat Available"); //rowData.add("Institute Name");
	 * rowData.add("%Year");
	 * 
	 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel);
	 * 
	 * int cnt = 1; float admCatPer = 0; for (int i = 0; i < facList.size(); i++) {
	 * expoExcel = new ExportToExcel(); rowData = new ArrayList<String>(); try {
	 * admCatPer =
	 * facList.get(i).getCatTotStudent()*100/facList.get(i).getSeatsAvailable();
	 * }catch(Exception e) { System.err.println("Invalid No.---"+e.getMessage());
	 * admCatPer=0; } cnt = cnt + i;
	 * 
	 * rowData.add("" + (i + 1)); rowData.add("" +
	 * facList.get(i).getAcademic_year()); rowData.add("" +
	 * facList.get(i).getCastName()); rowData.add("" +
	 * facList.get(i).getCatTotStudent()); rowData.add("" +
	 * facList.get(i).getSeatsAvailable()); //rowData.add("" +
	 * facList.get(i).getInstituteName()); rowData.add("" + admCatPer);
	 * 
	 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel);
	 * 
	 * } XSSFWorkbook wb = null; try {
	 * 
	 * System.out.println("Excel List :" + exportToExcelList.toString()); String rep
	 * = null; try { rep = facList.get(0).getInstituteName(); } catch (Exception e)
	 * {
	 * 
	 * rep = "-";
	 * 
	 * } System.err.println("headingName  " + headingName); // String excelName =
	 * (String) session.getAttribute("excelName"); wb =
	 * ExceUtil.createWorkbook(exportToExcelList, rep, reportName, "");
	 * ExceUtil.autoSizeColumns(wb, 3);
	 * response.setContentType("application/vnd.ms-excel"); String date = new
	 * SimpleDateFormat("yyyy-MM-dd").format(new Date());
	 * response.setHeader("Content-disposition", "attachment; filename=" +
	 * reportName + "-" + date + ".xlsx"); wb.write(response.getOutputStream());
	 * 
	 * } catch (IOException ioe) { throw new
	 * RuntimeException("Error writing spreadsheet to output stream"); } finally {
	 * if (wb != null) { wb.close(); } }
	 * 
	 * }
	 * 
	 * } catch (DocumentException ex) {
	 * 
	 * System.out.println("Pdf Generation Error: " + ex.getMessage());
	 * 
	 * ex.printStackTrace();
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("Exce in showratioReport " + e.getMessage());
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * }
	 */

	@RequestMapping(value = "/showStudPerformInFinalYear", method = RequestMethod.POST)
	public void showStudPerformInFinalYear(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Teaching Learning and Evaluation : Students Performance in Final Year";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			String temp_ac_year = request.getParameter("temp_ac_year");
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

				hcell = new PdfPCell(
						new Phrase("No. of Final Year Students Appeared for University Exams", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(
						new Phrase("No. of Final Year Students Passed for University Exams", tableHeaderFont));
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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));

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
					// rowData.add("Academic Year");
					// rowData.add("Institute Name");
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
						// rowData.add("" + studList.get(i).getInstituteName());
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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "", 'F');
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
			String temp_ac_year = request.getParameter("temp_ac_year");
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
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f });

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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));

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
					// rowData.add("Academic Year");
					rowData.add("No. of Classroom with LCD");
					rowData.add("No. of  Classroom with WiFi/LAN");
					rowData.add("No. of Seminar Hall");
					//rowData.add("Institute Name");

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
						//rowData.add("" + ictFacList.get(i).getInstituteName());

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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "", 'E');
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

		String reportName = "Infrastructure and Learning Resources : Expenditure on Purchase of	Books and Journals";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			ExpenditureOnPrchaseBooksJournal[] resArray = rest.postForObject(
					Constants.url + "/getExpenditureOnPrchaseBooksJournal", map,
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

					cell = new PdfPCell(new Phrase("" + budget.getCostOfBooks(), headFontData));
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
						avgAnulExpd = budget.getTotalExpenditures() + avgAnulExpd;
					} catch (Exception e) {
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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));

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
				document.add(new Paragraph("Average Annual Expenditure in Last 5-Years : " + avgAnulExpd / 5));
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
					// rowData.add("Academic Year");

					// rowData.add("Institute Name");
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
						/// rowData.add("" + bookList.get(i).getInstituteName());
						rowData.add("" + bookList.get(i).getAcademicYear());
						rowData.add("" + bookList.get(i).getCostOfBooks());
						rowData.add("" + bookList.get(i).getCostOfJournal());
						rowData.add("" + bookList.get(i).getCostOfEjournal());
						rowData.add("" + bookList.get(i).getTotalExpenditures());
						
						try {
							ttlBgtper = bookList.get(i).getTotalExpenditures() + ttlBgtper;
							avgBgtPer = ttlBgtper/5;
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					System.out.println("AVG Budget = " + avgBgtPer);
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
						
						String avg = String.valueOf(avgBgtPer);
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "Average Annual Expenditure in Last 5-Years : "+avg, 'F');
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
			String temp_ac_year = request.getParameter("temp_ac_year");
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
						bgt = Float.parseFloat(budget.getBudgetUtilized()) * 100 / budget.getExInt1();

					} catch (Exception e) {
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

					cell = new PdfPCell(new Phrase("" + budget.getBudgetUtilized(), headFontData));
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

					cell = new PdfPCell(new Phrase("" + bgt, headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					try {
						ttlBugtPer = bgt + ttlBugtPer;
						avgPerOnBugt = ttlBugtPer / index;
					} catch (Exception e) {
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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));

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
				System.out.println("Ttl Bugt %-----" + ttlBugtPer);
				System.out.println("Avg Bugt %-----" + avgPerOnBugt);
				document.add(new Paragraph("Average % of Budget on Infrastructure Augmentation : " + avgPerOnBugt));
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
					// rowData.add("Academic Year");

					rowData.add("Financial Year");
					rowData.add("Budget on Infrastructure Augmentation");
					rowData.add("Total Budget Excluding Salary");
					// rowData.add("Institute Name");
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
							bgtPer = Float.parseFloat(budgetList.get(i).getBudgetUtilized()) * 100
									/ budgetList.get(i).getExInt1();
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}

						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + budgetList.get(i).getFinYear());
						rowData.add("" + budgetList.get(i).getBudgetUtilized());
						rowData.add("" + budgetList.get(i).getExInt1());
						// rowData.add("" + budgetList.get(i).getInstituteName());
						rowData.add("" + bgtPer);

						try {
							ttlBgtper = bgtPer + ttlBgtper;
							avgBgtPer = ttlBgtper / cnt;
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					System.out.println("AVG Budget = " + avgBgtPer);
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = budgetList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						String avg = String.valueOf(avgBgtPer);
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "Average % of Budget on Infrastructure Augmentation : "+avg,'D');
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

			// String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			// map.add("acYearList", ac_year);
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
						studcompratio = stdCmpRatioList.getNoOfComputers() / stdCmpRatioList.getNoOfStudUtilizing();

					} catch (Exception e) {
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

					cell = new PdfPCell(new Phrase("" + stdCmpRatioList.getPurchaseDate(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

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

					cell = new PdfPCell(new Phrase("" + studcompratio, headFontData));
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
				// document.add(new Paragraph("Academic Year : 2019-20"));

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
					// rowData.add("Academic Year");

					rowData.add("No. of Computers");
					rowData.add("Purchase Date");
					rowData.add("Purchase Amount");
					rowData.add("No. of Student Utilising");					
					rowData.add("% of Budget on Infrastructure Augmentation");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;

					float ratio = 0;
					for (int i = 0; i < studCompList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						try {
							ratio = studCompList.get(i).getNoOfComputers() / studCompList.get(i).getNoOfStudUtilizing();
						} catch (Exception e) {
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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName, "", "", 'F');
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

			// String ac_year = request.getParameter("ac_year");
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
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f });

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
				 * hcell = new PdfPCell(new Phrase("E-Content Development Facility",
				 * tableHeaderFont)); hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
				 * 
				 * table.addCell(hcell);
				 */

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

					/*
					 * table.addCell(cell); cell = new PdfPCell(new Phrase("" +
					 * econt.geteContentDevFacility(), headFontData));
					 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					 */

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + econt.getNameEcontentDevFacility(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + econt.getVideoLink(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + econt.getExVar1(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

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
				// document.add(new Paragraph("Academic Year : 2019-20"));
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
					// rowData.add("Academic Year");

					// rowData.add("E-Content Development");
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
						// rowData.add("" + eContList.get(i).geteContentDevFacility());
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
						String eCont= eContList.get(0).geteContentDevFacility();
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,eCont,"", 'D');
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

			// String ac_year = request.getParameter("ac_year");
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
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f });

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

					cell = new PdfPCell(new Phrase("" + intrnt.getLeaseLineBandwidth(), headFontData));
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
				// document.add(new Paragraph("Academic Year : 2019-20"));

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
					// rowData.add("Academic Year");

					// rowData.add("E-Content Development");
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
						// rowData.add("" + eContList.get(i).geteContentDevFacility());
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
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"", "",'C');
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
	
	@RequestMapping(value = "/showExpndPhyAcdSupprtFacilities", method = RequestMethod.POST)
	public void showExpndPhyAcdSupprtFacilities(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Infrastructure and Learning Resources : Expenditure on Physical & Academic Support Facilities";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();

			// String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("instId", instituteId);

			ExpndturOnPhysclAcademicSupprt[] resArray = rest.postForObject(Constants.url + "/getExpndPhyAcdSupprtFacilities", map,
					ExpndturOnPhysclAcademicSupprt[].class);
			List<ExpndturOnPhysclAcademicSupprt> expndList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", expndList);

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
				headingName = expndList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f  });

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

				hcell = new PdfPCell(new Phrase("Financial Year)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Expenditure on Physical & Academic Support Facilities", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Total Expenditure excluding Salary", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("% of Expenditure on Physical & Academic Support Facilities", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				float expdPer = 0;			
				
				for (int i = 0; i < expndList.size(); i++) {
					// System.err.println("I " + i);
					ExpndturOnPhysclAcademicSupprt expd = expndList.get(i);

					expdPer = expd.getExpdOnPhyAcad()*100/expd.getTtlExpd();
					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + expd.getFinYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + expd.getExpdOnPhyAcad(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + expd.getTtlExpd(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + expdPer, headFontData));
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
				// document.add(new Paragraph("Academic Year : 2019-20"));

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
					// rowData.add("Academic Year");

					// rowData.add("E-Content Development");
					rowData.add("Financial Year");
					rowData.add("Expenditure on Physical & Academic Support Facilities");
					rowData.add("Total Expenditure excluding Salary");
					rowData.add("% of Expenditure on Physical & Academic Support Facilities");
					

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;

					float expndPer = 0;
					for (int i = 0; i < expndList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						expndPer = expndList.get(i).getExpdOnPhyAcad()*100/expndList.get(i).getTtlExpd();
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + expndList.get(i).getFinYear());
						rowData.add("" + expndList.get(i).getExpdOnPhyAcad());
						rowData.add("" + expndList.get(i).getTtlExpd());
						rowData.add("" + expndPer);

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = expndList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"", "",'E');
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
	
	@RequestMapping(value = "/showExpndGreenInitveWsteMgmt", method = RequestMethod.POST)
	public void showExpndGreenInitveWsteMgmt(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Institutional Values and Best Practices : Expenditure on Green Initiatives & Waste Management";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			ExpndGreenInitveWsteMgmt[] resArray = rest.postForObject(Constants.url + "/getExpndGreenInitveWsteMgmt", map,
					ExpndGreenInitveWsteMgmt[].class);
			List<ExpndGreenInitveWsteMgmt> expndGrnList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", expndGrnList);

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
				headingName = expndGrnList.get(0).getInstituteName();
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

				hcell = new PdfPCell(new Phrase("Financial Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Expenditure on Green Initiatives & Waste Management Excluding Salary", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Annual Expenditure on Green Initiatives & Waste Management Excluding Salary", tableHeaderFont));
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

				hcell = new PdfPCell(new Phrase("% of Expenditure on Green Initiatives & Waste Management", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				float bgt = 0;
				float ttlBugtPer = 0;
				float avgPerOnBugt = 0;

				for (int i = 0; i < expndGrnList.size(); i++) {
					// System.err.println("I " + i);
					ExpndGreenInitveWsteMgmt budget = expndGrnList.get(i);
					try {
						bgt = budget.getBudgetUtilized() * 100 / budget.getTtlExpnd();

					} catch (Exception e) {
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

					cell = new PdfPCell(new Phrase("" + budget.getBudgetUtilized(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + budget.getTtlExpnd(), headFontData));
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

					cell = new PdfPCell(new Phrase("" + bgt, headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					try {
						ttlBugtPer = bgt + ttlBugtPer;
						avgPerOnBugt = ttlBugtPer / index;
					} catch (Exception e) {
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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));

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
				System.out.println("Ttl Bugt %-----" + ttlBugtPer);
				System.out.println("Avg Bugt %-----" + avgPerOnBugt);
				document.add(new Paragraph("Average % of Green Initiatives & Waste Management : " + avgPerOnBugt));
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
					// rowData.add("Academic Year");

					rowData.add("Financial Year");
					rowData.add("Expenditure on Green Initiatives & Waste Management Excluding Salary");
					rowData.add("Annual Expenditure on Green Initiatives & Waste Management Excluding Salary");
					// rowData.add("Institute Name");
					rowData.add("% of Expenditure on Green Initiatives & Waste Management");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					float bgtPer = 0;
					float ttlBgtper = 0;
					float avgBgtPer = 0;
					for (int i = 0; i < expndGrnList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						try {
							bgtPer = expndGrnList.get(i).getBudgetUtilized() * 100
									/ expndGrnList.get(i).getTtlExpnd();
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}

						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + expndGrnList.get(i).getFinYear());
						rowData.add("" + expndGrnList.get(i).getBudgetUtilized());
						rowData.add("" + expndGrnList.get(i).getTtlExpnd());
						// rowData.add("" + budgetList.get(i).getInstituteName());
						rowData.add("" + bgtPer);

						try {
							ttlBgtper = bgtPer + ttlBgtper;
							avgBgtPer = ttlBgtper / cnt;
						} catch (Exception e) {
							System.err.println(e.getMessage());
						}
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					System.out.println("AVG Budget = " + avgBgtPer);
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = expndGrnList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						String avg = String.valueOf(avgBgtPer);
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "Average % of of Green Initiatives & Waste Management : "+avg,'D');
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

	@RequestMapping(value = "/showInitivAddrsLoctnAdvDisadv", method = RequestMethod.POST)
	public void showInitivAddrsLoctnAdvDisadv(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Institutional Values and Best Practices :  Initiative to Address Locational Advantages & Disadvantages";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			InitivAddrsLoctnAdvDisadv[] resArray = rest.postForObject(Constants.url + "/getInitivAddrsLoctnAdvDisadv", map,
					InitivAddrsLoctnAdvDisadv[].class);
			List<InitivAddrsLoctnAdvDisadv> initivAdrsLocAdvDisadvList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", initivAdrsLocAdvDisadvList);

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
				headingName = initivAdrsLocAdvDisadvList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(4);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f});

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

				hcell = new PdfPCell(new Phrase("Name of Initiative", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Beneficiary of Initiative", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;

				for (int i = 0; i < initivAdrsLocAdvDisadvList.size(); i++) {
					// System.err.println("I " + i);
					InitivAddrsLoctnAdvDisadv adv = initivAdrsLocAdvDisadvList.get(i);
					

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					cell = new PdfPCell(new Phrase("" + adv.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + adv.getNameOfInitiatives(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + adv.getNoStudPart(), headFontData));
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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));

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
					rowData.add("Name of Initiative");
					rowData.add("Beneficiary if initiative");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					for (int i = 0; i < initivAdrsLocAdvDisadvList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + initivAdrsLocAdvDisadvList.get(i).getAcademicYear());
						rowData.add("" + initivAdrsLocAdvDisadvList.get(i).getInstituteName());
						rowData.add("" + initivAdrsLocAdvDisadvList.get(i).getNoStudPart());
						
						
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = initivAdrsLocAdvDisadvList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "",'D');
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
	
	
	@RequestMapping(value = "/showNoInitivAddrsLoctnAdvDisadv", method = RequestMethod.POST)
	public void showNoInitivAddrsLoctnAdvDisadv(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Institutional Values and Best Practices : No of Initiative to Address Locational Advantages & Disadvantages";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
		//	String temp_ac_year = request.getParameter("temp_ac_year");
		//	String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("instId", instituteId);

			NoInitivAddrsLoctnAdvDisadv[] resArray = rest.postForObject(Constants.url + "/getNoInitivAddrsLoctnAdvDisadv", map,
					NoInitivAddrsLoctnAdvDisadv[].class);
			List<NoInitivAddrsLoctnAdvDisadv> initiativeList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", initiativeList);

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
				headingName = initiativeList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

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
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total No. of Initiatives", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				int index = 0;
			
				for (int i = 0; i < initiativeList.size(); i++) {
					// System.err.println("I " + i);
					NoInitivAddrsLoctnAdvDisadv stud = initiativeList.get(i);
					
					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					// cell.setPaddingLeft(10);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getTtlInitives(), headFontData));
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
				//document.add(new Paragraph("\n"));
				//document.add(new Paragraph("Academic Year : " + temp_ac_year));
			
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
					rowData.add("Total No. of Initiatives");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str = 0;
					for (int i = 0; i < initiativeList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + initiativeList.get(i).getAcademicYear());
						rowData.add("" + initiativeList.get(i).getTtlInitives());
						

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = initiativeList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"", "",'C' );
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
	
	/*@RequestMapping(value = "/showHumanValuProfEthics", method = RequestMethod.POST)
	public void showHumanValuProfEthics(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Institutional Values and Best Practices : Human Values & Professional Ethics";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
		//	String temp_ac_year = request.getParameter("temp_ac_year");
		//	String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("instId", instituteId);

			NoInitivAddrsLoctnAdvDisadv[] resArray = rest.postForObject(Constants.url + "/getNoInitivAddrsLoctnAdvDisadv", map,
					NoInitivAddrsLoctnAdvDisadv[].class);
			List<NoInitivAddrsLoctnAdvDisadv> initiativeList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", initiativeList);

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
				headingName = initiativeList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

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
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Academic Year", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Total No. of Initiatives", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				
				int index = 0;
			
				for (int i = 0; i < initiativeList.size(); i++) {
					// System.err.println("I " + i);
					NoInitivAddrsLoctnAdvDisadv stud = initiativeList.get(i);
					
					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					// cell.setPaddingLeft(10);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getTtlInitives(), headFontData));
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
				//document.add(new Paragraph("\n"));
				//document.add(new Paragraph("Academic Year : " + temp_ac_year));
			
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
					rowData.add("Total No. of Initiatives");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str = 0;
					for (int i = 0; i < initiativeList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + initiativeList.get(i).getAcademicYear());
						rowData.add("" + initiativeList.get(i).getTtlInitives());
						

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = initiativeList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"", "",'C' );
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

	}*/
	
	@RequestMapping(value = "/showNoOfLinkages", method = RequestMethod.POST)
	public void showNoOfLinkages(HttpServletRequest request, HttpServletResponse response) {

		String reportName =  "Research, Innovation and Extension : No of Linkages";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);
			NoOfLinkages[] resArray = rest.postForObject(Constants.url + "/getNoOfLinkages", map,
					NoOfLinkages[].class);
			List<NoOfLinkages> linkgList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", linkgList);

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
				headingName = linkgList.get(0).getInstituteName();
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

				hcell = new PdfPCell(new Phrase("Title of Linkages", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Agency with", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Nature of Linkage", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Years of", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);


				
				int index = 0;
			
				for (int i = 0; i < linkgList.size(); i++) {
					// System.err.println("I " + i);
					NoOfLinkages stud = linkgList.get(i);
					
					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getLinkageTitle(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					// cell.setPaddingLeft(10);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getPartneringInstitute(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + stud.getNatureOfLinkage(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + stud.getEstbYear(), headFontData));
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
				//document.add(new Paragraph("\n"));
				//document.add(new Paragraph("Academic Year : " + temp_ac_year));
			
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
					rowData.add("Title of Linkages");
					rowData.add("No. of Agency with");
					rowData.add("Nature of Linkage");
					rowData.add("Years of");
					
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str = 0;
					for (int i = 0; i < linkgList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						
						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + linkgList.get(i).getLinkageTitle());
						rowData.add("" + linkgList.get(i).getPartneringInstitute());
						rowData.add("" + linkgList.get(i).getNatureOfLinkage());
						rowData.add("" + linkgList.get(i).getEstbYear());
						

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = linkgList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "",'E' );
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
	
	/*
	 * @RequestMapping(value = "/showFunctnlMou", method = RequestMethod.POST)
	 * public void showFunctnlMou(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * String reportName =
	 * "Research, Innovation and Extension : Functional MoU's R&D";
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("report/ratio_report1");
	 * 
	 * HttpSession session = request.getSession();
	 * 
	 * String temp_ac_year = request.getParameter("temp_ac_year"); String ac_year =
	 * request.getParameter("ac_year"); int instituteId = (int)
	 * session.getAttribute("instituteId");
	 * 
	 * map = new LinkedMultiValueMap<>(); map.add("acYearList", ac_year);
	 * map.add("instId", instituteId);
	 * 
	 * FunctionalMou[] resArray = rest.postForObject(Constants.url +
	 * "/getFunctnlMou", map, FunctionalMou[].class); List<FunctionalMou> mouList =
	 * new ArrayList<>(Arrays.asList(resArray));
	 * 
	 * model.addObject("list", mouList);
	 * 
	 * Document document = new Document(PageSize.A4);
	 * 
	 * document.setMargins(Constants.marginLeft, Constants.marginRight,
	 * Constants.marginTop, Constants.marginBottom);
	 * document.setMarginMirroring(false);
	 * 
	 * String FILE_PATH = Constants.REPORT_SAVE; File file = new File(FILE_PATH);
	 * 
	 * PdfWriter writer = null;
	 * 
	 * FileOutputStream out = new FileOutputStream(FILE_PATH); try { writer =
	 * PdfWriter.getInstance(document, out); } catch (DocumentException e) {
	 * 
	 * e.printStackTrace(); }
	 * 
	 * String header = ""; String title = "";
	 * 
	 * DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy"); String headingName =
	 * null; try { headingName = mouList.get(0).getInstituteName(); } catch
	 * (Exception e) {
	 * 
	 * headingName = "-";
	 * 
	 * } ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);
	 * 
	 * writer.setPageEvent(event);
	 * 
	 * PdfPTable table = new PdfPTable(6);
	 * 
	 * table.setHeaderRows(1);
	 * 
	 * try { table.setWidthPercentage(100); table.setWidths(new float[] { 2.4f,
	 * 3.2f, 3.2f, 3.2f, 3.2f, 3.2f});
	 * 
	 * Font headFontData = Constants.headFontData;// new
	 * Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, // BaseColor.BLACK); Font
	 * tableHeaderFont = Constants.tableHeaderFont; // new
	 * Font(FontFamily.HELVETICA, 12, Font.BOLD, // BaseColor.BLACK);
	 * tableHeaderFont.setColor(Constants.tableHeaderFontBaseColor);
	 * 
	 * PdfPCell hcell = new PdfPCell();
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("Academic Year)", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("MoU Signed with Organization",
	 * tableHeaderFont)); hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("From Date", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("To Date", tableHeaderFont));
	 * hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * hcell = new PdfPCell(new Phrase("No. of Benificiary of MoU",
	 * tableHeaderFont)); hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * hcell.setBackgroundColor(Constants.baseColorTableHeader);
	 * 
	 * table.addCell(hcell);
	 * 
	 * int index = 0;
	 * 
	 * 
	 * for (int i = 0; i < mouList.size(); i++) { // System.err.println("I " + i);
	 * FunctionalMou mou = mouList.get(i);
	 * 
	 * 
	 * index++; PdfPCell cell; cell = new PdfPCell(new Phrase(String.valueOf(index),
	 * headFontData)); cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * 
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase("" + mou.getAcademicYear(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * 
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase("" + mou.getOrgName(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * 
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase("" + mou.getDurFromdt(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * 
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase("" + mou.getDurTodt(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	 * 
	 * table.addCell(cell);
	 * 
	 * cell = new PdfPCell(new Phrase("" + mou.getNo_benif(), headFontData));
	 * cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 * cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	 * 
	 * table.addCell(cell);
	 * 
	 * }
	 * 
	 * document.open(); Font reportNameFont = Constants.reportNameFont;// new
	 * Font(FontFamily.TIMES_ROMAN, 14.0f, // Font.UNDERLINE, BaseColor.BLACK);
	 * 
	 * Paragraph name = new Paragraph(reportName, reportNameFont);
	 * name.setAlignment(Element.ALIGN_CENTER); document.add(name);
	 * 
	 * document.add(new Paragraph("\n")); document.add(new
	 * Paragraph("Academic Year : " + temp_ac_year));
	 * 
	 * DateFormat DF = new SimpleDateFormat("dd-MM-yyyy"); document.add(new
	 * Paragraph("\n")); document.add(table);
	 * 
	 * int totalPages = writer.getPageNumber();
	 * 
	 * System.out.println("Page no " + totalPages);
	 * 
	 * document.close(); int p = Integer.parseInt(request.getParameter("p"));
	 * System.err.println("p " + p);
	 * 
	 * if (p == 1) {
	 * 
	 * if (file != null) {
	 * 
	 * String mimeType = URLConnection.guessContentTypeFromName(file.getName());
	 * 
	 * if (mimeType == null) {
	 * 
	 * mimeType = "application/pdf";
	 * 
	 * }
	 * 
	 * response.setContentType(mimeType);
	 * 
	 * response.addHeader("content-disposition",
	 * String.format("inline; filename=\"%s\"", file.getName()));
	 * 
	 * response.setContentLength((int) file.length());
	 * 
	 * InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	 * 
	 * try { FileCopyUtils.copy(inputStream, response.getOutputStream()); } catch
	 * (IOException e) { System.out.println("Excep in Opening a Pdf File");
	 * e.printStackTrace(); } } } else {
	 * 
	 * List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();
	 * 
	 * ExportToExcel expoExcel = new ExportToExcel(); List<String> rowData = new
	 * ArrayList<String>();
	 * 
	 * rowData.add("Sr. No"); // rowData.add("Academic Year");
	 * 
	 * // rowData.add("E-Content Development"); rowData.add("Academic Year");
	 * rowData.add("MoU Signed with Organization"); rowData.add("From Date");
	 * rowData.add("To Date"); rowData.add("No. Benificiary of MoU");
	 * 
	 * 
	 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel);
	 * 
	 * int cnt = 1;
	 * 
	 * for (int i = 0; i < mouList.size(); i++) { expoExcel = new ExportToExcel();
	 * rowData = new ArrayList<String>(); cnt = cnt + i;
	 * 
	 * rowData.add("" + (i + 1)); rowData.add("" +
	 * mouList.get(i).getAcademicYear()); rowData.add("" +
	 * mouList.get(i).getOrgName()); rowData.add("" +
	 * mouList.get(i).getDurFromdt()); rowData.add("" +
	 * mouList.get(i).getDurTodt()); rowData.add("" + mouList.get(i).getNo_benif());
	 * 
	 * expoExcel.setRowData(rowData); exportToExcelList.add(expoExcel);
	 * 
	 * }
	 * 
	 * XSSFWorkbook wb = null; try {
	 * 
	 * System.out.println("Excel List :" + exportToExcelList.toString()); String rep
	 * = null; try { rep = mouList.get(0).getInstituteName(); } catch (Exception e)
	 * {
	 * 
	 * rep = "-";
	 * 
	 * } System.err.println("headingName  " + headingName); // String excelName =
	 * (String) session.getAttribute("excelName"); wb =
	 * ExceUtil.createWorkbook(exportToExcelList, rep,
	 * reportName,"Academic Year : "+temp_ac_year, "",'F');
	 * ExceUtil.autoSizeColumns(wb, 3);
	 * response.setContentType("application/vnd.ms-excel"); String date = new
	 * SimpleDateFormat("yyyy-MM-dd").format(new Date());
	 * response.setHeader("Content-disposition", "attachment; filename=" +
	 * reportName + "-" + date + ".xlsx"); wb.write(response.getOutputStream());
	 * 
	 * } catch (IOException ioe) { throw new
	 * RuntimeException("Error writing spreadsheet to output stream"); } finally {
	 * if (wb != null) { wb.close(); } }
	 * 
	 * }
	 * 
	 * } catch (DocumentException ex) {
	 * 
	 * System.out.println("Pdf Generation Error: " + ex.getMessage());
	 * 
	 * ex.printStackTrace();
	 * 
	 * }
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("Exce in showratioReport " + e.getMessage());
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * }
	 */
	@RequestMapping(value = "/showFunctnlMou", method = RequestMethod.POST)
	public void showFunctnlMou(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Research, Innovation and Extension : Functional MoU's R&D";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			FunctionalMou[] resArray = rest.postForObject(Constants.url +"/getFunctnlMou", map, FunctionalMou[].class); 
			List<FunctionalMou> mouList = new ArrayList<>(Arrays.asList(resArray));
					 
			model.addObject("list", mouList);
					

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
				headingName = mouList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(6);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f  });

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

				hcell = new PdfPCell(new Phrase("Mou Signed with Organization", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("From Date", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("To Date", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("No. of Benificiary", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				int index = 0;

				for (int i = 0; i < mouList.size(); i++) {
					// System.err.println("I " + i);
					FunctionalMou mou = mouList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					cell = new PdfPCell(new Phrase("" + mou.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + mou.getOrgName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + mou.getDurFromdt(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + mou.getDurTodt(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + mou.getNo_benif(), headFontData));
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
				document.add(new Paragraph("Academic Year : " + temp_ac_year));

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
					rowData.add("MoU Signed with Organization");
					rowData.add("From Date");
					rowData.add("To Date");
					rowData.add("No. Benificiary of MoU");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;

					for (int i = 0; i < mouList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();

						cnt = cnt + i;

						rowData.add("" + (i + 1));
						rowData.add("" + mouList.get(i).getAcademicYear());
						rowData.add("" + mouList.get(i).getOrgName());
						rowData.add("" + mouList.get(i).getDurFromdt());
						rowData.add("" + mouList.get(i).getDurTodt());
						rowData.add("" + mouList.get(i).getNo_benif());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}
					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = mouList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, "", 'F');
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
	
	@RequestMapping(value = "/showAwardRecog", method = RequestMethod.POST)
	public void showAwardRecog(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Research, Innovation and Extension : Award/Recognition for Extension Activity";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();

			String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			AwrdRecgAgnstExtActivityReport[] resArray = rest.postForObject(Constants.url + "/getAwardRecog", map,
					AwrdRecgAgnstExtActivityReport[].class);
			List<AwrdRecgAgnstExtActivityReport> awrdList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", awrdList);

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
			String title = "                 ";

			DateFormat DF2 = new SimpleDateFormat("dd-MM-yyyy");
			String headingName = null;
			try {
				headingName = awrdList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

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
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				hcell = new PdfPCell(new Phrase("Sr.No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name Extension Activity", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Awarding/Recognizing Agency - Authority", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Years Of Awards", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				
				for (int i = 0; i < awrdList.size(); i++) {
					// System.err.println("I " + i);
					AwrdRecgAgnstExtActivityReport awrd = awrdList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + awrd.getActName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + awrd.getNameAwardingBody(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + awrd.getAwardYear(), headFontData));
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
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : " + temp_ac_year));


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
					rowData.add("Name Extension Activity");
					rowData.add("Awarding/Recognizing Agency - Authority");
					rowData.add("Years Of Awards");

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					int str = 0;
					for (int i = 0; i < awrdList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();

						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + awrdList.get(i).getActName());
						rowData.add("" + awrdList.get(i).getNameAwardingBody());
						rowData.add("" + awrdList.get(i).getAwardYear());

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = awrdList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year," ", 'C');
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

			System.err.println("Exce in showAwardRecog " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showNoAwardRecogExtAct", method = RequestMethod.POST)
	public void showFacPartiVarBodies(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Research, Innovation and Extension : No Award/Recognition for Extension Activity";

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

			NoAwardRecogExtAct[] resArray = rest.postForObject(Constants.url + "getNoAwardRecogExtAct", map,
					NoAwardRecogExtAct[].class);
			List<NoAwardRecogExtAct> noAwrdList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", noAwrdList);

			BufferedOutputStream outStream = null;
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
				headingName = noAwrdList.get(0).getInstituteName();
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

				hcell = new PdfPCell(new Phrase("Year of Award", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of  Award/Recognition", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				int index = 0;
				for (int i = 0; i < noAwrdList.size(); i++) {
					// System.err.println("I " + i);
					NoAwardRecogExtAct awrd = noAwrdList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + awrd.getAwardYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + awrd.getNoAward(), headFontData));
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

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Year of Award");
					rowData.add("No. of  Award/Recognition");
				

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < noAwrdList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + noAwrdList.get(i).getAwardYear());
						rowData.add("" + noAwrdList.get(i).getNoAward());
						
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
								"Academic Year:" + temp_ac_year + " ", "", 'C');

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

			System.err.println("Exce in showNoAwardRecogExtAct " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showIntelPropRght", method = RequestMethod.POST)
	public void showIntelPropRght(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Research, Innovation and Extension : Intellectual Property Rights and Industry Institute Initiatives";

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

			IntelectulPropRightReport[] resArray = rest.postForObject(Constants.url + "getIntelPropRght", map,
					IntelectulPropRightReport[].class);
			List<IntelectulPropRightReport> intelPropList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", intelPropList);

			BufferedOutputStream outStream = null;
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
				headingName = intelPropList.get(0).getInstituteName();
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

				hcell = new PdfPCell(new Phrase("Title of Seminar/Workshops on Ipr & III", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("From Date", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("To Date", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Participants", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				int index = 0;
				for (int i = 0; i < intelPropList.size(); i++) {
					// System.err.println("I " + i);
					IntelectulPropRightReport prop = intelPropList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prop.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + prop.getConName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + prop.getConFromdt(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + prop.getConTodt(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + prop.getConPcount(), headFontData));
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

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Academic Year");
					rowData.add("Title of Seminar on IPR & III");
					rowData.add("From Date");
					rowData.add("To Date");
					rowData.add("No. of Participants");
				

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < intelPropList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + intelPropList.get(i).getAcademicYear());
						rowData.add("" + intelPropList.get(i).getConName());
						rowData.add("" + intelPropList.get(i).getConFromdt());
						rowData.add("" + intelPropList.get(i).getConTodt());
						rowData.add("" + intelPropList.get(i).getConPcount());
						
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

			System.err.println("Exce in showIntelPropRght " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showAvgPerPlacement", method = RequestMethod.POST)
	public void showAvgPerPlacement(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Student Support and Progression : Avg % of Placement: (Last Five Years)";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");
			
			String prg_name = request.getParameter("prog_name");
			
			map = new LinkedMultiValueMap<>();

			map.add("programId", prg_name);
			Program progrm = rest.postForObject(Constants.url + "/getProgramByProgramId", map, Program.class);
			
			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();

			map.add("prgName", prg_name);
			map.add("instId", instituteId);
			map.add("acYearList", ac_year);

			AvgPerPlacement[] resArray = rest.postForObject(Constants.url + "getAvgPerPlacement", map,
					AvgPerPlacement[].class);
			List<AvgPerPlacement> studPlaceList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", studPlaceList);

			BufferedOutputStream outStream = null;
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
				headingName = studPlaceList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(9);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f});
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

				hcell = new PdfPCell(new Phrase("No. of Out going Students", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("No. of Student Placed", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Name of Employer(Company/Industry/Organization)", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Address of Employer", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Contact Details", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Min. Package", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Max. Package", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				float perPlacmntPreYr=0;
				float  AvgPlcmntFiveYr=0;
				float ttlStudPlaced = 0;
				float ttlstudPassed = 0;
				int index = 0;
				for (int i = 0; i < studPlaceList.size(); i++) {
					
					AvgPerPlacement studPlace = studPlaceList.get(i);
					
					//perPlacmntPreYr = (studPlace.getNoStudentPlaced()/studPlace.getNoStudPass())*100;
					ttlStudPlaced = studPlace.getNoStudentPlaced()+ttlStudPlaced;
					ttlstudPassed = studPlace.getNoStudPass()+ttlstudPassed;
					
					//AvgPlcmntFiveYr = perPlacmntPreYr/5;
					
					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + studPlace.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + studPlace.getNoStudPass(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + studPlace.getNoStudentPlaced(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + studPlace.getEmpyrName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + studPlace.getEmpyrAdd(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + studPlace.getContactDetail(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + studPlace.getMinPackage(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + studPlace.getMaxPackage(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
				}
				
				System.out.println("Add="+ttlStudPlaced);
				System.out.println("VVV"+ttlstudPassed+" / "+ttlstudPassed);
				
				perPlacmntPreYr = (ttlStudPlaced/ttlstudPassed)*100;
				System.out.println("perPlacmntPreYr "+perPlacmntPreYr);
				AvgPlcmntFiveYr = perPlacmntPreYr/5;
				
				System.out.println("AVG %="+perPlacmntPreYr+" "+AvgPlcmntFiveYr);
				
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
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("For Academic Year :" + temp_ac_year + ""));
				
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Programe Type :" + progrm.getNameOfProgram() + ""));
				document.add(new Paragraph("\n"));
				document.add(table);
				
				if(temp_ac_year.equals("Last Five Years")) {
					document.add(new Paragraph("\n"));
					document.add(new Paragraph("Avg. % of Placement for Last Five Year :" + AvgPlcmntFiveYr + ""));
				}else {
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("% of Placement Pre Year :" + perPlacmntPreYr + ""));
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

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Academic Year");
					rowData.add("No. of Out going Students");
					rowData.add("No. of Student Placed");
					rowData.add("Name of Employer(Company/Industry/Organization)");
					rowData.add("Address of Employer");
					rowData.add("Contact Details");
					rowData.add("Min. Package");
					rowData.add("Max. Package");
				
					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					
					for (int i = 0; i < studPlaceList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + studPlaceList.get(i).getAcademicYear());
						rowData.add("" + studPlaceList.get(i).getNoStudPass());
						rowData.add("" + studPlaceList.get(i).getNoStudentPlaced());
						rowData.add("" + studPlaceList.get(i).getEmpyrName());
						rowData.add("" + studPlaceList.get(i).getEmpyrAdd());
						rowData.add("" + studPlaceList.get(i).getContactDetail());
						rowData.add("" + studPlaceList.get(i).getMinPackage());
						rowData.add("" + studPlaceList.get(i).getMaxPackage());
						
						
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						// wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
						// "Academic Year :" + temp_ac_year + " ");
					
						String preYear = "% of Placement Pre Year : "+ String.valueOf(perPlacmntPreYr);
						String plcmntLastFivYear = "Avg. % of Placement for Last Five Year :" + String.valueOf(AvgPlcmntFiveYr) ;
						System.err.println(perPlacmntPreYr + " HERE " +AvgPlcmntFiveYr);
						
						if(temp_ac_year.equals("Last Five Years")) {
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year:" + temp_ac_year + "\n Programe Type : "+progrm.getNameOfProgram(), plcmntLastFivYear, 'I');
						}else {
							wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
									"Academic Year:" + temp_ac_year + "\n Programe Type : "+progrm.getNameOfProgram() , preYear, 'I');
						}
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

			System.err.println("Exce in showAvgPerPlacement " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showStudProgression", method = RequestMethod.POST)
	public void showStudProgression(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Student Support and Progression  : % of Students Progression ( Higher Education ): (Current YearData)";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("acYear", ac_year);
			map.add("instId", instituteId);

			StudProgression[] resArray = rest.postForObject(Constants.url + "/getStudProgression", map,
					StudProgression[].class);
			List<StudProgression> studProgList = new ArrayList<>(Arrays.asList(resArray));
			System.out.println("List : "+studProgList);
			model.addObject("list", studProgList);

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
				headingName = studProgList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				/*table.setWidthPercentage(100);
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

				
				 hcell = new PdfPCell(new Phrase("Institute Name", tableHeaderFont));
				 hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				 hcell.setBackgroundColor(Constants.baseColorTableHeader);
				  
				 table.addCell(hcell);
				 

				hcell = new PdfPCell(new Phrase("Year Wise %", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);*/

				int index = 0;
				float studPass = 0;
				float noStud = 0;
				float studProg = 0;

				for (int i = 0; i < studProgList.size(); i++) {
					// System.err.println("I " + i);
					StudProgression stud = studProgList.get(i);
					try {
						studPass = stud.getNoStudPass();
						noStud = stud.getNoStudent();
						studProg = (noStud/studPass)*100;
						//studProg = (stud.getNoStudent()/stud.getNoStudPass())*100;
						
						
					} catch (Exception e) {
						System.err.println("Invalid Values---" + e.getMessage());
					}
					index++;
					PdfPCell cell;
					/*cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getAcademicYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					// cell.setPaddingLeft(10);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getNoOfPwdStud(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + stud.getTotalStudEnrolled(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					
					 cell = new PdfPCell(new Phrase("" + stud.getInstituteName(), headFontData));
					 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
					 
					 table.addCell(cell);
					 

					cell = new PdfPCell(new Phrase("" + studPer, headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);*/

				}
				System.out.println("No Stud------------------="+noStud);
				System.out.println("Stud Pass------------------="+studPass);
				System.out.println("% Per------------------="+studProg);
				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : " + temp_ac_year));
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
				document.add(new Paragraph("Students Progression % Per Year : " + studProg));
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

					/*rowData.add("Sr. No");
					rowData.add("Academic Year");
					rowData.add("No. of Differently Abled Student");
					rowData.add("Total No. of Student Enrolled");
					rowData.add("Institute Name");
					rowData.add("Year Wise %");*/

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					
					for (int i = 0; i < studProgList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						/*str = studList.get(i).getNoOfPwdStud() * 100 / studList.get(i).getTotalStudEnrolled();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + studList.get(i).getAcademicYear());
						rowData.add("" + studList.get(i).getNoOfPwdStud());
						rowData.add("" + studList.get(i).getTotalStudEnrolled());
						rowData.add("" + studList.get(i).getInstituteName());
						rowData.add("" + str);*/

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = studProgList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						String summry = "Students Progression % Per Year"+String .valueOf(studProg);
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, summry,'E' );
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

			System.err.println("Exce in showStudProgression " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showTeacherAwardRecognitn", method = RequestMethod.POST)
	public void showTeacherAwardRecognitn(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Student Support and Progression  : Teachers Recognition/Awards and Incentives Information";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("acYear", ac_year);

			TeacherAwardRecognitn[] resArray = rest.postForObject(Constants.url + "getTeacherAwardRecognitn", map,
					TeacherAwardRecognitn[].class);
			List<TeacherAwardRecognitn> techrAwrdList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", techrAwrdList);

			BufferedOutputStream outStream = null;
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
				headingName = techrAwrdList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(7);

			table.setHeaderRows(1);

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

				hcell = new PdfPCell(new Phrase("Name of Teacher/Awardee", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Address/Contact No.", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Name of Award/Recognition", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Name of Awarding Agency ", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Year of Award/Recognition", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Incentive Details", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				int index = 0;
				for (int i = 0; i < techrAwrdList.size(); i++) {
					// System.err.println("I " + i);
					TeacherAwardRecognitn awrd = techrAwrdList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + awrd.getFacultyFirstName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + awrd.getContactNo(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + awrd.getAwardName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + awrd.getAwardAuthority(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + awrd.getAwardDate(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + awrd.getIncentive(), headFontData));
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

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Name of Teacher/Awardee");
					rowData.add("Address/Contact No.");
					rowData.add("Name of Award/Recognition");
					rowData.add("Name of Awarding Agency");
					rowData.add("Year of Award/Recognition");
					rowData.add("Incentive Details");
				

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < techrAwrdList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + techrAwrdList.get(i).getFacultyFirstName());
						rowData.add("" + techrAwrdList.get(i).getContactNo());
						rowData.add("" + techrAwrdList.get(i).getAwardName());
						rowData.add("" + techrAwrdList.get(i).getAwardAuthority());
						rowData.add("" + techrAwrdList.get(i).getAwardDate());
						rowData.add("" + techrAwrdList.get(i).getIncentive());
						
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

			System.err.println("Exce in showTeacherAwardRecognitn " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showTechrResrchPaprJournlInfo", method = RequestMethod.POST)
	public void showTechrResrchPaprJournlInfo(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Student Support and Progression  : Teacher Research Paper/Journal Information";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);
			map.add("acYear", ac_year);

			TechrResrchPaprJournlInfo[] resArray = rest.postForObject(Constants.url + "getTechrResrchPaprJournlInfo", map,
					TechrResrchPaprJournlInfo[].class);
			List<TechrResrchPaprJournlInfo> resrchInfoList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", resrchInfoList);

			BufferedOutputStream outStream = null;
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
				headingName = resrchInfoList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(7);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f});
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

				hcell = new PdfPCell(new Phrase("Name of Author/Co-Author", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Department of Authors", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Title of Paper", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Name of Journal", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Academic Year of Publication", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("If UGC/SCOPUS/DIO Recognized", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				int index = 0;
				String flag = null;
				for (int i = 0; i < resrchInfoList.size(); i++) {
					// System.err.println("I " + i);
					TechrResrchPaprJournlInfo resrch = resrchInfoList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + resrch.getFacultyFirstName()+" "+resrch.getCoAuthor() , headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + resrch.getDeptName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + resrch.getTitle(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + resrch.getJournalName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + resrch.getPublicationYear(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					if(resrch.getJournalType()==0) {
						flag="Yes";
					}else {
						flag="No";
					}
					
					cell = new PdfPCell(new Phrase("" + flag, headFontData));
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

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Name of Author/Co-Author");
					rowData.add("Department of Authors");
					rowData.add("Title of Paper");
					rowData.add("Name of Journal");
					rowData.add("Academic Year of Publication");
					rowData.add("If UGC/SCOPUS/DIO Recognized");
				

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < resrchInfoList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + resrchInfoList.get(i).getFacultyFirstName()+" "+resrchInfoList.get(i).getCoAuthor());
						rowData.add("" + resrchInfoList.get(i).getDeptName());
						rowData.add("" + resrchInfoList.get(i).getTitle());
						rowData.add("" + resrchInfoList.get(i).getJournalName());
						rowData.add("" + resrchInfoList.get(i).getPublicationYear());
						rowData.add("" + flag);
						
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

			System.err.println("Exce in showTechrResrchPaprJournlInfo " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showTechrResrchPaprJournlRatio", method = RequestMethod.POST)
	public void showTechrResrchPaprJournlRatio(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Student Support and Progression  : Teacher Research Paper/Journal Information";

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

			TechrResrchPaprJournlRatio[] resArray = rest.postForObject(Constants.url + "getTechrResrchPaprJournlRatio", map,
					TechrResrchPaprJournlRatio[].class);
			List<TechrResrchPaprJournlRatio> resrchRatioList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", resrchRatioList);

			BufferedOutputStream outStream = null;
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
				headingName = resrchRatioList.get(0).getInstituteName();
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
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f});
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

				hcell = new PdfPCell(new Phrase("No. of Publication in UGC/SCOPUS/DIO Journals", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Total No. of Full Time Teachers in AY", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				
				int index = 0;
				float ttlTechr = 0;
				float avgTech = 0;
				float ttlPublcatn = 0;
				float resrchPprPerTechr = 0;
				String flag = null;
				for (int i = 0; i < resrchRatioList.size(); i++) {
					// System.err.println("I " + i);
					TechrResrchPaprJournlRatio ratio = resrchRatioList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + ratio.getAcademicYear() , headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + ratio.getPublishedInUgc(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + ratio.getFullTimeTeacher(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					ttlPublcatn=ratio.getPublishedInUgc()+ttlPublcatn;
					ttlTechr=ratio.getFullTimeTeacher()+ttlTechr;
					
				}
				
				System.out.println("Totl Teacher----------"+ttlTechr);
				avgTech=ttlTechr/5;
				System.out.println("Avg Last yr------------"+avgTech);
				System.out.println("TTlNo Publication-----"+ttlPublcatn);
				resrchPprPerTechr = ttlPublcatn/avgTech;
				System.out.println("Research Paper Per Teacher-------"+resrchPprPerTechr);
				
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
				
				document.add(new Paragraph("\n"));
				if(temp_ac_year.equals("Last Five Years")) {
				document.add(new Paragraph("No. of Research Papers per Teacher :" + resrchPprPerTechr + ""));
				document.add(new Paragraph("\n"));
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

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("Academic Year");
					rowData.add("No. of Publication in UGC/SCOPUS/DIO Journals");
					rowData.add("Total No. of Full Time Teachers in AY");
				

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < resrchRatioList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + resrchRatioList.get(i).getAcademicYear());
						rowData.add("" + resrchRatioList.get(i).getPublishedInUgc());
						rowData.add("" + resrchRatioList.get(i).getFullTimeTeacher());
												
						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());

						// String excelName = (String) session.getAttribute("excelName");
						// wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
						// "Academic Year :" + temp_ac_year + " ");
						
						if(temp_ac_year.equals("Last Five Years")) {
						String resrchPaper="No. of Research Papers per Teacher :"+String.valueOf(resrchPprPerTechr);
					
						wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
								"Academic Year:" + temp_ac_year + " ",resrchPaper, 'F');
						}
						else {
							String resrchPaper=String.valueOf(resrchPprPerTechr);
							wb = ExceUtil.createWorkbook(exportToExcelList, headingName, reportName,
									"Academic Year:" + temp_ac_year + " ","", 'F');
						}
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

			System.err.println("Exce in showTechrResrchPaprJournlRatio " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showResrchProjectGrants", method = RequestMethod.POST)
	public void showResrchProjectGrants(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Student Support and Progression  : Research Project Grants";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			
			map = new LinkedMultiValueMap<>();
			map.add("instId", instituteId);
			map.add("acYear", ac_year);

			ResrchProjectGrants[] resArray = rest.postForObject(Constants.url + "getResrchProjectGrants", map,
					ResrchProjectGrants[].class);
			List<ResrchProjectGrants> resrchGrantList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", resrchGrantList);

			BufferedOutputStream outStream = null;
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
				headingName = resrchGrantList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);
			// writer.add(new Paragraph("Curricular Aspects"));

			PdfPTable table = new PdfPTable(8);

			table.setHeaderRows(1);

			try {
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f, 3.2f});
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

				hcell = new PdfPCell(new Phrase("Name of Project", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name of Investigator", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Dept. of Investigator", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Grant Sanction", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("From Date", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("To Date", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Sponsoring Authority", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				int index = 0;
				String flag = null;
				for (int i = 0; i < resrchGrantList.size(); i++) {
					// System.err.println("I " + i);
					ResrchProjectGrants grant = resrchGrantList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + grant.getProjName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + grant.getProjInvName(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + grant.getProjInvDept(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + grant.getProjGrant(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + grant.getProjFrdt(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					cell = new PdfPCell(new Phrase("" + grant.getProjTodt(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + grant.getProj_sponsor(), headFontData));
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
					rowData.add("Name of Project");
					rowData.add("Name of Investigator");
					rowData.add("Dept. of Investigator");
					rowData.add("Grant Sanction");
					rowData.add("From Date");
					rowData.add("To Date");
					rowData.add("Sponsoring Authority");
				

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < resrchGrantList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + resrchGrantList.get(i).getProjName());
						rowData.add("" + resrchGrantList.get(i).getProjInvName());
						rowData.add("" + resrchGrantList.get(i).getProjInvDept());
						rowData.add("" + resrchGrantList.get(i).getProjGrant());
						rowData.add("" + resrchGrantList.get(i).getProjFrdt());
						rowData.add("" + resrchGrantList.get(i).getProjTodt());
						rowData.add("" + resrchGrantList.get(i).getProj_sponsor());
						
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

			System.err.println("Exce in showResrchProjectGrants " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showFullTimeTechrInstResrchGuide", method = RequestMethod.POST)
	public void showFullTimeTechrInstResrchGuide(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Research, Innovation and Extension : No. of Full Time Teachers in the Institute as Research Guide";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);

			map.add("acYear", ac_year);

			FullTimeTechrInstResrchGuide[] resArray = rest.postForObject(Constants.url + "getFullTimeTechrInstResrchGuide", map,
					FullTimeTechrInstResrchGuide[].class);
			List<FullTimeTechrInstResrchGuide> rsrchGuidList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", rsrchGuidList);

			BufferedOutputStream outStream = null;
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
				headingName = rsrchGuidList.get(0).getInstituteName();
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
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f});
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

				hcell = new PdfPCell(new Phrase("No. of Full Time Teachers", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Research Guide Recognitions to Full Time Teacher", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("% of Research Guide", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				int index = 0;
				float perResrchGuide = 0;
				
				for (int i = 0; i < rsrchGuidList.size(); i++) {
					// System.err.println("I " + i);
					FullTimeTechrInstResrchGuide guide = rsrchGuidList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + guide.getFullTimeTeacher(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + guide.getNoResearchGuide(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					perResrchGuide=(guide.getNoResearchGuide()/guide.getFullTimeTeacher())*100;
					
					cell = new PdfPCell(new Phrase("" + perResrchGuide, headFontData));
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

					List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

					ExportToExcel expoExcel = new ExportToExcel();
					List<String> rowData = new ArrayList<String>();

					rowData.add("Sr. No");
					rowData.add("No. of Full Time Teacher");
					rowData.add("No. of Research Guide Recognitions to Full Time Teacher");
					rowData.add("% of Research Guide");
					

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < rsrchGuidList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + rsrchGuidList.get(i).getFullTimeTeacher());
						rowData.add("" + rsrchGuidList.get(i).getNoResearchGuide());
						rowData.add("" + perResrchGuide);
						
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

			System.err.println("Exce in showIntelPropRght " + e.getMessage());
			e.printStackTrace();

		}

	}
	
	@RequestMapping(value = "/showPerNewCource", method = RequestMethod.POST)
	public void showPerNewCource(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Curricular Aspects : Percentage(%) of New	Courses Introduced";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			String temp_ac_year = request.getParameter("temp_ac_year");
			String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
			map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			PerNewCource[] resArray = rest.postForObject(Constants.url + "/getPerNewCource", map,
					PerNewCource[].class);
			List<PerNewCource> lastFiveYrList= new ArrayList<>(Arrays.asList(resArray));
			System.out.println("List : "+lastFiveYrList);
			model.addObject("list", lastFiveYrList);

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
				headingName = lastFiveYrList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				int index = 0;
				float noCourse = 0;
				float ttlNoCourse = 0;
				float perCourseIntro = 0;

				for (int i = 0; i < lastFiveYrList.size(); i++) {
				
					try {
						noCourse = lastFiveYrList.get(0).getNoCoursesInLast5(); 		//---------------Courses of Last 5 Years
						ttlNoCourse = lastFiveYrList.get(1).getNoCoursesInLast5();		//---------------Total No. of Courses until now
						perCourseIntro = (noCourse*100)/ttlNoCourse;
						
					} catch (Exception e) {
						System.err.println("Invalid Values---" + e.getMessage());
					}
					index++;
					PdfPCell cell;
					
				}
				
				System.out.println("No Course------------------="+noCourse);
				System.out.println("Totla Course------------------="+ttlNoCourse);
				System.out.println("% Per------------------="+perCourseIntro);
				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Academic Year : " + temp_ac_year));
				

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("\n"));
				document.add(table);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Percentage(%) of New Courses Introduced : " + perCourseIntro));
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


					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					
					for (int i = 0; i < lastFiveYrList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = lastFiveYrList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						String summry = "Percentage(%) of New Courses Introduced : "+String .valueOf(perCourseIntro);
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"Academic Year : "+temp_ac_year, summry,'E' );
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

			System.err.println("Exce in showPerNewCource " + e.getMessage());
			e.printStackTrace();

		}

	}

	@RequestMapping(value = "/showPerProgCbseElecticwCourse", method = RequestMethod.POST)
	public void showPerProgCbseElecticwCourse(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Curricular Aspects : Percentage(%) of Programs with CBCS/Elective courses";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/ratio_report1");

			HttpSession session = request.getSession();
			String temp_ac_year = request.getParameter("temp_ac_year");
			//String ac_year = request.getParameter("ac_year");
			int instituteId = (int) session.getAttribute("instituteId");

			map = new LinkedMultiValueMap<>();
		//	map.add("acYearList", ac_year);
			map.add("instId", instituteId);

			PerProgCbseElectiveCourse[] resArray = rest.postForObject(Constants.url + "/getPerProgCbseElectiveCourse", map,
					PerProgCbseElectiveCourse[].class);
			List<PerProgCbseElectiveCourse> courseList= new ArrayList<>(Arrays.asList(resArray));
			System.out.println("List : "+courseList);
			model.addObject("list", courseList);

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
				headingName = courseList.get(0).getInstituteName();
			} catch (Exception e) {

				headingName = "-";

			}
			ItextPageEvent event = new ItextPageEvent(header, title, "", headingName);

			writer.setPageEvent(event);

			PdfPTable table = new PdfPTable(5);

			table.setHeaderRows(1);

			try {
				int index = 0;
				float noProgm = 0;
				float ttlNoProgm = 0;
				float perProgmCourse = 0;

				for (int i = 0; i < courseList.size(); i++) {
				
					try {
						noProgm = courseList.get(0).getCount1(); 		//---------------No. of Programs with CBSE/Elective courses implemented.
						ttlNoProgm = courseList.get(1).getCount1();		//---------------Total No. of Program offered.
						perProgmCourse = (noProgm*100)/ttlNoProgm;
						
					} catch (Exception e) {
						System.err.println("Invalid Values---" + e.getMessage());
					}
					index++;
					PdfPCell cell;
					
				}
				
				System.out.println("No Course------------------="+noProgm);
				System.out.println("Totle Course Offered------------------="+ttlNoProgm);
				System.out.println("% Per------------------="+perProgmCourse);
				document.open();
				Font reportNameFont = Constants.reportNameFont;// new Font(FontFamily.TIMES_ROMAN, 14.0f,
																// Font.UNDERLINE, BaseColor.BLACK);

				Paragraph name = new Paragraph(reportName, reportNameFont);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				//document.add(new Paragraph("\n"));
				//document.add(new Paragraph("Academic Year : " + temp_ac_year));
				

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				document.add(new Paragraph("\n"));
				document.add(table);
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Percentage(%) of Progremes with CBSE/Elective Courses : " + perProgmCourse));
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


					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

					int cnt = 1;
					
					for (int i = 0; i < courseList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						

						expoExcel.setRowData(rowData);
						exportToExcelList.add(expoExcel);

					}

					XSSFWorkbook wb = null;
					try {

						System.out.println("Excel List :" + exportToExcelList.toString());
						String rep = null;
						try {
							rep = courseList.get(0).getInstituteName();
						} catch (Exception e) {

							rep = "-";

						}
						System.err.println("headingName  " + headingName);
						// String excelName = (String) session.getAttribute("excelName");
						String summry = "Percentage(%) of Progremes with CBSE/Elective Courses : "+String .valueOf(perProgmCourse);
						wb = ExceUtil.createWorkbook(exportToExcelList, rep, reportName,"", summry,'E' );
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

			System.err.println("Exce in showPerProgCbseElecticwCourse " + e.getMessage());
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/showFildeProjectInternReport", method = RequestMethod.POST)
	public void showFildeProjectInternReport(HttpServletRequest request, HttpServletResponse response) {

		String reportName = "Curricular Aspects :  Fields Project/Internships";

		ModelAndView model = null;
		try {

			model = new ModelAndView("report/prog_report1");

			String ac_year = request.getParameter("ac_year");
			String temp_ac_year = request.getParameter("temp_ac_year");
			int prog_name = Integer.parseInt(request.getParameter("prog_name"));
			
			map = new LinkedMultiValueMap<>();
			map.add("programId", prog_name);
			Program progrm = rest.postForObject(Constants.url + "/getProgramByProgramId", map, Program.class);
			
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			map = new LinkedMultiValueMap<>();

			map.add("instId", instituteId);
			map.add("prog_name", prog_name);
			map.add("acYear", ac_year);

			FildeProjectInternReport[] resArray = rest.postForObject(Constants.url + "getFildeProjectInternReport", map,
					FildeProjectInternReport[].class);
			List<FildeProjectInternReport> internList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("list", internList);

			BufferedOutputStream outStream = null;
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
				headingName = internList.get(0).getInstituteName();
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
				table.setWidths(new float[] { 2.4f, 3.2f, 3.2f, 3.2f, 3.2f});
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

				hcell = new PdfPCell(new Phrase("Program Name", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Provision for Undertaking Field Projects/Internship"));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("No. of Students Undertaking Field Projects/Internship Code", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				hcell = new PdfPCell(new Phrase("Link to the Relevant Documents", tableHeaderFont));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(Constants.baseColorTableHeader);

				table.addCell(hcell);
				
				int index = 0;
				float perResrchGuide = 0;
				
				for (int i = 0; i < internList.size(); i++) {
					// System.err.println("I " + i);
					FildeProjectInternReport intern = internList.get(i);

					index++;
					PdfPCell cell;
					cell = new PdfPCell(new Phrase(String.valueOf(index), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + intern.getProgramType(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + intern.getProvisionForUndertaking(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + intern.getNoOfStudUndertaking(), headFontData));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_LEFT);

					table.addCell(cell);
					
					cell = new PdfPCell(new Phrase("" + intern.getDocument(), headFontData));
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
				
				document.add(new Paragraph("\n"));
				document.add(new Paragraph("Program Type : "+progrm.getNameOfProgram() ));
				document.add(new Paragraph("\n"));

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
					rowData.add("Program Name");
					rowData.add("Type of Program");
					rowData.add("Provision for Undertaking Field Projects/Internship");
					rowData.add("No. of Students Undertaking Field Projects/Internship Code");
					rowData.add("Link to the Relevant Documents");

					

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);
					int cnt = 1;
					for (int i = 0; i < internList.size(); i++) {
						expoExcel = new ExportToExcel();
						rowData = new ArrayList<String>();
						cnt = cnt + i;

						rowData.add("" + (i + 1));

						rowData.add("" + internList.get(i).getProgramType());
						rowData.add("" + internList.get(i).getProvisionForUndertaking());
						rowData.add("" + internList.get(i).getNoOfStudUndertaking());
						rowData.add("" + internList.get(i).getNoOfStudUndertaking());
						rowData.add("" + internList.get(i).getDocument());
						
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
								"Academic Year:" + temp_ac_year + "\n Programe Type : "+progrm.getNameOfProgram(), "", 'F');

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

			System.err.println("Exce in showIntelPropRght " + e.getMessage());
			e.printStackTrace();

		}

	}
}

