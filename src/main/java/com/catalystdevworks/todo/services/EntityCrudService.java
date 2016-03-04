package com.catalystdevworks.todo.services;

import com.catalystdevworks.todo.dao.EntityCrudDao;

import java.util.List;

public abstract class EntityCrudService<T> {


	protected EntityCrudDao<T> dao;

	public EntityCrudService(EntityCrudDao<T> dao) {
		this.dao = dao;
	}

	public List<T> getAllObjects(Integer id){
		return dao.getAllObjects(id);
	}

	public T getSingleObject(int id) {
		return dao.getSingleObject(id);
	}

	public boolean createObject(T object) {
		try{
			return dao.createObject(object);
		}catch(Exception ex){
			return false;
		}
	}

	public boolean editObject(T object) {
		return dao.editObject(object);
	}

	public boolean deleteObject(int id) {
		return dao.deleteObject(id);
	}
}
