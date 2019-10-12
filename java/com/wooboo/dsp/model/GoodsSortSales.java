package com.wooboo.dsp.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.wooboo.dsp.util.NumberUtil;


/**
 * MenuInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "goods_sort_sales")
public class GoodsSortSales   implements Comparable, java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long pId;
	private String sortName;
	private Long sortLev;
	private Long sortSeq;
	private String visitility;
	private String sortDesc;
	private Long creator;
	private Date create_time;
	private Long modifyor;
	private Date modify_time;
	
    private List<GoodsSortSales> list=new ArrayList<GoodsSortSales>();
    //是否是当前菜单
    private String current;
	// Constructors

    
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="P_ID")    
    @OrderBy(" sortSeq asc")
	public List<GoodsSortSales> getList() {
		return list;
	}

	public void setList(List<GoodsSortSales> list) {
		this.list = list;
	}

    
	/** default constructor */
	public GoodsSortSales() {
	}

	
	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "P_ID")
	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}


	@Column(name = "SORT_NAME", length = 32)
	public String getSortName() {
		return this.sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}
	
	@Column(name = "SORT_LEV")
	public Long getSortLev() {
		return this.sortLev;
	}

	public void setSortLev(Long sortLev) {
		this.sortLev = sortLev;
	}
	
	@Column(name = "SORT_SEQ")
	public Long getSortSeq() {
		return this.sortSeq;
	}

	public void setSortSeq(Long sortSeq) {
		this.sortSeq = sortSeq;
	}

	@Column(name = "VISITILITY", length = 1)
	public String getVisitility() {
		return this.visitility;
	}

	public void setVisitility(String visitility) {
		this.visitility = visitility;
	}

	@Column(name = "SORT_DESC", length = 128)
	public String getSortDesc() {
		return this.sortDesc;
	}

	public void setSortDesc(String sortDesc) {
		this.sortDesc = sortDesc;
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

	@Transient
	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	@Override
	public int compareTo(Object o) {
		GoodsSortSales m1 = (GoodsSortSales)o;
		if(NumberUtil.getInt(this.getSortSeq(), 0)<NumberUtil.getInt(m1.getSortSeq(), 0)){
			return 1;
		}else{
			return 0;
		}
	}

}