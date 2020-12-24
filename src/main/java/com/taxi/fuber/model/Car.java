package com.taxi.fuber.model;

import java.time.LocalDateTime;

public class Car {
    private String plateNumber;
    private Color color;
    private Location currentLocation;
    private double speedKmPh;
    private LocalDateTime nextAvailableAt;

    public Car(String plateNumber, Color color, Location location) {
        this.plateNumber = plateNumber;
        this.color = color;
        this.currentLocation = location;
        this.speedKmPh = TripRule.STANDARD_SPEED_KM_PH;
        this.nextAvailableAt = LocalDateTime.now();
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
        return nextAvailableAt.isBefore(time);
    }

    public double calculateTripCharge(double distanceOfTrip) {
        return getTimeTakenInMinutes(distanceOfTrip) * TripRule.CHARGE_PER_MINUTE +
                distanceOfTrip * TripRule.CHARGE_PER_KM + this.color.getAdditionalCharge();
    }

    public double getTimeTakenInMinutes(double distanceInKm) {
        return (distanceInKm / this.speedKmPh) * 60;
    }
}

