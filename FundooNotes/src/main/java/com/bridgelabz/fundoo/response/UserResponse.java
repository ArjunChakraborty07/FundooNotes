package com.bridgelabz.fundoo.response;

import com.bridgelabz.fundoo.exception.UserCustomException.TypeOfException;

public class UserResponse {
	private String token;
	public UserResponse() {
		super();
		
	}
	private String message;
	private TypeOfException type;
	
	public TypeOfException getType() {
		return type;
	}

	public void setType(TypeOfException type) {
		this.type = type;
	}

	public UserResponse(String token, String message, TypeOfException type) {
		super();
		this.token = token;
		this.message = message;
		this.type=type;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	

	
}
