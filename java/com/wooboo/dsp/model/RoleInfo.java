package com.wooboo.dsp.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Formula;

/**
 * RoleInfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role_info")
public class RoleInfo implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private Integer rolePid;
	private String roleName;
	private Integer roleLevel;
	private String roleDesc;
	private String roleCode;
	private String isPlay;
	
	private Integer isSel;
	
	// Constructors

	/** default constructor */
	public RoleInfo() {
	}

	/** full constructor */
	public RoleInfo(Integer rolePid, String roleName, Integer roleLevel,
			String roleDesc, String roleCode, String isPlay) {
		this.rolePid = rolePid;
		this.roleName = roleName;
		this.roleLevel = roleLevel;
		this.roleDesc = roleDesc;
		this.roleCode = roleCode;
		this.isPlay = isPlay;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	@Column(name = "ROLE_PID")
	public Integer getRolePid() {
		return this.rolePid;
	}

	public void setRolePid(Integer rolePid) {
		this.rolePid = rolePid;
	}

	@Column(name = "ROLE_NAME", length = 16)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "ROLE_LEVEL")
	public Integer getRoleLevel() {
		return this.roleLevel;
	}

	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}

	@Column(name = "ROLE_DESC", length = 256)
	public String getRoleDesc() {
		return this.roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	@Column(name = "ROLE_CODE", length = 16)
	public String getRoleCode() {
		return this.roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	@Column(name = "IS_PLAY", length = 1)
	public String getIsPlay() {
		return this.isPlay;
	}

	public void setIsPlay(String isPlay) {
		this.isPlay = isPlay;
	}
	
	@Transient
	public Integer getIsSel() {
		return isSel;
	}

	public void setIsSel(Integer isSel) {
		this.isSel = isSel;
	}
	
	

}