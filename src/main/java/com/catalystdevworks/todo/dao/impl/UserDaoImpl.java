package com.catalystdevworks.todo.dao.impl;

import javax.transaction.Transactional;

import com.catalystdevworks.todo.entities.User;
import org.springframework.stereotype.Repository;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Users;

@Repository
@Transactional
public class UserDaoImpl  extends EntityCrudDao<Users> {

	public UserDaoImpl() {
		super(Users.class);
	}

	public Users getByUsername(String user_email) {
		return em.createQuery(getSelectStatement() + " x WHERE x.userEmail = :user_email", Users.class)
				.setParameter("user_email", user_email)
				.getSingleResult();
	}

}