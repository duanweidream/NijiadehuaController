package com.wooboo.dsp.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "sys_department")
public class SysDepartMent implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer id;
	@Column(name = "parent_id")
	public Integer parentId;
	@Column(name = "part_name")
	public String partName;
	@Column(name = "py")
	public String py;
	@Column(name = "pinyin")
	public String pinyin;
	
	
    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="parent_id")    
    @OrderBy(" py asc")
	public List<SysDepartMent> list = new ArrayList<>();
	
	
	
	public SysDepartMent(){}
	public SysDepartMent(Integer id, String partName) {
		super();
		this.id = id;
		this.partName = partName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public List<SysDepartMent> getList() {
		return list;
	}
	public void setList(List<SysDepartMent> list) {
		this.list = list;
	}
	public String getPy() {
		return py;
	}
	public void setPy(String py) {
		this.py = py;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	} 
	
}
