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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.master.model.prodetail.GetAlumni;
import com.ats.rusasoft.model.Hod;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.GetInstTrainTeachDetail;
import com.ats.rusasoft.model.instprofile.InstituteTraining;

@Controller
@Scope("session")
public class InstProfTeachTrainContr {
	RestTemplate rest = new RestTemplate();

	@RequestMapping(value = "/showProfDevelopment", method = RequestMethod.GET)
	public ModelAndView showProfDevelopment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showProfDevelopment", "showProfDevelopment", "1", "0", "0",
					"0", newModuleList);

			if (viewAccess.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/profDevelpment");
				model.addObject("title", "Training Teaching List");

				Info addAccess = AccessControll.checkAccess("showProfDevelopment", "showProfDevelopment", "0", "1", "0",
						"0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showProfDevelopment", "showProfDevelopment", "0", "0",
						"1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showProfDevelopment", "showProfDevelopment", "0", "0",
						"0", "1", newModuleList);

				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("instId", userObj.getGetData().getUserInstituteId());
				map.add("yearId", session.getAttribute("acYearId"));
				map.add("trainningType", 1);

				GetInstTrainTeachDetail[] resArray = rest.postForObject(Constants.url + "getInstTrainTeachDetailList",
						map, GetInstTrainTeachDetail[].class);
				List<GetInstTrainTeachDetail> instTrainList = new ArrayList<>(Arrays.asList(resArray));

				System.err.println("instTrainList " + instTrainList.toString());

				model.addObject("instTrainList", instTrainList);
				model.addObject("trainnig_type", 1);


			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/showAddProfDevelopment", method = RequestMethod.GET)
	public ModelAndView showAddProfDevelopment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_prof_dev");
		try {

			model.addObject("title", "Add Training Teaching ");

			model.addObject("trainnig_type", 1);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// insertTeachTraing
	@RequestMapping(value = "/insertTeachTraing", method = RequestMethod.POST)
	public String insertTeachTraing(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertTeachTraing");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			int trainigType = Integer.parseInt(request.getParameter("trainnig_type"));
			System.err.println("tra type received " +trainigType);
			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info addAccess = AccessControll.checkAccess("insertTeachTraing", "showProfDevelopment", "0", "1", "0", "0",
					newModuleList);
			if (addAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {

				int trainingId = 0;

				try {
					trainingId = Integer.parseInt(request.getParameter("training_id"));
				} catch (Exception e) {
					trainingId = 0;
				}

				InstituteTraining instTrain = new InstituteTraining();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				instTrain.setTrainingFromdt(DateConvertor.convertToYMD(request.getParameter("fromDate")));

				instTrain.setTrainingId(trainingId);
				instTrain.setTrainingPcount(Integer.parseInt(request.getParameter("no_of_participant")));
				instTrain.setTrainingTitle(request.getParameter("dev_Prog_title"));

				instTrain.setTrainingTodt(DateConvertor.convertToYMD(request.getParameter("toDate")));
				instTrain.setTrainingType(trainigType);

				int yearId = (int) session.getAttribute("acYearId");

				instTrain.setYearId(yearId);
				instTrain.setInstituteId(userObj.getGetData().getUserInstituteId());
				
				if(instTrain.getTrainingPcount()==0) {
					instTrain.setDelStatus(0);
				}else {
				instTrain.setDelStatus(1);
				}
				
				instTrain.setIsActive(1);
				instTrain.setExInt1(1);
				instTrain.setExInt2(2);
				instTrain.setExVar1("NA");
				instTrain.setExVar2("NA");

				instTrain.setMakerDatetime(curDateTime);
				instTrain.setMakerUserId(userObj.getUserId());

				InstituteTraining instTrainning = rest.postForObject(Constants.url + "saveInstituteTraining", instTrain,
						InstituteTraining.class);

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (trainigType == 1) { // for pro
					if (isView == 1)
						redirect = "redirect:/showProfDevelopment";
					else
						redirect = "redirect:/showAddProfDevelopment";
				} else { // for academic
					if (isView == 1)
						redirect = "redirect:/showAdminDevelopment";
					else
						redirect = "redirect:/showAddAdminDevelopment";
				}

			}
		} catch (Exception e) {
			System.err.println("Exce in save insertTeachTraing  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;

	}

	@RequestMapping(value = "/showAdminDevelopment", method = RequestMethod.GET)
	public ModelAndView showAdminDevelopment(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("instituteInfo/IQAC/administrativeDevlop");
		try {

			HttpSession session = request.getSession();

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showAdminDevelopment", "showAdminDevelopment", "1", "0", "0",
					"0", newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("instituteInfo/IQAC/profDevelpment");

				// model = new ModelAndView("instituteInfo/IQAC/administrativeDevlop");
				model.addObject("title", "Training Non-Teaching List");

				Info addAccess = AccessControll.checkAccess("showAdminDevelopment", "showAdminDevelopment", "0", "1",
						"0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showAdminDevelopment", "showAdminDevelopment", "0", "0",
						"1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showAdminDevelopment", "showAdminDevelopment", "0", "0",
						"0", "1", newModuleList);

				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("instId", userObj.getGetData().getUserInstituteId());
				map.add("yearId", session.getAttribute("acYearId"));
				map.add("trainningType", 2);

				GetInstTrainTeachDetail[] resArray = rest.postForObject(Constants.url + "getInstTrainTeachDetailList",
						map, GetInstTrainTeachDetail[].class);
				List<GetInstTrainTeachDetail> instTrainList = new ArrayList<>(Arrays.asList(resArray));

				System.err.println("instTrainList " + instTrainList.toString());

				model.addObject("instTrainList", instTrainList);
				model.addObject("trainnig_type", 2);


			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddAdminDevelopment", method = RequestMethod.GET)
	public ModelAndView showAddAdminDevelopment(HttpServletRequest request, HttpServletResponse response) {
		// hfg

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_prof_dev");
		// ModelAndView model = new
		// ModelAndView("instituteInfo/IQAC/add_administrativeDevlop");
		try {

			model.addObject("title", " Add Training Non-Teaching");
			model.addObject("trainnig_type", 2);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;

	}
	
	//showEditInstTraining
	
	@RequestMapping(value = "/showEditInstTraining", method = RequestMethod.POST)
	public ModelAndView showEditInstTraining(HttpServletRequest request, HttpServletResponse response) {
		// hfg

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/add_prof_dev");
		
		try {

			int trainnig_type=Integer.parseInt(request.getParameter("trainnig_type"));
			int trainingId=Integer.parseInt(request.getParameter("training_id"));
			
			if(trainnig_type==2) {
			model.addObject("title", "Edit Training Non-Teaching");
			}else {
				model.addObject("title", "Edit Training Teaching");
			}
			model.addObject("trainnig_type", trainnig_type);
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("trainingId", trainingId);

			GetInstTrainTeachDetail getTrainiForEdit = rest.postForObject(Constants.url + "getInstTrainTeachDetailById",
					map, GetInstTrainTeachDetail.class);
			
			model.addObject("trainning", getTrainiForEdit);
			

		} catch (Exception e) {
			System.err.println("Exce in showing /showEditInstTraining " +e.getMessage());
			e.printStackTrace();
		}

		return model;

	}
	
	

}
