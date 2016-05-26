package com.itheima.studentsystem.domain;
/*
 *   实体bean  
 *   
 *   保存数据
 *   
 *   有什么样的 表就有 什么样的 bean
 *  
 */
public class Student {
	private String id;
	private String name;
	private String sex;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Student(String id, String name, String sex) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
	}
	public Student() {
		super();
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", sex=" + sex + "]";
	}
	
}
