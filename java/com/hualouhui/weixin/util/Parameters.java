package com.hualouhui.weixin.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Parameters {
    
    public static String WEB_BASE_URL = Config.getValue("image.url");

    public String getCookieString(){
    	return CookieHelper.FIELD_NAME+"="+getMyCookieValue();
    }
    
    public void setMyCookieValue(String myCookieValue) {
        this.myCookieValue = myCookieValue;
    }
    
    public String getMyCookieValue() {
        return this.myCookieValue;
    }
    
    public void setUploadException(Exception e) {
        uploadException = e;
    }
    
    public boolean hasUploadException() {
        return uploadException != null;
    }
    
    public void addFile(String key, String uri, byte[] fileBinary) {
        fileData.put(key, fileBinary);
        files.put(key, uri);
    }
    
    public void addField(String key, String value) {
        fields.put(key, value);
    }
    public void addArray(String key,String[] values){
    	arrays.put(key, values);
    }
    public Set<String> getFileKeys() {
        return files.keySet();
    }

    public String getFileUri(String key) {
        return files.get(key);
    }

    public String getFileUrl(String key) {
    	String uri = getFileUri(key);
        return uri!=null?WEB_BASE_URL+uri:null;
    }

    public byte[] getFileData(String key) {
        return fileData.get(key);
    }
        
    public Set<String> getFieldKeys() {
        return fields.keySet();
    }

    public Map<String, String> getFieldKeyValues() {
        return new HashMap<String, String>(fields);
    }
    public Map<String, String[]> getFieldsKeyValues() {
        return new HashMap<String, String[]>(arrays);
    }

    public String getFieldValue(String key) {
        return fields.get(key);
    }

    public Set<String> getFileUrls() {
        return new HashSet<String>(files.values());
    }

    public String toString() {
        return files.toString()+","+fields.toString();
    }

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getCookieValue(String name){
		Cookie[] cookies = this.request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (StringUtil.equals(name, cookie.getName())) {
		        try {
					return URLDecoder.decode(cookie.getValue(),"utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
		        }
		    }
		} 
		return null;
	}
	
	 public String toQueryString() {
			StringBuffer bf = new StringBuffer(100);
			String common = "";
			String name = "";
			String value = "";
			String[] temp=null;
			Enumeration <String> en = request.getParameterNames();
			while (en.hasMoreElements()) {
				name = StringUtil.dealNull(en.nextElement());
				temp=request.getParameterValues(name);
				if(temp.length<=1){
					value = StringUtil.dealNull(request.getParameter(name));
				}else{
					String t="";
					StringBuffer vBuffer=new StringBuffer();
					for(String s:temp){
						vBuffer.append(t+s);
						t=",";
					}
					value=vBuffer.toString();
				}
				if (!StringUtil.isEmpty(value)) {
					bf.append(common);
					bf.append(name);
					bf.append("=");
					//StringUtil
					bf.append(StringUtil.encode(value, StringUtil.dealNull(request.getCharacterEncoding(), "utf-8")));
					common = "&";
				}
			}
			return bf.toString();
		}
	
	
	private HashMap<String, String> fields = new HashMap<String, String>();
	private HashMap<String, String[]> arrays = new HashMap<String, String[]>();
    private HashMap<String, String> files = new HashMap<String, String>();
    private HashMap<String, byte[]> fileData = new HashMap<String, byte[]>();
    private Exception uploadException = null;
    private String myCookieValue = null;
    private String cookie_shopping_cart = null;
    private String cuisine_shopping_cart = null;
    private String outside_shopping_cart = null;
    
    public HttpServletRequest request;
    public HttpServletResponse response;

	public String getCookie_shopping_cart() {
		return getCookieValue(CookieHelper.COOKIE_SHOPPING_CART);
	}

	public void setCookie_shopping_cart(String cookie_shopping_cart) {
		this.cookie_shopping_cart = cookie_shopping_cart;
	}

	public String getCuisine_shopping_cart() {
		return getCookieValue(CookieHelper.CUISINE_SHOPPING_CART);
	}

	public void setCuisine_shopping_cart(String cuisine_shopping_cart) {
		this.cuisine_shopping_cart = cuisine_shopping_cart;
	}

	public String getOutside_shopping_cart() {
		return getCookieValue(CookieHelper.OUTSIDE_SHOPPING_CART);
	}

	public void setOutside_shopping_cart(String outside_shopping_cart) {
		this.outside_shopping_cart = outside_shopping_cart;
	}
    
    
    
    
    
}
