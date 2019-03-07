package com.ats.rusasoft.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.GetHod;
import com.ats.rusasoft.model.GetInstituteList;
import com.ats.rusasoft.model.Hod;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Quolification;

@Controller
@Scope("session")
public class MastersController {


	@RequestMapping(value = "/hodRegistration", method = RequestMethod.GET)
	public ModelAndView hodRegistration(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/hodRegistration");
		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());
			Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
			List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
			System.err.println("deptList " + deptList.toString());

			model.addObject("deptList", deptList);
			
			
			map = new LinkedMultiValueMap<String, Object>();

			map.add("type", 1);
			Quolification[] quolArray = restTemplate.postForObject(Constants.url + "getQuolificationList", map, Quolification[].class);
			List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("quolfList " + quolfList.toString());

			model.addObject("quolfList", quolfList);
			Hod hod=new Hod();
			model.addObject("hod", hod);

		} catch (Exception e) {

			System.err.println("exception In hodRegistration at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/hodList", method = RequestMethod.GET)
	public ModelAndView hodList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/hodList");

		try {

			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());
			GetHod[] hodArray = restTemplate.postForObject(Constants.url + "getHodListByInstId", map, GetHod[].class);
			List<GetHod> hodList = new ArrayList<>(Arrays.asList(hodArray));
			System.err.println("hodList " + hodList.toString());

			model.addObject("hodList", hodList);
		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
	public ModelAndView addFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addFaculty");
		model.addObject("title", "Add Department");
		try {

			Dept dept = new Dept();

			model.addObject("dept", dept);

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showDeptList", method = RequestMethod.GET)
	public ModelAndView showDeptList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/deptList");
		model.addObject("title", "Department List");
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());
			Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
			List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
			System.err.println("deptList " + deptList.toString());

			model.addObject("deptList", deptList);
		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addPrincipal", method = RequestMethod.GET)
	public ModelAndView addPrincipal(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addPrincipal");
		model.addObject("title", "Add Principal");
		try {

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPrincipalList", method = RequestMethod.GET)
	public ModelAndView showPrincipalList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/principalList");
		model.addObject("title", "Principal List");
		try {

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPendingInstitute", method = RequestMethod.GET)
	public ModelAndView showPendingInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/pendingInstituteList");

			RestTemplate restTemplate = new RestTemplate();

			GetInstituteList[] instArray = restTemplate.getForObject(Constants.url + "getAllPendingInstitutes",
					GetInstituteList[].class);
			List<GetInstituteList> instList = new ArrayList<>(Arrays.asList(instArray));

			model.addObject("pendInstList", instList);

			model.addObject("title", " Pending Institute");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showApprovedInstitute", method = RequestMethod.GET)
	public ModelAndView showApprovedInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/approvedInstituteList");

			model.addObject("title", " Pending Institute");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	

}
