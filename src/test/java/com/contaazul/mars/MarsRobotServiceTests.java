package com.contaazul.mars;

import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.orientation.NorthOrientation;
import com.contaazul.mars.orientation.SouthOrientation;
import com.contaazul.mars.planner.*;
import com.contaazul.mars.robot.MarsRobot;
import com.contaazul.mars.robot.command.Command;
import com.contaazul.mars.robot.command.InvalidCommandException;
import com.contaazul.mars.robot.command.LeftCommand;
import com.contaazul.mars.robot.command.MarchCommand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MarsRobotServiceTests {

	@Test
	public void sendValidCommandsToExistentRobot() throws RobotAlreadyRegisteredException,
			InvalidRobotException, IllegalAccessException, InvalidTransformationTypeException,
			RobotNotRegisteredException, InvalidCommandException, InstantiationException,
			InvalidPositionException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		MarsRobotService marsRobotService = new MarsRobotService(marsRobotPlanner);

		//when Envia comandos para um robô
		String retorno = marsRobotService.sendCommands("robot1", "ML");

		//then O retorno é igual o esperado
		assertThat(retorno).isEqualTo("(0, 1, W)");
	}

	@Test(expected = InvalidCommandException.class)
	public void sendInvalidCommandsToExistentRobot() throws RobotAlreadyRegisteredException,
			InvalidRobotException, IllegalAccessException, InvalidTransformationTypeException,
			RobotNotRegisteredException, InvalidCommandException, InstantiationException,
			InvalidPositionException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		MarsRobotService marsRobotService = new MarsRobotService(marsRobotPlanner);

		//when Envia comandos para um robô
		String retorno = marsRobotService.sendCommands("robot1", "A");

		//then É lançada uma exceção por ter comando inválido. A asserção serve para identificar que houve erro no teste.
		assertThat(retorno).isEqualTo("(11, 11, 11)");
	}

	@Test(expected = InvalidPositionException.class)
	public void sendInvalidPositionToExistentRobot() throws RobotAlreadyRegisteredException,
			InvalidRobotException, IllegalAccessException, InvalidTransformationTypeException,
			RobotNotRegisteredException, InvalidCommandException, InstantiationException,
			InvalidPositionException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		MarsRobotService marsRobotService = new MarsRobotService(marsRobotPlanner);

		//when Envia comandos para um robô
		String retorno = marsRobotService.sendCommands("robot1", "RRM");

		//then É lançada uma exceção por ter comando inválido. A asserção serve para identificar que houve erro no teste.
		assertThat(retorno).isEqualTo("(11, 11, 11)");
	}





}