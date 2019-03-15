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
public class FacultyModuleController {

	RestTemplate rest = new RestTemplate();

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

	@RequestMapping(value = "/showAddPublicationDetailsList", method = RequestMethod.GET)
	public ModelAndView showAddPublicationDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/pubDetailList");

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

	@RequestMapping(value = "/showResearchDetailsList", method = RequestMethod.GET)
	public ModelAndView showResearchDetailsList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/researchProjectList");

			model.addObject("title", "Research Details Form");

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

	@RequestMapping(value = "/showOrganizedList", method = RequestMethod.GET)
	public ModelAndView showOrganizedList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/organizedList");

			model.addObject("title", "Organized Details List");

		} catch (Exception e) {

			System.err.println("exception In showOrganizedList at faculty Contr" + e.getMessage());

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

	@RequestMapping(value = "/showAddSubDetails", method = RequestMethod.GET)
	public ModelAndView showAddSubDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/addSubDetail");

			model.addObject("title", "Subject Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showpoPsoFaculty", method = RequestMethod.GET)
	public ModelAndView showpoPsoFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/coPSOFaculty");

			model.addObject("title", "Faculty CO PO");

		} catch (Exception e) {

			System.err.println("exception In showpoPsoFaculty at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPsoFaculty", method = RequestMethod.GET)
	public ModelAndView showPsoFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/PSOFaculty");

			model.addObject("title", "Faculty PSO");

		} catch (Exception e) {

			System.err.println("exception In showpoPsoFaculty at Faculty Contr" + e.getMessage());

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

			model.addObject("title", "Academic Details ");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddAcademicDetails", method = RequestMethod.GET)
	public ModelAndView showAddAcademicDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/addAcademicDetails");

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

			System.out.println("hii...");
			model = new ModelAndView("FacultyDetails/personalDetailList");

			model.addObject("title", "Personal Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addPersonalDetails", method = RequestMethod.GET)
	public ModelAndView addPersonalDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			System.out.println("hii...");
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

			model = new ModelAndView("FacultyDetails/mPhillDetailList");

			model.addObject("title", "M.phill/Ph.D.  Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddMphillDetails", method = RequestMethod.GET)
	public ModelAndView showAddMphillDetails(HttpServletRequest request, HttpServletResponse response) {

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

	@RequestMapping(value = "/showAddStudMentor", method = RequestMethod.GET)
	public ModelAndView showAddStudMentor(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/addStudMentor");

			model.addObject("title", "Add Mentoring Details Form");

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

	@RequestMapping(value = "/showBookPub", method = RequestMethod.GET)
	public ModelAndView showBookPub(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/bookPub");

			model.addObject("title", "Book Publication");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showBookPubList", method = RequestMethod.GET)
	public ModelAndView showBookPubList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/bookPubList");

			model.addObject("title", "Book Publication");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showJournalPub", method = RequestMethod.GET)
	public ModelAndView showJournalPub(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/journalPub");

			model.addObject("title", "Journal Publication");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showJournalPubList", method = RequestMethod.GET)
	public ModelAndView showJournalPubList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/journalPubList");

			model.addObject("title", "Journal Publication");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showOutReachContri", method = RequestMethod.GET)
	public ModelAndView showOutReachContri(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/outReachContri");

			model.addObject("title", "Out Reach Contribution");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showOutReachContriList", method = RequestMethod.GET)
	public ModelAndView showOutReachContriList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/outReachContriList");

			model.addObject("title", "Out Reach Contribution List");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPhdGuide", method = RequestMethod.GET)
	public ModelAndView showPhdGuide(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/phdGuidence");

			model.addObject("title", "Ph.D. Guidance");

		} catch (Exception e) {

			System.err.println("exception In showPhdGuide at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPhdGuideList", method = RequestMethod.GET)
	public ModelAndView showPhdGuideList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/phdGuideList");

			model.addObject("title", "Ph.D. Guidance List");

		} catch (Exception e) {

			System.err.println("exception In showPhdGuideList at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

}
