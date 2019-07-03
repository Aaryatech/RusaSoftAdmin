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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate; 

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.TotSancIntakeProgwise;
import com.ats.rusasoft.model.graph.BudgetGraphDash;
import com.ats.rusasoft.model.graph.BudgetResponse;
import com.ats.rusasoft.model.graph.GetTotStudentPassedAndAppearInFinYr;
import com.ats.rusasoft.model.graph.NoOfResearchPubGraphDean;
import com.ats.rusasoft.model.graph.ProgSanctnIntake;
import com.ats.rusasoft.model.graph.ProgTypStudPlacedGraph;
import com.ats.rusasoft.model.graph.SancIntakeStudAdmittedGraph;
import com.ats.rusasoft.model.graph.StudSupprtSchemGraph;
import com.ats.rusasoft.model.graph.StudpassApperaedTaughByFac;
import com.ats.rusasoft.model.graph.TeacherStudUsingLibrary; 

@Controller
@Scope("session")
public class GraphController {
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	Calendar cal = Calendar.getInstance();
	String curDateTime = dateFormat.format(cal.getTime());
	String redirect = null;
	RestTemplate restTemplate = new RestTemplate();

	@RequestMapping(value = "/getGraph", method = RequestMethod.GET)
	public @ResponseBody List<SancIntakeStudAdmittedGraph> getGraph(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<SancIntakeStudAdmittedGraph> sancIntakeAdmi = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession();
	 		int instituteId = (int) session.getAttribute("instituteId");
			 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 
		 
			map.add("instId", instituteId);
			SancIntakeStudAdmittedGraph[] quolArray = restTemplate.postForObject(Constants.url + "getGraph1", map,
					SancIntakeStudAdmittedGraph[].class);
			sancIntakeAdmi = new ArrayList<>(Arrays.asList(quolArray));
			//System.err.println("SancIntakeStudAdmittedGraph GC " + sancIntakeAdmi.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return sancIntakeAdmi;
	}
	
	@RequestMapping(value = "/getTotSancIntakeProgramwiseGraph", method = RequestMethod.GET)
	public @ResponseBody List<TotSancIntakeProgwise> getTotSancIntakeProgramwiseGraph(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<TotSancIntakeProgwise> sancIntakeAdmi = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession();
	 		int instituteId = (int) session.getAttribute("instituteId");
			 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 
		 
			map.add("instId", instituteId);
			TotSancIntakeProgwise[] quolArray = restTemplate.postForObject(Constants.url + "/getTotSancIntakeProgramwiseGraph", map,
					TotSancIntakeProgwise[].class);
			sancIntakeAdmi = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("SancIntakeStudAdmittedGraph GC " + sancIntakeAdmi.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return sancIntakeAdmi;
	}
	
	@RequestMapping(value = "/getAllStudSupprtSchemGraph", method = RequestMethod.GET)
	public @ResponseBody List<StudSupprtSchemGraph> getAllStudSupprtSchemGraph(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<StudSupprtSchemGraph> sancIntakeAdmi = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession();
	 		int instituteId = (int) session.getAttribute("instituteId");
			 
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 
		 
			map.add("instId", instituteId);
			StudSupprtSchemGraph[] quolArray = restTemplate.postForObject(Constants.url + "/getAllStudSupprtSchemGraph", map,
					StudSupprtSchemGraph[].class);
			sancIntakeAdmi = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("SancIntakeStudAdmittedGraph GC " + sancIntakeAdmi.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return sancIntakeAdmi;
	}
	
	@RequestMapping(value = "/getTotStudentPassedAndAppearInFinYrGraphForHod", method = RequestMethod.GET)
	public @ResponseBody List<GetTotStudentPassedAndAppearInFinYr> getTotStudentPassedAndAppearInFinYrGraphForHod(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<GetTotStudentPassedAndAppearInFinYr> list = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession();
	 		int instituteId = (int) session.getAttribute("instituteId");
	 		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			 
		 
			map.add("instId", instituteId);
			map.add("facultyId", userObj.getUserId());
			GetTotStudentPassedAndAppearInFinYr[] quolArray = restTemplate.postForObject(Constants.url + "/getTotStudentPassedAndAppearInFinYrGraphForHod", map,
					GetTotStudentPassedAndAppearInFinYr[].class);
			list = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("GetTotStudentPassedAndAppearInFinYr GC " + list.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return list;
	}
	
	@RequestMapping(value = "/sanctioinalIntakeandNostudentAdmitedproramwise", method = RequestMethod.GET)
	public @ResponseBody List<ProgSanctnIntake> sanctioinalIntakeandNostudentAdmitedproramwise(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<ProgSanctnIntake> list = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession(); 
	 		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			  
			map.add("makerUserId", userObj.getUserId());
			ProgSanctnIntake[] quolArray = restTemplate.postForObject(Constants.url + "/getAllProgSanctnIntakeGraph", map,
					ProgSanctnIntake[].class);
			list = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("ProgSanctnIntake GC " + list.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return list;
	}
	
	@RequestMapping(value = "/getAllProgTypStudPlacedGraph", method = RequestMethod.GET)
	public @ResponseBody List<ProgTypStudPlacedGraph> getAllProgTypStudPlacedGraph(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<ProgTypStudPlacedGraph> list = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession(); 
	 		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			  
			map.add("makerUserId", userObj.getUserId());
			ProgTypStudPlacedGraph[] quolArray = restTemplate.postForObject(Constants.url + "/getAllProgTypStudPlacedGraph", map,
					ProgTypStudPlacedGraph[].class);
			list = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("ProgTypStudPlacedGraph GC " + list.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return list;
	}
	
	@RequestMapping(value = "/getGraphForNoofTeacherStudUsingLib", method = RequestMethod.GET)
	public @ResponseBody List<TeacherStudUsingLibrary> getGraphForNoofTeacherStudUsingLib(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<TeacherStudUsingLibrary> list = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession(); 
			int instituteId = (int) session.getAttribute("instituteId");
	 		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			  
			map.add("instId", instituteId);
			TeacherStudUsingLibrary[] quolArray = restTemplate.postForObject(Constants.url + "/getGraphForNoofTeacherStudUsingLib", map,
					TeacherStudUsingLibrary[].class);
			list = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("TeacherStudUsingLibrary GC " + list.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return list;
	}
	
	@RequestMapping(value = "/getStudpassAppearedTaughByFacGraph", method = RequestMethod.GET)
	public @ResponseBody List<StudpassApperaedTaughByFac> getStudpassAppearedTaughByFacGraph(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<StudpassApperaedTaughByFac> list = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession(); 
			int instituteId = (int) session.getAttribute("instituteId");
	 		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
			map.add("facultyId", userObj.getUserId());
			map.add("instId", instituteId);
			StudpassApperaedTaughByFac[] quolArray = restTemplate.postForObject(Constants.url + "/getStudpassAppearedTaughByFacGraph", map,
					StudpassApperaedTaughByFac[].class);
			list = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("StudpassApperaedTaughByFac GC " + list.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return list;
	}
	
	@RequestMapping(value = "/getNoOfResearchPubGraphDean", method = RequestMethod.GET)
	public @ResponseBody List<NoOfResearchPubGraphDean> getNoOfResearchPubGraphDean(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<NoOfResearchPubGraphDean> list = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession(); 
			int instituteId = (int) session.getAttribute("instituteId");
	 		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
			map.add("instId", instituteId);
			NoOfResearchPubGraphDean[] quolArray = restTemplate.postForObject(Constants.url + "/getNoOfResearchPubGraphDean", map,
					NoOfResearchPubGraphDean[].class);
			list = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("NoOfResearchPubGraphDean GC " + list.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return list;
	}
	
	@RequestMapping(value = "/getBudgetInfrastructureDetail", method = RequestMethod.GET)
	public @ResponseBody List<BudgetGraphDash> getBudgetInfrastructureDetail(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<BudgetGraphDash> list = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession(); 
			int instituteId = (int) session.getAttribute("instituteId");
	 		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
			map.add("instId", instituteId);
			BudgetGraphDash[] quolArray = restTemplate.postForObject(Constants.url + "/getBudgetInfrastructureDetail", map,
					BudgetGraphDash[].class);
			list = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("BudgetGraphDash GC " + list.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return list;
	}
	
	@RequestMapping(value = "/getBudgetInfrastructureDetailCurr", method = RequestMethod.GET)
	public @ResponseBody List<BudgetGraphDash> getBudgetInfrastructureDetailCurr(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<BudgetGraphDash> list = new ArrayList<>();
		
		try {
			
			HttpSession session = request.getSession(); 
			int instituteId = (int) session.getAttribute("instituteId");
	 		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
			map.add("instId", instituteId);
			BudgetGraphDash[] quolArray = restTemplate.postForObject(Constants.url + "/getBudgetInfrastructureDetailCurr", map,
					BudgetGraphDash[].class);
			list = new ArrayList<>(Arrays.asList(quolArray));
			
			//System.err.println("BudgetGraphDash GC " + list.toString());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return list;
	}
	
	@RequestMapping(value = "/getAllBugetsGraph", method = RequestMethod.GET)
	public @ResponseBody BudgetResponse getAllBugetsGraph(HttpServletRequest request, HttpServletResponse response) {
		
		
		BudgetResponse budgetResponse = new BudgetResponse();
		
		try {
			
			HttpSession session = request.getSession(); 
			int instituteId = (int) session.getAttribute("instituteId");
	 		LoginResponse userObj = (LoginResponse) session.getAttribute("userObj");
			MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(); 
			map.add("instId", instituteId);
			budgetResponse = restTemplate.postForObject(Constants.url + "/getAllBugetsGraph", map,
					BudgetResponse.class);
			 
			
			System.err.println("budgetResponse GC " + budgetResponse.getInfraRes());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
 
		return budgetResponse;
	}
}
