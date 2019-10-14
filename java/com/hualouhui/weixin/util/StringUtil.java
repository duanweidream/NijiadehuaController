package com.hualouhui.weixin.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.ServletInputStream;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringEscapeUtils;

import Decoder.BASE64Encoder;


public class StringUtil {
	private static final String BLANK = "";
	
	

	
	public static boolean isEmpty(String s){
		return s == null || BLANK.compareTo(s) == 0;
	}
	public static boolean isEmpty(Object str) {
		return str == null || BLANK.equals(str);
	}
	public static boolean isEmpty(Object ...strs){
		for(Object str:strs){
			if(isEmpty(str)){
				return true;
			}
		}
		return false;
	}
	public static String dealNull(String str) {
		return str != null ? str.trim() : BLANK;
	}
	public static String dealNull(String str, String defaultVal) {
		return str != null ? str.trim() : defaultVal;
	}
	public static String dealNull(Object str,String defaultValue){
		return str != null ? str.toString().trim() : defaultValue;
	}
	public static String dealNull(Object obj) {
		String str = BLANK;
		if(obj!=null){
			if(obj instanceof String){
				str = (String)obj;
			}else{
				str = obj.toString();
			}
		}
		return str;
	}
	
	
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length() - 1))) {
				return filename.substring(i + 1);
			}
		}
		return null;
	}
	public static String getFileName(String filepath){
		if ((filepath != null) && (filepath.length() > 0)) {
			int i = filepath.lastIndexOf('/');
			if ((i > -1) && (i < (filepath.length() - 1))) {
				return filepath.substring(i + 1);
			}
		}
		return filepath;
	}
	public static String getFilePath(String filepath){
		if ((filepath != null) && (filepath.length() > 0)) {
			int i = filepath.lastIndexOf('/');
			if ((i > -1) && (i < (filepath.length() - 1))) {
				return filepath.substring(0,i + 1);
			}
		}
		return filepath;
	}
	public static boolean equals(Object value1, Object value2) {
		boolean is = false;
		if (value1 == value2) { // is null or self
			return is = true;
		}
		if (value1 != null && value2 != null) { // is not null;
			return value1.equals(value2);
		}
		return is;
	}
	public static int random(int n){
		if(n==0) return 0;
		Random random = new Random();
		return Math.abs(random.nextInt())%n;
	}
	public static String formatOrderId(Date date,Integer orderId){
        String prefix = DateUtil.getFormatDate(date, "yyyyMM");// createdTime.substring(0, tIndex).replace("-", "").substring(0, 6);        
        return String.format("%s%06d", prefix, orderId);
    }
	public static String createUuid(String mac,String imei){
		return MD5Util.MD5Encode(mac.replaceAll(":","")+imei, null);
	}
	public static String encode(String str, final String encoding) {
		try {
			if (!isEmpty(str)) {
				str = URLEncoder.encode(str, encoding);
			}
		} catch (Exception e) {
		}
		return str;
	}
	public static String decode(String str, final String encoding) {
		try {
			if (!isEmpty(str)) {
				str = URLDecoder.decode(str, encoding);
			}
		} catch (Exception e) {
		}
		return str;
	}
	
	//生成问答的sessionid
	public static String createSessionid(Integer userId,Integer requestId){
		return "sessionid"+userId+requestId;
	}
	public static String filterCityName(String cityName){
		if(!isEmpty(cityName)){
			return cityName.replaceAll("(省|市|区|县)", "");
		}
		return "";
	}
	
	public static String createId(Integer length) { 
		String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
	public static String createCode(Integer length) { 
		String base = "123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return sb.toString();     
	 } 
	public static String streamToString(ServletInputStream inputStream) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while ((line = br.readLine()) != null) {  
            sb.append(line);  
        }  
        return sb.toString();
	}
	public static String getLanguage(){
		Locale locale = Locale.getDefault(); 
		return locale.getLanguage();
	}
	
	public static String getValueBykeyFromUrl(String url,String key){
		String result = "";
		//redirect_url?code=xxx&portal_server=https://portalserver:port/portal
        //http://5.18.0.1/portal/cloudlogin.html?code=ce321f2cbf344335bafab8776f881591&userip=5.18.0.37&portal_server=http://lvzhou.h3c.com:80/portal/protocol&_ts=1479190177471
		
		if(url.indexOf('?') != -1){
			url = url.replace('?','&');
		}
		
        String[] array = url.split("&");
        for(String s : array){
        	String[] tem = s.split("=");
        	if(tem[0].equals(key)){
        		result = tem[1];
        		break;
        	}
        }
        
        return result;
	}
	
	public static void main(String[] args) throws Exception{
		System.out.println(getValueBykeyFromUrl("redirect_url?code=xxx&portal_server=https://portalserver:port/portal", "portal_server"));
	}
	
	
	
}