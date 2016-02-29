package com.catalystdevworks.todo.services.impl;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.dao.impl.UserDaoImpl;
import com.catalystdevworks.todo.entities.Task;
import com.catalystdevworks.todo.entities.Users;
import com.catalystdevworks.todo.services.EntityCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
