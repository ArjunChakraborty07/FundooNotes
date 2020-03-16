package com.bridgelabz.fundoo.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

	private String name;	
	private String email;	
	private String phone;	
	private String password;
	
}
