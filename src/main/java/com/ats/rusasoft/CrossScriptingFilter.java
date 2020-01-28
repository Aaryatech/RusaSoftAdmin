package com.ats.rusasoft;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
//https://jeevanpatil.wordpress.com/2011/07/22/prevention_of_xss/
public class CrossScriptingFilter implements Filter  {
	
	
    private FilterConfig filterConfig;

	public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
    	//System.err.println("In CSF");
    	//logger.info("Inlter CrossScriptingFilter  ...............");
        chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);
      //  logger.info("Outlter CrossScriptingFilter ...............");
    }
	
	
}
