package com.catalystdevworks.todo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@NotNull
	@Column(name = "title")
	private String taskTitle;
	
	@Column(name = "details")
	private String taskDetails;
	
	@Column(name = "due_date")
	private long dueDate;

	@Column(name = "last_modified_date")
	private long lastModifiedDate;

	@Column(name= "sync_date")
	private long syncDate;

	@Column(name = "location")
	private String locationName;

	@Column
	private double latitude;

	@Column
	private double longitude;

	@Column
	private String timeZone;

	@Column
	private boolean completed;
	
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Users user;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Set<Participant> participants;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDetails() {
		return taskDetails;
	}

	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}

	public long getDueDate() {
		return dueDate;
	}

	public void setDueDate(long dueDate) {
		this.dueDate = dueDate;
	}

	public String getLocationName() { return locationName; }

	public void setLocationName(String locationName) { this.locationName = locationName; }

	public double getLatitude() { return latitude; }

	public void setLatitude(double latitude) { this.latitude = latitude; }

	public double getLongitude() { return longitude; }

	public void setLongitude(double longitude) { this.longitude = longitude; }

	public String getTimeZone() { return timeZone; }

	public void setTimeZone(String timeZone) { this.timeZone = timeZone; }

	public long getLastModifiedDate() { return lastModifiedDate; }

	public void setLastModifiedDate(long date) { lastModifiedDate = date; }

	public long getSyncDate() { return syncDate; }

	public void setSyncDate(long syncDate) { this.syncDate = syncDate; }

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public boolean isCompleted() { return completed; }

	public void setCompleted(boolean completed) { this.completed = completed; }

    public Set<Participant> getParticipants() { return participants; }

    public void setParticipants(Set<Participant> participants) { this.participants = participants; }

}
