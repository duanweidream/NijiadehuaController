package com.hualouhui.weixin.action;

import java.util.List;
import net.sf.json.JSONObject;
import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.Sales;
import com.hualouhui.weixin.service.HomeService;
import com.hualouhui.weixin.util.StringUtil;

public class Home extends ActionBase {


	@Override
	public Result invokeService() throws ApiError {
		
		JSONObject jsonObject = new JSONObject();
		
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
			jsonObject.put("code",0);
			jsonObject.put("data",salesList);
			
			return new Result(jsonObject);
		}catch (Exception e) {
			e.printStackTrace();
			throw ApiError.Type.BUSINESS_ERROR.toException("首页数据查询失败");
		}
		
		
	}

}
