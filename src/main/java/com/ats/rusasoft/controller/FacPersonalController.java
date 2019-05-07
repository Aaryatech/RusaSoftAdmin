package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.ats.rusasoft.faculty.model.FacultyAcademic;
import com.ats.rusasoft.faculty.model.FacultyPersonalDetail;
import com.ats.rusasoft.faculty.model.FacultyPhdDetails;
import com.ats.rusasoft.faculty.model.GetFacAcademic;
import com.ats.rusasoft.faculty.model.GetFacPerDetail;
import com.ats.rusasoft.faculty.model.GetFacPhdGuideDetail;
import com.ats.rusasoft.faculty.model.Journal;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
public class FacPersonalController {
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
	@RequestMapping(value = "/showPersonalDetails", method = RequestMethod.GET)
	public ModelAndView showPersonalDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			System.out.println("hii...");
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showPersonalDetails", "showPersonalDetails", "1", "0", "0",
					"0", newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("FacultyDetails/personalDetailList");

				Info addAccess = AccessControll.checkAccess("showPersonalDetails", "showPersonalDetails", "0", "1", "0",
						"0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showPersonalDetails", "showPersonalDetails", "0", "0",
						"1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showPersonalDetails", "showPersonalDetails", "0", "0",
						"0", "1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false) {
					System.err.println("Edit acce ==0");
					model.addObject("editAccess", 0);

				}

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				model.addObject("title", "Faculty Personal Details List");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				GetFacPerDetail[] facPerDetArr = rest.postForObject(Constants.url + "getFacPerDetailList", map,
						GetFacPerDetail[].class);
				List<GetFacPerDetail> facPerDetList = new ArrayList<>(Arrays.asList(facPerDetArr));

				model.addObject("facPerDetList", facPerDetList);

			} else {

				model = new ModelAndView("accessDenied");
			}
		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addPersonalDetails", method = RequestMethod.GET)
	public ModelAndView addPersonalDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		Info access = null;
		try {

			int facultyId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			String title = null;
			int temp = 0;
			try {
				temp = Integer.parseInt(request.getParameter("temp"));
			} catch (Exception e) {
				temp = 0;
			}

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			if (temp == 1) {

				access = AccessControll.checkAccess("addPersonalDetails", "addPersonalDetails", "0", "1", "0", "0",
						newModuleList);
			} else {

				access = AccessControll.checkAccess("addPersonalDetails", "addPersonalDetails", "0", "0", "1", "0",
						newModuleList);

			}
			System.err.println("addPersonalDetails " + access.toString());
			if (access.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {
				try {
					facultyId = Integer.parseInt(request.getParameter("add_fac_detail_id"));
					title = request.getParameter("title");

				} catch (Exception e) {
					facultyId = 0;// 0;
				}

				// int facultyId = 12;

				model = new ModelAndView("FacultyDetails/personalDetail");

				model.addObject("title", "Add Faculty Personal Details");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList edt:" + deptList.toString());
				model.addObject("deptList", deptList);

				// map.add("id", facultyId);
				map.add("type", 1);

				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);

				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));

				model.addObject("quolfList", quolfList);

				List<Designation> designationList = rest.getForObject(Constants.url + "/getAllDesignations",
						List.class);
				model.addObject("desigList", designationList);
				map = new LinkedMultiValueMap<>();
				map.add("id", userObj.getStaff().getFacultyId());
				Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				System.out.println("staff" + staff);

				if (temp == 1) {
					model.addObject("temp", temp);
				} else {
					model.addObject("temp", 0);
				}

				model.addObject("staff", staff);

				map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getStaff().getFacultyId());
				FacultyPersonalDetail facPerDetail = rest.postForObject(Constants.url + "/getFacultyPersonalDetail",
						map, FacultyPersonalDetail.class);
				try {
					facPerDetail.setfDob(DateConvertor.convertToDMY(facPerDetail.getfDob()));
				} catch (Exception e) {

				}
				model.addObject("facPerDetail", facPerDetail);
			}
		} catch (Exception e) {
			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	// insertFacPersonalDetail

	@RequestMapping(value = "/insertFacPersonalDetail", method = RequestMethod.POST)
	public String insertFacPersonalDetail(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertFacPersonalDetail");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int staffId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				staffId = Integer.parseInt(request.getParameter("staff_id"));
			} catch (Exception e) {
				staffId = 0;
			}

			System.err.println("staffId id  " + staffId);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = new Info();
			AccessControll.checkAccess("insertFacPersonalDetail", "showPersonalDetails", "0", "1", "0", "0",
					newModuleList);
			editAccess.setError(false);

			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				FacultyPersonalDetail facPersDetail = new FacultyPersonalDetail();
				int temp = Integer.parseInt(request.getParameter("temp"));
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				facPersDetail.setfAadhar(request.getParameter("f_aadhar"));
				facPersDetail.setFacultyId(staffId);
				facPersDetail.setfPan(request.getParameter("f_pan"));
				facPersDetail.setfAddress(request.getParameter("fac_address"));
				facPersDetail.setfAddress2(request.getParameter("fac_address2"));
				facPersDetail.setfDob(DateConvertor.convertToYMD(request.getParameter("f_dob")));
				facPersDetail.setfPastExp(Float.parseFloat(request.getParameter("f_prevExp")));
				facPersDetail.setfPhone(request.getParameter("f_phone"));
				facPersDetail.setfResident(request.getParameter("f_resident"));
				facPersDetail.setIsAddSame(Integer.parseInt(request.getParameter("is_add_same")));
				facPersDetail.setMakerPersDatetime(curDateTime);
				facPersDetail.setMakerPersUserId(userObj.getUserId());

				facPersDetail.setfGender(Integer.parseInt(request.getParameter("f_gender")));

				FacultyPersonalDetail facPerDetail = restTemplate.postForObject(
						Constants.url + "saveFacultyPersonalDetail", facPersDetail, FacultyPersonalDetail.class);

				// int isView = Integer.parseInt(request.getParameter("is_view"));
				if(facPerDetail!=null) {
					session.setAttribute("successMsg", "New Record Added Sucessfully");
				}
				else {
					session.setAttribute("successMsg", "Record Not Added");
				}
				if (temp == 1)
					redirect = "redirect:/addPersonalDetails";
				else
					redirect = "redirect:/addPersonalDetails";
			}

		} catch (Exception e) {
			System.err.println("Exce in save addPersonalDetails  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	@RequestMapping(value = "/showMphillDetails", method = RequestMethod.GET)
	public ModelAndView showMphillDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showMphillDetails", "showMphillDetails", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {

				model = new ModelAndView("FacultyDetails/mPhillDetailList");

				model.addObject("title", "Faculty M.Phil./Ph.D. Details List");

				Info addAccess = AccessControll.checkAccess("showMphillDetails", "showMphillDetails", "0", "1", "0",
						"0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showMphillDetails", "showMphillDetails", "0", "0", "1",
						"0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showMphillDetails", "showMphillDetails", "0", "0", "0",
						"1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);
				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				GetFacPhdGuideDetail[] facPhdGuideArray = rest.postForObject(Constants.url + "getFacPhdGuideDetailList",
						map, GetFacPhdGuideDetail[].class);
				List<GetFacPhdGuideDetail> facPhdGuideList = new ArrayList<>(Arrays.asList(facPhdGuideArray));
				System.err.println("facPhdGuideList " + facPhdGuideList.toString());

				model.addObject("facPhdGuideList", facPhdGuideList);

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {
			System.err.println("exception In facPhdGuideList at Master Contr" + e.getMessage());
			e.printStackTrace();
		}
		return model;
	}

	@RequestMapping(value = "/showAddMphillDetails", method = RequestMethod.GET)
	public ModelAndView showAddMphillDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			String title = request.getParameter("title");
			int temp1 = 0;

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showAddMphillDetails", "showAddMphillDetails", "1", "0", "0",
					"0", newModuleList);

			if (viewAccess.isError() == false) {

				model = new ModelAndView("FacultyDetails/mPhillDetail");
				model.addObject("title", "Add Faculty M.Phil./Ph.D. Details");

				int facultyId = 0;
				try {
					facultyId = Integer.parseInt(request.getParameter("add_fac_detail_id"));
				} catch (Exception e) {
					facultyId = 0;
				}
				try {

					temp1 = Integer.parseInt(request.getParameter("temp1"));
				} catch (Exception e) {
					temp1 = 0;
				}
				if (temp1 == 1) {
					model.addObject("temp1", temp1);
				} else {
					model.addObject("temp1", 0);
				}

				// model.addObject("title", "Personal Details Form");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				// map.add("instId", userObj.getGetData().getUserInstituteId());

				map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getStaff().getFacultyId());
				FacultyPhdDetails facPhdDetail = rest.postForObject(Constants.url + "/getFacultyPhdDetail", map,
						FacultyPhdDetails.class);
				try {
					facPhdDetail.setPhdRecognitionDt(DateConvertor.convertToDMY(facPhdDetail.getPhdRecognitionDt()));
					facPhdDetail.setPhdValidDt(DateConvertor.convertToDMY(facPhdDetail.getPhdValidDt()));
				} catch (Exception e) {
					// TODO: handle exception
				}
				model.addObject("facPhdDetail", facPhdDetail);

			}
		} catch (Exception e) {
			System.err.println("exception In showAddMphillDetails at Master Contr" + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/insertFacPhdDetail", method = RequestMethod.POST)
	public String insertFacPhdDetail(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertFacPhdDetail");
		ModelAndView model = null;
		String redirect = null;
		int temp1 = Integer.parseInt(request.getParameter("staff_id"));
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int staffId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				staffId = Integer.parseInt(request.getParameter("staff_id"));
			} catch (Exception e) {
				staffId = 0;
			}

			System.err.println("staffId id  " + staffId);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("insertFacPhdDetail", "showMphillDetails", "0", "1", "0", "0",
					newModuleList);
			editAccess.setError(false);

			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				FacultyPhdDetails facPhdDetail = new FacultyPhdDetails();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				facPhdDetail.setIsPhdGuide(Integer.parseInt(request.getParameter("isPhdGuide")));

				// if (facPhdDetail.getIsPhdGuide() == 1) {

				facPhdDetail.setFacultyId(staffId);

				facPhdDetail.setIsIctUsed(Integer.parseInt(request.getParameter("isIctUsed")));

				facPhdDetail.setPhdRecognitionDt(DateConvertor.convertToYMD(request.getParameter("phdRecognitionDt")));
				facPhdDetail.setPhdValidDt(DateConvertor.convertToYMD(request.getParameter("phdValidDt")));

				facPhdDetail.setPhdStuMphill(Integer.parseInt(request.getParameter("phdStuMphill")));
				facPhdDetail.setPhdStuPg(Integer.parseInt(request.getParameter("phdStuPg")));
				facPhdDetail.setPhdStuPhd(Integer.parseInt(request.getParameter("phdStuPhd")));

				facPhdDetail.setMakerPhdDatetime(curDateTime);
				facPhdDetail.setMakerPhdUserId(userObj.getUserId());

				FacultyPhdDetails facPerDetail = restTemplate.postForObject(Constants.url + "saveFacultyPhdDetails",
						facPhdDetail, FacultyPhdDetails.class);

				// }

				// int isView = Integer.parseInt(request.getParameter("is_view"));
				if(facPerDetail!=null) {
					session.setAttribute("successMsg", "New Record Added Sucessfully");
				}
				else {
					session.setAttribute("successMsg", "Record Not Added");
				}
				
				if (temp1 == 1)
					redirect = "redirect:/showAddMphillDetails";
				else
					redirect = "redirect:/showAddMphillDetails";
				// if (isView == 1)
				// redirect = "redirect:/showMphillDetails";
				// else
				// redirect = "redirect:/showAddMphillDetails";
			}

		} catch (Exception e) {
			System.err.println("Exce in save saveFacultyPhdDetails  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	// Fac Academic

	@RequestMapping(value = "/showAddAcademicDetails", method = RequestMethod.POST)
	public ModelAndView showAddAcademicDetails(HttpServletRequest request, HttpServletResponse response) {
		int temp2 = 0;
		ModelAndView model = null;
		// http: // localhost:8895/rusasoft/showAddAcademicDetails
		int facultyId = 0;
		try {

			model = new ModelAndView("FacultyDetails/addAcademicDetails");

			model.addObject("title", "Add Faculty Academic Details");

			try {
				facultyId = Integer.parseInt(request.getParameter("add_fac_detail_id"));
			} catch (Exception e) {
				facultyId = 0;
			}
			try {
				temp2 = Integer.parseInt(request.getParameter("temp2"));
			} catch (Exception e) {
				temp2 = 0;
			}

			if (temp2 == 1) {
				model.addObject("temp2", temp2);
			} else {
				model.addObject("temp2", 0);
			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("type", 1);
			Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
					Quolification[].class);
			List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));

			model.addObject("quolfList", quolfList);

			map = new LinkedMultiValueMap<String, Object>();
			map.add("facultyId", facultyId);
			// getFacAcademicByFacId

			FacultyAcademic editFacAcad = rest.postForObject(Constants.url + "getFacAcademicByFacId", map,
					FacultyAcademic.class);
			System.err.println("editFacAcad " + editFacAcad.toString());

			model.addObject("editFacAcad", editFacAcad);
			model.addObject("facultyId", facultyId);

		} catch (Exception e) {
			// model.addObject("editFacAcad", editFacAcad);
			model.addObject("facultyId", facultyId);

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAcademicDetails", method = RequestMethod.GET)
	public ModelAndView showAcademicDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			// model = new ModelAndView("FacultyDetails/academicDetails");

			// model.addObject("title", "Academic Details ");

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showAcademicDetails", "showAcademicDetails", "1", "0", "0",
					"0", newModuleList);

			if (viewAccess.isError() == false) {

				model = new ModelAndView("FacultyDetails/academicDetails");

				model.addObject("title", " Faculty Academic Details List");

				Info addAccess = AccessControll.checkAccess("showAcademicDetails", "showAcademicDetails", "0", "1", "0",
						"0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showAcademicDetails", "showAcademicDetails", "0", "0",
						"1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showAcademicDetails", "showAcademicDetails", "0", "0",
						"0", "1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false) {
					System.err.println("Edit acce ==0");
					model.addObject("editAccess", 0);

				}

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				GetFacAcademic[] facAcaArr = rest.postForObject(Constants.url + "getFacAcademicList", map,
						GetFacAcademic[].class);
				List<GetFacAcademic> facAcadList = new ArrayList<>(Arrays.asList(facAcaArr));
				System.err.println("facAcadList " + facAcadList.toString());

				model.addObject("facAcadList", facAcadList);

				model.addObject("temp2", 0);

			} else {

				model = new ModelAndView("accessDenied");

			}

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// insertFacAcademic
	@RequestMapping(value = "/insertFacAcademic", method = RequestMethod.POST)
	public String insertFacAcademic(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertFacAcademic");
		ModelAndView model = null;
		String redirect = null;
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int facAcadId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				facAcadId = Integer.parseInt(request.getParameter("fac_aca_id"));
			} catch (Exception e) {
				facAcadId = 0;
			}

			System.err.println("fac_aca_id id  " + facAcadId);

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("insertFacAcademic", "showEditFacAcademic", "0", "1", "0", "0",
					newModuleList);
			// editAccess.setError(false);

			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {

				FacultyAcademic facAcademic = new FacultyAcademic();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				int exInt = 0;
				String exVar = "Na";

				facAcademic.setDelStatus(1);

				facAcademic.setExInt1(exInt);
				facAcademic.setExInt2(exInt);
				facAcademic.setExVar1(exVar);
				facAcademic.setExVar2(exVar);

				facAcademic.setfAcaId(facAcadId);

				facAcademic.setFacultyId(userObj.getStaff().getFacultyId());
				facAcademic.setfClass(request.getParameter("fClass"));
				facAcademic.setfPassingYear(request.getParameter("fPassingYear"));
				facAcademic.setfQualificationId(Integer.parseInt(request.getParameter("fQualificationId")));
				facAcademic.setfUniversity(request.getParameter("fUniversity"));

				facAcademic.setIsActive(1);

				facAcademic.setMakerEnterDatetime(curDateTime);
				facAcademic.setMakerUserId(userObj.getUserId());

				FacultyAcademic facAcadeRes = restTemplate.postForObject(Constants.url + "saveFacultyAcademic",
						facAcademic, FacultyAcademic.class);
				
				if(facAcadeRes!=null) {
					session.setAttribute("successMsg", "New Record Added Sucessfully");
				}
				else {
					session.setAttribute("successMsg", "Record Not Added");
				}
				redirect = "redirect:/showEditFacAcademic";

			}

		} catch (Exception e) {
			System.err.println("Exce in save showEditFacAcademic  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	// editFacAcademic

	@RequestMapping(value = "/showEditFacAcademic", method = RequestMethod.GET)
	public ModelAndView showEditFacAcademic(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("showEditFacAcademic", "showEditFacAcademic", "0", "0", "1",
					"0", newModuleList);
			// editAccess.setError(false); // remove this.
			if (editAccess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				model = new ModelAndView("FacultyDetails/addAcademicDetails");

				model.addObject("title", "Add Faculty Academic Details");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				/*
				 * map.add("fAcaId", fAcaId); // getInstitute FacultyAcademic editFacAcad =
				 * rest.postForObject(Constants.url + "getFacAcademic", map,
				 * FacultyAcademic.class); try { //
				 * editInst.setRegDate(DateConvertor.convertToDMY(editInst.getRegDate())); }
				 * catch (Exception e) { System.err.
				 * println("Exce in show Edit showEditFacAcademic /showEditFacAcademic"); }
				 */
				// getFacAcademicByFacId

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("facultyId", userObj.getStaff().getFacultyId());
				// getInstitute
			
				try {
					
					FacultyAcademic editFacAcad = rest.postForObject(Constants.url + "getFacAcademicByFacId", map,
							FacultyAcademic.class);
					model.addObject("editFacAcad", editFacAcad);

					// editInst.setRegDate(DateConvertor.convertToDMY(editInst.getRegDate()));
				} catch (Exception e) {
					System.err.println("Exce in show Edit showEditFacAcademic /showEditFacAcademic");
				}


				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);

				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);

				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));

				model.addObject("quolfList", quolfList);

				// model.addObject("facultyId", editFacAcad.getFacultyId());

			}
		} catch (Exception e) {
			System.err.println("Exce in editInstitute/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/showFacultyAllDetails/{facultyId}", method = RequestMethod.GET)
	public ModelAndView showFacultyAllDetails(@PathVariable("facultyId") int facultyId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showFacultyAllDetails", "showStaffList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("FacultyDetails/view_faculty");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map = new LinkedMultiValueMap<>();
				map.add("facultyId", facultyId);
				FacultyPhdDetails facPhdDetail = rest.postForObject(Constants.url + "/getFacultyPhdDetail", map,
						FacultyPhdDetails.class);

				try {
					facPhdDetail.setPhdRecognitionDt(DateConvertor.convertToDMY(facPhdDetail.getPhdRecognitionDt()));
					facPhdDetail.setPhdValidDt(DateConvertor.convertToDMY(facPhdDetail.getPhdValidDt()));
				} catch (Exception e) {
					// TODO: handle exception
				}
				model.addObject("facPhdDetail", facPhdDetail);

				System.out.println("=====================" + facPhdDetail.toString());

				map = new LinkedMultiValueMap<>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList edt:" + deptList.toString());
				model.addObject("deptList", deptList);

				// map.add("id", facultyId);
				map.add("type", 1);

				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);

				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));

				model.addObject("quolfList", quolfList);

				List<Designation> designationList = rest.getForObject(Constants.url + "/getAllDesignations",
						List.class);
				model.addObject("desigList", designationList);
				map = new LinkedMultiValueMap<>();
				map.add("id", facultyId);
				Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				System.out.println("staff" + staff);

				model.addObject("staff", staff);
				map = new LinkedMultiValueMap<>();
				map.add("facultyId", facultyId);
				FacultyPersonalDetail facPerDetail = rest.postForObject(Constants.url + "/getFacultyPersonalDetail",
						map, FacultyPersonalDetail.class);
				try {
					facPerDetail.setfDob(DateConvertor.convertToDMY(facPerDetail.getfDob()));
				} catch (Exception e) {

				}
				model.addObject("facPerDetail", facPerDetail);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("facultyId", facultyId);
				// getFacAcademicByFacId

				FacultyAcademic editFacAcad = rest.postForObject(Constants.url + "getFacAcademicByFacId", map,
						FacultyAcademic.class);
				System.err.println("editFacAcad " + editFacAcad.toString());

				model.addObject("editFacAcad", editFacAcad);

			}
		} catch (Exception e) {

			System.err.println("exception In   at FacPersonalController Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

}
