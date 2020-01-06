package com.nijiadehua.api.controller.v1.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.service.OrderService;
import com.nijiadehua.api.util.StringUtil;

/**
 * ClassName:Create</br> Function: 订单生成 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-15 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value="/v/1/mingjia/order")
public class MingjiaOrderController{
	
	@Autowired
	OrderService orderService;
	
	@ResponseBody
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public Result create(@RequestBody String json) throws ServiceException{
		
		if(StringUtil.isEmpty(json)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			orderService.createOrder(json);
			
			return new Result();
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public Result list(@RequestBody String json) throws ServiceException{
		
		if(StringUtil.isEmpty(json)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			//orderService.createOrder(json);
			
			return new Result();
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/detail",method=RequestMethod.GET)
	public Result detail(@RequestBody String json) throws ServiceException{
		
		if(StringUtil.isEmpty(json)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			//orderService.createOrder(json);
			
			return new Result();
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value="/cancel",method=RequestMethod.GET)
	public Result cancel(String order_no) throws ServiceException{
		
		if(StringUtil.isEmpty(order_no)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			//orderService.cancel(json);
			
			return new Result();
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	
}
