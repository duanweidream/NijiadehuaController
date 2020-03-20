package com.nijiadehua.api.base.wechat.pay;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.github.wxpay.sdk.WXPayConstants.SignType;
import com.github.wxpay.sdk.WXPayUtil;
import com.nijiadehua.api.base.http.HttpClientUtils;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.util.MD5Util;


//微信支付工具类
public class MiniPayUtil {

	public static Logger logger = Logger.getLogger(MiniPayUtil.class);
	public static String MCH_ID="1573616051";//mchid
	public static String MCH_SECRET="0a3b6f64f0523984e51323fe53b8c504";//mchsecret
	
	public static String APP_ID		= "wx6e2a2a319598b1e5";	//appid
	public static String APP_SECRET	= "e5dad9e4ff67e4916e1eb75ed5521f32";	//appsecret
	
	public static String NOTIFY_URL	= "https://api.qljtfw.com/mini/v/1/pay/notify"; //"http://inner.hua1000.com/order/notify";  //支付完成后的回调处理页面,*替换成notify_url.asp所在路径
	public static String charset="utf-8";
    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";


    public static Map<String,String> unifiedorder(String openid,String ip,String order_id,String goods,int amount) throws Exception{
    	String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    	
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("appid", APP_ID);
		map.put("mch_id", MCH_ID);
		map.put("openid", openid);
		map.put("nonce_str", WXPayUtil.generateNonceStr());
		map.put("body", goods);
		map.put("out_trade_no", order_id); //商户系统内部订单号，要求32个字符内
		map.put("total_fee", amount+""); //标价金额,标价金额
		map.put("spbill_create_ip", ip); //终端IP,调用微信支付API的机器IP
		map.put("notify_url",NOTIFY_URL); //异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
		map.put("trade_type", "JSAPI");
		map.put("sign", WXPayUtil.generateSignature(map, MCH_SECRET,SignType.MD5));
		
		String xml = WXPayUtil.mapToXml(map);
		String responseXml = HttpClientUtils.postXml(url,xml);
		
        String return_code;
        Map<String, String> respData = WXPayUtil.xmlToMap(responseXml);
        if (respData.containsKey("return_code")) {
            return_code = respData.get("return_code");
        }else {
            throw new Exception(String.format("No `return_code` in XML: %s", responseXml));
        }

        if (return_code.equals(FAIL)) {
            return respData;
        }else if (return_code.equals(SUCCESS)) {
           if (WXPayUtil.isSignatureValid(respData,MCH_SECRET,SignType.MD5)) {
               return respData;
           }else {
               throw new Exception(String.format("Invalid sign value in XML: %s", responseXml));
           }
        }
        else {
            throw new Exception(String.format("return_code value %s is invalid in XML: %s", return_code, responseXml));
        }
    
	}

	public static void main(String[] args) throws Exception{


		Map<String,String> map = unifiedorder("oDjJa5FaFCXh6EMI_YCF_OmMQrJQ", "223.202.209.6","20200118183800000059","勤奋 勤奋 50X100cm",1);
		System.out.println(map);
		
		
	}
	
}
