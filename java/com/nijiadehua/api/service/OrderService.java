package com.nijiadehua.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.controller.v1.order.request.OrderCreateRequest;
import com.nijiadehua.api.controller.v1.order.request.OrderSubmitRequest;
import com.nijiadehua.api.controller.v1.order.request.OrderSubmitRequest.Delivery;
import com.nijiadehua.api.controller.v1.order.request.OrderCreateRequest.Sales;
import com.nijiadehua.api.controller.v1.order.response.OrderCreateResponse;
import com.nijiadehua.api.controller.v1.order.response.OrderDetailResponse;
import com.nijiadehua.api.controller.v1.order.response.OrderSearchResponse;
import com.nijiadehua.api.controller.v1.order.response.OrderCreateResponse.OrderGoods;
import com.nijiadehua.api.controller.v1.order.response.OrderSearchResponse.Goods;
import com.nijiadehua.api.dao.OrderDao;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.model.ArtOrderGoods;
import com.nijiadehua.api.model.ArtOrderInfo;
import com.nijiadehua.api.model.ArtSalesInfo;
import com.nijiadehua.api.model.Page;
import com.nijiadehua.api.util.CryptoUtil;
import com.nijiadehua.api.util.JsonUtil;
import com.nijiadehua.api.util.NumberUtil;
import com.nijiadehua.api.util.OrderId;
import com.nijiadehua.api.util.StringUtil;


/**
 * ClassName:Create</br> Function: 订单服务 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-24 上午10:09:34</br>
 * 
 */
@Service
public class OrderService {
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	private static Logger log = Logger.getLogger(OrderService.class);
	
	@Autowired
	private OrderDao orderDao;

	public OrderCreateResponse createOrder(String json) throws ServiceException{
		OrderCreateResponse orderCreateResponse = new OrderCreateResponse();
		try {
			
			log.logInfo("json:"+json);
			OrderCreateRequest orderCreateRequest = (OrderCreateRequest)JsonUtil.jsongToObject(json,OrderCreateRequest.class);
			
			if(null == orderCreateRequest){
				throw new ServiceException("请求参数错误");
			}
			
			List<Sales> sales = orderCreateRequest.getSales();
			if(sales.size() == 0){
				throw new ServiceException("商品信息为空");
			}
			
			if(StringUtil.isEmpty(orderCreateRequest.getUser_id())){
				throw new ServiceException("请求用户id为空");
			}
			
			String order_id = OrderId.getInstance().getOrderId();
			if(StringUtil.isEmpty(order_id)) {
				throw new ServiceException("订单号获取失败");
			}
			
			Date current_time = new Date();
			
			Collection goodsList = new ArrayList<ArtOrderGoods>();
			for(Sales sa : orderCreateRequest.getSales()){
				Long sales_id = sa.getSales_id();
				int sales_number = sa.getQty();
				List<Long> spec = sa.getSpec();
				
				//a.sales_id,a.product_id,a.sales_name,a.title,a.sales_price,a.mkt_price,d.img_url sales_img
				Object[] salesInfo = orderDao.querySalesProdInfoBySalesId(sales_id);
				if(salesInfo == null){
					throw new ServiceException("销售品【"+sales_id+"】不存在");
				}
				
				StringBuffer sku_code = new StringBuffer(salesInfo[1].toString());
				for(Long sid : spec) {
					sku_code.append("&").append(sid);
				}
				String skuCode = CryptoUtil.encryptData(sku_code.toString());
				
				Object[] skuInfo = orderDao.querySkuSpecInfoBySkuCode(skuCode);
				if(StringUtil.isEmpty(skuInfo) || StringUtil.isEmpty(skuInfo[0])){
					throw new ServiceException("SKU规格【"+skuCode+"】不存在");
				}
				
				
				ArtOrderGoods orderGoods = new ArtOrderGoods();
				orderGoods.setUser_id(orderCreateRequest.getUser_id());
				orderGoods.setOrder_id(order_id);
				orderGoods.setSales_id(sales_id);
				orderGoods.setSales_name(salesInfo[2]+"");
				orderGoods.setSales_title(salesInfo[3]+"");
				orderGoods.setSku_id(Long.valueOf(skuInfo[0].toString()));
				orderGoods.setSku_name(skuInfo[1]+"");
				orderGoods.setSales_price(Double.valueOf(salesInfo[4].toString()));
				orderGoods.setMkt_price(Double.valueOf(salesInfo[5].toString()));
				orderGoods.setQty(sales_number);
				orderGoods.setSales_icon(salesInfo[6]+"");
				orderGoods.setStatus(1L);
				orderGoods.setCreate_time(current_time);
				goodsList.add(orderGoods);
				
			}
			
			orderDao.savebatchObject(goodsList);
			
			orderCreateResponse.setUser_id(orderCreateRequest.getUser_id());
			orderCreateResponse.setOrder_id(order_id);
			
			List<OrderGoods> order_goods = new ArrayList<OrderGoods>();
			for(Object gd : goodsList) {
				ArtOrderGoods goods = (ArtOrderGoods)gd;
				
				OrderGoods od = new OrderGoods();
				od.setSales_id(goods.getSales_id());
				od.setSales_name(goods.getSales_name());
				od.setSales_title(goods.getSales_title());
				od.setSku_id(goods.getSku_id());
				od.setSku_name(goods.getSku_name());
				od.setSales_price(goods.getSales_price());
				od.setMkt_price(goods.getMkt_price());
				od.setQty(goods.getQty());
				od.setSales_icon(goods.getSales_icon());
				
				order_goods.add(od);
			}
			
			orderCreateResponse.setOrder_goods(order_goods);
			return orderCreateResponse;
			
		} catch (Exception e) {
			log.logError("订单生成失败",e);
			throw new ServiceException("订单生成失败："+e.getMessage());
		}
		
	}
	
	public void submitOrder(String json) throws ServiceException{
		
		try {
			
			log.logInfo("json:"+json);
			OrderSubmitRequest orderSubmitRequest = (OrderSubmitRequest)JsonUtil.jsongToObject(json,OrderSubmitRequest.class);
			
			if(null == orderSubmitRequest){
				throw new ServiceException("请求参数错误");
			}
			
			if(StringUtil.isEmpty(orderSubmitRequest.getUser_id(),orderSubmitRequest.getOrder_id(),orderSubmitRequest.getDelivery())){
				throw new ServiceException("缺少必要参数");
			}
			
			List<ArtOrderGoods> goods = orderDao.queryOrderGoodsByOrderId(orderSubmitRequest.getOrder_id());
			if(goods == null || goods.size() == 0) {
				throw new ServiceException("订单商品不存在");
			}
			
			Date current_time = new Date();
			
			double order_amount = 0;
			double pay_amount = 0;
			for(ArtOrderGoods good : goods) {
				
				//a.sales_id,a.product_id,a.sales_name,a.title,a.sales_price,a.mkt_price,d.img_url sales_img
				Object[] salesInfo = orderDao.querySalesProdInfoBySalesId(good.getSales_id());
				if(salesInfo == null){
					throw new ServiceException("销售品【"+good.getSales_id()+"】不存在或已下架");
				}
				
				int stock = orderDao.queryProdStockBySkuId(good.getSku_id());
				if(good.getQty() > stock) {
					throw new ServiceException("【"+good.getSales_id()+"】库存不足，当前库存："+stock+"，购入数量："+good.getQty());
				}
				
				
				Sql outbount = new Sql(" insert into art_stock_outbound (product_id,sku_id,out_stock,out_type,remark,valid,create_time,modify_time) values (?,?,?,?,?,?,?,?) ");
				outbount.addParam(salesInfo[1],good.getSku_id(),good.getQty(),1,good.getOrder_id(),1,current_time,current_time);
				Long out_id = jdbcTemplate.saveObject(outbount);
				if(out_id == null) {
					throw new ServiceException("销售品【"+good.getSales_id()+"】扣减库存失败");
				}
				
				Sql sku = new Sql(" update art_prod_sku set sku_stock = ?,modify_time = ? where sku_id = ? ");
				sku.addParam(stock - good.getQty(),current_time,good.getSku_id());
				int sku_result = jdbcTemplate.updateObject(sku);
				if(sku_result == 0) {
					throw new ServiceException("销售品【"+good.getSales_id()+"】扣减库存失败");
				}
				
				order_amount += good.getSales_price();
				pay_amount += good.getSales_price();
				
			}
			
			/*Delivery delivery = orderSubmitRequest.getDelivery();
			Sql order = new Sql(" insert into art_order_info (order_id,user_id,order_sort,order_status,order_amount,pay_type,pay_amount,delivery_mode,delivery_send,delivery_name,delivery_phone,delivery_country,delivery_province,delivery_city,delivery_district,delivery_address,delivery_postal_code,order_remark,create_time) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			order.addParam(orderSubmitRequest.getOrder_id(),orderSubmitRequest.getUser_id(),"shufa",1,order_amount,orderSubmitRequest.getPay_type(),pay_amount,delivery.getDelivery_mode(),delivery.getDelivery_send(),delivery.getDelivery_name(),delivery.getDelivery_phone(),delivery.getDelivery_country(),delivery.getDelivery_province(),delivery.getDelivery_city(),delivery.getDelivery_district(),delivery.getDelivery_address(),delivery.getDelivery_postal_code(),orderSubmitRequest.getOrder_remark(),current_time);
			jdbcTemplate.saveObject(order);*/
			
			ArtOrderInfo orderInfo = new ArtOrderInfo();
			Delivery delivery = orderSubmitRequest.getDelivery();
			orderInfo.setOrder_id(orderSubmitRequest.getOrder_id());
			orderInfo.setUser_id(orderSubmitRequest.getUser_id());
			orderInfo.setOrder_sort("shufa");
			orderInfo.setOrder_status(1L);
			orderInfo.setOrder_amount(order_amount);
			orderInfo.setPay_type(orderSubmitRequest.getPay_type());
			orderInfo.setPay_amount(pay_amount);
			orderInfo.setDelivery_mode(delivery.getDelivery_mode());
			orderInfo.setDelivery_send(delivery.getDelivery_send());
			orderInfo.setDelivery_name(delivery.getDelivery_name());
			orderInfo.setDelivery_phone(delivery.getDelivery_phone());
			orderInfo.setDelivery_country(delivery.getDelivery_country());
			orderInfo.setDelivery_province(delivery.getDelivery_province());
			orderInfo.setDelivery_city(delivery.getDelivery_city());
			orderInfo.setDelivery_district(delivery.getDelivery_district());
			orderInfo.setDelivery_address(delivery.getDelivery_address());
			orderInfo.setDelivery_postal_code(delivery.getDelivery_postal_code());
			orderInfo.setOrder_remark(orderSubmitRequest.getOrder_remark());
			orderInfo.setCreate_time(current_time);
			orderDao.saveObject(orderInfo);
		} catch (Exception e) {
			log.logError("订单提交失败",e);
			throw new ServiceException("订单提交失败："+e.getMessage());
		}
		
	}
	
	
	public void searchOrderForPage(Page page,Long user_id,Long status) throws ServiceException{
		try {
			Sql sql = new Sql(" select user_id,order_id,order_status,order_amount,pay_amount,order_remark,create_time from art_order_info where order_status <> 9 ");
			sql.append("and", "user_id", "=", user_id);
			if(!StringUtil.isEmpty(status)){
				sql.append("and", "order_status", "=", status);
			}
			
			sql.append(" order by create_time desc ");
			jdbcTemplate.search(page, sql,OrderSearchResponse.class);
			
	
			List<OrderSearchResponse> listTrip = (List<OrderSearchResponse>)page.getList();
			for(OrderSearchResponse order : listTrip) {
				Sql goods = new Sql(" select sales_id,sales_name,sales_title,sku_id,sku_name,sales_price,mkt_price,qty,sales_icon from art_order_goods where order_id = ?  ");
				goods.addParam(order.getOrder_id());
				List<Goods> goodsList = jdbcTemplate.queryForList(goods,Goods.class);
				order.setGoods(goodsList);
			}
			
			
			page.setList(listTrip);
			
		}catch(Exception e) {
			log.logError("订单列表分页查询错误", e);
			throw new ServiceException("订单列表分页查询错误："+e.getMessage());
		}
	}
		
	public OrderDetailResponse queryOrderDetailByOrderId(Long user_id,String order_id) throws ServiceException{
		try {
			Sql sql = new Sql(" select user_id,order_id,order_status,order_amount,pay_amount,order_remark,DATE_FORMAT(create_time,'%Y-%m-%d %H:%i:%s') create_time,delivery_mode,delivery_name,delivery_phone,delivery_address,delivery_send,express_company,express_number,express_freight from art_order_info where order_id = ? and user_id = ? ");
			sql.addParam(order_id,user_id);
			
			OrderDetailResponse order = jdbcTemplate.findObject(sql, OrderDetailResponse.class);
			if(order == null) {
				throw new ServiceException("订单【"+order_id+"】不存在");
			}
			
			Sql goods = new Sql(" select sales_id,sales_name,sales_title,sku_id,sku_name,sales_price,mkt_price,qty,sales_icon from art_order_goods where order_id = ?  ");
			goods.addParam(order.getOrder_id());
			List<com.nijiadehua.api.controller.v1.order.response.OrderDetailResponse.Goods> goodsList = jdbcTemplate.queryForList(goods,com.nijiadehua.api.controller.v1.order.response.OrderDetailResponse.Goods.class);
			order.setGoods(goodsList);
			
			
			return order;
		}catch(Exception e) {
			log.logError("订单详情查询错误", e);
			throw new ServiceException("订单详情查询错误："+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
	
	}
	
	
	
}
