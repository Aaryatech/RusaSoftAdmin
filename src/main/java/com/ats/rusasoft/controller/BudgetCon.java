package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.commons.Names;
import com.ats.rusasoft.commons.SessionKeyGen;
import com.ats.rusasoft.faculty.model.Journal;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.budget.AcademicBudget;
import com.ats.rusasoft.model.budget.FinancialYear;
import com.ats.rusasoft.model.budget.GetAcademicBudget;
import com.ats.rusasoft.model.budget.GetInfraStructureBudget;
import com.ats.rusasoft.model.budget.GetPhysicalFacilityBudget;
import com.ats.rusasoft.model.budget.InfraStructureBudget;
import com.ats.rusasoft.model.budget.LibraryBookBudget;
import com.ats.rusasoft.model.budget.LibraryBudget;
import com.ats.rusasoft.model.budget.PhysicalFacilityBudget;
import com.ats.rusasoft.model.budget.WasteMngtBudget;

@Controller
@Scope("session")
public class BudgetCon {
	RestTemplate rest = new RestTemplate();
 
	MultiValueMap<String, Object> map = null;// new LinkedMultiValueMap<>();

	@RequestMapping(value = "/budgetPhysicalFacility", method = RequestMethod.GET)
	public ModelAndView budgetPhysicalFacility(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;// new ModelAndView("budgetForm/infra_budget_facility_list");
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("budgetPhysicalFacility", "budgetPhysicalFacility", "1", "0",
					"0", "0", newModuleList);

			// viewAccess.setError(false);
			if (viewAccess.isError() == false) {
				model = new ModelAndView("budgetForm/budget_phy_facility_list");

				model.addObject("title", Names.budget_physical_list);
				model.addObject("budRupees", Names.Rupees);

				Info addAccess = AccessControll.checkAccess("budgetPhysicalFacility", "budgetPhysicalFacility", "0",
						"1", "0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("budgetPhysicalFacility", "budgetPhysicalFacility", "0",
						"0", "1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("budgetPhysicalFacility", "budgetPhysicalFacility", "0",
						"0", "0", "1", newModuleList);

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

	@RequestMapping(value = "/budgetAddPhysicalFacility", method = RequestMethod.GET)
	public ModelAndView budgetAddPhysicalFacility(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model=null;
		try {

			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			aceess = AccessControll.checkAccess("budgetAddInfrastructureFacility", "budgetInfrastructureFacility", "0",
					"1", "0", "0", newModuleList);
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {

				 model = new ModelAndView("budgetForm/budget_phy_facility_add");
				model.addObject("title", Names.budget_physical_add);
				model.addObject("budRupees", Names.Rupees);

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);

				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("curFinYear", curFinYear.getFinYearId());
				map.add("instituteId", (int) userObj.getGetData().getUserInstituteId());

				PhysicalFacilityBudget budget = rest.postForObject(
						Constants.url + "/getphysicalFacilityBudgetByFinYearId", map, PhysicalFacilityBudget.class);

				model.addObject("editBudget", budget);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertPhyFacilityBudget", method = RequestMethod.POST)
	public String insertPhyFacilityBudget(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertPhyFacilityBudget");

		String redirect = null;
		try {
			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				RestTemplate restTemplate = new RestTemplate();

				map = new LinkedMultiValueMap<String, Object>();

				int physicalFacilityBudgetId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
				try {
					physicalFacilityBudgetId = Integer.parseInt(request.getParameter("physicalFacilityBudgetId"));
				} catch (Exception e) {
					physicalFacilityBudgetId = 0;
				}

				System.err.println("physicalFacilityBudgetId id  " + physicalFacilityBudgetId);

				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

				Info aceess = null;

				if (physicalFacilityBudgetId == 0) {

					aceess = AccessControll.checkAccess("insertPhyFacilityBudget", "budgetPhysicalFacility", "0", "1",
							"0", "0", newModuleList);
				} else {

					aceess = AccessControll.checkAccess("insertPhyFacilityBudget", "budgetPhysicalFacility", "0", "0",
							"1", "0", newModuleList);

				}

				if (aceess.isError() == true) {
					redirect = "redirect:/accessDenied";
				} else {
					LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

					PhysicalFacilityBudget budget = new PhysicalFacilityBudget();

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					budget.setAcYearId((int) session.getAttribute("acYearId"));
					budget.setAddBy(userObj.getUserId());
					budget.setInstituteId(userObj.getGetData().getUserInstituteId());

					budget.setAddDatetime(curDateTime);

					budget.setPhysicalFacilityBudgetId(physicalFacilityBudgetId);

					budget.setBudgetAllocated(Integer.parseInt(request.getParameter("budget_allocated")));
					budget.setBudgetUtilized(Integer.parseInt(request.getParameter("budget_utilized")));
					budget.setFinYearId(Integer.parseInt(request.getParameter("fin_year_id")));
					budget.setPhysicalFacilityBudgetTitle(
							XssEscapeUtils.jsoupParse(request.getParameter("infra_budget_title")));

					int exInt1 = 0;
					budget.setExInt1(Integer.parseInt(request.getParameter("ttl_exp")));
					budget.setExInt2(exInt1);

					budget.setExVar1(request.getParameter("funding_from"));
					budget.setExVar2(request.getParameter("otherSource"));

					budget.setIsActive(1);
					budget.setDelStatus(1);

					PhysicalFacilityBudget budgetRes = restTemplate.postForObject(
							Constants.url + "savePhysicalFacilityBudget", budget, PhysicalFacilityBudget.class);

					int isView = Integer.parseInt(request.getParameter("is_view"));
					if (isView == 1)
						redirect = "redirect:/budgetPhysicalFacility";
					else
						redirect = "redirect:/budgetAddPhysicalFacility";
				}
			} else {
				System.err.println("in else");
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			System.err.println("Exce in save budgetRes  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	@RequestMapping(value = "/editPhysicalBudget/{physicalFacilityBudgetId}", method = RequestMethod.GET)
	public ModelAndView editPhysicalBudget(@PathVariable("physicalFacilityBudgetId") int physicalFacilityBudgetId,
			HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("budgetAddPhysicalFacility", "budgetPhysicalFacility", "0", "0", "1",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("budgetForm/budget_phy_facility_add");
				model.addObject("title", Names.budget_physical_edit);

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("physicalFacilityBudgetId", physicalFacilityBudgetId);

				PhysicalFacilityBudget editBudget = rest.postForObject(Constants.url + "/getphysicalFacilityBudgetById",
						map, PhysicalFacilityBudget.class);
				//System.out.println("journalId:" + physicalFacilityBudgetId);

				model.addObject("editBudget", editBudget);

			}
		} catch (Exception e) {

			System.err.println("exception In editBudget at Budget Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deletePhyBudget/{physicalFacilityBudgetId}/{token}", method = RequestMethod.GET)
	public String deletePhyBudget(@PathVariable("physicalFacilityBudgetId") int physicalFacilityBudgetId,
			@PathVariable("token") String token, HttpServletRequest request) {
		String value = null;
		try {

			HttpSession session = request.getSession();
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {
				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
				Info view = AccessControll.checkAccess("budgetAddPhysicalFacility", "budgetPhysicalFacility", "0", "0",
						"0", "1", newModuleList);
				if (view.isError() == true) {

					value = "redirect:/accessDenied";

				} else {
					Info inf = new Info();

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("phyBudgetIdList", physicalFacilityBudgetId);
					Info miqc = rest.postForObject(Constants.url + "/deletePhysicalFacilityBudget", map, Info.class);
					value = "redirect:/budgetPhysicalFacility";
				}
			} else {
				value = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			e.getStackTrace();
		}

		return value;

	}

	@RequestMapping(value = "/budgetOnAcadamicSupportFacilities", method = RequestMethod.GET)
	public ModelAndView budgetOnAcadamicSupportFacilities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("budgetOnAcadamicSupportFacilities",
					"budgetOnAcadamicSupportFacilities", "1", "0", "0", "0", newModuleList);

			// viewAccess.setError(false);
			if (viewAccess.isError() == false) {
				model = new ModelAndView("budgetForm/budget_acadamic_facilities_list");

				model.addObject("title", Names.budget_academic_facilities_list);

				Info addAccess = AccessControll.checkAccess("budgetOnAcadamicSupportFacilities",
						"budgetOnAcadamicSupportFacilities", "0", "1", "0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("budgetOnAcadamicSupportFacilities",
						"budgetOnAcadamicSupportFacilities", "0", "0", "1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("budgetOnAcadamicSupportFacilities",
						"budgetOnAcadamicSupportFacilities", "0", "0", "0", "1", newModuleList);

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

				GetAcademicBudget[] resArray = rest.postForObject(Constants.url + "/getAcademicBudgetListByAcYearId",
						map, GetAcademicBudget[].class);
				List<GetAcademicBudget> budgetList = new ArrayList<>(Arrays.asList(resArray));

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

	@RequestMapping(value = "/budgetAddOnAcadamicSupportFacilities", method = RequestMethod.GET)
	public ModelAndView budgetAddOnAcadamicSupportFacilities(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("budgetForm/budget_acadamic_facilities_add");
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

				model.addObject("title", Names.budget_academic_facilities_add);

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("curFinYear", curFinYear.getFinYearId());
				map.add("instituteId", (int) userObj.getGetData().getUserInstituteId());

				AcademicBudget budget = rest.postForObject(Constants.url + "/getAcademicBudgetByFinYearId", map,
						AcademicBudget.class);

				model.addObject("editBudget", budget);
				model.addObject("budRupees", Names.Rupees);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertAcademicBudget", method = RequestMethod.POST)
	public String insertAcademicBudget(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertAcademicBudget");

		String redirect = null;
		try {

			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				RestTemplate restTemplate = new RestTemplate();

				map = new LinkedMultiValueMap<String, Object>();

				int academicBudgetId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
				try {
					academicBudgetId = Integer.parseInt(request.getParameter("academicBudgetId"));
				} catch (Exception e) {
					academicBudgetId = 0;
				}

				System.err.println("academicBudgetId id  " + academicBudgetId);
				
				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

				Info aceess = null;

				if (academicBudgetId == 0) {

					aceess = AccessControll.checkAccess("insertAcademicBudget", "budgetOnAcadamicSupportFacilities",
							"0", "1", "0", "0", newModuleList);
				} else {

					aceess = AccessControll.checkAccess("insertAcademicBudget", "budgetOnAcadamicSupportFacilities",
							"0", "0", "1", "0", newModuleList);

				}

				if (aceess.isError() == true) {
					redirect = "redirect:/accessDenied";
				} else {
					LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

					AcademicBudget budget = new AcademicBudget();

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();
					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					budget.setAcYearId((int) session.getAttribute("acYearId"));
					budget.setAddBy(userObj.getUserId());
					budget.setInstituteId(userObj.getGetData().getUserInstituteId());

					budget.setAddDatetime(curDateTime);

					budget.setAcademicBudgetId(academicBudgetId);

					budget.setBudgetAllocated(Integer.parseInt(request.getParameter("budget_allocated")));
					budget.setBudgetUtilized(Integer.parseInt(request.getParameter("budget_utilized")));
					budget.setFinYearId(Integer.parseInt(request.getParameter("fin_year_id")));
					budget.setAcademicBudgetTitle(
							XssEscapeUtils.jsoupParse(request.getParameter("infra_budget_title")));

					int exInt1 = 0;
					budget.setExInt1(Integer.parseInt(request.getParameter("ttl_exp")));
					budget.setExInt2(exInt1);

					budget.setExVar1(request.getParameter("funding_from"));
					budget.setExVar2(request.getParameter("otherSource"));

					budget.setIsActive(1);
					budget.setDelStatus(1);

					AcademicBudget budgetRes = restTemplate.postForObject(Constants.url + "saveAcademicBudget", budget,
							AcademicBudget.class);

					int isView = Integer.parseInt(request.getParameter("is_view"));
					if (isView == 1)
						redirect = "redirect:/budgetOnAcadamicSupportFacilities";
					else
						redirect = "redirect:/budgetAddOnAcadamicSupportFacilities";
				}
			} else {
				System.err.println("in else");
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			System.err.println("Exce in save budgetRes  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}

	@RequestMapping(value = "/editAcademicBudget/{academicBudgetId}", method = RequestMethod.GET)
	public ModelAndView editAcademicBudget(@PathVariable("academicBudgetId") int academicBudgetId,
			HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("budgetAddOnAcadamicSupportFacilities",
					"budgetOnAcadamicSupportFacilities", "0", "0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("budgetForm/budget_acadamic_facilities_add");
				model.addObject("title", Names.budget_academic_facilities_edit);

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("academicBudgetId", academicBudgetId);

				AcademicBudget editBudget = rest.postForObject(Constants.url + "/getAcademicBudgetById", map,
						AcademicBudget.class);
				//System.out.println("academicBudget LIST:" + editBudget);
				model.addObject("editBudget", editBudget);

			}
		} catch (Exception e) {

			System.err.println("exception In editBudget at Budget Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteAcademicBudget/{academicIdList}/{token}", method = RequestMethod.GET)
	public String deleteAcademicBudget(@PathVariable("academicIdList") int academicIdList,
			@PathVariable("token") String token, HttpServletRequest request) {
		String value = null;
		try {
			HttpSession session = request.getSession();
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
				Info view = AccessControll.checkAccess("budgetAddPhysicalFacility", "budgetPhysicalFacility", "0", "0",
						"0", "1", newModuleList);
				if (view.isError() == true) {

					value = "redirect:/accessDenied";

				} else {
					Info inf = new Info();

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("academicIdList", academicIdList);
					Info miqc = rest.postForObject(Constants.url + "/deleteAcademicBudget", map, Info.class);
					value = "redirect:/budgetOnAcadamicSupportFacilities";
				}

			} else {
				value = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return value;

	}

	@RequestMapping(value = "/getBudgetByFinYearId", method = RequestMethod.GET)
	public @ResponseBody Object getBudgetByFinYearId(HttpServletRequest request, HttpServletResponse response) {

		Object budget = null;
		Integer res = 1;
		map = new LinkedMultiValueMap<>();

		try {

			int tableId = Integer.parseInt(request.getParameter("tableId"));

			int finYearId = Integer.parseInt(request.getParameter("finYearId"));
			if (tableId == 1) {
				// Physical Facility Budget
				HttpSession session = request.getSession();
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("instituteId", (int) userObj.getGetData().getUserInstituteId());
				map.add("curFinYear", finYearId);

				budget = rest.postForObject(Constants.url + "/getphysicalFacilityBudgetByFinYearId", map,
						PhysicalFacilityBudget.class);
				if (budget == null) {
					res = 0;
					return res;
				}
			} else if (tableId == 2) {
				// Library Facility Budget

				HttpSession session = request.getSession();
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				map.add("instituteId", (int) userObj.getGetData().getUserInstituteId());
				map.add("curFinYear", finYearId);

				budget = rest.postForObject(Constants.url + "/getAcademicBudgetByFinYearId", map, AcademicBudget.class);
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

				budget = rest.postForObject(Constants.url + "/getLibBoookBudgetByFinYearId", map,
						LibraryBookBudget.class);
				if (budget == null) {
					res = 0;
					return res;
				}
			}

			// System.err.println("budget"+budget.toString());

		} catch (Exception e) {
			e.printStackTrace();

		}
		return budget;

	}

}
