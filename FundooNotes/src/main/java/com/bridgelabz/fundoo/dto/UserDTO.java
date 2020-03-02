package com.bridgelabz.fundoo.dto;

import org.springframework.stereotype.Component;

@Component
public class UserDTO {

	private String name;
	public UserDTO() {
	}
	public UserDTO(String name, String email, String password, String phone) {
		super();
		this.name = name;
		this.email = email;
		
		this.phone = phone;
		this.password = password;
	}
	private String email;
	
	private String phone;
	
	private String password;
	
	
	
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}