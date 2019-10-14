package com.hualouhui.weixin.base.wechat;

import java.util.HashMap;
import java.util.Map;

import com.hualouhui.weixin.base.http.HttpClient;
import com.hualouhui.weixin.base.log.Logger;

import net.sf.json.JSONObject;

/**
 * token 管理
 * */
public class JsApiTicket {

	public static final Logger logger = Logger.getLogger(JsApiTicket.class);
	public static String jsapi_ticket="";
	//刷新时间戳
	public static Long timestamp=0l;
	//强制刷新开关
	public static String onoff="on";
	//token有效期(单位:秒)
	public static Integer expires_in=7200;
	//{"{"access_token":"ACCESS_TOKEN","expires_in":7200}
	public synchronized static void refresh(){
		String tiken_url="https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+AccessToken.getAccessToken()+"&type=jsapi";
		
		Map<String, String> map = new HashMap<>();
		String jsonStr = HttpClient.readFromUrl(tiken_url, map);
        JSONObject json = JSONObject.fromObject(jsonStr);
        if(json.getInt("errcode")==0){
        	jsapi_ticket = json.getString("ticket");
            expires_in=json.getInt("expires_in");
            //关闭开关
            onoff="off";
            timestamp=System.currentTimeMillis();
        }
	}
	public static String getJsapiticket(){
		Long duration = (System.currentTimeMillis()-timestamp)/1000;
		if("on".equals(onoff)||duration.intValue()>=expires_in){
			refresh();
		}
		return jsapi_ticket;
	}
	public static void main(String[] args){
		JsApiTicket.refresh();
     	System.out.println(getJsapiticket());
	}
}
