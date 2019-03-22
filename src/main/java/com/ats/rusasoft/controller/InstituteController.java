	package com.ats.rusasoft.controller;
	
	import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.InstituteActivity;
import com.ats.rusasoft.model.InstituteSupport;
	
	@Controller
	@Scope("session")
	public class InstituteController {
		
		RestTemplate rest = new RestTemplate();
		
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
			  
			  model.addObject("instSpprt", instSpprt);
			  model.addObject("title", "Add Institute Schemes");
		  
		  } catch (Exception e) {
		  
		  e.printStackTrace();
		  
		  }
		  
		  return model;
		  
		 }
		 
	
		  @RequestMapping(value = "/insertInstituteSupport", method = RequestMethod.POST)
		  public String insertInstituteSupport(HttpServletRequest request, HttpServletResponse response) {
		  
			  HttpSession session = request.getSession();

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int yId = (int) session.getAttribute("acYearId");
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String curDateTime = dateFormat.format(cal.getTime());
				
			  
		 
		  try {
			
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
		  
		  } catch (Exception e) {
		  
		  e.printStackTrace();
		  
		  }
		  
		  return "redirect:/showInstituteSupport";
		  
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
		   
   /******************************************Institute Activity******************************************/
		   
			@RequestMapping(value = "/showActivityOrganized", method = RequestMethod.GET)
			public ModelAndView showActivityOrganized(HttpServletRequest request, HttpServletResponse response) {

				ModelAndView model = new ModelAndView("instituteInfo/IQAC/activityOrganized");
				try {

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
			  
				  HttpSession session = request.getSession();

					int instituteId = (int) session.getAttribute("instituteId");
					int userId = (int) session.getAttribute("userId");
					int yId = (int) session.getAttribute("acYearId");
					
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					String curDateTime = dateFormat.format(cal.getTime());
					
				  
			 
			  try { 
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
				  } catch (Exception e) {
			  

					e.printStackTrace();

				}
			  return "redirect:/showActivityOrganized";
		}
}
