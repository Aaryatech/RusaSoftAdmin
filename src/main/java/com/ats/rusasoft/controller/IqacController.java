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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.XssEscapeUtils;
import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.commons.ExportToExcel;
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.Dean;
import com.ats.rusasoft.model.DeansList;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.IqacList;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.MIqac;
import com.ats.rusasoft.model.NewDeanList;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.StaffList;
import com.ats.rusasoft.model.UserLogin;
import com.ats.rusasoft.model.accessright.AssignRoleDetailList;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class IqacController {

	RestTemplate rest = new RestTemplate();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());
	String redirect = null;

	@RequestMapping(value = "/chkFields", method = RequestMethod.GET)
	public @ResponseBody Info checkUniqueField(HttpServletRequest request, HttpServletResponse response) {

		Info info = new Info();

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String inputValue = request.getParameter("inputValue");
			int valueType = Integer.parseInt(request.getParameter("valueType"));
			int primaryKey = Integer.parseInt(request.getParameter("primaryKey"));
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			int tableId = Integer.parseInt(request.getParameter("tableId"));

			// System.out.println(
			// "Values:" + inputValue + " " + valueType + " " + primaryKey + " " + isEdit +
			// " " + tableId);

			map.add("inputValue", inputValue);
			map.add("valueType", valueType);
			map.add("tableId", tableId);
			map.add("isEditCall", isEdit);
			map.add("primaryKey", primaryKey);

			info = rest.postForObject(Constants.url + "chkUniqueValues", map, Info.class);
			System.err.println("Info Response  " + info.toString());

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return info;

	}

	@RequestMapping(value = "/iqacRegistration", method = RequestMethod.GET)
	public ModelAndView showRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		MultiValueMap<String, Object> map = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("iqacRegistration", "showIqacList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/iqacRegistration");
				Staff miqac = new Staff();
				model.addObject("miqc", miqac);

				/*
				 * Designation[] designArr = rest.getForObject(Constants.url +
				 * "/getAllDesignations", Designation[].class); List<Designation>
				 * designationList = new ArrayList<>(Arrays.asList(designArr));
				 * model.addObject("desigList", designationList);
				 */

				map = new LinkedMultiValueMap<String, Object>();
				map.add("desgList", Constants.facIQAC);
				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				model.addObject("title", "IQAC Registration");

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("addEdit", "0");
				model.addObject("quolfList", quolfList);

			}
		} catch (Exception e) {

			System.err.println("exception In iqacRegistration at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/iqacNewRegistration", method = RequestMethod.POST)
	public String newRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String token = request.getParameter("token");
		String key = (String) session.getAttribute("generatedKey");

		if (token.trim().equals(key.trim())) {

			try {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int instituteId = (int) session.getAttribute("instituteId");

				int userId = (int) session.getAttribute("userId");

				// //System.out.println("IdSS:" + userObj.getUserId()+" "+userId+" / "+"
				// "+instituteId+" "+userObj.getGetData().getUserInstituteId());
				int iqacId = Integer.parseInt(request.getParameter("iqac_id"));

				int isAccOff = 0, isHod = 0, isDean = 0, isStaff = 0, isLib = 0;
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
					isStaff = Integer.parseInt(request.getParameter("isStaff"));
				} catch (Exception e) {
					isStaff = 0;
				}
				try {
					isLib = Integer.parseInt(request.getParameter("isLib"));
				} catch (Exception e) {
					isLib = 0;
				}
				String roleNameList = null;

				roleNameList = Constants.IQAC_Role + "," + Constants.Faculty_Role;

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

				/*
				 * System.err.println("isAccOff" + isAccOff); System.err.println("isHod" +
				 * isHod); System.err.println("isDean" + isDean); System.err.println("isLib" +
				 * isLib);
				 */

				// write web service to get Role Ids..
				// dvd

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

				// System.out.println("Data:" + iqacId);
				String iqacName = request.getParameter("iqacName");
				// System.out.println("Data:" + iqacName);
				designation = Integer.parseInt(request.getParameter("designation"));
				String dateOfJoin = request.getParameter("dateOfJoin");
				String contact = request.getParameter("contactNo");
				String email = request.getParameter("email");
				int isState = Integer.parseInt(request.getParameter("is_state_same"));

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

					staff.setContactNo(XssEscapeUtils.jsoupParse(contact));
					staff.setCurrentDesignationId(designation);
					staff.setDeptId(deptIdList);
					staff.setEmail(XssEscapeUtils.jsoupParse(email));
					staff.setFacultyFirstName(XssEscapeUtils.jsoupParse(iqacName));
					staff.setFacultyId(iqacId);
					staff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
					staff.setHightestQualificationYear(null);
					staff.setIsAccOff(isAccOff);
					staff.setIsDean(isDean);
					staff.setIsFaculty(1);
					staff.setIsHod(isHod);
					staff.setIsIqac(1);
					staff.setIsLibrarian(isLib);
					staff.setIsPrincipal(0);

					staff.setIsStudent(0);
					staff.setIsWorking(1);
					staff.setJoiningDate(XssEscapeUtils.jsoupParse(dateOfJoin));
					staff.setLastUpdatedDatetime(curDateTime);
					staff.setMakerEnterDatetime(curDateTime);

					staff.setPassword("");
					staff.setRealivingDate(null);
					staff.setRoleIds(roleIds);
					staff.setTeachingTo(0);
					staff.setType(2);

					staff.setInstituteId(instituteId);

					staff.setIsSame(isState);
					if (isState == 1) {
						staff.setFacultyMiddelName("21"); // inserted state id
					} else {
						staff.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}
					staff.setDelStatus(1);
					staff.setIsActive(1);
					staff.setMakerUserId(userId);
					staff.setCheckerUserId(0);
					staff.setCheckerDatetime(curDateTime);

					staff.setExtravarchar1("NA");

					Staff addIqac = rest.postForObject(Constants.url + "/addNewStaff", staff, Staff.class);
				} else {
					map = new LinkedMultiValueMap<>();
					map.add("id", iqacId);

					Staff editIqac = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
					editIqac.setFacultyFirstName(XssEscapeUtils.jsoupParse(iqacName));
					editIqac.setDeptId(deptIdList);
					editIqac.setEmail(XssEscapeUtils.jsoupParse(email));
					editIqac.setFacultyId(iqacId);
					editIqac.setContactNo(XssEscapeUtils.jsoupParse(contact));
					editIqac.setCurrentDesignationId(designation);
					editIqac.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
					editIqac.setIsSame(isState);
					if (isState == 1) {
						editIqac.setFacultyMiddelName("21"); // inserted state id
					} else {
						editIqac.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}
					editIqac.setJoiningDate(XssEscapeUtils.jsoupParse(dateOfJoin));
					editIqac.setIsAccOff(isAccOff);
					editIqac.setIsDean(isDean);
					editIqac.setIsHod(isHod);
					editIqac.setIsLibrarian(isLib);
					editIqac.setRoleIds(roleIds);
					editIqac.setMakerUserId(userId);
					editIqac.setMakerEnterDatetime(curDateTime);
					editIqac.setCheckerUserId(0);
					editIqac.setCheckerDatetime(curDateTime);
					editIqac.setLastUpdatedDatetime(curDateTime);
					editIqac.setType(2);
					Staff editIqc = rest.postForObject(Constants.url + "/addNewStaff", editIqac, Staff.class);

				}
				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showIqacList";

			} catch (Exception e) {

				System.err.println("exception In iqacNewRegistration at showIqacList Contr" + e.getMessage());
				e.printStackTrace();

			}

		} else {
			System.err.println("in else");
			redirect = "redirect:/accessDenied";
		}

		return redirect;

	}

	@RequestMapping(value = "/showIqacList", method = RequestMethod.GET)
	public ModelAndView showIqacList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/iqacList");
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showIqacList", "showIqacList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				/*
				 * int userId = (int) session.getAttribute("userId"); int instituteId = (int)
				 * session.getAttribute("instituteId");
				 */

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				// System.out.println("Princi" + userObj.getStaff().getIsPrincipal());
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("instituteId", userObj.getStaff().getInstituteId());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());

				IqacList[] iqacArr = rest.postForObject(Constants.url + "/getAllIqac", map, IqacList[].class);
				List<IqacList> qacList = new ArrayList<>(Arrays.asList(iqacArr));
				// //System.out.println("IQACLIST" + userObj.getStaff().getIsPrincipal());

				model.addObject("QList", qacList);
				model.addObject("title", "IQAC Registration");

				model.addObject("listMapping", "showIqacList");

				Info add = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					// //System.out.println(" add Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					// //System.out.println(" edit Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					// //System.out.println(" delete Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}
		} catch (Exception e) {

			System.err.println("exception In showIqacList at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/editIqac/{iqacId}", method = RequestMethod.GET)
	public ModelAndView editIqac(@PathVariable("iqacId") int iqacId, HttpServletRequest request) {

		// //System.out.println("Id:" + iqacId);
		MultiValueMap<String, Object> map = null;
		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("editIqac/{iqacId}", "showIqacList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/iqacRegistration");

				/*
				 * Designation[] designArr = rest.getForObject(Constants.url +
				 * "/getAllDesignations", Designation[].class); List<Designation>
				 * designationList = new ArrayList<>(Arrays.asList(designArr));
				 * model.addObject("desigList", designationList);
				 */

				map = new LinkedMultiValueMap<String, Object>();
				map.add("desgList", Constants.facIQAC);
				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				model.addObject("quolfList", quolfList);

				map = new LinkedMultiValueMap<>();
				map.add("id", iqacId);
				Staff editIqac = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);

				model.addObject("miqc", editIqac);
				model.addObject("addEdit", "1");
				model.addObject("title", "Edit IQAC Registration");
			}
		} catch (Exception e) {

			System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteIqac/{iqacId}", method = RequestMethod.GET)
	public String deleteIqac(@PathVariable("iqacId") int iqacId, HttpServletRequest request) {

		String a = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteIqac/{iqacId}", "showIqacList", "0", "0", "0", "1",
					newModuleList);
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			} else {
				Info inf = new Info();
				// //System.out.println("Id:" + iqacId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("id", iqacId);
				Info iqac = rest.postForObject(Constants.url + "/deleteStaffById", map, Info.class);
				a = "redirect:/showIqacList";
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return a;

	}

	@RequestMapping(value = "/editIqac", method = RequestMethod.GET)
	public ModelAndView editIqacorChangeIqac(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editIqac", "editIqac", "1", "0", "0", "0", newModuleList);

			if (view.isError() == false) {

				model = new ModelAndView("master/iqacChange");
				MIqac miqac = new MIqac();
				model.addObject("miqac", miqac);

				Designation[] designArr = rest.getForObject(Constants.url + "/getAllDesignations", Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				int instituteId = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				MIqac miqc = rest.postForObject(Constants.url + "/getIqacbyInstituteId", map, MIqac.class);
				model.addObject("miqc", miqc);
				model.addObject("editIqac", 1);

			} else {
				model = new ModelAndView("accessDenied");
			}
		} catch (Exception e) {

			System.err.println("exception In iqacRegistration at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/changeIqac", method = RequestMethod.GET)
	public ModelAndView changeIqac(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		try {

			model = new ModelAndView("master/iqacChange");
			MIqac miqac = new MIqac();
			model.addObject("miqac", miqac);

			Designation[] designArr = rest.getForObject(Constants.url + "/getAllDesignations", Designation[].class);
			List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
			model.addObject("desigList", designationList);

		} catch (Exception e) {

			System.err.println("exception In iqacRegistration at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/submitChangeIqacorEditIqac", method = RequestMethod.POST)
	public String submitChangeIqacorEditIqac(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();

		try {
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");

			int iqacId = 0;
			int designation = 0;
			try {

				iqacId = Integer.parseInt(request.getParameter("iqac_id"));

			} catch (Exception e) {
				iqacId = 0;
				System.err.println("exception In iqacNewRegistration at showIqacList Contr" + e.getMessage());
				e.printStackTrace();

			}

			String iqacName = request.getParameter("iqacName");
			designation = Integer.parseInt(request.getParameter("designation"));
			String dateOfJoin = request.getParameter("dateOfJoin");
			String contact = request.getParameter("contactNo");
			String email = request.getParameter("email");

			// System.out.println("Data:" + iqacId + " " + iqacName + " " + dateOfJoin + " "
			// + contact + " " + email);
			MIqac miqac = new MIqac();
			if (iqacId == 0) {
				miqac.setIqacId(0);

			} else {
				miqac.setIqacId(iqacId);
			}

			miqac.setIqacName(XssEscapeUtils.jsoupParse(iqacName));
			miqac.setDesgntnId(designation);
			miqac.setInstituteId(instituteId);
			miqac.setJoiningDate(XssEscapeUtils.jsoupParse(dateOfJoin));
			miqac.setContactNo(XssEscapeUtils.jsoupParse(contact));
			miqac.setEmail(XssEscapeUtils.jsoupParse(email));
			miqac.setDelStatus(1);
			miqac.setIsActive(1);
			miqac.setIsEnrollSystem(1);
			miqac.setMakerUserId(userId);
			miqac.setMakerEnterDatetime(curDateTime);
			miqac.setCheckerUserId(0);
			miqac.setCheckerDatetime(curDateTime);
			miqac.setLastUpdatedDatetime(curDateTime);
			miqac.setType(2);

			if (iqacId == 0) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("userType", 2);
				Info info = rest.postForObject(Constants.url + "/blockPreviousIqacRecord", map, Info.class);

				try {

					map = new LinkedMultiValueMap<>();
					map.add("instituteId", instituteId);
					MIqac miqc1 = rest.postForObject(Constants.url + "/getIqacbyInstituteId", map, MIqac.class);
					miqc1.setIsActive(0);
					MIqac res = rest.postForObject(Constants.url + "/insertNewIqac", miqc1, MIqac.class);

				} catch (Exception e) {

				}

				MIqac iqac = rest.postForObject(Constants.url + "/insertNewIqac", miqac, MIqac.class);

			} else {

				MIqac iqac = rest.postForObject(Constants.url + "/insertNewIqac", miqac, MIqac.class);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return "redirect:/editIqac";

	}

	/**********************************************
	 * Staff/Faculty
	 **********************************************/

	@RequestMapping(value = "/showRegisterStaff", method = RequestMethod.GET)
	public ModelAndView showRegisterStaff(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		MultiValueMap<String, Object> map = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		String a = null;
		try {
			Info view = AccessControll.checkAccess("showRegisterStaff", "showStaffList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			} else {
				model = new ModelAndView("master/regstaff");

				map = new LinkedMultiValueMap<>();

				// HttpSession session = request.getSession();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("deptIds", userObj.getStaff().getDeptId());
				Dept[] instArray = rest.postForObject(Constants.url + "getDeptListDeptIdIn", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<>();

				map.add("desgList", Constants.facFaculty);

				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map = new LinkedMultiValueMap<>();
				map.add("type", 1);

				Quolification[] quolArray = rest.postForObject(Constants.url + "/getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());
				model.addObject("quolfList", quolfList);

				map.add("type0", 0);
				Quolification[] teachTo = rest.postForObject(Constants.url + "/getQuolificationListToTeach", map,
						Quolification[].class);
				List<Quolification> teachingList = new ArrayList<>(Arrays.asList(teachTo));
				System.err.println("teachList " + teachingList);
				model.addObject("teachingList", teachingList);

				Staff staff = new Staff();
				model.addObject("staff", staff);

				model.addObject("addEdit", "0");
				model.addObject("title", "Register Faculty");
			}
		} catch (Exception e) {

			System.err.println("exception In showHodAfterLogin at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
	public String addFaculty(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				ModelAndView model = new ModelAndView("master/addFaculty");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String curDateTime = dateFormat.format(cal.getTime());

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				// model.addObject("title", "Add Department");

				int facId = Integer.parseInt(request.getParameter("faculty_id"));
				int highestQualification = Integer.parseInt(request.getParameter("hod_quolf"));

				String yrofHighestQualification = request.getParameter("yr_highest_qualification_acqrd");
				int designation = Integer.parseInt(request.getParameter("designation"));
				String joinDate = request.getParameter("join_date");
				int isReg = Integer.parseInt(request.getParameter("is_registration"));
				int isState = Integer.parseInt(request.getParameter("is_state_same"));
				int teachTo = Integer.parseInt(request.getParameter("teachTo"));
				String contactNo = request.getParameter("contact_no");
				String email = request.getParameter("email");
				String facName = request.getParameter("faculty_first_name");

				int isAccOff = 0, isHod = 0, isDean = 0, isStaff = 0, isLib = 0;
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
					isStaff = Integer.parseInt(request.getParameter("isStaff"));
				} catch (Exception e) {
					isStaff = 0;
				}
				try {
					isLib = Integer.parseInt(request.getParameter("isLib"));
				} catch (Exception e) {
					isLib = 0;
				}
				String roleNameList = null;

				roleNameList = Constants.Faculty_Role;

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

				/*
				 * System.err.println("isAccOff" + isAccOff); System.err.println("isHod" +
				 * isHod); System.err.println("isDean" + isDean); System.err.println("isLib" +
				 * isLib);
				 */

				// write web service to get Role Ids..
				// dvd

				map.add("roleNameList", roleNameList);
				AssignRoleDetailList[] roleArray = rest.postForObject(Constants.url + "getRoleIdsByRoleNameList", map,
						AssignRoleDetailList[].class);
				List<AssignRoleDetailList> roleIdsList = new ArrayList<>(Arrays.asList(roleArray));

				String roleIds = new String();
				for (int i = 0; i < roleIdsList.size(); i++) {
					roleIds = roleIdsList.get(i).getRoleId() + "," + roleIds;
				}

				String[] deptIds = request.getParameterValues("dept");
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < deptIds.length; i++) {
					sb = sb.append(deptIds[i] + ",");

				}

				String deptIdList = sb.toString();
				deptIdList = deptIdList.substring(0, deptIdList.length() - 1);
				int addEdit = Integer.parseInt(request.getParameter("addEdit"));

				if (addEdit == 0) {
					Staff staff = new Staff();

					staff.setFacultyId(Integer.parseInt(request.getParameter("faculty_id")));

					staff.setContactNo(XssEscapeUtils.jsoupParse(contactNo));
					staff.setCurrentDesignationId(designation);
					staff.setDeptId(deptIdList);
					staff.setEmail(XssEscapeUtils.jsoupParse(email));
					staff.setFacultyFirstName(XssEscapeUtils.jsoupParse(facName));
					staff.setHighestQualification(highestQualification);
					staff.setHightestQualificationYear(yrofHighestQualification);
					staff.setIsSame(isState);
					if (isState == 1) {
						staff.setFacultyMiddelName("21"); // inserted state id
					} else {
						staff.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}
					staff.setIsAccOff(isAccOff);
					staff.setIsDean(isDean);
					staff.setIsFaculty(1);
					staff.setIsHod(isHod);
					staff.setIsIqac(0);
					staff.setIsLibrarian(0);
					staff.setIsPrincipal(0);

					staff.setIsStudent(0);
					staff.setIsWorking(1);
					staff.setJoiningDate(XssEscapeUtils.jsoupParse(joinDate));
					staff.setLastUpdatedDatetime(curDateTime);
					staff.setMakerEnterDatetime(curDateTime);

					staff.setPassword("");
					staff.setIsWorking(isReg);
					staff.setRoleIds(roleIds);
					staff.setTeachingTo(0);
					staff.setType(4);

					staff.setInstituteId(instituteId);
					staff.setDelStatus(1);
					staff.setIsActive(1);
					staff.setMakerUserId(userId);
					staff.setMakerEnterDatetime(curDateTime);
					staff.setCheckerUserId(0);
					staff.setCheckerDatetime(curDateTime);
					staff.setLastUpdatedDatetime(curDateTime);

					staff.setExtravarchar1("NA");
					if (isReg == 0) {
						String realivingDate = request.getParameter("acc_off_relDate");
						staff.setRealivingDate(XssEscapeUtils.jsoupParse(realivingDate));

					} else {
						staff.setRealivingDate(null);
					}

					staff.setTeachingTo(teachTo);
					staff.setContactNo(XssEscapeUtils.jsoupParse(contactNo));
					staff.setEmail(XssEscapeUtils.jsoupParse(email));
					staff.setEditUserId(0);
					staff.setType(4);
					staff.setRoleIds(roleIds);
					staff.setIsFaculty(1);
					staff.setExtraint1(0);
					staff.setExtravarchar1("NA");

					// System.out.println("Staff:" + staff.toString());

					Staff stf = rest.postForObject(Constants.url + "/addNewStaff", staff, Staff.class);
				} else {
					map = new LinkedMultiValueMap<>();
					map.add("id", facId);

					Staff editStaff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);

					String editFacName = request.getParameter("faculty_first_name");
					editStaff.setFacultyFirstName(XssEscapeUtils.jsoupParse(editFacName));
					editStaff.setDeptId(deptIdList);
					editStaff.setEmail(XssEscapeUtils.jsoupParse(email));
					editStaff.setFacultyId(facId);
					editStaff.setContactNo(XssEscapeUtils.jsoupParse(contactNo));
					editStaff.setCurrentDesignationId(designation);
					editStaff.setHightestQualificationYear(yrofHighestQualification);
					editStaff.setHighestQualification(Integer.parseInt(request.getParameter("hod_quolf")));
					editStaff.setIsSame(isState);
					if (isState == 1) {
						editStaff.setFacultyMiddelName("21"); // inserted state id
					} else {
						editStaff.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}
					editStaff.setJoiningDate(XssEscapeUtils.jsoupParse(joinDate));
					String realivingDate = request.getParameter("acc_off_relDate");
					editStaff.setRealivingDate(XssEscapeUtils.jsoupParse(realivingDate));
					editStaff.setMakerUserId(userId);
					editStaff.setMakerEnterDatetime(curDateTime);
					editStaff.setEditUserId(0);
					editStaff.setLastUpdatedDatetime(curDateTime);
					editStaff.setCheckerUserId(0);
					editStaff.setCheckerDatetime(curDateTime);
					editStaff.setType(4);
					// editStaff.setRoleIds(roleIds);
					editStaff.setIsFaculty(1);
					editStaff.setExtraint1(0);
					editStaff.setExtravarchar1("NA");

					Staff hod = rest.postForObject(Constants.url + "/addNewStaff", editStaff, Staff.class);

				}
				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showStaffList";
				else
					redirect = "redirect:/showRegisterStaff";

			} else {
				redirect = "redirect:/accessDenied";
			}

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());
			e.printStackTrace();

		}
		return redirect;

	}

	@RequestMapping(value = "/showStaffList", method = RequestMethod.GET)
	public ModelAndView showStaffList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showStaffList", "showStaffList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/staffList");

				model.addObject("title", "Faculty Registration");
				// int facId = (int) session.getAttribute("instituteId");
				int user = userObj.getUserId();
				System.err.println("User=" + user);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				LoginResponse facId = (LoginResponse) session.getAttribute("userObj");
				// System.out.println("User="+userObj.getStaff().getIsHod());
				int yId = (int) session.getAttribute("acYearId");
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isFaculty", userObj.getStaff().getIsFaculty());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				map.add("user", user);

				// map.add("facId", facId);

				StaffList[] staff = rest.postForObject(Constants.url + "/getListStaff", map, StaffList[].class);
				List<StaffList> staffList = new ArrayList<>(Arrays.asList(staff));
				// System.out.println("Staff List:" + staffList);

				model.addObject("staffList", staffList);
				model.addObject("listMapping", "showStaffList");

				List<ExportToExcel> exportToExcelList = new ArrayList<ExportToExcel>();

				ExportToExcel expoExcel = new ExportToExcel();
				List<String> rowData = new ArrayList<String>();

				rowData.add("Sr. No");
				rowData.add("Faculty Name");
				rowData.add("Qualification");
				rowData.add("Department");
				rowData.add("Joining Date");
				rowData.add("Contact No");
				rowData.add("Email");

				expoExcel.setRowData(rowData);
				exportToExcelList.add(expoExcel);
				int cnt = 1;
				for (int i = 0; i < staffList.size(); i++) {
					expoExcel = new ExportToExcel();
					rowData = new ArrayList<String>();
					cnt = cnt + i;

					rowData.add("" + (i + 1));

					rowData.add("" + staffList.get(i).getFacultyFirstName());
					rowData.add("" + staffList.get(i).getQualificationName());
					rowData.add("" + staffList.get(i).getDeptName());
					rowData.add("" + staffList.get(i).getJoiningDate());
					rowData.add("" + staffList.get(i).getContactNo());
					rowData.add("" + staffList.get(i).getEmail());

					expoExcel.setRowData(rowData);
					exportToExcelList.add(expoExcel);

				}

				session.setAttribute("exportExcelList", exportToExcelList);
				session.setAttribute("excelName", "GetMatIssueHeader");
				Info add = AccessControll.checkAccess("showStaffList", "showStaffList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showStaffList", "showStaffList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showStaffList", "showStaffList", "0", "0", "0", "1",
						newModuleList);

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

	@RequestMapping(value = "/editFaculity/{facultyId}", method = RequestMethod.GET)
	public ModelAndView editFaculity(@PathVariable("facultyId") int facultyId, HttpServletRequest request) {

		// System.out.println("Id:" + facultyId);
		MultiValueMap<String, Object> map = null;
		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("editFaculity/{facultyId}", "showStaffList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/regstaff");

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map = new LinkedMultiValueMap<>();
				map.add("deptIds", userObj.getStaff().getDeptId());
				Dept[] instArray = rest.postForObject(Constants.url + "getDeptListDeptIdIn", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList edt:" + deptList.toString());
				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<>();
				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());
				model.addObject("quolfList", quolfList);

				map = new LinkedMultiValueMap<>();
				map.add("type0", 0);
				Quolification[] teachTo = rest.postForObject(Constants.url + "/getQuolificationListToTeach", map,
						Quolification[].class);
				List<Quolification> teachingList = new ArrayList<>(Arrays.asList(teachTo));
				System.err.println("teachList " + teachingList);
				model.addObject("teachingList", teachingList);

				map = new LinkedMultiValueMap<>();
				map.add("desgList", Constants.facFaculty);
				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map = new LinkedMultiValueMap<>();
				map.add("type", 1);
				AcademicYear[] acadYrArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId", map,
						AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(acadYrArray));
				model.addObject("acaYearList", acaYearList);

				map.add("id", facultyId);
				Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				// System.out.println("staff" + staff);

				model.addObject("title", "Edit Faculty");
				model.addObject("addEdit", "1");
				model.addObject("staff", staff);
			}
		} catch (Exception e) {

			System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/deleteFaculity/{facultyId}", method = RequestMethod.GET)
	public String deleteStaff(@PathVariable("facultyId") int facultyId, HttpServletRequest request) {

		String a = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteFaculity/{facultyId}", "showStaffList", "0", "0", "0", "1",
					newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			} else {

				Info inf = new Info();
				// System.out.println("Id:" + facultyId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("id", facultyId);
				Info faculty = rest.postForObject(Constants.url + "/deleteStaffById", map, Info.class);

				a = "redirect:/showStaffList";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

	@RequestMapping(value = "/delSlectedStaff/{staffId}", method = RequestMethod.GET)
	public String deletSelectedStaff(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int staffId) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedStaff/{staffId}", "showStaffList", "0", "0", "0", "1",
				newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (staffId == 0) {

					System.err.println("Multiple records delete ");
					String[] staffIds = request.getParameterValues("staffIds");
					// System.out.println("id are" + staffIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < staffIds.length; i++) {
						sb = sb.append(staffIds[i] + ",");

					}
					String staffIdList = sb.toString();
					staffIdList = staffIdList.substring(0, staffIdList.length() - 1);

					map.add("staffIdList", staffIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("staffIdList", staffId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteStaffSlected", map, Info.class);

				a = "redirect:/showStaffList";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}

	/****************************************
	 * Dean / R&D Registration
	 **********************************************/

	@RequestMapping(value = "/showRegDean", method = RequestMethod.GET)
	public ModelAndView showRegDean(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		MultiValueMap<String, Object> map = null;
		try {
			Info view = AccessControll.checkAccess("showRegDean", "showDeanList", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/deanReg");

				map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("instId", userObj.getGetData().getUserInstituteId());

				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("desgList", Constants.facDean);
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
				Staff dean = new Staff();
				model.addObject("dean", dean);

				model.addObject("quolfList", quolfList);
				model.addObject("addEdit", "0");
				model.addObject("title", "Add Dean R & D");
			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertNewDean", method = RequestMethod.POST)
	public String addNewDean(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int instituteId = (int) session.getAttribute("instituteId");

				int userId = (int) session.getAttribute("userId");

				// //System.out.println("IdSS:" + userObj.getUserId()+" "+userId+" / "+"
				// "+instituteId+" "+userObj.getGetData().getUserInstituteId());
				int deanId = Integer.parseInt(request.getParameter("dean_id"));

				int isAccOff = 0, isHod = 0, isDean = 0, isStaff = 0, isLib = 0;
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
					isStaff = Integer.parseInt(request.getParameter("isStaff"));
				} catch (Exception e) {
					isStaff = 0;
				}
				try {
					isLib = Integer.parseInt(request.getParameter("isLib"));
				} catch (Exception e) {
					isLib = 0;
				}
				String roleNameList = null;

				roleNameList = Constants.Dean_Role + "," + Constants.Faculty_Role;

				/*
				 * if (isAccOff == 1) { roleNameList = roleNameList + "," +
				 * Constants.Account_Officer_Role; }
				 */

				if (isHod == 1) {
					roleNameList = roleNameList + "," + Constants.HOD_Role;
				}

				/*
				 * if (isDean == 1) { roleNameList = roleNameList + "," + Constants.Dean_Role; }
				 * 
				 * if (isLib == 1) { roleNameList = roleNameList + "," +
				 * Constants.Librarian_Role;
				 * 
				 * }
				 */

				/*
				 * System.err.println("isAccOff" + isAccOff); System.err.println("isHod" +
				 * isHod); System.err.println("isDean" + isDean); System.err.println("isLib" +
				 * isLib);
				 */

				// write web service to get Role Ids..
				// dvd

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

				// System.out.println("Data:" + deanId);
				String deanName = request.getParameter("dean_name");
				// System.out.println("Data:" + deanName);
				designation = Integer.parseInt(request.getParameter("designation"));
				String dateOfJoin = request.getParameter("join_date");
				String dateOfRel = request.getParameter("acc_off_relDate");
				String contact = request.getParameter("contact_no");
				String email = request.getParameter("email");
				int isState = Integer.parseInt(request.getParameter("is_state_same"));

				String[] deptIds = request.getParameterValues("depart");
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < deptIds.length; i++) {
					sb = sb.append(deptIds[i] + ",");

				}
				String deptIdList = sb.toString();
				deptIdList = deptIdList.substring(0, deptIdList.length() - 1);
				int addEdit = Integer.parseInt(request.getParameter("addEdit"));
				if (addEdit == 0) {
					Staff staff = new Staff();

					staff.setContactNo(XssEscapeUtils.jsoupParse(contact));
					staff.setCurrentDesignationId(designation);
					staff.setDeptId(deptIdList);
					staff.setEmail(XssEscapeUtils.jsoupParse(email));
					staff.setFacultyFirstName(XssEscapeUtils.jsoupParse(deanName));
					staff.setFacultyId(deanId);
					staff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
					staff.setHightestQualificationYear(null);
					staff.setIsSame(isState);
					if (isState == 1) {
						staff.setFacultyMiddelName("21"); // inserted state id
					} else {
						staff.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}
					staff.setIsAccOff(0);
					staff.setIsDean(1);
					staff.setIsFaculty(1);
					staff.setIsHod(isHod);
					staff.setIsIqac(0);
					staff.setIsLibrarian(isLib);
					staff.setIsPrincipal(0);

					staff.setIsStudent(0);
					staff.setIsWorking(Integer.parseInt(request.getParameter("is_registration")));
					staff.setJoiningDate(XssEscapeUtils.jsoupParse(dateOfJoin));
					staff.setLastUpdatedDatetime(curDateTime);
					staff.setMakerEnterDatetime(curDateTime);

					staff.setPassword("");
					staff.setRealivingDate(XssEscapeUtils.jsoupParse(dateOfRel));
					staff.setRoleIds(roleIds);
					staff.setTeachingTo(0);
					staff.setType(6);

					staff.setInstituteId(instituteId);
					staff.setDelStatus(1);
					staff.setIsActive(1);
					staff.setMakerUserId(userId);
					staff.setCheckerUserId(0);
					staff.setCheckerDatetime(curDateTime);

					staff.setExtravarchar1("NA");
					Staff newDean = rest.postForObject(Constants.url + "/addNewStaff", staff, Staff.class);
				} else {
					map = new LinkedMultiValueMap<>();
					map.add("id", deanId);

					Staff editStaff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);

					editStaff.setFacultyFirstName(XssEscapeUtils.jsoupParse(deanName));
					editStaff.setDeptId(deptIdList);
					editStaff.setEmail(XssEscapeUtils.jsoupParse(email));
					editStaff.setFacultyId(deanId);
					editStaff.setContactNo(XssEscapeUtils.jsoupParse(contact));
					editStaff.setCurrentDesignationId(designation);
					editStaff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
					editStaff.setJoiningDate(XssEscapeUtils.jsoupParse(dateOfJoin));
					editStaff.setRealivingDate(XssEscapeUtils.jsoupParse(dateOfRel));

					editStaff.setIsSame(isState);
					if (isState == 1) {
						editStaff.setFacultyMiddelName("21"); // inserted state id
					} else {
						editStaff.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}

					editStaff.setIsHod(isHod);
					// editStaff.setRoleIds(roleIds);
					editStaff.setType(6);
					editStaff.setIsWorking(Integer.parseInt(request.getParameter("is_registration")));

					editStaff.setMakerUserId(userId);
					editStaff.setMakerEnterDatetime(curDateTime);
					editStaff.setCheckerUserId(0);
					editStaff.setCheckerDatetime(curDateTime);
					editStaff.setLastUpdatedDatetime(curDateTime);

					Staff editDean = rest.postForObject(Constants.url + "/addNewStaff", editStaff, Staff.class);
				}
				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showDeanList";

			}

			else {
				System.err.println("in else");
				redirect = "redirect:/accessDenied";
			}
		} catch (Exception e) {

			System.err.println("exception In iqacNewRegistration at showIqacList Contr" + e.getMessage());
			e.printStackTrace();

		}
		return redirect;

		/*
		 * 
		 * try {
		 * 
		 * HttpSession session = request.getSession();
		 * 
		 * int instituteId = (int) session.getAttribute("instituteId"); int userId =
		 * (int) session.getAttribute("userId"); String deanName =
		 * request.getParameter("dean_name"); String contactNo =
		 * request.getParameter("contact_no"); String email =
		 * request.getParameter("email"); int qualificaton =
		 * Integer.parseInt(request.getParameter("hod_quolf")); String joinDate =
		 * request.getParameter("join_date"); int isReg =
		 * Integer.parseInt(request.getParameter("is_registration"));
		 * 
		 * Dean dean = new Dean();
		 * 
		 * dean.setDeanId(Integer.parseInt(request.getParameter("dean_id")));
		 * dean.setDeanName(deanName); dean.setInstituteId(instituteId);
		 * dean.setContactNo(contactNo); dean.setEmail(email);
		 * dean.setQualificationId(qualificaton); dean.setJoiningDate(joinDate);
		 * 
		 * if (isReg == 0) {
		 * dean.setRealivingDate(request.getParameter("acc_off_relDate"));
		 * 
		 * } else { dean.setRealivingDate(null); } dean.setMakerUserId(userId);
		 * dean.setDelStatus(1);
		 * 
		 * dean.setMakerEnterDatetime(curDateTime);
		 * 
		 * dean.setExtraint1(6); dean.setExtravarchar1("NA");
		 * 
		 * Dean deanSave = rest.postForObject(Constants.url + "/saveNewDean", dean,
		 * Dean.class); int isView = Integer.parseInt(request.getParameter("is_view"));
		 * if (isView == 1) redirect = "redirect:/showDeanList"; else redirect =
		 * "redirect:/showRegDean"; } catch (Exception e) {
		 * System.err.println("exception In showStaffList at Master Contr" +
		 * e.getMessage()); e.printStackTrace();
		 * 
		 * } return redirect;
		 * 
		 */}

	@RequestMapping(value = "/showDeanList", method = RequestMethod.GET)
	public ModelAndView showDeanList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showDeanList", "showDeanList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/deanList");

				model.addObject("title", "Dean R & D Registration ");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				LoginResponse facId = (LoginResponse) session.getAttribute("userObj");
				int yId = (int) session.getAttribute("acYearId");
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				map.add("isDean", userObj.getStaff().getIsDean());

				NewDeanList[] deans = rest.postForObject(Constants.url + "/getListDean", map, NewDeanList[].class);
				List<NewDeanList> deanList = new ArrayList<>(Arrays.asList(deans));
				// System.out.println("Dean List:" + deanList);

				model.addObject("deanList", deanList);
				model.addObject("listMapping", "showDeanList");

				Info add = AccessControll.checkAccess("showDeanList", "showDeanList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showDeanList", "showDeanList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showDeanList", "showDeanList", "0", "0", "0", "1",
						newModuleList);

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

	@RequestMapping(value = "/editDean/{deanId}", method = RequestMethod.GET)
	public ModelAndView editDean(@PathVariable("deanId") int deanId, HttpServletRequest request) {

		// System.out.println("Id:" + deanId);

		ModelAndView model = null;
		MultiValueMap<String, Object> map = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("editDean/{deanId}", "showDeanList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/deanReg");

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map = new LinkedMultiValueMap<>();
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList edt:" + deptList.toString());
				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("desgList", Constants.facDean);
				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map = new LinkedMultiValueMap<>();
				map.add("type", 1);

				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);

				map = new LinkedMultiValueMap<>();
				map.add("id", deanId);
				Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				// System.out.println("staff:" + staff);

				model.addObject("dean", staff);
				model.addObject("addEdit", "1");
				model.addObject("title", "Edit Dean R & D");
			}
		} catch (Exception e) {

			System.err.println("exception In editDean at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/deleteDean/{deanId}", method = RequestMethod.GET)
	public String deleteDean(@PathVariable("deanId") int deanId, HttpServletRequest request) {
		String a = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteDean/{deanId}", "showDeanList", "0", "0", "0", "1",
					newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			} else {

				Info inf = new Info();
				// System.out.println("Id:" + deanId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("id", deanId);
				Info dean = rest.postForObject(Constants.url + "/deleteStaffById", map, Info.class);
				a = "redirect:/showDeanList";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

	/***********************************
	 * Training & Placement Officer
	 **************************************/
	@RequestMapping(value = "/showTrainingAndPlacementOfficer", method = RequestMethod.GET)
	public ModelAndView showTrainingAndPlacementOfficer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showTrainingAndPlacementOfficer", "showTrainingAndPlacementOfficer",
					"1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/traningPlacementOfficerList");

				model.addObject("title", "Training & Placement Officer");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				LoginResponse facId = (LoginResponse) session.getAttribute("userObj");
				int yId = (int) session.getAttribute("acYearId");
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				map.add("isDean", userObj.getStaff().getIsDean());
				map.add("isTpo", userObj.getStaff().getIsTpo());

				NewDeanList[] deans = rest.postForObject(Constants.url + "/getTraningOfficerList", map,
						NewDeanList[].class);
				List<NewDeanList> trainOfficrList = new ArrayList<>(Arrays.asList(deans));
				// System.out.println("Training Officer List:" + trainOfficrList);

				model.addObject("trainOfficrList", trainOfficrList);
				model.addObject("listMapping", "showTrainingAndPlacementOfficer");

				Info add = AccessControll.checkAccess("showTrainingAndPlacementOfficer",
						"showTrainingAndPlacementOfficer", "0", "1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showTrainingAndPlacementOfficer",
						"showTrainingAndPlacementOfficer", "0", "0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showTrainingAndPlacementOfficer",
						"showTrainingAndPlacementOfficer", "0", "0", "0", "1", newModuleList);

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

	@RequestMapping(value = "/newTrainingAndPlacementOfficer", method = RequestMethod.GET)
	public ModelAndView newTrainingAndPlacementOfficer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		MultiValueMap<String, Object> map = null;
		try {
			Info view = AccessControll.checkAccess("newTrainingAndPlacementOfficer", "showTrainingAndPlacementOfficer",
					"0", "1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/training&placementOfficer");

				map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("instId", userObj.getGetData().getUserInstituteId());

				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("desgList", Constants.facTrainPlace);

				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());
				Staff trnPlaceOff = new Staff();
				model.addObject("trnPlaceOff", trnPlaceOff);

				model.addObject("quolfList", quolfList);
				model.addObject("addEdit", "0");
				model.addObject("title", "Add Training & Placement Officer");
			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertTrainingandPlacementOff", method = RequestMethod.POST)
	public String insertTrainingandPlacementOff(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int instituteId = (int) session.getAttribute("instituteId");

				int userId = (int) session.getAttribute("userId");

				int officerId = Integer.parseInt(request.getParameter("pacementOfficerId"));

				int isAccOff = 0, isHod = 0, isDean = 0, isStaff = 0, isLib = 0;
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
					isStaff = Integer.parseInt(request.getParameter("isStaff"));
				} catch (Exception e) {
					isStaff = 0;
				}
				try {
					isLib = Integer.parseInt(request.getParameter("isLib"));
				} catch (Exception e) {
					isLib = 0;
				}
				String roleNameList = null;

				roleNameList = Constants.TPO_Role + "," + Constants.Faculty_Role;

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

				/*
				 * System.err.println("isAccOff" + isAccOff); System.err.println("isHod" +
				 * isHod); System.err.println("isDean" + isDean); System.err.println("isLib" +
				 * isLib);
				 */

				// write web service to get Role Ids..
				// dvd

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

				System.out.println("Data:" + request.getParameter("quolif"));
				String pacementOfficerName = request.getParameter("pacementOfficerName");
				designation = Integer.parseInt(request.getParameter("designation"));
				String dateOfJoin = request.getParameter("join_date");
				String dateOfRel = request.getParameter("acc_off_relDate");
				String contact = request.getParameter("contact_no");
				String email = request.getParameter("email");
				int isState = Integer.parseInt(request.getParameter("is_state_same"));

				String[] deptIds = request.getParameterValues("depart");
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < deptIds.length; i++) {
					sb = sb.append(deptIds[i] + ",");

				}
				String deptIdList = sb.toString();
				deptIdList = deptIdList.substring(0, deptIdList.length() - 1);
				int addEdit = Integer.parseInt(request.getParameter("addEdit"));
				if (addEdit == 0) {
					Staff staff = new Staff();

					staff.setContactNo(XssEscapeUtils.jsoupParse(contact));
					staff.setCurrentDesignationId(designation);
					staff.setDeptId(deptIdList);
					staff.setEmail(XssEscapeUtils.jsoupParse(email));
					staff.setFacultyFirstName(XssEscapeUtils.jsoupParse(pacementOfficerName));
					staff.setFacultyId(officerId);

					staff.setIsSame(isState);
					if (isState == 1) {
						staff.setFacultyMiddelName("21"); // inserted state id
					} else {
						staff.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}

					staff.setHightestQualificationYear(null);
					staff.setIsAccOff(0);
					staff.setIsDean(0);
					staff.setIsFaculty(1);
					staff.setIsHod(0);
					staff.setIsIqac(0);
					staff.setIsLibrarian(0);
					staff.setIsPrincipal(0);
					staff.setIsTpo(1);
					staff.setIsExtActOff(0);

					staff.setIsStudent(0);
					staff.setIsWorking(Integer.parseInt(request.getParameter("is_registration")));
					staff.setLastUpdatedDatetime(curDateTime);
					staff.setMakerEnterDatetime(curDateTime);

					staff.setPassword("");
					staff.setJoiningDate(XssEscapeUtils.jsoupParse(dateOfJoin));
					staff.setRealivingDate(XssEscapeUtils.jsoupParse(dateOfRel));
					staff.setRoleIds(roleIds);
					staff.setTeachingTo(0);
					staff.setType(7);

					staff.setInstituteId(instituteId);
					staff.setDelStatus(1);
					staff.setIsActive(1);
					staff.setMakerUserId(userId);
					staff.setCheckerUserId(0);
					staff.setCheckerDatetime(curDateTime);
					staff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));

					staff.setExtravarchar1("NA");
					Staff newDean = rest.postForObject(Constants.url + "/addNewStaff", staff, Staff.class);
				} else {
					map = new LinkedMultiValueMap<>();
					map.add("id", officerId);

					Staff editStaff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);

					editStaff.setFacultyFirstName(XssEscapeUtils.jsoupParse(pacementOfficerName));
					editStaff.setDeptId(deptIdList);
					editStaff.setEmail(XssEscapeUtils.jsoupParse(email));
					editStaff.setFacultyId(officerId);
					editStaff.setContactNo(XssEscapeUtils.jsoupParse(contact));
					editStaff.setCurrentDesignationId(designation);
					editStaff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
					editStaff.setIsSame(isState);
					if (isState == 1) {
						editStaff.setFacultyMiddelName("21"); // inserted state id
					} else {
						editStaff.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}
					editStaff.setJoiningDate(XssEscapeUtils.jsoupParse(dateOfJoin));
					editStaff.setRealivingDate(XssEscapeUtils.jsoupParse(dateOfRel));
					// editStaff.setRoleIds(roleIds);
					editStaff.setType(7);
					editStaff.setIsWorking(Integer.parseInt(request.getParameter("is_registration")));

					editStaff.setMakerUserId(userId);
					editStaff.setMakerEnterDatetime(curDateTime);
					editStaff.setCheckerUserId(0);
					editStaff.setCheckerDatetime(curDateTime);
					editStaff.setLastUpdatedDatetime(curDateTime);

					Staff editDean = rest.postForObject(Constants.url + "/addNewStaff", editStaff, Staff.class);
				}
				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showTrainingAndPlacementOfficer";

			}

			else {

				redirect = "redirect:/accessDenied";
			}

		} catch (Exception e) {

			System.err.println(
					"exception In Training & Placement Officer Registration at showIqacList Contr" + e.getMessage());
			e.printStackTrace();

		}
		return redirect;

	}

	@RequestMapping(value = "/editTpo/{tpoId}", method = RequestMethod.GET)
	public ModelAndView editTpo(@PathVariable("tpoId") int tpoId, HttpServletRequest request) {

		// System.out.println("Id:" + tpoId);

		ModelAndView model = null;
		MultiValueMap<String, Object> map = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("editTpo/{tpoId}", "showTrainingAndPlacementOfficer", "0", "0", "1",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/training&placementOfficer");

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map = new LinkedMultiValueMap<>();
				map.add("instId", userObj.getGetData().getUserInstituteId());

				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList edt:" + deptList.toString());
				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("desgList", Constants.facTrainPlace);

				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map = new LinkedMultiValueMap<>();
				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);

				map = new LinkedMultiValueMap<>();
				map.add("id", tpoId);
				Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				// System.out.println("staff:" + staff);

				model.addObject("trnPlaceOff", staff);
				model.addObject("addEdit", "1");
				model.addObject("title", "Edit Training & Placement Officer");
			}
		} catch (Exception e) {

			System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/deleteTpo/{tpoId}", method = RequestMethod.GET)
	public String deleteTpo(@PathVariable("tpoId") int tpoId, HttpServletRequest request) {
		String a = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteDean/{tpoId}", "showTrainingAndPlacementOfficer", "0", "0",
					"0", "1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			} else {

				Info inf = new Info();
				// System.out.println("Id:" + tpoId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("id", tpoId);
				Info dean = rest.postForObject(Constants.url + "/deleteStaffById", map, Info.class);
				a = "redirect:/showTrainingAndPlacementOfficer";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

	/****************************
	 * Extension Activity
	 *******************************/
	@RequestMapping(value = "/showExternalActivities", method = RequestMethod.GET)
	public ModelAndView showExternalActivities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showExternalActivities", "showExternalActivities", "1", "0", "0",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/showExtensionActivityOfficerList");

				model.addObject("title", "Extension Activity Officer");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				LoginResponse facId = (LoginResponse) session.getAttribute("userObj");
				int yId = (int) session.getAttribute("acYearId");
				map.add("facultyId", userObj.getGetData().getUserDetailId());
				map.add("yearId", yId);
				map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
				map.add("isHod", userObj.getStaff().getIsHod());
				map.add("isIQAC", userObj.getStaff().getIsIqac());
				map.add("deptIdList", userObj.getStaff().getDeptId());
				map.add("instituteId", userObj.getStaff().getInstituteId());
				map.add("isDean", userObj.getStaff().getIsDean());
				map.add("isExactActOff", userObj.getStaff().getIsExtActOff());

				NewDeanList[] expActListarr = rest.postForObject(Constants.url + "/getExtActList", map,
						NewDeanList[].class);
				List<NewDeanList> expActList = new ArrayList<>(Arrays.asList(expActListarr));
				// System.out.println("Extension Activity Officer List:" + expActList);

				model.addObject("expActList", expActList);
				model.addObject("listMapping", "showExternalActivities");

				Info add = AccessControll.checkAccess("showExternalActivities", "showExternalActivities", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showExternalActivities", "showExternalActivities", "0", "0",
						"1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showExternalActivities", "showExternalActivities", "0", "0",
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

	@RequestMapping(value = "/newExternalActivitiesOfficer", method = RequestMethod.GET)
	public ModelAndView newExternalActivitiesOfficer(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		MultiValueMap<String, Object> map = null;
		try {
			Info view = AccessControll.checkAccess("newExternalActivitiesOfficer", "showExternalActivities", "0", "1",
					"0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/extensionActOfficer");

				map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("instId", userObj.getGetData().getUserInstituteId());

				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();

				map.add("desgList", Constants.facExternlAct);
				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());
				Staff extActOff = new Staff();
				model.addObject("extActOff", extActOff);

				model.addObject("quolfList", quolfList);
				model.addObject("addEdit", "0");
				model.addObject("title", "Add Extension Activity Officer");
			}
		} catch (Exception e) {

			System.err.println("exception In newExternalActivitiesOfficer" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/extActOfficerName", method = RequestMethod.POST)
	public String extActOfficerName(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int instituteId = (int) session.getAttribute("instituteId");

				int userId = (int) session.getAttribute("userId");

				int extActOfficerId = Integer.parseInt(request.getParameter("extActOfficerId"));

				String roleNameList = null;

				roleNameList = Constants.EAO_Role + "," + Constants.Faculty_Role;

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

				// System.out.println("Data:" + extActOfficerId);
				String extActOfficerName = request.getParameter("extActOfficerName");
				// System.out.println("Data:" + extActOfficerName);
				designation = Integer.parseInt(request.getParameter("designation"));
				String dateOfJoin = request.getParameter("join_date");
				String dateOfRel = request.getParameter("acc_off_relDate");
				String contact = request.getParameter("contact_no");
				String email = request.getParameter("email");

				int isState = Integer.parseInt(request.getParameter("is_state_same"));

				String[] deptIds = request.getParameterValues("depart");
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < deptIds.length; i++) {
					sb = sb.append(deptIds[i] + ",");

				}
				String deptIdList = sb.toString();
				deptIdList = deptIdList.substring(0, deptIdList.length() - 1);
				int addEdit = Integer.parseInt(request.getParameter("addEdit"));
				if (addEdit == 0) {
					Staff staff = new Staff();

					staff.setContactNo(XssEscapeUtils.jsoupParse(contact));
					staff.setCurrentDesignationId(designation);
					staff.setDeptId(deptIdList);
					staff.setEmail(XssEscapeUtils.jsoupParse(email));
					staff.setFacultyFirstName(XssEscapeUtils.jsoupParse(extActOfficerName));
					staff.setFacultyId(extActOfficerId);
					staff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));

					staff.setIsSame(isState);
					if (isState == 1) {
						staff.setFacultyMiddelName("21"); // inserted state id
					} else {
						staff.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}

					staff.setHightestQualificationYear(null);
					staff.setIsAccOff(0);
					staff.setIsDean(0);
					staff.setIsFaculty(1);
					staff.setIsHod(0);
					staff.setIsIqac(0);
					staff.setIsLibrarian(0);
					staff.setIsPrincipal(0);
					staff.setIsTpo(0);
					staff.setIsExtActOff(1);

					staff.setIsStudent(0);
					staff.setIsWorking(Integer.parseInt(request.getParameter("is_registration")));
					staff.setJoiningDate(XssEscapeUtils.jsoupParse(dateOfJoin));

					staff.setPassword("");
					staff.setRealivingDate(XssEscapeUtils.jsoupParse(dateOfRel));
					staff.setRoleIds(roleIds);
					staff.setTeachingTo(0);
					staff.setType(8);

					staff.setInstituteId(instituteId);
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
					Staff newDean = rest.postForObject(Constants.url + "/addNewStaff", staff, Staff.class);
				} else {
					map = new LinkedMultiValueMap<>();
					map.add("id", extActOfficerId);

					Staff editStaff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);

					editStaff.setFacultyFirstName(XssEscapeUtils.jsoupParse(extActOfficerName));
					editStaff.setDeptId(deptIdList);
					editStaff.setEmail(XssEscapeUtils.jsoupParse(email));
					editStaff.setFacultyId(extActOfficerId);
					editStaff.setContactNo(XssEscapeUtils.jsoupParse(contact));
					editStaff.setCurrentDesignationId(designation);
					editStaff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));

					editStaff.setIsSame(isState);
					if (isState == 1) {
						editStaff.setFacultyMiddelName("21"); // inserted state id
					} else {
						editStaff.setFacultyMiddelName(request.getParameter("state_id")); // inserted state id
					}
					editStaff.setJoiningDate(XssEscapeUtils.jsoupParse(dateOfJoin));
					editStaff.setRealivingDate(XssEscapeUtils.jsoupParse(dateOfRel));
					// editStaff.setRoleIds(roleIds);
					editStaff.setType(8);
					editStaff.setIsWorking(Integer.parseInt(request.getParameter("is_registration")));

					editStaff.setMakerUserId(userId);
					editStaff.setMakerEnterDatetime(curDateTime);
					editStaff.setCheckerUserId(0);
					editStaff.setCheckerDatetime(curDateTime);
					editStaff.setLastUpdatedDatetime(curDateTime);

					Staff editDean = rest.postForObject(Constants.url + "/addNewStaff", editStaff, Staff.class);
				}
				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showExternalActivities";
			} 
			else {

				redirect = "redirect:/accessDenied";
			}
		} catch (Exception e) {

			System.err.println(
					"exception In Traning & Placement Officer Registration at showIqacList Contr" + e.getMessage());
			e.printStackTrace();

		}
		return redirect;

	}

	@RequestMapping(value = "/editExtActOff/{extOffId}", method = RequestMethod.GET)
	public ModelAndView editExtActOff(@PathVariable("extOffId") int extOffId, HttpServletRequest request) {

		// System.out.println("Id:" + extOffId);

		ModelAndView model = null;

		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("editExtActOff/{tpoId}", "showExternalActivities", "0", "0", "1",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/extensionActOfficer");
				MultiValueMap<String, Object> map = null;

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map = new LinkedMultiValueMap<>();
				map.add("instId", userObj.getGetData().getUserInstituteId());

				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList edt:" + deptList.toString());
				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<>();
				map.add("desgList", Constants.facExternlAct);

				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map = new LinkedMultiValueMap<>();
				map.add("type", 1);

				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);
				map = new LinkedMultiValueMap<>();
				map.add("id", extOffId);
				Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				// System.out.println("staffExt:" + staff);

				model.addObject("extActOff", staff);
				model.addObject("addEdit", "1");
				model.addObject("title", "Edit Extension Activity Officer");
			}
		} catch (Exception e) {

			System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/deleteExtAxtOff/{extOffId}", method = RequestMethod.GET)
	public String deleteExtAxtOff(@PathVariable("extOffId") int extOffId, HttpServletRequest request) {
		String a = null;
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteExtAxtOff/{extOffId}", "showExternalActivities", "0", "0",
					"0", "1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			} else {

				Info inf = new Info();
				// System.out.println("Id:" + extOffId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("id", extOffId);
				Info dean = rest.postForObject(Constants.url + "/deleteStaffById", map, Info.class);
				a = "redirect:/showExternalActivities";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return a;

	}

}
