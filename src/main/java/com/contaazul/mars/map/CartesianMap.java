package com.contaazul.mars.map;

import com.contaazul.mars.coordinate.CartesianCoordinate;
import com.contaazul.mars.coordinate.Coordinate;

public class CartesianMap extends AbstractMap{
    Long minX = 0l;
    Long minY = 0l;

    Long maxX = 4l;
    Long maxY = 4l;


    @Override
    public boolean validPosition(Coordinate coordinate) {
        Boolean isValid = false;
        CartesianCoordinate cartesianCoordinate = (CartesianCoordinate)coordinate;
        if ((cartesianCoordinate.getX() <= maxX && cartesianCoordinate.getY() <=maxY) &&
                (cartesianCoordinate.getX() >= minX && cartesianCoordinate.getY() >=minY)) {
            isValid = true;

        }
        return isValid;
    }
}
