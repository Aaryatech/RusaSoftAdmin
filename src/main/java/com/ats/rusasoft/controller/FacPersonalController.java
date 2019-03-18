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

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.faculty.model.FacultyPersonalDetail;
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

	@RequestMapping(value = "/showFacultyDetails", method = RequestMethod.GET)
	public ModelAndView showFacultyDetails1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/facultyDetails");

			model.addObject("title", "Faculty Details Form");

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

			model.addObject("staff", staff);

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	//insertFacPersonalDetail
	
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
					facPersDetail.setfDob(request.getParameter("f_dob"));
					facPersDetail.setfPastExp(Float.parseFloat(request.getParameter("f_prevExp")));
					facPersDetail.setfPhone(request.getParameter("f_phone"));
					facPersDetail.setfResident(request.getParameter("f_resident"));
					facPersDetail.setIsAddSame(Integer.parseInt(request.getParameter("is_add_same")));
					facPersDetail.setMakerPersDatetime(curDateTime);
					facPersDetail.setMakerPersUserId(userObj.getUserId());


					FacultyPersonalDetail facPerDetail = restTemplate.postForObject(Constants.url + "saveFacultyPersonalDetail", facPersDetail,
							FacultyPersonalDetail.class);


				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showAlumini";
				else
					redirect = "redirect:/showAddAlumini";
			}

		} catch (Exception e) {
			System.err.println("Exce in save Alumni  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

}
