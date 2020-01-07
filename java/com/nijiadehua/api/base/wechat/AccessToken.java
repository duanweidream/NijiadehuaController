package com.nijiadehua.api.base.wechat;

import com.nijiadehua.api.base.log.Logger;

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
	/*public synchronized static void refresh(){
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
        
	}*/
	
}
