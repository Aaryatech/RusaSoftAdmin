/*package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.master.model.Info;
import com.ats.rusasoft.model.LinkageMaster;
import com.ats.rusasoft.model.LoginResponse;
import com.itextpdf.text.List;

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

	@RequestMapping(value = "/showAddInstProf", method = RequestMethod.GET)
	public ModelAndView showAddInstProf(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/list_instProf");
		try {

			model.addObject("title", "Institute Profile Information");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	
	 * @RequestMapping(value = "/showVisionMission", method = RequestMethod.GET)
	 * public ModelAndView showVisionMission(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = new ModelAndView("instituteInfo/IQAC/visionMission");
	 * try {
	 * 
	 * model.addObject("title", "Institute Vision And Mission");
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
	 

	
	
	
	 * @RequestMapping(value = "/showActivityOrganized", method = RequestMethod.GET)
	 * public ModelAndView showActivityOrganized(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = new
	 * ModelAndView("instituteInfo/IQAC/activityOrganized"); try {
	 * 
	 * model.addObject("title", "Activities Organized");
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
	 * 
	 * @RequestMapping(value = "/showAddActivityOrganized", method =
	 * RequestMethod.GET) public ModelAndView
	 * showAddActivityOrganized(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = new
	 * ModelAndView("instituteInfo/IQAC/add_activity_organized"); try {
	 * 
	 * model.addObject("title", "Add Activities Organized");
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

	@RequestMapping(value = "/showAddInstituteOfferingCourse", method = RequestMethod.GET)
	public ModelAndView showAddInstituteOfferingCourse(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_institute_course");
		try {

			model.addObject("title", "Add Institute Offering Course");

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

	@RequestMapping(value = "/showAddIncubationCentreDetail", method = RequestMethod.GET)
	public ModelAndView showAddIncubationCentreDetail(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_incubation");
		try {

			model.addObject("title", "Add Incubation Centre");

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

	@RequestMapping(value = "/showAddGenderEquity", method = RequestMethod.GET)
	public ModelAndView showAddGenderEquity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_gender_equity");
		try {

			model.addObject("title", "Gender Equality Programme");

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

	@RequestMapping(value = "/showAddInitiativeToEngage", method = RequestMethod.GET)
	public ModelAndView showAddInitiativeToEngage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_initiative");
		try {

			model.addObject("title", "Add Initiative to Enagage & Contribute to Local Community");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showIntellectualProperty", method = RequestMethod.GET)
	public ModelAndView showIntellectualProperty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/intellectualProperty");
		try {

			model.addObject("title", "Intellectual Property Rights And Industries");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddIntellectualProperty", method = RequestMethod.GET)
	public ModelAndView showAddIntellectualProperty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_intel_prop");
		try {

			model.addObject("title", "Intellectual Property Rights And Industries");

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

	@RequestMapping(value = "/showAddEnvConciuosness", method = RequestMethod.GET)
	public ModelAndView showAddEnvConciuosness(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/env_conciousness_list");
		try {

			model.addObject("title", "Environmental Consciousness and Sustainability List");

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
	//

	@RequestMapping(value = "/showAddUderTakingProv", method = RequestMethod.GET)
	public ModelAndView showAddUderTakingProv(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_prov_under_taking");
		try {

			model.addObject("title", "Add Provision for Undertaking Field Projects/Internship");

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

*/