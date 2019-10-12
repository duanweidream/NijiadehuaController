package com.wooboo.dsp.base.rest;


import com.wooboo.dsp.system.util.ApiError;

import net.sf.json.JSONObject;

public class Result {

	public int code;
    public JSONObject data;
    public String message;
    
    
    public Result(ApiError error) {
    	this.data = new JSONObject();
    	this.code=error.getErrorCode();
    	this.message=error.getHint();
    }
    
    
    public Result() {
    	this.data = new JSONObject();
    	this.code=200;
    	this.message="操作成功";
    }

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	public void setError(ApiError error){
		this.code=error.errorCode;
		this.message=error.getHint();
	}
    
    
	
}
