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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.faculty.model.PhdGuidList;
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.FacultyActivity;
import com.ats.rusasoft.model.FacultyBook;
import com.ats.rusasoft.model.FacultyConference;
import com.ats.rusasoft.model.FacultyContribution;
import com.ats.rusasoft.model.FacultyPhdGuide;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.OrganizedList;
import com.ats.rusasoft.model.OutreachType;
import com.ats.rusasoft.model.StudMentorList;
import com.ats.rusasoft.model.StudentMentoring;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class FacultyModuleController {

	RestTemplate rest = new RestTemplate();

	/*
	 * @RequestMapping(value = "/showFacultyDetails", method = RequestMethod.GET)
	 * public ModelAndView showFacultyDetails1(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("master/facultyDetails");
	 * 
	 * model.addObject("title", "Faculty Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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

	/********************************************** Publication Detail*************************************************/
	@RequestMapping(value = "/showPublicationDetails", method = RequestMethod.GET)
	public ModelAndView showPublicationDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showPublicationDetails", "showAddPublicationDetailsList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
			FacultyConference facConf = new FacultyConference();
			model = new ModelAndView("FacultyDetails/publicationDetail");

			model.addObject("title", "Add Faculty's Published Content Details");
			model.addObject("facConf", facConf);
			model.addObject("formindex", 2);
			}
		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddPublicationDetailsList", method = RequestMethod.GET)
	public ModelAndView showAddPublicationDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			
			Info view = AccessControll.checkAccess("showAddPublicationDetailsList", "showAddPublicationDetailsList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
			LoginResponse facId = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("facId", facId.getRegPrimaryKey());
			model = new ModelAndView("FacultyDetails/pubDetailList");

			FacultyConference[] facCon = rest.postForObject(Constants.url + "/getfacultyConferenceByFacId", map,
					FacultyConference[].class);
			List<FacultyConference> facConfList = new ArrayList<>(Arrays.asList(facCon));
			System.out.println("FacConfLIst:" + facConfList);

			model.addObject("facConList", facConfList);
			model.addObject("title", "Faculty's Published Content Details");
			model.addObject("formindex", 2);
			
			Info add = AccessControll.checkAccess("showAddPublicationDetailsList", "showAddPublicationDetailsList", "0", "1", "0", "0",
					newModuleList);
			Info edit = AccessControll.checkAccess("showAddPublicationDetailsList", "showAddPublicationDetailsList", "0", "0", "1", "0",
					newModuleList);
			Info delete = AccessControll.checkAccess("showAddPublicationDetailsList", "showAddPublicationDetailsList", "0", "0", "0", "1",
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

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertFacultyConf", method = RequestMethod.POST)
	public String insertFacultyConf(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddProgDistinctive", "showProgDistinctive", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				System.err.println("Inside insertDist method");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String curDateTime = dateFormat.format(cal.getTime());

				FacultyConference facConf = new FacultyConference();

				
				int yId = (int) session.getAttribute("acYearId");
				int userId = (int) session.getAttribute("userId");
				int confId = 0;
				try {
					confId = Integer.parseInt(request.getParameter("conf_id"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (confId == 0) {
					facConf.setConfId(0);
				} else {
					facConf.setConfId(confId);
				}
				facConf.setFacultyId(userObj.getGetData().getUserDetailId());
				facConf.setYearId(yId);
				facConf.setConfName(request.getParameter("conf_name"));
				facConf.setConfType(request.getParameter("conf_type"));
				facConf.setConfDate(request.getParameter("conf_date"));
				facConf.setConfVenue(request.getParameter("conf_venue"));
				facConf.setConfFundFrom(request.getParameter("conf_fund"));
				facConf.setConfFundAmt(Integer.parseInt(request.getParameter("conf_amt")));
				facConf.setDelStatus(1);
				facConf.setIsActive(1);
				facConf.setMakerUserId(userId);
				facConf.setMakerEnterDatetime(curDateTime);
				facConf.setExInt1(0);
				facConf.setExVar1("Na");

				int is_view = Integer.parseInt(request.getParameter("is_view"));

				System.out.println("Fac Conf:" + facConf.toString());

				FacultyConference insFconf = rest.postForObject(Constants.url + "/insertNewFacConference", facConf,
						FacultyConference.class);
				System.out.println(insFconf.toString());

				if (is_view == 1) {
					returnString = "redirect:/showAddPublicationDetailsList";
				} else {
					returnString = "redirect:/showPublicationDetails";
				}
			} else {

				returnString = "redirect:/accessDenied";

			}

		} catch (Exception e) {
			System.err.println("EXCE in distInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/editFacultyConfrnc/{facId}", method = RequestMethod.GET)
	public ModelAndView editFacultyConf(@PathVariable("facId") int facId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		try {
		model = new ModelAndView("FacultyDetails/publicationDetail");
		
		HttpSession session = request.getSession();
		
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("editFacultyConfrnc/{facId}", "showAddPublicationDetailsList", "0", "0", "1", "0",
				newModuleList);

		if (view.isError() == true) {

			model = new ModelAndView("accessDenied");

		} else {
			
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("facId", facId);

		FacultyConference fConference = rest.postForObject(Constants.url + "/getFacConfByFacId", map,
				FacultyConference.class);
		model.addObject("facConf", fConference);
		model.addObject("title", "Edit Faculty's Published Content Details");
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/deleteFacultyConfrnc/{facId}", method = RequestMethod.GET)
	public String deleteFacultyConf(@PathVariable("facId") int facId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteFacultyConfrnc/{facId}", "showAddPublicationDetailsList", "0", "0", "0", "1",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("facId", facId);

		FacultyConference deletfConference = rest.postForObject(Constants.url + "/deleteFacultyConfrncById", map,
				FacultyConference.class);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showAddPublicationDetailsList";

	}
	

	@RequestMapping(value = "/delSlectedPublicationDetail/{confId}", method = RequestMethod.GET)
	public String deleteinstLinkages(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int confId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedPublicationDetail/{confId}", "showAddPublicationDetailsList", "0", "0",
				"0", "1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (confId == 0) {

					System.err.println("Multiple records delete ");
					String[] confIds = request.getParameterValues("confId");
					System.out.println("id are" + confIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < confIds.length; i++) {
						sb = sb.append(confIds[i] + ",");

					}
					String confIdList = sb.toString();
					confIdList = confIdList.substring(0, confIdList.length() - 1);

					map.add("confIdList", confIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("confIdList", confId);
				}

				Info errMsg = rest.postForObject(Constants.url + "delPubicationDetails", map, Info.class);

				a = "redirect:/showAddPublicationDetailsList";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/*
	 * @RequestMapping(value = "/showResearchDetails", method = RequestMethod.GET)
	 * public ModelAndView showResearchDetails(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/researchProDetail");
	 * 
	 * model.addObject("title", "Research Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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

	/*
	 * @RequestMapping(value = "/showResearchDetailsList", method =
	 * RequestMethod.GET) public ModelAndView
	 * showResearchDetailsList(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/researchProjectList");
	 * 
	 * model.addObject("title", "Research Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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

	/*
	 * @RequestMapping(value = "/showOutReachDetails", method = RequestMethod.GET)
	 * public ModelAndView showOutReachDetails(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/outReach");
	 * 
	 * model.addObject("title", "Out Reach Activity Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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

	/************************************** Faculty Activity *****************************************/

	@RequestMapping(value = "/showOrganized", method = RequestMethod.GET)
	public ModelAndView showOrganized(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		int inst_id = (int) session.getAttribute("instituteId");
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showOrganized", "showOrganizedList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else { 
			FacultyActivity facAct = new FacultyActivity();
			model = new ModelAndView("FacultyDetails/organized");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("instituteId", inst_id);

			OutreachType[] facultyOutreachArr = rest.postForObject(Constants.url + "/getOutReachTypeList", map,
					OutreachType[].class);
			List<OutreachType> facultyOutreachTypeList = new ArrayList<>(Arrays.asList(facultyOutreachArr));
			model.addObject("facultyOutreachTypeList", facultyOutreachTypeList);

			model.addObject("activity", facAct);
			model.addObject("title", "Organized Details Form");
			}
			
		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showOrganizedList", method = RequestMethod.GET)
	public ModelAndView showOrganizedList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showOrganizedList", "showOrganizedList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
		
			LoginResponse facId = (LoginResponse) session.getAttribute("userObj");
			int yId = (int) session.getAttribute("acYearId");
			int inst_id = (int) session.getAttribute("instituteId");

			model = new ModelAndView("FacultyDetails/organizedList");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map.add("yrId", yId);
			map.add("facId", facId.getRegPrimaryKey());
			OrganizedList[] faccAcArr = rest.postForObject(Constants.url + "/getAllActivityById", map,
					OrganizedList[].class);
			List<OrganizedList> facActList = new ArrayList<>(Arrays.asList(faccAcArr));
			model.addObject("facActList", facActList);

			model.addObject("title", "Faculty Organized - Out Reach Activity List");
			Info add = AccessControll.checkAccess("showOrganizedList", "showOrganizedList", "0", "1", "0", "0",
					newModuleList);
			Info edit = AccessControll.checkAccess("showOrganizedList", "showOrganizedList", "0", "0", "1", "0",
					newModuleList);
			Info delete = AccessControll.checkAccess("showOrganizedList", "showOrganizedList", "0", "0", "0", "1",
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

			System.err.println("exception In showOrganizedList at faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertFacultyActivity", method = RequestMethod.POST)
	public String insertFacultyActivity(HttpServletRequest request, HttpServletResponse response) {

		try {

			FacultyActivity facAct = new FacultyActivity();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int yId = (int) session.getAttribute("acYearId");
			int userId = (int) session.getAttribute("userId");

			facAct.setActivityId(Integer.parseInt(request.getParameter("activity_id")));
			facAct.setFacultyId(userObj.getGetData().getUserDetailId());
			facAct.setYearId(yId);
			facAct.setActivityType(request.getParameter("activity_type"));
			facAct.setActivityName(request.getParameter("activity_name"));
			facAct.setActivityLevel(request.getParameter("activity_level"));
			facAct.setActivityDate(request.getParameter("activity_date"));
			facAct.setActivityParticipants(request.getParameter("activity_part"));
			facAct.setActivityFundedBy(request.getParameter("activity_found"));
			facAct.setActivityAmountSanctioned(Integer.parseInt(request.getParameter("amt_sanc")));
			facAct.setActivityAmountUtilised(Integer.parseInt(request.getParameter("amt_utilise")));
			facAct.setDelStatus(1);
			facAct.setIsActive(1);
			facAct.setMakerUserId(userId);
			facAct.setMakerEnterDatetime(curDateTime);
			facAct.setExInt1(0);
			facAct.setExVar1("NA");

			FacultyActivity facacc = rest.postForObject(Constants.url + "/insertFacultyActivity", facAct,
					FacultyActivity.class);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:/showOrganizedList";

	}

	@RequestMapping(value = "/editFacultyActivity/{facActivityId}", method = RequestMethod.GET)
	public ModelAndView editFacultyAct(@PathVariable("facActivityId") int facActivityId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
	try {
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("editFacultyActivity/{facActivityId}", "showOrganizedList", "0", "0", "1", "0",
				newModuleList);

		if (view.isError() == true) {

			model = new ModelAndView("accessDenied");

		} else { 
		int inst_id = (int) session.getAttribute("instituteId");
		
		model = new ModelAndView("FacultyDetails/organized");
		model.addObject("title", "Edit Organized Outreach Activity Details");	

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();     

		map.add("instituteId", inst_id);

		OutreachType[] facultyOutreachArr = rest.postForObject(Constants.url + "/getOutReachTypeList", map,
				OutreachType[].class);
		List<OutreachType> facultyOutreachTypeList = new ArrayList<>(Arrays.asList(facultyOutreachArr));
		model.addObject("facultyOutreachTypeList", facultyOutreachTypeList);

		map.add("facActivityId", facActivityId);
		FacultyActivity fActivity = rest.postForObject(Constants.url + "/getFacActivityByActvId", map,
				FacultyActivity.class);
		model.addObject("activity", fActivity);
	}
	}catch(Exception e) {
		e.printStackTrace();
	}
		return model;

	}

	@RequestMapping(value = "/deleteFacultyActivity/{activityId}", method = RequestMethod.GET)
	public String deleteActivity(@PathVariable("activityId") int activityId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		try {
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("deleteFacultyActivity/{activityId}", "showOrganizedList", "0", "0", "0", "1",
				newModuleList);

		if (view.isError() == true) {

			model = new ModelAndView("accessDenied");

		} else {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("activityId", activityId);

		FacultyActivity deletActivity = rest.postForObject(Constants.url + "/deleteActivityById", map,
				FacultyActivity.class);
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/showOrganizedList";

	}
	
	@RequestMapping(value = "/delOrganizedDetails/{activityId}", method = RequestMethod.GET)
	public String delOrganizedDetails(HttpServletRequest request, HttpServletResponse response, @PathVariable int activityId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delOrganizedDetails/{activityId}", "showOrganizedList", "0", "0",
				"0", "1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (activityId == 0) {

					System.err.println("Multiple records delete ");
					String[] activityIds = request.getParameterValues("activityId");
					System.out.println("id are" + "linknameIds");

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < activityIds.length; i++) {
						sb = sb.append(activityIds[i] + ",");

					}
					String activityIdList = sb.toString();
					activityIdList = activityIdList.substring(0, activityIdList.length() - 1);

					map.add("activityIdList", activityIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("activityIdList", activityId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteOrgActivity", map, Info.class);

				a = "redirect:/showOrganizedList";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/*
	 * @RequestMapping(value = "/showSubDetails", method = RequestMethod.GET) public
	 * ModelAndView showSubDetails(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/subDetails");
	 * 
	 * model.addObject("title", "Subject Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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
	/*
	 * @RequestMapping(value = "/showAddSubDetails", method = RequestMethod.GET)
	 * public ModelAndView showAddSubDetails(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/addSubDetail");
	 * 
	 * model.addObject("title", "Subject Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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

	@RequestMapping(value = "/showpoPsoFaculty", method = RequestMethod.GET)
	public ModelAndView showpoPsoFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/coPSOFaculty");

			model.addObject("title", "Faculty CO PO");

		} catch (Exception e) {

			System.err.println("exception In showpoPsoFaculty at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPsoFaculty", method = RequestMethod.GET)
	public ModelAndView showPsoFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/PSOFaculty");

			model.addObject("title", "Faculty PSO");

		} catch (Exception e) {

			System.err.println("exception In showpoPsoFaculty at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showSubDetails1", method = RequestMethod.GET)
	public ModelAndView showSubDetails1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ResearchInnovation/subDetails1");

			model.addObject("title", "Subject Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	/*
	 * @RequestMapping(value = "/showAcademicDetails", method = RequestMethod.GET)
	 * public ModelAndView showAcademicDetails(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/academicDetails");
	 * 
	 * model.addObject("title", "Academic Details ");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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
	/*
	 * @RequestMapping(value = "/showAddAcademicDetails", method =
	 * RequestMethod.GET) public ModelAndView
	 * showAddAcademicDetails(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/addAcademicDetails");
	 * 
	 * model.addObject("title", "Academic Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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

	/*
	 * @RequestMapping(value = "/showPersonalDetails", method = RequestMethod.GET)
	 * public ModelAndView showPersonalDetails(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * System.out.println("hii..."); model = new
	 * ModelAndView("FacultyDetails/personalDetailList");
	 * 
	 * model.addObject("title", "Personal Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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

	/*
	 * @RequestMapping(value = "/addPersonalDetails", method = RequestMethod.GET)
	 * public ModelAndView addPersonalDetails(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * System.out.println("hii..."); model = new
	 * ModelAndView("FacultyDetails/personalDetail");
	 * 
	 * model.addObject("title", "Personal Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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

	/*
	 * @RequestMapping(value = "/showMphillDetails", method = RequestMethod.GET)
	 * public ModelAndView showMphillDetails(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/mPhillDetailList");
	 * 
	 * model.addObject("title", "M.phill/Ph.D.  Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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
	 * @RequestMapping(value = "/showAddMphillDetails", method = RequestMethod.GET)
	 * public ModelAndView showAddMphillDetails(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/mPhillDetail");
	 * 
	 * model.addObject("title", "M.phill/Ph.D.  Details Form");
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In showFacultyDetails at Master Contr" +
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

	/********************************* Student Mentor Details****************************************/
	@RequestMapping(value = "/showStudMentor", method = RequestMethod.GET)
	public ModelAndView showStudMentor(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showStudMentor", "showStudMentor", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/studMentor");

				LoginResponse facId = (LoginResponse) session.getAttribute("userObj");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				int acYearId = (int) session.getAttribute("acYearId");

				map.add("facId", facId.getRegPrimaryKey());
				map.add("yearId", acYearId);
				
				System.err.println(" yearId   Accessable "+acYearId);

				StudMentorList[] studL = rest.postForObject(Constants.url + "/getStudentMentoringDetailsList", map,
						StudMentorList[].class);
				List<StudMentorList> mentorList = new ArrayList<>(Arrays.asList(studL));
				System.err.println("Studmont List:" + mentorList);
				model.addObject("studL", mentorList);

				model.addObject("title", "Faculty Worked for Students Mentoring");

				Info add = AccessControll.checkAccess("showStudMentor", "showStudMentor", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showStudMentor", "showStudMentor", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showStudMentor", "showStudMentor", "0", "0", "0", "1",
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

		} catch (

		Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddStudMentor", method = RequestMethod.GET)
	public ModelAndView showAddStudMentor(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("showAddStudMentor", "showStudMentor", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				StudentMentoring stud = new StudentMentoring();
				model = new ModelAndView("FacultyDetails/addStudMentor");

				model.addObject("title", "Add Faculty Worked for Students Mentoring");
				model.addObject("stud", stud);
			}
		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addStudMentor", method = RequestMethod.POST)
	public String addStudMentor(HttpServletRequest request, HttpServletResponse response) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String curDateTime = dateFormat.format(cal.getTime());
		String returnString = new String();
		StudentMentoring stud = new StudentMentoring();

		HttpSession session = request.getSession();
		LoginResponse facId = (LoginResponse) session.getAttribute("userObj");
		int yId = (int) session.getAttribute("acYearId");
		int userId = (int) session.getAttribute("userId");
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddStudMentor", "showStudMentor", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				int is_view = Integer.parseInt(request.getParameter("is_view"));
				stud.setMenId(Integer.parseInt(request.getParameter("menId")));
				stud.setFacultyId(facId.getGetData().getUserDetailId());
				stud.setMenStuCount(Integer.parseInt(request.getParameter("stud_no")));
				stud.setYearId(yId);
				stud.setDelStatus(1);
				stud.setIsActive(1);
				stud.setMakerUserId(facId.getGetData().getUserInstituteId());
				stud.setMakerEnterDatetime(curDateTime);
				stud.setExInt1(0);
				stud.setExVar1("NA");

				System.out.println("Mentor:" + stud.toString());
				StudentMentoring saveStud = rest.postForObject(Constants.url + "/insertStudentMentoringDetails", stud,
						StudentMentoring.class);
				System.out.println(saveStud.toString());

				if (is_view == 1) {
					returnString = "redirect:/showStudMentor";
				} else {
					returnString = "redirect:/showAddStudMentor";
				}
			}

			else {

				returnString = "redirect:/accessDenied";

			}
		} catch (Exception e) {

			System.err.println("exception In showAddStudMentor at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return returnString;

	}

	@RequestMapping(value = "/editFacultyMentor/{menId}", method = RequestMethod.GET)
	public ModelAndView editFacultyMentor(@PathVariable("menId") int menId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editFacultyMentor/{menId}", "showStudMentor", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/addStudMentor");

				System.out.println("MID:" + menId);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("mId", menId);

				StudentMentoring studMontr = rest.postForObject(Constants.url + "/editFacultyMentoringDetailsById", map,
						StudentMentoring.class);
				System.out.println("Data:" + studMontr);

				model.addObject("title", "Edit Faculty Worked for Students Mentoring");

				model.addObject("stud", studMontr);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/deleteFacultyMentor/{menId}", method = RequestMethod.GET)
	public String deleteFacultyMentor(@PathVariable("menId") int menId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;

		String a = null;

		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteFacultyMentor/{menId}", "showStudMentor", "0", "0", "0", "1",
					newModuleList);
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			} else {
				model = new ModelAndView("FacultyDetails/addStudMentor");
				// int countid = Integer.parseInt(request.getParameter("menId"));
				System.out.println("MID:" + menId);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("mId", menId);

				StudentMentoring studMontr = rest.postForObject(Constants.url + "/deleteFacultyMentoringDetailsById",
						map, StudentMentoring.class);
				System.out.println("Data:" + studMontr);

				model.addObject("title", "Edit Monitoring Details");

				model.addObject("stud", studMontr);
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:/showStudMentor";

	}

	@RequestMapping(value = "/delSlectedStudmentr/{menId}", method = RequestMethod.GET)
	public String menIds(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int menId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("menIdsmenIds/{menId}", "showStudMentor", "0", "0",
				"0", "1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (menId == 0) {

					System.err.println("Multiple records delete ");
					String[] menIds = request.getParameterValues("menId");
					System.out.println("id are" + menIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < menIds.length; i++) {
						sb = sb.append(menIds[i] + ",");

					}
					String menIdList = sb.toString();
					menIdList = menIdList.substring(0, menIdList.length() - 1);

					map.add("menIdList", menIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("menIdList", menId);
				}

				Info errMsg = rest.postForObject(Constants.url + "delSlectedStudMentor", map, Info.class);

				a = "redirect:/showStudMentor";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}
	
	
	/************************************ Book Publication************************************/

	@RequestMapping(value = "/showBookPub", method = RequestMethod.GET)
	public ModelAndView showBookPub(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		
				Info view = AccessControll.checkAccess("showBookPub", "showIqacList", "0", "1", "0", "0",
						newModuleList);

				if (view.isError() == true) {

					model = new ModelAndView("accessDenied");

				} else {
			FacultyBook facBook = new FacultyBook();

			model = new ModelAndView("FacultyDetails/bookPub");

			model.addObject("book", facBook);
			model.addObject("title", "Add Faculty's Published Journal Details");
			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showBookPubList", method = RequestMethod.GET)
	public ModelAndView showBookPubList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/bookPubList");

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showBookPub", "showBookPubList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

			
			LoginResponse facId = (LoginResponse) session.getAttribute("userObj");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map.add("facId", facId.getRegPrimaryKey());
			FacultyBook[] booArr = rest.postForObject(Constants.url + "/getAllPublishedBooks", map,
					FacultyBook[].class);
			List<FacultyBook> bookList = new ArrayList<>(Arrays.asList(booArr));
			System.out.println("BookList:" + bookList);
			model.addObject("bookList", bookList);

			model.addObject("title", "Faculty's Published Journal Details");

			Info add = AccessControll.checkAccess("showBookPubList", "showBookPubList", "0", "1", "0", "0",
					newModuleList);
			Info edit = AccessControll.checkAccess("showBookPubList", "showBookPubList", "0", "0", "1", "0",
					newModuleList);
			Info delete = AccessControll.checkAccess("showBookPubList", "showBookPubList", "0", "0", "0", "1",
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

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertFacultyBook", method = RequestMethod.POST)
	public String insertFacultyBook(HttpServletRequest request, HttpServletResponse response) {

		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int yId = (int) session.getAttribute("acYearId");
			int userId = (int) session.getAttribute("userId");

			FacultyBook facBook = new FacultyBook();

			facBook.setBookId(Integer.parseInt(request.getParameter("book_id")));

			facBook.setFacultyId(userObj.getGetData().getUserDetailId());
			facBook.setYearId(yId);
			facBook.setBookTitle(request.getParameter("book_title"));
			facBook.setBookEdition(request.getParameter("book_edition"));
			facBook.setBookAuthor(request.getParameter("author"));
			facBook.setBookCoauther1(request.getParameter("co_author1"));
			facBook.setBookCoauther2(request.getParameter("co_author2"));
			facBook.setBookCoauther3(request.getParameter("co_author3"));
			facBook.setBookPublisher(request.getParameter("publisher"));
			facBook.setBookIsbn(request.getParameter("isbn"));
			facBook.setBookPubYear(request.getParameter("year_publication"));
			facBook.setDelStatus(1);
			facBook.setIsActive(1);
			facBook.setMakerUserId(userId);
			facBook.setMakerEnterDatetime(curDateTime);
			facBook.setExInt1(0);
			facBook.setExVar1("NA");

			FacultyBook facpubbook = rest.postForObject(Constants.url + "/savefacultyPubBook", facBook,
					FacultyBook.class);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:/showBookPubList";

	}

	@RequestMapping(value = "/editBookPublished/{bookId}", method = RequestMethod.GET)
	public ModelAndView editBookPublishedBook(@PathVariable("bookId") int bookId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = null;
		try {
			
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			
			Info view = AccessControll.checkAccess("editBookPublished/{bookId}", "showIqacList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("bookId", bookId);
			model = new ModelAndView("FacultyDetails/bookPub");

			FacultyBook pubBook = rest.postForObject(Constants.url + "/getPubBookById", map, FacultyBook.class);
			model.addObject("book", pubBook);

			model.addObject("title", "Edit Faculty's Published Journal Details");
			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteBookPublished/{bookId}", method = RequestMethod.GET)
	public String deleteBookPublished(@PathVariable("bookId") int bookId, HttpServletRequest request,
			HttpServletResponse response) {
			ModelAndView model = null;
		
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			
			Info view = AccessControll.checkAccess("deleteBookPublished/{bookId}", "showIqacList", "0", "0", "0", "1",
					newModuleList);
	
		if (view.isError() == true) {
	
			model = new ModelAndView("accessDenied");
	
		} else {
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
	
			map.add("bookId", bookId);
	
			FacultyBook pubBook = rest.postForObject(Constants.url + "/deletePubBookById", map, FacultyBook.class);
			}
		}catch(Exception e) {
		e.printStackTrace();
		}
			return "redirect:/showBookPubList";

	}

	
	@RequestMapping(value = "/deleteFacultyJournal/{bookId}", method = RequestMethod.GET)
	public String deleteLinkages(HttpServletRequest request, HttpServletResponse response, @PathVariable int bookId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteFacultyJournal/{bookId}", "showBookPubList", "0", "0",
				"0", "1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (bookId == 0) {

					System.err.println("Multiple records delete ");
					String[] bookIds = request.getParameterValues("bookId");
					System.out.println("id are" + "bookIds");

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < bookIds.length; i++) {
						sb = sb.append(bookIds[i] + ",");

					}
					String bookIdsList = sb.toString();
					bookIdsList = bookIdsList.substring(0, bookIdsList.length() - 1);

					map.add("bookIdsList", bookIdsList);
				} else {

					System.err.println("Single Record delete ");
					map.add("bookIdsList", bookId);
				}

				Info errMsg = rest.postForObject(Constants.url + "delFacultyPubJournals", map, Info.class);

				a = "redirect:/showBookPubList";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}
	/*
	 * @RequestMapping(value = "/showJournalPub", method = RequestMethod.GET) public
	 * ModelAndView showJournalPub(HttpServletRequest request, HttpServletResponse
	 * response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/journalPub");
	 * 
	 * model.addObject("title", "Journal Publication");
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

	/*
	 * @RequestMapping(value = "/showJournalPubList", method = RequestMethod.GET)
	 * public ModelAndView showJournalPubList(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = null; try {
	 * 
	 * model = new ModelAndView("FacultyDetails/journalPubList");
	 * 
	 * model.addObject("title", "Journal Publication");
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

	/*********************************************
	 * Faculty Contribution
	 ***********************************************/

	@RequestMapping(value = "/showOutReachContri", method = RequestMethod.GET)
	public ModelAndView showOutReachContri(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showOutReachContri", "showOutReachContriList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
			FacultyContribution facCon = new FacultyContribution();

			model = new ModelAndView("FacultyDetails/outReachContri");

			model.addObject("title", "Add Faculty's Contribution in Out Reach Activity");
			model.addObject("facContri", facCon);
			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertOutReachContri", method = RequestMethod.POST)
	public String insertOutReachContri(HttpServletRequest request, HttpServletResponse response) {

		try {

			FacultyContribution facCon = new FacultyContribution();

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int yId = (int) session.getAttribute("acYearId");
			int userId = (int) session.getAttribute("userId");

			facCon.setConId(Integer.parseInt(request.getParameter("fac_contriId")));

			facCon.setFacultyId(userObj.getGetData().getUserDetailId());
			facCon.setYearId(yId);
			facCon.setConLevel(request.getParameter("level"));
			facCon.setConName(request.getParameter("con_name"));
			facCon.setConUniversity(request.getParameter("university"));
			facCon.setConFrom(request.getParameter("from_date"));
			facCon.setConTo(request.getParameter("to_date"));
			facCon.setConExamSetting(Integer.parseInt(request.getParameter("examSetting")));
			facCon.setConAsEvaluation(Integer.parseInt(request.getParameter("ansEvaluation")));
			facCon.setConAsModeration(Integer.parseInt(request.getParameter("ansmod")));
			facCon.setDelStatus(1);
			facCon.setIsActive(1);
			facCon.setMakerUserId(userId);
			facCon.setMakerEnterDatetime(curDateTime);
			facCon.setExtraInt1(0);
			facCon.setExtraVar1("NA");

			FacultyContribution facContr = rest.postForObject(Constants.url + "/saveOutReachContri", facCon,
					FacultyContribution.class);

		} catch (Exception e) {
			e.printStackTrace();

		}
		return "redirect:/showOutReachContriList";

	}

	@RequestMapping(value = "/showOutReachContriList", method = RequestMethod.GET)
	public ModelAndView showOutReachContriList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showOutReachContriList", "showOutReachContriList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
			LoginResponse facId = (LoginResponse) session.getAttribute("userObj");
			int yId = (int) session.getAttribute("acYearId");

			model = new ModelAndView("FacultyDetails/outReachContriList");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map.add("facId", facId.getRegPrimaryKey());
			map.add("yrId", yId);

			FacultyContribution[] faccArr = rest.postForObject(Constants.url + "/getAllOutReachContri", map,
					FacultyContribution[].class);
			List<FacultyContribution> faccList = new ArrayList<>(Arrays.asList(faccArr));

			model.addObject("ContriList", faccList);
			model.addObject("title", "Faculty's Contribution in Out Reach Activity List");
			Info add = AccessControll.checkAccess("showOutReachContriList", "showOutReachContriList", "0", "1", "0", "0",
					newModuleList);
			Info edit = AccessControll.checkAccess("showOutReachContriList", "showOutReachContriList", "0", "0", "1", "0",
					newModuleList);
			Info delete = AccessControll.checkAccess("showOutReachContriList", "showOutReachContriList", "0", "0", "0", "1",
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

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/editContribtn/{conId}", method = RequestMethod.GET)
	public ModelAndView editContribtn(@PathVariable("conId") int conId, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("ID:" + conId);
		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editContribtn/{conId}", "showOutReachContriList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
			model = new ModelAndView("FacultyDetails/outReachContri");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("conId", conId);

			FacultyContribution fcondata = rest.postForObject(Constants.url + "/getOutReachContriById", map,
					FacultyContribution.class);
			model.addObject("facContri", fcondata);
			model.addObject("title", "Edit Out Reach Contribution");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/deleteContribtn/{conId}", method = RequestMethod.GET)
	public String deleteContribtn(@PathVariable("conId") int conId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteContribtn/{conId}", "showOutReachContriList", "0", "0", "0", "1",
					newModuleList);
		
			if (view.isError() == true) {
		
				model = new ModelAndView("accessDenied");
		
			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		
				map.add("conId", conId);
		
				FacultyContribution delFcon = rest.postForObject(Constants.url + "/deleteReachContriById", map,
						FacultyContribution.class);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
				return "redirect:/showOutReachContriList";
		
	}
	
	
	@RequestMapping(value = "/deleteOutReahContri/{facContId}", method = RequestMethod.GET)
	public String deleteOutReahContri(HttpServletRequest request, HttpServletResponse response, @PathVariable int facContId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteOutReahContri/{facContId}", "showOutReachContriList", "0", "0",
				"0", "1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (facContId == 0) {

					System.err.println("Multiple records delete ");
					String[] facContIds = request.getParameterValues("facContId");
					
					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < facContIds.length; i++) {
						sb = sb.append(facContIds[i] + ",");

					}
					String facContIdsList = sb.toString();
					facContIdsList = facContIdsList.substring(0, facContIdsList.length() - 1);

					map.add("facContIdsList", facContIdsList);
				} else {

					System.err.println("Single Record delete ");
					map.add("facContIdsList", facContId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteOutReachContiList", map, Info.class);

				a = "redirect:/showOutReachContriList";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteOutReachContiList at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/*****************************************
	 * Phd Guide
	 ************************************************/
	@RequestMapping(value = "/showPhdGuide", method = RequestMethod.GET)
	public ModelAndView showPhdGuide(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		MultiValueMap<String, Object> map=null;
		try {
			
			System.out.println("Hello");
			
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showPhdGuide", "showPhdGuideList", "0", "1", "0", "0",
					newModuleList);

			
			
			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
			FacultyPhdGuide phd = new FacultyPhdGuide();
			model = new ModelAndView("FacultyDetails/phdGuidence");

			map =new LinkedMultiValueMap<String, Object>(); 
			 map.add("type", 1);
			
			AcademicYear[] quolArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId", map, AcademicYear[].class);
			List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("acaYearList " + acaYearList.toString());

			model.addObject("acaYearList", acaYearList);
			model.addObject("title", "Ph.D. Guideline");
			model.addObject("phd", phd);
			}
		} catch (Exception e) {

			System.err.println("exception In showPhdGuide at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPhdGuideList", method = RequestMethod.GET)
	public ModelAndView showPhdGuideList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showPhdGuideList", "showPhdGuideList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
			LoginResponse facId = (LoginResponse) session.getAttribute("userObj");
			int yId = (int) session.getAttribute("acYearId");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map.add("facId", facId.getRegPrimaryKey());
			map.add("yrId", yId);

			model = new ModelAndView("FacultyDetails/phdGuideList");

			PhdGuidList[] phdArr = rest.postForObject(Constants.url + "/getAllPhdGuid", map, PhdGuidList[].class);
			List<PhdGuidList> phdList = new ArrayList<>(Arrays.asList(phdArr));
			System.out.println("List:" + phdList);
			model.addObject("phdList", phdList);
			model.addObject("title", "Ph.D. Guidance List");
			Info add = AccessControll.checkAccess("showPhdGuideList", "showPhdGuideList", "0", "1", "0", "0",
					newModuleList);
			Info edit = AccessControll.checkAccess("showPhdGuideList", "showPhdGuideList", "0", "0", "1", "0",
					newModuleList);
			Info delete = AccessControll.checkAccess("showPhdGuideList", "showPhdGuideList", "0", "0", "0", "1",
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

			System.err.println("exception In showPhdGuideList at Faculty Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertPhdGuide", method = RequestMethod.POST)
	public String insertPhdGuide(HttpServletRequest request, HttpServletResponse response) {

		try {

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int yId = (int) session.getAttribute("acYearId");
			int userId = (int) session.getAttribute("userId");

			FacultyPhdGuide phd = new FacultyPhdGuide();

			phd.setPhdId(Integer.parseInt(request.getParameter("phdGiudeId")));
			phd.setCoGuideName(request.getParameter("co_guide_name"));
			phd.setPhdAwardedYear(Integer.parseInt(request.getParameter("phd_year_awarded")));
			phd.setFacultyId(userObj.getGetData().getUserDetailId());
			phd.setYearId(yId);
			phd.setIsPhdGuide(Integer.parseInt(request.getParameter("phdGuide")));
			phd.setIsCoGuide(Integer.parseInt(request.getParameter("coGuide")));

			phd.setPhdScholarName(request.getParameter("phd_scholar"));
			phd.setPhdRegYear(request.getParameter("phd_year_reg"));
			phd.setPhdTopic(request.getParameter("phd_topic"));
			phd.setIsPhdAwarded(Integer.parseInt(request.getParameter("awarded")));

			phd.setDelStatus(1);
			phd.setIsActive(1);
			phd.setMakerUserId(userId);
			phd.setMakerEnterDatetime(curDateTime);
			phd.setExInt1(0);
			phd.setExVar1("NA");
			System.out.println(phd.toString());

			FacultyPhdGuide savePhd = rest.postForObject(Constants.url + "/insertPhdGuide", phd, FacultyPhdGuide.class);

		} catch (Exception e) {

			System.err.println("exception In showPhdGuide at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/showPhdGuideList";

	}

	@RequestMapping(value = "/editPhdGuide/{phdId}", method = RequestMethod.GET)
	public ModelAndView editPhdGuide(@PathVariable("phdId") int phdId, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("ID:" + phdId);
		ModelAndView model = null;
		
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editPhdGuide/{phdId}", "showPhdGuideList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
			model = new ModelAndView("FacultyDetails/phdGuidence");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			

			
			 map.add("type", 1);
			
			AcademicYear[] quolArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId", map, AcademicYear[].class);
			List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("acaYearList " + acaYearList.toString());

			model.addObject("acaYearList", acaYearList);
			
			map.add("phdId", phdId);
			FacultyPhdGuide phdData = rest.postForObject(Constants.url + "/getPhdGuideById", map,
					FacultyPhdGuide.class);
			model.addObject("phd", phdData);
			model.addObject("title", "Edit Ph.D. Guideline");
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/deletePhdGuide/{phdId}", method = RequestMethod.GET)
	public String deletePhdGuide(@PathVariable("phdId") int phdId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			ModelAndView model = null;
		
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deletePhdGuide/{phdId}", "showPhdGuideList", "0", "0", "0", "1",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

		map.add("phdId", phdId);

		FacultyPhdGuide delPhd = rest.postForObject(Constants.url + "/deletePhdGuideById", map, FacultyPhdGuide.class);
			}
		}catch(Exception e) {
				e.printStackTrace();
		}
		return "redirect:/showPhdGuideList";

	}
	
	
	@RequestMapping(value = "/deletePhdGuidenceDetail/{phdId}", method = RequestMethod.GET)
	public String deletePhdGuidenceDetail(HttpServletRequest request, HttpServletResponse response, @PathVariable int phdId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deletePhdGuidenceDetail/{phdId}", "showPhdGuideList", "0", "0",
				"0", "1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (phdId == 0) {

					System.err.println("Multiple records delete ");
					String[] phdIds = request.getParameterValues("phdId");
					
					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < phdIds.length; i++) {
						sb = sb.append(phdIds[i] + ",");

					}
					String phdIdsList = sb.toString();
					phdIdsList = phdIdsList.substring(0, phdIdsList.length() - 1);

					map.add("phdIdsList", phdIdsList);
				} else {

					System.err.println("Single Record delete ");
					map.add("phdIdsList", phdId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deletePhdGuidList", map, Info.class);

				a = "redirect:/showPhdGuideList";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteOutReachContiList at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

}
