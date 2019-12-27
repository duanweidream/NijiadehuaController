package com.nijiadehua.api.controller.v1.home.response;

/**
 * MenuInfo entity. @author MyEclipse Persistence Tools
 */
public class HomeFocusResponse implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long location_id;
	private String location_name;
	private String img_url;
	private String land_url;
	private String location_desc;
	

	// Constructors

	/** default constructor */
	public HomeFocusResponse() {
	}


	public Long getLocation_id() {
		return location_id;
	}


	public void setLocation_id(Long location_id) {
		this.location_id = location_id;
	}


	public String getLocation_name() {
		return location_name;
	}


	public void setLocation_name(String location_name) {
		this.location_name = location_name;
	}


	public String getImg_url() {
		return img_url;
	}


	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}


	public String getLand_url() {
		return land_url;
	}


	public void setLand_url(String land_url) {
		this.land_url = land_url;
	}


	public String getLocation_desc() {
		return location_desc;
	}


	public void setLocation_desc(String location_desc) {
		this.location_desc = location_desc;
	}

	
	
}