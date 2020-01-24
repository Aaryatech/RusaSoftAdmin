package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
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

import com.ats.rusasoft.XssEscapeUtils;
import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.Names;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.budget.FinancialYear;
import com.ats.rusasoft.model.budget.GetInfraStructureBudget;
import com.ats.rusasoft.model.budget.GetLibraryBudget;
import com.ats.rusasoft.model.budget.GetWasteMngtBudget;
import com.ats.rusasoft.model.budget.InfraStructureBudget;
import com.ats.rusasoft.model.budget.LibraryBudget;
import com.ats.rusasoft.model.budget.WasteMngtBudget;

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
				model.addObject("budRupees", Names.Rupees);
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

				map = new LinkedMultiValueMap<>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("acYearId", (int) session.getAttribute("acYearId"));
				map.add("instId", (int) userObj.getGetData().getUserInstituteId());

				GetInfraStructureBudget[] resArray = rest.postForObject(
						Constants.url + "/getInfraStructureBudgetListByAcYearId", map, GetInfraStructureBudget[].class);
				List<GetInfraStructureBudget> budgetList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("budgetList", budgetList);
				model.addObject("budRupees", Names.Rupees);

			} else {
				model = new ModelAndView("accessDenied");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/getBudgetDataByFinYearId", method = RequestMethod.GET)
	public @ResponseBody Object getBudgetDataByFinYearId(HttpServletRequest request, HttpServletResponse response) {

		Object budget = null;
		Integer res = 1;
		map = new LinkedMultiValueMap<>();

		try {

			int tableId = Integer.parseInt(request.getParameter("tableId"));

			int finYearId = Integer.parseInt(request.getParameter("finYearId"));
			if (tableId == 1) {
				// Infra Budget
				HttpSession session = request.getSession();
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("instId", (int) userObj.getGetData().getUserInstituteId());
				map.add("curFinYear", finYearId);

				budget = rest.postForObject(Constants.url + "/getInfraStructureBudgetByFinYearId", map,
						InfraStructureBudget.class);
				if (budget == null) {
					res = 0;
					return res;
				}
			} else if (tableId == 2) {
				// Library Facility Budget

				HttpSession session = request.getSession();
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("instId", (int) userObj.getGetData().getUserInstituteId());
				map.add("curFinYear", finYearId);

				budget = rest.postForObject(Constants.url + "/getLibBudgetByFinYearId", map, LibraryBudget.class);
				if (budget == null) {
					res = 0;
					return res;
				}
			} else if (tableId == 3) {
				// Waste And Green Mngt Budget

				HttpSession session = request.getSession();
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("instId", (int) userObj.getGetData().getUserInstituteId());
				map.add("curFinYear", finYearId);

				budget = rest.postForObject(Constants.url + "/getWasteMngtBudgetByFinYearId", map,
						WasteMngtBudget.class);
				System.err.println("Data------------"+budget);
				if (budget == null) {
					res = 0;
					return res;
				}
			}

		} catch (Exception e) {
			System.err.println("Exe in getBudgetDataByFinYearId @BudgeConSac  " + e.getMessage());

			e.printStackTrace();

		}
		return budget;

	}

	// add_infrastructure_facility

	@RequestMapping(value = "/budgetAddInfrastructureFacility", method = RequestMethod.GET)
	public ModelAndView budgetAddInfrastructureFacility(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model=null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			aceess = AccessControll.checkAccess("budgetAddInfrastructureFacility", "budgetInfrastructureFacility", "0",
					"1", "0", "0", newModuleList);
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {
				model = new ModelAndView("budgetForm/infra_budget_facility_add");
				model.addObject("title", Names.infra_budget_add);
				model.addObject("budRupees", Names.Rupees);

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);

				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("curFinYear", curFinYear.getFinYearId());
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", (int) userObj.getGetData().getUserInstituteId());

				InfraStructureBudget budget = rest.postForObject(Constants.url + "/getInfraStructureBudgetByFinYearId",
						map, InfraStructureBudget.class);

				model.addObject("budget", budget);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}


	@RequestMapping(value = "/insertInfraBudget", method = RequestMethod.POST)
	public String insertInfraBudget(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertInfraBudget");
		ModelAndView model = null;
		String redirect = null;
		try {

			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				RestTemplate restTemplate = new RestTemplate();

				map = new LinkedMultiValueMap<String, Object>();

				int infraBudgetId = 0;
				try {
					infraBudgetId = Integer.parseInt(request.getParameter("infraBudgetId"));
				} catch (Exception e) {
					infraBudgetId = 0;
				}

				//System.err.println("infraBudgetId id  " + infraBudgetId);

				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

				Info aceess = null;

				if (infraBudgetId == 0) {

					aceess = AccessControll.checkAccess("insertInfraBudget", "budgetInfrastructureFacility", "0", "1",
							"0", "0", newModuleList);
				} else {

					aceess = AccessControll.checkAccess("insertInfraBudget", "budgetInfrastructureFacility", "0", "0",
							"1", "0", newModuleList);

				}
				// aceess.setError(false);// comment this

				if (aceess.isError() == true) {
					redirect = "redirect:/accessDenied";
				} else {
					LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

					InfraStructureBudget infraBudget = new InfraStructureBudget();

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					infraBudget.setAcYearId((int) session.getAttribute("acYearId"));
					infraBudget.setAddBy(userObj.getUserId());
					infraBudget.setInstituteId(userObj.getGetData().getUserInstituteId());

					infraBudget.setAddDatetime(curDateTime);

					infraBudget.setInfraBudgetId(infraBudgetId);

					infraBudget.setBudgetAllocated(
							Integer.parseInt(XssEscapeUtils.jsoupParse(request.getParameter("budget_allocated"))));
					infraBudget.setBudgetUtilized(
							Integer.parseInt(XssEscapeUtils.jsoupParse(request.getParameter("budget_utilized"))));
					infraBudget.setFinYearId(Integer.parseInt(request.getParameter("fin_year_id")));
					infraBudget
							.setInfraBudgetTitle(XssEscapeUtils.jsoupParse(request.getParameter("infra_budget_title")));

					int exInt1 = 0;
					infraBudget.setExInt1(Integer.parseInt(request.getParameter("ttl_expd")));
					infraBudget.setExInt2(exInt1);

					infraBudget.setExVar1(request.getParameter("funding_from")); // Source of Funding
					infraBudget.setExVar2(request.getParameter("otherSource")); // Other Source of Funding

					infraBudget.setIsActive(1);
					infraBudget.setDelStatus(1);

					InfraStructureBudget infraBudgetRes = restTemplate.postForObject(
							Constants.url + "saveInfraStructureBudget", infraBudget, InfraStructureBudget.class);

					int isView = Integer.parseInt(request.getParameter("is_view"));
					if (isView == 1)
						redirect = "redirect:/budgetInfrastructureFacility";
					else
						redirect = "redirect:/budgetAddInfrastructureFacility";
				}
			} else {
				System.err.println("in else");
				redirect = "redirect:/accessDenied";
			}

		} catch (Exception e) {
			System.err.println("Exce in save addPersonalDetails  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;

	}

	// showEditInfraBudget

	@RequestMapping(value = "/showEditInfraBudget", method = RequestMethod.POST)
	public ModelAndView showEditInfraBudget(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/infra_budget_facility_add");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			aceess = AccessControll.checkAccess("budgetAddInfrastructureFacility", "budgetInfrastructureFacility", "0",
					"0", "1", "0", newModuleList);
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {
				model = new ModelAndView("budgetForm/infra_budget_facility_add");
				model.addObject("title", Names.infra_budget_edit);
				model.addObject("budRupees", Names.Rupees);

				int infraBudgetId = Integer.parseInt(request.getParameter("infraBudgetId"));

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);

				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("infraBudgetId", infraBudgetId);

				InfraStructureBudget budget = rest.postForObject(
						Constants.url + "/getInfraStructureBudgetByInfraBudgetId", map, InfraStructureBudget.class);

				model.addObject("budget", budget);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// deleteInfraBudget
	@RequestMapping(value = "/deleteInfraBudget/{infraBudgetId}", method = RequestMethod.GET)
	public String deleteDepts(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int infraBudgetId) {

		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("deleteInfraBudget", "budgetInfrastructureFacility", "0",
					"0", "0", "1", newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (infraBudgetId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("infraBudgetId");
					//System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String infraBudgetIdList = sb.toString();
					infraBudgetIdList = infraBudgetIdList.substring(0, infraBudgetIdList.length() - 1);

					map.add("infraBudgetIdList", infraBudgetIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("infraBudgetIdList", infraBudgetId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteInfraBudget", map, Info.class);
				redirect = "redirect:/budgetInfrastructureFacility";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteDepts at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect; // "redirect:/showDeptList";

	}

	@RequestMapping(value = "/budgetOnLibrary", method = RequestMethod.GET)
	public ModelAndView budgetOnLibrary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("budgetForm/budget_library_list");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("budgetOnLibrary", "budgetOnLibrary", "1", "0", "0", "0",
					newModuleList);

			// viewAccess.setError(false);
			if (viewAccess.isError() == false) {
				model = new ModelAndView("budgetForm/budget_library_list");// new
																			// ModelAndView("budgetForm/infra_budget_facility_list");

				model.addObject("title", Names.budget_library_list);
				model.addObject("budRupees", Names.Rupees);

				Info addAccess = AccessControll.checkAccess("budgetOnLibrary", "budgetOnLibrary", "0", "1", "0", "0",
						newModuleList);

				// addAccess.setError(false);

				Info editAccess = AccessControll.checkAccess("budgetOnLibrary", "budgetOnLibrary", "0", "0", "1", "0",
						newModuleList);
				// editAccess.setError(false);
				Info deleteAccess = AccessControll.checkAccess("budgetOnLibrary", "budgetOnLibrary", "0", "0", "0", "1",
						newModuleList);
				// deleteAccess.setError(false);
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

				GetLibraryBudget[] resArray = rest.postForObject(Constants.url + "/getLibraryBudgetListByAcYearId", map,
						GetLibraryBudget[].class);
				List<GetLibraryBudget> budgetList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("budgetList", budgetList);

			} else {
				model = new ModelAndView("accessDenied");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/budgetAddOnLibrary", method = RequestMethod.GET)
	public ModelAndView budgetAddOnLibrary(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null; // new ModelAndView("budgetForm/budget_library_add");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			aceess = AccessControll.checkAccess("budgetAddOnLibrary", "budgetOnLibrary", "0", "1", "0", "0",
					newModuleList);
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				model = new ModelAndView("budgetForm/budget_library_add");

				model.addObject("title", Names.budget_library_add);
				model.addObject("budRupees", Names.Rupees);

				// model.addObject("title", Names.infra_budget_add);

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);

				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("curFinYear", curFinYear.getFinYearId());
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", (int) userObj.getGetData().getUserInstituteId());

				LibraryBudget budget = rest.postForObject(Constants.url + "/getLibBudgetByFinYearId", map,
						LibraryBudget.class);

				model.addObject("budget", budget);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// insertLibBudget
	@RequestMapping(value = "/insertLibBudget", method = RequestMethod.POST)
	public String insertLibBudget(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertLibBudget");
		ModelAndView model = null;
		String redirect = null;
		try {
			
			HttpSession session = request.getSession();
			String token=request.getParameter("token");
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {

			RestTemplate restTemplate = new RestTemplate();

			map = new LinkedMultiValueMap<String, Object>();

			int libBudgetId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				libBudgetId = Integer.parseInt(request.getParameter("libBudgetId"));
			} catch (Exception e) {
				libBudgetId = 0;
			}

			System.err.println("infraBudgetId id  " + libBudgetId);

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			if (libBudgetId == 0) {

				aceess = AccessControll.checkAccess("insertLibBudget", "budgetOnLibrary", "0", "1", "0", "0",
						newModuleList);
			} else {

				aceess = AccessControll.checkAccess("insertLibBudget", "budgetOnLibrary", "0", "0", "1", "0",
						newModuleList);

			}
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				LibraryBudget libBudget = new LibraryBudget();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				libBudget.setAcYearId((int) session.getAttribute("acYearId"));
				libBudget.setAddBy(userObj.getUserId());
				libBudget.setInstituteId(userObj.getGetData().getUserInstituteId());

				libBudget.setAddDatetime(curDateTime);

				libBudget.setLibBudgetId(libBudgetId);

				libBudget.setBudgetAllocated(Integer.parseInt(request.getParameter("budget_allocated")));
				libBudget.setBudgetUtilized(Integer.parseInt(request.getParameter("budget_utilized")));
				libBudget.setFinYearId(Integer.parseInt(request.getParameter("fin_year_id")));
				libBudget.setLibBudgetTitle(XssEscapeUtils.jsoupParse(request.getParameter("lib_budget_title")));

				int exInt1 = 0;
				libBudget.setExInt1(Integer.parseInt(request.getParameter("ttl_exp")));
				libBudget.setExInt2(exInt1);
				String exVar1 = "NA";
				libBudget.setExVar1(request.getParameter("funding_from"));
				libBudget.setExVar2(request.getParameter("otherSource"));

				libBudget.setIsActive(1);
				libBudget.setDelStatus(1);

				LibraryBudget infraBudgetRes = restTemplate.postForObject(Constants.url + "saveLibraryBudget",
						libBudget, LibraryBudget.class);

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/budgetOnLibrary";
				else
					redirect = "redirect:/budgetAddOnLibrary";
			}
			
			}else {
				System.err.println("in else");
				redirect = "redirect:/accessDenied";
			}

		} catch (Exception e) {
			System.err.println("Exce in save insertLibBudget  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;
	}


	@RequestMapping(value = "/showEditLibBudget", method = RequestMethod.POST)
	public ModelAndView showEditLibBudget(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("budgetForm/infra_budget_facility_add");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			aceess = AccessControll.checkAccess("budgetAddInfrastructureFacility", "budgetOnLibrary", "0", "0", "1",
					"0", newModuleList);
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {
				model = new ModelAndView("budgetForm/budget_library_add");

				model.addObject("title", Names.lib_budget_edit);
				model.addObject("budRupees", Names.Rupees);
				int libBudgetId = Integer.parseInt(request.getParameter("libBudgetId"));

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);

				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("libBudgetId", libBudgetId);

				LibraryBudget budget = rest.postForObject(Constants.url + "/getLibBudgetByLibBudgetId", map,
						LibraryBudget.class);

				model.addObject("budget", budget);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// deleteLibBudget
	// deleteLibBudget/

	@RequestMapping(value = "/deleteLibBudget/{libBudgetId}", method = RequestMethod.GET)
	public String deleteLibBudget(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int libBudgetId) {

		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("deleteLibBudget", "budgetOnLibrary", "0", "0", "0", "1",
					newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (libBudgetId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("libBudgetId");
					// //System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String libBudgetIdList = sb.toString();
					libBudgetIdList = libBudgetIdList.substring(0, libBudgetIdList.length() - 1);

					map.add("libBudgetIdList", libBudgetIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("libBudgetIdList", libBudgetId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteLibBudget", map, Info.class);
				redirect = "redirect:/budgetOnLibrary";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteLibBudget at BudgeConSac Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect; // "redirect:/showDeptList";

	}

	// Wase Mngt And Green Initiatives Budget

	@RequestMapping(value = "/budgetOnGreenInitiativesAndWasteMngmnt", method = RequestMethod.GET)
	public ModelAndView budgetOnGreenInitiativesAndWasteMngmnt(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("budgetForm/waste_management _budget_list");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("budgetOnGreenInitiativesAndWasteMngmnt",
					"budgetOnGreenInitiativesAndWasteMngmnt", "1", "0", "0", "0", newModuleList);

			if (viewAccess.isError() == false) {
				model = new ModelAndView("budgetForm/waste_management _budget_list");

				model.addObject("title", Names.waste_management_budget_list);
				model.addObject("budRupees", Names.Rupees);
				Info addAccess = AccessControll.checkAccess("budgetOnGreenInitiativesAndWasteMngmnt",
						"budgetOnGreenInitiativesAndWasteMngmnt", "0", "1", "0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("budgetOnGreenInitiativesAndWasteMngmnt",
						"budgetOnGreenInitiativesAndWasteMngmnt", "0", "0", "1", "0", newModuleList);
				Info deleteAccess = AccessControll.checkAccess("budgetOnGreenInitiativesAndWasteMngmnt",
						"budgetOnGreenInitiativesAndWasteMngmnt", "0", "0", "0", "1", newModuleList);
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

				GetWasteMngtBudget[] resArray = rest.postForObject(Constants.url + "/getWasteMngtBudgetListByAcYearId",
						map, GetWasteMngtBudget[].class);
				List<GetWasteMngtBudget> budgetList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("budgetList", budgetList);

			} else {
				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// waste_management _budget_add

	@RequestMapping(value = "/budgetAddOnGreenInitiativesAndWasteMngmnt", method = RequestMethod.GET)
	public ModelAndView budgetAddOnGreenInitiativesAndWasteMngmnt(HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("budgetForm/waste_management_budget_add");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			aceess = AccessControll.checkAccess("budgetAddOnGreenInitiativesAndWasteMngmnt",
					"budgetOnGreenInitiativesAndWasteMngmnt", "0", "1", "0", "0", newModuleList);
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				model = new ModelAndView("budgetForm/waste_management_budget_add");
				model.addObject("title", Names.waste_management_budget_add);
				model.addObject("budRupees", Names.Rupees);
				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);

				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("curFinYear", curFinYear.getFinYearId());
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", (int) userObj.getGetData().getUserInstituteId());

				WasteMngtBudget budget = rest.postForObject(Constants.url + "/getWasteMngtBudgetByFinYearId", map,
						WasteMngtBudget.class);

				model.addObject("budget", budget);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	// insertWasteMngtBudget

	@RequestMapping(value = "/insertWasteMngtBudget", method = RequestMethod.POST)
	public String insertWasteMngtBudget(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertWasteMngtBudget");
		ModelAndView model = null;
		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			map = new LinkedMultiValueMap<String, Object>();

			int wasteMngtBudgetId = 0;
			try {
				wasteMngtBudgetId = Integer.parseInt(request.getParameter("wasteMngtBudgetId"));
			} catch (Exception e) {
				wasteMngtBudgetId = 0;
			}

			System.err.println("wasteMngtBudgetId   " + wasteMngtBudgetId);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			if (wasteMngtBudgetId == 0) {

				aceess = AccessControll.checkAccess("insertWasteMngtBudget", "budgetOnGreenInitiativesAndWasteMngmnt",
						"0", "1", "0", "0", newModuleList);
			} else {

				aceess = AccessControll.checkAccess("insertWasteMngtBudget", "budgetOnGreenInitiativesAndWasteMngmnt",
						"0", "0", "1", "0", newModuleList);

			}

			if (aceess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				WasteMngtBudget wasteAndGreenMngtBudget = new WasteMngtBudget();

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();
				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				wasteAndGreenMngtBudget.setAcYearId((int) session.getAttribute("acYearId"));
				wasteAndGreenMngtBudget.setAddBy(userObj.getUserId());
				wasteAndGreenMngtBudget.setInstituteId(userObj.getGetData().getUserInstituteId());

				wasteAndGreenMngtBudget.setAddDatetime(curDateTime);

				wasteAndGreenMngtBudget.setWasteMngtBudgetId(wasteMngtBudgetId);

				wasteAndGreenMngtBudget.setBudgetAllocated(Integer.parseInt(request.getParameter("budget_allocated")));
				wasteAndGreenMngtBudget.setBudgetUtilized(Integer.parseInt(request.getParameter("budget_utilized")));
				wasteAndGreenMngtBudget.setFinYearId(Integer.parseInt(request.getParameter("fin_year_id")));
				wasteAndGreenMngtBudget.setWasteMngtBudgetTitle("");

				int exInt1 = 0;
				wasteAndGreenMngtBudget.setExInt1(Integer.parseInt(request.getParameter("ttl_exp")));
				wasteAndGreenMngtBudget.setExInt2(exInt1);
			
				wasteAndGreenMngtBudget.setExVar1(request.getParameter("funding_from"));
				wasteAndGreenMngtBudget.setExVar2(request.getParameter("otherSource"));

				wasteAndGreenMngtBudget.setIsActive(1);
				wasteAndGreenMngtBudget.setDelStatus(1);

				WasteMngtBudget wasteMngtBudgetRes = restTemplate.postForObject(Constants.url + "saveWasteMngtBudget",
						wasteAndGreenMngtBudget, WasteMngtBudget.class);

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/budgetOnGreenInitiativesAndWasteMngmnt";
				else
					redirect = "redirect:/budgetAddOnGreenInitiativesAndWasteMngmnt";
			}

		} catch (Exception e) {
			System.err.println("Exce in save insertWasteMngtBudget  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	// showEditWasteMngtBudget

	@RequestMapping(value = "/showEditWasteMngtBudget", method = RequestMethod.POST)
	public ModelAndView showEditWasteMngtBudget(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("budgetForm/infra_budget_facility_add");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			aceess = AccessControll.checkAccess("showEditWasteMngtBudget", "budgetOnGreenInitiativesAndWasteMngmnt",
					"0", "0", "1", "0", newModuleList);
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {
				model = new ModelAndView("budgetForm/waste_management_budget_add");
				model.addObject("title", Names.waste_management_budget_edit);
				model.addObject("budRupees", Names.Rupees);
				int wasteMngtBudgetId = Integer.parseInt(request.getParameter("wasteMngtBudgetId"));

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);

				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("wasteMngtBudgetId", wasteMngtBudgetId);

				WasteMngtBudget budget = rest.postForObject(Constants.url + "/getWasteMngtBudgetBywasteMngtBudgetId",
						map, WasteMngtBudget.class);

				model.addObject("budget", budget);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteWasteMngtBudget/{wasteMngtBudgetId}", method = RequestMethod.GET)
	public String deleteWasteMngtBudget(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int wasteMngtBudgetId) {

		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("deleteWasteMngtBudget",
					"budgetOnGreenInitiativesAndWasteMngmnt", "0", "0", "0", "1", newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (wasteMngtBudgetId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("wasteMngtBudgetId");
					// //System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String wasteMngtBudgetIdList = sb.toString();
					wasteMngtBudgetIdList = wasteMngtBudgetIdList.substring(0, wasteMngtBudgetIdList.length() - 1);

					map.add("wasteMngtBudgetIdList", wasteMngtBudgetIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("wasteMngtBudgetIdList", wasteMngtBudgetId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteWasteMngtBudget", map, Info.class);
				redirect = "redirect:/budgetOnGreenInitiativesAndWasteMngmnt";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteWasteMngtBudget at BudgeConSac Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect; // "redirect:/showDeptList";

	}

}
