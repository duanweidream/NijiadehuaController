package com.nijiadehua.api.controller.v1.pay;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.order.response.OrderCreateResponse;
import com.nijiadehua.api.controller.v1.order.response.OrderDetailResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.service.OrderService;
import com.nijiadehua.api.util.StringUtil;
/**
 * ClassName:PayController</br> Function: 支付相关 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2020-01-15 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value="/v/1/pay")
public class PayController{
	
	@Autowired
	OrderService orderService;
	
	@ResponseBody
	@RequestMapping(value="/unifiedorder",method=RequestMethod.POST)
	public Result create(@RequestBody String json) throws ServiceException{
		
		if(StringUtil.isEmpty(json)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			OrderCreateResponse orderCreateResponse = orderService.createOrder(json);
			
			return new Result(orderCreateResponse);
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/notify",method=RequestMethod.POST)
	public Result submit(@RequestBody String json) throws ServiceException{
		
		if(StringUtil.isEmpty(json)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			orderService.submitOrder(json);
			
			return new Result();
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public Result detail(Long user_id,String order_id) throws ServiceException{
		
		if(StringUtil.isEmpty(user_id,order_id)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			OrderDetailResponse orderDetailResponse = orderService.queryOrderDetailByOrderId(user_id, order_id);
			
			return new Result(orderDetailResponse);
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	
	
}
