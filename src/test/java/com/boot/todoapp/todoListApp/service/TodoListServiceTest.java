package com.boot.todoapp.todoListApp.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
	
	
//	protected void checkNamedParam(final String param) throws Exception {
//		String output = (StringUtils.isNullOrEmpty(param)) ? "World" : param;
//		this.mockMvc.perform(get("/hello/" + (null == param ? "" : param))).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().string(containsString("Hello " + output + "!")));
//	}
	
	
}
