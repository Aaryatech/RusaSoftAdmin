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
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.GenderEqalityPrg;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.InstituteActivity;
import com.ats.rusasoft.model.InstituteSupport;
import com.ats.rusasoft.model.IntelPrpoRight;
import com.ats.rusasoft.model.StaffList;
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
		String redirect=null; 
		/*********************************************Institute Support**********************************************/
	
		@RequestMapping(value = "/showInstituteSupport", method = RequestMethod.GET)
		public ModelAndView showInstituteSupport(HttpServletRequest request, HttpServletResponse response) {
	
			ModelAndView model = new ModelAndView("instituteInfo/IQAC/instituteSupport");
			try {
				
			
				model.addObject("title", "Institute Schemes List");
				model.addObject("title1", "Institute Support Financially by Awarding Scholarship/Freeships like schemes other than Government Schemes ");
	

				  HttpSession session = request.getSession();

					int instituteId = (int) session.getAttribute("instituteId");
					int userId = (int) session.getAttribute("userId");
					int yId = (int) session.getAttribute("acYearId");
					
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("instituteId", instituteId);
					map.add("yId", yId);
					
					InstituteSupport[]  instSuprtArr = rest.postForObject(Constants.url+"/getSchemesByIds", map, InstituteSupport[].class);
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
				
			} catch (Exception e) {
	
				e.printStackTrace();
	
			}
	
			return model;
	
		}
	
		
		  @RequestMapping(value = "/showAddInstituteSupport", method = RequestMethod.GET)
		  public ModelAndView showAddInstituteSupport(HttpServletRequest request, HttpServletResponse response) {
		  
		  ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_institute_support"); 
		  try {
		  
			  InstituteSupport instSpprt = new InstituteSupport();
			  MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId",
						map, AcademicYear[].class);
				
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);
			  
			  model.addObject("instSpprt", instSpprt);
			  model.addObject("title", "Add Institute Schemes");
		  
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
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String curDateTime = dateFormat.format(cal.getTime());
				
			
			  InstituteSupport instSpprt = new InstituteSupport();
			 
			  instSpprt.setInstSchemeId(Integer.parseInt(request.getParameter("inst_scheme_id")));
			  instSpprt.setInstituteId(instituteId);
			  instSpprt.setYearId(yId);
			  instSpprt.setInstSchemeName(request.getParameter("inst_scheme_name"));
			  instSpprt.setInstStudentsBenefited(request.getParameter("inst_students_benefited"));
			  instSpprt.setInstSchmeOfferedby(request.getParameter("inst_schme_offeredby"));
			  instSpprt.setDelStatus(1);
			  instSpprt.setIsActive(1);
			  instSpprt.setMakerUserId(userId);
			  instSpprt.setMakerDatetime(curDateTime);
			  instSpprt.setExInt1(0);
			  instSpprt.setExInt2(0);
			  instSpprt.setExVar1("NA");
			  instSpprt.setExVar2("NA");
			 			  
			  InstituteSupport saveInstSupprt = rest.postForObject(Constants.url+"/addInstSupprt", instSpprt, InstituteSupport.class);

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
		  public ModelAndView editInstituteScheme(@PathVariable("schmId") int schmId, HttpServletRequest request, HttpServletResponse response) {
		  
		  ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_institute_support"); 
		  try {
		  
			  	InstituteSupport instSpprt = new InstituteSupport();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			
				map.add("schmId", schmId);
				
				InstituteSupport  suprtSchm = rest.postForObject(Constants.url+"/getSuprtSchemeBySchmId", map, InstituteSupport.class);
				model.addObject("instSpprt", suprtSchm);
			  
			  
			  model.addObject("title", "Edit Institute Schemes");
		  
		  } catch (Exception e) {
		  
		  e.printStackTrace();
		  
		  }
		  
		  return model;
		  
		 }
		  
		   @RequestMapping(value = "/deleteInstituteScheme/{schmId}", method = RequestMethod.GET)
		  public String deleteInstituteScheme(@PathVariable("schmId") int schmId, HttpServletRequest request, HttpServletResponse response) {
			   
			   try {
			   MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				
				map.add("schmId", schmId);
				
				InstituteSupport  delSchm = rest.postForObject(Constants.url+"/deleteSuprtSchemeBySchmId", map, InstituteSupport.class);
			 
			   }catch(Exception e) {
				   e.printStackTrace();
			   }
				
				return "redirect:/showInstituteSupport";
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
						
						InstituteSupport[]  instSuprtArr = rest.postForObject(Constants.url+"/getSchemesByIds", map, InstituteSupport[].class);
						List<InstituteSupport> instSuprtlist = new ArrayList<>(Arrays.asList(instSuprtArr));
					
					
						BufferedOutputStream outStream = null;
						System.out.println("Inside Pdf showCustomerwisePdf");
						Document document = new Document(PageSize.A4);

						DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						System.out.println("time in Gen Bill PDF ==" + dateFormat.format(cal.getTime()));
						String FILE_PATH = Constants.REPORT_SAVE;
						File file = new File(FILE_PATH);

						PdfWriter writer = null;

						FileOutputStream out = new FileOutputStream(FILE_PATH);
						try
						{
							writer = PdfWriter.getInstance(document, out);
						} catch (DocumentException e) {

							e.printStackTrace();
						}

						PdfPTable table = new PdfPTable(4);
						try {
							System.out.println("Inside PDF Table try");
							table.setWidthPercentage(100);
							table.setWidths(new float[] { 2.4f, 3.4f, 3.2f, 3.2f});
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
							//Paragraph p1 = new Paragraph("From Date:" + fromDate + "  To Date:" + toDate, headFont);
							//p1.setAlignment(Element.ALIGN_CENTER);
							//document.add(p1);
							document.add(new Paragraph("\n"));
							document.add(table);

							int totalPages = writer.getPageNumber();

							System.out.println("Page no " + totalPages);

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
									System.out.println("Excep in Opening a Pdf File");
									e.printStackTrace();
								}
							}

						} catch (DocumentException ex) {

							System.out.println("Pdf Generation Error: " + ex.getMessage());

							ex.printStackTrace();

						}
				} catch (Exception e) {
		
					e.printStackTrace();
		
				}
		
			
		
			}
		   
   /******************************************Institute Activity******************************************/
		   
			@RequestMapping(value = "/showActivityOrganized", method = RequestMethod.GET)
			public ModelAndView showActivityOrganized(HttpServletRequest request, HttpServletResponse response) {

				ModelAndView model = new ModelAndView("instituteInfo/IQAC/activityOrganized");
				try {
					HttpSession session = request.getSession();

					int instituteId = (int) session.getAttribute("instituteId");
					int yId = (int) session.getAttribute("acYearId");
					
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("instituteId", instituteId);
					map.add("yId", yId);
					
					InstituteActivity[] instActArr = rest.postForObject(Constants.url+"/getAllInstituteActities", map, InstituteActivity[].class);
					List<InstituteActivity> activityList = new ArrayList<>(Arrays.asList(instActArr));
					model.addObject("instActList", activityList);
					
					model.addObject("title", "Activities Organized");

				} catch (Exception e) {

					e.printStackTrace();

				}

				return model;

			}
		   
		   
		   @RequestMapping(value = "/showAddActivityOrganized", method = RequestMethod.GET)
			public ModelAndView showAddActivityOrganized(HttpServletRequest request, HttpServletResponse response) {

				ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_activity_organized");
				try {
					InstituteActivity instAct = new InstituteActivity();
					model.addObject("instAct", instAct);
					model.addObject("title", "Add Activities Organized");

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
				  		instAct.setInstActivityParticipation(request.getParameter("inst_activity_participation"));
				  		instAct.setDelStatus(1);
				  		instAct.setIsActive(1);
				  		instAct.setMakerUserId(userId);
				  		instAct.setMakerDatetime(curDateTime);
				  		instAct.setExInt1(0);
				  		instAct.setExInt2(0);
				  		instAct.setExVar1("NA");
				  		instAct.setExVar2("NA");
				  		System.out.println(instAct.toString());
				  		InstituteActivity saveinstActvt = rest.postForObject(Constants.url+"/addNewInstituteActity", instAct, InstituteActivity.class);
				 
				  		int isView = Integer.parseInt(request.getParameter("is_view"));
						if (isView == 1)
							redirect = "redirect:/showAddActivityOrganized";
						else
							redirect = "redirect:/showActivityOrganized";
			 
			 } catch (Exception e) {
			  

					e.printStackTrace();

				}
			  return redirect;
		}
			  
			  
			  @RequestMapping(value = "/editActivity/{instActvId}", method = RequestMethod.GET)
			  public ModelAndView editActivity(@PathVariable("instActvId") int instActvId, HttpServletRequest request, HttpServletResponse response) {
			  
					ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_activity_organized");
			  try {
			  
				  InstituteActivity instAct = new InstituteActivity();

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				
					map.add("instActvId", instActvId);
					
					InstituteActivity  activity = rest.postForObject(Constants.url+"/getInstActivityById", map, InstituteActivity.class);
					model.addObject("instAct", activity);
				  
				  
				  model.addObject("title", "Edit Activities Organized");
			  
			  } catch (Exception e) {
			  
			  e.printStackTrace();
			  
			  }
			  
			  return model;
			  
			 }
			  
		  @RequestMapping(value = "/deleteActivity/{instActvId}", method = RequestMethod.GET)
			  public String deleteActivity(@PathVariable("instActvId") int instActvId, HttpServletRequest request, HttpServletResponse response) {
				   
			 try {
				   MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("instActvId", instActvId);
					
					InstituteActivity  delActiv = rest.postForObject(Constants.url+"/deleteActivId", map, InstituteActivity.class);
				 
				   }catch(Exception e) {
					   e.printStackTrace();
				   }
					
					return "redirect:/showActivityOrganized";
		  }
	
/********************************Intelectual Prproperty Right****************************************/
		
		  @RequestMapping(value = "/showIntellectualProperty", method = RequestMethod.GET)
			public ModelAndView showIntellectualProperty(HttpServletRequest request, HttpServletResponse response) {

				ModelAndView model = new ModelAndView("instituteInfo/IQAC/intellectualProperty");
				try {
					
					HttpSession session = request.getSession();

					int instituteId = (int) session.getAttribute("instituteId");
					int yId = (int) session.getAttribute("acYearId");
					
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("instituteId", instituteId);
					map.add("yId", yId);
					
					IntelPrpoRight[] intelPropArr = rest.postForObject(Constants.url+"/getAllIntelPropRights", map, IntelPrpoRight[].class);
					List<IntelPrpoRight> intelRightsList = new ArrayList<>(Arrays.asList(intelPropArr));
					model.addObject("intelRightsList", intelRightsList);
					
					model.addObject("title", "Intellectual Property Rights And Industries");

				} catch (Exception e) {

					e.printStackTrace();

				}

				return model;

			}

		  
		  
		  @RequestMapping(value = "/showAddIntellectualProperty", method = RequestMethod.GET)
			public ModelAndView showAddIntellectualProperty(HttpServletRequest request, HttpServletResponse response) {

				ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_intel_prop");
				try {
					IntelPrpoRight intelProp = new IntelPrpoRight();
					model.addObject("intelProp", intelProp);

					model.addObject("title", "Add Intellectual Property Rights And Industries");

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
				  intelProp.setConFromdt(request.getParameter("fromDate"));
				  intelProp.setConTodt(request.getParameter("toDate"));
				  intelProp.setConPcount(request.getParameter("participant"));
				  intelProp.setDelStatus(1);
				  intelProp.setIsActive(1);
				  intelProp.setMakerUserId(userId);
				  intelProp.setMakerDatetime(curDateTime);
				  intelProp.setExInt1(0);
				  intelProp.setExVar1("NA");
				  
				  IntelPrpoRight saveIntelPropRght = rest.postForObject(Constants.url+"/andNewIntelPropRight", intelProp, IntelPrpoRight.class);
				  int isView = Integer.parseInt(request.getParameter("is_view"));
					if (isView == 1)
						redirect = "redirect:/showIntellectualProperty";
					else
						redirect = "redirect:/showAddIntellectualProperty";
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
			  
				return redirect;
			  
		  }
		  
		  
		  @RequestMapping(value = "/editIntelPropRight/{conId}", method = RequestMethod.GET)
		  public ModelAndView editIntelPropRight(@PathVariable("conId") int conId, HttpServletRequest request, HttpServletResponse response) {
		  
				ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_intel_prop");
		  try {
		  
			  IntelPrpoRight intelProp = new IntelPrpoRight();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			
				map.add("conId", conId);
				
				IntelPrpoRight  right = rest.postForObject(Constants.url+"/getIntelPropRigntById", map, IntelPrpoRight.class);
				model.addObject("intelProp", right);
			  
			  
			  model.addObject("title", "Edit Intellectual Property Rights And Industries");
		  
		  } catch (Exception e) {
		  
		  e.printStackTrace();
		  
		  }
		  
		  return model;
		  
		 }
		  
		  @RequestMapping(value = "/deleteIntelPropRight/{conId}", method = RequestMethod.GET)
		  public String deleteIntelPropRight(@PathVariable("conId") int conId, HttpServletRequest request, HttpServletResponse response) {
			   
		 try {
			   MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("conId", conId);
				
				IntelPrpoRight  delRght = rest.postForObject(Constants.url+"/deleteRightById", map, IntelPrpoRight.class);
			 
			   }catch(Exception e) {
				   e.printStackTrace();
			   }
				
				return "redirect:/showIntellectualProperty";
	}
		 
	/************************************Gender Equality Programe******************************************/
		  @RequestMapping(value = "/showGenderEquity", method = RequestMethod.GET)
			public ModelAndView showGenderEquity(HttpServletRequest request, HttpServletResponse response) {

				ModelAndView model = new ModelAndView("instituteInfo/IQAC/genderEquity");
				try {

					HttpSession session = request.getSession();

					int instituteId = (int) session.getAttribute("instituteId");
					int yId = (int) session.getAttribute("acYearId");
					
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("instituteId", instituteId);
					map.add("yId", yId);
					
					GenderEqalityPrg[] gndrEqltyArr = rest.postForObject(Constants.url+"/getAllGenderEqltyData", map, GenderEqalityPrg[].class);
					List<GenderEqalityPrg> gndrEqltyList = new ArrayList<>(Arrays.asList(gndrEqltyArr));
					model.addObject("gndrList", gndrEqltyList);
					
					model.addObject("title", "Gender Equality Programme");

				} catch (Exception e) {

					e.printStackTrace();

				}

				return model;

			}
		  
		  
			@RequestMapping(value = "/showAddGenderEquity", method = RequestMethod.GET)
			public ModelAndView showAddGenderEquity(HttpServletRequest request, HttpServletResponse response) {

				ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_gender_equity");
				try {
					GenderEqalityPrg gendrEqualityt = new GenderEqalityPrg();
					
					model.addObject("title", "Gender Equality Programme");
					model.addObject("gndrEqual", gendrEqualityt);

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
						gendrEqualityt.setGprogPcount(request.getParameter("participant"));
						gendrEqualityt.setDelStatus(1);
						gendrEqualityt.setIsActive(1);
						gendrEqualityt.setMakerUserId(userId);
						gendrEqualityt.setMakerDatetime(curDateTime);
						gendrEqualityt.setExInt1(0);
						gendrEqualityt.setExVar1("NA");
					
						GenderEqalityPrg gProgEq = rest.postForObject(Constants.url+"/addGendrEqtyPrg", gendrEqualityt, GenderEqalityPrg.class);
						
						 int isView = Integer.parseInt(request.getParameter("is_view"));
							if (isView == 1)
								redirect = "redirect:/showGenderEquity";
							else
								redirect = "redirect:/showAddGenderEquity";
				}catch(Exception E) {
					
				}
				return redirect;
			
			}
			
			
			@RequestMapping(value = "/editGenderEquality/{gndrDataId}", method = RequestMethod.GET)
			  public ModelAndView editGenderEquality(@PathVariable("gndrDataId") int gndrDataId, HttpServletRequest request, HttpServletResponse response) {
			  
				ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_gender_equity");
			  try {
			  
				  GenderEqalityPrg gendrEqualityt = new GenderEqalityPrg();

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				
					map.add("gndrDataId", gndrDataId);
					
					GenderEqalityPrg  gndrEqlity = rest.postForObject(Constants.url+"/editGenderEqualityById", map, GenderEqalityPrg.class);
					model.addObject("gndrEqual", gndrEqlity);
				  
				  
				  model.addObject("title", "Edit Gender Equality Programme");
			  
			  } catch (Exception e) {
			  
			  e.printStackTrace();
			  
			  }
			  
			  return model;
			  
			 }
			
}
