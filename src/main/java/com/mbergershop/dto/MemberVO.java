package com.mbergershop.dto;

import java.sql.Timestamp;

public class MemberVO {
	  private String name;
	  private String gender;
	  private String admin;
	  private String phone;
	  private String email;
	  private String password;	 
	  private String address;
	  
	  private String count;
	  
	  


	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", gender=" + gender + ", phone=" + phone + ", email=" + email + ", password="
				+ password + ", address=" + address + "]";
	}
	
	

	  

}
