package com.contaazul.mars.coordinate;

import com.contaazul.mars.MarsController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe que representa uma coordenada cartesiana (X,Y)
 */
public class CartesianCoordinate extends AbstractCoordinate {
    private static final Logger logger = LoggerFactory.getLogger(MarsController.class);
    Long x;
    Long y;

    public CartesianCoordinate(Long x, Long y){
        this.x = x;
        this.y = y;
    }

    @Override
    public CartesianCoordinate applyTransformation(Coordinate transformation){
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
            logger.error("Nao foi possível aplicar a transformação pelo seguinte motivo: " + e.getMessage());
            return this;
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
