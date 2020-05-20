package com.boot.todoapp.todoListApp.reposetry;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.todoapp.todoListApp.model.TaskDetails;

public interface TaskDetailsReposetry extends JpaRepository<TaskDetails, Integer>{
	// Repository interface should extends JPA repository and have to mention with entity it is dealing and its primary key type
}
