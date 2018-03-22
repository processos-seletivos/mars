package com.contaazul.mars.coordinate;

public class CartesianCoordinate extends AbstractCoordinate {
    Long x;
    Long y;

    CartesianCoordinate(Long x, Long y){
        this.x = x;
        this.y = y;
    }

    @Override
    public Coordinate applyTransformation(Coordinate transformation) {
        CartesianCoordinate cartesianTransformation = null;
        CartesianCoordinate newCoordinate = null;
        try {
            cartesianTransformation = (CartesianCoordinate) transformation;
            newCoordinate = new CartesianCoordinate(
                    (x+cartesianTransformation.getX()),
                    (y+cartesianTransformation.getY())
            );

        }
        catch (Exception e) {
            //TODO: Launch transformation exception
        }
        return newCoordinate;
    }

    Long getX(){
        return this.x;
    }
    Long getY(){
        return this.x;
    }
}
