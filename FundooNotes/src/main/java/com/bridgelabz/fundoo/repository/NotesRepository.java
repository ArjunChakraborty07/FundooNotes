package com.bridgelabz.fundoo.repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.entity.NotesEntity;

@SuppressWarnings("rawtypes")
@Repository
public class NotesRepository {

	@Autowired
	private EntityManager entityManager;
	
	String id="id";
	String query="From NotesEntity where user_id=:";
	
	@Transactional
	public void save(NotesEntity notesEntity) 
	{		
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(notesEntity);
	}
	
	@Transactional
	@Modifying
	public void update(int userId, NotesEntity notesEntity)
	{
		Session session=entityManager.unwrap(Session.class);
		Query q=session.createQuery(query+id);
		q.setParameter(0, userId);
		session.saveOrUpdate(notesEntity);
	}

	@Transactional
	public void delete(int userId) 
	{
		Session session = entityManager.unwrap(Session.class);
		Query q=session.createQuery(query+id);		
		session.delete(q.setParameter(id, userId));
		
	}	
	
	@Transactional
	public NotesEntity getNote(int userId, int noteId) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("From NoteEntity where userId=? and noteId=?");
		q.setParameter(0, userId);
		q.setParameter(1, noteId);
		return (NotesEntity) q.uniqueResult();
	}
	
}
