package com.wooboo.dsp.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.system.util.HttpHelp;
import com.wooboo.dsp.system.util.SessionHelper;

@Controller
@RequestMapping(value="logout")
public class LogOutController{
	/**
	 * 退出登录
	 * @param user
	 * @param request
	 * @param response
	 * @param json
	 * @param remember
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public void logout(HttpServletRequest request,	HttpServletResponse response) throws IOException{
		SessionHelper.invalidate();
		HttpHelp.removeCookie(response,ATTSystem.COOKIE_NAME);
		HttpHelp.removeCookie(response,ATTSystem.COOKIE_BEE_USER);
		response.sendRedirect("/");
		
	}
	
}
