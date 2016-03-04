package com.catalystdevworks.todo.dao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public abstract class EntityCrudDao<T> {

	@PersistenceContext
	protected EntityManager em;
	private final Class<T> entity;
	private String table;

	public EntityCrudDao(Class<T> entity) {
		this.table = entity.getSimpleName();
		this.entity = entity;
	}
	
	protected String getSelectStatement(){
		return "SELECT x from "+ table;
	}

	public List<T> getAllObjects(Integer id){
		return em.createQuery(getSelectStatement() +" x", entity).getResultList();
	}

	public T getSingleObject(int id) {
		return em.createQuery(getSelectStatement() +" x where x.id = :id", entity)
				.setParameter("id",id).getSingleResult();
	}

	public boolean createObject(T object) {
		try{
			em.persist(object);
			return true;
		}catch(Exception e){
			System.out.println("An error occured on entity creation");
			return false;
		}
	}

	public boolean editObject(T object) {
		em.merge(object);
		return true;
	}

	public boolean deleteObject(int id) {
		em.remove(getSingleObject(id));
		return true;
	}
}
