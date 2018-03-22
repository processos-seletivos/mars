package com.contaazul.mars.robot.command;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.orientation.Orientation;

public class MarchCommand extends AbstractCommand {
    @Override
    public Orientation getNewOrientation(Orientation currentOrientation) {
        return currentOrientation;
    }

    @Override
    public Coordinate getNewCoordinate(Coordinate currentCoordinate) {
        return null;
    }
}
