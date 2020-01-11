package com.nijiadehua.api.controller.v1.address.request;

public class AddressCreateRequest {
	
	private Long user_id;
	private String delivery_name;
	private String delivery_phone;
	private String delivery_country;
	private String delivery_province;
	private String delivery_city;
	private String delivery_district;
	private String delivery_address;
	private String delivery_postal_code;
	
	
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public String getDelivery_name() {
		return delivery_name;
	}
	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}
	public String getDelivery_phone() {
		return delivery_phone;
	}
	public void setDelivery_phone(String delivery_phone) {
		this.delivery_phone = delivery_phone;
	}
	public String getDelivery_country() {
		return delivery_country;
	}
	public void setDelivery_country(String delivery_country) {
		this.delivery_country = delivery_country;
	}
	public String getDelivery_province() {
		return delivery_province;
	}
	public void setDelivery_province(String delivery_province) {
		this.delivery_province = delivery_province;
	}
	public String getDelivery_city() {
		return delivery_city;
	}
	public void setDelivery_city(String delivery_city) {
		this.delivery_city = delivery_city;
	}
	public String getDelivery_district() {
		return delivery_district;
	}
	public void setDelivery_district(String delivery_district) {
		this.delivery_district = delivery_district;
	}
	public String getDelivery_address() {
		return delivery_address;
	}
	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	public String getDelivery_postal_code() {
		return delivery_postal_code;
	}
	public void setDelivery_postal_code(String delivery_postal_code) {
		this.delivery_postal_code = delivery_postal_code;
	}
	

}
