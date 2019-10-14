package com.hualouhui.weixin.action;

import java.io.IOException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.log.Logger;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.base.wechat.WeChatManager;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.util.Config;
import com.hualouhui.weixin.util.Parameters;
import com.hualouhui.weixin.util.StringUtil;

import weixin.popular.support.ExpireKey;
import weixin.popular.support.expirekey.DefaultExpireKey;
import weixin.popular.util.SignatureUtil;

/**
 * @author  luoyouhua 2016-04-07
 * 微信认证接口。用于微信的相关接口通知。如关注 取消关注等。
 * */
public class Listener extends ActionBase{
	private static final Logger logger = Logger.getLogger(Listener.class);
	private static ExpireKey expireKey = new DefaultExpireKey();
	public static String token=Config.getValue("weixin.api.token");
	public String signature,timestamp,nonce,echostr;
	public String encrypt_type,msg_signature;
	public HttpServletRequest request;
	
	public Listener(){
		 needCheckAuth =false;
	}
	
	@Override
	public void initParameters(Parameters params) throws ApiError {	
		signature = params.getFieldValue("signature");
        timestamp = params.getFieldValue("timestamp");
        nonce = params.getFieldValue("nonce");
        echostr = params.getFieldValue("echostr");
        request=params.getRequest();
        if(StringUtil.isEmpty(timestamp,nonce,signature)){
        	throw ApiError.Type.INVALID_PARAM.toException();
        }
	}
	
	@Override
	public Result invokeService() throws ApiError {
		Result result = new Result();
        //签名验证
		if(!StringUtil.equals(SignatureUtil.generateEventMessageSignature(token,timestamp,nonce), signature)){
			throw ApiError.Type.LOGIC_ERROR.toException("signature error!");
		}
		//服务器初次验证
		if(!StringUtil.isEmpty(echostr)){
			result.setResponseString(echostr);
			return result;
		}
		//事件接收
		try {
			ServletInputStream inputStream = request.getInputStream();
			if(inputStream!=null){
				String xmlString = StringUtil.streamToString(inputStream);
				String responseString = WeChatManager.weChatEventProcess(xmlString);
				result.setResponseString(responseString);
			}
		} catch (IOException e) {
			throw ApiError.Type.LOGIC_ERROR.toException(e.getMessage());
		}
		
		
		
		return result;
	}


}
