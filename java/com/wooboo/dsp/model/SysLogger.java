package com.wooboo.dsp.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "sys_logger")
public class SysLogger {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long id;
	
	@Column(name = "business_id")
	public Long businessId;
	
	@Column(name = "business_code")
	public String businessCode;
	
	@Column(name = "operate_name")
	public String operateName;
	
	@Column(name = "create_user")
	public Long createUser;
	
	@Column(name = "operate_desc")
	public String operateDesc;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "create_time")
	public Date createTime;
	@Column(name = "user_name")
	public String userName;
	@Column(name = "ip_str")
	public String ipStr;
	
	
	
	
	public SysLogger(){}

	public SysLogger(Long id, Long businessId, String businessCode, String operateName, Long createUser,
			String operateDesc, Date createTime) {
		super();
		this.id = id;
		this.businessId = businessId;
		this.businessCode = businessCode;
		this.operateName = operateName;
		this.createUser = createUser;
		this.operateDesc = operateDesc;
		this.createTime = createTime;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getBusinessId() {
		return businessId;
	}


	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}


	public String getBusinessCode() {
		return businessCode;
	}


	public void setBusinessCode(String businessCode) {
		this.businessCode = businessCode;
	}


	public String getOperateName() {
		return operateName;
	}


	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}


	public Long getCreateUser() {
		return createUser;
	}


	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}


	public String getOperateDesc() {
		return operateDesc;
	}


	public void setOperateDesc(String operateDesc) {
		this.operateDesc = operateDesc;
	}


	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIpStr() {
		return ipStr;
	}

	public void setIpStr(String ipStr) {
		this.ipStr = ipStr;
	}
	
	
}
