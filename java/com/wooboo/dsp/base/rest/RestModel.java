package com.wooboo.dsp.base.rest;
import com.wooboo.dsp.base.enums.NijiadehuaEnums;

/**
 * REST 返回JSON 标准对象
 * 
 * @author Administrator
 * 
 */
public class RestModel  {
	
	
	public RestModel(){
		
	}
	
	public RestModel(NijiadehuaEnums errorEnum){
			this.code = errorEnum.getCode();
			this.errorDescription = errorEnum.getDesc();
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
	
	public static RestModel getRestModel(long code,String desc){
		RestModel m=new RestModel();
		m.setCode(code);
		m.setErrorDescription(desc);
		return m;
	}
	
	public static RestModel getResModel(long code,String desc,Object data){
		RestModel rm=new RestModel();
		rm.setCode(code);
		rm.setErrorDescription(desc);
		rm.setDataObject(data);
		return rm;
	}
	
	public static String strToJson(String key,String val){
		
		return "{\""+key+"\":\""+val+"\"}";
	}

	@Override
	public String toString(){
		return "{\"code\":"+this.code+",\"errorDescription\":\""+this.errorDescription+"\",\"dataObject\":\""+this.dataObject+"\"}";
	}
	
}
