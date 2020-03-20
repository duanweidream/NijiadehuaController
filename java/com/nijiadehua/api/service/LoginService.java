package com.nijiadehua.api.service;

import java.util.Date;
import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.base.wechat.WeChatApi;
import com.nijiadehua.api.controller.v1.login.request.LoginRequest;
import com.nijiadehua.api.controller.v1.login.request.LoginRequest.User;
import com.nijiadehua.api.controller.v1.login.response.LoginResponse;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.util.JsonUtil;
import com.nijiadehua.api.util.StringUtil;

import net.sf.json.JSONObject;

public class LoginService {
	
	private Logger log = Logger.getLogger(LoginService.class);
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public LoginResponse login(String json) throws ServiceException{
		
		try {
			
			Date currentTime = new Date();
			
			LoginRequest loginRequest = (LoginRequest)JsonUtil.jsongToObject(json,LoginRequest.class);
			
			if(loginRequest == null || StringUtil.isEmpty(loginRequest.getCode())) {
				throw new ServiceException("缺少必要的参数");
			}
			
			
			JSONObject authInfo = WeChatApi.getOpenIdByCode(loginRequest.getCode());
			if(!authInfo.has("openid")) {
				int errcode = authInfo.getInt("errcode");
				String errmsg = authInfo.getString("errmsg");
				
				if(errcode != 0) {
					throw new ServiceException("调用微信接口错误："+errmsg);
				}
				
			}
			String openid = authInfo.getString("openid");
			
			Sql queryUser = new Sql(" select * from art_user_info where open_id = ? ");
			queryUser.addParam(openid);
			LoginResponse loginResponse = jdbcTemplate.findObject(queryUser, LoginResponse.class);
			
			User user = loginRequest.getUser();
			if(loginResponse == null) {
				Sql sql = new Sql("insert into art_user_info (`wx_name`,`wx_img`,`wx_sex`,`open_id`,`phone`,`identity`,`login_count`,`create_time`,`first_login_time`,`last_login_time`)");
				sql.append("values (?,?,?,?,?,?,?,?,?,?) ");
				sql.addParam(user.getWx_name(),user.getWx_img(),user.getWx_sex(),openid,user.getPhone(),1,1,currentTime,currentTime,currentTime);
				Long id = jdbcTemplate.saveObject(sql);
				if(id == null) {
					throw new ServiceException("数据执行错误");
				}
				
				loginResponse = new LoginResponse();
				loginResponse.setWx_id(id);
				loginResponse.setWx_name(user.getWx_name());
				loginResponse.setWx_img(user.getWx_img());
				loginResponse.setWx_sex(user.getWx_sex());
				loginResponse.setPhone(user.getPhone());
				return loginResponse;
			}else {
				Sql sql = new Sql(" update art_user_info set `wx_name`=?,`wx_img`=?,`wx_sex`=?,`phone`=?,`login_count`=`login_count`+1,`last_login_time`=? where wx_id = ? ");
				sql.addParam(loginRequest.getUser().getWx_name(),loginRequest.getUser().getWx_img(),loginRequest.getUser().getWx_sex(),loginRequest.getUser().getPhone(),currentTime,loginResponse.getWx_id());
				int result = jdbcTemplate.updateObject(sql);
				if(result == 0) {
					throw new ServiceException("数据执行错误");
				}
				
				loginResponse.setWx_id(loginResponse.getWx_id());
				loginResponse.setWx_name(user.getWx_name());
				loginResponse.setWx_img(user.getWx_img());
				loginResponse.setWx_sex(user.getWx_sex());
				loginResponse.setPhone(user.getPhone());
				return loginResponse;
				
			}
		
		}catch (Exception e) {
			e.printStackTrace();
			log.logError("登录失败",e);
			throw new ServiceException("登录失败："+e.getMessage());
		}
		
	}

	
	
}
