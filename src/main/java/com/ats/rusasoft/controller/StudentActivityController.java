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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.master.ProgramMission;
import com.ats.rusasoft.master.ProgramSpeceficOutcome;
import com.ats.rusasoft.master.model.Program;
import com.ats.rusasoft.master.model.prodetail.ProgramType;
import com.ats.rusasoft.master.model.prodetail.StudQualifyingExam;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.MExtensionActivity;
import com.ats.rusasoft.model.ProgramActivity;
import com.ats.rusasoft.model.ProgramDetailSaveResponse;
import com.ats.rusasoft.model.ProgramEducationObjective;
import com.ats.rusasoft.model.ProgramOutcome;
import com.ats.rusasoft.model.ProgramVision;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.StudPerformFinalYr;
import com.ats.rusasoft.model.StudPerformFinalYrList;
import com.ats.rusasoft.model.TExtensionActivity;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class StudentActivityController {

	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/showStudOrgnizedActivity", method = RequestMethod.GET)
	public ModelAndView showStudOrgnizedActivity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showStudOrgnizedActivity", "showStudOrgnizedActivity", "1", "0",
					"0", "0", newModuleList);
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			System.out.println(view);

			if (view.isError() == false) {

				model = new ModelAndView("ProgramDetails/studActivity");
				model.addObject("title", "Student Activity Organized List");
				Info add = AccessControll.checkAccess("showStudOrgnizedActivity", "showStudOrgnizedActivity", "0", "1",
						"0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showStudOrgnizedActivity", "showStudOrgnizedActivity", "0", "0",
						"1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showStudOrgnizedActivity", "showStudOrgnizedActivity", "0",
						"0", "0", "1", newModuleList);

				if (add.isError() == false) {
					model.addObject("isAdd", 1);
				}
				if (edit.isError() == false) {
					model.addObject("isEdit", 1);
				}
				if (delete.isError() == false) {
					model.addObject("isDelete", 1);
				}
				int acYearId = (Integer) session.getAttribute("acYearId");
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("yearId", acYearId);
				map.add("type", 0);
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				ProgramActivity[] programActivity = restTemplate
						.postForObject(Constants.url + "getStudentAcitivityList", map, ProgramActivity[].class);
				List<ProgramActivity> list = new ArrayList<ProgramActivity>(Arrays.asList(programActivity));
				model.addObject("list", list);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddStudentOrgnizedActivity", method = RequestMethod.GET)
	public ModelAndView showAddStudentOrgnizedActivity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddStudentOrgnizedActivity", "showStudOrgnizedActivity", "0",
					"1", "0", "0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				model = new ModelAndView("ProgramDetails/addStudActOrganized");
				model.addObject("title", "Add Student Activity Organized");

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/submitOrgnizedActivity", method = RequestMethod.POST)
	public String submitOrgnizedActivity(HttpServletRequest request, HttpServletResponse response) {

		String returnString = new String();

		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddStudentOrgnizedActivity", "showStudOrgnizedActivity", "0",
					"1", "0", "0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				Date maleDate = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

				String activityName = request.getParameter("activityName");
				String date = request.getParameter("date");
				String year = request.getParameter("year");
				String branch = request.getParameter("branch");
				int noStudent = Integer.parseInt(request.getParameter("noStudent"));
				String level = request.getParameter("level");
				int is_view = Integer.parseInt(request.getParameter("is_view"));
				int acYearId = (Integer) session.getAttribute("acYearId");
				int activityId = Integer.parseInt(request.getParameter("activityId"));

				ProgramActivity programActivity = new ProgramActivity();
				if (activityName.equals("7")) {
					String otherActivityName = request.getParameter("otherActivityName");
					programActivity.setActivityName(otherActivityName);
				} else {
					programActivity.setActivityName(activityName);
				}

				if (activityId != 0) {

					programActivity.setStudentActivityId(activityId);
				}

				programActivity.setDate(date);
				programActivity.setYear(year);
				programActivity.setParticipatedStudent(noStudent);
				programActivity.setBranch(branch);
				programActivity.setDelStatus(1);
				programActivity.setInstituteId(userObj.getGetData().getUserInstituteId());
				programActivity.setLevel(level);
				programActivity.setAddDate(sf.format(maleDate));
				programActivity.setMakerUserId(userObj.getUserId());
				programActivity.setYearId(acYearId);
				programActivity.setIsActive(1);
				programActivity.setType(0);
				System.out.println(programActivity);
				ProgramActivity res = restTemplate.postForObject(Constants.url + "/saveStudentActivity",
						programActivity, ProgramActivity.class);
				if (is_view == 1) {
					returnString = "redirect:/showStudOrgnizedActivity";
				} else {
					returnString = "redirect:/showAddStudentOrgnizedActivity";
				}

			} else {

				returnString = "redirect:/accessDenied";
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return returnString;

	}

	@RequestMapping(value = "/editStudentOrgnizedActivity/{activityId}", method = RequestMethod.GET)
	public ModelAndView editStudentOrgnizedActivity(@PathVariable("activityId") int activityId,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editStudentOrgnizedActivity", "showStudOrgnizedActivity", "0", "0",
					"1", "0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				int acYearId = (Integer) session.getAttribute("acYearId");
				model = new ModelAndView("ProgramDetails/addStudActOrganized");
				model.addObject("title", "Edit Student Activity Organized");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("activityId", activityId);
				map.add("yearId", acYearId);
				map.add("type", 0);
				ProgramActivity editProgramActivity = restTemplate
						.postForObject(Constants.url + "/getStudentAcitivityByActivityId", map, ProgramActivity.class);
				model.addObject("editProgramActivity", editProgramActivity);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteStudentOrgnizedActivity/{activityId}", method = RequestMethod.GET)
	public String deleteStudentOrgnizedActivity(@PathVariable("activityId") int activityId, HttpServletRequest request,
			HttpServletResponse response) {

		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteStudentOrgnizedActivity", "showStudOrgnizedActivity", "0",
					"0", "0", "1", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("activityId", activityId);
				Info res = restTemplate.postForObject(Constants.url + "/deleteActivity", map, Info.class);

				returnString = "redirect:/showStudOrgnizedActivity";

			} else {

				returnString = "redirect:/accessDenied";
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return returnString;

	}

	@RequestMapping(value = "/showStudAttendActivity", method = RequestMethod.GET)
	public ModelAndView showStudAttendActivity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showStudAttendActivity", "showStudAttendActivity", "1", "0", "0",
					"0", newModuleList);

			System.out.println(view);
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			if (view.isError() == false) {

				model = new ModelAndView("ProgramDetails/studActivityAttend");
				model.addObject("title", "Student Participated Activity List");

				Info add = AccessControll.checkAccess("showStudAttendActivity", "showStudAttendActivity", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showStudAttendActivity", "showStudAttendActivity", "0", "0",
						"1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showStudAttendActivity", "showStudAttendActivity", "0", "0",
						"0", "1", newModuleList);

				if (add.isError() == false) {
					model.addObject("isAdd", 1);
				}
				if (edit.isError() == false) {
					model.addObject("isEdit", 1);
				}
				if (delete.isError() == false) {
					model.addObject("isDelete", 1);
				}
				int acYearId = (Integer) session.getAttribute("acYearId");
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("yearId", acYearId);
				map.add("type", 1);
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				ProgramActivity[] programActivity = restTemplate
						.postForObject(Constants.url + "getStudentAcitivityList", map, ProgramActivity[].class);
				List<ProgramActivity> list = new ArrayList<ProgramActivity>(Arrays.asList(programActivity));
				model.addObject("list", list);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddStudentAttendActivity", method = RequestMethod.GET)
	public ModelAndView showAddStudentAttendActivity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddStudentAttendActivity", "showStudAttendActivity", "0", "1",
					"0", "0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				model = new ModelAndView("ProgramDetails/addStudActAttend");
				model.addObject("title", "Add Student Participated Activity");

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/submitAttendedActivity", method = RequestMethod.POST)
	public String submitAttendedActivity(HttpServletRequest request, HttpServletResponse response) {

		String returnString = new String();

		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("submitAttendedActivity", "showStudAttendActivity", "0", "1", "0",
					"0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				Date maleDate = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
				String stud = "NA";
				String awrd = "NA";
				String adhar = "NA";
				String activityName = request.getParameter("activityName");
				String date = request.getParameter("date");
				String year = request.getParameter("year");
				String branch = request.getParameter("branch");
				int noStudent = Integer.parseInt(request.getParameter("noStudent"));
				String level = request.getParameter("level");
				int is_view = Integer.parseInt(request.getParameter("is_view"));
				int acYearId = (Integer) session.getAttribute("acYearId");
				int activityId = Integer.parseInt(request.getParameter("activityId"));

				ProgramActivity programActivity = new ProgramActivity();
				if (activityName.equals("7")) {
					String otherActivityName = request.getParameter("otherActivityName");
					programActivity.setActivityName(otherActivityName);
				} else {
					programActivity.setActivityName(activityName);
				}

				if (activityId != 0) {

					programActivity.setStudentActivityId(activityId);
				}

				programActivity.setDate(date);
				programActivity.setYear(year);
				programActivity.setParticipatedStudent(noStudent);
				programActivity.setBranch(branch);
				programActivity.setDelStatus(1);
				programActivity.setInstituteId(userObj.getGetData().getUserInstituteId());
				programActivity.setLevel(level);
				programActivity.setAddDate(sf.format(maleDate));
				programActivity.setMakerUserId(userObj.getUserId());
				programActivity.setYearId(acYearId);
				programActivity.setType(1);
				programActivity.setIsActive(1);
				
				 stud = request.getParameter("stud_name");
				if(stud!=null) {
					programActivity.setExVar1(stud);
				}else {
					programActivity.setExVar1(stud);
				}
			
				 awrd = request.getParameter("name_award");
				if(awrd!=null) {
					programActivity.setExVar2(awrd);
				}else {
					programActivity.setExVar2(awrd);
				}
				
				 adhar = request.getParameter("adhar_no");
				if(adhar!=null) {				
				programActivity.setAadharNo(adhar);
				}else {
					programActivity.setAadharNo(adhar);
				}
				
				ProgramActivity res = restTemplate.postForObject(Constants.url + "/saveStudentActivity",
						programActivity, ProgramActivity.class);
				if (is_view == 1) {
					returnString = "redirect:/showStudAttendActivity";
				} else {
					returnString = "redirect:/showAddStudentAttendActivity";
				}

			} else {

				returnString = "redirect:/accessDenied";
				returnString = "redirect:/showAddStudentAttendActivity";
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return returnString;

	}
	@RequestMapping(value = "/editStudentAttendActivity/{activityId}", method = RequestMethod.GET)
	public ModelAndView editStudentAttendActivity(@PathVariable("activityId") int activityId,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editStudentAttendActivity", "showStudAttendActivity", "0", "0", "1",
					"0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				int acYearId = (Integer) session.getAttribute("acYearId");
				model = new ModelAndView("ProgramDetails/addStudActAttend");
				model.addObject("title", "Edit Student Participated Activity");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("activityId", activityId);
				map.add("yearId", acYearId);
				map.add("type", 1);
				ProgramActivity editProgramActivity = restTemplate
						.postForObject(Constants.url + "/getStudentAcitivityByActivityId", map, ProgramActivity.class);
				model.addObject("editProgramActivity", editProgramActivity);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteStudentAttendActivity/{activityId}", method = RequestMethod.GET)
	public String deleteStudentAttendActivity(@PathVariable("activityId") int activityId, HttpServletRequest request,
			HttpServletResponse response) {

		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteStudentAttendActivity", "showStudAttendActivity", "0", "0",
					"0", "1", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("activityId", activityId);
				Info res = restTemplate.postForObject(Constants.url + "/deleteActivity", map, Info.class);

				returnString = "redirect:/showStudAttendActivity";

			} else {

				returnString = "redirect:/accessDenied";
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return returnString;

	}

	// view
	@RequestMapping(value = "/showProgramList", method = RequestMethod.GET)
	public ModelAndView showProgramList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showProgramList", "showProgramList", "1", "0", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				model = new ModelAndView("ProgramDetails/programDetails1");
				model.addObject("title", " Program Details");

				Info add = AccessControll.checkAccess("showProgramList", "showProgramList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showProgramList", "showProgramList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showProgramList", "showProgramList", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					model.addObject("isAdd", 1);
				}
				if (edit.isError() == false) {
					model.addObject("isEdit", 1);
				}
				if (delete.isError() == false) {
					model.addObject("isDelete", 1);
				}
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				Program[] program = restTemplate.postForObject(Constants.url + "/getProgramList", map, Program[].class);
				List<Program> list = new ArrayList<Program>(Arrays.asList(program));
				model.addObject("list", list);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// add
	@RequestMapping(value = "/showAddProgram", method = RequestMethod.GET)
	public ModelAndView showAddProgram(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddProgram", "showProgramList", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				model = new ModelAndView("ProgramDetails/addProgDetail");
				model.addObject("title", " Add Program ");

				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				model.addObject("progTypeList", progTypeList);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/submitAddProgram", method = RequestMethod.POST)
	public String submitAddProgram(HttpServletRequest request, HttpServletResponse response) {

		String returnString = new String();

		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("submitAddProgram", "showProgramList", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				Date maleDate = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

				int programType = Integer.parseInt(request.getParameter("programType"));
				int monthDuration = Integer.parseInt(request.getParameter("monthDuration"));
				String date = request.getParameter("date");
				String nameOfProgram = request.getParameter("nameOfProgram");
				int intake = Integer.parseInt(request.getParameter("intake"));
				String approvedBy = request.getParameter("approvedBy");
				int is_view = Integer.parseInt(request.getParameter("is_view"));
				int programId = Integer.parseInt(request.getParameter("programId"));

				Program program = new Program();

				if (approvedBy.equals("7")) {
					String otherApprovedBy = request.getParameter("otherApprovedBy");
					program.setApprovedBy(otherApprovedBy);
				} else {
					program.setApprovedBy(approvedBy);
				}

				if (programId != 0) {

					program.setProgramId(programId);
				}

				program.setNameOfProgram(nameOfProgram);
				program.setDateOfIntroduction(date);
				program.setSanctionalIntake(intake);
				program.setProgramType(programType);
				program.setMonthDuration(monthDuration);
				program.setDelStatus(1);
				program.setInstituteId(userObj.getGetData().getUserInstituteId());
				program.setMakerdatetime(sf.format(maleDate));
				program.setMakerUserId(userObj.getUserId());
				program.setIsActive(1);
				program.setExInt1(Integer.parseInt(request.getParameter("programCode")));

				Program res = restTemplate.postForObject(Constants.url + "/saveProgram", program, Program.class);

				if (is_view == 1) {
					returnString = "redirect:/showProgramList";
				} else {
					returnString = "redirect:/showAddProgram";
				}
			} else {

				returnString = "redirect:/accessDenied";
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return returnString;

	}

	@RequestMapping(value = "/editProgram/{programId}", method = RequestMethod.GET)
	public ModelAndView editProgram(@PathVariable("programId") int programId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editProgram", "showProgramList", "0", "0", "1", "0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				int acYearId = (Integer) session.getAttribute("acYearId");
				model = new ModelAndView("ProgramDetails/addProgDetail");
				model.addObject("title", " Edit Program ");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("programId", programId);
				Program editProgram = restTemplate.postForObject(Constants.url + "/getProgramByProgramId", map,
						Program.class);
				model.addObject("editProgram", editProgram);

				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				model.addObject("progTypeList", progTypeList);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteProgram/{programId}", method = RequestMethod.GET)
	public String deleteProgram(@PathVariable("programId") int programId, HttpServletRequest request,
			HttpServletResponse response) {

		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteProgram", "showProgramList", "0", "0", "0", "1",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("programId", programId);
				Info res = restTemplate.postForObject(Constants.url + "/deleteProgram", map, Info.class);

				returnString = "redirect:/showProgramList";

			} else {

				returnString = "redirect:/accessDenied";
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return returnString;

	}

	@RequestMapping(value = "/addProgramDetail/{programId}", method = RequestMethod.GET)
	public ModelAndView addProgramDetail(@PathVariable("programId") int programId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/progDetailDash");
			model.addObject("title", "Add Program Details");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("programId", programId);
			Program programDetail = restTemplate.postForObject(Constants.url + "/getProgramByProgramId", map,
					Program.class);
			model.addObject("programDetail", programDetail);

			ProgramVision[] programVision = restTemplate.postForObject(Constants.url + "/getProgramVisionList", map,
					ProgramVision[].class);
			List<ProgramVision> programVisionList = new ArrayList<>(Arrays.asList(programVision));

			ProgramMission[] ProgramMission = restTemplate.postForObject(Constants.url + "/getProgramMissionList", map,
					ProgramMission[].class);
			List<ProgramMission> programMissionList = new ArrayList<>(Arrays.asList(ProgramMission));

			ProgramEducationObjective[] programEducationObjective = restTemplate.postForObject(
					Constants.url + "/getProgramEducationObjectiveList", map, ProgramEducationObjective[].class);
			List<ProgramEducationObjective> programEducationObjectiveList = new ArrayList<>(
					Arrays.asList(programEducationObjective));

			ProgramOutcome[] programOutcome = restTemplate.postForObject(Constants.url + "/getProgramOutcomeList", map,
					ProgramOutcome[].class);
			List<ProgramOutcome> programOutcomeList = new ArrayList<>(Arrays.asList(programOutcome));

			ProgramSpeceficOutcome[] programSpeceficOutcome = restTemplate.postForObject(
					Constants.url + "/getProgramSpeceficOutcomeList", map, ProgramSpeceficOutcome[].class);
			List<ProgramSpeceficOutcome> programSpeceficOutcomeList = new ArrayList<>(
					Arrays.asList(programSpeceficOutcome));

			model.addObject("programVisionList", programVisionList);
			model.addObject("programMissionList", programMissionList);
			model.addObject("programEducationObjectiveList", programEducationObjectiveList);
			model.addObject("programOutcomeList", programOutcomeList);
			model.addObject("programSpeceficOutcomeList", programSpeceficOutcomeList);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/saveProgramVission", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse saveProgramVission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			Date date = new Date();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String programVission = request.getParameter("programVission");
			String programVissionRemark = request.getParameter("programVissionRemark");
			int programId = Integer.parseInt(request.getParameter("programId"));
			int programVissionId = Integer.parseInt(request.getParameter("programVissionId"));
			ProgramVision ProgramVision = new ProgramVision();

			ProgramVision.setVisionId(programVissionId);
			ProgramVision.setDelStatus(1);
			ProgramVision.setIsActive(1);
			ProgramVision.setVisionRemark(programVissionRemark);
			ProgramVision.setVisionText(programVission);
			ProgramVision.setInstituteId(userObj.getGetData().getUserInstituteId());
			ProgramVision.setMakerUserId(userObj.getUserId());
			ProgramVision.setMakerdatetime(sf.format(date));
			ProgramVision.setProgramId(programId);

			ProgramVision res = restTemplate.postForObject(Constants.url + "/saveProgramVision", ProgramVision,
					ProgramVision.class);

			if (res == null) {
				info.setError(true);
				info.setMsg("error while saving");
				programDetailSaveResponse.setInfo(info);
			} else {
				info.setError(false);
				info.setMsg("saved");
				programDetailSaveResponse.setInfo(info);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("programId", programId);
				ProgramVision[] arry = restTemplate.postForObject(Constants.url + "/getProgramVisionList", map,
						ProgramVision[].class);
				List<ProgramVision> list = new ArrayList<>(Arrays.asList(arry));
				programDetailSaveResponse.setProgramVissionList(list);
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/deleteProgramVission", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse deleteProgramVission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			int programId = Integer.parseInt(request.getParameter("programId"));
			int visionId = Integer.parseInt(request.getParameter("visionId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("visionId", visionId);
			info = restTemplate.postForObject(Constants.url + "/deleteProgramVision", map, Info.class);
			programDetailSaveResponse.setInfo(info);

			map = new LinkedMultiValueMap<>();
			map.add("programId", programId);
			ProgramVision[] arry = restTemplate.postForObject(Constants.url + "/getProgramVisionList", map,
					ProgramVision[].class);
			List<ProgramVision> list = new ArrayList<>(Arrays.asList(arry));
			programDetailSaveResponse.setProgramVissionList(list);

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/editProgramVission", method = RequestMethod.GET)
	public @ResponseBody ProgramVision editProgramVission(HttpServletRequest request, HttpServletResponse response) {

		ProgramVision programVision = new ProgramVision();

		try {

			int visionId = Integer.parseInt(request.getParameter("visionId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("visionId", visionId);
			programVision = restTemplate.postForObject(Constants.url + "/getProgramVisionByVisionId", map,
					ProgramVision.class);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return programVision;

	}

	@RequestMapping(value = "/saveProgramMission", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse saveProgramMission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			Date date = new Date();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String programMission = request.getParameter("programMission");
			String programMissionRemark = request.getParameter("programMissionRemark");
			int programId = Integer.parseInt(request.getParameter("programId"));
			int programMissionId = Integer.parseInt(request.getParameter("programMissionId"));

			ProgramMission save = new ProgramMission();

			save.setMissionId(programMissionId);
			save.setDelStatus(1);
			save.setIsActive(1);
			save.setMissionRemark(programMissionRemark);
			save.setMissionText(programMission);
			save.setInstituteId(userObj.getGetData().getUserInstituteId());
			save.setMakerUserId(userObj.getUserId());
			save.setMakerdatetime(sf.format(date));
			save.setProgramId(programId);

			ProgramMission res = restTemplate.postForObject(Constants.url + "/saveProgramMission", save,
					ProgramMission.class);

			if (res == null) {
				info.setError(true);
				info.setMsg("error while saving");
				programDetailSaveResponse.setInfo(info);
			} else {
				info.setError(false);
				info.setMsg("saved");
				programDetailSaveResponse.setInfo(info);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("programId", programId);
				ProgramMission[] arry = restTemplate.postForObject(Constants.url + "/getProgramMissionList", map,
						ProgramMission[].class);
				List<ProgramMission> list = new ArrayList<>(Arrays.asList(arry));
				programDetailSaveResponse.setProgramMissionList(list);
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/deleteProgramMission", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse deleteProgramMission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			int programId = Integer.parseInt(request.getParameter("programId"));
			int missionId = Integer.parseInt(request.getParameter("missionId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("missionId", missionId);
			info = restTemplate.postForObject(Constants.url + "/deleteProgramMission", map, Info.class);
			programDetailSaveResponse.setInfo(info);

			map = new LinkedMultiValueMap<>();
			map.add("programId", programId);
			ProgramMission[] arry = restTemplate.postForObject(Constants.url + "/getProgramMissionList", map,
					ProgramMission[].class);
			List<ProgramMission> list = new ArrayList<>(Arrays.asList(arry));
			programDetailSaveResponse.setProgramMissionList(list);

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/editProgramMission", method = RequestMethod.GET)
	public @ResponseBody ProgramMission editProgramMission(HttpServletRequest request, HttpServletResponse response) {

		ProgramMission programMission = new ProgramMission();

		try {

			int missionId = Integer.parseInt(request.getParameter("missionId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("missionId", missionId);
			programMission = restTemplate.postForObject(Constants.url + "/getProgramMissionByMissionId", map,
					ProgramMission.class);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return programMission;

	}

	@RequestMapping(value = "/saveProgramPeo", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse saveProgramPeo(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			Date date = new Date();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String peoText = request.getParameter("peoText");
			String peoRemark = request.getParameter("peoRemark");
			int programId = Integer.parseInt(request.getParameter("programId"));
			int programPeoId = Integer.parseInt(request.getParameter("programPeoId"));

			ProgramEducationObjective programEducationObjective = new ProgramEducationObjective();

			programEducationObjective.setPeoId(programPeoId);
			programEducationObjective.setDelStatus(1);
			programEducationObjective.setIsActive(1);
			programEducationObjective.setPeoRemark(peoRemark);
			programEducationObjective.setPeoText(peoText);
			programEducationObjective.setInstituteId(userObj.getGetData().getUserInstituteId());
			programEducationObjective.setMakerUserId(userObj.getUserId());
			programEducationObjective.setMakerdatetime(sf.format(date));
			programEducationObjective.setProgramId(programId);

			ProgramEducationObjective res = restTemplate.postForObject(Constants.url + "/saveProgramEducationObjective",
					programEducationObjective, ProgramEducationObjective.class);

			if (res == null) {
				info.setError(true);
				info.setMsg("error while saving");
				programDetailSaveResponse.setInfo(info);
			} else {
				info.setError(false);
				info.setMsg("saved");
				programDetailSaveResponse.setInfo(info);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("programId", programId);
				ProgramEducationObjective[] arry = restTemplate.postForObject(
						Constants.url + "/getProgramEducationObjectiveList", map, ProgramEducationObjective[].class);
				List<ProgramEducationObjective> list = new ArrayList<>(Arrays.asList(arry));
				programDetailSaveResponse.setProgramEducationObjectiveList(list);
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/deleteProgramPeo", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse deleteProgramPeo(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			int programId = Integer.parseInt(request.getParameter("programId"));
			int peoId = Integer.parseInt(request.getParameter("peoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("peoId", peoId);
			info = restTemplate.postForObject(Constants.url + "/deleteProgramEducationObjective", map, Info.class);
			programDetailSaveResponse.setInfo(info);

			map = new LinkedMultiValueMap<>();
			map.add("programId", programId);
			ProgramEducationObjective[] arry = restTemplate.postForObject(
					Constants.url + "/getProgramEducationObjectiveList", map, ProgramEducationObjective[].class);
			List<ProgramEducationObjective> list = new ArrayList<>(Arrays.asList(arry));
			programDetailSaveResponse.setProgramEducationObjectiveList(list);

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/editProgramPeo", method = RequestMethod.GET)
	public @ResponseBody ProgramEducationObjective editProgramPeo(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramEducationObjective programEducationObjective = new ProgramEducationObjective();

		try {

			int peoId = Integer.parseInt(request.getParameter("peoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("peoId", peoId);
			programEducationObjective = restTemplate.postForObject(
					Constants.url + "/getProgramEducationObjectiveByPeoId", map, ProgramEducationObjective.class);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return programEducationObjective;

	}

	@RequestMapping(value = "/saveProgramPo", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse saveProgramPo(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			Date date = new Date();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String poText = request.getParameter("poText");
			String poRemark = request.getParameter("poRemark");
			int programId = Integer.parseInt(request.getParameter("programId"));
			int poId = Integer.parseInt(request.getParameter("poId"));
			ProgramOutcome programOutcome = new ProgramOutcome();

			programOutcome.setPoId(poId);
			programOutcome.setDelStatus(1);
			programOutcome.setIsActive(1);
			programOutcome.setPoRemark(poRemark);
			programOutcome.setPoText(poText);
			programOutcome.setInstituteId(userObj.getGetData().getUserInstituteId());
			programOutcome.setMakerUserId(userObj.getUserId());
			programOutcome.setMakerdatetime(sf.format(date));
			programOutcome.setProgramId(programId);

			ProgramOutcome res = restTemplate.postForObject(Constants.url + "/saveProgramOutcome", programOutcome,
					ProgramOutcome.class);

			if (res == null) {
				info.setError(true);
				info.setMsg("error while saving");
				programDetailSaveResponse.setInfo(info);
			} else {
				info.setError(false);
				info.setMsg("saved");
				programDetailSaveResponse.setInfo(info);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("programId", programId);
				ProgramOutcome[] arry = restTemplate.postForObject(Constants.url + "/getProgramOutcomeList", map,
						ProgramOutcome[].class);
				List<ProgramOutcome> list = new ArrayList<>(Arrays.asList(arry));
				programDetailSaveResponse.setProgramOutcomeList(list);
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/deleteProgramPo", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse deleteProgramPo(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			int programId = Integer.parseInt(request.getParameter("programId"));
			int poId = Integer.parseInt(request.getParameter("poId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("poId", poId);
			info = restTemplate.postForObject(Constants.url + "/deleteProgramOutcome", map, Info.class);
			programDetailSaveResponse.setInfo(info);

			map = new LinkedMultiValueMap<>();
			map.add("programId", programId);
			ProgramOutcome[] arry = restTemplate.postForObject(Constants.url + "/getProgramOutcomeList", map,
					ProgramOutcome[].class);
			List<ProgramOutcome> list = new ArrayList<>(Arrays.asList(arry));
			programDetailSaveResponse.setProgramOutcomeList(list);

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/editProgramPo", method = RequestMethod.GET)
	public @ResponseBody ProgramOutcome editProgramPo(HttpServletRequest request, HttpServletResponse response) {

		ProgramOutcome programOutcome = new ProgramOutcome();

		try {

			int poId = Integer.parseInt(request.getParameter("poId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("poId", poId);
			programOutcome = restTemplate.postForObject(Constants.url + "/getProgramOutcomeByPoId", map,
					ProgramOutcome.class);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return programOutcome;

	}

	@RequestMapping(value = "/saveProgramPso", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse saveProgramPso(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			Date date = new Date();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String psoText = request.getParameter("psoText");
			String psoRemark = request.getParameter("psoRemark");
			int programId = Integer.parseInt(request.getParameter("programId"));
			int psoId = Integer.parseInt(request.getParameter("psoId"));

			ProgramSpeceficOutcome programSpeceficOutcome = new ProgramSpeceficOutcome();

			programSpeceficOutcome.setPsoId(psoId);
			programSpeceficOutcome.setDelStatus(1);
			programSpeceficOutcome.setIsActive(1);
			programSpeceficOutcome.setPsoRemark(psoRemark);
			programSpeceficOutcome.setPsoText(psoText);
			programSpeceficOutcome.setInstituteId(userObj.getGetData().getUserInstituteId());
			programSpeceficOutcome.setMakerUserId(userObj.getUserId());
			programSpeceficOutcome.setMakerdatetime(sf.format(date));
			programSpeceficOutcome.setProgramId(programId);

			ProgramSpeceficOutcome res = restTemplate.postForObject(Constants.url + "/saveProgramSpeceficOutcome",
					programSpeceficOutcome, ProgramSpeceficOutcome.class);

			if (res == null) {
				info.setError(true);
				info.setMsg("error while saving");
				programDetailSaveResponse.setInfo(info);
			} else {
				info.setError(false);
				info.setMsg("saved");
				programDetailSaveResponse.setInfo(info);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("programId", programId);
				ProgramSpeceficOutcome[] arry = restTemplate.postForObject(
						Constants.url + "/getProgramSpeceficOutcomeList", map, ProgramSpeceficOutcome[].class);
				List<ProgramSpeceficOutcome> list = new ArrayList<>(Arrays.asList(arry));
				programDetailSaveResponse.setProgramSpeceficOutcomeList(list);
			}

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/deleteProgramPso", method = RequestMethod.GET)
	public @ResponseBody ProgramDetailSaveResponse deleteProgramPso(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramDetailSaveResponse programDetailSaveResponse = new ProgramDetailSaveResponse();
		Info info = new Info();
		try {

			int programId = Integer.parseInt(request.getParameter("programId"));
			int psoId = Integer.parseInt(request.getParameter("psoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("psoId", psoId);
			info = restTemplate.postForObject(Constants.url + "/deleteProgramSpeceficOutcome", map, Info.class);
			programDetailSaveResponse.setInfo(info);

			map = new LinkedMultiValueMap<>();
			map.add("programId", programId);
			ProgramSpeceficOutcome[] arry = restTemplate.postForObject(Constants.url + "/getProgramSpeceficOutcomeList",
					map, ProgramSpeceficOutcome[].class);
			List<ProgramSpeceficOutcome> list = new ArrayList<>(Arrays.asList(arry));
			programDetailSaveResponse.setProgramSpeceficOutcomeList(list);

		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");
			programDetailSaveResponse.setInfo(info);

		}

		return programDetailSaveResponse;

	}

	@RequestMapping(value = "/editProgramPso", method = RequestMethod.GET)
	public @ResponseBody ProgramSpeceficOutcome editProgramPso(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramSpeceficOutcome programSpeceficOutcome = new ProgramSpeceficOutcome();

		try {

			int psoId = Integer.parseInt(request.getParameter("psoId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("psoId", psoId);
			programSpeceficOutcome = restTemplate.postForObject(Constants.url + "/getProgramSpeceficOutcomeByPsoId",
					map, ProgramSpeceficOutcome.class);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return programSpeceficOutcome;

	}
	
	
	/*********************************Student Performance in final year********************************/

	@RequestMapping(value = "/getProgramTypeByProgramId", method = RequestMethod.GET)
	public @ResponseBody List<Program> getProgramTypeByProgram(HttpServletRequest request,
			HttpServletResponse response) {

		List<Program> list = new ArrayList<>();

		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int programType = Integer.parseInt(request.getParameter("programType"));
			map.add("programTypeId", programType);
			map.add("instituteId", userObj.getGetData().getUserInstituteId());

			Program[] program = restTemplate.postForObject(Constants.url + "/getProgramByProgramTypeId", map,
					Program[].class);
			list = new ArrayList<Program>(Arrays.asList(program));

		} catch (Exception e) {
			System.err.println("Exce in getProgramTypeByProgram  " + e.getMessage());
			e.printStackTrace();
		}

		return list;

	}
	
	@RequestMapping(value = "/showStudPerformInFinlYr", method = RequestMethod.GET)
	public ModelAndView showStudPerformInFinlYr(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showStudPerformInFinlYr", "showStudPerformInFinlYr", "1", "0", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				model = new ModelAndView("ProgramDetails/studentPerformanceList");
				model.addObject("title", "Student Performance in Final Year");
				
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				Program[] program = restTemplate.postForObject(Constants.url + "/getProgramList", map, Program[].class);
				List<Program> list = new ArrayList<Program>(Arrays.asList(program));
				model.addObject("list", list);
				
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				StudPerformFinalYrList[] passedStudArr = restTemplate.postForObject(Constants.url + "getstudPassingPer",map,
						StudPerformFinalYrList[].class);
				List<StudPerformFinalYrList> passedStudList = new ArrayList<>(Arrays.asList(passedStudArr));
				
				model.addObject("passedStudList", passedStudList);

				Info add = AccessControll.checkAccess("showStudPerformInFinlYr", "showStudPerformInFinlYr", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showStudPerformInFinlYr", "showStudPerformInFinlYr", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showStudPerformInFinlYr", "showStudPerformInFinlYr", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					model.addObject("isAdd", 1);
				}
				if (edit.isError() == false) {
					model.addObject("isEdit", 1);
				}
				if (delete.isError() == false) {
					model.addObject("isDelete", 1);
				}
				
				
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showStudPerformInFinlYr at StudAct Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	
	@RequestMapping(value = "/addStudPerfromancInFinalYear", method = RequestMethod.GET)
	public ModelAndView addStudPerfromancInFinalYear(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("addStudPerfromancInFinalYear", "showStudPerformInFinlYr", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				model = new ModelAndView("ProgramDetails/addstudPerform");
				model.addObject("title", " Add Student Performance in Final Year ");
				StudPerformFinalYr studPer = new StudPerformFinalYr();

				
				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				model.addObject("progTypeList", progTypeList);
				model.addObject("studPer", studPer);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In addStudPerfromancInFinalYear at Student Activity" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	@RequestMapping(value = "/insertStudPerformInFinalYr", method = RequestMethod.POST)
	public String insertStudPerformInFinalYr(HttpServletRequest request, HttpServletResponse response) {
		
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
		
			StudPerformFinalYr studPer = new StudPerformFinalYr();
			int appear = Integer.parseInt(request.getParameter("stud_appeared"));
			int passed = Integer.parseInt(request.getParameter("stud_passed"));
			double passingper = Double.parseDouble(request.getParameter("stud_pass_per"));
			studPer.setStudPerformId(Integer.parseInt(request.getParameter("stud_perform_id")));
			studPer.setProgName(Integer.parseInt(request.getParameter("programType")));
			studPer.setProgType(Integer.parseInt(request.getParameter("programTypeId")));
			studPer.setNoStudAppear(appear);
			studPer.setNoStudPass(passed);
			studPer.setPassingPer(passingper);
			studPer.setDelStatus(1);
			studPer.setIsActive(1);
			studPer.setInstId(userObj.getGetData().getUserInstituteId());
			studPer.setMakerUserId(userObj.getUserId());
			studPer.setMakingTime(sf.format(date));
			studPer.setExInt1(0);
			studPer.setExInt2(0);
			studPer.setExVar1("NA");
			studPer.setExVar2("NA");
			System.out.println(studPer.toString());
			
			StudPerformFinalYr saveStudPerform = restTemplate.postForObject(Constants.url+"/addStudPerformFinalYear", studPer, StudPerformFinalYr.class);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		
		return "redirect:/showStudPerformInFinlYr";
		
	}

	@RequestMapping(value = "/editStudPerform/{studperId}", method = RequestMethod.GET)
	public ModelAndView editStudPerform(@PathVariable("studperId") int studperId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editStudPerform/{studperId}", "showStudPerformInFinlYr", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");
			}

			else {
				model = new ModelAndView("ProgramDetails/addstudPerform");
				model.addObject("title", " Edit Student Performance in Final Year ");
				
				map.add("studperId", studperId);
				StudPerformFinalYr studPer = restTemplate.postForObject(Constants.url + "getStudPerformanceById",map,
						StudPerformFinalYr.class);
				model.addObject("studPer", studPer);
				
				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				model.addObject("progTypeList", progTypeList);
				
				
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return model;

	}
	
	@RequestMapping(value = "/deleteStudPerform/{studperId}", method = RequestMethod.GET)
	public String deleteTExtActivity(@PathVariable("studperId") int studperId, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		try {
			String a = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteStudPerform/{studperId}", "showStudPerformInFinlYr", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("studperId", studperId);

				StudPerformFinalYr delAct = restTemplate.postForObject(Constants.url + "/deleteStudPerformanceById", map, StudPerformFinalYr.class);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return "redirect:/showStudPerformInFinlYr";

	}
	
	@RequestMapping(value = "/deleteSeldata/{studInfo}", method = RequestMethod.GET)
	public String deleteSeldata(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int studInfo) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteSelExtsnActivities/{exActId}", "showStudPerformInFinlYr", "0", "0", "0",
				"1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (studInfo == 0) {

					System.err.println("Multiple records delete ");
					String[] studInfos = request.getParameterValues("studInfo");
					System.out.println("id are" + studInfo);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < studInfos.length; i++) {
						sb = sb.append(studInfos[i] + ",");

					}
					String studInfoList = sb.toString();
					studInfoList = studInfoList.substring(0, studInfoList.length() - 1);

					map.add("studInfoList", studInfoList);
				} else {

					System.err.println("Single Record delete ");
					map.add("studInfoList", studInfo);
				}

				Info errMsg = restTemplate.postForObject(Constants.url + "deleteSelStudperformnc", map, Info.class);

				a = "redirect:/showStudPerformInFinlYr";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}
	
/*************************************Students Qualifying Exam Details*************************************/
	
	
	@RequestMapping(value = "/showStudentsQualifyingExamDetails", method = RequestMethod.GET)
	public ModelAndView showStudentsQualifyingExamDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showStudentsQualifyingExamDetails", "showStudentsQualifyingExamDetails", "1", "0", "0",
					"0", newModuleList);

			System.out.println(view);
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			if (view.isError() == false) {

				model = new ModelAndView("ProgramDetails/studQualifyExamDtl");
				model.addObject("title", "Students Qualifying Exam Details");

				Info add = AccessControll.checkAccess("showStudentsQualifyingExamDetails", "showStudentsQualifyingExamDetails", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showStudentsQualifyingExamDetails", "showStudentsQualifyingExamDetails", "0", "0",
						"1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showStudentsQualifyingExamDetails", "showStudentsQualifyingExamDetails", "0", "0",
						"0", "1", newModuleList);

				if (add.isError() == false) {
					model.addObject("isAdd", 1);
				}
				if (edit.isError() == false) {
					model.addObject("isEdit", 1);
				}
				if (delete.isError() == false) {
					model.addObject("isDelete", 1);
				}
				int acYearId = (Integer) session.getAttribute("acYearId");
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("yearId", acYearId);
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				StudQualifyingExam[] studQlifExamArr = restTemplate
						.postForObject(Constants.url + "getStudQualifiedExamList", map, StudQualifyingExam[].class);
				List<StudQualifyingExam> studQlifExamList = new ArrayList<StudQualifyingExam>(Arrays.asList(studQlifExamArr));
				model.addObject("studQlifExamList", studQlifExamList);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	@RequestMapping(value = "/addStudQualifyExmDtl", method = RequestMethod.GET)
	public ModelAndView addStudQualifyExmDtl(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("addStudQualifyExmDtl", "showStudentsQualifyingExamDetails", "0", "1",
					"0", "0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				StudQualifyingExam studQlifyExam = new StudQualifyingExam();
				model = new ModelAndView("ProgramDetails/addStudQualifyExamDetail");
				model.addObject("title", "Add Students Qualifying Exam Details");
				model.addObject("studQlifyExam", studQlifyExam);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/insertStudQualifyExamDtl", method = RequestMethod.POST)
	public String insertStudQualifyExamDtl(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());
			
			StudQualifyingExam stud = new StudQualifyingExam();
			
			stud.setStudExmId(Integer.parseInt(request.getParameter("studExmId")));
			stud.setNameQualifExam(request.getParameter("qualify_exam"));
			stud.setLevelExam(request.getParameter("exam_level"));
			stud.setNoStudAppeared(Integer.parseInt(request.getParameter("no_stud_appear")));
			stud.setNoStudQualified(Integer.parseInt(request.getParameter("no_stud_qualify")));
			stud.setInstId(instituteId);
			stud.setAcYearId(yId);
			stud.setDelStatus(1);
			stud.setIsActive(1);
			stud.setMakerUserId(userId);
			stud.setMakerEnterDatetime(curDateTime);
			stud.setExInt1(0);
			stud.setExInt2(0);
			stud.setExVar1("NA");
			stud.setExVar2("NA");
			//System.out.println(stud.toString());
			StudQualifyingExam addStudQalifyExam = restTemplate.postForObject(Constants.url+"/saveStudQualifyExam", stud, StudQualifyingExam.class);
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/showStudentsQualifyingExamDetails";
	}
	
	@RequestMapping(value = "/editStudQualifyExam/{studExmId}", method = RequestMethod.GET)
	public ModelAndView editStudQualifyExam(@PathVariable("studExmId") int studExmId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("/editStudQualifyExam/{studExmId}", "showStudentsQualifyingExamDetails", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");
			}

			else {
				
				model = new ModelAndView("ProgramDetails/addStudQualifyExamDetail");
				model.addObject("title", "Edit Students Qualifying Exam Details");
				
				map.add("studExmId", studExmId);
				StudQualifyingExam studQlifyExam = restTemplate.postForObject(Constants.url + "getStudQulifExmById",map,
						StudQualifyingExam.class);
				model.addObject("studQlifyExam", studQlifyExam);
				
				
				
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return model;

	}
	
	@RequestMapping(value = "/deleteStudQualifyExam/{studExmId}", method = RequestMethod.GET)
	public String deleteStudQualifyExam(@PathVariable("studExmId") int studExmId, HttpServletRequest request,
			HttpServletResponse response) {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		try {
			String a = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteStudQualifyExam/{studExmId}", "showStudentsQualifyingExamDetails", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("studExmId", studExmId);

				StudQualifyingExam delAct = restTemplate.postForObject(Constants.url + "/deleteStudQulifExmByById", map, StudQualifyingExam.class);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return "redirect:/showStudentsQualifyingExamDetails";

	}

	@RequestMapping(value = "/delSlectedStudQlifExmDtl/{studQlfExmId}", method = RequestMethod.GET)
	public String delSlectedStudQlifExmDtl(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int studQlfExmId) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedStudQlifExmDtl/{studQlfExmId}", "showStudentsQualifyingExamDetails", "0", "0", "0",
				"1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (studQlfExmId == 0) {

					System.err.println("Multiple records delete ");
					String[] studQlfExmIds = request.getParameterValues("studQlfExmId");
					System.out.println("id are" + studQlfExmId);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < studQlfExmIds.length; i++) {
						sb = sb.append(studQlfExmIds[i] + ",");

					}
					String studQlfExmIdList = sb.toString();
					studQlfExmIdList = studQlfExmIdList.substring(0, studQlfExmIdList.length() - 1);

					map.add("studQlfExmIdList", studQlfExmIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("studQlfExmIdList", studQlfExmId);
				}

				Info errMsg = restTemplate.postForObject(Constants.url + "deleteSelStudQulifiedExm", map, Info.class);

				a = "redirect:/showStudentsQualifyingExamDetails";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}
	
	
}
