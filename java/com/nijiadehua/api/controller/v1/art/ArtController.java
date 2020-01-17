package com.nijiadehua.api.controller.v1.art;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.art.response.ArtResponse;
import com.nijiadehua.api.controller.v1.login.response.LoginResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.service.ArtService;

import net.sf.json.JSONObject;

/**
 * ClassName:ArtController</br>
 * Function: 小程序用户登录 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2020-01-17 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value = "/v/1/mingjia")
public class ArtController {
	
	private static Logger log = Logger.getLogger("api");
	
	@Autowired
	private ArtService artService;
	
	@ResponseBody
	@RequestMapping(value = "/art",method=RequestMethod.GET)
	public Result queryArtInfo(Long art_id) throws ServiceException {
		log.logInfo("[/v/1/mingjia/art]request:"+art_id);
		try {
			
			ArtResponse artResponse = artService.queryArtInfoByArtId(art_id);
			
			log.logInfo("[/v/1/mingjia/art]response:"+JSONObject.fromObject(artResponse).toString());
			return new Result(artResponse);
		} catch (Exception e) {
			log.logInfo("[/v/1/mingjia/art]error:"+e.getMessage());
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
	}
}
