package com.nijiadehua.api.controller.v1.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.address.response.AddressResponse;
import com.nijiadehua.api.controller.v1.sales.response.DetailResponse;
import com.nijiadehua.api.controller.v1.sales.response.SalesAttrResponse;
import com.nijiadehua.api.controller.v1.sales.response.SearchResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.model.Page;
import com.nijiadehua.api.service.AddressService;
import com.nijiadehua.api.service.SalesService;
import com.nijiadehua.api.util.CryptoUtil;
import com.nijiadehua.api.util.NumberUtil;
import com.nijiadehua.api.util.StringUtil;

import net.sf.json.JSONObject;

/**
 * ClassName:AddressController</br> Function: 收货地址管理 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-15 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value="/v/1/address")
public class AddressController {
	
	@Autowired
	private AddressService addressService;
	
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public Result search(Long user_id) throws Exception {
		
		if(StringUtil.isEmpty(user_id)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try {
		
			List<AddressResponse> addressList = addressService.queryAdressListByUserId(user_id);
			return new Result(addressList);
			
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public Result create(@RequestBody String json) throws Exception {
		if(StringUtil.isEmpty(json)){
			return new Result(ApiError.Type.INVALID_PARAM.toException("参数错误!"));
		}
		
		try {
			
			
			
			
			return new Result();
		}catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
	}


	
}
