package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExceptionMessage extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus status;
	
	
}