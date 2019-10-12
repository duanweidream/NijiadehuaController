package com.wooboo.dsp.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;



/**
 * SysUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "sys_user")
public class SysUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private RoleInfo roleInfo;
	private String userName;
	private String userPhone;
	private String email;
	private String password;
	private String nickName;
	private Integer loginTime;
	private Date createDate;
	private Date lastDate;
	private String state;
	private Integer level;
	
	private String userTheme;
	private Date lockTime;
	private Integer loginFailTime;
	
	// Constructors

	/** default constructor */
	public SysUser() {
	}

	/** minimal constructor */
	public SysUser(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	/** full constructor */
	public SysUser(RoleInfo roleInfo, String userName, String password,
			String nickName, Integer loginTime, Date createDate, Date lastDate,String state) {
		this.roleInfo = roleInfo;
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.loginTime = loginTime;
		this.createDate = createDate;
		this.lastDate = lastDate;
		this.state=state;
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

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ROLE_ID")
	public RoleInfo getRoleInfo() {
		return this.roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	@Column(name = "USER_NAME", nullable = false, length = 32)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD", nullable = false, length = 40)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "NICK_NAME", length = 32)
	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Column(name = "LOGIN_TIME")
	public Integer getLoginTime() {
		return this.loginTime;
	}

	public void setLoginTime(Integer loginTime) {
		this.loginTime = loginTime;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "CREATE_DATE", length = 0)
	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "LAST_DATE", length = 0)
	public Date getLastDate() {
		return this.lastDate;
	}

	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	@Column(name = "state", length = 1)
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	@Column(name = "level")
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Column(name = "user_phone")
	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	@Column(name = "email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Transient
	public String getUserTheme() {
		return userTheme;
	}

	public void setUserTheme(String userTheme) {
		this.userTheme = userTheme;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lock_time")
	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}
	
	
	@Column(name = "login_fail_time")
	public Integer getLoginFailTime() {
		return loginFailTime;
	}
	
	public void setLoginFailTime(Integer loginFailTime) {
		this.loginFailTime = loginFailTime;
	}

	

}