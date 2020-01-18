package com.nijiadehua.api.controller.v1.sales.response;

import java.util.ArrayList;
import java.util.List;

public class DetailResponse {

	private Long sales_id;
	private String sort_code;
	private String sort_short_name;
	private String sort_long_name;
	private String sales_name;
	private Long art_id;
	private String art_name;
	private String sales_title;
	private Double sales_price;
	private Double mkt_price;
	private List<String> sales_img = new ArrayList<String>();
	public Long getSales_id() {
		return sales_id;
	}
	public void setSales_id(Long sales_id) {
		this.sales_id = sales_id;
	}
	public String getSort_code() {
		return sort_code;
	}
	public void setSort_code(String sort_code) {
		this.sort_code = sort_code;
	}
	public String getSort_short_name() {
		return sort_short_name;
	}
	public void setSort_short_name(String sort_short_name) {
		this.sort_short_name = sort_short_name;
	}
	public String getSort_long_name() {
		return sort_long_name;
	}
	public void setSort_long_name(String sort_long_name) {
		this.sort_long_name = sort_long_name;
	}
	public String getSales_name() {
		return sales_name;
	}
	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}
	
	
	public Long getArt_id() {
		return art_id;
	}
	public void setArt_id(Long art_id) {
		this.art_id = art_id;
	}
	public String getArt_name() {
		return art_name;
	}
	public void setArt_name(String art_name) {
		this.art_name = art_name;
	}
	public String getSales_title() {
		return sales_title;
	}
	public void setSales_title(String sales_title) {
		this.sales_title = sales_title;
	}
	public Double getSales_price() {
		return sales_price;
	}
	public void setSales_price(Double sales_price) {
		this.sales_price = sales_price;
	}
	public Double getMkt_price() {
		return mkt_price;
	}
	public void setMkt_price(Double mkt_price) {
		this.mkt_price = mkt_price;
	}
	public List<String> getSales_img() {
		return sales_img;
	}
	public void setSales_img(List<String> sales_img) {
		this.sales_img = sales_img;
	}
	
	public static class Img{
		private String img_url;

		public String getImg_url() {
			return img_url;
		}

		public void setImg_url(String img_url) {
			this.img_url = img_url;
		}
		
		
	}
	
	
}
