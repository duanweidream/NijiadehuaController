package com.nijiadehua.api.base.db;

import java.sql.ResultSet;

import com.nijiadehua.api.exception.ApiError;
 public abstract interface RowMapper {
	public Object mapRow(ResultSet rs, int arg1) throws ApiError;
	public Object mapRow(ResultSet rs) throws ApiError;
}
