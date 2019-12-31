package com.nijiadehua.api.controller.v1.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nijiadehua.api.base.rest.Result;
import com.nijiadehua.api.controller.v1.login.response.LoginResponse;
import com.nijiadehua.api.exception.ApiError;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.service.LoginService;

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

	@ResponseBody
	@RequestMapping(value = "/login")
	public Result login(@RequestBody String json) throws ServiceException {

		try {
			LoginService loginService = new LoginService();
			LoginResponse loginRespon = loginService.login(json);

			return new Result(loginRespon);
		} catch (Exception e) {
			return new Result(ApiError.Type.BUSINESS_ERROR.toException(e.getMessage()));
		}

	}
}
