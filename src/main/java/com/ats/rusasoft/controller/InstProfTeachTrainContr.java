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
public class InstProfTeachTrainContr {

	
	@RequestMapping(value = "/showProfDevelopment", method = RequestMethod.GET)
	public ModelAndView showProfDevelopment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model =null;
		try {
			model= new ModelAndView("instituteInfo/IQAC/profDevelpment");
			model.addObject("title", "Training Teaching List");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddProfDevelopment", method = RequestMethod.GET)
	public ModelAndView showAddProfDevelopment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_prof_dev");
		try {

			model.addObject("title", "Add Training Teaching ");
			

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	//insertTeachTraing
	
	
	
	

	@RequestMapping(value = "/showAdminDevelopment", method = RequestMethod.GET)
	public ModelAndView showAdminDevelopment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/administrativeDevlop");
		try {

			model.addObject("title", "Training Non-Teaching List");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddAdminDevelopment", method = RequestMethod.GET)
	public ModelAndView showAddAdminDevelopment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_administrativeDevlop");
		try {

			model.addObject("title", " Add Training Non-Teaching");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

}
