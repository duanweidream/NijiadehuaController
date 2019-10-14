package com.hualouhui.weixin.action.base;



import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.constants.ContentType;
import com.hualouhui.weixin.util.CookieHelper;
import com.hualouhui.weixin.util.ParamHelper;
import com.hualouhui.weixin.util.Parameters;
import com.hualouhui.weixin.util.StringUtil;

/**
 * 功能描述: api请求调度入口地址
 * @author luo
 * @date 2015.08.13
 */
public class Dispatcher extends HttpServlet implements Servlet {
	private static Logger logger = Logger.getLogger(Dispatcher.class);
	private static final long serialVersionUID = 1L;
	public void init() throws ServletException {
		
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		logger.logInfo(request.getRequestURI()+"?"+toQueryString(request));
		request.setCharacterEncoding("UTF-8");
		ParamHelper paramHelper = ParamHelper.newInstance();
		String myCookieValue = null;
		String cookie_shopping_cart = null;
		String cuisine_shopping_cart = null;
		String outside_shopping_cart = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		        if (CookieHelper.FIELD_NAME.equals(cookie.getName())) {
		        myCookieValue = cookie.getValue();
		    }
		    if (CookieHelper.COOKIE_SHOPPING_CART.equals(cookie.getName())) {
		       cookie_shopping_cart = cookie.getValue();
		    }
		    if (CookieHelper.CUISINE_SHOPPING_CART.equals(cookie.getName())) {
			       cuisine_shopping_cart = cookie.getValue();
			}
		    if (CookieHelper.OUTSIDE_SHOPPING_CART.equals(cookie.getName())) {
			       outside_shopping_cart = cookie.getValue();
			}
		}    
		}
		
		List<FileItem> fileItemList = null;
	    if (ServletFileUpload.isMultipartContent(request)) {
	        ServletFileUpload sfu = paramHelper.createFileUploader();
	        try {
				fileItemList = sfu.parseRequest(request);
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	    }
	    Map<String, String[]> parameterMap = request.getParameterMap();
	    Parameters params = paramHelper.parse(parameterMap, fileItemList, myCookieValue,cookie_shopping_cart,cuisine_shopping_cart,outside_shopping_cart, request, response);
	    ActionBase action = ActionBase.createInstance(request.getRequestURI());
	    Result result = action.execute(params);
	    view(result,request,response);
		
	}

	private void view(Result result,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		//用户cookie
    	if(result.getCookie()!=null){
        	Cookie cookie = new Cookie(CookieHelper.FIELD_NAME, result.getCookie());
        	cookie.setPath("/");
        	cookie.setMaxAge(-1);//永久不过期
        	response.addCookie(cookie);
    	}
		//用户cookie
    	if(result.getCookie_shopping_cart()!=null){
        	Cookie cookie = new Cookie(CookieHelper.COOKIE_SHOPPING_CART, URLEncoder.encode(result.getCookie_shopping_cart(),"utf-8"));
        	cookie.setPath("/");
        	cookie.setMaxAge(-1);//永久不过期
        	response.addCookie(cookie);
    	}
		//用户cookie
    	if(result.getCuisine_shopping_cart()!=null){
        	Cookie cookie = new Cookie(CookieHelper.CUISINE_SHOPPING_CART, URLEncoder.encode(result.getCuisine_shopping_cart(),"utf-8"));
        	cookie.setPath("/");
        	cookie.setMaxAge(-1);//永久不过期
        	response.addCookie(cookie);
    	}
		//用户cookie
    	if(result.getOutside_shopping_cart()!=null){
        	Cookie cookie = new Cookie(CookieHelper.OUTSIDE_SHOPPING_CART, URLEncoder.encode(result.getOutside_shopping_cart(),"utf-8"));
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
    	ContentType type = ContentType.fromValue(result.contentType);
    	if(type==null||type==ContentType.applicationJson){
    		outPrintJson(StringUtil.dealNull(result.getResponseString(),""), response);
    	}else if(type==ContentType.textHtml){
    		redirect(result, request, response);
    	}else if(type==ContentType.imageJpeg){
    		imageIo(request, response);
    	}
	}
	
	
	
	
	 private void redirect(Result result,HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	       if(result.hasError()){
	    	   response.sendRedirect("/error/error.jsp");
	       }else{
	    	   if("forward".equals(result.type)){
	    		   //System.out.println("forword=============");
	    		   for(Entry<String, Object> en:result.map.entrySet()){
	    	    	   request.setAttribute(en.getKey(), en.getValue());
	    	       }
	    		   //System.out.println("request.getRequestDispatcher");
	    		   request.getRequestDispatcher(result.redirect).forward(request, response);
	    	   }else{
	    		   boolean b =true;
	    		   StringBuffer sbuf = new StringBuffer("");
	    		   for(Entry<String, Object> en:result.map.entrySet()){
	    			   sbuf.append((b?"?":"&")+en.getKey()+"="+en.getValue());
	    			   if(b){b=false;}
	    	       }
	    		   response.sendRedirect(result.redirect+sbuf.toString());
	    	   }
	    	   
	       }
	     
		  
	    }
	
	
	private void outPrintJson(String outJson,HttpServletResponse response) throws IOException{
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
	public void imageIo(HttpServletRequest request,HttpServletResponse response) throws IOException{
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
	
	public void destroy() {
		//shutdown();
	}
	
}
