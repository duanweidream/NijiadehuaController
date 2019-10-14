package com.hualouhui.weixin.base.wechat;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.hualouhui.weixin.base.http.HttpClientUtils;
import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.util.Config;

import net.sf.json.JSONObject;

/**
 * token 管理
 * */
public class AccessToken {

	public static final Logger logger = Logger.getLogger(AccessToken.class);
	public static String access_token="";
	//刷新时间戳
	public static Long timestamp=0l;
	//强制刷新开关
	public static String onoff="on";
	//token有效期(单位:秒)
	public static Integer expires_in=7200;
	//{"{"access_token":"ACCESS_TOKEN","expires_in":7200}
	public synchronized static void refresh(){
		Map<String, String> map = new HashMap<>();
		map.put("grant_type", "client_credential");
		map.put("appid", Config.getValue("weixin.api.appid"));
		map.put("secret", Config.getValue("weixin.api.secret"));
		String jsonStr = HttpClientUtils.readFromURL("https://api.weixin.qq.com/cgi-bin/token", map, null);
		
		System.out.println(jsonStr);
		
        JSONObject json = JSONObject.fromObject(jsonStr);
        access_token = json.getString("access_token");
        expires_in=json.getInt("expires_in");
        //关闭开关
        onoff="off";
        timestamp=System.currentTimeMillis();
        
	}
	public static String getAccessToken(){
		Long duration = (System.currentTimeMillis()-timestamp)/1000;
		System.out.println("duration="+duration);
		if("on".equals(onoff)||duration.intValue()>=expires_in){
			refresh();
		}
		return access_token;
	}
	public static void main(String[] args){
		AccessToken.refresh();
     	System.out.println(getAccessToken());
	}
}
