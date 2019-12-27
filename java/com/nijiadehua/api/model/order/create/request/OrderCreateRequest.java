package com.nijiadehua.api.model.order.create.request;

import java.io.Serializable;

public class OrderCreateRequest implements Serializable{

	public String user_id;
	public Sales[] sales;
	public Integer pay_type;
	public Delivery delivery;
	public String order_remark;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public Sales[] getSales() {
		return sales;
	}
	public void setSales(Sales[] sales) {
		this.sales = sales;
	}
	public Integer getPay_type() {
		return pay_type;
	}
	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}
	public Delivery getDelivery() {
		return delivery;
	}
	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}
	
	public String getOrder_remark() {
		return order_remark;
	}
	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}


	public static class Sales{
		private Long sales_id;
		private int sales_number;
		public Long getSales_id() {
			return sales_id;
		}
		public void setSales_id(Long sales_id) {
			this.sales_id = sales_id;
		}
		public int getSales_number() {
			return sales_number;
		}
		public void setSales_number(int sales_number) {
			this.sales_number = sales_number;
		}
		
		
	} 
	
	public static class Delivery{
		private int delivery_mode;
		private String delivery_name;
		private String delivery_phone;
		private String delivery_address;
		private int delivery_send;
		private int delivery_time;
		public int getDelivery_mode() {
			return delivery_mode;
		}
		public void setDelivery_mode(int delivery_mode) {
			this.delivery_mode = delivery_mode;
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
		public String getDelivery_address() {
			return delivery_address;
		}
		public void setDelivery_address(String delivery_address) {
			this.delivery_address = delivery_address;
		}
		public int getDelivery_send() {
			return delivery_send;
		}
		public void setDelivery_send(int delivery_send) {
			this.delivery_send = delivery_send;
		}
		public int getDelivery_time() {
			return delivery_time;
		}
		public void setDelivery_time(int delivery_time) {
			this.delivery_time = delivery_time;
		}
		
		
	}
	
	
	
}
