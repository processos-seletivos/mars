package com.contaazul.mars.planner;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.robot.Robot;
import com.contaazul.mars.robot.command.Command;

import java.util.List;

/**
 * Interface com os métodos comuns aos robot planners.
 */
public interface RobotPlanner {

    public Boolean manageRobot(Robot robot) throws Exception;
    public Boolean validPosition(Coordinate coordinate, Robot robot);
    public void sendCommandListToRobot(String code, List<Command> decodedCommands) throws InvalidPositionException, InvalidTransformationTypeException, RobotNotRegisteredException;
    public Boolean removeRobot(Robot robot) throws RobotNotRegisteredException;
}
