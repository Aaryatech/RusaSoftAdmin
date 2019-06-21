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
import com.ats.rusasoft.faculty.model.FacultyEmpowerment;
import com.ats.rusasoft.model.Designation;
import com.ats.rusasoft.model.GetStudentDetail;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.IqacBasicInfo;
import com.ats.rusasoft.model.LinkageMaster;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.instprofile.GetInstituteLinkage;
import com.ats.rusasoft.model.instprofile.InstituteAMC;
import com.ats.rusasoft.model.instprofile.InstituteBestPractices;
import com.ats.rusasoft.model.instprofile.InstituteFunctionalMOU;
import com.ats.rusasoft.model.instprofile.InstituteLinkage;
import com.ats.rusasoft.model.instprofile.RedressedStudGrievance;

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
				//System.out.println(instRes.toString());

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
				//System.out.println(instRes.toString());

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditredInforarian/{instId}  " + e.getMessage());
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

				IqacBasicInfo redInfo = new IqacBasicInfo();
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				String iqac_info_id = request.getParameter("iqac_info_id");

				//System.out.println("iqac_info_id" + "iqac_info_id");

				String alt_faculty_name = request.getParameter("alt_faculty_name");
				String alt_fac_contact = request.getParameter("alt_fac_contact");

				String phone_no = request.getParameter("phone_no");
				String registered_email = request.getParameter("registered_email");
				String alt_email = request.getParameter("alt_email");

				String fax_no = request.getParameter("fax_no");

				String estb_date = request.getParameter("estb_date");

				if (iqac_info_id.isEmpty() == true) {

					//System.out.println("inst id is" + inst_id);

					redInfo.setEstabilishmentDate(DateConvertor.convertToYMD(estb_date));
					redInfo.setIqacAltEmail1(registered_email);
					redInfo.setIqacAltEmail2(alt_email);
					redInfo.setIqacAltFax(fax_no);
					redInfo.setIqacAltMobile(alt_fac_contact);
					redInfo.setIqacAltName(alt_faculty_name);
					redInfo.setIqacAltPhone(phone_no);
					redInfo.setIsActive(1);

					redInfo.setMakerUserId(maker_id);

					redInfo.setInstituteId(inst_id);
					redInfo.setDelStatus(1);

					redInfo.setExInt1(1);
					redInfo.setExInt2(1);
					redInfo.setExVar1(request.getParameter("designation"));	//designation
					redInfo.setExVar2("NA");

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					redInfo.setMakerEnterDatetime(curDateTime);

					IqacBasicInfo editInst = rest.postForObject(Constants.url + "saveInstituteBasicInfo", redInfo,
							IqacBasicInfo.class);

				} else {
					//System.out.println("in edit");

					//System.out.println("iqac_info_id" + iqac_info_id);
					map.add("iqacInfoId", iqac_info_id); // getInstitute Hod hod =
					IqacBasicInfo redInfo1 = rest.postForObject(Constants.url + "getIqacInfoByIqacInfoId", map,
							IqacBasicInfo.class);
					redInfo1.setEstabilishmentDate(DateConvertor.convertToYMD(estb_date));
					redInfo1.setIqacAltEmail1(registered_email);
					redInfo1.setIqacAltEmail2(alt_email);
					redInfo1.setIqacAltFax(fax_no);
					redInfo1.setIqacAltMobile(alt_fac_contact);
					redInfo1.setIqacAltName(alt_faculty_name);
					redInfo1.setIqacAltPhone(phone_no);
					redInfo1.setExVar1(request.getParameter("designation"));	//designation
					redInfo.setMakerUserId(maker_id);

					redInfo.setInstituteId(inst_id);

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					redInfo1.setMakerEnterDatetime(curDateTime);

					IqacBasicInfo editInst = rest.postForObject(Constants.url + "saveInstituteBasicInfo", redInfo1,
							IqacBasicInfo.class);

				}
				a = "redirect:/showInstProfList";

			}

		} catch (Exception e) {
			System.err.println("Exce in save redInfo  " + e.getMessage());
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
				//System.out.println(" showredInfoList Accessable ");

				model = new ModelAndView("instituteInfo/IQAC/instProfList");

				model.addObject("title", "Alternate IQAC Details");

				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instituteId", inst_id); // getInstitute Hod hod =
				IqacBasicInfo instRes = rest.postForObject(Constants.url + "getIqacInfoByInstId", map,
						IqacBasicInfo.class);
				model.addObject("instProfRes", instRes);
				model.addObject("date", DateConvertor.convertToDMY(instRes.getEstabilishmentDate()));
				//System.out.println(instRes.toString());

				Info add = AccessControll.checkAccess("showInstProfList", "showInstProfList", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("showInstProfList", "showInstProfList", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("showInstProfList", "showInstProfList", "0", "0", "0", "1",
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

	@RequestMapping(value = "/deleteInstProf/{iqacInfoId}", method = RequestMethod.GET)
	public String deleteredInforarians(HttpServletRequest request, HttpServletResponse response,
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
				model.addObject("title", "Institute's Functional MoU's (Academic)");

				int acYearId = (int) session.getAttribute("acYearId");
				int inst_id = (int) session.getAttribute("instituteId");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);
				map.add("yearId", acYearId);

				InstituteFunctionalMOU[] instArray = rest.postForObject(Constants.url + "getAllMouByInstituteId", map,
						InstituteFunctionalMOU[].class);
				List<InstituteFunctionalMOU> mouList = new ArrayList<>(Arrays.asList(instArray));

				//System.out.println("InstituteFunctionalMOU list is" + mouList.toString());

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
				model.addObject("title", "Add Institute's MoU's (Academic)");
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

				InstituteFunctionalMOU redInfo = new InstituteFunctionalMOU();
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				int mou_id = Integer.parseInt(request.getParameter("mou_id"));

				//System.out.println("mou_id" + mou_id);

				String MOU_agency = request.getParameter("MOU_agency");
				
				String title = request.getParameter("title");
				String fromDate = request.getParameter("fromDate");
				String toDate = request.getParameter("toDate");

				String beneficiaryMOU = request.getParameter("beneficiaryMOU");

				String beneficiaryMOUNo = request.getParameter("beneficiaryMOUNo");
				String instituteOfMoU = request.getParameter("otherCourse");

				if (mou_id == 0) {

					//System.out.println("inst id is" + inst_id);

					redInfo.setMouFromdt((DateConvertor.convertToYMD(fromDate)));
					redInfo.setMouTodt(((DateConvertor.convertToYMD(toDate))));
					redInfo.setMouBeneficiary(beneficiaryMOU);
					redInfo.setMouTitle(title);
					redInfo.setYearId(acYearId);
					redInfo.setMouBeneficiaryNos(Integer.parseInt(beneficiaryMOUNo));
					redInfo.setMouInstitute(instituteOfMoU);   // other
					redInfo.setMouAgency(MOU_agency); //NIIT, IIT,IIT,University...7
					redInfo.setIsActive(1);

					redInfo.setMakerUserId(maker_id);

					redInfo.setInstituteId(inst_id);
					redInfo.setDelStatus(1);

					redInfo.setExInt1(1);
					redInfo.setExInt2(1);
					redInfo.setExVar1("NA");
					redInfo.setExVar2("NA");

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					redInfo.setMakerDatetime(curDateTime);
					//System.out.println("Dt:"+redInfo.toString());

					InstituteFunctionalMOU editInst = rest.postForObject(Constants.url + "saveInstituteMOU", redInfo,
							InstituteFunctionalMOU.class);

				} else {

					//System.out.println("in edit");

					//System.out.println("mou_id" + mou_id);
					map.add("mouId", mou_id);
					// getInstitute Hod hod =
					InstituteFunctionalMOU redInfo1 = rest.postForObject(Constants.url + "getMOUByMouId", map,
							InstituteFunctionalMOU.class);

					redInfo1.setMouFromdt((DateConvertor.convertToYMD(fromDate)));
					redInfo1.setMouTodt(((DateConvertor.convertToYMD(toDate))));
					redInfo1.setMouBeneficiary(beneficiaryMOU);
					redInfo1.setMouTitle(title);
					redInfo1.setYearId(acYearId);
					redInfo1.setMouBeneficiaryNos(Integer.parseInt(beneficiaryMOUNo));
					redInfo1.setMouInstitute(instituteOfMoU);
					redInfo1.setMouAgency(MOU_agency);

					redInfo1.setMakerUserId(maker_id);

					redInfo1.setInstituteId(inst_id);

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					redInfo1.setMakerDatetime(curDateTime);

					InstituteFunctionalMOU editInst = rest.postForObject(Constants.url + "saveInstituteMOU", redInfo1,
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
			System.err.println("Exce in save redInfo  " + e.getMessage());
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
				//System.out.println("edit_mou_id id is" + edit_mou_id);

				model.addObject("title", " Edit Institute's Functional MoU's (Academic)");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("mouId", edit_mou_id);

				InstituteFunctionalMOU editInst = rest.postForObject(Constants.url + "getMOUByMouId", map,
						InstituteFunctionalMOU.class);
				//System.out.println("InstituteFunctionalMOU is" + editInst.toString());
				model.addObject("editInst", editInst);

				model.addObject("fdate", DateConvertor.convertToDMY(editInst.getMouFromdt()));
				model.addObject("tdate", DateConvertor.convertToDMY(editInst.getMouTodt()));

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditredInforarian/{instId}  " + e.getMessage());
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
					//System.out.println("id are" + mouIds);

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

				//System.out.println("colList list is" + linkageList.toString());
				model.addObject("linkageList", linkageList);

				Info add = AccessControll.checkAccess("showCollaborationLinkages", "showCollaborationLinkages", "0",
						"1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showCollaborationLinkages", "showCollaborationLinkages", "0",
						"0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showCollaborationLinkages", "showCollaborationLinkages", "0",
						"0", "0", "1", newModuleList);

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

				//System.out.println("colList list is" + coltList.toString());
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
				//System.out.println("edit_mou_id id is" + edit_link_id);

				model.addObject("title", " Edit Collaboration and Linkages ");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("linkId", edit_link_id);

				InstituteLinkage editInst = rest.postForObject(Constants.url + "getInstLinkageByLinkId", map,
						InstituteLinkage.class);
				//System.out.println("InstituteFunctionalMOU is" + editInst.toString());
				model.addObject("editInst", editInst);

				int inst_id = (int) session.getAttribute("instituteId");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);

				LinkageMaster[] instArray = rest.postForObject(Constants.url + "getAllInstLinkageNamesByInstituteId",
						map, LinkageMaster[].class);
				List<LinkageMaster> coltList = new ArrayList<>(Arrays.asList(instArray));

				//System.out.println("colList list is" + coltList.toString());
				model.addObject("colList", coltList);

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditredInforarian/{instId}  " + e.getMessage());
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

				InstituteLinkage redInfo = new InstituteLinkage();
				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				int link_id = Integer.parseInt(request.getParameter("link_id"));

				//System.out.println("link_id" + link_id);

				String colName = request.getParameter("colName");

				String col_agency = request.getParameter("col_agency");
				String linkageNature = request.getParameter("linkageNature");
				String beneficiaryMOU = request.getParameter("beneficiaryMOU");

				String totalParticipants = request.getParameter("totalParticipants");

				if (link_id == 0) {

					//System.out.println("inst id is" + inst_id);

					redInfo.setLinkAgency(col_agency);
					redInfo.setLinkBeneficiaryNos(Integer.parseInt(totalParticipants));
					redInfo.setLinkBeneficiary(beneficiaryMOU);
					redInfo.setLinknameId(Integer.parseInt(colName));
					redInfo.setLinkNature(linkageNature);

					redInfo.setMakerUserId(maker_id);
					redInfo.setInstituteId(inst_id);
					redInfo.setYearId(acYearId);

					redInfo.setDelStatus(1);
					redInfo.setIsActive(1);
					redInfo.setExInt1(1);
					redInfo.setExInt2(1);
					redInfo.setExVar1("NA");
					redInfo.setExVar2("NA");

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					redInfo.setMakerDatetime(curDateTime);

					InstituteLinkage editInst = rest.postForObject(Constants.url + "saveInstituteColLinkage", redInfo,
							InstituteLinkage.class);

				} else {

					//System.out.println("in edit InstituteLinkage");

					//System.out.println("link_id" + link_id);
					map.add("linkId", link_id);

					InstituteLinkage redInfo1 = rest.postForObject(Constants.url + "getInstLinkageByLinkId", map,
							InstituteLinkage.class);

					redInfo1.setLinkAgency(col_agency);
					redInfo1.setLinkBeneficiaryNos(Integer.parseInt(totalParticipants));
					redInfo1.setLinkBeneficiary(beneficiaryMOU);
					redInfo1.setLinknameId(Integer.parseInt(colName));
					redInfo1.setLinkNature(linkageNature);

					redInfo1.setYearId(acYearId);

					redInfo1.setMakerUserId(maker_id);

					redInfo1.setInstituteId(inst_id);

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					redInfo1.setMakerDatetime(curDateTime);

					InstituteLinkage editInst = rest.postForObject(Constants.url + "saveInstituteColLinkage", redInfo1,
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
			System.err.println("Exce in save redInfo  " + e.getMessage());
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
					//System.out.println("id are" + linkIds);

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

				//System.out.println("colList list is" + coltList.toString());
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

	@RequestMapping(value = "/insertLinkageMaster", method = RequestMethod.POST)
	public String insertLinkageMaster(HttpServletRequest request, HttpServletResponse response) {
		String returnString = new String();
		try {

			HttpSession session = request.getSession();
			List<ModuleJson> newModuleList = (List) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("insertLinkageMaster", "showMasterCollaborationLinkages", "0", "1",
					"0", "0", newModuleList);

			//System.out.println(view);

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

					//System.out.println("in edit InstituteLinkage");

					//System.out.println("linkage_id" + linkage_id);
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

				//System.out.println("colList list is" + coltList.toString());
				model.addObject("colList", coltList);

				////////////////

				int edit_linkage_id = Integer.parseInt(request.getParameter("edit_linkage_id"));
				//System.out.println("edit_link_id id is" + edit_linkage_id);

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
			System.err.println("Exce in showEditredInforarian/{instId}  " + e.getMessage());
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
					//System.out.println("id are" + "linknameIds");

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

				model.addObject("title", "Annual Maintenance(AMC)");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int inst_id = (int) session.getAttribute("instituteId");
				int acYearId = (int) session.getAttribute("acYearId");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);
				map.add("yearId", acYearId);

				InstituteAMC[] instArray = rest.postForObject(Constants.url + "getAllInstAMCByInstituteId", map,
						InstituteAMC[].class);
				List<InstituteAMC> amcList = new ArrayList<>(Arrays.asList(instArray));

				//System.out.println("amcList list is" + amcList.toString());
				model.addObject("amcList", amcList);

				Info add = AccessControll.checkAccess("showAMC", "showAMC", "0", "1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showAMC", "showAMC", "0", "0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showAMC", "showAMC", "0", "0", "0", "1", newModuleList);

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

				model.addObject("title", "Add Annual Maintenance(AMC)");

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
				//System.out.println("edit_amc_id id is" + edit_amc_id);

				model.addObject("title", " Edit Annual Maintenance(AMC)");

				map.add("amcId", edit_amc_id);

				InstituteAMC editInst = rest.postForObject(Constants.url + "getInstAMCByAmcId", map,
						InstituteAMC.class);

				model.addObject("editInst", editInst);

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditredInforarian/{instId}  " + e.getMessage());
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
					//System.out.println("id are" + "amcIds");

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

			//System.out.println(view);

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

				InstituteAMC redInfo = new InstituteAMC();

				if (amc_id == 0) {

					redInfo.setAmcCompany(amcCompany);
					redInfo.setAmcExpenditure(amcExpenditure);
					redInfo.setAmcRemarks(amcRemarks);
					redInfo.setAmcTitle(amcTitle);

					redInfo.setMakerUserId(maker_id);
					redInfo.setInstituteId(inst_id);
					redInfo.setYearId(acYearId);

					redInfo.setDelStatus(1);
					redInfo.setIsActive(1);
					redInfo.setExInt1(1);
					redInfo.setExInt2(1);
					redInfo.setExVar1("NA");
					redInfo.setExVar2("NA");

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					redInfo.setMakerDatetime(curDateTime);

					InstituteAMC linkMasterInsertRes = rest.postForObject(Constants.url + "saveInstituteAMC", redInfo,
							InstituteAMC.class);

					System.err.println("linkMasterInsertRes " + linkMasterInsertRes.toString());

				} else {

					//System.out.println("in edit InstituteLinkage");

					//System.out.println("amc_id" + amc_id);
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					map.add("amcId", amc_id);

					InstituteAMC redInfo1 = rest.postForObject(Constants.url + "getInstAMCByAmcId", map,
							InstituteAMC.class);

					redInfo1.setAmcCompany(amcCompany);
					redInfo1.setAmcExpenditure(amcExpenditure);
					redInfo1.setAmcRemarks(amcRemarks);
					redInfo1.setAmcTitle(amcTitle);

					redInfo1.setMakerUserId(maker_id);
					redInfo1.setInstituteId(inst_id);
					redInfo1.setYearId(acYearId);

					InstituteAMC linkMasterInsertRes = rest.postForObject(Constants.url + "saveInstituteAMC", redInfo1,
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

				model.addObject("title", "Best Practices");

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int inst_id = (int) session.getAttribute("instituteId");
				int acYearId = (int) session.getAttribute("acYearId");

				map = new LinkedMultiValueMap<String, Object>();
				map.add("instId", inst_id);
				map.add("yearId", acYearId);

				InstituteBestPractices[] instArray = rest.postForObject(
						Constants.url + "getAllInstBestPracticesByInstituteId", map, InstituteBestPractices[].class);
				List<InstituteBestPractices> pracList = new ArrayList<>(Arrays.asList(instArray));

				//System.out.println("pracList list is" + pracList.toString());
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

				model.addObject("title", "Add Best Practices");
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

			//System.out.println(view);

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

				InstituteBestPractices redInfo = new InstituteBestPractices();

				if (prac_id == 0) {

					redInfo.setPracticesBeneficiary(practices_beneficiary);
					redInfo.setPracticesEffectiveFrom(DateConvertor.convertToYMD(practices_effective_from));
					redInfo.setPracticesName(practices_name);

					redInfo.setMakerUserId(maker_id);
					redInfo.setInstituteId(inst_id);
					redInfo.setYearId(acYearId);

					redInfo.setDelStatus(1);
					redInfo.setIsActive(1);
					redInfo.setExInt1(1);
					redInfo.setExInt2(1);
					redInfo.setExVar1("NA");
					redInfo.setExVar2("NA");

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

					String curDate = dateFormatStr.format(new Date());

					redInfo.setMakerDatetime(curDateTime);

					InstituteBestPractices linkMasterInsertRes = rest.postForObject(
							Constants.url + "saveInstituteBestPractices", redInfo, InstituteBestPractices.class);

					System.err.println("linkMasterInsertRes " + linkMasterInsertRes.toString());

				} else {

					//System.out.println("in edit InstituteLinkage");

					//System.out.println("prac_id" + prac_id);
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
					map.add("practicesId", prac_id);

					InstituteBestPractices redInfo1 = rest.postForObject(Constants.url + "getInstBestPracByPracId", map,
							InstituteBestPractices.class);

					redInfo1.setPracticesBeneficiary(practices_beneficiary);
					redInfo1.setPracticesEffectiveFrom(DateConvertor.convertToYMD(practices_effective_from));
					redInfo1.setPracticesName(practices_name);

					redInfo1.setMakerUserId(maker_id);
					redInfo1.setInstituteId(inst_id);
					redInfo1.setYearId(acYearId);

					InstituteBestPractices linkMasterInsertRes = rest.postForObject(
							Constants.url + "saveInstituteBestPractices", redInfo1, InstituteBestPractices.class);

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
				//System.out.println("edit_prac_id id is" + edit_prac_id);

				model.addObject("title", " Edit Best Practices");

				map.add("practicesId", edit_prac_id);

				InstituteBestPractices editInst = rest.postForObject(Constants.url + "getInstBestPracByPracId", map,
						InstituteBestPractices.class);

				model.addObject("editInst", editInst);

				model.addObject("date", DateConvertor.convertToDMY(editInst.getPracticesEffectiveFrom()));

			}

		} catch (Exception e) {
			System.err.println("Exce in showEditredInforarian/{instId}  " + e.getMessage());
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
					//System.out.println("id are" + "amcIds");

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
	/*********************************************Redressed Student Grievance********************************************/

		@RequestMapping(value = "/showRedressdeStudGrievnce", method = RequestMethod.GET)
		public ModelAndView showRedressdeStudGrievnce(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = null;
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			try {

				Info view = AccessControll.checkAccess("showRedressdeStudGrievnce", "showRedressdeStudGrievnce", "1", "0", "0", "0",
						newModuleList);

				if (view.isError() == true) {

					model = new ModelAndView("accessDenied");

				} else {
					model = new ModelAndView("instituteInfo/IQAC/showStudGrievanceList");

					model.addObject("title", "Redressed of Student Grievances");

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

					int inst_id = (int) session.getAttribute("instituteId");
					int acYearId = (int) session.getAttribute("acYearId");

					map = new LinkedMultiValueMap<String, Object>();
					map.add("instId", inst_id);
					map.add("yearId", acYearId);
					RedressedStudGrievance redressed = new RedressedStudGrievance();
				
					RedressedStudGrievance[] studGrievArray = rest.postForObject( Constants.url + "getAllStudGrievByInstituteIdAndAcademicYear", map, RedressedStudGrievance[].class);
				  List<RedressedStudGrievance> studGrievlist = new ArrayList<>(Arrays.asList(studGrievArray));
				 
				 //System.out.println("Stud Griev list="+studGrievlist);
				 model.addObject("studGrievlist", studGrievlist);
				 
					Info add = AccessControll.checkAccess("showRedressdeStudGrievnce", "showRedressdeStudGrievnce", "0", "1", "0", "0",
							newModuleList);
					Info edit = AccessControll.checkAccess("showRedressdeStudGrievnce", "showRedressdeStudGrievnce", "0", "0", "1", "0",
							newModuleList);
					Info delete = AccessControll.checkAccess("showRedressdeStudGrievnce", "showRedressdeStudGrievnce", "0", "0", "0", "1",
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
		
		@RequestMapping(value = "/addRedressdeStudGrievnceInfo", method = RequestMethod.GET)
		public ModelAndView addRedressdeStudGrievnce(HttpServletRequest request, HttpServletResponse response) {

			ModelAndView model = null;

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			try {

				Info view = AccessControll.checkAccess("addRedressdeStudGrievnceInfo", "showRedressdeStudGrievnce", "0", "1", "0", "0",
						newModuleList);

				if (view.isError() == true) {

					model = new ModelAndView("accessDenied");

				} else {

					model = new ModelAndView("instituteInfo/IQAC/addRedressedStudGrievnce");

					model.addObject("title", "Add Redressed of Student Grievances");
					RedressedStudGrievance redressed = new RedressedStudGrievance();
					model.addObject("redressed", redressed);
				}

			} catch (Exception e) {

				e.printStackTrace();

			}

			return model;

		}
		
		@RequestMapping(value = "/insertStudGrievance", method = RequestMethod.POST)
		public String insertStudGrievance(HttpServletRequest request, HttpServletResponse response) {
			String returnString = new String();
			try {

				HttpSession session = request.getSession();
				List<ModuleJson> newModuleList = (List) session.getAttribute("newModuleList");
				Info view = AccessControll.checkAccess("insertStudGrievance", "showRedressdeStudGrievnce", "0", "1", "0", "0",
						newModuleList);

				//System.out.println(view);

				if (view.isError() == false) {
					LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");

					System.err.println("Inside insertJournal method");

					int inst_id = (int) session.getAttribute("instituteId");

					int maker_id = (int) session.getAttribute("userId");
					int acYearId = (int) session.getAttribute("acYearId");

					

				
					RedressedStudGrievance redInfo = new RedressedStudGrievance();

					

						redInfo.setRedrsStudGrvncId(Integer.parseInt(request.getParameter("stud_griev_id")));
						redInfo.setStudGrievnce(request.getParameter("stud_griev"));
						redInfo.setIsTransparent(Integer.parseInt(request.getParameter("isTrans")));
						redInfo.setNoTrnsprntGrievnceAppeld(Integer.parseInt(request.getParameter("trans_grivnc")));
						redInfo.setNoTrnsprntRedrsed(Integer.parseInt(request.getParameter("trans_redressed")));
						redInfo.setIsTimeBound(Integer.parseInt(request.getParameter("isTime")));
						redInfo.setNoTimeGrievnceAppeld(Integer.parseInt(request.getParameter("time_griev")));
						redInfo.setNoTimeRedrsed(Integer.parseInt(request.getParameter("time_redress")));
						redInfo.setIsEfficient(Integer.parseInt(request.getParameter("isEfcint")));
						redInfo.setNoEfficntGrievnceAppeld(Integer.parseInt(request.getParameter("effGriev")));
						redInfo.setNoEfficntRedrsed(Integer.parseInt(request.getParameter("eff_redress")));
						redInfo.setInstId(inst_id);
						redInfo.setAcadYearId(acYearId);
						redInfo.setMakerEnterDatetime(curDateTime);
						redInfo.setMakerUserId(maker_id);
						redInfo.setDelStatus(1);
						redInfo.setIsActive(1);
						redInfo.setExInt1(0);
						redInfo.setExInt2(0);
						redInfo.setExVar1("NA");
						redInfo.setExVar2("NA");

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar cal = Calendar.getInstance();

						String curDateTime = dateFormat.format(cal.getTime());

						DateFormat dateFormatStr = new SimpleDateFormat("yyyy-MM-dd");

						String curDate = dateFormatStr.format(new Date());

						RedressedStudGrievance studGrivInfo = rest.postForObject(
								Constants.url + "saveStudGrievance", redInfo, RedressedStudGrievance.class);

						System.err.println("Student Grievance " + studGrivInfo.toString());
						
						returnString="redirect:/showRedressdeStudGrievnce";
					
				} else {

					returnString = "redirect:/accessDenied";

				}
			}

			catch (Exception e) {
				System.err.println("EXCE in insertStudGrievance " + e.getMessage());
				e.printStackTrace();

			}
			return returnString;

		}
		
		@RequestMapping(value = "/editStudGrievance/{studGrievancId}", method = RequestMethod.GET)
		public ModelAndView editStudGrievance(HttpServletRequest request, HttpServletResponse response,@PathVariable("studGrievancId") int studGrievancId) {

			ModelAndView model = null;

			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			try {

				Info view = AccessControll.checkAccess("editStudGrievance/{studGrievancId}", "showRedressdeStudGrievnce", "0", "0", "1", "0",
						newModuleList);

				if (view.isError() == true) {

					model = new ModelAndView("accessDenied");

				} else {

					model = new ModelAndView("instituteInfo/IQAC/addRedressedStudGrievnce");

					model.addObject("title", "Edit Redressed of Student Grievances");
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					
					map.add("studGrievancId", studGrievancId);
					RedressedStudGrievance redressed = rest.postForObject(Constants.url+"/getStudGrievanceById", map, RedressedStudGrievance.class);
					//System.out.println(redressed);
					model.addObject("redressed", redressed);
				}

			} catch (Exception e) {

				e.printStackTrace();

			}

			return model;

		}
		
		@RequestMapping(value = "/deleteStudGrievance/{studGrievancId}", method = RequestMethod.GET)
		public String deleteStudGrievance(@PathVariable("studGrievancId") int studGrievancId, HttpServletRequest request,
				HttpServletResponse response) {
			try {
				ModelAndView model = null;

				HttpSession session = request.getSession();
				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
				Info view = AccessControll.checkAccess("deleteStudGrievance/{studGrievancId}", "showRedressdeStudGrievnce", "0", "0", "0", "1",
						newModuleList);

				if (view.isError() == true) {

					model = new ModelAndView("accessDenied");

				} else {
					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

					map.add("studGrievancId", studGrievancId);

					RedressedStudGrievance delStud = rest.postForObject(Constants.url + "/deleteStudGrievanceById", map,
							RedressedStudGrievance.class);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/showRedressdeStudGrievnce";

		}
		
		@RequestMapping(value = "/deleteSelStudGrievance/{grievanceId}", method = RequestMethod.GET)
		public String deleteSelStudGrievance(HttpServletRequest request, HttpServletResponse response, @PathVariable int grievanceId) {
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
					if (grievanceId == 0) {

						System.err.println("Multiple records delete ");
						String[] grievanceIds = request.getParameterValues("grievanceId");
						//System.out.println("id are" + "amcIds");

						StringBuilder sb = new StringBuilder();

						for (int i = 0; i < grievanceIds.length; i++) {
							sb = sb.append(grievanceIds[i] + ",");

						}
						String grievanceIdList = sb.toString();
						grievanceIdList = grievanceIdList.substring(0, grievanceIdList.length() - 1);

						map.add("grievanceIdList", grievanceIdList);
					} else {

						System.err.println("Single Record delete ");
						map.add("grievanceIdList", grievanceId);
					}

					Info errMsg = rest.postForObject(Constants.url + "deleteStudentGrievance", map, Info.class);

					a = "redirect:/showRedressdeStudGrievnce";

				}

			} catch (Exception e) {

				System.err.println(" Exception In deleteSelStudGrievance at Master Contr " + e.getMessage());

				e.printStackTrace();

			}
			return a;
		}
}
