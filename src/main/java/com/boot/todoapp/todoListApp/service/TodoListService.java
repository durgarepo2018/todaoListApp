package com.boot.todoapp.todoListApp.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boot.todoapp.todoListApp.model.TodoListDataModel;



@RestController

public class TodoListService {

	
	
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
	
	@GetMapping("/todoList/addTask")
	public TodoListDataModel addTask() {
		return  new TodoListDataModel("addTask");
	}
	
	@GetMapping("/todoList/updateTask")
	public TodoListDataModel updateTask() {
		return  new TodoListDataModel("updateTask");
	}
	
	@GetMapping("/todoList/deleteTask")
	public TodoListDataModel deleteTask() {
		return  new TodoListDataModel("deleteTask");
	}
	
	@GetMapping("/todoList/fetchTaskList")
	public TodoListDataModel fetchTaskListByItsStatus() {
		return  new TodoListDataModel("fetchTaskListByItsStatus");
	}
	
	@GetMapping("/todoList/readTaskDetails")
	public TodoListDataModel fetchTaskByTaskId() {
		return  new TodoListDataModel("fetchTaskListByItsStatus");
	}
}



