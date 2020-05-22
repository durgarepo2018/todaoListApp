package com.boot.todoapp.todoListApp.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.boot.todoapp.todoListApp.model.TaskDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TodoListServiceTest {

	@Autowired
	private MockMvc mockMvc ;
	
	


	@Test
	public void testInvalidReqUrl() throws Exception {
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/invalidUrl")
			).andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	@Test
	public void testHelloMessage() throws Exception {
		
 
		mockMvc.perform(get("/hello")).andExpect(status().isOk())
			 .andExpect(content().string("Hello"));
		 
		// mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
		//.andExpect(content().string(containsString("Hello")));
	}
	
	@Test
    public void validateSimpleJsonRequest() throws Exception {
        mockMvc.perform(get("/helloJson")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Hello In JSON")));
              //  .andExpect(jsonPath("$.value", Matchers.is("Hello World")))
               // .andExpect(jsonPath("$.*", Matchers.hasSize(2)));
    }
	
	@Test
	public void validateSecuriedSvcWithInvalidCredentials() throws Exception {
		
        mockMvc.perform(get("/todolist/login")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
                
	}
	@Test
	public void validateSecuriedSvcWithInValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin111", "password");
        mockMvc.perform(get("/todolist/login")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               
                
	}
	
	@Test
	public void validateSecuriedSvcWithValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
        mockMvc.perform(get("/todolist/login")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Login Sucess")));
                
	}
	
	@Test
	public void validateSecuriedSvcWithValidUserCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(get("/todolist/login")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Login Sucess")));
                
	}
	
	
	@Test
	public void validateSecuriedVisitorSvcWithInvalidCredentials() throws Exception {
		
        mockMvc.perform(get("/todoList/visitorLogin")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
                
	}
	
	
	@Test
	public void validateSecuriedVisitorSvcWithInValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin222", "password");
        mockMvc.perform(get("/todoList/visitorLogin")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	@Test
	public void validateSecuriedVisitorSvcWithValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
        mockMvc.perform(get("/todoList/visitorLogin")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	@Test
	public void validateSecuriedSvcWithValidVisitorCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("visitor", "password");
        mockMvc.perform(get("/todoList/visitorLogin")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	
	
	@Test
	public void validateSecuriedAddTaskSvcWithInvalidCredentials() throws Exception {
		
        mockMvc.perform(post("/todoList/addTask")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
                
	}
	
	
	@Test
	public void validateSecuriedAddTaskSvcWithInValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin222", "password");
        mockMvc.perform(post("/todoList/addTask")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	@Test
	public void validateSecuriedAddTaskSvcWithValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
	
		TaskDetails user = new TaskDetails("Test Script", "TestTask1", " Junit Testing Task");
	
        mockMvc.perform(post("/todoList/addTask")
               .accept(MediaType.APPLICATION_JSON)
               .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user))
               .headers(httpHeaders))
               .andExpect(status().isCreated());
                
	}
	
	@Test
	public void validateAddTaskSvcWithBadInput() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
	
		//TaskDetails user = new TaskDetails("Test Script", "TestTask1", " Junit Testing Task");
	
        mockMvc.perform(post("/todoList/addTask")
               .accept(MediaType.APPLICATION_JSON)
               .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString("Just a String "))
               .headers(httpHeaders))
               .andExpect(status().isBadRequest());
                
	}
	
	@Test
	public void validateSecuriedAddTaskSvcWithValidVisitorCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("visitor", "password");
        mockMvc.perform(post("/todoList/addTask")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isForbidden());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	
	 private final ObjectMapper mapper = new ObjectMapper();
	 
	@Test
	public void validateSecuriedAddTaskSvcWithValidUserCredentials() throws Exception {
		
		 TaskDetails user = new TaskDetails("Test Script", "TestTask1", " Junit Testing Task");

		 
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(post("/todoList/addTask")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user))
                .headers(httpHeaders))
                .andExpect(status().isCreated());
              //  .andExpect(jsonPath("$.title", Matchers.is("addTask")));
                
	}
	
	
	@Test
	public void validateSecuriedUpdateTaskSvcWithInvalidURL() throws Exception {
		
        mockMvc.perform(get("/todoList/updateTask")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
                
	}
	

	@Test
	public void validateSecuriedUpdateTaskSvcWithInvalidCredentials() throws Exception {
		
        mockMvc.perform(put("/todoList/updateTask/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
                
	}
	
	@Test
	public void validateSecuriedUpdateTaskSvcWithInValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin222", "password");
        mockMvc.perform(put("/todoList/updateTask")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	@Test
	public void validateSecuriedUpdateTaskSvcWithValidAdminCredentials() throws Exception {
		
		TaskDetails taskDetails = new TaskDetails("Test Script", "TestTask1", " Junit Testing Task");

		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
        mockMvc.perform(put("/todoList/updateTask/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(taskDetails))
                .headers(httpHeaders))
                .andExpect(status().isBadRequest());
              //  .andExpect(jsonPath("$.title", Matchers.is("updateTask")));
                
	}
	
	@Test
	public void validateSecuriedUpdateTaskSvcWithValidVisitorCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("visitor", "password");
        mockMvc.perform(put("/todoList/updateTask/3")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isForbidden());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	

	@Test
	public void validateSecuriedUpdateTaskSvcWithValidUserAndBadReqData() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(put("/todoList/updateTask/1")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isBadRequest());
               // .andExpect(jsonPath("$.title", Matchers.is("updateTask")));
                
	}

	@Test
	public void validateUpdateTaskSvcWithInValidRquestData() throws Exception {
		
		// No Status Value 
		 TaskDetails user = new TaskDetails("Durga", "May 21th Home Work- Updated", " May 21th  Home Work Updated ");

		 
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(put("/todoList/updateTask/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user))
                .headers(httpHeaders))
                .andExpect(status().isBadRequest());
               // .andExpect(jsonPath("$.title", Matchers.is("updateTask")));
                
	}
	
	@Test
	public void testUpdateTaskWithInValidStatus() throws Exception {
		
		// No Status Value 
		 TaskDetails user = new TaskDetails("Durga","CLOSEDDSDS", "May 21th Home Work- Updated", " May 21th  Home Work Updated ");

		 
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(put("/todoList/updateTask/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user))
                .headers(httpHeaders))
                .andExpect(status().isBadRequest());
               // .andExpect(jsonPath("$.title", Matchers.is("updateTask")));
                
	}
	
	@Test
	public void testUpdateTaskWithValidStatus() throws Exception {
		
		// No Status Value 
		 TaskDetails user = new TaskDetails("Durga", "May 21th Home Work- Updated 111", "PENDING", "111 May 21th  Home Work Updated ");

		 
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(put("/todoList/updateTask/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(user))
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("Task Details Updated Successfully.")));
                
	}
	@Test
	public void validateSecuriedDeleteTaskSvcWithInvalidCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user4343", "password434");
        mockMvc.perform(delete("/todoList/deleteTask/1")
        		 .headers(httpHeaders)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
                
	}
	
	
	@Test
	public void validateSecuriedDeleteTaskSvcWithInValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin222", "password");
        mockMvc.perform(get("/todoList/deleteTask")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	@Test
	public void validateSecuriedDeleteTaskSvcWithValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
        mockMvc.perform(delete("/todoList/deleteTask/2")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.title", Matchers.is("deleteTask")));
                
	}
	
	@Test
	public void validateSecuriedDeleteTaskSvcWithValidVisitorCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("visitor", "password");
        mockMvc.perform(get("/todoList/deleteTask")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isNotFound());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	@Test
	public void validateDeleteTaskSvcWithValidVisitorCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user21212", "password");
        mockMvc.perform(delete("/todoList/deleteTask/1")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}

	@Test
	public void validateSecuriedDeleteTaskSvcWithValidUserCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(delete("/todoList/deleteTask/1")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.title", Matchers.is("deleteTask")));
                
	}
	
	
	@Test
	public void validateFetchTaskByStatus() throws Exception {
		
        mockMvc.perform(get("/todoList/findTaskBystatus/open")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
                
	}
	
	
	@Test
	public void validateFetchTaskByStatusInValidCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin222", "password");
        mockMvc.perform(get("/todoList/findTaskBystatus/HOLD")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	@Test
	public void validateFetchTaskByStatusWithValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
        mockMvc.perform(get("/todoList/findTaskBystatus/CLOSED")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	
	@Test
	public void validateSecuriedFetchTaskByStatusSvcWithValidVisitorCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("visitor", "password");
        mockMvc.perform(get("/todoList/findTaskBystatus/CLOSED")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isForbidden());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	

	@Test
	public void validateFetchTaskByStatusWithValidUserCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(get("/todoList/findTaskBystatus/HOLD")
                .headers(httpHeaders))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	

	@Test
	public void validateFetchTaskByInvalidStatus() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(get("/todoList/findTaskBystatus/HOLD3232")
                .headers(httpHeaders))
                .andExpect(status().isBadRequest());
               // .andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	
	@Test
	public void validateFetchTaskByOPENStatus() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(get("/todoList/findTaskBystatus/OPEN")
                .headers(httpHeaders))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	
	@Test
	public void validateFetchTaskByHOLDStatus() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(get("/todoList/findTaskBystatus/hold")
                .headers(httpHeaders))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	
	@Test
	public void validateFetchTaskByDELETEStatus() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(get("/todoList/findTaskBystatus/DeleTe")
                .headers(httpHeaders))
                .andExpect(status().isBadRequest());
               // .andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	
	@Test
	public void validateFetchTaskByPendingStatus() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(get("/todoList/findTaskBystatus/PENDING")
                .headers(httpHeaders))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	
	@Test
	public void validateSecuriedFetchTaskListSvcWithInvalidCredentials() throws Exception {
		
        mockMvc.perform(get("/todoList/fetchTaskList")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
                
	}
	
	
	@Test
	public void validateSecuriedFetchTaskListSvcWithInValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin222", "password");
        mockMvc.perform(get("/todoList/fetchTaskList")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	@Test
	public void validateSecuriedFetchTaskListSvcWithValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
        mockMvc.perform(get("/todoList/fetchTaskList")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	
	@Test
	public void validateSecuriedFetchTaskListSvcWithValidVisitorCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("visitor", "password");
        mockMvc.perform(get("/todoList/fetchTaskList")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isForbidden());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	

	@Test
	public void validateSecuriedFetchTaskListSvcWithValidUserCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(get("/todoList/fetchTaskList")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk());
               // .andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}

	
	
	@Test
	public void validatefindTaskByUpdateBy() throws Exception {
		
        mockMvc.perform(get("/todoList/findTaskByUpdateBy/durga")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
                
	}
	
	
	@Test
	public void validatefindTaskByUpdateByInValidUrl() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin222", "password");
        mockMvc.perform(get("/todoList/findTaskByUpdateBy")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	@Test
	public void validatefindTaskByUpdateByInValidCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin222", "password");
        mockMvc.perform(get("/todoList/findTaskByUpdateBy/xxx")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	@Test
	public void validatefindTaskByUpdateByWithValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
        mockMvc.perform(get("/todoList/findTaskByUpdateBy/durga")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}

	
	
	@Test
	public void validatefindTaskByUpdateBySvcWithValidVisitorCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("visitor", "password");
        mockMvc.perform(get("/todoList/findTaskByUpdateBy/SOMEUSER")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isForbidden());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	



	

	@Test
	public void validateSecuriedreadTaskDetailsSvcWithInvalidCredentials() throws Exception {
		
        mockMvc.perform(get("/todoList/readTaskDetails")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
                
	}
	
	
	@Test
	public void validateSecuriedreadTaskDetailsSvcWithInValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin222", "password");
        mockMvc.perform(get("/todoList/readTaskDetails")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isUnauthorized());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	@Test
	public void validateSecuriedreadTaskDetailsListSvcWithValidAdminCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("admin", "password");
        mockMvc.perform(get("/todoList/readTaskDetails")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	
	@Test
	public void validateSecuriedreadTaskDetailsListSvcWithValidVisitorCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("visitor", "password");
        mockMvc.perform(get("/todoList/readTaskDetails")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isForbidden());
               // .andExpect(jsonPath("$.title", Matchers.is("Visitor Login Success")));
                
	}
	
	

	@Test
	public void validateSecuriedreadTaskDetailsListSvcWithValidUserCredentials() throws Exception {
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setBasicAuth("user", "password");
        mockMvc.perform(get("/todoList/readTaskDetails")
                .accept(MediaType.APPLICATION_JSON)
                .headers(httpHeaders))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", Matchers.is("fetchTaskListByItsStatus")));
                
	}
	
	
//	protected void checkNamedParam(final String param) throws Exception {
//		String output = (StringUtils.isNullOrEmpty(param)) ? "World" : param;
//		this.mockMvc.perform(get("/hello/" + (null == param ? "" : param))).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Hello " + output + "!")));
//	}
	
	
}
