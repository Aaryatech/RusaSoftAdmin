package com.ats.rusasoft.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ats.rusasoft.master.model.Info;

@Controller
@Scope("session")
public class DateController {
	
	
	@RequestMapping(value = "/getDateValidation", method = RequestMethod.GET)
	public @ResponseBody Info  getDateValidation(HttpServletRequest request, HttpServletResponse response) {
		Info info=new Info();
		ModelAndView model=new ModelAndView("login");
		try {
			String fromDate ="01-02-2019"; //new String(),
			String toDate ="02-02-2019";// new String();
			try {
			 fromDate=request.getParameter("fromDate");
			}catch (Exception e) {
				fromDate="03-02-2019";
			}
			
			try {
				 toDate=request.getParameter("toDate");
				}catch (Exception e) {
					toDate="02-02-2019";
				}
			System.err.println("from"+fromDate + "to " +toDate);
			DateFormat dateFor=new SimpleDateFormat("dd-MM-yyyy");
			
			Date fd=dateFor.parse(fromDate);
			Date td=dateFor.parse(toDate);
			
			
			if(fd.before(td)) {
				System.err.println("1");
				info.setError(false);
			}else {
				System.err.println("2");
				info.setError(true);
			}
			System.err.println("info " +info.toString());
			
			
			
			
			
		}catch (Exception e) {
			e.printStackTrace();
			info.setError(true);
		}
		return info;
	}
}
