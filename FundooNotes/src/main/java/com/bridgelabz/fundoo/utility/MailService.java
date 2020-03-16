package com.bridgelabz.fundoo.utility;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MailService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private String message;
	private String collaboratorMail;
}
