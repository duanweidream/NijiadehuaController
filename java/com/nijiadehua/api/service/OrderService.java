package com.nijiadehua.api.service;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.model.order.create.request.OrderCreateRequest;
import com.nijiadehua.api.model.order.create.request.OrderCreateRequest.Sales;
import com.nijiadehua.api.util.JsonUtil;


/**
 * ClassName:Create</br> Function: 订单服务 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-24 上午10:09:34</br>
 * 
 */
public class OrderService {
	
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();

	public void create(String json) throws ApiError{
		try {
			
			System.out.println("json:"+json);
			OrderCreateRequest orderCreateRequest = (OrderCreateRequest)JsonUtil.jsongToObject(json,OrderCreateRequest.class);
			System.out.println("sales:"+orderCreateRequest.getSales().length);
			
			for(Sales sales : orderCreateRequest.getSales()){
				Long sales_id = sales.getSales_id();
				int sales_number = sales.getSales_number();
				System.out.println("sales_id:"+sales_id+" sales_number:"+sales_number);	
				
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
}
