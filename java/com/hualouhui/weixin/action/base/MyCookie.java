package com.hualouhui.weixin.action.base;

public class MyCookie {

	public String name, value;
	public Integer maxage;//second
	public MyCookie(){
		
	}
	
	public MyCookie(String name, String value, Integer maxage) {
		super();
		this.name = name;
		this.value = value;
		this.maxage = maxage;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Integer getMaxage() {
		return maxage;
	}
	public void setMaxage(Integer maxage) {
		this.maxage = maxage;
	}
	
	
}
