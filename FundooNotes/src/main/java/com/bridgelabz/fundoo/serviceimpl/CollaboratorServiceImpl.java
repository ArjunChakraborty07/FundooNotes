package com.bridgelabz.fundoo.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.entity.NotesEntity;
import com.bridgelabz.fundoo.entity.UserEntity;
import com.bridgelabz.fundoo.exception.CollabCustomException;
import com.bridgelabz.fundoo.exception.CollabCustomException.TypeOfException;
import com.bridgelabz.fundoo.repository.INoteRepository;
import com.bridgelabz.fundoo.repository.IUserRepository;
import com.bridgelabz.fundoo.service.ICollaboratorService;
import com.bridgelabz.fundoo.utility.JWTOperations;

@Service
public class CollaboratorServiceImpl implements ICollaboratorService{

	@Autowired
	private JWTOperations jwt;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private INoteRepository noteRepository;
	
	public void addcolab(int noteId, String email, String token) {
		int userId=jwt.tokenDecoder(token);
		UserEntity user=userRepository.getUserById(userId).orElseThrow(()-> new CollabCustomException("User not found...Cannot Collaborate...", null, TypeOfException.USER_NOT_FOUND));
		userRepository.getUserByMail(email).orElseThrow(()-> new CollabCustomException("Collaborator not found...Cannot Collaborate...",null,TypeOfException.COLLAB_NOT_FOUND));
		NotesEntity notes= noteRepository.getNote(userId, noteId).orElseThrow(()->new CollabCustomException("Note Not Found...", null, TypeOfException.NOTE_NOT_FOUND));
		user.getCollabNote().add(notes);
		userRepository.save(user);
	}

	public void removeCollab(int noteId, String token, String email) {
		int userId=jwt.tokenDecoder(token);
		UserEntity user=userRepository.getUserById(userId).orElseThrow(()-> new CollabCustomException("User not found...", null,TypeOfException.COLLAB_NOT_FOUND));
		NotesEntity note=user.getCollabNote().stream().filter(c->c.getNoteId()==noteId).findFirst().orElseThrow(()-> new CollabCustomException("Note not found...", null,TypeOfException.COLLAB_NOT_FOUND));
		user.getCollabNote().remove(note);
		userRepository.save(user);
	}

	public List<NotesEntity> getAllCollabs(String token) {
		
		return null;
	}

}
