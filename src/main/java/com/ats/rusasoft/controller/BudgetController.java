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
public class BudgetController {

	@RequestMapping(value = "/budgetInfrastructureFacility", method = RequestMethod.GET)
	public ModelAndView showStudMentor(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/infrastructurefacility");
		try {

			
			model.addObject("title", "Budget of Infrastructure Facility");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/budgetOnLibrary", method = RequestMethod.GET)
	public ModelAndView budgetOnLibrary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budgetOnLibrary");
		try {

			
			model.addObject("title", "Budget on Library Facility");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/budgetPhysicalFacility", method = RequestMethod.GET)
	public ModelAndView budgetPhysicalFacility(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budgetPhysicalFacility");
		try {

			
			model.addObject("title", "Budget on Physical Facilities");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/budgetOnAcadamicSupportFacilities", method = RequestMethod.GET)
	public ModelAndView budgetOnAcadamicSupportFacilities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budgetAcadamicSupportFacilities");
		try {

			
			model.addObject("title", "Budget on Acadamic Support Facilities");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/budgetOnGreenInitiativesAndWasteMngmnt", method = RequestMethod.GET)
	public ModelAndView budgetOnGreenInitiativesAndWasteMngmnt(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budgetWasteManagement");
		try {

			
			model.addObject("title", "Budget on Green Initiatives & Waste Management");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/budgetOnLibraryBooks", method = RequestMethod.GET)
	public ModelAndView budgetOnLibraryBooks(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budgetOnLibraryBooks");
		try {

			
			model.addObject("title", "Budget on Library Book ");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

}
