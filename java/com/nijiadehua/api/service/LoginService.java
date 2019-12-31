package com.nijiadehua.api.service;

import java.util.Date;
import java.util.List;

import com.nijiadehua.api.base.db.JdbcTemplate;
import com.nijiadehua.api.base.db.Sql;
import com.nijiadehua.api.base.log.Logger;
import com.nijiadehua.api.controller.v1.login.request.LoginRequest;
import com.nijiadehua.api.controller.v1.login.response.LoginResponse;
import com.nijiadehua.api.exception.ServiceException;
import com.nijiadehua.api.util.JsonUtil;
import com.nijiadehua.api.util.StringUtil;

public class LoginService {
	
	private Logger log = Logger.getLogger(LoginService.class);
	
	private JdbcTemplate jdbcTemplate = new JdbcTemplate();
	
	public LoginResponse login(String json) throws ServiceException{
		
		try {
			Date currentTime = new Date();
			
			LoginRequest loginRequest = (LoginRequest)JsonUtil.jsongToObject(json,LoginRequest.class);
			
			if(loginRequest == null || StringUtil.isEmpty(loginRequest.getOpen_id())) {
				throw new ServiceException("缺少必要的参数");
			}
			
			Sql sql = new Sql("insert into art_user_info (`wx_name`,`wx_img`,`wx_sex`,`open_id`,`unionid`,`phone`,`identity`,`login_count`,`create_time`,`first_login_time`,`last_login_time`)");
			sql.append("values (?,?,?,?,?,?,?,?,?,?,?) ");
			sql.append("on duplicate key update `wx_name`=?,`wx_img`=?,`wx_sex`=?,`unionid`=?,`phone`=?,`login_count`=`login_count`+1,`last_login_time`=?");
			sql.addParam(loginRequest.getWx_name(),loginRequest.getWx_img(),loginRequest.getWx_sex(),loginRequest.getOpen_id(),loginRequest.getUnionid(),loginRequest.getPhone(),1,0,currentTime,currentTime,currentTime);
			sql.addParam(loginRequest.getWx_name(),loginRequest.getWx_img(),loginRequest.getWx_sex(),loginRequest.getUnionid(),loginRequest.getPhone(),currentTime);
			int result = jdbcTemplate.updateObject(sql);
			if(result == 0) {
				throw new ServiceException("数据执行错误");
			}
			
			Sql queryUser = new Sql(" select * from art_user_info where open_id = ? ");
			queryUser.addParam(loginRequest.getOpen_id());
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
	
	
}
