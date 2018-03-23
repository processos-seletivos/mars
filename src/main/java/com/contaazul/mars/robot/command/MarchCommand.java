package com.contaazul.mars.robot.command;

import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.orientation.Orientation;

/**
 * Comando que faz o robô avançar uma coordenada na direção em que estiver
 */
public class MarchCommand extends AbstractCommand {
    @Override
    public Orientation getNewOrientation(Orientation currentOrientation) {
        return currentOrientation;
    }

    @Override
    public CartesianCoordinate getNewCoordinate(Coordinate currentCoordinate, Orientation currentOrientation) {
        switch (currentOrientation.getValue()) {
            case "N":
                return (CartesianCoordinate)currentCoordinate.applyTransformation(new CartesianCoordinate(0l,1l));
            case "S":
                return (CartesianCoordinate)currentCoordinate.applyTransformation(new CartesianCoordinate(0l,-1l));
            case "E":
                return (CartesianCoordinate)currentCoordinate.applyTransformation(new CartesianCoordinate(1l,0l));
            case "W":
                return (CartesianCoordinate)currentCoordinate.applyTransformation(new CartesianCoordinate(-1l,0l));
        }
        return null;
    }
}
