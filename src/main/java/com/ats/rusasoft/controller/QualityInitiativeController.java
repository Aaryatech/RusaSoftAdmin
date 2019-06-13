package com.ats.rusasoft.controller;

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.GetInstTrainTeachDetail;
import com.ats.rusasoft.model.instprofile.GetInstituteQuality;
import com.ats.rusasoft.model.instprofile.InstituteQuality;
import com.ats.rusasoft.model.instprofile.InstituteTraining;
import com.ats.rusasoft.model.instprofile.QualityInitiative;

@Controller
@Scope("session")
public class QualityInitiativeController {

	RestTemplate rest = new RestTemplate();

	MultiValueMap<String, Object> map = null;

	// Master
	@RequestMapping(value = "/showAddQualityInitiative", method = RequestMethod.GET)
	public ModelAndView showAddQualityInitiative(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showAddQualityInitiative", "showAddQualityInitiative", "1",
					"0", "0", "0", newModuleList);

			if (viewAccess.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/add_quality_initiative");
				model.addObject("title", "Quality Initiatives");

				Info addAccess = AccessControll.checkAccess("showAddQualityInitiative", "showAddQualityInitiative", "0",
						"1", "0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showAddQualityInitiative", "showAddQualityInitiative",
						"0", "0", "1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showAddQualityInitiative", "showAddQualityInitiative",
						"0", "0", "0", "1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false) {
					System.err.println("Edit acce ==0");
					model.addObject("editAccess", 0);
				}

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				QualityInitiative[] instArray = rest.getForObject(Constants.url + "getQualityInitiativeList",
						QualityInitiative[].class);
				List<QualityInitiative> qualInintList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("qualInintList " + qualInintList.toString());

				model.addObject("qualInintList", qualInintList);
			} else {

				model = new ModelAndView("accessDenied");

			}

		} catch (Exception e) {
			System.err.println("Exce in show Qua ini " + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// insertQualityInitiative
	// Master
	@RequestMapping(value = "/insertQualityInitiative", method = RequestMethod.POST)
	public String insertQualityInitiative(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertQualityInitiative");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("insertQualityInitiative", "showAddQualityInitiative", "1",
					"0", "0", "0", newModuleList);

			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				QualityInitiative qualInit = new QualityInitiative();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());
				int qualityInitiativeId = 0;
				try {
					qualityInitiativeId = Integer.parseInt(request.getParameter("qual_inti_id"));
				} catch (Exception e) {
					qualityInitiativeId = 0;
				}
				qualInit.setQualityInitiativeId(qualityInitiativeId);
				qualInit.setQualityInitiativeName(request.getParameter("quality_initiative_name"));
				qualInit.setDelStatus(1);
				String exVar1 = "NA";
				qualInit.setExVar1(exVar1);
				qualInit.setIsActive(1);
				qualInit.setMakerUserId(userObj.getUserId());// get from Session
				qualInit.setMakerDatetime(curDateTime);

				QualityInitiative qualInRes = rest.postForObject(Constants.url + "saveQualityInitiative", qualInit,
						QualityInitiative.class);

				if(qualInRes!=null) {
					session.setAttribute("successMsg", "New Record Saved Sucessfully");
				}
				else {
					session.setAttribute("successMsg", "Record Not Saved");
				}
				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showAddQualityInitiative";
				else
					redirect = "redirect:/showAddQualityInitiative";
			}
		} catch (Exception e) {
			System.err.println("Exce in save saveQualityInitiative  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	// qualityInitiativeId Ajax
	// Master
	@RequestMapping(value = "/getQualityInitiativeById", method = RequestMethod.GET)
	public @ResponseBody QualityInitiative qualityInitiativeId(HttpServletRequest request,
			HttpServletResponse response) {
		QualityInitiative qualInt = null;

		try {
			int qualityInitiativeId = Integer.parseInt(request.getParameter("qualityInitiativeId"));
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("qualityInitiativeId", qualityInitiativeId);
			qualInt = rest.postForObject(Constants.url + "getQualityInitiativeById", map, QualityInitiative.class);

		} catch (Exception e) {
			System.err.println("Ex in  Ajax qualityInitiativeId ");
		}
		return qualInt;

	}

	// deleteQualiInit
	// Master
	@RequestMapping(value = "/deleteQualiInit/{qualityInitiativeId}", method = RequestMethod.GET)
	public String deleteQualiInit(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int qualityInitiativeId) {
		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("deleteQualiInit/{hodId}", "showAddQualityInitiative", "0",
					"0", "0", "1", newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (qualityInitiativeId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("accOffIds");
					System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String quaInitList = sb.toString();
					quaInitList = quaInitList.substring(0, quaInitList.length() - 1);

					map.add("qualityInitiativeIdList", quaInitList);
				} else {

					System.err.println("Single Record delete ");
					map.add("qualityInitiativeIdList", qualityInitiativeId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteQualiInitiatives", map, Info.class);

				redirect = "redirect:/showAddQualityInitiative";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteQualiInit at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect;

	}

	////////////////
	// Txn
	@RequestMapping(value = "/showInternalQualityInitiative", method = RequestMethod.GET)
	public ModelAndView showInternalQualityInitiative(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("instituteInfo/IQAC/internalQuality");
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showInternalQualityInitiative",
					"showInternalQualityInitiative", "1", "0", "0", "0", newModuleList);

			if (viewAccess.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/internalQuality");
				model.addObject("title", "Institute Internal Quality Initiatives");

				Info addAccess = AccessControll.checkAccess("showInternalQualityInitiative",
						"showInternalQualityInitiative", "0", "1", "0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showInternalQualityInitiative",
						"showInternalQualityInitiative", "0", "0", "1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showInternalQualityInitiative",
						"showInternalQualityInitiative", "0", "0", "0", "1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("instId", userObj.getGetData().getUserInstituteId());
				map.add("yearId", session.getAttribute("acYearId"));

				GetInstituteQuality[] resArray = rest.postForObject(Constants.url + "getInstituteQualityList", map,
						GetInstituteQuality[].class);
				List<GetInstituteQuality> instQualList = new ArrayList<>(Arrays.asList(resArray));

				// System.err.println("instQualList " + instQualList.toString());

				model.addObject("instQualList", instQualList);

			} else {

				model = new ModelAndView("accessDenied");

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddInternalQualityInitiative", method = RequestMethod.GET)
	public ModelAndView showAddInternalQualityInitiative(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_internal_quality");
		try {

			model.addObject("title", "Add Institute Internal Quality Initiatives");
			InstituteQuality editQuality = new InstituteQuality();
			model.addObject("editQuality", editQuality);
			
			QualityInitiative[] instArray = rest.getForObject(Constants.url + "getQualityInitiativeList",
					QualityInitiative[].class);
			List<QualityInitiative> qualInintList = new ArrayList<>(Arrays.asList(instArray));
			
			model.addObject("qualInintList", qualInintList);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// insertInstQuaInitiative
	// dds
	// insert institute Quality ini..Sac
	@RequestMapping(value = "/insertInstQuaInitiative", method = RequestMethod.POST)
	public String insertInstQuaInitiative(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertTeachTraing");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info addAccess = AccessControll.checkAccess("insertInstQuaInitiative", "showInternalQualityInitiative", "0",
					"1", "0", "0", newModuleList);
			if (addAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				int qualityId = 0;
				try {
					qualityId = Integer.parseInt(request.getParameter("qualityId"));
				} catch (Exception e) {
					qualityId = 0;
				}

				InstituteQuality instQuality = new InstituteQuality();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				instQuality.setQualityFromdt(DateConvertor.convertToYMD(request.getParameter("fromDate")));
				instQuality.setQualityTodt(DateConvertor.convertToYMD(request.getParameter("toDate")));
				int participant = 0 ;
				participant = Integer.parseInt(request.getParameter("no_of_participant"));
				System.out.println("DATA="+participant);
				if(participant == 0 ) {
				instQuality.setQualityPcount(0);
				}else {
					instQuality.setQualityPcount(participant);
				}
				instQuality.setQualityId(qualityId);
				instQuality.setQualityInitiativeId(Integer.parseInt(request.getParameter("qualityInitId")));

				int yearId = (int) session.getAttribute("acYearId");

				instQuality.setYearId(yearId);
				instQuality.setInstituteId(userObj.getGetData().getUserInstituteId());

				instQuality.setDelStatus(1);
				instQuality.setIsActive(1);
				instQuality.setExInt1(1);
				instQuality.setExInt2(1);
				instQuality.setExVar1(request.getParameter("qltyInitiative"));
				instQuality.setExVar2("NA");

				instQuality.setMakerDatetime(curDateTime);
				instQuality.setMakerUserId(userObj.getUserId());

				InstituteQuality insertQualRes = rest.postForObject(Constants.url + "saveInstituteQuality", instQuality,
						InstituteQuality.class);

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showInternalQualityInitiative";
				else
					redirect = "redirect:/showAddInternalQualityInitiative";

			}
		} catch (Exception e) {
			System.err.println("Exce in save insertInstQuaInitiative  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;

	}

	// showEditInstQuality Edit Page

	@RequestMapping(value = "/showEditInstQuality", method = RequestMethod.POST)
	public ModelAndView showEditInstTraining(HttpServletRequest request, HttpServletResponse response) {
		// hfg

		ModelAndView model = null;// new ModelAndView("instituteInfo/IQAC/add_prof_dev");

		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("showEditInstQuality", "showInternalQualityInitiative", "0",
					"0", "1", "0", newModuleList);
			if (editAccess.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/add_internal_quality");
				model.addObject("title", "Edit Institute Internal Quality Initiatives");

				int qualityId = Integer.parseInt(request.getParameter("qualityId"));

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("qualityId", qualityId);

				GetInstituteQuality editInstQuality = rest.postForObject(Constants.url + "getInstituteQualityById", map,
						GetInstituteQuality.class);
				System.err.println("List"+editInstQuality);
				model.addObject("editQuality", editInstQuality);

				QualityInitiative[] instArray = rest.getForObject(Constants.url + "getQualityInitiativeList",
						QualityInitiative[].class);
				List<QualityInitiative> qualInintList = new ArrayList<>(Arrays.asList(instArray));

				model.addObject("qualInintList", qualInintList);

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {
			System.err.println("Exce in showing /showEditInstQuality " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	// deleteInstiQuality/${instTrain.qualityId}

	@RequestMapping(value = "/deleteInstiQuality/{qualityId}", method = RequestMethod.GET)
	public String deleteInstiQuality(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int qualityId) {
		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("deleteInstiQuality/{qualityId}",
					"showInternalQualityInitiative", "0", "0", "0", "1", newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (qualityId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("qualityId");

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String qualityIdList = sb.toString();
					qualityIdList = qualityIdList.substring(0, qualityIdList.length() - 1);

					map.add("qualityIdList", qualityIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("qualityIdList", qualityId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteInstQualities", map, Info.class);

				redirect = "redirect:/showInternalQualityInitiative";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteInstiQuality at QualInitiave  Contr " + e.getMessage());
			e.printStackTrace();

		}

		return redirect;

	}
}
