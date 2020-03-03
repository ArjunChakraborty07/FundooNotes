package com.bridgelabz.fundoo.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.NotesDTO;
import com.bridgelabz.fundoo.entity.NotesEntity;
import com.bridgelabz.fundoo.repository.NotesRepository;
import com.bridgelabz.fundoo.utility.JWTOperations;

@Service
public class NoteServiceImpl {

	@Autowired
	private NotesRepository notesRepository;

	@Autowired
	private JWTOperations jwt;
	
	public void createNotes(NotesDTO notesDTO, String token) {
		
		NotesEntity notesEntity= new NotesEntity();		
		BeanUtils.copyProperties(notesDTO, notesEntity);
		notesEntity.setCreatedDate(LocalDateTime.now());
		notesEntity.setColor("white");
		notesEntity.setPinned(false);
		notesEntity.setArchived(false);
		notesEntity.setTrashed(false);
		notesEntity.setRemainderDate(null);
		notesRepository.save(notesEntity);
	
	}

	public void edit(NotesDTO notesDTO, String token) {
		int id=jwt.tokenDecoder(token);
		NotesEntity notesEntity= notesRepository.getAllValues(id);	
		BeanUtils.copyProperties(notesDTO, notesEntity);
		notesRepository.update(notesEntity);
	}

	public void delete(String token) {
		
		notesRepository.delete(token);		
		
	}

	public void archieve(String token) {
		
		NotesEntity notesEntity= notesRepository.getAllValues(token);
		if(notesEntity.isArchived())
		{
			notesEntity.setArchived(false);
			notesRepository.update(token, notesEntity);
		}	
		else
		{
			notesEntity.setArchived(true);
			notesRepository.update(token,notesEntity);
		}
	}

	public void pinned(String token) {
		
		NotesEntity notesEntity= notesRepository.getAllValues(token);
		if(notesEntity.isPinned())
		{
			notesEntity.setPinned(false);
			notesRepository.update(token, notesEntity);
		}	
		else
		{
			notesEntity.setPinned(true);
			notesRepository.update(token,notesEntity);
		}		
	}

	public void trashed(String token) {
		
		NotesEntity notesEntity= notesRepository.getAllValues(token);
		if(notesEntity.isTrashed())
		{
			notesEntity.setTrashed(false);
			notesRepository.update(token, notesEntity);
		}	
		else
		{
			notesEntity.setTrashed(true);
			notesRepository.update(token,notesEntity);
		}
	}
	

	
}
