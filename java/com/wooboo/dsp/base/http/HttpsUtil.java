package com.wooboo.dsp.base.http;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;



public class HttpsUtil {
	
	
	public static final Charset UTF_8 = Charset.forName("UTF-8");
	
	private static class TrustAnyTrustManager implements X509TrustManager {

		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
		}

		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	/**
	 * post方式请求服务器(https协议)
	 * 
	 * @param url
	 *            请求地址
	 * @param content
	 *            参数
	 * @param charset
	 *            编码
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static String POST(String url, String postJson)
			throws NoSuchAlgorithmException, KeyManagementException,
			IOException {
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, new TrustManager[] { new TrustAnyTrustManager() },
				new java.security.SecureRandom());

		URL console = new URL(url);
		HttpsURLConnection conn = (HttpsURLConnection) console.openConnection();
		conn.addRequestProperty("Content-Type", "application/json");
		conn.setConnectTimeout(30000);
		conn.setReadTimeout(30000);
		conn.setSSLSocketFactory(sc.getSocketFactory());
		conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
		conn.setDoOutput(true);
		conn.connect();
		DataOutputStream out = new DataOutputStream(conn.getOutputStream());
		out.write(postJson.getBytes("utf-8"));
		// 刷新、关闭
		out.flush();
		out.close();
		InputStream is = conn.getInputStream();
		if (is != null) {
			ByteArrayOutputStream outStream = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				outStream.write(buffer, 0, len);
			}
			is.close();
			return new String(outStream.toByteArray(),UTF_8);
		}
		return null;
	}

	public static void main(String[] args) throws KeyManagementException,
			NoSuchAlgorithmException, IOException {
		String url = "https://120.196.166.158/toolbar";

		// {"ad_id":"2151","ad_url":"http://wwww.wobmob.cn","img_url":"http://test.images.petany.com/bee/20170414/1000_1FP0BL9SN5QK6DK5U.jpg","ad_content":"toolbal_640*100_1","oper_type":"A"}

		HashMap<String, String> params = new HashMap<String, String>();
		params.put("ad_id", "2153");
		params.put("ad_url", "http://wwww.wobmob.cn");
		params.put("img_url",
				"http://test.images.petany.com/bee/20170414/1000_1FP0BL9SN5QK6DK5U.jpg");
		params.put("ad_content", "toolbal_640*100_1");
		params.put("oper_type", "A");

		String postJson = net.sf.json.JSONObject.fromObject(params).toString();
		
		//{"id":2152,"code"=200,message =success update 2152}
		String res = POST(url, postJson);

		System.out.println(res);

	}

}
