package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	private HttpStatus status;

	
}