package com.bridgelabz.fundoo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.UserDetails;
import com.bridgelabz.fundoo.serviceimpl.RegistrationServiceImpl;

@RestController
public class RegistrationController {

	@Autowired
	private RegistrationServiceImpl obj;
	
	@GetMapping("/Registration")
	public List<UserDetails> display()
	{
		return obj.getDetails();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/Registration")
	public void register(@RequestBody UserDetails object)
	{
		obj.addUser(object);
	}
	
	@RequestMapping("/Registration/{name}")
	public UserDetails showDelails(@PathVariable String name)
	{
		return obj.getData(name);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/Registration/{name}")
	public void updateDetails(@RequestBody UserDetails object, @PathVariable String name)
	{
		obj.updateUser(object, name);
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/Registration/{name}")
	public void deletaDetails(@PathVariable String name)
	{
		obj.deleteUser(name);
	}
}
