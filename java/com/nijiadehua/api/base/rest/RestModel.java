package com.nijiadehua.api.base.rest;
public class RestModel {

	
	
	public RestModel(){
		
	}
	
	/**
	 * 响应码
	 */
	private long code;
	/**
	 * 响应描述
	 */
	private String errorDescription = "";
	/**
	 * 响应数据
	 */
	private Object dataObject="" ;
	
	public long getCode() {
		return code;
	}
	public void setCode(long code) {
		this.code = code;
	}

	public String getErrorDescription() {
		return errorDescription;
	}

	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	
	public Object getDataObject() {
		return dataObject;
	}

	public void setDataObject(Object dataObject) {
		this.dataObject = dataObject;
	}
	
	
}
