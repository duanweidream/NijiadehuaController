package com.hualouhui.weixin.model;

import java.util.Date;


public class VerificationCode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	public Integer id;
	public String phone_number,verification_code,is_verified;
	public Date send_time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getVerification_code() {
		return verification_code;
	}
	public void setVerification_code(String verification_code) {
		this.verification_code = verification_code;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	public String getIs_verified() {
		return is_verified;
	}
	public void setIs_verified(String is_verified) {
		this.is_verified = is_verified;
	}
}
