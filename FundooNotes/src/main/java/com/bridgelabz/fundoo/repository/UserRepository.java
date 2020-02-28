package com.bridgelabz.fundoo.repository;



import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.dto.UserDetails;
import com.bridgelabz.fundoo.entity.UserEntity;
@SuppressWarnings("rawtypes")
@Repository
public class UserRepository {
	@Autowired
	private EntityManager entityManager;
	
	

	@Transactional
	public void save(UserEntity data) {

		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(data);
	}
	
	@Transactional
	public UserEntity loginProcess(UserDetails object)
	{

		Session session = entityManager.unwrap(Session.class);		
		Query q=session.createQuery("From UserEntity where email_id=:email");
		q.setParameter("email", object.getEmail());
		return (UserEntity)q.uniqueResult();
		
	}
	@Transactional
	public boolean forgetpwd(UserDetails object)
	{
		try
		{
			Session session = entityManager.unwrap(Session.class);		
			Query q=session.createQuery("From UserEntity where email_id=:email");
			q.setParameter("email", object.getEmail());
			return true;
		}	
		catch(Exception e)
		{
			return false;
		}
	}
	@Transactional
	@Modifying
	public void updatepassword(UserEntity data) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query q=session.createQuery("UPDATE UserEntity set password =:password where email =:email");
		q.setParameter("password", data.getPassword());
		q.setParameter("email", data.getEmail());
		q.executeUpdate();
	}
	@Transactional
	@Modifying
	public void verification(UserEntity data) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query q=session.createQuery("UPDATE UserEntity set verify =:verify where email =:email");
		q.setParameter("verify", data.getverify());
		q.setParameter("email", data.getEmail());
		q.executeUpdate();
	}
	
	
}
