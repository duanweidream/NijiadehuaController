package com.hualouhui.weixin.base.db;

import java.sql.ResultSet;

import com.hualouhui.weixin.exception.ApiError;
 public abstract interface RowMapper {
	public Object mapRow(ResultSet rs, int arg1) throws ApiError;
	public Object mapRow(ResultSet rs) throws ApiError;
}
