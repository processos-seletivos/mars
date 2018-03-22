package com.contaazul.mars.robot;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.orientation.Orientation;

public abstract class AbstractRobot implements Robot{

    Coordinate coordinate;
    Orientation orientation;

    AbstractRobot(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }
}
