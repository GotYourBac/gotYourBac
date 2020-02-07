package com.GotYourBac.gotYourBac;

import com.GotYourBac.gotYourBac.controllers.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class GotYourBacApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	UserController userController;

	@Test
	void contextLoads() {
	}

	//Test homepage rendering
	@Test
	public void shouldHaveHomePage() throws Exception {
		this.mockMvc.perform(get("/"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("GotYourBAC is an online calculator for Blood Alcohol Content.")));
	}

	@Test
	public void shouldHaveRegistrationPage() throws Exception {
		this.mockMvc.perform(get("/registration"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<h2>Register new user</h2>")));
	}


	@Test
	public void shouldHaveLoginPage() throws Exception {
		this.mockMvc.perform(get("/login"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("<h2>Login here:</h2>")));
	}

//	@Test
//	public void shouldHaveProfilePage() throws Exception {
//		this.mockMvc.perform(get("/profile"))
//				.andDo(print())
//				.andExpect(status().isOk())
//				.andExpect(content().string(containsString("<h2>Users Profile</h2>")));
//	}

//TODO: Example Post Test
//	@Test
//	public void givenGreetURIWithPostAndFormData_whenMockMVC_thenResponseOK() {
//		this.mockMvc.perform(post("/greetWithPostAndFormData").param("id", "1")
//				.param("name", "John Doe")).andDo(print()).andExpect(status().isOk())
//
//				.andExpect(content().contentType("application/json;charset=UTF-8"))
//				.andExpect(jsonPath("$.message").value("Hello World John Doe!!!"))
//				.andExpect(jsonPath("$.id").value(1));
//	}


}
