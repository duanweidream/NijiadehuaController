package com.wooboo.dsp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * RoleMenu entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_role_menu")
public class RoleMenu implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer roleId;
	private Integer menuId;

	// Constructors

	/** default constructor */
	public RoleMenu() {
	}

	/** full constructor */
	public RoleMenu(Integer roleId, Integer menuId) {
		this.roleId = roleId;
		this.menuId = menuId;
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

	@Column(name = "MENU_ID")
	public Integer getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

}