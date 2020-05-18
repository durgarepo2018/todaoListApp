package com.boot.todoapp.todoListApp.service;

import org.springframework.http.MediaType;
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
	
	
  
}



