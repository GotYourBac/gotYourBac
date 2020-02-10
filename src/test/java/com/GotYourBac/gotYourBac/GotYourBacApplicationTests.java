package com.GotYourBac.gotYourBac;

import com.GotYourBac.gotYourBac.controllers.UserController;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class GotYourBacApplicationTests {

	@Autowired
	WebApplicationContext webApplicationContext;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	UserController userController;

	@Before("")
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldHaveHomePage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<li>What is BAC???</li>")));
	}

	@Test
	public void shouldHaveRegistrationPage() throws Exception {
		this.mockMvc.perform(get("/registration"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<h2>Register</h2>")));
	}


	@Test
	public void shouldHaveLoginPage() throws Exception {
		this.mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<input type=\"text\" name=\"username\" onkeyup=\"this.value = this.value.toLowerCase();\">")));
	}

//	@Test
//	public void shouldHaveProfilePage() throws Exception {
//		this.mockMvc.perform(get("/profile"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().string(containsString("<h2>Users Profile</h2>")));
//	}

//	why zombie code?

//TODO: Example Post Test
//	@Test
//	public void givenGreetURIWithPostAndFormData_whenMockMVC_thenResponseOK() {
//		this.mockMvc.perform(post("/greetWithPostAndFormData").param("id", "1")
//				.param("name", "John Doe")).andDo(print()).andExpect(status().isOk())
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
//				.andExpect(jsonPath("$.message").value("Hello World John Doe!!!"))
//				.andExpect(jsonPath("$.id").value(1));
//	}


}
