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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.Names;
import com.ats.rusasoft.master.model.NewCourseInfo;
import com.ats.rusasoft.master.model.NewCourseInfoList;
import com.ats.rusasoft.master.model.prodetail.AlumniAssocAct;
import com.ats.rusasoft.master.model.prodetail.AlumniDetail;
import com.ats.rusasoft.master.model.prodetail.Cast;
import com.ats.rusasoft.master.model.prodetail.DifrentlyAbledStud;
import com.ats.rusasoft.master.model.prodetail.DifrentlyAbledStudList;
import com.ats.rusasoft.master.model.prodetail.FieldProjectsIntern;
import com.ats.rusasoft.master.model.prodetail.FieldProjectsInternList;
import com.ats.rusasoft.master.model.prodetail.GetAlumni;
import com.ats.rusasoft.master.model.prodetail.GetHigherEduDetail;
import com.ats.rusasoft.master.model.prodetail.GetTrainPlace;
import com.ats.rusasoft.master.model.prodetail.HigherEducDetail;
import com.ats.rusasoft.master.model.prodetail.ProgramType;
import com.ats.rusasoft.master.model.prodetail.TrainPlacement;
import com.ats.rusasoft.master.model.prodetail.ValueAddedCourses;
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.GovtScholarships;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class AlumniTrainingController {
	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/blockUser", method = RequestMethod.POST)
	public String blockUser(HttpServletRequest request, HttpServletResponse response) {

		String redirect = null;
		try {

			int userId = Integer.parseInt(request.getParameter("userId"));
			String listMapping = request.getParameter("listMapping");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info blockAccess = AccessControll.checkAccess("blockUser", listMapping, "0", "0", "0", "1", newModuleList);

			if (blockAccess.isError() == false) {

				map.add("userId", userId);

				Info errMsg = restTemplate.postForObject(Constants.url + "blockUser", map, Info.class);

				redirect = "redirect:/" + listMapping;

			} else {

				redirect = "redirect:/accessDenied";
			}

		} catch (Exception e) {

			System.err.println("exception In blockUser at AlumniTrainingController Contr " + e.getMessage());
			e.printStackTrace();

		}

		return redirect;

	}

	@RequestMapping(value = "/showAlumini", method = RequestMethod.GET)
	public ModelAndView showAlumini(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/alumini");

			model.addObject("title", "Alumni Contribution Activity");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showAlumini", "showAlumini", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {

				Info addAccess = AccessControll.checkAccess("showAlumini", "showAlumini", "0", "1", "0", "0",
						newModuleList);

				Info editAccess = AccessControll.checkAccess("showAlumini", "showAlumini", "0", "0", "1", "0",
						newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showAlumini", "showAlumini", "0", "0", "0", "1",
						newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				// GetAlumni

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				map.add("yearId", session.getAttribute("acYearId"));

				GetAlumni[] almArray = restTemplate.postForObject(Constants.url + "getAlumniList", map,
						GetAlumni[].class);
				List<GetAlumni> alumList = new ArrayList<>(Arrays.asList(almArray));
				// System.err.println("alumList " + alumList.toString());

				model.addObject("alumList", alumList);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAlumini at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddAlumini", method = RequestMethod.GET)
	public ModelAndView showAddAlumini(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info addAccess = AccessControll.checkAccess("showAlumini", "showAlumini", "0", "1", "0", "0",
					newModuleList);
			if (addAccess.isError() == false) {

				model = new ModelAndView("ProgramDetails/addAluminiDetails");

				model.addObject("title", "Add Alumni Contribution Activity");
				AlumniDetail alumni = new AlumniDetail();
				model.addObject("alumni", alumni);
			} else {

				model = new ModelAndView("accessDenied");
			}

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
		int amount = 0;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int alumniId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				alumniId = Integer.parseInt(request.getParameter("alumni_id"));

			} catch (Exception e) {
				alumniId = 0;
			}
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = null;
			if (alumniId == 0) {

				editAccess = AccessControll.checkAccess("insertAlumni", "showAlumini", "0", "1", "0", "0",
						newModuleList);
			} else {

				editAccess = AccessControll.checkAccess("insertAlumni", "showAlumini", "0", "0", "1", "0",
						newModuleList);
			}

			System.err.println("alumniId id  " + alumniId);

			// editAccess.setError(false);

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

					amount = Integer.parseInt(request.getParameter("alumini_amt"));
					if (amount > 0) {
						alumni.setExInt1(amount);
						alumni.setExInt2(1);
					} else {
						alumni.setExInt1(amount);
						alumni.setExInt2(0);
					}
					String exVar1 = "NA";
					alumni.setExVar1(request.getParameter("designation"));
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
					System.out.println(alumni.toString());
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
					alumni.setExVar1(request.getParameter("designation"));
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

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("showEditAlum", "showAlumini", "0", "0", "1", "",
					newModuleList);

			if (editAccess.isError() == false) {
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				model = new ModelAndView("ProgramDetails/addAluminiDetails");

				model.addObject("title", "Edit Alumni Contribution Activity");

				int alumniId = Integer.parseInt(request.getParameter("edit_alum_id"));

				map.add("alumniId", alumniId);
				AlumniDetail alumni = restTemplate.postForObject(Constants.url + "getAlumni", map, AlumniDetail.class);

				model.addObject("alumni", alumni);

				model.addObject("editAccess", 0);
				model.addObject("deleteAccess", 0);
			} else {
				model = new ModelAndView("accessDenied");
			}
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
		String a = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info delAccess = AccessControll.checkAccess("deleteAlum", "showAlumini", "0", "0", "0", "1", newModuleList);

			if (delAccess.isError() == false) {
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

				a = "redirect:/showAlumini";
			} else {
				a = "redirect:/accessDenied";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteAlum at Alum Contr " + e.getMessage());

			e.printStackTrace();

		}

		return a;

	}

	// Training and Placement

	@RequestMapping(value = "/showStudTran", method = RequestMethod.GET)
	public ModelAndView showStudTran(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showStudTran", "showStudTran", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("ProgramDetails/training");

				Info addAccess = AccessControll.checkAccess("showStudTran", "showStudTran", "0", "1", "0", "0",
						newModuleList);

				Info editAccess = AccessControll.checkAccess("showStudTran", "showStudTran", "0", "0", "1", "0",
						newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showStudTran", "showStudTran", "0", "0", "0", "1",
						newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				model.addObject("title", "Student Placement Details");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				RestTemplate restTemplate = new RestTemplate();

				// GetAlumni

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				map.add("yearId", session.getAttribute("acYearId"));

				GetTrainPlace[] traArray = restTemplate.postForObject(Constants.url + "getGetTrainPlaceList", map,
						GetTrainPlace[].class);
				List<GetTrainPlace> trainPlaceList = new ArrayList<>(Arrays.asList(traArray));
				System.err.println("trainPlaceList " + trainPlaceList.toString());

				model.addObject("trainPlaceList", trainPlaceList);
				model.addObject("figure", Names.Rupees);
			} else {

				model = new ModelAndView("accessDenied");

			}

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

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info access = null;

			access = AccessControll.checkAccess("showAddStudTran", "showStudTran", "0", "1", "0", "0", newModuleList);
			if (access.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				model = new ModelAndView("ProgramDetails/addStudTraining");

				model.addObject("title", "Add Student Placement Details");
				model.addObject("figure", Names.Rupees);
				

				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				// System.err.println("progTypeList " + progTypeList.toString());

				model.addObject("progTypeList", progTypeList);
			}

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
			String minPackge = "-";
			String maxPackge = "-";
			String suuppAgnc = "NA";
			try {
				placeId = Integer.parseInt(request.getParameter("place_id"));
			} catch (Exception e) {
				placeId = 0;
			}

			System.err.println("placeId id  " + placeId);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info access = null;

			if (placeId == 0) {

				access = AccessControll.checkAccess("insertTrainPlace", "showStudTran", "0", "1", "0", "0",
						newModuleList);
			} else {
				access = AccessControll.checkAccess("insertTrainPlace", "showStudTran", "0", "0", "1", "0",
						newModuleList);
			}
			/// editAccess.setError(false);

			if (access.isError() == true) {
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
					
					minPackge = request.getParameter("package_offered");
					maxPackge = request.getParameter("max_package_offered");
					if (minPackge == null) {
						trainPlace.setPakageOfferd(minPackge);
					} else {
						trainPlace.setPakageOfferd(minPackge);
					}
					
					if (maxPackge == null) {
						trainPlace.setExVar1(maxPackge);
					} else {
						trainPlace.setExVar1(maxPackge);
					}
					

					trainPlace.setPlacementId(placeId);
					trainPlace.setProgramName(request.getParameter("prog_name"));
					trainPlace.setProgramType(Integer.parseInt(request.getParameter("prog_type")));

					suuppAgnc = request.getParameter("sup_agency_name");
					if (suuppAgnc == null) {
						trainPlace.setSupportAgencyName(suuppAgnc);
					} else {
						trainPlace.setSupportAgencyName(suuppAgnc);
					}
					trainPlace.setDelStatus(1);

					int exInt1 = 0;
					trainPlace.setExInt1(exInt1);
					trainPlace.setExInt2(exInt1);
					trainPlace.setExVar2("NA");
					trainPlace.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session
					trainPlace.setIsActive(1);
					trainPlace.setMakerUserId(userObj.getUserId());// get from Session

					trainPlace.setMakerDatetime(curDateTime);
					int yearId = (int) session.getAttribute("acYearId");
					trainPlace.setYearId(yearId);

					System.out.println("Placements:" + trainPlace.toString());

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
					
					minPackge = request.getParameter("package_offered");
					maxPackge = request.getParameter("max_package_offered");
					if (minPackge == null) {
						trainPlace.setPakageOfferd(minPackge);
					} else {
						trainPlace.setPakageOfferd(minPackge);
					}
					
					if (maxPackge == null) {
						trainPlace.setExVar1(maxPackge);
					} else {
						trainPlace.setExVar1(maxPackge);
					}
					
					trainPlace.setPlacementId(placeId);
					trainPlace.setProgramName(request.getParameter("prog_name"));
					trainPlace.setProgramType(Integer.parseInt(request.getParameter("prog_type")));
					suuppAgnc = request.getParameter("sup_agency_name");
					if (suuppAgnc == null) {
						trainPlace.setSupportAgencyName(suuppAgnc);
					} else {
						trainPlace.setSupportAgencyName(suuppAgnc);
					}

					trainPlace.setDelStatus(1);

					int exInt1 = 0;
					trainPlace.setExInt1(exInt1);
					trainPlace.setExInt2(exInt1);
					trainPlace.setExVar2("NA");
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

	// showEditT&P

	@RequestMapping(value = "/showEditTP", method = RequestMethod.POST)
	public ModelAndView showEditTP(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info access = null;

			access = AccessControll.checkAccess("showEditTP", "showStudTran", "0", "0", "1", "0", newModuleList);

			if (access.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				model = new ModelAndView("ProgramDetails/addStudTraining");

				model.addObject("title", "Edit Student Placement Details");

				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				System.err.println("progTypeList " + progTypeList.toString());

				model.addObject("progTypeList", progTypeList);

				int placementId = Integer.parseInt(request.getParameter("edit_place_id"));

				map.add("placementId", placementId);
				TrainPlacement trainPlace = restTemplate.postForObject(Constants.url + "getTrainPlacement", map,
						TrainPlacement.class);

				model.addObject("trainPlace", trainPlace);

				model.addObject("editAccess", 0);
				model.addObject("deleteAccess", 0);
			}

		} catch (Exception e) {

			System.err.println("exception In showEditTP at AlumTrani Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// deleteTranPlace

	@RequestMapping(value = "/deleteTranPlace/{placementId}", method = RequestMethod.GET)
	public String deleteTranPlace(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int placementId) {
		String redirect = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info access = null;

			access = AccessControll.checkAccess("deleteTranPlace", "showStudTran", "0", "0", "0", "1", newModuleList);

			if (access.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
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
				redirect = "redirect:/showStudTran";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteTranPlace at AlumTrain  Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect;
	}

	@RequestMapping(value = "/showHighEdu", method = RequestMethod.GET)
	public ModelAndView showHighEdu(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/highEdu");

			model.addObject("title", "Progression to Higher Education Details");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			// GetAlumni

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showHighEdu", "showHighEdu", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {

				Info addAccess = AccessControll.checkAccess("showHighEdu", "showHighEdu", "0", "1", "0", "0",
						newModuleList);

				Info editAccess = AccessControll.checkAccess("showHighEdu", "showHighEdu", "0", "0", "1", "0",
						newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showHighEdu", "showHighEdu", "0", "0", "0", "1",
						newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				map.add("yearId", session.getAttribute("acYearId"));

				GetHigherEduDetail[] eduArray = restTemplate.postForObject(Constants.url + "getHigherEduDetailList",
						map, GetHigherEduDetail[].class);
				List<GetHigherEduDetail> highEduList = new ArrayList<>(Arrays.asList(eduArray));
				// System.err.println("highEduList " + highEduList.toString());

				model.addObject("highEduList", highEduList);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showHighEdu List Page  at Alumni Con Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddHighEdu", method = RequestMethod.GET)
	public ModelAndView showAddHighEdu(HttpServletRequest request, HttpServletResponse response) {
		RestTemplate restTemplate = new RestTemplate();

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info access = AccessControll.checkAccess("showAddHighEdu", "showHighEdu", "0", "1", "0", "0",
					newModuleList);

			if (access.isError() == false) {
				model = new ModelAndView("ProgramDetails/addHighEducation");

				model.addObject("title", "Add Progression to Higher Education Details ");

				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				// System.err.println("progTypeList " + progTypeList.toString());

				model.addObject("progTypeList", progTypeList);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// getProgTypeByProgId

	@RequestMapping(value = "/getProgTypeByProgId", method = RequestMethod.GET)
	public @ResponseBody List<ProgramType> getProgTypeByProgId(HttpServletRequest request,
			HttpServletResponse response) {
		List<ProgramType> progTypeList = null;
		try {
			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			int progTypeId = Integer.parseInt(request.getParameter("progId"));
			map.add("progTypeId", progTypeId);

			ProgramType[] progTypes = restTemplate.postForObject(Constants.url + "getHigherProgList", map,
					ProgramType[].class);
			progTypeList = new ArrayList<>(Arrays.asList(progTypes));
			// System.err.println("progTypeList " + progTypeList.toString());

		} catch (Exception e) {

			System.err.println("exception In getProgTypeByProgId at Ajax " + e.getMessage());

			e.printStackTrace();
		}

		return progTypeList;
	}

	// insertHigherEduDetail

	@RequestMapping(value = "/insertHigherEduDetail", method = RequestMethod.POST)
	public String insertHigherEduDetail(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertHigherEduDetail");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int eduDetailId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				eduDetailId = Integer.parseInt(request.getParameter("high_edu_id"));
			} catch (Exception e) {
				eduDetailId = 0;
			}

			// System.err.println("eduDetailId id " + eduDetailId);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info access = null;
			if (eduDetailId == 0) {
				access = AccessControll.checkAccess("insertHigherEduDetail", "showHighEdu", "0", "1", "0", "0",
						newModuleList);
			} else {
				access = AccessControll.checkAccess("insertHigherEduDetail", "showHighEdu", "0", "0", "1", "0",
						newModuleList);
			}

			if (access.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				if (eduDetailId == 0 || eduDetailId > 0) {

					HigherEducDetail higherEdu = new HigherEducDetail();

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					higherEdu.setEducationDetailId(eduDetailId);

					higherEdu.setNoStudent(Integer.parseInt(request.getParameter("no_of_student")));
					higherEdu.setProceedingTo(Integer.parseInt(request.getParameter("proceed_prog_type")));
					higherEdu.setProgramType(Integer.parseInt(request.getParameter("prog_type")));

					int exInt1 = 0;
					String exVar1 = "NA";

					higherEdu.setExInt1(exInt1);
					higherEdu.setExInt2(exInt1);
					higherEdu.setExVar1(exVar1);
					higherEdu.setExVar2(exVar1);

					higherEdu.setIsActive(1);
					higherEdu.setDelStatus(1);

					higherEdu.setMakerUserId(userObj.getUserId());// get from Session
					higherEdu.setMakerDatetime(curDateTime);

					int yearId = (int) session.getAttribute("acYearId");

					higherEdu.setYearId(yearId);
					higherEdu.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session

					HigherEducDetail trainPlaceRes = restTemplate.postForObject(Constants.url + "saveHigherEducDetail",
							higherEdu, HigherEducDetail.class);

				} else {

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				System.err.println("is View  " + isView);
				if (isView == 1)
					redirect = "redirect:/showHighEdu";
				else
					redirect = "redirect:/showAddHighEdu";
			}

		} catch (Exception e) {
			System.err.println("Exce in save insertHigherEduDetail  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;

	}

	// showEditEduDetail

	// edit_eduDet_id
	@RequestMapping(value = "/showEditEduDetail", method = RequestMethod.POST)
	public ModelAndView showEditEduDetail(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info access = AccessControll.checkAccess("showEditEduDetail", "showHighEdu", "0", "0", "1", "0",
					newModuleList);

			if (access.isError() == false) {

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				model = new ModelAndView("ProgramDetails/addHighEducation");

				model.addObject("title", "Edit Progression to Higher Education Details");

				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				// System.err.println("progTypeList " + progTypeList.toString());

				model.addObject("progTypeList", progTypeList);

				int eduDetailId = Integer.parseInt(request.getParameter("edit_eduDet_id"));

				map.add("eduDetailId", eduDetailId);
				HigherEducDetail highEduDet = restTemplate.postForObject(Constants.url + "getHigherEducDetail", map,
						HigherEducDetail.class);

				model.addObject("highEduDet", highEduDet);

				model.addObject("editAccess", 0);
				model.addObject("deleteAccess", 0);
			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showEditTP at AlumTrani Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// deleteEduDetail

	@RequestMapping(value = "/deleteEduDetail/{educationDetailId}", method = RequestMethod.GET)
	public String deleteEduDetail(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int educationDetailId) {
		String redirect = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info access = AccessControll.checkAccess("deleteEduDetail", "showHighEdu", "0", "0", "0", "1",
					newModuleList);

			if (access.isError() == false) {
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (educationDetailId == 0) {

					// System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("educationDetailId");
					// System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String instIdList = sb.toString();
					instIdList = instIdList.substring(0, instIdList.length() - 1);

					map.add("educationDetailIds", instIdList);
				} else {

					System.err.println("Single Record delete  /deleteHigherEducDetail");
					map.add("educationDetailIds", educationDetailId);
				}

				Info errMsg = restTemplate.postForObject(Constants.url + "deleteHigherEducDetail", map, Info.class);
				redirect = "redirect:/showHighEdu";
			} else {

				redirect = "redirect:/accessDenied";

			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteEduDetail at AlumTrain  Contr " + e.getMessage());

			e.printStackTrace();
		}

		return redirect;

	}

	/***************************************
	 * Alumni Association Activity
	 **********************************/

	@RequestMapping(value = "/showAlumniAssociationActivity", method = RequestMethod.GET)
	public ModelAndView showAlumniAssociationActivity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/almniAssoActList");

			model.addObject("title", "Alumni Association Activity");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showAlumniAssociationActivity",
					"showAlumniAssociationActivity", "1", "0", "0", "0", newModuleList);

			if (viewAccess.isError() == false) {

				Info addAccess = AccessControll.checkAccess("showAlumniAssociationActivity",
						"showAlumniAssociationActivity", "0", "1", "0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showAlumniAssociationActivity",
						"showAlumniAssociationActivity", "0", "0", "1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showAlumniAssociationActivity",
						"showAlumniAssociationActivity", "0", "0", "0", "1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				System.err.println(
						"sess " + userObj.getGetData().getUserInstituteId() + "  " + session.getAttribute("acYearId"));
				map.add("instituteId", userObj.getGetData().getUserInstituteId());
				map.add("yId", session.getAttribute("acYearId"));

				AlumniAssocAct[] almArray = rest.postForObject(Constants.url + "getAlumniAssocActivitiesList", map,
						AlumniAssocAct[].class);
				List<AlumniAssocAct> alumList = new ArrayList<>(Arrays.asList(almArray));
				System.err.println("alumList " + alumList.toString());

				model.addObject("alumList", alumList);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAlumniAssociationActivity at AlumniTrain Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addAlumniAssociationActivity", method = RequestMethod.GET)
	public ModelAndView addAlumniAssociationActivity(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info addAccess = AccessControll.checkAccess("addAlumniAssociationActivity", "showAlumniAssociationActivity",
					"0", "1", "0", "0", newModuleList);
			if (addAccess.isError() == false) {

				model = new ModelAndView("ProgramDetails/addAlmniAssocAct");

				model.addObject("title", "Add Alumni Association Activity");
				AlumniAssocAct alumni = new AlumniAssocAct();
				model.addObject("alumni", alumni);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In addAlumniAssociationActivity at AlmniTrain Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}// insertAlumniAssocAct

	@RequestMapping(value = "/insertAlumniAssocAct", method = RequestMethod.POST)
	public String insertAlumniAssocAct(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String curDateTime = dateFormat.format(cal.getTime());

			AlumniAssocAct alumni = new AlumniAssocAct();
			alumni.setAlmAssocActId(Integer.parseInt(request.getParameter("alumni_assoc_act_id")));
			alumni.setAlumniMeetngAgnda(request.getParameter("alum_meet_agnd"));
			alumni.setDateOfMeeting(request.getParameter("date_meet"));
			alumni.setNameAlumniAssoc(request.getParameter("name_alumini_assoc"));
			alumni.setAlumniRegNo(Integer.parseInt(request.getParameter("almni_reg_no")));
			alumni.setDateAlumniReg(request.getParameter("date_of_almni_reg"));
			alumni.setNoAlumniReg(Integer.parseInt(request.getParameter("registred_almni_no")));
			alumni.setNoMemberAttended(Integer.parseInt(request.getParameter("no_member_attnd")));
			alumni.setTtlNoAlumniEnrolled(Integer.parseInt(request.getParameter("ttl_no_almni_enrolled")));
			alumni.setInstId(instituteId);
			alumni.setAcYearId(yId);
			alumni.setDelStatus(1);
			alumni.setIsActive(1);
			alumni.setMakerUserId(userId);
			alumni.setMakerEnterDatetime(curDateTime);
			alumni.setExInt1(0);
			alumni.setExInt2(0);
			alumni.setExVar1("NA");
			alumni.setExVar2("NA");

			AlumniAssocAct almAct = rest.postForObject(Constants.url + "/saveAlumniAssoAct", alumni,
					AlumniAssocAct.class);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return "redirect:/showAlumniAssociationActivity";

	}

	@RequestMapping(value = "/editAlumAlumniAssocAct/{almniActivityId}", method = RequestMethod.GET)
	public ModelAndView editAlumAlumniAssocAct(@PathVariable("almniActivityId") int almniActivityId,
			HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("ProgramDetails/addAlmniAssocAct");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editAlumAlumniAssocAct/{almniActivityId}",
					"showAlumniAssociationActivity", "0", "0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

				map.add("almniActivityId", almniActivityId);

				AlumniAssocAct alumni = rest.postForObject(Constants.url + "/editAlumniAssocActivityById", map,
						AlumniAssocAct.class);
				model.addObject("alumni", alumni);

				model.addObject("title", "Edit Alumni Association Activity");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteAlumniAssocAct/{almniActivityId}", method = RequestMethod.GET)
	public String deleteAlumniAssocAct(@PathVariable("almniActivityId") int almniActivityId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteAlumniAssocAct/{almniActivityId}",
					"showAlumniAssociationActivity", "0", "0", "0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("almniActivityId", almniActivityId);

				AlumniAssocAct delIct = rest.postForObject(Constants.url + "/delAlumniAssocActivityById", map,
						AlumniAssocAct.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showAlumniAssociationActivity";
	}

	@RequestMapping(value = "/deleteSelectedAlum/{alumni}", method = RequestMethod.GET)
	public String deleteSelectedAlum(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int alumni) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteSelectedAlum/{alumni}", "showAlumniAssociationActivity", "0",
					"0", "0", "1", newModuleList);

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (alumni == 0) {

					System.err.println("Multiple records delete ");
					String[] alumnis = request.getParameterValues("alumni");
					System.out.println("id are" + alumnis);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < alumnis.length; i++) {
						sb = sb.append(alumnis[i] + ",");

					}
					String alumniList = sb.toString();
					alumniList = alumniList.substring(0, alumniList.length() - 1);

					map.add("alumniList", alumniList);
				} else {

					System.err.println("Single Record delete ");
					map.add("alumniList", alumni);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelAlumni", map, Info.class);

				a = "redirect:/showAlumniAssociationActivity";
			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteSelectedAlum at Institute Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/*********************************
	 * New Course Information
	 *********************************/

	@RequestMapping(value = "/showNewCourseInfo", method = RequestMethod.GET)
	public ModelAndView showNewCourseInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showNewCourseInfo", "showNewCourseInfo", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("ProgramDetails/showNewCourscInfo");

				Info addAccess = AccessControll.checkAccess("showNewCourseInfo", "showNewCourseInfo", "0", "1", "0",
						"0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showNewCourseInfo", "showNewCourseInfo", "0", "0", "1",
						"0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showNewCourseInfo", "showNewCourseInfo", "0", "0", "0",
						"1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				model.addObject("title", "New Courses Information");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				RestTemplate restTemplate = new RestTemplate();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				map.add("yearId", session.getAttribute("acYearId"));
				System.out.println("Sess Data=" + userObj.getGetData().getUserInstituteId() + " "
						+ session.getAttribute("acYearId"));

				NewCourseInfoList[] courseArr = restTemplate.postForObject(Constants.url + "getAllNewCourseList", map,
						NewCourseInfoList[].class);
				List<NewCourseInfoList> courseList = new ArrayList<>(Arrays.asList(courseArr));
				System.err.println("courseArr " + courseList.toString());

				model.addObject("courseList", courseList);
			} else {

				model = new ModelAndView("accessDenied");

			}

		} catch (Exception e) {

			System.err.println("exception In New Course Information at Alumni Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addNewCourseInfo", method = RequestMethod.GET)
	public ModelAndView addNewCourseInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info access = null;

			access = AccessControll.checkAccess("addNewCourseInfo", "showNewCourseInfo", "0", "1", "0", "0",
					newModuleList);
			if (access.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				model = new ModelAndView("ProgramDetails/addNewCourseInfo");

				model.addObject("title", "Add New Course Information");

				NewCourseInfo newCourse = new NewCourseInfo();
				model.addObject("newCourse", newCourse);

				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				System.err.println("progTypeList " + progTypeList.toString());

				model.addObject("progTypeList", progTypeList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId", map,
						AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);
			}

		} catch (Exception e) {
			System.err.println("exception In showNewCourseInfo at AlumTrain Contr" + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/insertNewCourseInfo", method = RequestMethod.POST)
	public String insertNewCourseInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String curDateTime = dateFormat.format(cal.getTime());

			NewCourseInfo course = new NewCourseInfo();

			course.setCourseId(Integer.parseInt(request.getParameter("course_id")));
			course.setProgName(Integer.parseInt(request.getParameter("prog_name")));
			course.setProgType(Integer.parseInt(request.getParameter("prog_type")));
			course.setApplicableYear(request.getParameter("applicabl_year"));
			course.setCourseName(request.getParameter("courseName"));
			course.setCourseCode(Integer.parseInt(request.getParameter("courseCode")));
			course.setIntroduceFrom(request.getParameter("acadYear"));
			course.setDocument(request.getParameter("document"));
			course.setDelStatus(1);
			course.setIsActive(1);
			course.setMakerUserId(userId);
			course.setMakerDatetime(curDateTime);
			course.setInstId(instituteId);
			course.setExInt1(0);
			course.setExInt2(0);
			course.setExVar1("NA");
			course.setExVar2("NA");
			System.out.println("Course=" + course.toString());

			NewCourseInfo saveCourse = rest.postForObject(Constants.url + "/saveNewCourseInfo", course,
					NewCourseInfo.class);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return "redirect:/showNewCourseInfo";

	}

	@RequestMapping(value = "/editCourseInfo/{courseId}", method = RequestMethod.GET)
	public ModelAndView editCourseInfo(@PathVariable("courseId") int courseId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("ProgramDetails/addNewCourseInfo");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editCourseInfo/{courseId}", "showNewCourseInfo", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				MultiValueMap<String, Object> map = null;

				ProgramType[] progTypes = rest.getForObject(Constants.url + "getAllProgramType", ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				System.err.println("progTypeList " + progTypeList.toString());

				model.addObject("progTypeList", progTypeList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = rest.postForObject(Constants.url + "getAcademicYearListByTypeId", map,
						AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);

				
				map = new LinkedMultiValueMap<>();
				map.add("courseId", courseId);
				NewCourseInfo newCourse = rest.postForObject(Constants.url + "/getByCourseId", map,
						NewCourseInfo.class);
				System.err.println("Course " + newCourse.toString());
				model.addObject("newCourse", newCourse);

				model.addObject("title", "Edit New Course Information");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/deleteCourseInfo/{courseId}", method = RequestMethod.GET)
	public String deleteCourseInfo(@PathVariable("courseId") int courseId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteCourseInfo/{courseId}",
					"showNewCourseInfo", "0", "0", "0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("courseId", courseId);

				NewCourseInfo delCourseId = rest.postForObject(Constants.url + "/delCourseById", map,
						NewCourseInfo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showNewCourseInfo";
	}
	
	/*************************************Value Added Courses************************************/
	@RequestMapping(value = "/showValueAddedCourses", method = RequestMethod.GET)
	public ModelAndView showVlaueAddedCourses(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/showValueAddedCourses");

			model.addObject("title", "Value Added Courses");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showValueAddedCourses", "showValueAddedCourses", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {

				Info addAccess = AccessControll.checkAccess("showValueAddedCourses", "showValueAddedCourses", "0", "1", "0", "0",
						newModuleList);

				Info editAccess = AccessControll.checkAccess("showValueAddedCourses", "showValueAddedCourses", "0", "0", "1", "0",
						newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showValueAddedCourses", "showValueAddedCourses", "0", "0", "0", "1",
						newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				// GetAlumni

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				map.add("yearId", session.getAttribute("acYearId"));

				ValueAddedCourses[] courseArray = restTemplate.postForObject(Constants.url + "getValueAddedCurseList", map,
						ValueAddedCourses[].class);
				List<ValueAddedCourses> courseList = new ArrayList<>(Arrays.asList(courseArray));
				// System.err.println("alumList " + alumList.toString());

				model.addObject("crsList", courseList);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAlumini at Alumini Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/addValAddCourse", method = RequestMethod.GET)
	public ModelAndView addValAddCourse(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info addAccess = AccessControll.checkAccess("addValAddCourse", "showValueAddedCourses", "0", "1", "0", "0",
					newModuleList);
			if (addAccess.isError() == false) {

				model = new ModelAndView("ProgramDetails/newValueAddCourse");

				model.addObject("title", "Add Value Added Courses");
				ValueAddedCourses value = new ValueAddedCourses();
				model.addObject("value", value);
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In addValAddCourse at Alumini Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/insertValueAddedCourse", method = RequestMethod.POST)
	public String insertValueAddedCourse(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String curDateTime = dateFormat.format(cal.getTime());

			ValueAddedCourses course = new ValueAddedCourses();

			course.setValueAddedCourseId(Integer.parseInt(request.getParameter("alumni_id")));
			course.setValueAddedCourseName(request.getParameter("course_name"));
			course.setCourseCode(request.getParameter("course_code"));
			course.setYearOfOffering(request.getParameter("year_of_offering"));
			course.setNoOfTimesOffer(Integer.parseInt(request.getParameter("no_times_offer")));
			course.setYearOfDiscontinuation(request.getParameter("year_of_discontinue"));
			course.setNoOfStudentsEnrolled(Integer.parseInt(request.getParameter("no_student_enrolled")));
			course.setNoOfStudentsCompletedCourse(Integer.parseInt(request.getParameter("no_student_completed_course")));
			course.setDelStatus(1);
			course.setIsActive(1);
			course.setAcademicYearId(yId);
			course.setMakerUserId(userId);
			course.setMakerEnterDatetime(curDateTime);
			course.setInstId(instituteId);
			course.setExInt1(0);
			course.setExInt2(0);
			course.setExVar1("NA");
			course.setExVar2("NA");
			System.out.println("Course=" + course.toString());

			ValueAddedCourses saveValAddCourse = rest.postForObject(Constants.url + "/saveValueAddedCourse", course,
					ValueAddedCourses.class);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return "redirect:/showValueAddedCourses";

	}


	@RequestMapping(value = "/editCourse/{courseId}", method = RequestMethod.GET)
	public ModelAndView editCourse(@PathVariable("courseId") int courseId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("ProgramDetails/newValueAddCourse");

		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editCourse/{courseId}", "showValueAddedCourses", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				MultiValueMap<String, Object> map = null;
				
				map = new LinkedMultiValueMap<>();
				map.add("courseId", courseId);
				ValueAddedCourses value = rest.postForObject(Constants.url + "/getValueAddedCourseById", map,
						ValueAddedCourses.class);
				model.addObject("value", value);

				model.addObject("title", "Edit Value Added Courses");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/deleteCourse/{courseId}", method = RequestMethod.GET)
	public String deleteCourse(@PathVariable("courseId") int courseId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteCourse/{courseId}",
					"showValueAddedCourses", "0", "0", "0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("courseId", courseId);

				NewCourseInfo delCourseId = rest.postForObject(Constants.url + "/deleteValueAddedCourse", map,
						NewCourseInfo.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showValueAddedCourses";
	}
	
	/*************************************Field Projects/Internships************************************/
	
	@RequestMapping(value = "/showFieldProjectIntern", method = RequestMethod.GET)
	public ModelAndView showFieldProjectIntern(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/showFieldProjectInternship");

			model.addObject("title", "Field Projects/Internships");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showFieldProjectIntern", "showFieldProjectIntern", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {

				Info addAccess = AccessControll.checkAccess("showFieldProjectIntern", "showFieldProjectIntern", "0", "1", "0", "0",
						newModuleList);

				Info editAccess = AccessControll.checkAccess("showFieldProjectIntern", "showFieldProjectIntern", "0", "0", "1", "0",
						newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showFieldProjectIntern", "showFieldProjectIntern", "0", "0", "0", "1",
						newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				map.add("yearId", session.getAttribute("acYearId"));

				FieldProjectsInternList[] fieldArray = restTemplate.postForObject(Constants.url + "getProjectInternFieldList", map,
						FieldProjectsInternList[].class);
				List<FieldProjectsInternList> fieldList = new ArrayList<>(Arrays.asList(fieldArray));
				// System.err.println("alumList " + alumList.toString());

				model.addObject("fieldList", fieldList);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showFieldProjectIntern at Alumini Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/addFieldProject", method = RequestMethod.GET)
	public ModelAndView addFieldProject(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info access = null;

			access = AccessControll.checkAccess("addFieldProject", "showFieldProjectIntern", "0", "1", "0", "0",
					newModuleList);
			if (access.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				model = new ModelAndView("ProgramDetails/addFieldProjctIntern");

				model.addObject("title", "Add Field Projects/Internships");

				FieldProjectsIntern fieldProject = new FieldProjectsIntern();
				model.addObject("fieldProject", fieldProject);

				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				System.err.println("progTypeList " + progTypeList.toString());

				model.addObject("progTypeList", progTypeList);

			}

		} catch (Exception e) {
			System.err.println("exception In showNewCourseInfo at AlumTrain Contr" + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	@RequestMapping(value = "/insertFieldProject", method = RequestMethod.POST)
	public String insertFieldProject(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String curDateTime = dateFormat.format(cal.getTime());

			FieldProjectsIntern field = new FieldProjectsIntern();

			field.setFieldProjectInternId(Integer.parseInt(request.getParameter("field_id")));
			field.setProgramName(Integer.parseInt(request.getParameter("prog_name")));
			field.setProgramType(Integer.parseInt(request.getParameter("prog_type")));
			field.setProvisionForUndertaking(request.getParameter("pro_undertake"));
			field.setNoOfStudUndertaking(Integer.parseInt(request.getParameter("no_stud_undertake")));
			field.setDocument(request.getParameter("document"));
			field.setDelStatus(1);
			field.setIsActive(1);
			field.setAcYearId(yId);
			field.setMakerUserId(userId);
			field.setMakerEnterDatetime(curDateTime);
			field.setInstId(instituteId);
			field.setExInt1(0);
			field.setExInt2(0);
			field.setExVar1("NA");
			field.setExVar2("NA");
			System.out.println("field=" + field.toString());

			FieldProjectsIntern saveValAddCourse = rest.postForObject(Constants.url + "/saveFieldProjectsIntern", field,
					FieldProjectsIntern.class);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return "redirect:/showFieldProjectIntern";

	}
	
	@RequestMapping(value = "/editProjectintern/{fieldId}", method = RequestMethod.GET)
	public ModelAndView editProjectintern(@PathVariable("fieldId") int fieldId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("ProgramDetails/addFieldProjctIntern");

		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editProjectintern/{fieldId}", "showFieldProjectIntern", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				MultiValueMap<String, Object> map = null;
				
				map = new LinkedMultiValueMap<>();
				map.add("fieldId", fieldId);
				FieldProjectsIntern fieldProject = rest.postForObject(Constants.url + "/getFieldProjectInternById", map,
						FieldProjectsIntern.class);
				System.err.println("Project " + fieldProject.toString());
				
				model.addObject("fieldProject", fieldProject);
				
				ProgramType[] progTypes = rest.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				System.err.println("Prg List " + progTypeList.toString());
				model.addObject("progTypeList", progTypeList);
				
				model.addObject("title", "Edit Field Projects/Internships");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/deleteProjectintern/{fieldId}", method = RequestMethod.GET)
	public String deleteProjectintern(@PathVariable("fieldId") int fieldId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteProjectintern/{fieldId}",
					"showFieldProjectIntern", "0", "0", "0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("fieldId", fieldId);

				FieldProjectsIntern delFeildId = rest.postForObject(Constants.url + "/deleteFieldProjectById", map,
						FieldProjectsIntern.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showFieldProjectIntern";
	}
	
	/******************************Differently Abled_Student*******************************/

	@RequestMapping(value = "/showDifferentlyAbledStudent", method = RequestMethod.GET)
	public ModelAndView showDifferentlyAbledStudent(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showDifferentlyAbledStudent", "showDifferentlyAbledStudent", "1", "0", "0", "0",
					newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("ProgramDetails/showDifrntlyDisbldStud");

				Info addAccess = AccessControll.checkAccess("showDifferentlyAbledStudent", "showDifferentlyAbledStudent", "0", "1", "0",
						"0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showDifferentlyAbledStudent", "showDifferentlyAbledStudent", "0", "0", "1",
						"0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showDifferentlyAbledStudent", "showDifferentlyAbledStudent", "0", "0", "0",
						"1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				model.addObject("title", "Differently Abled Students (Divyangjan)");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				RestTemplate restTemplate = new RestTemplate();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				map.add("yearId", session.getAttribute("acYearId"));
				System.out.println("Sess Data=" + userObj.getGetData().getUserInstituteId() + " "
						+ session.getAttribute("acYearId"));

				DifrentlyAbledStudList[] difStudArr = restTemplate.postForObject(Constants.url + "getAllDifferentlyDisableStudentsList", map,
						DifrentlyAbledStudList[].class);
				List<DifrentlyAbledStudList> difStudList = new ArrayList<>(Arrays.asList(difStudArr));
				System.out.println("difStudList " + difStudList.toString());

				model.addObject("difStudList", difStudList);
			} else {

				model = new ModelAndView("accessDenied");

			}

		} catch (Exception e) {

			System.err.println("exception In showDifferentlyAbledStudent at Alumni Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/addNewDifAbldStudent", method = RequestMethod.GET)
	public ModelAndView addNewDifAbldStudent(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info access = null;

			access = AccessControll.checkAccess("addNewDifAbldStudent", "showNewCourseInfo", "0", "1", "0", "0",
					newModuleList);
			if (access.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				model = new ModelAndView("ProgramDetails/addDifAbldStud");

				model.addObject("title", "Add Differently Abled Students (Divyangjan)");

				DifrentlyAbledStud difDisStud = new DifrentlyAbledStud();
				model.addObject("difDisStud", difDisStud);

				ProgramType[] progTypes = restTemplate.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				System.err.println("progTypeList " + progTypeList.toString());

				model.addObject("progTypeList", progTypeList);

			}

		} catch (Exception e) {
			System.err.println("exception In addNewDifAbldStudent at AlumTrain Contr" + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	@RequestMapping(value = "/insertDifrnDisStudent", method = RequestMethod.POST)
	public String insertDifrnDisStudent(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int yId = (int) session.getAttribute("acYearId");

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();

			String curDateTime = dateFormat.format(cal.getTime());

			DifrentlyAbledStud stud = new DifrentlyAbledStud();

			stud.setDifAbleStudId(Integer.parseInt(request.getParameter("studDifDisId")));
			stud.setNameOfStud(request.getParameter("studentName"));
			stud.setGender(Integer.parseInt(request.getParameter("gender")));
			stud.setUdidCardNo(request.getParameter("udid"));
			stud.setTypeOfDisability(request.getParameter("disablityType"));
			stud.setPercntOfDisability(Integer.parseInt(request.getParameter("disablity")));
			stud.setYearOfEnrollement(request.getParameter("enrolledYear"));
			stud.setProgramId(Integer.parseInt(request.getParameter("prog_name")));
			stud.setProgTypeId(Integer.parseInt(request.getParameter("prog_type")));
			stud.setDelStatus(1);
			stud.setIsActive(1);
			stud.setAcadYearId(yId);
			stud.setMakerUserId(userId);
			stud.setMakerEnterDatetime(curDateTime);
			stud.setInstId(instituteId);
			stud.setExInt1(0);
			stud.setExInt2(0);
			stud.setExVar1("NA");
			stud.setExVar2("NA");
			System.out.println("stud=" + stud.toString());

			DifrentlyAbledStud saveValAddCourse = rest.postForObject(Constants.url + "/saveDifferentlyDisabledStudent", stud,
					DifrentlyAbledStud.class);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return "redirect:/showDifferentlyAbledStudent";

	}
	

	@RequestMapping(value = "/editDisableStudInfo/{studId}", method = RequestMethod.GET)
	public ModelAndView editDisableStudInfo(@PathVariable("studId") int studId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("ProgramDetails/addDifAbldStud");		
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("editDisableStudInfo/{studId}", "showDifferentlyAbledStudent", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				MultiValueMap<String, Object> map = null;
				
				map = new LinkedMultiValueMap<>();
				map.add("studId", studId);
				DifrentlyAbledStud difDisStud = rest.postForObject(Constants.url + "/getDifferntDisabelStudentById", map,
						DifrentlyAbledStud.class);
				model.addObject("difDisStud", difDisStud);

				ProgramType[] progTypes = rest.getForObject(Constants.url + "getAllProgramType",
						ProgramType[].class);
				List<ProgramType> progTypeList = new ArrayList<>(Arrays.asList(progTypes));
				System.err.println("progTypeList " + progTypeList.toString());

				model.addObject("progTypeList", progTypeList);
				
				model.addObject("title", "Edit Differently Abled Students (Divyangjan)");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/deleteDisableStudInfo/{studId}", method = RequestMethod.GET)
	public String deleteDisableStudInfo(@PathVariable("studId") int studId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			ModelAndView model = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("/deleteDisableStudInfo/{studId}",
					"showDifferentlyAbledStudent", "0", "0", "0", "1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("studId", studId);

				DifrentlyAbledStud delStudId = rest.postForObject(Constants.url + "/deleteDifDisStudById", map,
						DifrentlyAbledStud.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:/showDifferentlyAbledStudent";
	}
	
}
