package com.ats.rusasoft.controller;

import java.text.DateFormat;
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
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.faculty.model.Journal;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.InstituteSupport;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.Disctinctveness;
import com.ats.rusasoft.model.instprofile.GetDisctinctveness;

@Controller
@Scope("session")
public class InstituteDistController {
	RestTemplate rest = new RestTemplate();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Date now = new Date();
	String curDate = dateFormat.format(new Date());
	String dateTime = dateFormat.format(now);

	@RequestMapping(value = "/showProgDistinctive", method = RequestMethod.GET)
	public ModelAndView showProgDistinctive(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showProgDistinctive", "showProgDistinctive", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/instDistinctive");

				model.addObject("title", "Institutional Distnctiveness");
				int instituteId = (int) session.getAttribute("instituteId");
				int yId = (int) session.getAttribute("acYearId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("instId", instituteId);
				map.add("yearId", yId);

				GetDisctinctveness[] distArray = rest.postForObject(Constants.url + "/getAllDistByInstituteId", map,
						GetDisctinctveness[].class);
				List<GetDisctinctveness> distlist = new ArrayList<>(Arrays.asList(distArray));

				model.addObject("distlist", distlist);

				Info edit = AccessControll.checkAccess("showProgDistinctive", "showProgDistinctive", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showProgDistinctive", "showProgDistinctive", "0", "0", "0",
						"1", newModuleList);

				if (add.isError() == false) {
					System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showProgDistinctive at Institute Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddProgDistinctive", method = RequestMethod.GET)
	public ModelAndView showAddProgDistinctive(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info add = AccessControll.checkAccess("showAddProgDistinctive", "showProgDistinctive", "1", "0", "0", "0",
					newModuleList);

			if (add.isError() == false) {

				model = new ModelAndView("instituteInfo/IQAC/add_prog_distinctive");

				model.addObject("title", " Add Institutional Distnctiveness");

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAddProgDistinctive at Institutedist Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertDist", method = RequestMethod.POST)
	public String insertDist(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAddProgDistinctive", "showProgDistinctive", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				System.err.println("Inside insertDist method");

				int distId = 0;
				try {
					distId = Integer.parseInt(request.getParameter("distId"));
				} catch (Exception e) {
					distId = 0;
				}

				String distApplicableFrom = request.getParameter("date");
				String distBeneficiary = request.getParameter("befStake");
				String distName = request.getParameter("title");
				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int yearId = (int) session.getAttribute("acYearId");

				int is_view = Integer.parseInt(request.getParameter("is_view"));

				Disctinctveness dist = new Disctinctveness();
				dist.setDelStatus(1);
				dist.setDistApplicableFrom(DateConvertor.convertToYMD(distApplicableFrom));
				dist.setDistBeneficiary(distBeneficiary);
				dist.setDistId(distId);

				dist.setDistName(distName);
				dist.setExInt1(1);
				dist.setExInt2(1);
				dist.setExVar1("NA");
				dist.setExVar2("NA");
				dist.setInstituteId(instituteId);
				dist.setIsActive(1);
				dist.setMakerDatetime(dateTime);
				dist.setMakerUserId(userId);
				dist.setYearId(yearId);

				Disctinctveness distInsertRes = rest.postForObject(Constants.url + "saveDist", dist,
						Disctinctveness.class);

				System.err.println("distInsertRes " + distInsertRes.toString());

				if (is_view == 1) {
					returnString = "redirect:/showProgDistinctive";
				} else {
					returnString = "redirect:/showAddProgDistinctive";
				}
			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in distInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/editDist/{distId}", method = RequestMethod.GET)
	public ModelAndView editDist(@PathVariable("distId") int distId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("showAddProgDistinctive", "showProgDistinctive", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_prog_distinctive");

				model.addObject("title", " Add Institutional Distnctiveness");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("distId", distId);

				Disctinctveness editDist = rest.postForObject(Constants.url + "/getDistByDistId", map,
						Disctinctveness.class);
				System.out.println("distId:" + distId);

				model.addObject("editDist", editDist);

			}
		} catch (Exception e) {

			System.err.println("exception In editDist at InstrituteDist Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteDist/{distId}", method = RequestMethod.GET)
	public String deleteDist(@PathVariable("distId") int distId, HttpServletRequest request) {
		String value = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		Info view = AccessControll.checkAccess("showAddProgDistinctive", "showProgDistinctive", "0", "0", "0", "1",
				newModuleList);
		if (view.isError() == true) {

			value = "redirect:/accessDenied";

		} else {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
			map.add("distIdList", distId);
			Info info = rest.postForObject(Constants.url + "/deleteDists", map, Info.class);
			value = "redirect:/showProgDistinctive";
		}
		return value;

	}

}
