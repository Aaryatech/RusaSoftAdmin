package com.ats.rusasoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

public class FacultyModuleController {
	
	
	

	@RequestMapping(value = "/showFacultyDetails", method = RequestMethod.GET)
	public ModelAndView showFacultyDetails1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/facultyDetails");

			model.addObject("title", "Faculty Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPublicationDetails", method = RequestMethod.GET)
	public ModelAndView showFacultyDetails2(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/publicationDetail");

			model.addObject("title", "Publication Details Form");
			model.addObject("formindex", 2);

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showResearchDetails", method = RequestMethod.GET)
	public ModelAndView showResearchDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/researchProDetail");

			model.addObject("title", "Research Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showConsultancyDetails", method = RequestMethod.GET)
	public ModelAndView showResearchProDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/consultancy");

			model.addObject("title", "Consultancy Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPatentDetails", method = RequestMethod.GET)
	public ModelAndView showPatentDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/patentDetails");

			model.addObject("title", "Patent Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAwardDetails", method = RequestMethod.GET)
	public ModelAndView showAwardDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/awardDetails");

			model.addObject("title", "Award Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showOutReachDetails", method = RequestMethod.GET)
	public ModelAndView showOutReachDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/outReach");

			model.addObject("title", "Out Reach Activity Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showOrganized", method = RequestMethod.GET)
	public ModelAndView showOrganized(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/organized");

			model.addObject("title", "Organized Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showSubDetails", method = RequestMethod.GET)
	public ModelAndView showSubDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/subDetails");

			model.addObject("title", "Subject Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showSubDetails1", method = RequestMethod.GET)
	public ModelAndView showSubDetails1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ResearchInnovation/subDetails1");

			model.addObject("title", "Subject Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAcademicDetails", method = RequestMethod.GET)
	public ModelAndView showAcademicDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/academicDetails");

			model.addObject("title", "Academic Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPersonalDetails", method = RequestMethod.GET)
	public ModelAndView showPersonalDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/personalDetail");

			model.addObject("title", "Personal Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showMphillDetails", method = RequestMethod.GET)
	public ModelAndView showMphillDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/mPhillDetail");

			model.addObject("title", "M.phill/Ph.D.  Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showStudMentor", method = RequestMethod.GET)
	public ModelAndView showStudMentor(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/studMentor");

			model.addObject("title", "Mentoring Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPhdGuide", method = RequestMethod.GET)
	public ModelAndView showPhdGuide(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/phdGuidence");

			model.addObject("title", "Ph.D. Guide Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showSWOC", method = RequestMethod.GET)
	public ModelAndView showSWOC(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/swoc");

			model.addObject("title", "SWOC Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

}
