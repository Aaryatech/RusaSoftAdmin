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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.EContentDevFacility;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.InstResearchDevMous;
import com.ats.rusasoft.model.LibBookPurchase;
import com.ats.rusasoft.model.MExtActList;
import com.ats.rusasoft.model.MExtensionActivity;
import com.ats.rusasoft.model.ResearchDevMou;
import com.ats.rusasoft.model.TExtensionActivity;
import com.ats.rusasoft.model.TFacultyStudLinkage;
import com.ats.rusasoft.model.TNeighbourhoodCommActivities;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class ResearchAndInnovation {
	
	RestTemplate rest = new RestTemplate();
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());
	String redirect = null;
	
	MultiValueMap<String, Object> map = null;
	@RequestMapping(value = "/showExtensionActivity", method = RequestMethod.GET)
	public ModelAndView showExtensionActivity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showExtensionActivity", "showExtensionActivity", "1", "0", "0", "0",
				newModuleList);

		if (view.isError() == true) {

			model = new ModelAndView("accessDenied");

		} else {
			model = new ModelAndView("resrch&innovatn/showExtensionActList");
			map = new LinkedMultiValueMap<String, Object>();
			map.add("instituteId", instituteId);
						
			MExtActList[] neighbourArr =  rest.postForObject(Constants.url+"/getAllExtActivities", map, MExtActList[].class);
			List<MExtActList> mExtActList = new ArrayList<>(Arrays.asList(neighbourArr));
			//System.out.println("Lists="+mExtActList);
			
			model.addObject("mExtActList", mExtActList);
			model.addObject("title", "Extension Activity");

			Info add = AccessControll.checkAccess("showExtensionActivity", "showExtensionActivity", "0", "1", "0", "0",
					newModuleList);
			Info edit = AccessControll.checkAccess("showExtensionActivity", "showExtensionActivity", "0", "0", "1", "0",
					newModuleList);
			Info delete = AccessControll.checkAccess("showExtensionActivity", "showExtensionActivity", "0", "0", "0",
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

			System.err.println("exception In showExtensionActivity at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/newExtensionActivity", method = RequestMethod.GET)
	public ModelAndView newExtensionActivity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		TExtensionActivity tExtAct = new TExtensionActivity();
		try {
			Info view = AccessControll.checkAccess("newExtensionActivity", "showExtensionActivity", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
					
				  	
					model = new ModelAndView("resrch&innovatn/addExtensionActivity");
					MExtensionActivity[] mExtArr = rest.getForObject(Constants.url+"/getAllExtensionActivities", MExtensionActivity[].class);
					List<MExtensionActivity> mExtList = new ArrayList<>(Arrays.asList(mExtArr));
					System.out.println("List="+mExtList);
					model.addObject("mExtList", mExtList);
					
					model.addObject("tExtAct", tExtAct);
					model.addObject("title", "Add Extension Activity");
			}
		} catch (Exception e) {

			System.err.println("exception In newExtensionActivity at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/insertExstensionActivity", method = RequestMethod.POST)
	public String insertExstensionActivity(HttpServletRequest request, HttpServletResponse response) {
	
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");
			
			TExtensionActivity tExtAct = new TExtensionActivity();
			
			tExtAct.setInstExtensionActId(Integer.parseInt(request.getParameter("inst_extension_act_id")));
			int actId = Integer.parseInt(request.getParameter("activity_id"));
			tExtAct.setExtensionActivityId(actId);
			tExtAct.setInstId(instituteId);
			tExtAct.setAcYearId(acadYear);
			if(actId == 0) {
			tExtAct.settActivityTitle(request.getParameter("activity_name"));
			tExtAct.setExInt1(1);
			}else {
				tExtAct.settActivityTitle("NA");
				tExtAct.setExInt1(0);
			}
			tExtAct.setNoOfStudParticipated(Integer.parseInt(request.getParameter("no_student_part")));
			tExtAct.setNoOfStudInInst(Integer.parseInt(request.getParameter("student_in_institute")));
			tExtAct.setNoOfFacultyParticipated(Integer.parseInt(request.getParameter("no_faculty")));
			tExtAct.setNoOfFacultyInInst(Integer.parseInt(request.getParameter("faculty_in_inst")));
			tExtAct.setDelStatus(1);
			tExtAct.setIsActive(1);
			tExtAct.setMakerUserId(userId);
			tExtAct.setMakerDatetime(curDateTime);
			tExtAct.setExVar1("NA");
			tExtAct.setExInt2(0);
			tExtAct.setExVar2("NA");
			System.out.println(tExtAct.toString());
			
			TExtensionActivity saveExtActivity = rest.postForObject(Constants.url+"/saveExtActivity", tExtAct, TExtensionActivity.class);
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/showExtensionActivity";
		
	}
	
	
	@RequestMapping(value = "/editTExtActivity/{extActId}", method = RequestMethod.GET)
	public ModelAndView editLibBook(@PathVariable("extActId") int extActId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("resrch&innovatn/addExtensionActivity");;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editTExtActivity/{extActId}", "showExtensionActivity", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");
			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("extActId", extActId);

				TExtensionActivity extAct = rest.postForObject(Constants.url + "/getExtActivityById", map, TExtensionActivity.class);
				model.addObject("tExtAct", extAct);
				
				MExtensionActivity[] mExtArr = rest.getForObject(Constants.url+"/getAllExtensionActivities", MExtensionActivity[].class);
				List<MExtensionActivity> mExtList = new ArrayList<>(Arrays.asList(mExtArr));
				System.out.println("List="+mExtList);
				model.addObject("mExtList", mExtList);

				model.addObject("title", "Edit Extension Activity");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return model;

	}
	
	@RequestMapping(value = "/deleteTExtActivity/{extActId}", method = RequestMethod.GET)
	public String deleteTExtActivity(@PathVariable("extActId") int extActId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String a = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("deleteTExtActivity/{extActId}", "showExtensionActivity", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("extActId", extActId);

				TExtensionActivity delAct = rest.postForObject(Constants.url + "/deleteTExtActById", map, TExtensionActivity.class);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return "redirect:/showExtensionActivity";

	}
	
	@RequestMapping(value = "/deleteSelExtsnActivities/{exActId}", method = RequestMethod.GET)
	public String deleteSelExtsnActivities(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int exActId) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteSelExtsnActivities/{exActId}", "showExtensionActivity", "0", "0", "0",
				"1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (exActId == 0) {

					System.err.println("Multiple records delete ");
					String[] exActIds = request.getParameterValues("exActId");
					System.out.println("id are" + exActIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < exActIds.length; i++) {
						sb = sb.append(exActIds[i] + ",");

					}
					String exActIdsList = sb.toString();
					exActIdsList = exActIdsList.substring(0, exActIdsList.length() - 1);

					map.add("exActIdsList", exActIdsList);
				} else {

					System.err.println("Single Record delete ");
					map.add("exActIdsList", exActId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelExtAct", map, Info.class);

				a = "redirect:/showExtensionActivity";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}
	
	@RequestMapping(value = "/showInstResrchDevMous", method = RequestMethod.GET)
	public ModelAndView showInstResrchDevMous(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showInstResrchDevMous", "showInstResrchDevMous", "1", "0", "0", "0",
				newModuleList);

		if (view.isError() == true) {

			model = new ModelAndView("accessDenied");

		} else {
			model = new ModelAndView("resrch&innovatn/showInstResrchDevMous");
			map = new LinkedMultiValueMap<String, Object>();
			System.out.println("Inst="+instituteId);
			map.add("instituteId", instituteId);
						
			InstResearchDevMous[] rsrchMouArr =  rest.postForObject(Constants.url+"/getAllRsrchDevMous", map, InstResearchDevMous[].class);
			List<InstResearchDevMous> rsrchMouList = new ArrayList<>(Arrays.asList(rsrchMouArr));
			System.out.println("Lists="+rsrchMouList);
			
			model.addObject("rsrchMouList", rsrchMouList);
			model.addObject("title", "Institute Research Development MOUs");

			Info add = AccessControll.checkAccess("showInstResrchDevMous", "showInstResrchDevMous", "0", "1", "0", "0",
					newModuleList);
			Info edit = AccessControll.checkAccess("showInstResrchDevMous", "showInstResrchDevMous", "0", "0", "1", "0",
					newModuleList);
			Info delete = AccessControll.checkAccess("showInstResrchDevMous", "showInstResrchDevMous", "0", "0", "0",
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

			System.err.println("exception In showInstResrchDevMous" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/newInstResrchDevMous", method = RequestMethod.GET)
	public ModelAndView newInstResrchDevMous(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		InstResearchDevMous tMous = new InstResearchDevMous();
		try {
			Info view = AccessControll.checkAccess("newInstResrchDevMous", "showInstResrchDevMous", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
					
				  	
					model = new ModelAndView("resrch&innovatn/addInstResrchDevMous");
					ResearchDevMou[] mouArr = rest.getForObject(Constants.url+"/getAllRsrchDevMous", ResearchDevMou[].class);
					List<ResearchDevMou> mouList = new ArrayList<>(Arrays.asList(mouArr));
					System.out.println("List="+mouList);
					model.addObject("mouList", mouList);
					
					model.addObject("tMous", tMous);
					model.addObject("title", "Add Institute Research Development MOUs");
			}
		} catch (Exception e) {

			System.err.println("exception In newExtensionActivity at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	

	@RequestMapping(value = "/insertResrchDevMou", method = RequestMethod.POST)
	public String insertResrchDevMou(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");
			
			InstResearchDevMous tMous = new InstResearchDevMous();
			
			tMous.setInstReasearchDevMouId(Integer.parseInt(request.getParameter("inst_reasearch_dev_mouId")));
			int mouId = Integer.parseInt(request.getParameter("activity_id"));
			tMous.setResearchDevMouId(mouId);
			tMous.setInstId(instituteId);
			tMous.setAcYearId(acadYear);
			if(mouId==0) {
			tMous.setMouTitle(request.getParameter("mou_name"));
			tMous.setExInt1(1);
			}else {
				tMous.setMouTitle("NA");
				tMous.setExInt1(0);
			}
			tMous.setOrgName(request.getParameter("org_name"));
			tMous.setMouSignedYear(request.getParameter("mou_signed_year"));
			tMous.setDurFromdt(request.getParameter("from_date"));
			tMous.setDurTodt(request.getParameter("to_date"));
			tMous.setActivitiesMou(request.getParameter("mou_avtivity"));
			tMous.setNoOfStudBenif(Integer.parseInt(request.getParameter("no_stud")));
			tMous.setNoOfStaffBenif(Integer.parseInt(request.getParameter("no_faculty")));
			tMous.setDelStatus(1);
			tMous.setIsActive(1);
			tMous.setMakerUserId(userId);
			tMous.setMakerDatetime(curDateTime);
			tMous.setExInt2(0);
			tMous.setExVar1("NA");
			tMous.setExVar2("NA");
			System.out.println(tMous.toString());
		
			InstResearchDevMous mou = rest.postForObject(Constants.url+"/savResrchDevMou", tMous, InstResearchDevMous.class);
				
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/showInstResrchDevMous";
		
		
	}
	
	@RequestMapping(value = "/editRsrchMou/{mouRsrchDevId}", method = RequestMethod.GET)
	public ModelAndView editRsrchMou(@PathVariable("mouRsrchDevId") int mouRsrchDevId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("resrch&innovatn/addInstResrchDevMous");;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editRsrchMou/{rsrchId}", "showInstResrchDevMous", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");
			}

			else {
				
				System.err.println("IDss="+mouRsrchDevId);
				map = new LinkedMultiValueMap<>();
				map.add("mouRsrchDevId", mouRsrchDevId);

				InstResearchDevMous rsrchMou = rest.postForObject(Constants.url + "/getMouRsrchDevById", map, InstResearchDevMous.class);
				model.addObject("tMous", rsrchMou);
				
				ResearchDevMou[] mouArr = rest.getForObject(Constants.url+"/getAllRsrchDevMous", ResearchDevMou[].class);
				List<ResearchDevMou> mouList = new ArrayList<>(Arrays.asList(mouArr));
				System.out.println("List="+mouList);
				model.addObject("mouList", mouList);

				model.addObject("title", "Edit Institute Research Development MOUs");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return model;

	}
	
	@RequestMapping(value = "/deleteRsrchMou/{mouRsrchDevId}", method = RequestMethod.GET)
	public String deleteRsrchMou(@PathVariable("mouRsrchDevId") int mouRsrchDevId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String a = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("deleteRsrchMou/{mouRsrchDevId}", "showInstResrchDevMous", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("mouRsrchDevId", mouRsrchDevId);

				InstResearchDevMous delAct = rest.postForObject(Constants.url + "/deleteRsrchMouById", map, InstResearchDevMous.class);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return "redirect:/showInstResrchDevMous";

	}
	
	@RequestMapping(value = "/deleteSelMouResrchDev/{mouIds}", method = RequestMethod.GET)
	public String deleteSelMouResrchDev(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int mouIds) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteSelMouResrchDev/{mouIds}", "showInstResrchDevMous", "0", "0", "0",
				"1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (mouIds == 0) {

					System.err.println("Multiple records delete ");
					String[] mouRsrchDevIds = request.getParameterValues("mouIds");
					System.out.println("id are" + mouRsrchDevIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < mouRsrchDevIds.length; i++) {
						sb = sb.append(mouRsrchDevIds[i] + ",");

					}
					String mouRsrchDevIdList = sb.toString();
					mouRsrchDevIdList = mouRsrchDevIdList.substring(0, mouRsrchDevIdList.length() - 1);

					map.add("mouRsrchDevIdList", mouRsrchDevIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("mouRsrchDevIdList", mouIds);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelResearchMous", map, Info.class);

				a = "redirect:/showInstResrchDevMous";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}
	
	
	/********************************************************************************************************/
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
	
	
	
	
}
