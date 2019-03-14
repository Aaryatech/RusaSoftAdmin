package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.StudentSupprtScheme;

@Controller
@Scope("session")
public class ProgramDetailModuleController {

	RestTemplate rest = new RestTemplate();
	
	MultiValueMap<String, Object> map = null;

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


	/************************************************Student Support Scheme***************************************************/
	@RequestMapping(value = "/showAddStudSupp", method = RequestMethod.GET)
	public ModelAndView showAddStudSupp(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			
			model = new ModelAndView("ProgramDetails/addStudSuppSch");

			StudentSupprtScheme stud = new StudentSupprtScheme();
			model.addObject("stud", stud);
			model.addObject("title", "Student Support Scheme");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	
	
	@RequestMapping(value="/insertStudentSuppurtScheme", method=RequestMethod.POST)
	public String  insertStudentSuppurtScheme(HttpServletRequest request, HttpServletResponse response) {
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String curDateTime = dateFormat.format(cal.getTime());
		
		StudentSupprtScheme stud = new StudentSupprtScheme();
		HttpSession session = request.getSession();
		int instituteId =(int)session.getAttribute("instituteId");
		int yearId = (int)session.getAttribute("acYearId");
		try {
			
			int studSuprtSchm = Integer.parseInt(request.getParameter("stud_suprt_schm"));
			
			stud.setSprtSchmId(studSuprtSchm);
			stud.setInstituteId(instituteId);
			stud.setYearId(yearId);
			String scheme = request.getParameter("schemeName"); 
			if(scheme.equals("7")) {
			stud.setSchemeName(request.getParameter("anotherScheme"));}
			else {
			stud.setSchemeName(scheme);}
			stud.setLevel(request.getParameter("level"));
			stud.setType(request.getParameter("type"));
			stud.setNoStudentBenifited(Integer.parseInt(request.getParameter("studBenifit")));
			stud.setSupportAgencyName(request.getParameter("supportAgency"));
			stud.setImplementationYear(request.getParameter("yearofIntro"));
			stud.setDelStatus(1);
			stud.setIsActive(1);
			stud.setAddDate(curDateTime);
			stud.setMakerUserId(0);
			stud.setExtraInt1(1);//Programe Id
			stud.setExtraInt2(0);
			stud.setExtraVarchar1("NA");
			stud.setExtraVarchar2("NA");
			System.out.println("Student:"+stud.toString());
			
			StudentSupprtScheme studScheme = rest.postForObject(Constants.url+"/saveStudentSupprtScheme", stud, StudentSupprtScheme.class);
			
		}catch(Exception e) {
			
		}
		
		return "redirect:/showAddStudSupp";
		
	}

	@RequestMapping(value = "/showStudSupp", method = RequestMethod.GET)
	public ModelAndView showProgDetail(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			List<StudentSupprtScheme> studSchmList = rest.getForObject(Constants.url+"/getAllStudentSchemes", List.class);
			System.out.println("Student List:"+studSchmList);
			
			model = new ModelAndView("ProgramDetails/StudSuppSch");

			model.addObject("title", "Student Support Scheme");
			
			model.addObject("studList", studSchmList);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/editStudSchm/{stdSchmId}", method = RequestMethod.GET)
	public ModelAndView editStudSchm(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("stdSchmId") int stdSchmId) {

		ModelAndView model = null;
		try {System.out.println("id:"+stdSchmId);
			
			map = new LinkedMultiValueMap<>();
			
			map.add("id", stdSchmId);
			StudentSupprtScheme studSchm = rest.postForObject(Constants.url+"/getStudentSchemesById", map, StudentSupprtScheme.class);
			System.out.println("Student:"+studSchm);
			
			model = new ModelAndView("ProgramDetails/addStudSuppSch");

			model.addObject("title", "Edit Student Support Scheme");
			model.addObject("studId", studSchm.getSprtSchmId());
			model.addObject("stud", studSchm);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/deleteStudSchm/{stdSchmId}", method = RequestMethod.GET)
	public String deleteStudSchm(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("stdSchmId") int stdSchmId) {

		
		try {System.out.println("id:"+stdSchmId);
			
			map = new LinkedMultiValueMap<>();
			
			map.add("id", stdSchmId);
			StudentSupprtScheme studSchm = rest.postForObject(Constants.url+"/deleteStudentSchemesById", map, StudentSupprtScheme.class);
			System.out.println("Student:"+studSchm);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/showStudSupp";

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
