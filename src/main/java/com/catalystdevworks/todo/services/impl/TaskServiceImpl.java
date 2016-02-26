package com.catalystdevworks.todo.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Task;
import com.catalystdevworks.todo.services.EntityCrudService;

@Service
public class TaskServiceImpl implements EntityCrudService<Task> {
	
	@Autowired
	private EntityCrudDao<Task> taskDaoImpl;
	
	private void setTaskDaoImpl(EntityCrudDao<Task> taskDaoImpl){
		this.taskDaoImpl = taskDaoImpl;
	}

	@Override
	public List<Task> getAllObjects(Integer id) {
		return taskDaoImpl.getAllObjects(id);
	}

	@Override
	public List<Task> getAllObjects() {
        return taskDaoImpl.getAllObjects();
	}


	@Override
	public Task getSingleObject(int id) {
		return taskDaoImpl.getSingleObject(id);
	}

	@Override
	public boolean createObject(Task object) {
		return taskDaoImpl.createObject(object);
	}

	@Override
	public boolean editObject(Task object) {
		return taskDaoImpl.editObject(object); 
	}

	@Override
	public boolean deleteObject(int id) {
		return taskDaoImpl.deleteObject(id);
	}
}
