package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.NotesDTO;
import com.bridgelabz.fundoo.serviceimpl.NoteServiceImpl;

@RestController
@RequestMapping("notes")
public class NotesController {

	@Autowired
	NoteServiceImpl noteService;
	
	@PostMapping("/create/{id}")
	public void create(@RequestBody NotesDTO notesDTO,@PathVariable int id)
	{
		noteService.createNotes(notesDTO);
	}
	
	@PutMapping("/edit/{id}")
	public void edit(@RequestBody NotesDTO notesDTO, @PathVariable int id)
	{
		noteService.edit(notesDTO, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable int id)
	{
		noteService.delete(id);
	}
	
	@PutMapping("/archieve/{id}")
	public void archieve(@PathVariable int id)
	{
		noteService.archieve(id);
	}
	
	@PutMapping("/pinned/{id}")
	public void pinned(@PathVariable int id)
	{
		noteService.pinned(id);
	}
	
	@PutMapping("/trashed/{id}")
	public void trashed(@PathVariable int id)
	{
		noteService.trashed(id);
	}
}
