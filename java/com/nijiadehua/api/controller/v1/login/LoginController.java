package com.nijiadehua.api.controller.v1.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.login.response.LoginResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.service.LoginService;

import net.sf.json.JSONObject;

/**
 * ClassName:LoginController</br>
 * Function: 小程序用户登录 </br>
 * 
 * @author duanwei</br>
 * @version 1.0</br>
 * @Date 2019-12-30 上午10:09:34</br>
 * 
 */
@Controller
@RequestMapping(value = "/v/1/")
public class LoginController {
	
	private static Logger log = Logger.getLogger("api");
	
	@ResponseBody
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public Result login(@RequestBody String json) throws ServiceException {
		log.logInfo("[/v/1/login]request:"+json);
		LoginResponse loginRespon = null;
		try {
			
			LoginService loginService = new LoginService();
			loginRespon = loginService.login(json);
			
		} catch (Exception e) {
			log.logInfo("[/v/1/login]error:"+e.getMessage());
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}
		log.logInfo("[/v/1/login]response:"+JSONObject.fromObject(loginRespon).toString());
		return new Result(loginRespon);
	}
}
