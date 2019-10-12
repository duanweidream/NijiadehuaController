package com.wooboo.dsp.base.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/**
 * ClassName:HttpUtils<br/> 
 * Function: http请求操作工具类，默认utf-8编码<br/>
 * 
 * @author jiahuan.ma<br/>
 * @version 1.0<br/>
 * @Date 2015-7-20 下午3:57:07<br/>
 * 
 */

public class HttpUtils {
	
	/**
	 * 连接超时时间
	 */
	private static final int CONNECTION_TIMEOUT_MS = 360000;

	/**
	 * 读取数据超时时间
	 */
	private static final int SO_TIMEOUT_MS = 360000;

	/**
	 * JSON格式数据编码
	 */
	private static final String CONTENT_TYPE_JSON_CHARSET = "application/json;charset=utf-8";

	/**
	 * xml格式数据编码
	private static final String CONTENT_TYPE_XML_CHARSET = "application/xml;charset=utf-8";*/

	/**
	 * httpclient读取内容时使用的字符集
	 */
	public static final String CONTENT_CHARSET = "UTF-8";
	public static final Charset UTF_8 = Charset.forName("UTF-8");

	private HttpUtils() {

	}

	/**
	 * 简单get调用
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String GET(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException {
		return simpleGetInvoke(url, params, CONTENT_CHARSET);
	}
	
	public static JSONObject postFromUrl4Json(String url,Map<String, String> map) {
		try{
			String responseJson = simplePostInvoke(url, map,CONTENT_CHARSET);
			if(null!=responseJson){
				try{
					return JSONObject.fromObject(responseJson);
				}catch(Exception e){
					return null;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	/**简单get调用，指定返回内容字符集
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public static String simpleGetInvoke(String url,
			Map<String, String> params, String charset)
			throws ClientProtocolException, IOException, URISyntaxException {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = buildHttpClient(false);
			HttpGet get = buildHttpGet(url, params);
			response = client.execute(get);
			assertStatus(response);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// 输入流关闭，同时会自动触发http连接的release
					String returnStr = EntityUtils.toString(entity, charset);
					return returnStr;
				}
			}else {
				get.abort();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(client);
		}
		return null;
	}

	/**
	 * POST 发送键值对
	 * 
	 * @param url
	 * @param params
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String POST(String url, Map<String, String> params)
			throws URISyntaxException, ClientProtocolException, IOException {
		return simplePostInvoke(url, params, CONTENT_CHARSET);
	}
	
	/** POST发送json串
	 * @param url
	 * @param json
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String POST(String url, String json)
			throws URISyntaxException, ClientProtocolException, IOException {
		return simplePostInvoke(url, json, CONTENT_CHARSET);
	}
	
	public static String POST_TEST(String url, String json)
			throws URISyntaxException, ClientProtocolException, IOException {
		return simplePostInvoke(url, json, CONTENT_CHARSET);
	}
	

	/**POST发送键值对，并指定返回内容字符集
	 * @param url
	 * @param params
	 * @param charset
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String simplePostInvoke(String url,
			Map<String, String> params, String charset)
			throws URISyntaxException, ClientProtocolException, IOException {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = buildHttpClient(false);
			HttpPost post = buildHttpPost(url, params);
			response = client.execute(post);
			assertStatus(response);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// 输入流关闭，同时会自动触发http连接的release
					String returnStr = EntityUtils.toString(entity, charset);
					return returnStr;
				}
			}else {
				post.abort();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(client);
		}
		return null;
	}
	
	/** 使用POST 发送json，并指定返回内容字符集
	 * @param url
	 * @param json
	 * @param charset
	 * @return
	 * @throws URISyntaxException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String simplePostInvoke(String url,
			String json, String charset)
			throws URISyntaxException, ClientProtocolException, IOException {
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;
		try {
			client = buildHttpClient(false);
			HttpPost post = buildHttpPost(url, json);
			response = client.execute(post);
			assertStatus(response);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					// 输入流关闭，同时会自动触发http连接的release
					String returnStr = EntityUtils.toString(entity, charset);
					return returnStr;
				}
			}else {
				post.abort();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			HttpClientUtils.closeQuietly(response);
			HttpClientUtils.closeQuietly(client);
		}
		return null;
	}

	/**
	 * 创建HttpClient
	 * 
	 * @param isMultiThread
	 * @return
	 */
	public static CloseableHttpClient buildHttpClient(boolean isMultiThread) {
		CloseableHttpClient client;
		if (isMultiThread)
			client = HttpClientBuilder
					.create()
					.setConnectionManager(
							new PoolingHttpClientConnectionManager()).build();
		else
			client = HttpClientBuilder.create().build();
		// 设置代理服务器地址和端口
		// client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
		return client;
	}

	/**
	 * 构建httpPost对象
	 * 
	 * @param url
	 * @param headers
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 */
	public static HttpPost buildHttpPost(String url, Map<String, String> params)
			throws UnsupportedEncodingException, URISyntaxException {
		HttpPost post = new HttpPost(url);
		setHttpHeader(post);
		HttpEntity he = null;
		if (params != null) {
			List<NameValuePair> formparams = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				formparams.add(new BasicNameValuePair(key, params.get(key)));
			}
			he = new UrlEncodedFormEntity(formparams, UTF_8);
			post.setEntity(he);
		}
		// 在RequestContent.process中会自动写入消息体的长度，自己不用写入，写入反而检测报错
		// setContentLength(post, he);
		return post;

	}
	
	/**
	 * 构建httpPost对象
	 * 
	 * @param url
	 * @param headers
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 */
	public static HttpPost buildHttpPost(String url, String json)
			throws UnsupportedEncodingException, URISyntaxException {
		HttpPost post = new HttpPost(url);
		setHttpHeader(post);
		StringEntity se = new StringEntity(json,CONTENT_CHARSET);
		se.setContentEncoding(CONTENT_CHARSET);   
        se.setContentType("application/json"); 
        post.setEntity(se);
		return post;
	}

	/**
	 * 构建httpGet对象
	 * 
	 * @param url
	 * @param headers
	 * @return
	 * @throws URISyntaxException
	 */
	public static HttpGet buildHttpGet(String url, Map<String, String> params)
			throws URISyntaxException {
		HttpGet get = new HttpGet(buildGetUrl(url, params));
		return get;
	}

	/**
	 * build getUrl str
	 * 
	 * @param url
	 * @param params
	 * @return
	 */
	private static String buildGetUrl(String url, Map<String, String> params) {
		StringBuffer uriStr = new StringBuffer(url);
		if (params != null) {
			List<NameValuePair> ps = new ArrayList<NameValuePair>();
			for (String key : params.keySet()) {
				ps.add(new BasicNameValuePair(key, params.get(key)));
			}
			uriStr.append("?");
			uriStr.append(URLEncodedUtils.format(ps, UTF_8));
		}
		return uriStr.toString();
	}

	/**
	 * 设置HttpMethod通用配置
	 * 
	 * @param httpMethod
	 */
	public static void setHttpHeader(HttpRequestBase httpMethod) {
		// 设置内容编码格式
		httpMethod.setHeader(HTTP.CONTENT_ENCODING, CONTENT_CHARSET);
		// 设置头部数据类型及编码
		httpMethod.setHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_JSON_CHARSET);
		// httpMethod.setHeader(HTTP.CHARSET_PARAM, CONTENT_CHARSET);
		// httpMethod.setHeader(HTTP.CONTENT_TYPE, CONTENT_TYPE_XML_CHARSET);
	}

	/**
	 * 设置成消息体的长度 setting MessageBody length
	 * 
	 * @param httpMethod
	 * @param he
	 */
	public static void setContentLength(HttpRequestBase httpMethod,
			HttpEntity he) {
		if (he == null) {
			return;
		}
		httpMethod.setHeader(HTTP.CONTENT_LEN,
				String.valueOf(he.getContentLength()));
	}

	/**
	 * 构建公用RequestConfig
	 * 
	 * @return
	 */
	public static RequestConfig buildRequestConfig() {
		// 设置请求和传输超时时间
		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(SO_TIMEOUT_MS)
				.setConnectTimeout(CONNECTION_TIMEOUT_MS).build();
		return requestConfig;
	}

	/**
	 * 强验证必须是200状态否则报异常
	 * 
	 * @param res
	 * @throws HttpException
	 */
	private static void assertStatus(HttpResponse res) throws IOException {
		int code = res.getStatusLine().getStatusCode();
		switch (code) {
		case HttpStatus.SC_OK:
		case HttpStatus.SC_NO_CONTENT:
			/* case HttpStatus.SC_CREATED: 
			 * case HttpStatus.SC_ACCEPTED: 
			 * case HttpStatus.SC_NON_AUTHORITATIVE_INFORMATION: 
			 * case HttpStatus.SC_RESET_CONTENT: 
			 * case HttpStatus.SC_PARTIAL_CONTENT: 
			 * case HttpStatus.SC_MULTI_STATUS:
			 */
			break;
		default:
			throw new IOException("服务器响应:"+code);
		}
	}
	
	/** 获取key对应的字符串value
	 * @param map
	 * @param key
	 * @return
	 */
	public static  String getParameterByMap(Map<String, Object> map, String key) {
        return (String) (map.get(key) != null
                && map.get(key).getClass().isArray() ? ((String[]) map.get(key))[0]
                : map.get(key));
    }

    /**获取key对应的数字value
     * @param map
     * @param key
     * @return
     */
    public static  Integer getParameterIntegerByMap(Map<String, Object> map,
            String key) {
        String v = getParameterByMap(map, key);
        return StringUtils.isNumeric(v) && !StringUtils.isEmpty(v) ? Integer.valueOf(v) : 0;
    }
    
    
    
    
    
}
