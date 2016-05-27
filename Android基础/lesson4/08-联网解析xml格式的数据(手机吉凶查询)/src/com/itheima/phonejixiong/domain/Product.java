package com.itheima.phonejixiong.domain;
/*
 * 
<smartresult>
	<product type="mobile">
	<phonenum>13691689110</phonenum>
	<location>广东深圳移动神州行卡</location>
	<phoneJx>惨淡经营，难免贫困，此数不吉，最好改名 凶</phoneJx>
	</product>
</smartresult>
 * 
 * 
 */
public class Product {
	
	private String type ;
	private String phonenum ;
	private String location ;
	private String phoneJx ;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPhoneJx() {
		return phoneJx;
	}
	public void setPhoneJx(String phoneJx) {
		this.phoneJx = phoneJx;
	}
	@Override
	public String toString() {
		return "Product [type=" + type + ", phonenum=" + phonenum
				+ ", location=" + location + ", phoneJx=" + phoneJx + "]";
	}
	
	
}
