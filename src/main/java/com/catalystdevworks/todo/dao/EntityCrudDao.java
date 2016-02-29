package com.catalystdevworks.todo.dao;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public abstract class EntityCrudDao<T> {

	private final Class<T> entity;
	@PersistenceContext
	protected EntityManager em;
	private String table;

	public EntityCrudDao(Class<T> entity) {
		this.table = entity.getSimpleName();
		this.entity = entity;
	}
	private String getSelect(){
		return "SELECT x from  "+ table;
	}

	public List<T> getAllObjects(Integer id){
		return em.createQuery(getSelect() +" x", entity).getResultList();
	}


	public T getSingleObject(int id) {
		return em.createQuery(getSelect() +" x where x.id = :id", entity)
				.setParameter("id",id).getSingleResult();
	}

	public boolean createObject(T object) {
		em.persist(object);
		return true;
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
