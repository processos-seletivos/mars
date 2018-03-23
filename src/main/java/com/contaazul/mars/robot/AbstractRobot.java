package com.contaazul.mars.robot;

import com.contaazul.mars.coordinate.Coordinate;
import com.contaazul.mars.orientation.Orientation;


/**
 * Classe abstrata que representa um robô genérico. Não podem ser criados objetos dessa classe, mas as subclasses
 * já herdarão seus atributos e implementações de métodos
 */
public abstract class AbstractRobot implements Robot{
    private String code;

    Coordinate coordinate;
    Orientation orientation;

    AbstractRobot(String code, Coordinate coordinate, Orientation orientation) {
        this.code = code;
        this.coordinate = coordinate;
        this.orientation = orientation;
    }

    @Override
    public Coordinate getCoordinate() {
        return coordinate;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }

    @Override
    public String getCode() {
        return code;
    }


}
