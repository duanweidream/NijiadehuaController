package com.wooboo.dsp.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sys_city_info")
public class SysCityInfo implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	public Integer id;
	
	@Column(name = "parent_code")
	public String parentCode;
	
	@Column(name = "city_name")
	public String cityName;
	
	@Column(name = "city_code")
	public String cityCode;
	
	@Column(name = "city_type")
	public String cityType;
	
	@Transient
	public String checked;
	
	public SysCityInfo(){}

	public SysCityInfo(Integer id, String parentCode, String cityName, String cityCode, String cityType) {
		super();
		this.id = id;
		this.parentCode = parentCode;
		this.cityName = cityName;
		this.cityCode = cityCode;
		this.cityType = cityType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityType() {
		return cityType;
	}

	public void setCityType(String cityType) {
		this.cityType = cityType;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
	
	
	
}
