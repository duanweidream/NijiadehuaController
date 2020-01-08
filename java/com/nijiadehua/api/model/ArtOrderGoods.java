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
@Table(name = "art_order_goods")
public class ArtOrderGoods implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String order_id;
	private Long sales_id;
	private String sales_name;
	private String title;
	private String sku_id;
	private String sku_name;
	private Double sales_price;
	private Double mkt_price;
	private Integer qty;
	private String sales_icon;
	private Long status;
	private Date create_time;


	// Constructors

	/** default constructor */
	public ArtOrderGoods() {
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
	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
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
	
	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "sku_id")
	public String getSku_id() {
		return sku_id;
	}

	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}
	
	@Column(name = "sku_name")
	public String getSku_name() {
		return sku_name;
	}

	public void setSku_name(String sku_name) {
		this.sku_name = sku_name;
	}

	@Column(name = "sales_price")
	public Double getSales_price() {
		return sales_price;
	}


	public void setSales_price(Double sales_price) {
		this.sales_price = sales_price;
	}
	
	@Column(name = "mkt_price")
	public Double getMkt_price() {
		return mkt_price;
	}

	public void setMkt_price(Double mkt_price) {
		this.mkt_price = mkt_price;
	}

	
	@Column(name = "qty")
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
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