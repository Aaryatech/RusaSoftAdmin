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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.faculty.model.FacultyAcademic;
import com.ats.rusasoft.faculty.model.FacultyPersonalDetail;
import com.ats.rusasoft.faculty.model.FacultyPhdDetails;
import com.ats.rusasoft.faculty.model.GetFacPerDetail;
import com.ats.rusasoft.master.model.prodetail.AlumniDetail;
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
			model = new ModelAndView("FacultyDetails/personalDetailList");

			model.addObject("title", "Personal Details Form");
			HttpSession session = request.getSession();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());

			GetFacPerDetail[] facPerDetArr = rest.postForObject(Constants.url + "getFacPerDetailList", map,
					GetFacPerDetail[].class);
			List<GetFacPerDetail> facPerDetList = new ArrayList<>(Arrays.asList(facPerDetArr));

			model.addObject("facPerDetList", facPerDetList);
		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addPersonalDetails", method = RequestMethod.GET)
	public ModelAndView addPersonalDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			int facultyId = 12;

			HttpSession session = request.getSession();

			model = new ModelAndView("FacultyDetails/personalDetail");

			model.addObject("title", "Personal Details Form");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());
			Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
			List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
			System.err.println("deptList edt:" + deptList.toString());
			model.addObject("deptList", deptList);

			map.add("id", facultyId);
			map.add("type", 1);

			Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
					Quolification[].class);

			List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));

			model.addObject("quolfList", quolfList);

			List<Designation> designationList = rest.getForObject(Constants.url + "/getAllDesignations", List.class);
			model.addObject("desigList", designationList);

			Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
			System.out.println("staff" + staff);
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Calendar startCalendar = new GregorianCalendar();
			startCalendar.setTime(df.parse(staff.getJoiningDate()));
			Calendar endCalendar = new GregorianCalendar();
			endCalendar.setTime(new Date());

			int diffYear = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
			int diffMonth = diffYear * 12 + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);

			System.err.println("diffMonth " + diffMonth);
			System.err.println("diffYear " + diffYear);

			model.addObject("staff", staff);
			map = new LinkedMultiValueMap<>();
			map.add("facultyId", facultyId);
			FacultyPersonalDetail facPerDetail = rest.postForObject(Constants.url + "/getFacultyPersonalDetail", map,
					FacultyPersonalDetail.class);
			facPerDetail.setfDob(DateConvertor.convertToDMY(facPerDetail.getfDob()));
			model.addObject("facPerDetail", facPerDetail);
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

			Info editAccess = new Info();// AccessControll.checkAccess("insertAlumni", "showAlumini", "1", "0", "0",
											// "0",
			// newModuleList);
			editAccess.setError(false);

			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				FacultyPersonalDetail facPersDetail = new FacultyPersonalDetail();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				facPersDetail.setfAadhar(request.getParameter("f_aadhar"));
				facPersDetail.setFacultyId(staffId);
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

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showPersonalDetails";
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

			model = new ModelAndView("FacultyDetails/mPhillDetailList");

			model.addObject("title", "M.phill/Ph.D.  Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddMphillDetails", method = RequestMethod.GET)
	public ModelAndView showAddMphillDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/mPhillDetail");
			model.addObject("title", "M.phill/Ph.D.  Details Form");

			int facultyId = 12;

			HttpSession session = request.getSession();

			model.addObject("title", "Personal Details Form");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			// map.add("instId", userObj.getGetData().getUserInstituteId());

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

			Info editAccess = new Info();// AccessControll.checkAccess("insertAlumni", "showAlumini", "1", "0", "0",
											// "0",
			// newModuleList);
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

				if (facPhdDetail.getIsPhdGuide() == 1) {

					facPhdDetail.setFacultyId(staffId);

					facPhdDetail.setIsIctUsed(Integer.parseInt(request.getParameter("isIctUsed")));

					facPhdDetail
							.setPhdRecognitionDt(DateConvertor.convertToYMD(request.getParameter("phdRecognitionDt")));
					facPhdDetail.setPhdValidDt(DateConvertor.convertToYMD(request.getParameter("phdValidDt")));

					facPhdDetail.setPhdStuMphill(Integer.parseInt(request.getParameter("phdStuMphill")));
					facPhdDetail.setPhdStuPg(Integer.parseInt(request.getParameter("phdStuPg")));
					facPhdDetail.setPhdStuPhd(Integer.parseInt(request.getParameter("phdStuPhd")));

					facPhdDetail.setMakerPhdDatetime(curDateTime);
					facPhdDetail.setMakerPhdUserId(userObj.getUserId());

					FacultyPhdDetails facPerDetail = restTemplate.postForObject(Constants.url + "saveFacultyPhdDetails",
							facPhdDetail, FacultyPhdDetails.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showMphillDetails";
				else
					redirect = "redirect:/showAddMphillDetails";
			}

		} catch (Exception e) {
			System.err.println("Exce in save saveFacultyPhdDetails  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	// Fac Academic

	@RequestMapping(value = "/showAddAcademicDetails", method = RequestMethod.GET)
	public ModelAndView showAddAcademicDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;http://localhost:8895/rusasoft/showAddAcademicDetails
		try {

			model = new ModelAndView("FacultyDetails/addAcademicDetails");

			model.addObject("title", "Academic Details Form");
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("type", 1);
			Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
					Quolification[].class);
			List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("quolfList " + quolfList.toString());

			model.addObject("quolfList", quolfList);


		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAcademicDetails", method = RequestMethod.GET)
	public ModelAndView showAcademicDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/academicDetails");

			model.addObject("title", "Academic Details ");
			
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

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int facAcadId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				facAcadId = Integer.parseInt(request.getParameter("fac_aca_id"));
			} catch (Exception e) {
				facAcadId = 0;
			}

			System.err.println("fac_aca_id id  " + facAcadId);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = new Info();// AccessControll.checkAccess("insertAlumni", "showAlumini", "1", "0", "0",
											// "0",
			// newModuleList);
			editAccess.setError(false);

			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

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

				facAcademic.setFacultyId(Integer.parseInt(request.getParameter("fac_id")));
				facAcademic.setfClass(request.getParameter("fClass"));
				facAcademic.setfPassingYear(request.getParameter("fPassingYear"));
				facAcademic.setfQualificationId(Integer.parseInt(request.getParameter("fQualificationId")));
				facAcademic.setfUniversity(request.getParameter("fUniversity"));

				facAcademic.setIsActive(1);

				facAcademic.setMakerEnterDatetime(curDateTime);
				facAcademic.setMakerUserId(userObj.getUserId());

				FacultyAcademic facAcadeRes = restTemplate.postForObject(Constants.url + "saveFacultyAcademic",
						facAcademic, FacultyAcademic.class);

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showAcademicDetails";
				else
					redirect = "redirect:/showAddAcademicDetails";
			}

		} catch (Exception e) {
			System.err.println("Exce in save saveFacultyPhdDetails  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

}
