package com.bridgelabz.fundoo.response;

import com.bridgelabz.fundoo.exception.NotesCustomException.TypeOfException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteResponse {
	
		private String token;	
		private String message;
		private TypeOfException type;												
	
}
