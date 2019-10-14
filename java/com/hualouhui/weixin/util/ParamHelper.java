package com.hualouhui.weixin.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.action.base.MyCookie;
import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.base.rest.Result;

public class ParamHelper {

	public static final Logger logcat = Logger.getLogger(ParamHelper.class);

    public static final int FILE_SIZE_MAX = 2 * 1024 * 1024;
    public static final int FILE_NUMBER_MAX = 5;
    public HttpServletRequest request;
    public HttpServletResponse response;
    public Result result;
    public static ParamHelper newInstance() {
        return new ParamHelper();// for mock and update
    }
    
    protected ParamHelper(){};
    
    public Parameters parse(Map<String, String[]> parameterMap, List<FileItem> fileItemList, String myCookieValue,String cookie_shopping_cart,String cuisine_shopping_cart,String outside_shopping_cart,HttpServletRequest request,HttpServletResponse response) {
    	this.request=request;
        this.response=response;
        
        
    	return  parse(parameterMap, fileItemList, myCookieValue,cookie_shopping_cart,cuisine_shopping_cart,cuisine_shopping_cart,toQueryString(request));
    }
    public Parameters parse(Map<String, String[]> parameterMap, List<FileItem> fileItemList, String myCookieValue,String cookie_shopping_cart,String cuisine_shopping_cart,String outside_shopping_cart,String queryString) {
        Parameters parameters = new Parameters();
        if (fileItemList != null) {
            for (FileItem item : fileItemList) {
            	String key = item.getFieldName();
                if (item.isFormField()) {
                    String value = item.getString();
    				parameters.addField(key, value);
                }else{
                    String fileName = item.getName();
                    byte[] fileData = getUploadedFileData(key);
                    if(fileData!=null&&fileData.length>0){
                    	String uri = getUploadedFileUri(fileName, fileData);
                        //System.out.println("uri="+uri+"  fileName:"+fileName+"  key:"+key);
                    	parameters.addFile(key, uri, fileData);
                    }
                    
                }
            }	
        }
        if (parameterMap != null) {
            for (String key : parameterMap.keySet()) {
                String[] values = parameterMap.get(key);
                if(values!=null){
                	List<String> list = new ArrayList<String>();
                	for(String s:values){
                		if(s!=null){
                			list.add(s);
                		}
                	}
                	if(list.size()>0){
                		if(list.size()==1){
	                   		parameters.addField(key, list.get(0));	
	                   	}else{
	                   	 String[] array = (String[])list.toArray(new String[list.size()]);
	                   		parameters.addArray(key,array);
	                   	}
                	}
                }
                
                //if (values != null && values.length > 0 && values[0] != null) {
                //    parameters.addField(key, values[0]);	
                //}
            }	
        }
        if (myCookieValue != null) {
            parameters.setMyCookieValue(myCookieValue);
        }
        if (cookie_shopping_cart != null) {
            parameters.setCookie_shopping_cart(cookie_shopping_cart);
        }
        if (cuisine_shopping_cart != null) {
            parameters.setCuisine_shopping_cart(cuisine_shopping_cart);
        }
        if (outside_shopping_cart != null) {
            parameters.setOutside_shopping_cart(outside_shopping_cart);
        }
        parameters.setRequest(request);
        parameters.addField("remote_client_ip", getIpAddr());
        parameters.addField("queryString", queryString);
        parameters.addField("remote_uri", request.getRequestURI());
        parameters.addField("request.servername", request.getServerName());
        return parameters;
    }
        
    private String getUploadedFileUri(String fileName, byte[] fileData) {
        long partTimestamp = System.currentTimeMillis() % 10000 + (long)(Math.random() * 1000);
        String md5 = ByteHelper.calculateMd5(fileData);
        int surfixStart = fileName.lastIndexOf('.');
        String surfix = (surfixStart <= 0) ? "" : fileName.substring(surfixStart);
//        if (isDuapp) {
//            return partTimestamp+md5+surfix;
//        }
        String filedir=getFileDir();
        return filedir+partTimestamp+md5+surfix;
    }
    
    private String getFileDir(){
    	SimpleDateFormat sdf = new SimpleDateFormat("/yyyy/MM/");
		return sdf.format(new Date());
    }
    

    public ServletFileUpload createFileUploader() {
        FileItemFactory dff = createFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(dff);
        sfu.setSizeMax(FILE_SIZE_MAX * FILE_NUMBER_MAX);
        sfu.setFileSizeMax(FILE_SIZE_MAX);
        return sfu;
    }
    
    protected FileItemFactory createFileItemFactory() {
        return memoryFileItemFactory;
    }

    protected String getUploadedFileUrl(String key) {
        return memoryFileItemFactory.getUploadedFileUrl(key);
    }    
    
    protected byte[] getUploadedFileData(String key) {
        return memoryFileItemFactory.getUploadedFileData(key);
    }
    
    private MemoryFileItemFactory memoryFileItemFactory = new MemoryFileItemFactory();
    
    public void execute(Map<String, String[]> parameterMap, List<FileItem> fileItemList, String myCookieValue,String cookie_shopping_cart,String cuisine_shopping_cart,String outside_shopping_cart,HttpServletRequest request,HttpServletResponse response){
    	 //参数处理
    	 Parameters params = parse(parameterMap, fileItemList, myCookieValue,cookie_shopping_cart,cuisine_shopping_cart,outside_shopping_cart,request,response);
    	 loggerParam(params);
    	 //业务处理
    	 ActionBase action = ActionBase.createInstance(request.getRequestURI());
    	 result = action.execute(params);
    	 addCookie();
    	 view(params);
    }
    
    private void loggerParam(Parameters params){
    	logcat.trace(request.getRequestURI()+"?"+StringUtil.dealNull(params.getFieldValue("queryString")));
    }
    
    private void loggerParam_bak(Parameters params){
    	StringBuffer paramStr = new StringBuffer();        
        for (String key : params.getFieldKeys()) {
            String value = params.getFieldValue(key);
            paramStr.append(paramStr.length() > 0 ? ", " : "").append(key).append("=").append(value);
        }
        //logger.debug(request.getRequestURI()+paramStr.toString());
    }
    
    public void addCookie(){
    	//用户cookie
    	if(result.getCookie()!=null){
        	Cookie cookie = new Cookie(CookieHelper.FIELD_NAME, result.getCookie());
        	cookie.setPath("/");
        	cookie.setMaxAge(-1);//永久不过期
        	response.addCookie(cookie);
    	}
    	//用户cookie
    	if(result.getCookie_shopping_cart()!=null){
        	Cookie cookie = new Cookie(CookieHelper.COOKIE_SHOPPING_CART, result.getCookie_shopping_cart());
        	cookie.setPath("/");
        	cookie.setMaxAge(-1);//永久不过期
        	response.addCookie(cookie);
    	}
    	//用户cookie
    	if(result.getCuisine_shopping_cart()!=null){
        	Cookie cookie = new Cookie(CookieHelper.CUISINE_SHOPPING_CART, result.getCuisine_shopping_cart());
        	cookie.setPath("/");
        	cookie.setMaxAge(-1);//永久不过期
        	response.addCookie(cookie);
    	}
    	//用户cookie
    	if(result.getOutside_shopping_cart()!=null){
        	Cookie cookie = new Cookie(CookieHelper.OUTSIDE_SHOPPING_CART, result.getOutside_shopping_cart());
        	cookie.setPath("/");
        	cookie.setMaxAge(-1);//永久不过期
        	response.addCookie(cookie);
    	}
    	//其他cookie
    	for(MyCookie mc:result.getCookieList()){
    		Cookie cookie = new Cookie(mc.name, mc.value);
        	cookie.setPath("/");
        	cookie.setMaxAge(mc.maxage);
        	response.addCookie(cookie);
    	}
    	
    	
    }
    
    private void view(Parameters param){
		try {
			if(!StringUtil.isEmpty(result.redirect)){
				Map<String,String> map =  param.getFieldKeyValues();
				for(Entry<String, String> en:map.entrySet()){
				}
				redirect(result.redirect);
			}else{
				outPrint(result);
				//outPrint(StringUtil.dealNull(result.getResponseString(),""));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    
    private void redirect(String uri) throws IOException, ServletException{
       
       if(result.hasError()){
    	   response.sendRedirect("/error/error.jsp");
       }else{
    	   if("forward".equals(result.type)){
    		   for(Entry<String, Object> en:result.map.entrySet()){
    	    	   request.setAttribute(en.getKey(), en.getValue());
    	       }
    		   request.getRequestDispatcher(uri).forward(request, response);
    	   }else{
    		   boolean b =true;
    		   StringBuffer sbuf = new StringBuffer("");
    		   for(Entry<String, Object> en:result.map.entrySet()){
    			   sbuf.append((b?"?":"&")+en.getKey()+"="+en.getValue());
    			   if(b){b=false;}
    	       }
    		   response.sendRedirect(uri+sbuf.toString());
    	   }
    	   //response.sendRedirect(uri);
       }
    }
    public static String toQueryString(HttpServletRequest request) {
		StringBuffer bf = new StringBuffer(100);
		String common = "";
		String name = "";
		String value = "";
		String[] temp=null;
		Enumeration <String> en = request.getParameterNames();
		while (en.hasMoreElements()) {
			name = StringUtil.dealNull(en.nextElement());
			
			temp=request.getParameterValues(name);
			if(temp.length<=1){
				value = StringUtil.dealNull(request.getParameter(name));
			}else{
				String t="";
				StringBuffer vBuffer=new StringBuffer();
				for(String s:temp){
					vBuffer.append(t+s);
					t=",";
				}
				value=vBuffer.toString();
			}
			if (!StringUtil.isEmpty(value)) {
				bf.append(common);
				bf.append(name);
				bf.append("=");
				//StringUtil
				bf.append(StringUtil.encode(value, StringUtil.dealNull(request.getCharacterEncoding(), "utf-8")));
				common = "&";
			}
		}
		return bf.toString();
	}
    
    
    
    public void outPrint(Result r) throws IOException{
      if("image/jpeg".equals(r.contentType)){
    	  imageIo();
      }else{
    	  outPrint(StringUtil.dealNull(r.getResponseString(),""));
      }
    }
	private void outPrint(String outJson) throws IOException{
		response.setContentType("application/json");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		//out.println(EscapeUtil.escape(outJson.toString()));
		out.println(outJson);
		out.flush();
		out.close();
	}
	
	public void imageIo() throws IOException{
		System.out.println("==========================");
		try {
			BufferedImage bufferImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferImage.createGraphics();
			g.setBackground(Color.BLUE);
			g.drawLine(0, 0, 1, 1);
			g.dispose();
			bufferImage.flush();
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			request.setCharacterEncoding("UTF-8");
			
			ImageIO.write(bufferImage, "jpg", response.getOutputStream());
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			//response.getOutputStream().close();
		}
	}
	
	
	
	public final static String IP1 = "x-forwarded-for";
	public final static String IP2 = "Proxy-Client-IP";
	public final static String IP3 = "WL-Proxy-Client-IP";
	public final static String UNKNOWN = "unknown";
	public final static String COMMA = ",";
	public String getIpAddr() {
		String ip = request.getHeader(IP1);
		if (ip == null || ip.length() == 0|| UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(IP2);
		}
		if (ip == null || ip.length() == 0|| UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getHeader(IP3);
		}
		if (ip == null || ip.length() == 0|| UNKNOWN.equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.contains(COMMA)) {
			int i = ip.indexOf(COMMA);
			ip = ip.substring(0, i);
		}
		return ip;
	}
	
    
}
