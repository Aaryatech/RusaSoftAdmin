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
import com.ats.rusasoft.model.AcademicYear;
import com.ats.rusasoft.model.Dept;
import com.ats.rusasoft.model.GetInstituteList;
import com.ats.rusasoft.model.GetStudentDetail;
import com.ats.rusasoft.model.Hod;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.Institute;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.Student;

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
	
	//////////////////////////*****************Librarian*************//////////////////
	
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
			int maker_id =(int)session.getAttribute("userId");
			
			
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
				lib.setMakerUserId(maker_id);
				
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
				lib1.setMakerUserId(maker_id);
				
				lib1.setInstituteId(inst_id);
				
			
			
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());
					
					lib1.setMakerEnterDatetime(curDateTime);
				
					
					Librarian editInst = rest.postForObject(Constants.url + "saveLibrarian", lib1, Librarian.class);

				
				
			}
				
				
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

	///////////////////////////////****Student************///////////////////
		
		@RequestMapping(value = "/showRegStud", method = RequestMethod.GET)
		public ModelAndView showRegStud(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = null;
			try {

				model = new ModelAndView("master/studReg");

				model.addObject("title", "Student Registration");

				Student editStudent =new Student();
				model.addObject("editStudent", editStudent);
				
				
				RestTemplate restTemplate = new RestTemplate();
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = restTemplate.postForObject(Constants.url + "getAcademicYearListByTypeId", map, AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);
				
				
				 restTemplate = new RestTemplate();
		        map = new LinkedMultiValueMap<String, Object>();
				HttpSession session = request.getSession();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int instId=userObj.getGetData().getUserInstituteId();
				map.add("instId",instId );
				System.err.println("deptList in showRegStud" + instId);
				
				Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList in showRegStud" + deptList.toString());

				model.addObject("deptList", deptList);
				


			} catch (Exception e) {

				System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

				e.printStackTrace();

			}

			return model;

		}

		
		
		@RequestMapping(value = "/insertStudent", method = RequestMethod.POST)
		public String insertStudent(HttpServletRequest request, HttpServletResponse response) {
			System.err.println("in insert insertStudent");
			ModelAndView model = null;
			HttpSession session = request.getSession();
			
			int inst_id =(int)session.getAttribute("instituteId");
			int maker_id =(int)session.getAttribute("userId");
			try {
				Student lib=new Student();
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int student_id = Integer.parseInt(request.getParameter("student_id"));
				System.out.println("student_id"+"student_id");
				
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
					
					System.out.println("inst id is"+inst_id);
					
			
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
				
				System.out.println("in edit");
			
				
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
				
				
			} catch (Exception e) {
				System.err.println("Exce in save lib  " + e.getMessage());
				e.printStackTrace();
			}
			
			int isView=Integer.parseInt(request.getParameter("is_view"));
			if(isView==1)
				return "redirect:/showStudList";
			else
				return "redirect:/showRegStud";

		

		}
		

		
		@RequestMapping(value = "/showStudList", method = RequestMethod.GET)
		public ModelAndView showStudList(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = null;
			try {

				model = new ModelAndView("master/studList");

				model.addObject("title", "Student List");
				
           HttpSession session = request.getSession();
				
				int inst_id =(int)session.getAttribute("instituteId");
				System.out.println("Student list inst id::::"+inst_id);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", inst_id);
                
				GetStudentDetail[] instArray = rest.postForObject(Constants.url + "getAllStudentByInstituteId",map,
						GetStudentDetail[].class);
				List<GetStudentDetail> StudList = new ArrayList<>(Arrays.asList(instArray));
				
				System.out.println("Student list is"+StudList.toString());

				model.addObject("StudList", StudList);
				
			} catch (Exception e) {

				System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

				e.printStackTrace();

			}

			return model;

		}
		
		
		@RequestMapping(value = "/showEditStudent", method = RequestMethod.POST)
		public ModelAndView showEditStudent(HttpServletRequest request, HttpServletResponse response) {
			ModelAndView model = null;

			try {

				model = new ModelAndView("master/studReg");

				int student_id = Integer.parseInt(request.getParameter("edit_stud_id"));
				System.out.println("Student id is"+student_id);

				model.addObject("title", " Edit Student  Registration");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			
			
				map.add("studId", student_id); 
				Student editStudent = rest.postForObject(Constants.url + "getStudentByStudentId", map, Student.class); 
				System.out.println("librarian is"+editStudent.toString());
			
			
				model.addObject("editStudent", editStudent);
				model.addObject("student_id",student_id);
				
				RestTemplate restTemplate = new RestTemplate();
			map = new LinkedMultiValueMap<String, Object>();
				map.add("type", 1);
				AcademicYear[] quolArray = restTemplate.postForObject(Constants.url + "getAcademicYearListByTypeId", map, AcademicYear[].class);
				List<AcademicYear> acaYearList = new ArrayList<>(Arrays.asList(quolArray));
				System.err.println("acaYearList " + acaYearList.toString());

				model.addObject("acaYearList", acaYearList);
			
				
				
				 restTemplate = new RestTemplate();
		        map = new LinkedMultiValueMap<String, Object>();
				HttpSession session = request.getSession();

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());
				Dept[] instArray = restTemplate.postForObject(Constants.url + "getAllDeptList", map, Dept[].class);
				List<Dept> deptList = new ArrayList<>(Arrays.asList(instArray));
				System.err.println("deptList " + deptList.toString());

				model.addObject("deptList", deptList);
				

			} catch (Exception e) {
				System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
				e.printStackTrace();
			}

			return model;

		}
		
		

		@RequestMapping(value = "/deleteStudents/{studId}", method = RequestMethod.GET)
		public String deleteStudents(HttpServletRequest request, HttpServletResponse response, @PathVariable int studId) {

			try {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (studId == 0) {

					System.err.println("Multiple records delete ");
					String[] studIds = request.getParameterValues("studIds");
					System.out.println("id are" + studIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < studIds.length; i++) {
						sb = sb.append(studIds[i] + ",");

					}
					String studIdList = sb.toString();
					studIdList = studIdList.substring(0, studIdList.length() - 1);

					map.add("studIdList", studIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("studIdList", studId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteStudents", map, Info.class);

			} catch (Exception e) {

				System.err.println(" Exception In deleteStudents at Master Contr " + e.getMessage());

				e.printStackTrace();

			}

			return "redirect:/showStudList";

		}


		
}
