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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.master.model.prodetail.AlumniDetail;
import com.ats.rusasoft.master.model.prodetail.Cast;
import com.ats.rusasoft.master.model.prodetail.GetAlumni;
import com.ats.rusasoft.master.model.prodetail.GetHigherEduDetail;
import com.ats.rusasoft.master.model.prodetail.GetTrainPlace;
import com.ats.rusasoft.master.model.prodetail.HigherEducDetail;
import com.ats.rusasoft.master.model.prodetail.ProgramType;
import com.ats.rusasoft.master.model.prodetail.TrainPlacement;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class AlumniTrainingController {
	
	@RequestMapping(value = "/blockUser", method = RequestMethod.POST)
	public String blockUser(HttpServletRequest request, HttpServletResponse response) {

		String redirect=null;
		try {

			int userId=Integer.parseInt(request.getParameter("userId"));
			String listMapping=request.getParameter("listMapping");
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info blockAccess = AccessControll.checkAccess("blockUser", listMapping, "0", "0", "0", "1",
					newModuleList);
			
			if(blockAccess.isError()==false) {

				
				map.add("userId",userId);

				Info errMsg = restTemplate.postForObject(Constants.url + "blockUser", map, Info.class);

				redirect="redirect:/"+listMapping;
				
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

			model.addObject("title", "Alumni Contribution Activities Details");

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

				model.addObject("title", "Add Alumni Contribution Activities Details");
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
					if(amount>0) {
					alumni.setExInt1(amount);
					alumni.setExInt2(1);
					}else {
					alumni.setExInt1(amount);
					alumni.setExInt2(0);
					}
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

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("showEditAlum", "showAlumini", "0", "0", "1", "",
					newModuleList);

			if (editAccess.isError() == false) {
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				model = new ModelAndView("ProgramDetails/addAluminiDetails");

				model.addObject("title", "Edit Alumni Contribution Activities Details");

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

				model.addObject("title", "Student Placement Details List");

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
			String packge = "-";
			String suuppAgnc= "NA";
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
					packge = request.getParameter("package_offered");
					if(packge==null) {
						trainPlace.setPakageOfferd(packge);
					}else {
						trainPlace.setPakageOfferd(packge);
					}
					
					trainPlace.setPlacementId(placeId);
					trainPlace.setProgramName(request.getParameter("prog_name"));
					trainPlace.setProgramType(Integer.parseInt(request.getParameter("prog_type")));
					
					suuppAgnc = request.getParameter("sup_agency_name");
					if(suuppAgnc==null) {
						trainPlace.setSupportAgencyName(suuppAgnc);
					}else {
						trainPlace.setSupportAgencyName(suuppAgnc);
					}
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
					
					System.out.println("Placements:"+trainPlace.toString());

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
					packge = request.getParameter("package_offered");
					if(packge==null) {
						trainPlace.setPakageOfferd(packge);
					}else {
						trainPlace.setPakageOfferd(packge);
					}
					trainPlace.setPlacementId(placeId);
					trainPlace.setProgramName(request.getParameter("prog_name"));
					trainPlace.setProgramType(Integer.parseInt(request.getParameter("prog_type")));
					suuppAgnc = request.getParameter("sup_agency_name");
					if(suuppAgnc==null) {
						trainPlace.setSupportAgencyName(suuppAgnc);
					}else {
						trainPlace.setSupportAgencyName(suuppAgnc);
					}

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

			model.addObject("title", "Progression to Higher Education Details List");

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

}
