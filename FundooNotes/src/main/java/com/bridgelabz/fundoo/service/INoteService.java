package com.bridgelabz.fundoo.service;

import com.bridgelabz.fundoo.dto.NotesDTO;

public interface INoteService {

	public boolean createNotes(NotesDTO notesDTO,String token);
	public boolean edit(NotesDTO notesDTO, String token, int noteId);
	public boolean delete(String token);
	public boolean deleteNote(String token, int noteId);
	public String archieve(int noteId, String token);
	public String pinned(int noteId,String token);
	public String trashed(int noteId,String token);
}