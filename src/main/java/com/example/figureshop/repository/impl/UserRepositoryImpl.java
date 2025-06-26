package com.example.figureshop.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.example.figureshop.config.HibernateConfig;
import com.example.figureshop.entity.User;
import com.example.figureshop.repository.IUserRepository;

@Repository
public class UserRepositoryImpl implements IUserRepository {

	@Override
	public void save(User user) {
		Session session = HibernateConfig.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.persist(user);
		tx.commit();
		session.close();
	}

	@Override
	public List<User> findAll() {
		Session session = HibernateConfig.getSessionFactory().openSession();
		List<User> users = session.createQuery("FROM User", User.class).list();
		session.close();
		return users;
	}

}
