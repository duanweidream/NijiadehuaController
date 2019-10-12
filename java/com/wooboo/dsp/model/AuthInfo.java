package com.wooboo.dsp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * AuthInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_auth_info")
public class AuthInfo implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer pid;
	private String authName;
	private String model;
	private String authRegexp;
	private String authCode;
	private Integer sort;
	private String authDesc;
    private List<AuthInfo> list = new ArrayList<AuthInfo>();
	// Constructors

	/** default constructor */
	public AuthInfo() {
	}
	
	@OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="PID")    
	public List<AuthInfo> getList() {
		return list;
	}

	public void setList(List<AuthInfo> list) {
		this.list = list;
	}

	/** full constructor */
	public AuthInfo(Integer pid, String authName, String model,
			String authRegexp, String authCode, Integer sort, String authDesc) {
		this.pid = pid;
		this.authName = authName;
		this.model = model;
		this.authRegexp = authRegexp;
		this.authCode = authCode;
		this.sort = sort;
		this.authDesc = authDesc;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "PID")
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	@Column(name = "AUTH_NAME", length = 64)
	public String getAuthName() {
		return this.authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	@Column(name = "MODEL", length = 1)
	public String getModel() {
		return this.model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(name = "AUTH_REGEXP", length = 64)
	public String getAuthRegexp() {
		return this.authRegexp;
	}

	public void setAuthRegexp(String authRegexp) {
		this.authRegexp = authRegexp;
	}

	@Column(name = "AUTH_CODE", length = 16)
	public String getAuthCode() {
		return this.authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	@Column(name = "SORT")
	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	@Column(name = "AUTH_DESC", length = 64)
	public String getAuthDesc() {
		return this.authDesc;
	}

	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}

}