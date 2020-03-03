package com.bridgelabz.fundoo.repository;



import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.dto.UserDTO;
import com.bridgelabz.fundoo.entity.UserEntity;



@SuppressWarnings("rawtypes")
@Repository
public class UserRepository {
	@Autowired
	private EntityManager entityManager;
	
	String email="email";
	String query="From UserEntity where email_id=:";
	
	@Transactional
	public UserEntity getAllValues(String emailid)
	{
		Session session = entityManager.unwrap(Session.class);
		Query q=session.createQuery(query+email);		
		q.setParameter(email, emailid);
		return (UserEntity) q.uniqueResult();
	}		
	
	@Transactional
	public boolean save(UserEntity userEntity) {

		Session session = entityManager.unwrap(Session.class);
		Query q=session.createQuery(query+email);
		q.setParameter(email, userEntity.getEmail());
		if((UserEntity)q.uniqueResult()==null)
		{				
			session.saveOrUpdate(userEntity);
			return true;
		}
		return false;	
	}
	
	@Transactional
	public UserEntity loginProcess(UserDTO userDetails)
	{

		Session session = entityManager.unwrap(Session.class);		
		Query q=session.createQuery(query+email);
		q.setParameter(email, userDetails.getEmail());
		return (UserEntity)q.uniqueResult();
		
	}
	@Transactional
	public UserDTO forgetpwd(UserDTO userDetails)
	{		
			Session session = entityManager.unwrap(Session.class);		
			Query q=session.createQuery(query+email);
			q.setParameter(email, userDetails.getEmail());
			return (UserDTO)q.uniqueResult();
	}
	
	@Transactional
	@Modifying
	public void updatepassword(UserEntity userEntity) {
		
		Session session = entityManager.unwrap(Session.class);
		
		Query q=session.createQuery("UPDATE UserEntity set password =:password where email =:"+email);
		q.setParameter("password", userEntity.getPassword());
		q.setParameter(email, userEntity.getEmail());
		q.executeUpdate();
	}
	
	@Transactional
	@Modifying
	public void verification(UserEntity userEntity) {
		
		Session session = entityManager.unwrap(Session.class);
		userEntity.setVerify(true);
		Query q=session.createQuery("UPDATE UserEntity set verify =:verify where email =:email");
		q.setParameter("verify", userEntity.getVerify());
		q.setParameter("email", userEntity.getEmail());
		q.executeUpdate();
	}
	
	
}
