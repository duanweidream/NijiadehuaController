package com.nijiadehua.api.controller.v1.area.response;

import java.util.ArrayList;
import java.util.List;

public class AreaResponse {

	private int area_id;
	private String area_name;
	private int parent_id;
	private List<AreaResponse> child = new ArrayList<AreaResponse>();
	public int getArea_id() {
		return area_id;
	}
	public void setArea_id(int area_id) {
		this.area_id = area_id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public List<AreaResponse> getChild() {
		return child;
	}
	public void setChild(List<AreaResponse> child) {
		this.child = child;
	}
	
	
	
	
	
	
}
