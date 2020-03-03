package com.bridgelabz.fundoo.repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.entity.NotesEntity;

@Repository
public class NotesRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Transactional
	public void save(NotesEntity notesEntity) {
		
			Session session = entityManager.unwrap(Session.class);
			session.saveOrUpdate(notesEntity);
			
		}
		
	

	
}
