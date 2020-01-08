package com.nijiadehua.api.controller.v1.order.request;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;

public class OrderCreateRequest implements Serializable{

	public Long user_id;
	public List<Sales> sales = new ArrayList<Sales>();
	

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public List<Sales> getSales() {
		return sales;
	}


	public void setSales(List<Sales> sales) {
		this.sales = sales;
	}


	public static class Sales{
		private Long sales_id;
		private List<Long> spec = new ArrayList<Long>(); 
		private int qty;
		public Long getSales_id() {
			return sales_id;
		}
		public void setSales_id(Long sales_id) {
			this.sales_id = sales_id;
		}
		public List<Long> getSpec() {
			return spec;
		}
		public void setSpec(List<Long> spec) {
			this.spec = spec;
		}
		public int getQty() {
			return qty;
		}
		public void setQty(int qty) {
			this.qty = qty;
		}
		
		
	} 

	
	
}
