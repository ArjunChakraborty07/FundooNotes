package com.bridgelabz.fundoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bridgelabz.fundoo.response.Response;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	
	@ExceptionHandler(UserServiceExceptionHandler.class)
	public ResponseEntity<Response> handleAllUserException(UserServiceExceptionHandler exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new Response(exception.getStatus(), exception.getMessage()));
	}

}
