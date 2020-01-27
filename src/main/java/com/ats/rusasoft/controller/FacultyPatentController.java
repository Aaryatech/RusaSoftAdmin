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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.XssEscapeUtils;
import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.commons.SessionKeyGen;
import com.ats.rusasoft.faculty.model.GetFacultyOutrea;
import com.ats.rusasoft.faculty.model.GetFacultyOutreach;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.FacultyAward;
import com.ats.rusasoft.model.FacultyOutreach;
import com.ats.rusasoft.model.FacultyPatent;
import com.ats.rusasoft.model.GetFacultyAward;
import com.ats.rusasoft.model.GetFacultyPatent;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.MIqac;
import com.ats.rusasoft.model.OutreachType;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class FacultyPatentController {

	RestTemplate rest = new RestTemplate();
	private String makerEnterDatetime;

	@RequestMapping(value = "/showPatentDetailsList", method = RequestMethod.GET)
	public ModelAndView showPatentDetailsList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showPatentDetailsList", "showPatentDetailsList", "1", "0", "0", "0",
					newModuleList);
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/patentDetailList");

				int yId = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());

				GetFacultyPatent[] arry = rest.postForObject(Constants.url + "/getPatentListByFacultyIdAndtype", map,
						GetFacultyPatent[].class);

				List<GetFacultyPatent> facultyPatentList = new ArrayList<>(Arrays.asList(arry));

				/*
				 * for (int i = 0; i < facultyPatentList.size(); i++) {
				 * 
				 * facultyPatentList.get(i).setPatentFilingDate(
				 * DateConvertor.convertToDMY(facultyPatentList.get(i).getPatentFilingDate()));
				 * facultyPatentList.get(i)
				 * .setPatentPubDate(DateConvertor.convertToDMY(facultyPatentList.get(i).
				 * getPatentPubDate()));
				 * 
				 * }
				 */

				// System.out.println("faculty Patent List :" + facultyPatentList);

				model.addObject("title", "Faculty's Patent Work Details");

				model.addObject("facultyPatentList", facultyPatentList);
				Info add = AccessControll.checkAccess("showPatentDetailsList", "showPatentDetailsList", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showPatentDetailsList", "showPatentDetailsList", "0", "0", "1",
						"0", newModuleList);
				Info delete = AccessControll.checkAccess("showPatentDetailsList", "showPatentDetailsList", "0", "0",
						"0", "1", newModuleList);

				if (add.isError() == false) {
					// System.out.println(" add Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					// System.out.println(" edit Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					// System.out.println(" delete Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPatentDetails", method = RequestMethod.GET)
	public ModelAndView showPatentDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showPatentDetails", "showPatentDetailsList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("FacultyDetails/patentDetails");

				model.addObject("title", "Add Faculty's Patent Work Details");
			}
		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;
	}

	@RequestMapping(value = "/insertPatentDetail", method = RequestMethod.POST)
	public String insertPatentDetail(HttpServletRequest request, HttpServletResponse response) {

		String returnString = new String();
		try {
			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
				Info view = AccessControll.checkAccess("showPatentDetails", "showPatentDetailsList", "0", "1", "0", "0",
						newModuleList);

				if (view.isError() == false) {
					LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
					int userId = (int) session.getAttribute("userId");
					int acYearId = (int) session.getAttribute("acYearId");
					int patentId = 0;

					try {

						patentId = Integer.parseInt(request.getParameter("patentId"));

					} catch (Exception e) {
						patentId = 0;
						System.err.println("exception In iqacNewRegistration at showIqacList Contr" + e.getMessage());
						e.printStackTrace();

					}

					// System.out.println("patentId:" + patentId);
					String patentNo = request.getParameter("patentNo");
					// System.out.println("patentNo:" + patentNo);
					String parentTitle = request.getParameter("parentTitle");
					String fillingDate = null;

					fillingDate = request.getParameter("fillingDate");

					String guideName = request.getParameter("guideName");
					String pubDate = request.getParameter("pubDate");
					int is_view = Integer.parseInt(request.getParameter("is_view"));

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());
					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					FacultyPatent faculty = new FacultyPatent();
					/* if (patentId == 0) { */

					faculty.setPatentId(patentId);
					faculty.setFacultyId(userObj.getGetData().getUserDetailId());
					faculty.setPatentTitle(XssEscapeUtils.jsoupParse(parentTitle));
					faculty.setDelStatus(1);
					faculty.setIsActive(1);
					faculty.setYearId(acYearId);
					faculty.setPatentFileNo(XssEscapeUtils.jsoupParse(patentNo));
					faculty.setPatentFilingDate(DateConvertor.convertToYMD(fillingDate));
					faculty.setPatentGuideName(XssEscapeUtils.jsoupParse(guideName));
					faculty.setPatentPubDate(DateConvertor.convertToYMD(pubDate));
					faculty.setMakerUserId(userId);
					faculty.setMakerEnterDatetime(curDateTime);

					FacultyPatent patent = rest.postForObject(Constants.url + "/saveFacultyPatent", faculty,
							FacultyPatent.class);

					/*
					 * } else { //faculty.setPatentId(patentId);
					 * faculty.setFacultyId(userObj.getGetData().getUserDetailId());
					 * faculty.setPatentTitle(parentTitle); faculty.setDelStatus(1);
					 * faculty.setIsActive(1); faculty.setYearId(acYearId);
					 * faculty.setPatentFileNo(patentNo);
					 * faculty.setPatentFilingDate(DateConvertor.convertToYMD(fillingDate));
					 * faculty.setPatentGuideName(guideName);
					 * faculty.setPatentPubDate(DateConvertor.convertToYMD(pubDate));
					 * faculty.setMakerUserId(userId); faculty.setMakerEnterDatetime(curDateTime);
					 * 
					 * FacultyPatent patent = rest.postForObject(Constants.url +
					 * "/saveFacultyPatent", faculty, FacultyPatent.class);
					 * 
					 * }
					 */

					if (is_view == 1) {
						returnString = "redirect:/showPatentDetailsList";
					} else {
						returnString = "redirect:/showPatentDetails";
					}
				} else {

					returnString = "redirect:/accessDenied";

				}
			} else {

				returnString = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			System.err.println("EXCE in vendInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;
	}

	@RequestMapping(value = "/editPatent/{patentId}", method = RequestMethod.GET)
	public ModelAndView editPatent(@PathVariable("patentId") int patentId, HttpServletRequest request) {

		// System.out.println("Id:" + patentId);

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showPatentDetails", "showPatentDetailsList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("patentId", patentId);
				model = new ModelAndView("FacultyDetails/patentDetails");
				model.addObject("title", "Edit Faculty's Patent Work Details");
				FacultyPatent patent = rest.postForObject(Constants.url + "/getFacultyPatent", map,
						FacultyPatent.class);
				// //System.out.println("patent"+patent);
				patent.setPatentFilingDate(DateConvertor.convertToDMY(patent.getPatentFilingDate()));
				patent.setPatentPubDate(DateConvertor.convertToDMY(patent.getPatentPubDate()));
				model.addObject("patent", patent);
			}
		} catch (Exception e) {

			System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteFacultyPatent/{patentId}/{hashKey}", method = RequestMethod.GET)
	public String deleteFacultyPatent(@PathVariable("patentId") int patentId, HttpServletRequest request,
			@PathVariable String hashKey) {
		String a = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("deleteFacultyPatent", "showPatentDetailsList", "0", "0", "0", "1",
				newModuleList);
		String key = (String) session.getAttribute("generatedKey");

		if (hashKey.trim().equals(key.trim())) {
			if (view.isError() == true) {
				a = "redirect:/accessDenied";
			} else {
				Info inf = new Info();
				// System.out.println("patentId:" + patentId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("patentId", patentId);
				Info miqc = rest.postForObject(Constants.url + "/deletePetentFaculty", map, Info.class);
				a = "redirect:/showPatentDetailsList";
			}
		} else {
			a = "redirect:/showPatentDetailsList";
		}

		SessionKeyGen.changeSessionKey(request);
		return a;

	}

	@RequestMapping(value = "/showAwardDetails", method = RequestMethod.GET)
	public ModelAndView showAwardDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/awardDetails");

			FacultyAward award = new FacultyAward();
			model.addObject("award", award);

			model.addObject("title", "Add Faculty Received Award - Recognition Details");

		} catch (Exception e) {

			System.err.println("exception In showAwardDetails at Library Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAwardDetailsList", method = RequestMethod.GET)
	public ModelAndView showAwardDetailsList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showAwardDetailsList", "showAwardDetailsList", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/awardDetailsList");
				int yId = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				GetFacultyAward[] arr = rest.postForObject(Constants.url + "/getAwardListByFacultyIdAndtype", map,
						GetFacultyAward[].class);
				List<GetFacultyAward> facultyAwardList = new ArrayList<GetFacultyAward>(Arrays.asList(arr));

				/*
				 * for (int i = 0; i < facultyAwardList.size(); i++) {
				 * 
				 * facultyAwardList.get(i)
				 * .setAwardDate(DateConvertor.convertToDMY(facultyAwardList.get(i).getAwardDate
				 * ())); facultyAwardList.get(i).setAwardValidityFrom(facultyAwardList.get(i).
				 * getAwardValidityFrom());
				 * facultyAwardList.get(i).setAwardValidityTo(facultyAwardList.get(i).
				 * getAwardValidityTo()); }
				 */
				// System.out.println("faculty Patent List :" + facultyAwardList);

				model.addObject("title", "Faculty Received Award - Recognition Details");

				model.addObject("facultyAwardList", facultyAwardList);

				Info add = AccessControll.checkAccess("showAwardDetailsList", "showAwardDetailsList", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showAwardDetailsList", "showAwardDetailsList", "0", "0", "1",
						"0", newModuleList);
				Info delete = AccessControll.checkAccess("showAwardDetailsList", "showAwardDetailsList", "0", "0", "0",
						"1", newModuleList);

				if (add.isError() == false) {
					// System.out.println(" add Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					// System.out.println(" edit Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					// System.out.println(" delete Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			System.err.println("exception In facultyAwardList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertAwardDetail", method = RequestMethod.POST)
	public String insertAwardDetail(HttpServletRequest request, HttpServletResponse response) {

		String returnString = new String();
		try {
			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
				Info view = AccessControll.checkAccess("insertAwardDetail", "showAwardDetailsList", "0", "1", "0", "0",
						newModuleList);

				if (view.isError() == false) {
					LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
					int userId = (int) session.getAttribute("userId");
					int acYearId = (int) session.getAttribute("acYearId");

					int awardId = 0;
					try {

						awardId = Integer.parseInt(request.getParameter("awardId"));

					} catch (Exception e) {
						awardId = 0;
						System.err.println("exception In iqacNewRegistration at showIqacList Contr" + e.getMessage());
						e.printStackTrace();

					}

					// System.out.println("awardId:" + awardId);

					String name = request.getParameter("name");
					String agency = request.getParameter("agency");
					String nature = request.getParameter("nature");
					String date = request.getParameter("date");
					int validity = Integer.parseInt(request.getParameter("validity"));
					int is_view = Integer.parseInt(request.getParameter("is_view"));
					int awrdRecog = Integer.parseInt(request.getParameter("award_recog"));

					String fromDate = request.getParameter("fromDate");

					String fromTo = request.getParameter("toDate");

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());
					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					FacultyAward faculty = new FacultyAward();
					if (awardId == 0) {

						faculty.setAwardId(awardId);
						faculty.setFacultyId(userObj.getGetData().getUserDetailId());
						faculty.setAwardName(XssEscapeUtils.jsoupParse(name));
						faculty.setDelStatus(1);
						faculty.setIsActive(1);
						faculty.setYearId(acYearId);
						faculty.setAwardAuthority(XssEscapeUtils.jsoupParse(agency));
						faculty.setExInt1(awrdRecog);
						if (awrdRecog == 1) {
							faculty.setExVar1(request.getParameter("incentive"));
						}
						faculty.setAwardDate(DateConvertor.convertToYMD(date));
						faculty.setAwardNature(XssEscapeUtils.jsoupParse(nature));
						faculty.setAwardValidity(validity);
						if (validity == 0) {
							faculty.setAwardValidityFrom(DateConvertor.convertToYMD(fromDate));
							faculty.setAwardValidityTo(DateConvertor.convertToYMD(fromTo));
						}

						faculty.setMakerUserId(userId);
						faculty.setMakerEnterDatetime(curDateTime);

						FacultyAward patent = rest.postForObject(Constants.url + "/saveFacultyAward", faculty,
								FacultyAward.class);

					} else {
						faculty.setAwardId(awardId);
						faculty.setFacultyId(userObj.getGetData().getUserDetailId());
						faculty.setAwardName(name);
						faculty.setDelStatus(1);
						faculty.setIsActive(1);
						faculty.setYearId(acYearId);
						faculty.setAwardAuthority(agency);
						faculty.setExInt1(awrdRecog);
						if (awrdRecog == 1) {
							faculty.setExVar1(request.getParameter("incentive"));
						}
						faculty.setAwardDate(DateConvertor.convertToYMD(date));
						faculty.setAwardNature(nature);
						faculty.setAwardValidity(validity);
						if (validity == 0) {
							faculty.setAwardValidityFrom(DateConvertor.convertToYMD(fromDate));
							faculty.setAwardValidityTo(DateConvertor.convertToYMD(fromTo));
						}
						faculty.setMakerUserId(userId);
						faculty.setMakerEnterDatetime(curDateTime);

						FacultyAward patent = rest.postForObject(Constants.url + "/saveFacultyAward", faculty,
								FacultyAward.class);

					}

					if (is_view == 1) {
						returnString = "redirect:/showAwardDetailsList";
					} else {
						returnString = "redirect:/showAwardDetails";
					}
				} else {

					returnString = "redirect:/accessDenied";

				}
			} else {

				returnString = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		}

		catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			System.err.println("EXCE in vendInsertRes " + e.getMessage());
			e.printStackTrace();
			returnString = "redirect:/showAwardDetailsList";
		}
		return returnString;

	}

	@RequestMapping(value = "/editAward/{awardId}", method = RequestMethod.GET)
	public ModelAndView editAward(@PathVariable("awardId") int awardId, HttpServletRequest request) {

		// System.out.println("Id:" + awardId);

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("editAward", "showAwardDetailsList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("awardId", awardId);

				model = new ModelAndView("FacultyDetails/awardDetails");
				model.addObject("title", "Edit Faculty Received Award - Recognition Details");

				FacultyAward award = rest.postForObject(Constants.url + "/getFacultyAwardById", map,
						FacultyAward.class);
				// //System.out.println("award"+award);

				award.setAwardDate(DateConvertor.convertToDMY(award.getAwardDate()));
				if (award.getAwardValidity() == 0) {
					award.setAwardValidityFrom(DateConvertor.convertToDMY(award.getAwardValidityFrom()));
					award.setAwardValidityTo(DateConvertor.convertToDMY(award.getAwardValidityTo()));
				}
				model.addObject("award", award);

			}
		} catch (Exception e) {

			System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteFacultyAward/{awardId}/{hashKey}", method = RequestMethod.GET)
	public String deleteFacultyAward(@PathVariable("awardId") int awardId, HttpServletRequest request,
			@PathVariable String hashKey) {
		String a = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("deleteFacultyAward/{awardId}/{hashKey}", "showAwardDetailsList", "0",
				"0", "0", "1", newModuleList);
		String key = (String) session.getAttribute("generatedKey");

		if (hashKey.trim().equals(key.trim())) {
			if (view.isError() == true) {
				a = "redirect:/accessDenied";
			} else {
				Info inf = new Info();
				// System.out.println("awardId:" + awardId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("awardId", awardId);
				Info miqc = rest.postForObject(Constants.url + "/deleteAwardFaculty", map, Info.class);
				a = "redirect:/showAwardDetailsList";
			}
		} else {
			a = "redirect:/showAwardDetailsList";
		}
		SessionKeyGen.changeSessionKey(request);
		return a;

	}

	@RequestMapping(value = "/showOutReachDetailsList", method = RequestMethod.GET)
	public ModelAndView showOutReachDetailsList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showOutReachDetailsList", "showOutReachDetailsList", "1", "0", "0",
					"0", newModuleList);
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/outReachList");

				model.addObject("title", "Attended - Out Reach Activity");

				int yId = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());

				GetFacultyOutrea[] facultyOutreachListArray = rest.postForObject(
						Constants.url + "getOutReachListByFacultyIdAndtype", map, GetFacultyOutrea[].class);

				List<GetFacultyOutrea> facultyOutreachList = new ArrayList<>(Arrays.asList(facultyOutreachListArray));

				// System.out.println("faculty outreach List :" +
				// facultyOutreachList.toString());

				for (int i = 0; i < facultyOutreachList.size(); i++) {
					facultyOutreachList.get(i)
							.setOutreachDate(DateConvertor.convertToDMY(facultyOutreachList.get(i).getOutreachDate()));

				}

				model.addObject("facultyOutreachList", facultyOutreachList);

				Info add = AccessControll.checkAccess("showOutReachDetailsList", "showOutReachDetailsList", "0", "1",
						"0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showOutReachDetailsList", "showOutReachDetailsList", "0", "0",
						"1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showOutReachDetailsList", "showOutReachDetailsList", "0", "0",
						"0", "1", newModuleList);

				if (add.isError() == false) {
					// System.out.println(" add Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					// System.out.println(" edit Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					// System.out.println(" delete Accessable ");
					model.addObject("deleteAccess", 0);

				}

			}

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;
	}

	@RequestMapping(value = "/showOutReachDetails", method = RequestMethod.GET)
	public ModelAndView showOutReachDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		int inst_id = (int) session.getAttribute("instituteId");
		try {

			model = new ModelAndView("FacultyDetails/outReach");

			model.addObject("title", "Add Attended - Out Reach Activity");

			FacultyOutreach editInst = new FacultyOutreach();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			map.add("instituteId", inst_id);
			List<OutreachType> facultyOutreachTypeList = rest.postForObject(Constants.url + "/getOutReachTypeList", map,
					List.class);
			// System.out.println("facultyOutreachTypeListList :" +
			// facultyOutreachTypeList.toString());

			model.addObject("facultyOutreachTypeList", facultyOutreachTypeList);
			model.addObject("editInst", editInst);

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertOutReachActivity", method = RequestMethod.POST)
	public String insertOutReachActivity(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String a = null;
		try {

			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				Info view = AccessControll.checkAccess("insertOutReachActivity", "showOutReachDetailsList", "0", "1",
						"0", "0", newModuleList);

				if (view.isError() == true)

				{

					a = "redirect:/accessDenied";

				}

				else {
					System.err.println("in insert insertLibrarian");
					ModelAndView model = null;

					int inst_id = (int) session.getAttribute("instituteId");
					int maker_id = (int) session.getAttribute("userId");
					int year_id = (int) session.getAttribute("acYearId");

					FacultyOutreach lib = new FacultyOutreach();
					RestTemplate restTemplate = new RestTemplate();

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

					int outreach_id = Integer.parseInt(request.getParameter("outreach_id"));
					// System.out.println("librarian_id" + "librarian_id");

					String act_name = request.getParameter("act_name");

					String activity_type = request.getParameter("activity_type");

					int faculty_id = userObj.getGetData().getUserDetailId();
					String act_level = request.getParameter("act_level");
					String act_date = request.getParameter("act_date");

					System.err.println("outreach_id id  " + outreach_id);
					if (outreach_id == 0) {

						// System.out.println("inst id is" + inst_id);

						lib.setFacultyId(faculty_id);
						lib.setIsActive(1);
						lib.setOutreachDate(DateConvertor.convertToYMD(act_date));
						lib.setOutreachName(XssEscapeUtils.jsoupParse(act_name));
						lib.setOutreachType(activity_type);
						lib.setOutreachLevel(act_level);
						lib.setYearId(year_id);

						lib.setMakerUserId(maker_id);

						lib.setInstituteId(inst_id);
						lib.setDelStatus(1);
						lib.setExInt1(1);
						lib.setExInt2(1);
						lib.setExVar1("NA");
						lib.setExVar2("NA");

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						String curDateTime = dateFormat.format(cal.getTime());

						DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

						String curDate = dateFormatStr.format(new Date());

						lib.setMakerEnterDatetime(curDateTime);

						FacultyOutreach editInst = rest.postForObject(Constants.url + "saveFacultyOutReach", lib,
								FacultyOutreach.class);

					} else {
						// System.out.println("in edit");

						map.add("outreachId", outreach_id); // getInstitute Hod hod =
						FacultyOutreach lib1 = rest.postForObject(Constants.url + "getFacultyOutReach", map,
								FacultyOutreach.class);
						lib1.setFacultyId(faculty_id);
						lib1.setIsActive(1);

						lib1.setOutreachDate(DateConvertor.convertToYMD(act_date));
						lib1.setOutreachName(XssEscapeUtils.jsoupParse(act_name));
						lib1.setOutreachType(activity_type);
						lib1.setOutreachLevel(act_level);
						lib1.setYearId(year_id);

						lib1.setMakerUserId(maker_id);

						lib1.setInstituteId(inst_id);

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						String curDateTime = dateFormat.format(cal.getTime());

						DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

						String curDate = dateFormatStr.format(new Date());

						lib1.setMakerEnterDatetime(curDateTime);
						FacultyOutreach editInst = rest.postForObject(Constants.url + "saveFacultyOutReach", lib1,
								FacultyOutreach.class);
					}

					int isView = Integer.parseInt(request.getParameter("is_view"));
					if (isView == 1)
						a = "redirect:/showOutReachDetailsList";

					else
						a = "redirect:/showOutReachDetails";

				}
			} else {

				a = "redirect:/accessDenied";
			}

		}

		catch (Exception e) {
			System.err.println("Exce in save lib  " + e.getMessage());
			e.printStackTrace();
		}

		return a;

	}

	@RequestMapping(value = "/deleteFacOutReach/{outId}", method = RequestMethod.GET)
	public String deleteLibrarians(HttpServletRequest request, HttpServletResponse response, @PathVariable int outId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteFacOutReach/{outId}", "showOutReachDetailsList", "0", "0", "0",
				"1", newModuleList);
		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				System.err.println("Single Record delete ");
				map.add("outreachId", outId);

				Info errMsg = rest.postForObject(Constants.url + "deleteOutreachFaculty", map, Info.class);

				a = "redirect:/showOutReachDetailsList";
			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	@RequestMapping(value = "/showEditFacOutReach", method = RequestMethod.POST)
	public ModelAndView showEditFacOutReach(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showEditFacOutReach", "showOutReachDetailsList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/outReach");
				int inst_id = (int) session.getAttribute("instituteId");
				System.err.println("inst_id id is" + inst_id);
				int edit_outreach_id = Integer.parseInt(request.getParameter("e_outreach_id"));
				System.err.println("edit_outreach_id id is" + edit_outreach_id);

				model.addObject("title", "Edit Attended - Out Reach Activity");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("outreachId", edit_outreach_id);

				FacultyOutreach editInst = rest.postForObject(Constants.url + "getFacultyOutReach", map,
						FacultyOutreach.class);

				System.err.println("FacultyOutreach is" + editInst.toString());

				model.addObject("editInst", editInst);
				model.addObject("edit_outreach_id", 1);

				RestTemplate restTemplate = new RestTemplate();
				map = new LinkedMultiValueMap<String, Object>();

				map.add("instituteId", inst_id);
				List<OutreachType> facultyOutreachTypeList = rest.postForObject(Constants.url + "/getOutReachTypeList",
						map, List.class);

				model.addObject("date", DateConvertor.convertToDMY(editInst.getOutreachDate()));
				// System.out.println("facultyOutreachTypeListList :" +
				// facultyOutreachTypeList.toString());

				model.addObject("facultyOutreachTypeList", facultyOutreachTypeList);
			}

		} catch (Exception e) {
			System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

}
