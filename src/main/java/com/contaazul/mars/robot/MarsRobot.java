package com.contaazul.mars.robot;

import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.orientation.Orientation;
import com.contaazul.mars.robot.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe que representa os robôs enviados para Marte
 */
public class MarsRobot extends AbstractRobot{

    private static final Logger logger = LoggerFactory.getLogger(MarsRobot.class);

    public MarsRobot(String code, CartesianCoordinate coordinate, Orientation orientation) {
        super(code, (Coordinate)coordinate, orientation);
    }

    @Override
    public void executeCommand(Command command) {
        logger.debug("Posição antes de executar o comando da classe " + command.getClass() + ":" + this.getCurrentPositonAndOrientation());
        this.coordinate = command.getNewCoordinate(this.getCoordinate(), this.getOrientation());
        this.orientation = command.getNewOrientation(this.getOrientation());
        logger.debug("Posição após de executar o comando da classe " + command.getClass() + ":" + this.getCurrentPositonAndOrientation());
    }

    @Override
    public String getCurrentPositonAndOrientation() {
        return "(" +
                ((CartesianCoordinate)coordinate).getX() + ", " +
                ((CartesianCoordinate)coordinate).getY() + ", " +
               orientation.getValue() +  ")";
    };
}
