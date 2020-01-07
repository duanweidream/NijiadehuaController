package com.nijiadehua.api.base.wechat;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

import com.nijiadehua.api.base.http.HttpClient;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.util.Config;

import net.sf.json.JSONObject;

/**
 * 
 * 微信api调用接口
 * */
public class WeChatApi {

	public static final Logger logger = Logger.getLogger(WeChatApi.class);
	public static final String app_id=Config.getValue("weixin.api.appid");
	public static final String secret=Config.getValue("weixin.api.secret");
	protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());
	protected static Header xmlHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_XML.toString());
	
	
	
	//通过code获取access_token openid等
	//https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
	public static JSONObject getOpenIdByCode(String code) throws ApiError{
		Map<String, String> map = new HashMap<>();
		map.put("appid",Config.getValue("weixin.api.appid"));
		map.put("secret",Config.getValue("weixin.api.secret"));
		map.put("js_code",code);
		map.put("grant_type","authorization_code");
		String responseString = HttpClient.readFromUrl("https://api.weixin.qq.com/sns/jscode2session",map);
		
		System.out.println("getOpenIdByCode:"+responseString+" code:"+code);
		
		JSONObject json = parseApiJson(responseString);
		return json;
	}
	
	
	private static JSONObject parseApiJson(String jsonStr) throws ApiError{
		try{
			JSONObject o = JSONObject.fromObject(jsonStr);
            return o;
		}catch (Exception e){
		    throw ApiError.Type.BUSINESS_ERROR.toException();	
		}
	}
	
	
	public static void main(String[] args) throws ApiError {
		
		JSONObject json = getOpenIdByCode("123");
		
		
	}
	
	
	
	
	
	
}
     