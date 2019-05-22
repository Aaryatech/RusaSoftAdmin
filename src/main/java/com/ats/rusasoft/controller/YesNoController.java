package com.ats.rusasoft.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.Names;
import com.ats.rusasoft.master.model.prodetail.GetStudAdmLocwise;
import com.ats.rusasoft.master.model.prodetail.Location;
import com.ats.rusasoft.master.model.prodetail.NameIdBean;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.InstituteYesNo;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.ProgramActivity;
import com.ats.rusasoft.model.SectionList;
import com.ats.rusasoft.model.StakeholderFb;
import com.ats.rusasoft.model.YesNoMaster;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.InstStakeholderFeedback;

@Controller
@Scope("session")
public class YesNoController {

	RestTemplate restTemplate = new RestTemplate();
	List<YesNoMaster> yesNoMasterList = new ArrayList<>();
	List<YesNoMaster> yesNoMasterListPage2 = new ArrayList<>();
	List<YesNoMaster> yesNoMasterListPage3 = new ArrayList<>();
	List<YesNoMaster> yesNoMasterListPage4 = new ArrayList<>();
	List<YesNoMaster> yesNoMasterListPage5 = new ArrayList<>();
	List<StakeholderFb> stfbList = new ArrayList<>();
	List<InstituteYesNo> instituteYesNoList = new ArrayList<>();
	List<InstituteYesNo> instituteYesNoListPage2 = new ArrayList<>();
	List<InstituteYesNo> instituteYesNoListPage3 = new ArrayList<>();
	List<InstituteYesNo> instituteYesNoListPage4 = new ArrayList<>();
	List<InstituteYesNo> instituteYesNoListPage5 = new ArrayList<>();

	List<InstituteYesNo> instituteYesNoTab1List = new ArrayList<>();
	List<InstituteYesNo> instituteYesNoTab2List = new ArrayList<>();
	List<InstituteYesNo> instituteYesNoTab3List = new ArrayList<>();
	List<InstStakeholderFeedback> proofList = new ArrayList<>();

	List<InstituteYesNo> instituteYesNoTab4List = new ArrayList<>();
	List<InstituteYesNo> instituteYesNoTab5List = new ArrayList<>();
	List<InstituteYesNo> instituteYesNoTab6List = new ArrayList<>();
	List<InstStakeholderFeedback> tempFb = new ArrayList<>();

	@RequestMapping(value = "/selectYestNo", method = RequestMethod.GET)
	public ModelAndView showAddStudAddmitLocWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("yesno/YesNoValues");
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");

			model.addObject("title", "E-Governance");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("pageCode", "PAGE1");
			YesNoMaster[] yesNoMaster = restTemplate.postForObject(Constants.url + "/getYesNoList", map,
					YesNoMaster[].class);
			yesNoMasterList = new ArrayList<>(Arrays.asList(yesNoMaster));

			SectionList[] section = restTemplate.postForObject(Constants.url + "/getSectionListByPageCode", map,
					SectionList[].class);
			List<SectionList> sectionList = new ArrayList<>(Arrays.asList(section));

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("pageCode", "PAGE1");
			InstituteYesNo[] instituteYesNo = restTemplate
					.postForObject(Constants.url + "/getInstituteYesNoListByInstituteId", map, InstituteYesNo[].class);
			instituteYesNoList = new ArrayList<>(Arrays.asList(instituteYesNo));

			/*
			 * System.out.println("yesNoMasterList " + yesNoMasterList);
			 * System.out.println("sectionList " + sectionList);
			 * System.out.println("instituteYesNoList " + instituteYesNoList);
			 */

			model.addObject("yesNoMasterList", yesNoMasterList);
			model.addObject("sectionList", sectionList);
			model.addObject("instituteYesNoList", instituteYesNoList);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/selectYestNoLib", method = RequestMethod.GET)
	public ModelAndView selectYestNoLib(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("selectYestNoLib", "selectYestNoLib", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("yesno/yesNoLib");
				model.addObject("title", Names.infra_budget_list);
				model.addObject("budRupees", Names.Rupees);
				Info addAccess = AccessControll.checkAccess("selectYestNoLib", "selectYestNoLib", "0", "1", "0", "0",
						newModuleList);

				Info editAccess = AccessControll.checkAccess("selectYestNoLib", "selectYestNoLib", "0", "0", "1", "0",
						newModuleList);
				Info deleteAccess = AccessControll.checkAccess("selectYestNoLib", "selectYestNoLib", "0", "0", "0", "1",
						newModuleList);
				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int acYearId = (Integer) session.getAttribute("acYearId");

				model.addObject("title", "FACILITIES IN Library");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("pageCode", "PAGE6");
				YesNoMaster[] yesNoMaster = restTemplate.postForObject(Constants.url + "/getYesNoList", map,
						YesNoMaster[].class);
				yesNoMasterList = new ArrayList<>(Arrays.asList(yesNoMaster));

				SectionList[] section = restTemplate.postForObject(Constants.url + "/getSectionListByPageCode", map,
						SectionList[].class);
				List<SectionList> sectionList = new ArrayList<>(Arrays.asList(section));

				map = new LinkedMultiValueMap<>();
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				map.add("yearId", acYearId);
				map.add("pageCode", "PAGE6");
				InstituteYesNo[] instituteYesNo = restTemplate.postForObject(
						Constants.url + "/getInstituteYesNoListByInstituteId", map, InstituteYesNo[].class);
				instituteYesNoList = new ArrayList<>(Arrays.asList(instituteYesNo));

				/*
				 * System.out.println("yesNoMasterList " + yesNoMasterList);
				 * System.out.println("sectionList " + sectionList);
				 * System.out.println("instituteYesNoList " + instituteYesNoList);
				 */

				model.addObject("yesNoMasterList", yesNoMasterList);
				model.addObject("sectionList", sectionList);
				model.addObject("instituteYesNoList", instituteYesNoList);
			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In selectYestNoLib at YN Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/selectYestNoInfra", method = RequestMethod.GET)
	public ModelAndView selectYestNoInfra(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("yesno/yesNoInfra");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("selectYestNoInfra", "selectYestNoInfra", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("yesno/yesNoInfra");
				model.addObject("title", Names.infra_budget_list);
				model.addObject("budRupees", Names.Rupees);
				Info addAccess = AccessControll.checkAccess("selectYestNoInfra", "selectYestNoInfra", "0", "1", "0",
						"0", newModuleList);

				Info editAccess = AccessControll.checkAccess("selectYestNoInfra", "selectYestNoInfra", "0", "0", "1",
						"0", newModuleList);
				Info deleteAccess = AccessControll.checkAccess("selectYestNoInfra", "selectYestNoInfra", "0", "0", "0",
						"1", newModuleList);
				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int acYearId = (Integer) session.getAttribute("acYearId");

				model.addObject("title", "Campus Infrastructure And Differently abled friendliness");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("pageCode", "PAGE7");
				YesNoMaster[] yesNoMaster = restTemplate.postForObject(Constants.url + "/getYesNoList", map,
						YesNoMaster[].class);
				yesNoMasterList = new ArrayList<>(Arrays.asList(yesNoMaster));

				SectionList[] section = restTemplate.postForObject(Constants.url + "/getSectionListByPageCode", map,
						SectionList[].class);
				List<SectionList> sectionList = new ArrayList<>(Arrays.asList(section));

				map = new LinkedMultiValueMap<>();
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				map.add("yearId", acYearId);
				map.add("pageCode", "PAGE7");
				InstituteYesNo[] instituteYesNo = restTemplate.postForObject(
						Constants.url + "/getInstituteYesNoListByInstituteId", map, InstituteYesNo[].class);
				instituteYesNoList = new ArrayList<>(Arrays.asList(instituteYesNo));

				/*
				 * System.out.println("yesNoMasterList " + yesNoMasterList);
				 * System.out.println("sectionList " + sectionList);
				 * System.out.println("instituteYesNoList " + instituteYesNoList);
				 */

				model.addObject("yesNoMasterList", yesNoMasterList);
				model.addObject("sectionList", sectionList);
				model.addObject("instituteYesNoList", instituteYesNoList);
			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In selectYestNoInfra at YN Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/submitYesNo", method = RequestMethod.POST)
	public String submitYesNo(HttpServletRequest request, HttpServletResponse response) {
		int selectYestNoLib = 0;
		int selectYestNoInfra = 0;
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int count = Integer.parseInt(request.getParameter("srindex"));

			List<InstituteYesNo> save = new ArrayList<>();

			for (int i = 0; i < yesNoMasterList.size(); i++) {

				int value = Integer.parseInt(request.getParameter("yesNo" + yesNoMasterList.get(i).getYesnoId()));
				int find = 0;

				for (int j = 0; j < instituteYesNoList.size(); j++) {

					if (instituteYesNoList.get(j).getYesnoId() == yesNoMasterList.get(i).getYesnoId()) {
						find = 1;

						if (value == 1) {
							instituteYesNoList.get(j).setMakerUserId(userObj.getUserId());
							instituteYesNoList.get(j).setMakerDatetime(sf.format(date));
							instituteYesNoList.get(j).setInstYesnoResponse(
									request.getParameter("respnsevalue" + yesNoMasterList.get(i).getYesnoId()));
						} else {

							instituteYesNoList.get(j).setDelStatus(0);
						}

					}
				}

				if (find == 0 && value == 1) {

					InstituteYesNo instituteYesNo = new InstituteYesNo();
					instituteYesNo.setDelStatus(1);
					instituteYesNo.setIsActive(1);
					instituteYesNo.setMakerUserId(userObj.getUserId());
					instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
					instituteYesNo.setMakerDatetime(sf.format(date));
					instituteYesNo.setYesnoPagecode(yesNoMasterList.get(i).getYesnoPagecode());
					instituteYesNo.setSectionCode(yesNoMasterList.get(i).getYesnoSeccode());
					instituteYesNo.setInstYesnoResponse(
							request.getParameter("respnsevalue" + yesNoMasterList.get(i).getYesnoId()));
					instituteYesNo.setYearId(acYearId);
					instituteYesNo.setYesnoId(yesNoMasterList.get(i).getYesnoId());
					instituteYesNoList.add(instituteYesNo);
				}

			}

			for (int j = 0; j < instituteYesNoList.size(); j++) {

				if (instituteYesNoList.get(j).getYesnoId() == 0) {
					int value = Integer.parseInt(
							request.getParameter("dynamicprevyesno" + instituteYesNoList.get(j).getInstYesnoId()));

					if (value == 1) {
						instituteYesNoList.get(j).setMakerUserId(userObj.getUserId());
						instituteYesNoList.get(j).setMakerDatetime(sf.format(date));
						instituteYesNoList.get(j).setInstYesnoResponse(request
								.getParameter("dynamicprevyesnovalue" + instituteYesNoList.get(j).getInstYesnoId()));
					} else {

						instituteYesNoList.get(j).setDelStatus(0);
					}
				}

			}

			if (count != 0) {
				for (int j = 1; j <= count; j++) {

					int value = Integer.parseInt(request.getParameter("dynamicyesno" + j));

					if (value == 1) {
						InstituteYesNo instituteYesNo = new InstituteYesNo();
						instituteYesNo.setDelStatus(1);
						instituteYesNo.setIsActive(1);
						instituteYesNo.setMakerUserId(userObj.getUserId());
						instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
						instituteYesNo.setMakerDatetime(sf.format(date));
						instituteYesNo.setYesnoPagecode("PAGE1");
						instituteYesNo.setSectionCode("other");
						instituteYesNo.setInstYesnoResponse(request.getParameter("dynamicyesnovalue" + j));
						instituteYesNo.setYesnoDynamicTitle(request.getParameter("otherTitleName" + j));
						instituteYesNo.setYearId(acYearId);
						instituteYesNoList.add(instituteYesNo);
					}
				}

			}
			InstituteYesNo[] instituteYesNo = restTemplate.postForObject(Constants.url + "/saveYesNo",
					instituteYesNoList, InstituteYesNo[].class);
			try {
				selectYestNoLib = Integer.parseInt(request.getParameter("selectYestNoLib"));
			} catch (Exception e) {
				selectYestNoLib = 0;
			}
			selectYestNoInfra = Integer.parseInt(request.getParameter("selectYestNoInfra"));

		} catch (Exception e) {

			selectYestNoInfra = 0;
			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}
		if (selectYestNoLib == 1) {
			return "redirect:/selectYestNoLib";
		} else if (selectYestNoInfra == 1) {
			return "redirect:/selectYestNoInfra";
		} else {
			return "redirect:/selectYestNo";
		}

	}

	@RequestMapping(value = "/fixedYesNo", method = RequestMethod.GET)
	public ModelAndView fixedYesNo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("yesno/fixedYesNo");
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");

			model.addObject("title", "Examination Related Grievances");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab1");
			InstituteYesNo[] instituteYesNo = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab1List = new ArrayList<>(Arrays.asList(instituteYesNo));

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab2");
			InstituteYesNo[] instituteYesN = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab2List = new ArrayList<>(Arrays.asList(instituteYesN));

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab3");
			InstituteYesNo[] arr = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab3List = new ArrayList<>(Arrays.asList(arr));

			model.addObject("instituteYesNoTab1List", instituteYesNoTab1List);
			model.addObject("instituteYesNoTab2List", instituteYesNoTab2List);
			model.addObject("instituteYesNoTab3List", instituteYesNoTab3List);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addTransperent", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> addTransperent(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String transperentspeficytext = request.getParameter("transperentspeficytext");

			int transId = Integer.parseInt(request.getParameter("transId"));

			if (transId == 0) {
				InstituteYesNo instituteYesNo = new InstituteYesNo();
				instituteYesNo.setDelStatus(1);
				instituteYesNo.setIsActive(1);
				instituteYesNo.setMakerUserId(userObj.getUserId());
				instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
				instituteYesNo.setMakerDatetime(sf.format(date));
				instituteYesNo.setYesnoPagecode("tab1");
				instituteYesNo.setSectionCode("tab1");
				instituteYesNo.setInstYesnoResponse(transperentspeficytext);
				instituteYesNo.setYesnoDynamicTitle("transpernt");
				instituteYesNo.setYearId(acYearId);

				System.out.println(instituteYesNo);
				InstituteYesNo resp = restTemplate.postForObject(Constants.url + "/saveYesNoSingle", instituteYesNo,
						InstituteYesNo.class);
			} else {
				System.err.println("In else Its Edit Call " + transperentspeficytext);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("id", transId);
				map.add("yesNoResponse", transperentspeficytext);

				Info resp = restTemplate.postForObject(Constants.url + "/editYesNoRecord", map, Info.class);

			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab1");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab1List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab1List;

	}

	// editTranspernt Ajax sachin for edit data show
	@RequestMapping(value = "/editTranspernt", method = RequestMethod.GET)
	public @ResponseBody InstituteYesNo editTransperntAjax(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("yesno/fixedYesNoSecond");
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
		int acYearId = (Integer) session.getAttribute("acYearId");
		// int instYesnoId = Integer.parseInt(request.getParameter("instYesnoId"));
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", Integer.parseInt(request.getParameter("instYesnoId")));

		map.add("instituteId", userObj.getGetData().getUserInstituteId());
		map.add("yearId", acYearId);
		// map.add("secCode", "tab1");
		InstituteYesNo editInstyn = restTemplate.postForObject(Constants.url + "/getInstituteYesNoById", map,
				InstituteYesNo.class);
		System.out.println("res=" + editInstyn.toString());

		model.addObject("editYN", editInstyn);
		return editInstyn;

	}

	@RequestMapping(value = "/editTranspernt/{instYesnoId}", method = RequestMethod.GET)
	public @ResponseBody ModelAndView editTranspernt(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("instYesnoId") int instYesnoId) {
		ModelAndView model = new ModelAndView("yesno/fixedYesNoSecond");
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
		int acYearId = (Integer) session.getAttribute("acYearId");
		// int instYesnoId = Integer.parseInt(request.getParameter("instYesnoId"));
		System.out.println("Id:" + instYesnoId);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", instYesnoId);

		map.add("instituteId", userObj.getGetData().getUserInstituteId());
		map.add("yearId", acYearId);
		// map.add("secCode", "tab1");
		InstituteYesNo editInstyn = restTemplate.postForObject(Constants.url + "/getInstituteYesNoById", map,
				InstituteYesNo.class);
		System.out.println("res=" + editInstyn.toString());

		model.addObject("editYN", editInstyn);
		return model;

	}

	@RequestMapping(value = "/deleteTranspernt", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> deleteTranspernt(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int instYesnoId = Integer.parseInt(request.getParameter("instYesnoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("id", instYesnoId);
System.err.println();

			Info resp = restTemplate.postForObject(Constants.url + "/deleteYesNoRecord", map, Info.class);

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab1");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab1List = new ArrayList<>(Arrays.asList(ary));
			
		
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab1List;

	}

	@RequestMapping(value = "/addTimeboundAddDive", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> addTimeboundAddDive(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String transperentspeficytext = request.getParameter("transperentspeficytext");

			int timeBoundId = Integer.parseInt(request.getParameter("timeBoundId"));

			if (timeBoundId == 0) {
				InstituteYesNo instituteYesNo = new InstituteYesNo();
				instituteYesNo.setDelStatus(1);
				instituteYesNo.setIsActive(1);
				instituteYesNo.setMakerUserId(userObj.getUserId());
				instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
				instituteYesNo.setMakerDatetime(sf.format(date));
				instituteYesNo.setYesnoPagecode("tab2");
				instituteYesNo.setSectionCode("tab2");
				instituteYesNo.setInstYesnoResponse(transperentspeficytext);
				instituteYesNo.setYesnoDynamicTitle("Time bound");
				instituteYesNo.setYearId(acYearId);

				System.out.println(instituteYesNo);
				InstituteYesNo resp = restTemplate.postForObject(Constants.url + "/saveYesNoSingle", instituteYesNo,
						InstituteYesNo.class);
			} else {
				System.err.println("In else Its Edit Call " + transperentspeficytext);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("id", timeBoundId);
				map.add("yesNoResponse", transperentspeficytext);

				Info resp = restTemplate.postForObject(Constants.url + "/editYesNoRecord", map, Info.class);

			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab2");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab2List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab2List;

	}

	@RequestMapping(value = "/deleteTimebound", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> deleteTimebound(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int instYesnoId = Integer.parseInt(request.getParameter("instYesnoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("id", instYesnoId);

			Info resp = restTemplate.postForObject(Constants.url + "/deleteYesNoRecord", map, Info.class);

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab2");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab2List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab2List;

	}

	@RequestMapping(value = "/addEfficient", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> addEfficient(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String transperentspeficytext = request.getParameter("transperentspeficytext");

			int efficientId = Integer.parseInt(request.getParameter("efficientId"));

			if (efficientId == 0) {
				InstituteYesNo instituteYesNo = new InstituteYesNo();
				instituteYesNo.setDelStatus(1);
				instituteYesNo.setIsActive(1);
				instituteYesNo.setMakerUserId(userObj.getUserId());
				instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
				instituteYesNo.setMakerDatetime(sf.format(date));
				instituteYesNo.setYesnoPagecode("tab3");
				instituteYesNo.setSectionCode("tab3");
				instituteYesNo.setInstYesnoResponse(transperentspeficytext);
				instituteYesNo.setYesnoDynamicTitle("Efficient");
				instituteYesNo.setYearId(acYearId);

				System.out.println(instituteYesNo);
				InstituteYesNo resp = restTemplate.postForObject(Constants.url + "/saveYesNoSingle", instituteYesNo,
						InstituteYesNo.class);
			} else {
				System.err.println("In else Its Edit Call " + transperentspeficytext);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("id", efficientId);
				map.add("yesNoResponse", transperentspeficytext);

				Info resp = restTemplate.postForObject(Constants.url + "/editYesNoRecord", map, Info.class);

			}
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab3");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab3List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab3List;

	}

	@RequestMapping(value = "/deleteEfficient", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> deleteEfficient(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int instYesnoId = Integer.parseInt(request.getParameter("instYesnoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("id", instYesnoId);

			Info resp = restTemplate.postForObject(Constants.url + "/deleteYesNoRecord", map, Info.class);

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab3");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab3List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab3List;

	}

	@RequestMapping(value = "/selectYestNoPageSecond", method = RequestMethod.GET)
	public ModelAndView selectYestNoPageSecond(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("yesno/YesNoValues2");
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			model.addObject("title", "Stakeholder's Feedback Details");

			StakeholderFb[] yesNoMaster = restTemplate.getForObject(Constants.url + "/getAllStakeHolderFb",
					StakeholderFb[].class);
			stfbList = new ArrayList<>(Arrays.asList(yesNoMaster));
			model.addObject("stakeFblist", stfbList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("instId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);

			InstStakeholderFeedback[] instituteYesNo = restTemplate
					.postForObject(Constants.url + "/getAllStakeByInstituteId", map, InstStakeholderFeedback[].class);
			tempFb = new ArrayList<>(Arrays.asList(instituteYesNo));
			model.addObject("tempFb", tempFb);
			
			System.out.println("tempFb:::" + tempFb.toString());
			try {
			if(tempFb.size()>0) {
				System.out.println("size gt 0");
				model.addObject("isEdit",1);
				
			}
			else {
				System.out.println("is edit 0 in else part");
				model.addObject("isEdit",0);
			}
			}
			catch (Exception e) {

				System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

				e.printStackTrace();

			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertInstStakeholder", method = RequestMethod.POST)
	public String insertLeaveStructure(HttpServletRequest request, HttpServletResponse response) {
		try {
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			int isEdit=0;
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			try {
				 isEdit=Integer.parseInt(request.getParameter("isEdit"));
			}
			catch(Exception e) {
				isEdit=0;
			}
			
			
			System.out.println("isEdit:::" +isEdit );
			
			
			InstStakeholderFeedback head = null;
			proofList = new ArrayList<>();
		
			for (int i = 0; i < stfbList.size(); i++) {
				head = new InstStakeholderFeedback();
				if (Integer.parseInt(request.getParameter("yesNo" + stfbList.get(i).getFeedbackId())) == 1) {
 					System.out.println("yes " + i);
 					if(isEdit==1) {
 						head.setStakFbId(Integer.parseInt(request.getParameter("stakFbId" + tempFb.get(i).getStakFbId())));
 					} 
 					head.setFbYesno(Integer.parseInt(request.getParameter("yesNo" + stfbList.get(i).getFeedbackId())));
					head.setFbFromId(stfbList.get(i).getFeedbackId());
					head.setFbProcess(request.getParameter("fbProcess" + stfbList.get(i).getFeedbackId()));
					head.setYrSem(Integer.parseInt(request.getParameter("quolif" + stfbList.get(i).getFeedbackId())));
					head.setDelStatus(1);
					head.setIsActive(1);
					head.setMakerUserId(userObj.getUserId());
					head.setMakerDatetime(sf.format(date));
					head.setInstituteId(userObj.getGetData().getUserInstituteId());
					head.setAcYearId(acYearId);
					head.setExInt1(1);
					head.setExInt2(1);
					head.setExVar1("NA");
					head.setExVar2("NA");
					System.out.println("head in yes:::" + head.toString());
					
				}

				else {
					if(isEdit==1) {
 						head.setStakFbId(Integer.parseInt(request.getParameter("stakFbId" + tempFb.get(i).getStakFbId())));
 					} 
					head.setFbYesno(Integer.parseInt(request.getParameter("yesNo" + stfbList.get(i).getFeedbackId())));
					System.out.println("no " + i);
					head.setFbFromId(stfbList.get(i).getFeedbackId());
					head.setFbProcess("NA");
					head.setYrSem(0);
					head.setDelStatus(1);
					head.setIsActive(1);
					head.setMakerUserId(userObj.getUserId());
					head.setMakerDatetime(sf.format(date));
					head.setInstituteId(userObj.getGetData().getUserInstituteId());
					head.setAcYearId(acYearId);
					head.setExInt1(1);
					head.setExInt2(1);
					head.setExVar1("NA");
					head.setExVar2("NA");
					System.err.println("head in no:::" + head.toString());
				}
				proofList.add(head);
			}
			 
			
			System.out.println("proofList is :::" + proofList.toString());
			InstStakeholderFeedback[] instituteYesNo = restTemplate
					.postForObject(Constants.url + "/saveInstStakeholder", proofList, InstStakeholderFeedback[].class);
			if (instituteYesNo != null) {
				session.setAttribute("successMsg", "Record Inserted Successfully");
			} else {
				session.setAttribute("errorMsg", "Failed to Insert Record");
			}
			
			

			

		} catch (

		Exception e) {

			System.err.println("Exce In submitInsertLeaveStructure method  " + e.getMessage());
			e.printStackTrace();

		}

		return "redirect:/selectYestNoPageSecond";

	}

	@RequestMapping(value = "/selectYestNoPageThird", method = RequestMethod.GET)
	public ModelAndView selectYestNoPageThird(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("yesno/YesNoValues3");
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");

			model.addObject("title", "Gender Sensitivity Facility");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("pageCode", "PAGE3");
			YesNoMaster[] yesNoMaster = restTemplate.postForObject(Constants.url + "/getYesNoList", map,
					YesNoMaster[].class);
			yesNoMasterListPage3 = new ArrayList<>(Arrays.asList(yesNoMaster));

			SectionList[] section = restTemplate.postForObject(Constants.url + "/getSectionListByPageCode", map,
					SectionList[].class);
			List<SectionList> sectionList = new ArrayList<>(Arrays.asList(section));

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("pageCode", "PAGE3");
			InstituteYesNo[] instituteYesNo = restTemplate
					.postForObject(Constants.url + "/getInstituteYesNoListByInstituteId", map, InstituteYesNo[].class);
			instituteYesNoListPage3 = new ArrayList<>(Arrays.asList(instituteYesNo));

			/*
			 * System.out.println("yesNoMasterList " + yesNoMasterList);
			 * System.out.println("sectionList " + sectionList);
			 * System.out.println("instituteYesNoList " + instituteYesNoList);
			 */

			model.addObject("yesNoMasterList", yesNoMasterListPage3);
			model.addObject("sectionList", sectionList);
			model.addObject("instituteYesNoList", instituteYesNoListPage3);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/submitYesNoPageThird", method = RequestMethod.POST)
	public String submitYesNoPageThird(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int count = Integer.parseInt(request.getParameter("srindex"));

			List<InstituteYesNo> save = new ArrayList<>();

			for (int i = 0; i < yesNoMasterListPage3.size(); i++) {

				int value = Integer.parseInt(request.getParameter("yesNo" + yesNoMasterListPage3.get(i).getYesnoId()));
				int find = 0;

				for (int j = 0; j < instituteYesNoListPage3.size(); j++) {

					if (instituteYesNoListPage3.get(j).getYesnoId() == yesNoMasterListPage3.get(i).getYesnoId()) {
						find = 1;

						if (value == 1) {
							instituteYesNoListPage3.get(j).setMakerUserId(userObj.getUserId());
							instituteYesNoListPage3.get(j).setMakerDatetime(sf.format(date));
							instituteYesNoListPage3.get(j).setInstYesnoResponse(
									request.getParameter("respnsevalue" + yesNoMasterListPage3.get(i).getYesnoId()));
						} else {

							instituteYesNoListPage3.get(j).setDelStatus(0);
						}

					}
				}

				if (find == 0 && value == 1) {

					InstituteYesNo instituteYesNo = new InstituteYesNo();
					instituteYesNo.setDelStatus(1);
					instituteYesNo.setIsActive(1);
					instituteYesNo.setMakerUserId(userObj.getUserId());
					instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
					instituteYesNo.setMakerDatetime(sf.format(date));
					instituteYesNo.setYesnoPagecode(yesNoMasterListPage3.get(i).getYesnoPagecode());
					instituteYesNo.setSectionCode(yesNoMasterListPage3.get(i).getYesnoSeccode());
					instituteYesNo.setInstYesnoResponse(
							request.getParameter("respnsevalue" + yesNoMasterListPage3.get(i).getYesnoId()));
					instituteYesNo.setYearId(acYearId);
					instituteYesNo.setYesnoId(yesNoMasterListPage3.get(i).getYesnoId());
					instituteYesNoListPage3.add(instituteYesNo);
				}

			}

			for (int j = 0; j < instituteYesNoListPage3.size(); j++) {

				if (instituteYesNoListPage3.get(j).getYesnoId() == 0) {
					int value = Integer.parseInt(
							request.getParameter("dynamicprevyesno" + instituteYesNoListPage3.get(j).getInstYesnoId()));

					if (value == 1) {
						instituteYesNoListPage3.get(j).setMakerUserId(userObj.getUserId());
						instituteYesNoListPage3.get(j).setMakerDatetime(sf.format(date));
						instituteYesNoListPage3.get(j).setInstYesnoResponse(request.getParameter(
								"dynamicprevyesnovalue" + instituteYesNoListPage3.get(j).getInstYesnoId()));
					} else {

						instituteYesNoListPage3.get(j).setDelStatus(0);
					}
				}

			}

			if (count != 0) {
				for (int j = 1; j <= count; j++) {

					int value = Integer.parseInt(request.getParameter("dynamicyesno" + j));

					if (value == 1) {
						InstituteYesNo instituteYesNo = new InstituteYesNo();
						instituteYesNo.setDelStatus(1);
						instituteYesNo.setIsActive(1);
						instituteYesNo.setMakerUserId(userObj.getUserId());
						instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
						instituteYesNo.setMakerDatetime(sf.format(date));
						instituteYesNo.setYesnoPagecode("PAGE3");
						instituteYesNo.setSectionCode("other");
						instituteYesNo.setInstYesnoResponse(request.getParameter("dynamicyesnovalue" + j));
						instituteYesNo.setYesnoDynamicTitle(request.getParameter("otherTitleName" + j));
						instituteYesNo.setYearId(acYearId);
						instituteYesNoListPage3.add(instituteYesNo);
					}
				}

			}
			InstituteYesNo[] instituteYesNo = restTemplate.postForObject(Constants.url + "/saveYesNo",
					instituteYesNoListPage3, InstituteYesNo[].class);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/selectYestNoPageThird";

	}

	@RequestMapping(value = "/selectYestNoPageFourth", method = RequestMethod.GET)
	public ModelAndView selectYestNoPageFourth(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("yesno/YesNoValues4");
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");

			model.addObject("title", "Environmental Consciousness Display");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("pageCode", "PAGE4");
			YesNoMaster[] yesNoMaster = restTemplate.postForObject(Constants.url + "/getYesNoList", map,
					YesNoMaster[].class);
			yesNoMasterListPage4 = new ArrayList<>(Arrays.asList(yesNoMaster));

			SectionList[] section = restTemplate.postForObject(Constants.url + "/getSectionListByPageCode", map,
					SectionList[].class);
			List<SectionList> sectionList = new ArrayList<>(Arrays.asList(section));

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("pageCode", "PAGE4");
			InstituteYesNo[] instituteYesNo = restTemplate
					.postForObject(Constants.url + "/getInstituteYesNoListByInstituteId", map, InstituteYesNo[].class);
			instituteYesNoListPage4 = new ArrayList<>(Arrays.asList(instituteYesNo));

			/*
			 * System.out.println("yesNoMasterList " + yesNoMasterList);
			 * System.out.println("sectionList " + sectionList);
			 * System.out.println("instituteYesNoList " + instituteYesNoList);
			 */

			model.addObject("yesNoMasterList", yesNoMasterListPage4);
			model.addObject("sectionList", sectionList);
			model.addObject("instituteYesNoList", instituteYesNoListPage4);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/submitYesNoPageFourth", method = RequestMethod.POST)
	public String submitYesNoPageFourth(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int count = Integer.parseInt(request.getParameter("srindex"));

			List<InstituteYesNo> save = new ArrayList<>();

			for (int i = 0; i < yesNoMasterListPage4.size(); i++) {

				int value = Integer.parseInt(request.getParameter("yesNo" + yesNoMasterListPage4.get(i).getYesnoId()));
				int find = 0;

				for (int j = 0; j < instituteYesNoListPage4.size(); j++) {

					if (instituteYesNoListPage4.get(j).getYesnoId() == yesNoMasterListPage4.get(i).getYesnoId()) {
						find = 1;

						if (value == 1) {
							instituteYesNoListPage4.get(j).setMakerUserId(userObj.getUserId());
							instituteYesNoListPage4.get(j).setMakerDatetime(sf.format(date));
							instituteYesNoListPage4.get(j).setInstYesnoResponse(
									request.getParameter("respnsevalue" + yesNoMasterListPage4.get(i).getYesnoId()));
						} else {

							instituteYesNoListPage4.get(j).setDelStatus(0);
						}

					}
				}

				if (find == 0 && value == 1) {

					InstituteYesNo instituteYesNo = new InstituteYesNo();
					instituteYesNo.setDelStatus(1);
					instituteYesNo.setIsActive(1);
					instituteYesNo.setMakerUserId(userObj.getUserId());
					instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
					instituteYesNo.setMakerDatetime(sf.format(date));
					instituteYesNo.setYesnoPagecode(yesNoMasterListPage4.get(i).getYesnoPagecode());
					instituteYesNo.setSectionCode(yesNoMasterListPage4.get(i).getYesnoSeccode());
					instituteYesNo.setInstYesnoResponse(
							request.getParameter("respnsevalue" + yesNoMasterListPage4.get(i).getYesnoId()));
					instituteYesNo.setYearId(acYearId);
					instituteYesNo.setYesnoId(yesNoMasterListPage4.get(i).getYesnoId());
					instituteYesNoListPage4.add(instituteYesNo);
				}

			}

			for (int j = 0; j < instituteYesNoListPage4.size(); j++) {

				if (instituteYesNoListPage4.get(j).getYesnoId() == 0) {
					int value = Integer.parseInt(
							request.getParameter("dynamicprevyesno" + instituteYesNoListPage4.get(j).getInstYesnoId()));

					if (value == 1) {
						instituteYesNoListPage4.get(j).setMakerUserId(userObj.getUserId());
						instituteYesNoListPage4.get(j).setMakerDatetime(sf.format(date));
						instituteYesNoListPage4.get(j).setInstYesnoResponse(request.getParameter(
								"dynamicprevyesnovalue" + instituteYesNoListPage4.get(j).getInstYesnoId()));
					} else {

						instituteYesNoListPage4.get(j).setDelStatus(0);
					}
				}

			}

			if (count != 0) {
				for (int j = 1; j <= count; j++) {

					int value = Integer.parseInt(request.getParameter("dynamicyesno" + j));

					if (value == 1) {
						InstituteYesNo instituteYesNo = new InstituteYesNo();
						instituteYesNo.setDelStatus(1);
						instituteYesNo.setIsActive(1);
						instituteYesNo.setMakerUserId(userObj.getUserId());
						instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
						instituteYesNo.setMakerDatetime(sf.format(date));
						instituteYesNo.setYesnoPagecode("PAGE3");
						instituteYesNo.setSectionCode("other");
						instituteYesNo.setInstYesnoResponse(request.getParameter("dynamicyesnovalue" + j));
						instituteYesNo.setYesnoDynamicTitle(request.getParameter("otherTitleName" + j));
						instituteYesNo.setYearId(acYearId);
						instituteYesNoListPage4.add(instituteYesNo);
					}
				}

			}
			InstituteYesNo[] instituteYesNo = restTemplate.postForObject(Constants.url + "/saveYesNo",
					instituteYesNoListPage4, InstituteYesNo[].class);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/selectYestNoPageFourth";

	}

	@RequestMapping(value = "/selectYestNoPageFifth", method = RequestMethod.GET)
	public ModelAndView selectYestNoPageFifth(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("yesno/YesNoValues5");
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");

			model.addObject("title", "Human Values - Professional Ethics Display");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("pageCode", "PAGE5");
			YesNoMaster[] yesNoMaster = restTemplate.postForObject(Constants.url + "/getYesNoList", map,
					YesNoMaster[].class);
			yesNoMasterListPage5 = new ArrayList<>(Arrays.asList(yesNoMaster));

			SectionList[] section = restTemplate.postForObject(Constants.url + "/getSectionListByPageCode", map,
					SectionList[].class);
			List<SectionList> sectionList = new ArrayList<>(Arrays.asList(section));

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("pageCode", "PAGE5");
			InstituteYesNo[] instituteYesNo = restTemplate
					.postForObject(Constants.url + "/getInstituteYesNoListByInstituteId", map, InstituteYesNo[].class);
			instituteYesNoListPage5 = new ArrayList<>(Arrays.asList(instituteYesNo));

			/*
			 * System.out.println("yesNoMasterList " + yesNoMasterList);
			 * System.out.println("sectionList " + sectionList);
			 * System.out.println("instituteYesNoList " + instituteYesNoList);
			 */

			model.addObject("yesNoMasterList", yesNoMasterListPage5);
			model.addObject("sectionList", sectionList);
			model.addObject("instituteYesNoList", instituteYesNoListPage5);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/submitYesNoPageFifth", method = RequestMethod.POST)
	public String submitYesNoPageFifth(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int count = Integer.parseInt(request.getParameter("srindex"));

			List<InstituteYesNo> save = new ArrayList<>();

			for (int i = 0; i < yesNoMasterListPage5.size(); i++) {

				int value = Integer.parseInt(request.getParameter("yesNo" + yesNoMasterListPage5.get(i).getYesnoId()));
				int find = 0;

				for (int j = 0; j < instituteYesNoListPage5.size(); j++) {

					if (instituteYesNoListPage5.get(j).getYesnoId() == yesNoMasterListPage5.get(i).getYesnoId()) {
						find = 1;

						if (value == 1) {
							instituteYesNoListPage5.get(j).setMakerUserId(userObj.getUserId());
							instituteYesNoListPage5.get(j).setMakerDatetime(sf.format(date));
							instituteYesNoListPage5.get(j).setInstYesnoResponse(
									request.getParameter("respnsevalue" + yesNoMasterListPage5.get(i).getYesnoId()));
						} else {

							instituteYesNoListPage5.get(j).setDelStatus(0);
						}

					}
				}

				if (find == 0 && value == 1) {

					InstituteYesNo instituteYesNo = new InstituteYesNo();
					instituteYesNo.setDelStatus(1);
					instituteYesNo.setIsActive(1);
					instituteYesNo.setMakerUserId(userObj.getUserId());
					instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
					instituteYesNo.setMakerDatetime(sf.format(date));
					instituteYesNo.setYesnoPagecode(yesNoMasterListPage5.get(i).getYesnoPagecode());
					instituteYesNo.setSectionCode(yesNoMasterListPage5.get(i).getYesnoSeccode());
					instituteYesNo.setInstYesnoResponse(
							request.getParameter("respnsevalue" + yesNoMasterListPage5.get(i).getYesnoId()));
					instituteYesNo.setYearId(acYearId);
					instituteYesNo.setYesnoId(yesNoMasterListPage5.get(i).getYesnoId());
					instituteYesNoListPage5.add(instituteYesNo);
				}

			}

			for (int j = 0; j < instituteYesNoListPage5.size(); j++) {

				if (instituteYesNoListPage5.get(j).getYesnoId() == 0) {
					int value = Integer.parseInt(
							request.getParameter("dynamicprevyesno" + instituteYesNoListPage5.get(j).getInstYesnoId()));

					if (value == 1) {
						instituteYesNoListPage5.get(j).setMakerUserId(userObj.getUserId());
						instituteYesNoListPage5.get(j).setMakerDatetime(sf.format(date));
						instituteYesNoListPage5.get(j).setInstYesnoResponse(request.getParameter(
								"dynamicprevyesnovalue" + instituteYesNoListPage5.get(j).getInstYesnoId()));
					} else {

						instituteYesNoListPage5.get(j).setDelStatus(0);
					}
				}

			}

			if (count != 0) {
				for (int j = 1; j <= count; j++) {

					int value = Integer.parseInt(request.getParameter("dynamicyesno" + j));

					if (value == 1) {
						InstituteYesNo instituteYesNo = new InstituteYesNo();
						instituteYesNo.setDelStatus(1);
						instituteYesNo.setIsActive(1);
						instituteYesNo.setMakerUserId(userObj.getUserId());
						instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
						instituteYesNo.setMakerDatetime(sf.format(date));
						instituteYesNo.setYesnoPagecode("PAGE3");
						instituteYesNo.setSectionCode("other");
						instituteYesNo.setInstYesnoResponse(request.getParameter("dynamicyesnovalue" + j));
						instituteYesNo.setYesnoDynamicTitle(request.getParameter("otherTitleName" + j));
						instituteYesNo.setYearId(acYearId);
						instituteYesNoListPage5.add(instituteYesNo);
					}
				}

			}
			InstituteYesNo[] instituteYesNo = restTemplate.postForObject(Constants.url + "/saveYesNo",
					instituteYesNoListPage5, InstituteYesNo[].class);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/selectYestNoPageFifth";

	}

	@RequestMapping(value = "/fixedYesNoSecond", method = RequestMethod.GET)
	public ModelAndView fixedYesNoSecond(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("yesno/fixedYesNoSecond");
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");

			model.addObject("title", "Curriculum and Cross Cutting Issues");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab4");
			InstituteYesNo[] instituteYesNo = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab4List = new ArrayList<>(Arrays.asList(instituteYesNo));

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab5");
			InstituteYesNo[] instituteYesN = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab5List = new ArrayList<>(Arrays.asList(instituteYesN));

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab6");
			InstituteYesNo[] arr = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab6List = new ArrayList<>(Arrays.asList(arr));

			model.addObject("instituteYesNoTab1List", instituteYesNoTab4List);
			model.addObject("instituteYesNoTab2List", instituteYesNoTab5List);
			model.addObject("instituteYesNoTab3List", instituteYesNoTab6List);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addGender", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> addGender(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String transperentspeficytext = request.getParameter("transperentspeficytext");

			int transId = Integer.parseInt(request.getParameter("transId"));

			if (transId == 0) {

				InstituteYesNo instituteYesNo = new InstituteYesNo();
				instituteYesNo.setDelStatus(1);
				instituteYesNo.setIsActive(1);
				instituteYesNo.setMakerUserId(userObj.getUserId());
				instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
				instituteYesNo.setMakerDatetime(sf.format(date));
				instituteYesNo.setYesnoPagecode("tab4");
				instituteYesNo.setSectionCode("tab4");
				instituteYesNo.setInstYesnoResponse(transperentspeficytext);
				instituteYesNo.setYesnoDynamicTitle("Gender");
				instituteYesNo.setYearId(acYearId);

				System.out.println(instituteYesNo);
				InstituteYesNo resp = restTemplate.postForObject(Constants.url + "/saveYesNoSingle", instituteYesNo,
						InstituteYesNo.class);
			} else {
				System.err.println("In else Its Edit Call " + transperentspeficytext);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("id", transId);
				map.add("yesNoResponse", transperentspeficytext);

				Info resp = restTemplate.postForObject(Constants.url + "/editYesNoRecord", map, Info.class);

			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab4");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab4List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab4List;

	}

	@RequestMapping(value = "/deleteGender", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> deleteGender(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int instYesnoId = Integer.parseInt(request.getParameter("instYesnoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("id", instYesnoId);

			Info resp = restTemplate.postForObject(Constants.url + "/deleteYesNoRecord", map, Info.class);

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab4");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab4List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab4List;

	}

	@RequestMapping(value = "/addEnvironmant", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> addEnvironmant(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String transperentspeficytext = request.getParameter("transperentspeficytext");

			int timeBoundId = Integer.parseInt(request.getParameter("timeBoundId"));

			if (timeBoundId == 0) {

				InstituteYesNo instituteYesNo = new InstituteYesNo();
				instituteYesNo.setDelStatus(1);
				instituteYesNo.setIsActive(1);
				instituteYesNo.setMakerUserId(userObj.getUserId());
				instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
				instituteYesNo.setMakerDatetime(sf.format(date));
				instituteYesNo.setYesnoPagecode("tab5");
				instituteYesNo.setSectionCode("tab5");
				instituteYesNo.setInstYesnoResponse(transperentspeficytext);
				instituteYesNo.setYesnoDynamicTitle("Environment");
				instituteYesNo.setYearId(acYearId);

				System.out.println(instituteYesNo);
				InstituteYesNo resp = restTemplate.postForObject(Constants.url + "/saveYesNoSingle", instituteYesNo,
						InstituteYesNo.class);
			} else {
				System.err.println("Edit time bound " + timeBoundId);
				System.err.println("In else Its Edit Call " + transperentspeficytext);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("id", timeBoundId);
				map.add("yesNoResponse", transperentspeficytext);

				Info resp = restTemplate.postForObject(Constants.url + "/editYesNoRecord", map, Info.class);

			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab5");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab5List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab5List;

	}

	@RequestMapping(value = "/deleteEnvironmant", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> deleteEnvironmant(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int instYesnoId = Integer.parseInt(request.getParameter("instYesnoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("id", instYesnoId);

			Info resp = restTemplate.postForObject(Constants.url + "/deleteYesNoRecord", map, Info.class);

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab5");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab5List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab5List;

	}

	@RequestMapping(value = "/addHumanValues", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> addHumanValues(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String transperentspeficytext = request.getParameter("transperentspeficytext");
			int efficientId = Integer.parseInt(request.getParameter("efficientId"));

			if (efficientId == 0) {
				InstituteYesNo instituteYesNo = new InstituteYesNo();
				instituteYesNo.setDelStatus(1);
				instituteYesNo.setIsActive(1);
				instituteYesNo.setMakerUserId(userObj.getUserId());
				instituteYesNo.setInstituteId(userObj.getGetData().getUserInstituteId());
				instituteYesNo.setMakerDatetime(sf.format(date));
				instituteYesNo.setYesnoPagecode("tab6");
				instituteYesNo.setSectionCode("tab6");
				instituteYesNo.setInstYesnoResponse(transperentspeficytext);
				instituteYesNo.setYesnoDynamicTitle("HumanValues");
				instituteYesNo.setYearId(acYearId);

				System.out.println(instituteYesNo);
				InstituteYesNo resp = restTemplate.postForObject(Constants.url + "/saveYesNoSingle", instituteYesNo,
						InstituteYesNo.class);
			} else {

				System.err.println("In else Its Edit Call " + transperentspeficytext);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("id", efficientId);
				map.add("yesNoResponse", transperentspeficytext);

				Info resp = restTemplate.postForObject(Constants.url + "/editYesNoRecord", map, Info.class);

			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab6");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab6List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab6List;

	}

	@RequestMapping(value = "/deleteHumanValues", method = RequestMethod.GET)
	public @ResponseBody List<InstituteYesNo> deleteHumanValues(HttpServletRequest request,
			HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int acYearId = (Integer) session.getAttribute("acYearId");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			int instYesnoId = Integer.parseInt(request.getParameter("instYesnoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("id", instYesnoId);

			Info resp = restTemplate.postForObject(Constants.url + "/deleteYesNoRecord", map, Info.class);

			map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			map.add("yearId", acYearId);
			map.add("secCode", "tab6");
			InstituteYesNo[] ary = restTemplate.postForObject(
					Constants.url + "/getInstituteYesNoListByInstituteIdAndSectionCode", map, InstituteYesNo[].class);
			instituteYesNoTab6List = new ArrayList<>(Arrays.asList(ary));

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return instituteYesNoTab6List;

	}

}
