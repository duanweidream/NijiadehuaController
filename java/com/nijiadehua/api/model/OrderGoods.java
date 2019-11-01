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
@Table(name = "order_goods")
public class OrderGoods implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long order_id;
	private Long sales_id;
	private String sales_name;
	private String sales_title;
	private Double sales_price;
	private Integer sales_number;
	private String sales_icon;
	private Long status;
	private Date create_time;


	// Constructors

	/** default constructor */
	public OrderGoods() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "order_id")
	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
	@Column(name = "sales_id")
	public Long getSales_id() {
		return sales_id;
	}

	public void setSales_id(Long sales_id) {
		this.sales_id = sales_id;
	}
	
	@Column(name = "sales_name")
	public String getSales_name() {
		return sales_name;
	}

	public void setSales_name(String sales_name) {
		this.sales_name = sales_name;
	}
	
	@Column(name = "sales_title")
	public String getSales_title() {
		return sales_title;
	}

	public void setSales_title(String sales_title) {
		this.sales_title = sales_title;
	}
	
	@Column(name = "sales_price")
	public Double getSales_price() {
		return sales_price;
	}

	public void setSales_price(Double sales_price) {
		this.sales_price = sales_price;
	}
	
	@Column(name = "sales_number")
	public Integer getSales_number() {
		return sales_number;
	}

	public void setSales_number(Integer sales_number) {
		this.sales_number = sales_number;
	}
	
	@Column(name = "sales_icon")
	public String getSales_icon() {
		return sales_icon;
	}

	public void setSales_icon(String sales_icon) {
		this.sales_icon = sales_icon;
	}
	
	@Column(name = "status")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
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