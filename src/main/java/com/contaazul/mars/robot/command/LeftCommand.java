package com.contaazul.mars.robot.command;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.orientation.Orientation;

public class LeftCommand extends AbstractCommand {
    @Override
    public Orientation getNewOrientation(Orientation currentOrientation) {
        return null;
    }

    @Override
    public Coordinate getNewCoordinate(Coordinate currentCoordinate) {
        return currentCoordinate;
    }
}
