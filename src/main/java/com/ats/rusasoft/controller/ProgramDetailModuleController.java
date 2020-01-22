package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.ats.rusasoft.XssEscapeUtils;
import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.master.model.prodetail.StudentSchemeList;
import com.ats.rusasoft.model.GetProgram;
import com.ats.rusasoft.model.GetStudentDetail;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.ProgramOutcome;
import com.ats.rusasoft.model.ProgramSpeceficOutcome;
import com.ats.rusasoft.model.StudentSupprtScheme;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.InstituteFunctionalMOU;


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

			model.addObject("title", " Program");

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

			model.addObject("title", " Add Program ");

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

	@RequestMapping(value = "/showpoPso/{programId}", method = RequestMethod.GET)
	public ModelAndView showpoPso(@PathVariable int programId, HttpServletRequest request, HttpServletResponse response) {
System.err.println("HELLO " +programId);
		ModelAndView model = null;
		
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showpoPso", "showProgramList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

			model = new ModelAndView("ProgramDetails/poPSO");

			model.addObject("title", " PO-PSO Mapping");
			/*
			 * HttpSession session = request.getSession(); int instituteId
			 * =(int)session.getAttribute("instituteId");
			 */
            map = new LinkedMultiValueMap<>();
			
			map.add("programId", programId);
			GetProgram progDetail = rest.postForObject(Constants.url+"/getProgramByProgId", map, GetProgram.class);
			//System.out.println("Program:"+progDetail);
			model.addObject("progDetail", progDetail);
		
			//model.addObject("title", "Edit Student Support Scheme");
			//model.addObject("studId", studSchm.getSprtSchmId());
			
			 map = new LinkedMultiValueMap<>();
				
			map.add("programId", programId);
				

				ProgramOutcome[] instArray = rest.postForObject(Constants.url + "getProgramOutcomeListByProgramId", map,
						ProgramOutcome[].class);
				List<ProgramOutcome> poList = new ArrayList<>(Arrays.asList(instArray));

				//System.out.println("po list is" + poList.toString());
			
			
				model.addObject("poList", poList);
			
			}
			

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/showMapPOPSO/{poId}/{programId}", method = RequestMethod.GET)
	public ModelAndView showMapPOPSO(@PathVariable("poId") int poId,@PathVariable("programId") int programId, HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/mapPSO");

			model.addObject("title", "PO-PSO Mapping");
			
			
			    map = new LinkedMultiValueMap<>();
				
				map.add("programId", programId);
				GetProgram progDetail = rest.postForObject(Constants.url+"/getProgramByProgId", map, GetProgram.class);
				//System.out.println("Program:"+progDetail);
				model.addObject("progDetail", progDetail);
				
				
				map = new LinkedMultiValueMap<>();

				map.add("poId", poId);
				ProgramOutcome poDetail = rest.postForObject(Constants.url+"/getProgramOutcomeByPOId", map, ProgramOutcome.class);
				//System.out.println("Program:"+poDetail);
				model.addObject("poDetail", poDetail);
				model.addObject("poId1", poDetail.getPoId());
				//System.out.println("po id iss"+poDetail.getPoId());
				
				map = new LinkedMultiValueMap<>();
				
				map.add("programId", programId);
				
				ProgramSpeceficOutcome[] instArray = rest.postForObject(Constants.url + "getProgramSpecificOutcomeList", map,
						ProgramSpeceficOutcome[].class);
				List<ProgramSpeceficOutcome> psoList = new ArrayList<>(Arrays.asList(instArray));
				
				model.addObject("prgId",programId);
				model.addObject("psoDetail", psoList);
				
				
				String psoIds=poDetail.getPsoMapId();
				//System.out.println("poDetail detail::::"+poDetail.toString());
				//String[] psoIdsarray = psoIds.split(",",4);
				
				// String[] values = psoIds.split(",");
				 try {
				 List<String> items = Arrays.asList(psoIds.split(","));
				//System.out.println("psoIdsarray is after ::::"+items);
				 
				model.addObject("items",items);
				
				 }catch(Exception e) {
					 
				 }
				

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/popsomapped", method = RequestMethod.GET)
	public String popsomapped(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String a = null;

		String poId1 = request.getParameter("poId1");
		int prgId = Integer.parseInt(request.getParameter("prgId"));
		//System.out.println("po id "+poId1+" "+prgId);

		String satValue = request.getParameter("satValue");
		//System.out.println("po satValue "+satValue);
	
		try {
			
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			

					System.err.println("Multiple records delete ");
					String[] psoId = request.getParameterValues("psoIds");
					//System.out.println("id are" + psoId);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < psoId.length; i++) {
						sb = sb.append(psoId[i] + ",");

					}
					String psoIdList = sb.toString();
					psoIdList = psoIdList.substring(0, psoIdList.length() - 1);
					//System.out.println("pso id list"+psoIdList);
					
					
					
					map.add("psoIdList", psoIdList);
					map.add("poId", poId1);
					map.add("satValue", satValue);
					
					

				Info errMsg = rest.postForObject(Constants.url + "updatePoMapping", map, Info.class);
				a = "redirect:/showpoPso/"+prgId;
		
	}
		catch (Exception e) {

			System.err.println(" Exception In deleteStudents at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return a;

	}

	


	/*
	 * @RequestMapping(value = "/showStudAddmit", method = RequestMethod.GET) public
	 * ModelAndView showStudAddmit(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("ProgramDetails/studAdmitted");
	 * 
	 * model.addObject("title", "Student Addmitted Categorywise");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showStaffList at Master Contr" +
	 * e.getMessage());
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return model;
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = "/showAddStudAdmitCatWise", method =
	 * RequestMethod.GET) public ModelAndView
	 * showAddStudAddmitCatWise(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("ProgramDetails/addStudCatwise");
	 * 
	 * model.addObject("title", "Add Student  Categorywise");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showStaffList at Master Contr" +
	 * e.getMessage());
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return model;
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = "/showStudAddmitLoc", method = RequestMethod.GET)
	 * public ModelAndView showStudAddmitLoc(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("ProgramDetails/studAdmittedLoc");
	 * 
	 * model.addObject("title", "Student Addmitted Locationwise");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showStaffList at Master Contr" +
	 * e.getMessage());
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return model;
	 * 
	 * }
	 * 
	 * 
	 * @RequestMapping(value = "/showAddStudAddmitLocWise", method =
	 * RequestMethod.GET) public ModelAndView
	 * showAddStudAddmitLocWise(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("ProgramDetails/addStudLocwise");
	 * 
	 * model.addObject("title", "Add Student Locationwise ");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showStaffList at Master Contr" +
	 * e.getMessage());
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return model;
	 * 
	 * }
	 */
	

	/************************************************Student Support Scheme***************************************************/
	@RequestMapping(value = "/showAddStudSupp", method = RequestMethod.GET)
	public ModelAndView showAddStudSupp(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			
			model = new ModelAndView("ProgramDetails/addStudSuppSch");

			StudentSupprtScheme stud = new StudentSupprtScheme();
			model.addObject("stud", stud);
			model.addObject("title", "Add Student Support Scheme");

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

		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
		
		int instituteId =(int)session.getAttribute("instituteId");
		int yearId = (int)session.getAttribute("acYearId");
		try {
			
			int studSuprtSchm = Integer.parseInt(request.getParameter("stud_suprt_schm"));
			
			stud.setSprtSchmId(studSuprtSchm);
			stud.setInstituteId(instituteId);
			stud.setYearId(yearId);
			String scheme = request.getParameter("schemeName"); 
			if(scheme.contentEquals("7")) {
				stud.setSchemeName("NA");
				stud.setExtraVarchar1(XssEscapeUtils.jsoupParse(request.getParameter("anotherScheme")));
				stud.setExtraInt1(7);
				}else {
					stud.setSchemeName(scheme);
					stud.setExtraVarchar1("NA");
					stud.setExtraInt1(0);
				}
			
			
			stud.setLevel(request.getParameter("level"));
			stud.setType(request.getParameter("type"));
			stud.setNoStudentBenifited(Integer.parseInt(request.getParameter("studBenifit")));
			stud.setSupportAgencyName(XssEscapeUtils.jsoupParse(request.getParameter("supportAgency")));
			stud.setImplementationYear(request.getParameter("yearofIntro"));
			stud.setDelStatus(1);
			stud.setIsActive(1);
			stud.setAddDate(curDateTime);
			stud.setMakerUserId(userObj.getUserId());
			
			stud.setExtraInt2(0);
			
			stud.setExtraVarchar2("NA");
			//System.out.println("Student:"+stud.toString());
			
			StudentSupprtScheme studScheme = rest.postForObject(Constants.url+"/saveStudentSupprtScheme", stud, StudentSupprtScheme.class);
			
		}catch(Exception e) {
			System.err.println("Exce in save lib  " + e.getMessage());
			e.printStackTrace();
		}
		
		
		
		String a=null;
		int isView = Integer.parseInt(request.getParameter("is_view"));
		if (isView == 1)
			a = "redirect:/showStudSupp";

		else
			a = "redirect:/showAddStudSupp";
		
		
		return a;
		
	}

	@RequestMapping(value = "/showStudSupp", method = RequestMethod.GET)
	public ModelAndView showProgDetail(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();


			HttpSession session = request.getSession();
			int instituteId =(int)session.getAttribute("instituteId");
			int yearId = (int)session.getAttribute("acYearId");
			
			map.add("yearId",yearId);
			map.add("instId",instituteId);
		
		//List<StudentSupprtScheme> studSchmList = rest.postForObject(Constants.url+"/getAllStudentSchemes", map,List.class);
		////System.out.println("Student sch List:"+studSchmList.get(0).getImplementationYear());
			StudentSchemeList[] instArray = rest.postForObject(Constants.url + "getAllStudentSchemes", map,
					StudentSchemeList[].class);
			List<StudentSchemeList> studSchmList = new ArrayList<>(Arrays.asList(instArray));
			//System.out.println("Student sch List:"+studSchmList.toString());
			
			
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
		try {//System.out.println("id:"+stdSchmId);
			
			map = new LinkedMultiValueMap<>();
			
			map.add("id", stdSchmId);
			StudentSupprtScheme studSchm = rest.postForObject(Constants.url+"/getStudentSchemesById", map, StudentSupprtScheme.class);
			//System.out.println("Student:"+studSchm);
			
			
	
			
			model = new ModelAndView("ProgramDetails/addStudSuppSch");
			model.addObject("ydate",DateConvertor.convertToDMY(studSchm.getImplementationYear()));
			model.addObject("title", "Edit Student Support Scheme");
			model.addObject("studId", studSchm.getSprtSchmId());
			model.addObject("stud", studSchm);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	/*
	 * @RequestMapping(value = "/deleteStudSchm/{stdSchmId}", method =
	 * RequestMethod.GET) public String deleteStudSchm(HttpServletRequest request,
	 * HttpServletResponse response,
	 * 
	 * @PathVariable("stdSchmId") int stdSchmId) {
	 * 
	 * 
	 * try {//System.out.println("id:"+stdSchmId);
	 * 
	 * map = new LinkedMultiValueMap<>();
	 * 
	 * map.add("id", stdSchmId); StudentSupprtScheme studSchm =
	 * rest.postForObject(Constants.url+"/deleteStudentSchemesById", map,
	 * StudentSupprtScheme.class); //System.out.println("Student:"+studSchm);
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showStaffList at Master Contr" +
	 * e.getMessage());
	 * 
	 * e.printStackTrace();
	 * 
	 * }
	 * 
	 * return "redirect:/showStudSupp";
	 * 
	 * }
	 */
	
	
	@RequestMapping(value = "/deleteStudSchm/{stdSchmId}", method = RequestMethod.GET)
	public String deleteStudSchm(HttpServletRequest request, HttpServletResponse response, @PathVariable int stdSchmId) {

		HttpSession session = request.getSession();
		String a = null;
		/*
		 * List<ModuleJson> newModuleList = (List<ModuleJson>)
		 * session.getAttribute("newModuleList");
		 * 
		 * Info view = AccessControll.checkAccess("deleteStudSchm/{stdSchmId}",
		 * "showStudSupp", "0", "0", "0", "1", newModuleList); try {
		 */
			/*if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}*/

			/*else {*/
		
		try {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (stdSchmId == 0) {

					System.err.println("Multiple records delete ");
					String[] studentSchmIds = request.getParameterValues("studentSchmIds");
					//System.out.println("id are" + studentSchmIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < studentSchmIds.length; i++) {
						sb = sb.append(studentSchmIds[i] + ",");

					}
					String studIdList = sb.toString();
					studIdList = studIdList.substring(0, studIdList.length() - 1);
					//System.out.println("stud id list"+studIdList);

					map.add("id", studIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("id", stdSchmId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteStudentSchemesRecordById", map, Info.class);
				a ="redirect:/showStudSupp";
			/* } */
		} catch (Exception e) {

			System.err.println(" Exception In deleteStudents at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return a;

	}

	/*@RequestMapping(value = "/showStudTran", method = RequestMethod.GET)
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

	}*/

	/*@RequestMapping(value = "/showHighEdu", method = RequestMethod.GET)
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

	}*/

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

	
	/*@RequestMapping(value = "/showAlumini", method = RequestMethod.GET)
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

	}*/
	
	

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
