package com.hualouhui.weixin.model;

import java.sql.Timestamp;

public class Techan {
	
	private Integer id;
	private String goods_name;
	private Long belongs_to_etc;
	private String goods_sort_level1;
	private String goods_sort_level2;
	private String goods_spec;
	private Long goods_stock;
	private Float goods_price;
	private String goods_desc;
	private String goods_icon_url;
	private String goods_pic_url;
	private String etc_name;
	private Timestamp create_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public Long getBelongs_to_etc() {
		return belongs_to_etc;
	}
	public void setBelongs_to_etc(Long belongs_to_etc) {
		this.belongs_to_etc = belongs_to_etc;
	}
	public String getGoods_sort_level1() {
		return goods_sort_level1;
	}
	public void setGoods_sort_level1(String goods_sort_level1) {
		this.goods_sort_level1 = goods_sort_level1;
	}
	public String getGoods_sort_level2() {
		return goods_sort_level2;
	}
	public void setGoods_sort_level2(String goods_sort_level2) {
		this.goods_sort_level2 = goods_sort_level2;
	}
	public String getGoods_spec() {
		return goods_spec;
	}
	public void setGoods_spec(String goods_spec) {
		this.goods_spec = goods_spec;
	}
	public Long getGoods_stock() {
		return goods_stock;
	}
	public void setGoods_stock(Long goods_stock) {
		this.goods_stock = goods_stock;
	}
	public Float getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(Float goods_price) {
		this.goods_price = goods_price;
	}
	public String getGoods_desc() {
		return goods_desc;
	}
	public void setGoods_desc(String goods_desc) {
		this.goods_desc = goods_desc;
	}
	public String getGoods_pic_url() {
		return goods_pic_url;
	}
	public void setGoods_pic_url(String goods_pic_url) {
		this.goods_pic_url = goods_pic_url;
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
	public String getGoods_icon_url() {
		return goods_icon_url;
	}
	public void setGoods_icon_url(String goods_icon_url) {
		this.goods_icon_url = goods_icon_url;
	}
	
	
}
