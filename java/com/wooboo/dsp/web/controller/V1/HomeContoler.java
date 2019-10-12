package com.wooboo.dsp.web.controller.V1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.model.Sales;
import com.wooboo.dsp.service.V1.HomeService;
import com.wooboo.dsp.system.util.ApiError;

@Controller("V1.0/home")
@RequestMapping(value="/V1")
public class HomeContoler {
	
	@Autowired
	HomeService homeService;
	
	/**
	 * 币种资料->保存币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home")
	@ResponseBody
	public Result home() {

		try {
			System.out.println("home===");
			List<Sales> sales =  homeService.home();
			System.out.println("sales:"+sales.size());
		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}
}
