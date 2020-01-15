package com.nijiadehua.api.controller.v1.pay.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

public class ArtOrderInfo implements Serializable{

	private Double pay_amount;
	private String sales_name;
	private String sku_name;
	public Double getPay_amount() {
		return pay_amount;
	}
	public void setPay_amount(Double pay_amount) {
		this.pay_amount = pay_amount;
	}
	public String getSales_name() {
		return sales_name;
	}
	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}
	public String getSku_name() {
		return sku_name;
	}
	public void setSku_name(String sku_name) {
		this.sku_name = sku_name;
	}
	
	
	
}
