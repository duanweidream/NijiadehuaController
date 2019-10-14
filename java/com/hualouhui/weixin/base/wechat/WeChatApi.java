package com.hualouhui.weixin.base.wechat;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

import com.hualouhui.weixin.base.handler.BeanListJsonHander;
import com.hualouhui.weixin.base.http.HttpClient;
import com.hualouhui.weixin.base.http.HttpClientUtils;
import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.UserInfo;
import com.hualouhui.weixin.service.UserInfoService;
import com.hualouhui.weixin.util.Config;
import com.hualouhui.weixin.util.StringUtil;

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
	
	/**
	 * openid获取用户信息.
	 * http请求方式: GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN 
	 * @throws ApiError 
	 * */
	public static void loadUserInfo(String openid) throws ApiError{
		logger.logInfo("WeChatApi.loadUserInfo(openid="+openid+")");
		Map<String, String> map = new HashMap<>();
		map.put("access_token",AccessToken.getAccessToken());
		map.put("openid", openid);
		Locale locale = Locale.getDefault();  
		map.put("lang", locale.getLanguage());
		String jsonStr = HttpClientUtils.readFromURL("https://api.weixin.qq.com/cgi-bin/user/info", map,null);
		
		JSONObject json = parseApiJson(jsonStr);
		
		BeanListJsonHander<UserInfo> cr = new BeanListJsonHander<UserInfo>(UserInfo.class);
		cr.handle(json, UserInfo.class);
		UserInfo fan = cr.handle(json, UserInfo.class);
		logger.logInfo(fan.openid+" "+fan.getNickname()+"  "+fan.getCountry()+" "+fan.getProvince()+"  "+fan.getHeadimgurl());
		UserInfoService userInfoService = new UserInfoService();
		
		UserInfo userInfo = userInfoService.findUserInfoByOpenid(fan.openid);
		if(userInfo == null){
			Long userid = userInfoService.saveUserInfo(fan);
			fan.setId(userid);
		}else{
			fan.setId(userInfo.getId());
			userInfoService.updateUserInfo(fan);
		}
		
	}
	
	/**
	 * openid获取用户信息.
	 * http请求方式: GET https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN 
	 * @throws ApiError 
	 * */
	public static UserInfo getWxUserInfo(String openid) throws ApiError{
		logger.logInfo("WeChatApi.loadUserInfo(openid="+openid+")");
		Map<String, String> map = new HashMap<>();
		map.put("access_token",AccessToken.getAccessToken());
		map.put("openid", openid);
		Locale locale = Locale.getDefault();  
		map.put("lang", locale.getLanguage());
		String jsonStr = HttpClientUtils.readFromURL("https://api.weixin.qq.com/cgi-bin/user/info", map,null);

		JSONObject json = parseApiJson(jsonStr);
		
		BeanListJsonHander<UserInfo> cr = new BeanListJsonHander<UserInfo>(UserInfo.class);
		cr.handle(json, UserInfo.class);
		UserInfo fan = cr.handle(json, UserInfo.class);
		logger.logInfo(fan.openid+" "+fan.getNickname()+"  "+fan.getCountry()+" "+fan.getProvince()+"  "+fan.getHeadimgurl());
		return fan;
	}
	
	//取消关注
	public static void unsubscribe(String openid) throws ApiError{
		logger.logInfo("WeChatApi.unsubscribe(openid="+openid+")");
		UserInfoService userService = new UserInfoService();
		userService.unsubscribe(openid);
	}
	//创建菜单
	public static JSONObject menuCreate(JSONObject menuJson) throws ApiError{
		//先删除菜单
		menuDelete();
		logger.logInfo("WeChatApi.menuCreate(json="+menuJson.toString()+")");
		Map<String, String> map = new HashMap<>();
		map.put("access_token",AccessToken.getAccessToken());
		String responseString = HttpClient.postJsonString("https://api.weixin.qq.com/cgi-bin/menu/create", menuJson.toString(), map);
		JSONObject json = parseApiJson(responseString);
		return json;
	}
	public static JSONObject menuDelete() throws ApiError{
		logger.logInfo("WeChatApi.menuDelete()");
		Map<String, String> map = new HashMap<>();
		map.put("access_token",AccessToken.getAccessToken());
		
		String responseString = HttpClient.postJsonString("https://api.weixin.qq.com/cgi-bin/menu/delete", null,map);
		JSONObject json = parseApiJson(responseString);
		return json;
	}
	//通过code获取access_token openid等
	public static JSONObject sns_access_token(String code) throws ApiError{
		Map<String, String> map = new HashMap<>();
		map.put("appid",Config.getValue("weixin.api.appid"));
		map.put("secret",Config.getValue("weixin.api.secret"));
		map.put("code",code);
		map.put("grant_type","authorization_code");
		String responseString = HttpClient.readFromUrl("https://api.weixin.qq.com/sns/oauth2/access_token",map);
		
		System.out.println("sns_access_token:"+responseString+" code:"+code);
		
		JSONObject json = parseApiJson(responseString);
		return json;
	}
	
	//
	
	public static JSONObject snsuerinfo(String accesstoken,String openid) throws ApiError{
		
		Map<String, String> map = new HashMap<>();
		map.put("access_token",accesstoken);
		map.put("openid",StringUtil.dealNull(openid));
		map.put("lang", StringUtil.getLanguage());
		
		System.out.println("utf-8");
		String responseString = HttpClientUtils.readFromURL("https://api.weixin.qq.com/sns/userinfo", map, null);
		System.out.println("responseString==="+responseString);
		//String responseString = HttpClient.readFromUrl("https://api.weixin.qq.com/sns/userinfo",map,"utf-8");
		JSONObject json = parseApiJson(responseString);
		return json;
	}
	
	
	private static JSONObject parseApiJson(String jsonStr) throws ApiError{
		try{
			JSONObject o = JSONObject.fromObject(jsonStr);
            return o;
		}catch (Exception e){
		    throw ApiError.Type.LOGIC_ERROR.toException();	
		}
	}
	
	//获取ssid列表
	public static JSONObject getSsidList() throws ApiError{
		Map<String, String> map = new HashMap<>();
		map.put("access_token",AccessToken.getAccessToken());
		String responseString = HttpClient.readFromUrl("https://api.weixin.qq.com/bizwifi/device/list",map);
		System.out.println("getSsidList==="+responseString);
		JSONObject json = parseApiJson(responseString);
		return json;
	}

	
}
     