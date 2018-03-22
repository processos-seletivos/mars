package com.contaazul.mars.robot.command;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.orientation.Orientation;

public interface Command {

    public Orientation getNewOrientation(Orientation currentOrientation);

    public Coordinate getNewCoordinate(Coordinate currentCoordinate);
}
