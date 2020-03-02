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
		notesRepository.save(notesEntity);
	}

	
}
