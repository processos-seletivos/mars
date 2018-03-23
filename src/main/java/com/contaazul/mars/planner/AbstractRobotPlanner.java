package com.contaazul.mars.planner;

import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.map.Map;
import com.contaazul.mars.orientation.Orientation;
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
    public void manageRobot(Robot robot) throws RobotAlreadyRegisteredException {
        if (robots.containsKey(robot.getCode())){
            throw new RobotAlreadyRegisteredException("A robot with this code has already been registered");
        }
        robots.put(robot.getCode(), robot);
    }

    /**
     *
     * @param code
     * @param decodedCommands
     * @throws InvalidPositionException
     * @throws InvalidTransformationTypeException
     */
    public void sendCommandListToRobot(String code, List<Command> decodedCommands) throws InvalidPositionException, InvalidTransformationTypeException {
        Robot robot = robots.get(code);
        decodedCommands.stream().forEach((Command command) -> {
            robot.executeCommand(command);
        });

    }


    public Boolean validPosition(Coordinate coordinate) {
        Boolean isValid = false;
        if (map.validPosition(coordinate) && isAvailablePosition(coordinate)){
            isValid = true;
        }
        return isValid;
    }

    Boolean isAvailablePosition(Coordinate coordinate) {
        Boolean isAvailable = true;
        for (Robot robot : robots.values()){
            if (robot.getCoordinate().equals(coordinate)){
                isAvailable = false;
                break;
            }
        }
        return isAvailable;
    }


    public void removeRobot(Robot robot) throws RobotNotRegisteredException {
        if (!robots.containsKey(robot.getCode())){
            throw new RobotNotRegisteredException("A robot with this code is not registered");
        }
        robots.remove(robot.getCode());
    }

    abstract Boolean pathIsSecure(List<Command> decodedCommands, CartesianCoordinate robotCoordinate, Orientation robotOrientation) throws InvalidTransformationTypeException;
}
