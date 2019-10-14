package com.hualouhui.weixin.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HttpContext;

import com.hualouhui.weixin.base.log.Logger;



//import sun.misc.BASE64Encoder;

public class SmsHelper {

	private static final Logger logger = Logger.getLogger(SmsHelper.class);
	private static final String message_key=Config.getValue("message.key");
	private static final String enabled = Config.getValue("message.enabled");
	
    public static void sendMessageTo(String phone, String message) throws Exception{
    	if ("true".equalsIgnoreCase(enabled)) {
    		 DefaultHttpClient client = new DefaultHttpClient();
    	        client.addRequestInterceptor(new HttpRequestInterceptor() {
    	            @Override
    	            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
    	                request.addHeader("Accept-Encoding", "gzip");
    	                //request.addHeader("Authorization", "Basic " + new BASE64Encoder().encode("api:0ee84b8110ac16e18ca3168055c48ddf".getBytes("utf-8")));
    	                //request.addHeader("Authorization", "Basic " + new BASE64Encoder().encode("api:f980ea2ebea3cfe11c81a74bd58c06e0".getBytes("utf-8")));
    	                //request.addHeader("Authorization", "Basic " + new BASE64Encoder().encode("api:e7f48319bf1a2b206dcf6a610c41c773".getBytes("utf-8")));
    	                request.addHeader("Authorization", "Basic " + Base64.encodeBase64String("api:4887c06083f87b3008c7d18ecb453d5d".getBytes("utf-8")));
    	            }
    	        });
    	        client.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
    	        client.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
    	        HttpPost request = new HttpPost("https://sms-api.luosimao.com/v1/send.json");
    	        ByteArrayOutputStream bos = null;
    	        InputStream bis = null;
    	        byte[] buf = new byte[10240];
    	        String content = null;
    	        try {
    	            List<NameValuePair> params = new ArrayList<NameValuePair>();
    	            params.add(new BasicNameValuePair("mobile", phone));
    	            //params.add(new BasicNameValuePair("message", message+"【花千束】"));
    	            params.add(new BasicNameValuePair("message", message+"【齐鲁美驿】"));
    	            request.setEntity(new UrlEncodedFormEntity(params, "utf-8"));


    	            HttpResponse response = client.execute(request);

    	            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
    	                bis = response.getEntity().getContent();
    	                Header[] gzip = response.getHeaders("Content-Encoding");

    	                bos = new ByteArrayOutputStream();
    	                int count;
    	                while ((count = bis.read(buf)) != -1) {
    	                    bos.write(buf, 0, count);
    	                }
    	                bis.close();

    	                if (gzip.length > 0 && gzip[0].getValue().equalsIgnoreCase("gzip")) {
    	                    GZIPInputStream gzin = new GZIPInputStream(new ByteArrayInputStream(bos.toByteArray()));
    	                    StringBuffer sb = new StringBuffer();
    	                    int size;
    	                    while ((size = gzin.read(buf)) != -1) {
    	                        sb.append(new String(buf, 0, size, "utf-8"));
    	                    }
    	                    gzin.close();
    	                    bos.close();

    	                    content = sb.toString();
    	                } else {
    	                    content = bos.toString();
    	                }

    	                System.out.println(content);
    	            } else {
    	                System.out.println("error code is " + response.getStatusLine().getStatusCode());
    	            }
//    	            return content;

    	        } finally {
    	            if (bis != null) {
    	                try {
    	                    bis.close();// 最后要关闭BufferedReader
    	                } catch (Exception e) {
    	                }
    	            }
    	        }
    	}else{
    		logger.info("message enabled:%s", enabled);
    	}
    }
    
    public void sendTo(List<String> phone_numbers,String message) throws Exception{
    	for(String phone_number:phone_numbers){
    		sendMessageTo(phone_number, message);
    	}
    }
    
    public static void main(String[] args)throws Exception {
        sendMessageTo("18610921396", "hello【花千束】");
    }
}
