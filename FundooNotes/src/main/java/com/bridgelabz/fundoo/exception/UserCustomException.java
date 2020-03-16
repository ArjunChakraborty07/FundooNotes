package com.bridgelabz.fundoo.exception;

public class UserCustomException extends RuntimeException{

	
	private static final long serialVersionUID = 1L;
	private String message;
	private String token;
	
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

	public TypeOfException getType() {
		return type;
	}

	public void setType(TypeOfException type) {
		this.type = type;
	}

	
    private TypeOfException type;
    
    public UserCustomException()
    { 
    }
    
    public UserCustomException(String message, TypeOfException type, String token)
    {
        this.message = message;
        this.type = type;
        this.token=token;
    }
    
    public enum TypeOfException
    {      
        USER_NOT_FOUND,   
        FORBIDDEN            
    }     

}
