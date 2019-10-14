package com.hualouhui.weixin.base.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.hualouhui.weixin.action.base.MyCookie;
import com.hualouhui.weixin.constants.ContentType;
import com.hualouhui.weixin.exception.ApiError;

public class Result {

    public String cookie, contentType,redirect,type;
    public String responseString;
    public JSONObject ret;
    private byte[] bytes;
    private ApiError error;
    private List<MyCookie> cookieList = new ArrayList<>();
    private String cookie_shopping_cart;
    private String cuisine_shopping_cart;
    private String outside_shopping_cart;
    
    public boolean hasError(){
    	return null!=error;
    }
    public Map<String, Object> map=new HashMap<String, Object>();
    
    
    public Result(byte[] bytes, String contentType) {
    	this.ret = null;
        this.bytes = bytes;
        this.contentType = contentType;
    }
   
    
    public Result() {
    	this.ret = new JSONObject();
    	this.ret.put("code", 200);
        this.cookie = null;
    }
    
    public Result(JSONObject jsonObj, String cookie) {
        this.ret = jsonObj;
        this.cookie = cookie;
    }

    public Result(ApiError.Type errorType, String cookie) {
        this(errorType.toException(), cookie);
    }

    public Result(ApiError error, String cookie) {
        this(error.toJSONObject(), cookie);
        this.error=error;
    }
    
    public Result(JSONObject jsonObj) {
        this(jsonObj, null);
    }
    public void setResponseString(String strs){
    	this.responseString=strs;
    }
    
    public void setRequestId(long requestId) {
    	ret.put("request_id", requestId);
    }
    
    public void put(Object key,Object value){
    	ret.put(key, value);
    }
    public void setRet(JSONObject obj){
    	obj.put("code", 200);
    	this.ret = obj;
    }
    public void setRet(JSONObject obj,String cookie){
    	obj.put("code", 200);
    	this.ret = obj;
    	this.cookie=cookie;
    }
    public String getResponseString() {
    	//if(!StringUtil.isEmpty(responseString)){
        if(null!=responseString){
    		return responseString;
    	}else{
            return ret == null ? null : ret.toString();
    	}
    }
    
    public String getCookie() {
        return cookie;
    }
    
    public byte[] getResponseBytes() {
        return bytes;
    }
    
    public String getContentType() {
        return contentType;
    }
	public void setRedirect(String redirect) {
		this.contentType=ContentType.textHtml.toString();
		this.redirect = redirect;
		this.type="redirect";
	}
	public void forword(String url){
		this.contentType=ContentType.textHtml.toString();
		this.redirect=url;
		this.type="forward";
	}
	
	public void writeImg(){
		this.contentType=ContentType.imageJpeg.toString();
		//this.redirect=url;
		//this.type="forward";
	}
	
	public void setAttr(String key,Object value){
    	map.put(key, value);
    }
	public void addCookie(MyCookie cookie){
		cookieList.add(cookie);
	}
	public void addCookie(String cookie){
		this.cookie=cookie;
	}
	


	public String getCookie_shopping_cart() {
		return cookie_shopping_cart;
	}


	public void setCookie_shopping_cart(String cookie_shopping_cart) {
		this.cookie_shopping_cart = cookie_shopping_cart;
	}
	
	

	public String getCuisine_shopping_cart() {
		return cuisine_shopping_cart;
	}


	public void setCuisine_shopping_cart(String cuisine_shopping_cart) {
		this.cuisine_shopping_cart = cuisine_shopping_cart;
	}


	public String getOutside_shopping_cart() {
		return outside_shopping_cart;
	}


	public void setOutside_shopping_cart(String outside_shopping_cart) {
		this.outside_shopping_cart = outside_shopping_cart;
	}


	public List<MyCookie> getCookieList(){
		return this.cookieList;
	}
}
