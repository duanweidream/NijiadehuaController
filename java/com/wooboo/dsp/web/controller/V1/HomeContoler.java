package com.wooboo.dsp.web.controller.V1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.exception.ServiceException;
import com.wooboo.dsp.model.ArtistInfo;
import com.wooboo.dsp.system.util.ApiError;

@Controller("V1.0/home")
@RequestMapping(value="/V1.0")
public class HomeContoler {
	/**
	 * 币种资料->保存币种
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home")
	@ResponseBody
	public Result addSu(ArtistInfo artistInfo) {

		try {
			
		} catch (ServiceException e) {
			return new Result(ApiError.Type.SERVICE_EXCEPTION.toException(e
					.getMessage()));
		}
		return new Result();

	}
}
