package com.hualouhui.weixin.exception;

import net.sf.json.JSONObject;

public class ApiError extends Exception{
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 4993240651886084140L;
	public int code;
    private String msg;
    
    public ApiError(int code, String errMessage, String msg) {
        super(errMessage);
        this.msg = msg;
        this.code = code;
    }
    


	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public String getMsg() {
		return msg;
	}



	public void setMsg(String msg) {
		this.msg = msg;
	}



	public JSONObject toJSONObject() {
    	JSONObject retObj = new JSONObject();
    	retObj.put("code", code);
    	retObj.put("msg", msg);
    	return retObj;
    }
    
    public String toString() {
    	return "{\"code\":"+code+", \"msg\":\""+msg+"\"}";
    }
    
    public static enum Type {
    	NO_SUCH_SERVICE(1000, "没有对应的服务"),
    	BUSINESS_ERROR(1111, "业务错误"),
        INTERNAL_ERROR(1001, "内部错误"),
        NOT_LOGGED_IN(2001, "尚未登录"), 
        INVALID_PARAM(3001, "参数错误");
       
        
        private int errorCode;
        private String hint;

        private Type(int errorCode, String hint) {
            this.errorCode = errorCode;
            this.hint = hint;
        }
        public int getErrorCode() {
            return this.errorCode;
        }
        public String getHint() {
            return this.hint;
        }
        public ApiError toException() {
            return new ApiError(errorCode, this.name(), hint);
        }
        public ApiError toException(String error) {
            return new ApiError(errorCode, this.name(), error);
        }
    }    
}
