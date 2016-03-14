package com.catalystdevworks.todo.dao.impl;

import javax.transaction.Transactional;

import com.catalystdevworks.todo.entities.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Task;

@Repository
@Transactional
public class TaskDaoImpl extends EntityCrudDao<Task>{
	public TaskDaoImpl() {
		super(Task.class);
	}


	@Override
	public Task createObject(Task task) {
        System.out.println("in createObject, em = " + em);
		int userId = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("userId = " + userId);
		Users user = em.createQuery("SELECT x from Users x where x.userId = :id", Users.class)
                .setParameter("id",userId).getSingleResult();

        System.out.println("got user, user = " + user);
        task.setUser(user);
        try {
            em.persist(task);
            return task;
        } catch (Exception e) {
            System.out.println("An error occurred on entity creation");
            return null;
        }

	}

    @Override
    public boolean editObject(Task task) {
        int userId = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = em.createQuery("SELECT x from Users x where x.userId = :id", Users.class)
                .setParameter("id",userId).getSingleResult();

        System.out.println("got user, user = " + user);
        task.setUser(user);
        em.merge(task);
        return true;
    }

}

