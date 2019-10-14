package com.hualouhui.weixin.util;

import com.hualouhui.weixin.exception.ApiError;

public class ParamParser {

	public static int getInt(Parameters params, String name, int defaultValue) throws ApiError{
		String value = params.getFieldValue(name);
		try{
			if (value != null) {
				return Integer.parseInt(value);
			}else{
				return defaultValue;
			}
		}catch(NumberFormatException e) {
		}
		throw ApiError.Type.INVALID_PARAM.toException();
	}

	public static int getInt(Parameters params, String name) throws ApiError{
		String value = params.getFieldValue(name);
		try{
			if (value != null) {
				return Integer.parseInt(value);				
			}
		}catch(NumberFormatException e) {
		}
		throw ApiError.Type.INVALID_PARAM.toException();
	}

	public static String getString(Parameters params, String name) throws ApiError{
		String value = params.getFieldValue(name);
		if (value == null) {
			throw ApiError.Type.INVALID_PARAM.toException();
		}
		return value;
	}
}
