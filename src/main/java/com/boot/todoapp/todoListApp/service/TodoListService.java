package com.boot.todoapp.todoListApp.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoListService {

	
	
	@GetMapping(path="/hello")
	public String userLogin() {
		return "Hello";
		
	}
}
