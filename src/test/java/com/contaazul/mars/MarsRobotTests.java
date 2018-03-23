package com.contaazul.mars;

import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.orientation.NorthOrientation;
import com.contaazul.mars.orientation.SouthOrientation;
import com.contaazul.mars.robot.MarsRobot;
import com.contaazul.mars.robot.command.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarsRobotTests {

	@Test
	public void getCurrentPositionAndOrientation() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsRobot marsRobot = new MarsRobot("robot1", new CartesianCoordinate(0l, 0l), new SouthOrientation());
		//when
		String currentPositionAndOrientation = marsRobot.getCurrentPositonAndOrientation();
		//then
		assertThat(currentPositionAndOrientation).isEqualTo("(0, 0, S)");

	}

	@Test
	public void executeLeftCommand() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsRobot marsRobot = new MarsRobot("robot1", new CartesianCoordinate(0l, 0l), new SouthOrientation());
		//when
		marsRobot.executeCommand(new LeftCommand());
		String currentPositionAndOrientation = marsRobot.getCurrentPositonAndOrientation();
		//then
		assertThat(currentPositionAndOrientation).isEqualTo("(0, 0, E)");

	}

	@Test
	public void executeRigthCommand() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsRobot marsRobot = new MarsRobot("robot1", new CartesianCoordinate(0l, 0l), new SouthOrientation());
		//when
		marsRobot.executeCommand(new RightCommand());
		String currentPositionAndOrientation = marsRobot.getCurrentPositonAndOrientation();
		//then
		assertThat(currentPositionAndOrientation).isEqualTo("(0, 0, W)");

	}

	@Test
	public void executeMarchCommand() throws IllegalAccessException, InvalidCommandException, InstantiationException {
		//given
		MarsRobot marsRobot = new MarsRobot("robot1", new CartesianCoordinate(0l, 0l), new NorthOrientation());
		//when
		marsRobot.executeCommand(new MarchCommand());
		String currentPositionAndOrientation = marsRobot.getCurrentPositonAndOrientation();
		//then
		assertThat(currentPositionAndOrientation).isEqualTo("(0, 1, N)");

	}
}