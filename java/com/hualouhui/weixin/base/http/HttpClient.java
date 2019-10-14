package com.hualouhui.weixin.base.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.util.Config;
import com.hualouhui.weixin.util.StringUtil;





public class HttpClient {

	private static final Logger logger = Logger.getLogger(HttpClient.class);
	private static int timeout = 5000;
	private static int retryExecutionCount = 2;
	protected static CloseableHttpClient httpClient = HttpClientFactory.createHttpClient(100,10,timeout,retryExecutionCount);
	private static final String[] supportedProtocols = new String[]{"TLSv1"};
	
	public static String readFromUrl(String uri,Map<String, String> map){
		return readFromUrl(uri, map, "utf-8");
	}
	
	public static String readFromUrl(String uri,Map<String, String> map,String charset){
		System.out.println(charset);
		
		boolean b = true;
    	StringBuffer sbuf = new StringBuffer("");
        for(Entry<String, String> en:map.entrySet()){
        	sbuf.append(b?("?"+en.getKey()+"="+en.getValue()):("&"+en.getKey()+"="+en.getValue()));
        	if(b){b=false;}
        }
    	String url = uri+sbuf.toString();
		logger.logInfo("HttpClient.postJsonString(uri="+url+")");
		RequestBuilder builder = RequestBuilder.get();
		builder.setCharset(Charset.forName(charset));
		builder.setHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());
		builder.setUri(url);
		String responseString =  execute(builder.build());
		logger.logInfo("responseString:"+responseString);
        return responseString;
	}
	
	
	
	public static String postJsonString(String uri,String json,Map<String, String> map){
		logger.logInfo("HttpClient.postJsonString(uri="+uri+",json="+json+")");
		RequestBuilder builder = RequestBuilder.post();
		builder.setHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_JSON.toString());
		builder.setUri(uri);
		if(null!=map){
			for(Entry<String, String> en:map.entrySet()){
				builder.addParameter(en.getKey(), en.getValue());
			}	
		}
		if(!StringUtil.isEmpty(json)){
			builder.setEntity(new StringEntity(json, Charset.forName("utf-8")));
		}
		
		String responseString =  execute(builder.build());
		logger.logInfo("responseString:"+responseString);
        return responseString;
	}
	public static String postXmlString(String uri,String xml,Map<String, String> map){
		logger.logInfo("HttpClient.postJsonString(uri="+uri+",xml="+xml+")");
		RequestBuilder builder = RequestBuilder.post();
		builder.setHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_XML.toString());
		builder.setUri(uri);
		if(null!=map){
			for(Entry<String, String> en:map.entrySet()){
				builder.addParameter(en.getKey(), en.getValue());
			}	
		}
		if(!StringUtil.isEmpty(xml)){
			builder.setEntity(new StringEntity(xml, Charset.forName("utf-8")));
		}
		String responseString =  execute(builder.build());
		logger.logInfo("responseString:"+responseString);
        return responseString;
	}
	public static String post4keystore(String uri,String xml,Map<String, String> map) throws Exception{
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		String path = Thread.currentThread().getContextClassLoader().getResource("config/apiclient_cert.p12").getPath();
		 FileInputStream instream = new FileInputStream(new File(path));
		 keyStore.load(instream,Config.getValue("weixin.pay.mchid").toCharArray());
		 instream.close();
		 CloseableHttpClient client = createKeyMaterialHttpClient(keyStore, Config.getValue("weixin.pay.mchid"), supportedProtocols, timeout, retryExecutionCount);
		 RequestBuilder builder = RequestBuilder.post();
	        builder.setHeader(HttpHeaders.CONTENT_TYPE,ContentType.APPLICATION_XML.toString());
			builder.setUri(uri);
			if(null!=map){
				for(Entry<String, String> en:map.entrySet()){
					builder.addParameter(en.getKey(), en.getValue());
				}	
			}
			if(!StringUtil.isEmpty(xml)){
				builder.setEntity(new StringEntity(xml, Charset.forName("utf-8")));
			}
	        
	        
	        CloseableHttpResponse response = client.execute(builder.build());
	        return EntityUtils.toString(response.getEntity());
	}
	
	public static CloseableHttpClient createKeyMaterialHttpClient(KeyStore keystore,String keyPassword,String[] supportedProtocols,int timeout,int retryExecutionCount) {
		try {
			SSLContext sslContext = SSLContexts.custom().useSSL().loadKeyMaterial(keystore, keyPassword.toCharArray()).build();
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext,supportedProtocols,
	                null,SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
			return HttpClientBuilder.create()
									.setDefaultSocketConfig(socketConfig)
									.setSSLSocketFactory(sf)
									.build();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnrecoverableKeyException e) {
			e.printStackTrace();
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String execute(HttpUriRequest request){
		try {
			CloseableHttpResponse response = httpClient.execute(request);
			return EntityUtils.toString(response.getEntity());
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public static void main(String[] args){
		Map<String, String> map = new HashMap<>();
		map.put("hello", "kitty");
		String string = HttpClient.readFromUrl("http://test.wx.wobmob.cn/rest/2.0/campaign/rule", map);
        System.out.println(string);
	}
	
}
