package com.catalystdevworks.todo.services.impl;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.dao.impl.UserDaoImpl;
import com.catalystdevworks.todo.entities.Users;
import com.catalystdevworks.todo.services.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl {

	@Autowired
	private UserDaoImpl userDaoImpl;

//	public void setUsersDaoImpl(EntityCrudDao<Users> usersDaoImpl){
//		this.userDaoImpl = usersDaoImpl;
//	}
	
	public List<Users> getAllObjects(Integer id) {
		return userDaoImpl.getAllObjects(id);
	}

	public Users getSingleObject(int id) {
		return userDaoImpl.getSingleObject(id);
	}

	public boolean createObject(Users object) {
		return userDaoImpl.createObject(object);
	}

	public boolean editObject(Users object) {
		return userDaoImpl.editObject(object);
	}

	public boolean deleteObject(int id) {
		return userDaoImpl.deleteObject(id);
	}

	public Users getByUsername(String username){
		return userDaoImpl.getByUsername(username);
	}
}
