package com.wooboo.dsp.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * MenuInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "order_info")
public class OrderInfo implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long order_id;
	private String order_no;
	private Long order_status;
	private Double order_amount;
	private Double pay_amount;
	private Date pay_time;
	private Date order_create_time;


	// Constructors

	/** default constructor */
	public OrderInfo() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "order_id", unique = true, nullable = false)
	public Long getOrder_id() {
		return order_id;
	}


	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	@Column(name = "order_no")
	public String getOrder_no() {
		return order_no;
	}


	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	@Column(name = "order_status")
	public Long getOrder_status() {
		return order_status;
	}


	public void setOrder_status(Long order_status) {
		this.order_status = order_status;
	}

	@Column(name = "order_amount")
	public Double getOrder_amount() {
		return order_amount;
	}


	public void setOrder_amount(Double order_amount) {
		this.order_amount = order_amount;
	}

	@Column(name = "pay_amount")
	public Double getPay_amount() {
		return pay_amount;
	}


	public void setPay_amount(Double pay_amount) {
		this.pay_amount = pay_amount;
	}

	@Column(name = "pay_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getPay_time() {
		return pay_time;
	}


	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	@Column(name = "order_create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOrder_create_time() {
		return order_create_time;
	}


	public void setOrder_create_time(Date order_create_time) {
		this.order_create_time = order_create_time;
	}

	
}