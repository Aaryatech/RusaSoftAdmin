package com.ats.rusasoft.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.ProgramActivity;
import com.ats.rusasoft.model.ProgramDetailSaveResponse;
import com.ats.rusasoft.model.ProgramEducationObjective;
import com.ats.rusasoft.model.ProgramOutcome;
import com.ats.rusasoft.model.ProgramVision;
import com.ats.rusasoft.model.Quolification;
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
				model.addObject("title", "Student Activity List (Organized)");
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
				model.addObject("title", "Student Activity Organized");

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

				programActivity.setDate(DateConvertor.convertToYMD(date));
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
				model.addObject("title", "Student Activity Attended");

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
				model.addObject("title", "Student Activity Attended");

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

				programActivity.setDate(DateConvertor.convertToYMD(date));
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
				model = new ModelAndView("ProgramDetails/addStudActOrganized");
				model.addObject("title", "Edit Student Activity Organized");
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
				model.addObject("title", " Program List");

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
				program.setDateOfIntroduction(DateConvertor.convertToYMD(date));
				program.setSanctionalIntake(intake);
				program.setProgramType(programType);
				program.setMonthDuration(monthDuration);
				program.setDelStatus(1);
				program.setInstituteId(userObj.getGetData().getUserInstituteId());
				program.setMakerdatetime(sf.format(maleDate));
				program.setMakerUserId(userObj.getUserId());
				program.setIsActive(1);

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
			
			ProgramEducationObjective[] programEducationObjective = restTemplate.postForObject(Constants.url + "/getProgramEducationObjectiveList", map,
					ProgramEducationObjective[].class);
			List<ProgramEducationObjective> programEducationObjectiveList = new ArrayList<>(Arrays.asList(programEducationObjective));
			
			ProgramOutcome[] programOutcome = restTemplate.postForObject(Constants.url + "/getProgramOutcomeList", map,
					ProgramOutcome[].class);
			List<ProgramOutcome> programOutcomeList = new ArrayList<>(Arrays.asList(programOutcome)); 
			
			ProgramSpeceficOutcome[] programSpeceficOutcome = restTemplate.postForObject(Constants.url + "/getProgramSpeceficOutcomeList", map,
					ProgramSpeceficOutcome[].class);
			List<ProgramSpeceficOutcome> programSpeceficOutcomeList = new ArrayList<>(Arrays.asList(programSpeceficOutcome));
			
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
				info  = restTemplate.postForObject(Constants.url + "/deleteProgramVision", map,
						Info.class);
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
	public @ResponseBody ProgramVision editProgramVission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramVision programVision = new ProgramVision();
	 
		try {

			  
			int visionId = Integer.parseInt(request.getParameter("visionId"));
 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
				map.add("visionId", visionId);
				programVision  = restTemplate.postForObject(Constants.url + "/getProgramVisionByVisionId", map,
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
				info  = restTemplate.postForObject(Constants.url + "/deleteProgramMission", map,
						Info.class);
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
	public @ResponseBody ProgramMission editProgramMission(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramMission programMission = new ProgramMission();
	 
		try {

			  
			int missionId = Integer.parseInt(request.getParameter("missionId"));
 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
				map.add("missionId", missionId);
				programMission  = restTemplate.postForObject(Constants.url + "/getProgramMissionByMissionId", map,
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

			ProgramEducationObjective res = restTemplate.postForObject(Constants.url + "/saveProgramEducationObjective", programEducationObjective,
					ProgramEducationObjective.class);

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
				ProgramEducationObjective[] arry = restTemplate.postForObject(Constants.url + "/getProgramEducationObjectiveList", map,
						ProgramEducationObjective[].class);
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
				info  = restTemplate.postForObject(Constants.url + "/deleteProgramEducationObjective", map,
						Info.class);
				programDetailSaveResponse.setInfo(info);
				
				
				map = new LinkedMultiValueMap<>();
				map.add("programId", programId);
				ProgramEducationObjective[] arry = restTemplate.postForObject(Constants.url + "/getProgramEducationObjectiveList", map,
						ProgramEducationObjective[].class);
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
				programEducationObjective  = restTemplate.postForObject(Constants.url + "/getProgramEducationObjectiveByPeoId", map,
						ProgramEducationObjective.class);
				 

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
				info  = restTemplate.postForObject(Constants.url + "/deleteProgramOutcome", map,
						Info.class);
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
	public @ResponseBody ProgramOutcome editProgramPo(HttpServletRequest request,
			HttpServletResponse response) {

		ProgramOutcome programOutcome = new ProgramOutcome();
	 
		try {

			  
			int poId = Integer.parseInt(request.getParameter("poId"));
 
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>(); 
				map.add("poId", poId);
				programOutcome  = restTemplate.postForObject(Constants.url + "/getProgramOutcomeByPoId", map,
						ProgramOutcome.class);
				 

		} catch (Exception e) {

			e.printStackTrace();
			 

		}

		return programOutcome;

	}
	
}
