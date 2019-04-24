package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Commons;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.faculty.model.Journal;
import com.ats.rusasoft.model.AccOfficer;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.EContentDevFacility;
import com.ats.rusasoft.model.GetAccOfficer;
import com.ats.rusasoft.model.GetInstituteList;
import com.ats.rusasoft.model.Hod;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.LibBookPurchase;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.NewDeanList;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.TFacultyStudLinkage;
import com.ats.rusasoft.model.TNeighbourhoodCommActivities;
import com.ats.rusasoft.model.accessright.AssignRoleDetailList;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class MasterController {
	RestTemplate rest = new RestTemplate();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());
	String redirect = null;

	@RequestMapping(value = "/checkUniqueField", method = RequestMethod.GET)
	public @ResponseBody Info checkUniqueField(HttpServletRequest request, HttpServletResponse response) {

		Info info = new Info();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String inputValue = request.getParameter("inputValue");
			int valueType = Integer.parseInt(request.getParameter("valueType"));
			int primaryKey = Integer.parseInt(request.getParameter("primaryKey"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int tableId = Integer.parseInt(request.getParameter("tableId"));

			map.add("inputValue", inputValue);
			map.add("valueType", valueType);
			map.add("tableId", tableId);
			map.add("isEditCall", isEdit);
			map.add("primaryKey", primaryKey);

			info = rest.postForObject(Constants.url + "checkUniqueField", map, Info.class);
			System.err.println("Info Response  " + info.toString());

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = "/showRegisterInstitute", method = RequestMethod.GET)
	public ModelAndView showRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showRegisterInstitute", "showInstituteList", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {
				model = new ModelAndView("master/reginstitute");

				model.addObject("title", "Institute Registration");

				Institute editInst = new Institute();

				model.addObject("editInst", editInst);

				model.addObject("access", add);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showIqacAfterLogin", method = RequestMethod.GET)
	public ModelAndView showIqacAfterLogin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/iqaclogin");

			model.addObject("title", "Fill Institute Information");

		} catch (Exception e) {

			System.err.println("exception In showIqacAfterLogin at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// instituteList

	@RequestMapping(value = "/showInstituteList", method = RequestMethod.GET)
	public ModelAndView showInstituteList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showInstituteList", "showInstituteList", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {

				Info addAccess = AccessControll.checkAccess("showInstituteList", "showInstituteList", "0", "1", "0",
						"0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showInstituteList", "showInstituteList", "0", "0", "1",
						"0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showInstituteList", "showInstituteList", "0", "0", "0",
						"1", newModuleList);

				model = new ModelAndView("master/instituteList");

				model.addObject("title", "Verified Institute List");

				Institute editInst = new Institute();

				model.addObject("editInst", editInst);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false) {
					System.err.println("Edit acce ==0");
					model.addObject("editAccess", 0);

				}

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				GetInstituteList[] instArray = restTemplate.getForObject(Constants.url + "getAllInstitutes",
						GetInstituteList[].class);
				List<GetInstituteList> instList = new ArrayList<>(Arrays.asList(instArray));

				model.addObject("instList", instList);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showInstituteList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showProgramDetails", method = RequestMethod.GET)
	public ModelAndView showProgramDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/programDetails");

			model.addObject("title", "Program Details Form");

		} catch (Exception e) {

			System.err.println("exception In showProgramDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	// res&innoForm

	@RequestMapping(value = "/showStudFacultyLinkage", method = RequestMethod.GET)
	public ModelAndView showResearchAndInnovationForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showStudFacultyLinkage", "showStudFacultyLinkage", "1", "0", "0", "0",
				newModuleList);

		if (view.isError() == true) {

			model = new ModelAndView("accessDenied");

		} else {
			model = new ModelAndView("master/showStudFacLinkg");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instituteId", instituteId);
			
			TFacultyStudLinkage[] studFacLinkArr =  rest.postForObject(Constants.url+"/showStudFacLinks", map, TFacultyStudLinkage[].class);
			List<TFacultyStudLinkage> linkageList = new ArrayList<>(Arrays.asList(studFacLinkArr));
			System.out.println("Links="+linkageList);
			
			model.addObject("linkageList", linkageList);
			model.addObject("title", "Student Faculty Linkage");

			Info add = AccessControll.checkAccess("showStudFacultyLinkage", "showStudFacultyLinkage", "0", "1", "0", "0",
					newModuleList);
			Info edit = AccessControll.checkAccess("showStudFacultyLinkage", "showStudFacultyLinkage", "0", "0", "1", "0",
					newModuleList);
			Info delete = AccessControll.checkAccess("showStudFacultyLinkage", "showStudFacultyLinkage", "0", "0", "0",
					"1", newModuleList);

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

			System.err.println("exception In showResearchAndInnovationForm at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/addStudFacLinkage", method = RequestMethod.GET)
	public ModelAndView addStudFacLinkage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		EContentDevFacility eCont = new EContentDevFacility();
		try {
			Info view = AccessControll.checkAccess("addStudFacLinkage", "showStudFacultyLinkage", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				TFacultyStudLinkage linkage = new TFacultyStudLinkage();
				  
				model = new ModelAndView("master/addstudFacLinkg");
					model.addObject("linkage", linkage);
					model.addObject("title", "Add Faculty Student Linkage");
			}
		} catch (Exception e) {

			System.err.println("exception In addEContentDev at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertStudFacultylinkg", method = RequestMethod.POST)
	public String insertStudFacultylinkg(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");
		TFacultyStudLinkage linkage = new TFacultyStudLinkage();
		
		linkage.setFacultyStudLinkageId(Integer.parseInt(request.getParameter("fac_stud_link_id")));
		linkage.setInstId(instituteId);
		linkage.setAcYearId(acadYear);
		linkage.setLinkageTitle(request.getParameter("link_title"));
		linkage.setPartneringInstitute(request.getParameter("part_inst"));
		linkage.setIndustryName(request.getParameter("insustry_name"));
		linkage.setIndustryFromYear(request.getParameter("indust_year"));
		linkage.setResearchLabName(request.getParameter("resrch_lab_name"));
		linkage.setLabFromYear(request.getParameter("resrch_lab_year"));
		linkage.setDurationFrom(request.getParameter("from_date"));
		linkage.setDurationTo(request.getParameter("to_date"));
		linkage.setNatureOfLinkage(request.getParameter("naturelinkage"));
		linkage.setNoStudentParticipated(Integer.parseInt(request.getParameter("participate")) );
		linkage.setDelStatus(1);
		linkage.setIsActive(1);
		linkage.setMakerUserId(userId);
		linkage.setMaker_datetime(curDateTime);
		linkage.setExInt1(0);
		linkage.setExInt2(0);
		linkage.setExVar1("NA");
		linkage.setExVar2("NA");
		
		System.out.println(linkage.toString());
		
		TFacultyStudLinkage addLinkage = rest.postForObject(Constants.url+"/newstudFacLink", linkage, TFacultyStudLinkage.class);
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			
		}
		return "redirect:/showStudFacultyLinkage";
		
	}
	
	@RequestMapping(value = "/editLinkage/{linkId}", method = RequestMethod.GET)
	public ModelAndView editLinkage(@PathVariable("linkId") int linkId, HttpServletRequest request) {

		// System.out.println("Id:" + iqacId);

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		try {

			Info view = AccessControll.checkAccess("editLinkage/{linkId}", "showStudFacultyLinkage", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				
				map.add("linkId", linkId);
				model = new ModelAndView("master/addstudFacLinkg");
				model.addObject("title", "Edit Faculty Student Linkage");
				TFacultyStudLinkage  linkage = rest.postForObject(Constants.url+"/getStudFacLinksById", map, TFacultyStudLinkage.class);
				model.addObject("linkage", linkage);
			}
		}catch(Exception e) {
			
		}
		return model;
	}
	
	@RequestMapping(value = "/deleteLinkage/{linkId}", method = RequestMethod.GET)
	public String  deleteLinkage(@PathVariable("linkId") int linkId, HttpServletRequest request) {
		
		String a = null;
		try {
			
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			Info view = AccessControll.checkAccess("deleteLinkage/{linkId}", "showStudFacultyLinkage", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("linkId", linkId);

				TFacultyStudLinkage delLink = rest.postForObject(Constants.url + "/deleteLinkById", map, TFacultyStudLinkage.class);
				a="redirect:/showStudFacultyLinkage";
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return a;
		
	}

	
	@RequestMapping(value = "/deleteSelStudFacLinks/{linkageId}", method = RequestMethod.GET)
	public String deleteSelStudFacLinks(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int linkageId) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteSelStudFacLinks/{contentId}", "showStudFacultyLinkage", "0", "0", "0",
				"1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (linkageId == 0) {

					System.err.println("Multiple records delete ");
					String[] linkageIds = request.getParameterValues("linkageId");
					System.out.println("id are" + linkageIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < linkageIds.length; i++) {
						sb = sb.append(linkageIds[i] + ",");

					}
					String linkageIdsList = sb.toString();
					linkageIdsList = linkageIdsList.substring(0, linkageIdsList.length() - 1);

					map.add("linkageIdsList", linkageIdsList);
				} else {

					System.err.println("Single Record delete ");
					map.add("linkageIdsList", linkageId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelLinks", map, Info.class);

				a = "redirect:/showStudFacultyLinkage";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}
	
	
	/**********************************************Neighbourhood ********************************************/
	
	@RequestMapping(value = "/showNeighbourhoodCommActivities", method = RequestMethod.GET)
	public ModelAndView showNeighbourhoodCommActivities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showNeighbourhoodCommActivities", "showNeighbourhoodCommActivities", "1", "0", "0", "0",
				newModuleList);

		if (view.isError() == true) {

			model = new ModelAndView("accessDenied");

		} else {
			model = new ModelAndView("master/neighbourhoodCommActList");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instituteId", instituteId);
			
			TNeighbourhoodCommActivities[] neighbourArr =  rest.postForObject(Constants.url+"/showNeighbourCommActivities", map, TNeighbourhoodCommActivities[].class);
			List<TNeighbourhoodCommActivities> neighbrCommActList = new ArrayList<>(Arrays.asList(neighbourArr));
			System.out.println("Links="+neighbrCommActList);
			
			model.addObject("neighbrCommActList", neighbrCommActList);
			model.addObject("title", "Neighbourhood Community Activity");

			Info add = AccessControll.checkAccess("showNeighbourhoodCommActivities", "showNeighbourhoodCommActivities", "0", "1", "0", "0",
					newModuleList);
			Info edit = AccessControll.checkAccess("showNeighbourhoodCommActivities", "showNeighbourhoodCommActivities", "0", "0", "1", "0",
					newModuleList);
			Info delete = AccessControll.checkAccess("showNeighbourhoodCommActivities", "showNeighbourhoodCommActivities", "0", "0", "0",
					"1", newModuleList);

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

			System.err.println("exception In showNeighbourhoodCommActivities at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/newNeighbourhoodCommAct", method = RequestMethod.GET)
	public ModelAndView newNeighbourhoodCommAct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		TNeighbourhoodCommActivities neighbourCommAct = new TNeighbourhoodCommActivities();
		try {
			Info view = AccessControll.checkAccess("newNeighbourhoodCommAct", "showNeighbourhoodCommActivities", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				
				  
					model = new ModelAndView("master/neighbourhoodCommAct");
					model.addObject("neighbourCommAct", neighbourCommAct);
					model.addObject("title", "Add Neighbourhood Community Activity");
			}
		} catch (Exception e) {

			System.err.println("exception In addEContentDev at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/insertneighbrhdCommActvity", method = RequestMethod.POST)
	public String insertneighbrhdCommActvity(HttpServletRequest request, HttpServletResponse response) {

		try {
				HttpSession session = request.getSession();
				
				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int acadYear = (int) session.getAttribute("acYearId");
				
				TNeighbourhoodCommActivities neighbourCommAct = new TNeighbourhoodCommActivities();
				
				neighbourCommAct.setInstNeighbourhoodCommActId(Integer.parseInt(request.getParameter("neghbh_comm_act_id")));
				neighbourCommAct.setInstId(instituteId);
				neighbourCommAct.setActivityName(request.getParameter("activity_name"));
				neighbourCommAct.setNoOfStud(Integer.parseInt(request.getParameter("no_student")));
				neighbourCommAct.setTotalStudent(Integer.parseInt(request.getParameter("ttl_student")));
				neighbourCommAct.setNoOfFaculty(Integer.parseInt(request.getParameter("no_faculty")));
				neighbourCommAct.setTotalFaculty(Integer.parseInt(request.getParameter("ttl_faculty")));
				neighbourCommAct.setAssociationWith(request.getParameter("association"));
				neighbourCommAct.setAcYearId(acadYear);
				neighbourCommAct.setDelStatus(1);
				neighbourCommAct.setIsActive(1);
				neighbourCommAct.setMakerUserId(userId);
				neighbourCommAct.setMakerDatetime(curDateTime);
				neighbourCommAct.setExInt1(Integer.parseInt(request.getParameter("isActivity")));
				neighbourCommAct.setExInt2(0);
				neighbourCommAct.setExVar1("NA");
				neighbourCommAct.setExVar2("NA");
				
				System.out.println(neighbourCommAct.toString());
				
				TNeighbourhoodCommActivities commAct = rest.postForObject(Constants.url+"/saveNeighbourhoodCommAct", 
				neighbourCommAct, TNeighbourhoodCommActivities.class);
				
				
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return "redirect:/showNeighbourhoodCommActivities";
	
	}
	
	
	@RequestMapping(value = "/editNeighbCommActivity/{neighbCommActId}", method = RequestMethod.GET)
	public ModelAndView editNeighbCommActivity(@PathVariable("neighbCommActId") int neighbCommActId, HttpServletRequest request) {

		// System.out.println("Id:" + iqacId);

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		try {

			Info view = AccessControll.checkAccess("editNeighbCommActivity/{neighbCommActId}", "showNeighbourhoodCommActivities", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				
				map.add("neighbCommActId", neighbCommActId);
				model = new ModelAndView("master/neighbourhoodCommAct");
				model.addObject("title", "Edit Neighbourhood Community Activity");
				TNeighbourhoodCommActivities  neighbourCommAct = rest.postForObject(Constants.url+"/getneighbCommActivityById", map, TNeighbourhoodCommActivities.class);
				model.addObject("neighbourCommAct", neighbourCommAct);
			}
		}catch(Exception e) {
			
		}
		return model;
	}
	
	@RequestMapping(value = "/deleteNeighbCommActivity/{neighbCommActId}", method = RequestMethod.GET)
	public String  deleteNeighbCommActivity(@PathVariable("neighbCommActId") int neighbCommActId, HttpServletRequest request) {
		
		String a = null;
		try {
			
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			Info view = AccessControll.checkAccess("deleteNeighbCommActivity/{neighbCommActId}", "showNeighbourhoodCommActivities", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("neighbCommActId", neighbCommActId);

				TNeighbourhoodCommActivities delNeighbourCommAct = rest.postForObject(Constants.url + "/deleteneghCommActivityId", map, TNeighbourhoodCommActivities.class);
				a="redirect:/showNeighbourhoodCommActivities";
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return a;
		
	}
	
	@RequestMapping(value = "/deleteSelNeghbCommActivities/{actId}", method = RequestMethod.GET)
	public String deleteSelNeghbCommActivities(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int actId) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteSelNeghbCommActivities/{actId}", "showNeighbourhoodCommActivities", "0", "0", "0",
				"1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (actId == 0) {

					System.err.println("Multiple records delete ");
					String[] actIds = request.getParameterValues("actId");
					System.out.println("id are" + actIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < actIds.length; i++) {
						sb = sb.append(actIds[i] + ",");

					}
					String actIdList = sb.toString();
					actIdList = actIdList.substring(0, actIdList.length() - 1);

					map.add("actIdList", actIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("actIdList", actId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelAcitivities", map, Info.class);

				a = "redirect:/showNeighbourhoodCommActivities";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}
	
	
	/**********************************Neighbourhood ********************************************/

	/********************************** Infrastructure *******************************************/
	@RequestMapping(value = "/showInfrastructureForm", method = RequestMethod.GET)
	public ModelAndView showInfrastructureForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/infrastructure");

			model.addObject("title", "Infrastructure Form");

		} catch (Exception e) {

			System.err.println("exception In showInfrastructureForm at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/addEContentDev", method = RequestMethod.GET)
	public ModelAndView addEContentDev(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		EContentDevFacility eCont = new EContentDevFacility();
		try {
			Info view = AccessControll.checkAccess("addEContentDev", "econtentDevelopment", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				
				  
					model = new ModelAndView("infrastructure/econtentDev");
					model.addObject("content", eCont);
					model.addObject("title", "Add E-Content Development");
			}
		} catch (Exception e) {

			System.err.println("exception In addEContentDev at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	/*
	 * @RequestMapping(value = "/addEContentDev", method = RequestMethod.GET) public
	 * ModelAndView addEContentDev(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = new ModelAndView("infrastructure/econtentDev");
	 * HttpSession session = request.getSession(); List<ModuleJson> newModuleList =
	 * (List<ModuleJson>) session.getAttribute("newModuleList");
	 * 
	 * // try { Info view = AccessControll.checkAccess("econtentDevelopment",
	 * "econtentDevelopment", "0", "1", "0", "0", newModuleList);
	 * 
	 * if (view.isError() == true) {
	 * 
	 * model = new ModelAndView("accessDenied");
	 * 
	 * } else {
	 * 
	 * 
	 * 
	 * TInstEContentDevFacility eCont = new TInstEContentDevFacility();
	 * model.addObject("eCont", eCont);
	 * 
	 * model.addObject("title", "Add E-Content");
	 * 
	 * //} } catch (Exception e) {
	 * 
	 * System.err.println("exception In addEContentDev at Master Contr" +
	 * e.getMessage());
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return model;
	 * 
	 * }
	 */

	@RequestMapping(value = "/econtentDevelopment", method = RequestMethod.GET)
	public ModelAndView showlibBookPurchased(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("infrastructure/e_contentDevList");
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("econtentDevelopment", "econtentDevelopment", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int acadYear = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
				map.add("instituteId", instituteId);
				map.add("acadYear", acadYear);

				EContentDevFacility[] econtentarr = rest.postForObject(Constants.url + "/showEComtentDevFaclity", map,
						EContentDevFacility[].class);
				List<EContentDevFacility> contentList = new ArrayList<>(Arrays.asList(econtentarr));
				System.err.println("eCont="+contentList);
				model.addObject("contentList", contentList);
				model.addObject("title", "E-Content Development List");

				Info add = AccessControll.checkAccess("econtentDevelopment", "econtentDevelopment", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("econtentDevelopment", "econtentDevelopment", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("econtentDevelopment", "econtentDevelopment", "0", "0", "0",
						"1", newModuleList);

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
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/inserteContentFacilities", method = RequestMethod.POST)
	public String inserteContentFacilities(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int instituteId = (int) session.getAttribute("instituteId");

			int userId = (int) session.getAttribute("userId");

			EContentDevFacility eCont = new EContentDevFacility();

			eCont.setInstEContentDevFacilityId(Integer.parseInt(request.getParameter("econtentId")));
			eCont.setInstId(instituteId);
			eCont.seteContentDevFacility(request.getParameter("e_contentType"));
			eCont.setNameEcontentDevFacility(request.getParameter("e_contentName"));
			eCont.setVideoLink(request.getParameter("video_link"));
			eCont.setDelStatus(1);
			eCont.setIsActive(1);
			eCont.setMakerUserId(userId);
			eCont.setMakerDatetime(curDateTime);
			eCont.setExInt1(0);
			eCont.setExInt2(0);
			eCont.setExInt2(0);
			eCont.setExVar1("NA");
			eCont.setExVar2("NA");
			//System.out.println(eCont.toString());
			EContentDevFacility contents = rest.postForObject(Constants.url + "/saveEcontentDevFacilities", eCont,
					EContentDevFacility.class);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/econtentDevelopment";

	}
	
	@RequestMapping(value = "/editEContent/{contentId}", method = RequestMethod.GET)
	public ModelAndView editEContent(@PathVariable("contentId") int contentId, HttpServletRequest request) {

		// System.out.println("Id:" + iqacId);

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		try {

			Info view = AccessControll.checkAccess("editEContent/{iqacId}", "econtentDevelopment", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				
				map.add("contentId", contentId);
				model = new ModelAndView("infrastructure/econtentDev");
				model.addObject("title", "Edit E-Content Development");
				EContentDevFacility  econtDev = rest.postForObject(Constants.url+"/getEContentDevFecilityById", map, EContentDevFacility.class);
				model.addObject("content", econtDev);
			}
		}catch(Exception e) {
			
		}
		return model;
	}
	
	@RequestMapping(value = "/deleteEContent/{contentId}", method = RequestMethod.GET)
	public String deleteEContent(@PathVariable("contentId") int contentId, HttpServletRequest request,
			HttpServletResponse response) {
		String a = null;
		try {
			
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			Info view = AccessControll.checkAccess("deleteEContent/{contentId}", "econtentDevelopment", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("contentId", contentId);

				EContentDevFacility delContent = rest.postForObject(Constants.url + "/deleteEContentById", map, EContentDevFacility.class);
				a="redirect:/econtentDevelopment";
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return a;

	}
	
	@RequestMapping(value = "/delSlectedEContentDevFaclities/{econtent}", method = RequestMethod.GET)
	public String delSlectedPurchasedLibBooks(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int econtent) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedEContentDevFaclities/{contentId}", "econtentDevelopment", "0", "0", "0",
				"1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (econtent == 0) {

					System.err.println("Multiple records delete ");
					String[] contentIds = request.getParameterValues("econtent");
					System.out.println("id are" + contentIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < contentIds.length; i++) {
						sb = sb.append(contentIds[i] + ",");

					}
					String contentIdsList = sb.toString();
					contentIdsList = contentIdsList.substring(0, contentIdsList.length() - 1);

					map.add("contentIdsList", contentIdsList);
				} else {

					System.err.println("Single Record delete ");
					map.add("contentIdsList", econtent);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelContent", map, Info.class);

				a = "redirect:/econtentDevelopment";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}

	/////////////////////////// ****faculty
	/////////////////////////// details****//////////////////////////////////

	@RequestMapping(value = "/showRegAcc", method = RequestMethod.GET)
	public ModelAndView showRegAcc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		RestTemplate restTemplate = new RestTemplate();

		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("hodRegistration", "hodList", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/accReg");

				model.addObject("title", "Account Officer Registration");

				Designation[] designArr = restTemplate.getForObject(Constants.url + "/getAllDesignations",
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();

				map.add("type", 1);
				Quolification[] quolArray = restTemplate.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);
				model.addObject("addEdit", "0");

			}
		} catch (Exception e) {

			System.err.println("exception In showRegAcc at Masters Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertAccOff", method = RequestMethod.POST)
	public String insertAccOff(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int instituteId = (int) session.getAttribute("instituteId");

			int userId = (int) session.getAttribute("userId");

			int isAccOff = 0;

			try {
				isAccOff = Integer.parseInt(request.getParameter("isAccOff"));
			} catch (Exception e) {
				isAccOff = 0;
			}

			int accId = 0;

			try {
				accId = Integer.parseInt(request.getParameter("acc_id"));
			} catch (Exception e) {
				accId = 0;
			}

			String roleNameList = null;

			roleNameList = Constants.Account_Officer_Role + "," + Constants.Faculty_Role;

			if (isAccOff == 1) {
				roleNameList = roleNameList + "," + Constants.Account_Officer_Role;
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("roleNameList", roleNameList);
			AssignRoleDetailList[] roleArray = rest.postForObject(Constants.url + "getRoleIdsByRoleNameList", map,
					AssignRoleDetailList[].class);
			List<AssignRoleDetailList> roleIdsList = new ArrayList<>(Arrays.asList(roleArray));

			String roleIds = new String();
			for (int i = 0; i < roleIdsList.size(); i++) {
				roleIds = roleIdsList.get(i).getRoleId() + "," + roleIds;
			}
			System.err.println("roleIds " + roleIds);

			int designation = 0;

			System.out.println("Data:" + accId);
			String accName = request.getParameter("accName");
			System.out.println("Data:" + accName);
			designation = Integer.parseInt(request.getParameter("designation"));
			String dateOfJoin = request.getParameter("dateOfJoin");
			String contact = request.getParameter("contactNo");
			String email = request.getParameter("email");

			String[] deptIds = request.getParameterValues("dept_id");
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < deptIds.length; i++) {
				sb = sb.append(deptIds[i] + ",");

			}
			String deptIdList = sb.toString();
			deptIdList = deptIdList.substring(0, deptIdList.length() - 1);
			int addEdit = Integer.parseInt(request.getParameter("addEdit"));
			if (addEdit == 0) {
				Staff staff = new Staff();

				staff.setContactNo(contact);
				staff.setCurrentDesignationId(designation);
				staff.setDeptId(deptIdList);
				staff.setEmail(email);
				staff.setFacultyFirstName(accName);
				staff.setFacultyId(accId);
				staff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
				staff.setHightestQualificationYear(null);
				staff.setIsAccOff(1);
				staff.setIsDean(0);
				staff.setIsFaculty(1);
				staff.setIsHod(0);
				staff.setIsIqac(0);
				staff.setIsLibrarian(0);
				staff.setIsPrincipal(0);

				staff.setIsStudent(0);
				staff.setIsWorking(Integer.parseInt(request.getParameter("isWorking")));
				staff.setJoiningDate(dateOfJoin);
				staff.setLastUpdatedDatetime(curDateTime);
				staff.setMakerEnterDatetime(curDateTime);

				staff.setPassword("");
				staff.setRealivingDate(null);
				staff.setRoleIds(roleIds);
				staff.setTeachingTo(0);
				staff.setType(5);

				staff.setInstituteId(instituteId);
				staff.setJoiningDate(dateOfJoin);
				staff.setContactNo(contact);
				staff.setEmail(email);
				staff.setDelStatus(1);
				staff.setIsActive(1);
				staff.setMakerUserId(userId);
				staff.setMakerEnterDatetime(curDateTime);
				staff.setCheckerUserId(0);
				staff.setCheckerDatetime(curDateTime);
				staff.setLastUpdatedDatetime(curDateTime);

				staff.setExtravarchar1("NA");
				try {
					staff.setRealivingDate(request.getParameter("acc_off_relDate"));

				} catch (Exception e) {
					staff.setRealivingDate(null);
				}
				Staff hod = rest.postForObject(Constants.url + "/addNewStaff", staff, Staff.class);

			} else {

				map = new LinkedMultiValueMap<>();
				map.add("id", accId);

				Staff editHod = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				editHod.setFacultyFirstName(accName);
				editHod.setDeptId(deptIdList);
				editHod.setEmail(email);
				editHod.setFacultyId(accId);
				editHod.setContactNo(contact);
				editHod.setCurrentDesignationId(designation);
				editHod.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
				editHod.setJoiningDate(dateOfJoin);
				editHod.setIsAccOff(1);
				editHod.setType(5);
				editHod.setIsWorking(Integer.parseInt(request.getParameter("isWorking")));

				try {
					editHod.setRealivingDate(request.getParameter("acc_off_relDate"));

				} catch (Exception e) {
					editHod.setRealivingDate(null);
				}

				Staff hod = rest.postForObject(Constants.url + "/addNewStaff", editHod, Staff.class);

			}

			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/showAccList";
			else
				redirect = "redirect:/showRegAcc";
		} catch (Exception e) {

			System.err.println("exception In showRegAcc at Masters Contr" + e.getMessage());
			e.printStackTrace();

		}
		return redirect;

	}

	@RequestMapping(value = "/showAccList", method = RequestMethod.GET)
	public ModelAndView showAccList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showAccList", "showAccList", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("master/accList");

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				NewDeanList[] accOffArray = rest.postForObject(Constants.url + "getNewAccOffList", map,
						NewDeanList[].class);
				List<NewDeanList> accOffList = new ArrayList<>(Arrays.asList(accOffArray));
				System.err.println("accOffList " + accOffList.toString());

				model.addObject("accOffList", accOffList);
				model.addObject("listMapping", "showAccList");

				model.addObject("title", "Account Officer Registration List");

				Info addAccess = AccessControll.checkAccess("showAccList", "showAccList", "0", "1", "0", "0",
						newModuleList);

				Info editAccess = AccessControll.checkAccess("showAccList", "showAccList", "0", "0", "1", "0",
						newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showAccList", "showAccList", "0", "0", "0", "1",
						newModuleList);

				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

			} else {
				model = new ModelAndView("accessDenied");
			}
		} catch (Exception e) {

			System.err.println("exception In showAccList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showEditaccOff/{facultyId}", method = RequestMethod.GET)
	public ModelAndView showEditaccOff(@PathVariable("facultyId") int facultyId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info addAccess = AccessControll.checkAccess("showRegAcc", "showAccList", "0", "0", "1", "0", newModuleList);

			if (addAccess.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/accReg");

				model.addObject("title", "Edit Account Officer Registration");

				Designation[] designArr = rest.getForObject(Constants.url + "/getAllDesignations", Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();

				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);

				map = new LinkedMultiValueMap<>();
				map.add("id", facultyId);

				Staff editFaculty = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				System.out.println("facultyId:" + facultyId);

				model.addObject("editFaculty", editFaculty);
				model.addObject("addEdit", "1");

				List<Integer> deptIdList = Stream.of(editFaculty.getDeptId().split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());

				model.addObject("deptIdList", deptIdList);

			}
		} catch (Exception e) {

			System.err.println("exception In editFaculty at Library Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteaccOff/{facultyId}", method = RequestMethod.GET)
	public String deleteaccOff(HttpServletRequest request, HttpServletResponse response, @PathVariable int facultyId) {
		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("showRegAcc", "showAccList", "0", "0", "0", "1",
					newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (facultyId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("accOffIds");
					System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String hodIdList = sb.toString();
					hodIdList = hodIdList.substring(0, hodIdList.length() - 1);

					map.add("staffIdList", hodIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("staffIdList", facultyId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteStaffSlected", map, Info.class);

				redirect = "redirect:/showAccList";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteaccOff at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect;

	}

	@RequestMapping(value = "/showLinkage", method = RequestMethod.GET)
	public ModelAndView showLinkage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ResearchInnovation/linkage");

			model.addObject("title", "Linkage");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showMOUs", method = RequestMethod.GET)
	public ModelAndView showMOUs(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ResearchInnovation/mous");

			model.addObject("title", "MOUs");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showExtAct", method = RequestMethod.GET)
	public ModelAndView showExtAct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ResearchInnovation/extActivities");

			model.addObject("title", "Extension Activities");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showGenIssue", method = RequestMethod.GET)
	public ModelAndView showGenIssue(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ResearchInnovation/genderIssue");

			model.addObject("title", "Gender Issue Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showComAct", method = RequestMethod.GET)
	public ModelAndView showComAct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ResearchInnovation/comActivties");

			model.addObject("title", "Community Activities");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	///////////////// Program Details///////////////////

	/////////////////////// infrastructure////////////////////////////

	@RequestMapping(value = "/showinfra", method = RequestMethod.GET)
	public ModelAndView showinfra(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/facilities");

			model.addObject("title", "Infrastructure Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showInstruct", method = RequestMethod.GET)
	public ModelAndView showInstruct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/institutional");

			model.addObject("title", "Instructional Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAdmin", method = RequestMethod.GET)
	public ModelAndView showAdmin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/administrative");

			model.addObject("title", "Adminstrative Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAmeneties", method = RequestMethod.GET)
	public ModelAndView showAmeneties(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/amenities");

			model.addObject("title", "Ameneties Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showITinfra", method = RequestMethod.GET)
	public ModelAndView showITinfra(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/ITinfra");

			model.addObject("title", "IT Infrastructure Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showInternetCon", method = RequestMethod.GET)
	public ModelAndView showInternetCon(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/internetCon");

			model.addObject("title", "Internet Connection Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showeContent", method = RequestMethod.GET)
	public ModelAndView showeContent(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/eContent");

			model.addObject("title", "e Content Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPhysicalFacilities", method = RequestMethod.GET)
	public ModelAndView showPhysicalFacilities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/physicalFacilities");

			model.addObject("title", "Physical Facilities ");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAdjuntFaculty", method = RequestMethod.GET)
	public ModelAndView showAdjuntFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/adjuntFaculty");

			model.addObject("title", "Adjunt Faculty Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showFacultyInfo", method = RequestMethod.GET)
	public ModelAndView showFacultyInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/facultyInfo");

			model.addObject("title", " Faculty Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// Institute

	// Insert Institute

	@RequestMapping(value = "/insertInstitute", method = RequestMethod.POST)
	public String insertInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		int instId = Integer.parseInt(request.getParameter("inst_id"));
		String redirect = null;
		try {

			/*
			 * HttpSession session = request.getSession();
			 * 
			 * List<ModuleJson> newModuleList = (List<ModuleJson>)
			 * session.getAttribute("newModuleList");
			 * 
			 * Info editAccess = AccessControll.checkAccess("insertInstitute",
			 * "showInstituteList", "1", "0", "0", "0", newModuleList);
			 * 
			 * if (editAccess.isError() == true) { model = new ModelAndView("accessDenied");
			 * redirect = "redirect:/accessDenied"; } else {
			 * 
			 * LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			 */
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int exInt = 0;
			String exVar = "";
			if (instId == 0) {
				Institute institute = new Institute();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				String aisheCode = request.getParameter("aishe_code");
				institute.setAisheCode(aisheCode);

				institute.setCheckerDatetime(curDateTime);
				institute.setCheckerUserId(0);

				institute.setContactNo(request.getParameter("princ_contact"));
				institute.setDelStatus(1);
				institute.setEmail(request.getParameter("princ_email"));

				institute.setExInt1(exInt);
				institute.setExInt2(exInt);
				institute.setExVar1(exVar);
				institute.setExVar2(exVar);

				institute.setInstituteAdd(request.getParameter("inst_add"));
				institute.setInstituteId(instId);
				institute.setInstituteName(request.getParameter("inst_name"));

				institute.setIsActive(1);
				institute.setIsEnrollSystem(0);// set to 1 when user loged in for first time and changed his/her
												// pass.
												// Initially its zero
				int isReg = Integer.parseInt(request.getParameter("is_registration"));
				institute.setIsRegistration(isReg);

				institute.setLastUpdatedDatetime(curDateTime);
				institute.setMakerEnterDatetime(curDateTime);
				institute.setMakerUserId(0);// user id who is creating this record for ex principal is
											// user who creates
				// iqac
				// and hod to student

				institute.setPresidentName(request.getParameter("pres_name"));
				institute.setPrincipalName(request.getParameter("princ_name"));
				if (isReg == 1)
					institute.setRegDate(DateConvertor.convertToYMD(request.getParameter("reg_date")));
				institute.setTrustAdd(request.getParameter("trusty_add"));

				institute.setTrustContactNo(request.getParameter("trusty_con_no"));
				institute.setTrustName(request.getParameter("trusty_name"));
				institute.setUserType(0);// for institute its 0

				institute.setPresidenContact(request.getParameter("pres_contact"));
				institute.setPresidentEmail(request.getParameter("pres_email"));

				institute.setVillage(request.getParameter("village"));
				institute.setTaluka(request.getParameter("taluka"));
				institute.setDistrict(request.getParameter("district"));
				institute.setState(request.getParameter("state"));
				institute.setPincode(request.getParameter("pin"));

				System.out.println(institute);

				Institute info = restTemplate.postForObject(Constants.url + "saveInstitute", institute,
						Institute.class);

			} else {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", instId);
				// getInstitute
				Institute institute = rest.postForObject(Constants.url + "getInstitute", map, Institute.class);

				String aisheCode = request.getParameter("aishe_code");
				institute.setAisheCode(aisheCode);

				institute.setContactNo(request.getParameter("princ_contact"));
				institute.setEmail(request.getParameter("princ_email"));
				institute.setInstituteAdd(request.getParameter("inst_add"));
				institute.setInstituteName(request.getParameter("inst_name"));

				int isReg = Integer.parseInt(request.getParameter("is_registration"));
				institute.setIsRegistration(isReg);

				institute.setLastUpdatedDatetime(curDateTime);

				institute.setPresidentName(request.getParameter("pres_name"));
				institute.setPrincipalName(request.getParameter("princ_name"));
				if (isReg == 1)
					institute.setRegDate(DateConvertor.convertToYMD(request.getParameter("reg_date")));
				else
					institute.setRegDate(null);

				institute.setTrustAdd(request.getParameter("trusty_add"));

				institute.setTrustContactNo(request.getParameter("trusty_con_no"));
				institute.setTrustName(request.getParameter("trusty_name"));

				institute.setPresidenContact(request.getParameter("pres_contact"));
				institute.setPresidentEmail(request.getParameter("pres_email"));

				institute.setVillage(request.getParameter("village"));
				institute.setTaluka(request.getParameter("taluka"));
				institute.setDistrict(request.getParameter("district"));
				institute.setState(request.getParameter("state"));
				institute.setPincode(request.getParameter("pin"));

				Institute info = restTemplate.postForObject(Constants.url + "saveInstitute", institute,
						Institute.class);
			}
			redirect = "redirect/showInstituteList";

			if (instId == 0)
				redirect = "redirect:/";
			else
				redirect = "redirect:/showInstituteList";
			// }
		} catch (Exception e) {

			System.err.println(" Exception In saveInstitute at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return redirect;

	}

	// deleteInstitutes
	@RequestMapping(value = "/deleteInstitutes/{instId}", method = RequestMethod.GET)
	public String deleteInstitutes(HttpServletRequest request, HttpServletResponse response, @PathVariable int instId) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			if (instId == 0) {

				System.err.println("Multiple records delete ");
				String[] instIds = request.getParameterValues("instIds");
				System.out.println("id are" + instIds);

				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < instIds.length; i++) {
					sb = sb.append(instIds[i] + ",");

				}
				String instIdList = sb.toString();
				instIdList = instIdList.substring(0, instIdList.length() - 1);

				map.add("instIdList", instIdList);
			} else {

				System.err.println("Single Record delete ");
				map.add("instIdList", instId);
			}

			Info errMsg = rest.postForObject(Constants.url + "deleteInstitutes", map, Info.class);

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/showInstituteList";

	}

	// Approve Inst

	@RequestMapping(value = "/approveInstitutes/{instId}", method = RequestMethod.GET)
	public String approveInstitutes(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int instId) {

		String redirect = null;
		try {
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info addAccess = AccessControll.checkAccess("approveInstitutes/{instId}", "showInstituteList", "1", "0",
					"0", "0", newModuleList);

			if (addAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (instId == 0) {

					System.err.println("Multiple records approve ");
					String[] instIds = request.getParameterValues("instIds");
					System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();
					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String instIdList = sb.toString();
					instIdList = instIdList.substring(0, instIdList.length() - 1);

					map.add("instIdList", instIdList);
					map.add("aprUserId", userObj.getUserId());
				} else {
					map.add("aprUserId", userObj.getUserId());

					System.err.println("Single Record delete ");
					map.add("instIdList", instId);
				}

				Info errMsg = rest.postForObject(Constants.url + "approveInstitutes", map, Info.class);
				redirect = "redirect:/showInstituteList";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect;

	}

	@RequestMapping(value = "/showEditInstitute", method = RequestMethod.POST)
	public ModelAndView showEditInstitute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("showEditInstitute", "showInstituteList", "0", "0", "1", "0",
					newModuleList);
			if (editAccess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				model = new ModelAndView("master/reginstitute");

				int instId = Integer.parseInt(request.getParameter("edit_inst_id"));

				model.addObject("title", " Edit Institute Reginstration");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", instId);
				// getInstitute
				Institute editInst = rest.postForObject(Constants.url + "getInstitute", map, Institute.class);
				try {
					editInst.setRegDate(DateConvertor.convertToDMY(editInst.getRegDate()));
				} catch (Exception e) {
					System.err.println("Exce in show Edit Institute /showEditInstitute");
				}
				model.addObject("editInst", editInst);
				model.addObject("instituteId", instId);

			}
		} catch (Exception e) {
			System.err.println("Exce in editInstitute/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	// Institute */

	// Dept

	// insertDept

	@RequestMapping(value = "/insertDept", method = RequestMethod.POST)
	public String insertDept(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert dept");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int deptId = Integer.parseInt(request.getParameter("dept_id"));
			System.err.println("Dept id  " + deptId);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("insertDept", "showDeptList", "1", "0", "0", "0",
					newModuleList);

			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				if (deptId == 0) {
					Dept dept = new Dept();
					String deptName = request.getParameter("dept_name");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					dept.setAddDate(curDate);
					dept.setDelStatus(1);
					dept.setDeptId(deptId);
					dept.setDeptName(deptName);
					int exInt1 = 0;
					dept.setExInt1(exInt1);
					dept.setExInt2(exInt1);
					String exVar1 = "NA";
					dept.setExVar1(exVar1);
					dept.setExVar2(exVar1);
					dept.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session
					dept.setIsActive(1);
					dept.setMakerUserId(userObj.getUserId());// get from Session
					Dept editInst = rest.postForObject(Constants.url + "saveDept", dept, Dept.class);

				} else {

					map.add("deptId", deptId);
					// getInstitute
					Dept dept = rest.postForObject(Constants.url + "getDept", map, Dept.class);
					String deptName = request.getParameter("dept_name");
					dept.setDeptName(deptName);
					dept.setMakerUserId(userObj.getUserId());// get from Session
					Dept editInst = rest.postForObject(Constants.url + "saveDept", dept, Dept.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showDeptList";
				else
					redirect = "redirect:/addFaculty";
			}

		} catch (Exception e) {
			System.err.println("Exce in save dept  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}
	// getAllDeptList

	@RequestMapping(value = "/showEditDept", method = RequestMethod.POST)
	public ModelAndView showEditDept(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("showEditDept", "showDeptList", "0", "0", "1", "0",
					newModuleList);
			if (editAccess.isError() == true) {
				new ModelAndView("accessDenied");
			} else {

				model = new ModelAndView("master/addFaculty");

				int deptId = Integer.parseInt(request.getParameter("edit_dept_id"));

				model.addObject("title", "Edit Department");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("deptId", deptId);
				// getInstitute
				Dept editDept = rest.postForObject(Constants.url + "getDept", map, Dept.class);
				model.addObject("dept", editDept);
				model.addObject("deptId", deptId);

			}
		} catch (Exception e) {
			System.err.println("Exce in showEditDept  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/deleteDepts/{deptId}", method = RequestMethod.GET)
	public String deleteDepts(HttpServletRequest request, HttpServletResponse response, @PathVariable int deptId) {

		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("showEditDept", "showDeptList", "0", "0", "0", "1",
					newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (deptId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("deptIds");
					System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String deptIdList = sb.toString();
					deptIdList = deptIdList.substring(0, deptIdList.length() - 1);

					map.add("deptIdList", deptIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("deptIdList", deptId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteDepts", map, Info.class);
				redirect = "redirect:/showDeptList";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteDepts at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect; // "redirect:/showDeptList";

	}

	Hod editHod;

	@RequestMapping(value = "/changeHod/{hodId}", method = RequestMethod.GET)
	public ModelAndView editIqac(@PathVariable("hodId") int hodId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("hodRegistration", "hodList", "0", "0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/ChangeHod");
				model.addObject("title", "Change HOD");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("hodId", hodId);
				editHod = rest.postForObject(Constants.url + "/getHod", map, Hod.class);
				System.out.println("hodId:" + hodId);

				model.addObject("hod", editHod);

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();

				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);

			}
		} catch (Exception e) {

			System.err.println("exception In changeHod at master Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/changeHodSubmit", method = RequestMethod.POST)
	public String changeHodSubmit(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert changeHodSubmit");

		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int hodId = Integer.parseInt(request.getParameter("hod_id"));
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			System.err.println("hodId id  " + hodId);

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			/*
			 * Info addAccess = AccessControll.checkAccess("insertHod", "hodList", "0", "1",
			 * "0", "0", newModuleList); if (addAccess.isError() == true) { redirect =
			 * "redirect:/accessDenied"; } else {
			 */

			if (hodId > 0) {

				Hod hod = new Hod();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				hod.setContactNo(request.getParameter("hod_mob"));
				hod.setDelStatus(1);
				// hod.setDeptId(Integer.parseInt(request.getParameter("hod_dept_id")));
				String[] deptIds = request.getParameterValues("hod_dept_id");
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < deptIds.length; i++) {
					sb = sb.append(deptIds[i] + ",");

				}
				String deptIdList = sb.toString();
				deptIdList = deptIdList.substring(0, deptIdList.length() - 1);
				System.err.println("deptIdList " + deptIdList);
				hod.setDeptId(deptIdList);

				hod.setEditBy(0);
				hod.setEmail(request.getParameter("hod_email"));
				hod.setExInt1(1);
				hod.setExInt2(2);
				hod.setExVar1("NA");
				hod.setExVar2("NA");
				hod.setHighestQualificationId(Integer.parseInt(request.getParameter("hod_quolf")));

				hod.setHodName(request.getParameter("hod_name"));
				hod.setInstituteId(userObj.getGetData().getUserInstituteId());
				hod.setIsActive(1);
				hod.setIsEnrollSystem(0);
				hod.setMakerDate(curDateTime);
				hod.setMakerId(userObj.getUserId());
				hod.setUpdateDatetime(curDateTime);

				Hod changeHod = rest.postForObject(Constants.url + "saveHod", hod, Hod.class);

				if (changeHod != null) {
					System.out.println(changeHod.toString());
					System.out.println("In Null" + hodId);
					map = new LinkedMultiValueMap<String, Object>();
					map.add("hodId", hodId);

					Info info = rest.postForObject(Constants.url + "/updateHodStatus", map, Info.class);
					System.out.println("info" + info.toString());

					map = new LinkedMultiValueMap<String, Object>();
					map.add("regPrimaryKey", hodId);
					map.add("userType", 3);

					info = rest.postForObject(Constants.url + "/blockPreviousHodRecord", map, Info.class);

					System.out.println("block previous record" + info.toString());
					//
				}

			}
			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/hodList";
			else
				redirect = "redirect:/hodRegistration";

		} catch (Exception e) {
			System.err.println("Exce in save dept  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;

	}

	/*****************************************************************************************************/
	@RequestMapping(value = "/viewInstitutes/{instId}", method = RequestMethod.GET)
	public ModelAndView viewInstitutes(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int instId) {
		ModelAndView model = new ModelAndView();
		MultiValueMap<String, Object> map = null;
		String a = null;
		try {
			HttpSession session = request.getSession();
			/*
			 * List<ModuleJson> newModuleList = (List<ModuleJson>)
			 * session.getAttribute("newModuleList"); Info view =
			 * AccessControll.checkAccess("showPendingInstitute", "showPendingInstitute",
			 * "1", "0", "0", "0", newModuleList);
			 * 
			 * if (view.isError() == true) {
			 * 
			 * model = new ModelAndView("accessDenied");
			 * 
			 * } else {
			 */

			model = new ModelAndView("showInstitute");
			// int instituteId = Integer.parseInt(request.getParameter("instId"));
			System.out.println("id are" + instId);

			map = new LinkedMultiValueMap<String, Object>();
			map.add("instId", instId);

			Institute showInst = rest.postForObject(Constants.url + "/showInstituteData", map, Institute.class);

			model.addObject("showInst", showInst);
			/*
			 * Info delete = AccessControll.checkAccess("showPendingInstitute",
			 * "showPendingInstitute", "0", "0", "0", "1", newModuleList);
			 * 
			 * 
			 * 
			 * }
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/deleteInst/{instId}", method = RequestMethod.GET)
	public String deleteInstitutes(@PathVariable("instId") int instId, HttpServletRequest request) {

		String a = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showPendingInstitute", "showPendingInstitute", "0", "0", "0", "1",
					newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			} else {

				Info inf = new Info();
				System.out.println("Id:" + instId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instId", instId);
				Info inst = rest.postForObject(Constants.url + "/deleteInstituteById", map, Info.class);

				a = "redirect:/showPendingInstitute";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}
	
	
	
		
}
