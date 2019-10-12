package com.wooboo.dsp.web.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wooboo.dsp.base.enums.ATTSystem;
import com.wooboo.dsp.base.log.Logger;
import com.wooboo.dsp.base.rest.Result;
import com.wooboo.dsp.model.SysUser;
import com.wooboo.dsp.service.system.SystemService;
import com.wooboo.dsp.system.util.ApiError;
import com.wooboo.dsp.system.util.DateUtil;
import com.wooboo.dsp.system.util.HttpHelp;
import com.wooboo.dsp.system.util.SessionHelper;
import com.wooboo.dsp.util.AESKit;
import com.wooboo.dsp.util.MD5Util;
import com.wooboo.dsp.util.StringUtil;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value="login")
public class LoginController{
	
	private static Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private SystemService systemService;
	
	@ResponseBody
	@RequestMapping(value="/slider")
	public Result slider(){
		
		Result result = new Result();
		String token = StringUtil.createId(32);
		SessionHelper.set(ATTSystem.SLIDER_TOKEN, token);
		JSONObject data = new JSONObject();
        data.put("token", token);
        result.data=data;
		return result;
	}
	
	
	
	@ResponseBody
	@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
	public  Result login(@ModelAttribute SysUser user, HttpServletRequest request,HttpServletResponse response,String remember,String slider){


		Result result = new Result();
		String slider_code = (String)SessionHelper.get(ATTSystem.SLIDER_TOKEN);
		
		//验证滑动code
		if(!StringUtil.equals(slider_code, slider)){
			return new Result(ApiError.Type.LOGIC_ERROR.toException("滑动验证错误！"));
		}
		
		if(!StringUtil.isEmpty(user.getUserName(),user.getPassword())){
			
			String srcUser = AESKit.Decrypt(user.getUserName());
			String srcPassword = AESKit.Decrypt(user.getPassword());
			
			SysUser u = systemService.findSysUserByName(srcUser);
			if(u==null){
				return new Result(ApiError.Type.NULL_DATA.toException("用户名或密码错误！"));
			}else{
				//用户锁定，并且在30分钟以内 不管密码多错，提示用户锁定状态.
				if(StringUtil.equals(u.getState(), ATTSystem.LOGIC_LOCK) && null!=u.getLockTime() && DateUtil.getMinByTwoDate(u.getLockTime(),new Date())<30){
					Long minute = DateUtil.getMinByTwoDate(u.getLockTime(),new Date());
					return new Result(ApiError.Type.LOGIC_ERROR.toException("用户已锁定，请"+(30-minute)+"分钟后试!"));
				}if(!StringUtil.equals(u.getPassword(), new MD5Util().getMD5ofStr(srcPassword))){
					//密码比较
					u.setLoginFailTime(null==u.getLoginFailTime()?1:u.getLoginFailTime()+1);
					Integer times = 3-u.getLoginFailTime();
					//超过三次则锁定用户
					if(u.getLoginFailTime()>=3){
					    u.setLockTime(new Date());
						u.setState(ATTSystem.LOGIC_LOCK);
						result = new Result(ApiError.Type.LOGIC_ERROR.toException("账号已经锁定，请30分钟后在试!"));
					}else{
						result = new Result(ApiError.Type.LOGIC_ERROR.toException("用户名或密码错误！还有"+times+"次机会。"));
					}
					systemService.updateObject(u);
					
					return result;
				}else if(StringUtil.equals(u.getState(), ATTSystem.LOGIC_FALSE)){
					return new Result(ApiError.Type.LOGIC_ERROR.toException("用户 "+u.getUserName()+" 已禁用！请与管理员联系。"));
				}else{
					//登录操作
					//如果是锁定状态则解锁
					if(StringUtil.equals(u.getState(), ATTSystem.LOGIC_LOCK)){
						u.setState(ATTSystem.LOGIC_TRUE);
						u.setLoginFailTime(0);
					}
				    systemService.updateUserForLogin(u);
				    HttpHelp.addCookie(response,null, ATTSystem.DAYS_TIMES, "/", ATTSystem.COOKIE_NAME, user.getUserName());//设置cookie
					
				    //自动登录授权token
				    if(ATTSystem.LOGIC_TRUE.equals(remember)){
				    	String ip = HttpHelp.getIpAddr(request);
				    	String token = HttpHelp.createCookie(u, ip);
				    	 HttpHelp.addCookie(response,null, ATTSystem.DAYS_TIMES, "/", "beeusertoken", token);//设置cookie
				    }
				}
			}
		}else{
			return new Result(ApiError.Type.INVALID_PARAM.toException("用户名或密码错误！"));
		}
        return result;

	
	}
	
	/**
	 * 自动登录
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/auto",method = {RequestMethod.GET,RequestMethod.POST})
	public String auto(HttpServletRequest request,	HttpServletResponse response){
	    
		if(SessionHelper.hasLogin()){
			return "redirect:/user/dashboard";
		}else{
			String token = HttpHelp.getCookieValue("beeusertoken");
			String ip = HttpHelp.getIpAddr(request);
			SysUser user = HttpHelp.getUserInfo(token, ip);
			if(null!=user){
				SysUser loginUser = systemService.getObject(user.getId(), SysUser.class);
				if(null!=loginUser){
				    systemService.updateUserForLogin(loginUser);
				    HttpHelp.addCookie(response,null, ATTSystem.DAYS_TIMES, "/", ATTSystem.COOKIE_NAME, loginUser.getUserName());//设置cookie
				    return "redirect:/user/index";
				}
			} 
			return "/login";
		}
	}
	
	
}
