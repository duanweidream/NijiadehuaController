package com.nijiadehua.api.controller.v1.pay.response;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

public class ArtPayInfo implements Serializable{

	private Long pay_id;
	private String order_id;
	private String prepay_id;
	private String transaction_id;
	private Double order_amount;
	private Integer state;
	private String create_time;
	private String update_time;
	public Long getPay_id() {
		return pay_id;
	}
	public void setPay_id(Long pay_id) {
		this.pay_id = pay_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public Double getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(Double order_amount) {
		this.order_amount = order_amount;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	
	
	
}
