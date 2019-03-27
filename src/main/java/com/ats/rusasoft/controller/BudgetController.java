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
public class BudgetController {

	@RequestMapping(value = "/budgetOnLibraryBooks", method = RequestMethod.GET)
	public ModelAndView budgetOnLibraryBooks(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/library_book_budget_list");
		try {

			model.addObject("title", Names.library_book_budget_list);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/budgetAddOnLibraryBooks", method = RequestMethod.GET)
	public ModelAndView budgetAddOnLibraryBooks(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/library_book_budget_add");
		try {

			model.addObject("title", Names.library_book_budget_add);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

}
