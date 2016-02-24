package com.catalystdevworks.todo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalystdevworks.todo.entities.Task;
import com.catalystdevworks.todo.services.EntityCrudService;
import com.catalystdevworks.todo.utility.EndPointConstants;

@RestController
public class TaskController {
	
	@Autowired
	EntityCrudService<Task> taskServiceImpl;
	
	public void setTaskServiceImpl(EntityCrudService<Task> taskServiceImpl){
		this.taskServiceImpl = taskServiceImpl;
	}
	
	@RequestMapping(value="/allTasks", method = RequestMethod.GET)
	public List<Task> getAllTasks(){
		return null;
	}
	
	@RequestMapping(value=EndPointConstants.TASK_GET_DELETE_ENDPOINT, method = RequestMethod.GET)
	public Task getSingleTask(@PathVariable int id){
		return taskServiceImpl.getSingleObject(id);
	}
	
	@RequestMapping(value=EndPointConstants.TASK_POST_PUT_ENDPOINT, method = RequestMethod.POST)
	public boolean createTask(@RequestBody Task task){
		return taskServiceImpl.createObject(task);
	}
	
	@RequestMapping(value=EndPointConstants.TASK_POST_PUT_ENDPOINT, method = RequestMethod.PUT)
	public boolean editTask(@RequestBody Task task){
		return taskServiceImpl.editObject(task);
	}
	
	@RequestMapping(value=EndPointConstants.TASK_GET_DELETE_ENDPOINT, method=RequestMethod.DELETE)
	public boolean deleteStore(@PathVariable int id){
		return taskServiceImpl.deleteObject(id);
	}
}