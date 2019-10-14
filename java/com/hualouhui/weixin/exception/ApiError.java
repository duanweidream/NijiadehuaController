package com.hualouhui.weixin.exception;

import net.sf.json.JSONObject;

public class ApiError extends Exception{
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 4993240651886084140L;
	public int errorCode;
    private String hint;
    
    public ApiError(int errorCode, String errMessage, String errHint) {
        super(errMessage);
        this.hint = errHint;
        
        System.out.println(this.hint);
        
        this.errorCode = errorCode;
    }
    
    public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getHint() {
		return hint;
	}

	public void setHint(String hint) {
		this.hint = hint;
	}

	public JSONObject toJSONObject() {
    	JSONObject retObj = new JSONObject();
    	retObj.put("code", errorCode);
    	retObj.put("error_hint", hint);
    	return retObj;
    }
    
    public String toString() {
    	return "{\"error_code\":"+errorCode+", \"error_msg\":\""+getMessage()+"\", \"error_hint\":\""+hint+"\"}";
    }
    
    public static enum Type {
    	NO_SUCH_SERVICE(1000, "没有对应的服务"),
        INTERNAL_ERROR(1001, "内部错误"),
        NOT_LOGGED_IN(2001, "尚未登录"), 
        INVALID_PARAM(3001, "参数错误"),
        NULL_DATA(3002, "查不到相应数据"),
        LOGIC_ERROR(3003,"逻辑错误"),
        DB_OPERATE_ERROR(3004, "数据库操作错误");
       
        
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
