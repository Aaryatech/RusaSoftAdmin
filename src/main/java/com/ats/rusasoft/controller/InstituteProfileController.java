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
public class InstituteProfileController {

	@RequestMapping(value = "/instituteProfileInfo", method = RequestMethod.GET)
	public ModelAndView showStudMentor(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/instituteProfileInfo");
		try {

			model.addObject("title", "Institute Profile Information");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showInstProf", method = RequestMethod.GET)
	public ModelAndView showInstProf(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/instProf");
		try {

			model.addObject("title", "Institute Profile Information");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showVisionMission", method = RequestMethod.GET)
	public ModelAndView showVisionMission(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/visionMission");
		try {

			model.addObject("title", "Vision & Mission");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showInstituteSupport", method = RequestMethod.GET)
	public ModelAndView showInstituteSupport(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/instituteSupport");
		try {

			model.addObject("title", "Institute Financial Support");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showActivityOrganized", method = RequestMethod.GET)
	public ModelAndView showActivityOrganized(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/activityOrganized");
		try {

			model.addObject("title", "Activities Organized");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showEContentFacilities", method = RequestMethod.GET)
	public ModelAndView showEContentFacilities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/eContentFacilities");
		try {

			model.addObject("title", "Institute Profile Information");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showReforms", method = RequestMethod.GET)
	public ModelAndView showReforms(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/reforms");
		try {

			model.addObject("title", "Evaluation Process & Reforms");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddReforms", method = RequestMethod.GET)
	public ModelAndView showAddReforms(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addReforms");
		try {

			model.addObject("title", "Add Evaluation Process & Reforms");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showStudentPerformance", method = RequestMethod.GET)
	public ModelAndView showStudentPerformance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/studentPerformance");
		try {

			model.addObject("title", "Student Performance");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddStudentPerformance", method = RequestMethod.GET)
	public ModelAndView showAddStudentPerformance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addStudePerformance");
		try {

			model.addObject("title", "Add Student Performance");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showTransparentGrievance", method = RequestMethod.GET)
	public ModelAndView showTransparentGrievance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/transparentGrievances");
		try {

			model.addObject("title", "Mechanism of Examination Related Grievance(Transparent) ");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddTransparentGrievance", method = RequestMethod.GET)
	public ModelAndView showAddTransparentGrievance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addTransGrievances");
		try {

			model.addObject("title", "Mechanism of Examination Related Grievance(Transparent) ");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showTimeBoundGrievance", method = RequestMethod.GET)
	public ModelAndView showTimeBoundGrievance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/timeBoundGrievances");
		try {

			model.addObject("title", "Mechanism of Examination Related Grievance(Time Bound) ");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddTimeBoundGrievance", method = RequestMethod.GET)
	public ModelAndView showAddTimeBoundGrievance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addTimeBoundGrie");
		try {

			model.addObject("title", "Add Mechanism of Examination Related Grievance(Time Bound) ");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showEfficienttGrievance", method = RequestMethod.GET)
	public ModelAndView showEfficienttGrievance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/efficientGrievances");
		try {

			model.addObject("title", "Mechanism of Examination Related Grievance(Efficient) ");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddEfficienttGrievance", method = RequestMethod.GET)
	public ModelAndView showAddEfficienttGrievance(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addEffiGrie");
		try {

			model.addObject("title", "Mechanism of Examination Related Grievance(Efficient) ");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showInstituteOfferingCourse", method = RequestMethod.GET)
	public ModelAndView showInstituteOfferingCourse(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/instituteOffersCourse");
		try {

			model.addObject("title", "Institute Offering Course");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showCurriculum", method = RequestMethod.GET)
	public ModelAndView showCurriculum(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/curriculumCrosscutting");
		try {

			model.addObject("title", "Curriculum & Cross-cutting Issues");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showFeedback", method = RequestMethod.GET)
	public ModelAndView showFeedback(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/feedBack");
		try {

			model.addObject("title", "Feedback");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	/*
	 * @RequestMapping(value = "/showEGovernanceFacilities", method =
	 * RequestMethod.GET) public ModelAndView
	 * showEGovernanceFacilities(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = new ModelAndView("instituteInfo/IQAC/eGovernance"); try
	 * {
	 * 
	 * 
	 * model.addObject("title", "Institute Profile Information");
	 * 
	 * } catch (Exception e) {
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return model;
	 * 
	 * }
	 */

	@RequestMapping(value = "/showCollaborationLinkages", method = RequestMethod.GET)
	public ModelAndView showCollaborationLinkages(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/collaborationLinkages");
		try {

			model.addObject("title", "Collaboration & Linkages");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddCollaborationLinkages", method = RequestMethod.GET)
	public ModelAndView showAddCollaborationLinkages(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addCollabLink");
		try {

			model.addObject("title", "Add Collaboration & Linkages");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showFunctionalMOUs", method = RequestMethod.GET)
	public ModelAndView showFunctionalMOUs(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/functionalMOUs");
		try {

			model.addObject("title", "Functional MoUs");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	//

	@RequestMapping(value = "/showAddFunctionalMOUs", method = RequestMethod.GET)
	public ModelAndView showAddFunctionalMOUs(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addFunMOUs");
		try {

			model.addObject("title", "Add Functional MoUs");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showIncubationCentreDetail", method = RequestMethod.GET)
	public ModelAndView showIncubationCentre(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/incubation");
		try {

			model.addObject("title", "Incubation Centre");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showBestPractice", method = RequestMethod.GET)
	public ModelAndView showBestPractice(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/bestPrac");
		try {

			model.addObject("title", "Best Practices");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showGenderEquity", method = RequestMethod.GET)
	public ModelAndView showGenderEquity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/genderEquity");
		try {

			model.addObject("title", "Gender Equality Programme");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showProgDistinctive", method = RequestMethod.GET)
	public ModelAndView showProgDistinctive(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/instDistinctive");
		try {

			model.addObject("title", " Institutional Distnctiveness");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showHumanValues", method = RequestMethod.GET)
	public ModelAndView showHumanValues(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/humanValues");
		try {

			model.addObject("title", "Human Values & Professional Ethics");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showInternalQualityInitiative", method = RequestMethod.GET)
	public ModelAndView showInternalQualityInitiative(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/internalQuality");
		try {

			model.addObject("title", "Internal Quality Initiatives");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showInitiativeToEngage", method = RequestMethod.GET)
	public ModelAndView showInitiativeToEngage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/initiativeToEnagage");
		try {

			model.addObject("title", "Initiative to Enagage & Contribute to Local Community");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showIntellectualProperty", method = RequestMethod.GET)
	public ModelAndView showIntellectualProperty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/intellectualProperty");
		try {

			model.addObject("title", "Intellectual Property Rights & Industries");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showProfDevelopment", method = RequestMethod.GET)
	public ModelAndView showProfDevelopment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/profDevelpment");
		try {

			model.addObject("title", "Professional Development");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAdminDevelopment", method = RequestMethod.GET)
	public ModelAndView showAdminDevelopment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/administrativeDevlop");
		try {

			model.addObject("title", "Administrative Development");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showGenderSensitivity", method = RequestMethod.GET)
	public ModelAndView showGenderSensitivity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/genderSensitivity");
		try {

			model.addObject("title", "Gender Sensitivity Shown in Facilities");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showEnvConciuosness", method = RequestMethod.GET)
	public ModelAndView showEnvConciuosness(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/envConciousness");
		try {

			model.addObject("title", "Environmental Consciousness and Sustainability");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showGreenPractices", method = RequestMethod.GET)
	public ModelAndView showGreenPractices(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/greenPractices");
		try {

			model.addObject("title", "Green Practices");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showSpecInitiatives", method = RequestMethod.GET)
	public ModelAndView showSpecInitiatives(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/specInitiatives");
		try {

			model.addObject("title", "Specific Initiatives");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showUderTakingProv", method = RequestMethod.GET)
	public ModelAndView showUderTakingProv(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/provForUndertaking");
		try {

			model.addObject("title", "Provision for Undertaking Field Projects/Internship");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAMC", method = RequestMethod.GET)
	public ModelAndView showAMC(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/amc");
		try {

			model.addObject("title", "Annual Maintenance");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showCommitteeDetail", method = RequestMethod.GET)
	public ModelAndView showCommitteeDetail(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/committee");
		try {

			model.addObject("title", "Committee Details");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddedCourses", method = RequestMethod.GET)
	public ModelAndView showAddedCourses(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addedCourse");
		try {

			model.addObject("title", "Course Details");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

}
