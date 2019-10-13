package com.wooboo.dsp.web.controller.V1;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooboo.dsp.base.enums.NijiadehuaEnums;
import com.wooboo.dsp.base.rest.ResponsePrint;
import com.wooboo.dsp.base.rest.RestModel;
import com.wooboo.dsp.base.rest.RestModelManger;
import com.wooboo.dsp.base.rest.RestModelToJson;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.model.Sales;
import com.wooboo.dsp.service.V1.HomeService;

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
	public String home(HttpServletRequest request,
			HttpServletResponse response) {
		String resJson = "";
		try {
			System.out.println("home===");
			//List<Sales> sales =  homeService.home();
			
			List<Sales> sales =  homeService.home();
			
			//System.out.println("sales:"+sales.size());
			RestModel rest = RestModelManger.getRestModel(NijiadehuaEnums.SUCCESS, sales);
			resJson = RestModelToJson.getRestJosn(rest);
			ResponsePrint.outStr(response, resJson);
		} catch (ServiceException e) {
//			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
//					.getMessage()));
		}
		return null;

	}
}
