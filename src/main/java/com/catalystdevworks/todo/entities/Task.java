package com.catalystdevworks.todo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

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
	private String dueDate;

	@Column(name = "location")
	private String locationName;

	@Column
	private double latitude;

	@Column
	private double longitude;
	
	@ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	private Users user;

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

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getLocationName() { return locationName; }

	public void setLocationName(String locationName) { this.locationName = locationName; }

	public double getLatitude() { return latitude; }

	public void setLatitude(double latitude) { this.latitude = latitude; }

	public double getLongitude() { return longitude; }

	public void setLongitude(double longitude) { this.longitude = longitude; }

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}
}
