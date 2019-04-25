package com.ats.rusasoft.controller;

import java.text.DateFormat;

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
import com.ats.rusasoft.faculty.model.GetJournal;
import com.ats.rusasoft.faculty.model.GetSWOC;
import com.ats.rusasoft.faculty.model.GetSubject;
import com.ats.rusasoft.faculty.model.Journal;
import com.ats.rusasoft.faculty.model.ResearchProject;
import com.ats.rusasoft.faculty.model.SWOC;
import com.ats.rusasoft.faculty.model.Subject;
import com.ats.rusasoft.master.model.Program;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.IqacList;
import com.ats.rusasoft.model.LinkageMaster;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.MIqac;
import com.ats.rusasoft.model.ProgramOutcome;
import com.ats.rusasoft.model.ProgramSpeceficOutcome;
import com.ats.rusasoft.model.SubjectCo;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class FacultyController {
	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/showJournalPub", method = RequestMethod.GET)
	public ModelAndView showJournalPub(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showJournalPub", "showJournalPubList", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("FacultyDetails/journalPub");

				model.addObject("title", "Add Faculty's Published Journal Details");

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertJournal", method = RequestMethod.POST)
	public String insertJournal(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showJournalPub", "showJournalPubList", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				System.err.println("Inside insertJournal method");

				int journalId = 0;
				try {
					journalId = Integer.parseInt(request.getParameter("journalId"));
				} catch (Exception e) {
					journalId = 0;
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();
				String curDate = dateFormat.format(new Date());
				String dateTime = dateFormat.format(now);

				String journalName = request.getParameter("journalName");
				String issue = request.getParameter("issue");
				String journalVolume = request.getParameter("volume");
				String journalPgFrom = request.getParameter("journalPgFrom");
				String journalPgTo = request.getParameter("journalPgTo");
				String journalYear = request.getParameter("journalYear");
				String journalType = request.getParameter("journalType");

				System.out.println(journalName);
				System.out.println(issue);
				System.out.println(journalVolume);
				System.out.println(journalPgFrom);
				System.out.println(journalPgTo);
				System.out.println(journalYear);
				System.out.println(journalType);
				int is_view = Integer.parseInt(request.getParameter("is_view"));
				int jouStd = Integer.parseInt(request.getParameter("jouStd"));

				Journal journal = new Journal();
				journal.setDelStatus(1);
				journal.setExInt1(1);
				journal.setExInt2(1);
				journal.setExVar1("NA");
				journal.setExVar2("NA");
				journal.setFacultyId(userObj.getGetData().getUserDetailId());
				int yearId = (int) session.getAttribute("acYearId");
				journal.setYearId(yearId);
				journal.setIsActive(1);
				journal.setJournalId(journalId);
				journal.setJournalIssue(issue);
				journal.setJournalName(journalName);
				journal.setJournalPgFrom(journalPgFrom);
				journal.setJournalPgTo(journalPgTo);
				journal.setJournalVolume(journalVolume);
				journal.setJournalYear(journalYear);
				journal.setMakerUserId(userObj.getUserId());
				journal.setJournalStandard(jouStd);
				journal.setJournalType(journalType);
				journal.setMakerEnterDatetime(dateTime);

				Journal journalInsertRes = rest.postForObject(Constants.url + "saveJournal", journal, Journal.class);

				System.err.println("journalInsertRes " + journalInsertRes.toString());

				if (is_view == 1) {
					returnString = "redirect:/showJournalPubList";
				} else {
					returnString = "redirect:/showJournalPub";
				}
			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in vendInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/showJournalPubList", method = RequestMethod.GET)
	public ModelAndView showJournalPubList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showJournalPubList", "showJournalPubList", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/journalPubList");

				model.addObject("title", "Faculty's Published Journal Details List");
				int yId = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());

				List<GetJournal> jouList = rest.postForObject(Constants.url + "/getJournalListByFacultyIdAndtype", map,
						List.class);

				System.out.println("jouList" + jouList);

				model.addObject("jouList", jouList);

				Info add = AccessControll.checkAccess("showJournalPubList", "showJournalPubList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showJournalPubList", "showJournalPubList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showJournalPubList", "showJournalPubList", "0", "0", "0", "1",
						newModuleList);
				System.err.println("Addd" + add);

				if (add.isError() == false) {
					System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}

		} catch (Exception e) {

			System.err.println("exception In showJournalPubList at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/editJournal/{journalId}", method = RequestMethod.GET)
	public ModelAndView editIqac(@PathVariable("journalId") int journalId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showJournalPub", "showJournalPubList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/journalPub");
				model.addObject("title", "Edit Faculty's Published Journal Details");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("journalId", journalId);

				Journal editJournal = rest.postForObject(Constants.url + "/getJournalByJournalId", map, Journal.class);
				System.out.println("journalId:" + journalId);

				model.addObject("editJournal", editJournal);

				model.addObject("date", DateConvertor.convertToDMY(editJournal.getJournalYear()));
			}
		} catch (Exception e) {

			System.err.println("exception In editJournal at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteJournal/{journalId}", method = RequestMethod.GET)
	public String deleteIqac(@PathVariable("journalId") int journalId, HttpServletRequest request) {
		String value = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showJournalPub", "showJournalPubList", "0", "0", "0", "1",
				newModuleList);
		if (view.isError() == true) {

			value = "redirect:/accessDenied";

		} else {
			Info inf = new Info();
			System.out.println("Id:" + journalId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("jouIdList", journalId);
			Info miqc = rest.postForObject(Constants.url + "/deleteJournals", map, Info.class);
			value = "redirect:/showJournalPubList";
		}
		return value;

	}

	@RequestMapping(value = "/showResearchDetails", method = RequestMethod.GET)
	public ModelAndView showResearchDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showResearchDetails", "showResearchDetailsList", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("FacultyDetails/researchProDetail");

				model.addObject("title", "Add Faculty's Research Project Details");

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertResearchProject", method = RequestMethod.POST)
	public String insertResearchProject(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();

		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showResearchDetails", "showResearchDetailsList", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				System.err.println("Inside insertResearchProject method");

				int projId = 0;
				try {
					projId = Integer.parseInt(request.getParameter("projId"));
				} catch (Exception e) {
					projId = 0;
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();
				// String curDate = dateFormat.format(new Date());
				String dateTime = dateFormat.format(now);

				String deptName = request.getParameter("deptName");
				String PIName = request.getParameter("PIName");
				String spoAuth = request.getParameter("spoAuth");
				String yearOfPS = request.getParameter("yearOfPS");
				String projName = request.getParameter("projName");
				String coPrincipalName = request.getParameter("coPrincipal");
				String deptCoName = request.getParameter("deptCoName");
				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");

				String grant = request.getParameter("grant");

				// int grant = Integer.parseInt(request.getParameter("grant"));

				float totalAmt = Float.parseFloat(request.getParameter("totalAmt"));
				float amtRec = Float.parseFloat(request.getParameter("amtRec"));
				int is_view = Integer.parseInt(request.getParameter("is_view"));

				System.out.println("----------" + is_view);
				ResearchProject project = new ResearchProject();
				project.setDelStatus(1);
				project.setExInt1(1);
				project.setExInt2(1);
				project.setExVar1("NA");
				project.setExVar2("NA");
				project.setFacultyId(userObj.getGetData().getUserDetailId());
				int yearId = (int) session.getAttribute("acYearId");
				project.setYearId(yearId);
				project.setIsActive(1);
				project.setMakerEnterDatetime(dateTime);
				project.setMakerUserId(userObj.getUserId());

				project.setProjFrdt(DateConvertor.convertToYMD(fromDate));
				project.setProjGrant(grant);
				project.setProjId(projId);

				project.setProjInvDept(deptName);
				project.setProjInvDept2(deptCoName);
				project.setProjTodt(DateConvertor.convertToYMD(toDate));
				project.setProjYear(yearOfPS);
				project.setProjInvName2(coPrincipalName);
				project.setProjSponsor(spoAuth);
				project.setProjTotalAmt(totalAmt);
				project.setProjAmtRec(amtRec);
				project.setProjName(projName);
				project.setProjInvName(PIName);

				ResearchProject researchInsertRes = rest.postForObject(Constants.url + "saveReaserchProject", project,
						ResearchProject.class);

				System.err.println("researchInsertRes " + researchInsertRes.toString());

				if (is_view == 1) {
					returnString = "redirect:/showResearchDetailsList";
				} else {
					returnString = "redirect:/showResearchDetails";
				}
			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (

		Exception e) {
			System.err.println("EXCE in vendInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/showResearchDetailsList", method = RequestMethod.GET)
	public ModelAndView showResearchDetailsList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showResearchDetailsList", "showResearchDetailsList", "1", "0", "0",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/researchProjectList");

				model.addObject("title", "Faculty's Research Project Details List");
				int yId = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());

				List<GetResearchProject> jouList = rest
						.postForObject(Constants.url + "/getProjectListByFacultyIdAndtype", map, List.class);

				System.out.println("jouList" + jouList);

				model.addObject("jouList", jouList);

				Info add = AccessControll.checkAccess("showResearchDetailsList", "showResearchDetailsList", "0", "1",
						"0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showResearchDetailsList", "showResearchDetailsList", "0", "0",
						"1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showResearchDetailsList", "showResearchDetailsList", "0", "0",
						"0", "1", newModuleList);

				if (add.isError() == false) {
					System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}

		} catch (Exception e) {

			System.err.println("exception In showResearchDetailsList at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/editProject/{projId}", method = RequestMethod.GET)
	public ModelAndView editProject(@PathVariable("projId") int projId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showResearchDetails", "showResearchDetailsList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/researchProDetail");
				model.addObject("title", "Edit Faculty's Research Project Details");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("projectId", projId);

				ResearchProject editProject = rest.postForObject(Constants.url + "/getProjectByProjId", map,
						ResearchProject.class);
				System.out.println("projId:" + projId);

				model.addObject("editProject", editProject);

			}
		} catch (Exception e) {

			System.err.println("exception In editJournal at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteProject/{projectId}", method = RequestMethod.GET)
	public String deleteProject(@PathVariable("projectId") int projectId, HttpServletRequest request) {
		String value = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showResearchDetails", "showResearchDetailsList", "0", "0", "0", "1",
				newModuleList);
		if (view.isError() == true) {

			value = "redirect:/accessDenied";

		} else {
			Info inf = new Info();
			System.out.println("Id:" + projectId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("projIdList", projectId);
			Info miqc = rest.postForObject(Constants.url + "/deleteResearchDetails", map, Info.class);
			value = "redirect:/showResearchDetailsList";
		}
		return value;

	}

	@RequestMapping(value = "/showSubDetailsList", method = RequestMethod.GET)
	public ModelAndView showSubDetailsList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showSubDetailsList", "showSubDetailsList", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/subDetails");
				model.addObject("title", "Faculty's Teaching Subject Details List");

				int yId = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				List<GetSubject> subList = rest.postForObject(Constants.url + "/getSubjectListByFacultyIdAndtype", map,
						List.class);

				System.out.println("subList" + subList);

				model.addObject("subList", subList);

				Info add = AccessControll.checkAccess("showSubDetailsList", "showSubDetailsList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showSubDetailsList", "showSubDetailsList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showSubDetailsList", "showSubDetailsList", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}

		} catch (Exception e) {

			System.err.println("exception In showSubDetailsList at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/showAddSubDetails", method = RequestMethod.GET)
	public ModelAndView showAddSubDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showAddSubDetails", "showSubDetailsList", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("instId", userObj.getStaff().getInstituteId());

				Program[] program = rest.postForObject(Constants.url + "/getAllProgramList", map, Program[].class);
				List<Program> proList = new ArrayList<Program>(Arrays.asList(program));

				model = new ModelAndView("FacultyDetails/addSubDetail");

				model.addObject("title", "Add Faculty's Teaching Subject Details");
				model.addObject("proList", proList);

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAddSubDetails at faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertSubject", method = RequestMethod.POST)
	public String insertSubject(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddSubDetails", "showSubDetailsList", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				System.err.println("Inside insertSubject method");

				int subId = 0;
				try {
					subId = Integer.parseInt(request.getParameter("subId"));
				} catch (Exception e) {
					subId = 0;
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();
				String curDate = dateFormat.format(new Date());
				String dateTime = dateFormat.format(now);

				String subCode = request.getParameter("subCode");
				String sem = request.getParameter("sem");
				String subName = request.getParameter("subName");
				String subType = request.getParameter("subType");

				int is_view = Integer.parseInt(request.getParameter("is_view"));
				int programId = Integer.parseInt(request.getParameter("programId"));
				int isCbse = Integer.parseInt(request.getParameter("isCbse"));
				int noStudApp = Integer.parseInt(request.getParameter("noStudApp"));
				int pass = Integer.parseInt(request.getParameter("pass"));
				float rslt = Float.parseFloat(request.getParameter("rslt"));

				// pass

				Subject sub = new Subject();
				sub.setDelStatus(1);
				sub.setExInt1(1);
				sub.setExInt2(1);
				sub.setExVar1("NA");
				sub.setExVar2("NA");
				sub.setFacultyId(userObj.getGetData().getUserDetailId());
				int yearId = (int) session.getAttribute("acYearId");
				sub.setYearId(yearId);
				sub.setIsActive(1);
				sub.setSubId(subId);
				sub.setMakerEnterDatetime(dateTime);
				sub.setMakerUserId(userObj.getUserId());
				sub.setProgId(programId);
				sub.setSubCode(subCode);
				sub.setSubIsCbse(isCbse);
				sub.setSubName(subName);
				sub.setSubPassPer(rslt);
				sub.setSubSem(sem);
				sub.setSubStuAppear(noStudApp);
				sub.setSubStuPassed(pass);
				sub.setSubType(subType);

				Subject subInsertRes = rest.postForObject(Constants.url + "saveSubject", sub, Subject.class);

				System.err.println("subInsertRes " + subInsertRes.toString());

				if (is_view == 1) {
					returnString = "redirect:/showSubDetailsList";
				} else {
					returnString = "redirect:/showAddSubDetails";
				}
			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in vendInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/editSubject/{subId}", method = RequestMethod.GET)
	public ModelAndView editSubject(@PathVariable("subId") int subId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showAddSubDetails", "showSubDetailsList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("instId", userObj.getStaff().getInstituteId());

				Program[] program = rest.postForObject(Constants.url + "/getAllProgramList", map, Program[].class);
				List<Program> proList = new ArrayList<Program>(Arrays.asList(program));

				model = new ModelAndView("FacultyDetails/addSubDetail");

				model.addObject("title", "Edit Faculty's Teaching Subject Details");
				model.addObject("proList", proList);
				map = new LinkedMultiValueMap<>();
				map.add("subId", subId);

				Subject editSubject = rest.postForObject(Constants.url + "/getSubjectBySubId", map, Subject.class);
				System.out.println("subId:" + subId);

				model.addObject("editSubject", editSubject);

			}
		} catch (Exception e) {

			System.err.println("exception In editSubject at faculty Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteSubject/{subId}", method = RequestMethod.GET)
	public String deleteSubject(@PathVariable("subId") int subId, HttpServletRequest request) {
		String value = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showAddSubDetails", "showSubDetailsList", "0", "0", "0", "1",
				newModuleList);
		if (view.isError() == true) {

			value = "redirect:/accessDenied";

		} else {
			Info inf = new Info();
			System.out.println("Id:" + subId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("subIdList", subId);
			Info miqc = rest.postForObject(Constants.url + "/deleteSubjects", map, Info.class);
			value = "redirect:/showSubDetailsList";
		}
		return value;

	}

	@RequestMapping(value = "/showAddCo/{subId}", method = RequestMethod.GET)
	public ModelAndView showpoPsoFaculty(@PathVariable("subId") int subId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			model = new ModelAndView("FacultyDetails/coPSOFaculty");
			model.addObject("title", "Faculty CO PO");

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map = new LinkedMultiValueMap<>();
			map.add("subId", subId);

			Subject editSubject = rest.postForObject(Constants.url + "/getSubjectBySubId", map, Subject.class);
			model.addObject("subject", editSubject);

			map = new LinkedMultiValueMap<>();
			map.add("programId", editSubject.getProgId());
			Program programDetail = rest.postForObject(Constants.url + "/getProgramByProgramId", map, Program.class);
			model.addObject("programDetail", programDetail);
			model.addObject("subId", subId);
			

			map = new LinkedMultiValueMap<>();
			map.add("subId", subId);
			map.add("facultyId", userObj.getGetData().getUserDetailId());

			SubjectCo[] arry = rest.postForObject(Constants.url + "/getSubjectCoListBySubId", map, SubjectCo[].class);
			List<SubjectCo> subjectCoList = new ArrayList<>(Arrays.asList(arry));

			model.addObject("programDetail", programDetail);
			model.addObject("subId", subId);
			model.addObject("subjectCoList", subjectCoList);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "submitSubjectCo", method = RequestMethod.POST)
	public String submitSubjectCo(HttpServletRequest request, HttpServletResponse response) {

		int subId = Integer.parseInt(request.getParameter("subId"));
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			Date date = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String coName = request.getParameter("coName");
			int coId = Integer.parseInt(request.getParameter("coId"));

			if (coId != 0) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("coName", coName);
				map.add("coId", coId);
				Info info = rest.postForObject(Constants.url + "/updateSubjeCoName", map, Info.class);

			} else {

				SubjectCo subjectCo = new SubjectCo();
				subjectCo.setCoId(coId);
				subjectCo.setDelStatus(1);
				subjectCo.setIsActive(1);
				subjectCo.setMakerEnterDatetime(sf.format(date));
				subjectCo.setSubId(subId);
				subjectCo.setFacultyId(userObj.getGetData().getUserDetailId());
				subjectCo.setMakerUserId(userObj.getUserId());
				subjectCo.setCoName(coName);

				SubjectCo arry = rest.postForObject(Constants.url + "/saveSubjectCo", subjectCo, SubjectCo.class);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return "redirect:/showAddCo/" + subId;

	}

	@RequestMapping(value = "/deleteSubjectCo/{coId}/{subId}", method = RequestMethod.GET)
	public String deleteSubjectCo(@PathVariable("coId") int coId, @PathVariable("subId") int subId,
			HttpServletRequest request, HttpServletResponse response) {

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("coId", coId);
			Info info = rest.postForObject(Constants.url + "/deleteSubjectsCo", map, Info.class);
			System.out.println(info);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return "redirect:/showAddCo/" + subId;

	}

	@RequestMapping(value = "/editSubjectCo/{coId}/{subId}", method = RequestMethod.GET)
	public ModelAndView editSubjectCo(@PathVariable("coId") int coId, @PathVariable("subId") int subId,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			model = new ModelAndView("FacultyDetails/coPSOFaculty");
			model.addObject("title", "Faculty CO PO");

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			
			
			map.add("subId", subId);

			Subject editSubject = rest.postForObject(Constants.url + "/getSubjectBySubId", map, Subject.class);
			model.addObject("subject", editSubject);
			
			map = new LinkedMultiValueMap<>();

			map.add("programId", editSubject.getProgId());
			Program programDetail = rest.postForObject(Constants.url + "/getProgramByProgramId", map, Program.class);
			model.addObject("programDetail", programDetail);
			model.addObject("subId", subId);

			map = new LinkedMultiValueMap<>();
			map.add("subId", subId);
			map.add("facultyId", userObj.getGetData().getUserDetailId());

			SubjectCo[] arry = rest.postForObject(Constants.url + "/getSubjectCoListBySubId", map, SubjectCo[].class);
			List<SubjectCo> subjectCoList = new ArrayList<>(Arrays.asList(arry));

			map = new LinkedMultiValueMap<>();
			map.add("coId", coId);
			SubjectCo editSubjectCo = rest.postForObject(Constants.url + "/getSubjectCoBySubId", map, SubjectCo.class);
			
			

			model.addObject("programDetail", programDetail);
			model.addObject("subId", subId);
			model.addObject("subjectCoList", subjectCoList);
			model.addObject("editSubjectCo", editSubjectCo);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	SubjectCo subjectCo = new SubjectCo();

	@RequestMapping(value = "/mapPoCo", method = RequestMethod.GET)
	public ModelAndView showPsoFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("FacultyDetails/PSOFaculty");
		try {

			model.addObject("title", "CO PO Mapping");
			int coId = Integer.parseInt(request.getParameter("coId"));
			int subId = Integer.parseInt(request.getParameter("subId"));
			int programId = Integer.parseInt(request.getParameter("programId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("programId", programId);
			ProgramOutcome[] programOutcomList = rest.postForObject(Constants.url + "/getProgramOutcomeListByProgramId",
					map, ProgramOutcome[].class);
			model.addObject("programOutcomList", programOutcomList);

			map = new LinkedMultiValueMap<>();
			map.add("coId", coId);
			subjectCo = rest.postForObject(Constants.url + "/getSubjectCoBySubId", map, SubjectCo.class);

			map.add("programId", programId);
			Program programDetail = rest.postForObject(Constants.url + "/getProgramByProgramId", map, Program.class);
			model.addObject("programDetail", programDetail);

			model.addObject("coId", coId);
			model.addObject("subId", subId);
			model.addObject("programId", programId);

			String[] poarry = subjectCo.getCoPoMap().split(",");
			model.addObject("poarry", poarry);
			model.addObject("subjectCo", subjectCo);
		} catch (Exception e) {

			System.err.println("exception In showpoPsoFaculty at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "submitPoCoMapping", method = RequestMethod.POST)
	public String submitPoCoMapping(HttpServletRequest request, HttpServletResponse response) {

		int coId = Integer.parseInt(request.getParameter("coId"));
		int subId = Integer.parseInt(request.getParameter("subId"));
		int programId = Integer.parseInt(request.getParameter("programId"));

		try {

			String poIds = new String();

			try {
				String[] ids = request.getParameterValues("poIds");
				for (int i = 0; i < ids.length; i++) {
					System.out.println(ids[i]);
					poIds = poIds + "," + ids[i];
				}
				subjectCo.setCoPoMap(poIds.substring(1, poIds.length()));

			} catch (Exception e) {

				subjectCo.setCoPoMap("");
			}

			String satisfyingValue = request.getParameter("satisfyingValue");

			subjectCo.setCoPoSatisfyingValue(satisfyingValue);
			System.out.println("subjectCo " + subjectCo);
			SubjectCo arry = rest.postForObject(Constants.url + "/saveSubjectCo", subjectCo, SubjectCo.class);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return "redirect:/mapPoCo?coId=" + coId + "&subId=" + subId + "&programId=" + programId;

	}

	@RequestMapping(value = "/mapPsoCo", method = RequestMethod.GET)
	public ModelAndView mapPsoCo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("FacultyDetails/PSOFacultyMapping");
		try {

			model.addObject("title", "CO PSO Mapping");
			int coId = Integer.parseInt(request.getParameter("coId"));
			int subId = Integer.parseInt(request.getParameter("subId"));
			int programId = Integer.parseInt(request.getParameter("programId"));

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("programId", programId);
			ProgramSpeceficOutcome[] programSpeceficOutcomeList = rest.postForObject(
					Constants.url + "/getProgramSpecificOutcomeList", map, ProgramSpeceficOutcome[].class);
			model.addObject("programSpeceficOutcomeList", programSpeceficOutcomeList);

			map = new LinkedMultiValueMap<>();
			map.add("coId", coId);
			subjectCo = rest.postForObject(Constants.url + "/getSubjectCoBySubId", map, SubjectCo.class);

			map.add("programId", programId);
			Program programDetail = rest.postForObject(Constants.url + "/getProgramByProgramId", map, Program.class);
			model.addObject("programDetail", programDetail);

			model.addObject("coId", coId);
			model.addObject("subId", subId);
			model.addObject("programId", programId);

			String[] psoarry = subjectCo.getCoPsoMap().split(",");
			model.addObject("psoarry", psoarry);
			model.addObject("subjectCo", subjectCo);
		} catch (Exception e) {

			System.err.println("exception In showpoPsoFaculty at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "submitPsoCoMapping", method = RequestMethod.POST)
	public String submitPsoCoMapping(HttpServletRequest request, HttpServletResponse response) {

		int coId = Integer.parseInt(request.getParameter("coId"));
		int subId = Integer.parseInt(request.getParameter("subId"));
		int programId = Integer.parseInt(request.getParameter("programId"));

		try {

			String poIds = new String();

			try {
				String[] ids = request.getParameterValues("psoIds");

				for (int i = 0; i < ids.length; i++) {
					System.out.println(ids[i]);
					poIds = poIds + "," + ids[i];
				}

				subjectCo.setCoPsoMap(poIds.substring(1, poIds.length()));

			} catch (Exception e) {

				subjectCo.setCoPsoMap("");
			}

			String satisfyingValue = request.getParameter("satisfyingValue");

			subjectCo.setCoPsoSatisfyingValue(satisfyingValue);
			System.out.println("subjectCo " + subjectCo);
			SubjectCo arry = rest.postForObject(Constants.url + "/saveSubjectCo", subjectCo, SubjectCo.class);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return "redirect:/mapPsoCo?coId=" + coId + "&subId=" + subId + "&programId=" + programId;

	}

	@RequestMapping(value = "/showSWOC", method = RequestMethod.GET)
	public ModelAndView showSWOC(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showSWOC", "showSWOCList", "1", "0", "0", "0", newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("FacultyDetails/swoc");

				model.addObject("title", "Faculty's SWOC Analysis");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("swocType", 1);
				SWOC[] arry = rest.postForObject(Constants.url + "/getSWOCByFacultyIdAndType", map, SWOC[].class);
				List<SWOC> swocList = new ArrayList<>(Arrays.asList(arry));
				model.addObject("strengthList", swocList);

				map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("swocType", 2);
				arry = rest.postForObject(Constants.url + "/getSWOCByFacultyIdAndType", map, SWOC[].class);
				List<SWOC> weakNessList = new ArrayList<>(Arrays.asList(arry));
				model.addObject("weakNessList", weakNessList);

				map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("swocType", 3);
				arry = rest.postForObject(Constants.url + "/getSWOCByFacultyIdAndType", map, SWOC[].class);
				List<SWOC> oppList = new ArrayList<>(Arrays.asList(arry));
				model.addObject("oppList", oppList);

				map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("swocType", 4);
				arry = rest.postForObject(Constants.url + "/getSWOCByFacultyIdAndType", map, SWOC[].class);
				List<SWOC> challengelist = new ArrayList<>(Arrays.asList(arry));
				model.addObject("challengelist", challengelist);

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showSWOCList", method = RequestMethod.GET)
	public ModelAndView showSWOCList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showSWOCList", "showSWOCList", "1", "0", "0", "0", newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("FacultyDetails/swocList");

				model.addObject("title", "Faculty's SWOC Analysis");
				int acYearId = (Integer) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("type", 1);
				map.add("yearId", acYearId);
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				GetSWOC[] arry = rest.postForObject(Constants.url + "/getSwocListByFacultyIdAndtype", map,
						GetSWOC[].class);
				List<GetSWOC> swocList = new ArrayList<>(Arrays.asList(arry));
				model.addObject("strengthList", swocList);

				map = new LinkedMultiValueMap<>();

				map.add("type", 2);
				map.add("yearId", acYearId);
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				arry = rest.postForObject(Constants.url + "/getSwocListByFacultyIdAndtype", map, GetSWOC[].class);
				List<GetSWOC> weakNessList = new ArrayList<>(Arrays.asList(arry));
				model.addObject("weakNessList", weakNessList);

				map = new LinkedMultiValueMap<>();

				map.add("type", 3);
				map.add("yearId", acYearId);
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				arry = rest.postForObject(Constants.url + "/getSwocListByFacultyIdAndtype", map, GetSWOC[].class);
				List<GetSWOC> oppList = new ArrayList<>(Arrays.asList(arry));
				model.addObject("oppList", oppList);

				map = new LinkedMultiValueMap<>();

				map.add("type", 4);
				map.add("yearId", acYearId);
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				arry = rest.postForObject(Constants.url + "/getSwocListByFacultyIdAndtype", map, GetSWOC[].class);
				List<GetSWOC> challengelist = new ArrayList<>(Arrays.asList(arry));
				model.addObject("challengelist", challengelist);

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showSWOCList at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/saveSWOC", method = RequestMethod.GET)
	public @ResponseBody List<SWOC> saveSWOC(HttpServletRequest request, HttpServletResponse response) {
		List<SWOC> swocList = new ArrayList<>();
		SWOC swoc = new SWOC();
		Info info = new Info();
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			Date date = new Date();

			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

			String swocText = request.getParameter("swocText");
			int swocType = Integer.parseInt(request.getParameter("swocType"));
			int swocId = Integer.parseInt(request.getParameter("swocId"));

			swoc.setDelStatus(1);
			swoc.setExInt1(1);
			swoc.setExInt2(1);
			swoc.setExVar2("NA");
			swoc.setExVar1("NA");
			swoc.setFacultyId(userObj.getGetData().getUserDetailId());
			swoc.setIsActive(1);
			swoc.setMakerEnterDatetime(sf.format(date));
			swoc.setMakerUserId(userObj.getUserId());
			swoc.setSwocText(swocText);
			swoc.setSwocType(swocType);
			int yearId = (int) session.getAttribute("acYearId");
			swoc.setYearId(yearId);
			swoc.setSwocId(swocId);

			SWOC res = rest.postForObject(Constants.url + "/saveSWOC", swoc, SWOC.class);
			System.out.println(res.toString());

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("facultyId", userObj.getGetData().getUserDetailId());
			map.add("swocType", swocType);
			SWOC[] arry = rest.postForObject(Constants.url + "/getSWOCByFacultyIdAndType", map, SWOC[].class);
			List<SWOC> list = new ArrayList<>(Arrays.asList(arry));
			swocList.addAll(list);
			System.out.println("swocList" + swocList.toString());

		} catch (

		Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");

		}

		return swocList;

	}

	@RequestMapping(value = "/editSwoc", method = RequestMethod.GET)
	public @ResponseBody SWOC editSwoc(HttpServletRequest request, HttpServletResponse response) {

		SWOC swoc = new SWOC();

		try {

			int swocId = Integer.parseInt(request.getParameter("swocId"));
			System.out.println("swocId" + swocId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("swocId", swocId);
			swoc = rest.postForObject(Constants.url + "/getSWOCBySwocId", map, SWOC.class);

			System.out.println("swocId" + swoc.toString());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return swoc;

	}

	@RequestMapping(value = "/deleteSwoc", method = RequestMethod.GET)
	public @ResponseBody List<SWOC> deleteSwoc(HttpServletRequest request, HttpServletResponse response) {

		List<SWOC> swocList = new ArrayList<>();
		Info info = new Info();
		SWOC swoc = new SWOC();
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			int swocId = Integer.parseInt(request.getParameter("swocId"));
			int swocType = Integer.parseInt(request.getParameter("swocType"));
			MultiValueMap<String, Object> map2 = new LinkedMultiValueMap<>();
			map2.add("swocId", swocId);
			swoc = rest.postForObject(Constants.url + "/getSWOCBySwocId", map2, SWOC.class);

			System.out.println("swoc" + swoc.toString());

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("swocIdList", swocId);
			info = rest.postForObject(Constants.url + "/deleteSwoc", map, Info.class);
			System.out.println(swocId);
			System.out.println(info.toString());

			map = new LinkedMultiValueMap<>();
			map.add("facultyId", userObj.getGetData().getUserDetailId());
			map.add("swocType", swocType);
			SWOC[] arry = rest.postForObject(Constants.url + "/getSWOCByFacultyIdAndType", map, SWOC[].class);
			List<SWOC> list = new ArrayList<>(Arrays.asList(arry));
			swocList.addAll(list);
			System.out.println("swocList" + swocList.toString());
		} catch (Exception e) {

			e.printStackTrace();
			info.setError(true);
			info.setMsg("error while saving");

		}

		return swocList;

	}

}
