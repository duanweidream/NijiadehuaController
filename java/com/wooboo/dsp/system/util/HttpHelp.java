package com.wooboo.dsp.system.util;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.context.ApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.model.SysUser;
import com.wooboo.dsp.util.ByteHelper;
import com.wooboo.dsp.util.StringUtil;


public class HttpHelp {
	private final static Logger log = Logger.getLogger(HttpHelp.class);
	public final static String IP1 = "x-forwarded-for";
	public final static String IP2 = "Proxy-Client-IP";
	public final static String IP3 = "WL-Proxy-Client-IP";
	public final static String UNKNOWN = "unknown";
	public final static String COMMA = ",";
	public static final String KEY = "dfyfhqs9";
	public static final String ALGORITHM = "DES";
	public static Long TIMES=60*60*24*7L;//cookie 
	
    public static String[] parseToken(String token){
    	
    	if (token != null) {
    	    try{
        	    byte[] cookieBytes = ByteHelper.hex2byte(token);
                byte[] decodedBytes = decryptData(cookieBytes, KEY.getBytes());
            	String decodedString = new String(decodedBytes);
            	String[] parts = decodedString.split("\\|");
            	
                return parts;
    	    }catch(Exception e){    		
    	    	e.printStackTrace();
    	    }
    	}
    	return null;
    }
	
	public static SysUser getUserInfo(String token,String ip){
    	SysUser user = new SysUser();
    	if (token != null) {
    	    try{
        	    byte[] cookieBytes = ByteHelper.hex2byte(token);
                byte[] decodedBytes = decryptData(cookieBytes, KEY.getBytes());
            	String decodedString = new String(decodedBytes);
            	//System.out.println(decodedString+" =================");
            	//String[] parts = decodedString.split("@");
            	String[] parts = decodedString.split(",");

            	user.setId(Long.parseLong(parts[0]));
            	user.setUserName(parts[1]);
            	String tip = parts[2];
            	Long second = (System.currentTimeMillis()/1000)-Long.parseLong(parts[3]);
            	
            	
            	if(StringUtil.equals(ip, tip)&&second<TIMES){
            		return user;
            	}
    	    }catch(Exception e){    		
    	    	e.printStackTrace();
    	    }
    	}
        return null;
    }
	
	
	 public static String createCookie(SysUser user,String ip){
	    	String cookie = null;
	    	try{
	        	String content = user.getId() + ","+user.getUserName()+ "," + ip + ","+ (System.currentTimeMillis() / 1000);
	        	byte[] contentBytes = content.getBytes();
	        	byte[] keyBytes = KEY.getBytes();
	            byte[] cookieBytes = encryptData(contentBytes, keyBytes);
	            cookie = ByteHelper.byte2hex(cookieBytes);
	    	}catch(Exception e){    		
	    	    e.printStackTrace();
	    	}
	        return cookie;
	    } 
	
	    public static byte[] decryptData(byte[] input, byte[] key) throws Exception { 
	        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, ALGORITHM); 
	        Cipher c1 = Cipher.getInstance(ALGORITHM); 
	        c1.init(Cipher.DECRYPT_MODE, deskey); 
	        byte[] clearByte = c1.doFinal(input);   
	        return clearByte; 
	    } 
	 
	 
	 public static byte[] encryptData(byte[] input, byte[] key) throws Exception { 
	        SecretKey deskey = new javax.crypto.spec.SecretKeySpec(key, ALGORITHM); 
	        Cipher c1 = Cipher.getInstance(ALGORITHM); 
	        c1.init(Cipher.ENCRYPT_MODE, deskey); 
	        byte[] cipherByte = c1.doFinal(input);   
	        return cipherByte;  
	    } 
	 
	 
	public static void setCharacterEncoding(String encoding) throws UnsupportedEncodingException{	
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();
		//HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding(encoding);
	}	
	
	public static String getParameter(String key){	
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();
		//HttpServletRequest request = ServletActionContext.getRequest();
		return request.getParameter(key);
	}
	
	public static void setAttribute(String key, Object value){	
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();
		//HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute(key, value);
	}
	
	public static HttpServletRequest getRequest(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();
		return request;
	}
	public static <T> T getParameter(String key, Class <T> type){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();
		
		//HttpServletRequest request = ServletActionContext.getRequest();
		String value = request.getParameter(key);
		if(value!=null){
			if(Long.class.equals(type)){
				return (T)Long.valueOf(value);
			}else if(Integer.class.equals(type)){
				return (T)Integer.valueOf(value);
			}else{
				return (T)value;
			}
		}
		return null;
	}
	
	public static String getClientHost(){ //用户IPO
		//HttpServletRequest request = ServletActionContext.getRequest();
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();
		return request.getRemoteHost();
	}
	
	public static Cookie [] getCookies(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();
		Cookie [] cookies = request.getCookies();
		return cookies;
	}
	
	public static Cookie getCookie(String cookieName){
		Cookie [] cookies = getCookies();
		if(cookies!=null){
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if(cookieName.equals(cookie.getName())){
					return cookie;
				}
			}
		}
		return null;
	}
	
	public static String getCookieValue(String cookieName){
		Cookie cookie = getCookie(cookieName);
		if(cookie!=null){
			String cookieStr = cookie.getValue();
			if(cookieStr!=null){
				return EscapeUtil.unescape(cookieStr);
			}
		}
		return null;
	}
	public static Long getCookieMobileId(String cookieName){
		String mobileId = HttpHelp.getCookieValue(cookieName);
		if(!StringUtil.isEmpty(mobileId)){
			try{
				return  new Long(mobileId);
			}catch(Exception e){
				log.equals("home mothod error : Cann't  Convert Long [" + mobileId + "]");
				return  null;
			}
		}
		return null;
	}
	public static String getCookieMarketCode(String marketCode){
		return HttpHelp.getCookieValue(marketCode);
	}
	
	public static void addCookie(String domain, int maxAge, String path, String cookieName, String cookieValue){
	    HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		Cookie cookie = new Cookie(cookieName, cookieValue);//用户ID 
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		if(domain!=null){
			cookie.setDomain(domain);
		}
		response.addCookie(cookie);
	}
	
	public static void addCookie( HttpServletResponse response,String domain, int maxAge, String path, String cookieName, String cookieValue){
		Cookie cookie = new Cookie(cookieName, cookieValue);//用户ID 
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		if(domain!=null){
			cookie.setDomain(domain);
		}
		response.addCookie(cookie);
	}
	public static void addJsonCookie(String domain, int maxAge, String path, String cookieName, String cookieValue)
	{
		HttpServletResponse response = ((ServletWebRequest)RequestContextHolder.getRequestAttributes()).getResponse();
		if(cookieValue!=null)
		{
			cookieValue=EscapeUtil.escape(cookieValue);
		}
		Cookie cookie = new Cookie(cookieName, cookieValue);//用户ID 
		cookie.setPath(path);
		cookie.setMaxAge(maxAge);
		if(domain!=null){
			cookie.setDomain(domain);
		}
		response.addCookie(cookie);
	}
	
	public static void removeCookie( HttpServletResponse response,String cookieName){
		
		Cookie killMyCookie = new Cookie(cookieName, null); 
		killMyCookie.setMaxAge(0); 
		killMyCookie.setPath("/"); 
		response.addCookie(killMyCookie); 
	}
	

	
	public static <T> T getBean(ServletContext context, String beanName, Class <T> type){
		T bean = null;
		ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(context);
		if(ctx!=null){
			bean  = (T)ctx.getBean(beanName);
		}
		return bean;
	}
	
	public static String  getURL() throws Exception{
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes(); 
    	HttpServletRequest request =  attr.getRequest();
		Enumeration e=(Enumeration)request.getParameterNames();
		StringBuffer sbf=new StringBuffer();
		sbf.append(request.getRequestURI());
		sbf.append("?");
	    while(e.hasMoreElements())     {   
	       String  parName=(String)e.nextElement(); 
	       String value=request.getParameter(parName); 
	       sbf.append(parName);
	       sbf.append("=");
	       sbf.append(value);
	       sbf.append("-");//特意替换,如果用&表示,则会出问题.
	    }  
	    String url=sbf.toString();
	    return url=url.substring(0, url.length()-1);
	}
	

	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader(IP1);
		if (ip == null || ip.length() == 0|| UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(IP2);
		}
		if (ip == null || ip.length() == 0|| UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(IP3);
		}
		if (ip == null || ip.length() == 0|| UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.contains(COMMA)) {
			int i = ip.indexOf(COMMA);
			ip = ip.substring(0, i);
		}
		return ip;
	}
	
	public static void main(String[] args){
		parseToken("859A58C367466D0FA5754854FDA6FD6E9FA858A026FB0AF5");
		System.out.println();
	}
	
	
	
}
