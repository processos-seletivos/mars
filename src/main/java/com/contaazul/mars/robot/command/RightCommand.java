package com.contaazul.mars.robot.command;

import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.orientation.*;

/**
 * Comando que gira o robô em 90º para a direita
 */
public class RightCommand extends AbstractCommand {

    @Override
    public Orientation getNewOrientation(Orientation currentOrientation) {
        switch (currentOrientation.getValue()) {
            case "N":
                return new EastOrientation();
            case "W":
                return new NorthOrientation();
            case "S":
                return new WestOrientation();
            case "E":
                return new SouthOrientation();

        }
        return null;
    }

    @Override
    public CartesianCoordinate getNewCoordinate(Coordinate currentCoordinate, Orientation orientation) {
        return (CartesianCoordinate)currentCoordinate;
    }
}
