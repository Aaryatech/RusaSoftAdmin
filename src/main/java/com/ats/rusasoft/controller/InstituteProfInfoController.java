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
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.GetStudentDetail;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.IqacBasicInfo;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.LinkageMaster;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.GetInstituteLinkage;
import com.ats.rusasoft.model.instprofile.InstituteAMC;
import com.ats.rusasoft.model.instprofile.InstituteBestPractices;
import com.ats.rusasoft.model.instprofile.InstituteFunctionalMOU;
import com.ats.rusasoft.model.instprofile.InstituteLinkage;

@Controller
@Scope("session")
public class InstituteProfInfoController {

	RestTemplate rest = new RestTemplate();

	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());

	@RequestMapping(value = "/showInstProf", method = RequestMethod.GET)
	public ModelAndView showInstProf(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		model = new ModelAndView("instituteInfo/IQAC/instProf");
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showInstProf", "showInstProfList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model.addObject("title", "Add Alternate IQAC Details");
				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", inst_id); // getInstitute Hod hod =
				IqacBasicInfo instRes = rest.postForObject(Constants.url + "getIqacInfoByInstId", map,
						IqacBasicInfo.class);
				model.addObject("instRes", instRes);
				
				Designation[] designArr= rest.getForObject(Constants.url + "/getAllDesignations",Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);
				
				model.addObject("date", DateConvertor.convertToDMY(instRes.getEstabilishmentDate()));
				System.out.println(instRes.toString());

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}
	
	
	@RequestMapping(value = "/showEditInstProf", method = RequestMethod.GET)
	public ModelAndView showEditInstProf(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showEditInstProf", "showInstProfList", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/instProf");
				model.addObject("title", "Edit Alternate IQAC Details");
				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", inst_id); // getInstitute Hod hod =
				IqacBasicInfo instRes = rest.postForObject(Constants.url + "getIqacInfoByInstId", map,
						IqacBasicInfo.class);
				model.addObject("instRes", instRes);
				
				Designation[] designArr= rest.getForObject(Constants.url + "/getAllDesignations",Designation[].class);
				List<Designation> designationList = new ArrayList<>(Arrays.asList(designArr));
				model.addObject("desigList", designationList);
				
				model.addObject("date", DateConvertor.convertToDMY(instRes.getEstabilishmentDate()));
				System.out.println(instRes.toString());

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}


	@RequestMapping(value = "/insertIqacBasicInfo", method = RequestMethod.POST)
	public String insertIqacBasicInfo(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertIqacBasicInfo");
		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("insertIqacBasicInfo", "showInstProfList", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {

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

				if (iqac_info_id.isEmpty() == true) {

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
					lib.setExVar1(request.getParameter("designation"));	//designation
					lib.setExVar2("NA");

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					lib.setMakerEnterDatetime(curDateTime);

					IqacBasicInfo editInst = rest.postForObject(Constants.url + "saveInstituteBasicInfo", lib,
							IqacBasicInfo.class);

				} else {
					System.out.println("in edit");

					System.out.println("iqac_info_id" + iqac_info_id);
					map.add("iqacInfoId", iqac_info_id); // getInstitute Hod hod =
					IqacBasicInfo lib1 = rest.postForObject(Constants.url + "getIqacInfoByIqacInfoId", map,
							IqacBasicInfo.class);
					lib1.setEstabilishmentDate(DateConvertor.convertToYMD(estb_date));
					lib1.setIqacAltEmail1(registered_email);
					lib1.setIqacAltEmail2(alt_email);
					lib1.setIqacAltFax(fax_no);
					lib1.setIqacAltMobile(alt_fac_contact);
					lib1.setIqacAltName(alt_faculty_name);
					lib1.setIqacAltPhone(phone_no);
					lib1.setExVar1(request.getParameter("designation"));	//designation
					lib.setMakerUserId(maker_id);

					lib.setInstituteId(inst_id);

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					lib1.setMakerEnterDatetime(curDateTime);

					IqacBasicInfo editInst = rest.postForObject(Constants.url + "saveInstituteBasicInfo", lib1,
							IqacBasicInfo.class);

				}
				a = "redirect:/showInstProfList";

			}

		} catch (Exception e) {
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

			Info view = AccessControll.checkAccess("showInstProfList", "showInstProfList", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				System.out.println(" showLibList Accessable ");

				model = new ModelAndView("instituteInfo/IQAC/instProfList");

				model.addObject("title", "Alternate IQAC Details List");

				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", inst_id); // getInstitute Hod hod =
				IqacBasicInfo instRes = rest.postForObject(Constants.url + "getIqacInfoByInstId", map,
						IqacBasicInfo.class);
				model.addObject("instProfRes", instRes);
				model.addObject("date", DateConvertor.convertToDMY(instRes.getEstabilishmentDate()));
				System.out.println(instRes.toString());

				Info add = AccessControll.checkAccess("showInstProfList", "showInstProfList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showInstProfList", "showInstProfList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showInstProfList", "showInstProfList", "0", "0", "0", "1",
						newModuleList);

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

			}

		} catch (Exception e) {

			System.err.println("exception In showStaffList at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteInstProf/{iqacInfoId}", method = RequestMethod.GET)
	public String deleteLibrarians(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int iqacInfoId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteInstProf/{iqacInfoId}", "showInstProfList", "0", "0", "0", "1",
				newModuleList);

		try {
			/*
			 * if (view.isError() == true) {
			 * 
			 * a = "redirect:/accessDenied";
			 * 
			 * }
			 * 
			 * else {
			 */

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

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showFunctionalMOUs", "showFunctionalMOUs", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/functionalMOUs");
				model.addObject("title", "Institute's Functional MoU List");

				int acYearId = (int) session.getAttribute("acYearId");
				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);
				map.add("yearId", acYearId);

				InstituteFunctionalMOU[] instArray = rest.postForObject(Constants.url + "getAllMouByInstituteId", map,
						InstituteFunctionalMOU[].class);
				List<InstituteFunctionalMOU> mouList = new ArrayList<>(Arrays.asList(instArray));

				System.out.println("InstituteFunctionalMOU list is" + mouList.toString());

				for (int i = 0; i < mouList.size(); i++) {
					mouList.get(i).setMouFromdt(DateConvertor.convertToDMY(mouList.get(i).getMouFromdt()));
					mouList.get(i).setMouTodt(DateConvertor.convertToDMY(mouList.get(i).getMouTodt()));
				}

				model.addObject("mouList", mouList);

				Info add = AccessControll.checkAccess("showFunctionalMOUs", "showFunctionalMOUs", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showFunctionalMOUs", "showFunctionalMOUs", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showFunctionalMOUs", "showFunctionalMOUs", "0", "0", "0", "1",
						newModuleList);

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

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddFunctionalMOUs", method = RequestMethod.GET)
	public ModelAndView showAddFunctionalMOUs(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showAddFunctionalMOUs", "showFunctionalMOUs", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/addFunMOUs");
				model.addObject("title", "Add Institute's Functional MoU");
				InstituteFunctionalMOU editInst = new InstituteFunctionalMOU();

				model.addObject("editInst", editInst);
			}

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
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("insertFunctionalMOU", "showFunctionalMOUs", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {

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

				String MOU_agency = request.getParameter("MOU_agency");
				String c = null;
				if (MOU_agency.equals("7")) {
					c = request.getParameter("otherCourse");
				}else {
					c = "NA";
				}

				String title = request.getParameter("title");
				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");

				String beneficiaryMOU = request.getParameter("beneficiaryMOU");

				String beneficiaryMOUNo = request.getParameter("beneficiaryMOUNo");
				String instituteOfMoU = "NA";

				if (mou_id == 0) {

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
					System.out.println("Dt:"+lib.toString());

					InstituteFunctionalMOU editInst = rest.postForObject(Constants.url + "saveInstituteMOU", lib,
							InstituteFunctionalMOU.class);

				} else {

					System.out.println("in edit");

					System.out.println("mou_id" + mou_id);
					map.add("mouId", mou_id);
					// getInstitute Hod hod =
					InstituteFunctionalMOU lib1 = rest.postForObject(Constants.url + "getMOUByMouId", map,
							InstituteFunctionalMOU.class);

					lib1.setMouFromdt((DateConvertor.convertToYMD(fromDate)));
					lib1.setMouTodt(((DateConvertor.convertToYMD(toDate))));
					lib1.setMouBeneficiary(beneficiaryMOU);
					lib1.setMouTitle(title);
					lib1.setYearId(acYearId);
					lib1.setMouBeneficiaryNos(Integer.parseInt(beneficiaryMOUNo));
					lib1.setMouInstitute(instituteOfMoU);
					lib1.setMouAgency(c);

					lib1.setMakerUserId(maker_id);

					lib1.setInstituteId(inst_id);

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					lib1.setMakerDatetime(curDateTime);

					InstituteFunctionalMOU editInst = rest.postForObject(Constants.url + "saveInstituteMOU", lib1,
							InstituteFunctionalMOU.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					a = "redirect:/showFunctionalMOUs";

				else
					a = "redirect:/showAddFunctionalMOUs";

			}
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

			Info view = AccessControll.checkAccess("showEditMou", "showFunctionalMOUs", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/addFunMOUs");

				int edit_mou_id = Integer.parseInt(request.getParameter("edit_mou_id"));
				System.out.println("edit_mou_id id is" + edit_mou_id);

				model.addObject("title", " Edit Institute's Functional MoU");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("mouId", edit_mou_id);

				InstituteFunctionalMOU editInst = rest.postForObject(Constants.url + "getMOUByMouId", map,
						InstituteFunctionalMOU.class);
				System.out.println("InstituteFunctionalMOU is" + editInst.toString());
				model.addObject("editInst", editInst);

				model.addObject("fdate", DateConvertor.convertToDMY(editInst.getMouFromdt()));
				model.addObject("tdate", DateConvertor.convertToDMY(editInst.getMouTodt()));

			}

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

		Info view = AccessControll.checkAccess("deleteMous/{mouId}", "showFunctionalMOUs", "0", "0", "0", "1",
				newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

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

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	// ******************************linkage*******************************//

	@RequestMapping(value = "/showCollaborationLinkages", method = RequestMethod.GET)
	public ModelAndView showCollaborationLinkages(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showCollaborationLinkages", "showCollaborationLinkages", "1", "0",
					"0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/collaborationLinkages");
				model.addObject("title", "Collaboration and Linkages");

				int inst_id = (int) session.getAttribute("instituteId");
				int acYearId = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);
				map.add("yearId", acYearId);

				GetInstituteLinkage[] instArray = rest.postForObject(Constants.url + "getAllInstLinkageByInstituteId",
						map, GetInstituteLinkage[].class);
				List<GetInstituteLinkage> linkageList = new ArrayList<>(Arrays.asList(instArray));

				System.out.println("colList list is" + linkageList.toString());
				model.addObject("linkageList", linkageList);

				Info add = AccessControll.checkAccess("showCollaborationLinkages", "showCollaborationLinkages", "0",
						"1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showCollaborationLinkages", "showCollaborationLinkages", "0",
						"0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showCollaborationLinkages", "showCollaborationLinkages", "0",
						"0", "0", "1", newModuleList);

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

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddCollaborationLinkages", method = RequestMethod.GET)
	public ModelAndView showAddCollaborationLinkages(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showAddCollaborationLinkages", "showCollaborationLinkages", "0",
					"1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/addCollabLink");
				model.addObject("title", "Add Collaboration and Linkages");
				InstituteLinkage editInst = new InstituteLinkage();

				model.addObject("editInst", editInst);

				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);

				LinkageMaster[] instArray = rest.postForObject(Constants.url + "getAllInstLinkageNamesByInstituteId",
						map, LinkageMaster[].class);
				List<LinkageMaster> coltList = new ArrayList<>(Arrays.asList(instArray));

				System.out.println("colList list is" + coltList.toString());
				model.addObject("colList", coltList);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showEditColLinkage", method = RequestMethod.POST)
	public ModelAndView showEditColLinkage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showEditColLinkage", "showCollaborationLinkages", "0", "0", "1",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/addCollabLink");

				int edit_link_id = Integer.parseInt(request.getParameter("edit_link_id"));
				System.out.println("edit_mou_id id is" + edit_link_id);

				model.addObject("title", " Edit Collaboration and Linkages ");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("linkId", edit_link_id);

				InstituteLinkage editInst = rest.postForObject(Constants.url + "getInstLinkageByLinkId", map,
						InstituteLinkage.class);
				System.out.println("InstituteFunctionalMOU is" + editInst.toString());
				model.addObject("editInst", editInst);

				int inst_id = (int) session.getAttribute("instituteId");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);

				LinkageMaster[] instArray = rest.postForObject(Constants.url + "getAllInstLinkageNamesByInstituteId",
						map, LinkageMaster[].class);
				List<LinkageMaster> coltList = new ArrayList<>(Arrays.asList(instArray));

				System.out.println("colList list is" + coltList.toString());
				model.addObject("colList", coltList);

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/insertColLinkages", method = RequestMethod.POST)
	public String insertColLinkages(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertColLinkages");
		HttpSession session = request.getSession();
		String a = null;
		try {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info view = AccessControll.checkAccess("insertColLinkages", "showCollaborationLinkages", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true)

			{

				a = "redirect:/accessDenied";

			}

			else {

				System.err.println("in insert insertColLinkages");
				ModelAndView model = null;

				int inst_id = (int) session.getAttribute("instituteId");
				int maker_id = (int) session.getAttribute("userId");
				int acYearId = (int) session.getAttribute("acYearId");

				InstituteLinkage lib = new InstituteLinkage();
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				int link_id = Integer.parseInt(request.getParameter("link_id"));

				System.out.println("link_id" + link_id);

				String colName = request.getParameter("colName");

				String col_agency = request.getParameter("col_agency");
				String linkageNature = request.getParameter("linkageNature");
				String beneficiaryMOU = request.getParameter("beneficiaryMOU");

				String totalParticipants = request.getParameter("totalParticipants");

				if (link_id == 0) {

					System.out.println("inst id is" + inst_id);

					lib.setLinkAgency(col_agency);
					lib.setLinkBeneficiaryNos(Integer.parseInt(totalParticipants));
					lib.setLinkBeneficiary(beneficiaryMOU);
					lib.setLinknameId(Integer.parseInt(colName));
					lib.setLinkNature(linkageNature);

					lib.setMakerUserId(maker_id);
					lib.setInstituteId(inst_id);
					lib.setYearId(acYearId);

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

					lib.setMakerDatetime(curDateTime);

					InstituteLinkage editInst = rest.postForObject(Constants.url + "saveInstituteColLinkage", lib,
							InstituteLinkage.class);

				} else {

					System.out.println("in edit InstituteLinkage");

					System.out.println("link_id" + link_id);
					map.add("linkId", link_id);

					InstituteLinkage lib1 = rest.postForObject(Constants.url + "getInstLinkageByLinkId", map,
							InstituteLinkage.class);

					lib1.setLinkAgency(col_agency);
					lib1.setLinkBeneficiaryNos(Integer.parseInt(totalParticipants));
					lib1.setLinkBeneficiary(beneficiaryMOU);
					lib1.setLinknameId(Integer.parseInt(colName));
					lib1.setLinkNature(linkageNature);

					lib1.setYearId(acYearId);

					lib1.setMakerUserId(maker_id);

					lib1.setInstituteId(inst_id);

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					lib1.setMakerDatetime(curDateTime);

					InstituteLinkage editInst = rest.postForObject(Constants.url + "saveInstituteColLinkage", lib1,
							InstituteLinkage.class);

				}
				a = "redirect:/showCollaborationLinkages";

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					a = "redirect:/showCollaborationLinkages";

				else
					a = "redirect:/showAddCollaborationLinkages";

			}
		}

		catch (Exception e) {
			System.err.println("Exce in save lib  " + e.getMessage());
			e.printStackTrace();
		}

		return a;

	}

	@RequestMapping(value = "/deleteInstLinkages/{linkId}", method = RequestMethod.GET)
	public String deleteinstLinkages(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int linkId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteiInstLinkages/{linkId}", "showCollaborationLinkages", "0", "0",
				"0", "1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (linkId == 0) {

					System.err.println("Multiple records delete ");
					String[] linkIds = request.getParameterValues("linkIds");
					System.out.println("id are" + linkIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < linkIds.length; i++) {
						sb = sb.append(linkIds[i] + ",");

					}
					String linkIdList = sb.toString();
					linkIdList = linkIdList.substring(0, linkIdList.length() - 1);

					map.add("linkIdList", linkIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("linkIdList", linkId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteInstLinkages", map, Info.class);

				a = "redirect:/showCollaborationLinkages";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

////////////////////////////////////***********************Linkage Master************************/////////////

	@RequestMapping(value = "/showMasterCollaborationLinkages", method = RequestMethod.GET)
	public ModelAndView showMasterCollaborationLinkages(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showMasterCollaborationLinkages", "showMasterCollaborationLinkages",
					"1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/add_linkage_master");

				model.addObject("title", "Collaboration - Linkage Type Name");

				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);

				LinkageMaster[] instArray = rest.postForObject(Constants.url + "getAllInstLinkageNamesByInstituteId",
						map, LinkageMaster[].class);
				List<LinkageMaster> coltList = new ArrayList<>(Arrays.asList(instArray));

				System.out.println("colList list is" + coltList.toString());
				model.addObject("colList", coltList);

				LinkageMaster editInst = new LinkageMaster();
				model.addObject("editInst", editInst);

				Info add = AccessControll.checkAccess("showMasterCollaborationLinkages",
						"showMasterCollaborationLinkages", "0", "1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showMasterCollaborationLinkages",
						"showMasterCollaborationLinkages", "0", "0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showMasterCollaborationLinkages",
						"showMasterCollaborationLinkages", "0", "0", "0", "1", newModuleList);

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

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertLinkageMaster", method = RequestMethod.POST)
	public String insertLinkageMaster(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("insertLinkageMaster", "showMasterCollaborationLinkages", "0", "1",
					"0", "0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				System.err.println("Inside insertJournal method");

				int linknameId = 0;
				try {
					linknameId = Integer.parseInt(request.getParameter("linknameId"));
				} catch (Exception e) {
					linknameId = 0;
				}
				int inst_id = (int) session.getAttribute("instituteId");
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();
				String curDate = dateFormat.format(new Date());
				String dateTime = dateFormat.format(now);

				String linknameText = request.getParameter("linkname_text");
				String linknameRemarks = request.getParameter("linkname_remarks");

				int linkage_id = Integer.parseInt(request.getParameter("linkage_id"));

				LinkageMaster linkMaster = new LinkageMaster();

				if (linkage_id == 0) {

					linkMaster.setDelStatus(1);

					linkMaster.setInstituteId(inst_id);

					linkMaster.setIsActive(1);
					linkMaster.setLinknameId(linknameId);

					linkMaster.setLinknameRemarks(linknameRemarks);
					linkMaster.setLinknameText(linknameText);
					linkMaster.setMakerDatetime(dateTime);
					linkMaster.setMakerUserId(userObj.getUserId());

					LinkageMaster linkMasterInsertRes = rest.postForObject(Constants.url + "saveLinkageMaster",
							linkMaster, LinkageMaster.class);

					System.err.println("linkMasterInsertRes " + linkMasterInsertRes.toString());

				} else {

					System.out.println("in edit InstituteLinkage");

					System.out.println("linkage_id" + linkage_id);
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					map.add("linknameId", linkage_id);

					LinkageMaster linkMaster1 = rest.postForObject(Constants.url + "getInstLinkageMasterByLinkageId",
							map, LinkageMaster.class);

					linkMaster1.setLinknameRemarks(linknameRemarks);
					linkMaster1.setLinknameText(linknameText);
					linkMaster1.setMakerDatetime(dateTime);
					linkMaster1.setMakerUserId(userObj.getUserId());

					linkMaster1.setMakerDatetime(dateTime);
					linkMaster1.setMakerUserId(userObj.getUserId());

					LinkageMaster linkMasterInsertRes = rest.postForObject(Constants.url + "saveLinkageMaster",
							linkMaster1, LinkageMaster.class);

				}
				returnString = "redirect:/showMasterCollaborationLinkages";

			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in linkMasterInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/showEditLinkage", method = RequestMethod.POST)
	public ModelAndView showEditLinkage(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showEditLinkage", "showMasterCollaborationLinkages", "0", "0", "1",
					"0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_linkage_master");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int inst_id = (int) session.getAttribute("instituteId");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);

				LinkageMaster[] instArray = rest.postForObject(Constants.url + "getAllInstLinkageNamesByInstituteId",
						map, LinkageMaster[].class);
				List<LinkageMaster> coltList = new ArrayList<>(Arrays.asList(instArray));

				System.out.println("colList list is" + coltList.toString());
				model.addObject("colList", coltList);

				////////////////

				int edit_linkage_id = Integer.parseInt(request.getParameter("edit_linkage_id"));
				System.out.println("edit_link_id id is" + edit_linkage_id);

				model.addObject("title", " Edit Linkage Masters  ");

				map.add("linknameId", edit_linkage_id);

				LinkageMaster editInst = rest.postForObject(Constants.url + "getInstLinkageMasterByLinkageId", map,
						LinkageMaster.class);

				model.addObject("editInst", editInst);

				Info add = AccessControll.checkAccess("showMasterCollaborationLinkages",
						"showMasterCollaborationLinkages", "0", "1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showMasterCollaborationLinkages",
						"showMasterCollaborationLinkages", "0", "0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showMasterCollaborationLinkages",
						"showMasterCollaborationLinkages", "0", "0", "0", "1", newModuleList);

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

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/deleteLinkages/{linkId}", method = RequestMethod.GET)
	public String deleteLinkages(HttpServletRequest request, HttpServletResponse response, @PathVariable int linkId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteLinkages/{linkId}", "showMasterCollaborationLinkages", "0", "0",
				"0", "1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (linkId == 0) {

					System.err.println("Multiple records delete ");
					String[] linknameIds = request.getParameterValues("linknameIds");
					System.out.println("id are" + "linknameIds");

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < linknameIds.length; i++) {
						sb = sb.append(linknameIds[i] + ",");

					}
					String linknameIdList = sb.toString();
					linknameIdList = linknameIdList.substring(0, linknameIdList.length() - 1);

					map.add("linknameIdList", linknameIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("linknameIdList", linkId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteLinkName", map, Info.class);

				a = "redirect:/showMasterCollaborationLinkages";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	// **************************************AMC***************************//////////////////
	@RequestMapping(value = "/showAMC", method = RequestMethod.GET)
	public ModelAndView showAMC(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showAMC", "showAMC", "1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/amc");

				model.addObject("title", "AMC Details List");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int inst_id = (int) session.getAttribute("instituteId");
				int acYearId = (int) session.getAttribute("acYearId");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);
				map.add("yearId", acYearId);

				InstituteAMC[] instArray = rest.postForObject(Constants.url + "getAllInstAMCByInstituteId", map,
						InstituteAMC[].class);
				List<InstituteAMC> amcList = new ArrayList<>(Arrays.asList(instArray));

				System.out.println("amcList list is" + amcList.toString());
				model.addObject("amcList", amcList);

				Info add = AccessControll.checkAccess("showAMC", "showAMC", "0", "1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showAMC", "showAMC", "0", "0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showAMC", "showAMC", "0", "0", "0", "1", newModuleList);

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
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddAMC", method = RequestMethod.GET)
	public ModelAndView showAddAMC(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showAddAMC", "showAMC", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_amc");

				model.addObject("title", "Add AMC Details");

				InstituteAMC editInst = new InstituteAMC();
				model.addObject("editInst", editInst);

			}
		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showEditAmc", method = RequestMethod.POST)
	public ModelAndView showEditAmc(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showEditAmc", "showAMC", "0", "0", "1", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_amc");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int edit_amc_id = Integer.parseInt(request.getParameter("edit_amc_id"));
				System.out.println("edit_amc_id id is" + edit_amc_id);

				model.addObject("title", " Edit AMC Details");

				map.add("amcId", edit_amc_id);

				InstituteAMC editInst = rest.postForObject(Constants.url + "getInstAMCByAmcId", map,
						InstituteAMC.class);

				model.addObject("editInst", editInst);

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/deleteAMCS/{amcId}", method = RequestMethod.GET)
	public String deleteAMCS(HttpServletRequest request, HttpServletResponse response, @PathVariable int amcId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteAMCS/{amcId}", "showAMC", "0", "0", "0", "1", newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (amcId == 0) {

					System.err.println("Multiple records delete ");
					String[] amcIds = request.getParameterValues("amcIds");
					System.out.println("id are" + "amcIds");

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < amcIds.length; i++) {
						sb = sb.append(amcIds[i] + ",");

					}
					String amcIdList = sb.toString();
					amcIdList = amcIdList.substring(0, amcIdList.length() - 1);

					map.add("amcIdList", amcIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("amcIdList", amcId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteInstAmcs", map, Info.class);

				a = "redirect:/showAMC";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstitutes at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	@RequestMapping(value = "/insertInstituteAMC", method = RequestMethod.POST)
	public String insertInstituteAMC(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("insertInstituteAMC", "showAMC", "0", "1", "0", "0", newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				System.err.println("Inside insertJournal method");

				int inst_id = (int) session.getAttribute("instituteId");

				int maker_id = (int) session.getAttribute("userId");
				int acYearId = (int) session.getAttribute("acYearId");

				String amcRemarks = request.getParameter("amc_remark");
				int amcExpenditure = Integer.parseInt(request.getParameter("amc_expenditure"));
				String amcCompany = request.getParameter("amc_company");
				String amcTitle = request.getParameter("amc_title");
				int amc_id = Integer.parseInt(request.getParameter("amc_id"));

				InstituteAMC lib = new InstituteAMC();

				if (amc_id == 0) {

					lib.setAmcCompany(amcCompany);
					lib.setAmcExpenditure(amcExpenditure);
					lib.setAmcRemarks(amcRemarks);
					lib.setAmcTitle(amcTitle);

					lib.setMakerUserId(maker_id);
					lib.setInstituteId(inst_id);
					lib.setYearId(acYearId);

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

					lib.setMakerDatetime(curDateTime);

					InstituteAMC linkMasterInsertRes = rest.postForObject(Constants.url + "saveInstituteAMC", lib,
							InstituteAMC.class);

					System.err.println("linkMasterInsertRes " + linkMasterInsertRes.toString());

				} else {

					System.out.println("in edit InstituteLinkage");

					System.out.println("amc_id" + amc_id);
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					map.add("amcId", amc_id);

					InstituteAMC lib1 = rest.postForObject(Constants.url + "getInstAMCByAmcId", map,
							InstituteAMC.class);

					lib1.setAmcCompany(amcCompany);
					lib1.setAmcExpenditure(amcExpenditure);
					lib1.setAmcRemarks(amcRemarks);
					lib1.setAmcTitle(amcTitle);

					lib1.setMakerUserId(maker_id);
					lib1.setInstituteId(inst_id);
					lib1.setYearId(acYearId);

					InstituteAMC linkMasterInsertRes = rest.postForObject(Constants.url + "saveInstituteAMC", lib1,
							InstituteAMC.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					returnString = "redirect:/showAMC";

				else
					returnString = "redirect:/showAddAMC";

			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in linkMasterInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	// ********************************BestPractice***********************************************

	@RequestMapping(value = "/showBestPractice", method = RequestMethod.GET)
	public ModelAndView showBestPractice(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showBestPractice", "showBestPractice", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				model = new ModelAndView("instituteInfo/IQAC/bestPrac");

				model.addObject("title", "Best Practices Details");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int inst_id = (int) session.getAttribute("instituteId");
				int acYearId = (int) session.getAttribute("acYearId");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);
				map.add("yearId", acYearId);

				InstituteBestPractices[] instArray = rest.postForObject(
						Constants.url + "getAllInstBestPracticesByInstituteId", map, InstituteBestPractices[].class);
				List<InstituteBestPractices> pracList = new ArrayList<>(Arrays.asList(instArray));

				System.out.println("pracList list is" + pracList.toString());
				model.addObject("pracList", pracList);
				for (int i = 0; i < pracList.size(); i++) {
					pracList.get(i).setPracticesEffectiveFrom(
							DateConvertor.convertToDMY(pracList.get(i).getPracticesEffectiveFrom()));

				}

				Info add = AccessControll.checkAccess("showBestPractice", "showBestPractice", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showBestPractice", "showBestPractice", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showBestPractice", "showBestPractice", "0", "0", "0", "1",
						newModuleList);

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
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddBestPractice", method = RequestMethod.GET)
	public ModelAndView showAddBestPractice(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;

		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showAddBestPractice", "showBestPractice", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_best_prac");

				model.addObject("title", "Add Best Practices Details");
				InstituteBestPractices editInst = new InstituteBestPractices();
				model.addObject("editInst", editInst);
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/insertInstituteBestPract", method = RequestMethod.POST)
	public String insertInstituteBestPract(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("insertInstituteBestPract", "showBestPractice", "0", "1", "0", "0",
					newModuleList);

			System.out.println(view);

			if (view.isError() == false) {
				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

				System.err.println("Inside insertJournal method");

				int inst_id = (int) session.getAttribute("instituteId");

				int maker_id = (int) session.getAttribute("userId");
				int acYearId = (int) session.getAttribute("acYearId");

				String practices_beneficiary = request.getParameter("benificiary");

				String practices_effective_from = request.getParameter("practices_effective_from");
				String practices_name = request.getParameter("bestPrac");
				int prac_id = Integer.parseInt(request.getParameter("prac_id"));

				InstituteBestPractices lib = new InstituteBestPractices();

				if (prac_id == 0) {

					lib.setPracticesBeneficiary(practices_beneficiary);
					lib.setPracticesEffectiveFrom(DateConvertor.convertToYMD(practices_effective_from));
					lib.setPracticesName(practices_name);

					lib.setMakerUserId(maker_id);
					lib.setInstituteId(inst_id);
					lib.setYearId(acYearId);

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

					lib.setMakerDatetime(curDateTime);

					InstituteBestPractices linkMasterInsertRes = rest.postForObject(
							Constants.url + "saveInstituteBestPractices", lib, InstituteBestPractices.class);

					System.err.println("linkMasterInsertRes " + linkMasterInsertRes.toString());

				} else {

					System.out.println("in edit InstituteLinkage");

					System.out.println("prac_id" + prac_id);
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					map.add("practicesId", prac_id);

					InstituteBestPractices lib1 = rest.postForObject(Constants.url + "getInstBestPracByPracId", map,
							InstituteBestPractices.class);

					lib1.setPracticesBeneficiary(practices_beneficiary);
					lib1.setPracticesEffectiveFrom(DateConvertor.convertToYMD(practices_effective_from));
					lib1.setPracticesName(practices_name);

					lib1.setMakerUserId(maker_id);
					lib1.setInstituteId(inst_id);
					lib1.setYearId(acYearId);

					InstituteBestPractices linkMasterInsertRes = rest.postForObject(
							Constants.url + "saveInstituteBestPractices", lib1, InstituteBestPractices.class);

				}

				int isView = Integer.parseInt(request.getParameter("is_view"));
				if (isView == 1)
					returnString = "redirect:/showBestPractice";

				else
					returnString = "redirect:/showAddBestPractice";

			} else {

				returnString = "redirect:/accessDenied";

			}
		}

		catch (Exception e) {
			System.err.println("EXCE in linkMasterInsertRes " + e.getMessage());
			e.printStackTrace();

		}
		return returnString;

	}

	@RequestMapping(value = "/showEditBestPrac", method = RequestMethod.POST)
	public ModelAndView showEditBestPrac(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = null;
		HttpSession session = request.getSession();

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {

			Info view = AccessControll.checkAccess("showEditBestPrac", "showBestPractice", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("instituteInfo/IQAC/add_best_prac");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int edit_prac_id = Integer.parseInt(request.getParameter("edit_prac_id"));
				System.out.println("edit_prac_id id is" + edit_prac_id);

				model.addObject("title", " Edit Best Practices Details");

				map.add("practicesId", edit_prac_id);

				InstituteBestPractices editInst = rest.postForObject(Constants.url + "getInstBestPracByPracId", map,
						InstituteBestPractices.class);

				model.addObject("editInst", editInst);

				model.addObject("date", DateConvertor.convertToDMY(editInst.getPracticesEffectiveFrom()));

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditLibrarian/{instId}  " + e.getMessage());
			e.printStackTrace();
		}

		return model;

	}

	@RequestMapping(value = "/deleteBestPrac/{pracId}", method = RequestMethod.GET)
	public String deleteBestPrac(HttpServletRequest request, HttpServletResponse response, @PathVariable int pracId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("deleteBestPrac/{pracId}", "showBestPractice", "0", "0", "0", "1",
				newModuleList);

		try {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (pracId == 0) {

					System.err.println("Multiple records delete ");
					String[] pracIds = request.getParameterValues("pracIds");
					System.out.println("id are" + "amcIds");

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < pracIds.length; i++) {
						sb = sb.append(pracIds[i] + ",");

					}
					String practIdList = sb.toString();
					practIdList = practIdList.substring(0, practIdList.length() - 1);

					map.add("practIdList", practIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("practIdList", pracId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteInstBestPractices", map, Info.class);

				a = "redirect:/showBestPractice";

			}

		} catch (Exception e) {

			System.err.println(" Exception In deleteInstBestPractices at Master Contr " + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

}
