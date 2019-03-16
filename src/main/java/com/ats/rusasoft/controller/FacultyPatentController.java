package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.FacultyAward;
import com.ats.rusasoft.model.FacultyPatent;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.MIqac;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class FacultyPatentController {

	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/showPatentDetailsList", method = RequestMethod.GET)
	public ModelAndView showPatentDetailsList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showIqacList", "showIqacList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				List<FacultyPatent> facultyPatentList = rest.getForObject(Constants.url + "/getAllFacultyPatent",
						List.class);
				System.out.println("faculty Patent List :" + facultyPatentList);

				model = new ModelAndView("FacultyDetails/patentDetailList");

				model.addObject("title", "Patent Details List");

				model.addObject("facultyPatentList", facultyPatentList);
				Info add = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPatentDetails", method = RequestMethod.GET)
	public ModelAndView showPatentDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("iqacRegistration", "showIqacList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("FacultyDetails/patentDetails");

				model.addObject("title", "Patent Details Form");
			}
		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;
	}

	@RequestMapping(value = "/insertPatentDetail", method = RequestMethod.POST)
	public String insertPatentDetail(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		int userId = (int) session.getAttribute("userId");
		int acYearId = (int) session.getAttribute("acYearId");

		int patentId = 0;
		try {

			patentId = Integer.parseInt(request.getParameter("patentId"));

		} catch (Exception e) {
			patentId = 0;
			System.err.println("exception In iqacNewRegistration at showIqacList Contr" + e.getMessage());
			e.printStackTrace();

		}

		System.out.println("patentId:" + patentId);
		String patentNo = request.getParameter("patentNo");
		System.out.println("patentNo:" + patentNo);
		String parentTitle = request.getParameter("parentTitle");
		String fillingDate = request.getParameter("fillingDate");
		String guideName = request.getParameter("guideName");
		String pubDate = request.getParameter("pubDate");

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();

		String curDateTime = dateFormat.format(cal.getTime());
		DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

		String curDate = dateFormatStr.format(new Date());

		FacultyPatent faculty = new FacultyPatent();
		if (patentId == 0) {

		faculty.setPatentId(patentId);
		faculty.setFacultyId(userId);
		faculty.setPatentTitle(parentTitle);
		faculty.setDelStatus(1);
		faculty.setIsActive(1);
		faculty.setYearId(acYearId);
		faculty.setPatentFileNo(patentNo);
		faculty.setPatentFilingDate(DateConvertor.convertToYMD(fillingDate));
		faculty.setPatentGuideName(guideName);
		faculty.setPatentPubDate(DateConvertor.convertToYMD(pubDate));
		faculty.setMakerUserId(userId);
		faculty.setMakerEnterDatetime(curDateTime);
		
		FacultyPatent patent = rest.postForObject(Constants.url + "/saveFacultyPatent", faculty, FacultyPatent.class);

		}
		else
		{
			//faculty.setPatentId(patentId);
			faculty.setFacultyId(userId);
			faculty.setPatentTitle(parentTitle);
			faculty.setDelStatus(1);
			faculty.setIsActive(1);
			faculty.setYearId(acYearId);
			faculty.setPatentFileNo(patentNo);
			faculty.setPatentFilingDate(DateConvertor.convertToYMD(fillingDate));
			faculty.setPatentGuideName(guideName);
			faculty.setPatentPubDate(DateConvertor.convertToYMD(pubDate));
			faculty.setMakerUserId(userId);
			faculty.setMakerEnterDatetime(curDateTime);
			
			FacultyPatent patent = rest.postForObject(Constants.url + "/saveFacultyPatent", faculty, FacultyPatent.class);

		}

	
		return "redirect:/showPatentDetails";

	}
	
	@RequestMapping(value = "/editPatent/{patentId}", method = RequestMethod.GET)
	public ModelAndView editPatent(@PathVariable("patentId") int patentId, HttpServletRequest request) {
		
	System.out.println("Id:"+patentId);

		ModelAndView model = null;
		 HttpSession session = request.getSession();
			List<ModuleJson> newModuleList =(List<ModuleJson>)session.getAttribute("newModuleList"); 
		
		try {
			Info view = AccessControll.checkAccess("iqacRegistration", "showIqacList", "0", "0", "1", "0", newModuleList);
			
			if(view.isError()==true) {
						
						model = new ModelAndView("accessDenied");
										
					}
			else {
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("patentId", patentId);
			
			model = new ModelAndView("master/iqacRegistration");
			List<FacultyPatent> facultyPatentList = rest.getForObject(Constants.url + "/getAllFacultyPatent",List.class);
			System.out.println("faculty Patent List :" + facultyPatentList);

			model = new ModelAndView("FacultyDetails/patentDetails");

			
			FacultyPatent patent = rest.postForObject(Constants.url+"/getFacultyPatent", map, FacultyPatent.class);
			System.out.println("patent"+patent);
			
			model.addObject("patent", patent);
			}
		} catch (Exception e) {

			System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}
	
	
	  @RequestMapping(value = "/deleteFacultyPatent/{patentId}", method = RequestMethod.GET)
	  public String deleteFacultyPatent(@PathVariable("patentId") int patentId, HttpServletRequest request)
	  {		  
		  String a=null; HttpSession session = request.getSession(); 
		  List<ModuleJson> newModuleList =(List<ModuleJson>)session.getAttribute("newModuleList"); 
		  Info view = AccessControll.checkAccess("iqacRegistration", "showIqacList", "0", "0", "0","1", newModuleList); 
		  
		  if(view.isError()==true)
		  {	  
			  	a="redirect:/accessDenied";			  	
	      }
		  else 
		  { 
			  Info inf = new Info(); 
			  System.out.println("patentId:"+patentId);
	  
			  MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			  map.add("patentId", patentId); 
			  Info miqc = rest.postForObject(Constants.url+"/deleteIqacById", map, Info.class);
			  a="redirect:/showPatentDetailsList"; 
		  } 
		  
		  return a;
	  
	  }
	  
	  @RequestMapping(value = "/showAwardDetails", method = RequestMethod.GET)
	  public ModelAndView showAwardDetails(HttpServletRequest request,
	  HttpServletResponse response) {
	  
	  ModelAndView model = null; try {
	  
	  model = new ModelAndView("FacultyDetails/awardDetails");
	  
	  model.addObject("title", "Award Details Form");
	  
	  } catch (Exception e) {
	  
	  System.err.println("exception In showAwardDetails at Library Contr" +
	  e.getMessage());
	  
	  e.printStackTrace();
	  
	  }
	  
	  return model;
	  
	  }
	  
	  @RequestMapping(value = "/showAwardDetailsList", method = RequestMethod.GET)
	  public ModelAndView showAwardDetailsList(HttpServletRequest request,
	  HttpServletResponse response) {
	  
	  ModelAndView model = null; 
	  
	 
	  HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showIqacList", "showIqacList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				List<FacultyAward> facultyAwardList = rest.getForObject(Constants.url + "/getAllFacultyAward",
						List.class);
				System.out.println("faculty Patent List :" + facultyAwardList);

				 model = new ModelAndView("FacultyDetails/awardDetailsList");

				model.addObject("title", "Award Details List");

				model.addObject("facultyAwardList", facultyAwardList);
				Info add = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			System.err.println("exception In facultyAwardList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;
	  
	  }
	  
	  @RequestMapping(value = "/insertAwardDetail", method = RequestMethod.POST)
		public String insertAwardDetail(HttpServletRequest request, HttpServletResponse response) {

			HttpSession session = request.getSession();

			int userId = (int) session.getAttribute("userId");
			int acYearId = (int) session.getAttribute("acYearId");

			int awardId = 0;
			try {

				awardId = Integer.parseInt(request.getParameter("awardId"));

			} catch (Exception e) {
				awardId = 0;
				System.err.println("exception In iqacNewRegistration at showIqacList Contr" + e.getMessage());
				e.printStackTrace();

			}

			System.out.println("awardId:" + awardId);
		
			String name = request.getParameter("name");
			String agency = request.getParameter("agency");
			String nature = request.getParameter("nature");
			String date = request.getParameter("date");
			int validity = Integer.parseInt(request.getParameter("validity"));

			String fromDate = request.getParameter("fromDate");

			String fromTo = request.getParameter("fromTo");


			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String curDateTime = dateFormat.format(cal.getTime());
			DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

			String curDate = dateFormatStr.format(new Date());

			FacultyAward faculty = new FacultyAward();
			if (awardId == 0) {

			faculty.setAwardId(awardId);
			faculty.setFacultyId(userId);
			faculty.setAwardName(name);
			faculty.setDelStatus(1);
			faculty.setIsActive(1);
			faculty.setYearId(acYearId);
			faculty.setAwardAuthority(agency);
			faculty.setAwardDate(DateConvertor.convertToYMD(date));
			faculty.setAwardNature(nature);
			faculty.setAwardValidity(validity);
			if(validity==1)
			{
				faculty.setAwardValidityFrom(DateConvertor.convertToYMD(fromDate));
				faculty.setAwardValidityTo(DateConvertor.convertToYMD(fromTo));
			}	
			else
			{
				faculty.setAwardValidityFrom(curDate);
				faculty.setAwardValidityTo(curDate);
			}
		
			faculty.setMakerUserId(userId);
			faculty.setMakerEnterDatetime(curDateTime);
			
			FacultyAward patent = rest.postForObject(Constants.url + "/saveFacultyAward", faculty, FacultyAward.class);

			}
			else
			{
				faculty.setAwardId(awardId);
				faculty.setFacultyId(userId);
				faculty.setAwardName(name);
				faculty.setDelStatus(1);
				faculty.setIsActive(1);
				faculty.setYearId(acYearId);
				faculty.setAwardAuthority(agency);
				faculty.setAwardDate(DateConvertor.convertToYMD(date));
				faculty.setAwardNature(nature);
				faculty.setAwardValidity(validity);
				if(validity==0)
				{
					faculty.setAwardValidityFrom(DateConvertor.convertToYMD(fromDate));
					faculty.setAwardValidityFrom(DateConvertor.convertToYMD(fromTo));
				}		
			
				faculty.setMakerUserId(userId);
				faculty.setMakerEnterDatetime(curDateTime);
				
				FacultyAward patent = rest.postForObject(Constants.url + "/saveFacultyAward", faculty, FacultyAward.class);

			}

		
			return "redirect:/showAwardDetails";

		}
		
		@RequestMapping(value = "/editAward/{awardId}", method = RequestMethod.GET)
		public ModelAndView editAward(@PathVariable("awardId") int awardId, HttpServletRequest request) {
			
		System.out.println("Id:"+awardId);

			ModelAndView model = null;
			 HttpSession session = request.getSession();
				List<ModuleJson> newModuleList =(List<ModuleJson>)session.getAttribute("newModuleList"); 
			
			try {
				Info view = AccessControll.checkAccess("iqacRegistration", "showIqacList", "0", "0", "1", "0", newModuleList);
				
				if(view.isError()==true) {
							
							model = new ModelAndView("accessDenied");
											
						}
				else {
				
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("awardId", awardId);
				
				model = new ModelAndView("master/iqacRegistration");
				List<FacultyPatent> facultyPatentList = rest.getForObject(Constants.url + "/getAllFacultyAward",List.class);
				System.out.println("faculty Patent List :" + facultyPatentList);

				model = new ModelAndView("FacultyDetails/awardDetails");

				
				FacultyPatent award = rest.postForObject(Constants.url+"/getFacultyAwardById", map, FacultyPatent.class);
				System.out.println("award"+award);
				
				model.addObject("award", award);
				}
			} catch (Exception e) {

				System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

				e.printStackTrace();

			}
			return model;
		}
		
		
		  @RequestMapping(value = "/deleteFacultyAward/{awardId}", method = RequestMethod.GET)
		  public String deleteFacultyAward(@PathVariable("awardId") int awardId, HttpServletRequest request)
		  {		  
			  String a=null; HttpSession session = request.getSession(); 
			  List<ModuleJson> newModuleList =(List<ModuleJson>)session.getAttribute("newModuleList"); 
			  Info view = AccessControll.checkAccess("iqacRegistration", "showIqacList", "0", "0", "0","1", newModuleList); 
			  
			  if(view.isError()==true)
			  {	  
				  	a="redirect:/accessDenied";			  	
		      }
			  else 
			  { 
				  Info inf = new Info(); 
				  System.out.println("awardId:"+awardId);
		  
				  MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				  map.add("awardId", awardId); 
				  Info miqc = rest.postForObject(Constants.url+"/deleteIqacById", map, Info.class);
				  a="redirect:/showAwardDetailsList"; 
			  } 
			  
			  return a;
		  
		  }
	  
	  @RequestMapping(value = "/showOutReachDetailsList", method =
	  RequestMethod.GET) public ModelAndView
	  showOutReachDetailsList(HttpServletRequest request, HttpServletResponse
	  response) {
	  
	  ModelAndView model = null; try {
	  
	  model = new ModelAndView("FacultyDetails/outReachList");
	  
	  model.addObject("title", "Out Reach Activity List");
	  
	  } catch (Exception e) {
	  
	  System.err.println("exception In showOutReachDetailsList at Library Contr" +
	  e.getMessage());
	  
	  e.printStackTrace();
	  
	  }
	  
	  return model;
	  
	  }
	 

}
