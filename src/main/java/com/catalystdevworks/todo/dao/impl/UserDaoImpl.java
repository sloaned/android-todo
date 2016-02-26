package com.catalystdevworks.todo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Users;

@Repository
@Transactional
public class UserDaoImpl{
	
	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public List<Users> getAllObjects(Integer id) {
		return null;
	}


	public Users getSingleObject(int id) {
		return em.createQuery("SELECT u FROM users u WHERE u.id = :id", Users.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	public boolean createObject(Users object) {
		return em.merge(object) != null;
	}

	public boolean editObject(Users object) {
		return em.merge(object) != null;
	}

	public boolean deleteObject(int id) {
		try{
			em.createQuery("DELETE FROM users u WHERE u.id=:id")
			.setParameter("id", id).executeUpdate();
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}

	public Users getByUsername(String user_email) {
		System.out.println(user_email);
		return em.createQuery("SELECT u FROM users u WHERE u.userEmail = :user_email", Users.class)
				.setParameter("user_email", user_email)
				.getSingleResult();
	}
}