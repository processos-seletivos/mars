package com.contaazul.mars;


import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.map.CartesianMap;
import com.contaazul.mars.orientation.NorthOrientation;
import com.contaazul.mars.planner.InvalidPositionException;
import com.contaazul.mars.planner.MarsRobotPlanner;
import com.contaazul.mars.planner.RobotAlreadyRegisteredException;
import com.contaazul.mars.planner.RobotNotRegisteredException;
import com.contaazul.mars.robot.MarsRobot;
import com.contaazul.mars.robot.command.Command;
import com.contaazul.mars.robot.command.InvalidCommandException;
import com.contaazul.mars.robot.command.MarsCommandParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarsRobotService {

    @Autowired
    MarsRobotPlanner marsRobotPlanner;

    /**
     * Método responsável por enviar os comandos para um determinado robô, que é definido pelo código que é passado
     * como parâmetro
     * @param commands String contendo a lista de comandos
     * @return A posição atual do robô após a execução dos comandos
     * @throws IllegalAccessException
     * @throws InvalidCommandException Exceção lançada quando existe um comando inválido
     * @throws InstantiationException
     * @throws InvalidPositionException Exceção lançada quando a sequência de comandos conduz um robô a uma posição
     * inválida.
     */
    public String sendCommands(String robotCode, String commands) throws IllegalAccessException, InvalidCommandException,
            InstantiationException, InvalidPositionException, RobotAlreadyRegisteredException,
            RobotNotRegisteredException, InvalidTransformationTypeException {
        List<Command> decodedCommands = new MarsCommandParser().decodeCommandString(commands);

        //Cria um robô a cada chamada, com coordenada (0,0,N) para atender o requisito de não manter estado.
        // Em uma situação real, não seriam necesários esses passos
        MarsRobot marsRobot1 = new MarsRobot(robotCode,
                new CartesianCoordinate(0l,0l),
                new NorthOrientation());
            marsRobotPlanner.manageRobot(marsRobot1);


        marsRobotPlanner.sendCommandListToRobot(marsRobot1.getCode(), decodedCommands);
        String currentPosition = marsRobot1.getCurrentPositonAndOrientation();

        // Remove o robô após executar os comandos
        marsRobotPlanner.removeRobot(marsRobot1);
        return currentPosition;
    }

}
