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
public class UserDaoImpl implements EntityCrudDao<Users> {
	
	@PersistenceContext
	private EntityManager em;
	
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public List<Users> getAllObjects(Integer id) {
		return null;
	}

	@Override
	public Users getSingleObject(int id) {
		return em.createQuery("SELECT u FROM users u WHERE u.id = :id", Users.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public boolean createObject(Users object) {
		if(em.merge(object) instanceof Users){
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean editObject(Users object) {
		if(em.merge(object) instanceof Users){
			return true;
		} else {
			return false;
		}
	}

	@Override
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
}