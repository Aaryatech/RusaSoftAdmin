package com.ats.rusasoft;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ats.rusasoft.model.LoginResponse;


public class CheckUserInterceptor extends HandlerInterceptorAdapter {

   
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws IOException {

    	
    	    	
    	HttpSession session = request.getSession();

        String path = request.getRequestURI().substring(request.getContextPath().length());
     
      
		if(path.startsWith("/pdf") || path.startsWith("/captcha")) {
			return true;
		}
        try{
      	  String resourcesPath=path.substring(1, 4);
 

       if(resourcesPath.equalsIgnoreCase("res")){
     

      	 return true;
       }
       }catch (Exception e) {
			// TODO: handle exception
		}
   
    	
       
         
         if( ! path.equalsIgnoreCase("/sessionTimeOut") || path.startsWith("/resources")) {
        	 

				LoginResponse userObj  = null;
         try {
        	 
        	 userObj = (LoginResponse) session.getAttribute("userObj");
        	
        	 
         }catch (Exception e) {
			// TODO: handle exception
        	 
     
        	 
		}
         
         
         try {
         if(request.getServletPath().equals("/") || request.getServletPath().equals("/loginProcess") ||request.getServletPath().equals("/logout") ||request.getServletPath().equals("/login")
        		 ||request.getServletPath().equals("/showInstituteRegistrationForm")
        		 || request.getServletPath().equals("/insertInstituteDemo")
        		 || request.getServletPath().equals("/showOtpPage")
        		 || request.getServletPath().equals("/verifyOtpAndRegisterInstitute")
        		 || request.getServletPath().equals("/reGenOtp") || request.getServletPath().equals("/showforgotPassForm")
        		 || request.getServletPath().equals("/forgotPas")
        		 || request.getServletPath().equals("/OTPVerificationByContact")
        		 || request.getServletPath().equals("/OTPVerification")
        		 || request.getServletPath().equals("/changePassForm")
        		 || request.getServletPath().equals("/getInstituteMasterByAishe")
        		 || request.getServletPath().equals("/checkUniqueField")
        		 || request.getServletPath().equals("/reGenOtp1") || request.getServletPath().equals("/showOtpPageForChangePrinci") || request.getServletPath().equals("/verifyOtpAndChangePrincipal")   || request.getServletPath().equals("/insertChangePrinciple") || request.getServletPath().equals("/showChangePrincipalForm") || request.getServletPath().equals("/getInstituteMasterByAisheforPrincipal") || request.getServletPath().equals("/getInstituteMasterByAisheforPrincipalChange") || request.getServletPath().equals("/reGenOtpForChangePrincipal") ){ //||request.getServletPath().equals("/logout")
        	// System.out.println("Login request");// /reGenOtp1 
             return true;
         }
         else 
         if( userObj == null ) {
        	 System.out.println("Session Expired");

         //    request.setAttribute("emassage", "login failed");                
             response.sendRedirect(request.getContextPath()+"/sessionTimeOut");

             return false;          
         }else{                
             return true;
         }    
         }catch (Exception e) {
			e.printStackTrace();
             response.sendRedirect(request.getContextPath()+"/sessionTimeOut");

             return false;   
		}
         
         }
         return true;
         
}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
		
		super.postHandle(request, response, handler, modelAndView);
	}
    
    
}