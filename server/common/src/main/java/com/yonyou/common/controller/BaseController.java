package com.yonyou.common.controller;

import java.math.BigInteger;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @author jensen.chen
 *
 */
public class BaseController  implements ServletContextAware{
	

	@InitBinder
	 public void initBinder(WebDataBinder binder, WebRequest request) {
	        binder.registerCustomEditor(Date.class, 
	            new DateEditor());
	        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
	        binder.registerCustomEditor(Integer.class, null, new 
				   CustomNumberEditor(Integer.class, null, true)); 
				binder.registerCustomEditor(int.class, null, new 
				   CustomNumberEditor(Integer.class, null, true)); 
				binder.registerCustomEditor(Long.class, null, new 
				   CustomNumberEditor(Long.class, null, true)); 
				binder.registerCustomEditor(long.class, null, new 
				   CustomNumberEditor(Long.class, null, true)); 
				binder.registerCustomEditor(Float.class, new 
				   CustomNumberEditor(Float.class, true)); 
				binder.registerCustomEditor(Double.class, new 
				   CustomNumberEditor(Double.class, true)); 
				binder.registerCustomEditor(BigInteger.class, new 
				   CustomNumberEditor(BigInteger.class, true));
	
	    }
	
	public HttpSession getSession()
	{
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
								.getRequest().getSession(true);
	}
	
	public HttpServletRequest getRequest()
	{
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
								.getRequest();
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		// TODO Auto-generated method stub
		
	}
}
