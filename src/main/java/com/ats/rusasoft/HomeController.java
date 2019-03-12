package com.ats.rusasoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.UserLogin;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.GetUserDetail;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.LoginLog;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	RestTemplate restTemplate = new RestTemplate();

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	/*@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "welcome";
	}*/

	@RequestMapping(value = "/showCMSForm", method = RequestMethod.GET)
	public ModelAndView showCMSForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("cmsForm");

		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView showLoginForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("login");
			
			MultiValueMap<String, Object> map =new LinkedMultiValueMap<String, Object>();

			map =new LinkedMultiValueMap<String, Object>(); 
			 map.add("type", 1);
			
			AcademicYear[] quolArray = restTemplate.postForObject(Constants.url + "getAcademicYearListByTypeId", map, AcademicYear[].class);
			List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("acaYearList " + acaYearList.toString());

			model.addObject("acaYearList", acaYearList);

		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	
	@RequestMapping(value = "/showInstituteRegistrationForm", method = RequestMethod.GET)
	public ModelAndView showInstituteRegistrationForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("instituteRegistration");

			Institute editInst = new Institute();

			model.addObject("editInst", editInst);
		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/showforgotPassForm", method = RequestMethod.GET)
	public ModelAndView showforgotPassForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("forgotPassword");

		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/showWelcomePage", method = RequestMethod.GET)
	public ModelAndView showWelcomePage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("welcome");
			Date date = new Date();
			//DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		//	String formattedDate = dateFormat.format(date);

		//	model.addAttribute("serverTime", formattedDate);


		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/changePassForm", method = RequestMethod.POST)
	public String changePassForm(HttpServletRequest request, HttpServletResponse response) {

		/*ModelAndView model = null;*/
		try {
			HttpSession session = request.getSession();
			
			int userId=(int) session.getAttribute("userId");
			String password=request.getParameter("newPassword");
			
			/*String password="harsha";*/
			System.out.println("parameters are:::"+userId+password);
			MultiValueMap<String, Object> map =new LinkedMultiValueMap<String, Object>();
			map.add("password",password);
			map.add("userId",userId);


			//UserLogin userObj = restTemplate.postForObject(Constants.url+"changePass",map,UserLogin.class);
			
			Info errMsg = restTemplate.postForObject(Constants.url + "changePass", map, Info.class);
			System.out.println(errMsg);

		

		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		 return "redirect:/showWelcomePage";

	}

	@RequestMapping("/loginProcess")
	public ModelAndView helloWorld(HttpServletRequest request, HttpServletResponse res) throws IOException {
		
		String name = request.getParameter("username");
		String password = request.getParameter("userpassword");
		int loginAcYearId=Integer.parseInt(request.getParameter("ac_year_login"));
		
		
		System.out.println("Credential are::::"+name+password);

		ModelAndView mav = new ModelAndView("login");
		HttpSession session = request.getSession();
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		

		try {
			System.out.println("Login Process " + name);

			if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

				mav = new ModelAndView("login");
			} else {
				MultiValueMap<String, Object> map =new LinkedMultiValueMap<String, Object>();
				map.add("username",name);
				map.add("password",password);
				map.add("isBlock",1);


				LoginResponse userObj = restTemplate.postForObject(Constants.url+"login",map,LoginResponse.class);
				System.out.println("JSON Response Objet " + userObj.toString());
				String loginResponseMessage="";

				
				if (userObj!=null) {
					
					int a=userObj.getExInt1();
					 if(a==1) {
						 mav = new ModelAndView("welcome");
					}
					 else {
						 mav = new ModelAndView("changePassword");
					 }
					
						System.out.println("Login Successful");
						
						
						
					 

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						String curDateTime = dateFormat.format(cal.getTime());

						DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

						String curDate = dateFormatStr.format(new Date());
						
						InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
				        String hostName = addr.getHostName(); 
				        String userAgent = request.getHeader("User-Agent"); 
						
						LoginLog llog=new LoginLog();
						
						llog.setIpAddress(String.valueOf(addr));
						llog.setUserAgent(userAgent);
						llog.setUserId(userObj.getUserId());
				
						llog.setLoginDate(curDateTime);
						
						llog.setDelStatus(1);
						llog.setExInt1(1);
						llog.setExInt2(1);
						llog.setExVar1("NA");
						llog.setExVar2("NA");
						
					
						RestTemplate rest = new RestTemplate();
						LoginLog editInst = rest.postForObject(Constants.url + "saveLoginLog", llog, LoginLog.class);		 
					 
					 
					 
				
					session.setAttribute("userName", name);
					session.setAttribute("password", password);
					/*session.setAttribute("isEnroll", userObj.getExInt1());
					session.setAttribute("userId", userObj.getUserId());*/
					
					session.setAttribute("userObj", userObj);
					
					int instituteId=userObj.getGetData().getUserInstituteId();
					System.err.println("Institue Id:"+instituteId);
					session.setAttribute("instituteId", instituteId);
					
					int userId = userObj.getUserId();
					System.err.println("User Id:"+userId);
					session.setAttribute("userId", userId);
				 
					 
					map =new LinkedMultiValueMap<String, Object>();
					int typeId=userObj.getUserType();
					map.add("typeId", typeId);
					
					 
					 map =new LinkedMultiValueMap<String, Object>(); 
					map.add("userId", userId);
					System.out.println("Before web service");
					try {
					 ParameterizedTypeReference<List<ModuleJson>> typeRef = new ParameterizedTypeReference<List<ModuleJson>>() {
					 };
					ResponseEntity<List<ModuleJson>> responseEntity = restTemplate.exchange(Constants.url + "getRoleJson",
							HttpMethod.POST, new HttpEntity<>(map), typeRef);
					
					 List<ModuleJson> newModuleList = responseEntity.getBody();
					
					 //System.err.println("new Module List " +newModuleList.toString());
					 
						session.setAttribute("newModuleList", newModuleList);
						
						//sachin

						 map =new LinkedMultiValueMap<String, Object>(); 
						 map.add("type", userObj.getUserType());
						
						AcademicYear[] quolArray = restTemplate.postForObject(Constants.url + "getAcademicYearListByTypeId", map, AcademicYear[].class);
						List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
						System.err.println("acaYearList " + acaYearList.toString());

						session.setAttribute("acaYearList", acaYearList);
						
						session.setAttribute("acYearId", loginAcYearId);
						System.err.println("Session date year Id " +session.getAttribute("acYearId"));

						//getAcademicYearByYearId
						 map =new LinkedMultiValueMap<String, Object>(); 
						 map.add("yearId", loginAcYearId);
						AcademicYear acYear = restTemplate.postForObject(Constants.url + "getAcademicYearByYearId", map, AcademicYear.class);
						session.setAttribute("acYearValue", acYear.getAcademicYear());

					}
					catch (Exception e) {
						e.printStackTrace();
					}
				} 

			}
		} catch (Exception e) {
			e.printStackTrace();
			mav = new ModelAndView("login");
		}

		return mav;

	}
	
	
	@RequestMapping(value = "/setSubModId", method = RequestMethod.GET)
	public @ResponseBody void setSubModId(HttpServletRequest request,
		HttpServletResponse response) {
		int subModId=Integer.parseInt(request.getParameter("subModId"));
		int modId=Integer.parseInt(request.getParameter("modId"));
		 System.out.println("subModId " + subModId);
		System.out.println("modId " + modId); 
		HttpSession session = request.getSession();
		session.setAttribute("sessionModuleId", modId);
		session.setAttribute("sessionSubModuleId",subModId);
		 session.removeAttribute( "exportExcelList" );
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}
	
	//setAcaYearInSession
	
	@RequestMapping(value = "/setAcaYearInSession", method = RequestMethod.GET)
	public @ResponseBody AcademicYear setAcaYearInSession(HttpServletRequest request,
		HttpServletResponse response) {
		
		System.err.println("Hello ");
		int yearId=Integer.parseInt(request.getParameter("yearId"));
		String yearValue=request.getParameter("yearValue");
		HttpSession session = request.getSession();
		session.setAttribute("acYearId", yearId);
		//session.setAttribute("sessionSubModuleId",subModId);
		System.err.println("yearValue " +yearValue);
		System.err.println("Session date year Id " +session.getAttribute("acYearId"));
		MultiValueMap<String, Object> map =new LinkedMultiValueMap<String, Object>();
		map =new LinkedMultiValueMap<String, Object>(); 
		 map.add("yearId", yearId);
		AcademicYear acYear = restTemplate.postForObject(Constants.url + "getAcademicYearByYearId", map, AcademicYear.class);
		session.setAttribute("acYearValue", acYear.getAcademicYear());

		
		return acYear;
		
	}
	
	
}
