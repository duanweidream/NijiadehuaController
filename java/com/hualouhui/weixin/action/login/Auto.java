package com.hualouhui.weixin.action.login;

import net.sf.json.JSONObject;

import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.base.wechat.WeChatApi;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.UserInfo;
import com.hualouhui.weixin.service.UserInfoService;
import com.hualouhui.weixin.util.CookieHelper;
import com.hualouhui.weixin.util.Parameters;
import com.hualouhui.weixin.util.StringUtil;

public class Auto extends ActionBase{

	public Logger logger = Logger.getLogger(Auto.class);
	public String code,redirect;
	public Auto(){
		 needCheckAuth =false;
	}
	@Override
	public void initParameters(Parameters params) throws ApiError {
		
		code = params.getFieldValue("code");
		System.out.println("Auto:code,"+code);
		redirect = params.getFieldValue("state");
		if(StringUtil.isEmpty(redirect)){
			redirect = StringUtil.decode(redirect, "utf-8");
		}

		if(StringUtil.isEmpty(code)){
			ApiError.Type.INVALID_PARAM.toException();
		}
		
	}
	
	@Override
	public Result invokeService() throws ApiError {
		
		Result result = new Result();
		
		JSONObject sns_access_token = WeChatApi.sns_access_token(code);
		String openid  = sns_access_token.getString("openid");
		
		System.out.println("Auto:openid,"+openid);
		
		UserInfoService userInfoService = new UserInfoService();
		
		UserInfo fan = new UserInfoService().findUserInfoByOpenid(openid);
		if(null != fan){
			
			result.addCookie(CookieHelper.createCookie(fan));
			
		}else{
			
			UserInfo userInfo = WeChatApi.getWxUserInfo(openid);
			if(userInfo != null){
				userInfo.setOpenid(openid);
				Long userid = userInfoService.saveUserInfo(fan);
				userInfo.setId(userid);
				
				result.addCookie(CookieHelper.createCookie(userInfo));
			}else{
				logger.logInfo("warn:fan is null");
				result.setRedirect("/toPublish.do");
			}
			
		}
		
		result.setRedirect(StringUtil.dealNull(redirect,"/toPublish.do"));
		return result;
	}
	
}
