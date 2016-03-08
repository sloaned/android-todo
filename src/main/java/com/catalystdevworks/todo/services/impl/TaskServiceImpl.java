package com.catalystdevworks.todo.services.impl;

import com.catalystdevworks.todo.dao.impl.TaskDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
        System.out.println("getting all those user objects");
        for (Task task : tasks) {
            System.out.println("user = " + task.getUser().getUserEmail());
            task.getUser().setPassword("");
        }
        return tasks;

    }
}