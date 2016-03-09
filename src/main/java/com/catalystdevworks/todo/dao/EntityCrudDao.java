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
		System.out.println("in getAllObjects, userId = " + id + ", entity = " + entity + ", table = " + table);
        System.out.println("em = " + em);
		return em.createQuery(getSelectStatement() +" x where user.id = :id", entity)
				.setParameter("id",id).getResultList();
	}

	public T getSingleObject(int id) {
		System.out.println("in getSingleObject, id = " + id);
		System.out.println("entity = " + entity + ", table = " + table);
        System.out.println("em = " + em);
		return em.createQuery(getSelectStatement() +" x where x.id = :id", entity)
				.setParameter("id",id).getSingleResult();
	}

	public T createObject(T object) {
		try {
			em.persist(object);
			return object;
		} catch (Exception e) {
			System.out.println("An error occurred on entity creation");
			return null;
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
