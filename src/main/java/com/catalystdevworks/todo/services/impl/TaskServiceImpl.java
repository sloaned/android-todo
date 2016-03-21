package com.catalystdevworks.todo.services.impl;

import com.catalystdevworks.todo.dao.impl.TaskDaoImpl;
import com.catalystdevworks.todo.dao.impl.UserDaoImpl;
import com.catalystdevworks.todo.entities.User;
import com.catalystdevworks.todo.entities.Users;
import com.catalystdevworks.todo.security.TokenHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.catalystdevworks.todo.entities.Task;
import com.catalystdevworks.todo.services.EntityCrudService;

import java.util.Date;
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

	public List<Task> getTasksToSync() {
		Integer id = (Integer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Task> tasks = ((TaskDaoImpl)dao).getTasksToSync(id);
		for (Task task : tasks) {
			task.getUser().setPassword("");
		}
		return tasks;
	}
/*
	@Override
	public Task createObject(Task task) {

		System.out.println("principal user = " + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		int userId = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDaoImpl userDao = new UserDaoImpl();
		Users user = userDao.getSingleObject(userId);
		System.out.println("user has been set, user = " + user);
		task.setUser(user);
		return super.createObject(task);
	}  */

    @Override
    public boolean editObject(Task task) {
        Date date = new Date();
        long milliseconds = date.getTime();
        task.setLastModifiedDate(milliseconds);
        return super.editObject(task);
    }
}