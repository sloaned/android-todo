package com.catalystdevworks.todo.dao;

import java.util.List;

public interface EntityCrudDao<T> {

	public List<T> getAllObjects(Integer id);
	public List<T> getAllObjects();
	public T getSingleObject(int id);
	public boolean createObject(T object);
	public boolean editObject(T object);
	public boolean deleteObject(int id);	
	
}
