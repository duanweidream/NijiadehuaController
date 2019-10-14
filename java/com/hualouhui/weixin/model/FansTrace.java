package com.hualouhui.weixin.model;

import java.io.Serializable;
import java.util.Date;

public class FansTrace implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id,integral,fans_integral;
	private Date created_time;
	private String trace_desc,trace_name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public Integer getFans_integral() {
		return fans_integral;
	}
	public void setFans_integral(Integer fans_integral) {
		this.fans_integral = fans_integral;
	}
	public Date getCreated_time() {
		return created_time;
	}
	public void setCreated_time(Date created_time) {
		this.created_time = created_time;
	}
	public String getTrace_desc() {
		return trace_desc;
	}
	public void setTrace_desc(String trace_desc) {
		this.trace_desc = trace_desc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTrace_name() {
		return trace_name;
	}
	public void setTrace_name(String trace_name) {
		this.trace_name = trace_name;
	}

}
