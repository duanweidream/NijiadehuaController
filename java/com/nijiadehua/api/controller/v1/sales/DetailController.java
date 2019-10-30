package com.nijiadehua.api.controller.v1.sales;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.model.Sales;
import com.nijiadehua.api.service.SalesService;
import com.nijiadehua.api.util.StringUtil;


/**
 * ClassName:Detail</br> Function: 销售品详情 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-15 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value="/v/1/sales")
public class DetailController{

	@ResponseBody
	@RequestMapping(value="/detail")
	public Result invokeService(String sales_id) throws ApiError {
		
		if(StringUtil.isEmpty(sales_id)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		SalesService salesService = new SalesService();
		Sales sales = salesService.findSalesBySalesId(sales_id);
		if(sales == null){
			return new Result(ApiError.Type.BUSINESS_ERROR.toException("销售品不存在!"));
		}
		
		return new Result(sales);
	}


}
