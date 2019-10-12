package com.wooboo.dsp.model;

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

import org.springframework.format.annotation.DateTimeFormat;

/**
 * MenuInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_stock_inbound")
public class GoodsStockInbound implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private GoodsProductInfo productInfo;
	private Long in_stock;
	private Long in_type;
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
	public GoodsStockInbound() {
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	public GoodsProductInfo getProductInfo() {
		return productInfo;
	}

	public void setProductInfo(GoodsProductInfo productInfo) {
		this.productInfo = productInfo;
	}

	@Column(name = "in_stock")
	public Long getIn_stock() {
		return in_stock;
	}

	public void setIn_stock(Long in_stock) {
		this.in_stock = in_stock;
	}
	
	@Column(name = "in_type")
	public Long getIn_type() {
		return in_type;
	}

	public void setIn_type(Long in_type) {
		this.in_type = in_type;
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