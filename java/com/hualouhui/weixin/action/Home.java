package com.hualouhui.weixin.action;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.hualouhui.weixin.action.base.ActionBase;
import com.hualouhui.weixin.base.rest.Result;
import com.hualouhui.weixin.exception.ApiError;
import com.hualouhui.weixin.model.Sales;
import com.hualouhui.weixin.service.FlowerInfoService;
import com.hualouhui.weixin.util.Parameters;

public class Home extends ActionBase {

	@Override
	public void initParameters(Parameters params) throws ApiError {
		// TODO Auto-generated method stub
		super.initParameters(params);
	}

	@Override
	public Result invokeService() throws ApiError {
		Result rest = new Result();
		JSONObject jsonObject = new JSONObject();
		
		FlowerInfoService flowerInfoService = new FlowerInfoService();
		List<Sales> salesList = flowerInfoService.test();
		jsonObject.put("code",0);
		jsonObject.put("data",salesList);
		
		rest.ret = jsonObject;
		//rest.setRet(jsonObject);
		
		return rest;
	}

}
