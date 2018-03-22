package com.contaazul.mars.orientation;

public class AbstractOrientation implements Orientation {
    private String value;

    public AbstractOrientation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
