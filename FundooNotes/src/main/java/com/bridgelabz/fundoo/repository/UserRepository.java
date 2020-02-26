package com.bridgelabz.fundoo.repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.entity.UserEntity;

@Repository
public class UserRepository {
	@Autowired
	private EntityManager entityManager;

	@Transactional
	public void save(UserEntity user) {
//		Configuration con=new Configuration().configure().addAnnotatedClass(UserEntity.class);
//		SessionFactory sf = new Configuration().configure().buildSessionFactory();

//		SessionFactory sf=con.buildSessionFactory();

		Session session = entityManager.unwrap(Session.class);
		session.saveOrUpdate(user);
//	
	}
}