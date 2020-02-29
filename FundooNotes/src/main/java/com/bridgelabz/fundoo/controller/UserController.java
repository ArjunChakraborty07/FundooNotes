package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.UserDetails;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.service.IUserService;

@RestController
public class UserController {
	@Autowired
	private IUserService userService;
	

	@PostMapping("/users/register")
	public ResponseEntity<Response> register(@RequestBody UserDetails userDetails) {
		String result = userService.addUser(userDetails);
			return  ResponseEntity.status(HttpStatus.OK).body(new Response(200, result));		
	}
	
	@GetMapping("/users/login")
	public ResponseEntity<Response> login (@RequestBody UserDetails userDetails)
	{
		if (userService.userLogin(userDetails))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Login Successfull"));
		}
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(200, "Login Denied"));			
	}
	@PutMapping("/users/forgotPassword")
	public ResponseEntity<Response> forgotPassword(@RequestBody UserDetails userDetails)
	{
		if(userService.forgotpwd(userDetails))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Verification Mail send"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(200, "Invalid Email Id"));
	}
	
	@PutMapping("/users/resetPassword/{token}")
	public ResponseEntity<Response> resetPassword(@PathVariable("token") String token, @RequestBody UserDetails userDetails)
	{
		
		 if(userService.resetPassword(userDetails))
		 { 
			return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "Password Reset Successful")); 
		 }
		 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(200, "Update Denied"));
	}
		
	@GetMapping("/users/userVerification/{token}")
	public ResponseEntity<Response> userVerification(@PathVariable("token") String token)
	{
		if(userService.getVerify(token))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Response(200, "OK"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(200, "OK"));
	}
	
	
	
}
