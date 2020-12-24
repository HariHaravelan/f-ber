package com.taxi.fuber.model;

import java.time.LocalDateTime;

public class Car {
    private String plateNumber;
    private Color color;
    private Trip trip;
    private Location currentLocation;
    private double speedKmPh;
    private double STANDARD_SPEED_KM_PH = 60;

    public Car(String plateNumber, Color color, Location location) {
        this.plateNumber = plateNumber;
        this.color = color;
        this.currentLocation = location;
        this.trip = new Trip(LocalDateTime.now(), 0);
        this.speedKmPh = STANDARD_SPEED_KM_PH;
    }

    public Color getColor() {
        return this.color;
    }

    public String getPlateNumber() {
        return this.plateNumber;
    }

    public Location getLocation() {
        return this.currentLocation;
    }

    public boolean isAvailable(LocalDateTime time) {
        return trip.isComplete(time);
    }

    public void updateTrip(Trip trip) {
        this.trip = trip;
    }

    public long getTimeTakenInMinutes(double distanceInKm) {
        return 0;
    }
}

