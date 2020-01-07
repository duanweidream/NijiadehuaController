package com.nijiadehua.api.controller.v1.order.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nijiadehua.api.model.OrderGoods;

public class OrderSearchResponse {
	
	private String user_id;
	private Long order_id;
	private String order_no;
	private Long order_status;
	private double order_amount;
	private double pay_amount;
	private String create_time;
	private List<Goods> goods = new ArrayList<Goods>();
	
	
	
	public String getUser_id() {
		return user_id;
	}



	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}



	public Long getOrder_id() {
		return order_id;
	}



	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}



	public String getOrder_no() {
		return order_no;
	}



	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	


	public Long getOrder_status() {
		return order_status;
	}



	public void setOrder_status(Long order_status) {
		this.order_status = order_status;
	}



	public double getOrder_amount() {
		return order_amount;
	}



	public void setOrder_amount(double order_amount) {
		this.order_amount = order_amount;
	}



	public double getPay_amount() {
		return pay_amount;
	}



	public void setPay_amount(double pay_amount) {
		this.pay_amount = pay_amount;
	}



	public List<Goods> getGoods() {
		return goods;
	}



	public void setGoods(List<Goods> goods) {
		this.goods = goods;
	}



	public String getCreate_time() {
		return create_time;
	}



	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}



	public static class Goods{
		private Long sales_id;
		private String sales_name;
		private String title;
		private Long value_id;
		private String value_name;
		private Double sales_price;
		private Double mkt_price;
		private int qty;
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
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public Long getValue_id() {
			return value_id;
		}
		public void setValue_id(Long value_id) {
			this.value_id = value_id;
		}
		public String getValue_name() {
			return value_name;
		}
		public void setValue_name(String value_name) {
			this.value_name = value_name;
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
		public int getQty() {
			return qty;
		}
		public void setQty(int qty) {
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
