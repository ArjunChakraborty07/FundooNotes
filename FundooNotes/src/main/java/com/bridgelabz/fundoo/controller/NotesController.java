package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.NotesDTO;
import com.bridgelabz.fundoo.serviceimpl.NoteServiceImpl;

@RestController
@RequestMapping("/notes")
public class NotesController {

	@Autowired
	NoteServiceImpl noteService;
	
	@PostMapping("/create/{token}")
	public void create(@RequestBody NotesDTO notesDTO, @PathVariable("token") String token)
	{
		noteService.createNotes(notesDTO, token);
	}
	
	@PutMapping("/edit/{token}")
	public void edit(@RequestBody NotesDTO notesDTO, @PathVariable("token") String token, @RequestParam("noteId") int noteId)
	{
		noteService.edit(notesDTO, token, noteId);
	}
	
	@DeleteMapping("/delete/{token}")
	public void delete(@RequestBody int noteId,@PathVariable("token") String token)
	{
		noteService.delete(noteId,token);
	}
	
	@PutMapping("/archieve/{token}")
	public void archieve(@RequestBody int noteId,@PathVariable("token") String token)
	{
		noteService.archieve(noteId,token);
	}
	
	@PutMapping("/pinned/{token}")
	public void pinned(@RequestBody int noteId,@PathVariable("token") String token)
	{
		noteService.pinned(noteId,token);
	}
	
	@PutMapping("/trashed/{token}")
	public void trashed(@RequestBody int noteId,@PathVariable("token") String token)
	{
		noteService.trashed(noteId,token);
	}
}
