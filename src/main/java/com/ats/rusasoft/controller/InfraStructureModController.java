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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.master.model.prodetail.AlumniDetail;
import com.ats.rusasoft.master.model.prodetail.GetAlumni;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.infra.GetInstInfraAreaInfo;
import com.ats.rusasoft.model.infra.InfraAreaName;
import com.ats.rusasoft.model.infra.InfraAreaType;
import com.ats.rusasoft.model.infra.InstInfraAreaInfo;

@Controller
@Scope("session")
public class InfraStructureModController {

	@RequestMapping(value = "/showInstInfraAreawise", method = RequestMethod.GET)
	public ModelAndView showInstInfraAreawise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("infra/add_inst_infra_area");

			model.addObject("title", "Add Institute Infrastructure Detail Area wise");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showInstInfraAreawise", "showInstInfraAreawise", "1", "0",
					"0", "0", newModuleList);

			if (viewAccess.isError() == true) {

				Info addAccess = AccessControll.checkAccess("showInstInfraAreawise", "showInstInfraAreawise", "0", "1",
						"0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showInstInfraAreawise", "showInstInfraAreawise", "0", "0",
						"1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showInstInfraAreawise", "showInstInfraAreawise", "0",
						"0", "0", "1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				// GetAlumni

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				//map.add("yearId", session.getAttribute("acYearId"));

				InfraAreaType[] areaTypeArray = restTemplate.getForObject(Constants.url + "getInfraAreaTypeList",
						InfraAreaType[].class);
				
				List<InfraAreaType> areaTypeList = new ArrayList<>(Arrays.asList(areaTypeArray));
				
				model.addObject("areaTypeList", areaTypeList);

				GetInstInfraAreaInfo[] resArray = restTemplate.postForObject(Constants.url + "getInstInfraAreaInfoByInstId", map,
						GetInstInfraAreaInfo[].class);
				
				List<GetInstInfraAreaInfo> instInfraAreaList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("instInfraAreaList", instInfraAreaList);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showInstInfraAreawise at InfraStructure Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/getInfraAreaNameListByInfraAreaTypeId", method = RequestMethod.GET)
	public @ResponseBody List<InfraAreaName> getInfraAreaNameListByInfraAreaTypeId(HttpServletRequest request,
			HttpServletResponse response) {

		List<InfraAreaName> areaList = null;

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			
			map.add("infraAreaTypeId", request.getParameter("infraAreaTypeId"));

			InfraAreaName[] resArray = restTemplate
					.postForObject(Constants.url + "getInfraAreaNameListByInfraAreaTypeId", map, InfraAreaName[].class);
			areaList = new ArrayList<>(Arrays.asList(resArray));
			
		} catch (Exception e) {
			System.err.println("Excep in /getInfraAreaNameListByInfraAreaTypeId " + e.getMessage());
			e.printStackTrace();
		}

		return areaList;

	}
	
	//insertInstInfraArea
	
	@RequestMapping(value = "/insertInstInfraArea", method = RequestMethod.POST)
	public String insertInstInfraArea(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertInstInfraArea");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int instInfraAreaId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				instInfraAreaId = Integer.parseInt(request.getParameter("instInfraAreaId"));

			} catch (Exception e) {
				instInfraAreaId = 0;
			}
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = null;
			if (instInfraAreaId == 0) {

				editAccess = AccessControll.checkAccess("insertAlumni", "showAlumini", "0", "1", "0", "0",
						newModuleList);
			} else {

				editAccess = AccessControll.checkAccess("insertAlumni", "showAlumini", "0", "0", "1", "0",
						newModuleList);
			}

			System.err.println("alumniId id  " + instInfraAreaId);

			// editAccess.setError(false);

			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
					InstInfraAreaInfo instInfraAreaInfo = new InstInfraAreaInfo();

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					instInfraAreaInfo.setDelStatus(1);
					instInfraAreaInfo.setIsActive(1);

					int exInt1 = 0;
					instInfraAreaInfo.setExInt1(exInt1);
					instInfraAreaInfo.setExInt2(exInt1);
					String exVar1 = "NA";
					instInfraAreaInfo.setExVar1(exVar1);
					instInfraAreaInfo.setExVar2(exVar1);
					instInfraAreaInfo.setInstId(userObj.getGetData().getUserInstituteId());// get from Session
					instInfraAreaInfo.setMakerUserId(userObj.getUserId());// get from Session
					instInfraAreaInfo.setMakerDatetime(curDateTime);
					instInfraAreaInfo.setAreaLoc(request.getParameter("loc_of_area"));
					
					int areaType=Integer.parseInt(request.getParameter("area_type")); 
					int areaName=Integer.parseInt(request.getParameter("area_name")); 
					
					if(areaName==0) {
						instInfraAreaInfo.setAreaName(request.getParameter("other_area"));
					}else {
						instInfraAreaInfo.setAreaName(null);
					}
					
					instInfraAreaInfo.setAreaSqMtr(request.getParameter("area_in_sqm"));
					instInfraAreaInfo.setInfraAreaId(areaName);
					instInfraAreaInfo.setInfraAreaTypeId(areaType);
					instInfraAreaInfo.setInstInfraAreaId(instInfraAreaId);
					
					InstInfraAreaInfo instInfraAreawise = restTemplate.postForObject(Constants.url + "saveInstInfraAreaInfo", instInfraAreaInfo,
							InstInfraAreaInfo.class);
				

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showInstInfraAreawise";
				else
					redirect = "redirect:/showInstInfraAreawise";
			}

		} catch (Exception e) {
			System.err.println("Exce in save insertInstInfraArea  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}
	
	
	@RequestMapping(value = "/findByDelStatusAndIsActiveAndInstIdAndInfraAreaId", method = RequestMethod.GET)
	public @ResponseBody InstInfraAreaInfo findByDelStatusAndIsActiveAndInstIdAndInfraAreaId(HttpServletRequest request,
			HttpServletResponse response) {

		InstInfraAreaInfo resArray=new InstInfraAreaInfo();
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			map.add("areaId", request.getParameter("areaId"));
			map.add("instId", userObj.getGetData().getUserInstituteId());
			
			 resArray = restTemplate
					.postForObject(Constants.url + "findByDelStatusAndIsActiveAndInstIdAndInfraAreaId", map, InstInfraAreaInfo.class);
			
		} catch (Exception e) {
			System.err.println("Excep in /getInfraAreaNameListByInfraAreaTypeId " + e.getMessage());
			e.printStackTrace();
		}

		return resArray;

	}
	


}
