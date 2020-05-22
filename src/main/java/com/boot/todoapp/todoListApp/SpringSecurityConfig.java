package com.boot.todoapp.todoListApp;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // Create 2 users for demo
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    	
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("visitor").password("{noop}password").roles("VISITOR")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "VISITOR", "ADMIN")
                ;

    }

    // Secure the endpoins with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	 // add this line to use H2 web console
        http.headers().frameOptions().disable();
        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/todolist/login").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/todoList/addTask").hasRole("USER")
                .antMatchers(HttpMethod.PUT, "/todoList/updateTask/{taskId}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/todoList/deleteTask/{taskId").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/todoList/fetchTaskList").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/todoList/findTaskBystatus/{status}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/todoList/findTaskByUpdateBy/{updteBy}").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/todoList/readTaskDetails").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/todoList/visitorLogin").hasRole("VISITOR")
                
                //.antMatchers(HttpMethod.GET, "/redTodoList/**").hasRole("ADMIN")
               // .antMatchers(HttpMethod.POST, "/books").hasRole("c")
               // .antMatchers(HttpMethod.PUT, "/books/**").hasRole("ADMIN")
               // .antMatchers(HttpMethod.PATCH, "/books/**").hasRole("ADMIN")
               // .antMatchers(HttpMethod.DELETE, "/books/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

   
}