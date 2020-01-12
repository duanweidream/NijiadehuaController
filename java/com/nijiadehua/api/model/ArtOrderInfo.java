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
@Table(name = "art_order_info")
public class ArtOrderInfo implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String order_id;
	private Long user_id;
	private String order_sort;
	private Long order_status;
	private Double order_amount;
	private Integer pay_type;
	private Double pay_amount;
	private Date pay_time;
	private Integer delivery_mode;
	private Integer delivery_send;
	private String delivery_name;
	private String delivery_phone;
	private String delivery_country;
	private String delivery_province;
	private String delivery_city;
	private String delivery_district;
	private String delivery_address;
	private String delivery_postal_code;
	private String express_company;
	private String express_number;
	private Double express_freight;
	private String order_remark;
	private Date create_time;


	// Constructors

	/** default constructor */
	public ArtOrderInfo() {
	}

	// Property accessors
	@Id
	@Column(name = "order_id", unique = true, nullable = false)
	public String getOrder_id() {
		return order_id;
	}


	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	
	
	@Column(name = "user_id")
	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
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
	
	@Column(name = "delivery_send")
	public Integer getDelivery_send() {
		return delivery_send;
	}

	public void setDelivery_send(Integer delivery_send) {
		this.delivery_send = delivery_send;
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
	
	
	@Column(name = "delivery_country")
	public String getDelivery_country() {
		return delivery_country;
	}

	public void setDelivery_country(String delivery_country) {
		this.delivery_country = delivery_country;
	}
	
	@Column(name = "delivery_province")
	public String getDelivery_province() {
		return delivery_province;
	}

	public void setDelivery_province(String delivery_province) {
		this.delivery_province = delivery_province;
	}
	
	@Column(name = "delivery_city")
	public String getDelivery_city() {
		return delivery_city;
	}

	public void setDelivery_city(String delivery_city) {
		this.delivery_city = delivery_city;
	}
	
	@Column(name = "delivery_district")
	public String getDelivery_district() {
		return delivery_district;
	}

	public void setDelivery_district(String delivery_district) {
		this.delivery_district = delivery_district;
	}
	
	@Column(name = "delivery_postal_code")
	public String getDelivery_postal_code() {
		return delivery_postal_code;
	}

	public void setDelivery_postal_code(String delivery_postal_code) {
		this.delivery_postal_code = delivery_postal_code;
	}

	@Column(name = "delivery_address")
	public String getDelivery_address() {
		return delivery_address;
	}

	public void setDelivery_address(String delivery_address) {
		this.delivery_address = delivery_address;
	}
	
	
	

	@Column(name = "express_company")
	public String getExpress_company() {
		return express_company;
	}
	
	public void setExpress_company(String express_company) {
		this.express_company = express_company;
	}

	@Column(name = "express_number")
	public String getExpress_number() {
		return express_number;
	}

	public void setExpress_number(String express_number) {
		this.express_number = express_number;
	}

	@Column(name = "express_freight")
	public Double getExpress_freight() {
		return express_freight;
	}
	
	public void setExpress_freight(Double express_freight) {
		this.express_freight = express_freight;
	}
	
	@Column(name = "order_remark")
	public String getOrder_remark() {
		return order_remark;
	}


	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}

	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	
}