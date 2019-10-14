package com.hualouhui.weixin.model;

import java.io.Serializable;
import java.util.Date;

public class Fans implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String subscribe="0",openid,nickname,language,city,province,country,headimgurl;
	public String unionid,remark,hb_date;
	public Integer sex,groupid,integral,hb_times;
	public Date subscribe_time;
	
	public Fans(){}
	
	public Fans(String subscribe, String openid, String nickname, String language, String city, String province,
			String country, String headimgurl, String unionid, String remark, Integer sex,
			Integer groupid) {
		super();
		this.subscribe = subscribe;
		this.openid = openid;
		this.nickname = nickname;
		this.language = language;
		this.city = city;
		this.province = province;
		this.country = country;
		this.headimgurl = headimgurl;
		this.unionid = unionid;
		this.remark = remark;
		this.sex = sex;
		this.groupid = groupid;
	}


	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	public Date getSubscribe_time() {
		return subscribe_time;
	}
	public void setSubscribe_time(Date subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getGroupid() {
		return groupid;
	}
	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public String getHb_date() {
		return hb_date;
	}

	public void setHb_date(String hb_date) {
		this.hb_date = hb_date;
	}

	public Integer getHb_times() {
		return hb_times;
	}

	public void setHb_times(Integer hb_times) {
		this.hb_times = hb_times;
	}
	
}
