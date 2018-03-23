package com.contaazul.mars.coordinate;

/**
 * Interface com os métodos comuns a qualquer tipo de coordenada
 */
public interface Coordinate {

    /**
     * Dada uma transformação, aplica ela na própria coordenada, retornando o resultado da transformação, sem contudo,
     * alterar o próprio objeto.
     * @param transformation Transformação a ser aplicada na coordenada
     * @return Coordenada transformada
     * @throws InvalidTransformationTypeException Exceção lançada caso a transformação aplicada tenha um tipo diferente
     * da coordenada
     */

    public Coordinate applyTransformation(Coordinate transformation) throws InvalidTransformationTypeException;
}
