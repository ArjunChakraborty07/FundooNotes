package com.bridgelabz.fundoo.exception;

import java.io.IOException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.fundoo.response.Response;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(value = { IOException.class })
	public Response badRequest(Exception exception) {
		return new Response(400, "Bad Request");
	}

	@ExceptionHandler(value = { Exception.class })
	public Response unknowException(Exception exception) {
		return new Response(400, exception.getMessage());
	}
}
