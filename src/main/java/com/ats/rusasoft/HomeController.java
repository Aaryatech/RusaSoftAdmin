package com.ats.rusasoft;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Commons;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.master.State;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.UserLogin;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.accessright.SubModuleJson;
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.DashBoardCounts;
import com.ats.rusasoft.model.GetUserDetail;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.LibraryInfo;
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
	/*
	 * @RequestMapping(value = "/", method = RequestMethod.GET) public String
	 * home(Locale locale, Model model) {
	 * logger.info("Welcome home! The client locale is {}.", locale);
	 * 
	 * Date date = new Date(); DateFormat dateFormat =
	 * DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
	 * 
	 * String formattedDate = dateFormat.format(date);
	 * 
	 * model.addAttribute("serverTime", formattedDate);
	 * 
	 * return "welcome"; }
	 */

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

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map = new LinkedMultiValueMap<String, Object>();
			map.add("type", 1);

			AcademicYear[] quolArray = restTemplate.postForObject(Constants.url + "getAcademicYearListByTypeId", map,
					AcademicYear[].class);
			List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
			// System.err.println("acaYearList " + acaYearList.toString());

			model.addObject("acaYearList", acaYearList);

		} catch (Exception e) {

			System.err.println("exception In / at home Contr '/' mapping " + e.getMessage());

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
			model.addObject("isEdit", 0);
		} catch (Exception e) {

			System.err.println("exception In showInstituteRegistrationForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showWelcomePage", method = RequestMethod.GET)
	public ModelAndView showWelcomePage(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("welcome");

			// DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
			// DateFormat.LONG, locale);

			// String formattedDate = dateFormat.format(date);

			// model.addAttribute("serverTime", formattedDate);
			model.addObject("title", "DASHBOARD");
		} catch (Exception e) {

			System.err.println("exception In showWelcomePage at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/changePassForm", method = RequestMethod.POST)
	public String changePassForm(HttpServletRequest request, HttpServletResponse response) {

		/* ModelAndView model = null; */
		try {
			HttpSession session = request.getSession();
			int userId = 0;
			try {
				userId = (int) session.getAttribute("userId");
			} catch (Exception e) {
				userId = Integer.parseInt(request.getParameter("uid"));
				System.err.println("In Catch " + userId);
				// e.printStackTrace();
			}
			String password = request.getParameter("newPassword");

			/* String password="harsha"; */

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			String hashtext = new String();

			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				byte[] messageDigest = md.digest(password.getBytes());
				BigInteger number = new BigInteger(1, messageDigest);
				hashtext = number.toString(16);

			} catch (Exception e) {
				e.printStackTrace();
			}

			map.add("password", hashtext);
			map.add("userId", userId);

			// UserLogin userObj =
			// restTemplate.postForObject(Constants.url+"changePass",map,UserLogin.class);

			Info errMsg = restTemplate.postForObject(Constants.url + "changePass", map, Info.class);

			session.invalidate();

		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();
			HttpSession session = request.getSession();
			session.invalidate();
		}

		return "redirect:/";

	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView();
		try {

			model = new ModelAndView("welcome");
			model.addObject("title", "DASHBOARD");

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int instituteId = (int) session.getAttribute("instituteId");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instId", instituteId);
			map.add("isPrincipal", userObj.getStaff().getIsPrincipal());
			map.add("isIqac", userObj.getStaff().getIsIqac());
			map.add("isHod", userObj.getStaff().getIsHod());
			map.add("isFaculty", userObj.getStaff().getIsFaculty());
			map.add("isLibrarian", userObj.getStaff().getIsLibrarian());
			map.add("isAccOff", userObj.getStaff().getIsAccOff());
			map.add("isDean", userObj.getStaff().getIsDean());
			map.add("deptId", userObj.getStaff().getDeptId());
			map.add("facultyId", userObj.getUserId());

			DashBoardCounts dashBoardCounts = restTemplate.postForObject(Constants.url + "/getPrincipalDashCounts", map,
					DashBoardCounts.class);
			
			System.err.println("dashBoardCounts " +dashBoardCounts.toString());
			model.addObject("dashBoardCounts", dashBoardCounts);
		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	LinkedHashMap<String, Integer> tempMap = new LinkedHashMap<>();
	LinkedHashMap<String, Calendar> userTimeMap = new LinkedHashMap<>();

	@RequestMapping("/loginProcess")
	public String helloWorld(HttpServletRequest request, HttpServletResponse res, Model model) throws IOException {
		Instant start = Instant.now();

		String mav = "login";
		HttpSession session = request.getSession();

		String name = request.getParameter("username");
		String password = request.getParameter("userpassword");
		int acYearId = 0;
		try {
			acYearId = (int) session.getAttribute("acYearId");
		} catch (Exception e) {
			acYearId = 0;
		}
		int loginAcYearId = 0;
		if (acYearId == 0) {
			// System.err.println(" IN if First time acYearId ==0");
			// loginAcYearId=Integer.parseInt(request.getParameter("ac_year_login"));
		} else {
			// System.err.println("In Else its reload call ");
			loginAcYearId = acYearId;
		}

		res.setContentType("text/html");

		try {
			String captcha = session.getAttribute("captcha_security").toString();
			String verifyCaptcha = request.getParameter("captcha");
//captcha.equals(verifyCaptcha)
			if (captcha.trim().equals(verifyCaptcha.trim())) {

				if (name.equalsIgnoreCase("") || password.equalsIgnoreCase("") || name == null || password == null) {

					mav = "login";
					model.addAttribute("msg", "Enter  Login Credentials");

				} else {

					int validLogin = 0;
					Calendar time = Calendar.getInstance();
					time = DateConvertor.getCurTime();
					try {
						time = userTimeMap.get(name);
					} catch (Exception e) {
						time = DateConvertor.getCurTime();
						System.err.println("in catch " + e.getMessage());
						e.printStackTrace();
					}
					System.err
							.println("time " + time + "DateConvertor.getCurTime()  time " + DateConvertor.getCurTime());

					if (userTimeMap.get(name) == null) {
						validLogin = 1;

					} else if (DateConvertor.getCurTime().getTime().after(time.getTime())) {
						System.err.println("time exceed");
						validLogin = 1;
					}else {
						model.addAttribute("msg",
								"Your have crossed no. of invalid login attempts. Account blocked for some time!!");
				
						validLogin =0;
					}

					if (validLogin == 1) {

						MessageDigest md = MessageDigest.getInstance("MD5");
						byte[] messageDigest = md.digest(password.getBytes());
						BigInteger number = new BigInteger(1, messageDigest);
						String hashtext = number.toString(16);
						System.err.println("hashtext " + hashtext);
						MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
						map.add("username", name);
						map.add("password", hashtext);
						map.add("isBlock", 1);

						LoginResponse userObj = restTemplate.postForObject(Constants.url + "login", map,
								LoginResponse.class);

						String loginResponseMessage = "";

						if (userObj != null) {

							int a = userObj.getStaff().getIsEnrolled();

							if (a == 1) {
								mav = "redirect:/dashboard";
								tempMap.remove(userObj.getStaff().getEmail());
								map = new LinkedMultiValueMap<String, Object>();
								map.add("type", 1);

								AcademicYear[] quolArray = restTemplate.postForObject(
										Constants.url + "getAcademicYearListByTypeId", map, AcademicYear[].class);
								List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
								// mav.addObject("acaYearList", acaYearList);
								session.setAttribute("acaYearList", acaYearList);

							} else {
								mav = "changePassword";
								tempMap.remove(userObj.getStaff().getEmail());
							}

							DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							Calendar cal = Calendar.getInstance();

							String curDateTime = dateFormat.format(cal.getTime());

							DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

							String curDate = dateFormatStr.format(new Date());

							InetAddress addr = InetAddress.getByName(request.getRemoteAddr());
							String hostName = addr.getHostName();
							String userAgent = request.getHeader("User-Agent");

							LoginLog llog = new LoginLog();

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
							LoginLog editInst = rest.postForObject(Constants.url + "saveLoginLog", llog,
									LoginLog.class);

							session.setAttribute("userName", name);
							session.setAttribute("password", password);

							session.setAttribute("userObj", userObj);

							int instituteId = userObj.getGetData().getUserInstituteId();
							session.setAttribute("instituteId", instituteId);

							int userId = userObj.getUserId();
							session.setAttribute("userId", userId);

							session.setAttribute("addIcon", "fa fa-plus-square-o");
							session.setAttribute("saveIcon", "fa fa-floppy-o");
							session.setAttribute("cancelIcon", "fa fa-backward");
							session.setAttribute("forwardIcon", "fa fa-forward");
							session.setAttribute("deleteIcon", "fa fa-times");

							map = new LinkedMultiValueMap<String, Object>();
							int typeId = userObj.getUserType();
							map.add("typeId", typeId);

							/*
							 * map =new LinkedMultiValueMap<String, Object>(); map.add("userId", userId); //
							 * System.out.println("Before web service"); try {
							 * ParameterizedTypeReference<List<ModuleJson>> typeRef = new
							 * ParameterizedTypeReference<List<ModuleJson>>() { };
							 * ResponseEntity<List<ModuleJson>> responseEntity =
							 * restTemplate.exchange(Constants.url + "getRoleJson", HttpMethod.POST, new
							 * HttpEntity<>(map), typeRef);
							 * 
							 * List<ModuleJson> newModuleList = responseEntity.getBody();
							 */
							// session.setAttribute("newModuleList", newModuleList);
							map = new LinkedMultiValueMap<String, Object>();
							map.add("type", userObj.getUserType());

							AcademicYear[] quolArray = restTemplate.postForObject(
									Constants.url + "getAcademicYearListByTypeId", map, AcademicYear[].class);
							List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
							// System.err.println("acaYearList " + acaYearList.toString());

							// session.setAttribute("acaYearList", acaYearList);
							// ac yadded in sesion

							map = new LinkedMultiValueMap<String, Object>();
							map.add("isCurrent", 1);
							// System.err.println("Map " + map.toString());
							AcademicYear acYear1 = new AcademicYear();

							acYear1 = restTemplate.postForObject(Constants.url + "getAcademicYearByIsCurrent", map,
									AcademicYear.class);
							// System.err.println("acYear current " + acYear1.toString());

							session.setAttribute("acYearId", acYear1.getYearId());
							session.setAttribute("acYearValue", acYear1.getAcademicYear());
							// System.err.println("Session date year Id " +
							// session.getAttribute("acYearId"));

							// getAcademicYearByYearId
							/*
							 * map =new LinkedMultiValueMap<String, Object>(); map.add("yearId",
							 * loginAcYearId); AcademicYear acYear =
							 * restTemplate.postForObject(Constants.url + "getAcademicYearByYearId", map,
							 * AcademicYear.class); session.setAttribute("acYearValue",
							 * acYear.getAcademicYear());
							 */

							map = new LinkedMultiValueMap<String, Object>();
							map.add("instituteId", userObj.getGetData().getUserInstituteId());
							// getInstitute
							Institute instituteInfo = rest.postForObject(Constants.url + "getInstitute", map,
									Institute.class);
							session.setAttribute("instituteInfo", instituteInfo);
							// List<Integer> roleIdList=new ArrayList<>();

							List<Integer> roleIdList = Stream.of(userObj.getStaff().getRoleIds().split(","))
									.map(Integer::parseInt).collect(Collectors.toList());

							// roleIdList.add(13);
							// roleIdList.add(1);
							// roleIdList.add(14);
							Collections.sort(roleIdList);

							LinkedHashMap<Integer, List<ModuleJson>> moduleHashMap = new LinkedHashMap<Integer, List<ModuleJson>>();

							String time1 = dateFormat.format(cal.getTime());

							for (int i = 0; i < roleIdList.size(); i++) {

								map = new LinkedMultiValueMap<String, Object>();
								map.add("roleId", roleIdList.get(i));

								try {
									ParameterizedTypeReference<List<ModuleJson>> typeRef = new ParameterizedTypeReference<List<ModuleJson>>() {
									};
									ResponseEntity<List<ModuleJson>> responseEntity = restTemplate.exchange(
											Constants.url + "getRoleJsonByRoleId", HttpMethod.POST,
											new HttpEntity<>(map), typeRef);

									List<ModuleJson> newModuleList = responseEntity.getBody();

									moduleHashMap.put(i, newModuleList);

								} catch (Exception e) {
									e.printStackTrace();
								}
							} // end of for roleIdList;

							List<ModuleJson> newModuleList = moduleHashMap.get(0);

							for (int i = 1; i < moduleHashMap.size(); i++) {

								List<ModuleJson> list = moduleHashMap.get(i);

								for (int j = 0; j < list.size(); j++) {

									int findModuleId = 0;

									for (int k = 0; k < newModuleList.size(); k++) {
										// System.err.println("mod Id " + newModuleList.get(k).getModuleId());

										if (newModuleList.get(k).getModuleId() == list.get(j).getModuleId()) {
											if (newModuleList.get(k).getModuleId() == 4) {
												// System.err.println("mod Id 4 sub modules "
												// + newModuleList.get(k).getSubModuleJsonList().toString());
											}

											for (int m = 0; m < list.get(j).getSubModuleJsonList().size(); m++) {

												int findSubModId = 0;

												for (int l = 0; l < newModuleList.get(k).getSubModuleJsonList()
														.size(); l++) {

													if (newModuleList.get(k).getSubModuleJsonList().get(l)
															.getSubModuleId() == list.get(j).getSubModuleJsonList()
																	.get(m).getSubModuleId()) {
														findSubModId = 1;

														if (newModuleList.get(k).getSubModuleJsonList().get(l)
																.getAddApproveConfig().equals("0")) {
															newModuleList.get(k).getSubModuleJsonList().get(l)
																	.setAddApproveConfig(
																			list.get(j).getSubModuleJsonList().get(m)
																					.getAddApproveConfig());
														}

														if (newModuleList.get(k).getSubModuleJsonList().get(l)
																.getEditReject().equals("0")) {
															newModuleList.get(k).getSubModuleJsonList().get(l)
																	.setEditReject(list.get(j).getSubModuleJsonList()
																			.get(m).getEditReject());
														}

														if (newModuleList.get(k).getSubModuleJsonList().get(l)
																.getDeleteRejectApprove().equals("0")) {
															newModuleList.get(k).getSubModuleJsonList().get(l)
																	.setDeleteRejectApprove(
																			list.get(j).getSubModuleJsonList().get(m)
																					.getDeleteRejectApprove());
														}

														break;
													}
												}

												if (findSubModId == 0) {

													newModuleList.get(k).getSubModuleJsonList()
															.add(list.get(j).getSubModuleJsonList().get(m));
												}
											}

											findModuleId = 1;
											break;
										}

									}

									if (findModuleId == 0) {

										newModuleList.add(list.get(j));

									}

								}

							}

							String time2 = dateFormat.format(cal.getTime().getTime());

							for (int i = 0; i < newModuleList.size(); i++) {

								Collections.sort(newModuleList.get(i).getSubModuleJsonList(),
										new Comparator<SubModuleJson>() {
											public int compare(SubModuleJson c1, SubModuleJson c2) {
												if (c1.getOrderBy() > c2.getOrderBy())
													return 1;
												if (c1.getOrderBy() < c2.getOrderBy())
													return -1;
												return 0;
											}
										});

							}

							Collections.sort(newModuleList, new Comparator<ModuleJson>() {
								public int compare(ModuleJson c1, ModuleJson c2) {
									if (c1.getOrderBy() > c2.getOrderBy())
										return 1;
									if (c1.getOrderBy() < c2.getOrderBy())
										return -1;
									return 0;
								}
							});

							session.setAttribute("newModuleList", newModuleList);

							State[] stateArray = restTemplate.getForObject(Constants.url + "getStateList",
									State[].class);
							List<State> stateList = new ArrayList<>(Arrays.asList(stateArray));

							session.setAttribute("stateList", stateList);
						}
					} // end of if valid login ==1
				} // end of else for null user, pass
			} // end of if valid captcha matching
			else {
				mav = "login";
				model.addAttribute("msg", "Invalid Text");
				Random randChars = new Random();
				String sImageCode = (Long.toString(Math.abs(randChars.nextLong()), 36)).substring(0, 6);
				session.setAttribute("captcha_security", sImageCode);

				if (tempMap.containsKey(name)) {
					tempMap.put(name, tempMap.get(name) + 1);
				} else {
					tempMap.put(name, 1);
				}

				if (tempMap.get(name) > 4) {
					userTimeMap.put(name, DateConvertor.getTimePlus30Min());

					model.addAttribute("msg",
							"Your have crossed no. of invalid login attempts. Account blocked for some time!!");
				}

				System.err.println("map " + tempMap.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();

			mav = "login";
			model.addAttribute("msg", "Enter Valid  Login Credentials");
			/*
			 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
			 * Object>();
			 * 
			 * map = new LinkedMultiValueMap<String, Object>(); map.add("type", 1);
			 * 
			 * AcademicYear[] quolArray = restTemplate.postForObject(Constants.url +
			 * "getAcademicYearListByTypeId", map, AcademicYear[].class); List<AcademicYear>
			 * acaYearList = new ArrayList<>(Arrays.asList(quolArray));
			 * model.addAttribute("acaYearList", acaYearList);
			 */
		}

		// your code
		// Instant end = Instant.now();
		// Duration timeElapsed = Duration.between(start, end);
		// // System.out.println("Time taken: "+ timeElapsed.toMillis() +"
		// milliseconds");
		return mav;

	}

	@RequestMapping(value = "/setSubModId", method = RequestMethod.GET)
	public @ResponseBody void setSubModId(HttpServletRequest request, HttpServletResponse response) {
		int subModId = Integer.parseInt(request.getParameter("subModId"));
		int modId = Integer.parseInt(request.getParameter("modId"));
		/*
		 * // System.out.println("subModId " + subModId); // System.out.println("modId "
		 * + modId);
		 */
		HttpSession session = request.getSession();
		session.setAttribute("sessionModuleId", modId);
		session.setAttribute("sessionSubModuleId", subModId);
		session.removeAttribute("exportExcelList");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		// System.out.println("User Logout");

		session.invalidate();
		return "redirect:/";
	}

	// setAcaYearInSession

	@RequestMapping(value = "/setAcaYearInSession", method = RequestMethod.GET)
	public @ResponseBody AcademicYear setAcaYearInSession(HttpServletRequest request, HttpServletResponse response) {

		int yearId = Integer.parseInt(request.getParameter("yearId"));
		System.err.println("year Id " + yearId);
		String yearValue = request.getParameter("yearValue");
		HttpSession session = request.getSession();
		// session.setAttribute("sessionSubModuleId",subModId);
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		map = new LinkedMultiValueMap<String, Object>();
		map.add("yearId", yearId);
		System.err.println("Map  " + map.toString());
		AcademicYear acYear = new AcademicYear();

		acYear = restTemplate.postForObject(Constants.url + "getAcademicYearByYearId", map, AcademicYear.class);
		System.err.println("acYear  " + acYear.toString());

		session.setAttribute("acYearValue", acYear.getAcademicYear());
		session.setAttribute("acYearId", yearId);

		return acYear;

	}

	// Forgegt pass

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

	RestTemplate rest = new RestTemplate();
	Instant start = null;
	LinkedHashMap<String, String> userOtpMap = new LinkedHashMap<>();

	@RequestMapping(value = "/forgotPas", method = RequestMethod.POST)
	public ModelAndView checkUniqueField(HttpServletRequest request, HttpServletResponse response) {
		String c = null;
		System.err.println("Hiii  checkValue in /forgotPas forgot password checkUserName  ");
		Info info = new Info();
		ModelAndView model = null;

		try {
			// model = new ModelAndView("forgotPassword");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String inputValue = request.getParameter("username");
			System.err.println("Info inputValue  " + inputValue);

			map.add("inputValue", inputValue);
			map.add("flag", 1);

			info = rest.postForObject(Constants.url + "checkUserName", map, Info.class);
			System.err.println("Info Response  " + info.toString());

			if (info.isError() == true) {
				model = new ModelAndView("forgotPassword");
				// c="redirect:/showforgotPassForm";
				model.addObject("msg", "Invalid User Name");

			} else {
				model = new ModelAndView("verifyOTP");
				// c= "redirect:/showVerifyOTP";
				char[] genOTP1= Commons.OTP(6);
				String genOTP=info.getMsg();//String.valueOf(genOTP1);
System.err.println("forgotPas OTP "+genOTP);
				userOtpMap.put(inputValue, genOTP);
				model.addObject("username", inputValue);
				model.addObject("expFlag", 0);

				start = Instant.now();

				;
			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/reGenOtp1", method = RequestMethod.POST)
	public ModelAndView reGenOtp1(HttpServletRequest request, HttpServletResponse response) {
		String c = null;
		System.err.println("Hiii  checkValue  ");
		Info info = new Info();
		ModelAndView model = null;

		try {
			// model = new ModelAndView("forgotPassword");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String inputValue = request.getParameter("username");
			System.err.println("Username for regeneration mob***  " + inputValue);

			map.add("inputValue", inputValue);
			map.add("flag", 1);

			info = rest.postForObject(Constants.url + "checkUserName", map, Info.class);
			System.err.println("Info Response  " + info.toString());

			if (inputValue==null || inputValue=="") {
				model = new ModelAndView("forgotPassword");
				// c="redirect:/showforgotPassForm";
				model.addObject("msg", "Invalid User Name");

			} else {
				model = new ModelAndView("verifyOTP");
				model.addObject("expFlag", 0);
				char[] genOTP1= Commons.OTP(6);
				String genOTP=info.getMsg();//String.valueOf(genOTP1);
				System.err.println("reGenOtp1 OTP "+genOTP);

				userOtpMap.put(inputValue, genOTP);
				model.addObject("username", inputValue);
				model.addObject("msg", "OTP Resent Please check");
				start = Instant.now();
			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/showVerifyOTP", method = RequestMethod.GET)
	public ModelAndView showVerifyOTP(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
System.err.println("In showVerifyOTP ");
			model = new ModelAndView("verifyOTP");

		} catch (Exception e) {

			System.err.println("exception In showCMSForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/OTPVerification", method = RequestMethod.POST)
	public ModelAndView OTPVerification(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Hiii  OTPVerification  ");
		Info info = new Info();
		ModelAndView model = null;

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String otp = request.getParameter("otp");
			String username = request.getParameter("username");
			
			map.add("otp", otp);
			Instant end = Instant.now();
			Duration timeElapsed = Duration.between(start, end);
			System.out.println("Time taken: OTPVerification " + timeElapsed.toMinutes() + " minutes");
			if (timeElapsed.toMinutes() <= 2) {
				Staff staff = rest.postForObject(Constants.url + "VerifyOTP", map, Staff.class);
				if (userOtpMap.get(username).equals(otp)) {
					System.err.println("Staff" + staff);
					model = new ModelAndView("changePassword");
					model.addObject("userId", staff.getFacultyId());
				}

				else {
					model = new ModelAndView("verifyOTP");
					model.addObject("expFlag", 0);
					// c="redirect:/showforgotPassForm";
					model.addObject("msg", "Incorrect OTP");
					model.addObject("username", username);

				} 
			} else {
				model = new ModelAndView("verifyOTP");
				// c="redirect:/showforgotPassForm";
				model.addObject("expFlag", 1);
				model.addObject("msg", "Time out! Regenerate OTP");
				model.addObject("username", username);

			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	/*
	 * @RequestMapping(value = "/OTPVerification", method = RequestMethod.GET)
	 * public @ResponseBody Info OTPVerification(HttpServletRequest request,
	 * HttpServletResponse response) {
	 * 
	 * System.err.println("Hiii  checkValue  " ); Info info = new Info();
	 * 
	 * try {
	 * 
	 * MultiValueMap<String, Object> map = new LinkedMultiValueMap<String,
	 * Object>();
	 * 
	 * String otp = request.getParameter("otp");
	 * 
	 * map.add("otp", otp);
	 * 
	 * info = rest.postForObject(Constants.url + "VerifyOTP", map, Info.class);
	 * System.err.println("Info Response  " + info.toString());
	 * 
	 * } catch (Exception e) { System.err.println("Exce in checkUniqueField  " +
	 * e.getMessage()); e.printStackTrace(); }
	 * 
	 * return info;
	 * 
	 * }
	 */

	@RequestMapping(value = "/changeMobNoForm/{id}", method = RequestMethod.GET)
	public ModelAndView changeMobNo(@PathVariable("id") int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		try {

			model = new ModelAndView("changeMobNo");
			// System.out.println("Id Found" + id);
			model.addObject("title", "Change Contact No.");
			map.add("id", id);
			Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
			// System.out.println("staff" + staff.getContactNo());
			model.addObject("mobNo", staff.getContactNo());

		} catch (Exception e) {

			System.err.println("exception In changeMobNoForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/getOTPByNo", method = RequestMethod.POST)
	public ModelAndView getOTPByNo(HttpServletRequest request, HttpServletResponse response) {
		String c = null;
		System.err.println("Hiii  checkValue /getOTPByNo ");
		Info info = new Info();
		ModelAndView model = null;
		HttpSession session = request.getSession();
		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

		try {
			// model = new ModelAndView("forgotPassword");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String contact = request.getParameter("Oldcontact");
			// System.err.println("Info inputValue " + contact);

			map.add("contact", contact);

			System.err.println("userId " + userObj.getUserId());
			map.add("facultyId", userObj.getUserId());
			info = rest.postForObject(Constants.url + "changeContctNo", map, Info.class);
			System.err.println("Info Response  " + info.toString());

			if (info.isError() == true) {
				model = new ModelAndView("changeMobNo");
				// c="redirect:/showforgotPassForm";
				model.addObject("msg", "Invalid Mobile Number");

			} else {
				model = new ModelAndView("verifyOTP");
				// c= "redirect:/showVerifyOTP";
				model.addObject("username", info.getMsg());
				model.addObject("flag", 1);

			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/OTPVerificationByContact", method = RequestMethod.POST)
	public ModelAndView OTPVerificationByContact(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Hiii  OTPVerification  ");
		Info info = new Info();
		ModelAndView model = null;

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String otp = request.getParameter("otp");

			map.add("otp", otp);

			Staff staff = rest.postForObject(Constants.url + "VerifyOTP", map, Staff.class);
			/*
			 * System.err.println("hashRes Response  " + hashRes.toString());
			 * 
			 * Staff staff=hashRes.get(1); System.err.println("hashRes Response  " +
			 * hashRes.get(1));
			 */

			if (staff == null) {
				model = new ModelAndView("verifyOTP");
				// c="redirect:/showforgotPassForm";
				model.addObject("msg", "Incorrect OTP");

			} else {

				// Info errMsg = restTemplate.postForObject(Constants.url + "changePass", map,
				// Info.class);
				System.err.println("Staff" + staff);
				// model = new ModelAndView("login");
				model = new ModelAndView("changeMobNo");
				model.addObject("userId", staff.getFacultyId());
				model.addObject("flag", 1);

				// c= "redirect:/showVerifyOTP";
				/*
				 * BG
				 * 
				 * map =new LinkedMultiValueMap<String, Object>(); map.add("type", 1);
				 * model.addObject("msg","Password Sent on Your Phone Number"); AcademicYear[]
				 * quolArray = restTemplate.postForObject(Constants.url +
				 * "getAcademicYearListByTypeId", map, AcademicYear[].class); List<AcademicYear>
				 * acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				 * model.addObject("acaYearList", acaYearList);
				 */
			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}//

	HashMap<Integer, String> hm = null;

	@RequestMapping(value = "/updateContctNo", method = RequestMethod.POST)
	public ModelAndView updateContctNo(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Hiii  Update No.  ");
		Info info = new Info();
		ModelAndView model = null;

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int id = userObj.getStaff().getFacultyId();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String no = request.getParameter("newContact");
			hm = new HashMap<Integer, String>();
			hm.put(id, no);
			map.add("no", no);
			map.add("id", id);
			// System.out.println("Data==" + hm);

			String dtr = rest.postForObject(Constants.url + "sndOTPOnNewNo", map, String.class);

			// Staff staff = rest.postForObject(Constants.url + "updateMyNo",
			// map,Staff.class);

			if (dtr != null) {
				model = new ModelAndView("verifyOTP");
				model.addObject("msg", "Enter OTP");
				model.addObject("flag", 2);

			} else {

				// System.err.println("Staff" +staff);
				model = new ModelAndView("login");
				// model.addObject("msg","Contact No Sucessfully Updated");

			}

		} catch (Exception e) {
			System.err.println("Exce in updateContctNo  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/newNoVerifiedUpdate", method = RequestMethod.POST)
	public ModelAndView newNoVerifiedUpdate(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Hiii  Update No.  ");
		Info info = new Info();
		ModelAndView model = null;
		Staff staff = null;
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int id = userObj.getStaff().getFacultyId();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			for (Map.Entry m : hm.entrySet()) {
				// System.out.println("Data==" + m.getValue() + "//" + m.getKey());

				map.add("no", m.getValue());
				map.add("id", m.getKey());

				staff = rest.postForObject(Constants.url + "updateMyNo", map, Staff.class);
			}
			if (staff != null) {
				model = new ModelAndView("login");
				model.addObject("msg", "Your Contact No Update Sucessfully");
			} else {
				model = new ModelAndView("login");
				model.addObject("msg", "Please Try Again!");
			}
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return model;
	}

	@ExceptionHandler(LoginFailException.class)
	public String redirectToLogin() {
		// System.out.println("HomeController Login Fail Excep:");

		return "login";
	}

	@RequestMapping(value = "/sessionTimeOut", method = RequestMethod.GET)
	public ModelAndView displayLoginAgain(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("IN session time out ");
		ModelAndView model = new ModelAndView("login");

		logger.info("/login request mapping.");

		model.addObject("loginResponseMessage", "Session timeout ! Please login again . . .");

		return model;

	}

	// ****************************otp for change email****************************

	@RequestMapping(value = "/changeEmailIdForm/{id}", method = RequestMethod.GET)
	public ModelAndView changeEmailIdForm(@PathVariable("id") int id, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
		try {

			model = new ModelAndView("changeEmailId");
			// System.out.println("Id Found" + id);
			model.addObject("title", "Change Email ID");
			map.add("id", id);
			Staff staff = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
			// System.out.println("staff" + staff.getContactNo());
			model.addObject("mobNo", staff.getEmail());

		} catch (Exception e) {

			System.err.println("exception In changeMobNoForm at home Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/getOTPByEmailId", method = RequestMethod.POST)
	public ModelAndView getOTPByEmailId(HttpServletRequest request, HttpServletResponse response) {
		String c = null;
		System.err.println("Hiii  checkValue  ");
		Info info = new Info();
		ModelAndView model = null;

		try {
			// model = new ModelAndView("forgotPassword");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String contact = request.getParameter("Oldcontact");
			System.err.println("Info inputValue  " + contact);

			map.add("emailID", contact);

			info = rest.postForObject(Constants.url + "changeEmailId", map, Info.class);
			System.err.println("Info Response  " + info.toString());

			if (info.isError() == true) {
				model = new ModelAndView("changeEmailId");
				// c="redirect:/showforgotPassForm";
				model.addObject("msg", "Invalid Email ID ");

			} else {
				model = new ModelAndView("verifyOTPForEmail");
				model.addObject("username", info.getMsg());
				model.addObject("flag", 1);

			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/reGenOtpEmail", method = RequestMethod.POST)
	public ModelAndView reGenOtpEmail(HttpServletRequest request, HttpServletResponse response) {
		String c = null;
		System.err.println("Hiii  checkValue  ");
		Info info = new Info();
		ModelAndView model = null;

		try {
			// model = new ModelAndView("forgotPassword");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String inputValue = request.getParameter("username");
			System.err.println("Username for regeneration of email  " + inputValue);

			map.add("inputValue", inputValue);
			map.add("flag", 2);

			info = rest.postForObject(Constants.url + "checkUserName", map, Info.class);
			System.err.println("Info Response  " + info.toString());

			if (info.isError() == true) {
				model = new ModelAndView("forgotPassword");
				// c="redirect:/showforgotPassForm";
				model.addObject("msg", "Invalid User Name");

			} else {
				model = new ModelAndView("verifyOTP");
				// c= "redirect:/showVerifyOTP";
				model.addObject("username", info.getMsg());
				model.addObject("msg", "OTP Resent Please check");
				start = Instant.now();
			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/OTPVerificationByEmail", method = RequestMethod.POST)
	public ModelAndView OTPVerificationByEmail(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Hiii  OTPVerification 4 email ");
		Info info = new Info();
		ModelAndView model = null;

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String otp = request.getParameter("otp");

			map.add("otp", otp);

			Staff staff = rest.postForObject(Constants.url + "VerifyOTP", map, Staff.class);

			if (staff == null) {
				model = new ModelAndView("verifyOTP");
				model.addObject("msg", "Incorrect OTP");

			} else {

				System.err.println("Staff" + staff);
				model = new ModelAndView("changeEmailId");
				model.addObject("userId", staff.getFacultyId());
				model.addObject("flag", 1);

			}

		} catch (Exception e) {
			System.err.println("Exce in checkUniqueField  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}//

	HashMap<Integer, String> hm1 = null;

	@RequestMapping(value = "/updateEmailId", method = RequestMethod.POST)
	public ModelAndView updateEmailId(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Hiii  Update No.  ");
		Info info = new Info();
		ModelAndView model = null;

		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int id = userObj.getStaff().getFacultyId();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			String no = request.getParameter("newContact");
			hm1 = new HashMap<Integer, String>();
			hm1.put(id, no);
			map.add("emailId", no);
			map.add("id", id);

			String dtr = rest.postForObject(Constants.url + "sndOTPOnNewEmailId", map, String.class);

			// Staff staff = rest.postForObject(Constants.url + "updateMyNo",
			// map,Staff.class);

			if (dtr != null) {
				model = new ModelAndView("verifyOTPForEmail");
				model.addObject("msg", "Enter OTP");
				model.addObject("flag", 2);

			} else {

				// System.err.println("Staff" +staff);
				model = new ModelAndView("login");
				// model.addObject("msg","Contact No Sucessfully Updated");

			}

		} catch (Exception e) {
			System.err.println("Exce in updateContctNo  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/newEmailVerifiedUpdate", method = RequestMethod.POST)
	public ModelAndView newEmailVerifiedUpdate(HttpServletRequest request, HttpServletResponse response) {

		System.err.println("Hiii  Update No.  ");
		Info info = new Info();
		ModelAndView model = null;
		Staff staff = null;
		try {
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int id = userObj.getStaff().getFacultyId();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			for (Map.Entry m1 : hm1.entrySet()) {
				// System.out.println("Data==" + m.getValue() + "//" + m.getKey());

				map.add("email", m1.getValue());
				map.add("id", m1.getKey());

				staff = rest.postForObject(Constants.url + "updateMyEmail", map, Staff.class);
			}
			if (staff != null) {
				model = new ModelAndView("login");
				model.addObject("msg", "Your Email Id Update Sucessfully");
			} else {
				model = new ModelAndView("login");
				model.addObject("msg", "Please Try Again!");
			}
		} catch (Exception e) {
			// System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return model;
	}

}