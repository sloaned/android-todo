package com.catalystdevworks.todo.services.impl;

import com.catalystdevworks.todo.dao.impl.TaskDaoImpl;
import com.catalystdevworks.todo.entities.User;
import com.catalystdevworks.todo.entities.Users;
import com.catalystdevworks.todo.security.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.catalystdevworks.todo.entities.Task;
import com.catalystdevworks.todo.services.EntityCrudService;

import java.util.List;

@Service
public class TaskServiceImpl extends EntityCrudService<Task> {
	
	@Autowired
	public TaskServiceImpl(TaskDaoImpl dao) {
		super(dao);
	}
	
	//The purpose of this method is to obfuscate the users password
	@Override
	public Task getSingleObject(int id){
		Task task = super.getSingleObject(id);
		task.getUser().setPassword("");
		return task;
	}

	@Override
	public List<Task> getAllObjects() {
        List<Task> tasks = super.getAllObjects();
        for (Task task : tasks) {
            task.getUser().setPassword("");
        }
        return tasks;

    }

	@Override
	public Task createObject(Task task) {
		Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		task.setUser(user);
		return super.createObject(task);
	}
}