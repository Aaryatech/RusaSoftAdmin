package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.DateConvertor;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.IqacBasicInfo;
import com.ats.rusasoft.model.Librarian;
import com.ats.rusasoft.model.accessright.ModuleJson;

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

			Info view = AccessControll.checkAccess("insertIqacBasicInfo", "showInstProf", "0", "1", "0", "0", newModuleList);

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
				a="redirect:/showInstProf";

		}

		catch (Exception e) {
			System.err.println("Exce in save lib  " + e.getMessage());
			e.printStackTrace();
		}

		return a;

	}

}
