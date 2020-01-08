package com.nijiadehua.api.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * MenuInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_stock_outbound")
public class ArtStockOutbound implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long product_id;
	private Long out_stock;
	private Long out_type;
	private Long total_stock;
	private Long pre_stock;
	private Long rel_stock;
	private Long ava_stock;
	private String remark;
	private Long valid;
	private Long creator;
	private Date create_time;
	private Long modifyor;
	private Date modify_time;

	// Constructors
	
	/** default constructor */
	public ArtStockOutbound() {
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "product_id")
	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	
	
	@Column(name = "out_stock")
	public Long getOut_stock() {
		return out_stock;
	}

	public void setOut_stock(Long out_stock) {
		this.out_stock = out_stock;
	}
	
	@Column(name = "out_type")
	public Long getOut_type() {
		return out_type;
	}

	public void setOut_type(Long out_type) {
		this.out_type = out_type;
	}

	@Column(name = "total_stock")
	public Long getTotal_stock() {
		return total_stock;
	}

	public void setTotal_stock(Long total_stock) {
		this.total_stock = total_stock;
	}
	
	@Column(name = "pre_stock")
	public Long getPre_stock() {
		return pre_stock;
	}

	public void setPre_stock(Long pre_stock) {
		this.pre_stock = pre_stock;
	}
	
	@Column(name = "rel_stock")
	public Long getRel_stock() {
		return rel_stock;
	}

	public void setRel_stock(Long rel_stock) {
		this.rel_stock = rel_stock;
	}
	
	@Column(name = "ava_stock")
	public Long getAva_stock() {
		return ava_stock;
	}

	public void setAva_stock(Long ava_stock) {
		this.ava_stock = ava_stock;
	}
	
	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	@Column(name = "valid")
	public Long getValid() {
		return valid;
	}

	public void setValid(Long valid) {
		this.valid = valid;
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