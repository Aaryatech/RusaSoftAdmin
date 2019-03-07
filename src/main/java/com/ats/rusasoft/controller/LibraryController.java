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

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.model.GetInstituteList;
import com.ats.rusasoft.model.Hod;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.LoginResponse;
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
			Librarian editInst =new Librarian();
			
			RestTemplate restTemplate = new RestTemplate();
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("type", 1);
			Quolification[] quolArray = restTemplate.postForObject(Constants.url + "getQuolificationList", map, Quolification[].class);
			List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
			System.err.println("quolfList " + quolfList.toString());

			model.addObject("quolfList", quolfList);
			model.addObject("editInst", editInst);

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
			HttpSession session = request.getSession();
			
			int inst_id =(int)session.getAttribute("instituteId");
			try {
				Librarian lib=new Librarian();
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int librarianId = Integer.parseInt(request.getParameter("librarian_id"));
				System.out.println("librarian_id"+"librarian_id");
				
				String librarian_name = request.getParameter("librarian_name");
				String lib_con_num = request.getParameter("lib_con_num");

				String librarian_email = request.getParameter("librarian_email");

				int lib_quolf = Integer.parseInt(request.getParameter("lib_quolf"));

				String lib_joiningDate = request.getParameter("lib_joiningDate");
				String relieving_date = request.getParameter("relieving_date");

				
				System.err.println("librarian id  " + librarianId);
				if (librarianId == 0) {
					
					System.out.println("inst id is"+inst_id);
					
			
				lib.setLibrarianName(librarian_name);
				lib.setContactNo(lib_con_num);
				lib.setEmail(librarian_email);
				lib.setQualificationId(lib_quolf);
				lib.setRealivingDate(relieving_date);
				lib.setJoiningDate(lib_joiningDate);
				lib.setMakerUserId(1);
				
				lib.setInstituteId(inst_id);
				lib.setDelStatus(1);
				lib.setExInt1(1);
				lib.setExInt2(1);
				lib.setExVar1("NA");
				lib.setExVar2("NA");
				
			
			
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());
					
					lib.setMakerEnterDatetime(curDateTime);
				
					
				
				
					

					Librarian editInst = rest.postForObject(Constants.url + "saveLibrarian", lib, Librarian.class);

			} else {
				System.out.println("in edit");
				System.out.println("in edit");
				
				map.add("libId", librarianId); // getInstitute Hod hod =
				Librarian lib1 = rest.postForObject(Constants.url + "getLibrarianByLibId", map, Librarian.class); 
			
				
			
				lib1.setLibrarianName(librarian_name);
				lib1.setContactNo(lib_con_num);
				lib1.setEmail(librarian_email);
				lib1.setQualificationId(lib_quolf);
				lib1.setRealivingDate(relieving_date);
				lib1.setJoiningDate(lib_joiningDate);
				lib1.setMakerUserId(1);
				
				lib1.setInstituteId(inst_id);
				
			
			
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());
					
					lib1.setMakerEnterDatetime(curDateTime);
				
					
					Librarian editInst = rest.postForObject(Constants.url + "saveLibrarian", lib1, Librarian.class);

				
				
			}
				
				
				/*
				 * else {
				 * 
				 * //map.add("hodId", hodId); // getInstitute Hod hod =
				 * rest.postForObject(Constants.url + "getHod", map, Hod.class); String deptName
				 * = request.getParameter("dept_name"); DateFormat dateFormat = new
				 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Calendar cal =
				 * Calendar.getInstance();
				 * 
				 * String curDateTime = dateFormat.format(cal.getTime());
				 * 
				 * DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");
				 * 
				 * String curDate = dateFormatStr.format(new Date());
				 * 
				 * hod.setContactNo(request.getParameter("hod_mob"));
				 * hod.setDeptId(Integer.parseInt(request.getParameter("hod_dept_id")));
				 * hod.setEditBy(1);//session hod.setEmail(request.getParameter("hod_email"));
				 * 
				 * hod.setHighestQualificationId(Integer.parseInt(request.getParameter(
				 * "hod_quolf"))); hod.setHodName(request.getParameter("hod_name"));
				 * hod.setInstituteId(1);//from sess hod.setUpdateDatetime(curDateTime);
				 * 
				 * Hod editInst = rest.postForObject(Constants.url + "saveHod", hod, Hod.class);
				 * 
				 * }
				 */
			} catch (Exception e) {
				System.err.println("Exce in save lib  " + e.getMessage());
				e.printStackTrace();
			}
			
			int isView=Integer.parseInt(request.getParameter("is_view"));
			if(isView==1)
				return "redirect:/showLibList";
			else
				return "redirect:/showRegLib";

		

		}
		
		
		@RequestMapping(value = "/showEditLibrarian", method = RequestMethod.POST)
		public ModelAndView showEditLibrarian(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView model = null;

			try {

				model = new ModelAndView("master/libReg");

				int libId = Integer.parseInt(request.getParameter("edit_lib_id"));
				System.out.println("librarian id is"+libId);

				model.addObject("title", " Edit Librarian  Registration");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("libId", libId);
			
				Librarian editInst = rest.postForObject(Constants.url + "getLibrarianByLibId", map, Librarian.class); 
				System.out.println("librarian is"+editInst.toString());
			/*
			 * try {
			 * editInst.setJoiningDate(DateConvertor.convertToDMY(editInst.getJoiningDate())
			 * ); editInst.setRealivingDate(DateConvertor.convertToDMY(editInst.
			 * getRealivingDate())); } catch (Exception e) { // TODO: handle exception }
			 */
				System.out.println("librarian is"+editInst.toString());
				model.addObject("editInst", editInst);
				model.addObject("libId", libId);
				
				RestTemplate restTemplate = new RestTemplate();
			map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				Quolification[] quolArray = restTemplate.postForObject(Constants.url + "getQuolificationList", map, Quolification[].class);
				List<Quolification> quolfList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("quolfList " + quolfList.toString());

				model.addObject("quolfList", quolfList);

			} catch (Exception e) {
				System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
				e.printStackTrace();
			}

			return model;

		}

		
		@RequestMapping(value = "/showLibList", method = RequestMethod.GET)
		public ModelAndView showLibList(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = null;
			try {

				model = new ModelAndView("master/libList");

				model.addObject("title", "Librarian List");
				HttpSession session = request.getSession();
				
				int inst_id =(int)session.getAttribute("instituteId");
				

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);
                
				Librarian[] instArray = rest.postForObject(Constants.url + "getAllLibrarianByInstituteId",map,
						Librarian[].class);
				List<Librarian> libtList = new ArrayList<>(Arrays.asList(instArray));
				
				System.out.println("lib list is"+libtList.toString());

				model.addObject("libtList", libtList);


			} catch (Exception e) {

				System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

				e.printStackTrace();

			}

			return model;

		}
		
		@RequestMapping(value = "/deleteLibrarians/{libId}", method = RequestMethod.GET)
		public String deleteLibrarians(HttpServletRequest request, HttpServletResponse response, @PathVariable int libId) {

			try {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (libId == 0) {

					System.err.println("Multiple records delete ");
					String[] libIds = request.getParameterValues("libIds");
					System.out.println("id are" + libIds);

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

				Info errMsg = rest.postForObject(Constants.url + "deleteLibrarians", map, Info.class);

			} catch (Exception e) {

				System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

				e.printStackTrace();

			}

			return "redirect:/showLibList";

		}

	

}
