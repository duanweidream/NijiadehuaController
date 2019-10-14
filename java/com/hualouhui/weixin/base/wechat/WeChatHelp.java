package com.hualouhui.weixin.base.wechat;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import com.hualouhui.weixin.util.Config;

public class WeChatHelp {

	public static String getIpAddress(){
		 try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public String authorize_url(String url,String state){
		try {
			String redirect_url=URLEncoder.encode(Config.getValue("server.name")+url, "utf-8");
			StringBuffer sbuf = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?");
            sbuf.append("appid="+Config.getValue("weixin.api.appid"));
            sbuf.append("&redirect_uri="+redirect_url);
            sbuf.append("&response_type=code&scope=snsapi_base&state="+state+"&connect_redirect=1#wechat_redirect");
         
            return sbuf.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String authorize_userurl(String url,String state){
		try {
			String redirect_url=URLEncoder.encode(Config.getValue("server.name")+url, "utf-8");
			StringBuffer sbuf = new StringBuffer("https://open.weixin.qq.com/connect/oauth2/authorize?");
            sbuf.append("appid="+Config.getValue("weixin.api.appid"));
            sbuf.append("&redirect_uri="+redirect_url);
            sbuf.append("&response_type=code&scope=snsapi_userinfo&state="+state+"&connect_redirect=1#wechat_redirect");
            return sbuf.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
