package com.ats.rusasoft.controller;

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

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.MIqac;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.UserLogin;

@Controller
@Scope("session")
public class IqacController {
	
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/iqacRegistration", method = RequestMethod.GET)
	public ModelAndView showRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/iqacRegistration");
		try {
			List<Designation> designationList = rest.getForObject(Constants.url+"/getAllDesignations", List.class);
			
			model.addObject("desigList", designationList);
		
		} catch (Exception e) {

			System.err.println("exception In iqacRegistration at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}

		
		
		
		return model;

	}
	
	
	@RequestMapping(value = "/iqacNewRegistration", method = RequestMethod.POST)
	public String newRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		
		int instituteId =(int)session.getAttribute("instituteId");
		int userId =(int)session.getAttribute("userId");
		
		MIqac miqac = new MIqac();
		
		int iqacId=0;	
		int  designation = 0;
		try {		
			iqacId=0;
			//iqacId= Integer.parseInt(request.getParameter("iqac_id"));
			System.out.println("Data:"+iqacId);
			String iqacName = request.getParameter("iqacName2");
			System.out.println("Data:"+iqacName);
			designation = Integer.parseInt(request.getParameter("designtion2"));
			String dateOfJoin = request.getParameter("date2");
			String contact = request.getParameter("conNumber2");
			String email = request.getParameter("emailId2");
			
			System.out.println("Data:"+iqacId+" "+iqacName+" "+dateOfJoin+" "+contact+" "+email);
			
			miqac.setIqacId(iqacId);
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
			miqac.setMakerEnterDatetime("2019-03-04");
			miqac.setCheckerUserId(userId);
			miqac.setCheckerDatetime("2019-03-04");
			miqac.setLastUpdatedDatetime("2019-03-04");
 
			MIqac iqac = rest.postForObject(Constants.url + "/insertNewIqac",miqac ,MIqac.class);
			System.out.println("IQAC Resp:"+iqac);
			
			UserLogin user = new UserLogin();
			String userName = getAlphaNumericString(7); 
			String passWord = getAlphaNumericString(7);
			
			user.setRegPrimaryKey(iqac.getIqacId());
			user.setUserName(userName);
			user.setPass(passWord);
			user.setRoleId(0);
			user.setExInt1(0);
			user.setUserType(2);
			user.setExInt2(instituteId);
			user.setExVar1("NA");
			user.setExVar2("NA");
			user.setIsBlock(0);
		
			UserLogin usr = rest.postForObject(Constants.url + "/insertNewUser",user ,UserLogin.class);
		} catch (Exception e) {

			System.err.println("exception In iqacNewRegistration at showIqacList Contr" + e.getMessage());
			e.printStackTrace();
			//miqacId=0;
	}
	
	
		return "redirect:/iqacRegistration";

	}
	
	
	@RequestMapping(value = "/showIqacList", method = RequestMethod.GET)
	public ModelAndView showIqacList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/iqacList");
			
			List<MIqac> qacList = rest.getForObject(Constants.url+"/getAllIqac", List.class);
			
			System.out.println("IQACLIST"+qacList);
			
			model.addObject("QList", qacList);
			model.addObject("title", "IQAC List");

		} catch (Exception e) {

			System.err.println("exception In showIqacList at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	

	
	@RequestMapping(value = "/editIqac/{iqacId}", method = RequestMethod.GET)
	public ModelAndView editIqac(@PathVariable("iqacId") int iqacId) {
		
	System.out.println("Id:"+iqacId);

		ModelAndView model = new ModelAndView("master/iqacRegistration");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", iqacId);
		try {
			
			List<Designation> designationList = rest.getForObject(Constants.url+"/getAllDesignations", List.class);
			model.addObject("desigList", designationList);
			
			MIqac miqc = rest.postForObject(Constants.url+"/getIqacById", map, MIqac.class);
			System.out.println("miqc:"+miqc);
			
			model.addObject("miqc", miqc);
		
		} catch (Exception e) {

			System.err.println("exception In editIqac at Iqac Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}
	
	@RequestMapping(value = "/deleteIqac/{iqacId}", method = RequestMethod.GET)
	public String deleteIqac(@PathVariable("iqacId") int iqacId) {
		Info inf = new Info();
		System.out.println("Id:"+iqacId);
		
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		map.add("id", iqacId);
		Info miqc = rest.postForObject(Constants.url+"/deleteIqacById", map, Info.class);
		
		return "redirect:/showIqacList";
	
	}
	
	/********************************************Staff/Faculty**********************************************/

	@RequestMapping(value = "/addFaculty", method = RequestMethod.POST)
	public ModelAndView addFaculty(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("master/addFaculty");
		model.addObject("title","Add Department");
		int facultyId = 0;
		int instituteId = 0;
		int deptId = 0;
		try {
			
			String facultyMmemberName = request.getParameter("faculty_member_name");
			int highestQualification = Integer.parseInt(request.getParameter("highest_qualification"));
			//String otherQualification = request.getParameter("other_qualification");
			String yrofHighestQualification = request.getParameter("yr_highest_qualification_acqrd");
			int designation = Integer.parseInt(request.getParameter("designation"));
			String joinDate = request.getParameter("join_date");
			int isReg = Integer.parseInt(request.getParameter("isReg"));
			String relDate = request.getParameter("rel_date");
			int teachTo = Integer.parseInt(request.getParameter("teachTo"));
			String contactNo = request.getParameter("contact_no");
			String email = request.getParameter("email");
			
			Staff staff = new Staff();
			
			staff.setFacultyId(facultyId);
			staff.setInstituteId(instituteId);
			staff.setDeptId(deptId);
			staff.setFacultyName(facultyMmemberName);
			staff.setHighestQualification(highestQualification);
			staff.setHightestQualificationYear(yrofHighestQualification);
			staff.setCurrentDesignationId(designation);
			staff.setJoiningDate(joinDate);
			staff.setIsWorking(isReg);
			if(isReg == 0)
			staff.setRealivingDate(DateConvertor.convertToYMD(relDate));
		else {
			staff.setRealivingDate(null);
		}
			
			staff.setTeachingTo(teachTo);
			staff.setContactNo(contactNo);
			staff.setEmail(email);
			staff.setDelStatus(1);
			staff.setIsActive(1);
			staff.setMakerUserId(0);
			staff.setMakerEnterDatetime("2019-03-10");
			staff.setEditUserId(0);
			staff.setLastUpdatedDatetime("2019-03-10");
			staff.setCheckerUserId(0);
			staff.setCheckerDatetime("2019-03-10");
			staff.setExtraint1(0);
			staff.setExtravarchar1("NA");
			
			
			System.out.println("Staff:"+staff.toString());
			
			Staff stf = rest.postForObject(Constants.url+"/addNewStaff", staff, Staff.class);

		} catch (Exception e) {

			System.err.println("exception In showRegisterInstitute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/showStaffList", method = RequestMethod.GET)
	public ModelAndView showStaffList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/staffList");

			model.addObject("title", "Faculty List");
			
			
			List<Staff> staffList = rest.getForObject(Constants.url+"/getListStaff", List.class);
			System.out.println("Staff List:"+staffList);
			
			model.addObject("staffList", staffList);
			
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	
	
	
	
	static String getAlphaNumericString(int n) 
	{ 

		// chose a Character random from this String 
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
									+ "0123456789"
									+ "abcdefghijklmnopqrstuvxyz"; 

		// create StringBuffer size of AlphaNumericString 
		StringBuilder sb = new StringBuilder(n); 

		for (int i = 0; i < n; i++) { 

			// generate a random number between 
			// 0 to AlphaNumericString variable length 
			int index 
				= (int)(AlphaNumericString.length() 
						* Math.random()); 

			// add Character one by one in end of sb 
			sb.append(AlphaNumericString 
						.charAt(index)); 
		} 

		return sb.toString(); 
	}

}
