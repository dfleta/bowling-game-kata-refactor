package edu.gorillas;

public enum SYMBOLS {
    STRIKE(10), SPARE(10), ZERO(0);

    private int value;

    private SYMBOLS(int value) {
        this.value = value;
    };

    public int getValue() {
        return this.value;
    }

}
