package com.bridgelabz.fundoo.service;

import java.util.List;

import com.bridgelabz.fundoo.entity.NotesEntity;

public interface ICollaboratorService {

	public void addcolab(int noteId, String email, String token);
	public void removeCollab(int noteId, String token, String email);
	public List<NotesEntity> getAllCollabs(String token);
}
