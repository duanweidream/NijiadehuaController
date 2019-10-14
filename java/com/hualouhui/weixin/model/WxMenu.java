package com.hualouhui.weixin.model;

import java.util.List;

public class WxMenu {

	public String type,name,url,key;
	public List<WxMenu> sub_button;
	public WxMenu(){}
	public WxMenu(String type, String name, String url, String key) {
		super();
		this.type = type;
		this.name = name;
		this.url = url;
		this.key = key;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<WxMenu> getSub_button() {
		return sub_button;
	}
	public void setSub_button(List<WxMenu> sub_button) {
		this.sub_button = sub_button;
	}
	
}
