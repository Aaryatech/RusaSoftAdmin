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

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.commons.ExportToExcel;
import com.ats.rusasoft.model.Dean;
import com.ats.rusasoft.model.DeansList;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.IqacList;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.MIqac;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.StaffList;
import com.ats.rusasoft.model.UserLogin;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class IqacController {

	RestTemplate rest = new RestTemplate();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());

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

			System.out.println(
					"Values:" + inputValue + " " + valueType + " " + primaryKey + " " + isEdit + " " + tableId);

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

		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("iqacRegistration", "showIqacList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/iqacRegistration");
				MIqac miqac = new MIqac();
				model.addObject("miqac", miqac);

				List<Designation> designationList = rest.getForObject(Constants.url + "/getAllDesignations",
						List.class);
				model.addObject("desigList", designationList);
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

		System.out.println("Data:" + iqacId);
		String iqacName = request.getParameter("iqacName");
		System.out.println("Data:" + iqacName);
		designation = Integer.parseInt(request.getParameter("designation"));
		String dateOfJoin = request.getParameter("dateOfJoin");
		String contact = request.getParameter("contactNo");
		String email = request.getParameter("email");

		System.out.println("Data:" + iqacId + " " + iqacName + " " + dateOfJoin + " " + contact + " " + email);
		MIqac miqac = new MIqac();
		if (iqacId == 0) {
			miqac.setIqacId(0);

		} else {
			miqac.setIqacId(iqacId);
		}

		miqac.setIqacName(iqacName);
		miqac.setDesgntnId(designation);
		miqac.setInstituteId(instituteId);
		miqac.setJoiningDate(dateOfJoin);
		miqac.setContactNo(contact);
		miqac.setEmail(email);
		miqac.setDelStatus(1);
		miqac.setIsActive(1);
		miqac.setIsEnrollSystem(1);
		miqac.setMakerUserId(1);
		miqac.setMakerEnterDatetime(curDateTime);
		miqac.setCheckerUserId(userId);
		miqac.setCheckerDatetime(curDateTime);
		miqac.setLastUpdatedDatetime(curDateTime);
		miqac.setType(2);
		MIqac iqac = rest.postForObject(Constants.url + "/insertNewIqac", miqac, MIqac.class);

		return "redirect:/iqacRegistration";

	}

	@RequestMapping(value = "/showIqacList", method = RequestMethod.GET)
	public ModelAndView showIqacList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showIqacList", "showIqacList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int userId = (int) session.getAttribute("userId");
				int instituteId = (int) session.getAttribute("instituteId");

				model = new ModelAndView("master/iqacList");

				List<IqacList> qacList = rest.getForObject(Constants.url + "/getAllIqac", List.class);

				System.out.println("IQACLIST" + qacList);

				model.addObject("QList", qacList);
				model.addObject("title", "IQAC List");

				Info add = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showIqacList", "showIqacList", "0", "0", "0", "1",
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

			System.err.println("exception In showIqacList at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/editIqac/{iqacId}", method = RequestMethod.GET)
	public ModelAndView editIqac(@PathVariable("iqacId") int iqacId, HttpServletRequest request) {

		System.out.println("Id:" + iqacId);

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("iqacRegistration", "showIqacList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("id", iqacId);

				model = new ModelAndView("master/iqacRegistration");
				List<Designation> designationList = rest.getForObject(Constants.url + "/getAllDesignations",
						List.class);
				model.addObject("desigList", designationList);

				MIqac miqc = rest.postForObject(Constants.url + "/getIqacById", map, MIqac.class);
				System.out.println("miqc:" + miqc);

				model.addObject("miqc", miqc);
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
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("iqacRegistration", "showIqacList", "0", "0", "0", "1", newModuleList);
		if (view.isError() == true) {

			a = "redirect:/accessDenied";

		} else {
			Info inf = new Info();
			System.out.println("Id:" + iqacId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("id", iqacId);
			Info miqc = rest.postForObject(Constants.url + "/deleteIqacById", map, Info.class);
			a = "redirect:/showIqacList";
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
			
			if(view.isError()==false) {
				
			model = new ModelAndView("master/iqacChange");
			MIqac miqac = new MIqac();
			model.addObject("miqac", miqac);
			
			List<Designation> designationList = rest.getForObject(Constants.url+"/getAllDesignations", List.class);		
			model.addObject("desigList", designationList);
			int instituteId =(int)session.getAttribute("instituteId");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("instituteId", instituteId);
			MIqac miqc = rest.postForObject(Constants.url+"/getIqacbyInstituteId",map, MIqac.class);
			model.addObject("miqc", miqc);
			model.addObject("editIqac", 1);
			
			}else {
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

			List<Designation> designationList = rest.getForObject(Constants.url + "/getAllDesignations", List.class);
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

			System.out.println("Data:" + iqacId);
			String iqacName = request.getParameter("iqacName");
			System.out.println("Data:" + iqacName);
			designation = Integer.parseInt(request.getParameter("designation"));
			String dateOfJoin = request.getParameter("dateOfJoin");
			String contact = request.getParameter("contactNo");
			String email = request.getParameter("email");

			System.out.println("Data:" + iqacId + " " + iqacName + " " + dateOfJoin + " " + contact + " " + email);
			MIqac miqac = new MIqac();
			if (iqacId == 0) {
				miqac.setIqacId(0);

			} else {
				miqac.setIqacId(iqacId);
			}

			miqac.setIqacName(iqacName);
			miqac.setDesgntnId(designation);
			miqac.setInstituteId(instituteId);
			miqac.setJoiningDate(dateOfJoin);
			miqac.setContactNo(contact);
			miqac.setEmail(email);
			miqac.setDelStatus(1);
			miqac.setIsActive(1);
			miqac.setIsEnrollSystem(1);
			miqac.setMakerUserId(1);
			miqac.setMakerEnterDatetime(curDateTime);
			miqac.setCheckerUserId(userId);
			miqac.setCheckerDatetime(curDateTime);
			miqac.setLastUpdatedDatetime(curDateTime);
			miqac.setType(2);

			if (iqacId == 0) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("userType", 2);
				Info info = rest.postForObject(Constants.url + "/blockPreviousIqacRecord", map, Info.class);

				map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				MIqac miqc1 = rest.postForObject(Constants.url + "/getIqacbyInstituteId", map, MIqac.class);
				miqc1.setIsActive(0);
				MIqac res = rest.postForObject(Constants.url + "/insertNewIqac", miqc1, MIqac.class);

				MIqac iqac = rest.postForObject(Constants.url + "/insertNewIqac", miqac, MIqac.class);

			}else {
				
				MIqac iqac = rest.postForObject(Constants.url + "/insertNewIqac", miqac, MIqac.class);
				
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return "redirect:/editIqac";

	}

	/**********************************************Staff/Faculty**********************************************/

	@RequestMapping(value = "/showRegisterStaff", method = RequestMethod.GET)
	public ModelAndView showRegisterStaff(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		MultiValueMap<String, Object> map = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showRegisterStaff", "showStaffList", "0", "1", "0", "0",
					newModuleList);
			String a = null;
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			} else {
				model = new ModelAndView("master/regstaff");

				map = new LinkedMultiValueMap<>();

				// HttpSession session = request.getSession();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				List<Designation> designationList = rest.getForObject(Constants.url + "/getAllDesignations",
						List.class);
				model.addObject("desigList", designationList);

				map.add("type", 1);

				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());
				model.addObject("quolfList", quolfList);

				Staff staff = new Staff();
				model.addObject("staff", staff);

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

		ModelAndView model = new ModelAndView("master/addFaculty");
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		String curDateTime = dateFormat.format(cal.getTime());
		

		HttpSession session = request.getSession();

		int instituteId = (int) session.getAttribute("instituteId");

		model.addObject("title", "Add Department");

		int facultyId = 0;

		try {

			facultyId = Integer.parseInt(request.getParameter("faculty_id"));

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());
			e.printStackTrace();
			facultyId = 0;

		}
		String facultyMmemberName = request.getParameter("faculty_member_name");
		int highestQualification = Integer.parseInt(request.getParameter("hod_quolf"));
		// String otherQualification = request.getParameter("other_qualification");
		String yrofHighestQualification = request.getParameter("yr_highest_qualification_acqrd");
		int designation = Integer.parseInt(request.getParameter("designation"));
		String joinDate = request.getParameter("join_date");
		int isReg = Integer.parseInt(request.getParameter("is_registration"));

		int teachTo = Integer.parseInt(request.getParameter("teachTo"));
		String contactNo = request.getParameter("contact_no");
		String email = request.getParameter("email");

		Staff staff = new Staff();

		staff.setFacultyId(facultyId);

		staff.setInstituteId(instituteId);
		staff.setDeptId(Integer.parseInt(request.getParameter("dept")));
		staff.setFacultyName(facultyMmemberName);
		staff.setHighestQualification(highestQualification);
		staff.setHightestQualificationYear(yrofHighestQualification);
		staff.setCurrentDesignationId(designation);
		staff.setJoiningDate(joinDate);
		staff.setIsWorking(isReg);
		if (isReg == 0) {
			staff.setRealivingDate((request.getParameter("acc_off_relDate")));

		} else {
			staff.setRealivingDate(null);
		}

		staff.setTeachingTo(teachTo);
		staff.setContactNo(contactNo);
		staff.setEmail(email);
		staff.setDelStatus(1);
		staff.setIsActive(1);
		staff.setMakerUserId(0);
		staff.setMakerEnterDatetime(curDateTime);
		staff.setEditUserId(0);
		staff.setLastUpdatedDatetime(curDateTime);
		staff.setCheckerUserId(0);
		staff.setCheckerDatetime(curDateTime);
		staff.setExtraint1(4);
		staff.setExtravarchar1("NA");

		System.out.println("Staff:" + staff.toString());

		Staff stf = rest.postForObject(Constants.url + "/addNewStaff", staff, Staff.class);

		return "redirect:/showRegisterStaff";

	}

	@RequestMapping(value = "/showStaffList", method = RequestMethod.GET)
	public ModelAndView showStaffList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showStaffList", "showStaffList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/staffList");

				model.addObject("title", "Faculty List");
				int facId = (int) session.getAttribute("instituteId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("facId", facId);

				StaffList[] staff = rest.postForObject(Constants.url + "/getListStaff", map, StaffList[].class);
				List<StaffList> staffList = new ArrayList<>(Arrays.asList(staff));
				System.out.println("Staff List:" + staffList);

				model.addObject("staffList", staffList);

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

					rowData.add("" + staffList.get(i).getFacultyName());
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

	@RequestMapping(value = "/editFaculity/{facultyId}", method = RequestMethod.GET)
	public ModelAndView editFaculity(@PathVariable("facultyId") int facultyId, HttpServletRequest request) {

		System.out.println("Id:" + facultyId);

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showRegisterStaff", "showStaffList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/regstaff");
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
				System.err.println("quolfList " + quolfList.toString());
				model.addObject("quolfList", quolfList);

				List<Designation> designationList = rest.getForObject(Constants.url + "/getAllDesignations",
						List.class);
				model.addObject("desigList", designationList);

				Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				System.out.println("staff" + staff);

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
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showRegisterStaff", "showStaffList", "0", "0", "0", "1", newModuleList);
		String a = null;
		if (view.isError() == true) {

			a = "redirect:/accessDenied";

		} else {

			Info inf = new Info();
			System.out.println("Id:" + facultyId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("id", facultyId);
			Info miqc = rest.postForObject(Constants.url + "/deleteStaffById", map, Info.class);

			a = "redirect:/showStaffList";

		}
		return a;

	}

	/*****************************
	 * Dean / R&D Registration
	 ***********************************/

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

				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());
				Dean dean = new Dean();
				model.addObject("dean", dean);

				model.addObject("quolfList", quolfList);
				model.addObject("title", "Dean  Registration");
			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertNewDean", method = RequestMethod.POST)
	public String addNewDean(HttpServletRequest request, HttpServletResponse response) {

		int deanId = 0;
		try {

			deanId = Integer.parseInt(request.getParameter("dean_id"));
		} catch (Exception e) {
			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());
			e.printStackTrace();
			deanId = 0;
		}

		HttpSession session = request.getSession();

		int instituteId = (int) session.getAttribute("instituteId");
		String deanName = request.getParameter("dean_name");
		String contactNo = request.getParameter("contact_no");
		String email = request.getParameter("email");
		int qualificaton = Integer.parseInt(request.getParameter("hod_quolf"));
		String joinDate = request.getParameter("join_date");
		int isReg = Integer.parseInt(request.getParameter("is_registration"));

		Dean dean = new Dean();

		dean.setDeanId(deanId);
		dean.setDeanName(deanName);
		dean.setInstituteId(instituteId);
		dean.setContactNo(contactNo);
		dean.setEmail(email);
		dean.setQualificationId(qualificaton);
		dean.setJoiningDate(DateConvertor.convertToYMD(joinDate));

		if (isReg == 0) {
			dean.setRealivingDate(DateConvertor.convertToYMD(request.getParameter("acc_off_relDate")));

		} else {
			dean.setRealivingDate(null);
		}
		dean.setMakerUserId(0);
		dean.setDelStatus(1);

		dean.setMakerEnterDatetime(curDateTime);

		dean.setExtraint1(6);
		dean.setExtravarchar1("NA");
		System.out.println("Dean Data:" + dean);

		Dean deanSave = rest.postForObject(Constants.url + "/saveNewDean", dean, Dean.class);

		return "redirect:/showRegDean";

	}

	@RequestMapping(value = "/showDeanList", method = RequestMethod.GET)
	public ModelAndView showDeanList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showDeanList", "showDeanList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/deanList");

				model.addObject("title", "Dean R & D List");

				DeansList[] deans = rest.getForObject(Constants.url + "/getListDean", DeansList[].class);
				List<DeansList> deanList = new ArrayList<>(Arrays.asList(deans));
				System.out.println("Dean List:" + deanList);

				model.addObject("deanList", deanList);

				Info add = AccessControll.checkAccess("showDeanList", "showDeanList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showDeanList", "showDeanList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showDeanList", "showDeanList", "0", "0", "0", "1",
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

	@RequestMapping(value = "/editDean/{deanId}", method = RequestMethod.GET)
	public ModelAndView editDean(@PathVariable("deanId") int deanId, HttpServletRequest request) {

		System.out.println("Id:" + deanId);

		ModelAndView model = null;

		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showRegDean", "showDeanList", "0", "0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/deanReg");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("type", 1);

				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);

				map.add("id", deanId);
				Dean dean = rest.postForObject(Constants.url + "/getDeanById", map, Dean.class);
				System.out.println("dean" + dean);

				model.addObject("dean", dean);
				model.addObject("title", "Edit Dean");
			}
		} catch (Exception e) {

			System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/deleteDean/{deanId}", method = RequestMethod.GET)
	public String deleteDean(@PathVariable("deanId") int deanId, HttpServletRequest request) {

		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showRegDean", "showDeanList", "0", "0", "0", "1", newModuleList);
		String a = null;
		if (view.isError() == true) {

			a = "redirect:/accessDenied";

		} else {

			Info inf = new Info();
			System.out.println("Id:" + deanId);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("id", deanId);
			Info dean = rest.postForObject(Constants.url + "/deleteDeanById", map, Info.class);
			a = "redirect:/showDeanList";
		}
		return a;

	}

}
