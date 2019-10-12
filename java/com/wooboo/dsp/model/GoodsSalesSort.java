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
@Table(name = "goods_sales_sort")
public class GoodsSalesSort implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long sales_id;
	private Long sort_id;


	// Constructors

	/** default constructor */
	public GoodsSalesSort() {
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
	
	
	@Column(name = "sales_id")
	public Long getSales_id() {
		return sales_id;
	}
	
	public void setSales_id(Long sales_id) {
		this.sales_id = sales_id;
	}
	
	@Column(name = "sort_id")
	public Long getSort_id() {
		return sort_id;
	}


	public void setSort_id(Long sort_id) {
		this.sort_id = sort_id;
	}

}