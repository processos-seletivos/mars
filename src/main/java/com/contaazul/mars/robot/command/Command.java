package com.contaazul.mars.robot.command;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.coordinate.InvalidTransformationTypeException;
import com.contaazul.mars.orientation.Orientation;

/**
 * Interface com os métodos comuns aos commands
 */
public interface Command {

    /**
     * Dada uma orientação, obtém a nova orientação resultante da aplicação do comando
     * @param currentOrientation Orientação atual do robô
     * @return Nova orientação após a aplicação do comando
     */
    public Orientation getNewOrientation(Orientation currentOrientation);

    /**
     * Dada uma coordenada, obtém a nova coordenada resultante da aplicação do comando
     * @param currentCoordinate Coordenada atual do robô
     * @param currentOrientation Orientação atual do robô
     * @return Nova coordenada após a aplicação do comando
     * @throws InvalidTransformationTypeException
     */
    public Coordinate getNewCoordinate(Coordinate currentCoordinate, Orientation currentOrientation);
}
