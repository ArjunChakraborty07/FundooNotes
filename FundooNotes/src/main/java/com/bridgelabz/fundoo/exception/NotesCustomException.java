package com.bridgelabz.fundoo.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotesCustomException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private String token;
	public enum TypeOfException
    {      
        FORBIDDEN,  
        BAD_REQUEST,
        NOTE_NOT_FOUND,
        USER_NOT_FOUND
    }     
	private TypeOfException type;
}
