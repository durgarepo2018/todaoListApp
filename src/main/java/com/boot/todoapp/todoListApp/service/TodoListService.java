package com.boot.todoapp.todoListApp.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.todoapp.todoListApp.exception.InvalidInputException;
import com.boot.todoapp.todoListApp.exception.TaskNotFoundException;
import com.boot.todoapp.todoListApp.model.TaskDetails;
import com.boot.todoapp.todoListApp.model.TodoListDataModel;
import com.boot.todoapp.todoListApp.reposetry.TaskDetailsReposetry;
import com.boot.todoapp.todoListApp.util.ToDoListAppUtil.TaskStatus;



@RestController

public class TodoListService {

	
	@Autowired
	TaskDetailsReposetry taskDetailsReposetry;
	
	@GetMapping("/hello")
	public String sayHello() {
		return  "Hello";
	}
	
	@GetMapping("/helloJson")
	public TodoListDataModel sayHelloInJason() {
		return  new TodoListDataModel("Hello In JSON");
	}
	
	@GetMapping("/todolist/login")
	public TodoListDataModel securedLogin() {
		return  new TodoListDataModel("Login Sucess");
	}
	
	@GetMapping("/todoList/visitorLogin")
	public TodoListDataModel fetchTodoListByVisitorName() {
		return  new TodoListDataModel("Visitor Login Success");
	}
	
	@PostMapping("/todoList/addTask")
	public ResponseEntity<Object> addTask(@RequestBody TaskDetails taskDetails) {
		
		taskDetails.setStatus("OPEN");
		taskDetails.setCreatedDate(new Date());
		TaskDetails savedTaskDetails = taskDetailsReposetry.save(taskDetails);// service.saveUser(user);
		
		// This is to return the Status as Created and status code as 201 
		URI location = null;
		try {
			location = new URI("localhost:9002/fetchTaskList/"+savedTaskDetails.getTaskId());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/todoList/updateTask/{taskId}")
	public TodoListDataModel updateTask(@PathVariable int taskId, @RequestBody TaskDetails taskDetails) {
		
		Optional<TaskDetails> taskToBeUpdated = taskDetailsReposetry.findById(taskId) ;
		System.out.println("----------- Task to be updated "+ taskToBeUpdated.isPresent());
		if(!taskToBeUpdated.isPresent()) {
			throw new TaskNotFoundException(" Task Details Not found with Task ID : "+ taskId);
		} else {
			//if(taskDetails.getStatus() != null ) {
				boolean isValidStatus = false;
				
				for (TaskStatus taskStatus : TaskStatus.values()) { 
					
					if(taskStatus.toString().equalsIgnoreCase(taskDetails.getStatus() )) {
						isValidStatus = true;
						break;	
					} 
				}
				System.out.println(" ------ taskDetails.getStatus()  "+taskDetails +"----isValidStatus-------"+ isValidStatus);
				if(isValidStatus) {
					
					TaskDetails taskDetailsToBeUpdated = taskToBeUpdated.get();

					taskDetailsToBeUpdated.setUpdatedDate(new Date());
					taskDetailsToBeUpdated.setUpdateBy(taskDetails.getUpdateBy());
					taskDetailsToBeUpdated.setStatus(taskDetails.getStatus());
					taskDetailsToBeUpdated.setTaskDescription(taskDetails.getTaskDescription());
					taskDetailsToBeUpdated.setTaskName(taskDetails.getTaskName());
					TaskDetails updatedTaskDetails = taskDetailsReposetry.save(taskDetailsToBeUpdated);// service.saveUser(user);
					
					
				} else {
					StringBuffer statusValue = new StringBuffer("");
					for (TaskStatus validStatus : TaskStatus.values()) { 
						statusValue.append(validStatus.toString() + ":");
					}
					throw new InvalidInputException(" Invalid Status Value, Valid Status values are  : "+ statusValue.toString());
				}
				

			//}
		}
		
		return  new TodoListDataModel("Task Details Updated Successfully.");
	}
	
	@GetMapping("/todoList/deleteTask")
	public TodoListDataModel deleteTask() {
		return  new TodoListDataModel("deleteTask");
	}
	
	@GetMapping("/todoList/fetchTaskList")
	public List<TaskDetails> fetchTaskList() {
		
		
		return taskDetailsReposetry.findAll();
	}
	
	@GetMapping("/todoList/findTaskBystatus/{status}")
	public List<TaskDetails> fetchTaskListByItsStatus(@PathVariable String status) {
		System.out.println(" -----status---------------- "+ status);
		
		boolean isValidStatus = false;
		
		if( status != null) {
			for (TaskStatus taskStatus : TaskStatus.values()) { 
				
				if(taskStatus.toString().equalsIgnoreCase(status)) {
					isValidStatus = true;
					break;	
				} 
			}
		}
		
		if(isValidStatus) {
			Optional<List<TaskDetails>>  taskListOption = 
					taskDetailsReposetry.findByTaskStatus(status.toUpperCase());
			System.out.println(" -----taskListOption---------------- "+ taskListOption);
			if(taskListOption.isPresent()) {
				System.out.println(" -----taskListOption.get()---------------- "+ taskListOption.get());
				return taskListOption.get();
			}  else {
				return null;
			}
		} else {
			StringBuffer statusValue = new StringBuffer("");
			for (TaskStatus validStatus : TaskStatus.values()) { 
				statusValue.append(validStatus.toString() + ":");
			}
			throw new InvalidInputException(" Invalid Status Value, Valid Status values are  : "+ statusValue.toString());
		}
		
		
		//return null;
	}
	
	
	@GetMapping("/todoList/findTaskByUpdateBy/{updteBy}")
	public List<TaskDetails> fetchTaskListUpdateBy(@PathVariable String updteBy) {
		System.out.println(" -----updteBy---------------- "+ updteBy);
		
		Optional<List<TaskDetails>>  taskListOption = 
				taskDetailsReposetry.findByUpdatedBy(updteBy);
		System.out.println(" -----taskListOption---------------- "+ taskListOption);
		if(taskListOption.isPresent()) {
			System.out.println(" -----taskListOption.get()---------------- "+ taskListOption.get());
			return taskListOption.get();
		}  else {
			return null;
		}
		
		//return null;
	}
	
	@GetMapping("/todoList/readTaskDetails")
	public TodoListDataModel fetchTaskByTaskId() {
		return  new TodoListDataModel("fetchTaskListByItsStatus");
	}
}



