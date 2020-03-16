package com.bridgelabz.fundoo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.dto.NotesDTO;
import com.bridgelabz.fundoo.exception.NotesCustomException.TypeOfException;
import com.bridgelabz.fundoo.response.NoteResponse;
import com.bridgelabz.fundoo.service.INoteService;

@RestController
@RequestMapping("/notes")
public class NotesController {

	@Autowired
	INoteService noteService;
	
	@PostMapping("/create/{token}")
	public ResponseEntity<NoteResponse> create(@RequestBody NotesDTO notesDTO, @PathVariable("token") String token)
	{
		if(noteService.createNotes(notesDTO, token))
			return ResponseEntity.status(HttpStatus.CREATED).body(new NoteResponse(token, "Note Creation Successful...",null));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NoteResponse(null, "Note Creation Denied...",TypeOfException.USER_NOT_FOUND));
	}
	
	@PutMapping("/edit/{token}")
	public ResponseEntity<NoteResponse> edit(@RequestBody NotesDTO notesDTO, @PathVariable("token") String token, @RequestParam("noteId") int noteId)
	{
		if(noteService.edit(notesDTO, token, noteId))
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new NoteResponse(token, "Note Update Successful...",null));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NoteResponse(token, "Note Update Denied...",TypeOfException.NOTE_NOT_FOUND));
	}
	
	@DeleteMapping("/delete/{token}")
	public ResponseEntity<NoteResponse> delete(@RequestParam("noteId") int noteId,@PathVariable("token") String token)
	{
		if(noteService.deleteNote(token,noteId))
			return ResponseEntity.status(HttpStatus.OK).body(new NoteResponse(token, "Note deleted Successfully",null));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NoteResponse(token, "Bad Request...",TypeOfException.BAD_REQUEST));
	}
	@DeleteMapping("/deleteAll/{token}")
	public ResponseEntity<NoteResponse> delete(@PathVariable("token") String token)
	{
		if(noteService.delete(token))
		{
		return ResponseEntity.status(HttpStatus.OK).body(new NoteResponse(token, "Notes deleted Successfully",null));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new NoteResponse(token, "Bad Request...",TypeOfException.BAD_REQUEST));
	}
	
	
	@PutMapping("/archieve/{token}")
	public ResponseEntity<NoteResponse> archieve(@RequestParam("noteId") int noteId,@PathVariable("token") String token)
	{
		String message=noteService.archieve(noteId,token);
		return ResponseEntity.status(HttpStatus.OK).body(new NoteResponse(token, message,null));
	}
	
	@PutMapping("/pinned/{token}")
	public ResponseEntity<NoteResponse> pinned(@RequestParam("noteId") int noteId,@PathVariable("token") String token)
	{
		String message=noteService.pinned(noteId,token);
		return ResponseEntity.status(HttpStatus.OK).body(new NoteResponse(token, message,null));
	}
	
	@PutMapping("/trashed/{token}")
	public ResponseEntity<NoteResponse> trashed(@RequestParam("noteId") int noteId,@PathVariable("token") String token)
	{
		String message=noteService.trashed(noteId,token);
		return ResponseEntity.status(HttpStatus.OK).body(new NoteResponse(token, message,null));
	}
}
