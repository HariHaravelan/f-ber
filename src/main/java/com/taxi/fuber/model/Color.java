package com.taxi.fuber.model;

public enum Color {
    PINK(5), OTHERS(0);

    private int additionalCharge;

    Color(int additionalCharge) {
        this.additionalCharge = additionalCharge;
    }

    public int getAdditionalCharge() {
        return additionalCharge;
    }
}
