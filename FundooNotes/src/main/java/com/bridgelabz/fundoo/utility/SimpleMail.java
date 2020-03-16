package com.bridgelabz.fundoo.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SimpleMail {

	@Autowired
	private JavaMailSender javaMailSender;
	
	public void simpleMail(String email, String subject, String message)
	{
		SimpleMailMessage msg= new SimpleMailMessage();
		msg.setTo(email);
		msg.setSubject(subject);
		msg.setText(message);
		javaMailSender.send(msg);
	}
}
