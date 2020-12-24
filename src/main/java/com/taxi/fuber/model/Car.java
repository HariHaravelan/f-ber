package com.taxi.fuber.model;

import java.time.LocalDateTime;

public class Car {
    private String plateNumber;
    private Color color;
    private Trip trip;
    private Location currentLocation;

    public Car(String plateNumber, Color color, Location location) {
        this.plateNumber = plateNumber;
        this.color = color;
        this.currentLocation = location;
        this.trip = new Trip(LocalDateTime.now(), 0);
    }

    public Color getColor() {
        return this.color;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }
}

