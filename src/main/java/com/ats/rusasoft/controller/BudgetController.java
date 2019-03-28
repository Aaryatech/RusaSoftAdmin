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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.commons.Names;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.LibraryInfo;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.budget.FinancialYear;
import com.ats.rusasoft.model.budget.GetLibraryBookBudget;
import com.ats.rusasoft.model.budget.GetPhysicalFacilityBudget;
import com.ats.rusasoft.model.budget.LibraryBookBudget;
import com.ats.rusasoft.model.budget.LibraryBudget;

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

				GetLibraryBookBudget[] resArray = rest.postForObject(	Constants.url + "/getLibraryBookBudgetListByAcYearId", map, GetLibraryBookBudget[].class);
					
				List<GetLibraryBookBudget> budgetList = new ArrayList<>(Arrays.asList(resArray));

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
				System.err.println("curr year "+curFinYear);
				LibraryBookBudget budget = rest.postForObject(
						Constants.url + "/getLibBoookBudgetByFinYearId", map, LibraryBookBudget.class);

				model.addObject("budget", budget);
				
			//	System.err.println("curr budget "+budget.toString());

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	
	

	@RequestMapping(value = "/showEditLibBookBudget", method = RequestMethod.POST)
	public ModelAndView showEditLibBudget(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model =null;// new ModelAndView("budgetForm/infra_budget_facility_add");
		try {

			HttpSession session = request.getSession();
 
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			aceess = AccessControll.checkAccess("showEditLibBookBudget", "budgetOnLibraryBooks", "0",
					"0", "1", "0", newModuleList);
			// aceess.setError(false);// comment this

			if (aceess.isError() == true) {
				model = new ModelAndView("accessDenied");
			} else {
				model= new ModelAndView("budgetForm/library_book_budget_add");


				model.addObject("title", Names.lib_budget_edit);

				int libBookBudgetId = Integer.parseInt(request.getParameter("libBookId"));

				FinancialYear[] resArray = rest.getForObject(Constants.url + "/getFinancialYearList",
						FinancialYear[].class);
				List<FinancialYear> finYearList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("finYearList", finYearList);

				FinancialYear curFinYear = rest.getForObject(Constants.url + "/getCurrentFinancialYear",
						FinancialYear.class);

				map = new LinkedMultiValueMap<>();

				map.add("libraryBookBudgetId", libBookBudgetId);

				LibraryBookBudget budget = rest.postForObject(
						Constants.url + "/getLibBookBudgetByLibBookBudgetId", map, LibraryBookBudget.class);

				model.addObject("budget", budget);
			

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/deleteLibBookBudget/{libBookBudgetId}", method = RequestMethod.GET)
	public String deleteDepts(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int libBookBudgetId) {

		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("deleteLibBookBudget", "budgetOnLibraryBooks", "0", "0", "0", "1",
					newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (libBookBudgetId == 0) {

					System.err.println("Multiple records delete ");
					String[] bookIds = request.getParameterValues("bookIds");
					System.out.println("id are" + bookIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < bookIds.length; i++) {
						sb = sb.append(bookIds[i] + ",");

					}
					String bookIdList = sb.toString();
					bookIdList = bookIdList.substring(0, bookIdList.length() - 1);

					map.add("libBookBudgetIdList", bookIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("libBookBudgetIdList", libBookBudgetId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteLibBookBudget", map, Info.class);
				redirect = "redirect:/budgetOnLibraryBooks";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteDepts at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect; // "redirect:/showDeptList";

	}
	
	@RequestMapping(value = "/insertLibBookBudget", method = RequestMethod.POST)
	public String insertLibBookBudget(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertPhyFacilityBudget");

		String redirect = null;
		try {

			RestTemplate restTemplate = new RestTemplate();

			map = new LinkedMultiValueMap<String, Object>();

			int budget_id = 0;// Integer.parseInt(request.getParameter("alumni_id"));
			try {
				budget_id = Integer.parseInt(request.getParameter("budget_id"));
				System.err.println("budget_id id  " + budget_id);

			} catch (Exception e) {
				budget_id = 0;
			}

			System.err.println("budget_id id  " + budget_id);

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info aceess = null;

			if (budget_id == 0) {

				aceess = AccessControll.checkAccess("insertLibBookBudget", "budgetOnLibraryBooks", "0", "1", "0",
						"0", newModuleList);
			} else {

				aceess = AccessControll.checkAccess("insertLibBookBudget", "budgetOnLibraryBooks", "0", "0", "1",
						"0", newModuleList);

			}

			if (aceess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				
				int expenditure_on_book_purchase =Integer.parseInt(request.getParameter("expenditure_on_book_purchase"));

				int fin_year_id = Integer.parseInt(request.getParameter("fin_year_id"));

				int expenditure_on_journals_purchase =  Integer.parseInt(request.getParameter("expenditure_on_journals_purchase"));

				//int lib_quolf = Integer.parseInt(request.getParameter("lib_quolf"));

				int expenditure_on_eresources_purchase =  Integer.parseInt(request.getParameter("expenditure_on_eresources_purchase"));
				int expenditure_on_ejournals_purchase =  Integer.parseInt(request.getParameter("expenditure_on_ejournals_purchase"));

				int inst_id = (int) session.getAttribute("instituteId");
				int maker_id = (int) session.getAttribute("userId");
				int acYearId = (int) session.getAttribute("acYearId");
				
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				LibraryBookBudget lib = new LibraryBookBudget();
				lib.setExpenditureOnBookPurchase(expenditure_on_book_purchase);
				lib.setExpenditureOnEjournalsPurchase(expenditure_on_ejournals_purchase);
				lib.setExpenditureOnEresourcesPurchase(expenditure_on_eresources_purchase);
				lib.setExpenditureOnJournalsPurchase(expenditure_on_journals_purchase);
				
				lib.setLibraryBookBudgetId(budget_id);
				lib.setAddBy(maker_id);
				lib.setFinYearId(fin_year_id);
				lib.setAcYearId(acYearId);
			
				lib.setInstituteId(inst_id);
				lib.setDelStatus(1);
				lib.setIsActive(1);
				lib.setExInt1(1);
				lib.setExInt2(1);
				lib.setExVar1("NA");
				lib.setExVar2("NA");

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				lib.setAddDatetime(curDateTime);

				LibraryBookBudget editInst = rest.postForObject(Constants.url + "saveLibBookBudget", lib, LibraryBookBudget.class);

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					redirect = "redirect:/budgetOnLibraryBooks";
				else
					redirect = "redirect:/budgetAddOnLibraryBooks";
			}

		} catch (Exception e) {
			System.err.println("Exce in save budgetRes  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;// "redirect:/showDeptList";

	}


}
