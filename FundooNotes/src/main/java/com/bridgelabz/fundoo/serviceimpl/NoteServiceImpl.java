package com.bridgelabz.fundoo.serviceimpl;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.dto.NotesDTO;
import com.bridgelabz.fundoo.entity.NotesEntity;
import com.bridgelabz.fundoo.repository.NotesRepository;

@Service
public class NoteServiceImpl {

	@Autowired
	NotesRepository notesRepository;

	public void createNotes(NotesDTO notesDTO) {
		
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

	public void edit(NotesDTO notesDTO, int id) {
		
		NotesEntity notesEntity= notesRepository.getAllValues(id);	
		BeanUtils.copyProperties(notesDTO, notesEntity);
		notesRepository.update(id, notesEntity);
	}

	public void delete(int id) {
		
		notesRepository.delete(id);		
		
	}

	public void archieve(int id) {
		
		NotesEntity notesEntity= notesRepository.getAllValues(id);
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

	public void pinned(int id) {
		
		NotesEntity notesEntity= notesRepository.getAllValues(id);
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

	public void trashed(int id) {
		
		NotesEntity notesEntity= notesRepository.getAllValues(id);
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
