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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.ExportToExcel;
import com.ats.rusasoft.commons.Names;
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.GenderEqalityPrg;
import com.ats.rusasoft.model.GovtScholarships;
import com.ats.rusasoft.model.IctEnabledFacilities;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.InstituteActivity;
import com.ats.rusasoft.model.InstituteSupport;
import com.ats.rusasoft.model.IntelPrpoRight;
import com.ats.rusasoft.model.StaffList;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.ActCndctPrmtngUnivrslVal;
import com.ats.rusasoft.model.instprofile.SpecificLocalAdvntgDisadvntg;
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
@Scope("session")
public class InstituteController {

	RestTemplate rest = new RestTemplate();
	String redirect = null;

	/*********************************************
	 * Institute Support
	 **********************************************/

	@RequestMapping(value = "/showInstituteSupport", method = RequestMethod.GET)
	public ModelAndView showInstituteSupport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		try {

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("showInstituteSupport", "showInstituteSupport", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/instituteSupport");
				model.addObject("title", "Other (Besides Government) Financial Support Scheme");
				model.addObject("title1",
						"Institute Support Financially by Awarding Scholarship/Freeships like schemes other than Government Schemes ");

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("yId", yId);

				InstituteSupport[] instSuprtArr = rest.postForObject(Constants.url + "/getSchemesByIds", map,
						InstituteSupport[].class);
				List<InstituteSupport> instSuprtlist = new ArrayList<>(Arrays.asList(instSuprtArr));

				model.addObject("schemeList", instSuprtlist);

				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();

				rowData.add("Sr. No");
				rowData.add("Name of Scheme");
				rowData.add("No. of Students Benefited");
				rowData.add("Scheme/Support offered By");

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				int cnt = 1;
				for (int i = 0; i < instSuprtlist.size(); i++) {
					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();
					cnt = cnt + i;

					rowData.add("" + (i + 1));

					rowData.add("" + instSuprtlist.get(i).getInstSchemeName());
					rowData.add("" + instSuprtlist.get(i).getInstStudentsBenefited());
					rowData.add("" + instSuprtlist.get(i).getInstSchmeOfferedby());

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

				}

				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "GetMatIssueHeader");

				Info add = AccessControll.checkAccess("showInstituteSupport", "showInstituteSupport", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showInstituteSupport", "showInstituteSupport", "0", "0", "1",
						"0", newModuleList);
				Info delete = AccessControll.checkAccess("showInstituteSupport", "showInstituteSupport", "0", "0", "0",
						"1", newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddInstituteSupport", method = RequestMethod.GET)
	public ModelAndView showAddInstituteSupport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_institute_support");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/showAddInstituteSupport", "showInstituteSupport", "0", "1", "0",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				InstituteSupport instSpprt = new InstituteSupport();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId", map,
						AcademicYear[].class);

				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);

				model.addObject("instSpprt", instSpprt);
				model.addObject("title", "Add Other (Besides Government) Financial Support Scheme");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertInstituteSupport", method = RequestMethod.POST)
	public String insertInstituteSupport(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");
			int amt = 0;
						
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());

			InstituteSupport instSpprt = new InstituteSupport();

			instSpprt.setInstSchemeId(Integer.parseInt(request.getParameter("inst_scheme_id")));
			instSpprt.setInstituteId(instituteId);
			instSpprt.setYearId(yId);
			instSpprt.setInstSchemeName(request.getParameter("inst_scheme_name"));
			instSpprt.setInstStudentsBenefited(Integer.parseInt(request.getParameter("inst_students_benefited")));
			instSpprt.setInstSchmeOfferedby(request.getParameter("inst_schme_offeredby"));
			instSpprt.setDelStatus(1);
			instSpprt.setIsActive(1);
			instSpprt.setMakerUserId(userId);
			instSpprt.setMakerDatetime(curDateTime);
			try {
				amt =  Integer.parseInt(request.getParameter("amount"));
			}catch(Exception e){
				System.err.println(e.getMessage());
				amt = 0;
			}
			instSpprt.setExInt1(amt);
			instSpprt.setExInt2(0);
			instSpprt.setExVar1("NA");
			instSpprt.setExVar2("NA");

			InstituteSupport saveInstSupprt = rest.postForObject(Constants.url + "/addInstSupprt", instSpprt,
					InstituteSupport.class);

			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/showInstituteSupport";
			else
				redirect = "redirect:/showAddInstituteSupport";
		} catch (Exception e) {

			e.printStackTrace();

		}
		return redirect;

	}

	@RequestMapping(value = "/editInstituteScheme/{schmId}", method = RequestMethod.GET)
	public ModelAndView editInstituteScheme(@PathVariable("schmId") int schmId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/editInstituteScheme/{schmId}", "showInstituteSupport", "0", "0",
					"1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/add_institute_support");
				InstituteSupport instSpprt = new InstituteSupport();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("schmId", schmId);

				InstituteSupport suprtSchm = rest.postForObject(Constants.url + "/getSuprtSchemeBySchmId", map,
						InstituteSupport.class);
				model.addObject("instSpprt", suprtSchm);

				model.addObject("title", "Edit Other (Besides Government) Financial Support Scheme");

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteInstituteScheme/{schmId}", method = RequestMethod.GET)
	public String deleteInstituteScheme(@PathVariable("schmId") int schmId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map.add("schmId", schmId);

			InstituteSupport delSchm = rest.postForObject(Constants.url + "/deleteSuprtSchemeBySchmId", map,
					InstituteSupport.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showInstituteSupport";
	}

	@RequestMapping(value = "/delInstSupport/{instSchId}", method = RequestMethod.GET)
	public String deleteinstLinkages(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int instSchId) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("delInstSupport/{instId}", "showInstituteSupport", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (instSchId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("instSchId");
					//System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String instIdList = sb.toString();
					instIdList = instIdList.substring(0, instIdList.length() - 1);

					map.add("instIdList", instIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("instIdList", instSchId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deletinstSupport", map, Info.class);

				a = "redirect:/showInstituteSupport";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	@RequestMapping(value = "/instituteSchemePdf", method = RequestMethod.GET)
	public void instituteSchemePdf(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("instituteId", instituteId);
			map.add("yId", yId);

			InstituteSupport[] instSuprtArr = rest.postForObject(Constants.url + "/getSchemesByIds", map,
					InstituteSupport[].class);
			List<InstituteSupport> instSuprtlist = new ArrayList<>(Arrays.asList(instSuprtArr));

			BufferedOutputStream outStream = null;
			//System.out.println("Inside Pdf showCustomerwisePdf");
			Document document = new Document(PageSize.A4);

			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			//System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
			String FILE_PATH = Constants.REPORT_SAVE;
			File file = new File(FILE_PATH);

			PdfWriter writer = null;

			FileOutputStream out = new FileOutputStream(FILE_PATH);
			try {
				writer = PdfWriter.getInstance(document, out);
			} catch (DocumentException e) {

				e.printStackTrace();
			}

			PdfPTable table = new PdfPTable(4);
			try {
				//System.out.println("Inside PDF Table try");
				table.setWidthPercentage(100);
				table.setWidths(new float[] { 2.4f, 3.4f, 3.2f, 3.2f });
				Font headFont = new Font(FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
				Font headFont1 = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
				headFont1.setColor(BaseColor.WHITE);
				Font f = new Font(FontFamily.TIMES_ROMAN, 12.0f, Font.UNDERLINE, BaseColor.BLUE);

				PdfPCell hcell = new PdfPCell();
				hcell.setBackgroundColor(BaseColor.PINK);

				hcell.setPadding(3);
				hcell = new PdfPCell(new Phrase("Sr.No.", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Name of Scheme", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("No. of Students Benefited", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);
				table.addCell(hcell);

				hcell = new PdfPCell(new Phrase("Scheme/Support offered By", headFont1));
				hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				hcell.setBackgroundColor(BaseColor.PINK);
				table.addCell(hcell);

				int index = 0;
				for (InstituteSupport work : instSuprtlist) {
					index++;
					PdfPCell cell;

					cell = new PdfPCell(new Phrase(String.valueOf(index), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPadding(3);
					cell.setPaddingRight(2);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + work.getInstSchemeName(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + work.getInstStudentsBenefited(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

					cell = new PdfPCell(new Phrase("" + work.getInstSchmeOfferedby(), headFont));
					cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell.setPaddingRight(2);
					cell.setPadding(3);
					table.addCell(cell);

				}
				document.open();
				Paragraph name = new Paragraph("Institute Schemes List\n", f);
				name.setAlignment(Element.ALIGN_CENTER);
				document.add(name);
				document.add(new Paragraph(" "));
				/*
				 * Paragraph company = new Paragraph("Customer Wise Report\n", f);
				 * company.setAlignment(Element.ALIGN_CENTER); document.add(company);
				 * document.add(new Paragraph(" "));
				 */

				DateFormat DF = new SimpleDateFormat("dd-MM-yyyy");
				String reportDate = DF.format(new Date());
				// Paragraph p1 = new Paragraph("From Date:" + fromDate + " To Date:" + toDate,
				// headFont);
				// p1.setAlignment(Element.ALIGN_CENTER);
				// document.add(p1);
				document.add(new Paragraph("\n"));
				document.add(table);

				int totalPages = writer.getPageNumber();

				//System.out.println("Page no " + totalPages);

				document.close();

				if (file != null) {

					String mimeType = URLConnection.guessContentTypeFromName(file.getName());

					if (mimeType == null) {

						mimeType = "application/pdf";

					}

					response.setContentType(mimeType);

					response.addHeader("content-disposition", String.format("inline; filename=\"%s\"", file.getName()));

					response.setContentLength((int) file.length());

					InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

					try {
						FileCopyUtils.copy(inputStream, response.getOutputStream());
					} catch (IOException e) {
						//System.out.println("Excep in Opening a Pdf File");
						e.printStackTrace();
					}
				}

			} catch (DocumentException ex) {

				//System.out.println("Pdf Generation Error: " + ex.getMessage());

				ex.printStackTrace();

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	/******************************************
	 * Institute Activity
	 ******************************************/

	@RequestMapping(value = "/showActivityOrganized", method = RequestMethod.GET)
	public ModelAndView showActivityOrganized(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showActivityOrganized", "showActivityOrganized", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("yId", yId);

				InstituteActivity[] instActArr = rest.postForObject(Constants.url + "/getAllInstituteActities", map,
						InstituteActivity[].class);
				List<InstituteActivity> activityList = new ArrayList<>(Arrays.asList(instActArr));
				model = new ModelAndView("instituteInfo/IQAC/activityOrganized");
				model.addObject("instActList", activityList);

				model.addObject("title", "Institute Organized Activities");
				Info add = AccessControll.checkAccess("showActivityOrganized", "showActivityOrganized", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showActivityOrganized", "showActivityOrganized", "0", "0", "1",
						"0", newModuleList);
				Info delete = AccessControll.checkAccess("showActivityOrganized", "showActivityOrganized", "0", "0",
						"0", "1", newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddActivityOrganized", method = RequestMethod.GET)
	public ModelAndView showAddActivityOrganized(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddActivityOrganized", "showActivityOrganized", "0", "1", "0",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/add_activity_organized");
				InstituteActivity instAct = new InstituteActivity();
				model.addObject("instAct", instAct);

				model.addObject("title", "Add Institute Organized Activities");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertInstituteActivity", method = RequestMethod.POST)
	public String insertInstituteActivity(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());

			InstituteActivity instAct = new InstituteActivity();
			instAct.setInstActivityId(Integer.parseInt(request.getParameter("activityId")));
			instAct.setInstituteId(instituteId);
			instAct.setYearId(yId);
			instAct.setInstActivityType(request.getParameter("activityType"));
			instAct.setInstActivityLevel(request.getParameter("activityLevel"));
			instAct.setInstActivityName(request.getParameter("activityName"));
			instAct.setInstActivityFromdt(request.getParameter("fromDate"));
			instAct.setInstActivityTodt(request.getParameter("toDate"));
			instAct.setInstActivityParticipation(Integer.parseInt(request.getParameter("inst_activity_participation")));
			instAct.setDelStatus(1);
			instAct.setIsActive(1);
			instAct.setMakerUserId(userId);
			instAct.setMakerDatetime(curDateTime);
			instAct.setExInt1(0);
			instAct.setExInt2(0);
			instAct.setExVar1("NA");
			instAct.setExVar2("NA");
			//System.out.println(instAct.toString());
			InstituteActivity saveinstActvt = rest.postForObject(Constants.url + "/addNewInstituteActity", instAct,
					InstituteActivity.class);

			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/showActivityOrganized";
			else
				redirect = "redirect:/showAddActivityOrganized";

		} catch (Exception e) {

			e.printStackTrace();

		}
		return redirect;
	}

	@RequestMapping(value = "/editActivity/{instActvId}", method = RequestMethod.GET)
	public ModelAndView editActivity(@PathVariable("instActvId") int instActvId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_activity_organized");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddActivityOrganized", "showActivityOrganized", "0", "0", "1",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				InstituteActivity instAct = new InstituteActivity();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("instActvId", instActvId);

				InstituteActivity activity = rest.postForObject(Constants.url + "/getInstActivityById", map,
						InstituteActivity.class);
				model.addObject("instAct", activity);

				model.addObject("title", "Edit Institute Organized Activities");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteActivity/{instActvId}", method = RequestMethod.GET)
	public String deleteActivity(@PathVariable("instActvId") int instActvId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddActivityOrganized", "showActivityOrganized", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instActvId", instActvId);

				InstituteActivity delActiv = rest.postForObject(Constants.url + "/deleteActivId", map,
						InstituteActivity.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showActivityOrganized";
	}

	@RequestMapping(value = "/delOrgActivities/{activityId}", method = RequestMethod.GET)
	public String delOrgActivities(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int activityId) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("delOrgActivities/{instSchId}", "showActivityOrganized", "0", "0",
					"0", "1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (activityId == 0) {

					System.err.println("Multiple records delete ");
					String[] activityIds = request.getParameterValues("activityId");
					//System.out.println("id are" + activityIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < activityIds.length; i++) {
						sb = sb.append(activityIds[i] + ",");

					}
					String activityIdList = sb.toString();
					activityIdList = activityIdList.substring(0, activityIdList.length() - 1);

					map.add("activityIdList", activityIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("activityIdList", activityId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteOrgActivities", map, Info.class);

				a = "redirect:/showActivityOrganized";
			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/*********************************
	 * Intelectual Prproperty Right
	 *************************************/

	@RequestMapping(value = "/showIntellectualProperty", method = RequestMethod.GET)
	public ModelAndView showIntellectualProperty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showIntellectualProperty", "showIntellectualProperty", "1", "0",
					"0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/intellectualProperty");
				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("yId", yId);

				IntelPrpoRight[] intelPropArr = rest.postForObject(Constants.url + "/getAllIntelPropRights", map,
						IntelPrpoRight[].class);
				List<IntelPrpoRight> intelRightsList = new ArrayList<>(Arrays.asList(intelPropArr));
				model.addObject("intelRightsList", intelRightsList);

				model.addObject("title", "Intellectual Property Rights and Innovative Practices (Industry - Academic)");

				Info add = AccessControll.checkAccess("showIntellectualProperty", "showIntellectualProperty", "0", "1",
						"0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showIntellectualProperty", "showIntellectualProperty", "0", "0",
						"1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showIntellectualProperty", "showIntellectualProperty", "0",
						"0", "0", "1", newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddIntellectualProperty", method = RequestMethod.GET)
	public ModelAndView showAddIntellectualProperty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_intel_prop");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddIntellectualProperty", "showIntellectualProperty", "0", "1",
					"0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				IntelPrpoRight intelProp = new IntelPrpoRight();
				model.addObject("intelProp", intelProp);

				model.addObject("title",
						"Add Intellectual Property Rights and Innovative Practices (Industry - Academic)");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertPropIntelRight", method = RequestMethod.POST)
	public String addIntellectualProperty(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());

			IntelPrpoRight intelProp = new IntelPrpoRight();

			intelProp.setConId(Integer.parseInt(request.getParameter("intel_id")));
			intelProp.setInstituteId(instituteId);
			intelProp.setYearId(yId);
			intelProp.setConName(request.getParameter("ipr_title"));
			intelProp.setReportLink(request.getParameter("reports_link"));
			intelProp.setEstablishDate(request.getParameter("estb_date"));
			intelProp.setConFromdt(request.getParameter("fromDate"));
			intelProp.setConTodt(request.getParameter("toDate"));
			intelProp.setConPcount(Integer.parseInt(request.getParameter("participant")));
			intelProp.setDelStatus(1);
			intelProp.setIsActive(1);
			intelProp.setMakerUserId(userId);
			intelProp.setMakerDatetime(curDateTime);
			intelProp.setExInt1(0);
			intelProp.setExVar1("NA");

			IntelPrpoRight saveIntelPropRght = rest.postForObject(Constants.url + "addNewIntelPropRight", intelProp,
					IntelPrpoRight.class);
			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/showIntellectualProperty";
			else
				redirect = "redirect:/showAddIntellectualProperty";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return redirect;

	}

	@RequestMapping(value = "/editIntelPropRight/{conId}", method = RequestMethod.GET)
	public ModelAndView editIntelPropRight(@PathVariable("conId") int conId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_intel_prop");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddIntellectualProperty", "showIntellectualProperty", "0", "0",
					"1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				IntelPrpoRight intelProp = new IntelPrpoRight();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("conId", conId);

				IntelPrpoRight right = rest.postForObject(Constants.url + "/getIntelPropRigntById", map,
						IntelPrpoRight.class);
				model.addObject("intelProp", right);

				model.addObject("title",
						"Edit Intellectual Property Rights and Innovative Practices (Industry - Academic)");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteIntelPropRight/{conId}", method = RequestMethod.GET)
	public String deleteIntelPropRight(@PathVariable("conId") int conId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddIntellectualProperty", "showIntellectualProperty", "0", "0",
					"0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("conId", conId);

				IntelPrpoRight delRght = rest.postForObject(Constants.url + "/deleteRightById", map,
						IntelPrpoRight.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showIntellectualProperty";
	}

	@RequestMapping(value = "/delSelintelProp/{intel_rightId}", method = RequestMethod.GET)
	public String delSelintelProp(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int intel_rightId) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("delSelintelProp/{rightId}", "showIntellectualProperty", "0", "0",
					"0", "1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (intel_rightId == 0) {

					System.err.println("Multiple records delete ");
					String[] rightIds = request.getParameterValues("intel_rightId");
					//System.out.println("id are" + rightIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < rightIds.length; i++) {
						sb = sb.append(rightIds[i] + ",");

					}
					String rightIdList = sb.toString();
					rightIdList = rightIdList.substring(0, rightIdList.length() - 1);

					map.add("rightIdList", rightIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("rightIdList", intel_rightId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteIntelRights", map, Info.class);

				a = "redirect:/showIntellectualProperty";
			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/***********************************
	 * Gender Equality Programe
	 *************************************/
	@RequestMapping(value = "/showGenderEquity", method = RequestMethod.GET)
	public ModelAndView showGenderEquity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/genderEquity");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showGenderEquity", "showGenderEquity", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("yId", yId);

				GenderEqalityPrg[] gndrEqltyArr = rest.postForObject(Constants.url + "/getAllGenderEqltyData", map,
						GenderEqalityPrg[].class);
				List<GenderEqalityPrg> gndrEqltyList = new ArrayList<>(Arrays.asList(gndrEqltyArr));
				model.addObject("gndrList", gndrEqltyList);

				model.addObject("title", "Gender Equality Initiatives");
				Info add = AccessControll.checkAccess("showGenderEquity", "showGenderEquity", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showGenderEquity", "showGenderEquity", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showGenderEquity", "showGenderEquity", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddGenderEquity", method = RequestMethod.GET)
	public ModelAndView showAddGenderEquity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_gender_equity");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("showAddGenderEquity", "showGenderEquity", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				GenderEqalityPrg gendrEqualityt = new GenderEqalityPrg();

				model.addObject("title", "Add Gender Equality Initiatives");
				model.addObject("gndrEqual", gendrEqualityt);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertGenderEqualityPrgData", method = RequestMethod.POST)
	public String insertGenderEqualityPrgData(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());

			GenderEqalityPrg gendrEqualityt = new GenderEqalityPrg();

			gendrEqualityt.setGprogId(Integer.parseInt(request.getParameter("gender_eqlity _id")));
			gendrEqualityt.setInstituteId(instituteId);
			gendrEqualityt.setYearId(yId);
			gendrEqualityt.setGprogName(request.getParameter("title"));
			gendrEqualityt.setGprogFromdt(request.getParameter("fromDate"));
			gendrEqualityt.setGprogTodt(request.getParameter("toDate"));
			gendrEqualityt.setGprogPcount(Integer.parseInt(request.getParameter("participant")));
			gendrEqualityt.setDelStatus(1);
			gendrEqualityt.setIsActive(1);
			gendrEqualityt.setMakerUserId(userId);
			gendrEqualityt.setMakerDatetime(curDateTime);
			gendrEqualityt.setExInt1(0);
			gendrEqualityt.setExVar1("NA");

			GenderEqalityPrg gProgEq = rest.postForObject(Constants.url + "/addGendrEqtyPrg", gendrEqualityt,
					GenderEqalityPrg.class);

			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/showGenderEquity";
			else
				redirect = "redirect:/showAddGenderEquity";
		} catch (Exception E) {

		}
		return redirect;

	}

	@RequestMapping(value = "/editGenderEquality/{gndrDataId}", method = RequestMethod.GET)
	public ModelAndView editGenderEquality(@PathVariable("gndrDataId") int gndrDataId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_gender_equity");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("showAddGenderEquity", "showGenderEquity", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				GenderEqalityPrg gendrEqualityt = new GenderEqalityPrg();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("gndrDataId", gndrDataId);

				GenderEqalityPrg gndrEqlity = rest.postForObject(Constants.url + "/editGenderEqualityById", map,
						GenderEqalityPrg.class);
				model.addObject("gndrEqual", gndrEqlity);

				model.addObject("title", "Edit Gender Equality Initiatives");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteGenderEquality/{gndrDataId}", method = RequestMethod.GET)
	public String deleteGenderEquality(@PathVariable("gndrDataId") int gndrDataId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("deleteGenderEquality/{gndrDataId}", "showGenderEquity", "0", "0",
					"0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("gndrDataId", gndrDataId);

				GenderEqalityPrg delGnder = rest.postForObject(Constants.url + "/deleteGenderEqualityById", map,
						GenderEqalityPrg.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showGenderEquity";
	}

	@RequestMapping(value = "/delSelGenderEqty/{gndreqtyId}", method = RequestMethod.GET)
	public String delSelGenderEqty(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int gndreqtyId) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("delSelGenderEqty/{gndreqtyId}", "showGenderEquity", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (gndreqtyId == 0) {

					System.err.println("Multiple records delete ");
					String[] gndreqtyIds = request.getParameterValues("gndreqtyId");
					//System.out.println("id are" + gndreqtyIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < gndreqtyIds.length; i++) {
						sb = sb.append(gndreqtyIds[i] + ",");

					}
					String gndreqtyIdList = sb.toString();
					gndreqtyIdList = gndreqtyIdList.substring(0, gndreqtyIdList.length() - 1);

					map.add("gndreqtyIdList", gndreqtyIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("gndreqtyIdList", gndreqtyId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteGenderEquity", map, Info.class);

				a = "redirect:/showGenderEquity";
			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/*********************************
	 * ICT Enabled Facilities
	 *************************************/
	@RequestMapping(value = "/showICTEnblFaclities", method = RequestMethod.GET)
	public ModelAndView showICTEnblFaclities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/ictEnablFacilities");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showICTEnblFaclities", "showICTEnblFaclities", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				// map.add("yId", yId);

				IctEnabledFacilities[] ictEnbFacArr = rest.postForObject(Constants.url + "/getAllIctEnabledFacilities",
						map, IctEnabledFacilities[].class);
				List<IctEnabledFacilities> ictEnbFacList = new ArrayList<>(Arrays.asList(ictEnbFacArr));
				model.addObject("ictEnbFacList", ictEnbFacList);

				model.addObject("title", "ICT Enabled Facilities");
				Info add = AccessControll.checkAccess("showICTEnblFaclities", "showICTEnblFaclities", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showICTEnblFaclities", "showICTEnblFaclities", "0", "0", "1",
						"0", newModuleList);
				Info delete = AccessControll.checkAccess("showICTEnblFaclities", "showICTEnblFaclities", "0", "0", "0",
						"1", newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addICTEnblFaclities", method = RequestMethod.GET)
	public ModelAndView addICTEnblFaclities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addIctEnblFacilities");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("addICTEnblFaclities", "showICTEnblFaclities", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				IctEnabledFacilities ictEnbFac = new IctEnabledFacilities();

				model.addObject("title", "Add ICT Enabled Facilities");
				model.addObject("ictEnbFac", ictEnbFac);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	//

	@RequestMapping(value = "/insertIctEnblFac", method = RequestMethod.POST)
	public String insertIctEnblFac(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int instituteId = (int) session.getAttribute("instituteId");
		int userId = (int) session.getAttribute("userId");
		int yId = (int) session.getAttribute("acYearId");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String curDateTime = dateFormat.format(cal.getTime());

		try {
			IctEnabledFacilities ictEnbFac = new IctEnabledFacilities();

			ictEnbFac.setIctEnbFacId(Integer.parseInt(request.getParameter("ict_id")));
			ictEnbFac.setNoOfClassroom(Integer.parseInt(request.getParameter("classroom")));
			ictEnbFac.setNoClassromLcd(Integer.parseInt(request.getParameter("lcd")));
			ictEnbFac.setNoClassroomWifi(Integer.parseInt(request.getParameter("wifi")));
			ictEnbFac.setSeminarHall(Integer.parseInt(request.getParameter("seminar_hall")));
			ictEnbFac.setIctSeminarHall(Integer.parseInt(request.getParameter("ict_seminar")));
			ictEnbFac.setInstId(instituteId);
			ictEnbFac.setYearId(yId);
			ictEnbFac.setDelStatus(1);
			ictEnbFac.setIsActive(1);
			ictEnbFac.setMakerUserId(userId);
			ictEnbFac.setMakerEnterDatetime(curDateTime);
			ictEnbFac.setExInt1(Integer.parseInt(request.getParameter("lcd_led")));
			ictEnbFac.setExInt2(0);
			ictEnbFac.setExVar1("NA");
			ictEnbFac.setExVar2("NA");
			//System.out.println(ictEnbFac.toString());

			IctEnabledFacilities ictEnb = rest.postForObject(Constants.url + "/saveIctEnabledFacility", ictEnbFac,
					IctEnabledFacilities.class);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/showICTEnblFaclities";

	}

	@RequestMapping(value = "/editIct/{ictId}", method = RequestMethod.GET)
	public ModelAndView editIct(@PathVariable("ictId") int ictId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addIctEnblFacilities");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editIct/{ictId}", "showICTEnblFaclities", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				GenderEqalityPrg gendrEqualityt = new GenderEqalityPrg();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("ictId", ictId);

				IctEnabledFacilities ictEnbFac = rest.postForObject(Constants.url + "/editIctEnabledFaclityById", map,
						IctEnabledFacilities.class);
				model.addObject("ictEnbFac", ictEnbFac);

				model.addObject("title", "Edit ICT Enabled Facilities");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteIct/{ictId}", method = RequestMethod.GET)
	public String deleteIct(@PathVariable("ictId") int ictId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteIct/{ictId}", "showICTEnblFaclities", "0", "0", "0", "1",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("ictId", ictId);

				IctEnabledFacilities delIct = rest.postForObject(Constants.url + "/deleteIctEnabledFaclityById", map,
						IctEnabledFacilities.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showICTEnblFaclities";
	}

	@RequestMapping(value = "/deleteSelICtEnbFacilities/{exActId}", method = RequestMethod.GET)
	public String deleteSelICtEnbFacilities(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int exActId) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("deleteSelICtEnbFacilities/{exActId}", "showICTEnblFaclities", "0",
					"0", "0", "1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (exActId == 0) {

					System.err.println("Multiple records delete ");
					String[] ictIds = request.getParameterValues("exActId");
					//System.out.println("id are" + ictIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < ictIds.length; i++) {
						sb = sb.append(ictIds[i] + ",");

					}
					String ictIdList = sb.toString();
					ictIdList = ictIdList.substring(0, ictIdList.length() - 1);

					map.add("ictIdList", ictIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("ictIdList", exActId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteIctFacility", map, Info.class);

				a = "redirect:/showICTEnblFaclities";
			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteSelICtEnbFacilities at Institute Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/********************************
	 * Goverment Scholarship
	 ***************************************/

	@RequestMapping(value = "/showGovernmentScholarships", method = RequestMethod.GET)
	public ModelAndView showGovernmentScholarships(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/gvtScholarshiopList");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showGovernmentScholarships", "showGovernmentScholarships", "1", "0",
					"0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("yId", yId);

				GovtScholarships[] govtSchrArr = rest.postForObject(Constants.url + "/getAllGovtScholrSch", map,
						GovtScholarships[].class);
				List<GovtScholarships> govtSchrList = new ArrayList<>(Arrays.asList(govtSchrArr));
				model.addObject("govtSchrList", govtSchrList);
				model.addObject("budRupees", Names.Rupees);

				model.addObject("title", "Government Scholarships");
				Info add = AccessControll.checkAccess("showGovernmentScholarships", "showGovernmentScholarships", "0",
						"1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showGovernmentScholarships", "showGovernmentScholarships", "0",
						"0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showGovernmentScholarships", "showGovernmentScholarships",
						"0", "0", "0", "1", newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addGvtScholarship", method = RequestMethod.GET)
	public ModelAndView addGvtScholarship(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addGovermentScholarship");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("addGvtScholarship", "showGovernmentScholarships", "0", "1", "0",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				GovtScholarships govtScholr = new GovtScholarships();

				model.addObject("title", "Add Government Scholarships");
				model.addObject("govtScholr", govtScholr);
				model.addObject("budRupees", Names.Rupees);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertGoveScheme", method = RequestMethod.POST)
	public String insertGoveScheme(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int instituteId = (int) session.getAttribute("instituteId");
		int userId = (int) session.getAttribute("userId");
		int yId = (int) session.getAttribute("acYearId");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String curDateTime = dateFormat.format(cal.getTime());

		try {
			GovtScholarships govt = new GovtScholarships();

			govt.setGovtScholarId(Integer.parseInt(request.getParameter("govt_scholr_id")));
			govt.setNameOfScheme(request.getParameter("name_scheme"));
			govt.setNoOfStudBenftd(Integer.parseInt(request.getParameter("stud_bnfted")));
			govt.setSchmOffrdBy(request.getParameter("schm_ofrd"));
			govt.setInstId(instituteId);
			govt.setAcYearId(yId);
			govt.setDelStatus(1);
			govt.setIsActive(1);
			govt.setMakerUserId(userId);
			govt.setMakerEntrDatetime(curDateTime);
			if(request.getParameter("amount").isEmpty()) {
				
				govt.setExInt1(0);
			}else {
				govt.setExInt1(Integer.parseInt(request.getParameter("amount")));

			}
			govt.setExInt2(0);
			govt.setExVar1("NA");
			govt.setExVar2("NA");
			//System.out.println(govt.toString());

			GovtScholarships ictEnb = rest.postForObject(Constants.url + "/saveGovtScheme", govt,
					GovtScholarships.class);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/showGovernmentScholarships";

	}

	@RequestMapping(value = "/editGovtScholrSchm/{schmId}", method = RequestMethod.GET)
	public ModelAndView editGovtScholrSchm(@PathVariable("schmId") int schmId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addGovermentScholarship");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editGovtScholrSchm/{schmId}", "showGovernmentScholarships", "0",
					"0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				GovtScholarships gendrEqualityt = new GovtScholarships();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("schmId", schmId);

				GovtScholarships govtScholr = rest.postForObject(Constants.url + "/editGovtScholrScghmById", map,
						GovtScholarships.class);
				model.addObject("govtScholr", govtScholr);

				model.addObject("title", "Edit Government Scholarships");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteGovtScholrSchm/{schmId}", method = RequestMethod.GET)
	public String deleteGovtScholrSchm(@PathVariable("schmId") int schmId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteGovtScholrSchm/{schmId}", "showGovernmentScholarships", "0",
					"0", "0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("schmId", schmId);

				GovtScholarships delIct = rest.postForObject(Constants.url + "/delGovtScholrSchmById", map,
						GovtScholarships.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showGovernmentScholarships";
	}

	@RequestMapping(value = "/deleteSelGovtScholarSchemes/{suppSchmid}", method = RequestMethod.GET)
	public String deleteSelGovtScholarSchemes(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int suppSchmid) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("deleteSelGovtScholarSchemes/{exActId}",
					"showGovernmentScholarships", "0", "0", "0", "1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (suppSchmid == 0) {

					System.err.println("Multiple records delete ");
					String[] govtSchmIds = request.getParameterValues("suppSchmid");
					//System.out.println("id are" + govtSchmIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < govtSchmIds.length; i++) {
						sb = sb.append(govtSchmIds[i] + ",");

					}
					String schmIdList = sb.toString();
					schmIdList = schmIdList.substring(0, schmIdList.length() - 1);

					map.add("schmIdList", schmIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("schmIdList", suppSchmid);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelGovtSchm", map, Info.class);

				a = "redirect:/showGovernmentScholarships";
			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteSelGovtScholarSchemes at Institute Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/********************************
	 * Activities Conducted for Promoting Universal Values
	 ******************************/
	@RequestMapping(value = "/showActCondctPromotUnivrslValus", method = RequestMethod.GET)
	public ModelAndView showActCondctPromotUnivrslValus(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showActCondctPromotUnivrslValus", "showActCondctPromotUnivrslValus",
					"1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/actCondctPromtUnivrslVal");
				
				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("yId", yId);
				
				ActCndctPrmtngUnivrslVal[] actCndctPrgArr = rest.postForObject(Constants.url + "/getAllActivitiesCondctPromtUnivrsalValue", map,
						ActCndctPrmtngUnivrslVal[].class);
				List<ActCndctPrmtngUnivrslVal> actCndctPrgList = new ArrayList<>(Arrays.asList(actCndctPrgArr));
				
				model.addObject("actCndctPrgList", actCndctPrgList);

				model.addObject("title", " Activities Conducted for Promoting Universal Values");
				
				Info add = AccessControll.checkAccess("showActCondctPromotUnivrslValus",
						"showActCondctPromotUnivrslValus", "0", "1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showActCondctPromotUnivrslValus",
						"showActCondctPromotUnivrslValus", "0", "0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showActCondctPromotUnivrslValus",
						"showActCondctPromotUnivrslValus", "0", "0", "0", "1", newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addActCndctPromtUnivrslVal", method = RequestMethod.GET)
	public ModelAndView addActCndctPromtUnivrslVal(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("addActCndctPromtUnivrslVal", "showActCondctPromotUnivrslValus", "0",
					"1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/addCndctUnivrslPrmtVal");
				ActCndctPrmtngUnivrslVal actCndct = new ActCndctPrmtngUnivrslVal();
				model.addObject("actCndct", actCndct);

				model.addObject("title", "Add  Activities Conducted for Promoting Universal Values");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertActConduct", method = RequestMethod.POST)
	public String insertActConduct(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int instituteId = (int) session.getAttribute("instituteId");
		int userId = (int) session.getAttribute("userId");
		int yId = (int) session.getAttribute("acYearId");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String curDateTime = dateFormat.format(cal.getTime());
		ActCndctPrmtngUnivrslVal actCndct = new ActCndctPrmtngUnivrslVal();

		actCndct.setActCndctId(Integer.parseInt(request.getParameter("activityId")));
		actCndct.setTtleProgrmAct(request.getParameter("prg_title"));
		actCndct.setFromDate(request.getParameter("fromDate"));
		actCndct.setToDate(request.getParameter("toDate"));
		actCndct.setNoOfParticipant(Integer.parseInt(request.getParameter("no_participant")));
		actCndct.setInstId(instituteId);
		actCndct.setAcYearId(yId);
		actCndct.setDelStatus(1);
		actCndct.setIsActive(1);
		actCndct.setMakerUserId(userId);
		actCndct.setMakerDatetime(curDateTime);
		actCndct.setExInt1(0);
		actCndct.setExInt2(0);
		actCndct.setExVar1("NA");
		actCndct.setExVar2("NA");
		
		ActCndctPrmtngUnivrslVal saveAct = rest.postForObject(Constants.url+"/saveActivityConductPromtUniVal", actCndct, ActCndctPrmtngUnivrslVal.class);
		
		try {

		} catch (Exception e) {

		}
		return "redirect:/showActCondctPromotUnivrslValus";
	}
	
	@RequestMapping(value = "/editActCndctPromtUnivrsalValu/{actId}", method = RequestMethod.GET)
	public ModelAndView editActCndctPromtUnivrsalValu(@PathVariable("actId") int actId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addCndctUnivrslPrmtVal");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editActCndctPromtUnivrsalValu/{actId}", "showActCondctPromotUnivrslValus", "0",
					"0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				//System.out.println(actId);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("actId", actId);

				ActCndctPrmtngUnivrslVal actCndct = rest.postForObject(Constants.url + "/editActivityCndtPrmtUnivrslValById", map,
						ActCndctPrmtngUnivrslVal.class);
				model.addObject("actCndct", actCndct);

				model.addObject("title", "Edit Activities Conducted for Promoting Universal Values");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;
	}
	
	@RequestMapping(value = "/deleteActCndctPromtUnivrsalValu/{actId}", method = RequestMethod.GET)
	public String deleteActCndctPromtUnivrsalValu(@PathVariable("actId") int actId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteActCndctPromtUnivrsalValu/{actId}", "showActCondctPromotUnivrslValus", "0",
					"0", "0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("actId", actId);

				ActCndctPrmtngUnivrslVal delAct = rest.postForObject(Constants.url + "/delActCndctPrmtUniValById", map,
						ActCndctPrmtngUnivrslVal.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showActCondctPromotUnivrslValus";
	}

	@RequestMapping(value = "/delActivitiesCndctPrmotUnvrslVal/{activityId}", method = RequestMethod.GET)
	public String delActivitiesCndctPrmotUnvrslVal(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int activityId) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("delActivitiesCndctPrmotUnvrslVal/{exActId}",
					"showActCondctPromotUnivrslValus", "0", "0", "0", "1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (activityId == 0) {

					System.err.println("Multiple records delete ");
					String[] activityIds = request.getParameterValues("activityId");
					//System.out.println("id are" + activityIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < activityIds.length; i++) {
						sb = sb.append(activityIds[i] + ",");

					}
					String activityIdlist = sb.toString();
					activityIdlist = activityIdlist.substring(0, activityIdlist.length() - 1);

					map.add("activityIdlist", activityIdlist);
				} else {

					System.err.println("Single Record delete ");
					map.add("activityIdlist", activityId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelActivity", map, Info.class);

				a = "redirect:/showActCondctPromotUnivrslValus";
			}

		} catch (Exception e) {

			System.err.println(" Exception In delActivitiesCndctPrmotUnvrslVal at Institute Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}
	
	/********************************
	 *  Specific Initiatives for Local Advantages & Disadvantages
	 ******************************/
	@RequestMapping(value = "/showSpecficInitiativeforLocAdvDisadv", method = RequestMethod.GET)
	public ModelAndView showSpecficInitiativeforLocAdvDisadv(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showSpecficInitiativeforLocAdvDisadv", "showSpecficInitiativeforLocAdvDisadv",
					"1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/showSpecficInitveLocAdvDisadv");
				
				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("yId", yId);
				
				SpecificLocalAdvntgDisadvntg[] inItArr = rest.postForObject(Constants.url + "/getAllInitiativeAdvntgDisadvntg", map,
						SpecificLocalAdvntgDisadvntg[].class);
				List<SpecificLocalAdvntgDisadvntg> inItList = new ArrayList<>(Arrays.asList(inItArr));
				
				model.addObject("inItList", inItList);

				model.addObject("title", "Specific Initiatives for Local Advantages & Disadvantages");
				
				Info add = AccessControll.checkAccess("showSpecficInitiativeforLocAdvDisadv",
						"showSpecficInitiativeforLocAdvDisadv", "0", "1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showSpecficInitiativeforLocAdvDisadv",
						"showSpecficInitiativeforLocAdvDisadv", "0", "0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showSpecficInitiativeforLocAdvDisadv",
						"showSpecficInitiativeforLocAdvDisadv", "0", "0", "0", "1", newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/addSpecificInitiveLocAdvDisadv", method = RequestMethod.GET)
	public ModelAndView addSpecificInitiveLocAdvDisadv(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("addSpecificInitiveLocAdvDisadv", "showSpecficInitiativeforLocAdvDisadv", "0",
					"1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/addSpecficInitveLocAdvDisadv");
				SpecificLocalAdvntgDisadvntg specifyInit = new SpecificLocalAdvntgDisadvntg();

				model.addObject("specifyInit", specifyInit);

				model.addObject("title", "Add Specific Initiatives for Local Advantages & Disadvantages");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/insertInitiativeAdvDisadv", method = RequestMethod.POST)
	public String insertInitiativeAdvDisadv(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		int instituteId = (int) session.getAttribute("instituteId");
		int userId = (int) session.getAttribute("userId");
		int yId = (int) session.getAttribute("acYearId");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String curDateTime = dateFormat.format(cal.getTime());
		
		SpecificLocalAdvntgDisadvntg specify = new SpecificLocalAdvntgDisadvntg();

		specify.setSpciAdvId(Integer.parseInt(request.getParameter("initiative_id")));
		specify.setNameOfInitiatives(request.getParameter("name_inititatives"));
		specify.setIssueAddress(request.getParameter("issue_address"));
		specify.setFromDate(request.getParameter("fromDate"));
		specify.setToDate(request.getParameter("toDate"));
		specify.setNoStudPart(Integer.parseInt(request.getParameter("no_stud_participate")));
		specify.setInstId(instituteId);
		specify.setAcYearId(yId);
		specify.setDelStatus(1);
		specify.setIsActive(1);
		specify.setMakerUserId(userId);
		specify.setMakerDatetime(curDateTime);
		specify.setExInt1(0);
		specify.setExInt2(0);
		specify.setExVar1("NA");
		specify.setExVar2("NA");
		
		SpecificLocalAdvntgDisadvntg saveInit = rest.postForObject(Constants.url+"/saveInitiativeAdvDisadv", specify, SpecificLocalAdvntgDisadvntg.class);
		
		try {

		} catch (Exception e) {

		}
		return "redirect:/showSpecficInitiativeforLocAdvDisadv";
	}
	
	
	@RequestMapping(value = "/editInitiative/{inItId}", method = RequestMethod.GET)
	public ModelAndView editInitiative(@PathVariable("inItId") int inItId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addSpecficInitveLocAdvDisadv");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editInitiative/{inItId}", "showSpecficInitiativeforLocAdvDisadv", "0",
					"0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				//System.out.println(inItId);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("inItId", inItId);

				SpecificLocalAdvntgDisadvntg specifyInit = rest.postForObject(Constants.url + "/editInitiativeById", map,
						SpecificLocalAdvntgDisadvntg.class);
				model.addObject("specifyInit", specifyInit);

				model.addObject("title", "Edit Specific Initiatives for Local Advantages & Disadvantages");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;
	}
	
	@RequestMapping(value = "/deleteInitiative/{inItId}", method = RequestMethod.GET)
	public String deleteInitiative(@PathVariable("inItId") int inItId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteInitiative/{inItId}", "showSpecficInitiativeforLocAdvDisadv", "0",
					"0", "0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("inItId", inItId);

				ActCndctPrmtngUnivrslVal delAct = rest.postForObject(Constants.url + "/delInitiativeAdvDisadvById", map,
						ActCndctPrmtngUnivrslVal.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showSpecficInitiativeforLocAdvDisadv";
	}
	
	@RequestMapping(value = "/delSelcInitiativesAdvDisadv/{initAdvId}", method = RequestMethod.GET)
	public String delSelcInitiativesAdvDisadv(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int initAdvId) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("delActivitiesCndctPrmotUnvrslVal/{exActId}",
					"showActCondctPromotUnivrslValus", "0", "0", "0", "1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (initAdvId == 0) {

					System.err.println("Multiple records delete ");
					String[] initAdvIds = request.getParameterValues("initAdvId");
					//System.out.println("id are" + initAdvIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < initAdvIds.length; i++) {
						sb = sb.append(initAdvIds[i] + ",");

					}
					String initAdvIdList = sb.toString();
					initAdvIdList = initAdvIdList.substring(0, initAdvIdList.length() - 1);

					map.add("initAdvIdList", initAdvIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("initAdvIdList", initAdvId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelInitives", map, Info.class);

				a = "redirect:/showSpecficInitiativeforLocAdvDisadv";
			}

		} catch (Exception e) {

			System.err.println(" Exception In delSelcInitiativesAdvDisadv at Institute Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}
}
