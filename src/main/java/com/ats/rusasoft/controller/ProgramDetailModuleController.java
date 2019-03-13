package com.ats.rusasoft.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


@Controller
@Scope("session")
public class ProgramDetailModuleController {

	RestTemplate rest = new RestTemplate();
	
	

	@RequestMapping(value = "/showProgDetail1", method = RequestMethod.GET)
	public ModelAndView showProgDetail1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/programDetails1");

			model.addObject("title", " Program Details");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	

	@RequestMapping(value = "/showAddProgDetail", method = RequestMethod.GET)
	public ModelAndView showAddProgDetail1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addProgDetail");

			model.addObject("title", " Add Program Details");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	
	
	
	
	@RequestMapping(value = "/showEucationalObjective", method = RequestMethod.GET)
	public ModelAndView showEucationalObjective(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/educationalObjective");

			model.addObject("title", "Program Educational Objective");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showpoPso", method = RequestMethod.GET)
	public ModelAndView showpoPso(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/poPSO");

			model.addObject("title", "Program PO PSO");

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
	
	
	@RequestMapping(value = "/showAddStudAdmitCatWise", method = RequestMethod.GET)
	public ModelAndView showAddStudAddmitCatWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addStudCatwise");

			model.addObject("title", "Add Student  Categorywise");

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


	@RequestMapping(value = "/showStudSupp", method = RequestMethod.GET)
	public ModelAndView showProgDetail(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/StudSuppSch");

			model.addObject("title", "Student Support Scheme");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	

	@RequestMapping(value = "/showAddStudSupp", method = RequestMethod.GET)
	public ModelAndView showAddStudSupp(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addStudSuppSch");

			model.addObject("title", "Student Support Scheme");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showStudTran", method = RequestMethod.GET)
	public ModelAndView showStudTran(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/training");

			model.addObject("title", "Program Training Details");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	
	@RequestMapping(value = "/showAddStudTran", method = RequestMethod.GET)
	public ModelAndView showAddStudTran(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addStudTraining");

			model.addObject("title", "Program Training Details");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showHighEdu", method = RequestMethod.GET)
	public ModelAndView showHighEdu(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/highEdu");

			model.addObject("title", "Progression to Higher Education ");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/showAddHighEdu", method = RequestMethod.GET)
	public ModelAndView showAddHighEdu(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addHighEducation");

			model.addObject("title", "Progression to Higher Education ");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showStudAct", method = RequestMethod.GET)
	public ModelAndView showStudAct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/studActivity");

			model.addObject("title", "Student Activity Organized");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/showAddStudAct", method = RequestMethod.GET)
	public ModelAndView showAddStudAct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addStudActOrganized");

			model.addObject("title", "Student Activity Organized");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	

	@RequestMapping(value = "/showStudActAtten", method = RequestMethod.GET)
	public ModelAndView showStudActAtten(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/studActivityAttend");

			model.addObject("title", "Student Activity Attended");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	
	@RequestMapping(value = "/showAddStudActAtten", method = RequestMethod.GET)
	public ModelAndView showAddStudActAtten(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addStudActAttend");

			model.addObject("title", "Student Activity Attended");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	
	@RequestMapping(value = "/showAlumini", method = RequestMethod.GET)
	public ModelAndView showAlumini(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/alumini");

			model.addObject("title", "Alumini Association/Contribution");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/showAddAlumini", method = RequestMethod.GET)
	public ModelAndView showAddAlumini(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addAluminiDetails");

			model.addObject("title", "Add Alumini Contribution Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	

	@RequestMapping(value = "/showProgDsh", method = RequestMethod.GET)
	public ModelAndView showProgDsh(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/progDetailDash");

			model.addObject("title", "Add Program Details");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/showAddPO", method = RequestMethod.GET)
	public ModelAndView showAddPO(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addPO");

			model.addObject("title", "Add Program OutComes");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

}
