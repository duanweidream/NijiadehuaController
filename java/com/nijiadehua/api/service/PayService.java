package com.nijiadehua.api.service;

import java.io.File;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import com.nijiadehua.api.base.wechat.pay.MiniPayUtil;
import com.nijiadehua.api.controller.v1.pay.response.UnifiedorderResponse;
import com.nijiadehua.api.exception.ServiceException;

@Service
public class PayService {

	public UnifiedorderResponse unifiedorder() throws ServiceException{
		try {
			
			
			//{return_msg=appid参数长度有误, return_code=FAIL}
			//{result_code=SUCCESS, sign=37363EE7B96A5432B9D447564D724F10, mch_id=1573616051, prepay_id=wx1319002573923303ad89e9881683799500, return_msg=OK, appid=wx6e2a2a319598b1e5, nonce_str=8vuZBLOdZOgamfzi, return_code=SUCCESS, trade_type=JSAPI}
			Map<String,String> map = MiniPayUtil.unifiedorder(uri, openid, ip);
			
			
			return null;
		}catch (Exception e) {
			throw new ServiceException();
		}
	}
}
