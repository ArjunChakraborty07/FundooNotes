package com.bridgelabz.fundoo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "fundoo_table")
public class UserEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public UserEntity(int id, String name, String email, String phone, String password, boolean verify) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.verify= verify;
	}

	@Column(name = "user_name")
	public String name;
	@Column(name = "email_id")
	public String email;
	@Column(name = "phone_no")
	public String phone;
	public List<NotesEntity> getNotes() {
		return notes;
	}

	public void setNotes(List<NotesEntity> notes) {
		this.notes = notes;
	}

	@Column(name = "password")
	public String password;
	@Column(name = "verification")
	public boolean verify;	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<NotesEntity> notes;

	public UserEntity() {

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

	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
