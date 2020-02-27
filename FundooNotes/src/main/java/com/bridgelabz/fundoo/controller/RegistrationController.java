package com.bridgelabz.fundoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.UserDetails;
import com.bridgelabz.fundoo.response.Responce;
import com.bridgelabz.fundoo.serviceimpl.RegistrationServiceImpl;

@RestController
public class RegistrationController {
	@Autowired
	private RegistrationServiceImpl obj;

	@GetMapping("/Registration")
	public List<UserDetails> display() {
		return obj.getDetails();
	}

	@PostMapping("/Registration")
	public ResponseEntity<Responce> register(@RequestBody UserDetails object) {
		String result = obj.addUser(object);
		if (result != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new Responce(200, "Sucessfull!!!"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce(400, "Not Sucessfull!!!"));
	}
	@PostMapping("/Registration/login")
	public ResponseEntity<Responce> login (@RequestBody UserDetails object)
	{
		if (obj.userLogin(object))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Responce(200, "Sucessfull!!!"));
		}
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce(400, "Not Sucessfull!!!"));			
	}
	@PostMapping("/Registration/login/forgotpassword")
	public ResponseEntity<Responce> forgotPassword(@RequestBody UserDetails object)
	{
		if(obj.forgotpwd(object))
		{
			return ResponseEntity.status(HttpStatus.OK).body(new Responce(200, "Sucessfull!!!"));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Responce(400, "Not Sucessfull!!!"));
	}
		
	@RequestMapping("/Registration/{name}")
	public UserDetails showDelails(@PathVariable String name) {
		return obj.getData(name);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/Registration/{name}")
	public void updateDetails(@RequestBody UserDetails object, @PathVariable String name) {
		obj.updateUser(object, name);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/Registration/{name}")
	public void deletaDetails(@PathVariable String name) {
		obj.deleteUser(name);
	}
	

	@GetMapping(value = "/test")
	public String name() {
		return "hi";
	}
}
