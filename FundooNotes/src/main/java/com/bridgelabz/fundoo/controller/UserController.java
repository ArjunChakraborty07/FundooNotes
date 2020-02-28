package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.UserDetails;
import com.bridgelabz.fundoo.serviceimpl.RegistrationServiceImpl;

@RestController
public class UserController {
	@Autowired
	private RegistrationServiceImpl userService;
	

	@PostMapping("/users/register")
	public HeadersBuilder<BodyBuilder> register(@RequestBody UserDetails userDetails) {
		String result = userService.addUser(userDetails);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/users/login")
	public HeadersBuilder<BodyBuilder> login (@RequestBody UserDetails userDetails)
	{
		if (userService.userLogin(userDetails))
		{
			return ResponseEntity.status(HttpStatus.OK);
		}
	return ResponseEntity.status(HttpStatus.BAD_REQUEST);			
	}
	@PostMapping("/users/forgotPassword")
	public HeadersBuilder<BodyBuilder> forgotPassword(@RequestBody UserDetails userDetails)
	{
		if(userService.forgotpwd(userDetails))
		{
			return ResponseEntity.status(HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/users/resetPassword")
	public HeadersBuilder<BodyBuilder> resetPassword(@RequestBody UserDetails userDetails)
	{
		if(userService.resetPassword(userDetails))
		{
			return ResponseEntity.status(HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST);
	}
		
	@GetMapping("/users/userVerification/{token}")
	public HeadersBuilder<BodyBuilder> userVerification(@PathVariable("token") String token)
	{
		if(userService.getVerify(token))
		{
			return ResponseEntity.status(HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST);
	}
	
	
	
}
