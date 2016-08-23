package com.itheima.displaycontact.domain;

public class ContactInfo {
	
	private String name;
	private String email;
	private String phone;
	private String qq;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	@Override
	public String toString() {
		return "ContactInfo [name=" + name + ", email=" + email + ", phone="
				+ phone + ", qq=" + qq + "]";
	}
}
