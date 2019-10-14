package com.hualouhui.weixin.model;

import java.sql.Timestamp;

public class Canting {
	
	private Integer id;
	private String restaurant_name;
	private Long belongs_to_etc;
	private String etc_name;
	private double etc_x;
	private double etc_y;
	private String restaurant_addr;
	private String restaurant_tel;
	private String restaurant_build_area;
	private String restaurant_pic_url;
	private String restaurant_desc;
	private Timestamp create_time;
	private int restaurant_star;
	
	private int distance; //两地相距多少米
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRestaurant_name() {
		return restaurant_name;
	}
	public void setRestaurant_name(String restaurant_name) {
		this.restaurant_name = restaurant_name;
	}
	public Long getBelongs_to_etc() {
		return belongs_to_etc;
	}
	public void setBelongs_to_etc(Long belongs_to_etc) {
		this.belongs_to_etc = belongs_to_etc;
	}
	public String getRestaurant_addr() {
		return restaurant_addr;
	}
	public void setRestaurant_addr(String restaurant_addr) {
		this.restaurant_addr = restaurant_addr;
	}
	public String getRestaurant_tel() {
		return restaurant_tel;
	}
	public void setRestaurant_tel(String restaurant_tel) {
		this.restaurant_tel = restaurant_tel;
	}
	public String getRestaurant_build_area() {
		return restaurant_build_area;
	}
	public void setRestaurant_build_area(String restaurant_build_area) {
		this.restaurant_build_area = restaurant_build_area;
	}
	public String getRestaurant_pic_url() {
		return restaurant_pic_url;
	}
	public void setRestaurant_pic_url(String restaurant_pic_url) {
		this.restaurant_pic_url = restaurant_pic_url;
	}
	public String getRestaurant_desc() {
		return restaurant_desc;
	}
	public void setRestaurant_desc(String restaurant_desc) {
		this.restaurant_desc = restaurant_desc;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	
	public String getEtc_name() {
		return etc_name;
	}
	public void setEtc_name(String etc_name) {
		this.etc_name = etc_name;
	}
	public double getEtc_x() {
		return etc_x;
	}
	public void setEtc_x(double etc_x) {
		this.etc_x = etc_x;
	}
	public double getEtc_y() {
		return etc_y;
	}
	public void setEtc_y(double etc_y) {
		this.etc_y = etc_y;
	}
	public int getRestaurant_star() {
		return restaurant_star;
	}
	public void setRestaurant_star(int restaurant_star) {
		this.restaurant_star = restaurant_star;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}

	
	
}
