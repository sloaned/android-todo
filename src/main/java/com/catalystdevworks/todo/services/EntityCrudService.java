package com.catalystdevworks.todo.services;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface EntityCrudService<T> {

	public List<T> getAllObjects(Integer id);
	public T getSingleObject(int id);
	public boolean createObject(T object);
	public boolean editObject(T object);
	public boolean deleteObject(int id);

}
