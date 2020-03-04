package com.bridgelabz.fundoo.repository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.entity.NotesEntity;

@SuppressWarnings("rawtypes")
@Repository
public class NotesRepository {

	@Autowired
	private EntityManager entityManager;
	
	String id="id";
	String query="From NotesEntity where id=:";
	
	
	@Transactional
	public NotesEntity getAllValues(int id2)
	{
		Session session = entityManager.unwrap(Session.class);
		Query q=session.createQuery(query+id);		
		q.setParameter(id, id2);
		return (NotesEntity) q.uniqueResult();
	}
	
	@Transactional
	public void save(NotesEntity notesEntity) 
	{		
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(notesEntity);			
	}
	
	@Transactional
	public void update(int id2, NotesEntity notesEntity)
	{
		Session session=entityManager.unwrap(Session.class);
		Query q=session.createQuery(query+id);
		q.setParameter(0, id2);
		session.saveOrUpdate(notesEntity);
	}

	public void delete(int id2) 
	{
		Session session = entityManager.unwrap(Session.class);
		Query q=session.createQuery(query+id);		
		session.delete(q.setParameter(id, id2));
		
	}		
	
}
