package com.nijiadehua.api.controller.v1.order;

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
import com.nijiadehua.api.controller.v1.order.response.OrderSearchResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.model.Page;
import com.nijiadehua.api.service.OrderService;
import com.nijiadehua.api.util.NumberUtil;
import com.nijiadehua.api.util.StringUtil;

import net.sf.json.JSONObject;

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
			
			OrderCreateResponse orderCreateResponse = orderService.createOrder(json);
			
			return new Result(orderCreateResponse);
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/submit",method=RequestMethod.POST)
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
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public Result search(Long user_id,Long status,Integer currentPage) throws Exception {
		
		try {
			
			if(StringUtil.isEmpty(user_id)){
				return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
			}
			
			Page page = new Page(currentPage);
			
			
			orderService.searchOrderForPage(page,user_id,status);
			
			List<OrderSearchResponse> listTrip = (List<OrderSearchResponse>)page.getList();
			
			JSONObject data = new JSONObject();
			data.put("list", listTrip);
			data.put("itemCount", page.itemCount);
			data.put("totalCount", page.totalCount);
			data.put("startIndex", page.startIndex);
			data.put("currentPage",page.currentPage);
			data.put("totalPage", page.totalPage);
			return new Result(data);
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
	
	
	@ResponseBody
	@RequestMapping(value="/cancel",method=RequestMethod.GET)
	public Result cancel(Long user_id,String order_id) throws ServiceException{
		
		if(StringUtil.isEmpty(user_id,order_id)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			orderService.cancelOrder(user_id, order_id);
			return new Result();
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/confirm",method=RequestMethod.GET)
	public Result confirm(Long user_id,String order_id) throws ServiceException{
		
		if(StringUtil.isEmpty(user_id,order_id)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try{
			
			orderService.confirmOrder(user_id, order_id);
			return new Result();
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	
}
