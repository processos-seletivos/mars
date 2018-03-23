package com.contaazul.mars.orientation;

/**
 * Classe abstrata que implementa métodos comuns as orientações
 */
public class AbstractOrientation implements Orientation {
    private String value;

    public AbstractOrientation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
