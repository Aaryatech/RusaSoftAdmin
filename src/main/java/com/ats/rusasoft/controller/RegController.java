package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

@Controller
@Scope("session")
public class RegController {
	LinkedHashMap<String, Institute> instHashMap = new LinkedHashMap<String,Institute>();
	LinkedHashMap<String, String> otpKeyValue = new LinkedHashMap<String,String>();

	RestTemplate rest = new RestTemplate();
	
	
	
	@RequestMapping(value = "/getInstituteMasterByAishe", method = RequestMethod.GET)
	public @ResponseBody InstituteMaster  getInstituteMasterByAishe(HttpServletRequest request, HttpServletResponse response) {
		
		MultiValueMap<String, Object> map =new LinkedMultiValueMap<String, Object>();
		
		InstituteMaster imaster=null;
		
		try {
			
		String aisheCode=request.getParameter("aishe_code");
		
		map =new LinkedMultiValueMap<String, Object>(); 
		
		map.add("aisheCode", aisheCode);
			
		  imaster = rest.postForObject(Constants.url + "getInstituteMasterByAishe", map,
				 InstituteMaster.class);
		  if(imaster==null) {
			  System.err.println("Null IMASTER");
			  imaster=new InstituteMaster();
			  imaster.setMhInstId(-1);
		  }
		  
		  String res = rest.postForObject(Constants.url + "checkAisheCodeDuplication", map,
				  String.class);
		  if(res.equals("unique")) {
			  System.err.println("Unique");
		  }else {
			  System.err.println("Already Exists");
			  imaster=new InstituteMaster();
		  }
			
		}catch (Exception e) {
			System.err.println("Exce in imaster " +e.getMessage());
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
			AcademicYear acYear1  = restTemplate.postForObject(Constants.url + "getAcademicYearByIsCurrent", map,
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

				institute.setExInt1(yearId);	//academic Year
				institute.setExInt2(0);			//is_approved
				
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
					institute.setRegDate(DateConvertor.convertToYMD(request.getParameter("reg_date")));
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

				System.out.println("Data--------------"+institute.toString());

				//Institute info = restTemplate.postForObject(Constants.url + "saveInstitute", institute,
					//	Institute.class);
				
				instHashMap.put(institute.getContactNo(), institute);
				model.addObject("editInst", institute);
				System.err.println("instHashMap  size  " +instHashMap.size());

			} else {

				
			}
			
		} catch (Exception e) {

			System.err.println(" Exception In insertInstituteDemo at Reg Contr " + e.getMessage());

			e.printStackTrace();

		}
		return model;

	}
	
	
	//showOtpPage
	
	@RequestMapping(value = "/showOtpPage", method = RequestMethod.POST)
	public ModelAndView showOtpPage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("ask_otp");
		try {

			
			int isBack=Integer.parseInt(request.getParameter("is_back"));
			String otpNo=request.getParameter("otp_no");

			if(isBack==0) {
				System.err.println("in If.  Its Confirm Button Pressed" );

				model = new ModelAndView("ask_otp");
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String otp=getNumericOtp(6);
			String otpKey=getIntegerKey(4);
			
			String msg=" OTP for  Insitute Registration is " +otp+ ". Do not share OTP with anyone. RUSA Maharashtra";
		 
					map.add("senderID", "RUSAMH");
					map.add("user", "spdrusamah@gmail.com:Cyber@mva");
					map.add("receipientno", otpNo.trim());
					map.add("dcs", "0");
					map.add("msgtxt",msg);
					map.add("state", "4");
			String res = restTemplate.postForObject("http://api.mVaayoo.com/mvaayooapi/MessageCompose", map,
					String.class);	
			
			otpKeyValue.put(otpKey, otp);
			
			model.addObject("otpk", otpKey);
			model.addObject("otpNo", otpNo);
			
			////System.out.println(res);
			}else {
				System.err.println("in Else  its Back button Pressed " );
				
				model = new ModelAndView("instituteRegistration");
				Institute editInst=instHashMap.get(otpNo);
				try {
				editInst.setRegDate(DateConvertor.convertToDMY(editInst.getRegDate()));
				}catch (Exception e) {
					
				}
				
				model.addObject("editInst", editInst);
				
			}
		}catch (Exception e) {
			
			System.err.println("Exce in showing otp page " +e.getMessage());
			e.printStackTrace();
		}

		return model;
	}
	
	//verifyOtpAndRegisterInstitute
	@RequestMapping(value = "/verifyOtpAndRegisterInstitute", method = RequestMethod.POST)
	public ModelAndView  verifyOtpAndRegisterInstitute(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model=null;
		String redirect=null;
		try {
		String enteredOtp=request.getParameter("entered_otp");
		String otpk=request.getParameter("otpk");
		String otpNo=request.getParameter("otpNo");
		
		String storedOtp=otpKeyValue.get(otpk);
		if(enteredOtp.equals(storedOtp)) {
			System.err.println("Otp Matched ");
			
			Institute info = rest.postForObject(Constants.url + "saveInstitute", instHashMap.get(otpNo),
					Institute.class);
			
			model = new ModelAndView("login");
			
			model.addObject("msg","Registration Successfull");
			
			
			MultiValueMap<String, Object> map =new LinkedMultiValueMap<String, Object>();

			map =new LinkedMultiValueMap<String, Object>(); 
			 map.add("type", 1);
			
			AcademicYear[] quolArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId", map, AcademicYear[].class);
			List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("acaYearList " + acaYearList.toString());

			model.addObject("acaYearList", acaYearList);

			
			//redirect="redirect:/";
			
		}
		else {
			
			System.err.println("Otp not matched Please re enter");
			model = new ModelAndView("ask_otp");
			
			model.addObject("otpk", otpk);
			model.addObject("otpNo", otpNo);
			model.addObject("msg","OTP didnt matched. Please Re Enter");
			//redirect="redirect:/reEnterOtp/"+otpk+"/"+otpNo;
			
		}
		
		}catch (Exception e) {
		
		System.err.println("Exce in verifyOtpAndRegisterInstitute " +e.getMessage());
		e.printStackTrace();
		
			
		}
		
		return model;
	
	}
	
	//reGenOtp
	
	@RequestMapping(value = "/reGenOtp", method = RequestMethod.GET)
	public @ResponseBody NameIdBean  reGenOtp(HttpServletRequest request, HttpServletResponse response) {
		
		NameIdBean bean=new NameIdBean();
		
		
		try {
			String otpk=request.getParameter("otpk");
 
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String otpNo=request.getParameter("otp_no");
			String otp=getNumericOtp(6);
			String otpKey=otpk;
			
			String msg=" OTP for  Insitute Registration is " +otp + " (test message)";
		 
					map.add("senderID", "RUSAMH");
					map.add("user", "spdrusamah@gmail.com:Cyber@mva");
					map.add("receipientno", otpNo.trim());
					map.add("dcs", "0");
					map.add("msgtxt",msg);
					map.add("state", "4");
			String res = restTemplate.postForObject("http://api.mVaayoo.com/mvaayooapi/MessageCompose", map,
					String.class);	
			
			bean.setName(otp);
			otpKeyValue.put(otpKey, otp);
			
		}
		catch (Exception e) {
			System.err.println("Exce In ReGenerating OTP  " +e.getMessage());
			e.printStackTrace();
		}
		return bean;
		
	}
	
	
	
	static String getNumericOtp(int n) {

		// chose a Character random from this String
		String AlphaNumericString ="0123456789";

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
		String AlphaNumericString ="a0123456789P";

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
