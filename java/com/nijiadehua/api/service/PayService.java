package com.nijiadehua.api.service;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import com.github.wxpay.sdk.WXPayUtil;
import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.wechat.pay.MiniPayUtil;
import com.nijiadehua.api.base.wechat.pay.XMLUtil;
import com.nijiadehua.api.controller.v1.pay.response.ArtOrderInfo;
import com.nijiadehua.api.controller.v1.pay.response.ArtPayInfo;
import com.nijiadehua.api.controller.v1.pay.response.NotifyResponse;
import com.nijiadehua.api.controller.v1.pay.response.UnifiedorderResponse;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.util.StringUtil;

@Service
public class PayService {
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public UnifiedorderResponse unifiedorder(Long user_id,String ip,String order_id) throws ServiceException{
		try {
			UnifiedorderResponse unifiedorderResponse = new UnifiedorderResponse();
		
			Sql queryUser = new Sql(" select open_id from art_user_info where wx_id = ? ");
			queryUser.addParam(user_id);
			String openid = jdbcTemplate.findForString(queryUser);
			if(StringUtil.isEmpty(openid)) {
				throw new ServiceException("用户信息查询错误");
			}
			
			Sql queryOrder = new Sql(" select a.pay_amount,b.sales_name,b.sku_name from art_order_info a,art_order_goods b where a.order_id = b.order_id and a.order_status = 1 and a.order_id = ? and a.user_id = ? ");
			queryOrder.addParam(order_id,user_id);
			List<ArtOrderInfo> orderInfo = jdbcTemplate.queryForList(queryOrder,ArtOrderInfo.class);
			if(orderInfo.size() == 0) {
				throw new ServiceException("订单信息查询错误");
			}
			
			double pay_amount = 0;
			String goods = "";
			for(ArtOrderInfo good : orderInfo) {
				goods += good.getSales_name();
				pay_amount = good.getPay_amount();
			}
			
			//{return_msg=appid参数长度有误, return_code=FAIL}
			//{result_code=FAIL, sign=585F6938CA28E929A62860F967649201, mch_id=1573616051, err_code=INVALID_REQUEST, err_code_des=201 商户订单号重复, return_msg=OK, appid=wx6e2a2a319598b1e5, nonce_str=aTvboqJRyqgxuLX5, return_code=SUCCESS}
			//{result_code=SUCCESS, sign=37363EE7B96A5432B9D447564D724F10, mch_id=1573616051, prepay_id=wx1319002573923303ad89e9881683799500, return_msg=OK, appid=wx6e2a2a319598b1e5, nonce_str=8vuZBLOdZOgamfzi, return_code=SUCCESS, trade_type=JSAPI}
			int amount = (int)(pay_amount*100);
			Map<String,String> unifiedorder = MiniPayUtil.unifiedorder(openid, ip,order_id,goods,amount);
			if(!unifiedorder.containsKey("prepay_id") || StringUtil.isEmpty(unifiedorder.get("prepay_id"))) {
				throw new ServiceException("调用微信接口错误");
			}
			
			String timeStamp = WXPayUtil.getCurrentTimestamp() + "";
			String nonceStr = WXPayUtil.generateNonceStr();
			String prepay_id = unifiedorder.get("prepay_id");
			String signType = "MD5";
			
			Date current_time = new Date();
			
			Sql queryPaySql = new Sql(" select * from art_pay_info where order_id = ? ");
			queryPaySql.addParam(order_id);
			ArtPayInfo artPayInfo = jdbcTemplate.findObject(queryPaySql, ArtPayInfo.class);
			if(artPayInfo == null) {
				Sql savePaySql = new Sql(" insert into art_pay_info (order_id,prepay_id,order_amount,state,create_time,update_time) values (?,?,?,?,?,?) ");
				savePaySql.addParam(order_id,prepay_id,pay_amount,0,current_time,current_time);
				Long result = jdbcTemplate.saveObject(savePaySql);
				if(result == null) {
					throw new ServiceException("保存统一订单信息失败");
				}
				
			}else {
				Sql updatePaySql = new Sql(" update art_pay_info set prepay_id=?,update_time=? where order_id = ? ");
				updatePaySql.addParam(prepay_id,current_time,order_id);
				int result = jdbcTemplate.updateObject(updatePaySql);
				if(result == 0) {
					throw new ServiceException("更新统一订单信息失败");
				}
			}
			
			Map<String, String> data = new HashMap<String,String>();
			data.put("appId", MiniPayUtil.APP_ID);
			data.put("timeStamp", timeStamp);
			data.put("nonceStr", nonceStr);
			data.put("package", "prepay_id="+prepay_id);
			data.put("signType", signType);
			String paySign = WXPayUtil.generateSignature(data,MiniPayUtil.MCH_SECRET);
			
			unifiedorderResponse.setAppId(data.get("appId"));
			unifiedorderResponse.setTimeStamp(timeStamp);
			unifiedorderResponse.setNonceStr(nonceStr);
			unifiedorderResponse.setPrepay_id(data.get("package"));
			unifiedorderResponse.setSignType(signType);
			unifiedorderResponse.setPaySign(paySign);
			return unifiedorderResponse;
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("统一下单失败："+e.getMessage());
		}
	}
	
	
	public NotifyResponse notify(String xml) throws ServiceException{
		NotifyResponse notifyResponse = new NotifyResponse();
		try{
			if(StringUtil.isEmpty(xml)) {
				notifyResponse.setReturn_code("FAIL");
				notifyResponse.setReturn_msg("数据缺失XML");
				return notifyResponse;
			}
			
			Map<String, String> notify = XMLUtil.xmlToMap(xml);
			if(!notify.containsKey("return_code") || !notify.get("return_code").equals("SUCCESS")) {
				notifyResponse.setReturn_code("FAIL");
				notifyResponse.setReturn_msg("通信失败");
				return notifyResponse;
			}
			
			if(!notify.containsKey("result_code") || !notify.get("result_code").equals("SUCCESS")) {
				String err_code = notify.containsKey("err_code") ? notify.get("err_code") : "";
				String err_code_des = notify.containsKey("err_code_des") ? notify.get("err_code_des") : "";
				
				notifyResponse.setReturn_code("FAIL");
				notifyResponse.setReturn_msg("业务失败："+"["+err_code+"]"+err_code_des);
				return notifyResponse;
				
			}
			
			if(!WXPayUtil.isSignatureValid(notify,MiniPayUtil.MCH_SECRET)) {
				notifyResponse.setReturn_code("FAIL");
				notifyResponse.setReturn_msg("签名错误");
				return notifyResponse;
			}
			
			//是否已经接收过通知
			String out_trade_no = notify.get("out_trade_no");
			Sql queryPaySql = new Sql(" select * from art_pay_info where order_id = ? ");
			queryPaySql.addParam(out_trade_no);
			ArtPayInfo artPayInfo = jdbcTemplate.findObject(queryPaySql, ArtPayInfo.class);
			if(artPayInfo == null) {
				//微信通知错误，为找到统一支付订单
				notifyResponse.setReturn_code("FAIL");
				notifyResponse.setReturn_msg("未找到统一支付订单");
				return notifyResponse;
			}
			
			String transaction_id = notify.get("transaction_id");
			if(transaction_id.equals(artPayInfo.getTransaction_id())) {
				notifyResponse.setReturn_code("SUCCESS");
				notifyResponse.setReturn_msg("支付通知重复");
				return notifyResponse;
			}

			int total_fee = Integer.parseInt(notify.get("total_fee"));
			if((int)(artPayInfo.getOrder_amount()*100) != total_fee) {
				notifyResponse.setReturn_code("FAIL");
				notifyResponse.setReturn_msg("支付失败，到账金额和订单金额不一致");
				return notifyResponse;
			}
			
			Date current_time = new Date();
			
			Sql updatePaySql = new Sql(" update art_pay_info set transaction_id = ?,state = ?,update_time = ? where order_id = ? ");
			updatePaySql.addParam(transaction_id,1,current_time,out_trade_no);
			int updatePay = jdbcTemplate.updateObject(updatePaySql);
			if(updatePay == 0) {
				throw new ServiceException("业务失败：更新支付信息出错");
			}
			
			Sql updateOrderSql = new Sql(" update art_order_info set pay_time = ?,order_status = ?,update_time = ? where order_id = ? ");
			updateOrderSql.addParam(current_time,2,current_time,out_trade_no);
			int updateOrder = jdbcTemplate.updateObject(updateOrderSql);
			if(updateOrder == 0) {
				throw new ServiceException("业务失败：更新订单信息出错");
			}
			
			Sql outbount = new Sql(" update art_stock_outbound set out_type = ?,modify_time = ?,remark = ? where order_id = ? ");
			outbount.addParam(2,current_time,"订单支付完成",out_trade_no);
			int outbount_result = jdbcTemplate.updateObject(outbount);
			if(outbount_result == 0) {
				throw new ServiceException("订单号【"+out_trade_no+"】实际扣减库存失败");
			}
			
			notifyResponse.setReturn_code("SUCCESS");
			notifyResponse.setReturn_msg("支付通知成功");
			return notifyResponse;
		}catch(Exception e) {
			e.printStackTrace();
			throw new ServiceException("微信支付通知失败："+e.getMessage());
		}
		
	}

	
	public int queryOrderPayStatus(Long user_id,String order_id) throws ServiceException{
		try {
			Sql queryUser = new Sql(" select * from art_pay_info where order_id = ? ");
			queryUser.addParam(order_id);
			ArtPayInfo artPayInfo = jdbcTemplate.findObject(queryUser,ArtPayInfo.class);
			if(artPayInfo == null) {
				throw new ServiceException("订单不存在");
			}
			
			if(artPayInfo.getState() == 1) {
				return 1;
			}
			
			return 0;
		}catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("订单支付状态查询失败："+e.getMessage());
		}
	}
	
	
}
