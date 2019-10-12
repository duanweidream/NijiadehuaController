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
@Table(name = "goods_sales_info")
public class GoodsSalesInfo implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long sales_id;
	private String sales_name;
	private Long product_id;
	private String subtitle;
	private String size;
	private Double sales_price;
	private Double mkt_price;
	private Long artist_id;
	private Long sales_status;
	private String sales_img;
	private Long creator;
	private Date create_time;
	private Long modifyor;
	private Date modify_time;

	// Constructors

	/** default constructor */
	public GoodsSalesInfo() {
	}

	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sales_id", unique = true, nullable = false)
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

	@Column(name = "product_id")
	public Long getProduct_id() {
		return product_id;
	}


	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	@Column(name = "subtitle")
	public String getSubtitle() {
		return subtitle;
	}


	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	@Column(name = "size")
	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
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

	@Column(name = "artist_id")
	public Long getArtist_id() {
		return artist_id;
	}


	public void setArtist_id(Long artist_id) {
		this.artist_id = artist_id;
	}

	@Column(name = "sales_status")
	public Long getSales_status() {
		return sales_status;
	}


	public void setSales_status(Long sales_status) {
		this.sales_status = sales_status;
	}

	@Column(name = "sales_img")
	public String getSales_img() {
		return sales_img;
	}


	public void setSales_img(String sales_img) {
		this.sales_img = sales_img;
	}


	
	@Column(name = "creator")
	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}

	@Column(name = "create_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Column(name = "modifyor")
	public Long getModifyor() {
		return modifyor;
	}

	public void setModifyor(Long modifyor) {
		this.modifyor = modifyor;
	}

	@Column(name = "modify_time")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModify_time() {
		return modify_time;
	}

	public void setModify_time(Date modify_time) {
		this.modify_time = modify_time;
	}
	
}