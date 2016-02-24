package com.catalystdevworks.todo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Task;

@Repository
@Transactional
public class TaskDaoImpl implements EntityCrudDao<Task>{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> getAllObjects(Integer id) {
		return em.createQuery("SELECT t FROM task t WHERE user_id = :id")
				.setParameter("id", id)
				.getResultList();
	}

	@Override
	public Task getSingleObject(int id) {
		return em.createQuery("SELECT t FROM task t WHERE t.id =:id", Task.class)
				.setParameter("id", id)
				.getSingleResult();
	}

	@Override
	public boolean createObject(Task object) {
		
		if(em.merge(object) instanceof Task){
		return true;
		}else{
			return false;
		}	
	}

	@Override
	public boolean editObject(Task object) {
		if(em.merge(object) instanceof Task){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteObject(int id) {
		try{
			em.createQuery("DELETE FROM task t WHERE t.id=:id")
			.setParameter("id", id).executeUpdate();
			return true;
		}catch(Exception e){
			System.out.println(e);
			return false;
		}
	}
}