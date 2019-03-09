package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Commons;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.model.AccOfficer;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.GetAccOfficer;
import com.ats.rusasoft.model.GetInstituteList;
import com.ats.rusasoft.model.Hod;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Quolification;

@Controller
@Scope("session")
public class MasterController {
	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/checkUniqueField", method = RequestMethod.GET)
	public @ResponseBody Info checkUniqueField(HttpServletRequest request, HttpServletResponse response) {

		Info info = new Info();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String inputValue = request.getParameter("inputValue");
			int valueType = Integer.parseInt(request.getParameter("valueType"));
			int primaryKey = Integer.parseInt(request.getParameter("primaryKey"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int tableId = Integer.parseInt(request.getParameter("tableId"));
			
			
			map.add("inputValue", inputValue);
			map.add("valueType",valueType);
			map.add("tableId",tableId );
			map.add("isEditCall",isEdit );
			map.add("primaryKey", primaryKey);
			
			info = rest.postForObject(Constants.url + "checkUniqueField", map, Info.class);
			System.err.println("Info Response  " +info.toString());

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = "/showRegisterInstitute", method = RequestMethod.GET)
	public ModelAndView showRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/reginstitute");

			model.addObject("title", "Register Institute");

			Institute editInst = new Institute();

			model.addObject("editInst", editInst);

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

	

	// instituteList

	@RequestMapping(value = "/showInstituteList", method = RequestMethod.GET)
	public ModelAndView showInstituteList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/instituteList");
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			GetInstituteList[] instArray = restTemplate.getForObject(Constants.url + "getAllInstitutes",
					GetInstituteList[].class);
			List<GetInstituteList> instList = new ArrayList<>(Arrays.asList(instArray));

			model.addObject("instList", instList);

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

	/////////////////////////// ****faculty
	/////////////////////////// details****//////////////////////////////////

	@RequestMapping(value = "/showFacultyDetails/{type}", method = RequestMethod.GET)
	public ModelAndView showFacultyDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int type) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/facultyDetails");

			model.addObject("title", "Faculty Details Form");
			System.out.println("type is" + type);
			model.addObject("formindex", type);

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

	@RequestMapping(value = "/showSubDetails1", method = RequestMethod.GET)
	public ModelAndView showSubDetails1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ResearchInnovation/subDetails1");

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
			
			AccOfficer accOff=new AccOfficer();
			
			model.addObject("accOff", accOff);
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("type", 1);
			Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
					Quolification[].class);
			List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("quolfList " + quolfList.toString());

			model.addObject("quolfList", quolfList);
			

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
			
			HttpSession session = request.getSession();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());
			GetAccOfficer[] accOffArray = rest.postForObject(Constants.url + "getAccOffList", map, GetAccOfficer[].class);
			List<GetAccOfficer> accOffList = new ArrayList<>(Arrays.asList(accOffArray));
			System.err.println("accOffList " + accOffList.toString());

			model.addObject("accOffList", accOffList);
			

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

			model.addObject("title", "Linkage");

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

			model.addObject("title", "MOUs");

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

			model.addObject("title", "Extension Activities");

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

			model.addObject("title", "Gender Issue Detail");

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

			model.addObject("title", "Community Activities");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	///////////////// Program Details///////////////////

	@RequestMapping(value = "/showEucationalObjective", method = RequestMethod.GET)
	public ModelAndView showEucationalObjective(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/ProgramDetails/educationalObjective");

			model.addObject("title", "Program Details");

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

			model = new ModelAndView("master/ProgramDetails/poPSO");

			model.addObject("title", "Program Details");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

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

			model.addObject("title", "Student Addmitted Categorywise");

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

			model.addObject("title", "Student Addmitted Locationwise");

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

			model = new ModelAndView("master/ProgramDetails/training");

			model.addObject("title", "Training Details");

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

			model.addObject("title", "Higher Education Details");

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

			model = new ModelAndView("master/ProgramDetails/studActivityAttend");

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

			model = new ModelAndView("master/ProgramDetails/alumini");

			model.addObject("title", "Alumini");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	/////////////////////// infrastructure////////////////////////////

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

			model.addObject("title", "Instructional Detail");

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

			model.addObject("title", "Adminstrative Detail");

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

			model.addObject("title", "Ameneties Detail");

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

			model.addObject("title", "IT Infrastructure Detail");

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

			model.addObject("title", "Internet Connection Detail");

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

			model.addObject("title", "e Content Detail");

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

			model.addObject("title", "Physical Facilities ");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAdjuntFaculty", method = RequestMethod.GET)
	public ModelAndView showAdjuntFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/adjuntFaculty");

			model.addObject("title", "Adjunt Faculty Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showFacultyInfo", method = RequestMethod.GET)
	public ModelAndView showFacultyInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/facultyInfo");

			model.addObject("title", " Faculty Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// Insert Institute

	@RequestMapping(value = "/insertInstitute", method = RequestMethod.POST)
	public String insertInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		int instId = Integer.parseInt(request.getParameter("inst_id"));

		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int exInt = 0;
			String exVar = "";
			if (instId == 0) {
				Institute institute = new Institute();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				String aisheCode = request.getParameter("aishe_code");
				institute.setAisheCode(aisheCode);

				institute.setCheckerDatetime(curDateTime);
				institute.setCheckerUserId(0);

				institute.setContactNo(request.getParameter("princ_contact"));
				institute.setDelStatus(1);
				institute.setEmail(request.getParameter("princ_email"));

				institute.setExInt1(exInt);
				institute.setExInt2(exInt);
				institute.setExVar1(exVar);
				institute.setExVar2(exVar);

				institute.setInstituteAdd(request.getParameter("inst_add"));
				institute.setInstituteId(instId);
				institute.setInstituteName(request.getParameter("inst_name"));

				institute.setIsActive(1);
				institute.setIsEnrollSystem(0);// set to 1 when user loged in for first time and changed his/her pass.
												// Initially its zero
				int isReg = Integer.parseInt(request.getParameter("is_registration"));
				institute.setIsRegistration(isReg);

				institute.setLastUpdatedDatetime(curDateTime);
				institute.setMakerEnterDatetime(curDateTime);
				institute.setMakerUserId(0);// user id who is creating this record for ex principal is
																// user who creates
				// iqac
				// and hod to student

				institute.setPresidentName(request.getParameter("pres_name"));
				institute.setPrincipalName(request.getParameter("princ_name"));
				if (isReg == 1)
					institute.setRegDate(DateConvertor.convertToYMD(request.getParameter("reg_date")));
				institute.setTrustAdd(request.getParameter("trusty_add"));

				institute.setTrustContactNo(request.getParameter("trusty_con_no"));
				institute.setTrustName(request.getParameter("trusty_name"));
				institute.setUserType(0);// for institute its 0

				institute.setPresidenContact(request.getParameter("pres_contact"));
				institute.setPresidentEmail(request.getParameter("pres_email"));

				System.out.println(institute);

				Institute info = restTemplate.postForObject(Constants.url + "saveInstitute", institute,
						Institute.class);

			} else {

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", instId);
				// getInstitute
				Institute institute = rest.postForObject(Constants.url + "getInstitute", map, Institute.class);

				String aisheCode = request.getParameter("aishe_code");
				institute.setAisheCode(aisheCode);

				institute.setContactNo(request.getParameter("princ_contact"));
				institute.setEmail(request.getParameter("princ_email"));
				institute.setInstituteAdd(request.getParameter("inst_add"));
				institute.setInstituteName(request.getParameter("inst_name"));

				int isReg = Integer.parseInt(request.getParameter("is_registration"));
				institute.setIsRegistration(isReg);

				institute.setLastUpdatedDatetime(curDateTime);

				institute.setPresidentName(request.getParameter("pres_name"));
				institute.setPrincipalName(request.getParameter("princ_name"));
				if (isReg == 1)
					institute.setRegDate(DateConvertor.convertToYMD(request.getParameter("reg_date")));
				else
					institute.setRegDate(null);

				institute.setTrustAdd(request.getParameter("trusty_add"));

				institute.setTrustContactNo(request.getParameter("trusty_con_no"));
				institute.setTrustName(request.getParameter("trusty_name"));

				institute.setPresidenContact(request.getParameter("pres_contact"));
				institute.setPresidentEmail(request.getParameter("pres_email"));

				Institute info = restTemplate.postForObject(Constants.url + "saveInstitute", institute,
						Institute.class);
			}

		} catch (Exception e) {

			System.err.println(" Exception In saveInstitute at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		if (instId == 0)
			return "redirect:/showPendingInstitute";
		else
			return "redirect:/showInstituteList";

	}

	// deleteInstitutes
	@RequestMapping(value = "/deleteInstitutes/{instId}", method = RequestMethod.GET)
	public String deleteInstitutes(HttpServletRequest request, HttpServletResponse response, @PathVariable int instId) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			if (instId == 0) {

				System.err.println("Multiple records delete ");
				String[] instIds = request.getParameterValues("instIds");
				System.out.println("id are" + instIds);

				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < instIds.length; i++) {
					sb = sb.append(instIds[i] + ",");

				}
				String instIdList = sb.toString();
				instIdList = instIdList.substring(0, instIdList.length() - 1);

				map.add("instIdList", instIdList);
			} else {

				System.err.println("Single Record delete ");
				map.add("instIdList", instId);
			}

			Info errMsg = rest.postForObject(Constants.url + "deleteInstitutes", map, Info.class);

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/showInstituteList";

	}

	// Approve Inst

	@RequestMapping(value = "/approveInstitutes/{instId}", method = RequestMethod.GET)
	public String approveInstitutes(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int instId) {

		try {
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			if (instId == 0) {

				System.err.println("Multiple records approve ");
				String[] instIds = request.getParameterValues("instIds");
				System.out.println("id are" + instIds);

				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < instIds.length; i++) {
					sb = sb.append(instIds[i] + ",");

				}
				String instIdList = sb.toString();
				instIdList = instIdList.substring(0, instIdList.length() - 1);

				map.add("instIdList", instIdList);
				map.add("aprUserId", userObj.getUserId());
			} else {
				map.add("aprUserId", userObj.getUserId());

				System.err.println("Single Record delete ");
				map.add("instIdList", instId);
			}

			Info errMsg = rest.postForObject(Constants.url + "approveInstitutes", map, Info.class);

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/showInstituteList";

	}

	@RequestMapping(value = "/showEditInstitute", method = RequestMethod.POST)
	public ModelAndView showEditInstitute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		try {

			model = new ModelAndView("master/reginstitute");

			int instId = Integer.parseInt(request.getParameter("edit_inst_id"));

			model.addObject("title", " Edit Institute Reginstration");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instituteId", instId);
			// getInstitute
			Institute editInst = rest.postForObject(Constants.url + "getInstitute", map, Institute.class);
			try {
				editInst.setRegDate(DateConvertor.convertToDMY(editInst.getRegDate()));
			} catch (Exception e) {
				System.err.println("Exce in show Edit Institute /showEditInstitute");
			}
			model.addObject("editInst", editInst);
			model.addObject("instituteId", instId);

		} catch (Exception e) {
			System.err.println("Exce in editInstitute/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	// Dept

	// insertDept

	@RequestMapping(value = "/insertDept", method = RequestMethod.POST)
	public String insertDept(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert dept");
		ModelAndView model = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int deptId = Integer.parseInt(request.getParameter("dept_id"));
			System.err.println("Dept id  " + deptId);

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			if (deptId == 0) {
				Dept dept = new Dept();
				String deptName = request.getParameter("dept_name");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				dept.setAddDate(curDate);
				dept.setDelStatus(1);
				dept.setDeptId(deptId);
				dept.setDeptName(deptName);
				int exInt1 = 0;
				dept.setExInt1(exInt1);
				dept.setExInt2(exInt1);
				String exVar1 = "NA";
				dept.setExVar1(exVar1);
				dept.setExVar2(exVar1);
				dept.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session
				dept.setIsActive(1);
				dept.setMakerUserId(userObj.getUserId());// get from Session
				Dept editInst = rest.postForObject(Constants.url + "saveDept", dept, Dept.class);

			} else {

				map.add("deptId", deptId);
				// getInstitute
				Dept dept = rest.postForObject(Constants.url + "getDept", map, Dept.class);
				String deptName = request.getParameter("dept_name");
				dept.setDeptName(deptName);
				dept.setMakerUserId(userObj.getUserId());// get from Session
				Dept editInst = rest.postForObject(Constants.url + "saveDept", dept, Dept.class);

			}

		} catch (Exception e) {
			System.err.println("Exce in save dept  " + e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/showDeptList";

	}
	// getAllDeptList

	@RequestMapping(value = "/showEditDept", method = RequestMethod.POST)
	public ModelAndView showEditDept(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		try {

			model = new ModelAndView("master/addFaculty");

			int deptId = Integer.parseInt(request.getParameter("edit_dept_id"));

			model.addObject("title", " Edit Institute Reginstration");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("deptId", deptId);
			// getInstitute
			Dept editDept = rest.postForObject(Constants.url + "getDept", map, Dept.class);
			model.addObject("dept", editDept);
			model.addObject("deptId", deptId);

		} catch (Exception e) {
			System.err.println("Exce in showEditDept  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/deleteDepts/{deptId}", method = RequestMethod.GET)
	public String deleteDepts(HttpServletRequest request, HttpServletResponse response, @PathVariable int deptId) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			if (deptId == 0) {

				System.err.println("Multiple records delete ");
				String[] instIds = request.getParameterValues("deptIds");
				System.out.println("id are" + instIds);

				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < instIds.length; i++) {
					sb = sb.append(instIds[i] + ",");

				}
				String deptIdList = sb.toString();
				deptIdList = deptIdList.substring(0, deptIdList.length() - 1);

				map.add("deptIdList", deptIdList);
			} else {

				System.err.println("Single Record delete ");
				map.add("deptIdList", deptId);
			}

			Info errMsg = rest.postForObject(Constants.url + "deleteDepts", map, Info.class);

		} catch (Exception e) {

			System.err.println(" Exception In deleteDepts at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/showDeptList";

	}

	// insertHod
	@RequestMapping(value = "/insertHod", method = RequestMethod.POST)
	public String insertHod(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertHod");
		ModelAndView model = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int hodId = Integer.parseInt(request.getParameter("hod_id"));
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			System.err.println("hodId id  " + hodId);
			if (hodId == 0) {

				Hod hod = new Hod();

				String deptName = request.getParameter("dept_name");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				hod.setContactNo(request.getParameter("hod_mob"));
				hod.setDelStatus(1);
				hod.setDeptId(Integer.parseInt(request.getParameter("hod_dept_id")));
				hod.setEditBy(0);
				hod.setEmail(request.getParameter("hod_email"));
				hod.setExInt1(1);
				hod.setExInt2(2);
				hod.setExVar1("NA");
				hod.setExVar2("NA");
				hod.setHighestQualificationId(Integer.parseInt(request.getParameter("hod_quolf")));
				hod.setHodId(hodId);
				hod.setHodName(request.getParameter("hod_name"));
				hod.setInstituteId(userObj.getGetData().getUserInstituteId());
				hod.setIsActive(1);
				hod.setIsEnrollSystem(0);
				hod.setMakerDate(curDateTime);
				hod.setMakerId(userObj.getUserId());
				hod.setUpdateDatetime(curDateTime);

				Hod editInst = rest.postForObject(Constants.url + "saveHod", hod, Hod.class);

			} else {

				map.add("hodId", hodId);
				// getInstitute
				Hod hod = rest.postForObject(Constants.url + "getHod", map, Hod.class);
				String deptName = request.getParameter("dept_name");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				hod.setContactNo(request.getParameter("hod_mob"));
				hod.setDeptId(Integer.parseInt(request.getParameter("hod_dept_id")));
				hod.setEditBy(userObj.getUserId());// session
				hod.setEmail(request.getParameter("hod_email"));

				hod.setHighestQualificationId(Integer.parseInt(request.getParameter("hod_quolf")));
				hod.setHodName(request.getParameter("hod_name"));
				hod.setInstituteId(userObj.getGetData().getUserInstituteId());// from sess
				hod.setUpdateDatetime(curDateTime);

				Hod editInst = rest.postForObject(Constants.url + "saveHod", hod, Hod.class);

			}

		} catch (Exception e) {
			System.err.println("Exce in save dept  " + e.getMessage());
			e.printStackTrace();
		}

		int isView = Integer.parseInt(request.getParameter("is_view"));
		if (isView == 1)
			return "redirect:/hodList";
		else
			return "redirect:/hodRegistration";

	}

	// showEditHod
	@RequestMapping(value = "/showEditHod", method = RequestMethod.POST)
	public ModelAndView showEditHod(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		try {

			model = new ModelAndView("master/hodRegistration");

			int hodId = Integer.parseInt(request.getParameter("edit_hod_id"));

			model.addObject("title", " Edit HOD");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("hodId", hodId);
			// getInstitute
			Hod editHod = rest.postForObject(Constants.url + "getHod", map, Hod.class);
			model.addObject("hod", editHod);
			model.addObject("hodId", hodId);

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());
			Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
			List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
			System.err.println("deptList " + deptList.toString());

			model.addObject("deptList", deptList);

			map = new LinkedMultiValueMap<String, Object>();

			map.add("type", 1);
			Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
					Quolification[].class);
			List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("quolfList " + quolfList.toString());

			model.addObject("quolfList", quolfList);

		} catch (Exception e) {
			System.err.println("Exce in showEditHod  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	// deleteInstitutes
	@RequestMapping(value = "/deleteHod/{hodId}", method = RequestMethod.GET)
	public String deleteHod(HttpServletRequest request, HttpServletResponse response, @PathVariable int hodId) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			if (hodId == 0) {

				System.err.println("Multiple records delete ");
				String[] instIds = request.getParameterValues("hodIds");
				System.out.println("id are" + instIds);

				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < instIds.length; i++) {
					sb = sb.append(instIds[i] + ",");

				}
				String hodIdList = sb.toString();
				hodIdList = hodIdList.substring(0, hodIdList.length() - 1);

				map.add("hodIdList", hodIdList);
			} else {

				System.err.println("Single Record delete ");
				map.add("hodIdList", hodId);
			}

			Info errMsg = rest.postForObject(Constants.url + "deleteHods", map, Info.class);

		} catch (Exception e) {

			System.err.println(" Exception In deleteHod at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/hodList";

	}
	// insertAccOff fdf
		@RequestMapping(value = "/insertAccOff", method = RequestMethod.POST)
		public String insertAccOff(HttpServletRequest request, HttpServletResponse response) {
			System.err.println("in insert insertAccOff");
			ModelAndView model = null;
			try {

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int officerId = Integer.parseInt(request.getParameter("acc_off_id"));
				HttpSession session = request.getSession();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				System.err.println("officerId id  " + officerId);
				if (officerId == 0) {

					Hod hod = new Hod();

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());
					
					AccOfficer acOff=new AccOfficer();
					
					acOff.setAccOfficerName(request.getParameter("acc_off_name"));
					acOff.setContactNo(request.getParameter("acc_off_mob"));
					acOff.setDelStatus(1);
					acOff.setEmail(request.getParameter("acc_off_email"));
					acOff.setExInt1(1);
					acOff.setExVar1("Na");
					acOff.setInstituteId(userObj.getGetData().getUserInstituteId());
					acOff.setIsActive(1);
					acOff.setJoiningDate(DateConvertor.convertToYMD(request.getParameter("acc_off_joinDate")));
					acOff.setMakerEnterDatetime(curDateTime);
					acOff.setMakerUserId(userObj.getUserId());
					acOff.setOfficerId(officerId);
					acOff.setQualificationId(Integer.parseInt(request.getParameter("acc_quolf")));
					int isReg = Integer.parseInt(request.getParameter("is_registration"));

					if(isReg==0) {
					acOff.setRealivingDate(DateConvertor.convertToYMD(request.getParameter("acc_off_relDate")));
					
					}else {
						acOff.setRealivingDate(null);
					}

					AccOfficer editInst = rest.postForObject(Constants.url + "saveAccOfficer", acOff, AccOfficer.class);

				} else {

					map.add("accOffId", officerId);
					AccOfficer acOff=rest.postForObject(Constants.url + "getAccOfficer", map,
							AccOfficer.class);
					String deptName = request.getParameter("dept_name");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());


					acOff.setAccOfficerName(request.getParameter("acc_off_name"));
					acOff.setContactNo(request.getParameter("acc_off_mob"));
					acOff.setEmail(request.getParameter("acc_off_email"));
					acOff.setInstituteId(userObj.getGetData().getUserInstituteId());
					acOff.setJoiningDate(DateConvertor.convertToYMD(request.getParameter("acc_off_joinDate")));
					acOff.setOfficerId(officerId);
					acOff.setQualificationId(Integer.parseInt(request.getParameter("acc_quolf")));
					int isReg = Integer.parseInt(request.getParameter("is_registration"));

					if(isReg==0) {
					acOff.setRealivingDate(DateConvertor.convertToYMD(request.getParameter("acc_off_relDate")));
					
					}else {
						acOff.setRealivingDate(null);
					}

					AccOfficer editInst = rest.postForObject(Constants.url + "saveAccOfficer", acOff, AccOfficer.class);

				}

			} catch (Exception e) {
				System.err.println("Exce in save dept  " + e.getMessage());
				e.printStackTrace();
			}

			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				return "redirect:/showAccList";
			else
				return "redirect:/showRegAcc";

		}
		
		//showEditaccOff
		@RequestMapping(value = "/showEditaccOff", method = RequestMethod.POST)
		public ModelAndView showEditaccOff(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = null;
			try {

				model = new ModelAndView("master/accReg");

				model.addObject("title", "Edit Account Officer");
				int accOffId = Integer.parseInt(request.getParameter("edit_accOff_id"));

				
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);
				map = new LinkedMultiValueMap<String, Object>();

				map.add("accOffId", accOffId);
				AccOfficer accOff=rest.postForObject(Constants.url + "getAccOfficer", map,
						AccOfficer.class);
				accOff.setJoiningDate(DateConvertor.convertToDMY(accOff.getJoiningDate()));
				try {
					accOff.setRealivingDate(DateConvertor.convertToDMY(accOff.getRealivingDate()));
				}catch (Exception e) {
					
				}
				
				model.addObject("accOff", accOff);


			} catch (Exception e) {

				System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

				e.printStackTrace();

			}

			return model;

		}
		
		//deleteaccOff
		@RequestMapping(value = "/deleteaccOff/{accOffIds}", method = RequestMethod.GET)
		public String deleteaccOff(HttpServletRequest request, HttpServletResponse response, @PathVariable int accOffIds) {

			try {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (accOffIds == 0) {

					System.err.println("Multiple records delete ");
					String[] acOfIds = request.getParameterValues("accOffIds");
					System.out.println(" acOfIds id are" + acOfIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < acOfIds.length; i++) {
						sb = sb.append(acOfIds[i] + ",");

					}
					String accOffIdList = sb.toString();
					accOffIdList = accOffIdList.substring(0, accOffIdList.length() - 1);

					map.add("accOffIds", accOffIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("accOffIds", accOffIds);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteAccOfficers", map, Info.class);

			} catch (Exception e) {

				System.err.println(" Exception In deleteaccOff at Master Contr " + e.getMessage());

				e.printStackTrace();

			}

			return "redirect:/showAccList";

		}
		
		

}
