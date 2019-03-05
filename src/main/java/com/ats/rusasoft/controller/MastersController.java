package com.ats.rusasoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class MastersController {
	
	@RequestMapping(value = "/iqacRegistration", method = RequestMethod.GET)
	public ModelAndView showRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/iqacRegistration");
		try {
 

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		
		
		
		return model;

	}
	
	@RequestMapping(value = "/hodRegistration", method = RequestMethod.GET)
	public ModelAndView hodRegistration(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/hodRegistration");
		try {
 

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/hodList", method = RequestMethod.GET)
	public ModelAndView hodList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/hodList");
	
		try {
 

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
	public ModelAndView addFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addFaculty");
		model.addObject("title","Add Department");
		try {
 

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showDeptList", method = RequestMethod.GET)
	public ModelAndView showDeptList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/deptList");
		model.addObject("title","Department List");
		try {
 

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	
	@RequestMapping(value = "/addPrincipal", method = RequestMethod.GET)
	public ModelAndView addPrincipal(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addPrincipal");
		model.addObject("title","Add Principal");
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
		model.addObject("title","Principal List");
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
