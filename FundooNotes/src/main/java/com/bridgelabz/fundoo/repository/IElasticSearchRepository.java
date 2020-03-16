package com.bridgelabz.fundoo.repository;

import java.util.List;

import com.bridgelabz.fundoo.entity.NotesEntity;

public interface IElasticSearchRepository {

	public void createNote(NotesEntity note);
	public String updateNote(NotesEntity note);
	public String deleteNote(NotesEntity note);
	public List<NotesEntity> searchByTitle(String title);
	
	
}
