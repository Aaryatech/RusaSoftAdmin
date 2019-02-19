package com.ats.rusasoft.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class MasterController {
	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/showRegisterInstitute", method = RequestMethod.GET)
	public ModelAndView showRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/reginstitute");

			model.addObject("title", "Register Institute");

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showIqacAfterLogin", method = RequestMethod.GET)
	public ModelAndView showIqacAfterLogin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/iqaclogin");

			model.addObject("title", "Fill Institute Information");

		} catch (Exception e) {

			System.err.println("exception In showIqacAfterLogin at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showRegisterStaff", method = RequestMethod.GET)
	public ModelAndView showRegisterStaff(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/regstaff");

			model.addObject("title", "Register Faculty");

		} catch (Exception e) {

			System.err.println("exception In showHodAfterLogin at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// instituteList

	@RequestMapping(value = "/showInstituteList", method = RequestMethod.GET)
	public ModelAndView showInstituteList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/instituteList");

			model.addObject("title", "Institute List");

		} catch (Exception e) {

			System.err.println("exception In showInstituteList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	
	
	
	@RequestMapping(value = "/showInstituteInfoList", method = RequestMethod.GET)
	public ModelAndView showInstituteInfoList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/instituteInfo");

			model.addObject("title", "Institute List");

		} catch (Exception e) {

			System.err.println("exception In showInstituteList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showStaffList", method = RequestMethod.GET)
	public ModelAndView showStaffList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/staffList");

			model.addObject("title", "Faculty List");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	

	@RequestMapping(value = "/showProgramDetails", method = RequestMethod.GET)
	public ModelAndView showProgramDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/programDetails");

			model.addObject("title", "Program Details Form");

		} catch (Exception e) {

			System.err.println("exception In showProgramDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	// res&innoForm

	@RequestMapping(value = "/showResearchAndInnovationForm", method = RequestMethod.GET)
	public ModelAndView showResearchAndInnovationForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/res&innoForm");

			model.addObject("title", "Research And Innvovation Form");

		} catch (Exception e) {

			System.err.println("exception In showResearchAndInnovationForm at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showInfrastructureForm", method = RequestMethod.GET)
	public ModelAndView showInfrastructureForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/infrastructure");

			model.addObject("title", "Infrastructure Form");

		} catch (Exception e) {

			System.err.println("exception In showInfrastructureForm at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	
	///////////////////////////****faculty details****//////////////////////////////////
	
	
	@RequestMapping(value = "/showFacultyDetails/{type}", method = RequestMethod.GET)
	public ModelAndView showFacultyDetails(HttpServletRequest request, HttpServletResponse response, @PathVariable int type) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/facultyDetails");

			model.addObject("title", "Faculty Details Form");
			System.out.println("type is"+type);
			model.addObject("formindex",type);

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
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

			model = new ModelAndView("master/FacultyDetails/publicationDetail");

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

			model = new ModelAndView("master/FacultyDetails/researchProDetail");

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

			model = new ModelAndView("master/FacultyDetails/consultancy");

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

 			model = new ModelAndView("master/FacultyDetails/patentDetails");

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

 			model = new ModelAndView("master/FacultyDetails/awardDetails");

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

			model = new ModelAndView("master/FacultyDetails/outReach");

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

 			model = new ModelAndView("master/FacultyDetails/organized");

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

			model = new ModelAndView("master/FacultyDetails/subDetails");

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

			model = new ModelAndView("master/FacultyDetails/academicDetails");

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

 			model = new ModelAndView("master/FacultyDetails/personalDetail");

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

			model = new ModelAndView("master/FacultyDetails/mPhillDetail");

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

			model = new ModelAndView("master/FacultyDetails/studMentor");

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

			model = new ModelAndView("master/FacultyDetails/phdGuidence");

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

			model = new ModelAndView("master/FacultyDetails/swoc");

			model.addObject("title", "SWOC Details Form");
			

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
 	
	

}
