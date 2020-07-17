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

import com.ats.rusasoft.XssEscapeUtils;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.commons.FormValidation;
import com.ats.rusasoft.commons.SessionKeyGen;
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
	LinkedHashMap<Integer, String> temp = new LinkedHashMap<>();
	LinkedHashMap<String, Staff> staffHashMap = new LinkedHashMap<String, Staff>();
	RestTemplate rest = new RestTemplate();
	Instant start = null;
	public Instant pricipalOtpStart = null;

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
		int instId = 0;
		try {
			instId = Integer.parseInt(request.getParameter("inst_id"));
		} catch (Exception e) {
			instId = 0;
		}
		String redirect = null;
		try {

			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("isCurrent", 1);
				AcademicYear acYear1 = restTemplate.postForObject(Constants.url + "getAcademicYearByIsCurrent", map,
						AcademicYear.class);
				int yearId = acYear1.getYearId();

				String aisheCode = request.getParameter("aishe_code");
				String pricplContact = request.getParameter("princ_contact");
				String princplEmail = request.getParameter("princ_email");
				String instAdd = request.getParameter("inst_add");
				String instName = request.getParameter("inst_name");

				String prsidName = request.getParameter("pres_name");
				String princplName = request.getParameter("princ_name");
				String trustyAdd = request.getParameter("trusty_add");
				String trustyCont = request.getParameter("trusty_con_no");
				String trustyName = request.getParameter("trusty_name");
				String presContact = request.getParameter("pres_contact");
				String presEmail = request.getParameter("pres_email");

				String village = request.getParameter("village");
				String taluka = request.getParameter("taluka");
				String district = request.getParameter("district");
				String state = request.getParameter("state");
				String pin = request.getParameter("pin");

				String regDate1 = request.getParameter("reg_date");

				String isReg1 = request.getParameter("is_registration");

				String exVar1 = request.getParameter("inst_type");

				String exVar2 = request.getParameter("autonomy");

				Boolean error = false;

				if (FormValidation.Validaton(aisheCode, "") == true
						|| FormValidation.Validaton(pricplContact, "mobile") == true
						|| FormValidation.Validaton(princplEmail, "email") == true
						|| FormValidation.Validaton(instAdd, "") == true
						|| FormValidation.Validaton(prsidName, "") == true
						|| FormValidation.Validaton(princplName, "") == true
						|| FormValidation.Validaton(trustyAdd, "") == true
						|| FormValidation.Validaton(trustyCont, "") == true
						|| FormValidation.Validaton(trustyName, "") == true
						|| FormValidation.Validaton(instName, "") == true

						|| FormValidation.Validaton(presContact, "") == true
						|| FormValidation.Validaton(presEmail, "email") == true

						|| FormValidation.Validaton(village, "") == true || FormValidation.Validaton(taluka, "") == true

						|| FormValidation.Validaton(district, "") == true || FormValidation.Validaton(state, "") == true

						|| FormValidation.Validaton(isReg1, "") == true || FormValidation.Validaton(pin, "") == true

						|| FormValidation.Validaton(exVar1, "") == true
						|| FormValidation.Validaton(exVar2, "") == true) {

					error = true;
				}
				if (Integer.parseInt(isReg1) == 1) {
					if (FormValidation.Validaton(regDate1, "date") == true) {
						error = true;
					}
				}

				if (error == false) {

					String exVar = "";
					if (instId == 0) {
						Institute institute = new Institute();

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						String curDateTime = dateFormat.format(cal.getTime());

						// String aisheCode = request.getParameter("aishe_code");
						institute.setAisheCode(XssEscapeUtils.jsoupParse(aisheCode));

						institute.setCheckerDatetime(curDateTime);
						institute.setCheckerUserId(0);

						institute.setContactNo(XssEscapeUtils.jsoupParse(request.getParameter("princ_contact")));
						institute.setDelStatus(1);
						institute.setEmail(XssEscapeUtils.jsoupParse(request.getParameter("princ_email")));

						institute.setExInt1(yearId); // academic Year
						institute.setExInt2(0); // is_approved

						// institute.setExVar1(exVar);
						institute.setExVar1(XssEscapeUtils.jsoupParse(request.getParameter("inst_type")));

						institute.setExVar2(XssEscapeUtils.jsoupParse(request.getParameter("autonomy")));

						institute.setInstituteAdd(XssEscapeUtils.jsoupParse(request.getParameter("inst_add")));
						institute.setInstituteId(instId);
						institute.setInstituteName(XssEscapeUtils.jsoupParse(request.getParameter("inst_name")));

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

						institute.setPresidentName(XssEscapeUtils.jsoupParse(request.getParameter("pres_name")));
						institute.setPrincipalName(XssEscapeUtils.jsoupParse(request.getParameter("princ_name")));
						if (isReg == 1)
							institute.setRegDate(XssEscapeUtils.jsoupParse(request.getParameter("reg_date")));
						institute.setTrustAdd(XssEscapeUtils.jsoupParse(request.getParameter("trusty_add")));

						institute.setTrustContactNo(XssEscapeUtils.jsoupParse(request.getParameter("trusty_con_no")));
						institute.setTrustName(XssEscapeUtils.jsoupParse(request.getParameter("trusty_name")));
						institute.setUserType(0);// for institute its 0

						institute.setPresidenContact(XssEscapeUtils.jsoupParse(request.getParameter("pres_contact")));
						institute.setPresidentEmail(XssEscapeUtils.jsoupParse(request.getParameter("pres_email")));

						institute.setVillage(XssEscapeUtils.jsoupParse(request.getParameter("village")));
						institute.setTaluka(XssEscapeUtils.jsoupParse(request.getParameter("taluka")));
						institute.setDistrict(XssEscapeUtils.jsoupParse(request.getParameter("district")));
						institute.setState(XssEscapeUtils.jsoupParse(request.getParameter("state")));
						institute.setPincode(XssEscapeUtils.jsoupParse(request.getParameter("pin")));

						System.out.println("Data institute --------------" + institute.toString());

						// Institute info = restTemplate.postForObject(Constants.url + "saveInstitute",
						// institute,
						// Institute.class);

						instHashMap.put(institute.getContactNo(), institute);
						model.addObject("editInst", institute);
						System.err.println("instHashMap  size  " + instHashMap.size());

					} else {

					}
				} // end of if isError=false
				else {
					model = new ModelAndView("improperData");
				}

			} else {

				model = new ModelAndView("accessDenied");

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

			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

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
				String roleId = Constants.Princ_Role;

				Staff staff = new Staff();

				staff.setEmail(XssEscapeUtils.jsoupParse(princ_email));
				staff.setDeptId("0");
				staff.setFacultyFirstName(XssEscapeUtils.jsoupParse(princ_name));
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
				staff.setContactNo(XssEscapeUtils.jsoupParse(princ_contact));
				staff.setEmail(XssEscapeUtils.jsoupParse(princ_email));
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
				temp.put(1, XssEscapeUtils.jsoupParse(aisheCode));
				temp.put(2, XssEscapeUtils.jsoupParse(inst_name));

				// System.out.println("Staff*********:" + staff.toString());
				model.addObject("editInst", staff);
				model.addObject("aishe", XssEscapeUtils.jsoupParse(aisheCode));
				model.addObject("instName", XssEscapeUtils.jsoupParse(inst_name));
			} else {
				model = new ModelAndView("accessDenied");
			}
			SessionKeyGen.changeSessionKey(request);
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

				/*
				 * map.add("senderID", "RUSAMH"); map.add("user",
				 * "spdrusamah@gmail.com:Cyber@mva"); map.add("receipientno", otpNo.trim());
				 * map.add("dcs", "0"); map.add("msgtxt", msg); map.add("state", "4"); String
				 * res = restTemplate.postForObject(
				 * "http://api.mVaayoo.com/mvaayooapi/MessageCompose", map, String.class);
				 */

				map.add("username", "rusamah-wb");
				map.add("password", "Rus@@123456");
				map.add("senderid", "MHRUSA");
				map.add("mobileno", otpNo.trim());
				map.add("content", msg);
				map.add("smsservicetype", "singlemsg");
				String sms = restTemplate.postForObject("https://msdgweb.mgov.gov.in/esms/sendsmsrequest", map,
						String.class);

				System.err.println("in showOtpPageForChangePrinci  time start  is " + start);

				otpKeyValue.put(otpKey, otp);

				model.addObject("otpk", otpKey);
				model.addObject("otpNo", otpNo);
				pricipalOtpStart = Instant.now();
				System.out.println();
				System.err.println("in start submit time   is " + start);

			} else {

				model = new ModelAndView("showChangePrincipalForm");
				Staff editInst = staffHashMap.get(otpNo);
				String aisheCode = temp.get(1);
				String inst_name = temp.get(2);
				System.err.println("in Else  its Back button Pressed " + editInst.toString());
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
			String enteredOtp = XssEscapeUtils.jsoupParse(request.getParameter("entered_otp"));
			String otpk = XssEscapeUtils.jsoupParse(request.getParameter("otpk"));
			String otpNo = XssEscapeUtils.jsoupParse(request.getParameter("otpNo"));
			otpk=otpk.replaceAll("\\(.*?\\)" ,"");
			otpNo=otpNo.replaceAll("\\(.*?\\)" ,"");

			String storedOtp = otpKeyValue.get(otpk);
			if (enteredOtp.equals(storedOtp)) {
				System.err.println("Otp Matched ");

				Instant end = Instant.now();
				System.err.println("in final submit time end  is " + end);
				System.err.println("start was " + start);

				Duration timeElapsed = Duration.between(pricipalOtpStart, end);

				Staff editInst = staffHashMap.get(otpNo);
				System.out.println("Time taken: OTPVerification " + timeElapsed.getSeconds() + " seconds");
				if (timeElapsed.getSeconds() <= 120) {
					Staff hod = rest.postForObject(Constants.url + "/addNewStaff", editInst, Staff.class);
					model = new ModelAndView("login");

					model.addObject("msg", "Pricipal Change Requested Sucessfully");
				} else {
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
	public @ResponseBody NameIdBean reGenOtpForChangePrincipal(HttpServletRequest request,
			HttpServletResponse response) {

		NameIdBean bean = new NameIdBean();

		try {
			String otpk = XssEscapeUtils.jsoupParse(request.getParameter("otpk"));

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String otpNo = request.getParameter("otp_no");
			String otp = getNumericOtp(6);
			String otpKey = otpk;

			String msg = " OTP for  Change Principal is " + otp + "";

			/*
			 * map.add("senderID", "RUSAMH"); map.add("user",
			 * "spdrusamah@gmail.com:Cyber@mva"); map.add("receipientno", otpNo.trim());
			 * map.add("dcs", "0"); map.add("msgtxt", msg); map.add("state", "4"); String
			 * res = restTemplate.postForObject(
			 * "http://api.mVaayoo.com/mvaayooapi/MessageCompose", map, String.class);
			 */

			map.add("username", "rusamah-wb");
			map.add("password", "Rus@@123456");
			map.add("senderid", "MHRUSA");
			map.add("mobileno", otpNo.trim());
			map.add("content", msg);
			map.add("smsservicetype", "singlemsg");
			String sms = restTemplate.postForObject("https://msdgweb.mgov.gov.in/esms/sendsmsrequest", map,
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
			int isBack = 1;
			isBack = Integer.parseInt(request.getParameter("is_back"));
			String otpNo = XssEscapeUtils.jsoupParse(request.getParameter("otp_no"));
			Boolean error = false;

			if (FormValidation.Validaton(otpNo, "") == true) {

				error = true;
			}

			if (error == false) {
				if (isBack == 0) {
					System.err.println("in If.  Its Confirm Button Pressed");

					model = new ModelAndView("ask_otp");
					RestTemplate restTemplate = new RestTemplate();

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					String otp = getNumericOtp(6);
					String otpKey = getIntegerKey(4);
					System.err.println("OTP is " + otp);

					String msg = " OTP for  Institute Registration is " + otp
							+ ". Do not share OTP with anyone. RUSA Maharashtra";

					/*
					 * map.add("senderID", "RUSAMH"); map.add("user",
					 * "spdrusamah@gmail.com:Cyber@mva"); map.add("receipientno", otpNo.trim());
					 * map.add("dcs", "0"); map.add("msgtxt", msg); map.add("state", "4"); String
					 * res = restTemplate.postForObject(
					 * "http://api.mVaayoo.com/mvaayooapi/MessageCompose", map, String.class);
					 */

					map.add("username", "rusamah-wb");
					map.add("password", "Rus@@123456");
					map.add("senderid", "MHRUSA");
					map.add("mobileno", otpNo.trim());
					map.add("content", msg);
					map.add("smsservicetype", "singlemsg");
					String sms = restTemplate.postForObject("https://msdgweb.mgov.gov.in/esms/sendsmsrequest", map,
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

			} else {
				model = new ModelAndView("improperData");
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
			String enteredOtp = XssEscapeUtils.jsoupParse(request.getParameter("entered_otp"));
			String otpk = XssEscapeUtils.jsoupParse(request.getParameter("otpk"));
			String otpNo = XssEscapeUtils.jsoupParse(request.getParameter("otpNo"));

			String storedOtp = otpKeyValue.get(otpk);
			if (enteredOtp.equals(storedOtp)) {
				System.err.println("Otp Matched ");

				Institute editInst = instHashMap.get(otpNo);
				if (editInst.getIsRegistration() == 1)
					editInst.setRegDate(DateConvertor.convertToYMD(editInst.getRegDate()));

				Institute info = rest.postForObject(Constants.url + "saveInstitute", editInst, Institute.class);

				model = new ModelAndView("login");

				model.addObject("msg", "Registration Successful- (Wait for RUSA Approval/Contact RUSA)");
				instHashMap.remove(otpNo);
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

			String msg = " OTP for  Institute Registration is " + otp + "";

			/*
			 * map.add("senderID", "RUSAMH"); map.add("user",
			 * "spdrusamah@gmail.com:Cyber@mva"); map.add("receipientno", otpNo.trim());
			 * map.add("dcs", "0"); map.add("msgtxt", msg); map.add("state", "4"); String
			 * res = restTemplate.postForObject(
			 * "http://api.mVaayoo.com/mvaayooapi/MessageCompose", map, String.class);
			 */

			map.add("username", "rusamah-wb");
			map.add("password", "Rus@@123456");
			map.add("senderid", "MHRUSA");
			map.add("mobileno", otpNo.trim());
			map.add("content", msg);
			map.add("smsservicetype", "singlemsg");
			String sms = restTemplate.postForObject("https://msdgweb.mgov.gov.in/esms/sendsmsrequest", map,
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
		String AlphaNumericString = "*a012345(6789P&#";

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
