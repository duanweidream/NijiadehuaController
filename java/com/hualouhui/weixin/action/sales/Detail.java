package com.hualouhui.weixin.action.sales;

import net.sf.json.JSONObject;

import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.Sales;
import com.hualouhui.weixin.service.SalesService;
import com.hualouhui.weixin.util.Parameters;
import com.hualouhui.weixin.util.StringUtil;


/**
 * ClassName:Detail</br> Function: 销售品详情 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-15 上午10:09:34</br>
 * 
 */
public class Detail extends ActionBase{
	
	public String sales_id;//分类
	
	public Detail(){
		 needCheckAuth =false;
	}
	
	@Override
	public void initParameters(Parameters params) throws ApiError {	
		sales_id = params.getFieldValue("sales_id");
		if(StringUtil.isEmpty(sales_id)){
			throw ApiError.Type.INVALID_PARAM.toException("参数错误!");
		}
	}
	
	@Override
	public Result invokeService() throws ApiError {
		Result result = new Result();
		SalesService salesService = new SalesService();
		Sales sales = salesService.findSalesBySalesId(sales_id);
		if(sales == null){
			throw ApiError.Type.BUSINESS_ERROR.toException("销售品不存在!");
		}
		
		result.put("data",JSONObject.fromObject(sales));
		return result;
	}


}
