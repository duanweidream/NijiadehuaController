package com.nijiadehua.api.controller.v1;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.model.Sales;
import com.nijiadehua.api.service.HomeService;
import com.nijiadehua.api.util.StringUtil;

@Controller
@RequestMapping(value="/v/1")
public class HomeController{

	@ResponseBody
	@RequestMapping(value="/home")
	public Result invokeService() throws ApiError {
		
		try{
			HomeService homeService = new HomeService();
			List<Sales> salesList = homeService.queryHomeSalesList();
			for(Sales sales : salesList){
				String sales_img = sales.getSales_img();
				if(!StringUtil.isEmpty(sales_img)){
					String[] imgs = sales_img.split(",");
					if(imgs.length > 0){
						sales.setSales_img(imgs[0]);
					}
				}
			}
			
			return new Result(salesList);
		}catch (Exception e) {
			e.printStackTrace();
			throw ApiError.Type.BUSINESS_ERROR.toException("首页数据查询失败");
		}
		
		
	}

}
