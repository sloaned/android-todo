package com.catalystdevworks.todo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.catalystdevworks.todo.entities.Task;
import org.springframework.stereotype.Repository;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Users;

@Repository
@Transactional
public class UserDaoImpl  extends EntityCrudDao<Users>{

	public UserDaoImpl() {
		super(Users.class);
	}

	public Users getByUsername(String user_email) {
		System.out.println(user_email);
		return em.createQuery("SELECT u FROM users u WHERE u.userEmail = :user_email", Users.class)
				.setParameter("user_email", user_email)
				.getSingleResult();
	}
}