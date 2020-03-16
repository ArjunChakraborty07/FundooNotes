package com.bridgelabz.fundoo.repository;

import java.util.List;
import java.util.Optional;

import com.bridgelabz.fundoo.entity.NotesEntity;

public interface INoteRepository {

	public boolean save(NotesEntity notesEntity);
	public boolean update(int userId, NotesEntity notesEntity, int noteId);
	public boolean deleteAllNotes(int userId);
	public Optional<NotesEntity> getNote(int userId, int noteId);
	public List<NotesEntity> getAllNotes(int userid);
	public boolean deleteSelectedNote(int userId, int noteId);
}
