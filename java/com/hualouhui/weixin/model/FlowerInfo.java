package com.hualouhui.weixin.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

public class FlowerInfo implements Serializable{
	
	public Long id;
	public Long user_id;
	public String info_sort;
	public String info_content;
	public String info_contact;
	public String info_mobile;
	public List<FlowerImg> info_imgs = new ArrayList<FlowerImg>();
	public Date create_time;
	
	public String create_time_format;
	private String wx_header;
	private String wx_nick;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getInfo_sort() {
		return info_sort;
	}
	public void setInfo_sort(String info_sort) {
		this.info_sort = info_sort;
	}
	public String getInfo_content() {
		return info_content;
	}
	public void setInfo_content(String info_content) {
		this.info_content = info_content;
	}
	public String getInfo_contact() {
		return info_contact;
	}
	public void setInfo_contact(String info_contact) {
		this.info_contact = info_contact;
	}
	public String getInfo_mobile() {
		return info_mobile;
	}
	public void setInfo_mobile(String info_mobile) {
		this.info_mobile = info_mobile;
	}
	
	public List<FlowerImg> getInfo_imgs() {
		return info_imgs;
	}
	public void setInfo_imgs(List<FlowerImg> info_imgs) {
		this.info_imgs = info_imgs;
	}
	
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public String getWx_header() {
		return wx_header;
	}
	public void setWx_header(String wx_header) {
		this.wx_header = wx_header;
	}
	public String getWx_nick() {
		return wx_nick;
	}
	public void setWx_nick(String wx_nick) {
		this.wx_nick = wx_nick;
	}
	public String getCreate_time_format() {
		return create_time_format;
	}
	public void setCreate_time_format(String create_time_format) {
		this.create_time_format = create_time_format;
	}
	
	
	
}

