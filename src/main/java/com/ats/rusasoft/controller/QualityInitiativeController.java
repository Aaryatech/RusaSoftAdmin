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
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.QualityInitiative;

@Controller
@Scope("session")
public class QualityInitiativeController {

	RestTemplate rest = new RestTemplate();

	MultiValueMap<String, Object> map = null;

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
				model.addObject("title", "Add Quality Initiative");

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

			Info editAccess = AccessControll.checkAccess("insertDept", "showDeptList", "1", "0", "0", "0",
					newModuleList);

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

	@RequestMapping(value = "/showInternalQualityInitiative", method = RequestMethod.GET)
	public ModelAndView showInternalQualityInitiative(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/internalQuality");
		try {

			model.addObject("title", "Internal Quality Initiatives");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddInternalQualityInitiative", method = RequestMethod.GET)
	public ModelAndView showAddInternalQualityInitiative(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_internal_quality");
		try {

			model.addObject("title", "Add Internal Quality Initiatives");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// deleteQualiInit

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

}
