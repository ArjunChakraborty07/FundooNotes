package com.bridgelabz.fundoo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserEntity {		
	public String email;
	public String password;
	public int phone;
	public String name;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	
	@Column
	public String getName() {
		return name;
	}
	@Column
	public void setName(String name) {
		this.name = name;
	}
	@Column
	public String getEmail() {
		return email;
	}
	@Column
	public void setEmail(String email) {
		this.email = email;
	}
	@Column
	public String getPassword() {
		return password;
	}
	@Column
	public void setPassword(String password) {
		this.password = password;
	}
	@Column
	public int getPhone() {
		return phone;
	}
	@Column
	public void setPhone(int phone) {
		this.phone = phone;
	}
}
