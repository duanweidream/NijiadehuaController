/**
 * <p>Title: HttpClientUtil.java</p>
 * <p>Description:TODO</p>
 * <p>Copyright: Copyright (c) </p>
 * <p>Company: emar</p>
 * @author kingdom
 * @date 2013-5-30
 * @version 1.0
 */
package com.nijiadehua.api.base.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.CharBuffer;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.client.params.CookiePolicy;

import com.nijiadehua.api.base.log.Logger;



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
	
    public static String readFromURL(String path,Map<String,String> map, String encoding) {
    	boolean b = true;
    	StringBuffer sbuf = new StringBuffer("");
        for(Entry<String, String> en:map.entrySet()){
        	sbuf.append(b?("?"+en.getKey()+"="+en.getValue()):("&"+en.getKey()+"="+en.getValue()));
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
		logger.logInfo("readFromURL(path="+path+")");
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
			in = new BufferedReader(new InputStreamReader(con.getInputStream(), encoding));
			while (in.read(chars) > 0) {
				chars.flip();
				bf.append(chars);
				chars.clear();
			}
		} catch (Exception e) {
			logger.logDebug("Get url["+path+"] error! "+e.getMessage());
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
		logger.logDebug("send response "+(System.currentTimeMillis()-a)+": ms  "+bf.toString());
		return bf.toString();
	}
	
	public static String postFromUrl(String url,Map<String, String> map){
		
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
			logger.logDebug("send url:"+url+"?"+sbuf.toString());
			return postFromURL(url, ps);
		}else{
			logger.logDebug("Array of parameters may not be null");
		}
		return null;
	}
	
	
	
	
	public static String postFromURL(String url, NameValuePair[] msg) {
		logger.logDebug("send post url  :"+url);
		String strURL = url;
		String result = null;
		PostMethod post = null;
		try {
			post = new PostMethod(strURL);
			post.setRequestBody(msg);
			post.getParams().setParameter("http.protocol.cookie-policy",CookiePolicy.BROWSER_COMPATIBILITY);
			
			HttpClient httpclient = new HttpClient();
			httpclient.executeMethod(post);
			//post.setRequestHeader("Cookie", CookieStr);
			
			
			
			if (post.getStatusCode() == HttpStatus.SC_OK) {
				Cookie[] cookies = httpclient.getState().getCookies();
				for(Cookie cookie:cookies){
					System.out.println(cookie.toString());
					System.out.println(cookie.getName()+"============================================="+cookie.getValue()+"   "+cookie.getPath()+"  "+cookie.getDomain());
				}
				
				//post.get
				result = post.getResponseBodyAsString();
			} else {
				result = post.getStatusLine().toString();
			}
		} catch (Exception e) {
			logger.logDebug("postFromURL Error!" + e.getMessage());
		} finally {
			post.releaseConnection();
		}
		logger.logDebug("send response  :"+result);
		return result;
	}
	
	private static String listToStr(List<String> list){
		StringBuffer sbuf = new StringBuffer();
		for(String str:list){
			sbuf.append(str+" ");
		}
		return sbuf.toString();
	}
	
	
	
	public static String postXml(String uri,String xmlInfo) {
	        try {
	        	logger.logDebug("postXml(uri="+uri+")");
	        	logger.logDebug("postXml(xml="+xmlInfo+")");
	            URL url = new URL(uri);
	            URLConnection con = url.openConnection();
	            con.setDoOutput(true);
	            con.setRequestProperty("Pragma:", "no-cache");
	            con.setRequestProperty("Cache-Control", "no-cache");
	            con.setRequestProperty("Content-Type", "text/xml");

	            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());    
	            out.write(new String(xmlInfo.getBytes("ISO-8859-1")));
	            out.flush();
	            out.close();
	            BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	            StringBuffer sbuf = new StringBuffer();
	            String line = "";
	            for (line = br.readLine(); line != null; line = br.readLine()) {
	            	sbuf.append(line);
	            }
	            return sbuf.toString();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	public static String postFromUrlCookie(String url,Map<String, String> map,String cookie) {
		if(map!=null){
			NameValuePair[] ps =new NameValuePair[map.size()];
			int i=0;
			StringBuffer sbuf =new StringBuffer();
			boolean b = true;
			for(Entry<String, String> e:map.entrySet()){
				NameValuePair p = new NameValuePair();
				p.setName(e.getKey());
				//p.setValue(StringUtil.encode(e.getValue(), "utf-8"));
				p.setValue(e.getValue());
				sbuf.append(b?("?"+e.getKey()+"="+e.getValue()):("&"+e.getKey()+"="+e.getValue()));
				if(b){b=false;}
				ps[i]=p;
				i++;
			}
			logger.logDebug("send url:"+url+sbuf.toString());
			return postFromURLCookie(url, ps,cookie);
		}else{
			logger.logDebug("Array of parameters may not be null");
		}
		return null;
	}
	public static String postFromURLCookie(String url, NameValuePair[] msg ,String cookie) {
		logger.logDebug("send post url  :"+url);
		String strURL = url;
		String result = null;
		PostMethod post = null;
		try {
			post = new PostMethod(strURL);
			post.setRequestBody(msg);
			post.addRequestHeader("Cookie", cookie);
			post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "UTF-8");
			HttpClient httpclient = new HttpClient();
			httpclient.getHttpConnectionManager().getParams()  
            .setConnectionTimeout(5000);  
			httpclient.getHttpConnectionManager().getParams().setSoTimeout(5000);  
			httpclient.executeMethod(post);
			if (post.getStatusCode() == HttpStatus.SC_OK) {
				result = post.getResponseBodyAsString();
			} else {
				result = post.getStatusLine().toString();
			}
		} catch (Exception e) {
			logger.logDebug("postFromURL Error!" + e.getMessage());
		} finally {
			post.releaseConnection();
		}
		logger.logDebug("send response  :"+result);
		return result;
	}
	
	public static void main(String[] args){
		String str = "http://www.symria.com.cn/android/init.do?p=8%2FKUwuD%2B8bq4JgsmlHAmfxt9YjowCJJ8tpU6UOt39yEE4xCq9qE68W1JAKDS+PfKKU6j825EW0UCVnmQPfG9GvIzNmCkHGBBLCuKvzKgPc6erS2iZ9cQcBrPy+%2FP6esP0WaxiquL7YumadX60gcirUHbF2Asoh%2F6uaN4F%2FxGTHtT8JdcYPq60D+DA9UBd6wcgxVWGnHxMP7zWK4hZ5e3dUa%2Fz%2BPTz2MG4JxU1B13EPGT7j2jYU9+xV1XlA%3D%3D";
		String res = HttpClientUtils.readFromURL(str, null);
		System.out.println(res);
		//HttpClientUtils.readFromURL("http://www.ddbaiddu.com.cn",null);
	}

}
