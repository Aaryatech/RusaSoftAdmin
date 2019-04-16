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
import com.ats.rusasoft.faculty.model.Journal;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.InstituteSupport;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.Disctinctveness;
import com.ats.rusasoft.model.instprofile.GetDisctinctveness;
import com.ats.rusasoft.model.instprofile.GetHumanValues;
import com.ats.rusasoft.model.instprofile.GetResearchCenter;
import com.ats.rusasoft.model.instprofile.HumanValues;
import com.ats.rusasoft.model.instprofile.ResearchCenter;
import com.ats.rusasoft.model.instprofile.ValuesMaster;

@Controller
@Scope("session")
public class InstituteDistController {
	RestTemplate rest = new RestTemplate();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String curDate = dateFormat.format(new Date());
	String dateTime = dateFormat.format(now);

	@RequestMapping(value = "/showProgDistinctive", method = RequestMethod.GET)
	public ModelAndView showProgDistinctive(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("showProgDistinctive", "showProgDistinctive", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/instDistinctive");

				model.addObject("title", "Institutional Distinctiveness List");
				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instId", instituteId);
				map.add("yearId", yId);

				GetDisctinctveness[] distArray = rest.postForObject(Constants.url + "/getAllDistByInstituteId", map,
						GetDisctinctveness[].class);
				List<GetDisctinctveness> distlist = new ArrayList<>(Arrays.asList(distArray));

				model.addObject("distlist", distlist);

				Info add = AccessControll.checkAccess("showProgDistinctive", "showProgDistinctive", "0", "1", "0", "0",
						newModuleList);

				Info edit = AccessControll.checkAccess("showProgDistinctive", "showProgDistinctive", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showProgDistinctive", "showProgDistinctive", "0", "0", "0",
						"1", newModuleList);

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

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showProgDistinctive at Institute Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddProgDistinctive", method = RequestMethod.GET)
	public ModelAndView showAddProgDistinctive(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showAddProgDistinctive", "showProgDistinctive", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/add_prog_distinctive");

				model.addObject("title", " Add Institutional Distinctiveness");

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAddProgDistinctive at Institutedist Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertDist", method = RequestMethod.POST)
	public String insertDist(HttpServletRequest request, HttpServletResponse response) {
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

				int distId = 0;
				try {
					distId = Integer.parseInt(request.getParameter("distId"));
				} catch (Exception e) {
					distId = 0;
				}

				String distApplicableFrom = request.getParameter("date");
				String distBeneficiary = request.getParameter("befStake");
				String distName = request.getParameter("title");
				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int yearId = (int) session.getAttribute("acYearId");

				int is_view = Integer.parseInt(request.getParameter("is_view"));

				Disctinctveness dist = new Disctinctveness();
				dist.setDelStatus(1);
				dist.setDistApplicableFrom(DateConvertor.convertToYMD(distApplicableFrom));
				dist.setDistBeneficiary(distBeneficiary);
				dist.setDistId(distId);

				dist.setDistName(distName);
				dist.setExInt1(1);
				dist.setExInt2(1);
				dist.setExVar1("NA");
				dist.setExVar2("NA");
				dist.setInstituteId(instituteId);
				dist.setIsActive(1);
				dist.setMakerDatetime(dateTime);
				dist.setMakerUserId(userId);
				dist.setYearId(yearId);

				Disctinctveness distInsertRes = rest.postForObject(Constants.url + "saveDist", dist,
						Disctinctveness.class);

				System.err.println("distInsertRes " + distInsertRes.toString());

				if (is_view == 1) {
					returnString = "redirect:/showProgDistinctive";
				} else {
					returnString = "redirect:/showAddProgDistinctive";
				}
			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in distInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/editDist/{distId}", method = RequestMethod.GET)
	public ModelAndView editDist(@PathVariable("distId") int distId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showAddProgDistinctive", "showProgDistinctive", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_prog_distinctive");

				model.addObject("title", " Edit Institutional Distinctiveness");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("distId", distId);

				Disctinctveness editDist = rest.postForObject(Constants.url + "/getDistByDistId", map,
						Disctinctveness.class);
				System.out.println("distId:" + distId);

				model.addObject("editDist", editDist);

			}
		} catch (Exception e) {

			System.err.println("exception In editDist at InstrituteDist Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteDist/{distId}", method = RequestMethod.GET)
	public String deleteDist(@PathVariable("distId") int distId, HttpServletRequest request) {
		String value = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showAddProgDistinctive", "showProgDistinctive", "0", "0", "0", "1",
				newModuleList);
		if (view.isError() == true) {

			value = "redirect:/accessDenied";

		} else {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("distIdList", distId);
			Info info = rest.postForObject(Constants.url + "/deleteDists", map, Info.class);
			value = "redirect:/showProgDistinctive";
		}
		return value;

	}

	@RequestMapping(value = "/showHumanValues", method = RequestMethod.GET)
	public ModelAndView showHumanValues(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("showHumanValues", "showHumanValues", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/humanValues");
				model.addObject("title", "Human Values-Professional Ethics List");

				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instId", instituteId);
				map.add("yearId", yId);

				GetHumanValues[] valueArray = rest.postForObject(Constants.url + "/getAllHumanValuesByInstituteId", map,
						GetHumanValues[].class);
				List<GetHumanValues> valuelist = new ArrayList<>(Arrays.asList(valueArray));

				model.addObject("valuelist", valuelist);

				ValuesMaster[] distArray = rest.getForObject(Constants.url + "/getAllValuesList", ValuesMaster[].class);
				List<ValuesMaster> distlist = new ArrayList<>(Arrays.asList(distArray));

				model.addObject("distlist", distlist);

				Info add = AccessControll.checkAccess("showHumanValues", "showHumanValues", "0", "1", "0", "0",
						newModuleList);

				Info edit = AccessControll.checkAccess("showHumanValues", "showHumanValues", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showHumanValues", "showHumanValues", "0", "0", "0", "1",
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

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showHumanValues at Institute Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddHumanValues", method = RequestMethod.GET)
	public ModelAndView showAddHumanValues(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showAddHumanValues", "showHumanValues", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/add_human_values");

				model.addObject("title", "Add Human Values-Professional Ethics");

				ValuesMaster[] distArray = rest.getForObject(Constants.url + "/getAllValuesList", ValuesMaster[].class);
				List<ValuesMaster> distlist = new ArrayList<>(Arrays.asList(distArray));

				model.addObject("distlist", distlist);

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAddHumanValues at Institutedist Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertHumanValues", method = RequestMethod.POST)
	public String insertHumanValues(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddHumanValues", "showHumanValues", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				System.err.println("Inside insertHumanValues method");

				int valueId = 0;
				try {
					valueId = Integer.parseInt(request.getParameter("valueId"));
				} catch (Exception e) {
					valueId = 0;
				}
				int activityPcount = Integer.parseInt(request.getParameter("participant"));

				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");
				String activityName = request.getParameter("title");
				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int yearId = (int) session.getAttribute("acYearId");

				int is_view = Integer.parseInt(request.getParameter("is_view"));

				HumanValues value = new HumanValues();
				value.setDelStatus(1);
				value.setValueId(valueId);
				value.setExVar1("NA");
				value.setExVar2("NA");
				value.setInstituteId(instituteId);
				value.setIsActive(1);
				value.setMakerDatetime(dateTime);
				value.setMakerUserId(userId);
				value.setYearId(yearId);

				value.setExInt1(1);
				value.setExInt2(1);
				value.setActivityFromdt(DateConvertor.convertToYMD(fromDate));
				value.setActivityTodt(DateConvertor.convertToYMD(toDate));
				value.setActivityName(activityName);
				value.setActivityPcount(activityPcount);

				HumanValues valueInsertRes = rest.postForObject(Constants.url + "saveHumanValues", value,
						HumanValues.class);

				System.err.println("valueInsertRes " + valueInsertRes.toString());

				if (is_view == 1) {
					returnString = "redirect:/showHumanValues";
				} else {
					returnString = "redirect:/showAddHumanValues";
				}
			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in valueInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/editValue/{valueId}", method = RequestMethod.GET)
	public ModelAndView editValue(@PathVariable("valueId") int valueId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showAddHumanValues", "showHumanValues", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_human_values");

				model.addObject("title", "Edit Human Values-Professional Ethics");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("valueId", valueId);

				HumanValues editValue = rest.postForObject(Constants.url + "/getHumanValuesByValueId", map,
						HumanValues.class);
				System.out.println("valueId:" + valueId);

				model.addObject("editValue", editValue);

				ValuesMaster[] distArray = rest.getForObject(Constants.url + "/getAllValuesList", ValuesMaster[].class);
				List<ValuesMaster> distlist = new ArrayList<>(Arrays.asList(distArray));

				model.addObject("distlist", distlist);

			}
		} catch (Exception e) {

			System.err.println("exception In editDist at InstrituteDist Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteHumanValues/{valueId}", method = RequestMethod.GET)
	public String deleteHumanValues(@PathVariable("valueId") int valueId, HttpServletRequest request) {
		String value = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showAddHumanValues", "showHumanValues", "0", "0", "0", "1",
				newModuleList);
		if (view.isError() == true) {

			value = "redirect:/accessDenied";

		} else {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("valueIdList", valueId);
			Info info = rest.postForObject(Constants.url + "/deleteHumanVlaues", map, Info.class);
			value = "redirect:/showHumanValues";
		}
		return value;

	}

	@RequestMapping(value = "/showResearchCenter", method = RequestMethod.GET)
	public ModelAndView showResearchCenter(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("showResearchCenter", "showResearchCenter", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/list_research_center");
				model.addObject("title", "Research Center Details");

				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instId", instituteId);
				map.add("yearId", yId);

				GetResearchCenter[] researchArray = rest.postForObject(
						Constants.url + "/getAllResearchCenterByInstituteId", map, GetResearchCenter[].class);
				List<GetResearchCenter> centerlist = new ArrayList<>(Arrays.asList(researchArray));

				model.addObject("centerlist", centerlist);
				Info add = AccessControll.checkAccess("showResearchCenter", "showResearchCenter", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showResearchCenter", "showResearchCenter", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showResearchCenter", "showResearchCenter", "0", "0", "0", "1",
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

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showResearchCenter at Institute Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddResearchCenter", method = RequestMethod.GET)
	public ModelAndView showAddResearchCenter(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showAddResearchCenter", "showResearchCenter", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/add_research_center");

				model.addObject("title", "Add Research Center Details");

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAddResearchCenter at Institutedist Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertResearchCenter", method = RequestMethod.POST)
	public String insertResearchCenter(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddResearchCenter", "showResearchCenter", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				System.err.println("Inside insertResearchCenter method");

				int rcId = 0;
				try {
					rcId = Integer.parseInt(request.getParameter("rcId"));
				} catch (Exception e) {
					rcId = 0;
				}
				int rcGuideCount = Integer.parseInt(request.getParameter("rc_guide_count"));
				int rcStudentCount = Integer.parseInt(request.getParameter("rc_student_count"));

				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");
				String rcSubjectName = request.getParameter("rc_subject_name");
				String rcFacultyName = request.getParameter("rc_faculty_name");
				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int yearId = (int) session.getAttribute("acYearId");

				int is_view = Integer.parseInt(request.getParameter("is_view"));

				ResearchCenter value = new ResearchCenter();
				value.setDelStatus(1);
				value.setRcId(rcId);
				value.setExVar1("NA");
				value.setExVar2("NA");
				value.setInstituteId(instituteId);
				value.setIsActive(1);
				value.setMakerDatetime(dateTime);
				value.setMakerUserId(userId);
				value.setYearId(yearId);
				value.setRcFacultyName(rcFacultyName);
				value.setRcSubjectName(rcSubjectName);
				value.setRcValidityTodt(DateConvertor.convertToYMD(toDate));
				value.setRcValidityFromdt(DateConvertor.convertToYMD(fromDate));
				value.setRcStudentCount(rcStudentCount);
				value.setRcGuideCount(rcGuideCount);

				value.setExInt1(1);
				value.setExInt2(1);

				ResearchCenter valueInsertRes = rest.postForObject(Constants.url + "saveReserachCenter", value,
						ResearchCenter.class);

				System.err.println("valueInsertRes " + valueInsertRes.toString());

				if (is_view == 1) {
					returnString = "redirect:/showResearchCenter";
				} else {
					returnString = "redirect:/showAddResearchCenter";
				}
			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in valueInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/editResearchCenter/{rcId}", method = RequestMethod.GET)
	public ModelAndView editResearchCenter(@PathVariable("rcId") int rcId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showAddResearchCenter", "showResearchCenter", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_research_center");

				model.addObject("title", "Edit Research Center Details");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("rcId", rcId);

				ResearchCenter editValue = rest.postForObject(Constants.url + "/getResearchCenterByRcId", map,
						ResearchCenter.class);
				System.out.println("rcId:" + rcId);

				model.addObject("editValue", editValue);

			}
		} catch (Exception e) {

			System.err.println("exception In editResearchCenter at InstrituteDist Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteResearchCenter/{rcId}", method = RequestMethod.GET)
	public String deleteResearchCenter(@PathVariable("rcId") int rcId, HttpServletRequest request) {
		String value = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showAddResearchCenter", "showResearchCenter", "0", "0", "0", "1",
				newModuleList);
		if (view.isError() == true) {

			value = "redirect:/accessDenied";

		} else {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("rcIdList", rcId);
			Info info = rest.postForObject(Constants.url + "/deleteResearchCenter", map, Info.class);
			value = "redirect:/showResearchCenter";
		}
		return value;

	}

	@RequestMapping(value = "/showValuesMaster", method = RequestMethod.GET)
	public ModelAndView showValuesMaster(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showValuesMaster", "showValuesMaster", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/add_values_master");

				model.addObject("title", "Human Values and Professional Ethics Activity Names");

				ValuesMaster[] distArray = rest.getForObject(Constants.url + "/getAllValuesList", ValuesMaster[].class);
				List<ValuesMaster> distlist = new ArrayList<>(Arrays.asList(distArray));

				model.addObject("distlist", distlist);

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showValuesMaster at Institute Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertValueMaster", method = RequestMethod.POST)
	public String insertValueMaster(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showValuesMaster", "showValuesMaster", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				System.err.println("Inside insertValueMaster method");

				int valMastId = 0;
				try {
					valMastId = Integer.parseInt(request.getParameter("valMastId"));
				} catch (Exception e) {
					valMastId = 0;
				}
				int userId = (int) session.getAttribute("userId");

				String valText = request.getParameter("valText");
				ValuesMaster value = new ValuesMaster();
				value.setDelStatus(1);
				value.setIsActive(1);
				value.setMakerDatetime(dateTime);
				value.setMakerUserId(userId);
				value.setValText(valText);
				value.setValMastId(valMastId);

				ValuesMaster valueInsertRes = rest.postForObject(Constants.url + "saveValuesMaster", value,
						ValuesMaster.class);

				System.err.println("valueInsertRes " + valueInsertRes.toString());
				returnString = "redirect:/showValuesMaster";

			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in valueInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/editValuesMaster/{valMastId}", method = RequestMethod.GET)
	public ModelAndView editValuesMaster(@PathVariable("valMastId") int valMastId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showValuesMaster", "showValuesMaster", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_values_master");

				model.addObject("title", "Add  Values");

				ValuesMaster[] distArray = rest.getForObject(Constants.url + "/getAllValuesList", ValuesMaster[].class);
				List<ValuesMaster> distlist = new ArrayList<>(Arrays.asList(distArray));

				model.addObject("distlist", distlist);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("valMstId", valMastId);

				ValuesMaster editValue = rest.postForObject(Constants.url + "/getValuesMasterByValId", map,
						ValuesMaster.class);
				System.out.println("valMastId:" + valMastId);

				model.addObject("editValue", editValue);

			}
		} catch (Exception e) {

			System.err.println("exception In editResearchCenter at InstrituteDist Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteValuesMaster/{valMastId}", method = RequestMethod.GET)
	public String deleteValuesMaster(@PathVariable("valMastId") int valMastId, HttpServletRequest request) {
		String value = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showValuesMaster", "showValuesMaster", "0", "0", "0", "1",
				newModuleList);
		if (view.isError() == true) {

			value = "redirect:/accessDenied";

		} else {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("valIdList", valMastId);
			Info info = rest.postForObject(Constants.url + "/deleteValuesMaster", map, Info.class);
			value = "redirect:/showValuesMaster";
		}
		return value;

	}

}