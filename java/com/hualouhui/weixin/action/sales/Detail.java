package com.hualouhui.weixin.action.sales;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.FlowerImg;
import com.hualouhui.weixin.model.FlowerInfo;
import com.hualouhui.weixin.model.Page;
import com.hualouhui.weixin.service.FlowerInfoService;
import com.hualouhui.weixin.service.SalesService;
import com.hualouhui.weixin.util.Parameters;


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
	}
	
	@Override
	public Result invokeService() throws ApiError {
		Result result = new Result();
		SalesService salesService = new SalesService();
		salesService.findSalesBySalesId(sales_id);
		
		result.put("data", data);
		return result;
	}


}
