package com.nijiadehua.api.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.dao.OrderDao;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.model.GoodsSalesInfo;
import com.nijiadehua.api.model.OrderGoods;
import com.nijiadehua.api.model.OrderInfo;
import com.nijiadehua.api.model.order.create.request.OrderCreateRequest;
import com.nijiadehua.api.model.order.create.request.OrderCreateRequest.Sales;
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

	public void createOrder(String json) throws ServiceException{
		try {
			
			log.logInfo("json:"+json);
			OrderCreateRequest orderCreateRequest = (OrderCreateRequest)JsonUtil.jsongToObject(json,OrderCreateRequest.class);
			log.logInfo("sales:"+orderCreateRequest.getSales().length);
			
			double order_amount = 0;
			double pay_amount = 0;
			List<OrderGoods> goodsList = new ArrayList<OrderGoods>();
			for(Sales sales : orderCreateRequest.getSales()){
				Long sales_id = sales.getSales_id();
				int sales_number = sales.getSales_number();
				log.logInfo("sales_id:"+sales_id+" sales_number:"+sales_number);	
				
				GoodsSalesInfo goodsSalesInfo = orderDao.getObject(sales_id,GoodsSalesInfo.class);
				
				if(goodsSalesInfo == null){
					throw new ServiceException("销售品【"+sales_id+"】不存在");
				}
				
				order_amount = order_amount + goodsSalesInfo.getSales_price();
				pay_amount = pay_amount + goodsSalesInfo.getSales_price();
				
				OrderGoods orderGoods = new OrderGoods();
				orderGoods.setSales_id(sales_id);
				orderGoods.setSales_name(goodsSalesInfo.getSales_name());
				orderGoods.setSales_title(goodsSalesInfo.getSubtitle());
				orderGoods.setSales_price(goodsSalesInfo.getSales_price());
				orderGoods.setSales_number(sales_number);
				orderGoods.setSales_icon(goodsSalesInfo.getSales_img());
				orderGoods.setStatus(1L);
				orderGoods.setCreate_time(new Date());
				goodsList.add(orderGoods);
				
			}
			
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setOrder_sort("shufa");
			orderInfo.setOrder_amount(order_amount);
			orderInfo.setPay_amount(pay_amount);
			orderInfo.setOrder_status(1L);
			orderInfo.setOrder_create_time(new Date());
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
		
		
	}
	
}
