package com.hualouhui.weixin.action.base;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;

import com.hualouhui.weixin.base.ftp.FileOperate;
import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.Page;
import com.hualouhui.weixin.model.UserInfo;
import com.hualouhui.weixin.util.CookieHelper;
import com.hualouhui.weixin.util.NumberUtil;
import com.hualouhui.weixin.util.Parameters;

public class ActionBase {	
	
	Logger logger = Logger.getLogger(ActionBase.class);
	
    public static ActionBase createInstance(String uri) {
        ActionBase ret = new ActionBase();
        UriMeta meta = UriMeta.newUriMeta(uri);
       // ret.meta=UriMeta.newUriMeta(uri);
        String actionClassName = meta.className;
        if (actionClassName != null) {
            try{
                Class<?> clazz = Class.forName(actionClassName);
                ret = (ActionBase)clazz.newInstance();
            }catch(Exception e) {      
            	e.printStackTrace();
            }
        }    
        ret.meta=meta;
        return ret;
    }
    
    
    
    public static String getActionClassName(String uri) {
    	if(null==uri){
    		return null;
    	}
    	StringBuffer sbuf = new StringBuffer("com.huanlouhui.weixin.action.");
    	uri = uri.replaceAll("\\.do", "").replaceFirst("^/", "");
		String[] parts = uri.split("\\/");
		if(parts.length<2){
			return null;
		}
		sbuf.append(parts[parts.length-2]+".");
	    sbuf.append(Character.toUpperCase(parts[parts.length-1].charAt(0)));
		sbuf.append(parts[parts.length-1].substring(1));
		//sbuf.append(parts[0]+".");
		//sbuf.append(Character.toUpperCase(parts[1].charAt(0)));
		//sbuf.append(parts[1].substring(1));
        return sbuf.toString();
    }
    public Result execute(Parameters params) {
        Result ret = new Result();
        try{
        	
            if(checkAuth(params,ret)){
            	saveImages(params);
                initParameters(params);
                ret = invokeService();
            }
        }catch (ApiError err) {
        	logger.logError("发生内部错误",err);
            ret = new Result(err, null);
        }
        ret = (ret != null) ? ret : new Result(ApiError.Type.INTERNAL_ERROR, null);
        logcat.trace("HTTP RSP: "+ret.getResponseString());
        return ret;
    }
    
    public boolean checkAuth(Parameters params,Result result) throws ApiError{
    	String cookie = params.getMyCookieValue();
    	
    	System.out.println("MyCookieValue:"+cookie);
    	
        user = CookieHelper.getUserInfo(cookie);
        
        System.out.println("user.id:"+user.getId());
    	if (needCheckAuth) {
    		 if (user==null||user.id<=0) {
    			 if(meta.rest){
    				 //rest接口直接抛出异常
    				throw ApiError.Type.NOT_LOGGED_IN.toException();
    	    	 }else if(meta.diando){
    	    		String uri = params.getRequest().getRequestURI();
    	    		String qstring = params.getRequest().getQueryString();
    	    		if(null!=qstring){
    	    			uri=uri+"?"+qstring;
    	    		}
    	    		 
    	    		System.out.println("/pages/to.login.jsp");
    	    		result.setAttr("redirect", uri);
    	    		result.forword("/pages/to.login.jsp");
    	    	 }
    			 return false;
              }
        }
		return true;
    }
    public void saveImages(Parameters params) throws ApiError {
   	 for (String key : params.getFileKeys()) {
   		 InputStream in=new ByteArrayInputStream(params.getFileData(key));   
         String path = FileOperate.uploadFile(in, params.getFileUri(key));
         logcat.trace("path="+path+",key="+key);
   	 }
   }
   
	/**
	 * 获取request对象中post传递过来的json对象
	 * 
	 * @param request
	 * @return
	 */
	public String getJson(HttpServletRequest request) throws IOException {
		return IOUtils.toString(request.getInputStream(), "UTF-8");
	}
    
    public JSONObject getObjectString(String p){
		try{
			return  JSONObject.fromObject(p);//  JSONObject.fromObject(EscapeUtil.unescape(p));
		}catch(Exception e){
			return null;
		}
	}
    public Page newPage(Parameters params){
		Integer startIndex = NumberUtil.getInteger(params.getFieldValue("startIndex"), 0);
		Integer itemCount =  NumberUtil.getInteger(params.getFieldValue("itemCount"), 10);
		String queryString = params.getFieldValue("queryString");
    	return new Page(startIndex, itemCount,queryString);
    }
    
    public void initParameters(Parameters params) throws ApiError{
       
    }
    
    public Result invokeService() throws ApiError{
        return new Result(ApiError.Type.NO_SUCH_SERVICE.toException(), null);
    }
    
    protected ActionBase(){
    	logcat = Logger.getLogger(this.getClass());//.getLogcat(this.getClass());
    }
    public void setPageInfo(Page page,JSONObject data){
    	data.put("itemCount", page.itemCount);
        data.put("totalCount", page.totalCount);
        data.put("startIndex", page.startIndex);
        data.put("currentPage",page.currentPage);
        data.put("totalPage", page.totalPage);
    }
    
    
    
    //protected DataAccessor dataAccessor = DataAccessor.createInstance();
    protected Logger logcat = null;
    protected boolean needCheckAuth = false;
    protected UserInfo user = new UserInfo();
    public UriMeta meta=null;
    protected JSONObject data = new JSONObject(); 
}