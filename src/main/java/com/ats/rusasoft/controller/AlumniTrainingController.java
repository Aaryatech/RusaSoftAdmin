package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.master.model.prodetail.AlumniDetail;
import com.ats.rusasoft.master.model.prodetail.Cast;
import com.ats.rusasoft.master.model.prodetail.GetAlumni;
import com.ats.rusasoft.master.model.prodetail.GetTrainPlace;
import com.ats.rusasoft.master.model.prodetail.ProgramType;
import com.ats.rusasoft.master.model.prodetail.TrainPlacement;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
public class AlumniTrainingController {

	@RequestMapping(value = "/showAlumini", method = RequestMethod.GET)
	public ModelAndView showAlumini(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/alumini");

			model.addObject("title", "Alumini Association/Contribution");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			// GetAlumni

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());

			map.add("yearId", session.getAttribute("acYearId"));

			GetAlumni[] almArray = restTemplate.postForObject(Constants.url + "getAlumniList", map, GetAlumni[].class);
			List<GetAlumni> alumList = new ArrayList<>(Arrays.asList(almArray));
			System.err.println("alumList " + alumList.toString());

			model.addObject("alumList", alumList);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddAlumini", method = RequestMethod.GET)
	public ModelAndView showAddAlumini(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/addAluminiDetails");

			model.addObject("title", "Add Alumini Contribution Detail");

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// insertAlumni

	@RequestMapping(value = "/insertAlumni", method = RequestMethod.POST)
	public String insertAlumni(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertAlumni");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int alumniId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				alumniId = Integer.parseInt(request.getParameter("alumni_id"));
			} catch (Exception e) {
				alumniId = 0;
			}

			System.err.println("alumniId id  " + alumniId);

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
				if (alumniId == 0) {
					AlumniDetail alumni = new AlumniDetail();

					String almName = request.getParameter("alum_name");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					alumni.setAddDate(curDate);
					alumni.setDelStatus(1);
					alumni.setAlumniDetailId(alumniId);

					alumni.setAlumniName(almName);
					int exInt1 = 0;
					alumni.setExInt1(exInt1);
					alumni.setExInt2(exInt1);
					String exVar1 = "NA";
					alumni.setExVar1(exVar1);
					alumni.setExVar2(exVar1);
					alumni.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session
					alumni.setIsActive(1);
					alumni.setMakerUserId(userObj.getUserId());// get from Session
					if (request.getParameter("benif_to").equals("7")) {
						alumni.setBenefitTo(request.getParameter("other_benif"));

					} else {
						alumni.setBenefitTo(request.getParameter("benif_to"));

					}

					alumni.setContributionType(Integer.parseInt(request.getParameter("contr_type")));
					alumni.setContributionYear(request.getParameter("contr_year"));
					alumni.setMakerDatetime(curDateTime);
					alumni.setPassingYear(request.getParameter("year_of_pass"));
					alumni.setProgramId(1);
					int yearId = (int) session.getAttribute("acYearId");
					alumni.setYearId(yearId);

					AlumniDetail editInst = restTemplate.postForObject(Constants.url + "saveAlumni", alumni,
							AlumniDetail.class);

				} else {

					map.add("alumniId", alumniId);
					AlumniDetail alumni = restTemplate.postForObject(Constants.url + "getAlumni", map,
							AlumniDetail.class);

					String almName = request.getParameter("alum_name");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					alumni.setAddDate(curDate);
					alumni.setDelStatus(1);
					alumni.setAlumniDetailId(alumniId);

					alumni.setAlumniName(almName);
					// alumni.setInstituteId(userObj.getGetData().getUserInstituteId());// get from
					// Session
					// alumni.setIsActive(1);
					alumni.setMakerUserId(userObj.getUserId());// get from Session
					if (request.getParameter("benif_to").equals("7")) {
						alumni.setBenefitTo(request.getParameter("other_benif"));

					} else {
						alumni.setBenefitTo(request.getParameter("benif_to"));

					}

					alumni.setContributionType(Integer.parseInt(request.getParameter("contr_type")));
					alumni.setContributionYear(request.getParameter("contr_year"));
					alumni.setMakerDatetime(curDateTime);
					alumni.setPassingYear(request.getParameter("year_of_pass"));
					// alumni.setProgramId(1);
					int yearId = (int) session.getAttribute("acYearId");
					alumni.setYearId(yearId);

					AlumniDetail editInst = restTemplate.postForObject(Constants.url + "saveAlumni", alumni,
							AlumniDetail.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showAlumini";
				else
					redirect = "redirect:/showAddAlumini";
			}

		} catch (Exception e) {
			System.err.println("Exce in save Alumni  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	//

	@RequestMapping(value = "/showEditAlum", method = RequestMethod.POST)
	public ModelAndView showEditAlum(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			model = new ModelAndView("ProgramDetails/addAluminiDetails");

			model.addObject("title", "Edit Alumini Contribution Detail");

			int alumniId = Integer.parseInt(request.getParameter("edit_alum_id"));

			map.add("alumniId", alumniId);
			AlumniDetail alumni = restTemplate.postForObject(Constants.url + "getAlumni", map, AlumniDetail.class);

			model.addObject("alumni", alumni);

			model.addObject("editAccess", 0);
			model.addObject("deleteAccess", 0);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// deleteAlum

	@RequestMapping(value = "/deleteAlum/{alumniId}", method = RequestMethod.GET)
	public String deleteInstitutes(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int alumniId) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			if (alumniId == 0) {

				System.err.println("Multiple records delete ");
				String[] instIds = request.getParameterValues("instIds");
				System.out.println("id are" + instIds);

				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < instIds.length; i++) {
					sb = sb.append(instIds[i] + ",");

				}
				String instIdList = sb.toString();
				instIdList = instIdList.substring(0, instIdList.length() - 1);

				map.add("alumniIds", instIdList);
			} else {

				System.err.println("Single Record delete ");
				map.add("alumniIds", alumniId);
			}

			Info errMsg = restTemplate.postForObject(Constants.url + "deleteAlumni", map, Info.class);

		} catch (Exception e) {

			System.err.println(" Exception In deleteAlum at Alum Contr " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/showAlumini";

	}

	// Training and Placement

	@RequestMapping(value = "/showStudTran", method = RequestMethod.GET)
	public ModelAndView showStudTran(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/training");

			model.addObject("title", "Program Training Details");
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			// GetAlumni

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());

			map.add("yearId", session.getAttribute("acYearId"));

			GetTrainPlace[] traArray = restTemplate.postForObject(Constants.url + "getGetTrainPlaceList", map, GetTrainPlace[].class);
			List<GetTrainPlace> trainPlaceList = new ArrayList<>(Arrays.asList(traArray));
			System.err.println("trainPlaceList " + trainPlaceList.toString());

			model.addObject("trainPlaceList", trainPlaceList);


		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddStudTran", method = RequestMethod.GET)
	public ModelAndView showAddStudTran(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		RestTemplate restTemplate = new RestTemplate();

		try {

			model = new ModelAndView("ProgramDetails/addStudTraining");

			model.addObject("title", "Program Training Details");

			ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
					ProgramType[].class);
			List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
			System.err.println("progTypeList " + progTypeList.toString());

			model.addObject("progTypeList", progTypeList);

		} catch (Exception e) {

			System.err.println("exception In showAddStudTran at AlumTrain Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// insertTrainPlace

	@RequestMapping(value = "/insertTrainPlace", method = RequestMethod.POST)
	public String insertTrainPlace(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertTrainPlace");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int placeId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				placeId = Integer.parseInt(request.getParameter("place_id"));
			} catch (Exception e) {
				placeId = 0;
			}

			System.err.println("placeId id  " + placeId);

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
				if (placeId == 0) {
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
					trainPlace.setPlacementId(placeId);
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
					trainPlace.setPlacementId(placeId);
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
	
	//showEditT&P
	
	@RequestMapping(value = "/showEditTP", method = RequestMethod.POST)
	public ModelAndView showEditTP(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			model = new ModelAndView("ProgramDetails/addStudTraining");

			model.addObject("title", "Edit Program Training Details");

			ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
					ProgramType[].class);
			List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
			System.err.println("progTypeList " + progTypeList.toString());

			model.addObject("progTypeList", progTypeList);

			int placementId = Integer.parseInt(request.getParameter("edit_place_id"));

			map.add("placementId", placementId);
			TrainPlacement trainPlace = restTemplate.postForObject(Constants.url + "getTrainPlacement", map, TrainPlacement.class);

			model.addObject("trainPlace", trainPlace);

			model.addObject("editAccess", 0);
			model.addObject("deleteAccess", 0);

		} catch (Exception e) {

			System.err.println("exception In showEditTP at AlumTrani Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	//deleteTranPlace
	

	@RequestMapping(value = "/deleteTranPlace/{placementId}", method = RequestMethod.GET)
	public String deleteTranPlace(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int placementId) {

		try {
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			if (placementId == 0) {

				System.err.println("Multiple records delete ");
				String[] instIds = request.getParameterValues("placementId");
				System.out.println("id are" + instIds);

				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < instIds.length; i++) {
					sb = sb.append(instIds[i] + ",");

				}
				String instIdList = sb.toString();
				instIdList = instIdList.substring(0, instIdList.length() - 1);

				map.add("placementIds", instIdList);
			} else {

				System.err.println("Single Record delete ");
				map.add("placementIds", placementId);
			}

			Info errMsg = restTemplate.postForObject(Constants.url + "deleteTrainPlacement", map, Info.class);

		} catch (Exception e) {

			System.err.println(" Exception In deleteTranPlace at AlumTrain  Contr " + e.getMessage());

			e.printStackTrace();

		}

		return "redirect:/showStudTran";

	}
	

}
