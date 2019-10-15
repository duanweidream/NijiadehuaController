package com.hualouhui.weixin.action.sales;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.Page;
import com.hualouhui.weixin.model.home.Sales;
import com.hualouhui.weixin.service.SalesService;
import com.hualouhui.weixin.util.Parameters;


/**
 * ClassName:Search</br> Function: 商品搜索 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-15 上午10:09:34</br>
 * 
 */
public class Search extends ActionBase{
	
	public String sort_code;//分类
	public Page page;
	
	public Search(){
		 needCheckAuth =false;
	}
	
	@Override
	public void initParameters(Parameters params) throws ApiError {	
		sort_code = params.getFieldValue("sort_code");
        page = newPage(params);
	}
	
	@Override
	public Result invokeService() throws ApiError {
		Result result = new Result();
		SalesService salesService = new SalesService();
		
		salesService.searchSalesForPage(page, sort_code);
		
		List<Sales> listTrip = (List<Sales>)page.getList();
		
		
		JSONObject data = new JSONObject();
		data.put("list", JSONArray.fromObject(listTrip).toString());
		setPageInfo(page, data);
		result.put("data", data);
		return result;
	}


}
