package com.nijiadehua.api.controller.v1.login.request;

public class LoginRequest {

	private String wx_name;
	private String wx_img;
	private String wx_sex;
	private String open_id;
	private String unionid;
	private String phone;
	public String getWx_name() {
		return wx_name;
	}
	public void setWx_name(String wx_name) {
		this.wx_name = wx_name;
	}
	public String getWx_img() {
		return wx_img;
	}
	public void setWx_img(String wx_img) {
		this.wx_img = wx_img;
	}
	public String getWx_sex() {
		return wx_sex;
	}
	public void setWx_sex(String wx_sex) {
		this.wx_sex = wx_sex;
	}
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
