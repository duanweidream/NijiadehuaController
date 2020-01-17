package com.nijiadehua.api.base.wechat.pay;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.nijiadehua.api.base.http.HttpClientUtils;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.util.MD5Util;

import sun.security.jgss.spi.MechanismFactory;

//微信支付工具类
public class MiniPayUtil {

	public static Logger logger = Logger.getLogger(MiniPayUtil.class);
	public static String MCH_ID="1573616051";//mchid
	public static String MCH_SECRET="0a3b6f64f0523984e51323fe53b8c504";//mchsecret
	
	public static String APP_ID		= "wx6e2a2a319598b1e5";	//appid
	public static String APP_SECRET	= "e5dad9e4ff67e4916e1eb75ed5521f32";	//appsecret
	
	public static String NOTIFY_URL	= "https://a.yuchengmedia.com.cn/pay/notify"; //"http://inner.hua1000.com/order/notify";  //支付完成后的回调处理页面,*替换成notify_url.asp所在路径
	public static String charset="utf-8";
    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";

    public static Map<String,String> unifiedorder(String openid,String ip,String order_id,String goods,int amount) throws Exception{
    	String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    	
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("appid", APP_ID);
		map.put("mch_id", MCH_ID);
		map.put("openid", openid);
		map.put("nonce_str", Sha1Util.getNonceStr());
		map.put("body", goods);
		map.put("out_trade_no", order_id); //商户系统内部订单号，要求32个字符内
		map.put("total_fee", amount+""); //标价金额,标价金额
		map.put("spbill_create_ip", ip); //终端IP,调用微信支付API的机器IP
		map.put("notify_url",NOTIFY_URL); //异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
		map.put("trade_type", "JSAPI");
		map.put("sign", generateSignature(map, MCH_SECRET));
		
		String xml = XMLUtil.mapToXml(map);
		String responseXml = HttpClientUtils.postXml(url,xml);
		
        String return_code;
        Map<String, String> respData = XMLUtil.xmlToMap(responseXml);
        if (respData.containsKey("return_code")) {
            return_code = respData.get("return_code");
        }else {
            throw new Exception(String.format("No `return_code` in XML: %s", responseXml));
        }

        if (return_code.equals(FAIL)) {
            return respData;
        }else if (return_code.equals(SUCCESS)) {
           if (isSignatureValid(respData,MCH_SECRET)) {
               return respData;
           }else {
               throw new Exception(String.format("Invalid sign value in XML: %s", responseXml));
           }
        }
        else {
            throw new Exception(String.format("return_code value %s is invalid in XML: %s", return_code, responseXml));
        }
    
	}
	
    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data Map类型数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data) throws Exception {
        if (!data.containsKey(FIELD_SIGN) ){
            return false;
        }
        String sign = data.get(FIELD_SIGN);
        return generateSignature(data,MCH_SECRET).equals(sign);
    }
	
    /**
     * 判断签名是否正确，必须包含sign字段，否则返回false。
     *
     * @param data Map类型数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名是否正确
     * @throws Exception
     */
    public static boolean isSignatureValid(Map<String, String> data, String key) throws Exception {
        if (!data.containsKey(FIELD_SIGN) ){
            return false;
        }
        String sign = data.get(FIELD_SIGN);
        return generateSignature(data, key).equals(sign);
    }
	
    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data 待签名数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(FIELD_SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(MCH_ID);
        return MD5Util.MD5Encode(sb.toString(),charset).toUpperCase();
    }
    
    
    /**
     * 生成签名. 注意，若含有sign_type字段，必须和signType参数保持一致。
     *
     * @param data 待签名数据
     * @param key API密钥
     * @param signType 签名方式
     * @return 签名
     */
    public static String generateSignature(final Map<String, String> data, String key) throws Exception {
        Set<String> keySet = data.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        for (String k : keyArray) {
            if (k.equals(FIELD_SIGN)) {
                continue;
            }
            if (data.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append(k).append("=").append(data.get(k).trim()).append("&");
        }
        sb.append("key=").append(key);
        return MD5Util.MD5Encode(sb.toString(),charset).toUpperCase();
    }
    
//	private static String sign(SortedMap<String, String> map){
//		StringBuffer sbuf = new StringBuffer();
//		boolean b = true;
//		for(Entry<String, String> en:map.entrySet()){
//			if(!StringUtil.isEmpty(en.getValue())){
//				sbuf.append((b?"":"&")+en.getKey()+"="+en.getValue());
//				if(b){b=false;}
//			}
//		}
//		sbuf.append("&key="+MCH_SECRET);
//		logger.logInfo("signStr:"+sbuf.toString());
//		return MD5Util.MD5Encode(sbuf.toString(), charset).toUpperCase();
//	}
	
	public static void main(String[] args) throws Exception{

		//String xmlStr = "<xml><appid>wxe2b00ca6105d3538</appid><body>商品描述</body><mch_id>10028307</mch_id><nonce_str>e36258b3c74f08054a974a5fe1703f9c</nonce_str><notify_url>http://*/notify.php</notify_url><openid>oHGi9jmoyUVFUVcp04F1_df8WAX0</openid><out_trade_no>20141119060226</out_trade_no><sign>9dbc6464d8b3c1993845370ae1634d89</sign><spbill_create_ip>127.0.0.1</spbill_create_ip><total_fee>200</total_fee><trade_type>JSAPI</trade_type></xml>";
		//String responseXml = HttpClientUtils.postXml("https://api.mch.weixin.qq.com/pay/unifiedorder", xmlStr);
	    //System.out.println(responseXml);
		
		//String responseXml = unifiedorder("https://api.mch.weixin.qq.com/pay/unifiedorder", "oDjJa5JcnGyPCnQEP_XDesqX-W1U", "223.202.209.6");
		//System.out.println(responseXml);
		//Map<String,String> map = unifiedorder("https://api.mch.weixin.qq.com/pay/unifiedorder", "oDjJa5JcnGyPCnQEP_XDesqX-W1U", "223.202.209.6");
		//System.out.println(map);
		Map<String,String> map = unifiedorder("oDjJa5JcnGyPCnQEP_XDesqX-W1U", "223.202.209.6","DZ20190528160609934633","test哈哈",1);
		System.out.println(map);
	}
	
}
