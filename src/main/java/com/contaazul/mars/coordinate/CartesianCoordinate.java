package com.contaazul.mars.coordinate;

/**
 * Classe que representa uma coordenada cartesiana (X,Y)
 */
public class CartesianCoordinate extends AbstractCoordinate {
    Long x;
    Long y;

    public CartesianCoordinate(Long x, Long y){
        this.x = x;
        this.y = y;
    }

    @Override
    public CartesianCoordinate applyTransformation(Coordinate transformation) throws InvalidTransformationTypeException {
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
            throw  new InvalidTransformationTypeException("O tipo da transformação é diferente de Cartesiana");
        }
        return newCoordinate;
    }

    /**
     * Método para obter a posição no eixo x
     * @return Posição no eixo x
     */
    public Long getX(){
        return this.x;
    }

    /**
     * Método para obter a posição no eixo y
     * @return Posição no eixo y
     */
    public Long getY(){
        return this.y;
    }
}
