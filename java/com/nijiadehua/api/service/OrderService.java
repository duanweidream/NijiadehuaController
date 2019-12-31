package com.nijiadehua.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.controller.v1.order.request.OrderCreateRequest;
import com.nijiadehua.api.controller.v1.order.request.OrderCreateRequest.Delivery;
import com.nijiadehua.api.controller.v1.order.request.OrderCreateRequest.Sales;
import com.nijiadehua.api.controller.v1.order.response.OrderCreateResponse;
import com.nijiadehua.api.dao.OrderDao;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.model.GoodsStockOutbound;
import com.nijiadehua.api.model.OrderGoods;
import com.nijiadehua.api.model.OrderInfo;
import com.nijiadehua.api.util.JsonUtil;
import com.nijiadehua.api.util.UtilFunction;


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
			
			Delivery delivery = orderCreateRequest.getDelivery();
			
			if(null == delivery){
				throw new ServiceException("收货信息为空");
			}
			
			Date current_time = new Date();
			
			double order_amount = 0;
			double pay_amount = 0;
			List<OrderGoods> goodsList = new ArrayList<OrderGoods>();
			for(Sales sales : orderCreateRequest.getSales()){
				Long sales_id = sales.getSales_id();
				int sales_number = sales.getSales_number();
				
				//a.sales_id,a.product_id,a.sales_name,b.product_name,a.sales_price,a.mkt_price,a.sales_img,b.ava_stock,b.product_spec
				Object[] salesProd = orderDao.querySalesProdInfoBySalesId(sales_id);
				
				if(salesProd == null){
					throw new ServiceException("销售品【"+sales_id+"】不存在");
				}
				
				Long ava_stock = Long.valueOf(salesProd[6].toString());
				if(ava_stock == 0L){
					throw new ServiceException("销售品【"+sales_id+"】库存不足");
				}
				
				double sales_price = Double.valueOf(salesProd[4].toString());
				double mkt_price = Double.valueOf(salesProd[5].toString());
				
				order_amount = order_amount + sales_price;
				pay_amount = pay_amount + sales_price;
				
				OrderGoods orderGoods = new OrderGoods();
				orderGoods.setSales_id(sales_id);
				orderGoods.setSales_name(salesProd[2].toString());
				orderGoods.setProd_name(salesProd[3].toString());
				orderGoods.setProd_spec(String.valueOf(salesProd[7]));
				orderGoods.setSales_price(sales_price);
				orderGoods.setMkt_price(mkt_price);
				orderGoods.setSales_number(sales_number);
				orderGoods.setSales_icon(salesProd[5].toString());
				orderGoods.setStatus(1L);
				orderGoods.setCreate_time(current_time);
				goodsList.add(orderGoods);
				
				
//				GoodsStockOutbound goodsStockOutbound = new GoodsStockOutbound();
//				goodsStockOutbound.setProduct_id(product_id);
//				goodsStockOutbound.setOut_type(out_type)
//				goodsStockOutbound.setOut_stock(sales_number);
//				goodsStockOutbound.setTotal_stock(total_stock);
//				goodsStockOutbound.setPre_stock(pre_stock);
//				goodsStockOutbound.setRel_stock(rel_stock);
//				goodsStockOutbound.setAva_stock(ava_stock);
//				goodsStockOutbound.setRemark(remark);
//				goodsStockOutbound.setValid(valid);
//				goodsStockOutbound.setModify_time(current_time);
//				goodsStockOutbound.setCreate_time(current_time);
//				orderDao.saveObject(obj);
//				
//				goods
//				orderDao.updateObject(obj);
				
				
			}
			
			
			
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrder_sort("shufa");
			orderInfo.setOrder_status(1L);
			orderInfo.setOrder_amount(order_amount);
			orderInfo.setPay_type(orderCreateRequest.getPay_type());
			orderInfo.setPay_amount(pay_amount);
			orderInfo.setDelivery_mode(delivery.getDelivery_mode());
			orderInfo.setDelivery_name(delivery.getDelivery_name());
			orderInfo.setDelivery_phone(delivery.getDelivery_phone());
			orderInfo.setDelivery_address(delivery.getDelivery_address());
			orderInfo.setOrder_remark(orderCreateRequest.getOrder_remark());
			orderInfo.setOrder_create_time(current_time);
			orderDao.saveObject(orderInfo);
			orderInfo.setOrder_no(UtilFunction.getOrderNo(orderInfo.getOrder_id()));
		
			for(OrderGoods good : goodsList){
				good.setOrder_id(orderInfo.getOrder_id());
				orderDao.saveObject(good);
			}
			
		} catch (Exception e) {
			log.logError("订单生成失败",e);
			throw new ServiceException("订单生成失败："+e.getMessage());
		}
		
		return null;
	}
	
}
