package com.catalystdevworks.todo.dao.impl;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;

import com.catalystdevworks.todo.entities.Participant;
import com.catalystdevworks.todo.entities.Users;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import com.catalystdevworks.todo.dao.EntityCrudDao;
import com.catalystdevworks.todo.entities.Task;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class TaskDaoImpl extends EntityCrudDao<Task>{
	public TaskDaoImpl() {
		super(Task.class);
	}


	@Override
	public Task createObject(Task task) {

        System.out.println("in createObject, task name = " + task.getTaskTitle());

        int userId = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users user = em.createQuery("SELECT x from Users x where x.userId = :id", Users.class)
                .setParameter("id",userId).getSingleResult();
        task.setUser(user);

        for (Participant p : task.getParticipants()) {

            System.out.println("participant!");
            try {
                if (p.getParticipantName() != null) {
                    System.out.println(p.getParticipantName());
                    Integer id = em.createQuery("SELECT p.id FROM Participant p WHERE p.participantName = :name", Integer.class).setParameter("name", p.getParticipantName()).getSingleResult();
                    p.setId(id);
                }
            } catch (NoResultException e) {
                em.persist(p);
            }
        }

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

        for (Participant p : task.getParticipants()) {
            try {
                if (p.getParticipantName() != null) {
                    Integer id = em.createQuery("SELECT p.id FROM Participant p WHERE p.participantName = :name", Integer.class).setParameter("name", p.getParticipantName()).getSingleResult();
                    p.setId(id);
                }
            } catch (NoResultException e) {
                em.persist(p);
            }
        }

        em.merge(task);
        return true;
    }

    /*
    public boolean changeSyncDate(Task task) {
        int userId = (int) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = em.createQuery("SELECT x from Users x where x.userId = :id", Users.class)
                .setParameter("id",userId).getSingleResult();

        System.out.println("got user, user = " + user);
        task.setUser(user);

        for (Participant p : task.getParticipants()) {
            try {
                if (p.getParticipantName() != null) {
                    Integer id = em.createQuery("SELECT p.id FROM Participant p WHERE p.participantName = :name", Integer.class).setParameter("name", p.getParticipantName()).getSingleResult();
                    p.setId(id);
                }
            } catch (NoResultException e) {
                em.persist(p);
            }
        }

        em.merge(task);
        return true;
    }   */

    public List<Task> getTasksToSync(Integer id) {
        List<Task> tasks =  em.createQuery(getSelectStatement() +" x WHERE user.id = :id AND last_modified_date > sync_date", Task.class)
                .setParameter("id",id).getResultList();

        Date date = new Date();
        long milliseconds = date.getTime();
        for (Task task : tasks) {
            task.setSyncDate(milliseconds);
            editObject(task);
        }
        return tasks;
    }

}

