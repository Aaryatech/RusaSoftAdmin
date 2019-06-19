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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.faculty.model.Journal;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.GetHod;
import com.ats.rusasoft.model.GetInstituteList;
import com.ats.rusasoft.model.Hod;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.MIqac;
import com.ats.rusasoft.model.NewDeanList;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.accessright.AssignRoleDetailList;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class MastersController {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());
	String redirect = null;
	RestTemplate rest = new RestTemplate();

	/*
	 * @RequestMapping(value = "/hodRegistration", method = RequestMethod.GET)
	 * public ModelAndView hodRegistration(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * ModelAndView model = new ModelAndView("master/hodRegistration"); try {
	 * 
	 * RestTemplate restTemplate = new RestTemplate(); MultiValueMap<String, Object>
	 * map = new LinkedMultiValueMap<String, Object>(); HttpSession session =
	 * request.getSession();
	 * 
	 * LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
	 * map.add("instId", userObj.getGetData().getUserInstituteId()); Dept[]
	 * instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map,
	 * Dept[].class); List<Dept> deptList = new
	 * ArrayList<>(Arrays.asList(instArray)); System.err.println("deptList " +
	 * deptList.toString());
	 * 
	 * model.addObject("deptList", deptList);
	 * 
	 * map = new LinkedMultiValueMap<String, Object>();
	 * 
	 * map.add("type", 1); Quolification[] quolArray =
	 * restTemplate.postForObject(Constants.url + "getQuolificationList", map,
	 * Quolification[].class); List<Quolification> quolfList = new
	 * ArrayList<>(Arrays.asList(quolArray)); System.err.println("quolfList " +
	 * quolfList.toString());
	 * 
	 * model.addObject("title", "HOD Registration");
	 * 
	 * model.addObject("quolfList", quolfList); Hod hod = new Hod();
	 * model.addObject("hod", hod);
	 * 
	 * } catch (Exception e) {
	 * 
	 * System.err.println("exception In hodRegistration at Master Contr" +
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
	@RequestMapping(value = "/hodRegistration", method = RequestMethod.GET)
	public ModelAndView hodRegistration(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("hodRegistration", "hodList", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/hodRegistration");
				model.addObject("title", "HOD Registration");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("desgList", Constants.facHOD);
				Designation[] designArr = restTemplate.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map = new LinkedMultiValueMap<String, Object>();

				map.add("type", 1);
				Quolification[] quolArray = restTemplate.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);
				model.addObject("addEdit", "0");

				Staff editHod = new Staff();
				model.addObject("editHod", editHod);

				map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = restTemplate.postForObject(Constants.url + "getDeptForHodReg", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

			}
		} catch (Exception e) {

			System.err.println("exception In hodRegistration at Masters Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertHod", method = RequestMethod.POST)
	public String insertHod(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int instituteId = (int) session.getAttribute("instituteId");

			int userId = (int) session.getAttribute("userId");

			int isDean = 0;

			try {
				isDean = Integer.parseInt(request.getParameter("isDean"));
			} catch (Exception e) {
				isDean = 0;
			}

			String roleNameList = null;

			roleNameList = Constants.HOD_Role + "," + Constants.Faculty_Role;

			if (isDean == 1) {
				roleNameList = roleNameList + "," + Constants.Dean_Role;
			}

			int hodId = 0;
			try {
				hodId = Integer.parseInt(request.getParameter("hod_id"));
			} catch (Exception e) {
				hodId = 0;
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

			//System.out.println("Data:" + hodId);
			String hodName = request.getParameter("hodName");
			//System.out.println("Data:" + hodName);
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
				staff.setFacultyFirstName(hodName);
				staff.setFacultyId(hodId);
				staff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
				staff.setIsSame(Integer.parseInt(request.getParameter("is_state_same"))); // check state whether current
																							// or not
				staff.setHightestQualificationYear(null);
				staff.setIsAccOff(0);
				staff.setIsDean(isDean);
				staff.setIsFaculty(1);
				staff.setIsHod(1);
				staff.setIsIqac(0);
				staff.setIsLibrarian(0);
				staff.setIsPrincipal(0);

				staff.setIsStudent(0);
				staff.setIsWorking(1);
				staff.setJoiningDate(dateOfJoin);
				staff.setLastUpdatedDatetime(curDateTime);
				staff.setMakerEnterDatetime(curDateTime);

				staff.setPassword("");
				staff.setRealivingDate(null);
				staff.setRoleIds(roleIds);
				staff.setTeachingTo(0);
				staff.setType(3);

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
				map.add("id", hodId);

				Staff editHod = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				editHod.setFacultyFirstName(hodName);
				editHod.setDeptId(deptIdList);
				editHod.setEmail(email);
				editHod.setFacultyId(hodId);
				editHod.setContactNo(contact);
				editHod.setCurrentDesignationId(designation);
				editHod.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
				editHod.setIsSame(Integer.parseInt(request.getParameter("is_state_same"))); // check state whether
																							// current or not
				editHod.setJoiningDate(dateOfJoin);
				editHod.setIsHod(1);
				editHod.setIsDean(1);
				editHod.setType(3);

				Staff hod = rest.postForObject(Constants.url + "/addNewStaff", editHod, Staff.class);

			}

			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/hodList";
			else
				redirect = "redirect:/hodRegistration";
		} catch (Exception e) {

			System.err.println("exception In hodRegistration at Masters Contr" + e.getMessage());
			e.printStackTrace();

		}
		return redirect;

	}

	@RequestMapping(value = "/hodList", method = RequestMethod.GET)
	public ModelAndView hodList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		try {

			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("hodList", "hodList", "1", "0", "0", "0", newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("master/hodList");
				model.addObject("title", "HOD Registration");

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				NewDeanList[] hodArray = restTemplate.postForObject(Constants.url + "getNewHodList", map,
						NewDeanList[].class);
				List<NewDeanList> hodList = new ArrayList<>(Arrays.asList(hodArray));
				// System.err.println("hodList " + hodList.toString());

				model.addObject("hodList", hodList);
				model.addObject("listMapping", "hodList");

				Info addAccess = AccessControll.checkAccess("hodList", "hodList", "0", "1", "0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("hodList", "hodList", "0", "0", "1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("hodList", "hodList", "0", "0", "0", "1", newModuleList);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)

					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

			} else {
				model = new ModelAndView("accessDenied");

			}
		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showEditHod/{facultyId}", method = RequestMethod.GET)
	public ModelAndView showEditHod(@PathVariable("facultyId") int facultyId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		MultiValueMap<String, Object> map = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("hodRegistration", "hodList", "0", "0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/hodRegistration");
				model.addObject("title", "Edit HOD Registration");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("desgList", Constants.facHOD);
				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);

				map = new LinkedMultiValueMap<>();
				map.add("id", facultyId);

				Staff editHod = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				//System.out.println("deptIdList 1:" + editHod.getDeptId());

				model.addObject("editHod", editHod);
				model.addObject("addEdit", "1");
				System.err.println("deptIdList before " + editHod.getDeptId());
				List<Integer> deptIdList = Stream.of(editHod.getDeptId().split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());
				System.err.println("deptIdList after " + deptIdList.toString());
				model.addObject("deptIdList", deptIdList);

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", userObj.getGetData().getUserInstituteId());
				map.add("deptIdList", editHod.getDeptId());
				Dept[] instArray = rest.postForObject(Constants.url + "getDeptForHodRegForEdit", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));

				model.addObject("deptList", deptList);

			}
		} catch (Exception e) {

			System.err.println("exception In showEditHod at Mastr Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteHod/{facultyId}", method = RequestMethod.GET)
	public String deleteHod(HttpServletRequest request, HttpServletResponse response, @PathVariable int facultyId) {
		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("deleteHod/{hodId}", "hodList", "0", "0", "0", "1",
					newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (facultyId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("hodIds");
					//System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String hodIdList = sb.toString();
					hodIdList = hodIdList.substring(0, hodIdList.length() - 1);

					map.add("staffIdList", hodIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("staffIdList", facultyId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteStaffSlected", map, Info.class);

				redirect = "redirect:/hodList";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteHod at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect;

	}

	@RequestMapping(value = "/addFaculty", method = RequestMethod.GET)
	public ModelAndView addFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addFaculty");
		model.addObject("title", "Add Department");
		try {

			Dept dept = new Dept();

			model.addObject("dept", dept);

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showDeptList", method = RequestMethod.GET)
	public ModelAndView showDeptList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/deptList");
		model.addObject("title", "Department List");
		try {
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showDeptList", "showDeptList", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {

				Info addAccess = AccessControll.checkAccess("showDeptList", "showDeptList", "0", "1", "0", "0",
						newModuleList);

				Info editAccess = AccessControll.checkAccess("showDeptList", "showDeptList", "0", "0", "1", "0",
						newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showDeptList", "showDeptList", "0", "0", "0", "1",
						newModuleList);

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				model.addObject("deptList", deptList);

				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addPrincipal", method = RequestMethod.GET)
	public ModelAndView addPrincipal(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addPrincipal");
		model.addObject("title", "Add Principal");
		try {

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPrincipalList", method = RequestMethod.GET)
	public ModelAndView showPrincipalList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/principalList");
		model.addObject("title", "Principal List");
		try {

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showPendingInstitute", method = RequestMethod.GET)
	public ModelAndView showPendingInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/pendingInstituteList");

			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showPendingInstitute", "showPendingInstitute", "1", "0", "0",
					"0", newModuleList);

			if (viewAccess.isError() == false) {

				Info addAccess = AccessControll.checkAccess("showPendingInstitute", "showPendingInstitute", "0", "1",
						"0", "0", newModuleList);

				Info delete = AccessControll.checkAccess("showPendingInstitute", "showPendingInstitute", "0", "0", "0",
						"1", newModuleList);

				GetInstituteList[] instArray = restTemplate.getForObject(Constants.url + "getAllPendingInstitutes",
						GetInstituteList[].class);
				List<GetInstituteList> instList = new ArrayList<>(Arrays.asList(instArray));

				model.addObject("pendInstList", instList);

				model.addObject("title", "Institute Verification Pending List");

				if (addAccess.isError() == false) {
					model.addObject("addAccess", 0);

				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}

			}

			else {

				model = new ModelAndView("accessDenied");
			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showApprovedInstitute", method = RequestMethod.GET)
	public ModelAndView showApprovedInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/approvedInstituteList");

			model.addObject("title", " Pending Institute");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

}
