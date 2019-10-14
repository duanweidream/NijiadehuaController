package com.hualouhui.weixin.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Kefang {
	
	private Integer id;
	private String room_name;
	private Long belongs_to_etc;
	private String etc_name;
	private double etc_x;
	private double etc_y;
	private String room_addr;
	private String room_tel;
	private String room_bed;
	private String room_floor;
	private String room_build_area;
	private String room_have_breakfast;
	private String room_have_network;
	private String room_policy;
	private Float room_day_price;
	private Float room_prom_price;
	private String room_desc;
	private String room_pic_url;
	private Timestamp create_time;
	private int room_star;
	
	private int distance;
	
	private List<Kefang> childList = new ArrayList<Kefang>();
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public Long getBelongs_to_etc() {
		return belongs_to_etc;
	}
	public void setBelongs_to_etc(Long belongs_to_etc) {
		this.belongs_to_etc = belongs_to_etc;
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
	public String getRoom_addr() {
		return room_addr;
	}
	public void setRoom_addr(String room_addr) {
		this.room_addr = room_addr;
	}
	public String getRoom_tel() {
		return room_tel;
	}
	public void setRoom_tel(String room_tel) {
		this.room_tel = room_tel;
	}
	public String getRoom_bed() {
		return room_bed;
	}
	public void setRoom_bed(String room_bed) {
		this.room_bed = room_bed;
	}
	public String getRoom_floor() {
		return room_floor;
	}
	public void setRoom_floor(String room_floor) {
		this.room_floor = room_floor;
	}
	public String getRoom_build_area() {
		return room_build_area;
	}
	public void setRoom_build_area(String room_build_area) {
		this.room_build_area = room_build_area;
	}
	public String getRoom_have_breakfast() {
		return room_have_breakfast;
	}
	public void setRoom_have_breakfast(String room_have_breakfast) {
		this.room_have_breakfast = room_have_breakfast;
	}
	public String getRoom_have_network() {
		return room_have_network;
	}
	public void setRoom_have_network(String room_have_network) {
		this.room_have_network = room_have_network;
	}
	public String getRoom_policy() {
		return room_policy;
	}
	public void setRoom_policy(String room_policy) {
		this.room_policy = room_policy;
	}
	public Float getRoom_day_price() {
		return room_day_price;
	}
	public void setRoom_day_price(Float room_day_price) {
		this.room_day_price = room_day_price;
	}
	public Float getRoom_prom_price() {
		return room_prom_price;
	}
	public void setRoom_prom_price(Float room_prom_price) {
		this.room_prom_price = room_prom_price;
	}
	public String getRoom_desc() {
		return room_desc;
	}
	public void setRoom_desc(String room_desc) {
		this.room_desc = room_desc;
	}
	public String getRoom_pic_url() {
		return room_pic_url;
	}
	public void setRoom_pic_url(String room_pic_url) {
		this.room_pic_url = room_pic_url;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public int getRoom_star() {
		return room_star;
	}
	public void setRoom_star(int room_star) {
		this.room_star = room_star;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public List<Kefang> getChildList() {
		return childList;
	}
	public void setChildList(List<Kefang> childList) {
		this.childList = childList;
	}
	
	
	
}
