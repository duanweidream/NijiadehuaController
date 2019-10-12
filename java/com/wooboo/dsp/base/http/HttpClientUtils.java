/**
 * <p>Title: HttpClientUtil.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: emar</p>
 * @author kingdom
 * @date 2013-5-30
 * @version 1.0
 */
package com.wooboo.dsp.base.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

import com.wooboo.dsp.base.log.Logger;

import net.sf.json.JSONObject;


/**
 * <p>
 * Title: HttpClientUtil
 * </p>
 * <p>
 * Description:TODO
 * </p>
 * <p>
 * Company: emar
 * </p>
 * 
 * @author cuidd
 * @date 2013-5-30
 */
public class HttpClientUtils {


    private static Logger logger = Logger.getLogger(HttpClientUtils.class);
    public static String createUrl(String path,Map<String,String> map) {
    	boolean b = true;
    	StringBuffer sbuf = new StringBuffer(path);
    	int i=path.indexOf("?");
    	sbuf.append(i==-1?"?":"&");
        for(Entry<String, String> en:map.entrySet()){
        	String param = en.getKey();
        	String value = en.getValue();
        	sbuf.append(b?(param+"="+value):("&"+param+"="+value));
        	if(b){b=false;}
        }
		return sbuf.toString();
	}
    
    public static String readFromURL(String path,Map<String,String> map, String encoding) {
    	boolean b = true;
    	StringBuffer sbuf = new StringBuffer("");
        for(Entry<String, String> en:map.entrySet()){
        	String param = en.getKey();
        	String value = en.getValue();
        	sbuf.append(b?("?"+param+"="+value):("&"+param+"="+value));
        	if(b){b=false;}
        }
    	String url = path+sbuf.toString();
		return readFromURL(url, encoding, 1024, 5000);
	}
    public static String sendGet(String path){
    	return readFromURL(path, null);
    }
    public static String readFromURL(String path, String encoding) {
		return readFromURL(path, encoding, 1024, 5000);
	}

    public static String readFromURL(String path, String encoding, int init, int timeout)  {
		return readFromURL(path, encoding, init, timeout, null);
	}
    
	public static String readFromURL(String path, String encoding, int init, int timeout, Proxy proxy) {
		logger.info("send get url  :"+path);
		long a = System.currentTimeMillis();
		StringBuffer bf = new StringBuffer();
		URL url = null;
		URLConnection con = null;
		BufferedReader in = null;
		if (encoding == null) {
			encoding = "UTF-8";
		}
		try {
			url = new URL(path);
			if (proxy != null) {
				con = url.openConnection(proxy);
			} else {
				con = url.openConnection();
			}
			con.setReadTimeout(timeout);
			CharBuffer chars = CharBuffer.allocate(init);
			con.setRequestProperty("User-Agent","Mozilla/5.0 (iPhone; CPU iPhone OS 7_1_2 like Mac OS X) AppleWebKit/537.51.2 (KHTML, like Gecko) Version/7.0 Mobile/11D257 Safari/9537.53"); 
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), encoding));
			while (in.read(chars) > 0) {
				chars.flip();
				bf.append(chars);
				chars.clear();
			}
		} catch (Exception e) {
			logger.info("Get url["+path+"] error! "+e.getMessage());
			e.printStackTrace();
			return "false";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
			con = null;
			url = null;
		}
		logger.info("send response "+(System.currentTimeMillis()-a)+": ms  "+bf.toString());
		return bf.toString();
	}
	
	public static JSONObject getFromUrl4Json(String url,Map<String, String> map){
		String responseJson = readFromURL(url, map, "utf-8");
		if(null!=responseJson){
			try{
				return JSONObject.fromObject(responseJson);
			}catch(Exception e){
				return null;
			}
			
		}
		return null;
	}
	
	
	public static JSONObject postFromUrl4Json(String url,Map<String, String> map){
		String responseJson = postFromUrl(url, map,"utf-8");
		if(null!=responseJson){
			try{
				return JSONObject.fromObject(responseJson);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	public static String postFromUrl(String url,Map<String, String> map,String charset){
		if(map!=null){
			NameValuePair[] ps =new NameValuePair[map.size()];
			int i=0;
			StringBuffer sbuf =new StringBuffer();
			boolean b = true;
			for(Entry<String, String> e:map.entrySet()){
				NameValuePair p = new NameValuePair();
				p.setName(e.getKey());
				p.setValue(e.getValue());
				sbuf.append(b?(e.getKey()+"="+e.getValue()):("&"+e.getKey()+"="+e.getValue()));
				if(b){b=false;}
				ps[i]=p;
				i++;
			}
			logger.info("send url:"+url+"?"+sbuf.toString());
			return postFromURL(url, ps,charset);
		}else{
			logger.info("Array of parameters may not be null");
		}
		return null;
	}
	
	
	
	
	public static String postFromURL(String url, NameValuePair[] msg,String charset) {
		logger.info("send post url  :"+url);
		
		String strURL = url;
		String result = null;
		PostMethod post = null;
		try {
			post = new PostMethod(strURL);
			//"ISO-8859-1"
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,charset);
			//post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
			post.setRequestBody(msg);
			
			System.out.println(charset);
			
			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(post);
			if (post.getStatusCode() == HttpStatus.SC_OK) {
				result = post.getResponseBodyAsString();
			} else {
				result = post.getStatusLine().toString();
			}
		} catch (Exception e) {
			logger.info("postFromURL Error!" + e.getMessage());
		} finally {
			post.releaseConnection();
		}
		logger.info("send response  :"+result);
		return result;
	}
	
	public static void main(String[] args) throws Exception{
		
		String response = HttpClientUtils.readFromURL("http://223.202.209.23:8083/wb/2.0/frontpage/report", "utf-8");
		//System.out.println(response);
		
//		StringBuffer sbuf = new StringBuffer();
//		sbuf.append("adId=23");
//		sbuf.append("&appkey=AWAAB718ZDIG1173B3TO3JUVL80MCXIEHB");
//		sbuf.append("&ac=1");
//		sbuf.append("&adIds=");			

	   
	    
	   // System.out.println(HttpClientUtils.postFromUrl("http://localhost/AdService/android/motion.do", m));
	    
		
		 
		 
		//System.out.println("\u6210\u529f");
		//String res = HttpClientUtils.readFromURL("http://iphone.myzaker.com/zaker/apps_telecom.php?for=test&m=test1234", "ISO-8859-1");
		//	byte[] t = res.getBytes();
		//	char[] c = getChars(t);
		//	String s = new String(c);
			//for(byte s:t){
			//	System.out.println(s);
			//}
			//String str=new String(res.getBytes("gb2312"),"UTF-8");
			//System.out.println(s);
		
		
		//HttpClientUtils.readFromURL("http://www.ddbaiddu.com.cn",null);
	}



}
