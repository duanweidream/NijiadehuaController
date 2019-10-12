package com.wooboo.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RoleAuth entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role_auth")
public class RoleAuth implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roleId;
	private Integer authId;

	// Constructors

	/** default constructor */
	public RoleAuth() {
	}

	/** full constructor */
	public RoleAuth(Integer roleId, Integer authId) {
		this.roleId = roleId;
		this.authId = authId;
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

	@Column(name = "ROLE_ID")
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "AUTH_ID")
	public Integer getAuthId() {
		return this.authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

}