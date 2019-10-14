package com.hualouhui.weixin.base.wechat;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import org.jdom.JDOMException;

import com.hualouhui.weixin.base.http.HttpClient;
import com.hualouhui.weixin.base.wechat.pay.XMLUtil;
import com.hualouhui.weixin.util.Config;
import com.hualouhui.weixin.util.DateUtil;
import com.hualouhui.weixin.util.MD5Util;
import com.hualouhui.weixin.util.StringUtil;

public class Pay {
  
	private static String MCH_ID=Config.getValue("weixin.pay.mchid");
	private static String WX_APPID=Config.getValue("weixin.api.appid");
	private static String API_KEY=Config.getValue("weixin.api.key");
	private static String APP_SECRET=Config.getValue("weixin.app.secret");
	
	
	public static String convertXml(Map<String, String> map){
		StringBuffer sbuf = new StringBuffer();
		sbuf.append("<xml>");
		sbuf.append("<sign><![CDATA["+StringUtil.dealNull(map.get("sign"))+"]]></sign>");
		sbuf.append("<mch_billno><![CDATA["+StringUtil.dealNull(map.get("mch_billno"))+"]]></mch_billno>");
		sbuf.append("<mch_id><![CDATA["+StringUtil.dealNull(map.get("mch_id"))+"]]></mch_id>");
		sbuf.append("<wxappid><![CDATA["+StringUtil.dealNull(map.get("wxappid"))+"]]></wxappid>");
		sbuf.append("<send_name><![CDATA["+StringUtil.dealNull(map.get("send_name"))+"]]></send_name>");
		sbuf.append("<re_openid><![CDATA["+StringUtil.dealNull(map.get("re_openid"))+"]]></re_openid>");
		sbuf.append("<total_amount><![CDATA["+StringUtil.dealNull(map.get("total_amount"))+"]]></total_amount>");
		sbuf.append("<total_num><![CDATA["+StringUtil.dealNull(map.get("total_num"))+"]]></total_num>");
		sbuf.append("<wishing><![CDATA["+StringUtil.dealNull(map.get("wishing"))+"]]></wishing>");
		sbuf.append("<client_ip><![CDATA["+StringUtil.dealNull(map.get("client_ip"))+"]]></client_ip>");
		sbuf.append("<act_name><![CDATA["+StringUtil.dealNull(map.get("act_name"))+"]]></act_name>");
		sbuf.append("<remark><![CDATA["+StringUtil.dealNull(map.get("remark"))+"]]></remark>");
		sbuf.append("<nonce_str><![CDATA["+StringUtil.dealNull(map.get("nonce_str"))+"]]></nonce_str>");
		sbuf.append("</xml>");
		return sbuf.toString();
	}
	private static String sign(SortedMap<String, String> map){
		StringBuffer sbuf = new StringBuffer();
		boolean b = true;
		for(Entry<String, String> en:map.entrySet()){
			if(!StringUtil.isEmpty(en.getValue())){
				sbuf.append((b?"":"&")+en.getKey()+"="+en.getValue());
				if(b){b=false;}
			}
		}
		sbuf.append("&key="+API_KEY);
		
		return MD5Util.MD5Encode(sbuf.toString(), "utf-8").toUpperCase();
	}
	public static void main(String[] args) throws JDOMException, IOException{
	
	}
	
}
