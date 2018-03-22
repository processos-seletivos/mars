package com.contaazul.mars.robot;

import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.robot.command.Command;

public class MarsRobot extends AbstractRobot{

    MarsRobot(CartesianCoordinate coordinate) {
        super((Coordinate)coordinate);
    }

    @Override
    public void executeCommand(Command command) {
        this.coordinate = command.getNewCoordinate(this.getCoordinate());
        this.orientation = command.getNewOrientation(this.getOrientation());
    }
}
