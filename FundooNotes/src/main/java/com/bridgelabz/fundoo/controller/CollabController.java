package com.bridgelabz.fundoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.entity.NotesEntity;
import com.bridgelabz.fundoo.response.UserResponse;
import com.bridgelabz.fundoo.serviceimpl.CollaboratorServiceImpl;
import com.bridgelabz.fundoo.utility.MailService;
import com.bridgelabz.fundoo.utility.RabbitMQSender;


@RestController
public class CollabController {
	@Autowired
	private CollaboratorServiceImpl service;

	@Autowired
	private RabbitMQSender rabbit;
	
	@Autowired
	private MailService message;
	
	@PostMapping("/collabrator/add")
	public ResponseEntity<UserResponse> addCollab(@RequestParam("email") String email, @RequestParam("noteId") int noteId, @RequestHeader("token") String token) {
		service.addcolab(noteId, email, token);
		message.setCollaboratorMail(email);
		message.setMessage("Collaboration Successful");
		rabbit.send(message);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(new UserResponse(token, "Collaboration Successful...",null));

	}

	
	@DeleteMapping("/collabrator/remove")
	public ResponseEntity<UserResponse> removeCollab(@RequestParam("email") String email, @RequestParam("noteId") int noteId, @RequestHeader("token") String token) {
		service.removeCollab(noteId, token, email);
		return ResponseEntity.status(HttpStatus.OK).body(new UserResponse(token, "Collaboration Removed Successfully...",null));

	}

	
	@GetMapping("/getallcollab")
	public ResponseEntity<UserResponse> getAllCollab(@RequestHeader("token") String token) {
		List<NotesEntity> notes = service.getAllCollabs(token);
		return ResponseEntity.status(HttpStatus.FOUND).body(new UserResponse(token, "Operation Successful...",null));

	}

}