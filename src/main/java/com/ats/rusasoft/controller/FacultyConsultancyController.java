package com.ats.rusasoft.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.FacultyConsultancy;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")
public class FacultyConsultancyController {

	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/showConsultancyList", method = RequestMethod.GET)
	public ModelAndView showConsultancyList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showConsultancyList", "showConsultancyList", "1", "0", "0", "0",
					newModuleList);
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			System.out.println(view);

			if (view.isError() == false) {

				model = new ModelAndView("FacultyDetails/consultancy");
				model.addObject("title", "Consultancy List");

				Info add = AccessControll.checkAccess("showConsultancyList", "showConsultancyList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showConsultancyList", "showConsultancyList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showConsultancyList", "showConsultancyList", "0", "0", "0",
						"1", newModuleList);

				if (add.isError() == false) {
					model.addObject("isAdd", 1);
				}
				if (edit.isError() == false) {
					model.addObject("isEdit", 1);
				}
				if (delete.isError() == false) {
					model.addObject("isDelete", 1);
				}
				int acYearId = (Integer) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("yearId", acYearId);
				map.add("facultyId", userObj.getUserId());
				FacultyConsultancy[] arry = restTemplate.postForObject(
						Constants.url + "getFacultyConsultancyListByFacultyId", map, FacultyConsultancy[].class);
				List<FacultyConsultancy> list = new ArrayList<FacultyConsultancy>(Arrays.asList(arry));
				model.addObject("list", list);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddConsultancy", method = RequestMethod.GET)
	public ModelAndView showAddConsultancy(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddConsultancy", "showConsultancyList", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				model = new ModelAndView("FacultyDetails/consultancyDetailList");
				model.addObject("title", "Consultancy Details Form");

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/submitFacultyConsultancy", method = RequestMethod.POST)
	public String submitFacultyConsultancy(HttpServletRequest request, HttpServletResponse response) {

		String returnString = new String();

		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddConsultancy", "showConsultancyList", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int acYearId = (Integer) session.getAttribute("acYearId");

				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

				String nature = request.getParameter("nature");
				String sponser = request.getParameter("sponser");
				float amount = Float.parseFloat(request.getParameter("amount"));
				String conPeriod = request.getParameter("conPeriod");
				int projComp = Integer.parseInt(request.getParameter("projComp"));
				int is_view = Integer.parseInt(request.getParameter("is_view"));
				int consId = Integer.parseInt(request.getParameter("consId"));

				FacultyConsultancy facultyConsultancy = new FacultyConsultancy();

				if (consId != 0) {
					facultyConsultancy.setConsId(consId);
				}

				facultyConsultancy.setConsNature(nature);
				facultyConsultancy.setConsSponsor(sponser);
				facultyConsultancy.setConsAmount(amount);
				facultyConsultancy.setConsPeriod(conPeriod);
				facultyConsultancy.setIsConsCompleted(projComp);
				facultyConsultancy.setFacultyId(userObj.getUserId());
				facultyConsultancy.setMakerUserId(userObj.getUserId());
				facultyConsultancy.setMakerdatetime(sf.format(date));
				facultyConsultancy.setDelStatus(1);
				facultyConsultancy.setIsActive(1);
				facultyConsultancy.setYearId(acYearId);

				FacultyConsultancy resp = restTemplate.postForObject(Constants.url + "saveFacultyConsultancy",
						facultyConsultancy, FacultyConsultancy.class);

				if (is_view == 1) {
					returnString = "redirect:/showConsultancyList";
				} else {
					returnString = "redirect:/showAddConsultancy";
				}

			} else {

				returnString = "redirect:/accessDenied";

			}

		} catch (Exception e) {

			returnString = "redirect:/showConsultancyList";
			e.printStackTrace();

		}

		return returnString;

	}

	@RequestMapping(value = "/deleteConsultancy/{consId}", method = RequestMethod.GET)
	public String deleteConsultancy(@PathVariable("consId") int consId, HttpServletRequest request,
			HttpServletResponse response) {

		String returnString = new String();

		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteConsultancy", "showConsultancyList", "0", "0", "0", "1",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("consId", consId);
				Info resp = restTemplate.postForObject(Constants.url + "deleteConsultancy", map, Info.class);

				returnString = "redirect:/showConsultancyList";
			} else {

				returnString = "redirect:/accessDenied";
			}

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return returnString;

	}

}