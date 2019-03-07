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

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.Hod;
import com.ats.rusasoft.model.Quolification;

@Controller
@Scope("session")
public class LibraryController {
	
	RestTemplate rest = new RestTemplate();
	
	@RequestMapping(value = "/libraryBasicInfo", method = RequestMethod.GET)
	public ModelAndView showStudMentor(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/libraryBasicInfo");
		try {

			
			model.addObject("title", "Library Basic Information");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/rareBookInformation", method = RequestMethod.GET)
	public ModelAndView rareBookInformation(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/rareBookInformation");
		try {

			
			model.addObject("title", "Rare Book Information");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/showEShodhSindhu", method = RequestMethod.GET)
	public ModelAndView showEShodhSindhu(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/eShodhSindhu");
		try {

			
			model.addObject("title", "e Shodh Sindhu");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/showEShodhGanga", method = RequestMethod.GET)
	public ModelAndView showEShodhGanga(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/shodhGanga");
		try {

			
			model.addObject("title", "e Shodh Ganga");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	@RequestMapping(value = "/showEJournals", method = RequestMethod.GET)
	public ModelAndView showEJournals(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/eJournals");
		try {

			
			model.addObject("title", "e Journals");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/showEBooks", method = RequestMethod.GET)
	public ModelAndView showEBooks(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/eBooks");
		try {

			
			model.addObject("title", "e Books");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	@RequestMapping(value = "/showDatabase", method = RequestMethod.GET)
	public ModelAndView showDatabase(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/dataBase");
		try {

			
			model.addObject("title", "Database");

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	@RequestMapping(value = "/showRegLib", method = RequestMethod.GET)
	public ModelAndView showRegLib(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/libReg");

			model.addObject("title", "Librarian Registration");
			
			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("type", 2);
			Quolification[] quolArray = restTemplate.postForObject(Constants.url + "getQuolificationList", map, Quolification[].class);
			List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("quolfList " + quolfList.toString());

			model.addObject("quolfList", quolfList);

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	
	// insertHod
		@RequestMapping(value = "/insertLibrarian", method = RequestMethod.POST)
		public String insertLibrarian(HttpServletRequest request, HttpServletResponse response) {
			System.err.println("in insert insertLibrarian");
			ModelAndView model = null;
			try {

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int hodId = Integer.parseInt(request.getParameter("hod_id"));
				System.err.println("hodId id  " + hodId);
				if (hodId == 0) {
					
					Hod hod = new Hod();
					
					String deptName = request.getParameter("dept_name");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());
					
					hod.setContactNo(request.getParameter("hod_mob"));
					hod.setDelStatus(1);
					hod.setDeptId(Integer.parseInt(request.getParameter("hod_dept_id")));
					hod.setEditBy(1);
					hod.setEmail(request.getParameter("hod_email"));
					hod.setExInt1(1);
					hod.setExInt2(2);
					hod.setExVar1("NA");
					hod.setExVar2("NA");
					hod.setHighestQualificationId(Integer.parseInt(request.getParameter("hod_quolf")));
					hod.setHodId(hodId);
					hod.setHodName(request.getParameter("hod_name"));
					hod.setInstituteId(1);
					hod.setIsActive(1);
					hod.setIsEnrollSystem(0);
					hod.setMakerDate(curDateTime);
					hod.setMakerId(1);
					hod.setUpdateDatetime(curDateTime);
					

					Hod editInst = rest.postForObject(Constants.url + "saveHod", hod, Hod.class);

				} else {

					map.add("hodId", hodId);
					// getInstitute
					Hod hod = rest.postForObject(Constants.url + "getHod", map, Hod.class);
					String deptName = request.getParameter("dept_name");
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());
					
					hod.setContactNo(request.getParameter("hod_mob"));
					hod.setDeptId(Integer.parseInt(request.getParameter("hod_dept_id")));
					hod.setEditBy(1);//session
					hod.setEmail(request.getParameter("hod_email"));
				
					hod.setHighestQualificationId(Integer.parseInt(request.getParameter("hod_quolf")));
					hod.setHodName(request.getParameter("hod_name"));
					hod.setInstituteId(1);//from sess
					hod.setUpdateDatetime(curDateTime);

					Hod editInst = rest.postForObject(Constants.url + "saveHod", hod, Hod.class);

				}

			} catch (Exception e) {
				System.err.println("Exce in save dept  " + e.getMessage());
				e.printStackTrace();
			}

			return "redirect:/showDeptList";

		}
		

	

}
