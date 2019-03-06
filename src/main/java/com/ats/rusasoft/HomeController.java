package com.ats.rusasoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.UserLogin;
import com.ats.rusasoft.model.GetUserDetail;
import com.ats.rusasoft.model.Info;

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
					session.setAttribute("userName", name);
					session.setAttribute("password", password);
					/*session.setAttribute("isEnroll", userObj.getExInt1());
					session.setAttribute("userId", userObj.getUserId());*/
					
					session.setAttribute("userObj", userObj);
					
					
					
				/*	
					session.setAttribute("subUserName", userObj.getGetData().getSubUserName());
					session.setAttribute("subUserContactNum", userObj.getGetData().getUserConNumber());
					session.setAttribute("subUserDesnId", userObj.getGetData().getUserDesnId());
					session.setAttribute("subUserId", userObj.getGetData().getUserDetailId());
					session.setAttribute("qualId", userObj.getGetData().getUserQualId());
					session.setAttribute("instituteId", userObj.getGetData().getUserInstituteId());
					session.setAttribute("qualId", userObj.getGetData().getDeptId());*/
					
					loginResponseMessage="Login Successful";
					mav.addObject("loginResponseMessage",loginResponseMessage);
					
					
				 map =new LinkedMultiValueMap<String, Object>();
					int typeId=userObj.getUserType();
					map.add("typeId", typeId);
					
					GetUserDetail  userDataResponse= restTemplate.postForObject(Constants.url+"getUserDetailByTypeId",map,GetUserDetail.class);
					
					System.out.println("JSON Response Objet " + userDataResponse.toString());
				
				
				} 
else {

					
					mav = new ModelAndView("login");
					
					loginResponseMessage="Invalid Login Credentials";
					mav.addObject("loginResponseMessage",loginResponseMessage);
					
					System.out.println("Invalid login credentials");

				}

			}
		} catch (Exception e) {
			System.out.println("HomeController Login API Excep:  " + e.getMessage());
		}

		return mav;

	}
	
	
	
	
}
