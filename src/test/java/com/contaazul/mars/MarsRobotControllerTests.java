package com.contaazul.mars;

import com.contaazul.mars.robot.command.InvalidCommandException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MarsRobotController.class)
public class MarsRobotControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MarsRobotService marsRobotService;

	@Test
	public void sendNullCommand() throws Exception {

		given(marsRobotService.sendCommands("robot1", null)).willReturn("(0, 0, N)");


		mockMvc.perform(post("/rest/mars/")
		)
				.andExpect(status().isOk())
				.andExpect(content().string("(0, 0, N)"));
	}


	@Test
	public void sendValidSequence() throws Exception {

		given(marsRobotService.sendCommands("robot1", "MM")).willReturn("(0, 2, N)");


		mockMvc.perform(post("/rest/mars/MM")
		)
				.andExpect(status().isOk())
				.andExpect(content().string("(0, 2, N)"));
	}

	@Test
	public void sendInvalidSequence() throws Exception {

		given(marsRobotService.sendCommands("robot1", "A")).willThrow(InvalidCommandException.class);


		mockMvc.perform(post("/rest/mars/A")
		)
				.andExpect(status().isBadRequest())
				.andExpect(content().string(""));
	}






}