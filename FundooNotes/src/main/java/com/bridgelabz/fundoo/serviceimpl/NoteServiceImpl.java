package com.bridgelabz.fundoo.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.NotesDTO;
import com.bridgelabz.fundoo.entity.NotesEntity;
import com.bridgelabz.fundoo.entity.UserEntity;
import com.bridgelabz.fundoo.repository.NotesRepository;
import com.bridgelabz.fundoo.repository.UserRepository;
import com.bridgelabz.fundoo.utility.JWTOperations;

@Service
public class NoteServiceImpl {

	@Autowired
	private NotesRepository notesRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JWTOperations jwt;	
	
	public void createNotes(NotesDTO notesDTO,String token) {						
		
		
		UserEntity user=userRepository.getUserById(jwt.tokenDecoder(token));
		
		NotesEntity notesEntity= new NotesEntity();		
		BeanUtils.copyProperties(notesDTO, notesEntity);
		
		notesEntity.setCreatedDate(LocalDateTime.now());
		notesEntity.setColor("white");
		notesEntity.setPinned(false);
		notesEntity.setArchived(false);
		notesEntity.setTrashed(false);
		notesEntity.setRemainderDate(null);
		user.getNotes().add(notesEntity);
		notesRepository.save(notesEntity);
	
	}

	public void edit(NotesDTO notesDTO, String token, int noteId) {
		
		int id=jwt.tokenDecoder(token);
		NotesEntity notesEntity= notesRepository.getNote(noteId,id);	
		BeanUtils.copyProperties(notesDTO, notesEntity);
		notesEntity.setUpdatedDate(LocalDateTime.now());
		notesRepository.update(id,notesEntity);
	}

	public void delete(int noteId,String token) {
		
		int id=jwt.tokenDecoder(token);
		notesRepository.delete(id);		
		
	}

	public void archieve(int noteId, String token) {
		
		int id=jwt.tokenDecoder(token);
		NotesEntity notesEntity= notesRepository.getNote(noteId,id);
		if(notesEntity.isArchived())
		{
			notesEntity.setArchived(false);
			notesRepository.update(id, notesEntity);
		}	
		else
		{
			notesEntity.setArchived(true);
			notesRepository.update(id,notesEntity);
		}
	}

	public void pinned(int noteId,String token) {
		
		int id=jwt.tokenDecoder(token);
		NotesEntity notesEntity= notesRepository.getNote(noteId,id);
		if(notesEntity.isPinned())
		{
			notesEntity.setPinned(false);
			notesRepository.update(id, notesEntity);
		}	
		else
		{
			notesEntity.setPinned(true);
			notesRepository.update(id,notesEntity);
		}		
	}

	public void trashed(int noteId,String token) {
		
		int id=jwt.tokenDecoder(token);
		NotesEntity notesEntity= notesRepository.getNote(noteId,id);
		if(notesEntity.isTrashed())
		{
			notesEntity.setTrashed(false);
			notesRepository.update(id, notesEntity);
		}	
		else
		{
			notesEntity.setTrashed(true);
			notesRepository.update(id,notesEntity);
		}
	}
	

	
}
