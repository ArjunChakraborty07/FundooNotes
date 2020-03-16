package com.bridgelabz.fundoo.repositoryimpl;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.entity.NotesEntity;
import com.bridgelabz.fundoo.repository.INoteRepository;

@SuppressWarnings("rawtypes")
@Repository
public class NotesRepositoryImpl implements INoteRepository{

	@Autowired
	private EntityManager entityManager;
	
	String id="id";
	String query="From NotesEntity where user_id=:";
	
	@Transactional
	public boolean save(NotesEntity notesEntity) 
	{		
		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(notesEntity);
		return true;
	}
	
	@Transactional
	@Modifying
	public boolean update(int userId, NotesEntity notesEntity, int noteId)
	{
		
		Session session=entityManager.unwrap(Session.class);			
		Query q=session.createQuery("update NotesEntity set title=:title, description=:desc,  updated_date=:dt, is_archived=:iad, is_pinned=:ipd, is_trashed=:itd where user_id=:uid and note_id=:nid");
		q.setParameter("title", notesEntity.getTitle());
		q.setParameter("desc", notesEntity.getDescription());		
		q.setParameter("dt", notesEntity.getUpdatedDate());
		q.setParameter("itd", notesEntity.isTrashed());
		q.setParameter("ipd", notesEntity.isPinned());
		q.setParameter("iad", notesEntity.isArchived());
		q.setParameter("uid", userId);
		q.setParameter("nid", noteId);
		q.executeUpdate();
		return true;
	}

	@Transactional
	@Modifying
	public boolean deleteAllNotes(int userId) 
	{
		Session session = entityManager.unwrap(Session.class);
		Query q=session.createQuery("Delete from NotesEntity where user_Id=:id");
		q.setParameter("id", userId);
		q.executeUpdate();
		return true;		
	}	
	
	@SuppressWarnings("unchecked")
	@Transactional
	public Optional<NotesEntity> getNote(int userId, int noteId) {
		Session session = entityManager.unwrap(Session.class);
		Query q = session.createQuery("From NotesEntity where user_Id=:uid and note_Id=:nid");
		q.setParameter("uid", userId);
		q.setParameter("nid", noteId);
		return q.uniqueResultOptional();
	}

	@SuppressWarnings("unchecked")
	public List<NotesEntity> getAllNotes(int userid) {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from NoteEntity where user_id='" + userid + "'"
		+ "and is_trashed=false and is_archieved=false ORDER BY id DESC").getResultList();
		
		}
	
	
	@Transactional
	@Modifying
	public boolean deleteSelectedNote(int userId, int noteId) {
		Session session = entityManager.unwrap(Session.class);
		Query q=session.createQuery("Delete from NotesEntity where user_Id=:uid and note_Id=:nid");
		q.setParameter("uid", userId);
		q.setParameter("nid", noteId);
		q.executeUpdate();
		return true;
	}	
}
