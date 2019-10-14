package com.hualouhui.weixin.model;

import java.util.Date;

public class CampaignShare {

	public Integer id,ad_id,appmessage=0,timeline=0,qq=0,weibo=0,zone=0,integral=0;
    public String openid;
    public Date share_time;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAd_id() {
		return ad_id;
	}
	public void setAd_id(Integer ad_id) {
		this.ad_id = ad_id;
	}
	public Integer getAppmessage() {
		return appmessage;
	}
	public void setAppmessage(Integer appmessage) {
		this.appmessage = appmessage;
	}
	public Integer getTimeline() {
		return timeline;
	}
	public void setTimeline(Integer timeline) {
		this.timeline = timeline;
	}
	public Integer getQq() {
		return qq;
	}
	public void setQq(Integer qq) {
		this.qq = qq;
	}
	public Integer getWeibo() {
		return weibo;
	}
	public void setWeibo(Integer weibo) {
		this.weibo = weibo;
	}
	public Integer getZone() {
		return zone;
	}
	public void setZone(Integer zone) {
		this.zone = zone;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Date getShare_time() {
		return share_time;
	}
	public void setShare_time(Date share_time) {
		this.share_time = share_time;
	}
	
}
