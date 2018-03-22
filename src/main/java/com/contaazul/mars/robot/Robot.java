package com.contaazul.mars.robot;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.orientation.Orientation;
import com.contaazul.mars.robot.command.Command;

public interface Robot {

    public Coordinate getCoordinate();
    public Orientation getOrientation();

    public void executeCommand(Command command);
}
