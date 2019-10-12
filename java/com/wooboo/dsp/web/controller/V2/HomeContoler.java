package com.wooboo.dsp.web.controller.V2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.system.util.ApiError;

@Controller("V2.0/home")
@RequestMapping(value="/V2")
public class HomeContoler {
	
	/**
	 * 币种资料->保存币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/test")
	@ResponseBody
	public ModelAndView test() {
		ModelAndView model = new ModelAndView("test");
		return model;

	}
	
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
		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}
}
