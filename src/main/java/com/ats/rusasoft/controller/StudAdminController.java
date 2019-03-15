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
import com.ats.rusasoft.master.model.prodetail.StudAdmCatwise;
import com.ats.rusasoft.master.model.prodetail.TrainPlacement;
import com.ats.rusasoft.model.Dept;
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

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());
			
		
			Cast[] catsArray = restTemplate.getForObject(Constants.url + "getAllCastCategory",  Cast[].class);
			List<Cast> castList = new ArrayList<>(Arrays.asList(catsArray));
			System.err.println("castList " + castList.toString());
			
			model.addObject("castList", castList);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}


	@RequestMapping(value = "/showStudAddmitLoc", method = RequestMethod.GET)
	public ModelAndView showStudAddmitLoc(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/studAdmittedLoc");

			model.addObject("title", "Student Addmitted Locationwise");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/showStudAddmit", method = RequestMethod.GET)
	public ModelAndView showStudAddmit(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/studAdmitted");

			model.addObject("title", "Student Addmitted Categorywise");

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

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	//insertStudAdmCatwise
	
	@RequestMapping(value = "/insertStudAdmCatwise", method = RequestMethod.POST)
	public String insertStudAdmCatwise(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertStudAdmCatwise");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			Cast[] catsArray = restTemplate.getForObject(Constants.url + "getAllCastCategory",  Cast[].class);
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
				if (1 == 1) {

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					
					int exInt1 = 0;
					String exVar1 = "NA";

					
					int yearId = (int) session.getAttribute("acYearId");
					
					List<StudAdmCatwise> studListCatwise=new ArrayList<>();
					
					for(int i=0;i<castList.size();i++) {
						StudAdmCatwise studAdmCat = new StudAdmCatwise();

						studAdmCat.setCastId(castList.get(i).getCastId());
						
						studAdmCat.setCatTotStudent(Integer.parseInt(request.getParameter("cast_tot_stud"+castList.get(i).getCastId())));
						studAdmCat.setFemaleStudent(Integer.parseInt(request.getParameter("cast_f"+castList.get(i).getCastId())));
						studAdmCat.setMaleStudent(Integer.parseInt(request.getParameter("cast_m"+castList.get(i).getCastId())));
						studAdmCat.setTransStudent(Integer.parseInt(request.getParameter("cast_t"+castList.get(i).getCastId())));
						
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
					
					System.err.println("studListCatwise " +studListCatwise.toString());

					List<StudAdmCatwise> trainPlaceRes = restTemplate.postForObject(Constants.url + "saveStudentAdmCatwise",
							studListCatwise, List.class);

				} else {

					TrainPlacement trainPlace = new TrainPlacement();

					String almName = request.getParameter("alum_name");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					trainPlace.setContactDetail(request.getParameter("contact_detail"));
					trainPlace.setDelStatus(1);
					trainPlace.setEmpyrAdd(request.getParameter("employer_address"));
					trainPlace.setEmpyrName(request.getParameter("employer_name"));
					trainPlace.setNoStudentPlaced(Integer.parseInt(request.getParameter("no_stud_placed")));
					trainPlace.setPakageOfferd(request.getParameter("package_offered"));
					trainPlace.setPlacementId(0);
					trainPlace.setProgramName(request.getParameter("prog_name"));
					trainPlace.setProgramType(Integer.parseInt(request.getParameter("prog_type")));
					trainPlace.setSupportAgencyName(request.getParameter("sup_agency_name"));

					trainPlace.setDelStatus(1);

					int exInt1 = 0;
					trainPlace.setExInt1(exInt1);
					trainPlace.setExInt2(exInt1);
					String exVar1 = "NA";
					trainPlace.setExVar1(exVar1);
					trainPlace.setExVar2(exVar1);
					trainPlace.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session
					trainPlace.setIsActive(1);
					trainPlace.setMakerUserId(userObj.getUserId());// get from Session

					trainPlace.setMakerDatetime(curDateTime);
					int yearId = (int) session.getAttribute("acYearId");
					trainPlace.setYearId(yearId);

					TrainPlacement trainPlaceRes = restTemplate.postForObject(Constants.url + "saveTrainPlacement",
							trainPlace, TrainPlacement.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showStudTran";
				else
					redirect = "redirect:/showAddStudTran";
			}

		} catch (Exception e) {
			System.err.println("Exce in save insertTrainPlace  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;

	}

	
	

}
