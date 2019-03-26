package com.ats.rusasoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Names;

@Controller
@Scope("session")
public class BudgetConSac {
	
	
	@RequestMapping(value = "/budgetInfrastructureFacility", method = RequestMethod.GET)
	public ModelAndView budgetInfrastructureFacility(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/infra_budget_facility_list");
		try {

			model.addObject("title", Names.infra_budget_list);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// add_infrastructure_facility

	@RequestMapping(value = "/budgetAddInfrastructureFacility", method = RequestMethod.GET)
	public ModelAndView budgetAddInfrastructureFacility(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/infra_budget_facility_add");
		try {

			model.addObject("title", Names.infra_budget_add);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/budgetOnLibrary", method = RequestMethod.GET)
	public ModelAndView budgetOnLibrary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budget_library_list");
		try {

			model.addObject("title", Names.budget_library_list);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/budgetAddOnLibrary", method = RequestMethod.GET)
	public ModelAndView budgetAddOnLibrary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budget_library_add");
		try {

			model.addObject("title", Names.budget_library_add);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}


}
