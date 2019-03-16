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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.master.model.prodetail.Cast;
import com.ats.rusasoft.master.model.prodetail.GetStudAdmCatwise;
import com.ats.rusasoft.master.model.prodetail.GetStudAdmLocwise;
import com.ats.rusasoft.master.model.prodetail.Location;
import com.ats.rusasoft.master.model.prodetail.NameIdBean;
import com.ats.rusasoft.master.model.prodetail.StudAdmCatwise;
import com.ats.rusasoft.master.model.prodetail.StudAdmLocwise;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class StudAdminController {

	@RequestMapping(value = "/showAddStudAdmitCatWise", method = RequestMethod.GET)
	public ModelAndView showAddStudAddmitCatWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addStudCatwise");

			model.addObject("title", "Add Student  Categorywise");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			Cast[] catsArray = restTemplate.getForObject(Constants.url + "getAllCastCategory", Cast[].class);
			List<Cast> castList = new ArrayList<>(Arrays.asList(catsArray));
			System.err.println("castList " + castList.toString());

			model.addObject("castList", castList);

			map = new LinkedMultiValueMap<String, Object>();
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());

			int yearId = (int) session.getAttribute("acYearId");
			map.add("yearId", yearId);

			GetStudAdmCatwise[] castArray = restTemplate.postForObject(Constants.url + "getStudAdmCatwiseList", map,
					GetStudAdmCatwise[].class);
			List<GetStudAdmCatwise> studAdmCastList = new ArrayList<>(Arrays.asList(castArray));
			System.err.println("studAdmCastList " + studAdmCastList.toString());


			if (studAdmCastList.size() > 0) {

				model.addObject("isEdit", 1);
			} else {

				model.addObject("isEdit", 0);
			}
			//

			List<NameIdBean> newCastIds = new ArrayList<NameIdBean>();

			for (int i = 0; i < castList.size(); i++) {

				int isNew = 1;

				for (int j = 0; j < studAdmCastList.size(); j++) {

					if (castList.get(i).getCastId() == studAdmCastList.get(j).getCastId()) {
						isNew = 0;
					}
				}

				if (isNew == 1) {

					NameIdBean bean = new NameIdBean();

					bean.setId(castList.get(i).getCastId());
					bean.setName(castList.get(i).getCastName());
					newCastIds.add(bean);

				}
			}

			for (int i = 0; i < newCastIds.size(); i++) {

				GetStudAdmCatwise newCastObj = new GetStudAdmCatwise();

				newCastObj.setCastId(newCastIds.get(i).getId());
				newCastObj.setCastName(newCastIds.get(i).getName());

				studAdmCastList.add(newCastObj);
			}

			

			//
			model.addObject("studAdmCastList", studAdmCastList);


		} catch (Exception e) {

			System.err.println("exception In showAddStudAdmitCatWise at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	//List of Student Added Loc wise
	
	@RequestMapping(value = "/showStudAddmitLoc", method = RequestMethod.GET)
	public ModelAndView showStudAddmitLoc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/studAdmittedLoc");

			model.addObject("title", "Student Addmitted Locationwise");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());

			int yearId = (int) session.getAttribute("acYearId");
			map.add("yearId", yearId);

			GetStudAdmLocwise[] locArray = restTemplate.postForObject(Constants.url + "getStudAdmLocwiseList", map,
					GetStudAdmLocwise[].class);
			List<GetStudAdmLocwise> locAdmList = new ArrayList<>(Arrays.asList(locArray));
			System.err.println("locAdmList " + locAdmList.toString());

			model.addObject("locAdmList", locAdmList);

		} catch (Exception e) {

			System.err.println("exception In showStudAddmitLoc at Master Contr" + e.getMessage());
			e.printStackTrace();

		}

		return model;

	}

	// List of Stud Adm Cat Wise
	@RequestMapping(value = "/showStudAddmit", method = RequestMethod.GET)
	public ModelAndView showStudAddmit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/studAdmitted");

			model.addObject("title", "Student Addmitted Categorywise");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());

			int yearId = (int) session.getAttribute("acYearId");
			map.add("yearId", yearId);

			GetStudAdmCatwise[] catsArray = restTemplate.postForObject(Constants.url + "getStudAdmCatwiseList", map,
					GetStudAdmCatwise[].class);
			List<GetStudAdmCatwise> studAdmList = new ArrayList<>(Arrays.asList(catsArray));
			System.err.println("studAdmCastList " + studAdmList.toString());

			model.addObject("studAdmCastList", studAdmList);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddStudAddmitLocWise", method = RequestMethod.GET)
	public ModelAndView showAddStudAddmitLocWise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addStudLocwise");

			model.addObject("title", "Add Student Locationwise ");

			RestTemplate restTemplate = new RestTemplate();
			Location[] locArray = restTemplate.getForObject(Constants.url + "getAllLocation", Location[].class);
			List<Location> locList = new ArrayList<>(Arrays.asList(locArray));
			System.err.println("locList " + locList.toString());

			model.addObject("locList", locList);

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());

			int yearId = (int) session.getAttribute("acYearId");
			map.add("yearId", yearId);

			GetStudAdmLocwise[] locAdmStudArray = restTemplate.postForObject(Constants.url + "getStudAdmLocwiseList",
					map, GetStudAdmLocwise[].class);
			List<GetStudAdmLocwise> locAdmList = new ArrayList<>(Arrays.asList(locAdmStudArray));
			System.err.println("locAdmList " + locAdmList.toString());

			if (locAdmList.size() > 0) {

				model.addObject("isEdit", 1);
			} else {
				model.addObject("isEdit", 0);

			}

			List<NameIdBean> newLocIds = new ArrayList<NameIdBean>();

			for (int i = 0; i < locList.size(); i++) {

				int isNew = 1;

				for (int j = 0; j < locAdmList.size(); j++) {

					if (locList.get(i).getLocationId() == locAdmList.get(j).getLocationId()) {
						isNew = 0;
					}
				}

				if (isNew == 1) {

					NameIdBean bean = new NameIdBean();

					bean.setId(locList.get(i).getLocationId());
					bean.setName(locList.get(i).getLocationName());
					newLocIds.add(bean);

				}
			}

			for (int i = 0; i < newLocIds.size(); i++) {

				GetStudAdmLocwise newLocObj = new GetStudAdmLocwise();

				newLocObj.setLocationId(newLocIds.get(i).getId());
				newLocObj.setLocationName(newLocIds.get(i).getName());

				locAdmList.add(newLocObj);
			}

			model.addObject("locAdmList", locAdmList);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// insertStudAdmCatwise

	@RequestMapping(value = "/insertStudAdmCatwise", method = RequestMethod.POST)
	public String insertStudAdmCatwise(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertStudAdmCatwise");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			Cast[] catsArray = restTemplate.getForObject(Constants.url + "getAllCastCategory", Cast[].class);
			List<Cast> castList = new ArrayList<>(Arrays.asList(catsArray));
			System.err.println("castList " + castList.toString());

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = new Info();// AccessControll.checkAccess("insertAlumni", "showAlumini", "1", "0", "0",
											// "0",
			// newModuleList);
			editAccess.setError(false);

			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				int exInt1 = 0;
				String exVar1 = "NA";

				int yearId = (int) session.getAttribute("acYearId");

				int isEdit = Integer.parseInt(request.getParameter("isEdit"));
				if (isEdit == 0) {

					List<StudAdmCatwise> studListCatwise = new ArrayList<>();

					for (int i = 0; i < castList.size(); i++) {
						StudAdmCatwise studAdmCat = new StudAdmCatwise();

						studAdmCat.setCastId(castList.get(i).getCastId());

						studAdmCat.setFemaleStudent(
								Integer.parseInt(request.getParameter("cast_f" + castList.get(i).getCastId())));
						studAdmCat.setMaleStudent(
								Integer.parseInt(request.getParameter("cast_m" + castList.get(i).getCastId())));
						studAdmCat.setTransStudent(
								Integer.parseInt(request.getParameter("cast_t" + castList.get(i).getCastId())));
						// studAdmCat.setCatTotStudent(Integer.parseInt(request.getParameter("cast_tot_stud"+castList.get(i).getCastId())));
						studAdmCat.setCatTotStudent(studAdmCat.getFemaleStudent() + studAdmCat.getMaleStudent()
								+ studAdmCat.getTransStudent());

						studAdmCat.setProgramId(1);

						studAdmCat.setStudentCatId(0);

						studAdmCat.setExInt1(exInt1);
						studAdmCat.setExInt2(exInt1);
						studAdmCat.setExVar1(exVar1);
						studAdmCat.setExVar2(exVar1);

						studAdmCat.setDelStatus(1);
						studAdmCat.setIsActive(1);

						studAdmCat.setMakerUserId(userObj.getUserId());// get from Session
						studAdmCat.setMakerDatetime(curDateTime);

						studAdmCat.setYearId(yearId);
						studAdmCat.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session

						studListCatwise.add(studAdmCat);

					}

					System.err.println("studListCatwise " + studListCatwise.toString());

					List<StudAdmCatwise> trainPlaceRes = restTemplate
							.postForObject(Constants.url + "saveStudentAdmCatwise", studListCatwise, List.class);

				} else {
					System.err.println("Its Edit call ");

					map.add("instId", userObj.getGetData().getUserInstituteId());
					map.add("yearId", yearId);

					GetStudAdmCatwise[] castArray = restTemplate.postForObject(Constants.url + "getStudAdmCatwiseList",
							map, GetStudAdmCatwise[].class);
					List<GetStudAdmCatwise> studAdmList = new ArrayList<>(Arrays.asList(castArray));
					System.err.println("studAdmCastList " + studAdmList.toString());
					
					//
					List<NameIdBean> newCastIds = new ArrayList<NameIdBean>();

					for (int i = 0; i < castList.size(); i++) {

						int isNew = 1;

						for (int j = 0; j < studAdmList.size(); j++) {

							if (castList.get(i).getCastId() == studAdmList.get(j).getCastId()) {
								isNew = 0;
							}
						}

						if (isNew == 1) {

							NameIdBean bean = new NameIdBean();

							bean.setId(castList.get(i).getCastId());
							bean.setName(castList.get(i).getCastName());
							newCastIds.add(bean);

						}
					}

					for (int i = 0; i < newCastIds.size(); i++) {

						GetStudAdmCatwise newCastObj = new GetStudAdmCatwise();

						newCastObj.setCastId(newCastIds.get(i).getId());
						newCastObj.setCastName(newCastIds.get(i).getName());

						studAdmList.add(newCastObj);
					}
//

					List<StudAdmCatwise> studListCatwise = new ArrayList<>();

					for (int i = 0; i < studAdmList.size(); i++) {
						StudAdmCatwise studAdmCat = new StudAdmCatwise();

						studAdmCat.setCastId(studAdmList.get(i).getCastId());

						studAdmCat.setFemaleStudent(Integer
								.parseInt(request.getParameter("cast_f" + studAdmList.get(i).getStudentCatId())));
						studAdmCat.setMaleStudent(Integer
								.parseInt(request.getParameter("cast_m" + studAdmList.get(i).getStudentCatId())));
						studAdmCat.setTransStudent(Integer
								.parseInt(request.getParameter("cast_t" + studAdmList.get(i).getStudentCatId())));
						// studAdmCat.setCatTotStudent(Integer.parseInt(request.getParameter("cast_tot_stud"+castList.get(i).getCastId())));
						studAdmCat.setCatTotStudent(studAdmCat.getFemaleStudent() + studAdmCat.getMaleStudent()
								+ studAdmCat.getTransStudent());

						studAdmCat.setProgramId(1);

						studAdmCat.setStudentCatId(studAdmList.get(i).getStudentCatId());

						studAdmCat.setExInt1(exInt1);
						studAdmCat.setExInt2(exInt1);
						studAdmCat.setExVar1(exVar1);
						studAdmCat.setExVar2(exVar1);

						studAdmCat.setDelStatus(1);
						studAdmCat.setIsActive(1);

						studAdmCat.setMakerUserId(userObj.getUserId());// get from Session
						studAdmCat.setMakerDatetime(curDateTime);

						studAdmCat.setYearId(yearId);
						studAdmCat.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session

						studListCatwise.add(studAdmCat);

					}

					System.err.println("studListCatwise " + studListCatwise.toString());

					List<StudAdmCatwise> trainPlaceRes = restTemplate
							.postForObject(Constants.url + "saveStudentAdmCatwise", studListCatwise, List.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showAddStudAdmitCatWise";
				else
					redirect = "redirect:/showStudAddmit";
			}

		} catch (Exception e) {
			System.err.println("Exce in save insertTrainPlace  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;

	}

	// insertStudAdmLocwise

	@RequestMapping(value = "/insertStudAdmLocwise", method = RequestMethod.POST)
	public String insertStudAdmLocwise(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertStudAdmLocwise");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			Location[] locArray = restTemplate.getForObject(Constants.url + "getAllLocation", Location[].class);
			List<Location> locList = new ArrayList<>(Arrays.asList(locArray));
			System.err.println("locList " + locList.toString());

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = new Info();// AccessControll.checkAccess("insertAlumni", "showAlumini", "1", "0", "0",
											// "0",
			// newModuleList);
			editAccess.setError(false);
			int isEdit = Integer.parseInt(request.getParameter("isEdit"));
			if (editAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				int exInt1 = 0;
				String exVar1 = "NA";

				int yearId = (int) session.getAttribute("acYearId");

				if (isEdit == 0) {

					List<StudAdmLocwise> studListLocwise = new ArrayList<>();

					for (int i = 0; i < locList.size(); i++) {

						StudAdmLocwise studAdmLoc = new StudAdmLocwise();

						studAdmLoc.setLocationId(locList.get(i).getLocationId());

						studAdmLoc.setFemaleStudent(
								Integer.parseInt(request.getParameter("loc_f" + locList.get(i).getLocationId())));
						studAdmLoc.setMaleStudent(
								Integer.parseInt(request.getParameter("loc_m" + locList.get(i).getLocationId())));
						studAdmLoc.setTransStudent(
								Integer.parseInt(request.getParameter("loc_t" + locList.get(i).getLocationId())));
						// studAdmCat.setCatTotStudent(Integer.parseInt(request.getParameter("cast_tot_stud"+castList.get(i).getCastId())));
						studAdmLoc.setLocTotStudent(studAdmLoc.getFemaleStudent() + studAdmLoc.getMaleStudent()
								+ studAdmLoc.getTransStudent());

						studAdmLoc.setProgramId(1);

						studAdmLoc.setStudentLocId(0);

						studAdmLoc.setExInt1(exInt1);
						studAdmLoc.setExInt2(exInt1);
						studAdmLoc.setExVar1(exVar1);
						studAdmLoc.setExVar2(exVar1);

						studAdmLoc.setDelStatus(1);
						studAdmLoc.setIsActive(1);

						studAdmLoc.setMakerUserId(userObj.getUserId());// get from Session
						studAdmLoc.setMakerDatetime(curDateTime);

						studAdmLoc.setYearId(yearId);
						studAdmLoc.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session

						studListLocwise.add(studAdmLoc);

					}

					System.err.println("studListLocwise " + studListLocwise.toString());

					List<StudAdmLocwise> studAdmLocRes = restTemplate
							.postForObject(Constants.url + "saveStudentAdmLocwise", studListLocwise, List.class);

				} else {
					System.err.println("Its Edit call ");
					// Its Update Call; isedit=1

					map = new LinkedMultiValueMap<String, Object>();

					map.add("instId", userObj.getGetData().getUserInstituteId());

					map.add("yearId", yearId);

					GetStudAdmLocwise[] locAdmStudArray = restTemplate
							.postForObject(Constants.url + "getStudAdmLocwiseList", map, GetStudAdmLocwise[].class);
					List<GetStudAdmLocwise> locAdmList = new ArrayList<>(Arrays.asList(locAdmStudArray));
					System.err.println("locAdmList " + locAdmList.toString());

					//

					List<NameIdBean> newLocIds = new ArrayList<NameIdBean>();

					for (int i = 0; i < locList.size(); i++) {

						int isNew = 1;

						for (int j = 0; j < locAdmList.size(); j++) {

							if (locList.get(i).getLocationId() == locAdmList.get(j).getLocationId()) {
								isNew = 0;
							}
						}

						if (isNew == 1) {

							NameIdBean bean = new NameIdBean();

							bean.setId(locList.get(i).getLocationId());
							bean.setName(locList.get(i).getLocationName());
							newLocIds.add(bean);

						}
					}

					for (int i = 0; i < newLocIds.size(); i++) {

						GetStudAdmLocwise newLocObj = new GetStudAdmLocwise();

						newLocObj.setLocationId(newLocIds.get(i).getId());
						newLocObj.setLocationName(newLocIds.get(i).getName());

						locAdmList.add(newLocObj);
					}

					//

					List<StudAdmLocwise> studListLocwise = new ArrayList<>();

					for (int i = 0; i < locAdmList.size(); i++) {

						StudAdmLocwise studAdmLoc = new StudAdmLocwise();

						studAdmLoc.setLocationId(locAdmList.get(i).getLocationId());

						studAdmLoc.setFemaleStudent(
								Integer.parseInt(request.getParameter("loc_f" + locAdmList.get(i).getStudentLocId())));
						studAdmLoc.setMaleStudent(
								Integer.parseInt(request.getParameter("loc_m" + locAdmList.get(i).getStudentLocId())));
						studAdmLoc.setTransStudent(
								Integer.parseInt(request.getParameter("loc_t" + locAdmList.get(i).getStudentLocId())));
						// studAdmCat.setCatTotStudent(Integer.parseInt(request.getParameter("cast_tot_stud"+castList.get(i).getCastId())));
						studAdmLoc.setLocTotStudent(studAdmLoc.getFemaleStudent() + studAdmLoc.getMaleStudent()
								+ studAdmLoc.getTransStudent());

						studAdmLoc.setProgramId(1);

						studAdmLoc.setStudentLocId(locAdmList.get(i).getStudentLocId());

						studAdmLoc.setExInt1(exInt1);
						studAdmLoc.setExInt2(exInt1);
						studAdmLoc.setExVar1(exVar1);
						studAdmLoc.setExVar2(exVar1);

						studAdmLoc.setDelStatus(1);
						studAdmLoc.setIsActive(1);

						studAdmLoc.setMakerUserId(userObj.getUserId());// get from Session
						studAdmLoc.setMakerDatetime(curDateTime);

						studAdmLoc.setYearId(yearId);
						studAdmLoc.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session

						studListLocwise.add(studAdmLoc);

					}

					List<StudAdmLocwise> studAdmLocRes = restTemplate
							.postForObject(Constants.url + "saveStudentAdmLocwise", studListLocwise, List.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showStudAddmitLoc";
				else
					redirect = "redirect:/showAddStudAddmitLocWise";
			}

		} catch (Exception e) {
			System.err.println("Exce in save insertTrainPlace  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;

	}

}
