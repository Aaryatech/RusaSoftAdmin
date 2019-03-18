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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.faculty.model.GetJournal;
import com.ats.rusasoft.faculty.model.GetSubject;
import com.ats.rusasoft.faculty.model.Journal;
import com.ats.rusasoft.faculty.model.ResearchProject;
import com.ats.rusasoft.faculty.model.Subject;
import com.ats.rusasoft.master.model.Program;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.IqacList;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.MIqac;
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

				model.addObject("title", "Journal Publication");

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
				journal.setJournalYear(DateConvertor.convertToYMD(journalYear));
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

				model.addObject("title", "Journal Publication List");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());

				List<GetJournal> jouList = rest.postForObject(Constants.url + "/getJournalListByFacultyId", map,
						List.class);

				System.out.println("jouList" + jouList);

				model.addObject("jouList", jouList);

				Info add = AccessControll.checkAccess("showJournalPubList", "showJournalPubList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showJournalPubList", "showJournalPubList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showJournalPubList", "showJournalPubList", "0", "0", "0", "1",
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
				model.addObject("title", "Journal Publication");
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

			Info add = AccessControll.checkAccess("showJournalPub", "showJournalPubList", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("FacultyDetails/researchProDetail");

				model.addObject("title", "Research Details Form");

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

				int grant = Integer.parseInt(request.getParameter("grant"));

				float totalAmt = Float.parseFloat(request.getParameter("totalAmt"));
				float amtRec = Float.parseFloat(request.getParameter("amtRec"));
				int is_view = Integer.parseInt(request.getParameter("is_view"));
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
				project.setProjYear(DateConvertor.convertToYMD(yearOfPS));
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
		/*
		 * } catch (Exception e) { System.err.println("EXCE in vendInsertRes " +
		 * e.getMessage()); e.printStackTrace();
		 * 
		 * } return "redirect:/showResearchDetails";
		 */

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

				model.addObject("title", "Research Details Form List");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());

				List<GetResearchProject> jouList = rest.postForObject(Constants.url + "/getProjectListByFacultyId", map,
						List.class);

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
				model.addObject("title", "Research Details Form");
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

				model.addObject("title", "Subject Details Form");

				List<GetSubject> subList = rest.getForObject(Constants.url + "/getAllSubjectList", List.class);

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

				Program[] program = rest.getForObject(Constants.url + "/getAllProgramList", Program[].class);
				List<Program> proList = new ArrayList<Program>(Arrays.asList(program));

				model = new ModelAndView("FacultyDetails/addSubDetail");

				model.addObject("title", "Subject Details Form");
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

				System.err.println("Inside insertJournal method");

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

				Program[] program = rest.getForObject(Constants.url + "/getAllProgramList", Program[].class);
				List<Program> proList = new ArrayList<Program>(Arrays.asList(program));

				model = new ModelAndView("FacultyDetails/addSubDetail");

				model.addObject("title", "Edit Subject Details Form");
				model.addObject("proList", proList);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
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

}
