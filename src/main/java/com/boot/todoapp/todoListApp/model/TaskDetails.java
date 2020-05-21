package com.boot.todoapp.todoListApp.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TaskDetails {

	@Id
	@GeneratedValue
	private Integer taskId;
	
	private String createdBy;
	private String taskName;
	private Date createdDate;
	private Date updatedDate;
	private String status;
	private String updateBy;
	private String taskDescription;

	
	public TaskDetails() {
		
	}
	
	
	// To Create New Task
	public TaskDetails(String createdBy, String taskName, Date createdDate, String status,
			String taskDescription) {
		super();
		this.createdBy = createdBy;
		this.taskName = taskName;
		this.createdDate = createdDate;
		this.status = status;
		this.taskDescription = taskDescription;
	}

	public TaskDetails(String createdBy, String taskName,
			String taskDescription) {
		super();
		this.createdBy = createdBy;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
	}
    // To Update Task 
	public TaskDetails(String updateBy, String taskName, String status,
			 String taskDescription) {
		super();
		this.updateBy = updateBy;
		this.status = status;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
	}


	public Integer getTaskId() {
		return taskId;
	}
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}




	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	
	
	
	
}
