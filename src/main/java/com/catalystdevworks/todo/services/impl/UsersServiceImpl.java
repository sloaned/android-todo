package com.catalystdevworks.todo.services.impl;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Users;
import com.catalystdevworks.todo.services.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements EntityCrudService<Users> {

	@Autowired
	private EntityCrudDao<Users> userDaoImpl;

	public void setUsersDaoImpl(EntityCrudDao<Users> usersDaoImpl){
		this.userDaoImpl = usersDaoImpl;
	}
	
	@Override
	public List<Users> getAllObjects(Integer id) {
		return userDaoImpl.getAllObjects(id);
	}

	@Override
	public Users getSingleObject(int id) {
		return userDaoImpl.getSingleObject(id);
	}

	@Override
	public boolean createObject(Users object) {
		return userDaoImpl.createObject(object);
	}

	@Override
	public boolean editObject(Users object) {
		return userDaoImpl.editObject(object);
	}

	@Override
	public boolean deleteObject(int id) {
		return userDaoImpl.deleteObject(id);
	}
}
