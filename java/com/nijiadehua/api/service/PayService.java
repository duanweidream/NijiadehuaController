package com.nijiadehua.api.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.wechat.pay.MiniPayUtil;
import com.nijiadehua.api.base.wechat.pay.XMLUtil;
import com.nijiadehua.api.controller.v1.pay.response.NotifyResponse;
import com.nijiadehua.api.controller.v1.pay.response.UnifiedorderResponse;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.util.StringUtil;

@Service
public class PayService {

	public UnifiedorderResponse unifiedorder() throws ServiceException{
		try {
			
			
			//{return_msg=appid参数长度有误, return_code=FAIL}
			//{result_code=SUCCESS, sign=37363EE7B96A5432B9D447564D724F10, mch_id=1573616051, prepay_id=wx1319002573923303ad89e9881683799500, return_msg=OK, appid=wx6e2a2a319598b1e5, nonce_str=8vuZBLOdZOgamfzi, return_code=SUCCESS, trade_type=JSAPI}
			//Map<String,String> map = MiniPayUtil.unifiedorder(uri, openid, ip);
			
			
			return null;
		}catch (Exception e) {
			throw new ServiceException();
		}
	}
	
	
	public NotifyResponse notify(String xml) throws ServiceException{
		try{
			if(StringUtil.isEmpty(xml)) {
				throw new ServiceException("数据缺失XML:"+xml);
			}
			
			Map<String, String> notify = XMLUtil.xmlToMap(xml);
			if(notify.containsKey("return_code") || !notify.get("return_code").equals("SUCCESS")) {
				throw new ServiceException("通信失败");
			}
			
			if(notify.containsKey("result_code") || !notify.get("result_code").equals("SUCCESS")) {
				String err_code = notify.containsKey("err_code") ? notify.get("err_code") : "";
				String err_code_des = notify.containsKey("err_code_des") ? notify.get("err_code_des") : "";
				
				throw new ServiceException("业务失败："+"["+err_code+"]"+err_code_des);
			}
			
			if(!MiniPayUtil.isSignatureValid(notify)) {
				throw new ServiceException("签名错误");
			}
			
			Sql sql = new Sql(" select  ");
			
			
			return null;
		}catch(Exception e) {
			throw new ServiceException();
		}
		
	}
	
	public static void main(String[] args) {
		Map<String,String> map = new HashMap<String,String>();
		
	}
	
	
	
}
