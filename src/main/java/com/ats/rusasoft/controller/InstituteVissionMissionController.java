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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.InstitueMission;
import com.ats.rusasoft.model.InstitueVision;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.ProgramDetailSaveResponse;

@Controller
@Scope("session")
public class InstituteVissionMissionController {
	
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/showVisionMission", method = RequestMethod.GET)
	public ModelAndView showVisionMission(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/visionMission");
		try {

			model.addObject("title", "Institute Vision & Mission");
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("instituteId", userObj.getGetData().getUserInstituteId());
			 
			InstitueVision[] institueVision = rest.postForObject(Constants.url + "/getInsituteVisionList", map, InstitueVision[].class);
			model.addObject("institueVisionList", institueVision);
			
			InstitueMission[] institueMission = rest.postForObject(Constants.url + "/getInsituteMissionList", map, InstitueMission[].class);
			model.addObject("institueMissionList", institueMission);
			model.addObject("msgSucss", Constants.sucess_msg);
			model.addObject("msgFail", Constants.fail_msg);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/saveInstituteVission", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse saveProgramVission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			Date date = new Date();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String instVisionText = request.getParameter("instVisionText"); 
			int instituteVissionId = Integer.parseInt(request.getParameter("instituteVissionId")); 
			
			InstitueVision institueVision = new InstitueVision();

			institueVision.setInstVisionId(instituteVissionId);
			institueVision.setDelStatus(1);
			institueVision.setIsActive(1); 
			institueVision.setInstVisionText(instVisionText);
			institueVision.setInstituteId(userObj.getGetData().getUserInstituteId());
			institueVision.setMakerUserId(userObj.getUserId());
			institueVision.setMakerdatetime(sf.format(date));
		 

			InstitueVision res = rest.postForObject(Constants.url + "/saveInstituteVision", institueVision,
					InstitueVision.class);

			if (res == null) {
				info.setError(true);
				info.setMsg("error while saving");
				programDetailSaveResponse.setInfo(info);
			} else {
				info.setError(false);
				info.setMsg("saved");
				programDetailSaveResponse.setInfo(info);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				InstitueVision[] arry = rest.postForObject(Constants.url + "/getInsituteVisionList", map,
						InstitueVision[].class);
				List<InstitueVision> list = new ArrayList<>(Arrays.asList(arry));
				programDetailSaveResponse.setInstitueVisionList(list);
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}
	
	@RequestMapping(value = "/deleteInstituteVission", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse deleteInstituteVission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			 
			 
			int instVisionId = Integer.parseInt(request.getParameter("instVisionId"));
 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
				map.add("instVisionId", instVisionId);
				info  = rest.postForObject(Constants.url + "/deleteInstiuteVision", map,
						Info.class);
				programDetailSaveResponse.setInfo(info);
				
				HttpSession session = request.getSession();
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map = new LinkedMultiValueMap<>();
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				InstitueVision[] arry = rest.postForObject(Constants.url + "/getInsituteVisionList", map,
						InstitueVision[].class);
				List<InstitueVision> list = new ArrayList<>(Arrays.asList(arry));
				programDetailSaveResponse.setInstitueVisionList(list);
			 

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}
	
	@RequestMapping(value = "/editInstituteVission", method = RequestMethod.GET)
	public @ResponseBody InstitueVision editInstituteVission(HttpServletRequest request,
			HttpServletResponse response) {

		InstitueVision institueVision = new InstitueVision();
	 
		try {

			  
			int instVisionId = Integer.parseInt(request.getParameter("instVisionId"));
 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
				map.add("instVisionId", instVisionId);
				institueVision  = rest.postForObject(Constants.url + "/getInstituteVisionByVisionId", map,
						InstitueVision.class);
				 

		} catch (Exception e) {

			e.printStackTrace();
			 

		}

		return institueVision;

	}
	
	@RequestMapping(value = "/saveInstituteMission", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse saveInstituteMission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			Date date = new Date();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String instMissionText = request.getParameter("instMissionText"); 
			int instMissionId = Integer.parseInt(request.getParameter("instMissionId")); 
			
			InstitueMission institueMission = new InstitueMission();

			institueMission.setInstMissionId(instMissionId);
			institueMission.setDelStatus(1);
			institueMission.setIsActive(1); 
			institueMission.setInstMissionText(instMissionText);
			institueMission.setInstituteId(userObj.getGetData().getUserInstituteId());
			institueMission.setMakerUserId(userObj.getUserId());
			institueMission.setMakerdatetime(sf.format(date));
		 

			InstitueMission res = rest.postForObject(Constants.url + "/saveInstituteMission", institueMission,
					InstitueMission.class);

			if (res == null) {
				info.setError(true);
				info.setMsg("error while saving");
				programDetailSaveResponse.setInfo(info);
			} else {
				info.setError(false);
				info.setMsg("saved");
				programDetailSaveResponse.setInfo(info);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				InstitueMission[] arry = rest.postForObject(Constants.url + "/getInsituteMissionList", map,
						InstitueMission[].class);
				List<InstitueMission> list = new ArrayList<>(Arrays.asList(arry));
				programDetailSaveResponse.setInstitueMissionList(list);
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}
	
	@RequestMapping(value = "/deleteInstituteMission", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse deleteInstituteMission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			 
			 
			int instMissionId = Integer.parseInt(request.getParameter("instMissionId"));
 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
				map.add("instMissionId", instMissionId);
				info  = rest.postForObject(Constants.url + "/deleteInstiuteMission", map,
						Info.class);
				programDetailSaveResponse.setInfo(info);
				
				HttpSession session = request.getSession();
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				InstitueMission[] arry = rest.postForObject(Constants.url + "/getInsituteMissionList", map,
						InstitueMission[].class);
				List<InstitueMission> list = new ArrayList<>(Arrays.asList(arry));
				programDetailSaveResponse.setInstitueMissionList(list);
			 

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}
	
	@RequestMapping(value = "/editInstituteMission", method = RequestMethod.GET)
	public @ResponseBody InstitueMission editInstituteMission(HttpServletRequest request,
			HttpServletResponse response) {

		InstitueMission institueMission = new InstitueMission();
	 
		try {

			  
			int instMissionId = Integer.parseInt(request.getParameter("instMissionId"));
 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
				map.add("instMissionId", instMissionId);
				institueMission  = rest.postForObject(Constants.url + "/getInstituteMissionByMissionId", map,
						InstitueMission.class);
				 

		} catch (Exception e) {

			e.printStackTrace();
			 

		}

		return institueMission;

	}

}
