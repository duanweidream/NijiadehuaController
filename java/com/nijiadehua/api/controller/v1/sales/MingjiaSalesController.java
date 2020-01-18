package com.nijiadehua.api.controller.v1.sales;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.sales.response.DetailResponse;
import com.nijiadehua.api.controller.v1.sales.response.DetailResponse.Img;
import com.nijiadehua.api.controller.v1.sales.response.SalesAttrResponse;
import com.nijiadehua.api.controller.v1.sales.response.SearchResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.model.Page;
import com.nijiadehua.api.service.SalesService;
import com.nijiadehua.api.util.CryptoUtil;
import com.nijiadehua.api.util.NumberUtil;
import com.nijiadehua.api.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * ClassName:Search</br> Function: 商品搜索 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-15 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value="/v/1/mingjia/sales")
public class MingjiaSalesController {

	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public Result search(String sort_code,String startIndex,String itemCount) throws Exception {
		
		try {
			Integer startIndex1 = NumberUtil.getInteger(startIndex, 0);
			Integer itemCount1 =  NumberUtil.getInteger(itemCount, 10);
			
			Page page = new Page(startIndex1, itemCount1, null);
			
			SalesService salesService = new SalesService();
			
			salesService.searchSalesForPage(page, sort_code);
			
			List<SearchResponse> listTrip = (List<SearchResponse>)page.getList();
			
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
	public Result detail(Long sales_id) throws Exception {
		try {
			if(StringUtil.isEmpty(sales_id)){
				return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
			}
			
			SalesService salesService = new SalesService();
			DetailResponse sales = salesService.findSalesBySalesId(sales_id);
			if(sales == null){
				return new Result(ApiError.Type.BUSINESS_ERROR.toException("销售品不存在!"));
			}
			
			List<String> img_array = salesService.findSalesImgBySalesId(sales_id);
			sales.setSales_img(img_array);
			
			return new Result(sales);
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
	}


	@ResponseBody
	@RequestMapping(value="/attr",method=RequestMethod.GET)
	public Result attr(Long sales_id) throws Exception{
		try {
			if(StringUtil.isEmpty(sales_id)){
				return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
			}
			
			SalesService salesService = new SalesService();
			List<SalesAttrResponse> salesAttrList = salesService.findSalesAttrBySalesId(sales_id);
			if(salesAttrList == null || salesAttrList.size() == 0){
				return new Result(ApiError.Type.BUSINESS_ERROR.toException("名家销售品库存规格不存在!"));
			}
			
			return new Result(salesAttrList);
		}catch (ServiceException e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/stock",method=RequestMethod.POST)
	public Result stock(@RequestBody String json) throws Exception {
		
		try {
			
			
			if(StringUtil.isEmpty(json)){
				return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
			}
			
			JSONObject request = JSONObject.fromObject(json);
			String sales_id = request.getString("sales_id");
			String value_id = request.getString("value_id");
			
			if(StringUtil.isEmpty(sales_id,value_id)){
				return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
			}
			
			
			SalesService salesService = new SalesService();
			
			int stock = salesService.findSalesStockBySalesId(sales_id,value_id);
			
			return new Result(stock);
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
	}
	
}
