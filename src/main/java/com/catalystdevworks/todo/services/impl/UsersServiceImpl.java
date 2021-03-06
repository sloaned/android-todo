package com.catalystdevworks.todo.services.impl;

import com.catalystdevworks.todo.dao.impl.UserDaoImpl;
import com.catalystdevworks.todo.entities.Users;
import com.catalystdevworks.todo.services.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl extends EntityCrudService<Users>{

	@Autowired
	public UsersServiceImpl(UserDaoImpl dao) {
		super(dao);
	}

	public Users getByUsername(String username) {
		System.out.println("CALLED GETBYUSERNAME");
		return ((UserDaoImpl)dao).getByUsername(username);
	}
	
	//The purpose of this method is to obfuscate the users password
	@Override
	public Users getSingleObject(int id){
		Users requestedUser = super.getSingleObject(id);
		requestedUser.setPassword("");
		return requestedUser;
	}
}
