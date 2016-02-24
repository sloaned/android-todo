package com.catalystdevworks.todo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Users;
import com.catalystdevworks.todo.services.EntityCrudService;

@Service
public class UsersServiceImpl implements EntityCrudService<Users> {

	@Autowired
	private EntityCrudDao<Users> usersDaoImpl;

	public void setUsersDaoImpl(EntityCrudDao<Users> usersDaoImpl){
		this.usersDaoImpl = usersDaoImpl;
	}
	
	@Override
	public List<Users> getAllObjects(Integer id) {
		return usersDaoImpl.getAllObjects(id);
	}

	@Override
	public Users getSingleObject(int id) {
		return usersDaoImpl.getSingleObject(id);
	}

	@Override
	public boolean createObject(Users object) {
		return usersDaoImpl.createObject(object);
	}

	@Override
	public boolean editObject(Users object) {
		return usersDaoImpl.editObject(object);
	}

	@Override
	public boolean deleteObject(int id) {
		return usersDaoImpl.deleteObject(id);
	}
}
