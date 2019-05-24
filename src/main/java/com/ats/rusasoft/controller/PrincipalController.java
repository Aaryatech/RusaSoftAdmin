package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.accessright.AssignRoleDetailList;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class PrincipalController {
	RestTemplate rest = new RestTemplate();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());
	String redirect = null;

	@RequestMapping(value = "/showRegPrincipal", method = RequestMethod.GET)
	public ModelAndView showRegPrincipal(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = null;
		
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showRegPrincipal", "showRegPrincipal", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/principalReg");

				model.addObject("title", "Principal Registration");
				map = new LinkedMultiValueMap<String, Object>();
				
				map.add("desgList", Constants.facPrincipal);
				
				Designation[] designArr = restTemplate.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();

				map.add("type", 1);
				Quolification[] quolArray = restTemplate.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);

				map = new LinkedMultiValueMap<>();
				map.add("facultyId", userObj.getStaff().getFacultyId());

				Staff editFaculty = rest.postForObject(Constants.url + "/getFacultyByFacultyIdAndIsPrinci", map,
						Staff.class);

				model.addObject("editFaculty", editFaculty);
				if (editFaculty == null) {
					model.addObject("addEdit", "0");

				} else {
					model.addObject("addEdit", "1");
				}
				try {

					List<Integer> deptIdList = Stream.of(editFaculty.getDeptId().split(",")).map(Integer::parseInt)
							.collect(Collectors.toList());

					model.addObject("deptIdList", deptIdList);
				}

				catch (Exception e) {
					// TODO: handle exception
				}

			}
		} catch (Exception e) {

			System.err.println("exception In showRegPrincipal at Principal Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertPrincipal", method = RequestMethod.POST)
	public String insertPrincipal(HttpServletRequest request, HttpServletResponse response) {

		try {

			int facultyId = 0;

			try {
				facultyId = Integer.parseInt(request.getParameter("facultyId"));
			} catch (Exception e) {
				facultyId = 0;
			}

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int instituteId = (int) session.getAttribute("instituteId");

			int userId = (int) session.getAttribute("userId");

			int isAccOff = 0, isHod = 0, isDean = 0, isLib = 0;
			try {
				isAccOff = Integer.parseInt(request.getParameter("isAccOff"));
			} catch (Exception e) {
				isAccOff = 0;
			}
			try {
				isHod = Integer.parseInt(request.getParameter("isHod"));
			} catch (Exception e) {
				isHod = 0;
			}
			try {
				isDean = Integer.parseInt(request.getParameter("isDean"));
			} catch (Exception e) {
				isDean = 0;
			}

			try {
				isLib = Integer.parseInt(request.getParameter("isLib"));
			} catch (Exception e) {
				isLib = 0;
			}
			String roleNameList = null;

			roleNameList = Constants.Princ_Role + "," + Constants.Faculty_Role;

			if (isAccOff == 1) {
				roleNameList = roleNameList + "," + Constants.Account_Officer_Role;
			}
			if (isHod == 1) {
				roleNameList = roleNameList + "," + Constants.HOD_Role;
			}
			if (isDean == 1) {
				roleNameList = roleNameList + "," + Constants.Dean_Role;
			}
			if (isLib == 1) {
				roleNameList = roleNameList + "," + Constants.Librarian_Role;

			}

			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("roleNameList", roleNameList);
			AssignRoleDetailList[] roleArray = rest.postForObject(Constants.url + "getRoleIdsByRoleNameList", map,
					AssignRoleDetailList[].class);
			List<AssignRoleDetailList> roleIdsList = new ArrayList<>(Arrays.asList(roleArray));

			String roleIds = new String();
			for (int i = 0; i < roleIdsList.size(); i++) {
				roleIds = roleIdsList.get(i).getRoleId() + "," + roleIds;
			}
			System.err.println("roleIds " + roleIds);

			int designation = 0;

			System.out.println("Data:" + facultyId);
			String facultyName = request.getParameter("facultyName");
			System.out.println("facultyName:" + facultyName);
			designation = Integer.parseInt(request.getParameter("designation"));
			String dateOfJoin = request.getParameter("dateOfJoin");
			String contact = request.getParameter("contactNo");
			String email = request.getParameter("email");

			String[] deptIds = request.getParameterValues("dept_id");
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < deptIds.length; i++) {
				sb = sb.append(deptIds[i] + ",");

			}
			String deptIdList = sb.toString();
			deptIdList = deptIdList.substring(0, deptIdList.length() - 1);
			int addEdit = Integer.parseInt(request.getParameter("addEdit"));
			if (addEdit == 0) {
				Staff staff = new Staff();

				staff.setContactNo(contact);
				staff.setCurrentDesignationId(designation);
				staff.setDeptId(deptIdList);
				staff.setEmail(email);
				staff.setFacultyFirstName(facultyName);
				staff.setFacultyId(facultyId);
				staff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
				staff.setHightestQualificationYear(null);
				staff.setIsAccOff(isAccOff);
				staff.setIsDean(isDean);
				staff.setIsFaculty(1);
				staff.setIsHod(isHod);
				staff.setIsIqac(0);
				staff.setIsLibrarian(isLib);
				staff.setIsPrincipal(1);

				staff.setIsStudent(0);
				staff.setIsWorking(1);
				staff.setJoiningDate(dateOfJoin);
				staff.setLastUpdatedDatetime(curDateTime);
				staff.setMakerEnterDatetime(curDateTime);

				staff.setPassword("");
				staff.setRealivingDate(null);
				staff.setRoleIds(roleIds);
				staff.setTeachingTo(0);
				staff.setType(1);

				staff.setInstituteId(instituteId);
				staff.setJoiningDate(dateOfJoin);
				staff.setContactNo(contact);
				staff.setEmail(email);
				staff.setDelStatus(1);
				staff.setIsActive(1);
				staff.setMakerUserId(userId);
				staff.setMakerEnterDatetime(curDateTime);
				staff.setCheckerUserId(0);
				staff.setCheckerDatetime(curDateTime);
				staff.setLastUpdatedDatetime(curDateTime);

				staff.setExtravarchar1("NA");
				Staff hod = rest.postForObject(Constants.url + "/addNewStaff", staff, Staff.class);

			} else {

				map = new LinkedMultiValueMap<>();
				map.add("id", facultyId);

				Staff editHod = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				editHod.setFacultyFirstName(facultyName);
				editHod.setDeptId(deptIdList);
				editHod.setEmail(email);
				editHod.setFacultyId(facultyId);
				editHod.setContactNo(contact);
				editHod.setCurrentDesignationId(designation);
				editHod.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
				editHod.setJoiningDate(dateOfJoin);
				editHod.setIsFaculty(1);
				editHod.setIsHod(isHod);
				editHod.setIsIqac(0);
				editHod.setIsLibrarian(isLib);
				editHod.setIsPrincipal(1);
				editHod.setIsDean(isDean);
				editHod.setType(1);

				Staff hod = rest.postForObject(Constants.url + "/addNewStaff", editHod, Staff.class);

			}

			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/showRegPrincipal";
			else
				redirect = "redirect:/showRegPrincipal";
		} catch (Exception e) {

			System.err.println("exception In showRegPrincipal at Principal Contr" + e.getMessage());
			e.printStackTrace();

		}
		return redirect;

	}

	// getUserInfoByContcAndEmail

	@RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
	public @ResponseBody Staff getUserInfo(HttpServletRequest request, HttpServletResponse response) {

		Staff staff = new Staff();

		try {
			HttpSession session = request.getSession();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String inputValue = request.getParameter("inputValue");
			int valueType = Integer.parseInt(request.getParameter("valueType"));
			int instituteId = (int) session.getAttribute("instituteId");

			System.out.println("Values:" + inputValue + " " + valueType + " ");

			map.add("inputValue", inputValue);
			map.add("checkValue", valueType);
			map.add("instId", instituteId);

			staff = rest.postForObject(Constants.url + "getUserInfoByContcAndEmail", map, Staff.class);

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return staff;

	}

}
