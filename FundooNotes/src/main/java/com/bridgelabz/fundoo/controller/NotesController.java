package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.NotesDTO;
import com.bridgelabz.fundoo.serviceimpl.NoteServiceImpl;

@RestController
@RequestMapping("notes")
public class NotesController {

	@Autowired
	NoteServiceImpl noteService;
	public void create(NotesDTO notesDTO)
	{
		noteService.createNotes(notesDTO);
	}
	
}
