package com.wooboo.dsp.util;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: DSP 平台
 * </p>
 * 
 * <p>
 * Description: Wooboo DSP Platform
 * </p>
 * 
 * <p>
 * Copyright: Copyright (c) 2015.5.28
 * </p>
 * 
 * <p>
 * Company: Wooboo
 * </p>
 * 
 * 类名称：UtilFunction 类描述： 封装项目中公用函�?创建人：duanwei 创建时间�?015-5-28 下午5:13:21
 * 修改人：duanwei 修改时间�?015-5-28 下午5:13:21 修改备注�?
 * 
 * @author duanwei
 * 
 * @version 1.0.0
 */
public class UtilFunction {

	/**
	 * 判断字符是否为空(不空 返回true,否则返回false)
	 * 
	 * @param s
	 * @return
	 */
	public final static boolean isEmpty(String s) {
		if (s == null || "".equals(s.trim())) {
			return true;
		}

		return false;
	}

	/**
	 * 判断字符是否为空(不空 返回true,否则返回false)
	 * 
	 * @param s
	 * @return
	 */
	public final static boolean isEmpty(Object o) {
		if (o == null) {
			return true;
		}
		if ("".equals(o.toString())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 判断字符是否为空(不空 返回true,否则返回false)
	 * 
	 * @param s
	 * @return
	 */
	public final static String strElement(Object o) {
		if (o == null) {
			return "";
		}
		if ("null".equals(o.toString())) {
			return "";
		}
		return o.toString();
	}
	
	/**
	 * 判断字符是否为空(不空 返回当前数值,否则返回0)
	 * 
	 * @param s
	 * @return
	 */
	public final static String strNullZero(Object o) {
		if (o == null) {
			return "0";
		}
		if ("null".equals(o.toString())) {
			return "0";
		}
		return o.toString();
	}
	
	/**
	 * 删除StringBuffer前后的制定字�?
	 * 
	 * @param sb
	 *            原始字符�?
	 * @param c
	 *            待删除的字符
	 * @return
	 */
	public final static String trim(StringBuffer sb, char c) {
		if (sb.length() == 0) {
			return "";
		}

		// 删除前端的字�?
		while (true) {
			if (sb.length() == 0 || sb.charAt(0) != c) {
				break;
			}

			sb.deleteCharAt(0);
		}

		// 删除后端的字�?
		while (true) {
			int lastIndex = sb.length() - 1;
			if (sb.length() == 0 || sb.charAt(lastIndex) != c) {
				break;
			}

			sb.deleteCharAt(lastIndex);
		}

		return sb.toString();
	}

	/**
	 * 将list转成Array
	 * 
	 * @return
	 */
	public final static String[] getArrayByList(List<String> list) {
		return list.toArray(new String[0]);
	}

	/**
	 * 替换字符串中起始位指定位置字符[beginIndex-endIndex] 为replace<br>
	 * 
	 * <pre>
	 * �?：beginIndex �?��取�?�? 即字符的其实坐标值，小于0�?�?计算
	 * endIndex �?��取�?为参数string实际长度  大于字符串长度时按字符串实际长度计算
	 * 
	 * <pre>
	 * 
	 * <pre>
	 * 情景说明�?1 、string 为空 返回null
	 * 2�?当[beginIndex = endIndex] 返回原字符串string
	 * 3�?当[beginIndex > endIndex] 返回原字符串string
	 * 4、当 [beginIndex > string.length()] 返回原字符串string
	 * 5、当 [beginIndex < 0] 按照string实际�?��长度替换如：
	 * 	UtilFunction.replaceChar("great", -10, 2, '*') = **eat
	 * 6、当 [endIndex >= string.length()] 按照string实际�?��长度替换如：
	 * 	UtilFunction.replaceChar("张子�?, 1, 14, '*') = �?*
	 * 7、当 [endIndex< string.length()] 返回替换后字符串  如：
	 * UtilFunction.replaceChar("18612183104", 4, 8, '*') = 1861****104
	 * 
	 * <pre>
	 * @param string
	 * @param beginIndex
	 * @param endIndex
	 * @param replace
	 * @return
	 */
	public static String replaceChar(String string, int beginIndex,
			int endIndex, char replace) {
		if (string == null)
			return null;
		if (beginIndex >= string.length() || beginIndex > endIndex)
			return string;
		char data[] = string.toCharArray();
		int max = endIndex > data.length ? data.length : endIndex;
		for (int i = beginIndex < 0 ? 0 : beginIndex; i < max; i++) {
			data[i] = replace;
		}
		return String.valueOf(data);
	}

	/**
	 * 替换字符串中起始位指定位置字符[beginIndex-endIndex] �?
	 * 
	 * @param string
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public static String replaceCharToStar(String string, int beginIndex,
			int endIndex) {
		return replaceChar(string, beginIndex, endIndex, '*');
	}

	/**
	 * 替换所有的单引号 为 双引号
	 * 
	 * @param json
	 * @throws Exception
	 */
	public static String converteJson(String json){
        if("".equals(json)){
            return "{}";
        }else{
            json = json.replaceAll("=","\":\"");
            json = json.replaceAll("&","\",\"");
            json = json.replaceAll("&acute;","&quot;");
            json = json.replaceAll("\'","\""); 
            //return "{\""+json+"\"}";
            return json;
        }
       
    }
	
	/**
	 * 如果为空，默认返回0.00% ，否则换算返回
	 * 
	 * @param json
	 * @throws Exception
	 */
	public static String getPercentDefaultZero(Object o){
		if (o == null) {
			return "0.00%";
		}
		if ("null".equals(o.toString())) {
			return "0.00%";
		}
		
		String d = Float.valueOf(o.toString()) * 100 + "";
		
		return d;
		
	}
	
	/**
	 * 如果为空，默认返回0.00% ，否则换算返回
	 * 
	 * @param json
	 * @throws Exception
	 */
	public static String percentDefaultZero(Object o){
		if (o == null) {
			return "0.00%";
		}
		if ("null".equals(o.toString())) {
			return "0.00%";
		}
		
		String d = Float.valueOf(o.toString()) * 100 + "%";
		
		return d;
		
	}
	
	/**
	 * Java文件操作 获取文件扩展名
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	
	
	
	/**
	 * 通过HTTP下载文件，读成byte[],返回
	 * 
	 * @param url
	 * @return
	 */
	public final static String downFileToByteArray(String url) throws Exception{
		
		java.io.InputStream is = null;
        java.io.ByteArrayOutputStream bs = null;  
		try{
			java.net.URL urlNet = new java.net.URL(url);  
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlNet.openConnection();  
			if(conn.getResponseCode() == 200){
				//此处用getContent有局限性
				//is = (java.io.InputStream) conn.getContent();  
				is = (java.io.InputStream) conn.getInputStream();  
		        bs = new java.io.ByteArrayOutputStream();  
		          
		        int buffer = 1024;  
		        byte[] b = new byte[buffer];  
		        int n = 0;  
		        while ((n = is.read(b, 0, buffer)) > 0) {  
		            bs.write(b, 0, n);  
		        }  
		        String s = new String(bs.toByteArray(), "UTF-8");
		        //return bs.toByteArray();
		        return s;
			}
		}catch(Exception e){
			e.printStackTrace();
			//logger.error("downFileToByteArray error ",e);
		}finally{
			if(bs != null)bs.close(); 
			if(is != null)is.close();  
		}
		return null;
	}
	
	/**
	 * 通过HTTP下载文件，读成byte[],返回
	 * 
	 * @param url
	 * @return
	 */
	public final static FileOutputStream downFileToByteArrayTest(String url) throws Exception{
		
		java.io.InputStream is = null;
        java.io.ByteArrayOutputStream bs = null;
        FileOutputStream fs = null;
		try{
			java.net.URL urlNet = new java.net.URL(url);  
			java.net.HttpURLConnection conn = (java.net.HttpURLConnection) urlNet.openConnection();  
			if(conn.getResponseCode() == 200){
				//此处用getContent有局限性
				//is = (java.io.InputStream) conn.getContent();  
				is = (java.io.InputStream) conn.getInputStream();  
		        fs = new FileOutputStream(new File(url));
		        return fs;
			}
		}catch(Exception e){
			e.printStackTrace();
			//logger.error("downFileToByteArray error ",e);
		}finally{
			if(bs != null)bs.close(); 
			if(is != null)is.close();  
		}
		return null;
	}
	
	/**
	 * 将特殊字符全部替换
	 * 
	 * @param url
	 * @return
	 */
	public final static String replaceSpecStr(String str) throws Exception{
		
		String result = "";
		
		if(UtilFunction.isEmpty(str)){
			return result;
		}
		
		String regEx="[\\s~～·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";  
        Pattern p = Pattern.compile(regEx);  
        Matcher m = p.matcher(str);  
        return m.replaceAll("");  
        
//		str.replace("`", );
//		str.replace("~～ ", );
//		str.replace("!", newChar);
//		str.replace("@", newChar);
//		str.replace("#", newChar);
//		str.replace("$", newChar);
//		str.replace("%", newChar);
//		str.replace("^", newChar);
//		str.replace("&", newChar);
//		str.replace("*", newChar);
//		str.replace("&", newChar);
//		str.replace("&", newChar);
		
		
//		return null;
	}
	
	/**
	 * 判断网站url是否携带http，没有自动附加上
	 * @param url
	 * @return
	 */
	public final static String isSiteUrl(Object url){
		if(UtilFunction.isEmpty(url)){
			return null;
		}
		
		StringBuffer result = new StringBuffer();
		
		String tempUrl = url.toString();
		if(tempUrl.indexOf("http") != -1){
			result.append(url);
		}else{
			result.append("http://").append(url);
		}
		return result.toString();
	}
	
	public static boolean isEmail(String email){
		if(UtilFunction.isEmpty(email)){
			return false;
		}
		
		 String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";    
		 Pattern regex = Pattern.compile(check);    
		 Matcher matcher = regex.matcher(email);    
		 boolean isMatched = matcher.matches(); 
		 return isMatched;
	}
	
	/**
	 * 判断array是否包含value
	 * @param array
	 * @param value
	 * @return
	 */
	public static boolean isContains(String array,String value){
		
		boolean result = false;
		if(UtilFunction.isEmpty(array)){
			return false;
		}else{
			if(array.indexOf(',') != -1){
				String temp  = array;
				String[] temp_array = temp.split(",");
				for(String s : temp_array){
					if(s.equals(value)){
						result = true;
						break;
					}
				}
			}else{
				if(array.equals(value)){
					result = true;
				}
			}
		}
		System.out.println("result==="+result);
		return result;
	}
	
	public static void main(String args[]) throws Exception{
		//System.out.println(replaceSpecStr("段位～～~$[『【；"));
		System.out.println(isSiteUrl("baidu.com"));
		System.out.println(isEmail("dffdfdf@qq.com"));
		
	}
	
	
}
