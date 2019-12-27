package com.nijiadehua.api.model;

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
	private String order_sort;
	private Long order_status;
	private Double order_amount;
	private Integer pay_type;
	private Double pay_amount;
	private Date pay_time;
	private Integer delivery_mode;
	private String delivery_name;
	private String delivery_phone;
	private String delivery_address;
	private String delivery_company;
	private String delivery_no;
	private Double delivery_fee;
	private Integer delivery_send;
	private String order_remark;
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
	
	
	@Column(name = "order_sort")
	public String getOrder_sort() {
		return order_sort;
	}

	public void setOrder_sort(String order_sort) {
		this.order_sort = order_sort;
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
	
	
	@Column(name = "pay_type")
	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
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
	
	@Column(name = "delivery_mode")
	public Integer getDelivery_mode() {
		return delivery_mode;
	}

	public void setDelivery_mode(Integer delivery_mode) {
		this.delivery_mode = delivery_mode;
	}

	@Column(name = "delivery_name")
	public String getDelivery_name() {
		return delivery_name;
	}

	public void setDelivery_name(String delivery_name) {
		this.delivery_name = delivery_name;
	}
	
	@Column(name = "delivery_phone")
	public String getDelivery_phone() {
		return delivery_phone;
	}

	public void setDelivery_phone(String delivery_phone) {
		this.delivery_phone = delivery_phone;
	}
	
	@Column(name = "delivery_address")
	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	
	@Column(name = "delivery_company")
	public String getDelivery_company() {
		return delivery_company;
	}

	public void setDelivery_company(String delivery_company) {
		this.delivery_company = delivery_company;
	}
	
	@Column(name = "delivery_no")
	public String getDelivery_no() {
		return delivery_no;
	}

	public void setDelivery_no(String delivery_no) {
		this.delivery_no = delivery_no;
	}

	@Column(name = "delivery_fee")
	public Double getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(Double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}
	
	@Column(name = "delivery_send")
	public Integer getDelivery_send() {
		return delivery_send;
	}

	public void setDelivery_send(Integer delivery_send) {
		this.delivery_send = delivery_send;
	}
	
	@Column(name = "order_remark")
	public String getOrder_remark() {
		return order_remark;
	}

	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
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