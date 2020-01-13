package com.nijiadehua.api.base.wechat.pay;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import com.nijiadehua.api.base.http.HttpClientUtils;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.util.Config;
import com.nijiadehua.api.util.MD5Util;
import com.nijiadehua.api.util.StringUtil;

import net.sf.json.JSONObject;


//微信支付工具类
public class WeChatPayUtil {

	public static Logger logger = Logger.getLogger(WeChatPayUtil.class);
	public static String PARTNER= "1223298301";	//财付通商户号
	public static String MCH_ID="10028307";//mchid
	public static String API_KEY="496bd33584d955e3913f1a3e82bb2f2d";
	public static String PARTNER_KEY= "152d388cb751f00c42b3ab58cbf14314";	//财付通密钥
	public static String APP_ID		= "wxe2b00ca6105d3538";	//appid
	public static String APP_SECRET	= "e48a4d6761a7928c0ff0eb9d614fb750";	//appsecret
	public static String APP_KEY    = "Hrpj7QQgeZEaFsCipq4kgNxERsbRJIhHKZYSuLD8MohTdEtUQafl1i8CPcjVNzzFMlV0dhwXsHUF1TzujbrOzqADkTgVlYdfr9yRjaC2aDRVImFsMQ3Lf6gaWvGWSq75";	//paysignkey 128位字符串(非appkey)
	public static String NOTIFY_URL	= Config.getValue("wechat.notify.url"); //"http://inner.hua1000.com/order/notify";  //支付完成后的回调处理页面,*替换成notify_url.asp所在路径
	public static String REDIRECT_URI=Config.getValue("redirect.url");
	public static String charset="GBK";
	
	public static JSONObject getOpenUdid(String url){
		String strs = HttpClientUtils.readFromURL(url, charset);
		try{
			return JSONObject.fromObject(strs);
		}catch(Exception e){
			String error = "{\"errcode\":\"40029\",\"errmsg\":\""+e.getMessage()+"\"}" ;
			e.printStackTrace();
			return JSONObject.fromObject(error);
		}
	}

	public static String postOrderByXml(String uri,String openid,String ip){
		Double money_fen = 0.0;//order.deal_money_yuan*100;
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("openid", openid);
		//map.put("body", getBody(order.product_details));
		//map.put("out_trade_no", order.out_trade_no);
		map.put("total_fee", money_fen.intValue()+"");//单位分
		map.put("notify_url",NOTIFY_URL);
		map.put("trade_type", "JSAPI");
		map.put("appid",APP_ID);
		map.put("mch_id", MCH_ID);
		map.put("spbill_create_ip", ip);
		map.put("nonce_str", Sha1Util.getNonceStr());
		map.put("sign", sign(map));
		String xml = mapToXml(map);
		String responseXml = HttpClientUtils.postXml(uri, xml);
		return responseXml;
	}
	private static String getBody(String productDetails){
		try{
			JSONObject obj = JSONObject.fromObject(productDetails);
			return obj.containsValue("product_name")?obj.getString("product_name"):"h";
		}catch(Exception e){
			e.printStackTrace();
			return "back";
		}
	}
	public static String jsCallApiJson(String prepayId){
		SortedMap<String, String> jsMap = new TreeMap<String, String>();
		jsMap.put("appId",APP_ID);
		jsMap.put("timeStamp", new Date().getTime()+"");
		jsMap.put("nonceStr", Sha1Util.getNonceStr());
		jsMap.put("package", "prepay_id="+prepayId);
		jsMap.put("signType", "MD5");
		jsMap.put("paySign", sign(jsMap));
		return JSONObject.fromObject(jsMap).toString();
	}
	
	
	private static String mapToXml(SortedMap<String, String> map ){
		StringBuffer sbuf = new StringBuffer("<xml>");
		for(Entry<String, String> en:map.entrySet()){
			 sbuf.append("<"+en.getKey()+">"+en.getValue()+"</"+en.getKey()+">");	
			
             //if(StringUtil.dealNull(en.getValue()).matches("[0-9]*.?[0-9]*")){
             //	 sbuf.append("<"+en.getKey()+">"+en.getValue()+"</"+en.getKey()+">");		
             //}else{
             //	 sbuf.append("<"+en.getKey()+"><![CDATA["+en.getValue()+"]]></"+en.getKey()+">");		
             //}
             
		}
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
		logger.logInfo("signStr:"+sbuf.toString());
		return MD5Util.MD5Encode(sbuf.toString(), charset).toUpperCase();
	}
	
	public static String formatQueryString(SortedMap<String, String> map){
		StringBuffer sbuf = new StringBuffer();
		boolean b = true;
		for(Entry<String, String> en:map.entrySet()){
			String key = en.getKey();
			String value = UrlEncode(en.getValue());
			sbuf.append( (b?"?":"&")+key+"="+value);
			if(b){b=false;}
		}
		return sbuf.toString();
	}
	public static String UrlEncode(String src){
		try {
		    if(null==src){
		    	return "";
		    }else{
		    	return StringUtil.dealNull(URLEncoder.encode(src, charset)).replace("+", "%20");
		    }
		} catch (UnsupportedEncodingException e) {
			//e.printStackTrace();
		}
		return "";
	}
	public static void main(String[] args){

		//appid=wxe2b00ca6105d3538&body=商品描述&mch_id=10028307&nonce_str=5a45828dead8c065099cb653a2185df1&notify_url=http://inner.hua1000.com/order/notify&openid=oHGi9jmoyUVFUVcp04F1_df8WAX0&out_trade_no=20141119060226&spbill_create_ip=127.0.0.1&total_fee=20000&trade_type=JSAPI&key=496bd33584d955e3913f1a3e82bb2f2d
		
		System.out.println(jsCallApiJson("asdfasdf")); 
		
		//String xmlStr = "<xml><appid>wxe2b00ca6105d3538</appid><body>商品描述</body><mch_id>10028307</mch_id><nonce_str>e36258b3c74f08054a974a5fe1703f9c</nonce_str><notify_url>http://*/notify.php</notify_url><openid>oHGi9jmoyUVFUVcp04F1_df8WAX0</openid><out_trade_no>20141119060226</out_trade_no><sign>9dbc6464d8b3c1993845370ae1634d89</sign><spbill_create_ip>127.0.0.1</spbill_create_ip><total_fee>200</total_fee><trade_type>JSAPI</trade_type></xml>";
		//String responseXml = HttpClientUtils.postXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlStr);
	    //System.out.println(responseXml);
	}
	
}
