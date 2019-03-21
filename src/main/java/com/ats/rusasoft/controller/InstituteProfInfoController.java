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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.model.GetStudentDetail;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.IqacBasicInfo;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.InstituteFunctionalMOU;

@Controller
@Scope("session")
public class InstituteProfInfoController {

	RestTemplate rest = new RestTemplate();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());
	
	@RequestMapping(value = "/showInstProf", method = RequestMethod.GET)
	public ModelAndView showInstProf(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/instProf");
		try {
			HttpSession session = request.getSession();
			model.addObject("title", "Institute Profile Information");
			int inst_id = (int) session.getAttribute("instituteId");
			
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instituteId", inst_id); // getInstitute Hod hod =
			IqacBasicInfo instRes = rest.postForObject(Constants.url + "getIqacInfoByInstId", map, IqacBasicInfo.class);
			model.addObject("instRes", instRes);
			model.addObject("date",DateConvertor.convertToDMY(instRes.getEstabilishmentDate()));
			System.out.println(instRes.toString());

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/insertIqacBasicInfo", method = RequestMethod.POST)
	public String insertIqacBasicInfo(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertIqacBasicInfo");
		HttpSession session = request.getSession();
		String a = null;
		/*try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("insertIqacBasicInfo", "showInstProfList", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}
*/
		/*	else {*/
		try {
				System.err.println("in insert insertIqacBasicInfo");
				ModelAndView model = null;

				int inst_id = (int) session.getAttribute("instituteId");
				int maker_id = (int) session.getAttribute("userId");

				IqacBasicInfo lib = new IqacBasicInfo();
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				String iqac_info_id = request.getParameter("iqac_info_id");
				
				System.out.println("iqac_info_id" + "iqac_info_id");

				String alt_faculty_name = request.getParameter("alt_faculty_name");
				String alt_fac_contact = request.getParameter("alt_fac_contact");

				String phone_no = request.getParameter("phone_no");
				String registered_email = request.getParameter("registered_email");
				String alt_email = request.getParameter("alt_email");

				String fax_no = request.getParameter("fax_no");

				
				String estb_date = request.getParameter("estb_date");

			
				if (iqac_info_id.isEmpty()==true) {

					System.out.println("inst id is" + inst_id);

					lib.setEstabilishmentDate(DateConvertor.convertToYMD(estb_date));
					lib.setIqacAltEmail1(registered_email);
					lib.setIqacAltEmail2(alt_email);
					lib.setIqacAltFax(fax_no);
					lib.setIqacAltMobile(alt_fac_contact);
					lib.setIqacAltName(alt_faculty_name);
					lib.setIqacAltPhone(phone_no);
					lib.setIsActive(1);
					
					
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

					IqacBasicInfo editInst = rest.postForObject(Constants.url + "saveInstituteBasicInfo", lib, IqacBasicInfo.class);

				} else {
					System.out.println("in edit");
					
					System.out.println("iqac_info_id" + iqac_info_id);
					map.add("iqacInfoId", iqac_info_id); // getInstitute Hod hod =
					IqacBasicInfo lib1 = rest.postForObject(Constants.url + "getIqacInfoByIqacInfoId", map, IqacBasicInfo.class);
					lib1.setEstabilishmentDate(DateConvertor.convertToYMD(estb_date));
					lib1.setIqacAltEmail1(registered_email);
					lib1.setIqacAltEmail2(alt_email);
					lib1.setIqacAltFax(fax_no);
					lib1.setIqacAltMobile(alt_fac_contact);
					lib1.setIqacAltName(alt_faculty_name);
					lib1.setIqacAltPhone(phone_no);
				
					
					
					lib.setMakerUserId(maker_id);

					lib.setInstituteId(inst_id);

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					lib1.setMakerEnterDatetime(curDateTime);

					IqacBasicInfo editInst = rest.postForObject(Constants.url + "saveInstituteBasicInfo", lib1, IqacBasicInfo.class);

				
				
			}
				a="redirect:/showInstProfList";

		}

		catch (Exception e) {
			System.err.println("Exce in save lib  " + e.getMessage());
			e.printStackTrace();
		}

		return a;

	}
	
	

	@RequestMapping(value = "/showInstProfList", method = RequestMethod.GET)
	public ModelAndView showInstProfList(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showInstProfList", "showInstProfList", "1", "0", "0", "0", newModuleList);

		/*	if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {*/
				System.out.println(" showLibList Accessable ");

				 model = new ModelAndView("instituteInfo/IQAC/instProfList");

				model.addObject("title", "Institiute Profile List");

				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", inst_id); // getInstitute Hod hod =
				IqacBasicInfo instRes = rest.postForObject(Constants.url + "getIqacInfoByInstId", map, IqacBasicInfo.class);
				model.addObject("instProfRes", instRes);
				model.addObject("date",DateConvertor.convertToDMY(instRes.getEstabilishmentDate()));
				System.out.println(instRes.toString());

			/*
			 * Info add = AccessControll.checkAccess("showInstProfList", "showInstProfList",
			 * "0", "1", "0", "0", newModuleList); Info edit =
			 * AccessControll.checkAccess("showInstProfList", "showInstProfList", "0", "0",
			 * "1", "0", newModuleList); Info delete =
			 * AccessControll.checkAccess("showInstProfList", "showInstProfList", "0", "0",
			 * "0", "1",newModuleList);
			 * 
			 * if (add.isError() == false) { System.out.println(" add   Accessable ");
			 * model.addObject("addAccess", 0);
			 * 
			 * } if (edit.isError() == false) { System.out.println(" edit   Accessable ");
			 * model.addObject("editAccess", 0); } if (delete.isError() == false) {
			 * System.out.println(" delete   Accessable "); model.addObject("deleteAccess",
			 * 0);
			 * 
			 * }
			 */

			/* } */

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/deleteInstProf/{iqacInfoId}", method = RequestMethod.GET)
	public String deleteLibrarians(HttpServletRequest request, HttpServletResponse response, @PathVariable int iqacInfoId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteInstProf/{instId}", "showInstProfList", "0", "0", "0", "1",
				newModuleList);
		try {
			/*if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {*/

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					System.err.println("Single Record delete ");
					map.add("iqacInfoId", iqacInfoId);
				

				Info errMsg = rest.postForObject(Constants.url + "deleteInstProf", map, Info.class);

				a = "redirect:/showInstProfList";
			/* } */

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

//////////////////////////////************************functional MOUS**********************/////////
	
	
	@RequestMapping(value = "/showFunctionalMOUs", method = RequestMethod.GET)
	public ModelAndView showFunctionalMOUs(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/functionalMOUs");
		try {

			model.addObject("title", "Functional MoUs");
			HttpSession session = request.getSession();

			int acYearId = (int) session.getAttribute("acYearId");
			int inst_id = (int) session.getAttribute("instituteId");
			
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("instId", inst_id);
			map.add("yearId", acYearId);


			InstituteFunctionalMOU[] instArray = rest.postForObject(Constants.url + "getAllMouByInstituteId", map,
					InstituteFunctionalMOU[].class);
			List<InstituteFunctionalMOU> mouList = new ArrayList<>(Arrays.asList(instArray));

			System.out.println("InstituteFunctionalMOU list is" + mouList.toString());

			model.addObject("mouList", mouList);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	

	@RequestMapping(value = "/showAddFunctionalMOUs", method = RequestMethod.GET)
	public ModelAndView showAddFunctionalMOUs(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = new ModelAndView("instituteInfo/IQAC/addFunMOUs");
		try {

			model.addObject("title", "Add Functional MoUs");
			InstituteFunctionalMOU editInst=new InstituteFunctionalMOU();
			
			model.addObject("editInst", editInst);
			
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/insertFunctionalMOU", method = RequestMethod.POST)
	public String insertFunctionalMOU(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertIqacBasicInfo");
		HttpSession session = request.getSession();
		String a = null;
		/*try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("insertIqacBasicInfo", "showInstProfList", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}
*/
		/*	else {*/
		try {
				System.err.println("in insert insertFunctionalMOU");
				ModelAndView model = null;
				
				int inst_id = (int) session.getAttribute("instituteId");
				int maker_id = (int) session.getAttribute("userId");
				int acYearId = (int) session.getAttribute("acYearId");


				InstituteFunctionalMOU lib = new InstituteFunctionalMOU();
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				int mou_id = Integer.parseInt(request.getParameter("mou_id"));
				
				System.out.println("mou_id" + mou_id);

			
			/* String functionalMOU=null; */
				/*
				if((Integer.parseInt(request.getParameter(functionalMOU))) == 7) {
					functionalMOU = request.getParameter("otherCourse");
				}
				else {*/
			
			/* } */
				String	functionalMOU = request.getParameter("functionalMOU");
				String c=null;
				if(functionalMOU.equals("7")) {
					c=request.getParameter("otherCourse");
				}
				else {
					c=request.getParameter("functionalMOU");
				}
				
				
				String title = request.getParameter("title");
				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");

				String beneficiaryMOU = request.getParameter("beneficiaryMOU");

				
				String beneficiaryMOUNo = request.getParameter("beneficiaryMOUNo");
				String instituteOfMoU = request.getParameter("instituteOfMoU");
				
				

			
				if (mou_id==0) {

					System.out.println("inst id is" + inst_id);

					lib.setMouFromdt((DateConvertor.convertToYMD(fromDate)));
					lib.setMouTodt(((DateConvertor.convertToYMD(toDate))));
					lib.setMouBeneficiary(beneficiaryMOU);
					lib.setMouTitle(title);
					lib.setYearId(acYearId);
					lib.setMouBeneficiaryNos(Integer.parseInt(beneficiaryMOUNo));
					lib.setMouInstitute(instituteOfMoU);
					lib.setMouAgency(c);
					
					lib.setIsActive(1);
					
					
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

					lib.setMakerDatetime(curDateTime);

					InstituteFunctionalMOU editInst = rest.postForObject(Constants.url + "saveInstituteMOU", lib, InstituteFunctionalMOU.class);

				} else {
				
				  System.out.println("in edit");
				  
				  System.out.println("mou_id" + mou_id); 
				  map.add("mouId",mou_id);
				   // getInstitute Hod hod = 
				  InstituteFunctionalMOU lib1 =  rest.postForObject(Constants.url + "getMOUByMouId", map, InstituteFunctionalMOU.class);

					lib1.setMouFromdt((DateConvertor.convertToYMD(fromDate)));
					lib1.setMouTodt(((DateConvertor.convertToYMD(toDate))));
					lib1.setMouBeneficiary(beneficiaryMOU);
					lib1.setMouTitle(title);
					lib1.setYearId(acYearId);
					lib.setMouBeneficiaryNos(Integer.parseInt(beneficiaryMOUNo));
					lib1.setMouInstitute(instituteOfMoU);
					lib1.setMouAgency(functionalMOU);
				  
				  lib1.setMakerUserId(maker_id);
				  
				  lib1.setInstituteId(inst_id);
				  
				  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); Calendar
				  cal = Calendar.getInstance();
				  
				  String curDateTime = dateFormat.format(cal.getTime());
				  
				  DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");
				  
				  String curDate = dateFormatStr.format(new Date());
				  
				  lib1.setMakerDatetime(curDateTime);
				  
				  InstituteFunctionalMOU editInst = rest.postForObject(Constants.url + "saveInstituteMOU", lib1, InstituteFunctionalMOU.class);
			
				  
				  
				 
			}
				a="redirect:/showFunctionalMOUs";

		}

		catch (Exception e) {
			System.err.println("Exce in save lib  " + e.getMessage());
			e.printStackTrace();
		}

		return a;

	}
	

	@RequestMapping(value = "/showEditMou", method = RequestMethod.POST)
	public ModelAndView showEditMou(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			/*Info view = AccessControll.checkAccess("showEditMou", "showFunctionalMOUs", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
*/
			 model = new ModelAndView("instituteInfo/IQAC/addFunMOUs");

				int edit_mou_id = Integer.parseInt(request.getParameter("edit_mou_id"));
				System.out.println("edit_mou_id id is" + edit_mou_id);

				model.addObject("title", " Edit Functional MOU  ");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("mouId", edit_mou_id);

				 InstituteFunctionalMOU editInst =  rest.postForObject(Constants.url + "getMOUByMouId", map, InstituteFunctionalMOU.class);
				System.out.println("InstituteFunctionalMOU is" + editInst.toString());
				model.addObject("editInst", editInst);
			
				model.addObject("fdate", DateConvertor.convertToDMY(editInst.getMouFromdt()));
				model.addObject("tdate", DateConvertor.convertToDMY(editInst.getMouTodt()));
			
			/* } */

		} catch (Exception e) {
			System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/deleteMous/{mouId}", method = RequestMethod.GET)
	public String deleteMous(HttpServletRequest request, HttpServletResponse response, @PathVariable int mouId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		/*
		 * Info view = AccessControll.checkAccess("deleteMous/{mouId}", "showLibList",
		 * "0", "0", "0", "1", newModuleList);
		 */
		try {
			/*if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {*/

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (mouId == 0) {

					System.err.println("Multiple records delete ");
					String[] mouIds = request.getParameterValues("mouIds");
					System.out.println("id are" + mouIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < mouIds.length; i++) {
						sb = sb.append(mouIds[i] + ",");

					}
					String mouIdList = sb.toString();
					mouIdList = mouIdList.substring(0, mouIdList.length() - 1);

					map.add("mouIdList", mouIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("mouIdList", mouId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteMous", map, Info.class);

				a = "redirect:/showFunctionalMOUs";
			/*
			 * }
			 */
		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}
	
	
	


}
