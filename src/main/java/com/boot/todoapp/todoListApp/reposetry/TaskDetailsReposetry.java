package com.boot.todoapp.todoListApp.reposetry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.boot.todoapp.todoListApp.model.TaskDetails;

public interface TaskDetailsReposetry extends JpaRepository<TaskDetails, Integer>{
	// Repository interface should extends JPA repository and have to mention with entity it is dealing and its primary key type


	@Query("SELECT t FROM TaskDetails t where t.status = ?1")
    public Optional<List<TaskDetails>> findByTaskStatus(String status);
	
	@Query("SELECT t FROM TaskDetails t where t.updateBy = ?1")
    public Optional<List<TaskDetails>> findByUpdatedBy(String updateBy);
}
