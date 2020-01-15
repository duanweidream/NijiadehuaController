package com.nijiadehua.api.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.wechat.pay.MiniPayUtil;
import com.nijiadehua.api.controller.v1.login.response.LoginResponse;
import com.nijiadehua.api.controller.v1.pay.response.UnifiedorderResponse;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.util.StringUtil;

@Service
public class PayService {
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public UnifiedorderResponse unifiedorder(Long user_id,String ip,String order_id) throws ServiceException{
		try {
			
			Sql queryUser = new Sql(" select open_id from art_user_info where user_id = ? ");
			queryUser.addParam(user_id);
			String openid = jdbcTemplate.findForString(queryUser);
			if(StringUtil.isEmpty(openid)) {
				throw new ServiceException("用户信息查询错误");
			}
			
			Sql queryOrder = new Sql(" select a.pay_amount,b.sales_name,b.sku_name from art_order_info a,art_order_goods b where a.order_id = b.order_id and a.order_status = 1 and a.order_id = ? and a.user_id = ? ");
			queryOrder.addParam(order_id);
			Object[] orderInfo = jdbcTemplate.queryForList(queryOrder,);
			if(StringUtil.isEmpty(orderInfo)) {
				throw new ServiceException("订单信息查询错误");
			}
			
			
			//{return_msg=appid参数长度有误, return_code=FAIL}
			//{result_code=FAIL, sign=585F6938CA28E929A62860F967649201, mch_id=1573616051, err_code=INVALID_REQUEST, err_code_des=201 商户订单号重复, return_msg=OK, appid=wx6e2a2a319598b1e5, nonce_str=aTvboqJRyqgxuLX5, return_code=SUCCESS}
			//{result_code=SUCCESS, sign=37363EE7B96A5432B9D447564D724F10, mch_id=1573616051, prepay_id=wx1319002573923303ad89e9881683799500, return_msg=OK, appid=wx6e2a2a319598b1e5, nonce_str=8vuZBLOdZOgamfzi, return_code=SUCCESS, trade_type=JSAPI}
			Map<String,String> map = MiniPayUtil.unifiedorder(openid, ip, order_id, goods, amount);
			
			
			return null;
		}catch (Exception e) {
			throw new ServiceException();
		}
	}
}
