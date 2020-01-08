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
import com.nijiadehua.api.controller.v1.order.request.OrderCreateRequest.Sales;
import com.nijiadehua.api.controller.v1.order.response.OrderCreateResponse;
import com.nijiadehua.api.controller.v1.order.response.OrderDetailResponse;
import com.nijiadehua.api.controller.v1.order.response.OrderSearchResponse;
import com.nijiadehua.api.controller.v1.order.response.OrderSearchResponse.Goods;
import com.nijiadehua.api.dao.OrderDao;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.model.ArtOrderGoods;
import com.nijiadehua.api.model.ArtSalesInfo;
import com.nijiadehua.api.model.Page;
import com.nijiadehua.api.util.JsonUtil;
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
				
				ArtSalesInfo salesInfo = orderDao.getObject(sales_id, ArtSalesInfo.class);
				if(salesInfo == null){
					throw new ServiceException("销售品【"+sales_id+"】不存在");
				}
				
				
				
				
				ArtOrderGoods orderGoods = new ArtOrderGoods();
				orderGoods.setOrder_id(order_id);
				orderGoods.setSales_id(sales_id);
				orderGoods.setSales_name(salesInfo.getSales_name());
				orderGoods.setTitle(salesInfo.getTitle());
				orderGoods.setSku_id();
				orderGoods.setSku_name();
				orderGoods.setSales_price(salesInfo.getSales_price());
				orderGoods.setMkt_price(salesInfo.getMkt_price());
				orderGoods.setQty(sales_number);
				//orderGoods.setSales_icon(salesInfo.gets);
				orderGoods.setStatus(1L);
				orderGoods.setCreate_time(current_time);
				goodsList.add(orderGoods);
				
			}
			
			orderDao.savebatchObject(goodsList);
			
		} catch (Exception e) {
			log.logError("订单生成失败",e);
			throw new ServiceException("订单生成失败："+e.getMessage());
		}
		
		return null;
	}
	
	
	public void searchOrderForPage(Page page,String user_id,String status) throws ServiceException{
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
				Sql goods = new Sql(" select sales_id,sales_name,title,sku_id,sku_name,sales_price,mkt_price,qty,sales_icon from art_order_goods where order_id = ?  ");
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
		
	public OrderDetailResponse queryOrderDetailByOrderId(String user_id,String order_id) throws ServiceException{
		try {
			Sql sql = new Sql(" select user_id,order_id,order_status,order_amount,pay_amount,order_remark,create_time,delivery_mode,delivery_name,delivery_phone,delivery_address,delivery_send,express_company,express_number,express_freight from art_order_info where order_id = ? and user_id = ? ");
			sql.addParam(order_id,user_id);
			
			OrderDetailResponse order = jdbcTemplate.findObject(sql, OrderDetailResponse.class);
			
			Sql goods = new Sql(" select sales_id,sales_name,title,sku_id,sku_name,sales_price,mkt_price,qty,sales_icon from art_order_goods where order_id = ?  ");
			goods.addParam(order.getOrder_id());
			List<com.nijiadehua.api.controller.v1.order.response.OrderDetailResponse.Goods> goodsList = jdbcTemplate.queryForList(goods,com.nijiadehua.api.controller.v1.order.response.OrderDetailResponse.Goods.class);
			order.setGoods(goodsList);
			
			
			return order;
		}catch(Exception e) {
			log.logError("订单详情查询错误", e);
			throw new ServiceException("订单详情查询错误："+e.getMessage());
		}
	}
	
}
