package com.bridgelabz.fundoo.response;

import com.bridgelabz.fundoo.exception.LabelCustomException.TypeOfException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabelResponse {
	private String token;	
	private String message;
	private TypeOfException type;
}
