package com.nijiadehua.api.controller.v1.sales;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.model.Page;
import com.nijiadehua.api.model.home.Sales;
import com.nijiadehua.api.service.SalesService;
import com.nijiadehua.api.util.NumberUtil;


/**
 * ClassName:Search</br> Function: 商品搜索 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-15 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value="/v/1/sales")
public class SearchController{
	
	@ResponseBody
	@RequestMapping(value="/search")
	public Result invokeService(String sort_code,String startIndex,String itemCount) throws ApiError {
		
		Integer startIndex1 = NumberUtil.getInteger(startIndex, 0);
		Integer itemCount1 =  NumberUtil.getInteger(itemCount, 10);
		
		Page page = new Page(startIndex1, itemCount1, null);
		
		SalesService salesService = new SalesService();
		
		salesService.searchSalesForPage(page, sort_code);
		
		List<Sales> listTrip = (List<Sales>)page.getList();
		
		JSONObject data = new JSONObject();
		data.put("list", listTrip);
		data.put("itemCount", page.itemCount);
        data.put("totalCount", page.totalCount);
        data.put("startIndex", page.startIndex);
        data.put("currentPage",page.currentPage);
        data.put("totalPage", page.totalPage);
		return new Result(data);
	}


}
