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
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
public class AlumniController {

	
	@RequestMapping(value = "/showAlumini", method = RequestMethod.GET)
	public ModelAndView showAlumini(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("ProgramDetails/alumini");

			model.addObject("title", "Alumini Association/Contribution");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			//GetAlumni
			
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			map.add("instId", userObj.getGetData().getUserInstituteId());
			
			map.add("yearId", session.getAttribute("acYearId"));
			
		
			GetAlumni[] almArray = restTemplate.postForObject(Constants.url + "getAlumniList",map,  GetAlumni[].class);
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
	
	//insertAlumni
	
	
	@RequestMapping(value = "/insertAlumni", method = RequestMethod.POST)
	public String insertAlumni(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertAlumni");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			int alumniId =0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				alumniId = Integer.parseInt(request.getParameter("alumni_id"));
			}catch (Exception e) {
				alumniId=0;
			}
			
			System.err.println("alumniId id  " + alumniId);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = new Info();// AccessControll.checkAccess("insertAlumni", "showAlumini", "1", "0", "0", "0",
					//newModuleList);
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
					if(request.getParameter("benif_to").equals("7")) {
						alumni.setBenefitTo(request.getParameter("other_benif"));

					}else {
						alumni.setBenefitTo(request.getParameter("benif_to"));

					}
					
					alumni.setContributionType(Integer.parseInt(request.getParameter("contr_type")));
					alumni.setContributionYear(request.getParameter("contr_year"));
					alumni.setMakerDatetime(curDateTime);
					alumni.setPassingYear(request.getParameter("year_of_pass"));
					alumni.setProgramId(1);
					int yearId=(int) session.getAttribute("acYearId");
					alumni.setYearId(yearId);
					
					AlumniDetail editInst = restTemplate.postForObject(Constants.url + "saveAlumni", alumni, AlumniDetail.class);

				} else {

				
					
					map.add("alumniId", alumniId);
					AlumniDetail alumni = restTemplate.postForObject(Constants.url + "getAlumni", map, AlumniDetail.class);
					
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
					//alumni.setInstituteId(userObj.getGetData().getUserInstituteId());// get from Session
					//alumni.setIsActive(1);
					alumni.setMakerUserId(userObj.getUserId());// get from Session
					if(request.getParameter("benif_to").equals("7")) {
						alumni.setBenefitTo(request.getParameter("other_benif"));

					}else {
						alumni.setBenefitTo(request.getParameter("benif_to"));

					}
					
					alumni.setContributionType(Integer.parseInt(request.getParameter("contr_type")));
					alumni.setContributionYear(request.getParameter("contr_year"));
					alumni.setMakerDatetime(curDateTime);
					alumni.setPassingYear(request.getParameter("year_of_pass"));
					//alumni.setProgramId(1);
					int yearId=(int) session.getAttribute("acYearId");
					alumni.setYearId(yearId);
					
					AlumniDetail editInst = restTemplate.postForObject(Constants.url + "saveAlumni", alumni, AlumniDetail.class);


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
			
			int alumniId=Integer.parseInt(request.getParameter("edit_alum_id"));
			
			
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
	
	//deleteAlum
	
	@RequestMapping(value = "/deleteAlum/{alumniId}", method = RequestMethod.GET)
	public String deleteInstitutes(HttpServletRequest request, HttpServletResponse response, @PathVariable int alumniId) {

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
	
	
}
