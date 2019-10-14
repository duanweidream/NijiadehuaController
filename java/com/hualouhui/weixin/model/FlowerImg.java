package com.hualouhui.weixin.model;

import java.io.Serializable;
import java.util.Date;

import net.sf.json.JSONArray;

public class FlowerImg implements Serializable{
	
	private String mimeType;
	private String dataURL;
	private Long flowerId;
	public String getMimeType() {
		return mimeType;
	}
	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}
	public String getDataURL() {
		return dataURL;
	}
	public void setDataURL(String dataURL) {
		this.dataURL = dataURL;
	}
	public Long getFlowerId() {
		return flowerId;
	}
	public void setFlowerId(Long flowerId) {
		this.flowerId = flowerId;
	}
	
	
}

