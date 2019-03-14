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
import com.ats.rusasoft.master.model.prodetail.Cast;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.LoginResponse;


@Controller
@Scope("session")
public class StudAdminController {
	
	@RequestMapping(value = "/showAddStudAdmitCatWise", method = RequestMethod.GET)
	public ModelAndView showAddStudAddmitCatWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addStudCatwise");
			
			model.addObject("title", "Add Student  Categorywise");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());
			
		
			Cast[] catsArray = restTemplate.getForObject(Constants.url + "getAllCastCategory",  Cast[].class);
			List<Cast> castList = new ArrayList<>(Arrays.asList(catsArray));
			System.err.println("castList " + castList.toString());
			
			model.addObject("castList", castList);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}


	@RequestMapping(value = "/showStudAddmitLoc", method = RequestMethod.GET)
	public ModelAndView showStudAddmitLoc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/studAdmittedLoc");

			model.addObject("title", "Student Addmitted Locationwise");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/showStudAddmit", method = RequestMethod.GET)
	public ModelAndView showStudAddmit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/studAdmitted");

			model.addObject("title", "Student Addmitted Categorywise");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/showAddStudAddmitLocWise", method = RequestMethod.GET)
	public ModelAndView showAddStudAddmitLocWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addStudLocwise");

			model.addObject("title", "Add Student Locationwise ");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

}
