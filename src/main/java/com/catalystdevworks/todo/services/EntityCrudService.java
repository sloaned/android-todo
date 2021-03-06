package com.catalystdevworks.todo.services;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public abstract class EntityCrudService<T> {


	protected EntityCrudDao<T> dao;

	public EntityCrudService(EntityCrudDao<T> dao) {
		this.dao = dao;
	}

	public List<T> getAllObjects(){
		Integer id = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return dao.getAllObjects(id);
	}

	public T getSingleObject(int id) {
		return dao.getSingleObject(id);
	}

	public T createObject(T object) {
        System.out.println("in the createObject service");
		try{
			return dao.createObject(object);
		}catch(Exception ex){
			return null;
		}
	}

	public boolean editObject(T object) {
        System.out.println("in the editObject service");
		return dao.editObject(object);
	}

	public boolean deleteObject(int id) {
		System.out.println("The id to delete = " + id);
		return dao.deleteObject(id);
	}
}
