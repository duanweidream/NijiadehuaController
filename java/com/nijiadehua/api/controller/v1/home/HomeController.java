package com.nijiadehua.api.controller.v1.home;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.home.response.HomeFocusResponse;
import com.nijiadehua.api.controller.v1.home.response.HomeListResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.model.Sales;
import com.nijiadehua.api.service.HomeService;

@Controller
@RequestMapping(value="/v/1")
public class HomeController{

	@ResponseBody
	@RequestMapping(value="/home/focus",method=RequestMethod.GET)
	public Result homeFocus() throws Exception {
		
		try{
			HomeService homeService = new HomeService();
			List<HomeFocusResponse> salesList = homeService.queryLocationListByLocationType(1L);
			
			return new Result(salesList);
		}catch (Exception e) {
			e.printStackTrace();
			throw ApiError.Type.BUSINESS_ERROR.toException("查询首页位置数据查询失败："+e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/home/list",method=RequestMethod.GET)
	public Result homeList() throws Exception {
		
		try{
			HomeService homeService = new HomeService();
			List<HomeListResponse> salesList = homeService.queryHomeList();
			
			return new Result(salesList);
		}catch (Exception e) {
			e.printStackTrace();
			throw ApiError.Type.BUSINESS_ERROR.toException("首页分类数据查询失败："+e.getMessage());
		}
	}
	
}
