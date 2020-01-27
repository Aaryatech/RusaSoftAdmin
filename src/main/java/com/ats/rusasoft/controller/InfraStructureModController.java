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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.XssEscapeUtils;
import com.ats.rusasoft.commons.AccessControll;
import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.commons.SessionKeyGen;
import com.ats.rusasoft.master.model.prodetail.AlumniDetail;
import com.ats.rusasoft.master.model.prodetail.GetAlumni;
import com.ats.rusasoft.model.EContentDevFacility;
import com.ats.rusasoft.model.Info;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.accessright.ModuleJson;
import com.ats.rusasoft.model.infra.GetInstInfraAreaInfo;
import com.ats.rusasoft.model.infra.InfraAreaName;
import com.ats.rusasoft.model.infra.InfraAreaType;
import com.ats.rusasoft.model.infra.InstInfraAreaInfo;
import com.ats.rusasoft.model.infra.ItInfrastructure;
import com.ats.rusasoft.model.infra.TInstInternetInfo;

@Controller
@Scope("session")
public class InfraStructureModController {

	@RequestMapping(value = "/showInstInfraAreawise", method = RequestMethod.GET)
	public ModelAndView showInstInfraAreawise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("infra/list_inst_infra_area");

			model.addObject("title", "Institute Infrastructure Detail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info viewAccess = AccessControll.checkAccess("showInstInfraAreawise", "showInstInfraAreawise", "1", "0",
					"0", "0", newModuleList);

			if (viewAccess.isError() == false) {

				Info addAccess = AccessControll.checkAccess("showInstInfraAreawise", "showInstInfraAreawise", "0", "1",
						"0", "0", newModuleList);

				Info editAccess = AccessControll.checkAccess("showInstInfraAreawise", "showInstInfraAreawise", "0", "0",
						"1", "0", newModuleList);

				Info deleteAccess = AccessControll.checkAccess("showInstInfraAreawise", "showInstInfraAreawise", "0",
						"0", "0", "1", newModuleList);

				model.addObject("viewAccess", viewAccess);
				if (addAccess.isError() == false)
					model.addObject("addAccess", 0);

				if (editAccess.isError() == false)
					model.addObject("editAccess", 0);

				if (deleteAccess.isError() == false)
					model.addObject("deleteAccess", 0);

				// GetAlumni

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				// map.add("yearId", session.getAttribute("acYearId"));

				InfraAreaType[] areaTypeArray = restTemplate.getForObject(Constants.url + "getInfraAreaTypeList",
						InfraAreaType[].class);

				List<InfraAreaType> areaTypeList = new ArrayList<>(Arrays.asList(areaTypeArray));

				model.addObject("areaTypeList", areaTypeList);

				GetInstInfraAreaInfo[] resArray = restTemplate.postForObject(
						Constants.url + "getInstInfraAreaInfoByInstId", map, GetInstInfraAreaInfo[].class);

				List<GetInstInfraAreaInfo> instInfraAreaList = new ArrayList<>(Arrays.asList(resArray));

				model.addObject("instInfraAreaList", instInfraAreaList);

			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showInstInfraAreawise at InfraStructure Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/showAddInstInfraAreawise", method = RequestMethod.GET)
	public ModelAndView showAddInstInfraAreawise(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("infra/add_inst_infra_area");

			model.addObject("title", "Add Institute Infrastructure Detail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info addAccess = AccessControll.checkAccess("showAddInstInfraAreawise", "showInstInfraAreawise", "0", "1",
					"0", "0", newModuleList);

			if (addAccess.isError() == false) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				InfraAreaType[] areaTypeArray = restTemplate.getForObject(Constants.url + "getInfraAreaTypeList",
						InfraAreaType[].class);

				List<InfraAreaType> areaTypeList = new ArrayList<>(Arrays.asList(areaTypeArray));

				model.addObject("areaTypeList", areaTypeList);

				/*
				 * GetInstInfraAreaInfo[] resArray = restTemplate.postForObject(Constants.url +
				 * "getInstInfraAreaInfoByInstId", map, GetInstInfraAreaInfo[].class);
				 * 
				 * List<GetInstInfraAreaInfo> instInfraAreaList = new
				 * ArrayList<>(Arrays.asList(resArray));
				 * 
				 * model.addObject("instInfraAreaList", instInfraAreaList);
				 */
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAddInstInfraAreawise at InfraStructure Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/getInfraAreaNameListByInfraAreaTypeId", method = RequestMethod.GET)
	public @ResponseBody List<InfraAreaName> getInfraAreaNameListByInfraAreaTypeId(HttpServletRequest request,
			HttpServletResponse response) {

		List<InfraAreaName> areaList = null;

		try {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();

			map.add("infraAreaTypeId", request.getParameter("infraAreaTypeId"));

			InfraAreaName[] resArray = restTemplate
					.postForObject(Constants.url + "getInfraAreaNameListByInfraAreaTypeId", map, InfraAreaName[].class);
			areaList = new ArrayList<>(Arrays.asList(resArray));

		} catch (Exception e) {
			System.err.println("Excep in /getInfraAreaNameListByInfraAreaTypeId " + e.getMessage());
			e.printStackTrace();
		}

		return areaList;

	}

	// insertInstInfraArea

	@RequestMapping(value = "/insertInstInfraArea", method = RequestMethod.POST)
	public String insertInstInfraArea(HttpServletRequest request, HttpServletResponse response) {
		System.err.println("in insert insertInstInfraArea");
		ModelAndView model = null;
		String redirect = null;
		try {
			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				RestTemplate restTemplate = new RestTemplate();

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				int instInfraAreaId = 0;// Integer.parseInt(request.getParameter("alumni_id"));
				try {
					instInfraAreaId = Integer.parseInt(request.getParameter("instInfraAreaId"));

				} catch (Exception e) {
					instInfraAreaId = 0;
				}

				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

				Info editAccess = null;
				if (instInfraAreaId == 0) {

					editAccess = AccessControll.checkAccess("insertInstInfraArea", "showInstInfraAreawise", "0", "1",
							"0", "0", newModuleList);
				} else {

					editAccess = AccessControll.checkAccess("insertInstInfraArea", "showInstInfraAreawise", "0", "0",
							"1", "0", newModuleList);
				}

				System.err.println("instInfraAreaId  " + instInfraAreaId);

				// editAccess.setError(false);

				if (editAccess.isError() == true) {
					redirect = "redirect:/accessDenied";
				} else {
					LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
					InstInfraAreaInfo instInfraAreaInfo = new InstInfraAreaInfo();

					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Calendar cal = Calendar.getInstance();

					String curDateTime = dateFormat.format(cal.getTime());

					instInfraAreaInfo.setDelStatus(1);
					instInfraAreaInfo.setIsActive(1);

					int exInt1 = 0;
					instInfraAreaInfo.setExInt1(exInt1);
					instInfraAreaInfo.setExInt2(exInt1);
					String exVar1 = "NA";
					instInfraAreaInfo.setExVar1(exVar1);
					instInfraAreaInfo.setExVar2(exVar1);
					instInfraAreaInfo.setInstId(userObj.getGetData().getUserInstituteId());// get from Session
					instInfraAreaInfo.setMakerUserId(userObj.getUserId());// get from Session
					instInfraAreaInfo.setMakerDatetime(curDateTime);
					instInfraAreaInfo.setAreaLoc(XssEscapeUtils.jsoupParse(request.getParameter("loc_of_area")));

					int areaType = Integer.parseInt(request.getParameter("area_type"));
					int areaName = Integer.parseInt(XssEscapeUtils.jsoupParse(request.getParameter("area_name")));

					if (areaName == 0) {
						instInfraAreaInfo.setAreaName(XssEscapeUtils.jsoupParse(request.getParameter("other_area")));
					} else {
						instInfraAreaInfo.setAreaName(null);
					}

					instInfraAreaInfo.setAreaSqMtr(request.getParameter("area_in_sqm"));
					instInfraAreaInfo.setInfraAreaId(areaName);
					instInfraAreaInfo.setInfraAreaTypeId(areaType);
					instInfraAreaInfo.setInstInfraAreaId(instInfraAreaId);

					InstInfraAreaInfo instInfraAreawise = restTemplate.postForObject(
							Constants.url + "saveInstInfraAreaInfo", instInfraAreaInfo, InstInfraAreaInfo.class);

					int isView = Integer.parseInt(request.getParameter("is_view"));
					if (isView == 1)
						redirect = "redirect:/showInstInfraAreawise";
					else
						redirect = "redirect:/showInstInfraAreawise";
				}
			} else {
				System.err.println("in else");
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			System.err.println("Exce in save insertInstInfraArea  " + e.getMessage());
			e.printStackTrace();
		}

		return redirect;

	}

	@RequestMapping(value = "/findByDelStatusAndIsActiveAndInstIdAndInfraAreaId", method = RequestMethod.GET)
	public @ResponseBody InstInfraAreaInfo findByDelStatusAndIsActiveAndInstIdAndInfraAreaId(HttpServletRequest request,
			HttpServletResponse response) {

		InstInfraAreaInfo resArray = new InstInfraAreaInfo();
		try {

			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();
			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			map.add("areaId", request.getParameter("areaId"));
			map.add("instId", userObj.getGetData().getUserInstituteId());

			resArray = restTemplate.postForObject(Constants.url + "findByDelStatusAndIsActiveAndInstIdAndInfraAreaId",
					map, InstInfraAreaInfo.class);

		} catch (Exception e) {
			System.err.println("Excep in /getInfraAreaNameListByInfraAreaTypeId " + e.getMessage());
			e.printStackTrace();
		}

		return resArray;

	}

	@RequestMapping(value = "/showEditInstInfraArea", method = RequestMethod.POST)
	public ModelAndView showEditInstInfraArea(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("infra/add_inst_infra_area");

			model.addObject("title", "Edit Institute Infrastructure Detail");

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			RestTemplate restTemplate = new RestTemplate();
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info editAccess = AccessControll.checkAccess("showEditInstInfraArea", "showInstInfraAreawise", "0", "0",
					"1", "0", newModuleList);

			InstInfraAreaInfo editArea = new InstInfraAreaInfo();

			if (editAccess.isError() == false) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				map.add("instId", userObj.getGetData().getUserInstituteId());

				InfraAreaType[] areaTypeArray = restTemplate.getForObject(Constants.url + "getInfraAreaTypeList",
						InfraAreaType[].class);

				List<InfraAreaType> areaTypeList = new ArrayList<>(Arrays.asList(areaTypeArray));

				model.addObject("areaTypeList", areaTypeList);

				map = new LinkedMultiValueMap<String, Object>();

				int instInfraAreaId = Integer.parseInt(request.getParameter("instInfraAreaId"));
				System.err.println("Area Id " + instInfraAreaId);

				map.add("instInfraAreaId", instInfraAreaId);
				map.add("instId", userObj.getGetData().getUserInstituteId());

				editArea = restTemplate.postForObject(
						Constants.url + "findByDelStatusAndIsActiveAndInstIdAndInstInfraAreaId", map,
						InstInfraAreaInfo.class);

				model.addObject("editArea", editArea);
				System.err.println("editArea" + editArea.toString());

				/*
				 * GetInstInfraAreaInfo[] resArray = restTemplate.postForObject(Constants.url +
				 * "getInstInfraAreaInfoByInstId", map, GetInstInfraAreaInfo[].class);
				 * 
				 * List<GetInstInfraAreaInfo> instInfraAreaList = new
				 * ArrayList<>(Arrays.asList(resArray));
				 * 
				 * model.addObject("instInfraAreaList", instInfraAreaList);
				 */
			} else {

				model = new ModelAndView("accessDenied");
			}

		} catch (Exception e) {

			System.err.println("exception In showAddInstInfraAreawise at InfraStructure Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	// deleteInfraArea

	@RequestMapping(value = "/deleteInfraArea/{instInfraAreaId}/{token}", method = RequestMethod.GET)
	public String deleteaccOff(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int instInfraAreaId, @PathVariable String token) {
		String redirect = null;
		try {
			HttpSession session = request.getSession();
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("deleteInfraArea", "showInstInfraAreawise", "0", "0", "0",
					"1", newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (instInfraAreaId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("accOffIds");
					// System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String hodIdList = sb.toString();
					hodIdList = hodIdList.substring(0, hodIdList.length() - 1);

					map.add("instInfraAreaIdList", hodIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("instInfraAreaIdList", instInfraAreaId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteInstInfraArea", map, Info.class);

				redirect = "redirect:/showInstInfraAreawise";
			}

			}else {				
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			
			System.err.println(" Exception In deleteInstInfraArea at Master Contr " + e.getMessage());
			e.printStackTrace();

		}

		return redirect;

	}
	
	@RequestMapping(value = "/deleteMultiInfraArea/{instInfraAreaId}", method = RequestMethod.GET)
	public String deleteMultiInfraArea(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int instInfraAreaId) {
		String redirect = null;
		try {
			HttpSession session = request.getSession();
			String key=(String) session.getAttribute("generatedKey");
			String token=request.getParameter("token");
			if(token.trim().equals(key.trim())) {

			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

			

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

			Info deleteAccess = AccessControll.checkAccess("deleteInfraArea", "showInstInfraAreawise", "0", "0", "0",
					"1", newModuleList);
			if (deleteAccess.isError() == true) {
				redirect = "redirect:/accessDenied";
			} else {
				if (instInfraAreaId == 0) {

					System.err.println("Multiple records delete ");
					String[] instIds = request.getParameterValues("accOffIds");
					// System.out.println("id are" + instIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < instIds.length; i++) {
						sb = sb.append(instIds[i] + ",");

					}
					String hodIdList = sb.toString();
					hodIdList = hodIdList.substring(0, hodIdList.length() - 1);

					map.add("instInfraAreaIdList", hodIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("instInfraAreaIdList", instInfraAreaId);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteInstInfraArea", map, Info.class);

				redirect = "redirect:/showInstInfraAreawise";
			}

			}else {				
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			
			System.err.println(" Exception In deleteInstInfraArea at Master Contr " + e.getMessage());
			e.printStackTrace();

		}

		return redirect;

	}

	/**********************************
	 * Infrastructure
	 *******************************************/
	RestTemplate rest = new RestTemplate();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();

	String curDateTime = dateFormat.format(cal.getTime());

	@RequestMapping(value = "/showInfrastructureForm", method = RequestMethod.GET)
	public ModelAndView showInfrastructureForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {

			model = new ModelAndView("master/infrastructure");

			model.addObject("title", "Infrastructure Form");

		} catch (Exception e) {

			System.err.println("exception In showInfrastructureForm at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/addEContentDev", method = RequestMethod.GET)
	public ModelAndView addEContentDev(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		EContentDevFacility eCont = new EContentDevFacility();
		try {
			Info view = AccessControll.checkAccess("addEContentDev", "econtentDevelopment", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("infrastructure/econtentDev");
				model.addObject("content", eCont);
				model.addObject("title", "Add E-Content Development");
			}
		} catch (Exception e) {

			System.err.println("exception In addEContentDev at Master Contr" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/econtentDevelopment", method = RequestMethod.GET)
	public ModelAndView showlibBookPurchased(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("infrastructure/e_contentDevList");
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("econtentDevelopment", "econtentDevelopment", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int acadYear = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("instituteId", instituteId);
				map.add("acadYear", acadYear);

				EContentDevFacility[] econtentarr = rest.postForObject(Constants.url + "/showEComtentDevFaclity", map,
						EContentDevFacility[].class);
				List<EContentDevFacility> contentList = new ArrayList<>(Arrays.asList(econtentarr));
				System.err.println("eCont=" + contentList);
				model.addObject("contentList", contentList);
				model.addObject("title", "E-Content Development");

				Info add = AccessControll.checkAccess("econtentDevelopment", "econtentDevelopment", "0", "1", "0", "0",
						newModuleList);
				Info edit = AccessControll.checkAccess("econtentDevelopment", "econtentDevelopment", "0", "0", "1", "0",
						newModuleList);
				Info delete = AccessControll.checkAccess("econtentDevelopment", "econtentDevelopment", "0", "0", "0",
						"1", newModuleList);

				if (add.isError() == false) {
					// System.out.println(" add Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					// System.out.println(" edit Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					// System.out.println(" delete Accessable ");
					model.addObject("deleteAccess", 0);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/inserteContentFacilities", method = RequestMethod.POST)
	public String inserteContentFacilities(HttpServletRequest request, HttpServletResponse response) {
		String redirect = null;
		try {

			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int instituteId = (int) session.getAttribute("instituteId");

				int userId = (int) session.getAttribute("userId");

				EContentDevFacility eCont = new EContentDevFacility();

				eCont.setInstEContentDevFacilityId(Integer.parseInt(request.getParameter("econtentId")));
				eCont.setInstId(instituteId);
				eCont.seteContentDevFacility(request.getParameter("e_contentType"));
				eCont.setNameEcontentDevFacility(XssEscapeUtils.jsoupParse(request.getParameter("e_contentName")));
				eCont.setVideoLink(XssEscapeUtils.jsoupParse(request.getParameter("video_link")));
				eCont.setDelStatus(1);
				eCont.setIsActive(1);
				eCont.setMakerUserId(userId);
				eCont.setMakerDatetime(curDateTime);
				eCont.setExInt1(0);
				eCont.setExInt2(0);
				eCont.setExInt2(0);
				eCont.setExVar1(request.getParameter("year"));
				eCont.setExVar2("NA");
				//// System.out.println(eCont.toString());
				EContentDevFacility contents = rest.postForObject(Constants.url + "/saveEcontentDevFacilities", eCont,
						EContentDevFacility.class);
				redirect = "redirect:/econtentDevelopment";

			} else {
				System.err.println("in else");
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			e.printStackTrace();

		}

		return redirect;

	}

	@RequestMapping(value = "/editEContent/{contentId}", method = RequestMethod.GET)
	public ModelAndView editEContent(@PathVariable("contentId") int contentId, HttpServletRequest request) {

		// //System.out.println("Id:" + iqacId);

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		try {

			Info view = AccessControll.checkAccess("editEContent/{iqacId}", "econtentDevelopment", "0", "0", "1", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				map.add("contentId", contentId);
				model = new ModelAndView("infrastructure/econtentDev");
				model.addObject("title", "Edit E-Content Development");
				EContentDevFacility econtDev = rest.postForObject(Constants.url + "/getEContentDevFecilityById", map,
						EContentDevFacility.class);
				model.addObject("content", econtDev);
			}
		} catch (Exception e) {

		}
		return model;
	}

	@RequestMapping(value = "/deleteEContent/{contentId}/{token}", method = RequestMethod.GET)
	public String deleteEContent(@PathVariable("contentId") int contentId, @PathVariable("token") String token, HttpServletRequest request,
			HttpServletResponse response) {
		String a = null;
		try {
			HttpSession session = request.getSession();
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {

			
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			Info view = AccessControll.checkAccess("deleteEContent/{contentId}/{token}", "econtentDevelopment", "0", "0", "0",
					"1", newModuleList);

			if (view.isError() == true)

			{
				a = "redirect:/accessDenied";
			}

			else {
				map = new LinkedMultiValueMap<>();
				map.add("contentId", contentId);

				EContentDevFacility delContent = rest.postForObject(Constants.url + "/deleteEContentById", map,
						EContentDevFacility.class);
				a = "redirect:/econtentDevelopment";
			}

			}else {				
				a = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			e.printStackTrace();

		}
		return a;

	}

	@RequestMapping(value = "/delSlectedEContentDevFaclities/{econtent}", method = RequestMethod.GET)
	public String delSlectedPurchasedLibBooks(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int econtent) {
		HttpSession session = request.getSession();
		String a = null;
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedEContentDevFaclities/{contentId}", "econtentDevelopment", "0",
				"0", "0", "1", newModuleList);

		try {
			
			String token=request.getParameter("token");
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {

			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (econtent == 0) {

					System.err.println("Multiple records delete ");
					String[] contentIds = request.getParameterValues("econtent");
					// System.out.println("id are" + contentIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < contentIds.length; i++) {
						sb = sb.append(contentIds[i] + ",");

					}
					String contentIdsList = sb.toString();
					contentIdsList = contentIdsList.substring(0, contentIdsList.length() - 1);

					map.add("contentIdsList", contentIdsList);
				} else {

					System.err.println("Single Record delete ");
					map.add("contentIdsList", econtent);
				}

				Info errMsg = rest.postForObject(Constants.url + "deleteSelContent", map, Info.class);

				a = "redirect:/econtentDevelopment";

			}
			}else {				
				a = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			e.printStackTrace();
		}

		return a;

	}

	/***********************************
	 * It Infrastructure
	 ************************************/

	@RequestMapping(value = "/showItInfrastructure", method = RequestMethod.GET)
	public ModelAndView showItInfrastructure(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("infrastructure/itInfrastructureList");
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("econtentDevelopment", "econtentDevelopment", "1", "0", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int acadYear = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("instituteId", instituteId);
				// map.add("acadYear", acadYear);

				ItInfrastructure[] infraStructarr = rest.postForObject(Constants.url + "/showAllItInfrastructure", map,
						ItInfrastructure[].class);
				List<ItInfrastructure> infraList = new ArrayList<>(Arrays.asList(infraStructarr));
				System.err.println("It=" + infraList);
				model.addObject("infraList", infraList);
				model.addObject("title", "It Infrastructure");

				Info add = AccessControll.checkAccess("showItInfrastructure", "showItInfrastructure", "0", "1", "0",
						"0", newModuleList);
				Info edit = AccessControll.checkAccess("showItInfrastructure", "showItInfrastructure", "0", "0", "1",
						"0", newModuleList);
				Info delete = AccessControll.checkAccess("showItInfrastructure", "showItInfrastructure", "0", "0", "0",
						"1", newModuleList);

				if (add.isError() == false) {
					// System.out.println(" add Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					// System.out.println(" edit Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					// System.out.println(" delete Accessable ");
					model.addObject("deleteAccess", 0);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/addItInfrstructureInfo", method = RequestMethod.GET)
	public ModelAndView addItInfrstructureInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		ItInfrastructure itInfra = new ItInfrastructure();
		try {
			Info view = AccessControll.checkAccess("addItInfrstructureInfo", "showItInfrastructure", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("infrastructure/itInfrastructure");
				model.addObject("itInfra", itInfra);
				model.addObject("title", "Add It Infrastructure");
			}
		} catch (Exception e) {

			System.err.println("exception In showItInfrastructure" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/inserteItInfrastructure", method = RequestMethod.POST)
	public String inserteItInfrastructure(HttpServletRequest request, HttpServletResponse response) {
		String redirect =null;
		try {
			HttpSession session = request.getSession();
			String token = request.getParameter("token");
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");

				ItInfrastructure infrastur = new ItInfrastructure();

				infrastur.setInstItInfraInfoId(Integer.parseInt(request.getParameter("infraId")));
				infrastur.setInstId(instituteId);
				infrastur.setNoOfComputers(Integer.parseInt(request.getParameter("no_comp")));
				infrastur.setPurchaseDate(request.getParameter("purchase_date"));
				infrastur.setPurchaseAmt(Integer.parseInt(request.getParameter("purchase_amt")));
				infrastur.setNoOfStudUtilizing(Integer.parseInt(request.getParameter("stud_util")));
				infrastur.setDelStatus(1);
				infrastur.setIsActive(1);
				infrastur.setMakerUserId(userId);
				infrastur.setMakerDatetime(curDateTime);
				infrastur.setExInt1(0);
				infrastur.setExInt2(0);
				infrastur.setExVar1("NA");
				infrastur.setExVar2("NA");
				// System.out.println(infrastur.toString());

				ItInfrastructure infra = rest.postForObject(Constants.url + "/saveItInfrastructureInfo", infrastur,
						ItInfrastructure.class);
				redirect = "redirect:/showItInfrastructure";
			} else {
				System.err.println("in else");
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			System.err.println("exception In showItInfrastructure" + e.getMessage());
		}
		return redirect;
	}

	@RequestMapping(value = "/editItInfrastructInfo/{infraId}", method = RequestMethod.GET)
	public ModelAndView editItInfrastructInfo(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int infraId) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("addItInfrstructureInfo", "showItInfrastructure", "0", "1", "0", "0",
					newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				map.add("infraId", infraId);

				model = new ModelAndView("infrastructure/itInfrastructure");
				ItInfrastructure itInfra = rest.postForObject(Constants.url + "/getItInfraStructureById", map,
						ItInfrastructure.class);
				model.addObject("itInfra", itInfra);

				model.addObject("title", "Edit It Infrastructure");
			}
		} catch (Exception e) {

			System.err.println("exception In showItInfrastructure" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteItInfrastructInfo/{infraId}/{token}", method = RequestMethod.GET)
	public String deleteItInfrastructInfo(@PathVariable("infraId") int infraId, @PathVariable("token") String token, HttpServletRequest request) {
		String value = null;
		try {
			HttpSession session = request.getSession();
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {
			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("deleteItInfrastructInfo/{infraId}/{token}", "showItInfrastructure", "0", "0",
					"0", "1", newModuleList);
			if (view.isError() == true) {

				value = "redirect:/accessDenied";

			} else {

				// System.out.println("Id:" + infraId);

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
				map.add("infraId", infraId);
				Info itInfra = rest.postForObject(Constants.url + "/deletetInfraById", map, Info.class);

				value = "redirect:/showItInfrastructure";

			}
			SessionKeyGen.changeSessionKey(request);
			}else {				
				value = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return value;

	}

	@RequestMapping(value = "/delSlectedItInfrastructureInfo/{infraId}", method = RequestMethod.GET)
	public String delSlectedItInfrastructureInfo(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int infraId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedItInfrastructureInfo/{{infraId}}", "showItInfrastructure",
				"0", "0", "0", "1", newModuleList);

		try {
			String token=request.getParameter("token");
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (infraId == 0) {

					System.err.println("Multiple records delete ");
					String[] infraIds = request.getParameterValues("itInfraId");
					// System.out.println("id are" + infraIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < infraIds.length; i++) {
						sb = sb.append(infraIds[i] + ",");

					}
					String infraIdList = sb.toString();
					infraIdList = infraIdList.substring(0, infraIdList.length() - 1);

					map.add("infraIdList", infraIdList);
				} else {

					System.err.println("Single Record delete ");
					map.add("infraIdList", infraId);
				}

				Info errMsg = rest.postForObject(Constants.url + "delSelItInfraId", map, Info.class);

				a = "redirect:/showItInfrastructure";

			}

			}else {				
				a = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);

		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			System.err.println(" Exception In delSlectedItInfrastructureInfo" + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

	/**************************************
	 * Internet Connection
	 **********************************/
	@RequestMapping(value = "/showAllInternetConnectionInfo", method = RequestMethod.GET)
	public ModelAndView showAllInternetConnectionInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		try {
			model = new ModelAndView("infrastructure/intrernetConnectnList");
			HttpSession session = request.getSession();

			List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
			Info view = AccessControll.checkAccess("showAllInternetConnectionInfo", "showAllInternetConnectionInfo",
					"1", "0", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				int instituteId = (int) session.getAttribute("instituteId");
				int userId = (int) session.getAttribute("userId");
				int acadYear = (int) session.getAttribute("acYearId");
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("instituteId", instituteId);
				// map.add("acadYear", acadYear);

				TInstInternetInfo[] interConnectnarr = rest.postForObject(Constants.url + "/showAllInternetConnection",
						map, TInstInternetInfo[].class);
				List<TInstInternetInfo> connList = new ArrayList<>(Arrays.asList(interConnectnarr));
				System.err.println("It=" + connList);
				model.addObject("connList", connList);

				model.addObject("title", "Internet Connection");

				Info add = AccessControll.checkAccess("showAllInternetConnectionInfo", "showAllInternetConnectionInfo",
						"0", "1", "0", "0", newModuleList);
				Info edit = AccessControll.checkAccess("showAllInternetConnectionInfo", "showAllInternetConnectionInfo",
						"0", "0", "1", "0", newModuleList);
				Info delete = AccessControll.checkAccess("showAllInternetConnectionInfo",
						"showAllInternetConnectionInfo", "0", "0", "0", "1", newModuleList);

				if (add.isError() == false) {
					// System.out.println(" add Accessable ");
					model.addObject("addAccess", 0);

				}
				if (edit.isError() == false) {
					// System.out.println(" edit Accessable ");
					model.addObject("editAccess", 0);
				}
				if (delete.isError() == false) {
					// System.out.println(" delete Accessable ");
					model.addObject("deleteAccess", 0);

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;

	}

	@RequestMapping(value = "/addInternetConnectnInfo", method = RequestMethod.GET)
	public ModelAndView addInternetConnectnInfo(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
		TInstInternetInfo interConnec = new TInstInternetInfo();
		try {
			Info view = AccessControll.checkAccess("addInternetConnectnInfo", "showAllInternetConnectionInfo", "0", "1",
					"0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {

				model = new ModelAndView("infrastructure/internetConnectn");
				model.addObject("interConnec", interConnec);
				model.addObject("title", "Add Internet Connection");
			}
		} catch (Exception e) {

			System.err.println("exception In showItInfrastructure" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/inserteInternetConnecInfo", method = RequestMethod.POST)
	public String inserteInternetConnecInfo(HttpServletRequest request, HttpServletResponse response) {
		String redirect =null;
		try {
			HttpSession session = request.getSession();
			String token=request.getParameter("token");
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {

			LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			int instituteId = (int) session.getAttribute("instituteId");
			int userId = (int) session.getAttribute("userId");

			TInstInternetInfo internet = new TInstInternetInfo();

			internet.setInstInternetInfoId(Integer.parseInt(request.getParameter("internetId")));
			internet.setInstId(instituteId);
			internet.setNoOfCompWithInternetAccess(Integer.parseInt(request.getParameter("internet_access")));
			internet.setLeasedLineBandwidth(XssEscapeUtils.jsoupParse(request.getParameter("bandwidth")));
			internet.setLanConfSpeed(XssEscapeUtils.jsoupParse(request.getParameter("lan_conf")));
			internet.setIspName(XssEscapeUtils.jsoupParse(request.getParameter("isp_name")));
			internet.setPurchaseDate(request.getParameter("purchase_date"));
			internet.setDelStatus(1);
			internet.setIsActive(1);
			internet.setMakerUserId(userId);
			internet.setMakerDatetime(curDateTime);
			internet.setExInt1(0);
			internet.setExInt2(0);
			internet.setExVar1("NA");
			internet.setExVar2("NA");

			TInstInternetInfo interCon = rest.postForObject(Constants.url + "/saveNewInternetConnectionInfo", internet,
					TInstInternetInfo.class);
			
			redirect = "redirect:/showAllInternetConnectionInfo";

			}else {
				System.err.println("in else");
				redirect = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);

		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			e.printStackTrace();
		}
		return redirect;
	}

	@RequestMapping(value = "/editLanConnectionDetails/{connectionId}", method = RequestMethod.GET)
	public ModelAndView editLanConnectionDetails(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int connectionId) {

		ModelAndView model = null;
		HttpSession session = request.getSession();
		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		try {
			Info view = AccessControll.checkAccess("editLanConnectionDetails/{connectionId}",
					"showAllInternetConnectionInfo", "0", "1", "0", "0", newModuleList);

			if (view.isError() == true) {

				model = new ModelAndView("accessDenied");

			} else {
				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();

				map.add("connectionId", connectionId);
				TInstInternetInfo interConnec = rest.postForObject(Constants.url + "/getConnectionInfoById", map,
						TInstInternetInfo.class);

				model = new ModelAndView("infrastructure/internetConnectn");
				model.addObject("interConnec", interConnec);
				model.addObject("title", "Edit Internet Connection");
			}
		} catch (Exception e) {

			System.err.println("exception In editLanConnectionDetails" + e.getMessage());

			e.printStackTrace();

		}

		return model;

	}

	@RequestMapping(value = "/deleteLanConnectionDetails/{connectionId}/{token}", method = RequestMethod.GET)
	public String deleteLanConnectionDetails(@PathVariable("connectionId") int connectionId,@PathVariable("token") String token,
			HttpServletRequest request) {
		String value = null;
		try {

			HttpSession session = request.getSession();
			String key = (String) session.getAttribute("generatedKey");

			if (token.trim().equals(key.trim())) {

				List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");
				Info view = AccessControll.checkAccess("deleteLanConnectionDetails/{connectionId}/{token}",
						"showAllInternetConnectionInfo", "0", "0", "0", "1", newModuleList);
				if (view.isError() == true) {

					value = "redirect:/accessDenied";

				} else {

					// System.out.println("Id:" + connectionId);

					MultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
					map.add("connectionId", connectionId);
					Info itInfra = rest.postForObject(Constants.url + "/deletetLanConectionById", map, Info.class);

					value = "redirect:/showAllInternetConnectionInfo";

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

	@RequestMapping(value = "/delSlectedLanCompInfo/{lanInfoId}", method = RequestMethod.GET)
	public String delSlectedLanCompInfo(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int lanInfoId) {
		HttpSession session = request.getSession();
		String a = null;

		List<ModuleJson> newModuleList = (List<ModuleJson>) session.getAttribute("newModuleList");

		Info view = AccessControll.checkAccess("delSlectedItInfrastructureInfo/{infraId}",
				"showAllInternetConnectionInfo", "0", "0", "0", "1", newModuleList);

		try {
			
			String token=request.getParameter("token");
			String key=(String) session.getAttribute("generatedKey");
			
			if(token.trim().equals(key.trim())) {
			if (view.isError() == true) {

				a = "redirect:/accessDenied";

			}

			else {

				MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
				if (lanInfoId == 0) {

					System.err.println("Multiple records delete ");
					String[] lanInfoIds = request.getParameterValues("lanInfoId");
					// System.out.println("id are" + lanInfoIds);

					StringBuilder sb = new StringBuilder();

					for (int i = 0; i < lanInfoIds.length; i++) {
						sb = sb.append(lanInfoIds[i] + ",");

					}
					String lanInfoIdslList = sb.toString();
					lanInfoIdslList = lanInfoIdslList.substring(0, lanInfoIdslList.length() - 1);

					map.add("lanInfoIdslList", lanInfoIdslList);
				} else {

					System.err.println("Single Record delete ");
					map.add("lanInfoIdslList", lanInfoId);
				}

				Info errMsg = rest.postForObject(Constants.url + "delSelLanConnection", map, Info.class);

				a = "redirect:/showAllInternetConnectionInfo";

			}
			}else {				
				a = "redirect:/accessDenied";
			}
			SessionKeyGen.changeSessionKey(request);
		} catch (Exception e) {
			SessionKeyGen.changeSessionKey(request);
			System.err.println(" Exception In delSlectedLanCompInfo" + e.getMessage());

			e.printStackTrace();

		}
		return a;
	}

}
