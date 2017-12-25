package com.book.beans;

public class User {
private String userid;//使用uuid来生成id
private String password;//这里使用name与password登录
private String name;
private String  email;
private String phone;
private String role;//角色：判断是否是管理员.1:管理员，0:普通用户
public String getUserid() {
	return userid;
}
public void setUserid(String userid) {
	this.userid = userid;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
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
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

}
