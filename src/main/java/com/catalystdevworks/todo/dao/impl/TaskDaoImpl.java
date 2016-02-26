package com.catalystdevworks.todo.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Task;

@Repository
@Transactional
public class TaskDaoImpl extends EntityCrudDao<Task>{
	public TaskDaoImpl() {
		super(Task.class);
	}
}