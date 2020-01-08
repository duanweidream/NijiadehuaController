package com.nijiadehua.api.service;

import java.util.Date;
import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.base.wechat.WeChatApi;
import com.nijiadehua.api.controller.v1.login.request.LoginRequest;
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
			
			
			Sql sql = new Sql("insert into art_user_info (`wx_name`,`wx_img`,`wx_sex`,`open_id`,`phone`,`identity`,`login_count`,`create_time`,`first_login_time`,`last_login_time`)");
			sql.append("values (?,?,?,?,?,?,?,?,?,?) ");
			sql.append("on duplicate key update `wx_name`=?,`wx_img`=?,`wx_sex`=?,`phone`=?,`login_count`=`login_count`+1,`last_login_time`=?");
			sql.addParam(loginRequest.getUser().getWx_name(),loginRequest.getUser().getWx_img(),loginRequest.getUser().getWx_sex(),openid,loginRequest.getUser().getPhone(),1,1,currentTime,currentTime,currentTime);
			sql.addParam(loginRequest.getUser().getWx_name(),loginRequest.getUser().getWx_img(),loginRequest.getUser().getWx_sex(),loginRequest.getUser().getPhone(),currentTime);
			int result = jdbcTemplate.updateObject(sql);
			if(result == 0) {
				throw new ServiceException("数据执行错误");
			}
			
			Sql queryUser = new Sql(" select * from art_user_info where open_id = ? ");
			queryUser.addParam(openid);
			LoginResponse loginResponse = jdbcTemplate.findObject(queryUser, LoginResponse.class);
			if(loginResponse == null) {
				throw new ServiceException("用户信息查询错误");
			}
			
			return loginResponse;
		}catch (Exception e) {
			e.printStackTrace();
			log.logError("登录失败",e);
			throw new ServiceException("登录失败："+e.getMessage());
		}
		
	}
	
	
	public static void main(String[] args) {
		
		
	}
	
	
	
}
