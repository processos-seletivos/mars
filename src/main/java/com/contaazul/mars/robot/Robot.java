package com.contaazul.mars.robot;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.orientation.Orientation;
import com.contaazul.mars.robot.command.Command;

/**
 * Interface com os métodos comuns a todos os tipos de robôs
 */
public interface Robot {

    public Coordinate getCoordinate();
    public Orientation getOrientation();

    public void executeCommand(Command command);

    public String getCode();

    public String getCurrentPositonAndOrientation();
}
