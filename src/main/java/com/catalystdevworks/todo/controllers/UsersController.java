package com.catalystdevworks.todo.controllers;

import com.catalystdevworks.todo.services.impl.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalystdevworks.todo.entities.Users;
import com.catalystdevworks.todo.utility.EndPointConstants;

@RestController
public class UsersController {

	@Autowired
	private UsersServiceImpl usersServiceImpl;

//	public void setUserServiceImpl(EntityCrudService<Users> userServiceImpl) {
//		this.usersServiceImpl = userServiceImpl;
//	}
	
	@RequestMapping(value=EndPointConstants.USER_GET_DELETE_ENDPOINT, method = RequestMethod.GET)
	public Users getSingleUser(@PathVariable int id){
		return usersServiceImpl.getSingleObject(id);
		
	}
	
	@RequestMapping(value=EndPointConstants.USER_POST_PUT_ENDPOINT, method = RequestMethod.POST)
	public Users createUser(@RequestBody Users users){
		return usersServiceImpl.createObject(users);
	}
	
	@RequestMapping(value=EndPointConstants.USER_POST_PUT_ENDPOINT, method = RequestMethod.PUT)
	public boolean editUser(@RequestBody Users users){
		return usersServiceImpl.editObject(users);
	}
	
	@RequestMapping(value=EndPointConstants.USER_GET_DELETE_ENDPOINT, method=RequestMethod.DELETE)
	public boolean deleteStore(@PathVariable int id){
		return usersServiceImpl.deleteObject(id);
	}
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public Object getCurrentUser(){
		final Object user =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return user;
	}
}