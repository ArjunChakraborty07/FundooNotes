package com.bridgelabz.fundoo.response;

import com.bridgelabz.fundoo.exception.CollabCustomException.TypeOfException;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CollabResponse {
	private String token;	
	private String message;
	private TypeOfException type;
}
