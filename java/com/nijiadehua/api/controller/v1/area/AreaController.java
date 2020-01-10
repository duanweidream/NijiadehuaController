package com.nijiadehua.api.controller.v1.area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.area.response.AreaResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.service.AreaService;


/**
 * ClassName:AreaController</br> Function: 地域查询 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-10-15 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value="/v/1/area")
public class AreaController {
	
	@Autowired
	private AreaService areaService;
	
	@ResponseBody
	@RequestMapping(value="/search",method=RequestMethod.GET)
	public Result search() throws Exception {
		
		try {
			List<AreaResponse> areaList = areaService.queryAreaList();
			return new Result(areaList);
		}catch (ServiceException e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		
	}


}
