package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.master.InstituteMaster;
import com.ats.rusasoft.master.model.prodetail.NameIdBean;
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.accessright.AssignRoleDetailList;

@Controller
@Scope("session")
public class RegController {
	LinkedHashMap<String, Institute> instHashMap = new LinkedHashMap<String, Institute>();
	LinkedHashMap<String, String> otpKeyValue = new LinkedHashMap<String, String>();
	LinkedHashMap<Integer, String> temp = new LinkedHashMap<Integer, String>();
	LinkedHashMap<String, Staff> staffHashMap = new LinkedHashMap<String, Staff>();
	RestTemplate rest = new RestTemplate();
	Instant start=null;
	public Instant pricipalOtpStart=null;

	@RequestMapping(value = "/getInstituteMasterByAishe", method = RequestMethod.GET)
	public @ResponseBody InstituteMaster getInstituteMasterByAishe(HttpServletRequest request,
			HttpServletResponse response) {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		InstituteMaster imaster = null;

		try {

			String aisheCode = request.getParameter("aishe_code");

			map = new LinkedMultiValueMap<String, Object>();

			map.add("aisheCode", aisheCode);

			imaster = rest.postForObject(Constants.url + "getInstituteMasterByAishe", map, InstituteMaster.class);
			if (imaster == null) {
				System.err.println("Null IMASTER");
				imaster = new InstituteMaster();
				imaster.setMhInstId(-1);
			}

			String res = rest.postForObject(Constants.url + "checkAisheCodeDuplication", map, String.class);
			if (res.equals("unique")) {
				System.err.println("Unique");
			} else {
				System.err.println("Already Exists");
				imaster = new InstituteMaster();
			}

		} catch (Exception e) {
			System.err.println("Exce in imaster " + e.getMessage());
			e.printStackTrace();
		}

		return imaster;

	}

	@RequestMapping(value = "/getInstituteMasterByAisheforPrincipal", method = RequestMethod.GET)
	public @ResponseBody InstituteMaster getInstituteMasterByAisheforPrincipal(HttpServletRequest request,
			HttpServletResponse response) {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		InstituteMaster imaster = null;

		try {

			String aisheCode = request.getParameter("aishe_code");

			map = new LinkedMultiValueMap<String, Object>();

			map.add("aisheCode", aisheCode);

			imaster = rest.postForObject(Constants.url + "getInstituteMasterByAishe", map, InstituteMaster.class);

		} catch (Exception e) {
			System.err.println("Exce in imaster " + e.getMessage());
			e.printStackTrace();
		}

		return imaster;

	}

	@RequestMapping(value = "/getInstituteMasterByAisheforPrincipalChange", method = RequestMethod.GET)
	public @ResponseBody Institute getInstituteMasterByAisheforPrincipalChange(HttpServletRequest request,
			HttpServletResponse response) {

		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

		Institute imaster = null;

		try {

			String aisheCode = request.getParameter("aishe_code");

			map = new LinkedMultiValueMap<String, Object>();

			map.add("aisheCode", aisheCode);

			imaster = rest.postForObject(Constants.url + "getInstituteMasterByAisheForChangePrincipal", map,
					Institute.class);

		} catch (Exception e) {
			System.err.println("Exce in imaster " + e.getMessage());
			e.printStackTrace();
		}

		return imaster;

	}

	@RequestMapping(value = "/insertInstituteDemo", method = RequestMethod.POST)
	public ModelAndView insertInstitute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		ModelAndView model = new ModelAndView("confirmInstReg");
		int instId = Integer.parseInt(request.getParameter("inst_id"));
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("isCurrent", 1);
			AcademicYear acYear1 = restTemplate.postForObject(Constants.url + "getAcademicYearByIsCurrent", map,
					AcademicYear.class);
			int yearId = acYear1.getYearId();

			String exVar = "";
			if (instId == 0) {
				Institute institute = new Institute();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				String aisheCode = request.getParameter("aishe_code");
				institute.setAisheCode(aisheCode);

				institute.setCheckerDatetime(curDateTime);
				institute.setCheckerUserId(0);

				institute.setContactNo(request.getParameter("princ_contact"));
				institute.setDelStatus(1);
				institute.setEmail(request.getParameter("princ_email"));

				institute.setExInt1(yearId); // academic Year
				institute.setExInt2(0); // is_approved

				institute.setExVar1(exVar);
				institute.setExVar2(exVar);

				institute.setInstituteAdd(request.getParameter("inst_add"));
				institute.setInstituteId(instId);
				institute.setInstituteName(request.getParameter("inst_name"));

				institute.setIsActive(1);
				institute.setIsEnrollSystem(0);// set to 1 when user loged in for first time and changed his/her
												// pass.
												// Initially its zero
				int isReg = Integer.parseInt(request.getParameter("is_registration"));
				institute.setIsRegistration(isReg);

				institute.setLastUpdatedDatetime(curDateTime);
				institute.setMakerEnterDatetime(curDateTime);
				institute.setMakerUserId(0);// user id who is creating this record for ex principal is
											// user who creates
				// iqac
				// and hod to student

				institute.setPresidentName(request.getParameter("pres_name"));
				institute.setPrincipalName(request.getParameter("princ_name"));
				if (isReg == 1)
					institute.setRegDate(request.getParameter("reg_date"));
				institute.setTrustAdd(request.getParameter("trusty_add"));

				institute.setTrustContactNo(request.getParameter("trusty_con_no"));
				institute.setTrustName(request.getParameter("trusty_name"));
				institute.setUserType(0);// for institute its 0

				institute.setPresidenContact(request.getParameter("pres_contact"));
				institute.setPresidentEmail(request.getParameter("pres_email"));

				institute.setVillage(request.getParameter("village"));
				institute.setTaluka(request.getParameter("taluka"));
				institute.setDistrict(request.getParameter("district"));
				institute.setState(request.getParameter("state"));
				institute.setPincode(request.getParameter("pin"));

				System.out.println("Data--------------" + institute.toString());

				// Institute info = restTemplate.postForObject(Constants.url + "saveInstitute",
				// institute,
				// Institute.class);

				instHashMap.put(institute.getContactNo(), institute);
				model.addObject("editInst", institute);
				System.err.println("instHashMap  size  " + instHashMap.size());

			} else {

			}

		} catch (Exception e) {

			System.err.println(" Exception In insertInstituteDemo at Reg Contr " + e.getMessage());

			e.printStackTrace();

		}
		return model;

	}

	// Change Priciple work start

	@RequestMapping(value = "/showChangePrincipalForm", method = RequestMethod.GET)
	public String showChangePrincipalForm(HttpServletRequest request, HttpServletResponse response, Model model) {

		String mav = new String();
		try {

			mav = "showChangePrincipalForm";

		} catch (Exception e) {

			// System.err.println("exception In showInstituteRegistrationForm at home Contr"
			// + e.getMessage());
			mav = "redirect:/";
			e.printStackTrace();

		}

		return mav;

	}

	@RequestMapping(value = "/insertChangePrinciple", method = RequestMethod.POST)
	public ModelAndView insertChangePrinciple(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("confirmChangePrinci");

		try {

			int facultyId = 0;

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			String curDateTime = dateFormat.format(cal.getTime());
			// System.out.println("Data:" + facultyId);
			String aisheCode = request.getParameter("aishe_code");
			String inst_name = request.getParameter("inst_name");
			String princ_name = request.getParameter("princ_name");
			String princ_contact = request.getParameter("princ_contact");
			int instId = Integer.parseInt(request.getParameter("instId"));
			String princ_email = request.getParameter("princ_email");
			String roleId=Constants.Princ_Role;

			Staff staff = new Staff();

			staff.setEmail(princ_email);
			staff.setDeptId("0");
			staff.setFacultyFirstName(princ_name);
			staff.setFacultyMiddelName("");
			staff.setFacultyLastName("");
			staff.setFacultyId(facultyId);
			staff.setHighestQualification(0);
			staff.setHightestQualificationYear("");
			staff.setIsAccOff(0);
			staff.setIsDean(0);
			staff.setIsFaculty(1);
			staff.setIsHod(0);
			staff.setIsIqac(0);
			staff.setIsLibrarian(0);
			staff.setIsPrincipal(1);

			staff.setIsStudent(0);
			staff.setIsWorking(1);
			staff.setJoiningDate(curDateTime);
			staff.setLastUpdatedDatetime(curDateTime);
			staff.setMakerEnterDatetime(curDateTime);

			staff.setPassword("");
			staff.setRealivingDate(null);
			staff.setRoleIds(roleId);
			staff.setTeachingTo(0);
			staff.setType(1);

			staff.setInstituteId(instId);
			staff.setJoiningDate(curDateTime);
			staff.setContactNo(princ_contact);
			staff.setEmail(princ_email);
			staff.setDelStatus(0);
			staff.setIsActive(0);
			staff.setMakerUserId(0);
			staff.setMakerEnterDatetime(curDateTime);
			staff.setCheckerUserId(0);
			staff.setCheckerDatetime(curDateTime);
			staff.setLastUpdatedDatetime(curDateTime);
			staff.setIsEnrolled(0);

			staff.setIsSame(0);

			staff.setExtravarchar1("NA");

			staffHashMap.put(staff.getContactNo(), staff);
			temp.put(1, aisheCode);
			temp.put(2, inst_name);

			// System.out.println("Staff*********:" + staff.toString());
			model.addObject("editInst", staff);
			model.addObject("aishe", aisheCode);
			model.addObject("instName", inst_name);

		} catch (

		Exception e) {

			System.err.println("exception In showRegPrincipal at Principal Contr" + e.getMessage());
			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/showOtpPageForChangePrinci", method = RequestMethod.POST)
	public ModelAndView showOtpPageForChangePrinci(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("ask_otp_chPrinci");
		try {

			int isBack = Integer.parseInt(request.getParameter("is_back"));
			String otpNo = request.getParameter("otp_no");
			
			if (isBack == 0) {
				
				System.err.println("in If.  Its Confirm Button Pressed");

				model = new ModelAndView("ask_otp_chPrinci");
				model.addObject("expFlag", 0);
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				String otp = getNumericOtp(6);
				String otpKey = getIntegerKey(4);
				System.err.println("OTP is " + otp);

				String msg = " OTP for Change Principal is " + otp + ". Do not share OTP with anyone. RUSA Maharashtra";

				map.add("senderID", "RUSAMH");
				map.add("user", "spdrusamah@gmail.com:Cyber@mva");
				map.add("receipientno", otpNo.trim());
				map.add("dcs", "0");
				map.add("msgtxt", msg);
				map.add("state", "4");
				String res = restTemplate.postForObject("http://api.mVaayoo.com/mvaayooapi/MessageCompose", map,
						String.class);
				
			
			  System.err.println("in showOtpPageForChangePrinci  time start  is "+start);

				otpKeyValue.put(otpKey, otp);

				model.addObject("otpk", otpKey);
				model.addObject("otpNo", otpNo);
				  pricipalOtpStart = Instant.now();
			 System.out.println();
				System.err.println("in start submit time   is "+start);

			} else {
				

				model = new ModelAndView("showChangePrincipalForm");
				Staff editInst = staffHashMap.get(otpNo);
				 String aisheCode =temp.get(1);
				 String inst_name =temp.get(2);
				 System.err.println("in Else  its Back button Pressed "+editInst.toString() );
				model.addObject("editInst", editInst);
				model.addObject("aishe", aisheCode);
				model.addObject("instName", inst_name);
			}
		} catch (Exception e) {

			System.err.println("Exce in showing otp page " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/verifyOtpAndChangePrincipal", method = RequestMethod.POST)
	public ModelAndView verifyOtpAndChangePrincipal(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		
		try {
			String enteredOtp = request.getParameter("entered_otp");
			String otpk = request.getParameter("otpk");
			String otpNo = request.getParameter("otpNo");

			String storedOtp = otpKeyValue.get(otpk);
			if (enteredOtp.equals(storedOtp)) {
				System.err.println("Otp Matched ");
				
				Instant end = Instant.now();
				System.err.println("in final submit time end  is "+end);
				System.err.println("start was " +start);

				Duration timeElapsed = Duration.between(pricipalOtpStart, end);

				Staff editInst = staffHashMap.get(otpNo);
				System.out.println("Time taken: OTPVerification "+ timeElapsed.getSeconds() +" seconds");
				if(timeElapsed.getSeconds()<=120) {
				Staff hod = rest.postForObject(Constants.url + "/addNewStaff", editInst, Staff.class);
				model = new ModelAndView("login");

				model.addObject("msg", "Pricipal Changed Sucessfully");
				}
				else {
					model = new ModelAndView("ask_otp_chPrinci");
					model.addObject("otpk", otpk);
					model.addObject("otpNo", otpNo);
					model.addObject("expFlag", 1);
					
				 
					model.addObject("msg", "Time out! Regenerate OTP");
				}
			  

			} else {

				System.err.println("Otp not matched Please re enter");
				model = new ModelAndView("ask_otp_chPrinci");
				model.addObject("expFlag", 0);
				model.addObject("otpk", otpk);
				model.addObject("otpNo", otpNo);
				model.addObject("msg", "OTP didnt matched. Please Re Enter");

			}

		} catch (Exception e) {

			System.err.println("Exce in verifyOtpAndRegisterInstitute " + e.getMessage());
			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/reGenOtpForChangePrincipal", method = RequestMethod.GET)
	public @ResponseBody NameIdBean reGenOtpForChangePrincipal(HttpServletRequest request, HttpServletResponse response) {

		NameIdBean bean = new NameIdBean();

		try {
			String otpk = request.getParameter("otpk");
			

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String otpNo = request.getParameter("otp_no");
			String otp = getNumericOtp(6);
			String otpKey = otpk;

			String msg = " OTP for  Change Principal is " + otp + " (test message)";

			map.add("senderID", "RUSAMH");
			map.add("user", "spdrusamah@gmail.com:Cyber@mva");
			map.add("receipientno", otpNo.trim());
			map.add("dcs", "0");
			map.add("msgtxt", msg);
			map.add("state", "4");
			String res = restTemplate.postForObject("http://api.mVaayoo.com/mvaayooapi/MessageCompose", map,
					String.class);

			bean.setName(otp);
			otpKeyValue.put(otpKey, otp);

		} catch (Exception e) {
			System.err.println("Exce In ReGenerating OTP  " + e.getMessage());
			e.printStackTrace();
		}
		return bean;

	}
	// Change Priciple work end

	// showOtpPage

	@RequestMapping(value = "/showOtpPage", method = RequestMethod.POST)
	public ModelAndView showOtpPage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("ask_otp");
		try {

			int isBack = Integer.parseInt(request.getParameter("is_back"));
			String otpNo = request.getParameter("otp_no");

			if (isBack == 0) {
				System.err.println("in If.  Its Confirm Button Pressed");

				model = new ModelAndView("ask_otp");
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				String otp = getNumericOtp(6);
				String otpKey = getIntegerKey(4);
				System.err.println("OTP is " + otp);

				String msg = " OTP for  Insitute Registration is " + otp
						+ ". Do not share OTP with anyone. RUSA Maharashtra";

				map.add("senderID", "RUSAMH");
				map.add("user", "spdrusamah@gmail.com:Cyber@mva");
				map.add("receipientno", otpNo.trim());
				map.add("dcs", "0");
				map.add("msgtxt", msg);
				map.add("state", "4");
				String res = restTemplate.postForObject("http://api.mVaayoo.com/mvaayooapi/MessageCompose", map,
						String.class);

				otpKeyValue.put(otpKey, otp);

				model.addObject("otpk", otpKey);
				model.addObject("otpNo", otpNo);

				//// System.out.println(res);
			} else {
				System.err.println("in Else  its Back button Pressed ");

				model = new ModelAndView("instituteRegistration");
				Institute editInst = instHashMap.get(otpNo);

				model.addObject("isEdit", 1);

				try {
					editInst.setRegDate(DateConvertor.convertToDMY(editInst.getRegDate()));
				} catch (Exception e) {

				}

				model.addObject("editInst", editInst);

			}
		} catch (Exception e) {

			System.err.println("Exce in showing otp page " + e.getMessage());
			e.printStackTrace();
		}

		return model;
	}

	// verifyOtpAndRegisterInstitute
	@RequestMapping(value = "/verifyOtpAndRegisterInstitute", method = RequestMethod.POST)
	public ModelAndView verifyOtpAndRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		String redirect = null;
		try {
			String enteredOtp = request.getParameter("entered_otp");
			String otpk = request.getParameter("otpk");
			String otpNo = request.getParameter("otpNo");

			String storedOtp = otpKeyValue.get(otpk);
			if (enteredOtp.equals(storedOtp)) {
				System.err.println("Otp Matched ");

				Institute editInst = instHashMap.get(otpNo);
				editInst.setRegDate(DateConvertor.convertToYMD(editInst.getRegDate()));

				Institute info = rest.postForObject(Constants.url + "saveInstitute", editInst, Institute.class);

				model = new ModelAndView("login");

				model.addObject("msg", "Registration Successfull");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);

				AcademicYear[] quolArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId", map,
						AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);

				// redirect="redirect:/";

			} else {

				System.err.println("Otp not matched Please re enter");
				model = new ModelAndView("ask_otp");

				model.addObject("otpk", otpk);
				model.addObject("otpNo", otpNo);
				model.addObject("msg", "OTP didnt matched. Please Re Enter");
				// redirect="redirect:/reEnterOtp/"+otpk+"/"+otpNo;

			}

		} catch (Exception e) {

			System.err.println("Exce in verifyOtpAndRegisterInstitute " + e.getMessage());
			e.printStackTrace();

		}

		return model;

	}

	// reGenOtp

	@RequestMapping(value = "/reGenOtp", method = RequestMethod.GET)
	public @ResponseBody NameIdBean reGenOtp(HttpServletRequest request, HttpServletResponse response) {

		NameIdBean bean = new NameIdBean();

		try {
			String otpk = request.getParameter("otpk");
			

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String otpNo = request.getParameter("otp_no");
			String otp = getNumericOtp(6);
			String otpKey = otpk;

			String msg = " OTP for  Insitute Registration is " + otp + " (test message)";

			map.add("senderID", "RUSAMH");
			map.add("user", "spdrusamah@gmail.com:Cyber@mva");
			map.add("receipientno", otpNo.trim());
			map.add("dcs", "0");
			map.add("msgtxt", msg);
			map.add("state", "4");
			String res = restTemplate.postForObject("http://api.mVaayoo.com/mvaayooapi/MessageCompose", map,
					String.class);

			bean.setName(otp);
			otpKeyValue.put(otpKey, otp);

		} catch (Exception e) {
			System.err.println("Exce In ReGenerating OTP  " + e.getMessage());
			e.printStackTrace();
		}
		return bean;

	}

	static String getNumericOtp(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "0123456789";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	static String getIntegerKey(int n) {

		// chose a Character random from this String
		String AlphaNumericString = "a0123456789P";

		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			// generate a random number between
			// 0 to AlphaNumericString variable length
			int index = (int) (AlphaNumericString.length() * Math.random());

			// add Character one by one in end of sb
			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

}
