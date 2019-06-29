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
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.commons.Constants;
import com.ats.rusasoft.model.LoginResponse;
import com.ats.rusasoft.model.Quolification;
import com.ats.rusasoft.model.graph.SancIntakeStudAdmittedGraph;

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
		HttpSession session = request.getSession();
 		int instituteId = (int) session.getAttribute("instituteId");
		ModelAndView model = null;
		MultiValueMap<String, Object> map = null;
		map=new LinkedMultiValueMap<String, Object>(); 
		model = new ModelAndView("master/hodRegistration");
		map.add("instId", instituteId);
		SancIntakeStudAdmittedGraph[] quolArray = restTemplate.postForObject(Constants.url + "getGraph1", map,
				SancIntakeStudAdmittedGraph[].class);
		List<SancIntakeStudAdmittedGraph> sancIntakeAdmi = new ArrayList<>(Arrays.asList(quolArray));
		System.err.println("SancIntakeStudAdmittedGraph GC " + sancIntakeAdmi.toString());

		model.addObject("sancIntakeAdmi", sancIntakeAdmi);
		
		return sancIntakeAdmi;
	}
}
