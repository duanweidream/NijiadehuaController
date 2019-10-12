package com.wooboo.dsp.system.util;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.model.SysLogger;
import com.wooboo.dsp.model.SysUser;
import com.wooboo.dsp.service.base.BaseService;
import com.wooboo.dsp.util.SpringUtil;
import com.wooboo.dsp.util.StringUtil;


public class SessionHelper {
	
	public static void remove(String name){
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute(name);;
	}
	public static void invalidate() {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		if(session!=null){
			session.invalidate();
		}
	}
	public static Integer getRoleId(){
		SysUser sysUser = getSysUser();
		return sysUser.getRoleInfo().getRoleId();
	}
	
	public static boolean isSuperUser(){
		return StringUtil.equals("super", getRoleCode());
	}
	
	public static String getRoleCode(){
		SysUser sysUser = getSysUser();
		return sysUser.getRoleInfo().getRoleCode();
	}
	
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	return  attr.getRequest();
	}

	
	public static boolean hasLogin(){
		return null!=getSysUser();
	}
	public static Object get(String key){
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		return session.getAttribute(key);
	}
	
	
    public static SysUser getSysUser(){
    	HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		Object obj = session.getAttribute(ATTSystem.SESSION_USER);
		if(null!=obj){
			if(obj.getClass().equals(SysUser.class)){
				return  (SysUser)obj;
			}	
		}
		
		
    	return null;
    }
    public static String getIp(){
    	HttpServletRequest request = getRequest();
    	return HttpHelp.getIpAddr(request);
    }
    
    
    public static Long getUserId(){
    	SysUser user = getSysUser();
    	if(null!=user){
    		return user.getId();
    	}
    	return null;
    }
    
	public static <T> T get(String key, Class<T> toType) {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();
    	HttpSession session =  request.getSession();
		return (T)((Map) session).get(key);
	}

	public static void set(String key, Object value) {
		HttpServletRequest request = getRequest();
		HttpSession session = request.getSession();
		session.setAttribute(key, value);
	}
	
	
	public static void saveLogger(SysLogger logger){
		logger.setCreateUser(SessionHelper.getSysUser().getId());
		logger.setUserName(SessionHelper.getSysUser().getUserName());
	    logger.setCreateTime(new Date());
	    logger.setIpStr(getIp());
	    BaseService commonService = SpringUtil.getBean("baseService", BaseService.class);
	    commonService.saveObject(logger);
	}
	
	
	
	
}
