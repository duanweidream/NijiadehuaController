package com.nijiadehua.api.base.rest;
import net.sf.json.JSONObject;
import com.nijiadehua.api.exception.ApiError;

public class Result {

	public int code;
	public String msg;
    public Object data;
    
    
    public Result(ApiError error) {
    	this.data = new JSONObject();
    	this.code=error.getCode();
    	this.msg=error.getMsg();
    }
    
    
    public Result() {
    	this.code=0;
    	this.msg="操作成功";
    	this.data = new JSONObject();
    }
    
    public Result(Object object) {
    	this.code=0;
    	this.msg="操作成功";
    	this.data = object;
    }
    
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}


	public void setData(Object data) {
		this.data = data;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}


	public void setError(ApiError error){
		this.code = error.getCode();
		this.msg = error.getMsg();
	}
    
    
	
}
