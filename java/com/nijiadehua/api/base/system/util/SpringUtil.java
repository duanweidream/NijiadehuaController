package com.nijiadehua.api.base.system.util;

import javax.servlet.ServletContext;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.ServletContextAware;



public class SpringUtil implements ApplicationContextAware,ServletContextAware  {
	private static ApplicationContext applicationContext = null;
	public static ServletContext context;

	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringUtil.applicationContext = applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(String name, Class <T> type) {
		return (T)applicationContext.getBean(name);
	}
	
	 public static ServletContext getServletContext(){
	    	return context;
	    }
	@Override
	public void setServletContext(ServletContext context) {
		SpringUtil.context=context;
	}
}
