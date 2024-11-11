package org.gradle.samples;

import org.apache.catalina.User;
import org.gradle.fins.model.UserModel;
import org.gradle.fins.repository.UserRepository;
import org.gradle.fins.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
class SpringBootDemoApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Test
	public void checkGetAllAPI() throws Exception {
		// test param
		String userId = "alfasal";

		// test mock
		UserModel mockUser = new UserModel();

		// test function
		Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(mockUser));

		// test result
		Optional<UserModel> findUser = userService.getUserById(userId);
		UserModel userModel = new UserModel();
		if (findUser.isPresent())
		{
			userModel = findUser.get();
		}

		// Assert
		assertNotNull(userModel);
		assertEquals(userId, userModel.getUserId());
		assertEquals("John Doe", userModel.getUserName());
	}
}
