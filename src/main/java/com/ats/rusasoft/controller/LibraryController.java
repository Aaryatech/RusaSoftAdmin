package com.ats.rusasoft.controller;

import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.GetInstituteInfo;
import com.ats.rusasoft.model.GetInstituteList;
import com.ats.rusasoft.model.GetLib;
import com.ats.rusasoft.model.GetStudentDetail;
import com.ats.rusasoft.model.Hod;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.InstituteInfo;
import com.ats.rusasoft.model.LibBookPurchase;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.LibraryInfo;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.NewDeanList;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.RareBook;
import com.ats.rusasoft.model.Staff;
import com.ats.rusasoft.model.Student;
import com.ats.rusasoft.model.accessright.AssignRoleDetailList;
import com.ats.rusasoft.model.accessright.ModuleJson;

@Controller
@Scope("session")

public class LibraryController {

	RestTemplate rest = new RestTemplate();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());

	DateFormat datfmt = new SimpleDateFormat("yyyy-MM-dd");
	String simpleDate = datfmt.format(cal.getTime());

	String redirect = null;

	MultiValueMap<String, Object> map = null;

	@RequestMapping(value = "/showLibraryBasicInfo", method = RequestMethod.GET)
	public ModelAndView showLibraryBasicInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/libBasicInfoList");
		try {
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showLibraryBasicInfo", "showLibraryBasicInfo", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");

				model.addObject("title", "Library Basic Information");

				map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);

				LibraryInfo[] libInfoArr = rest.postForObject(Constants.url + "/libBasicInfoList", map,
						LibraryInfo[].class);
				List<LibraryInfo> libInfoList = new ArrayList<>(Arrays.asList(libInfoArr));

				model.addObject("libInfoList", libInfoList);
				Info add = AccessControll.checkAccess("showLibraryBasicInfo", "showLibraryBasicInfo", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showLibraryBasicInfo", "showLibraryBasicInfo", "0", "0", "1",
						"0", newModuleList);
				Info delete = AccessControll.checkAccess("showLibraryBasicInfo", "showLibraryBasicInfo", "0", "0", "0",
						"1", newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/libraryBasicInfo", method = RequestMethod.GET)
	public ModelAndView libraryBasicInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/libraryBasicInfo");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("libraryBasicInfo", "showLibraryBasicInfo", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				LibraryInfo libInfo = null;
				int instituteId = (int) session.getAttribute("instituteId");
				int acadYear = (int) session.getAttribute("acYearId");
				int lastYear = 0;
								
				//	LibraryInfo libInfo = new LibraryInfo();
				
				map = new LinkedMultiValueMap<>();
				map.add("instituteId", instituteId);
				map.add("acadYear", acadYear);
								
				 libInfo = rest.postForObject(Constants.url + "/getlibBasicInfo", map, LibraryInfo.class);
				 //System.out.println("libInfo="+libInfo);
				
				
				
				model.addObject("libInfo", libInfo);
				model.addObject("title", "Add Library Basic Information");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertLibInfo", method = RequestMethod.POST)
	public String insertLibBasicInfo(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");
			
			LibraryInfo libInfo = new LibraryInfo();

			
			try {
				libInfo.setLibInfoId(Integer.parseInt(request.getParameter("libInfoId")));
			}catch(Exception e) {
				libInfo.setLibInfoId(0);
				System.err.println(e.getMessage());
				
			}
			try {
				libInfo.setIsEresourceRemotly(Integer.parseInt(request.getParameter("usingremot")));
			}catch(Exception e) {
				libInfo.setIsEresourceRemotly(0);
				System.err.println(e.getMessage());
				
			}
			
			libInfo.setInstituteId(instituteId);
			libInfo.setIsLibAutomated(0);// Integer.parseInt(request.getParameter("isUsingSoft"))
			libInfo.setSoftName(request.getParameter("swName"));
			libInfo.setSoftVersion(request.getParameter("version"));
			libInfo.setUsersOfLms(Integer.parseInt(request.getParameter("userLms")));
			libInfo.setDateOfPurchaseAutomation(request.getParameter("purchaseDate"));
			libInfo.setNoCompLan(Integer.parseInt(request.getParameter("noOfComp")));
			libInfo.setBandwidthForAccessingEresources(request.getParameter("bandwidth"));
			
			libInfo.setAvgTeacher(Float.parseFloat(request.getParameter("avgTeacher")));
			libInfo.setAvgStudent(Float.parseFloat(request.getParameter("avgStud")));

			libInfo.setAddBy(userId);
			libInfo.setAddDatetime(curDateTime);
			libInfo.setEditBy(userId);
			libInfo.setEditDatetime(curDateTime);
			libInfo.setIsPlanning(0);
			libInfo.setDateOfPlanningEstablishment(simpleDate);
			libInfo.setIsAdministration(0);
			libInfo.setDateOfAdministrationEstablishment(simpleDate);
			libInfo.setIsFinanceAcc(0);
			libInfo.setDateOfFinanceEstablishment(simpleDate);
			libInfo.setIsStudentAdmition(0);
			libInfo.setDateOfStudentEstablishment(simpleDate);
			libInfo.setIsExamination(0);
			libInfo.setDateOfExaminationEstablishment(simpleDate);
			libInfo.setAcYearId(acadYear);

			libInfo.setDelStatus(1);
			libInfo.setExInt1(Integer.parseInt(request.getParameter("noOfBooks")));
			libInfo.setExVar1(request.getParameter("noOfVolumns"));

			LibraryInfo saveLib = rest.postForObject(Constants.url + "/insertlibBasicInfo", libInfo, LibraryInfo.class);
			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/showLibraryBasicInfo";
			else
				redirect = "redirect:/libraryBasicInfo";

		} catch (Exception e) {
			e.printStackTrace();

		}

		return redirect;

	}

	@RequestMapping(value = "/editLibInfo/{libInfoId}", method = RequestMethod.GET)
	public ModelAndView editLibInfo(@PathVariable("libInfoId") int libInfoId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/libraryBasicInfo");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editLibInfo/{libInfoId}", "showLibraryBasicInfo", "0", "0", "1",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				map = new LinkedMultiValueMap<>();
				map.add("libInfoId", libInfoId);

				LibraryInfo libInfo = rest.postForObject(Constants.url + "/editlibBasicInfoById", map,
						LibraryInfo.class);
				model.addObject("libInfo", libInfo);

				model.addObject("title", "Edit Library Basic Information");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteLibInfo/{libInfoId}", method = RequestMethod.GET)
	public String deleteLibInfo(@PathVariable("libInfoId") int libInfoId, HttpServletRequest request,
			HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/libraryBasicInfo");
		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteLibInfo/{libInfoId}", "showLibraryBasicInfo", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				map = new LinkedMultiValueMap<>();
				map.add("libInfoId", libInfoId);

				LibraryInfo libInfo = rest.postForObject(Constants.url + "/deletelibBasicInfoById", map,
						LibraryInfo.class);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return "redirect:/showLibraryBasicInfo";

	}

	@RequestMapping(value = "/delSlectedLibInfo/{libId}", method = RequestMethod.GET)
	public String deletSelectedStaff(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int libId) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedLibInfo/{libId}", "showLibraryBasicInfo", "0", "0", "0", "1",
				newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (libId == 0) {

					System.err.println("Multiple records delete ");
					String[] libIds = request.getParameterValues("libId");
					//System.out.println("id are" + libIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < libIds.length; i++) {
						sb = sb.append(libIds[i] + ",");

					}
					String libIdList = sb.toString();
					libIdList = libIdList.substring(0, libIdList.length() - 1);

					map.add("libIdList", libIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("libIdList", libId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelLib", map, Info.class);

				a = "redirect:/showLibraryBasicInfo";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}

	@RequestMapping(value = "/rareBookInformation", method = RequestMethod.GET)
	public ModelAndView rareBookInformation(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/rareBookInformation");

		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("rareBookInformation", "showRareBookInfo", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			}

			else {
				RareBook rareBook = new RareBook();

				model.addObject("title", "Add Library Rare Book - Manuscript Information");
				model.addObject("rareBook", rareBook);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showRareBookInfo", method = RequestMethod.GET)
	public ModelAndView showRareBookInformation(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("library/showRareBookInfo");
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showRareBookInfo", "showRareBookInfo", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				map = new LinkedMultiValueMap<>();

				map.add("instituteId", instituteId);

				RareBook[] rrbook = rest.postForObject(Constants.url + "/showRareBooksInfo", map, RareBook[].class);
				List<RareBook> rareBokList = new ArrayList<>(Arrays.asList(rrbook));

				model.addObject("rareBokList", rareBokList);
				model.addObject("title", "Library Rare Book - Manuscript Information");

				Info add = AccessControll.checkAccess("showRareBookInfo", "showRareBookInfo", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showRareBookInfo", "showRareBookInfo", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showRareBookInfo", "showRareBookInfo", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/insertRareBookInfo", method = RequestMethod.POST)
	public String saveRareBookInformation(HttpServletRequest request, HttpServletResponse response) {

		try {
			HttpSession session = request.getSession();
			int insId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");
			RareBook rareBook = new RareBook();

			rareBook.setRareBookInfoId(Integer.parseInt(request.getParameter("bookId")));
			rareBook.setInstituteId(insId);
			rareBook.setYearId(acadYear); // Academic Year
			rareBook.setUserId(userId);
			rareBook.setRareBookname(request.getParameter("bookName"));
			rareBook.setExVar1(request.getParameter("authorName"));
			rareBook.setPublisher(request.getParameter("publisher"));
			rareBook.setBookCopies(Integer.parseInt(request.getParameter("noOfBook")));
			rareBook.setCostOfBook(Integer.parseInt(request.getParameter("costOfBook")));
			rareBook.setPublicationYear(request.getParameter("year"));
			rareBook.setDelStatus(1);
			rareBook.setExInt1(0);
			
			RareBook saveBook = rest.postForObject(Constants.url + "/saveRareBook", rareBook, RareBook.class);
			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1) {
				redirect = "redirect:/showRareBookInfo";
			} else {
				redirect = "redirect:/rareBookInformation";
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return redirect;

	}

	@RequestMapping(value = "/editRareBookInfo/{bookId}", method = RequestMethod.GET)
	public ModelAndView editRareBookInfo(@PathVariable("bookId") int bookId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("library/rareBookInformation");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editRareBookInfo/{bookId}", "showRareBookInfo", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");
			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("bookId", bookId);

				RareBook editBook = rest.postForObject(Constants.url + "/getRareBookById", map, RareBook.class);
				model.addObject("rareBook", editBook);

				model.addObject("title", "Edit Library Rare Book - Manuscript Information");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return model;

	}

	@RequestMapping(value = "/deleteRareBookInfo/{bookId}", method = RequestMethod.GET)
	public String deleteRareBookInfo(@PathVariable("bookId") int bookId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String a = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("deleteRareBookInfo/{bookId}", "showRareBookInfo", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("bookId", bookId);

				RareBook editBook = rest.postForObject(Constants.url + "/deleteRareBookById", map, RareBook.class);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return "redirect:/showRareBookInfo";

	}

	@RequestMapping(value = "/delSlectedRareBook/{rareBookId}", method = RequestMethod.GET)
	public String delSlectedRareBook(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int rareBookId) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedRareBook/{rareBookId}", "showRareBookInfo", "0", "0", "0",
				"1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (rareBookId == 0) {

					System.err.println("Multiple records delete ");
					String[] rareBookIds = request.getParameterValues("rareBookId");
					//System.out.println("id are" + rareBookIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < rareBookIds.length; i++) {
						sb = sb.append(rareBookIds[i] + ",");

					}
					String rareBookIdList = sb.toString();
					rareBookIdList = rareBookIdList.substring(0, rareBookIdList.length() - 1);

					map.add("rareBookIdList", rareBookIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("rareBookIdList", rareBookId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelRareBooks", map, Info.class);

				a = "redirect:/showRareBookInfo";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}
/*****************************************************************************************************************************/	

	@RequestMapping(value = "/showlibBookPurchased", method = RequestMethod.GET)
	public ModelAndView showlibBookPurchased(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("library/libBookPurchasedList");
			HttpSession session = request.getSession();
			
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showlibBookPurchased", "showlibBookPurchased", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int acadYear = (int) session.getAttribute("acYearId");
				map = new LinkedMultiValueMap<>();

				map.add("instituteId", instituteId);
				map.add("acadYear", acadYear);

				LibBookPurchase[] libbookarr = rest.postForObject(Constants.url + "/showPurchsaeBooksList", map, LibBookPurchase[].class);
				List<LibBookPurchase> libraryBooks = new ArrayList<>(Arrays.asList(libbookarr));

				model.addObject("bookList", libraryBooks);
				model.addObject("title", "Library Purchase Information");

				Info add = AccessControll.checkAccess("showlibBookPurchased", "showlibBookPurchased", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showlibBookPurchased", "showlibBookPurchased", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showlibBookPurchased", "showlibBookPurchased", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}
	
	
	@RequestMapping(value = "/libBookPurchase", method = RequestMethod.GET)
	public ModelAndView libBookPurchase(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("library/libBookPurchase");

		try {
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("libBookPurchase", "showlibBookPurchased", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			}

			else {
				LibBookPurchase libBook = new LibBookPurchase();

				model.addObject("title", "Add Library Purchase Information");
				model.addObject("lib", libBook);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertLibBook", method = RequestMethod.POST)
	public String insertLibBook(HttpServletRequest request, HttpServletResponse response) {
		
		//try {
			int id = 0;
			
			HttpSession session = request.getSession();
			int insId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");
			int acadYear = (int) session.getAttribute("acYearId");
			id = Integer.parseInt(request.getParameter("bookId"));
			LibBookPurchase lib = new LibBookPurchase();
			
			lib.setBookPurchaseId(id);
			lib.setInstituteId(insId);
			lib.setAcademicYrid(acadYear);
			lib.setUserId(userId);
			
			lib.setNoOfBooks(Integer.parseInt(request.getParameter("noOfBookPrchs")));
			lib.setCostOfBooks(Integer.parseInt(request.getParameter("costOfBook")));
			
			lib.setNoOfJournal(Integer.parseInt(request.getParameter("noOfJournal")));
			lib.setCostOfJournal(Integer.parseInt(request.getParameter("costOfJournal")));
			
			lib.setNoOfEjournal(Integer.parseInt(request.getParameter("noOfEJournal")));
			lib.setCostOfEjournal(Integer.parseInt(request.getParameter("costOfEJournal")));
			
			lib.setDelStatus(1);
			lib.setExVar1("NA");
			lib.setExInt1(0);
			//System.out.println(lib.toString());
			
			LibBookPurchase libBook = rest.postForObject(Constants.url+"/newLibBookPurchase", lib, LibBookPurchase.class);
			
		/*}catch(Exception e){
			e.printStackTrace();
			//System.out.println();
		}*/
		return "redirect:/showlibBookPurchased";
	}
	
	
	
	@RequestMapping(value = "/editLibBook/{bookId}", method = RequestMethod.GET)
	public ModelAndView editLibBook(@PathVariable("bookId") int bookId, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView("library/libBookPurchase");
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("editLibBook/{bookId}", "showlibBookPurchased", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");
			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("bookId", bookId);

				LibBookPurchase editBookInfo = rest.postForObject(Constants.url + "/getLibBookById", map, LibBookPurchase.class);
				model.addObject("lib", editBookInfo);

				model.addObject("title", "Edit Library Purchase Information");
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return model;

	}
	
	
	@RequestMapping(value = "/deleteLibBook/{bookId}", method = RequestMethod.GET)
	public String deleteLibBookInfo(@PathVariable("bookId") int bookId, HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String a = null;
			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("deleteLibBook/{bookId}", "showlibBookPurchased", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("bookId", bookId);

				LibBookPurchase editBook = rest.postForObject(Constants.url + "/deleteLibBookById", map, LibBookPurchase.class);
			}
		} catch (Exception e) {

			e.printStackTrace();

		}
		return "redirect:/showlibBookPurchased";

	}
	
	@RequestMapping(value = "/delSlectedPurchasedLibBooks/{libBookId}", method = RequestMethod.GET)
	public String delSlectedPurchasedLibBooks(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int libBookId) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedPurchasedLibBooks/{libBookId}", "showlibBookPurchased", "0", "0", "0",
				"1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (libBookId == 0) {

					System.err.println("Multiple records delete ");
					String[] libBookIds = request.getParameterValues("libBookId");
					//System.out.println("id are" + libBookIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < libBookIds.length; i++) {
						sb = sb.append(libBookIds[i] + ",");

					}
					String libBookIdList = sb.toString();
					libBookIdList = libBookIdList.substring(0, libBookIdList.length() - 1);

					map.add("libBookIdList", libBookIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("libBookIdList", libBookId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelPurchasedBooks", map, Info.class);

				a = "redirect:/showlibBookPurchased";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;

	}
	
/**************************************************************************************************************************************/	
	

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

	////////////////////////// *****************Librarian*************//////////////////

	@RequestMapping(value = "/showLibList", method = RequestMethod.GET)
	public ModelAndView showLibList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showLibList", "showLibList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				//System.out.println(" showLibList Accessable ");

				model = new ModelAndView("master/libList");

				model.addObject("title", " Director Resource Center Registration");

				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", inst_id);

				NewDeanList[] instArray = rest.postForObject(Constants.url + "getNewLibraryList", map,
						NewDeanList[].class);
				List<NewDeanList> libtList = new ArrayList<>(Arrays.asList(instArray));

				//System.out.println("lib list is" + libtList.toString());

				model.addObject("libtList", libtList);
				model.addObject("listMapping", "showLibList");

				Info add = AccessControll.checkAccess("showLibList", "showLibList", "0", "1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showLibList", "showLibList", "0", "0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showLibList", "showLibList", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}

			}

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showRegLib", method = RequestMethod.GET)
	public ModelAndView showRegLib(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, Object> map = null;
				
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		try {
			Info view = AccessControll.checkAccess("showRegLib", "showLibList", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("master/libReg");

				model.addObject("title", "Add  Director Resource Center");
				map = new LinkedMultiValueMap<String, Object>();
				
				map.add("desgList", Constants.facLibrarian);
				Designation[] designArr = restTemplate.postForObject(Constants.url + "/getAllDesignations", map,
						Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();

				map.add("type", 1);
				Quolification[] quolArray = restTemplate.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);
				model.addObject("addEdit", "0");
				Staff editFaculty = new Staff();
				model.addObject("editFaculty", editFaculty);

			}
		} catch (Exception e) {

			System.err.println("exception In showRegLib at Library Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertLibrarian", method = RequestMethod.POST)
	public String insertLibrarian(HttpServletRequest request, HttpServletResponse response) {

		try {

			HttpSession session = request.getSession();

			int instituteId = (int) session.getAttribute("instituteId");

			int userId = (int) session.getAttribute("userId");
			int libId = 0;

			try {
				libId = Integer.parseInt(request.getParameter("lib_id"));
			} catch (Exception e) {
				libId = 0;
			}

			int isLib = 0;

			try {
				isLib = Integer.parseInt(request.getParameter("isLib"));
			} catch (Exception e) {
				isLib = 0;
			}
			String roleNameList = null;

			roleNameList = Constants.Librarian_Role;

			if (isLib == 1) {
				roleNameList = roleNameList + "," + Constants.Librarian_Role;

			}

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("roleNameList", roleNameList);
			AssignRoleDetailList[] roleArray = rest.postForObject(Constants.url + "getRoleIdsByRoleNameList", map,
					AssignRoleDetailList[].class);
			List<AssignRoleDetailList> roleIdsList = new ArrayList<>(Arrays.asList(roleArray));

			String roleIds = new String();
			for (int i = 0; i < roleIdsList.size(); i++) {
				roleIds = roleIdsList.get(i).getRoleId() + "," + roleIds;
			}
			System.err.println("roleIds " + roleIds);

			int designation = 0;

			String libName = request.getParameter("libName");
			//System.out.println("Data:" + libName);
			designation = Integer.parseInt(request.getParameter("designation"));
			String dateOfJoin = request.getParameter("dateOfJoin");
			String contact = request.getParameter("contactNo");
			String email = request.getParameter("email");
			int isState = Integer.parseInt(request.getParameter("is_state_same"));
			String[] deptIds = request.getParameterValues("dept_id");
			StringBuilder sb = new StringBuilder();

			for (int i = 0; i < deptIds.length; i++) {
				sb = sb.append(deptIds[i] + ",");

			}
			String deptIdList = sb.toString();
			deptIdList = deptIdList.substring(0, deptIdList.length() - 1);
			int addEdit = Integer.parseInt(request.getParameter("addEdit"));
			if (addEdit == 0) {
				Staff staff = new Staff();

				staff.setContactNo(contact);
				staff.setCurrentDesignationId(designation);
				staff.setDeptId(deptIdList);
				staff.setEmail(email);
				staff.setFacultyFirstName(libName);
				staff.setFacultyId(libId);
				staff.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
				
				staff.setIsSame(isState);
				if(isState==1) {
				staff.setFacultyMiddelName("21");		//inserted state id
				}else {
					staff.setFacultyMiddelName(request.getParameter("state_id"));		//inserted state id
				}
				
				staff.setHightestQualificationYear(null);
				staff.setIsAccOff(0);
				staff.setIsDean(0);
				staff.setIsFaculty(1);
				staff.setIsHod(0);
				staff.setIsIqac(0);
				staff.setIsLibrarian(1);
				staff.setIsPrincipal(0);

				staff.setIsStudent(0);
				staff.setIsWorking(Integer.parseInt(request.getParameter("isWorking")));
				staff.setJoiningDate(dateOfJoin);
				staff.setLastUpdatedDatetime(curDateTime);
				staff.setMakerEnterDatetime(curDateTime);

				staff.setPassword("");

				staff.setRoleIds(roleIds);
				staff.setTeachingTo(0);
				staff.setType(7);

				staff.setInstituteId(instituteId);
				staff.setJoiningDate(dateOfJoin);
				staff.setContactNo(contact);
				staff.setEmail(email);
				staff.setDelStatus(1);
				staff.setIsActive(1);
				staff.setMakerUserId(userId);
				staff.setMakerEnterDatetime(curDateTime);
				staff.setCheckerUserId(0);
				staff.setCheckerDatetime(curDateTime);
				staff.setLastUpdatedDatetime(curDateTime);

				staff.setExtravarchar1("NA");
				try {
					staff.setRealivingDate(request.getParameter("acc_off_relDate"));

				} catch (Exception e) {
					staff.setRealivingDate(null);
				}
				Staff Library = rest.postForObject(Constants.url + "/addNewStaff", staff, Staff.class);
			} else {

				map = new LinkedMultiValueMap<>();
				map.add("id", libId);

				Staff editHod = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				editHod.setFacultyFirstName(libName);
				editHod.setDeptId(deptIdList);
				editHod.setEmail(email);
				editHod.setFacultyId(libId);
				editHod.setContactNo(contact);
				editHod.setCurrentDesignationId(designation);
				editHod.setHighestQualification(Integer.parseInt(request.getParameter("quolif")));
			
				editHod.setIsSame(isState);
				if(isState==1) {
					editHod.setFacultyMiddelName("21");		//inserted state id
				}else {
					editHod.setFacultyMiddelName(request.getParameter("state_id"));		//inserted state id
				}
				editHod.setJoiningDate(dateOfJoin);
				editHod.setIsLibrarian(1);
				editHod.setType(7);
				editHod.setIsWorking(Integer.parseInt(request.getParameter("isWorking")));
				try {
					editHod.setRealivingDate(request.getParameter("acc_off_relDate"));

				} catch (Exception e) {
					editHod.setRealivingDate(null);
				}

				Staff hod = rest.postForObject(Constants.url + "/addNewStaff", editHod, Staff.class);

			}

			int isView = Integer.parseInt(request.getParameter("is_view"));
			if (isView == 1)
				redirect = "redirect:/showLibList";
			else
				redirect = "redirect:/showRegLib";
		} catch (Exception e) {

			System.err.println("exception In showRegLib at Library Contr" + e.getMessage());
			e.printStackTrace();

		}
		return redirect;

	}

	@RequestMapping(value = "/showEditLibrarian/{facultyId}", method = RequestMethod.GET)
	public ModelAndView showEditLibrarian(@PathVariable("facultyId") int facultyId, HttpServletRequest request) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		MultiValueMap<String, Object> map = null;
		try {
			Info view = AccessControll.checkAccess("showRegLib", "showLibList", "0", "0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/libReg");

				model.addObject("title", "Edit  Director Resource Center");
				
				map = new LinkedMultiValueMap<String, Object>();
				map.add("desgList", Constants.facLibrarian);
				Designation[] designArr = rest.postForObject(Constants.url + "/getAllDesignations", map, Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			
				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = rest.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				model.addObject("deptList", deptList);

				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				Quolification[] quolArray = rest.postForObject(Constants.url + "getQuolificationList", map,
						Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				model.addObject("quolfList", quolfList);

				map = new LinkedMultiValueMap<>();
				map.add("id", facultyId);

				Staff editFaculty = rest.postForObject(Constants.url + "/getStaffById", map, Staff.class);
				//System.out.println("facultyId:" + facultyId);

				model.addObject("editFaculty", editFaculty);
				model.addObject("addEdit", "1");

				List<Integer> deptIdList = Stream.of(editFaculty.getDeptId().split(",")).map(Integer::parseInt)
						.collect(Collectors.toList());

				model.addObject("deptIdList", deptIdList);

			}
		} catch (Exception e) {

			System.err.println("exception In editFaculty at Library Contr" + e.getMessage());

			e.printStackTrace();

		}
		return model;
	}

	@RequestMapping(value = "/deleteLibrarians/{facultyId}", method = RequestMethod.GET)
	public String deleteLibrarians(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int facultyId) {
		String redirect = null;
		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("showRegLib", "showLibList", "0", "0", "0", "1",
					newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (facultyId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("hodIds");
					//System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String hodIdList = sb.toString();
					hodIdList = hodIdList.substring(0, hodIdList.length() - 1);

					map.add("staffIdList", hodIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("staffIdList", facultyId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteStaffSlected", map, Info.class);

				redirect = "redirect:/showLibList";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteLibrarians at Library Contr " + e.getMessage());

			e.printStackTrace();

		}

		return redirect;

	}

	/////////////////////////////// ****Student************///////////////////

	@RequestMapping(value = "/showStudList", method = RequestMethod.GET)
	public ModelAndView showStudList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showStudList", "showStudList", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				//System.out.println(" showStudList Accessable ");

				model = new ModelAndView("master/studList");

				model.addObject("title", "Specific Student Information");

				int inst_id = (int) session.getAttribute("instituteId");
				//System.out.println("Student list inst id::::" + inst_id);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", inst_id);

				GetStudentDetail[] instArray = rest.postForObject(Constants.url + "getAllStudentByInstituteId", map,
						GetStudentDetail[].class);
				List<GetStudentDetail> StudList = new ArrayList<>(Arrays.asList(instArray));

				//System.out.println("Student list is" + StudList.toString());

				model.addObject("StudList", StudList);

				Info add = AccessControll.checkAccess("showStudList", "showStudList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showStudList", "showStudList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showStudList", "showStudList", "0", "0", "0", "1",
						newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}

			}

		} catch (Exception e) {

			System.err.println("exception In showStudList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showRegStud", method = RequestMethod.GET)
	public ModelAndView showRegStud(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showRegStud", "showStudList", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/studReg");

				model.addObject("title", "Add Specific Student Information");

				Student editStudent = new Student();
				model.addObject("editStudent", editStudent);

				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = restTemplate.postForObject(Constants.url + "getAcademicYearListByTypeId",
						map, AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);

				restTemplate = new RestTemplate();
				map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int instId = userObj.getGetData().getUserInstituteId();
				map.add("instId", instId);
				System.err.println("deptList in showRegStud" + instId);

				Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList in showRegStud" + deptList.toString());

				model.addObject("deptList", deptList);

			}
		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertStudent", method = RequestMethod.POST)
	public String insertStudent(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("insertStudent", "showStudList", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {
				System.err.println("in insert insertStudent");

				int inst_id = (int) session.getAttribute("instituteId");
				int maker_id = (int) session.getAttribute("userId");

				Student lib = new Student();
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int student_id = Integer.parseInt(request.getParameter("student_id"));
				//System.out.println("student_id" + "student_id");

				String student_name = request.getParameter("student_name");
				String academic_year = request.getParameter("academic_year");

				int stud_branch = Integer.parseInt(request.getParameter("stud_branch"));

				String id_number = request.getParameter("id_number");

				String stud_contact_no = request.getParameter("stud_contact_no");
				String student_email = request.getParameter("student_email");

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());

				if (student_id == 0) {

					//System.out.println("inst id is" + inst_id);

					lib.setStudentName(student_name);
					lib.setAcadamicYear(academic_year);
					lib.setDeptId(stud_branch);
					lib.setContactNo(stud_contact_no);
					lib.setEmail(student_email);
					lib.setIdNo(id_number);
					lib.setMakerUserId(maker_id);

					lib.setMakerEnterDatetime(curDateTime);

					lib.setInstituteId(inst_id);
					lib.setDelStatus(1);
					lib.setExInt1(1);
					lib.setExInt2(1);
					lib.setExVar1("NA");
					lib.setExVar2("NA");

					Student editInst = rest.postForObject(Constants.url + "saveStudent", lib, Student.class);

				} else {

					//System.out.println("in edit");

					map.add("studId", student_id);
					Student lib1 = rest.postForObject(Constants.url + "getStudentByStudentId", map, Student.class);

					lib1.setStudentName(student_name);
					lib1.setAcadamicYear(academic_year);
					lib1.setDeptId(stud_branch);
					lib1.setContactNo(stud_contact_no);
					lib1.setEmail(student_email);
					lib1.setIdNo(id_number);
					lib1.setMakerUserId(maker_id);

					lib1.setInstituteId(inst_id);

					Student editInst = rest.postForObject(Constants.url + "saveStudent", lib1, Student.class);

				}
				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					a = "redirect:/showStudList";
				else
					a = "redirect:/showRegStud";

			}

		} catch (Exception e) {
			System.err.println("Exce in save lib  " + e.getMessage());
			e.printStackTrace();
		}

		return a;

	}

	@RequestMapping(value = "/showEditStudent", method = RequestMethod.POST)
	public ModelAndView showEditStudent(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showEditStudent", "showStudList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/studReg");

				int student_id = Integer.parseInt(request.getParameter("edit_stud_id"));
				//System.out.println("Student id is" + student_id);

				model.addObject("title", " Edit Specific Student Information");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("studId", student_id);
				Student editStudent = rest.postForObject(Constants.url + "getStudentByStudentId", map, Student.class);
				//System.out.println("librarian is" + editStudent.toString());

				model.addObject("editStudent", editStudent);
				model.addObject("student_id", student_id);

				RestTemplate restTemplate = new RestTemplate();
				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = restTemplate.postForObject(Constants.url + "getAcademicYearListByTypeId",
						map, AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);

				restTemplate = new RestTemplate();
				map = new LinkedMultiValueMap<String, Object>();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);
			}

		} catch (Exception e) {
			System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/deleteStudents/{studId}", method = RequestMethod.GET)
	public String deleteStudents(HttpServletRequest request, HttpServletResponse response, @PathVariable int studId) {

		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteStudents/{studId}", "showStudList", "0", "0", "0", "1",
				newModuleList);
		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (studId == 0) {

					System.err.println("Multiple records delete ");
					String[] studIds = request.getParameterValues("studIds");
					//System.out.println("id are" + studIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < studIds.length; i++) {
						sb = sb.append(studIds[i] + ",");

					}

					String studIdList = sb.toString();
					studIdList = studIdList.substring(0, studIdList.length() - 1);
					//System.out.println("stud id list" + studIdList);

					map.add("studIdList", studIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("studIdList", studId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteStudents", map, Info.class);
				a = "redirect:/showStudList";
			}
		} catch (Exception e) {

			System.err.println(" Exception In deleteStudents at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return a;

	}

//////////////////////////**************************Institute Info*********************************/////////////

	@RequestMapping(value = "/showInstituteInfoList", method = RequestMethod.GET)
	public ModelAndView showInstituteInfoList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showInstituteInfoList", "showInstituteInfoList", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				//System.out.println(" showStudList Accessable ");

				model = new ModelAndView("master/instituteInfo");

				model.addObject("title", "Institute Information");

				int inst_id = (int) session.getAttribute("instituteId");
				int acad_year = (int) session.getAttribute("acYearId");
				//System.out.println("Student list inst id and year::::" + inst_id + " " + acad_year);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", inst_id);
				map.add("acadmicYear", acad_year);

				GetInstituteInfo[] instArray = rest.postForObject(Constants.url + "getAllInstituteInfoByInstituteId",
						map, GetInstituteInfo[].class);
				List<GetInstituteInfo> instInfoList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("acaYearList " + instInfoList.toString());

				model.addObject("instInfoList", instInfoList);

				Info add = AccessControll.checkAccess("showInstituteInfoList", "showInstituteInfoList", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showInstituteInfoList", "showInstituteInfoList", "0", "0", "1",
						"0", newModuleList);
				Info delete = AccessControll.checkAccess("showInstituteInfoList", "showInstituteInfoList", "0", "0",
						"0", "1", newModuleList);

				if (add.isError() == false) {
					//System.out.println(" add   Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					//System.out.println(" edit   Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					//System.out.println(" delete   Accessable ");
					model.addObject("deleteAccess", 0);

				}
			}

		} catch (Exception e) {

			System.err.println("exception In showInstituteList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showFillInstituteInfo", method = RequestMethod.GET)
	public ModelAndView showIqacAfterLogin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showFillInstituteInfo", "showInstituteInfoList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/iqaclogin");

				model.addObject("title", "Add Institute Information");

				InstituteInfo editInst = new InstituteInfo();

				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = restTemplate.postForObject(Constants.url + "getAcademicYearListByTypeId",
						map, AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);
				// model.addObject("editInstInfo", editInst);
			}

		} catch (Exception e) {

			System.err.println("exception In Institute at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showEditInstInfo", method = RequestMethod.POST)
	public ModelAndView showEditInstInfo(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showEditInstInfo", "showInstituteInfoList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("master/iqaclogin");

				int edit_inst_id = Integer.parseInt(request.getParameter("edit_inst_id"));
				//System.out.println("Student id is" + edit_inst_id);

				model.addObject("title", " Edit Institute Information  ");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("infoDetailId", edit_inst_id);
				InstituteInfo editInst = rest.postForObject(Constants.url + "getInstituteInfoByInfoDetailId", map,
						InstituteInfo.class);
				//System.out.println("editInst is" + editInst.toString());

				model.addObject("editInstInfo", editInst);
				model.addObject("student_id", edit_inst_id);

				RestTemplate restTemplate = new RestTemplate();
				map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = restTemplate.postForObject(Constants.url + "getAcademicYearListByTypeId",
						map, AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditInstInfo/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/insertInstituteInfo", method = RequestMethod.POST)
	public String insertInstituteInfo(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertInstituteInfo");
		ModelAndView model = null;

		HttpSession session = request.getSession();

		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("insertInstituteInfo", "showInstituteInfoList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {

				int inst_id = (int) session.getAttribute("instituteId");
				int maker_id = (int) session.getAttribute("userId");
				int academic_year = (int) session.getAttribute("acYearId");
				
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				String inst_info_id = null;
				int no_clock_hr_faculty = 0;
				try {
					inst_info_id = (request.getParameter("inst_info_id"));
					no_clock_hr_faculty = Integer.parseInt(request.getParameter("no_clockhr_Faculty"));
				} catch (Exception e) {
					System.err.println("Exce in save innfo stitute I  " + e.getMessage());
					e.printStackTrace();
					no_clock_hr_faculty = 0;
				}

				// int academic_year = Integer.parseInt(request.getParameter("academic_year"));
				int no_fullTime_Faculty = Integer.parseInt(request.getParameter("no_fullTime_Faculty"));
				int no_nonTeaching_faculty = Integer.parseInt(request.getParameter("no_nonTeaching_faculty"));
				int no_suppStaff = Integer.parseInt(request.getParameter("no_suppStaff"));
				int no_currAdmitted_Student = Integer.parseInt(request.getParameter("no_currAdmitted_Student"));
				String treasury_code = "NA"; // request.getParameter("treasury_code");
				String rusa_idNo = "NA"; // request.getParameter("rusa_idNo");

				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar cal = Calendar.getInstance();

				String curDateTime = dateFormat.format(cal.getTime());

				DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

				String curDate = dateFormatStr.format(new Date());
				int auton_status=Integer.parseInt(request.getParameter("auton_status"));

				if (inst_info_id.isEmpty() == true) {

					//System.out.println("inst id is" + inst_id);
					InstituteInfo lib = new InstituteInfo();

					lib.setYearId(academic_year);
					lib.setInstituteId(inst_id);
					lib.setNoCurrentAdmitedStnt(no_currAdmitted_Student);
					lib.setNoNonteachingIncludingOfficeStaff(no_nonTeaching_faculty);
					lib.setNoOfFulltimeFaculty(no_fullTime_Faculty);
					lib.setNoSupportStaff(no_suppStaff);
					lib.setAddBy(maker_id);
					lib.setEditBy(maker_id);
					lib.setEditDatetime(curDateTime);
					lib.setAddDatetime(curDateTime);
					lib.setRusaIdNo(rusa_idNo);
					lib.setTreasuryCode(treasury_code);
					lib.setDelStatus(1);
					lib.setExInt1(Integer.parseInt(request.getParameter("no_sanction_post")));
					lib.setExInt2(no_clock_hr_faculty);
					lib.setExVar1("NA");
					lib.setExVar2("NA");
					lib.setAutonStatus(auton_status);
					InstituteInfo editInstInfo = rest.postForObject(Constants.url + "saveInstituteInfo", lib,
							InstituteInfo.class);

				} else {

					//System.out.println("in edit InstituteInfo");

					//System.out.println("inst_info_id" + inst_info_id);

					map.add("infoDetailId", inst_info_id);
					InstituteInfo lib1 = rest.postForObject(Constants.url + "getInstituteInfoByInfoDetailId", map,
							InstituteInfo.class);

					lib1.setYearId(academic_year);
					lib1.setInstituteId(inst_id);
					lib1.setNoCurrentAdmitedStnt(no_currAdmitted_Student);
					lib1.setNoNonteachingIncludingOfficeStaff(no_nonTeaching_faculty);
					lib1.setNoOfFulltimeFaculty(no_fullTime_Faculty);
					lib1.setNoSupportStaff(no_suppStaff);
					lib1.setAddBy(maker_id);
					lib1.setEditBy(maker_id);
					lib1.setEditDatetime(curDateTime);
					lib1.setAddDatetime(curDateTime);
					lib1.setRusaIdNo(rusa_idNo);
					lib1.setTreasuryCode(treasury_code);
					lib1.setExInt1(Integer.parseInt(request.getParameter("no_sanction_post")));
					lib1.setExInt2(no_clock_hr_faculty);
					lib1.setAutonStatus(auton_status);

					InstituteInfo editInstInfo = rest.postForObject(Constants.url + "saveInstituteInfo", lib1,
							InstituteInfo.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 0)
					a = "redirect:/showInstituteInfoList";

			}

		} catch (Exception e) {
			System.err.println("Exce in save innfo stitute I  " + e.getMessage());
			e.printStackTrace();
		}

		return a;

	}

	@RequestMapping(value = "/deleteInstituteInfo/{instId}", method = RequestMethod.GET)
	public String deleteInstituteInfo(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int instId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteInstituteInfo/{instId}", "showInstituteInfoList", "0", "0", "0",
				"1", newModuleList);
		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (instId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("instIds");
					//System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String instIdList = sb.toString();
					instIdList = instIdList.substring(0, instIdList.length() - 1);

					map.add("instIdList", instIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("instIdList", instId);
				}
				a = "redirect:/showInstituteInfoList";
				Info errMsg = rest.postForObject(Constants.url + "deleteInstituteInfo", map, Info.class);
			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstituteInfo at Master Contr " + e.getMessage());

			e.printStackTrace();

		}

		return a;

	}

////////////////////*****************Neha************************///////////////

	@RequestMapping(value = "/showConsultancyDetails", method = RequestMethod.GET)
	public ModelAndView showResearchProDetails(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/consultancy");

			model.addObject("title", "Consultancy Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showConsultancyDetailYes", method = RequestMethod.GET)
	public ModelAndView showConsultancyDetailYes(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("FacultyDetails/consultancyDetailList");

			model.addObject("title", "Consultancy Details Form");

		} catch (Exception e) {

			System.err.println("exception In showFacultyDetails at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

}