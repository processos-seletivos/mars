package com.contaazul.mars.robot.command;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.orientation.*;

/**
 * Comando que gira o robô em 90º para a esquerda
 */
public class LeftCommand extends AbstractCommand {
    @Override
    public Orientation getNewOrientation(Orientation currentOrientation) {
        switch (currentOrientation.getValue()) {
            case "N":
                return new WestOrientation();
            case "W":
                return new SouthOrientation();
            case "S":
                return new EastOrientation();
            case "E":
                return new NorthOrientation();
        }
        return null;
    }

    @Override
    public Coordinate getNewCoordinate(Coordinate currentCoordinate, Orientation orientation) {
        return currentCoordinate;
    }
}
