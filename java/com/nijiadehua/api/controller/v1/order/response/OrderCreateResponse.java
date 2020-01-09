package com.nijiadehua.api.controller.v1.order.response;

import java.util.ArrayList;
import java.util.List;

public class OrderCreateResponse {

	private String order_id;
	private Long user_id;
	private List<OrderGoods> order_goods = new ArrayList<OrderGoods>();
	
	
	
	public String getOrder_id() {
		return order_id;
	}



	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}



	public Long getUser_id() {
		return user_id;
	}



	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}



	public List<OrderGoods> getOrder_goods() {
		return order_goods;
	}



	public void setOrder_goods(List<OrderGoods> order_goods) {
		this.order_goods = order_goods;
	}



	public static class OrderGoods{
		
		private Long sales_id;
		private String sales_name;
		private String sales_title;
		private String sku_id;
		private String sku_name;
		private Double sales_price;
		private Double mkt_price;
		private Integer qty;
		private String sales_icon;
		public Long getSales_id() {
			return sales_id;
		}
		public void setSales_id(Long sales_id) {
			this.sales_id = sales_id;
		}
		public String getSales_name() {
			return sales_name;
		}
		public void setSales_name(String sales_name) {
			this.sales_name = sales_name;
		}
		
		public String getSales_title() {
			return sales_title;
		}
		public void setSales_title(String sales_title) {
			this.sales_title = sales_title;
		}
		public String getSku_id() {
			return sku_id;
		}
		public void setSku_id(String sku_id) {
			this.sku_id = sku_id;
		}
		public String getSku_name() {
			return sku_name;
		}
		public void setSku_name(String sku_name) {
			this.sku_name = sku_name;
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
		public Integer getQty() {
			return qty;
		}
		public void setQty(Integer qty) {
			this.qty = qty;
		}
		public String getSales_icon() {
			return sales_icon;
		}
		public void setSales_icon(String sales_icon) {
			this.sales_icon = sales_icon;
		}
		
		
		
	}
	
	
}
