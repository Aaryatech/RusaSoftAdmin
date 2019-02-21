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
	
  @RequestMapping(value = "/showRegAcc", method = RequestMethod.GET)
	public ModelAndView showRefAcc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/accReg");

			model.addObject("title", "Account Officer Registration");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

  
  @RequestMapping(value = "/showAccList", method = RequestMethod.GET)
 	public ModelAndView showAccList(HttpServletRequest request, HttpServletResponse response) {

 		ModelAndView model = null;
 		try {

 			model = new ModelAndView("master/accList");

 			model.addObject("title", "Account Officer List");

 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}

  
  @RequestMapping(value = "/showRegDean", method = RequestMethod.GET)
 	public ModelAndView showRegDean(HttpServletRequest request, HttpServletResponse response) {

 		ModelAndView model = null;
 		try {

 			model = new ModelAndView("master/deanReg");

 			model.addObject("title", "Dean  Registration");

 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}
  
  
  @RequestMapping(value = "/showDeanList", method = RequestMethod.GET)
	public ModelAndView showDeanList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/deanList");

			model.addObject("title", "Dean R & D List");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

  @RequestMapping(value = "/showRegLib", method = RequestMethod.GET)
	public ModelAndView showRegLib(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/libReg");

			model.addObject("title", "Librarian Registration");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
  
  
  @RequestMapping(value = "/showLibList", method = RequestMethod.GET)
	public ModelAndView showLibList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/libList");

			model.addObject("title", "Librarian List");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
  
  @RequestMapping(value = "/showRegStud", method = RequestMethod.GET)
 	public ModelAndView showRegStud(HttpServletRequest request, HttpServletResponse response) {

 		ModelAndView model = null;
 		try {

 			model = new ModelAndView("master/studReg");

 			model.addObject("title", "Student Registration");

 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}
  
  
  @RequestMapping(value = "/showStudList", method = RequestMethod.GET)
 	public ModelAndView showStudList(HttpServletRequest request, HttpServletResponse response) {

 		ModelAndView model = null;
 		try {

 			model = new ModelAndView("master/studList");

 			model.addObject("title", "Student List");

 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}
  
  
  @RequestMapping(value = "/showBookPub", method = RequestMethod.GET)
	public ModelAndView showBookPub(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/FacultyDetails/bookPub");

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

			model = new ModelAndView("master/FacultyDetails/journalPub");

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

 			model = new ModelAndView("master/FacultyDetails/outReachContri");

 			model.addObject("title", "Out Reach Contribution");

 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}

  
  @RequestMapping(value = "/showLinkage", method = RequestMethod.GET)
	public ModelAndView showLinkage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ResearchInnovation/linkage");

			model.addObject("title", "Research & Innovation");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
  
  @RequestMapping(value = "/showMOUs", method = RequestMethod.GET)
 	public ModelAndView showMOUs(HttpServletRequest request, HttpServletResponse response) {

 		ModelAndView model = null;
 		try {

 			model = new ModelAndView("master/ResearchInnovation/mous");

 			model.addObject("title", "Research & Innovation");

 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}
  
  @RequestMapping(value = "/showExtAct", method = RequestMethod.GET)
	public ModelAndView showExtAct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ResearchInnovation/extActivities");

			model.addObject("title", "Research & Innovation");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

  @RequestMapping(value = "/showGenIssue", method = RequestMethod.GET)
 	public ModelAndView showGenIssue(HttpServletRequest request, HttpServletResponse response) {

 		ModelAndView model = null;
 		try {

 			model = new ModelAndView("master/ResearchInnovation/genderIssue");

 			model.addObject("title", "Research & Innovation");

 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}
  
  @RequestMapping(value = "/showComAct", method = RequestMethod.GET)
	public ModelAndView showComAct(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ResearchInnovation/comActivties");

			model.addObject("title", "Research & Innovation");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	/////////////////Program Details///////////////////
  
  @RequestMapping(value = "/showProgDetail1", method = RequestMethod.GET)
	public ModelAndView showProgDetail1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ProgramDetails/programDetails1");

			model.addObject("title", "Program Details");

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

 			model = new ModelAndView("master/ProgramDetails/studAdmitted");

 			model.addObject("title", "Student Addmitted");

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

			model = new ModelAndView("master/ProgramDetails/studAdmittedLoc");

			model.addObject("title", "Student Addmitted");

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

			model = new ModelAndView("master/ProgramDetails/StudSuppSch");

			model.addObject("title", "Program Details");

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

 			model = new ModelAndView("master/ProgramDetails/training");

 			model.addObject("title", "Program Details");

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

			model = new ModelAndView("master/ProgramDetails/highEdu");

			model.addObject("title", "Program Details");

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

 			model = new ModelAndView("master/ProgramDetails/studActivity");

 			model.addObject("title", "Program Details");

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

			model = new ModelAndView("master/ProgramDetails/alumini");

			model.addObject("title", "Program Details");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

///////////////////////infrastructure////////////////////////////
  
  
  @RequestMapping(value = "/showinfra", method = RequestMethod.GET)
	public ModelAndView showinfra(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/facilities");

			model.addObject("title", "Infrastructure Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
  
  @RequestMapping(value = "/showInstruct", method = RequestMethod.GET)
 	public ModelAndView showInstruct(HttpServletRequest request, HttpServletResponse response) {

 		ModelAndView model = null;
 		try {

 			model = new ModelAndView("master/InfrastructureModule/institutional");

 			model.addObject("title", "Infrastructure Detail");

 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}
  
  @RequestMapping(value = "/showAdmin", method = RequestMethod.GET)
	public ModelAndView showAdmin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/administrative");

			model.addObject("title", "Infrastructure Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
  @RequestMapping(value = "/showAmeneties", method = RequestMethod.GET)
	public ModelAndView showAmeneties(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/amenities");

			model.addObject("title", "Infrastructure Detail");
 
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
  
  @RequestMapping(value = "/showITinfra", method = RequestMethod.GET)
 	public ModelAndView showITinfra(HttpServletRequest request, HttpServletResponse response) {

 		ModelAndView model = null;
 		try {

 			model = new ModelAndView("master/InfrastructureModule/ITinfra");

 			model.addObject("title", "Infrastructure Detail");
  
 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}
  
  @RequestMapping(value = "/showInternetCon", method = RequestMethod.GET)
	public ModelAndView showInternetCon(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/internetCon");

			model.addObject("title", "Infrastructure Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

  @RequestMapping(value = "/showeContent", method = RequestMethod.GET)
 	public ModelAndView showeContent(HttpServletRequest request, HttpServletResponse response) {

 		ModelAndView model = null;
 		try {

 			model = new ModelAndView("master/InfrastructureModule/eContent");

 			model.addObject("title", "Infrastructure Detail");

 		} catch (Exception e) {

 			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

 			e.printStackTrace();

 		}

 		return model;

 	}

  
  @RequestMapping(value = "/showPhysicalFacilities", method = RequestMethod.GET)
	public ModelAndView showPhysicalFacilities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/InfrastructureModule/physicalFacilities");

			model.addObject("title", "Infrastructure Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}


  
 

}
