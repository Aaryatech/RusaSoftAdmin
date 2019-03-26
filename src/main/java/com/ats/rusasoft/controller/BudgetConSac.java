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
import com.ats.rusasoft.commons.Names;
import com.ats.rusasoft.faculty.model.FacultyPersonalDetail;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.budget.FinancialYear;
import com.ats.rusasoft.model.budget.InfraStructureBudget;

@Controller
@Scope("session")
public class BudgetConSac {
	RestTemplate rest = new RestTemplate();
	MultiValueMap<String, Object> map = null;// new LinkedMultiValueMap<>();

	@RequestMapping(value = "/budgetInfrastructureFacility", method = RequestMethod.GET)
	public ModelAndView budgetInfrastructureFacility(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("budgetForm/infra_budget_facility_list");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("budgetInfrastructureFacility", "budgetInfrastructureFacility",
					"1", "0", "0", "0", newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("budgetForm/infra_budget_facility_list");

				model.addObject("title", Names.infra_budget_list);

				Info addAccess = AccessControll.checkAccess("budgetInfrastructureFacility",
						"budgetInfrastructureFacility", "0", "1", "0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("budgetInfrastructureFacility",
						"budgetInfrastructureFacility", "0", "0", "1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("budgetInfrastructureFacility",
						"budgetInfrastructureFacility", "0", "0", "0", "1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

			} else {
				model = new ModelAndView("accessDenied");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// add_infrastructure_facility

	@RequestMapping(value = "/budgetAddInfrastructureFacility", method = RequestMethod.GET)
	public ModelAndView budgetAddInfrastructureFacility(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/infra_budget_facility_add");
		try {

			model.addObject("title", Names.infra_budget_add);

			FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
					FinancialYear[].class);
			List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

			model.addObject("finYearList", finYearList);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// insertInfraBudget

	@RequestMapping(value = "/insertInfraBudget", method = RequestMethod.POST)
	public String insertInfraBudget(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertInfraBudget");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			map = new LinkedMultiValueMap<String, Object>();

			int infraBudgetId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				infraBudgetId = Integer.parseInt(request.getParameter("infraBudgetId"));
			} catch (Exception e) {
				infraBudgetId = 0;
			}

			System.err.println("infraBudgetId id  " + infraBudgetId);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			if (infraBudgetId == 0) {
				
				aceess = AccessControll.checkAccess("insertInfraBudget", "budgetAddInfrastructureFacility", "0",
						"1", "0", "0", newModuleList);
			} else {
				
				aceess = AccessControll.checkAccess("insertInfraBudget", "budgetAddInfrastructureFacility", "0",
						"0", "1", "0", newModuleList);

			}
			//aceess.setError(false);

			if (aceess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				InfraStructureBudget infraBudget = new InfraStructureBudget();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());
				
				infraBudget.setAcYearId((int) session.getAttribute("acYearId"));
				infraBudget.setAddBy(userObj.getUserId());
				infraBudget.setAddDatetime(curDateTime);
				infraBudget.setBudgetAllocated(Integer.parseInt(request.getParameter("budget_allocated")));
				infraBudget.setBudgetUtilized(Integer.parseInt(request.getParameter("budget_utilized")));
				infraBudget.setFinYearId(Integer.parseInt(request.getParameter("fin_year_id")));
				infraBudget.setInfraBudgetId(infraBudgetId);
				infraBudget.setInstituteId(userObj.getGetData().getUserInstituteId());
				
				
				int exInt1 = 0;
				infraBudget.setExInt1(exInt1);
				infraBudget.setExInt2(exInt1);
				String exVar1 = "NA";
				infraBudget.setExVar1(exVar1);
				infraBudget.setExVar2(exVar1);

				infraBudget.setIsActive(1);
				infraBudget.setDelStatus(1);


				FacultyPersonalDetail facPerDetail = restTemplate.postForObject(
						Constants.url + "saveFacultyPersonalDetail", infraBudget, FacultyPersonalDetail.class);

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/showPersonalDetails";
				else
					redirect = "redirect:/addPersonalDetails";
			}

		} catch (Exception e) {
			System.err.println("Exce in save addPersonalDetails  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	@RequestMapping(value = "/budgetOnLibrary", method = RequestMethod.GET)
	public ModelAndView budgetOnLibrary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budget_library_list");
		try {

			model.addObject("title", Names.budget_library_list);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/budgetAddOnLibrary", method = RequestMethod.GET)
	public ModelAndView budgetAddOnLibrary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budget_library_add");
		try {

			model.addObject("title", Names.budget_library_add);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

}
