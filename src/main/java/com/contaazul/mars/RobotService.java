package com.contaazul.mars;

import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.planner.InvalidPositionException;
import com.contaazul.mars.planner.InvalidRobotException;
import com.contaazul.mars.planner.RobotAlreadyRegisteredException;
import com.contaazul.mars.planner.RobotNotRegisteredException;
import com.contaazul.mars.robot.command.InvalidCommandException;


public interface RobotService {
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
            RobotNotRegisteredException, InvalidTransformationTypeException, InvalidRobotException;
}
