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
public class MarsRobotPlannerTests {

	public void manageValidRobot() throws RobotAlreadyRegisteredException,
			InvalidRobotException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		MarsRobot marsRobot = new MarsRobot(
				"robot1", new CartesianCoordinate(0L,0L), new NorthOrientation()
		);

		//when Registra um robô existente
		Boolean retorno = marsRobotPlanner.manageRobot(marsRobot);

		//then O retorno é verdadeiro
		assertThat(retorno).isTrue();
	}

	@Test(expected = RobotAlreadyRegisteredException.class)
	public void manageAlreadyManagedRobot() throws IllegalAccessException,
			InvalidCommandException, InstantiationException, RobotNotRegisteredException,
			InvalidTransformationTypeException, InvalidPositionException, RobotAlreadyRegisteredException,
			InvalidRobotException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		MarsRobot marsRobot = new MarsRobot(
				"robot1", new CartesianCoordinate(0L,0L), new NorthOrientation()
		);
		marsRobotPlanner.manageRobot(marsRobot);

		//when Registra um robô já gerenciado
		Boolean retorno = marsRobotPlanner.manageRobot(marsRobot);


		//then Exceção é lançada por o robô já estar registrado. Se não for, irá falhar o teste, indicando que existe
		// erro na lógica
		assertThat(retorno).isFalse();
	}

	@Test(expected = InvalidRobotException.class)
	public void manageNullRobot() throws IllegalAccessException, InvalidCommandException, InstantiationException,
			RobotNotRegisteredException, InvalidTransformationTypeException, InvalidPositionException,
			RobotAlreadyRegisteredException, InvalidRobotException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();

		//when Registra um robô nulo
		Boolean retorno =  marsRobotPlanner.manageRobot(null);

		//then Uma exceção é lançada pelo tipo de robô não ser válido. Se não for, irá falhar o teste, indicando que
		// existe erro na lógica
		assertThat(retorno).isFalse();
	}

	@Test(expected = RobotNotRegisteredException.class)
	public void removeNotManagedRobot() throws RobotNotRegisteredException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		MarsRobot marsRobot = new MarsRobot(
				"robot1", new CartesianCoordinate(0L,0L), new NorthOrientation()
		);

		//when Remove robô não gerenciado
		Boolean retorno =  marsRobotPlanner.removeRobot(marsRobot);

		//then Uma exceção é lançada pelo robô não ser gerenciado. Se não for, irá falhar o teste, indicando que
		//	existe erro na lógica
		assertThat(retorno).isFalse();
	}

	public void removeManagedRobot() throws RobotNotRegisteredException, InvalidRobotException,
			RobotAlreadyRegisteredException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		MarsRobot marsRobot = new MarsRobot(
				"robot1", new CartesianCoordinate(0L,0L), new NorthOrientation()
		);
		marsRobotPlanner.manageRobot(marsRobot);

		//when Remove robô não gerenciado
		Boolean retorno =  marsRobotPlanner.removeRobot(marsRobot);

		//then O retorno deve ser verdadeiro
		assertThat(retorno).isTrue();
	}

	@Test(expected = RobotNotRegisteredException.class)
	public void sendCommandToNotRegisteredRobot() throws IllegalAccessException,
			InvalidCommandException, InstantiationException, RobotNotRegisteredException,
			InvalidTransformationTypeException, InvalidPositionException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		//when Manda o comando para um robô inexistente, gera exceção
		List<Command> commands = Arrays.asList(new LeftCommand());
		marsRobotPlanner.sendCommandListToRobot("robot1", commands);

		//then Lança exceção pelo robô não estar registrado
	}

	@Test
	public void sendCommandToRegisteredRobot() throws IllegalAccessException,
			InvalidCommandException, InstantiationException, RobotNotRegisteredException,
			InvalidTransformationTypeException, InvalidPositionException, RobotAlreadyRegisteredException,
			InvalidRobotException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		MarsRobot marsRobot = new MarsRobot(
				"robot1", new CartesianCoordinate(0L,0L), new NorthOrientation()
		);
		marsRobotPlanner.manageRobot(marsRobot);
		//when Manda o comando para um robô inexistente, gera exceção
		List<Command> commands = Arrays.asList(new LeftCommand());
		marsRobotPlanner.sendCommandListToRobot("robot1", commands);

		//then A posição do robô é atualizada de acordo com o comando
		assertThat(marsRobot.getCurrentPositonAndOrientation()).isEqualTo("(0, 0, W)");
	}

	@Test(expected = InvalidPositionException.class)
	public void sendCommandToRegisteredRobotInsecurePath() throws IllegalAccessException,
			InvalidCommandException, InstantiationException, RobotNotRegisteredException,
			InvalidTransformationTypeException, InvalidPositionException, RobotAlreadyRegisteredException,
			InvalidRobotException {
		//given
		MarsRobotPlanner marsRobotPlanner = new MarsRobotPlanner();
		marsRobotPlanner.manageRobot(
				new MarsRobot("robot1", new CartesianCoordinate(0L,0L), new SouthOrientation())
		);
		//when Manda o comando para um robô inexistente, gera exceção
		List<Command> commands = Arrays.asList(new MarchCommand());
		marsRobotPlanner.sendCommandListToRobot("robot1", commands);

		//then Lança exceção por ser posição inválida
	}




}