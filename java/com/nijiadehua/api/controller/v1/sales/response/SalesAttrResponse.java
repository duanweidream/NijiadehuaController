package com.nijiadehua.api.controller.v1.sales.response;

import java.util.ArrayList;
import java.util.List;

public class SalesAttrResponse {

	private Long attr_id;
	private String attr_name;
	private List<SalesValueResponse> values = new ArrayList<SalesValueResponse>();
	
	public Long getAttr_id() {
		return attr_id;
	}
	public void setAttr_id(Long attr_id) {
		this.attr_id = attr_id;
	}
	public String getAttr_name() {
		return attr_name;
	}
	public void setAttr_name(String attr_name) {
		this.attr_name = attr_name;
	}
	public List<SalesValueResponse> getValues() {
		return values;
	}
	public void setValues(List<SalesValueResponse> values) {
		this.values = values;
	}

	
	
	
	
}
