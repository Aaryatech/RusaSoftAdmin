package com.ats.rusasoft.controller;

import java.util.ArrayList;
import java.util.Arrays;
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
import com.ats.rusasoft.commons.Names;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.budget.FinancialYear;
import com.ats.rusasoft.model.budget.GetPhysicalFacilityBudget;
import com.ats.rusasoft.model.budget.PhysicalFacilityBudget;

@Controller
@Scope("session")
public class BudgetController {
	RestTemplate rest = new RestTemplate();

	MultiValueMap<String, Object> map = null;// new LinkedMultiValueMap<>();

	@RequestMapping(value = "/budgetOnLibraryBooks", method = RequestMethod.GET)
	public ModelAndView budgetOnLibraryBooks(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("budgetForm/infra_budget_facility_list");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("budgetOnLibraryBooks", "budgetOnLibraryBooks", "1", "0", "0",
					"0", newModuleList);

			// viewAccess.setError(false);
			if (viewAccess.isError() == false) {
				model = new ModelAndView("budgetForm/library_book_budget_list");

				model.addObject("title", Names.library_book_budget_list);

				Info addAccess = AccessControll.checkAccess("budgetOnLibraryBooks", "budgetOnLibraryBooks", "0", "1",
						"0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("budgetOnLibraryBooks", "budgetOnLibraryBooks", "0", "0",
						"1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("budgetOnLibraryBooks", "budgetOnLibraryBooks", "0", "0",
						"0", "1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				map = new LinkedMultiValueMap<>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("acYearId", (int) session.getAttribute("acYearId"));
				map.add("instId", (int) userObj.getGetData().getUserInstituteId());

				GetPhysicalFacilityBudget[] resArray = rest.postForObject(
						Constants.url + "/getPhysicalBudgetListByAcYearId", map, GetPhysicalFacilityBudget[].class);
				List<GetPhysicalFacilityBudget> budgetList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("budgetList", budgetList);

			} else {
				model = new ModelAndView("accessDenied");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/budgetAddOnLibraryBooks", method = RequestMethod.GET)
	public ModelAndView budgetAddOnLibraryBooks(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/library_book_budget_add");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			aceess = AccessControll.checkAccess("budgetAddOnLibraryBooks", "budgetOnLibraryBooks", "0", "1", "0", "0",
					newModuleList);
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				model.addObject("title", Names.library_book_budget_add);

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);

				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("curFinYear", curFinYear.getFinYearId());

				PhysicalFacilityBudget budget = rest.postForObject(
						Constants.url + "/getphysicalFacilityBudgetByFinYearId", map, PhysicalFacilityBudget.class);

				model.addObject("editBudget", budget);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

}
