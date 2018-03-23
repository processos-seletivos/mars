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

    public void manageRobot(Robot robot) throws Exception;
    public Boolean validPosition(Coordinate coordinate);
    public void sendCommandListToRobot(String code, List<Command> decodedCommands) throws InvalidPositionException, InvalidTransformationTypeException;


    void removeRobot(Robot robot) throws RobotNotRegisteredException;
}
