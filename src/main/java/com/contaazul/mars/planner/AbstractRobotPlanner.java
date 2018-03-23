package com.contaazul.mars.planner;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.map.Map;
import com.contaazul.mars.robot.Robot;
import com.contaazul.mars.robot.command.Command;

import java.util.HashMap;
import java.util.List;

/**
 * Classe abstrata com os atributos e métodos referentes aos robot planners
 */
public abstract class AbstractRobotPlanner implements RobotPlanner{
    HashMap<String, Robot> robots = new HashMap<>();
    Map map;
    AbstractRobotPlanner(Map map){
        this.map = map;
    }

    /**
     * Faz com que o planner passe a gerenciar um determinado robô
     * @param robot Robô que o planner deve gerenciar
     * @throws RobotAlreadyRegisteredException Exceção lançada caso um robô com o mesmo código já esteja sendo gerenciado
     */
    public Boolean manageRobot(Robot robot) throws RobotAlreadyRegisteredException, InvalidRobotException {
        if (robot == null) {
            throw new InvalidRobotException("This robot is not valid");
        }
        if (robots.containsKey(robot.getCode())){
            throw new RobotAlreadyRegisteredException("A robot with this code has already been registered");
        }
        robots.put(robot.getCode(), robot);
        return true;
    }

    /**
     *
     * @param code
     * @param decodedCommands
     * @throws InvalidPositionException
     * @throws InvalidTransformationTypeException
     */
    public void sendCommandListToRobot(String code, List<Command> decodedCommands) throws InvalidPositionException, InvalidTransformationTypeException, RobotNotRegisteredException {
        Robot robot = robots.get(code);
        decodedCommands.stream().forEach((Command command) -> robot.executeCommand(command));

    }

    public Boolean validPosition(Coordinate coordinate, Robot robot) {
        Boolean isValid = false;
        if (map.validPosition(coordinate) && isAvailablePosition(coordinate, robot)){
            isValid = true;
        }
        return isValid;
    }

    /**
     * Verifica se uma posição está disponível, ou seja, se não está ocupada por outro robô gerenciado
     * @param coordinate Coordenada para ser verificada
     * @param robot Robô
     * @return True se a posição estiver disponível ou false caso contrário
     */
    Boolean isAvailablePosition(Coordinate coordinate, Robot robot) {
        Boolean isAvailable = true;
        for (Robot aRobot : robots.values()){
            if (!(robot.getCode().equals(aRobot.getCode())) && aRobot.getCoordinate().equals(coordinate)){
                isAvailable = false;
                break;
            }
        }
        return isAvailable;
    }

    /**
     * Para de gerenciar um determinado robô
     * @param robot Robô que não será mais gerenciado
     * @return True se o robô parou de ser gerenciado, false caso contrário
     * @throws RobotNotRegisteredException Lança exceção indicando que o robô não está registrado
     */
    public Boolean removeRobot(Robot robot) throws RobotNotRegisteredException {
        if (!robots.containsKey(robot.getCode())){
            throw new RobotNotRegisteredException("A robot with this code is not registered");
        }
        robots.remove(robot.getCode());
        return true;
    }

    abstract Boolean pathIsSecure(List<Command> decodedCommands, Robot robot) throws InvalidTransformationTypeException;
}
