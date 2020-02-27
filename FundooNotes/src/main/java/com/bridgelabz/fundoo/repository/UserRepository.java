package com.bridgelabz.fundoo.repository;



import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.dto.UserDetails;
import com.bridgelabz.fundoo.entity.UserEntity;

@Repository
public class UserRepository {
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void save(UserEntity user) {

		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);
	}
	
	@SuppressWarnings("rawtypes")
	@Transactional
	public UserEntity loginProcess(UserDetails object)
	{

		Session session = entityManager.unwrap(Session.class);		
		Query q=session.createQuery("From UserEntity where email_id=:email");
		q.setParameter("email", object.getEmail());
		return (UserEntity)q.uniqueResult();
		
	}
}
